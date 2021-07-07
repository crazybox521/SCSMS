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
<title>成绩</title>
<script src="js/jquery.min.js"></script>
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
		background-color: #F2F2F2;
		width:100%;
	}
	.display{
		position: absolute;
		top:0px;
		right:0px;
		bottom:0px;
		left:0px;
		margin:0px auto;
		padding-left:20%;
		padding-right:20%;
	
	}
	.content{
		position: absolute;
		top:0px;
		right:150px;
		bottom:0px;
		left:150px;
		margin:15px auto;
		padding-left:10%;
		padding-right:10%;
		height:100%;
		background-color: #fff;
		border:1px solid #dddddd;
		box-shadow:0px 0px 7px 6px #e2e2e2;
	
	}
	.layui-form-item .layui-input-block input {
    width: 250px;
   }
   .title{
   		line-height: 100px;
   		font-size: 32px;
   		font-weight:bold
   }
	.error{
		text-align:center;
	    margin:15% auto;
	    width: 50%;
	    min-width:600px;
	    padding: 20px;
	    height:100%;
	}
		
</style>
</head>
<body>
<c:if test="${empty sessionScope.student}">
	<div class="error">
 		<h1>登录失效，点击返回主页重新登陆
 		<a href="index" target="_parent" class="layui-btn layui-btn-primary layui-border-red">返回主页</a></h1>
	</div>
		</c:if>
<c:if test="${not empty sessionScope.student}">
	<div class="container">
		<div class="display">
     	<div class="content">
     	<div class="title">我的成绩</div>
			<table class="layui-hide" id="test" lay-filter="test"></table>
			</div>
		</div>
		</div>
<script type="text/javascript">


		layui.use('table', function(){
		var table = layui.table;
		
		table.render({
			elem: '#test'
			,url: 'markController/stu_list' //数据接口
			,method:'post'
			,toolbar: '#demoTable' //开启头部工具栏，并为其绑定左侧模板
			,title: '成绩表'
			,cols: [[
			{type: 'checkbox', fixed: 'left'}
			,{field:'id', title:'ID', fixed: 'left', unresize: true, sort: true}
			,{field:'grade.gradeName', title:'班级名',templet: function(res){
				return '<em>'+ res.grade.gradeName +'</em>'
			}}
			,{field:'fenshu', title:'成绩'}
			,{field:'state', title:'等级'}
			]]
			,id: 'testReload'
			,page: true
		});

		
		});
		

	</script>
</c:if>

</body>
</html>