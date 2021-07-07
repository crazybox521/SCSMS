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
<title>个人信息页面</title>
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
	
   .layui-form-item .layui-input-block input {
    width: 250px;
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
     	<div class="title">修改资料</div>
     	<input type="hidden" id="userid" value="${ sessionScope.student.id}">
     	<div class="site-text">
      <form class="layui-form layui-form-pane" action="" lay-filter="form1">
      	<div class="layui-form-item">
          <label class="layui-form-label">用户名</label>
          <div class="layui-input-inline">
            <input type="text" name="userName" readonly="readonly" value="${ sessionScope.student.userName}" class="layui-input">
          </div>
          <div class="layui-form-mid layui-word-aux">注:用户名无法修改</div>
        </div>
        <div class="layui-form-item">
          <label class="layui-form-label">姓名</label>
          <div class="layui-input-block">
            <input type="text" name="name" required lay-verify="required" placeholder="请输入姓名" autocomplete="off" class="layui-input">
          </div>
        </div>
        <div class="layui-form-item">
          <label class="layui-form-label">联系电话</label>
          <div class="layui-input-block">
            <input type="text" name="phone" required lay-verify="required|phone" placeholder="请输入联系电话" autocomplete="off" class="layui-input">
          </div>
        </div>
        <div class="layui-form-item">
          <label class="layui-form-label">邮箱</label>
          <div class="layui-input-block">
            <input type="text" name="email" required lay-verify="required|email" placeholder="请输入邮箱" autocomplete="off" class="layui-input">
          </div>
        </div>
        <div class="layui-form-item" pane style="width:280px;">
          <label class="layui-form-label">性别</label>
          <div class="layui-input-block" >
            <input type="radio" name="sex" value="男" title="男" ><div class="layui-unselect layui-form-radio layui-form-radioed"><i class="layui-anim layui-icon layui-anim-scaleSpring"></i><div>男</div></div>
            <input type="radio" name="sex" value="女" title="女"  checked><div class="layui-unselect layui-form-radio"><i class="layui-anim layui-icon"></i><div>女</div></div>
          </div>
        </div>
        <div class="layui-form-item layui-form-text">
          <label class="layui-form-label">个人说明</label>
          <div class="layui-input-block" >
            <textarea name="notes" placeholder="请输入内容" class="layui-textarea" style="max-height:150px;"></textarea>
          </div>
        </div>
        <hr class="layui-border-red">
        <div class="layui-form-item" style="text-align: center">
        
          <button class="layui-btn layui-btn-danger" lay-submit lay-filter="form1">保存</button>
        </div>
      </form>
    </div>
     	
     	</div>
     	</div>
     
     
     
     
     </div>
     <script type="text/javascript">
     layui.use('form', function(){
       var form = layui.form;
       layer.load();
       setTimeout(function(){
    	   layer.closeAll('loading');
    	 }, 200);
       var userid =$("#userid").val();;
		$.ajax({
			url:"studentController/info",
			type:"post",
			data:{"userid":userid},
			dataType:"json",
			success:function(data){
					form.val("form1", { 
						  "name": data.name
						  ,"sex": data.sex
						  ,"phone":data.phone
						  ,"email":data.email
						  ,"notes":data.notes
						});
		        		form.render();
			
			}
		});
       
       form.on('submit(form1)', function(data){
      	 var formdata = data.field;
  		    $.ajax({
  		        type:"POST",
  		        url:"studentController/update",
  		        data:{"user.userName":formdata.userName,"name":formdata.name,"sex":formdata.sex,"email":formdata.email,"notes":formdata.notes,"phone":formdata.phone},
  		        dataType:"JSON",
  		        success:function (data) {
  		        	if(data.result=="yes"){
  		        		layer.closeAll();
  		        		layer.msg('保存成功');
  		        		form.render();
  					}
  		        }
  		    });
      	  return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
      	});
  
     });
     
     
     </script>
     </c:if>


</body>
</html>