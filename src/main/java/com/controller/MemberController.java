package com.controller;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.entity.Account;
import com.entity.Member;
import com.service.MemberService;
import com.service.OrganizationService;
import com.wordnik.swagger.annotations.Api;

@Api(value="restful",description="用户Api")
@Controller
@RequestMapping(value="/member")
public class MemberController {

	private static Logger logger = Logger.getLogger(MemberController.class);
	
	@Autowired@Qualifier("MemberServiceImpl")
	private MemberService memberService;
	
	@Autowired@Qualifier("OrganizationServiceImpl")
	private OrganizationService organizationService;
	
	@RequestMapping(value="/memberView",method = RequestMethod.GET)
	public String MemberView(HttpSession session,Model model) {
		logger.info("查看个人信息");
		Account account = (Account) session.getAttribute("user");
		logger.info("account = "+ToStringBuilder.reflectionToString(account, ToStringStyle.JSON_STYLE));
		Member member = memberService.findOneByAccountId(account.getId());
		if(member.getOrganizationId()!=null) {
			String organization = organizationService.findOne(member.getOrganizationId()).getName();
			member.setOrganization(organization);
		}
		member.setAccount(account);
		logger.info("member = "+ToStringBuilder.reflectionToString(member, ToStringStyle.JSON_STYLE));
		model.addAttribute("member",member);
		return "member";
	}
	
	@RequestMapping(value="/editMember/{accountId}",method = RequestMethod.GET)
	public String editMember(@PathVariable("accountId")String accountId,Model model) {
		model.addAttribute("accountId", accountId);
		if(memberService.findOneByAccountId(accountId)!=null) {
			return "member/update";
		}else return "member/create";
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@ModelAttribute Member member, Model model) {
    	logger.info("创建个人信息");
		memberService.createMember(member);
        model.addAttribute("msg", "新增成功");
        return "msg";
    } 
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
    public String update(HttpSession session,@ModelAttribute Member member, Model model) {
		logger.info("修改个人信息");
		member.setAccountId(((Account)session.getAttribute("user")).getId());
        memberService.updateMember(member);
        model.addAttribute("msg", "修改成功");
        return "msg";
    }
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") String id,Model model) {
		logger.info("删除个人信息");
        memberService.deleteMember(id);
        model.addAttribute("msg", "删除成功");
        return "msg";
    }
	
	
	
}
