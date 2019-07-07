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
	<form action="${pageContext.request.contextPath}/sendMail" method="post" enctype="multipart/form-data">
		<label>recipients:</label><input type="text" name="recipients"><br>
		<label>subject:</label><input type="text" name="subject"><br>
		<label>content:</label><input type="text" name="content"><br>
		<label>attachment:</label><input type="file" name="attachment">
		<input type="submit" value="提交">
	</form>
</body>
</html>