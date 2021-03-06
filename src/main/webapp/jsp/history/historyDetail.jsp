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
	<title>村落信息e</title>
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
</head>
<body>
	<div class="g_6 contents_header">
		<h3 class=" tab_label">修改详情</h3>
		<div><span class="label"></span></div>
	</div>
	<div class="g_6 contents_options">
		<div class="simple_buttons">
			<div class="bwIcon i_16_settings">
				<form id="form1" action="${pageContext.request.contextPath}/history/restoreHistory" method="post" onsubmit="return false">
					<input type="hidden" name="operationId" value="${operation.getId() }">
            	  	<input type="submit" value="还原" class="btn" onclick="submitForm()"/>
            </form>
	        </div>
		</div>
	</div>
	<div class="g_12 separator"><span></span></div>
	<div class="g_12" id="history_info">
		<div class="g_6">
			历史记录：
			<div class="widget_header">
				<h4 class="widget_header_title wwIcon i_16_forms">村落名：${villageHistory.name }</h4>
			</div>
			<div class="widget_contents noPadding">
				<div class="line_grid">
					<div class="g_12">
						<span class="label input">所在地：${villageHistory.location }</span>
					</div>
				</div>
				<div class="line_grid">
					<div class="g_12">
						<span class="label input">描述：${villageHistory.description }</span>
					</div>
				</div>
				<div class="line_grid">
					<div class="g_12">
						<span class="label input">创建机构：${villageHistory.organization }</span>
					</div>
				</div>
				<div class="line_grid">
					<div class="g_12">
						<span class="label input">修改时间：${villageHistory.changeDate }</span>
					</div>
				</div>
			</div>
		</div>

		<div class="g_6">
			当前村落信息：
			<div class="widget_header">
				<h4 class="widget_header_title wwIcon i_16_forms">村落名：${village.name }</h4>
			</div>
			<div class="widget_contents noPadding">
				<div class="line_grid">
					<div class="g_12">
						<span class="label input">所在地：${village.location }</span>
					</div>
				</div>
				<div class="line_grid">
					<div class="g_12">
						<span class="label input">描述：${village.description }</span>
					</div>
				</div>
				<div class="line_grid">
					<div class="g_12">
						<span class="label input">创建机构：${village.organization }</span>
					</div>
				</div>
				<div class="line_grid">
					<div class="g_12">
						<span class="label input">创建时间：${village.createDate }</span>
					</div>
				</div>
			</div>
		</div>

	</div>
<script>
	function submitForm() {
		$.ajax({            //几个参数需要注意一下
			type: "POST",//方法类型
			dataType: "json",//预期服务器返回的数据类型
			url: "${pageContext.request.contextPath}/history/recoveryHistory" ,//url
			data: $('#form1').serialize(),
			success: function (result) {
				//console.log(result);//打印服务端返回的数据(调试用)
				alert(result.msg);
				setTimeout("location.href='${pageContext.request.contextPath}/village/villageView'", 2000);
			},
			error : function() {
				alert("异常！");
			}
		});
	}
</script>
</body>
</html>