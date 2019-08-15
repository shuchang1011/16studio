package com.controller;

import java.sql.SQLSyntaxErrorException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.entity.Account;
import com.entity.Member;
import com.entity.Organization;
import com.entity.Role;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.service.AccountService;
import com.service.MemberService;
import com.service.OrganizationService;
import com.service.PasswordService;
import com.service.RoleService;
import com.wordnik.swagger.annotations.Api;

@Api(value="restful",description="账户Api")
@Controller
//定义一个非静态成员变量时候，则通过注解@Scope("prototype")，将其设置为多例模式。追求性能时，使用单例
@RequestMapping(value="/account") 
public class AccountController {

	private static Logger logger = Logger.getLogger(AccountController.class); 
	
	@Autowired@Qualifier("AccountServiceImpl")
	private AccountService accountService;
	
	@Autowired@Qualifier("PasswordService")
	private PasswordService passwordService;
	
	@Autowired@Qualifier("RoleServiceImpl")
	private RoleService roleService;
	
	@Autowired@Qualifier("OrganizationServiceImpl")
	private OrganizationService organizationService;
	
	@Autowired@Qualifier("MemberServiceImpl")
	private MemberService memberService;
	
	@RequestMapping(value="/userView",method = RequestMethod.GET)
    public String userView(HttpSession session,@RequestParam(required = false,defaultValue = "1",value = "pn")Integer pn,Model model) {
    	logger.info("查看账号信息");
    	Account account = (Account) session.getAttribute("user");
    	List<Account> userList;
    	if((account.getRole().getName()).equals("超级管理员")) {
    		PageHelper.startPage(pn, 5);
        	userList = accountService.findAll();
    	}else {
    		PageHelper.startPage(pn, 5);
        	userList = accountService.findAllInOrganization(accountService.findPersonalDataById(account.getId()).getOrganizationId());
    	}
    	for(int i=0;i<userList.size();i++) {
    		userList.get(i).setRole(accountService.findRole(userList.get(i).getAccountName()));
    		userList.get(i).setMember(accountService.findPersonalDataById(userList.get(i).getId()));
    		userList.get(i).setOrganization(accountService.findOrganizationById(userList.get(i).getId()));
    	}
    	PageInfo<Account> pageInfo = new PageInfo<>(userList,5);
    	model.addAttribute("pageInfo", pageInfo);
        return "user";
    }
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
    public String showCreateForm(HttpSession session,Model model) {
		Account account = (Account) session.getAttribute("user");
		List<Role> roleList;
		List<Organization> organizationList = organizationService.findAll();
		if((account.getRole().getName()).equals("超级管理员")) {
			roleList = roleService.findAll();
		}else {
			roleList = roleService.findAllOverLevel(accountService.findRole(account.getAccountName()).getLevel());
		}
		model.addAttribute("roleList", roleList);
		model.addAttribute("organizationList", organizationList);
        return "user/create";
    }

	@ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Map<String,Object> create(@RequestParam("accountName")String accountName, @RequestParam("displayName")String displayName,
					  @RequestParam("password")String password, @RequestParam("roleId")String roleId, @RequestParam("gender")String gender,
					  @RequestParam("age")int age, @RequestParam("mobile")String mobile, @RequestParam("email")String email,
					  @RequestParam("organizationId")String organizationId) {
    	logger.info("创建账号信息");
    	Map<String,Object> map = new HashMap<String, Object>();
    	Account account = new Account();
    	Member member = new Member();
    	account.setAccountName(accountName);
    	account.setDisplayName(displayName);
    	account.setPassword(password);
        try {
        	if(accountService.findAccountByName(account.getAccountName())!=null) {
        		map.put("msg", "用户名已存在");
        		return map;
        	}else {
        		accountService.createAccount(account);
        	}
		} catch (SQLSyntaxErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        member.setAccountId(accountService.findAccountByName(accountName).getId());
        member.setOrganizationId(organizationId);
        member.setGender(gender);
        member.setAge(age);
        member.setMobile(mobile);
        member.setEmail(email);
		try {
			memberService.createMember(member);
			memberService.createRoleMember(roleId, memberService.findOneByAccountId(member.getAccountId()).getId());
			map.put("msg", "新增成功");
		} catch (Exception e) {
			map.put("msg", e);
		}
		return map;
    }
    
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String UpdateForm(@PathVariable("id") String id, Model model) {
    	List<Role> roleList = roleService.findAll();
		List<Organization> organizationList = organizationService.findAll();
    	Account account = accountService.findAccountById(id);
    	Role role = accountService.findRole(account.getAccountName());
    	Member member = memberService.findOneByAccountId(id);
    	Organization organization = organizationService.findOne(member.getOrganizationId());
    	model.addAttribute("account", account);
    	model.addAttribute("role", role);
    	model.addAttribute("member", member);
        model.addAttribute("organization", organization);
        model.addAttribute("roleList", roleList);
		model.addAttribute("organizationList", organizationList);
        return "user/update";
    }

	@ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public Map<String,Object> update(@RequestParam("id")String accountId,@RequestParam("accountName")String accountName,@RequestParam("displayName")String displayName,
    		@RequestParam("password")String password,@RequestParam("roleId")String roleId,@RequestParam("gender")String gender,
    		@RequestParam("age")int age,@RequestParam("mobile")String mobile,@RequestParam("email")String email,
    		@RequestParam("organizationId")String organizationId) {
		logger.info("修改帐户信息");
		Map<String,Object> map = new HashMap<String, Object>();
		Account account = new Account();
    	Member member = new Member();
    	account.setId(accountId);
    	account.setAccountName(accountName);
    	account.setDisplayName(displayName);
		try {
			String Password = accountService.findAccountById(accountId).getPassword();
			if(passwordService.encryptPassword(account.getAccountName(), password).equals(Password)) {
				map.put("msg", "密码不能和之前的密码相同");
				return map;
			}else if(Password.equals(password)){
				account.setPassword(password);
			}else {
				account.setPassword(password);
				passwordService.encryptPassword(account);
			}
			accountService.updateAccount(account);
			member.setAccountId(accountId);
			member.setOrganizationId(organizationId);
			member.setGender(gender);
			member.setAge(age);
			member.setMobile(mobile);
			member.setEmail(email);
			memberService.updateMember(member);
			memberService.updateRoleMember(roleId,member.getId());
			map.put("msg", "修改成功");
		} catch (Exception e) {
			map.put("msg", e);
		}
		return map;
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") String id,Model model) {
        accountService.deleteAccount(id);
        model.addAttribute("msg", "删除成功");
        return "msg";
    }
	
}
