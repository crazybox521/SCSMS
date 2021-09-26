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
					<title>用户管理</title>
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
								<form class="layui-form layui-form-pane" action="" method="post">
									<div class="layui-form-item">
										<label class="layui-form-label">添加课程</label>
										<div class="layui-input-block">
											<input type="text" name="name" required lay-verify="required"
												placeholder="请输入课程名" autocomplete="off" class="layui-input">
										</div>
									</div>
									<div class="layui-form-item">
										<label class="layui-form-label">学时</label>
										<div class="layui-input-block">
											<input type="text" name="hours" required lay-verify="required|number|test"
												placeholder="请输入学时" autocomplete="off" class="layui-input">
										</div>
									</div>
									<div class="layui-form-item">
										<label class="layui-form-label">定价¥</label>
										<div class="layui-input-block">
											<input type="text" name="price" required lay-verify="required|number|test"
												placeholder="请输入价格" autocomplete="off" class="layui-input">
										</div>
									</div>
									<div class="layui-form-item layui-form-text">
										<label class="layui-form-label">课程说明</label>
										<div class="layui-input-block">
											<textarea name="notes" placeholder="请输入课程说明" class="layui-textarea"
												style="max-height:150px;"></textarea>
										</div>
									</div>

									<div class="layui-form-item">
										<div class="layui-input-block">
											<button class="layui-btn" lay-submit lay-filter="form1">立即提交</button>
											<button type="reset" class="layui-btn layui-btn-primary">重置</button>
										</div>
									</div>
								</form>
							</div>
							<div id="editBox">
								<form class="layui-form layui-form-pane" action="" lay-filter="form2">
									<div class="layui-form-item">
										<label class="layui-form-label">课程编号</label>
										<div class="layui-input-inline">
											<input type="text" name="id" readonly="readonly"
												value="${ sessionScope.student.userName}" class="layui-input">
										</div>
										<div class="layui-form-mid layui-word-aux">注:课程编号无法修改</div>
									</div>

									<div class="layui-form-item">
										<label class="layui-form-label">课程名</label>
										<div class="layui-input-block">
											<input type="text" name="name" required lay-verify="required"
												placeholder="请输入课程名" autocomplete="off" class="layui-input">
										</div>
									</div>

									<div class="layui-form-item">
										<label class="layui-form-label">学时</label>
										<div class="layui-input-block">
											<input type="text" name="hours" required lay-verify="required|number"
												placeholder="请输入学时" autocomplete="off" class="layui-input">
										</div>
									</div>

									<div class="layui-form-item">
										<label class="layui-form-label">定价¥</label>
										<div class="layui-input-block">
											<input type="text" name="price" required
												lay-verify="required|number placeholder=" 请输入定价" autocomplete="off"
												class="layui-input">
										</div>
									</div>

									<div class="layui-form-item layui-form-text">
										<label class="layui-form-label">课程说明</label>
										<div class="layui-input-block">
											<textarea name="notes" placeholder="请输入内容" class="layui-textarea"
												style="max-height:150px;"></textarea>
										</div>
									</div>
									<div class="layui-form-item" style="text-align: center;">
										<button class="layui-btn" lay-submit lay-filter="form2">保存</button>
									</div>
								</form>
							</div>
						</div>
						<script type="text/html" id="demoTable">

		<button class="layui-btn layui-btn-sm layui-btn-danger" lay-event="delData">删除选中行课程</button>
	</script>

						<script type="text/html" id="barDemo">
	  <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
	  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
	</script>
						<script type="text/javascript">
							function ShowAddText() {
								layer.open({
									type: 1,
									title: "添加课程",
									area: ["460px", "500px"],
									content: $("#addBox"),
								});
							}

							layui.use('form', function () {
								var form = layui.form;

								form.verify({
									test: function (value, item) { //value：表单的值、item：表单的DOM对象
										if (value > 1000000000) {
											return '价格太高了';
										}

									}


								});

								form.on('submit(form1)', function (data) {
									var formdata = data.field;
									$.ajax({
										type: "POST",
										url: "lessonController/insert",
										data: { "lessonName": formdata.name, "price": formdata.price, "notes": formdata.notes, "hours": formdata.hours },
										dataType: "JSON",
										success: function (data) {
											if (data.result == "yes") {
												layer.closeAll();
												layer.msg('添加成功');
												list(1);
											} else {
												layer.msg('添加失败');
											}
										}
									});
									return false;
								});
							});

							layui.use('table', function () {
								var table = layui.table;
								var form = layui.form;

								table.render({
									elem: '#test'
									, url: 'lessonController/list' //数据接口
									, method: 'post'
									, toolbar: '#demoTable' //开启头部工具栏，并为其绑定左侧模板
									, defaultToolbar: ['filter', 'exports', 'print', { //自定义头部工具栏右侧图标。如无需自定义，去除该参数即可
										title: '提示'
										, layEvent: 'LAYTABLE_TIPS'
										, icon: 'layui-icon-tips'
									}]
									, title: '学生数据表'
									, cellMinWidth: 100
									, cols: [[
										{ type: 'checkbox', fixed: 'left' }
										, { field: 'id', title: 'ID', fixed: 'left', unresize: true, sort: true }
										, { field: 'lessonName', title: '课程名' }
										, { field: 'price', title: '价格' }
										, { field: 'notes', title: '说明' }
										, { field: 'hours', title: '学时' }
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
												arr.push(data[i].user.id);
											}
											if (arr.length != 0) {
												layer.confirm('真的要删除么', function (index) {

													console.log(arr);
													$.ajax({
														url: "lessonController/deleteAll",
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
											layer.alert('这里是课程管理');
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
											lesson_delete(data.id);
											layer.close(index);
										});
									} else if (obj.event === 'edit') {
										lesson_edit(data.id);
									}
								});
							});

							function lesson_delete(id) {
								$.ajax({
									url: "lessonController/delete",
									type: "post",
									data: { "id": id },
									dataType: "json",
									success: function (data) {
										if (data.result == "yes") {
											list(1);
										}

									}
								});
							}
							layui.use('form', function () {
								var form = layui.form;

							});

							function lesson_edit(id) {
								layer.open({
									type: 1,
									title: "修改课程设置",
									area: ["460px", "600px"],
									content: $("#editBox"),
								});
								layui.use('form', function () {
									var form = layui.form;
									layer.load();
									setTimeout(function () {
										layer.closeAll('loading');
									}, 200);
									$.ajax({
										url: "lessonController/info",
										type: "post",
										data: { "id": id },
										dataType: "json",
										success: function (data) {
											form.val("form2", {
												"id": data.id
												, "name": data.lessonName
												, "price": data.price
												, "notes": data.notes
												, "hours": data.hours
											});
											form.render();

										}
									});

									form.on('submit(form2)', function (data) {
										var formdata = data.field;
										$.ajax({
											type: "POST",
											url: "lessonController/update",
											data: { "id": formdata.id, "lessonName": formdata.name, "price": formdata.price, "notes": formdata.notes, "hours": formdata.hours },
											dataType: "JSON",
											success: function (data) {
												if (data.result == "yes") {
													layer.closeAll();
													layer.msg('保存成功');
													form.render();
													list(1);
												}
											}
										});
										return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
									});

								});

							}

						</script>
					</c:if>
				</body>

				</html>