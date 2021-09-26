<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta charset="UTF-8">
<title>教师页面</title>
<script src="js/jquery.min.js"></script>
<link rel="icon" href="./favicon.ico">
<link rel="stylesheet" href="./layui/css/layui.css">
<script src="layui/layui.js"></script>
<style type="text/css">
	@charset "UTF-8";
	html,body{
		padding: 0px;
		margin: 0px;
		background-color: #fff;
	}
	.container{
		position: absolute;
		top:0px;
		right:0px;
		bottom:0px;
		left:0px;
		min-width:1200px;
		background-color: #fff;
		overflow:hidden;
		width:100%;
	}
	.header{
		position: absolute; 
	    top:0px;
		right:0px;
		bottom:0px;
		left:0px;
	    width:100%;
	    height:70px;
	    background-color:#FAFAFA;
	    box-shadow:0px 4px 10px #999;
	    min-width:1200px;
	}
	.title{
		display:inline-block;
		font-weight:bold;
		font-size:30px;
		color:#E02727;
	    float:left;
		width:30%;
		height:70px;
		line-height:70px;
	
	}
	.title label{
		display:inline-block;
		margin-left:30%;
		height:70px;
	}
	.btn1{
		display:inline-block;
		color:black;
		width:40%;
	}
	.btn2{
		display:inline-block;
		float:right;
		margin-right:10px;
		width:20%;
		
	}
	.bottom{
		position: absolute;
		top:0px;
		left:0px;
		right:0px;
		bottom:0px;
		width: 100%;
		height:100%;
		min-width:1200px;
		margin-top:70px;
	
	}
	iframe{
		position: absolute;
		top:0px;
		left:0px;
		right:0px;
		bottom:0px;
		width:100%;
		height:100%;
		min-width:1200px;
   }
   .layui-nav{
   		height:70px;
   }
   dd.layui-this a{
   		background-color:#FF5722!important;
   
   }
     .layui-nav .layui-nav-child a:hover {
 		 color: #FF5722!important;
	}
	.layui-nav-bar :after,.layui-this:after,.layui-nav-bar{
		background-color: #FF5722!important;
	}
  

</style>
</head>
<body>
      <c:if test="${empty sessionScope.teacher}">
			<a href="index">登陆</a>
		</c:if>
		<c:if test="${not empty sessionScope.teacher}">
		<div class="container">
		<div class="header">
			
			<div class ="title">
			<label>培训班</label>
			</div>
			
			<div class="btn1">
				<ul class="layui-nav layui-bg-gray">
				<li class="layui-nav-item" lay-unselect="" style="color:red;font-weight:bold;font-size:18px;">
					<a href="cla_tea" target="ifr" style="color:red;font-weight:bold;font-size:18px;">班级管理</a>
				</li>
				<li class="layui-nav-item" lay-unselect="" style="color:red;font-weight:bold;font-size:18px;">
					<a href="noti_tea" target="ifr" style="color:red;font-weight:bold;font-size:18px;">通知</a>
				</li>
				<li class="layui-nav-item" lay-unselect="" style="color:red;font-weight:bold;font-size:18px;">
					<a href="mark_tea" target="ifr" style="color:red;font-weight:bold;font-size:18px;">成绩管理</a>
				</li>
					
				</ul>
				
				
			</div>
			<div class="btn2">
			 <ul class="layui-nav layui-bg-gray">
		        <li class="layui-nav-item" lay-unselect="">
		          <a href="javascript:;" style="color:red;font-weight:bold;font-size:18px;">个人中心</a>
		          <dl class="layui-nav-child">
		            <dd><a href="info_tea" target="ifr">修改信息</a></dd>
		          </dl>
		        </li>
		        <li class="layui-nav-item" lay-unselect="">
		          <a href="javascript:;" style="color:red;font-weight:bold;font-size:18px;" >${sessionScope.teacher.userName}</a>
		          <dl class="layui-nav-child">
		            <dd><a href="pw_tea" target="ifr">修改密码</a></dd>
		            <dd><a href="loginout" >退出登录</a></dd>
		          </dl>
		        </li>
		      </ul>
			</div>
			

			</div>
			<div class="bottom">
				<iframe name="ifr" src="cla_tea" frameBorder="0"></iframe>
			</div>
		</div>
		<script type="text/javascript">
			layer.msg('hello');
		/*	$(function () {
	            //禁用“确认重新提交表单”
	            window.history.replaceState(null, null, window.location.href);
	        })*/
		</script>
		</c:if>
</body>
</html>