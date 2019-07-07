<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>
<%@ page language="java" import="com.zhuozhengsoft.pageoffice.*" %>
<%@ taglib uri="http://java.pageoffice.cn" prefix="po" %>
<%
	String path = request.getAttribute("realPath").toString();
	String suffix = request.getAttribute("suffix").toString();
	String id = request.getAttribute("id").toString();
	PageOfficeCtrl poCtrl1 = new PageOfficeCtrl(request);
    poCtrl1.setServerPage(request.getContextPath()+"/poserver.zz"); //此行必须

// Create custom toolbar
    poCtrl1.addCustomToolButton("保存并关闭","Save",1);
    poCtrl1.setSaveFilePage(request.getContextPath() + "/pageOffice/savefile/"+id);
    if(suffix.equals("doc")||suffix.equals("docx")){
    	poCtrl1.webOpen(path,OpenModeType.docNormalEdit,"123");//**这里改成自己的路径**
    }else if(suffix.equals("xls")||suffix.equals("xlsx")){
    	poCtrl1.webOpen(path,OpenModeType.xlsNormalEdit,"123");//**这里改成自己的路径**
    }else if(suffix.equals("ppt")||suffix.equals("pptx")){
    	poCtrl1.webOpen(path,OpenModeType.pptNormalEdit,"123");//**这里改成自己的路径**
    }
    poCtrl1.setTagId("PDFCtrl1"); //此行必须
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<script language="javascript" type="text/javascript">
	function Save() {
		document.getElementById("PDFCtrl1").WebSave();
		POBrowser.closeWindow();//关闭POBrowser窗口
	}
    function AfterDocumentOpened() {
        //alert(document.getElementById("PDFCtrl1").Caption);
    }
    function SetBookmarks() {
        document.getElementById("PDFCtrl1").BookmarksVisible = !document.getElementById("PDFCtrl1").BookmarksVisible;
    }

    function Print() {
        document.getElementById("PDFCtrl1").ShowDialog(4);
    }
    function SwitchFullScreen() {
        document.getElementById("PDFCtrl1").FullScreen = !document.getElementById("PDFCtrl1").FullScreen;
    }
    function SetFullScreen() {
        document.getElementById("PDFCtrl1").FullScreen = !document.getElementById("PageOfficeCtrl1").FullScreen;
    }
    function SetPageReal() {
        document.getElementById("PDFCtrl1").SetPageFit(1);
    }
    function SetPageFit() {
        document.getElementById("PDFCtrl1").SetPageFit(2);
    }
    function SetPageWidth() {
        document.getElementById("PDFCtrl1").SetPageFit(3);
    }
    function ZoomIn() {
        document.getElementById("PDFCtrl1").ZoomIn();
    }
    function ZoomOut() {
        document.getElementById("PDFCtrl1").ZoomOut();
    }
    function FirstPage() {
        document.getElementById("PDFCtrl1").GoToFirstPage();
    }
    function PreviousPage() {
        document.getElementById("PDFCtrl1").GoToPreviousPage();
    }
    function NextPage() {
        document.getElementById("PDFCtrl1").GoToNextPage();
    }
    function LastPage() {
        document.getElementById("PDFCtrl1").GoToLastPage();
    }
    function RotateRight() {
        document.getElementById("PDFCtrl1").RotateRight();
    }
    function RotateLeft() {
        document.getElementById("PDFCtrl1").RotateLeft();
    }
</script> 
<!--**************   卓正 PageOffice 客户端代码结束    ************************--> 
<div style="width:auto; height:600px;"> 
<po:PageOfficeCtrl  id="PDFCtrl1" /> 
</div>

</body>
</html>