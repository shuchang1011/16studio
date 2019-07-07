<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>档案详情</title>
</head>
<body>
	<a href="${pageContext.request.contextPath}/task/finishedTaskView">已完成任务</a><br>
	<a href="${pageContext.request.contextPath}/task/unFinishedTaskView">未完成任务</a><br>
	<a href="${pageContext.request.contextPath}/document/archive">任务通知</a><br>
</body>
</html>