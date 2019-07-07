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
	<form action="${pageContext.request.contextPath}/account/${account.getId() }/update" method="post">
		<input type="hidden" name="_method" value="PUT">
		<label>登陆名:</label><input type="text" name="accountName" value="${account.getAccountName() }"><br>
		<label>展示名:</label><input type="text" name="displayName" value="${account.getDisplayName() }"><br>
		<label>密码:</label><input type="password" name="password" value="${account.getPassword() }"><br>
		<label>角色:</label><select name="roleId">
		  						<option value="${role.getId() }" selected="selected">${role.getName()}</option>
								<c:forEach items="${roleList}" var="list">
									<option value="${list.getId() }">${list.getName()}</option>
								</c:forEach>
						    </select>
		<label>性别:</label><input type="text" name="gender" value="${member.getGender() }"><br>
		<label>年龄:</label><input type="text" name="age" value="${member.getAge() }"><br>
		<label>手机:</label><input type="text" name="mobile" value="${member.getMobile() }"><br>
		<label>邮箱:</label><input type="text" name="email" value="${member.getEmail() }"><br>
		<label>所处机构:</label><select name="organizationId">
			  						<option value="${organization.getId() }" selected="selected">${organization.getName()}</option>
									<c:forEach items="${organizationList}" var="list">
										<option value="${list.getId() }">${list.getName()}</option>
									</c:forEach>
						    	</select>
		<input type="submit" value="提交">
	</form>
</body>
</html>