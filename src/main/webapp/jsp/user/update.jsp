<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@page import="java.util.List"%>
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
	<div class="g_12 separator"><span></span></div>
	<div class="g_12 editor_info" id="editor_info">
		<div class="widget_header">
			<h4 class="widget_header_title wwIcon i_16_valid">编辑用户</h4>
		</div>
		<div class="widget_contents noPadding">
			<form action="${pageContext.request.contextPath}/account/update" method="post">
			<input type="hidden" name="_method" value="put" />
			<input type="hidden" name="id" value="${id}"><br>
			<div class="line_grid">
				<div class="g_3"><span class="label">登陆名<span class="must">*</span></span></div>
				<div class="g_9">
					<input type="text" name="accountName" value="${account.getAccountName() }" class="simple_field" required />
				</div>
			</div>
			<div class="line_grid">
				<div class="g_3"><span class="label">展示名<span class="must">*</span></span></div>
				<div class="g_9">
					<input type="text"  name="displayName" value="${account.getDisplayName() }" class="simple_field" required />
				</div>
			</div>
			<div class="line_grid">
				<div class="g_3"><span class="label">密码<span class="must">*</span></span></div>
				<div class="g_9">
					<input type="password"  name="password" value="${account.getPassword() }" class="simple_field" required />
				</div>
			</div>
			<div class="line_grid">
				<div class="g_3"><span class="label">角色<span class="must">*</span></span></div>
				<div class="g_9">
					<select name="roleId" class="simple_field" required>
  						<option value="${role.getId() }" selected="selected">${role.getName()}</option>
						<c:forEach items="${roleList}" var="list">
							<option value="${list.getId() }">${list.getName()}</option>
						</c:forEach>
				    </select>
				</div>
			</div>
			<div class="line_grid">
				<div class="g_3"><span class="label">性别<span class="must">*</span></span></div>
				<div class="g_9">
					<input type="text" name="gender" value="${member.getGender() }" class="simple_field" required />
				</div>
			</div>
			<div class="line_grid">
				<div class="g_3"><span class="label">年龄<span class="must">*</span></span></div>
				<div class="g_9">
					<input type="text"  name="age" value="${member.getAge() }" class="simple_field" required />
				</div>
			</div>
			<div class="line_grid">
				<div class="g_3"><span class="label">手机<span class="must">*</span></span></div>
				<div class="g_9">
					<input type="text"  name="mobile" value="${member.getMobile() }" class="simple_field" required />
				</div>
			</div>
			<div class="line_grid">
				<div class="g_3"><span class="label">邮箱<span class="must">*</span></span></div>
				<div class="g_9">
					<input type="text"  name="email" value="${member.getEmail() }" class="simple_field" required />
				</div>
			</div>
			<div class="line_grid">
				<div class="g_3"><span class="label">所处机构<span class="must">*</span></span></div>
				<div class="g_9">
					<select name="organizationId" class="simple_field" required>
  						<option value="${organization.getId() }" selected="selected">${organization.getName()}</option>
						<c:forEach items="${organizationList}" var="list">
							<option value="${list.getId() }">${list.getName()}</option>
						</c:forEach>
			    	</select>
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