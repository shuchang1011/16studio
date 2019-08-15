<%@page import="java.util.List"%>
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
	<div class="g_6 contents_header">
		<h3 class=" tab_label">审核管理</h3>
		<div><span class="label"></span></div>
	</div>
	<div class="g_12 separator"><span></span></div>
	<div class="g_12" id="self_info">
		<div class="widget_header wwOptions">
			<h4 class="widget_header_title wwIcon i_16_data">审核列表</h4>
		</div>
		<div class="widget_contents noPadding">
			<table class="datatable tables" id="DataTables_Table_0">
				<thead>
				<tr>
					<th style="width: 10%;">ID</th>
					<th style="width: 10%;">村落名</th>
					<th style="width: 10%;">文化类目</th>
					<th style="width: 10%;">文件类型</th>
					<th style="width: 10%;">提交人</th>
					<th style="width: 20%;">提交时间</th>
					<th style="width: 10%;">operation</th>
				</tr>
				<%
					int i = 1;
				%>
				<c:forEach items="${pageInfo.list}" var="list">
					<tr>
			            <td style="width: 10%;"><%=i++ %></td>
			            <td style="width: 10%;">${list.village }</td>
			            <td style="width: 10%;">${list.cultureaspect }</td>
			            <td style="width: 10%;">${list.type }</td>
			            <td style="width: 10%;">${list.createMember }</td>
			            <td style="width: 20%;">${list.submitDate }</td>
				        <td style="width: 10%;"><form action="${pageContext.request.contextPath}/document/getTempDoc/${list.getId()}" method="get">
		            	  	<input type="submit" value="查看详情"  class="submitIt simple_buttons"/>
		            	</form></td>
			        </tr>
				</c:forEach>
				</thead>
			</table>
		</div>
	</div>
</body>
</html>