<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript" src="../../ueditor/third-party/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="../../ueditor/ueditor.config.js"></script>
<script type="text/javascript" src="../../ueditor/ueditor.all.js"></script>
<script type="text/javascript" src="../../ueditor/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript" src="../../ueditor/index.js"></script>
</head>
<body>
	<h5>
		限定文件类型：
		<c:if test="${tempDoc.type eq 'image' }">图片</c:if>
		<c:if test="${tempDoc.type eq 'file' }">文档</c:if>
		<c:if test="${tempDoc.type eq 'video' }">视频</c:if>
		<c:if test="${tempDoc.type eq 'html' }">html</c:if>
		,若上传其他类型文件，只保存限定类型文件
	</h5>
	<form action="${pageContext.request.contextPath}/document/updateTemp" method="post" onsubmit="submitForm()">
		<input type="hidden" name="_method" value="put" />
		<input type="hidden" name="id" value="${tempDoc.id }" />
		<input type="hidden" name="type" value="${tempDoc.type }" />
		<input type="hidden" id="html" name="content" value="" />
		<label>标题:</label><input type="text" name="title" value="${tempDoc.title }"><br>
		<label>描述:</label><input type="text" name="description" value="${tempDoc.description }"><br>
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
		<c:when test="${tempDoc.getType() eq 'file' }">
			<div>
				<textarea id="myEditor" style="width: 100%; height: 285px;"><p><video class="edui-upload-video video-js vjs-default-skin video-js" controls="" preload="none" src="/shiroDemo/upload/fileDownLoad?url=${tempDoc.getPath() }" width="420" height="280"></video></p></textarea>
			</div>
		</c:when>
		
	</c:choose>
		<input type="submit" value="提交">
	</form>
<script type="text/javascript">
	
	var ue = UE.getEditor('myEditor');
	
	function submitForm() {
    	var html = UE.getEditor('myEditor').getContent();
    	//alert(html);
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