<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>
<%
    String path=request.getContextPath();
    String basePath=request.getScheme()+"://"+request.getServerName()+":"
    +request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<base href="<%=basePath%>">
	<title>主页</title>
	<link rel="stylesheet" href="layui/css/layui.css" />
	<script type="text/javascript" src="js/jquery-3.2.1.min.js" ></script>
</head>
<body>
<body class="layui-layout-body">
		<div class="layui-fluid">
			<div class="layui-row layui-col-space15">
				<div class="layui-col-md6">
					<div class="layui-card">
						<div class="layui-card-header">待归档档案</div>
						<div class="layui-card-body">
						    <div class="layui-collapse"  lay-accordion=""  lay-filter="test">
							  	<div class="layui-colla-item" id="taskAnnoucement">
							  	</div>
							</div>
						</div>
					</div>
				</div>
				<div class="layui-col-md6">
					<div class="layui-card">
						<div class="layui-card-header">未完成任务</div>
						<div class="layui-card-body">
							<div class="layui-collapse"  lay-accordion="" lay-filter="test1">
							  	<div class="layui-colla-item" id="schedule">
							  	</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
<script type="text/javascript" charset="utf-8" src="layui/layui.js"></script>
<script type="text/javascript">

$(function(){
	
	$.ajax({
        url: '${pageContext.request.contextPath}/document/showArchiveTempDoc',
        type: "get",
        dataType: 'json',
        success: function (data) {
			var tempDoc = data.tempDoc;
			for(var i = 0; i < tempDoc.length; i++){
				$('#taskAnnoucement').append('<h2 class="layui-colla-title">'+tempDoc[i].title+'</h2>'+'<div class="layui-colla-content layui-show"><p>'+tempDoc[i].description+'</p><a class="btn" href="${pageContext.request.contextPath}/document/getPassedTempDoc/'+tempDoc[i].id+'">查看详情</a>'+'</div>');
			}
			if(tempDoc.length == 0){
				$('#taskAnnoucement').append("暂无待审核档案");
			}
        }
    });
 	$.ajax({
        url: '${pageContext.request.contextPath}/task/unFinishedTask',
        type: "get",
        dataType: 'json',
        success: function (data) {
			var task = data.unfinishedTask;
			for(var i = 0; i < task.length; i++){
				$('#schedule').append('<h2 class="layui-colla-title">'+task[i].title+'</h2>'+'<div class="layui-colla-content layui-show"><p>档案类型：'+task[i].fileType+'</p><p>负责村落：'+task[i].village.name+'</p><p>任务内容：'+task[i].content+'</p><a class="btn" href="${pageContext.request.contextPath}/task/finishTask/'+task[i].id+'">完成</a>&nbsp;&nbsp;'+'<a class="btn" href="${pageContext.request.contextPath}/task/delete/'+task[i].id+'">删除</a>'+'</div>');
			}
			if(task.length == 0){
				$('#schedule').append("暂无未完成任务");
			}
        }
    }); 
 	
});
layui.use(['element', 'layer'], function(){
	  var element = layui.element;
	  var layer = layui.layer;
	  //监听折叠
	  element.init();
	 /*  element.render('collapse','test');
	  element.render('collapse','test1'); */
	});
</script>

</body>
</html>