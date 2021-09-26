<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
			<% String path=request.getContextPath(); String
				basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; %>
				<!DOCTYPE HTML>
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

						html,
						body {
							padding: 0px;
							margin: 0px;
							background-color: #fff;
						}

						.container {
							position: absolute;
							top: 0px;
							right: 0px;
							bottom: 0px;
							left: 0px;
							background-color: #ffffff;
							overflow: hidden;
							width: 100%;
						}

						.content {
							margin: 5px auto;
							width: 100%;
							min-width: 800px;
						}

						#addBox {
							display: none;
						}

						#editBox {
							display: none;
						}

						.error {
							text-align: center;
							margin: 15% auto;
							width: 50%;
							min-width: 600px;
							padding: 20px;
							height: 100%;
						}

						td {
							min-height: 50px;

						}
					</style>
				</head>

				<body>
					<c:if test="${empty sessionScope.admin}">
						<div class="error">
							<h1>登录失效，点击返回主页重新登陆
								<a href="index" target="_parent"
									class="layui-btn layui-btn-primary layui-border-red">返回主页</a>
							</h1>
						</div>
					</c:if>
					<c:if test="${not empty sessionScope.admin}">
						<div class="container">
							<div class="content">
								<div class="demoTable">
									搜索ID：
									<div class="layui-inline layui-form">
										<input class="layui-input" name="id" id="demoReload" autocomplete="off"
											lay-verify="number">
									</div>
									<button class="layui-btn" data-type="reload"><i class="layui-icon">&#xe615;</i>
										搜索</button>
									<a href="javascript:;" id="addBtn" class="layui-btn layui-btn-radius"
										onclick="ShowAddText()"><i class="layui-icon">&#xe608;</i>添加</a>
								</div>

								<table class="layui-hide" id="test" lay-filter="test"></table>

							</div>
							<div id="addBox">
								<form class="layui-form layui-form-pane" action="" method="post"
									lay-filter="form_addgrade">
									<div class="layui-form-item">
										<label class="layui-form-label">选择课程</label>
										<div class="layui-input-block">
											<select name="lesson" lay-verify="required" id="addlesson"
												lay-filter="form_addgradelesson">
												<option value="">请先选择课程</option>
											</select>
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
										<label class="layui-form-label">任课老师</label>
										<div class="layui-input-block">
											<select name="teacher" lay-verify="required" id="addteacher">
												<option value="">请选择任课老师</option>
											</select>
										</div>
									</div>
									<div class="layui-form-item">
										<label class="layui-form-label">开始时间</label>
										<div class="layui-input-inline">
											<input type="text" name="starttime" class="layui-input" id="time1" required
												lay-verify="required" placeholder="yyyy-MM-dd">
										</div>
									</div>
									<div class="layui-form-item">
										<label class="layui-form-label">结束时间</label>
										<div class="layui-input-inline">
											<input type="text" name="endtime" class="layui-input" id="time2" required
												lay-verify="required" placeholder="yyyy-MM-dd">
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
										<div class="layui-input-block">
											<textarea name="notes" placeholder="请输入班级说明" class="layui-textarea"
												style="max-height:150px;"></textarea>
										</div>
									</div>
									<div class="layui-form-item">
										<div class="layui-input-block">
											<button class="layui-btn" lay-submit
												lay-filter="form_addgrade">立即提交</button>
											<button type="reset" class="layui-btn layui-btn-primary">重置</button>
										</div>
									</div>
								</form>
							</div>
						</div>

						<script type="text/html" id="demoTable">

		<button class="layui-btn layui-btn-sm layui-btn-danger" lay-event="delData">删除选中行数据</button>
	</script>

						<script type="text/html" id="barDemo">

	  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
	</script>

						<script type="text/javascript">
							layui.use('laydate', function () {
								var laydate = layui.laydate;

								//执行一个laydate实例
								laydate.render({
									elem: '#time1' //指定元素
								});
								laydate.render({
									elem: '#time2' //指定元素
								});

							});


							layui.use('table', function () {
								var table = layui.table;
								var form = layui.form;

								table.render({
									elem: '#test'
									, url: 'gradeController/list' //数据接口
									, method: 'post'
									, toolbar: '#demoTable' //开启头部工具栏，并为其绑定左侧模板
									, defaultToolbar: ['filter', 'exports', 'print', { //自定义头部工具栏右侧图标。如无需自定义，去除该参数即可
										title: '提示'
										, layEvent: 'LAYTABLE_TIPS'
										, icon: 'layui-icon-tips'
									}]
									, title: '班级数据表'
									, cellMinWidth: 100
									, cols: [[
										{ type: 'checkbox', fixed: 'left' }
										, { field: 'id', title: 'ID', fixed: 'left', unresize: true, sort: true }
										, { field: 'gradeName', title: '班级名' }
										, {
											field: 'lesson.lessonName', title: '课程名', templet: function (res) {
												return '<em>' + res.lesson.lessonName + '</em>'
											}
										}
										, {
											field: 'room.roomName', title: '教室名', templet: function (res) {
												return '<em>' + res.room.roomName + '</em>'
											}
										}
										, {
											field: 'room.location', title: '上课地址', templet: function (res) {
												return '<em>' + res.room.location + '</em>'
											}
										}
										, {
											field: 'teacher.name', title: '任课老师', templet: function (res) {
												return '<em>' + res.teacher.name + '</em>'
											}
										}
										, { field: 'starttime', title: '开始时间' }
										, { field: 'endtime', title: '结束时间' }
										, { field: 'state', title: '状态' }
										, { field: 'notes', title: '说明' }
										, { fixed: 'right', title: '操作', toolbar: '#barDemo' }
									]]
									, id: 'testReload'
									, page: true
								});

								var $ = layui.$, active = {
									reload: function () {
										var demoReload = $('#demoReload');

										//执行重载
										table.reload('testReload', {
											page: {
												curr: 1 //重新从第 1 页开始
											}
											, where: {
												key: {
													id: demoReload.val()
												}
											}
										});
									}
								};

								$('.demoTable .layui-btn').on('click', function () {
									var type = $(this).data('type');
									active[type] ? active[type].call(this) : '';
								});

								//头工具栏事件
								table.on('toolbar(test)', function (obj) {
									var checkStatus = table.checkStatus(obj.config.id);
									switch (obj.event) {
										case 'delData':
											var data = checkStatus.data;
											var arr = [];
											for (var i = 0; i < data.length; i++) {
												arr.push(data[i].id);
											}
											if (arr.length != 0) {
												layer.confirm('真的要删除么', function (index) {

													console.log(arr);
													$.ajax({
														url: "gradeController/deleteAll",
														type: "post",
														data: { "arrs[]": arr },
														dataType: "json",
														success: function (data) {
															if (data.result == "yes") {
																console.log("succes");
																layer.close(index);
																table.reload('testReload');
																layer.msg('删除成功');

															}

														}
													});
												});
											} else {
												layer.msg('还没有选中一个数据');
											}


											break;


										//自定义头工具栏右侧图标 - 提示
										case 'LAYTABLE_TIPS':
											layer.alert('这里是班级管理');
											break;
									};
								});

								//监听行工具事件
								table.on('tool(test)', function (obj) {
									var data = obj.data;
									console.log(obj)
									if (obj.event === 'del') {
										layer.confirm('真的删除行么', function (index) {
											obj.del();
											grade_delete(data.id);
											layer.close(index);
										});
									}
								});
							});

							function grade_delete(id) {
								$.ajax({
									url: "gradeController/delete",
									type: "post",
									data: { "id": id },
									dataType: "json",
									success: function (data) {
										if (data.result == "yes") {
											console.log(data);
										}

									}
								});
							}
							function edit_submit(obj) {
								$.ajax({
									url: "gradeController/update?action=all",
									type: "post",
									data: { "password": obj.password, "id": obj.id },
									dataType: "json",
									success: function (data) {
										if (data.result == "yes") {
											console.log("succes");
										}

									}
								});
							}

							function ShowAddText() {
								layer.open({
									type: 1,
									title: "添加班级",
									area: ["460px", "600px"],
									content: $("#addBox")
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
									url: "lessonController/lessonlist",
									type: "post",
									data: {},
									dataType: "json",
									success: function (data) {
										console.log(data);//下面会提到这个data是什么值
										//使用循环遍历，给下拉列表赋值
										$.each(data.data, function (i, item) {
											// console.log(value.department_id);
											$('#addlesson').append(new Option(item.lessonName, item.id));// 下拉菜单里添加元素
										});
										layui.form.render("select");//重新渲染 固定写法

									}
								});

								$.ajax({
									url: "roomController/queryAll",
									type: "post",
									data: {},
									dataType: "json",
									success: function (data) {
										console.log(data);//下面会提到这个data是什么值
										//使用循环遍历，给下拉列表赋值
										$.each(data.data, function (i, item) {
											// console.log(value.department_id);
											$('#addroom').append(new Option(item.roomName, item.id));// 下拉菜单里添加元素
										});
										layui.form.render("select");//重新渲染 固定写法

									}
								});

								form.on('select(form_addgradelesson)', function (data) {
									console.log(data.elem); //得到select原始DOM对象
									console.log(data.value); //得到被选中的值
									$.ajax({
										url: "teacherController/lessontealist",
										type: "post",
										data: { "lessonid": data.value },
										dataType: "json",
										success: function (data) {
											console.log(data);//下面会提到这个data是什么值
											//使用循环遍历，给下拉列表赋值
											$.each(data.data, function (i, item) {
												// console.log(value.department_id);
												$('#addteacher').append(new Option(item.name, item.id));// 下拉菜单里添加元素
											});
											layui.form.render("select");//重新渲染 固定写法

										}
									});
								});

								form.on('submit(form_addgrade)', function (data) {
									var formdata = data.field;
									$.ajax({
										type: "POST",
										url: "gradeController/insert",
										data: { "gradeName": formdata.gradeName, "lesson.id": formdata.lesson, "teacher.id": formdata.teacher, "room.id": formdata.room, "notes": formdata.notes, "starttime": formdata.starttime, "endtime": formdata.endtime },
										dataType: "JSON",
										success: function (data) {
											if (data.result == "yes") {
												layer.closeAll();
												layer.msg('添加成功');
												table.reload('testReload');
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