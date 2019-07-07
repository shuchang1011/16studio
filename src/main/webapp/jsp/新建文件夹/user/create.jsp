<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@page import="java.util.List"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="${pageContext.request.contextPath}/account/create" method="post">
		<label>登陆名:</label><input type="text" name="accountName"><br>
		<label>展示名:</label><input type="text" name="displayName"><br>
		<label>密码:</label><input type="password" name="password"><br>
		<label>角色:</label><select name="roleId">
		  						<option>--请选择--</option>
								<c:forEach items="${roleList}" var="list">
									<option value="${list.getId() }">${list.getName()}</option>
								</c:forEach>
						    </select>
		<label>性别:</label><input type="text" name="gender"><br>
		<label>年龄:</label><input type="text" name="age"><br>
		<label>手机:</label><input type="text" name="mobile"><br>
		<label>邮箱:</label><input type="text" name="email"><br>
		<label>所处机构:</label><select name="organizationId">
			  						<option>--请选择--</option>
									<c:forEach items="${organizationList}" var="list">
										<option value="${list.getId() }">${list.getName()}</option>
									</c:forEach>
						    	</select>
		<input type="submit" value="提交">
	</form>
</body>
</html>