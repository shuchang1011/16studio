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
</head>
<body>
	<div id="container">
		<div></div>
		<div class="admin-login-background">
			<!--<div class="admin-header">-->
			<!--<img src="image/ex_logo.png" class="admin-logo">-->
			<!--</div>-->
			<h1 style="text-align: center; margin-top: -80px; font-size: 36px; color: rgba(0,0,0,0.6);">古村落档案信息数字化管理系统</h1>
			<form class="layui-form" method="post" action="${pageContext.request.contextPath}/Login/logon">
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
				<!-- <div>
					<input type="text" name="verify" placeholder="请输入验证码" autocomplete="off" class="layui-input admin-input admin-input-verify"> 
					<img class="admin-captcha" src="" onclick="updateVerify()">
				</div> -->
				<div class="layui-input-inline login-btn">
					<button class="layui-btn admin-button">登陆</button>
				</div>
				
			</form>
		</div>
	</div>
</body>
</html>

<%-- <!DOCTYPE html>
<html lang="en-US">
<head>
    <meta charset="utf-8" />
    <title>登陆</title>
    <base href="<%=basePath%>">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="css/style.css">
	<link rel="stylesheet" href="layui/css/layui.css">
	<!-- jQuryUI -->
	<script src="Javascript/jQueryUI/jquery-ui-1.8.21.min.js"></script>
	<!-- ColorPicker -->
	<script src="Javascript/ColorPicker/colorpicker.js"></script>
	<!-- ClEditor -->
	<script src="Javascript/ClEditor/jquery.cleditor.js"></script>
	<!-- Color Box -->
	<script src="Javascript/ColorBox/jquery.colorbox.js"></script>
</head>
<body>
    <div id="container">

        <h1 style="text-align: center; margin-top: 80px; font-size: 36px; color: rgba(0,0,0,0.6);">古村落档案信息数字化管理系统</h1>

        <div class="wrapper contents_wrapper">
		
		<div class="login">
			<div class="widget_header">
				<h4 class="widget_header_title wwIcon i_16_login">Login</h4>
			</div>
			<div class="widget_contents lgNoPadding" style="text-align:center;">
				<form method="post" action="${pageContext.request.contextPath}/Login/logon">
				<div class="line_grid">
					<div class="g_2 g_2M"><span class="label">User</span></div>
					<div class="g_10 g_10M">
						<input class="simple_field tooltip" name="username" title="Your Username" type="text" placeholder="Username"></div>
					<div class="clear"></div>
				</div>
				<div class="line_grid">
					<div class="g_2 g_2M"><span class="label">Password</span></div>
					<div class="g_10 g_10M">
						<input class="simple_field tooltip" name="password" title="Your Password" type="password">
					</div>
					<div class="clear"></div>
				</div>
				<div class="line_grid">
					<div class="g_6"><input class="submitIt simple_buttons" value="Log In" type="submit">
					</div>
					<div class="clear"></div>
				</div>
				 <font color="red">${requestScope.message}</font><br>
				</form>
			</div>
		</div>
	</div>
       
    </div>
</body>
</html> --%>