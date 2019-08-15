<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset=utf-8">
	<title>编辑个人信息</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<link rel="stylesheet" href="../../css/style.css" />
	<script type="text/javascript" charset="utf-8" src="../../media/js/jquery.js"></script>
	<script type="text/javascript" charset="utf-8" src="../../media/js/jquery.dataTables.js"></script>
	<script src="../../Javascript/jQueryUI/jquery-ui-1.8.21.min.js"></script>
	<script src="../../Javascript/ClEditor/jquery.cleditor.js"></script>
	<script src="../../Javascript/ColorBox/jquery.colorbox.js"></script>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>
	<div class="g_6 contents_header">
		<h3 class=" tab_label">个人信息</h3>
		<div><span class="label">Self_info</span></div>
	</div>
	<div class="g_12 separator" id="self_sepa"><span></span></div>
	<div class="g_12 editor_info">
		<div class="widget_header" id="">
			<h4 class="widget_header_title wwIcon i_16_valid">编辑信息</h4>
		</div>
		<div class="widget_contents noPadding">
			<form id="form1" action="${pageContext.request.contextPath}/member/update" method="post" onsubmit="return false">
				<input type="hidden" name="_method" value="put" />
				<div class="line_grid">
					<div class="g_3"><span class="label">性别<span class="must">*</span></span></div>
					<div class="g_9">
						<input type="text" name="gender" placeholder="请输入性别" class="simple_field" required />
					</div>
				</div>
				<div class="line_grid">
					<div class="g_3"><span class="label">年龄 <span class="must">*</span></span></div>
					<div class="g_9">
						<input class="simple_field" name="age" type="text" required />
					</div>
				</div>
				<div class="line_grid">
					<div class="g_3"><span class="label">手机<span class="must">*</span></span></div>
					<div class="g_9">
						<input class="simple_field" name="mobile" type="text" required />
					</div>
				</div>
				<div class="line_grid">
					<div class="g_3"><span class="label">Email <span class="must">*</span></span></div>
					<div class="g_9">
						<input class="simple_field" name="email" type="email" required />
						<div class="field_notice">Try typing a wrong email, without @ for ex ..</div>
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
<script>
	function submitForm() {
		$.ajax({            //几个参数需要注意一下
			type: "POST",//方法类型
			dataType: "json",//预期服务器返回的数据类型
			url: "${pageContext.request.contextPath}/member/update" ,//url
			data: $('#form1').serialize(),
			success: function (result) {
				console.log(result);//打印服务端返回的数据(调试用)
				alert(result.msg);
				setTimeout("location.href='${pageContext.request.contextPath}/member/memberView'", 2000);
			},
			error : function() {
				alert("异常！");
			}
		});
	}
</script>
</body>
</html>