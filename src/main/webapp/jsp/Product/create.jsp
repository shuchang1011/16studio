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
	<form action="${pageContext.request.contextPath}/productCreate" method="post">
		<label>accountName:</label><input type="text" name="accountName"><br>
		<label>displayName:</label><input type="text" name="displayName"><br>
		<label>password:</label><input type="password" name="password"><br>
		<label>mobile:</label><input type="text" name="mobile"><br>
		<input type="submit" value="提交">
	</form>
</body>
</html>