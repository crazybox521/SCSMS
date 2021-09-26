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
<title>系统管理员页面</title>
<script src="js/jquery.min.js"></script>
<link rel="icon" href="./favicon.ico">
<link rel="stylesheet" href="./layui/css/layui.css">
<script src="layui/layui.js"></script>
<style type="text/css">
@charset "UTF-8";
html,body{
	padding: 0px;
	margin: 0px;
	background-color:#ffffff;
}

iframe{
	position: absolute;
	top:0px;
	left:0px;
	right:0px;
	bottom:0px;
	width: 100%;
	height: 100%;
	-webkit-overflow-scrolling: touch;
  	overflow-y: scroll ;
}
#edit_pw{
	display:none;

}
</style>
</head>
<body>
      <c:if test="${empty sessionScope.admin}">
			<a href="index">登陆</a>
		</c:if>
		<c:if test="${not empty sessionScope.admin}">
		<div class="layui-layout layui-layout-admin">
		  <div class="layui-header">
		    <div class="layui-logo">培训班后台</div>
		    <!-- 头部区域（可配合layui 已有的水平导航） -->
		    <ul class="layui-nav layui-layout-left">
		      <li class="layui-nav-item"><a href="data_ad" target="ifr"><i class="layui-icon">&#xe665;</i>&nbsp;数据统计</a></li>
		    </ul>
		    <ul class="layui-nav layui-layout-right">
		      <li class="layui-nav-item">
		        <a href="javascript:;">
		          
		          ${sessionScope.admin.userName}
		        </a>
		        <dl class="layui-nav-child">
		          <dd><a href="javascript:;" onclick="ShowPwtext();">修改密码</a></dd>
		        </dl>
		      </li>
		      <li class="layui-nav-item"><a href="loginout">退出登录</a></li>
		    </ul>
		  </div>
		  
		  <div class="layui-side layui-bg-black">
		    <div class="layui-side-scroll">
		      <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
		      <ul class="layui-nav layui-nav-tree"  lay-filter="test">
		        <li class="layui-nav-item">
		        <a class="" href="javascript:;"><i class="layui-icon">&#xe683;</i>&nbsp;账号管理</a>
		          <dl class="layui-nav-child">
		            <dd><a href="user_ad" target="ifr">&nbsp;&nbsp;账号管理</a></dd>
		          </dl>
		        </li>
		        
		         <li class="layui-nav-item">
		         <a class="" href="javascript:;"><i class="layui-icon">&#xe66f;</i>&nbsp;学员管理</a>
		          <dl class="layui-nav-child">
		            <dd><a href="stu_ad" target="ifr">&nbsp;&nbsp;学员信息管理</a></dd>
		          </dl>
		        </li>
		        <li class="layui-nav-item">
		          <a class="" href="javascript:;"><i class="layui-icon">&#xe612;</i>&nbsp;教师管理</a>
		          <dl class="layui-nav-child">
		            <dd><a href="tea_ad" target="ifr">&nbsp;&nbsp;教师信息管理</a></dd>
		          </dl>
		        </li>
		        <li class="layui-nav-item">
		          <a href="javascript:;"><i class="layui-icon">&#xe614;</i>&nbsp;培训基础管理</a>
		          <dl class="layui-nav-child">
		            <dd><a href="lesson_ad" target="ifr">&nbsp;&nbsp;课程管理</a></dd>
		            <dd><a href="room_ad" target="ifr">&nbsp;&nbsp;教室管理</a></dd>
		        </dl>
		        </li>
		        <li class="layui-nav-item"><a href="cla_ad" target="ifr"><i class="layui-icon">&#xe63c;</i>&nbsp;班级管理</a></li>
		        <li class="layui-nav-item"><a href="pay_ad" target="ifr"><i class="layui-icon">&#xe657;</i>&nbsp;缴费管理</a></li>
		      </ul>
		    </div>
		  </div>
		  
		  <div class="layui-body">
		    <!-- 内容主体区域 -->
		    <div style="padding: 15px;"><iframe name="ifr" src="data_ad" frameBorder="0"></iframe></div>
		  </div>
		  <div id="edit_pw">
		  <form class="layui-form layui-form-pane" action="" lay-filter="editpw">
	      	<div class="layui-form-item">
	          <label class="layui-form-label">用户名</label>
	          <div class="layui-input-inline">
	            <input type="text" name="userName" readonly="readonly" value="${ sessionScope.admin.userName}" class="layui-input">
	          </div>
	          <div class="layui-form-mid layui-word-aux">注:用户名无法修改</div>
	        </div>
	        <div class="layui-form-item">
	          <label class="layui-form-label">原密码</label>
	          <div class="layui-input-block">
	            <input type="password" name="password1" required lay-verify="required" placeholder="请输入原密码" autocomplete="off" class="layui-input">
	          </div>
	        </div>
	        <div class="layui-form-item">
	          <label class="layui-form-label">修改密码</label>
	          <div class="layui-input-block">
	            <input type="password" name="password2" required lay-verify="required|pass" placeholder="请输入修改的密码" autocomplete="off" class="layui-input">
	          </div>
	        </div>
	         <div class="layui-form-item">
	          <label class="layui-form-label">确认密码</label>
	          <div class="layui-input-block">
	            <input type="password" name="password3" required lay-verify="required|pass|pwcheck" placeholder="请再次输入修改的密码" autocomplete="off" class="layui-input">
	          </div>
	        </div>
	        <hr class="layui-border-red">
	        <div class="layui-form-item" style="text-align: center">
	        
	          <button class="layui-btn layui-btn-danger" lay-submit lay-filter="editpw">保存</button>
	        </div>
	      </form>
      </div>
		  
		</div>
		<script type="text/javascript">
			layui.use('element', function(){
			  var element = layui.element;
			  
			});
			layer.msg('hello');
		/*	$(function () {
	            //禁用“确认重新提交表单”
	            window.history.replaceState(null, null, window.location.href);
	        })*/
	        layui.use('form', function(){
	            var form = layui.form;
	            layer.load();
	            setTimeout(function(){
	         	   layer.closeAll('loading');
	         	 }, 200);
	            
	            form.verify({
	         	   pwcheck: function(value, item){ //value：表单的值、item：表单的DOM对象
	         		   if($('input[name=password2]').val() !== value)
	         	            return '提示：两次输入密码不一致！';

	         	   }
	         	   
	         	   //数组的两个值分别代表：[正则匹配、匹配不符时的提示文字]
	         	   ,pass: [
	         	     /^[\S]{6,12}$/
	         	     ,'密码必须6到12位，且不能出现空格'
	         	   ] 
	         	 });      
	            
	            form.on('submit(editpw)', function(data){
	           	 var formdata = data.field;
	           	 console.log(formdata);
	       		    $.ajax({
	       		        type:"POST",
	       		        url:"userController/update?action=pw",
	       		        data:{"userName":formdata.userName,"password":formdata.password1,"password_ud":formdata.password3},
	       		        dataType:"JSON",
	       		        success:function (data) {
	       		        	if(data.result=="yes"){
	       		        		layer.closeAll();
	       		        		layer.msg('修改成功');
	       		        		form.render();
	       					}else{
	       						layer.closeAll();
	       		        		layer.msg('原密码错误');
	       						
	       					}
	       		        }
	       		    });
	           	  return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
	           	});
	       
	          });
	        function ShowPwtext(){
				layer.open({
					type:1,
					title:"修改密码",
					area:["460px","380px"],
					content:$("#edit_pw")
					});
				}

		</script>
		</c:if>
</body>
</html>