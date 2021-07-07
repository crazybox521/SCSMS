<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta charset="UTF-8">
<title>校外培训班</title>
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
		overflow:hidden;
		background-color: #fff;
		width:100%;
	}
	.header{
	    width:100%;
	    height:100px;
	    background-color:#FAFAFA;
	    position: absolute; 
	    top:0px;
	    box-shadow:0px 4px 10px #999;
	    min-width:1200px;
	}
	.title{
		display:inline-block;
		font-weight:bold;
		font-size:36px;
		color:#E02727;
	    float:left;
		width:50%;
		min-width:600px;
		height:100px;
		line-height:100px;
	
	}
	.title label{
		margin-left:10%;
		height:100px;
		min-width:600px;
	}
	.display_btn{
	    min-width:600px;
		display:inline-block;
		font-weight:bold;
		font-size:24px;
	    float:right;
	    width:50%;
	    height:100px;
	    margin:0 auto;
	    line-height:100px;
	}
	.display_btn div{
		display:inline-block;
		height:100px;
	
	}
	.btn1{
		float:left;
		color:black;
		width:60%;
	}
	.btn2{
		float:right;
		margin-right:10px;
		width:30%;
	}
	.display_btn .btn1 a{
	    text-decoration:none;
	    margin:0 auto;
	    color:#000;  
	}
	.display_btn .btn2 a{
	    text-decoration:none;
	    margin:0 auto;
	    color:#E02727;  
	}
	#loginBox{
	    display:none;
	}
	#RegisterBox{
	    display:none;
	
	
	}
	.layui-input-block{
		margin-right:20px;
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
		margin-top:100px;
	
	}
	iframe{
		position: absolute;
		top:0px;
		left:0px;
		right:0px;
		bottom:0px;
		width: 100%;
		height:87%;
		min-width:1200px;
   }
		
</style>
</head>
<body>
		<div class="container">   
		<div class="header">
			<div class="title">
			<label>校外培训班</label>
			</div>
            <div class="display_btn">
            	<div class="btn1" >
            		<a href="index"  >首页</a>
            		<a href="lesson" id="btna" target="ifr">课程中心</a>
            		
            		</div>
            	<div class="btn2">
            		<a href="javascript:;" id="bLogin" onclick="ShowLoginView()">登录</a>
           	   		<a href="javascript:;" id="bReg" onclick="ShowRegisterView()">注册</a>
           	    </div>
            </div>
            </div>
            <div class="bottom">
				<iframe name="ifr" src="welcome" frameBorder="0"></iframe>
			</div>
        
        <div id="loginBox">
         <form class="layui-form layui-form-pane" action="login"   method="post">
		        <div class="layui-form-item">
		          <label class="layui-form-label">用户名</label>
		          <div class="layui-input-block">
		            <input type="text" name="userName" required  lay-verify="required" placeholder="请输入用户名" autocomplete="off" class="layui-input">
		          </div>
		        </div>
		        <div class="layui-form-item">
		          <label class="layui-form-label">密码</label>
		          <div class="layui-input-block">
		            <input type="password" name="password" required lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input">
		          </div>
		   
		        </div>
		        <div class="layui-form-item">
		          <div class="layui-input-block">
		            <button class="layui-btn layui-btn-danger" lay-submit lay-filter="form1">登录</button>
		            <button type="reset" class="layui-btn layui-btn-primary layui-border-red">重置</button>
		          </div>
		        </div>
		      </form>
		</div>
		<div id="RegisterBox">
		<form class="layui-form layui-form-pane" action="">
		        <div class="layui-form-item">
		          <label class="layui-form-label">用户名</label>
		          <div class="layui-input-inline">
		            <input type="text" name="userName" required  lay-verify="required|username" placeholder="请填写用户名" autocomplete="off" class="layui-input">
		          </div>
		          <div class="layui-form-mid layui-word-aux">用户名不得含特殊字符</div>
		        </div>
		        <div class="layui-form-item">
		          <label class="layui-form-label">密码</label>
		          <div class="layui-input-inline">
		            <input type="password" name="password2" required lay-verify="required|pass" placeholder="请填写密码" autocomplete="off" class="layui-input">
		          </div>
		          <div class="layui-form-mid layui-word-aux">密码为6-12位</div>
		        </div>
		        <div class="layui-form-item">
		          <label class="layui-form-label">确认密码</label>
		          <div class="layui-input-inline">
		            <input type="password" name="password3" required lay-verify="required|pass|pwcheck" placeholder="请再次填写密码" autocomplete="off" class="layui-input">
		          </div>
		          <div class="layui-form-mid layui-word-aux">请再次输入密码</div>
		        </div>
		         <div class="layui-form-item">
		          <label class="layui-form-label">姓名</label>
		          <div class="layui-input-inline">
		            <input type="text" name="name" required  lay-verify="required" placeholder="请填写姓名" autocomplete="off" class="layui-input">
		          </div>
		          <div class="layui-form-mid layui-word-aux">请输入你的真实姓名</div>
		        </div> <div class="layui-form-item">
		          <label class="layui-form-label">电话号码</label>
		          <div class="layui-input-inline">
		            <input type="text" name="phone" required  lay-verify="required|phone" placeholder="请填写电话号码" autocomplete="off" class="layui-input">
		          </div>
		          <div class="layui-form-mid layui-word-aux">请输入正确的电话号码</div>
		        </div>
		        <div class="layui-form-item">
		          <div class="layui-input-block">
		            <button class="layui-btn layui-btn-danger" lay-submit lay-filter="form2">立即提交</button>
		            <button type="reset" class="layui-btn layui-btn-primary layui-border-red">重置</button>
		          </div>
		        </div>
		      </form>
		      </div>
		</div>
	<script type="text/javascript">
	    layui.use('form', function(){
		   var form = layui.form;
		   
		   form.verify({
			   pwcheck: function(value, item){ //value：表单的值、item：表单的DOM对象
	    		   if($('input[name=password2]').val() !== value)
	    	            return '提示：两次输入密码不一致！';

	    	   }
			   ,username: function(value, item){ //value：表单的值、item：表单的DOM对象
				    if(!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(value)){
				      return '用户名不能有特殊字符';
				    }
				    if(/(^\_)|(\__)|(\_+$)/.test(value)){
				      return '用户名首尾不能出现下划线\'_\'';
				    }
				    if(/^\d+\d+\d$/.test(value)){
				      return '用户名不能全为数字';
				    }
				  }
	    	   
	    	   //数组的两个值分别代表：[正则匹配、匹配不符时的提示文字]
	    	   ,pass: [
	    	     /^[\S]{6,12}$/
	    	     ,'密码必须6到12位，且不能出现空格'
	    	   ] 
	    	 });   
		   
		   form.on('submit(form1)',function(data){
		
		   });
		   
		   form.on('submit(form2)', function(data){
			   console.log(data);
			   var formdata = data.field;
			    $.ajax({
			        type:"POST",
			        url:"userController/insert",
			        data:{"userName":formdata.userName,"password":formdata.password2,"name":formdata.name,"phone":formdata.phone},
			        dataType:"JSON",
			        success:function (data) {
			        	if(data.result=="yes"){
			        		layer.closeAll();
			        		layer.msg('注册成功');
						}else{
							layer.msg('注册失败，换个用户名吧!');
						}
			        }
			    });
		     return false;
		   });
		 });
		function ShowLoginView(){
		    layer.open({
		        type:1,
		        title:"登录",
		        area:["395px","300px"],
		        content:$("#loginBox"),
		        });
		    }
		function ShowRegisterView(){
		    layer.open({
		        type:1,
		        title:"注册",
		        area:["480px","400px"],
		        content:$("#RegisterBox"),
		        });
		    }
    </script>
</body>
</html>