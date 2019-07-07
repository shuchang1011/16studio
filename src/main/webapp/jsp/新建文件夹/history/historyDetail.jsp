<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<style>
li{
	text-decoration: none;
	list-style: none;
	display: inline;	
}
.active{
	background-color: #30DDEB;
}
</style>
<title>村落信息</title>
</head>
<body>
	<table>
		<tr>
			<td colspan="3"></td>
			<td><form action="${pageContext.request.contextPath}/history/restoreHistory" method="post">
					<input type="hidden" name="operationId" value="${operation.getId() }">
            	  	<input type="submit" value="还原"/>
            </form></td>
		</tr>
		<tr>
			<td><font size="6" color="red">${operation.getOperation() }</font></td>
		</tr>
		<tr>
			<td>历史记录：</td>
		</tr>
		
		<tr>
			<th>ID</th>
			<th>村落名</th>
			<th>所在地</th>
			<th>描述</th>
			<th>创建机构</th>
			<th>修改时间</th>
		</tr>
		<tr>
            <td>${villageHistory.id }</td>
            <td>${villageHistory.name }</td>
            <td>${villageHistory.location }</td>
            <td>${villageHistory.description }</td>
            <td>${villageHistory.organization }</td>
            <td>${villageHistory.changeDate }</td>
        </tr>
        <tr></tr>
        <tr>
			<td>当前村落信息：</td>
		</tr>
		<tr>
			<th>ID</th>
			<th>村落名</th>
			<th>所在地</th>
			<th>描述</th>
			<th>创建机构</th>
			<th>创建时间</th>
		</tr>
		<tr>
            <td>${village.id }</td>
            <td>${village.name }</td>
            <td>${village.location }</td>
            <td>${village.description }</td>
            <td>${village.organization }</td>
            <td>${village.createDate }</td>
        </tr>
	</table>
</body>
</html>