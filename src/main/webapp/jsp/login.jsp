<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page isELIgnored="false"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<meta name="renderer" content="webkit">
	<title>古村落档案信息数字化管理系统</title>
	<link rel="stylesheet" href="../layui/css/layui.css">
	<link rel="stylesheet" href="../css/style.css">
	<script type="text/javascript" charset="utf-8" src="../media/js/jquery.js"></script>
</head>
<body>
	<div id="container">
		<div></div>
		<div class="admin-login-background">
			<!--<div class="admin-header">-->
			<!--<img src="image/ex_logo.png" class="admin-logo">-->
			<!--</div>-->
			<h1 style="text-align: center; margin-top: -80px; font-size: 36px; color: rgba(0,0,0,0.6);">古村落档案信息数字化管理系统</h1>
			<form id="form1" class="layui-form" method="post" action="${pageContext.request.contextPath}/Login/logon" onsubmit="return false">
				<div class="layui-input-inline">
					<i class="layui-icon layui-icon-username admin-icon" ></i>
					<input type="text" name="username" placeholder="请输入用户名" autocomplete="off"
						class="layui-input admin-input admin-input-username">
				</div>
				<div class="layui-input-inline">
					<i class="layui-icon layui-icon-password admin-icon"></i>
					<input type="password" name="password" placeholder="请输入密码"
						autocomplete="off" class="layui-input admin-input">
				</div>
				<div class="layui-input-inline login-btn">
					<input type="submit" value="登录" class="layui-btn admin-button" onclick="submitForm()"/>
				</div>
			</form>
		</div>
	</div>
<script>
	function submitForm() {
		$.ajax({            //几个参数需要注意一下
			type: "POST",//方法类型
			dataType: "json",//预期服务器返回的数据类型
			url: "${pageContext.request.contextPath}/Login/logon" ,//url
			data: $('#form1').serialize(),
			success: function (result) {
				//console.log(result);//打印服务端返回的数据(调试用)
				if(result.url == "index"){
					window.location.href='${pageContext.request.contextPath}/Login/index?msg='+result.msg;
				}else{
					alert(result.msg);
				}
			},
			error : function() {
				alert("异常！");
			}
		});
	}
</script>
</body>
</html>