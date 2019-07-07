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
	src="../../ueditor/third-party/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="../../ueditor/ueditor.config.js"></script>
<script type="text/javascript" src="../../ueditor/ueditor.all.js"></script>
<script type="text/javascript" src="../../ueditor/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript" src="../../ueditor/index.js"></script>
</head>
<body>
	<form action="${pageContext.request.contextPath}/document/archiveTempDoc/${tempDoc.getId()}" method="post">
		<input type="hidden" name="_method" value="put" />
		<input type="submit" name="state" value="归档" />
		<input type="submit" name="state" value="退回" />
    </form>
	<h5>
		文件类型：
		<c:if test="${tempDoc.getType() eq 'image' }">图片</c:if>
		<c:if test="${tempDoc.getType() eq 'file' }">文档</c:if>
		<c:if test="${tempDoc.getType() eq 'video' }">视频</c:if>
		<c:if test="${tempDoc.getType() eq 'html' }">html</c:if>
	</h5>
	<br>
	<label>标题:</label>${tempDoc.getTitle() }<br> 
	<label>描述:</label>${tempDoc.getDescription() }<br>
	<c:choose>
		<c:when test="${tempDoc.getType() eq 'html' }">
			<div>
				<textarea id="myEditor" style="width: 100%; height: 285px;">${tempDoc.getContent()}</textarea>
			</div>
		</c:when>
		<c:when test="${tempDoc.getType() eq 'image' }">
			<div>
				<textarea id="myEditor" style="width: 100%; height: 285px;"><p><img src="/ancientVillage/upload/viewImagesToPage?imagePath=${tempDoc.getPath() }" title="" alt="" width="374" height="431"/></p></textarea>
			</div>
		</c:when>
		<c:when test="${tempDoc.getType() eq 'video' }">
			<div>
				<textarea id="myEditor" style="width: 100%; height: 285px;"><p><video class="edui-upload-video video-js vjs-default-skin video-js" controls="" preload="none" src="/shiroDemo/upload/fileDownLoad?url=${tempDoc.getPath() }" width="420" height="280"></video></p></textarea>
			</div>
		</c:when>
		<c:otherwise>
			<form action="${pageContext.request.contextPath}/document/preview" method="get">
				<input type="hidden" name="path" value="${tempDoc.getPath() }"/>
				<input type="hidden" name="type" value="${tempDoc.getType() }"/>
	    	  	<input type="submit" value="预览"/>
	    	</form>
		</c:otherwise>
	</c:choose>
<script type="text/javascript">
	
	var ue = UE.getEditor('myEditor');
	
	function submitForm() {
    	var html = UE.getEditor('myEditor').getContent();
    	document.getElementById("html").value = html;
    }
	
	var editor = new UE.ui.Editor();
	editor.render('editor');
	
	editor.addListener( 'ready', function(edt) {
		var content_old = $('#content').html();
		if(content_old!=''){
			editor.execCommand('insertHtml', content_old);
		}
	 })
</script>
</body>
</html>
