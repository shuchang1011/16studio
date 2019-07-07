<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@page import="com.entity.Account" %>
<%@page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<script type="text/javascript" charset="utf-8" src="../../js/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
function count(){    
	var timer=document.getElementById('timer');
	var number=timer.innerHTML;
	number--;
	document.getElementById('timer').innerHTML=number;
    if(number==0){
<%
   	Account account = (Account)session.getAttribute("user");
   	String role = account.getRole().getName();
   	if(role.equals("超级管理员")){
%>
    window.location.href="${pageContext.request.contextPath}/organization/organizationView"; 
<%
	}else if(role.equals("机构管理员")){
%>
	window.location.href="${pageContext.request.contextPath}/Login/home2";
<%
	}else if(role.equals("学者")){
%>
	window.location.href="${pageContext.request.contextPath}/Login/home1";
<%
	}else{
%>
	window.location.href="${pageContext.request.contextPath}/Login/home";
<%
	}
%>
    }
}
//设置定时器，调用函数
setInterval("count()",1000);
</script>
<body>
	${requestScope.msg }<br><br>
	<div id="timer" style="float:left;">30</div><div style="float:left;">秒后返回首页</div>

</body>
</html>