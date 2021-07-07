<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML>
<html style="padding: 0px;margin: 0px;background-color: #fff;">
<head>
<base href="<%=basePath%>">
<meta charset="UTF-8">
<title>欢迎界面</title>		
<script src="js/jquery.min.js"></script>
<link rel="stylesheet" href="./layui/css/layui.css">
<script src="layui/layui.js"></script>
<style type="text/css">
	@charset "UTF-8";
	html,body{
		padding: 0px;
		margin: 0px;
		background-color: #ffffff;
	}
	.container{
		position: absolute;
		top:0px;
		right:0px;
		bottom:0px;
		left:0px;
		min-width:1200px;
		background-color: #fff;
		width:100%;
	}
	.display {
		width:100%;
		margin-bottom:100px;
	
	}
	.display div img{
		width:100%;
	}

		
</style>
</head>
<body>
			<div class="container">
			<div class="display">
		        	<div><img src="./image/index_1.jpg"  alt="教学模式" /></div>
		        	
		        </div>
			</div>
	<script type="text/javascript">
	    layui.use('layer', function(){
		   var layer = layui.layer;
		   layer.msg("欢迎进入校外培训班！")
		   
	
		 });

    </script>
</body>
</html>