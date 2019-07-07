package com.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.entity.Account;
import com.service.AccountService;
import com.util.CustomerContextHolder;
import com.wordnik.swagger.annotations.Api;

@Api(value="restful",description="登陆Api")
@Controller
@RequestMapping(value="/Login")
@SessionAttributes(value= {"user"})
public class LoginController {

	private static Logger logger = Logger.getLogger(LoginController.class);
	
	private Subject subject;
	
	@Autowired@Qualifier("AccountServiceImpl")
	private AccountService accountService;
	
	@RequestMapping(value="/home")
	public String navigateToHome() {
		System.out.println("跳转Login方法");
		return "home";
	}
	
	@RequestMapping(value="/home1")
	public String navigateToHome1() {
		System.out.println("跳转Login方法");
		return "home1";
	}
	
	@RequestMapping(value="/home2")
	public String navigateToHome2() {
		System.out.println("跳转Login方法");
		return "home2";
	}
	
	@RequestMapping(value="/login")
	public String navigateTo() {
		System.out.println("跳转Login方法");
		return "login";
	}
	
	@RequestMapping(value="/index")
	public String index() {
		System.out.println("跳转index方法");
		return "index";
	}
	
	@ResponseBody
	@RequestMapping(value="/logon",method=RequestMethod.POST)
	public ModelAndView login(@RequestParam("username")String accountName,
			@RequestParam("password")String password,ModelAndView mv) {
		//设置默认数据源
		System.out.println("进入Login方法");
		CustomerContextHolder.setCustomerType(CustomerContextHolder.DATASOURCE_A); 
		if(accountName == null){
			mv.addObject("message", "用户名不能为空");
            mv.setViewName("login");
            return mv;
        }
        //主体,当前状态为没有认证的状态“未认证”
		
        // 登录后存放进shiro token
		
        UsernamePasswordToken token=new UsernamePasswordToken(accountName,password);
        Account account;
        //登录方法（认证是否通过）
        //使用subject调用securityManager,安全管理器调用Realm
        try {
            //需要开始调用到Realm中
            System.out.println("========================================");
            System.out.println("1、进入认证方法");
            System.out.println(accountService.findAll());
            subject = SecurityUtils.getSubject();
            subject.login(token);
            account = accountService.findAccountByName(accountName);
            account.setMember(accountService.findPersonalDataById(account.getId()));
            account.setRole(accountService.findRole(account.getAccountName()));
            account.setOrganization(accountService.findOrganizationById(account.getId()));
            mv.addObject("user",account);
            mv.addObject("message", "登录成功！");
            mv.setViewName("index");
            logger.info("登陆系统");
        } catch (UnknownAccountException e) {
            mv.addObject("message", "账号/密码不正确");
            mv.setViewName("login");
        	
        } catch(IncorrectCredentialsException e) {
        	mv.addObject("message", "账号/密码不正确");
        	mv.setViewName("login");
        	
        } catch(LockedAccountException e) {
        	mv.addObject("message", "账号被锁定");
        	mv.setViewName("login");
        }
        return mv;
		
	}
	
	@RequestMapping(value="/error")
	public String hasNoPermission() {
		return "error";
	}
	
	@RequestMapping(value="/logout")
	public String logout() {
		logger.info("登出系统");
		subject.logout();
		return "login";
	}
	
}
