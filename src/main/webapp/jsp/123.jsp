<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>
<%@ page language="java" import="com.zhuozhengsoft.pageoffice.*" %>
<%@ taglib uri="http://java.pageoffice.cn" prefix="po" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<!-- office插件js begin 必须引入-->
    <script type="text/javascript" src="../jquery.min.js"></script>
    <script type="text/javascript" src="../pageoffice.js" id="po_js_main"></script>
    <!-- end -->
</head>
<body>
<a href="javascript:POBrowser.openWindowModeless('${pageContext.request.contextPath}/pageOffice/word','width=1200px;height=800px;');">打开文件</a>
</body>
</html>