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
<title>Insert title here</title>
</head>
<body>
	<table>
		<tr>
			<td colspan="4"></td>
			<td><form action="${pageContext.request.contextPath}/account/create" method="get">
            	  	<input type="submit" value="添加用户"/>
            </form></td>
		</tr>
		<tr>
			<th>ID</th>
			<th>登陆名</th>
			<th>展示名</th>
			<th>角色</th>
			<th>性别</th>
			<th>年龄</th>
			<th>手机</th>
			<th>邮箱</th>
			<th>所在机构</th>
			<th colspan="2">operation</th>
		</tr>
		<%
			int i = 1;
		%>
		<c:forEach items="${pageInfo.list}" var="list">
			<tr>
	            <td><%=i++ %></td>
	            <td>${list.accountName }</td>
	            <td>${list.displayName }</td>
	            <td>${list.role.name }</td>
	            <td>${list.member.gender }</td>
	            <td>${list.member.age }</td>
	            <td>${list.member.mobile }</td>
	            <td>${list.member.email }</td>
	            <td>${list.organization }</td>
	            <td><form action="${pageContext.request.contextPath}/account/update/${list.getId()}" method="get">
	            	  	<input type="submit" value="编辑"/>
	            </form></td>
	            <td><form action="${pageContext.request.contextPath}/account/${list.getId() }/delete" method="post">
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
                    
                    <li><a href="${pageContext.request.contextPath}/userView?pn=1">首页</a></li>
                    
                    <!--上一页-->
                    <li>
                        <c:if test="${pageInfo.hasPreviousPage}">
                            <a href="${pageContext.request.contextPath}/account/userView/${list.getId()}?pn=${pageInfo.pageNum-1}" aria-label="Previous">
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
                            <li><a href="${pageContext.request.contextPath}/account/userView/${list.getId()}?pn=${page_num}">${page_num}</a></li>
                        </c:if>
                    </c:forEach>
 
                    <!--下一页-->
                    <li>
                        <c:if test="${pageInfo.hasNextPage}">
                            <a href="${pageContext.request.contextPath}/account/userView/${list.getId()}?pn=${pageInfo.pageNum+1}"
                               aria-label="Next">
                                <span aria-hidden="true">»</span>
                            </a>
                        </c:if>
                    </li>
                    
                    <li><a href="${pageContext.request.contextPath}/account/userView/${list.getId()}?pn=${pageInfo.pages}">尾页</a></li>
                </ul>
            </nav>
        </div>
</body>
</html>