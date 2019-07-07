<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>
<%
    String path=request.getContextPath();
    String basePath=request.getScheme()+"://"+request.getServerName()+":"
    +request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>个人中心</title>
    <base href="<%=basePath%>">
    <link rel="stylesheet" type="text/css" href="css/userStu.css" />   
</head>

<body>
 <div id="container">
    <div class="title">管理平台</div>
    <div class="nowPos">当前位置:
        <div class="wenzi">欢迎您，(
       		${requestScope.user.getDisplayName() })
        	<a href="logout"><button class="zhuxiao">注销</button></a>
        </div>
    </div>
    <div id="content">
        <div id="L">
        	<c:if test="${user.getRole().getName() eq '超级管理员' }">
	        	<a id="organization" href="${pageContext.request.contextPath}/organization/organizationView" target="message">机构管理</a>
	            <a id="user" href="${pageContext.request.contextPath}/account/userView" target="message">人员管理</a>
	            <a id="village" href="${pageContext.request.contextPath}/village/information" target="message">村落管理</a>
	            <a id="document" href="${pageContext.request.contextPath}/document/showVillage" target="message">档案管理</a>
	            <a id="document" href="${pageContext.request.contextPath}/document/showDocument" target="message">文档查阅</a>
	            <a id="member" href="${pageContext.request.contextPath}/member/memberView" target="message">个人信息</a>
            </c:if>
            <c:if test="${user.getRole().getName() eq '机构管理员' }">
	            <a id="user" href="${pageContext.request.contextPath}/account/userView" target="message">人员管理</a>
	            <a id="task" href="${pageContext.request.contextPath}/task/taskView" target="message">任务管理</a>
	            <a id="village" href="${pageContext.request.contextPath}/village/information" target="message">村落管理</a>
	            <a id="document" href="${pageContext.request.contextPath}/document/showVillage" target="message">档案管理</a>
	            <a id="document" href="${pageContext.request.contextPath}/document/showDocument" target="message">文档查阅</a>
	            <a id="member" href="${pageContext.request.contextPath}/member/memberView" target="message">个人信息</a>
            </c:if>
            <c:if test="${user.getRole().getName() eq '学者' }">
	        	<a id="work" href="${pageContext.request.contextPath}/document/audit" target="message">文档审核</a>
	            <a id="document" href="${pageContext.request.contextPath}/document/showVillage" target="message">档案管理</a>
	            <a id="document" href="${pageContext.request.contextPath}/document/showDocument" target="message">文档查阅</a>
	            <a id="member" href="${pageContext.request.contextPath}/member/memberView" target="message">个人信息</a>
            </c:if>
            <c:if test="${user.getRole().getName() eq '录入员' }">
            	<a id="work" href="${pageContext.request.contextPath}/document/showMyTempDoc" target="message">工作管理</a>
	            <a id="document" href="${pageContext.request.contextPath}/document/showVillage" target="message">档案管理</a>
	            <a id="document" href="${pageContext.request.contextPath}/document/showDocument" target="message">文档查阅</a>
	            <a id="member" href="${pageContext.request.contextPath}/member/memberView" target="message">个人信息</a>
            </c:if>
        </div>
        <div id="R">
            <iframe name="message" frameborder="0" src="${pageContext.request.contextPath}/member/memberView" height="100%" width="100%" scrolling="yes"></iframe>
        </div>
    </div>
 </div>
</body>
</html>