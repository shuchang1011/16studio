package com.controller;

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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.entity.Account;
import com.entity.Cultureaspect;
import com.entity.Member;
import com.entity.Task;
import com.entity.TempDoc;
import com.entity.Village;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.service.AccountService;
import com.service.CultureaspectService;
import com.service.DocumentService;
import com.service.MemberService;
import com.service.TaskService;
import com.service.VillageService;
import com.wordnik.swagger.annotations.Api;

@Api(value="restful",description="任务Api")
@Controller
@RequestMapping(value="/task")
public class TaskController {

	private static Logger logger = Logger.getLogger(TaskController.class);
	
	@Autowired@Qualifier("TaskServiceImpl")
	private TaskService taskService;
	
	@Autowired@Qualifier("AccountServiceImpl")
	private AccountService accountService;
	
	@Autowired@Qualifier("MemberServiceImpl")
	private MemberService memberService;
	
	@Autowired@Qualifier("VillageServiceImpl")
	private VillageService villageService;
	
	@Autowired@Qualifier("CultureaspectServiceImpl")
	private CultureaspectService cultureaspectService;
	
	@Autowired@Qualifier("DocumentServiceImpl")
	private DocumentService documentService;
	
	@ResponseBody
	@RequestMapping(value="/getTaskAnnoucement",method=RequestMethod.GET)
	public Map<String,Object> getTaskAnnoucement(HttpSession session) {
		logger.info("获取任务通知");
		Account  account = (Account)session.getAttribute("user");
		Member member = memberService.findOneByAccountId(account.getId());
		logger.info("memberId = "+member.getId());
		List<Task> taskList = taskService.findAllByMemberId(member.getId());
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("task", taskList);
		return map;
	}
	
	@RequestMapping(value="/taskView",method=RequestMethod.GET)
	public String task() {
		return "taskView";
	}
	
	@RequestMapping(value="/finishedTaskView",method = RequestMethod.GET)
	public String finishedTaskView(HttpSession session,@RequestParam(required = false,defaultValue = "1",value = "pn")Integer pn,Model model) {
		logger.info("查看已完成任务");
		Account account = (Account) session.getAttribute("user");
		String organizationId = account.getMember().getOrganizationId();
		PageHelper.startPage(pn, 5);
		List<Task> taskList = taskService.findFinishedTask(organizationId);
		logger.info("taskList = "+ToStringBuilder.reflectionToString(taskList, ToStringStyle.JSON_STYLE));
		PageInfo<Task> pageInfo = new PageInfo<>(taskList,5);
		model.addAttribute("pageInfo", pageInfo);
		return "task/finished";
	}
	
	@ResponseBody
	@RequestMapping(value="/unFinishedTask",method = RequestMethod.GET)
	public Map<String,Object> unFinishedTask(HttpSession session) {
		logger.info("查看未完成任务");
		Account account = (Account) session.getAttribute("user");
		String organizationId = account.getMember().getOrganizationId();
		List<Task> taskList = taskService.findUnFinishedTask(organizationId);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("unfinishedTask", taskList);
		return map;
	}
	
	@RequestMapping(value="/unFinishedTaskView",method = RequestMethod.GET)
	public String unFinishedTaskView(HttpSession session,@RequestParam(required = false,defaultValue = "1",value = "pn")Integer pn,Model model) {
		logger.info("查看未完成任务");
		Account account = (Account) session.getAttribute("user");
		String organizationId = account.getMember().getOrganizationId();
		PageHelper.startPage(pn, 5);
		List<Task> taskList = taskService.findUnFinishedTask(organizationId);
		PageInfo<Task> pageInfo = new PageInfo<>(taskList,5);
		model.addAttribute("pageInfo", pageInfo);
		return "task/unfinished";
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
    public String showCreateForm(HttpSession session,Model model) {
		Account account = (Account) session.getAttribute("user");
		String organizationId = memberService.findOneByAccountId(account.getId()).getOrganizationId();
		List<Member> scholarList = memberService.findFreeScholarByOrganizationId(organizationId);
		List<Member> inputorList = memberService.findFreeInputorByOrganizationId(organizationId);
		for(int i = 0;i < scholarList.size();i++) {
			scholarList.get(i).setAccount(accountService.findAccountById(scholarList.get(i).getAccountId()));
		}
		for(int i = 0;i < inputorList.size();i++) {
			inputorList.get(i).setAccount(accountService.findAccountById(inputorList.get(i).getAccountId()));
		}
		List<Village> villageList = villageService.findAllByOrganizationId(organizationId);
		for (int i = 0;i < villageList.size();i++) {
			villageList.get(i).setCultureaspects(cultureaspectService.findAll());
		}
		List<Task> taskList = taskService.findAllByOrganizationId(organizationId);
		for (Task task : taskList) {
			for (int i = 0;i < villageList.size();i++) {
				if(task.getVillageId().equals(villageList.get(i).getId())) {
					for(Cultureaspect cultureaspect : villageList.get(i).getCultureaspects()) {
						if(cultureaspect.getId().equals(task.getCultureaspectId())) {
							villageList.get(i).getCultureaspects().remove(task.getCultureaspectId());
							break;
						}	
					}
					if(villageList.get(i).getCultureaspects()==null) {
						villageList.remove(i);
					}
					break;
				}
			}
		}
		model.addAttribute("organizationId", organizationId);
		model.addAttribute("scholarList", scholarList);
		model.addAttribute("inputorList", inputorList);
		model.addAttribute("villageList", villageList);
        return "task/create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(HttpSession session,@RequestParam("memberId")String[] membersId,@ModelAttribute Task task, Model model) {
    	logger.info("创建任务");
    	Account account = (Account) session.getAttribute("user");
    	String memberId = memberService.findOneByAccountId(account.getId()).getId();
    	task.setCreateMemberId(memberId);
    	task.setId(UUID.randomUUID().toString());
    	taskService.createTask(task,membersId);
        model.addAttribute("msg", "新增成功");
        return "msg";
    }
    
    @ResponseBody
    @RequestMapping(value="/getCultureaspect",method=RequestMethod.GET)
    public Map<String,Object> getCultureaspect(@RequestParam("id")String id) {
    	List<Cultureaspect> list = cultureaspectService.findAll();
		List<Task> taskList = taskService.findTaskByVillage(id);
		for (Task task : taskList) {
			if(list.contains(task.getCultureaspectId()))
				list.remove(task.getCultureaspectId());
		}
		Map<String, Object> modelMap = new HashMap<String, Object>();
		modelMap.put("list", list);
		return modelMap;
    }
    
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String UpdateForm(@PathVariable("id") String id, Model model) {
        model.addAttribute("id", id);
        return "task/update";
    }


    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public String update(@ModelAttribute Task task, Model model) {
    	logger.info("修改任务");
    	taskService.updateTask(task);
        model.addAttribute("msg", "修改成功");
        return "msg";
    }

    @RequestMapping(value = "/finishTask/{id}", method = RequestMethod.GET)
    public String finishTask(@PathVariable("id")String id, Model model) {
    	logger.info("完成任务");
    	taskService.FinishTask(id);
    	Task task = taskService.findOne(id);
    	List<TempDoc> tempDocList = documentService.findTempDocByVillageIdAndCultureaspectId(task.getVillageId(), task.getCultureaspectId());
    	for (TempDoc tempDoc : tempDocList) {
    		documentService.deleteTempDoc(tempDoc.getId());
		}
        model.addAttribute("msg", "任务完成");
        return "msg";
    }
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") String id,Model model) {
    	logger.info("删除任务");
    	taskService.deleteTask(id);
        model.addAttribute("msg", "删除成功");
        return "msg";
    }
	
}
