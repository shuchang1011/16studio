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
	<link rel="stylesheet" href="../../css/style.css" />
	<link rel="stylesheet" href="../../css/ystep.css" />
	<script type="text/javascript" charset="utf-8" src="../../media/js/jquery.js"></script>
	<script type="text/javascript" charset="utf-8" src="../../media/js/jquery.dataTables.js"></script>
	<!-- jQuryUI -->
	<script src="../../Javascript/jQueryUI/jquery-ui-1.8.21.min.js"></script>
	<!-- ClEditor -->
	<script src="../../Javascript/ClEditor/jquery.cleditor.js"></script>
	<!-- Color Box -->
	<script src="../../Javascript/ColorBox/jquery.colorbox.js"></script>
	
	<!-- Kanrisha Script -->
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<!-- 进度条 -->
	<script type="text/javascript" src="../../js/jquery.min.js"></script>
	<script type="text/javascript" src="../../js/setStep.js"></script>
	<!-- office插件js begin 必须引入-->
    <script type="text/javascript" src="../../jquery.min.js"></script>
    <script type="text/javascript" src="../../pageoffice.js" id="po_js_main"></script>
    <script type="text/javascript" src="../../ueditor/third-party/jquery-1.10.2.min.js"></script>
	<script type="text/javascript" src="../../ueditor/ueditor.config.js"></script>
	<script type="text/javascript" src="../../ueditor/ueditor.all.js"></script>
	<script type="text/javascript" src="../../ueditor/lang/zh-cn/zh-cn.js"></script>
	<script type="text/javascript" src="../../ueditor/index.js"></script>
</head>
<body>
	<div class="g_6 contents_header">
		<h3 class=" tab_label">档案信息</h3>
		<div><span class="label"></span></div>
	</div>
	<div class="g_6 contents_options">
		<div class="simple_buttons" id="valid">
            <div class="bwIcon i_16_settings">
				<form id="form1" action="${pageContext.request.contextPath}/document/submitTempDoc/${tempDoc.get(0).getId()}" method="post" onsubmit="return false">
		    	  	<input type="hidden" name="_method" value="put" />
					<input type="hidden" name="html" value="" />
		    	  	<input type="submit" value="提交" class="btn"/>
		   		</form>
            </div>
		</div>
	</div>
	<!-- 菜单及分页容器-->
	<div class="stepCont stepCont1">
		<!-- 菜单导航显示-->
		<div class='ystep-container ystep-lg ystep-blue'></div>
	</div>
	<div class="g_12" id="self_info">
		<div class="widget_header">
			<h4 class="widget_header_title wwIcon i_16_forms">档案信息</h4>
		</div>
		<div class="widget_contents noPadding">
			<div class="line_grid">
				<div class="g_3"><span class="label">标题</span></div>
				<div class="g_9">
					<span class="label input">${tempDoc.get(0).getTitle() }</span>
				</div>
			</div>
			<div class="line_grid">
				<div class="g_3"><span class="label">描述</span></div>
				<div class="g_9">
					<span class="label input">${tempDoc.get(0).getDescription() }</span>
				</div>
			</div>
			<c:choose>
				<c:when test="${tempDoc.get(0).getType() eq 'html' }">
					<div class="line_grid">
						<div>
							<textarea id="myEditor" style="width: 100%; height: 285px;">${tempDoc.get(0).getContent()}</textarea>
						</div>
					</div>
				</c:when>
				<c:when test="${tempDoc.get(0).getType() eq 'image' }">
					<div class="line_grid">
						<div>
							<textarea id="myEditor" style="width: 100%; height: 285px;">
								<c:forEach items='${tempDoc}' var='list'>
									<p><img src="/ancientVillage/upload/viewImagesToPage?imagePath=${list.getPath() }" title="" alt="" width="374" height="431"/></p>
								</c:forEach>
							</textarea>
						</div>
					</div>
				</c:when>
				<c:when test="${tempDoc.get(0).getType() eq 'video' }">
					<div class="line_grid">
						<div>
							<textarea id="myEditor" style="width: 100%; height: 285px;">
								<c:forEach items='${tempDoc}' var='list'>
									<p><video class="edui-upload-video video-js vjs-default-skin video-js" controls="" preload="none" src="/shiroDemo/upload/fileDownLoad?url=${list.getPath() }" width="420" height="280"></video></p>
								</c:forEach>
							</textarea>
						</div>
					</div>
				</c:when>
				<c:otherwise>
					<c:forEach items='${tempDoc}' var='list'>
						<div class="line_grid">
							<div class="g_3"><span class="label">文件</span></div>
							<div class="g_9">
								<span class="label input">${tempDoc.get(0).getTitle() }
									<form action="${pageContext.request.contextPath}/document/preview" method="get">
										<input type="hidden" name="path" value="${list.getPath() }"/>
										<input type="hidden" name="type" value="${list.getType() }"/>
										<input type="hidden" name="swfName" value="${list.getId() }"/>
							    	  	<input type="submit" value="预览"  class="submitIt simple_buttons"/>
							    	</form>
									<button onclick="showEditor()" class="submitIt simple_buttons">编辑</button>
							    	<%--<a class="submitIt simple_buttons" href="javascript:POBrowser.openWindowModeless('${pageContext.request.contextPath}/pageOffice/pdf/${list.getId() }','width=1200px;height=800px;');">编辑</a>--%>
							    </span>
							</div>
						</div>
						<textarea id="myEditor" style="width: 100%; height: 285px; display: none;">${list.getContent() }</textarea>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
<script>
	var step = 1;
	var state = "${tempDoc.get(0).getState()}";
	var init = function(){

		if((state == "")||(state == "审核中")||(state == "审核未通过")||(state == "归档未通过")){
			step = 1;
		}else if(state == "归档中"){
			step = 2;
		}else{
			step = 3;
		}
	};
	init();
	var step1=new SetStep({
		content:'.stepCont1',
		showBtn:false,
		clickAble:false,
		curStep:step
	});
	var ue = UE.getEditor('myEditor');

	function submitForm() {
		var html = UE.getEditor('myEditor').getContent();
		document.getElementById("html").value = html;
		$.ajax({            //几个参数需要注意一下
			type: "POST",	//方法类型
			dataType: "json",//预期服务器返回的数据类型
			url: "${pageContext.request.contextPath}/document/submitTempDoc/${tempDoc.get(0).getId()}" ,//url
			data: $('#form1').serialize(),
			success: function (result) {
				//console.log(result);//打印服务端返回的数据(调试用)
				alert(result.msg);
				setTimeout("location.href='${pageContext.request.contextPath}/document/showMyTempDoc'", 2000);
			},
			error : function() {
				alert("异常！");
			}
		});
	}

	function showEditor() {
		jQuery("#myEditor").show();
	}

	var editor = new UE.ui.Editor();
	editor.render('editor');

	editor.addListener( 'ready', function(edt) {
		var content_old = $('#content').html();
		if(content_old!=''){
			editor.execCommand('insertHtml', content_old);
		}
	 });
</script>
   
</body>
</html>