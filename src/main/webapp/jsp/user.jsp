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
		<h3 class=" tab_label">人员管理</h3>
		<div><span class="label">Personnel Management</span></div>
	</div>
	<div class="g_6 contents_options">
		<div class="simple_buttons">
			<div class="bwIcon i_16_settings">
				<form action="${pageContext.request.contextPath}/account/create" method="get">
	            	<input type="submit" value="添加用户" class="btn"/>
	            </form>
	        </div>
		</div>
	</div>
	<div class="g_12 separator"><span></span></div>
		<div class="g_12">
			<div class="widget_header">
				<h4 class="widget_header_title wwIcon i_16_tooltip">用户列表</h4>
			</div>
			<div class="widget_contents noPadding">
				<table class="datatable tables" id="DataTables_Table_0"  style="overflow:scroll;">
					<thead>
					<tr>
						<th style="width: 5%;">ID</th>
						<th style="width: 10%;">登陆名</th>
						<th style="width: 10%;">展示名</th>
						<th style="width: 20%;">角色</th>
						<th style="width: 5%;">性别</th>
						<th style="width: 10%;">年龄</th>
						<th style="width: 10%;">手机</th>
						<th style="width: 10%;">邮箱</th>
						<th style="width: 10%;">所在机构</th>
						<th style="width: 20%;" colspan="2">operation</th>
					</tr>
					<%
						int i = 1;
					%>
					<c:forEach items="${pageInfo.list}" var="list">
						<tr>
				            <td style="width: 5%;"><%=i++ %></td>
				            <td style="width: 10%;">${list.accountName }</td>
				            <td style="width: 10%;">${list.displayName }</td>
				            <td style="width: 20%;">${list.role.name }</td>
				            <td style="width: 5%;">${list.member.gender }</td>
				            <td style="width: 10%;">${list.member.age }</td>
				            <td style="width: 10%;">${list.member.mobile }</td>
				            <td style="width: 10%;">${list.member.email }</td>
				            <td style="width: 10%;">${list.organization }</td>
				            <td style="width: 10%;"><form action="${pageContext.request.contextPath}/account/update/${list.getId()}" method="get">
				            	  	<input type="submit" value="编辑" class="submitIt simple_buttons"/>
				            </form></td>
				            <td style="width: 10%;"><form id="form1" action="${pageContext.request.contextPath}/account/delete" method="post" onsubmit="return false">
								<input type="hidden" name="_method" value="delete" />
								<input type="hidden" name="id" value="${list.getId() }" />
								<input type="submit" value="删除" onclick="submitForm()" class="submitIt simple_buttons"/>
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
	                    
	                    <li><a href="${pageContext.request.contextPath}/account/userView?pn=1">首页</a></li>
	                    
	                    <!--上一页-->
	                    <li>
	                        <c:if test="${pageInfo.hasPreviousPage}">
	                            <a href="${pageContext.request.contextPath}/account/userView?pn=${pageInfo.pageNum-1}" aria-label="Previous">
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
	                            <li><a href="${pageContext.request.contextPath}/account/userView?pn=${page_num}">${page_num}</a></li>
	                        </c:if>
	                    </c:forEach>
	 
	                    <!--下一页-->
	                    <li>
	                        <c:if test="${pageInfo.hasNextPage}">
	                            <a href="${pageContext.request.contextPath}/account/userView?pn=${pageInfo.pageNum+1}"
	                               aria-label="Next">
	                                <span aria-hidden="true">»</span>
	                            </a>
	                        </c:if>
	                    </li>
	                    
	                    <li><a href="${pageContext.request.contextPath}/account/userView?pn=${pageInfo.pages}">尾页</a></li>
	                </ul>
	            </nav>
	        </div>
		</div>
		<div class="g_12 separator"><span></span></div>
<script>
	function submitForm() {
		var deleteButton = window.confirm("确定要删除吗？")
		if(deleteButton){
			$.ajax({            //几个参数需要注意一下
				type: "POST",//方法类型
				dataType: "json",//预期服务器返回的数据类型
				url: "${pageContext.request.contextPath}/account/delete" ,//url
				data: $('#form1').serialize(),
				success: function (result) {
					//console.log(result);//打印服务端返回的数据(调试用)
					alert(result.msg);
					setTimeout("location.href='${pageContext.request.contextPath}/account/userView'", 2000);
				},
				error : function() {
					alert("异常！");
				}
			});
		}
	}
</script>
</body>
</html>