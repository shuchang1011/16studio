//package com.controller;
//
//import java.io.UnsupportedEncodingException;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.servlet.ModelAndView;
//
//import com.service.DocumentService;
//import com.zhuozhengsoft.pageoffice.FileSaver;
//import com.zhuozhengsoft.pageoffice.OpenModeType;
//import com.zhuozhengsoft.pageoffice.PageOfficeCtrl;
//import com.zhuozhengsoft.pageoffice.wordwriter.WordDocument;
//
//@Controller
//@RequestMapping(value="pageOffice")
//public class PageOfficeController {
//
//	@Autowired@Qualifier("DocumentServiceImpl")
//	private DocumentService documentService;
//
//    @RequestMapping(value="/index", method=RequestMethod.GET)
//    public String showIndex(){
//        return "123";
//    }
//
//    /**
//     * office online打开
//     *
//     * @param request
//     * @param map
//     * @return
//     */
//    @RequestMapping(value="/word", method=RequestMethod.GET)
//    public String openword(HttpServletRequest request, HttpServletResponse response){
//    	PageOfficeCtrl poCtrl = new PageOfficeCtrl(request);
//		request.setAttribute("poCtrl", poCtrl);
//		poCtrl.setTitlebar(false);
//		// 设置服务页面
//		poCtrl.setServerPage(request.getContextPath() + "/poserver.zz");
//		// 添加保存按钮
//		poCtrl.addCustomToolButton("保存并关闭", "Save", 1);
//		poCtrl.addCustomToolButton("打印", "ShowPrintDlg()", 6);
//		poCtrl.addCustomToolButton("全屏切换", "SwitchFullScreen()", 4);
//		// 设置保存的action
//		poCtrl.setSaveFilePage(request.getContextPath() + "/savefile");
//		// 打开word
//		/*List<FileMeta> entity = fileMetaService.getList(getEntity(flag, id));
//		String path = ZipService.getPath(entity.get(0));
//		String realpath = path.replace("/", "\\\\");
//		String suffix = path.substring(path.lastIndexOf(".") + 1);
//		if ("doc".equals(suffix) || "docx".equals(suffix)) {
//			poCtrl.webOpen(realpath, OpenModeType.docNormalEdit, "张三");
//		} else if ("xls".equals(suffix) || "xlsx".equals(suffix)) {
//			poCtrl.webOpen(realpath, OpenModeType.xlsNormalEdit, "张三");
//		}*/
//		poCtrl.webOpen("d:\\2.docx", OpenModeType.docNormalEdit, "张三");
//		poCtrl.setTagId("PageOfficeCtrl1");
//		System.out.println("123");
//		return "word";
//		/*PageOfficeCtrl poCtrl=new PageOfficeCtrl(request);
//        //动态替换模版内容
//        WordDocument doc=new WordDocument();
//        doc.openDataRegion("Incubator").setValue("测试修改");//文档书签替换Incubator标签
//
//        request.setAttribute("poCtrl", poCtrl);
//
//        //设置服务页面
//        poCtrl.setServerPage(request.getContextPath()+"/poserver.zz");
//        //添加保存按钮
//        poCtrl.addCustomToolButton("保存并关闭","Save",1);
//        //设置保存的action
//        poCtrl.setSaveFilePage("savefile");
//        //打开word
//        poCtrl.setWriter(doc);//将替换的标签注入文档中
//        poCtrl.webOpen("../doc/2.docx",OpenModeType.docAdmin,"张三");
//        poCtrl.setTagId("PageOfficeCtrl1"); //此行必须
//
//        return "word";  */
//    }
//
//    @RequestMapping(value="/pdf/{id}",method=RequestMethod.GET)
//    public String  pdf(HttpServletRequest request, HttpServletResponse response,@PathVariable("id")String id){
//    	String path = documentService.findTempDocById(id).getPath();
//    	String realPath = path.replace("/", "\\\\");
//    	System.out.println("realPath:"+realPath);
//    	String suffix = path.substring(path.lastIndexOf(".") + 1);
//    	System.out.println("suffix:"+suffix);
//    	request.setAttribute("realPath", realPath);
//    	request.setAttribute("suffix", suffix);
//    	request.setAttribute("id", id);
//    	return "pdf";
//   }
//
//    //文件保存
//    @RequestMapping(value="/savefile/{id}")
//     public  void  savefile(HttpServletRequest request, HttpServletResponse response,@PathVariable("id")String id){
//    	String path = documentService.findTempDocById(id).getPath();
//        FileSaver fs = new FileSaver(request, response);
//        fs.saveToFile(path);
//        fs.close();
//    }
//
//}
