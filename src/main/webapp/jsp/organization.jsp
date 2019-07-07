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
	<style type="text/css" rel="stylesheet">
        .pageNum{
            color: #9D9D9D;
        }

        .pagination{
            height: 22px;
            margin: 0
        }
        .pagination li{
            float: left;
            margin-right: 10px;
        }
        .pagination li a{
            color: #9d9d9d;
        }
    </style>
</head>
<body>
	<div class="g_6 contents_header">
		<h3 class=" tab_label">机构管理</h3>
		<div><span class="label">Anstitution Management</span></div>
	</div>
	<div class="g_6 contents_options">
		<div class="simple_buttons" id="valid">
			<div class="bwIcon i_16_settings">
				<form action="${pageContext.request.contextPath}/organization/create" method="get">
	            	<input type="submit" value="添加机构" class="btn"/>
	            </form>
			</div>
		</div>
	</div>
	<div class="g_12 separator"><span></span></div>


	<!-- Quick Statistics -->
	<div class="g_12" id="self_info">
		<div class="widget_header wwOptions">
			<h4 class="widget_header_title wwIcon i_16_data">机构列表</h4>
		</div>
		<div class="widget_contents noPadding">
			<table class="datatable tables" id="DataTables_Table_0">
				<thead>
				<tr>
					<th style="width: 25%;">ID</th>
					<th style="width: 15%;">机构名</th>
					<th style="width: 20%;">创建时间</th>
					<th colspan="2" style="width: 20%;">operation</th>
				</tr>
				<c:forEach items="${pageInfo.list}" var="list">
					<tr>
			            <td style="width: 25%;">${list.id }</td>
			            <td style="width: 15%;">${list.name }</td>
			            <td style="width: 20%;">${list.createDate }</td>
			            <td style="width: 10%;"><form action="${pageContext.request.contextPath}/organization/update/${list.getId()}" method="get">
			            	  	<input type="submit" value="编辑"  class="submitIt simple_buttons"/>
			            </form></td>
			            <td style="width: 10%;"><form action="${pageContext.request.contextPath}/organization/${list.getId() }/delete" method="post">
			            	  	<input type="hidden" name="_method" value="delete" />
			            	  	<input type="submit" value="删除"  class="submitIt simple_buttons"/>
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
	                    
	                    <li><a href="${pageContext.request.contextPath}/organization/organizationView?pn=1">首页</a></li>
	                    
	                    <!--上一页-->
	                    <li>
	                        <c:if test="${pageInfo.hasPreviousPage}">
	                            <a href="${pageContext.request.contextPath}/organization/organizationView?pn=${pageInfo.pageNum-1}" aria-label="Previous">
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
	                            <li><a href="${pageContext.request.contextPath}/organization/organizationView?pn=${page_num}">${page_num}</a></li>
	                        </c:if>
	                    </c:forEach>
	 
	                    <!--下一页-->
	                    <li>
	                        <c:if test="${pageInfo.hasNextPage}">
	                            <a href="${pageContext.request.contextPath}/organization/organizationView?pn=${pageInfo.pageNum+1}"
	                               aria-label="Next">
	                                <span aria-hidden="true">»</span>
	                            </a>
	                        </c:if>
	                    </li>
	                    
	                    <li><a href="${pageContext.request.contextPath}/organization/organizationView?pn=${pageInfo.pages}">尾页</a></li>
	                </ul>
	            </nav>
	        </div>
	</div>
	<%-- <c:if test="${pageInfo.total != 0}">
		<table>
			<tr>
				<td colspan="3"></td>
				<td><form action="${pageContext.request.contextPath}/organization/create" method="get">
	            	  	<input type="submit" value="添加机构"/>
	            </form></td>
			</tr>
			<tr>
				<th>ID</th>
				<th>机构名</th>
				<th>创建时间</th>
				<th colspan="2">operation</th>
			</tr>
			<c:forEach items="${pageInfo.list}" var="list">
				<tr>
		            <td>${list.id }</td>
		            <td>${list.name }</td>
		            <td>${list.createDate }</td>
		            <td><form action="${pageContext.request.contextPath}/organization/update/${list.getId()}" method="get">
		            	  	<input type="submit" value="编辑"/>
		            </form></td>
		            <td><form action="${pageContext.request.contextPath}/organization/${list.getId() }/delete" method="post">
		            	  	<input type="hidden" name="_method" value="delete" />
		            	  	<input type="submit" value="删除"/>
		            </form></td>
		        </tr>
			</c:forEach>
		</table>
	
		<div class="col-md-6">
            当前第 ${pageInfo.pageNum} 页.总共 ${pageInfo.pages} 页.一共 ${pageInfo.total} 条记录
        </div>
 
        <!--点击分页-->
        <div class="col-md-6">
            <nav aria-label="Page navigation">
                <ul class="pagination">
                    
                    <li><a href="${pageContext.request.contextPath}/organization/organizationView?pn=1">首页</a></li>
                    
                    <!--上一页-->
                    <li>
                        <c:if test="${pageInfo.hasPreviousPage}">
                            <a href="${pageContext.request.contextPath}/organization/organizationView?pn=${pageInfo.pageNum-1}" aria-label="Previous">
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
                            <li><a href="${pageContext.request.contextPath}/organization/organizationView?pn=${page_num}">${page_num}</a></li>
                        </c:if>
                    </c:forEach>
 
                    <!--下一页-->
                    <li>
                        <c:if test="${pageInfo.hasNextPage}">
                            <a href="${pageContext.request.contextPath}/organization/organizationView?pn=${pageInfo.pageNum+1}"
                               aria-label="Next">
                                <span aria-hidden="true">»</span>
                            </a>
                        </c:if>
                    </li>
                    
                    <li><a href="${pageContext.request.contextPath}/organization/organizationView?pn=${pageInfo.pages}">尾页</a></li>
                </ul>
            </nav>
        </div>
	</c:if>
	<c:if test="${pageInfo.total == 0}">
		无机构信息<br>
		<form action="${pageContext.request.contextPath}/organization/create" method="get">
        	  	<input type="submit" value="添加机构"/>
        </form>
	</c:if> --%>
</body>
</html>