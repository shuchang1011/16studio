package com.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.entity.Account;
import com.entity.Cultureaspect;
import com.entity.Operation;
import com.entity.Village;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.service.CultureaspectService;
import com.service.HistoryService;
import com.service.MemberService;
import com.service.OrganizationService;
import com.service.VillageService;
import com.wordnik.swagger.annotations.Api;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Api(value="restful",description="村落Api")
@Controller
@RequestMapping(value="/village")
public class VillageController {

	private static Logger logger = Logger.getLogger(AccountController.class); 
	
	@Autowired@Qualifier("VillageServiceImpl")
	private VillageService villageService;
	
	@Autowired@Qualifier("OrganizationServiceImpl")
	private OrganizationService organizationService;
	
	@Autowired@Qualifier("CultureaspectServiceImpl")
	private CultureaspectService cultureaspectService;
	
	@Autowired@Qualifier("MemberServiceImpl")
	private MemberService memberService;
	
	@Autowired@Qualifier("HistoryServiceImpl")
	private HistoryService historyService;
	
	@RequestMapping(value="/information",method=RequestMethod.GET)
	public String showInfo(HttpSession session,Model model) {
		Account account = (Account) session.getAttribute("user");
		model.addAttribute("user", account);
		return "information";
	}
	
	@RequestMapping(value="/villageView",method = RequestMethod.GET)
	public String showVillage(@RequestParam(value="msg",required = false)String msg,@RequestParam(required = false,defaultValue = "1",value = "pn")Integer pn,Model model) {
		logger.info("查看村落信息");
		PageHelper.startPage(pn, 5);
    	List<Village> villageList = villageService.findAll();
    	for(int i = 0;i < villageList.size();i++) {
    		if(!villageList.get(i).getOrganizationId().equals(""))
    		villageList.get(i).setOrganization(organizationService.findOne(villageList.get(i).getOrganizationId()).getName());
    	}
    	PageInfo<Village> pageInfo = new PageInfo<>(villageList,5);
    	if(msg!=null){
			try {
				msg = new String(msg.getBytes("iso8859-1"),"UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			logger.info(msg);
		}
    	model.addAttribute("msg",msg);
    	model.addAttribute("organizationId", villageList.get(0).getOrganizationId());
    	model.addAttribute("pageInfo", pageInfo);
        return "village";
	}

	@RequestMapping(value="/cultureaspectView")
	public String showCultureaspect(@RequestParam(required = false,defaultValue = "1",value = "pn")Integer pn,Model model) {
		logger.info("查看文化方面");
		PageHelper.startPage(pn, 5);
		List<Cultureaspect> cultureaspectViewlist = cultureaspectService.findAll();
		PageInfo<Cultureaspect> pageInfo = new PageInfo<>(cultureaspectViewlist,5);
		model.addAttribute("pageInfo", pageInfo);
		return "cultureaspect";
	}
	
	@RequestMapping(value="/createVillage",method = RequestMethod.GET)
    public String showCreateVillage(HttpSession session,Model model) {
    	Account account = (Account)session.getAttribute("user");
    	logger.info("accountId  "+account.getId());
    	String organizationId = memberService.findOneByAccountId(account.getId()).getOrganizationId();
    	logger.info("organizationId  "+organizationId);
    	model.addAttribute("organizationId", organizationId);
    	return "village/create";
    }

    @ResponseBody
    @RequestMapping(value="/createVillage",method = RequestMethod.POST)
    public Map<String,Object> createVillage(@RequestParam("organizationId")String organizationId,@ModelAttribute Village village) {
    	logger.info("创建村落信息");
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			village.setOrganizationId(organizationId);
			villageService.createVillage(village);
			map.put("msg", "新增成功");
		} catch (Exception e) {
			map.put("msg", e);
		}
		return map;
    }
    
    @RequestMapping(value="/createCultureaspect",method = RequestMethod.GET)
    public String showCreateCultureaspect() {
    	return "cultureaspect/create";
    }

    @ResponseBody
    @RequestMapping(value="/createCultureaspect",method = RequestMethod.POST)
    public Map<String,Object> createCultureaspect(@ModelAttribute Cultureaspect cultureaspect) {
    	logger.info("创建文化类别信息");
		Map<String,Object> map = new HashMap<String, Object>();

		try {
			cultureaspectService.createCultureaspect(cultureaspect);
			map.put("msg", "新增成功");
		} catch (Exception e) {
			map.put("msg",e);
		}

    	return map;
    }
    
    @RequestMapping(value = "/updateVillage", method = RequestMethod.GET)
    public String UpdateVillageForm(HttpSession session, @RequestParam("id") String id, Model model, RedirectAttributes attr) {
    	Account account = (Account) session.getAttribute("user");
    	if(account.getRole().getName().equals("超级管理员")) {
    		model.addAttribute("id", id);
    		return "village/update";
    	}else {
    		//判断是否为当前机构创建的村落信息，否则无法修改
    		if(villageService.findOne(id).getOrganizationId().equals(memberService.findOneByAccountId(account.getId()).getOrganizationId())) {
    			model.addAttribute("id", id);
				return "village/update";
    		}else {
    			attr.addAttribute("msg", "当前村落信息非本机构创建，无法修改");
    			return "redirect:/village/villageView";
    		}
    	}
    }

    @ResponseBody
    @RequestMapping(value = "/updateVillage", method = RequestMethod.PUT)
    public Map<String,Object> updateVillage(@ModelAttribute Village village) {
    	logger.info("修改村落信息");
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			Village village2 = villageService.findOne(village.getId());
			Operation operation = new Operation();
			operation.setVillageId(village.getId());
			operation.setTarget("village");
			operation.setTargetId(village.getId());
			operation.setOperation("modify " + village2.getName());
			operation.setId(UUID.randomUUID().toString());
			historyService.createOperation(operation);
			village2.setOperationId(operation.getId());
			historyService.createVillageHistory(village2);
			villageService.updateVillage(village);
			map.put("msg", "修改成功");
		} catch (Exception e) {
			map.put("msg", e);
		}
		return map;
    }
    
    @RequestMapping(value = "/updateCultureaspect/{id}", method = RequestMethod.GET)
    public String UpdateCultureaspectForm(@PathVariable("id") String id, Model model) {
        model.addAttribute("id", id);
        return "cultureaspect/update";
    }

    @ResponseBody
    @RequestMapping(value = "/updateCultureaspect", method = RequestMethod.PUT)
    public Map<String,Object> updateCultureaspect(@ModelAttribute Cultureaspect cultureaspect) {
    	logger.info("修改文化类别信息");
		Map<String,Object> map = new HashMap<String, Object>();

		try {
			cultureaspectService.updateCultureaspect(cultureaspect);
			map.put("msg", "修改成功");
		} catch (Exception e) {
			map.put("msg", e);
		}

        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteVillage", method = RequestMethod.DELETE)
    public Map<String,Object> deleteVillage(HttpSession session,@RequestParam("id") String id) {
    	logger.info("删除村落");
		Map<String,Object> map = new HashMap<String, Object>();
    	Account account = (Account) session.getAttribute("user");
		try {
			if(account.getRole().getName().equals("超级管理员")) {
				villageService.deleteVillage(id);
				map.put("msg", "删除成功");
				return map;
			}else {
				//判断是否为当前机构创建的村落信息，否则无法删除
				if(villageService.findOne(id).getOrganizationId().equals(memberService.findOneByAccountId(account.getId()).getOrganizationId())) {
					villageService.deleteVillage(id);
					map.put("msg", "删除成功");
					return map;
				}else {
					map.put("msg", "当前村落信息非本机构创建，无法修改");
					return map;
				}
			}
		} catch (Exception e) {
			map.put("msg",e);
		}
		return map;
	}

    @ResponseBody
    @RequestMapping(value = "/deleteCultureaspect", method = RequestMethod.DELETE)
    public Map<String,Object> deleteCultureaspect(@RequestParam("id") String id) {
    	logger.info("删除文化类别");
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			cultureaspectService.deleteCultureaspect(id);
			map.put("msg", "删除成功");
		} catch (Exception e) {
			map.put("msg", e);
		}
		return map;
    }
}
