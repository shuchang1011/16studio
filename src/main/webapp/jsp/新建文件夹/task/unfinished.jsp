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
<title>未完成任务</title>
</head>
<body>
	<c:if test="${pageInfo.total != 0}">
		<table>
			<tr>
				<td colspan="3"></td>
				<td><form action="${pageContext.request.contextPath}/task/create" method="get">
	            	  	<input type="submit" value="创建任务"/>
	            </form></td>
			</tr>
			<tr>
				<th>标题</th>
				<th>任务内容</th>
				<th>文件类型</th>
				<th>负责村落</th>
				<th>任务负责人</th>
				<th>创建时间</th>
				<th>完成时间</th>
				<th>operation</th>
			</tr>
			<c:forEach items="${pageInfo.list}" var="list">
				<tr>
		            <td>${list.title }</td>
		            <td>${list.content }</td>
		            <td>${list.fileType }</td>
		            <td>${list.village.getName() }</td>
		            <td>
		            	${list.member.get(0).getAccount().getDisplayName()}
		            	<c:forEach items="${list.member}" var="m" begin="1">
		            		,${m.getAccount().getDisplayName()}
		            	</c:forEach>
		            </td>
		            <td>${list.createDate }</td>
		            <td>${list.finishedDate }</td>
		            
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
                    
                    <li><a href="${pageContext.request.contextPath}/task/unFinishedTaskView?pn=1">首页</a></li>
                    
                    <!--上一页-->
                    <li>
                        <c:if test="${pageInfo.hasPreviousPage}">
                            <a href="${pageContext.request.contextPath}/task/unFinishedTaskView?pn=${pageInfo.pageNum-1}" aria-label="Previous">
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
                            <li><a href="${pageContext.request.contextPath}/task/unFinishedTaskView?pn=${page_num}">${page_num}</a></li>
                        </c:if>
                    </c:forEach>
 
                    <!--下一页-->
                    <li>
                        <c:if test="${pageInfo.hasNextPage}">
                            <a href="${pageContext.request.contextPath}/task/unFinishedTaskView?pn=${pageInfo.pageNum+1}"
                               aria-label="Next">
                                <span aria-hidden="true">»</span>
                            </a>
                        </c:if>
                    </li>
                    
                    <li><a href="${pageContext.request.contextPath}/task/unFinishedTaskView?pn=${pageInfo.pages}">尾页</a></li>
                </ul>
            </nav>
        </div>
	</c:if>
	<c:if test="${pageInfo.total == 0}">
		无任务信息！<br>
		<form action="${pageContext.request.contextPath}/task/create" method="get">
        	  	<input type="submit" value="创建任务"/>
        </form>
	</c:if>
</body>
</html>