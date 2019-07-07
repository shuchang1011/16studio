<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>
<%@ page language="java" import="com.zhuozhengsoft.pageoffice.*" %>
<%@ taglib uri="http://java.pageoffice.cn" prefix="po" %>
<%
	PageOfficeCtrl poCtrl = (PageOfficeCtrl) request.getAttribute("poCtrl");
	poCtrl.setTagId("PageOfficeCtrl1");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>

<body>
    <script type="text/javascript">
    function Save() {
		document.getElementById("PageOfficeCtrl1").WebSave();
		POBrowser.closeWindow();//关闭POBrowser窗口
	}
	function ShowPrintDlg() {
		document.getElementById("PageOfficeCtrl1").ShowDialog(4); //打印对话框
	}
	function SwitchFullScreen() {//全屏
		document.getElementById("PageOfficeCtrl1").FullScreen = !document
				.getElementById("PageOfficeCtrl1").FullScreen;
	}
    </script>
    <%-- <div style="width: 100%; height: 100%;">
		<%=poCtrl.getHtmlCode("PageOfficeCtrl1")%>
	</div> --%>
    <div style="width:100%; height:100%;">
       <po:PageOfficeCtrl id="PageOfficeCtrl1"></po:PageOfficeCtrl>
    </div>
</body>
</html>