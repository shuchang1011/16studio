<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="${pageContext.request.contextPath}/village/updateVillage" method="post">
		<input type="hidden" name="_method" value="PUT">
		<input type="hidden" name="id" value="${id}">
		<label>村落名:</label><input type="text" name="name"><br>
		<label>所在地:</label><input type="text" name="location"><br>
		<label>描述:</label><input type="text" name="description"><br>
		<input type="submit" value="提交">
	</form>
</body>
</html>