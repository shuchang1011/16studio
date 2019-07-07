<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>
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

	<!-- Kanrisha Script -->
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>
	<div class="g_6 contents_header">
		<h3 class=" tab_label">档案信息</h3>
		<div><span class="label"></span></div>
	</div>
	<div class="g_6 contents_options">
		<div class="simple_buttons" id="valid">
			<div class="bwIcon i_16_settings">
				<form action="${pageContext.request.contextPath}/document/download" method="get">
		    	  	<input type="hidden" name="path" value="${document.getPath() }"/>
		    	  	<input type="hidden" name="title" value="${document.getTitle() }"/>
		    	  	<input type="submit" value="下载附件" class="btn"/>
		   		</form>
            </div>
		</div>
	</div>
	<div class="g_12 separator"><span></span></div>
	<div class="g_12" id="self_info">
		<div class="widget_header">
			<h4 class="widget_header_title wwIcon i_16_forms">档案信息</h4>
		</div>
		<div class="widget_contents noPadding">
			<div class="line_grid">
				<div class="g_3"><span class="label">标题</span></div>
				<div class="g_9">
					<span class="label input">${document.getTitle()}</span>
				</div>
			</div>
			<div class="line_grid">
				<div class="g_3"><span class="label">描述</span></div>
				<div class="g_9">
					<span class="label input">${document.getDescription()}</span>
				</div>
			</div>
			<div class="line_grid">
				<div class="g_3"><span class="label">文件类型</span></div>
				<div class="g_9">
					<span class="label input">${document.getType()}</span>
				</div>
			</div>
			<c:if test="${document.getType() eq 'image' }">
				<div class="line_grid">
					<div class="g_3"><span class="label">图片</span></div>
					<div class="g_9">
						<span class="label input"><img alt="${document.getTitle() }" src="${pageContext.request.contextPath}/document/fileDownLoad/${filename}/${document.getType()}"></span>
					</div>
				</div>
			</c:if>
			<c:if test="${document.getType() eq 'video' }">
				<div class="line_grid">
					<div class="g_3"><span class="label">视频</span></div>
					<div class="g_9">
						<span class="label input"><video width="400" height="300" src="${pageContext.request.contextPath}/document/fileDownLoad/${filename}/${document.getType()}" type="video" controls></span>
					</div>
				</div>
			</c:if>
			<c:if test="${document.getType() eq 'file' }">
				<div class="line_grid">
					<div class="g_3"><span class="label">文件</span></div>
					<div class="g_9">
						<span class="label input">${document.getTitle() }
							<form action="${pageContext.request.contextPath}/document/preview" method="get">
								<input type="hidden" name="path" value="${document.getPath() }"/>
								<input type="hidden" name="type" value="${document.getType() }"/>
								<input type="hidden" name="swfName" value="${document.getId() }"/>
					    	  	<input type="submit" value="预览"  class="submitIt simple_buttons"/>
					    	</form>
					    </span>
					</div>
				</div>
			</c:if>
			<c:if test="${document.getType() eq 'html' }">
				${document.getContent() }
			</c:if>
		</div>
	</div>
	<%-- <p>标题：${document.getTitle()}</p>
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
	</c:if> --%>
	
   
</body>
</html>