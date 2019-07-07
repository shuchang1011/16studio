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
	<form action="${pageContext.request.contextPath}/document/updateCultureaspect" method="post">
		<input type="hidden" name="_method" value="put">
		<input type="hidden" name="id" value="${id}">
		<label>文化类别:</label><input type="text" name="title"><br>
		<input type="submit" value="提交">
	</form>
</body>
</html>