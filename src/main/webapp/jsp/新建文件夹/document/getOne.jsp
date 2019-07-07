<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>查看详情</title>
</head>
<body>
	<p>标题：${document.getTitle()}</p>
	<p>描述：${document.getDescription()}</p>
	<p>文件类型：${document.getType()}</p>
	<form action="${pageContext.request.contextPath}/document/download" method="get">
    	  	<input type="hidden" name="path" value="${document.getPath() }"/>
    	  	<input type="hidden" name="title" value="${document.getTitle() }"/>
    	  	<input type="submit" value="下载附件"/>
    </form>
	<c:if test="${document.getType() eq 'image' }">
		<img alt="${document.getTitle() }" src="${pageContext.request.contextPath}/document/fileDownLoad/${filename}/${document.getType()}">
	</c:if>
	<c:if test="${document.getType() eq 'video' }">
		<video width="400" height="300" src="${pageContext.request.contextPath}/document/fileDownLoad/${filename}/${document.getType()}" type="video" controls>
	</c:if>
	<c:if test="${document.getType() eq 'file' }">
		<form action="${pageContext.request.contextPath}/document/preview" method="get">
			<input type="hidden" name="path" value="${document.getPath() }"/>
			<input type="hidden" name="type" value="${document.getType() }"/>
    	  	<input type="submit" value="预览"/>
    	</form>
	</c:if>
	
   
</body>
</html>