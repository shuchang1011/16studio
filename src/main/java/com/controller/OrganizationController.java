package com.controller;

import java.sql.SQLSyntaxErrorException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.entity.Account;
import com.entity.Organization;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.service.OrganizationService;
import com.wordnik.swagger.annotations.Api;

@Api(value="restful",description="组织Api")
@Controller
@RequestMapping(value="/organization")
public class OrganizationController {

	private static Logger logger = Logger.getLogger(OrganizationController.class);
	
	@Autowired@Qualifier("OrganizationServiceImpl")
	private OrganizationService organizationService;
	
	@RequestMapping(value="/organizationView",method = RequestMethod.GET)
	public String organizationView(@RequestParam(required = false,defaultValue = "1",value = "pn")Integer pn,Model model) {
		logger.info("查看机构信息");
		PageHelper.startPage(pn, 5);
		List<Organization> organizationList = organizationService.findAll();
		PageInfo<Organization> pageInfo = new PageInfo<>(organizationList,5);
		model.addAttribute("pageInfo", pageInfo);
		return "organization";
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
    public String showCreateForm() {
        return "organization/create";
    }

    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Map<String,Object> create(@ModelAttribute Organization organization, Model model) {
    	logger.info("创建机构信息");
        Map<String,Object> map = new HashMap<String, Object>();

        try {
            organizationService.createOrganization(organization);
            map.put("msg", "新增成功");
        } catch (Exception e) {
            map.put("msg", e);
        }

        return map;
    }
    
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String UpdateForm(@PathVariable("id") String id, Model model) {
        model.addAttribute("id", id);
        return "organization/update";
    }

    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public Map<String,Object> update(@ModelAttribute Organization organization, Model model) {
        logger.info("修改机构信息");
        Map<String,Object> map = new HashMap<String, Object>();
        try {
            organizationService.updateOrganization(organization);
            map.put("msg", "修改成功");
        } catch (Exception e) {
            map.put("msg", e);
        }
        return map;
    }
    
    @RequestMapping(value = "/{id}/delete", method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") String id,Model model) {
        organizationService.deleteOrganization(id);
        model.addAttribute("msg", "删除成功");
        return "msg";
    }
	
}
