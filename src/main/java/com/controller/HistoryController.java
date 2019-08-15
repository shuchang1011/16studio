package com.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.entity.Account;
import com.entity.Operation;
import com.entity.Village;
import com.entity.history;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.service.HistoryService;
import com.service.MemberService;
import com.service.OrganizationService;
import com.service.VillageService;
import com.wordnik.swagger.annotations.Api;
import org.springframework.web.bind.annotation.ResponseBody;

@Api(value="restful",description="历史详情Api")
@Controller
@RequestMapping(value="/history")
public class HistoryController {

	private static Logger logger = Logger.getLogger(HistoryController.class);
	
	@Autowired@Qualifier("HistoryServiceImpl")
	private HistoryService historyService;
	
	@Autowired@Qualifier("VillageServiceImpl")
	private VillageService villageService;
	
	@Autowired@Qualifier("OrganizationServiceImpl")
	private OrganizationService organizationService;
	
	@Autowired@Qualifier("MemberServiceImpl")
	private MemberService memberService;
	
	/**
	 * @param villageId
	 * @param target
	 * @param pn
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/getHistoryOperation",method=RequestMethod.GET)
	public String getHistoryOperation(@RequestParam("villageId")String villageId,@RequestParam("target")String target
			,@RequestParam(required = false,defaultValue = "1",value = "pn")Integer pn,Model model) {
		logger.info("获取村落修改信息");
		PageHelper.startPage(pn, 5);
		List<Operation> operationList = historyService.findAllOperation(villageId, target);
		PageInfo<Operation> pageInfo = new PageInfo<>(operationList,5);
		model.addAttribute("pageInfo", pageInfo);
		return "history/operation";
	}
	
	@RequestMapping(value="/getDeletedVillage",method=RequestMethod.GET)
	public String getDeletedVillage(HttpSession session,@RequestParam(required = false,defaultValue = "1",value = "pn")Integer pn
			,Model model) {
		logger.info("查看已删除村落");
		Account account = (Account) session.getAttribute("user");
		if(account.getRole().getName().equals("超级管理员")) {
			PageHelper.startPage(pn, 5);
			List<Village> list = villageService.findAllDeletedVillage();
			PageInfo<Village> pageInfo = new PageInfo<>(list,5);
			model.addAttribute("pageInfo", pageInfo);
		}else {
			String organizationId = memberService.findOneByAccountId(account.getId()).getOrganizationId();
			PageHelper.startPage(pn, 5);
			List<Village> villageList = villageService.findDeletedVillage(organizationId);
			PageInfo<Village> pageInfo = new PageInfo<>(villageList,5);
			model.addAttribute("pageInfo", pageInfo);
		}
		return "history/deletedVillage";
	}
	 
	@RequestMapping(value="/villageChangeView",method=RequestMethod.GET)
	public String villageChangeView(HttpSession session,@RequestParam(required = false,defaultValue = "1",value = "pn")Integer pn
			,Model model) {
		logger.info("查看村落修改信息");
		Account account = (Account) session.getAttribute("user");
		if(account.getRole().getName().equals("超级管理员")) {
			PageHelper.startPage(pn, 5);
			List<Village> list = villageService.findAll();
			PageInfo<Village> pageInfo = new PageInfo<>(list,5);
			model.addAttribute("pageInfo", pageInfo);
		}else {
			String organizationId = memberService.findOneByAccountId(account.getId()).getOrganizationId();
			PageHelper.startPage(pn, 5);
			List<Village> list = villageService.findAllByOrganizationId(organizationId);
			PageInfo<Village> pageInfo = new PageInfo<>(list,5);
			model.addAttribute("pageInfo", pageInfo);
		}
		return "history/village";
	}
	
	@RequestMapping(value="/getOperationDetail",method=RequestMethod.GET)
	public String getOperationDetail(@RequestParam("operationId")String operationId,Model model) {
		Operation operation = historyService.findOperation(operationId);
		Village villageHistory = historyService.findVillageByOperationId(operationId);
		Village village = villageService.findOne(operation.getVillageId());
		village.setOrganization(organizationService.findOne(village.getOrganizationId()).getName());
		villageHistory.setOrganization(organizationService.findOne(villageHistory.getOrganizationId()).getName());
		logger.info("village");
		model.addAttribute("village", village);
		model.addAttribute("operation", operation);
		model.addAttribute("villageHistory", villageHistory);
		return "history/historyDetail";
	}
	
	@RequestMapping(value="/restoreHistory",method=RequestMethod.POST)
	public String restoreHistory(@RequestParam("operationId")String operationId,Model model) {
		
		Village village = historyService.findVillageByOperationId(operationId);
		logger.info("restoreVillage");
		historyService.restoreVillage(village);
		logger.info("deleteOperation");
		historyService.deleteOperation(operationId);
		logger.info("deleteVillage");
		historyService.deleteVillage(operationId);
		logger.info("success");
		model.addAttribute("msg", "已还原历史修改数据");
		return "msg";
	}

	@ResponseBody
	@RequestMapping(value="/recoveryHistory",method=RequestMethod.POST)
	public Map<String,Object> recoveryHistory(@RequestParam("villageId")String villageId, Model model) {
		logger.info("恢复历史数据");
		Map<String,Object> map = new HashMap<String, Object>();

		try {
			historyService.recoveryVillage(villageId);
			map.put("msg", "已还原历史修改数据");
		} catch (Exception e) {
			map.put("msg", e);
		}

		return map;
	}
}