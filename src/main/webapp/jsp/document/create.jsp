<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<title>acient village</title>
	<!-- The Main CSS File -->
	<link rel="stylesheet" href="../../../css/style.css" />
	<script type="text/javascript" charset="utf-8" src="../../../media/js/jquery.js"></script>
	<script type="text/javascript" charset="utf-8" src="../../../media/js/jquery.dataTables.js"></script>
	<!-- jQuryUI -->
	<script src="../../../Javascript/jQueryUI/jquery-ui-1.8.21.min.js"></script>
	<!-- ClEditor -->
	<script src="../../../Javascript/ClEditor/jquery.cleditor.js"></script>
	<!-- Color Box -->
	<script src="../../../Javascript/ColorBox/jquery.colorbox.js"></script>
	<!-- Ueditor -->
	<script type="text/javascript" src="../../../ueditor/third-party/jquery-1.10.2.min.js"></script>
	<script type="text/javascript" src="../../../ueditor/ueditor.config.js"></script>
	<script type="text/javascript" src="../../../ueditor/ueditor.all.js"></script>
	<script type="text/javascript" src="../../../ueditor/lang/zh-cn/zh-cn.js"></script>
	<script type="text/javascript" src="../../../ueditor/index.js"></script>
	<!-- Kanrisha Script -->
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>
	<div class="g_6 contents_header">
		<h3 class=" tab_label">添加档案</h3>
		<div><span class="label"></span></div>
		<h4 class=" tab_label">限定文件类型：
		<c:if test="${fileType eq 'image' }">图片</c:if>
		<c:if test="${fileType eq 'file' }">文档</c:if>
		<c:if test="${fileType eq 'video' }">视频</c:if>
		<c:if test="${fileType eq 'html' }">html</c:if>
		,若上传其他类型文件，只保存限定类型文件</h4>
	</div>
	<div class="g_12 separator"><span></span></div>
	<div class="g_12 editor_info" id="editor_info">
		<div class="widget_contents noPadding">
			<form id="form1" action="${pageContext.request.contextPath}/document/createTempDoc" onsubmit="return false" method="post">
			<input type="hidden" name="villageId" value="${villageId }"> 
			<input type="hidden" name="cultureaspectId" value="${cultureaspectId }">
			<input type="hidden" name="createMemberId" value="${memberId }">
			<input type="hidden" name="fileType" value="${fileType }">
			<input type="hidden" id="html" name="content" value=""> 
			<div class="line_grid">
				<div class="g_3"><span class="label">选择审核人<span class="must">*</span></span></div>
				<div class="g_9">
					<select name="auditMemberId"  class="simple_field" required>
						<option>--请选择--</option>
						<c:forEach items="${scholarList}" var="list">
							<option value="${list.id }">${list.getAccount().getDisplayName()}</option>
						</c:forEach>
					</select>
				</div>
			</div>
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
			<div class="line_grid">
				<div>
					<textarea id="myEditor" style="width: 100%; height: 285px;overflow-y:scroll;"></textarea>
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
	//实例化编辑器
	//建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
	var ue = UE.getEditor('myEditor');
	function submitForm() {
		var html = UE.getEditor('myEditor').getContent();
		document.getElementById("html").value = html;
		$.ajax({            //几个参数需要注意一下
			type: "POST",//方法类型
			dataType: "json",//预期服务器返回的数据类型
			url: "${pageContext.request.contextPath}/document/createTempDoc" ,//url
			data: $('#form1').serialize(),
			success: function (result) {
				//console.log(result);//打印服务端返回的数据(调试用)
				alert(result.msg);
				setTimeout("location.href='${pageContext.request.contextPath}/document/showMyTempDoc'", 2000);
			},
			error : function() {
				alert("异常！");
			}
		});
	}
</script>
</body>
</html>
