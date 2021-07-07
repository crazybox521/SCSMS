<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">
<meta charset="UTF-8">
<title>数据显示</title>
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
		background-color: #ffffff;
		overflow: hidden;
		width:100%;
	}
	.content{
		margin:5px auto;
		width:100%;
		min-width:800px;
	}
	.layui-panel{
		background-color:#009688;
		color:#fff;
		font-size:18px;
	}

			
</style>
</head>
<body>
 <c:if test="${empty sessionScope.admin}">
 	<div class="error">
 		<h1>登录失效，点击返回主页重新登陆
 		<a href="index" target="_parent" class="layui-btn layui-btn-primary layui-border-red">返回主页</a></h1>
	</div>
	</c:if>
	<c:if test="${not empty sessionScope.admin}">
	<div class="container"> 
		<div class="content">
		<div class="layui-row layui-col-space30">
  		<div class="layui-col-md4">
    		<div class="layui-panel">
    		<div style="padding: 30px;" >总用户数</div>
			<div style="padding: 30px;" id="12"></div>
    	</div>   
 		</div>
		<div class="layui-col-md4">
		    <div class="layui-panel">
		      <div style="padding: 30px;" >总学员数</div>
		      <div style="padding: 30px;" id="13"></div>
		    </div>   
		  </div>
		  <div class="layui-col-md4">
		    <div class="layui-panel">
		     <div style="padding: 30px;" >总教师数</div>
		      <div style="padding: 30px;" id="14"></div>
		    </div>   
		  </div>
		  <div class="layui-col-md4">
		    <div class="layui-panel">
		     <div style="padding: 30px;" >总教室数</div>
		      <div style="padding: 30px;" id="15"></div>
		    </div>   
		  </div>
		  <div class="layui-col-md4">
		    <div class="layui-panel">
		     <div style="padding: 30px;" >总课程数</div>
		      <div style="padding: 30px;" id="16"></div>
		    </div>   
		  </div>
		  <div class="layui-col-md4">
		    <div class="layui-panel">
		     <div style="padding: 30px;" >总班级数</div>
		      <div style="padding: 30px;" id="17"></div>
		    </div>   
		  </div>
		 
		</div>    
		</div>
		
	</div>
   <script type="text/javascript">
  $(function(){
	  getData();
  });
  
  function getData(){
	  $.ajax({
		  url:"userController/queryCountAll",
			type:"post",
			data:{},
			dataType:"json",
			success:function(data){ 
					$("#12").text(data.uCount);
					$("#13").text(data.sCount);
					$("#14").text(data.tCount);
					$("#15").text(data.rCount);
					$("#16").text(data.lCount);
					$("#17").text(data.gCount);
			}
	  });
	  
  }

   </script>
	</c:if>
</body>
</html>