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
	<form action="${pageContext.request.contextPath}/member/update" method="post">
		<input type="hidden" name="_method" value="put" />
		<label>性别:</label><input type="text" name="gender"><br>
		<label>年龄:</label><input type="text" name="age"><br>
		<label>手机:</label><input type="text" name="mobile"><br>
		<label>E-mail:</label><input type="text" name="email"><br>
		<input type="submit" value="提交">
	</form>
</body>
</html>