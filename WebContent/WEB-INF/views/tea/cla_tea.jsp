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
<title>班级管理</title>
<script src="js/jquery.min.js"></script>
<link rel="stylesheet" href="./layui/css/layui.css">
<script src="layui/layui.js"></script>
<style type="text/css">
	@charset "UTF-8";
	html,body{
		padding: 0px;
		margin: 0px;
		background-color: #000;
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
	.display{
		position: absolute;
		top:0px;
		right:100px;
		bottom:0px;
		left:100px;
		margin:0px auto;
		min-width:970px;
	
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
   #addBox{
		display:none;
	}
	#editBox{
		display:none;
	}
	#student{
		display:none;
	}
		
</style>
</head>
<body>
<c:if test="${empty sessionScope.teacher}">
	<div class="error">
 		<h1>登录失效，点击返回主页重新登陆
 		<a href="index" target="_parent" class="layui-btn layui-btn-primary layui-border-red">返回主页</a></h1>
	</div>
		</c:if>
<c:if test="${not empty sessionScope.teacher}">
	<div class="container">
		<div class="display" >
		<div class="title">我的班级</div>
		<div class="demoTable">
				<a href="javascript:;" id="addBtn"
					class="layui-btn layui-btn-radius layui-btn-danger" onclick="ShowAddText()"><i
						class="layui-icon">&#xe608;</i>开设新班级</a>
			</div>

			<table class="layui-hide" id="test" lay-filter="test"></table>

		</div>
		<div id="addBox">
			<form class="layui-form layui-form-pane" action="" method="post" lay-filter="form_addgrade">
			<div  class="layui-form-item">
				 	<label class="layui-form-label">所授课程</label>
				      <div class="layui-input-block">
				        <input type="text" name="lesson" id="lesson" class="layui-input" required lay-verify="required" disabled>
				      </div>
				 </div>

				<div class="layui-form-item">
					<label class="layui-form-label">添加班级</label>
					<div class="layui-input-block">
						<input type="text" name="gradeName" required lay-verify="required"
							placeholder="请输入新班级名" autocomplete="off" class="layui-input">
					</div>
				</div>
				
				<div class="layui-form-item">
				      <label class="layui-form-label">开始时间</label>
				      <div class="layui-input-inline">
				        <input type="text" name="starttime" class="layui-input" id="time1" required lay-verify="required|date" placeholder="yyyy-MM-dd">
				      </div>
				 </div>
				 <div  class="layui-form-item">
				 	<label class="layui-form-label">结束时间</label>
				      <div class="layui-input-inline">
				        <input type="text" name="endtime" class="layui-input" id="time2" required lay-verify="required|date" placeholder="yyyy-MM-dd">
				      </div>
				 </div>
				 
				 <div class="layui-form-item">
					<label class="layui-form-label">上课地点</label>
					<div class="layui-input-block">
						<select name="room" lay-verify="required" id="addroom">
							<option value="">请选择上课地点</option>
						</select>
					</div>
				</div>
				    
				
				<div class="layui-form-item layui-form-text">
        			 <label class="layui-form-label">班级说明</label>
       				 <div class="layui-input-block" >
           			 <textarea name="notes" placeholder="请输入班级说明" class="layui-textarea" style="max-height:150px;"></textarea>
          		</div>
        		</div>
				<div class="layui-form-item">
					<div class="layui-input-block">
						<button class="layui-btn" lay-submit lay-filter="form_addgrade">立即提交</button>
						<button type="reset" class="layui-btn layui-btn-primary">重置</button>
					</div>
				</div>
			</form>
		</div>
		<div id="student">
		<table class="layui-hide" id="stulist" lay-filter="stulist"></table>
		
		</div>
	</div>

	 
	<script type="text/html" id="barDemo">
	   <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">查看</a>
		<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="jieshu">结束报名</a>
	</script>

	<script type="text/javascript">
	layui.use('laydate', function(){
		  var laydate = layui.laydate;
		  
		  //执行一个laydate实例
		  laydate.render({
		    elem: '#time1' //指定元素
		  });
		  laydate.render({
		    elem: '#time2' //指定元素
		  });
		  
		});


		layui.use('table', function(){
		var table = layui.table;
		var form = layui.form;
		
		table.render({
			elem: '#test'
			,url: 'gradeController/tea_list' //数据接口
			,method:'post'
			,skin: 'line'
			,toolbar: true //开启头部工具栏，并为其绑定左侧模板
			,defaultToolbar: ['filter', 'exports', 'print', { //自定义头部工具栏右侧图标。如无需自定义，去除该参数即可
			title: '提示'
			,layEvent: 'LAYTABLE_TIPS'
			,icon: 'layui-icon-tips'
			}]
			,title: '班级数据表'
			,cols: [[
			{type: 'checkbox', fixed: 'left'}
			,{field:'id', title:'ID', fixed: 'left',unresize: true, sort: true}
			,{field:'gradeName', title:'班级名'}
			,{field:'lesson.lessonName', title:'课程名', templet: function(res){
				return '<em>'+ res.lesson.lessonName +'</em>'
			}}
			,{field:'room.roomName', title:'教室名',  templet: function(res){
				return '<em>'+ res.room.roomName +'</em>'
			}}
			,{field:'room.location', title:'上课地址',  templet: function(res){
				return '<em>'+ res.room.location +'</em>'
			}}
			,{field:'starttime', title:'开始时间'}
			,{field:'endtime', title:'结束时间'}
			,{field:'state', title:'状态'}
			,{field:'notes', title:'说明'}
			,{fixed: 'right', title:'操作', toolbar: '#barDemo',width:150}
			]]
			,id: 'testReload'
			,page: true
		});

		
		
		//监听行工具事件
		table.on('tool(test)', function(obj){
			var data = obj.data;
			console.log(obj)
			if(obj.event === 'del'){
				var prarm="?id="+data.id;
				var table1=layui.table;
				table1.render({
					elem: '#stulist'
					,url: 'gradeController/mystu_list'+prarm //数据接口
					,method:'post'
					,skin: 'line'
					,title: '班级学生数据表'
					,cols: [[
					{type: 'checkbox', fixed: 'left'}
					,{field:'id', title:'ID', fixed: 'left',unresize: true, sort: true}
					,{field:'name', title:'姓名'}
					,{field:'sex', title:'性别'}
					,{field:'phone', title:'手机号'}
					,{field:'notes', title:'个人说明'}
					]]
					,id: 'stulistReload'
					,page: true
				});

				var index = layer.open({
					  type: 1,
					  content: $("#student"),
					  area: ['800px', '600px'],
					  title: '浏览学员'
					});
			}else if(obj.event === 'jieshu'){
				layer.confirm('真的结束报名么', function(index){
					$.ajax({
						url:"gradeController/jieshu",
						type:"post",
						data:{"id":data.id},
						dataType:"json",
						success:function(data){
							if(data.result=="yes"){
								
								table.reload('testReload');
							}
							
						}
					});
					layer.close(index);
				});
				
			}
		});
		});

		function grade_delete(id){
				$.ajax({
					url:"gradeController/delete",
					type:"post",
					data:{"id":id},
					dataType:"json",
					success:function(data){
						if(data.result=="yes"){
							console.log(data);
						}
						
					}
				});
			}
		function edit_submit(obj){
				$.ajax({
					url:"gradeController/update?action=all",
					type:"post",
					data:{"password":obj.password,"id":obj.id},
					dataType:"json",
					success:function(data){
						if(data.result=="yes"){
							console.log("succes");
						}
						
					}
				});
			}

		function ShowAddText(){
			layer.open({
					type:1,
					title:"添加班级",
					area:["460px","600px"],
					content:$("#addBox")
					});
		}
		function ShowEditText() {
			layer.open({
				type: 1,
				title: "修改班级",
				area: ["460px", "600px"],
				content: $("#addBox")
			});
		}

		layui.use('form', function () {
			var form = layui.form;
			var table = layui.table;
			
			$.ajax({
	 			url:"teacherController/getlesson",
	 			type:"post",
	 			data:{},
	 			dataType:"json",
	 			success:function(data){
	 				console.log(data);
	 				if(data.msg=="succes"){
	 					form.val("form_addgrade", { //formTest 即 class="layui-form" 所在元素属性 lay-filter="" 对应的值
	 						  "lesson": data.data.lesson.lessonName // "name": "value"
	 						  
	 						});
	 				}else{
	 					form.val("form_addgrade", { //formTest 即 class="layui-form" 所在元素属性 lay-filter="" 对应的值
	 						  "lesson": "管理人员还未给您设置教授课程" // "name": "value"
	 						  
	 						});
	 				}
	 			   
	 			
	 			}
	 		});

			 $.ajax({
	 			url:"roomController/queryAll",
	 			type:"post",
	 			data:{},
	 			dataType:"json",
	 			success:function(data){
					console.log(data);//下面会提到这个data是什么值
                    //使用循环遍历，给下拉列表赋值
                    $.each(data.data, function (i, item) {
                        // console.log(value.department_id);
                        $('#addroom').append(new Option(item.roomName,item.id));// 下拉菜单里添加元素
                    });
                    layui.form.render("select");//重新渲染 固定写法
	 			
	 			}
	 		});
			
			
			form.on('submit(form_addgrade)', function (data) {
				var formdata = data.field;
				$.ajax({
					type: "POST",
					url: "gradeController/tea_insert",
					data: { "gradeName": formdata.gradeName, "room.id":formdata.room,"notes":formdata.notes,"starttime":formdata.starttime,"endtime":formdata.endtime},
					dataType: "JSON",
					success: function (data) {
						if (data.result == "yes") {
							layer.closeAll();
							layer.msg('添加成功');
							table.reload('testReload');
						}else{
							layer.msg('添加失败，您还没有可教授课程，请联系管理人员！');
						}
					}
				});
				return false;
			});
		});
		

	</script>
</c:if>
</body>
</html>