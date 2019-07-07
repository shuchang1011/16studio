<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<title>acient village</title>
	<!-- The Main CSS File -->
	<link rel="stylesheet" href="../../css/style.css" />
	<script type="text/javascript" charset="utf-8" src="../../media/js/jquery.js"></script>
	<script type="text/javascript" charset="utf-8" src="../../media/js/jquery.dataTables.js"></script>
	<!-- jQuryUI -->
	<script src="../../Javascript/jQueryUI/jquery-ui-1.8.21.min.js"></script>
	<!-- ClEditor -->
	<script src="../../Javascript/ClEditor/jquery.cleditor.js"></script>
	<!-- Color Box -->
	<script src="../../Javascript/ColorBox/jquery.colorbox.js"></script>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<script type="text/javascript" src="../../ueditor/third-party/jquery-1.10.2.min.js"></script>
	<script type="text/javascript" src="../../ueditor/ueditor.config.js"></script>
	<script type="text/javascript" src="../../ueditor/ueditor.all.js"></script>
	<script type="text/javascript" src="../../ueditor/lang/zh-cn/zh-cn.js"></script>
	<script type="text/javascript" src="../../ueditor/index.js"></script>
</head>
<body>
	<div class="g_6 contents_header">
		<h3 class=" tab_label">编辑档案</h3>
		<div><span class="label"></span></div>
		<h4 class=" tab_label">限定文件类型：
		<c:if test="${tempDoc.get(0).type eq 'image' }">图片</c:if>
		<c:if test="${tempDoc.get(0).type eq 'file' }">文档</c:if>
		<c:if test="${tempDoc.get(0).type eq 'video' }">视频</c:if>
		<c:if test="${tempDoc.get(0).type eq 'html' }">html</c:if>
		,若上传其他类型文件，只保存限定类型文件</h4>
	</div>
	<div class="g_12 separator"><span></span></div>
	<div class="g_12 editor_info" id="editor_info">
		<div class="widget_header">
			<h4 class="widget_header_title wwIcon i_16_valid">添加机构</h4>
		</div>
		<div class="widget_contents noPadding">
			<form action="${pageContext.request.contextPath}/document/updateTemp" onsubmit="submitForm()" method="post">
			<input type="hidden" name="_method" value="put" />
			<input type="hidden" name="id" value="${tempDoc.get(0).id }" />
			<input type="hidden" name="type" value="${tempDoc.get(0).type }" />
			<input type="hidden" name="villageId" value="${tempDoc.get(0).villageId }" />
			<input type="hidden" name="cultureaspectId" value="${tempDoc.get(0).cultureaspectId }" />
			<input type="hidden" name="createMemberId" value="${tempDoc.get(0).createMemberId }" />
			<input type="hidden" name="auditMemberId" value="${tempDoc.get(0).auditMemberId }" />
			<input type="hidden" name="fileMemberId" value="${tempDoc.get(0).fileMemberId }" />
			<input type="hidden" name="fileId" value="${tempDoc.get(0).fileId }" />
			<input type="hidden" id="html" name="content" value="" />
			<div class="line_grid">
				<div class="g_3"><span class="label">标题</span></div>
				<div class="g_9">
					<input type="text" name="title" placeholder="" class="simple_field" required/>
				</div>
			</div>
			<div class="line_grid">
				<div class="g_3"><span class="label">描述</span></div>
				<div class="g_9">
					<input type="text" name="description" placeholder="" class="simple_field" required/>
				</div>
			</div>
			<c:choose>
				<c:when test="${tempDoc.get(0).getType() eq 'html' }">
					<div class="line_grid">
						<div>
							<textarea id="myEditor" style="width: 100%; height: 285px;">${tempDoc.get(0).getContent()}</textarea>
						</div>
					</div>
				</c:when>
				<c:when test="${tempDoc.get(0).getType() eq 'image' }">
					<div class="line_grid">
						<div>
							<textarea id="myEditor" style="width: 100%; height: 285px;">
								<c:forEach items='${tempDoc}' var='list'>
									<p><img src="/ancientVillage/upload/viewImagesToPage?imagePath=${list.getPath() }" title="" alt="" width="374" height="431"/></p>
								</c:forEach>
							</textarea>
						</div>
					</div>
				</c:when>
				<c:when test="${tempDoc.get(0).getType() eq 'video' }">
				<div class="line_grid">
					<div>
						<textarea id="myEditor" style="width: 100%; height: 285px;">
							<c:forEach items='${tempDoc}' var='list'>
								<p><video class="edui-upload-video video-js vjs-default-skin video-js" controls="" preload="none" src="/shiroDemo/upload/fileDownLoad?url=${list.getPath() }" width="420" height="280"></video></p>
							</c:forEach>
						</textarea>
					</div>
				</div>
				</c:when>
				<c:when test="${tempDoc.get(0).getType() eq 'file' }">
					<div class="line_grid">
						<div>
							<textarea id="myEditor" style="width: 100%; height: 285px;">
								<c:forEach items='${tempDoc}' var='list'>
									<p><img style="vertical-align: middle; margin-right: 2px; width: 25px; height: 25px;" src="http://localhost:8080/ancientVillage/ueditor/dialogs/attachment/fileTypeImages/icon_${list.getFormat()}.gif"/><a style="font-size:12px; color:#0066cc;" href="/ancientVillage/upload/fileDownLoad?url=${list.getPath() }&filename=${list.getTitle()}.${list.getFormat()}" title="16级工作室通讯录.docx">${list.getTitle() }</a></p>
								</c:forEach>
							</textarea>
						</div>
					</div>
				</c:when>
			</c:choose>
			<div class="line_grid">
				<div class="g_3"><span class="label">Submit</span></div>
				<div class="g_9">
					<input type="submit" value="提交" class="submitIt simple_buttons" />
				</div>
			</div>
			</form>
		</div>
	</div>
	<%-- <form action="${pageContext.request.contextPath}/document/updateTemp" method="post" onsubmit="submitForm()">
		<input type="hidden" name="_method" value="put" />
		<input type="hidden" name="id" value="${tempDoc.get(0).id }" />
		<input type="hidden" name="type" value="${tempDoc.get(0).type }" />
		<input type="hidden" name="villageId" value="${tempDoc.get(0).villageId }" />
		<input type="hidden" name="cultureaspectId" value="${tempDoc.get(0).cultureaspectId }" />
		<input type="hidden" name="createMemberId" value="${tempDoc.get(0).createMemberId }" />
		<input type="hidden" name="auditMemberId" value="${tempDoc.get(0).auditMemberId }" />
		<input type="hidden" name="fileMemberId" value="${tempDoc.get(0).fileMemberId }" />
		<input type="hidden" name="fileId" value="${tempDoc.get(0).fileId }" />
		<input type="hidden" id="html" name="content" value="" />
		<label>标题:</label><input type="text" name="title" value="${tempDoc.get(0).title }"><br>
		<label>描述:</label><input type="text" name="description" value="${tempDoc.get(0).description }"><br>
		<c:choose>
		<c:when test="${tempDoc.get(0).getType() eq 'html' }">
			<div>
				<textarea id="myEditor" style="width: 100%; height: 285px;">${tempDoc.get(0).getContent()}</textarea>
			</div>
		</c:when>
		<c:when test="${tempDoc.get(0).getType() eq 'image' }">
			<div>
				<textarea id="myEditor" style="width: 100%; height: 285px;">
					<c:forEach items='${tempDoc}' var='list'>
						<p><img src="/ancientVillage/upload/viewImagesToPage?imagePath=${list.getPath() }" title="" alt="" width="374" height="431"/></p>
					</c:forEach>
				</textarea>
			</div>
		</c:when>
		<c:when test="${tempDoc.get(0).getType() eq 'video' }">
			<div>
				<textarea id="myEditor" style="width: 100%; height: 285px;">
					<c:forEach items='${tempDoc}' var='list'>
						<p><video class="edui-upload-video video-js vjs-default-skin video-js" controls="" preload="none" src="/shiroDemo/upload/fileDownLoad?url=${list.getPath() }" width="420" height="280"></video></p>
					</c:forEach>
				</textarea>
			</div>
		</c:when>
		<c:when test="${tempDoc.get(0).getType() eq 'file' }">
			<div>
				<textarea id="myEditor" style="width: 100%; height: 285px;">
					<c:forEach items='${tempDoc}' var='list'>
						<p><img style="vertical-align: middle; margin-right: 2px; width: 25px; height: 25px;" src="http://localhost:8080/ancientVillage/ueditor/dialogs/attachment/fileTypeImages/icon_${list.getFormat()}.gif"/><a style="font-size:12px; color:#0066cc;" href="/ancientVillage/upload/fileDownLoad?url=${list.getPath() }&filename=${list.getTitle()}.${list.getFormat()}" title="16级工作室通讯录.docx">${list.getTitle() }</a></p>
					</c:forEach>
				</textarea>
			</div>
		</c:when>
		
	</c:choose>
		<input type="submit" value="提交">
	</form> --%>
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