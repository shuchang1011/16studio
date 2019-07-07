<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
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
    <title>登陆</title>
    <base href="<%=basePath%>">
    <style type="text/css">
	*{
	    margin: 0 auto;
	    padding: 0;
	    text-align: center;
	}
	#container{   
        background-size:cover;  
	    background-image: url("images/background.png");
	}
	.title{
	    width: 100%;
	    font-size: xx-large;
	    color: #ff0000;
	    margin-top:150px;
	}
	.dlBox{
	    position: relative;
	    top: 15px;
	    width: 400px;
	    padding-top: 20px;
	    height: 180px;
	    border: 1px solid black;
	}
	h2{
	    width: 100%;
	    color: #E7BBD1;
	}
	.useNum{
	    width: 100%;
	    margin: 10px 0;
	}
	input{
	    border: 1px solid #DDDDDD;
	    height: 28px;
	}
	.dl{
	    width: 80%;
	    height: 50px;
	    margin-top: 10px;
	    background-color:#EF5B00;
	    color: white;
	    size: 150px;
	}
	.box{
	    position: relative;
	    top: 100px;
	    left: -13px;
	    width: 410px;
	    height: 240px;
	    background-color: white;
	}
	#user{
	    position: relative;
	    top:0px;
	    left: 0px;
	    width: 400px;
	    height: 100px;
	    /* background-color: aqua; */
	}
	.item_account{
	    padding-left: 5%;
	    text-align: start;
	    margin-top: 10px;
	    width: 75%;
	    height: 50px;
	}
	</style>
</head>
<body>
    <div id="container">

        <div class="title">古村落档案信息数字化管理平台</div> 

        <div class="box">

            <form id="user" method="post" action="${pageContext.request.contextPath}/Login/logon">
                <label>用户名：</label><input class="item_account" type="text" name="username"><br/>
                <label>密码：</label><input class="item_account" type="password" name="password"><br/><font color="red">${requestScope.message}</font><br>
                <input class="dl" type="submit" value="登录" /> 
            </form>  

        </div>
       
    </div>
</body>
</html>