<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" import="com.zhuozhengsoft.pageoffice.*" %>
<%@ taglib uri="http://java.pageoffice.cn" prefix="po" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<title>acient village</title>
	<!-- The Main CSS File -->
	<link rel="stylesheet" href="../../css/style.css" />
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
	<script type="text/javascript" src="../../ueditor/third-party/jquery-1.10.2.min.js"></script>
	<script type="text/javascript" src="../../ueditor/ueditor.config.js"></script>
	<script type="text/javascript" src="../../ueditor/ueditor.all.js"></script>
	<script type="text/javascript" src="../../ueditor/lang/zh-cn/zh-cn.js"></script>
	<script type="text/javascript" src="../../ueditor/index.js"></script>
	<!-- office插件js begin 必须引入-->
    <script type="text/javascript" src="../../jquery.min.js"></script>
    <script type="text/javascript" src="../../pageoffice.js" id="po_js_main"></script>
    <!-- end -->
</head>
<body>
	<div class="g_6 contents_header">
		<h3 class=" tab_label">档案审核</h3>
		<div><span class="label"></span></div>
	</div>
	<div class="g_6 contents_options">
		<div class="simple_buttons" id="valid">
			<div class="bwIcon i_16_settings">
				<form id="form1" action="${pageContext.request.contextPath}/document/auditTempDoc/${tempDoc.get(0).getId()}" method="post" onsubmit="return false" >
					<input type="hidden" name="_method" value="put" />
                    <input type="hidden" id="html" name="html" value=""/>
                    <input type="hidden" id="remark" name="remark" value=""/>
					<input type="submit" name="state" value="通过" onclick="submitForm()" class="btn"/>
					<input type="submit" name="state" value="不通过" onclick="submitForm()" class="btn"/>
			    </form>
			</div>
		</div>
	</div>
	<div class="g_6 contents_header">
		<h3 class=" tab_label">审核档案</h3>
		<div><span class="label"></span></div>
		<h4 class=" tab_label">文件类型：
		<c:if test="${tempDoc.get(0).getType() eq 'image' }">图片</c:if>
		<c:if test="${tempDoc.get(0).getType() eq 'file' }">文档</c:if>
		<c:if test="${tempDoc.get(0).getType() eq 'video' }">视频</c:if>
		<c:if test="${tempDoc.get(0).getType() eq 'html' }">html</c:if>
		</h4>
	</div>
	<div class="g_12" id="self_info">
		<div class="widget_header">
			<h4 class="widget_header_title wwIcon i_16_forms">档案信息</h4>
		</div>
		<div class="widget_contents noPadding" style="height:100%;">
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
									<form action="${pageContext.request.contextPath}/document/preview" method="get" target="_blank">
										<input type="hidden" name="path" value="${list.getPath() }"/>
										<input type="hidden" name="type" value="${list.getType() }"/>
										<input type="hidden" name="swfName" value="${list.getId() }"/>
							    	  	<input type="submit" value="预览"  class="submitIt simple_buttons"/>
							    	</form>
                                    <button onclick="showEditor()" class="submitIt simple_buttons">编辑</button>
							    	<%--<form action="${pageContext.request.contextPath}/document/editDoc" method="get">
										<input type="hidden" name="id" value="${list.getId() }"/>
										<input type="hidden" name="content" value="${list.getContent() }"/>
							    	</form>--%>
							    	<%-- <a class="submitIt simple_buttons" href="javascript:POBrowser.openWindowModeless('${pageContext.request.contextPath}/pageOffice/pdf/${list.getId() }','width=1200px;height=800px;');">编辑</a> --%>
							    </span>
							</div>
						</div>
						<textarea id="myEditor" style="width: 100%; height: 285px; display: none;">${list.getContent() }</textarea>
					</c:forEach>
				</c:otherwise>
			</c:choose>
			<div class="line_grid">
				<div class="g_3"><span class="label">评价</span></div>
				<div class="g_9">
					<span class="label input"><textarea id="remarkArea" rows="" cols=""></textarea></span>
				</div>
			</div>
		</div>
	</div>
<script type="text/javascript">
	
	var ue = UE.getEditor('myEditor');
	
	function submitForm() {
    	var html = UE.getEditor('myEditor').getContent();
    	var remarkArea = document.getElementById("remarkArea").innerText;
        document.getElementById("html").value = html;
        document.getElementById("remark").value = remarkArea;
		$.ajax({            //几个参数需要注意一下
			type: "POST",	//方法类型
			dataType: "json",//预期服务器返回的数据类型
			url: "${pageContext.request.contextPath}/document/auditTempDoc/${tempDoc.get(0).getId()}" ,//url
			data: $('#form1').serialize(),
			success: function (result) {
				//console.log(result);//打印服务端返回的数据(调试用)
				alert(result.msg);
				setTimeout("location.href='${pageContext.request.contextPath}/document/showAuditTempDoc'", 2000);
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
	 })
</script>
</body>
</html>
