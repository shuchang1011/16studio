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
 td{
 	white-space: nowrap;
 }
</style>
<title>文献信息</title>
</head>
<body>
	<form action="${pageContext.request.contextPath}/document/conditionalQuery" method="get">
		<input type="text" name="condition"/>
		<select name="type">
				<option>--请选择--</option>
				<option value="village">村落</option>
				<option value="type">类型</option>
				<option value="title">文件名</option>
		</select>
		<input type="submit" value="submit"/>
	</form>
	${msg }
	<c:if test="${pageInfo.total != 0}">
		<table>
			<tr>
				<td>ID</td>
				<td>标题</td>
				<td>描述</td>
				<td>创建时间</td>
				<td colspan="2">operation</td>
			</tr>
			<c:forEach items="${pageInfo.list}" var="list">
				<tr>
		            <td>${list.id }</td>
		            <td>${list.title }</td>
		            <td>${list.description }</td>
		            <td>${list.createDate }</td>
		            <td><form action="${pageContext.request.contextPath}/document/getOne/${list.getId()}" method="get">
		            	  	<input type="submit" value="查看详情"/>
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
	</c:if>
	<c:if test="${pageInfo.total == 0}">
		无文献档案！<br>
	</c:if>
</body>
</html>