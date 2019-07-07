<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset=utf-8">
	<title>个人信息</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<link rel="stylesheet" href="../css/style.css" />
	<script type="text/javascript" charset="utf-8" src="../media/js/jquery.js"></script>
	<script type="text/javascript" charset="utf-8" src="../media/js/jquery.dataTables.js"></script>
	<script src="../Javascript/jQueryUI/jquery-ui-1.8.21.min.js"></script>
	<script src="../Javascript/ClEditor/jquery.cleditor.js"></script>
	<script src="../Javascript/ColorBox/jquery.colorbox.js"></script>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	
</head>
<body>
	<%-- <table>
		<c:if test="${not empty member}">
		<tr>
			<td></td>
			<td><form action="${pageContext.request.contextPath}/member/editMember/${member.getAccountId()}" method="get">
            	  	<input type="submit" value="编辑信息"/>
            </form></td>
		</tr>
		<tr>
			<td>用户名:</td>
			<td>${member.getAccount().getDisplayName()}</td>
		</tr>
		<tr>
			<td>性别:</td>
			<td>${member.getGender()}</td>
		</tr>
		<tr>
			<td>年龄:</td>
			<td>${member.getAge()}</td>
		</tr>
		<tr>
			<td>所在机构:</td>
			<c:if test="${not empty member.getOrganization() }"><td>${member.getOrganization()}</td></c:if>
			
		</tr>
		<tr>
			<td>手机:</td>
			<td>${member.getMobile()}</td>
		</tr>
		<tr>
			<td>E-mail:</td>
			<td>${member.getEmail()}</td>
		</tr>
	</c:if>
	<c:if test="${empty member }">
		尚未编辑个人信息！<br>
		<form action="${pageContext.request.contextPath}/member/editMember" method="get">
        	  	<input type="submit" value="编辑信息"/>
        </form>
	</c:if>
	</table> --%>
	<div class="g_6 contents_header">
		<h3 class=" tab_label">个人信息</h3>
		<div><span class="label">Self_info</span></div>
	</div>
	<div class="g_6 contents_options">
		<div class="simple_buttons" id="valid">
			<div class="bwIcon i_16_settings">
				<form action="${pageContext.request.contextPath}/member/editMember/${member.getAccountId()}" method="get">
	            	<input type="submit" value="编辑信息" class="btn"/>
	            </form>
            </div>
		</div>
	</div>

	<div class="g_12 separator"><span></span></div>

	<div class="g_12" id="self_info">
		<div class="widget_header">
			<h4 class="widget_header_title wwIcon i_16_forms">个人信息</h4>
		</div>
		<div class="widget_contents noPadding">
			<div class="line_grid">
				<div class="g_3"><span class="label">用户名</span></div>
				<div class="g_9">
					<span class="label input">${member.getAccount().getDisplayName()}</span>
				</div>
			</div>
			<div class="line_grid">
				<div class="g_3"><span class="label">性别</span></div>
				<div class="g_9">
					<span class="label input">${member.getGender()}</span>
				</div>
			</div>
			<div class="line_grid">
				<div class="g_3"><span class="label">年龄</span></div>
				<div class="g_9">
					<span class="label input">${member.getAge()}</span>
				</div>
			</div>
			<div class="line_grid">
				<div class="g_3"><span class="label">所在机构:</span></div>
				<div class="g_9">
					<span class="label input"><c:if test="${not empty member.getOrganization() }">${member.getOrganization()}</c:if></span>
				</div>
			</div>
			<div class="line_grid">
				<div class="g_3"><span class="label">手机:</span></div>
				<div class="g_9">
					<span class="label input">${member.getMobile()}</span>
				</div>
			</div>
			<div class="line_grid">
				<div class="g_3"><span class="label">E-mail</span></div>
				<div class="g_9">
					<span class="label input">${member.getEmail()}</span>
				</div>
			</div>
		</div>
	</div>
	
</body>
</html>