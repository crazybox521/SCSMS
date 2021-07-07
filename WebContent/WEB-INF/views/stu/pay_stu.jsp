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
<title>订单</title>
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
	.error{
		text-align:center;
	    margin:15% auto;
	    width: 50%;
	    min-width:600px;
	    padding: 20px;
	    height:100%;
	}
	
	 .title{
   		line-height: 100px;
   		font-size: 32px;
   		font-weight:bold
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
     	<div class="title">我的订单</div>
     	<table id="demo" lay-filter="test"></table>
     	</div>

		</div>

</div>
<script type="text/javascript">
layui.use('table', function(){
	  var table = layui.table;
	  
	  //第一个实例
	  table.render({
	    elem: '#demo'
	    ,url: 'payController/queryPageByStudent' //数据接口
	    ,method:'post'
	    ,page: true //开启分页
	    ,title: '用户数据表'
	    ,cols: [[ //表头
	      {field: 'id', title: 'ID', width:80, sort: true, fixed: 'left'}
	      ,{field: 'student.name', title: '姓名',  templet: function(res){
				return '<em>'+ res.student.name +'</em>'
			}}
	      ,{field: 'grade.gradeName', title: '班级名',templet: function(res){
				return '<em>'+ res.grade.gradeName +'</em>'
			}}
	      ,{field: 'number', title: '金额'} 
	      ,{field: 'payid', title: '商品订单号'}
	      ,{field: 'alipay', title: '支付宝订单号'}
	      ,{field: 'state', title: '状态'}
	    ]]
	  });
	  
	});
</script>
</c:if>

</body>
</html>