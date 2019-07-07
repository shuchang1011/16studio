<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/upload/";
	String swfPath = request.getAttribute("htmlpath").toString();
	String Path=request.getContextPath();
    String BasePath=request.getScheme()+"://"+request.getServerName()+":"
    +request.getServerPort()+Path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<base href="<%=BasePath%>">
<script type="text/javascript" src="js/flexpaper_flash.js"></script>
<script type="text/javascript" src="js/flexpaper_flash_debug.js"></script>
<script type="text/javascript" src="js/jquery.js"></script>
</head>
<body>
<a href="javascript:history.back(-1)">返回上一页</a>
	<div style="position: absolute; left: 50px; top: 50px;">
		<a id="viewerPlaceHolder"
			style="width: 820px; height: 650px; display: block"></a>

		<script type="text/javascript">   
                    var fp = new FlexPaperViewer(     
                             'FlexPaperViewer',  
                             'viewerPlaceHolder', 
                         { config : {  
                            SwfFile : "<%=basePath%><%=swfPath%>",
							Scale : 0.6,
							ZoomTransition : 'easeOut',
							ZoomTime : 0.5,
							ZoomInterval : 0.2,
							FitPageOnLoad : true,
							FitWidthOnLoad : false,
							FullScreenAsMaxWindow : false,
							ProgressiveLoading : false,
							MinZoomSize : 0.2,
							MaxZoomSize : 5,
							SearchMatchAll : false,
							InitViewMode : 'SinglePage',
							RenderingOrder : 'flash',
							ViewModeToolsVisible : true,
							ZoomToolsVisible : true,
							NavToolsVisible : true,
							CursorToolsVisible : true,
							SearchToolsVisible : true,

							localeChain : 'en_US'
						}
					});
		</script>
	</div>
</body>
</html>