<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>
<%@page import="com.entity.Village,java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript" src="../js/jquery-3.2.1.min.js"></script>
</head>
<body>
	<form action="${pageContext.request.contextPath}/task/create" method="post">
		<input type="hidden" name="organizationId" value="${organizationId }"><br>
		<label>选择村落:</label><select id="village" name="villageId" onchange="BuildSelectBox(this.value)">
									<option>--请选择--</option>
									<c:forEach items="${villageList}" var="list">
										<option value="${list.id }">${list.name}</option>
									</c:forEach>
								</select><br>
		<label>选择类别:</label><select id="cultureaspect" name="cultureaspectId" >
								</select><br>
		
		
		<label>选择学者:</label>
			<c:forEach items="${scholarList}" var="list">
				${list.getAccount().getDisplayName()}<input type="checkbox" name="memberId" value="${list.getId()}">
			</c:forEach>
		<br>	
		<label>选择录入员 :</label>
			<c:forEach items="${inputorList}" var="list">
				${list.getAccount().getDisplayName()}<input type="checkbox" name="memberId" value="${list.getId()}">
			</c:forEach>
		<br>	
		<label>标题:</label><input type="text" name="title"><br>	
		<label>内容:</label><input type="text" name="content"><br>	
		<label>文件类型:</label><select name="fileType">
									<option>--请选择--</option>
									<option value="image">图片</option>
									<option value="file">文档</option>
									<option value="video">视频</option>
									<option value="html">html</option>
								</select>
		<input type="submit" value="提交">
	</form>
<script type="text/javascript">
	
function BuildSelectBox(par) {
	 $("#cultureaspect").empty();
	 $.getJSON("${pageContext.request.contextPath}/task/getCultureaspect", { id: par }, function (json, textStatus) {
		 alert(json.list.length);
	  for (var i = json.list.length - 1; i >= 0; i--) {
		  alert(json.list[i].id);
	   $("#cultureaspect").prepend('<option value="' + json.list[i].id + '">' + json.list[i].title + '</option>')
	  };
	  $("#cultureaspect").prepend('<option value="0">--请选择--</option>')
	 });
	}
</script>
</body>
</html>