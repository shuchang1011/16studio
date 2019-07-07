package com.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.Constants;
import com.entity.Account;
import com.entity.Cultureaspect;
import com.entity.Document;
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
import com.service.OrganizationService;
import com.service.TaskService;
import com.service.VillageService;
import com.util.Doc2HtmlUtil;
import com.util.documentUtil;
import com.wordnik.swagger.annotations.Api;

@Api(value="restful",description="档案Api")
@Controller
@RequestMapping(value="/document")
public class DocumentController {

	private static Logger logger = Logger.getLogger(DocumentController.class);
	
	@Autowired@Qualifier("DocumentServiceImpl")
	private DocumentService documentService;
	
	@Autowired@Qualifier("VillageServiceImpl")
	private VillageService villageService;
	  
	@Autowired@Qualifier("CultureaspectServiceImpl")
	private CultureaspectService cultureaspectService;
	
	@Autowired@Qualifier("MemberServiceImpl")
	private MemberService memberService;
	
	@Autowired@Qualifier("OrganizationServiceImpl")
	private OrganizationService organizationService;
	
	@Autowired@Qualifier("AccountServiceImpl")
	private AccountService accountService;
	
	@Autowired@Qualifier("TaskServiceImpl")
	private TaskService taskService;
	
	public documentUtil docUtil = new documentUtil();
	
	@RequestMapping(value="/showDocument",method = RequestMethod.GET)
	public String showDocument(@RequestParam(required = false,defaultValue = "1",value = "pn")Integer pn,Model model) {
		logger.info("文档查阅");
		PageHelper.startPage(pn, 8);
		List<Document> documentList = documentService.findAll();
		PageInfo<Document> pageInfo = new PageInfo<>(documentList,8);
		for (Document document : documentList) {
			logger.info("documentList = "+ToStringBuilder.reflectionToString(document, ToStringStyle.JSON_STYLE));
		}
		logger.info("documentList = "+ToStringBuilder.reflectionToString(documentList, ToStringStyle.JSON_STYLE));
    	logger.info("pageInfo = "+ToStringBuilder.reflectionToString(pageInfo, ToStringStyle.JSON_STYLE));
		model.addAttribute("pageInfo", pageInfo);
		return "document/showDocument";
	}
	
	@RequestMapping(value="/conditionalQuery",method = RequestMethod.GET)
	public String conditionalQuery(@RequestParam("condition")String condition,@RequestParam("type")String type,
			@RequestParam(required = false,defaultValue = "1",value = "pn")Integer pn,Model model) {
		logger.info("条件查询"+type);
		try {
			condition = URLEncoder.encode(condition, "ISO-8859-1");
			condition = URLDecoder.decode(condition, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(type.equals("village")) {
			logger.info("cunluo");
			Village village = villageService.findOneByName(condition);
			if(village==null) {
				model.addAttribute("msg", "查询到0条记录");
			}else {
				PageHelper.startPage(pn, 8);
				List<Document> list = documentService.findByVillage(village.getId());
				PageInfo<Document> pageInfo = new PageInfo<>(list,8);
				for (Document document : list) {
					logger.info("documentList = "+ToStringBuilder.reflectionToString(document, ToStringStyle.JSON_STYLE));
				}
				logger.info("documentList = "+ToStringBuilder.reflectionToString(list, ToStringStyle.JSON_STYLE));
		    	logger.info("pageInfo = "+ToStringBuilder.reflectionToString(pageInfo, ToStringStyle.JSON_STYLE));
				model.addAttribute("pageInfo", pageInfo);
			}
		}else if(type.equals("type")) {
			logger.info("leixing");
			PageHelper.startPage(pn, 8);
			List<Document> list = documentService.findByType(condition);
			PageInfo<Document> pageInfo = new PageInfo<>(list,8);
			for (Document document : list) {
				logger.info("documentList = "+ToStringBuilder.reflectionToString(document, ToStringStyle.JSON_STYLE));
			}
			logger.info("documentList = "+ToStringBuilder.reflectionToString(list, ToStringStyle.JSON_STYLE));
	    	logger.info("pageInfo = "+ToStringBuilder.reflectionToString(pageInfo, ToStringStyle.JSON_STYLE));
			model.addAttribute("pageInfo", pageInfo);
		}else {
			logger.info("title");
			PageHelper.startPage(pn, 8);
			List<Document> list = documentService.findByTitle(condition);
			PageInfo<Document> pageInfo = new PageInfo<>(list,8);
			for (Document document : list) {
				logger.info("documentList = "+ToStringBuilder.reflectionToString(document, ToStringStyle.JSON_STYLE));
			}
			logger.info("documentList = "+ToStringBuilder.reflectionToString(list, ToStringStyle.JSON_STYLE));
	    	logger.info("pageInfo = "+ToStringBuilder.reflectionToString(pageInfo, ToStringStyle.JSON_STYLE));
			model.addAttribute("pageInfo", pageInfo);
		}
		
		model.addAttribute("condition", condition);
		model.addAttribute("type", type);
		return "document/showDocument";
	}
	
	@RequestMapping(value="/showVillage",method = RequestMethod.GET)
	public String villageView(@RequestParam(required = false,defaultValue = "1",value = "pn")Integer pn,Model model) {	
		PageHelper.startPage(pn, 5);
    	List<Village> villageList = villageService.findAll();
    	for(int i = 0;i < villageList.size();i++) {
    		villageList.get(i).setOrganization(organizationService.findOne(villageList.get(i).getOrganizationId()).getName());
    	}
    	PageInfo<Village> pageInfo = new PageInfo<>(villageList,5);
    	model.addAttribute("pageInfo", pageInfo);
        return "villages";
	}
	
	@RequestMapping(value="/showCultureaspect/{villageId}")
	public String cultureaspectView(@PathVariable("villageId")String villageId,
			@RequestParam(required = false,defaultValue = "1",value = "pn")Integer pn,Model model) {
		logger.info("查看文化方面");
		PageHelper.startPage(pn, 5);
		List<Cultureaspect> list = cultureaspectService.findAll();
		PageInfo<Cultureaspect> pageInfo = new PageInfo<>(list,5);
		model.addAttribute("villageId", villageId);
		model.addAttribute("pageInfo", pageInfo);
		return "cultureaspects";
	}
	
	@RequestMapping(value="/documentView/{villageId}/{cultureaspectId}",method = RequestMethod.GET)
	public String showAll(@PathVariable("villageId")String villageId,@PathVariable("cultureaspectId")String cultureaspectId,
			@RequestParam(required = false,defaultValue = "1",value = "pn")Integer pn,Model model) {
		logger.info("查看所有文件");
		PageHelper.startPage(pn, 5);
    	List<Document> documentList = documentService.findAllByVillageIdAndCultureaspectId(villageId, cultureaspectId);
    	PageInfo<Document> pageInfo = new PageInfo<>(documentList,5);
    	model.addAttribute("pageInfo", pageInfo);
    	model.addAttribute("villageId", villageId);
    	model.addAttribute("cultureaspectId", cultureaspectId);
        return "document";
	}

	@RequestMapping(value="/getOne/{id}",method = RequestMethod.GET)
	public String showOne(@PathVariable("id")String id,Model model) {
		logger.info("查看文件内容");
		Document doc = documentService.findOne(id);
		String filename = doc.getPath().substring(doc.getPath().lastIndexOf("/")+1);
		model.addAttribute("filename", filename);
		model.addAttribute("document", doc);
		return "document/getOne";
	}
	
	@RequestMapping(value="/showMyTempDoc",method=RequestMethod.GET)
	public String showMyTempDoc(HttpSession session,@RequestParam(required = false,defaultValue = "1",value = "pn")Integer pn,Model model) {
		logger.info("查看工作进度");
		Account account = (Account)session.getAttribute("user");
		String memberId = memberService.findOneByAccountId(account.getId()).getId();
		PageHelper.startPage(pn, 5);
		List<TempDoc> tempDocList = documentService.findTempDocByCreateMemberId(memberId);
		for (int i = 0;i < tempDocList.size();i++) {
			for(int j = i+1;j < tempDocList.size();j++) {
				if(tempDocList.get(i).getFileId().equals(tempDocList.get(j).getFileId())) {
					tempDocList.remove(j);
					j--;
				}
			}
		}
		for (TempDoc tempDoc : tempDocList) {
			tempDoc.setVillage(villageService.findOne(tempDoc.getVillageId()).getName());
			tempDoc.setCultureaspect(cultureaspectService.findOne(tempDoc.getCultureaspectId()).getTitle());
			tempDoc.setCreateMember(accountService.findAccountById(memberService.findOne(tempDoc.getCreateMemberId()).getAccountId()).getDisplayName());
		}
		PageInfo<TempDoc> pageInfo = new PageInfo<>(tempDocList,5);
		model.addAttribute("pageInfo", pageInfo);
		return "document/unSubmitedTempDoc";
		
	}
	
	@ResponseBody
	@RequestMapping(value="/showUnSubmitTempDoc",method=RequestMethod.GET)
	public Map<String,Object> showTempDoc1(HttpSession session,@RequestParam(required = false,defaultValue = "1",value = "pn")Integer pn,Model model) {
		logger.info("查看工作进度");
		Account account = (Account)session.getAttribute("user");
		String memberId = memberService.findOneByAccountId(account.getId()).getId();
		PageHelper.startPage(pn, 5);
		List<TempDoc> tempDocList = documentService.findUnSubmitTempDoc(memberId);
		for (int i = 0;i < tempDocList.size();i++) {
			logger.info(tempDocList.get(i).getContent());
			for(int j = i+1;j < tempDocList.size();j++) {
				if(tempDocList.get(i).getFileId().equals(tempDocList.get(j).getFileId())) {
					tempDocList.remove(j);
					j--;
				}
			}
		}
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("tempDoc", tempDocList);
		return map;
		
	}
	
	@RequestMapping(value="/showTempDoc/{id}",method = RequestMethod.GET)
	public String showTempDoc(@PathVariable("id")String tempDocId,Model model) {
		logger.info("获取临时文件");
		List<TempDoc> tempDoc = documentService.findTempDoc(tempDocId);
		for(int i = 0 ; i < tempDoc.size() ; i++) {
			if(!tempDoc.get(i).getType().equals("file")) {
				tempDoc.get(i).setPath(tempDoc.get(i).getPath().substring(tempDoc.get(i).getPath().lastIndexOf("/")+1));
			}
		}
		model.addAttribute("tempDoc", tempDoc);
		return "document/getTask";
	}
	
	@RequestMapping(value="/getTempDoc/{id}",method = RequestMethod.GET)
	public String getTempDoc(@PathVariable("id")String tempDocId,Model model) {
		logger.info("获取临时文件");
		List<TempDoc> tempDoc = documentService.findTempDoc(tempDocId);
		model.addAttribute("tempDoc", tempDoc);
		return "document/auditTempDoc";
	}
	
	@ResponseBody
	@RequestMapping(value="/showArchiveTempDoc",method = RequestMethod.GET)
	public Map<String,Object> archiveTempDoc(HttpSession session) {
		logger.info("获取可归档文件");
		Account account = (Account) session.getAttribute("user");
		String memberId = memberService.findOneByAccountId(account.getId()).getId();
		List<TempDoc> tempDocList = documentService.findPassedTempDoc(memberId);
		for (int i = 0;i < tempDocList.size();i++) {
			for(int j = i+1;j < tempDocList.size();j++) {
				if(tempDocList.get(i).getFileId().equals(tempDocList.get(j).getFileId())) {
					tempDocList.remove(j);
					j--;
				}
			}
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("tempDoc", tempDocList);
		return map;
	}
	
	@RequestMapping(value="/archive",method = RequestMethod.GET)
	public String archive(HttpSession session,@RequestParam(required = false,defaultValue = "1",value = "pn")Integer pn,Model model) {
		logger.info("获取可归档文件");
		Account account = (Account) session.getAttribute("user");
		String memberId = memberService.findOneByAccountId(account.getId()).getId();
		PageHelper.startPage(pn, 5);
		List<TempDoc> tempDocList = documentService.findPassedTempDoc(memberId);
		for (int i = 0;i < tempDocList.size();i++) {
			for(int j = i+1;j < tempDocList.size();j++) {
				if(tempDocList.get(i).getFileId().equals(tempDocList.get(j).getFileId())) {
					tempDocList.remove(j);
					j--;
				}
			}
		}
		for (TempDoc tempDoc : tempDocList) {
			tempDoc.setVillage(villageService.findOne(tempDoc.getVillageId()).getName());
			tempDoc.setCultureaspect(cultureaspectService.findOne(tempDoc.getCultureaspectId()).getTitle());
			tempDoc.setCreateMember(accountService.findAccountById(memberService.findOne(tempDoc.getCreateMemberId()).getAccountId()).getDisplayName());
			tempDoc.setAuditMember(accountService.findAccountById(memberService.findOne(tempDoc.getAuditMemberId()).getAccountId()).getDisplayName());
		}
		PageInfo<TempDoc> pageInfo = new PageInfo<>(tempDocList,5);
		model.addAttribute("pageInfo", pageInfo);
		return "document/archive";
	}
	
	@RequestMapping(value="/archiveTempDoc/{tempDocId}",method=RequestMethod.PUT)
	public String archiveTempDoc(@RequestParam("state")String state,@PathVariable("tempDocId")String tempDocId,Model model) {
		logger.info("归档工作");
		if(state.equals("归档")) {
			state = Constants.STATE_ARCHIVE_SUCCESS;
		}else {
			state = Constants.STATE_ARCHIVE_FAILED;
		}
		documentService.archiveTempDoc(tempDocId, state);
    	model.addAttribute("msg", state);
    	return "msg";
		
	}
	
	@RequestMapping(value="/getPassedTempDoc/{id}",method = RequestMethod.GET)
	public String getPassedTempDoc(@PathVariable("id")String tempDocId,Model model) {
		logger.info("获取归档文件详情");
		List<TempDoc> tempDoc = documentService.findTempDoc(tempDocId);
		model.addAttribute("tempDoc", tempDoc);
		return "document/archiveTempDoc";
	}
	
	@RequestMapping(value = "/create/{villageId}/{cultureaspectId}", method = RequestMethod.GET)
    public String showCreateForm(HttpSession session,@PathVariable("villageId")String villageId,
    		@PathVariable("cultureaspectId")String cultureaspectId,Model model) {
		logger.info("显示创建文件界面");
		Account account = (Account) session.getAttribute("user");
		String memberId = memberService.findOneByAccountId(account.getId()).getId();
    	if(account.getRole().getName().equals("超级管理员")) {
    		model.addAttribute("memberId", memberId);
    		model.addAttribute("villageId", villageId);
    		model.addAttribute("cultureaspectId", cultureaspectId);
    		return "document/create";
    	}else if(account.getRole().getName().equals("机构管理员")||account.getRole().getName().equals("学者")){
    		//判断是否为当前机构创建的村落信息，否则无法创建
    		if(taskService.findTaskByVillageAndCultureaspect(villageId,cultureaspectId)==null) {
    			model.addAttribute("msg", "未将当前村落任务分配给您");
    			return "msg";
    		}
    		if(villageService.findOne(villageId).getOrganizationId().equals(memberService.findOneByAccountId(account.getId()).getOrganizationId())) {
    			if(taskService.findTaskByVillageAndCultureaspect(villageId,cultureaspectId).isFinished()||(account.getRole().getName().equals("学者")&&!taskService.IsMemberInTask(villageId,cultureaspectId,memberId))) {
    				model.addAttribute("msg", "未将当前村落任务分配给您");
        			return "msg";
    			}
    			List<Member> scholarList = memberService.findScholarInTask(taskService.findTaskByVillageAndCultureaspect(villageId,cultureaspectId).getId());
    			for (int i = 0;i < scholarList.size();i++) {
    				scholarList.get(i).setAccount(accountService.findAccountById(scholarList.get(i).getAccountId()));
    			}
    			model.addAttribute("scholarList", scholarList);
    			model.addAttribute("fileType", taskService.findTaskByVillageAndCultureaspect(villageId,cultureaspectId).getFileType());
    			model.addAttribute("memberId", memberId);
    			model.addAttribute("villageId", villageId);
        		model.addAttribute("cultureaspectId", cultureaspectId);
        		return "document/create";
    		}else {
    			model.addAttribute("msg", "无法在非本机构创建的村落中构建文档");
    			return "msg";
    		}
    	}else {
    		//若学者已经分配任务给录入员，且录入员未提交任务，则可以创建
    		if(taskService.findTaskByVillageAndCultureaspect(villageId,cultureaspectId)!=null&&taskService.findTaskByVillageAndCultureaspect(villageId,cultureaspectId).isFinished()||!taskService.IsMemberInTask(villageId,cultureaspectId,memberId)) {
    			model.addAttribute("msg", "未分配当前村落的录入工作，无法录入");
    			return "msg";
    		}else {
    			List<Member> scholarList = memberService.findScholarInTask(taskService.findTaskByVillageAndCultureaspect(villageId,cultureaspectId).getId());
    			for (int i = 0;i < scholarList.size();i++) {
    				scholarList.get(i).setAccount(accountService.findAccountById(scholarList.get(i).getAccountId()));
    			}
    			model.addAttribute("scholarList", scholarList);
    			model.addAttribute("fileType", taskService.findTaskByVillageAndCultureaspect(villageId,cultureaspectId).getFileType());
    			model.addAttribute("memberId", memberId);
    			model.addAttribute("villageId", villageId);
        		model.addAttribute("cultureaspectId", cultureaspectId);
        		return "document/create";
    		}
    	}
		
    }

	@Transactional(rollbackFor={RuntimeException.class,Exception.class})
    @RequestMapping(value = "/createTempDoc", method = RequestMethod.POST)
    public String createTempDoc(@RequestParam("fileType")String fileType,@RequestParam("content")String content
    		,@ModelAttribute TempDoc tempDoc, Model model) {
    	logger.info("创建临时文件");
    	Task task = taskService.findTaskByVillageAndCultureaspect(tempDoc.getVillageId(),tempDoc.getCultureaspectId());
    	tempDoc.setFileMemberId(task.getCreateMemberId());
    	tempDoc.setType(fileType);
    	tempDoc.setFileId(UUID.randomUUID().toString());
    	if(fileType.equals("html")) {
    		Pattern p = Pattern.compile("imagePath=(.*?)\"");  
    		Matcher m = p.matcher(content); 
    		while(m.find()){  
    			tempDoc.setPath(m.group(1));
    			tempDoc.setContent(content);
        		documentService.createTempDocument(tempDoc);
    		} 
    		model.addAttribute("msg", "新增成功");
    	}else if(fileType.equals("file")||fileType.equals("video")){
    		logger.info("创建文件");
    		Pattern p = Pattern.compile("url=(.*?)&");  
    		Matcher m = p.matcher(content);  
    		if(!m.find()) {
    			model.addAttribute("msg", "请上传限制类型文件！");
    			return "msg";
    		}else {
    			tempDoc.setPath(m.group(1));
    			tempDoc.setFormat(m.group(1).substring(m.group(1).lastIndexOf('.')+1));
    			documentService.createTempDocument(tempDoc);
			}
    		while(m.find()){  
    			//System.out.println(m.group(1));  
    			tempDoc.setPath(m.group(1));
    			tempDoc.setFormat(m.group(1).substring(m.group(1).lastIndexOf('.')+1));
    			documentService.createTempDocument(tempDoc);
    		} 
            model.addAttribute("msg", "新增成功");
    	}else {
    		Pattern p = Pattern.compile("imagePath=(.*?)\"");  
    		Matcher m = p.matcher(content);  
    		if(!m.find()) {
    			model.addAttribute("msg", "请上传限制类型文件！");
    			return "msg";
    		}else {
    			tempDoc.setPath(m.group(1));
    			tempDoc.setFormat(m.group(1).substring(m.group(1).lastIndexOf('.')+1));
    			documentService.createTempDocument(tempDoc);
    		}
    		while(m.find()){  
    			System.out.println(m.group(1));  
    			tempDoc.setPath(m.group(1));
    			tempDoc.setFormat(m.group(1).substring(m.group(1).lastIndexOf('.')+1));
    			documentService.createTempDocument(tempDoc);
    		} 
    	}
    	model.addAttribute("msg","新增成功");
        return "msg";
    }
    
    @RequestMapping(value="/submitTempDoc/{id}",method = RequestMethod.PUT)
    public String submitTempDoc(@PathVariable("id")String id,Model model) {
		logger.info("提交审核");
    	documentService.submitTempDoc(id);
    	model.addAttribute("msg", "提交成功");
    	return "msg";
    	
    }
    
    @RequestMapping(value = "/updateTemp/{id}", method = RequestMethod.GET)
    public String UpdateTempForm(HttpSession session,@PathVariable("id") String id, Model model) {
    	Account account = (Account)session.getAttribute("user");
    	List<TempDoc> tempDoc = documentService.findTempDoc(id);
    	if(tempDoc.get(0).getCreateMemberId().equals(memberService.findOneByAccountId(account.getId()).getId())){
    		model.addAttribute("tempDoc", tempDoc);
            return "document/updateTemp";
    	}else {
    		model.addAttribute("msg", "无法修改他人录入档案");
    		return "msg";
    	}
    }
    
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String UpdateForm(@PathVariable("id") String id, Model model) {
		model.addAttribute("id", id);
        return "document/updateTemp";
    }

    @Transactional(rollbackFor={RuntimeException.class,Exception.class})
    @RequestMapping(value = "/updateTemp", method = RequestMethod.PUT)
    public String updateTemp(@ModelAttribute TempDoc tempDoc, Model model) {
    	logger.info("修改文件信息");
    	if(tempDoc.getType().equals("file")||tempDoc.getType().equals("video")){
    		Pattern p = Pattern.compile("url=(.*?)&");  
    		Matcher m = p.matcher(tempDoc.getContent());  
    		if(!m.find()) {
    			model.addAttribute("msg", "请上传限制类型文件！");
    			return "msg";
    		}else {
    			documentService.deleteTempDoc(tempDoc.getId());
    			tempDoc.setPath(m.group(1));
    			tempDoc.setFormat(m.group(1).substring(m.group(1).lastIndexOf('.')+1));
    			documentService.createTempDocument(tempDoc);
			}
    		while(m.find()){  
    			System.out.println(m.group(1));  
    			tempDoc.setPath(m.group(1));
    			tempDoc.setFormat(m.group(1).substring(m.group(1).lastIndexOf('.')+1));
    			documentService.createTempDocument(tempDoc);
    		} 
    	}else if(tempDoc.getType().equals("image")){
    		Pattern p = Pattern.compile("imagePath=(.*?)\"");  
    		Matcher m = p.matcher(tempDoc.getContent());  
    		if(!m.find()) {
    			model.addAttribute("msg", "请上传限制类型文件！");
    			return "msg";
    		}else {
    			documentService.deleteTempDoc(tempDoc.getId());
    			tempDoc.setPath(m.group(1));
    			tempDoc.setFormat(m.group(1).substring(m.group(1).lastIndexOf('.')+1));
    			documentService.createTempDocument(tempDoc);
    		}
    		while(m.find()){  
    			System.out.println(m.group(1));  
    			tempDoc.setPath(m.group(1));
    			tempDoc.setFormat(m.group(1).substring(m.group(1).lastIndexOf('.')+1));
    			documentService.createTempDocument(tempDoc);
    		} 
    	}
        model.addAttribute("msg", "修改成功");
        return "msg";
    }
    
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public String update(@ModelAttribute Document document, Model model) {
    	logger.info("修改文件信息");
        documentService.updateDocument(document);
        model.addAttribute("msg", "修改成功");
        return "msg";
    }

    
    @RequestMapping(value = "/{id}/delete", method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") String id,Model model) {
    	logger.info("删除文件");
        documentService.deleteDocument(id);
        model.addAttribute("msg", "删除成功");
        return "msg";
    }
	
    @ResponseBody
    @RequestMapping(value = "/showAuditTempDoc", method = RequestMethod.GET)
    public Map<String,Object> auditTempDoc(HttpSession session) {
    	logger.info("审核");
		Account account = (Account)session.getAttribute("user");
		String memberId = memberService.findOneByAccountId(account.getId()).getId();
    	List<TempDoc> submitTempDoc = documentService.findSubmitTempDoc(memberId);
    	for (int i = 0;i < submitTempDoc.size();i++) {
			for(int j = i+1;j < submitTempDoc.size();j++) {
				if(submitTempDoc.get(i).getFileId().equals(submitTempDoc.get(j).getFileId())) {
					submitTempDoc.remove(j);
					j--;
				}
			}
		}
    	Map<String,Object> map = new HashMap<String,Object>();
    	map.put("tempDoc", submitTempDoc);
    	return map;
    }
    
    @RequestMapping(value = "/audit", method = RequestMethod.GET)
    public String audit(HttpSession session
    		,@RequestParam(required = false,defaultValue = "1",value = "pn")Integer pn,Model model) {
    	logger.info("审核");
		Account account = (Account)session.getAttribute("user");
		String memberId = memberService.findOneByAccountId(account.getId()).getId();
		PageHelper.startPage(pn, 5);
    	List<TempDoc> submitTempDoc = documentService.findSubmitTempDoc(memberId);
    	for (int i = 0;i < submitTempDoc.size();i++) {
			for(int j = i+1;j < submitTempDoc.size();j++) {
				if(submitTempDoc.get(i).getFileId().equals(submitTempDoc.get(j).getFileId())) {
					submitTempDoc.remove(j);
					j--;
				}
			}
		}
    	for (TempDoc tempDoc : submitTempDoc) {
			tempDoc.setVillage(villageService.findOne(tempDoc.getVillageId()).getName());
			tempDoc.setCultureaspect(cultureaspectService.findOne(tempDoc.getCultureaspectId()).getTitle());
			tempDoc.setCreateMember(accountService.findAccountById(memberService.findOne(tempDoc.getCreateMemberId()).getAccountId()).getDisplayName());
		}
    	PageInfo<TempDoc> pageInfo = new PageInfo<>(submitTempDoc,5);
    	model.addAttribute("pageInfo", pageInfo);
    	return "document/audit";
    }
    
    @RequestMapping(value="/auditTempDoc/{tempDocId}",method=RequestMethod.PUT)
    public String auditTempDoc(@RequestParam("state")String state,@PathVariable("tempDocId")String tempDocId,Model model) {
		logger.info("审核工作");
		if(state.equals("通过")) {
			state = Constants.STATE_ARCHIVE;
		}else {
			state = Constants.STATE_AUDIT_FAILED;
		}
		documentService.auditTempDoc(tempDocId, state);
    	model.addAttribute("msg", state);
    	return "msg";
    }
    
    @RequestMapping(value="/download",method = RequestMethod.GET)
	public ResponseEntity<byte[]> download(@RequestParam("path")String path,@RequestParam("title")String title,Model model) throws IOException {
		//下载路径
		path = new String(path.getBytes("ISO-8859-1"), "utf-8");
		File file= new File(path);
		String downloadName = URLEncoder.encode(path.substring(path.lastIndexOf("/")+1),"UTF-8");
		HttpHeaders headers = new HttpHeaders();
		//通知浏览器以attachment（下载方式）打开图片
		headers.setContentDispositionFormData("attachment", downloadName);
		//application/octet-stream:二进制流数据（最常见的下载方式）
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		//201 Httpstatus.CREATED;
		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),headers,HttpStatus.CREATED);
	}
	
	 @RequestMapping(value="/preview",method=RequestMethod.GET)
	    public String preview(HttpServletRequest request,@RequestParam("path")String path
	    		,@RequestParam("swfName")String swfName,@RequestParam("type")String type,Model model) {
	    	try {
	    		System.out.println("进入preview");
	    		path = new String(path.getBytes("ISO-8859-1"), "utf-8");
	    		InputStream in = new FileInputStream(path);
	    		logger.info("1");
				String fileName = path.substring(path.lastIndexOf("/")+1,path.lastIndexOf("."));
				String filetype = path.substring(path.lastIndexOf(".")+1);
				logger.info("2");
				Doc2HtmlUtil doc2HtmlUtil = Doc2HtmlUtil.getDoc2HtmlUtilInstance();
				logger.info("3");
				//request.getSession().getServletContext().getRealPath(File.separator)获得项目的绝对路径
				String toFilePath = request.getSession().getServletContext().getRealPath("/upload");
				logger.info("4");
				String htmlpath = doc2HtmlUtil.file2swf(fileName,swfName,in, toFilePath, filetype);
				logger.info("htmlpath"+htmlpath);
				System.out.println(htmlpath);
				model.addAttribute("htmlpath", htmlpath);
				return "dataPreview";
	    	} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "document/getOne";
	    	
	    }
	
	 /**
		 * 下载文件
		 * @param request
		 * @param response
		 * @return
		 * @throws IOException
		 */
		@RequestMapping(value = "/fileDownLoad/{filename}/{type}",method = RequestMethod.GET)
		public void fileDownLoad(HttpServletRequest request, HttpServletResponse response,
				@PathVariable("filename")String fileName,@PathVariable("type")String type) throws IOException {
			// 下载本地文件
			
			//如果是IE浏览器，则用URLEncode解析
			String agent=request.getHeader("User-Agent").toLowerCase();
			if(agent.indexOf("mise")>0){
				fileName = URLEncoder.encode(fileName, "UTF-8");
			}else{//如果是谷歌、火狐则解析为ISO-8859-1
				fileName = new String(fileName.getBytes("ISO-8859-1"),"utf-8" );
			}
			String path = "c:/resource/static/"+type+"s/"+fileName;
			System.out.println("filename:"+fileName+" url:"+path);
			// 读到流中 
			InputStream inStream = new FileInputStream(path);// 文件的存放路径
//			fileName = url.substring(url.lastIndexOf("/")+1);
//			System.out.println("filename:"+fileName);
			// 设置输出的格式
			response.reset();
//			response.setContentType("bin");
			response.setContentType("application/vnd.ms-excel;charset=utf-8");
			response.addHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
			// 循环取出流中的数据
			byte[] b = new byte[100];
			int len;
			try {
				while ((len = inStream.read(b)) > 0)
					response.getOutputStream().write(b, 0, len);
				inStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
}
