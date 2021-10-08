<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% String path=request.getContextPath(); String
basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; %>
<!DOCTYPE html>
<html>

<head>
	<base href="<%=basePath%>">
	<meta charset="UTF-8">
	<title>错误页面</title>
	<script src="js/jquery.min.js"></script>
	<link rel="stylesheet" href="./layui/css/layui.css">
	<script src="layui/layui.js"></script>
	<style type="text/css">
		html,
		body {
			padding: 0px;
			margin: 0px;
			background-color: #ffffff;
		}

		.container {
			width: 100%;
			overflow: hidden;
			position: absolute;
			top: 0px;
			left: 0px;
			right: 0px;
			bottom: 0px;
			padding: 0px;
			margin: 0px auto;
			background-color: #ffffff;
		}

		.display {
			text-align: center;
			margin: 15% auto;
			width: 50%;
			min-width: 600px;
			padding: 20px;
			height: 100%;


		}

		.display_btn {
			margin: 20px auto;

		}
	</style>
</head>

<body>
	<div class="container">
		<div class="display">
			<div><i class="layui-icon" style="font-size: 60px; color: #FF5722;">&#xe69c;</i></div>
			<h1>出错了!</h1>
			<div class="display_btn">
				<a href="adminIndex" class="layui-btn layui-btn-lg layui-btn-normal">返回主页</a>
			</div>
			<div class="msg">


			</div>

		</div>
	</div>
</body>

</html>