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
    <title>个人中心</title>
    <base href="<%=basePath%>">
	<!-- <link rel="stylesheet" href="css/style.css" />
	<script type="text/javascript" charset="utf-8" src="media/js/jquery.js"></script>
	<script type="text/javascript" charset="utf-8" src="media/js/jquery.dataTables.js"></script>
	<script src="Javascript/jQueryUI/jquery-ui-1.8.21.min.js"></script>
	<script src="Javascript/ClEditor/jquery.cleditor.js"></script>
	<script src="Javascript/ColorBox/jquery.colorbox.js"></script>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />  -->
	<link rel="stylesheet" href="layui/css/layui.css" />
	<link rel="stylesheet" href="layui/css/global.css" />
	<link rel="stylesheet" href="layui/css/admin.css" />
	<link rel="stylesheet" href="layui/css/index.css" />
	<script>
		//iframe高度自适应
		function setIframeHeight(iframe) {
			if (iframe) {
				var iframeWin = iframe.contentWindow || iframe.contentDocument.parentWindow;
				if (iframeWin.document.body) {
					iframe.height = iframeWin.document.documentElement.scrollHeight || iframeWin.document.body.scrollHeight;
				}
			}
		};
	</script>
</head>

<body>
<div class="layui-layout layui-layout-admin">
	<div class="layui-header">
		<!--头部左侧导航-->
		<!-- <ul class="layui-nav layui-layout-left" lay-filter="leftmenu">
			<li class="layui-nav-item">
				<a href="javascript:;" class="hidetab" title="隐藏左侧菜单"><i class="layui-icon layui-icon-shrink-right"></i></a>
			</li>
			<li class="layui-nav-item">
				<a href="./index.html" title="主页"><i class="layui-icon layui-icon-home"></i></a>
			</li>
		</ul> -->
		<ul class="layui-nav layui-layout-right">
   			<li class="layui-nav-item">
   				<a class="javascript:;" href="javascript:;">
	   				<i class="layui-icon layui-icon-username"></i>
	   				<cite>${user.displayName}</cite>
   				</a>
   				<dl class="layui-nav-child">
					<dd><a href="${pageContext.request.contextPath}/member/memberView">基本资料</a></dd>
				</dl>
   			</li>
   			<li class="layui-nav-item ">
   				<a href="logout" title="">
					<span class="label">注销</span>
				</a>
			</li>
   		</ul>
	</div>
		<!-- 侧边菜单 -->
		<div class="layui-side layui-side-menu">
	        <div class="layui-side-scroll">
	        	<div class="logo" style="font:12px;">
	        		<span>古村落档案信息数字化管理系统</span>
	        	</div>
				<!-- lay-shrink收缩全部兄弟菜单子菜单 -->
	        	<ul class="layui-nav layui-nav-tree" lay-shrink="all" id="LAY-system-side-menu" lay-filter="navtree">
	        		<c:if test="${user.getRole().getName() eq '超级管理员' }">
		        		<li class="layui-nav-item">
		        			<a href="javascript:;" lay-href="${pageContext.request.contextPath}/organization/organizationView" title="General Info" target="message">
								<span class="iconfont" style="margin-left:-10%;">&#xe597;</span>
								<cite>机构管理</cite>
							</a>
		        		</li>
		        		<li class="layui-nav-item">
		        			<a href="javascript:;" lay-href="${pageContext.request.contextPath}/account/userView" title="Your Messages" target="message">
								<span class="iconfont" style="margin-left:-10%;">&#xe696;</span>
								<cite>人员管理</cite>
							</a>
		        		</li>
		        		<li class="layui-nav-item">
		        			<a href="javascript:;" lay-href="${pageContext.request.contextPath}/village/villageView" title="Visual Data" target="message">
								<span class="iconfont" style="margin-left:-10%;">&#xe65d;</span>
								<cite>村落管理</cite>
							</a>
		        		</li>
		        		<li class="layui-nav-item">
		        			<a href="javascript:;" lay-href="${pageContext.request.contextPath}/village/cultureaspectView" title="Visual Data" target="message">
								<span class="iconfont" style="margin-left:-10%;">&#xe7ac;</span>
								<cite>文化类别</cite>
							</a>
		        		</li>
		        		<li class="layui-nav-item">
		        			<a href="javascript:;" lay-href="${pageContext.request.contextPath}/document/showVillage" title="Kit elements" target="message">
								<span class="iconfont" style="margin-left:-10%;">&#xe625;</span>
								<cite>档案管理</cite>
							</a>
		        		</li>
		        		<li class="layui-nav-item">
		        			<a href="javascript:;" lay-href="${pageContext.request.contextPath}/document/showDocument" title="Some Rows" target="message">
								<span class="iconfont" style="margin-left:-10%;">&#xe71a;</span>
								<cite>文档查阅</cite>
							</a>
		        		</li>
		        		<li class="layui-nav-item">
		        			<a href="javascript:;" lay-href="${pageContext.request.contextPath}/member/memberView" title="Some Fields" target="message">
								<span class="iconfont" style="margin-left:-10%;">&#xe719;</span>
								<cite>个人信息</cite>
							</a>
		        		</li>
		        	</c:if>
		        	<c:if test="${user.getRole().getName() eq '机构管理员' }">
						<li class="layui-nav-item">
							<a href="javascript:;" lay-href="${pageContext.request.contextPath}/account/userView" title="Your Messages" target="message">
								<span class="iconfont" style="margin-left:-10%;">&#xe696;</span>
								<cite>人员管理</cite>
							</a>
						</li>
						<li class="layui-nav-item">
							<a href="javascript:;">
								<span class="iconfont" style="margin-left:-10%;">&#xe63b;</span>
								<cite>任务管理</cite>
								<span class="layui-nav-more"></span>
							</a>
							<dl class="layui-nav-child">
						      <dd class="">
						        <a href="javascript:;" lay-href="${pageContext.request.contextPath}/task/finishedTaskView" target="message">
						        	<span class="iconfont" style="margin-left:-10%;">&#xe501;</span>
						        	<cite>已完成任务</cite>
						        </a>
						      </dd>
						      <dd class="">
						        <a href="javascript:;" lay-href="${pageContext.request.contextPath}/task/unFinishedTaskView" target="message">
						        	<span class="iconfont" style="margin-left:-10%;">&#xe65b;</span>
						        	<cite>未完成任务</cite>
						        </a>
						      </dd>
						      <dd class="">
						        <a href="javascript:;" lay-href="${pageContext.request.contextPath}/document/archive" target="message">
						        	<span class="iconfont" style="margin-left:-10%;">&#xe59f;</span>
						        	<cite>任务通知</cite>
						        </a>
						      </dd>
						    </dl>
						</li>
						<li class="layui-nav-item">
							<a href="javascript:;" lay-href="${pageContext.request.contextPath}/village/villageView" title="Visual Data" target="message">
								<span class="iconfont" style="margin-left:-10%;">&#xe65d;</span>
								<cite>村落管理</cite>
							</a>
						</li>
						<li class="layui-nav-item">
							<a href="javascript:;" lay-href="${pageContext.request.contextPath}/village/cultureaspectView" title="Visual Data" target="message">
								<span class="iconfont" style="margin-left:-10%;">&#xe7ac;</span>
								<cite>文化类别</cite>
							</a>
						</li>
						<li class="layui-nav-item">
							<a href="javascript:;" lay-href="${pageContext.request.contextPath}/document/showVillage" title="Kit elements" target="message">
								<span class="iconfont" style="margin-left:-10%;">&#xe625;</span>
								<cite>档案管理</cite>
							</a>
						</li>
						<li class="layui-nav-item">
							<a href="javascript:;" lay-href="${pageContext.request.contextPath}/document/showDocument" title="Some Rows" target="message">
								<span class="iconfont" style="margin-left:-10%;">&#xe71a;</span>
								<cite>文档查阅</cite>
							</a>
						</li>
						<li class="layui-nav-item">
							<a href="javascript:;" lay-href="${pageContext.request.contextPath}/member/memberView" title="Some Fields" target="message">
								<span class="iconfont" style="margin-left:-10%;">&#xe719;</span>
								<cite>个人信息</cite>
							</a>
						</li>
					</c:if>
					<c:if test="${user.getRole().getName() eq '学者' }">
						<li class="layui-nav-item">
							<a href="javascript:;" lay-href="${pageContext.request.contextPath}/document/audit" title="Kit elements" target="message">
								<span class="iconfont" style="margin-left:-10%;">&#xe66c;</span>
								<cite>档案审核</cite>
							</a>
						</li>
						<li class="layui-nav-item">
							<a href="javascript:;" lay-href="${pageContext.request.contextPath}/document/showVillage" title="Kit elements" target="message">
								<span class="iconfont" style="margin-left:-10%;">&#xe625;</span>
								<cite>档案管理</cite>
							</a>
						</li>
						<li class="layui-nav-item">
							<a href="javascript:;" lay-href="${pageContext.request.contextPath}/document/showDocument" title="Some Rows" target="message">
								<span class="iconfont" style="margin-left:-10%;">&#xe71a;</span>
								<cite>文档查阅</cite>
							</a>
						</li>
						<li class="layui-nav-item">
							<a href="javascript:;" lay-href="${pageContext.request.contextPath}/member/memberView" title="Some Fields" target="message">
								<span class="iconfont" style="margin-left:-10%;">&#xe719;</span>
								<cite>个人信息</cite>
							</a>
						</li>
					</c:if>
					<c:if test="${user.getRole().getName() eq '录入员' }">
						<li class="layui-nav-item">
							<a href="javascript:;" lay-href="${pageContext.request.contextPath}/document/showMyTempDoc" title="Kit elements" target="message">
								<span class="iconfont" style="margin-left:-10%;">&#xe610;</span>
								<cite>工作管理</cite>
							</a>
						</li>
						<li class="layui-nav-item">
							<a href="javascript:;" lay-href="${pageContext.request.contextPath}/document/showVillage" title="Kit elements" target="message">
								<span class="iconfont" style="margin-left:-10%;">&#xe625;</span>
								<cite>档案管理</cite>
							</a>
						</li>
						<li class="layui-nav-item">
							<a href="javascript:;" lay-href="${pageContext.request.contextPath}/document/showDocument" title="Some Rows" target="message">
								<span class="iconfont" style="margin-left:-10%;">&#xe71a;</span>
								<cite>文档查阅</cite>
							</a>
						</li>
						<li class="layui-nav-item">
							<a href="javascript:;" lay-href="${pageContext.request.contextPath}/member/memberView" title="Some Fields" target="message">
								<span class="iconfont" style="margin-left:-10%;">&#xe719;</span>
								<cite>个人信息</cite>
							</a>
						</li>
					</c:if>
	        	</ul>
	        </div>
	        <!--正文-->
			<div class="layui-body layui-bg-gray" style="top:50px;">
				<!--选项卡-->
				<div class="layui-admin-pagetabs">
					<div class="layui-tab layui-tab-brief"  lay-allowClose="true" lay-filter="pagetabs">
						<ul class="layui-tab-title layui-bg-white">
							<li class="layui-this" lay-id="${pageContext.request.contextPath}/organization/organizationView"><span class="iconfont">&#xe719;</span></li>
						</ul>
						<div class="layui-tab-content">
	                    	<div class="layui-tab-item layui-show">
	                    		<c:if test="${user.getRole().getName() eq '录入员' }">
	                    			<iframe src="${pageContext.request.contextPath}/Login/home" class="layui-admin-iframe" scrolling="yes" frameborder="0" onload="setIframeHeight(this);" style="width:100%;"></iframe>
	                    		</c:if>
	                    		<c:if test="${user.getRole().getName() eq '学者' }">
	                    			<iframe src="${pageContext.request.contextPath}/Login/home1" class="layui-admin-iframe" scrolling="yes" frameborder="0" onload="setIframeHeight(this);" style="width:100%;"></iframe>
	                    		</c:if>
	                    		<c:if test="${user.getRole().getName() eq '机构管理员' }">
	                    			<iframe src="${pageContext.request.contextPath}/Login/home2" class="layui-admin-iframe" scrolling="yes" frameborder="0" onload="setIframeHeight(this);" style="width:100%;"></iframe>
	                    		</c:if>
	                    		<c:if test="${user.getRole().getName() eq '超级管理员' }">
	                    			<iframe src="${pageContext.request.contextPath}/organization/organizationView" class="layui-admin-iframe" scrolling="yes" frameborder="0" onload="setIframeHeight(this);" style="width:100%;"></iframe>
	                    		</c:if>
		                    </div>
	                	</div>
					</div>
				</div>
				
			</div>
	</div>
</div>
<script type="text/javascript" charset="utf-8" src="layui/layui.js"></script>
<script type="text/javascript">

layui.use(['layer','element','jquery'], function(){
  var $ = layui.jquery;
  var layer = layui.layer;
  var element = layui.element;//Tab的切换功能，切换事件监听等，需要依赖element模块

//隐藏tab主页关闭标签
	$(".layui-tab-title li:first-child i:last-child").css("display","none");
//tab点击监控
	element.on('tab(pagetabs)',function(data){
		//tab切换时，左侧菜单对应选中
		var url = $(this).attr("lay-id");
		$(".layui-nav-tree li").each(function(i,e){
			if($(this).find("a").attr("lay-href")==url){
				$(this).attr("class","layui-nav-item layui-this");
			}else{
				$(this).attr("class","layui-nav-item");
			}
		})
		$(".layui-nav-tree li dl dd").each(function(i,e){
			if($(this).find("a").attr("lay-href")==url){
				$(this).attr("class","layui-this");
			}else{
				$(this).attr("class","");
			}
		})
	});
	//左侧垂直菜单监控
	element.on('nav(navtree)',function(elem){
		if($(".layui-side-menu").width()<200){
			$(".layui-side-menu").animate({width:$(".layui-side-menu").width()+144+"px"});
			$(".layui-body").animate({left:$(".layui-body").position().left+144+"px"});
			//$(".layui-footer").animate({left:$(".layui-footer").position().left+144+"px"});
			//$(".layui-layout-left li:first-child").find("a").attr("class","hidetab");
			//$(".layui-layout-left li:first-child").find("i").attr("class","layui-icon layui-icon-shrink-right");
			$(".layui-nav-tree").find("li").each(function(em,ind){
				$(this).find("cite").css("display","");
				$(this).find("dl").css("display","");
			});
		}else{
			if($(this).attr("lay-href")!=undefined){
				var  flag = true;
				//url
				var url = $(this).attr("lay-href");
				//判断选项卡中是否存在已打开的页面，如果有直接切换到打开页面
				$(".layui-tab-title li").each(function(i,e){
					if($(this).attr("lay-id")==url){
						//切换选项卡
						element.tabChange('pagetabs', url);
						flag = false;
					}
				})
				if(flag){
					//新增选项卡
					element.tabAdd('pagetabs', {
					  title: elem[0].getElementsByTagName("cite")[0].innerText
					  ,content: '<iframe src="'+url+'" class="layui-admin-iframe" scrolling="yes" frameborder="0" onload="setIframeHeight(this)" style="width:100%"></iframe>'
					  ,id: url
					});
					//切换选项卡
					element.tabChange('pagetabs', url);
				}
			}
		}
	});
});
</script>
</body>
</html>
