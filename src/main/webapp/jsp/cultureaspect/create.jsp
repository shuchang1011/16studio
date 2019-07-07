<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<title>acient village</title>
	<!-- The Main CSS File -->
	<link rel="stylesheet" href="../css/style.css" />
	<script type="text/javascript" charset="utf-8" src="../media/js/jquery.js"></script>
	<script type="text/javascript" charset="utf-8" src="../media/js/jquery.dataTables.js"></script>
	<!-- jQuryUI -->
	<script src="../Javascript/jQueryUI/jquery-ui-1.8.21.min.js"></script>
	<!-- ClEditor -->
	<script src="../Javascript/ClEditor/jquery.cleditor.js"></script>
	<!-- Color Box -->
	<script src="../Javascript/ColorBox/jquery.colorbox.js"></script>

	<!-- Kanrisha Script -->
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>
	<div class="g_12 separator"><span></span></div>
	<div class="g_12 editor_info" id="editor_info">
		<div class="widget_header">
			<h4 class="widget_header_title wwIcon i_16_valid">添加文化类目</h4>
		</div>
		<div class="widget_contents noPadding">
			<form action="${pageContext.request.contextPath}/document/createCultureaspect" method="post">
			<div class="line_grid">
				<div class="g_3"><span class="label">文化类别<span class="must">*</span></span></div>
				<div class="g_9">
					<input type="text" name="title" class="simple_field" required />
				</div>
			</div>
			<div class="line_grid">
				<div class="g_3"><span class="label">Submit</span></div>
				<div class="g_9">
					<input type="submit" value="提交" class="submitIt simple_buttons" />
				</div>
			</div>
			</form>
		</div>
	</div>	
</body>
</html>