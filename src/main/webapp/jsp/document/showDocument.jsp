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
		<h3 class=" tab_label">档案查阅</h3>
		<div><span class="label"></span></div>
	</div>
	<div class="g_12 separator"><span></span></div>
	<div class="g_12" id="self_info">
		<div class="widget_header wwOptions">
			<h4 class="widget_header_title wwIcon i_16_data">档案列表</h4>
		</div>
		<div class="widget_header wwOptions">
			<h4 class="widget_header_title wwIcon i_16_data">
				<form action="${pageContext.request.contextPath}/document/conditionalQuery" method="get">
					<input type="text" name="condition"/>
					<select name="type">
							<option>--请选择--</option>
							<option value="village">村落</option>
							<option value="type">类型</option>
							<option value="title">文件名</option>
					</select>
					<input type="submit" value="submit" class="submitIt simple_buttons"/>
				</form>
			</h4>
		</div>
		<div class="widget_contents noPadding">
			<table class="datatable tables" id="DataTables_Table_0">
				<thead>
				<tr>
					<th style="width: 10%;">ID</th>
					<th style="width: 20%;">标题</th>
					<th style="width: 20%;">描述</th>
					<th style="width: 20%;">创建时间</th>
					<th style="width: 20%;">operation</th>
				</tr>
				<%
					int i = 1;
				%>
				<c:forEach items="${pageInfo.list}" var="list">
					<tr>
			            <td style="width: 10%;"><%=i++ %></td>
			            <td style="width: 20%;">${list.title }</td>
			            <td style="width: 20%;">${list.description }</td>
			            <td style="width: 20%;">${list.createDate }</td>
		          	 	<td style="width: 20%;text-align: center"><form action="${pageContext.request.contextPath}/document/getOne/${list.getId()}" method="get">
		            	  	<input type="submit" value="查看详情"  class="submitIt simple_buttons"/>
		            	</form></td>
			        </tr>
				</c:forEach>
				</thead>
			</table>
		</div>
		<div class="g_6 pageNum">
	       	 当前第 ${pageInfo.pageNum} 页.总共 ${pageInfo.pages} 页.一共 ${pageInfo.total} 条记录
	    </div>
	 
	        <!--点击分页-->
	        <div class="g_6">
            <nav aria-label="Page navigation">
                <ul class="pagination">
                    
                    <li><a href="${pageContext.request.contextPath}/document/conditionalQuery?pn=1&condition=${condition}&type=${type}">首页</a></li>
                    
                    <!--上一页-->
                    <li>
                        <c:if test="${pageInfo.hasPreviousPage}">
                            <a href="${pageContext.request.contextPath}/document/conditionalQuery?pn=${pageInfo.pageNum-1}&condition=${condition}&type=${type}" aria-label="Previous">
                                <span aria-hidden="true">«</span>
                            </a>
                        </c:if>
                    </li>
 
                    <!--循环遍历连续显示的页面，若是当前页就高亮显示，并且没有链接-->
                    <c:forEach items="${pageInfo.navigatepageNums}" var="page_num">
                        <c:if test="${page_num == pageInfo.pageNum}">
                            <li class="active"><a href="#">${page_num}</a></li>
                        </c:if>
                        <c:if test="${page_num != pageInfo.pageNum}">
                            <li><a href="${pageContext.request.contextPath}/document/conditionalQuery?pn=${page_num}&condition=${condition}&type=${type}">${page_num}</a></li>
                        </c:if>
                    </c:forEach>
 
                    <!--下一页-->
                    <li>
                        <c:if test="${pageInfo.hasNextPage}">
                            <a href="${pageContext.request.contextPath}/document/conditionalQuery?pn=${pageInfo.pageNum+1}&condition=${condition}&type=${type}"
                               aria-label="Next">
                                <span aria-hidden="true">»</span>
                            </a>
                        </c:if>
                    </li>
                    
                    <li><a href="${pageContext.request.contextPath}/document/conditionalQuery?pn=${pageInfo.pages}&condition=${condition}&type=${type}">尾页</a></li>
                </ul>
            </nav>
        </div>
	</div>
</body>
</html>