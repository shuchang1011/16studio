<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>个人信息</title>
</head>
<body>
	<table>
		<c:if test="${not empty member}">
		<tr>
			<td></td>
			<td><form action="${pageContext.request.contextPath}/member/editMember/${member.getAccountId()}" method="get">
            	  	<input type="submit" value="编辑信息"/>
            </form></td>
		</tr>
		<tr>
			<td>用户名:</td>
			<td>${member.getAccount().getDisplayName()}</td>
		</tr>
		<tr>
			<td>性别:</td>
			<td>${member.getGender()}</td>
		</tr>
		<tr>
			<td>年龄:</td>
			<td>${member.getAge()}</td>
		</tr>
		<tr>
			<td>所在机构:</td>
			<c:if test="${not empty member.getOrganization() }"><td>${member.getOrganization()}</td></c:if>
			
		</tr>
		<tr>
			<td>手机:</td>
			<td>${member.getMobile()}</td>
		</tr>
		<tr>
			<td>E-mail:</td>
			<td>${member.getEmail()}</td>
		</tr>
	</c:if>
	<c:if test="${empty member }">
		尚未编辑个人信息！<br>
		<form action="${pageContext.request.contextPath}/member/editMember" method="get">
        	  	<input type="submit" value="编辑信息"/>
        </form>
	</c:if>
	</table>
	
	
</body>
</html>