<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<style>
li{
	text-decoration: none;
	list-style: none;
	display: inline;	
}
.active{
	background-color: #30DDEB;
}
</style>
<title>村落信息</title>
</head>
<body>
	<c:if test="${pageInfo.total != 0}">
		<table>
			<tr>
				<td colspan="3"></td>
				<td><form action="${pageContext.request.contextPath}/village/createVillage" method="get">
	            	  	<input type="submit" value="添加村落"/>
	            </form></td>
	            <td><form action="${pageContext.request.contextPath}/history/villageChangeView" method="get">
	            		<input type="hidden" name="organizationId" value="${organizationId }">
	            	  	<input type="submit" value="查看历史修改详情"/>
	            </form></td>
	            <td><form action="${pageContext.request.contextPath}/history/getDeletedVillage" method="get">
	            		<input type="hidden" name="organizationId" value="${organizationId }">
	            	  	<input type="submit" value="查看已删除村落信息"/>
	            </form></td>
			</tr>
			<%-- <tr>
	    		<td>
	    			<form action="${pageContext.request.contextPath}/preview" method="post" enctype="multipart/form-data">
	    				<input type="file" id="importFile" name="importFile"/>
	    				<input type="submit" name="button" value="预览">
	    			</form>
	    		</td>
			</tr> --%>
			<tr>
				<th>ID</th>
				<th>村落名</th>
				<th>所在地</th>
				<th>描述</th>
				<th>创建机构</th>
				<th>创建时间</th>
				<th colspan="2">operation</th>
			</tr>
			<c:forEach items="${pageInfo.list}" var="list">
				<tr>
		            <td>${list.id }</td>
		            <td>${list.name }</td>
		            <td>${list.location }</td>
		            <td>${list.description }</td>
		            <td>${list.organization }</td>
		            <td>${list.createDate }</td>
		            <td><form action="${pageContext.request.contextPath}/village/updateVillage/${list.getId()}" method="get">
	            	  	<input type="submit" value="编辑"/>
	            	</form></td>
	            	<td><form action="${pageContext.request.contextPath}/village/${list.getId()}/deleteVillage" method="post">
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
                    
                    <li><a href="${pageContext.request.contextPath}/village/villageView?pn=1">首页</a></li>
                    
                    <!--上一页-->
                    <li>
                        <c:if test="${pageInfo.hasPreviousPage}">
                            <a href="${pageContext.request.contextPath}/village/villageView?pn=${pageInfo.pageNum-1}" aria-label="Previous">
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
                            <li><a href="${pageContext.request.contextPath}/village/villageView?pn=${page_num}">${page_num}</a></li>
                        </c:if>
                    </c:forEach>
 
                    <!--下一页-->
                    <li>
                        <c:if test="${pageInfo.hasNextPage}">
                            <a href="${pageContext.request.contextPath}/village/villageView?pn=${pageInfo.pageNum+1}"
                               aria-label="Next">
                                <span aria-hidden="true">»</span>
                            </a>
                        </c:if>
                    </li>
                    
                    <li><a href="${pageContext.request.contextPath}/village/villageView?pn=${pageInfo.pages}">尾页</a></li>
                </ul>
            </nav>
        </div>
	</c:if>
	<c:if test="${pageInfo.total == 0}">
		无村落信息！<br>
		<form action="${pageContext.request.contextPath}/village/createVillage" method="get">
        	  	<input type="submit" value="添加村落"/>
        </form>
	</c:if>
</body>
</html>