<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>
<%@page import="com.entity.Village,java.util.List" %>
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
	<script type="text/javascript" src="../js/jquery-3.2.1.min.js"></script>
</head>
<body>
	<div class="g_12 separator"><span></span></div>
	<div class="g_12 editor_info" id="editor_info">
		<div class="widget_header">
			<h4 class="widget_header_title wwIcon i_16_valid">添加任务</h4>
		</div>
		<div class="widget_contents noPadding">
			<form id="form1" action="${pageContext.request.contextPath}/task/create" method="post" onsubmit="return false">
			<input type="hidden" name="organizationId" value="${organizationId }">
			<div class="line_grid">
				<div class="g_3"><span class="label">选择村落<span class="must">*</span></span></div>
				<div class="g_9">
					<select id="village" name="villageId" onchange="BuildSelectBox(this.value)" class="simple_field" required>
						<option>--请选择--</option>
						<c:forEach items="${villageList}" var="list">
							<option value="${list.id }">${list.name}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="line_grid">
				<div class="g_3"><span class="label">选择类别<span class="must">*</span></span></div>
				<div class="g_9">
					<select id="cultureaspect" name="cultureaspectId" class="simple_field" required>
					</select>
				</div>
			</div>
			<div class="line_grid">
				<div class="g_3"><span class="label">选择学者<span class="must">*</span></span></div>
				<div class="g_9">
					<c:forEach items="${scholarList}" var="list">
						${list.getAccount().getDisplayName()}<input type="checkbox" name="memberId" value="${list.getId()}">
					</c:forEach>
				</div>
			</div>
			<div class="line_grid">
				<div class="g_3"><span class="label">选择录入员 <span class="must">*</span></span></div>
				<div class="g_9">
					<c:forEach items="${inputorList}" var="list">
						${list.getAccount().getDisplayName()}<input type="checkbox" name="memberId" value="${list.getId()}">
					</c:forEach>
				</div>
			</div>
			<div class="line_grid">
				<div class="g_3"><span class="label">标题<span class="must">*</span></span></div>
				<div class="g_9">
					<input type="text"  name="title" placeholder="" class="simple_field" required />
				</div>
			</div>
			
			<div class="line_grid">
				<div class="g_3"><span class="label">内容<span class="must">*</span></span></div>
				<div class="g_9">
					<input type="text" name="content" placeholder="" class="simple_field" required />
				</div>
			</div>
			
			<div class="line_grid">
				<div class="g_3"><span class="label">文件类型<span class="must">*</span></span></div>
				<div class="g_9">
					<select name="fileType" class="simple_field" required>
						<option>--请选择--</option>
						<option value="image">图片</option>
						<option value="file">文档</option>
						<option value="video">视频</option>
						<option value="html">html</option>
                    </select>
				</div>
			</div>
			<div class="line_grid">
				<div class="g_3"><span class="label">Submit</span></div>
				<div class="g_9">
					<input type="submit" value="提交" onclick="submitForm()" class="submitIt simple_buttons" />
				</div>
			</div>
			</form>
		</div>
	</div>
<script type="text/javascript">
	
function BuildSelectBox(par) {
	 $("#cultureaspect").empty();
	 $.getJSON("${pageContext.request.contextPath}/task/getCultureaspect", { id: par }, function (json, textStatus) {
	  for (var i = json.list.length - 1; i >= 0; i--) {
	      $("#cultureaspect").prepend('<option value="' + json.list[i].id + '">' + json.list[i].title + '</option>')
	  };
	  $("#cultureaspect").prepend('<option value="0">--请选择--</option>')
	 });
	}

function submitForm() {
    $.ajax({            //几个参数需要注意一下
        type: "POST",//方法类型
        dataType: "json",//预期服务器返回的数据类型
        url: "${pageContext.request.contextPath}/task/createTask" ,//url
        data: $('#form1').serialize(),
        success: function (result) {
            console.log(result);//打印服务端返回的数据(调试用)
            alert(result.msg);
            setTimeout("location.href='${pageContext.request.contextPath}/task/unFinishedTaskView'", 2000);
        },
        error : function() {
            alert("异常！");
        }
    });
}

</script>
</body>
</html>