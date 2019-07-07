<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript"
	src="../../../ueditor/third-party/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="../../../ueditor/ueditor.config.js"></script>
<script type="text/javascript" src="../../../ueditor/ueditor.all.js"></script>
<script type="text/javascript"
	src="../../../ueditor/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript" src="../../../ueditor/index.js"></script>
</head>
<body>
	<h5>
		限定文件类型：
		<c:if test="${fileType eq 'image' }">图片</c:if>
		<c:if test="${fileType eq 'file' }">文档</c:if>
		<c:if test="${fileType eq 'video' }">视频</c:if>
		<c:if test="${fileType eq 'html' }">html</c:if>
		,若上传其他类型文件，只保存限定类型文件
	</h5>
	<form action="${pageContext.request.contextPath}/document/createTempDoc" onsubmit="submitForm()" method="post">
		<input type="hidden" name="villageId" value="${villageId }"> 
		<input type="hidden" name="cultureaspectId" value="${cultureaspectId }">
		<input type="hidden" name="createMemberId" value="${memberId }">
		<input type="hidden" name="fileType" value="${fileType }">
		<input type="hidden" id="html" name="content" value=""> 
		<label>选择审核人:</label>
		<select name="auditMemberId">
				<option>--请选择--</option>
				<c:forEach items="${scholarList}" var="list">
					<option value="${list.id }">${list.getAccount().getDisplayName()}</option>
				</c:forEach>
		</select><br> 
		<label>标题:</label><input type="text" name="title"><br>
		<label>描述:</label><input type="text" name="description"><br>
		<div>
			<textarea id="myEditor" style="width: 100%; height: 285px;"></textarea>
		</div> 
		<input type="submit" value="提交">
	</form>
<script type="text/javascript">
	//实例化编辑器
	//建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
	var ue = UE.getEditor('myEditor');
	function submitForm() {
		var html = UE.getEditor('myEditor').getContent();
		alert("123");
		alert(html)
		document.getElementById("html").value = html;
		var a = document.getElementsByName("content");
		for (var i = 0; i < a.length; i++) {
			alert(a[i].value);
		}

	}
</script>
</body>
</html>
