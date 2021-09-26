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
							margin: 0px auto;
							width: 100%;
							min-width: 900px;
						}

						#addBox {
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
									<div class="layui-inline">
										<input class="layui-input" name="id" id="demoReload" autocomplete="off">
									</div>
									<button class="layui-btn" data-type="reload"><i class="layui-icon">&#xe615;</i>
										搜索</button>
									<a href="javascript:;" id="addBtn" class="layui-btn layui-btn-radius"
										onclick="ShowAddText()"><i class="layui-icon">&#xe608;</i>添加</a>
								</div>

								<table class="layui-hide" id="test" lay-filter="test"></table>

							</div>
							<div id="addBox">
								<form class="layui-form" action="userController/insert" method="post">
									<div class="layui-form-item">
										<label class="layui-form-label">添加用户</label>
										<div class="layui-input-block">
											<input type="text" name="userName" required lay-verify="required"
												placeholder="请输入用户名" autocomplete="off" class="layui-input">
										</div>
									</div>
									<div class="layui-form-item">
										<label class="layui-form-label">输入密码</label>
										<div class="layui-input-inline">
											<input type="text" name="password" required lay-verify="required|userName"
												placeholder="请输入密码" autocomplete="off" class="layui-input">
										</div>
										<div class="layui-form-mid layui-word-aux">密码为6-12位</div>
									</div>
									<div class="layui-form-item">
										<label class="layui-form-label">用户身份</label>
										<div class="layui-input-block">
											<select name="role" lay-verify="required">
												<option value=""></option>
												<option value="1">系统管理员</option>
												<option value="2">教师</option>
												<option value="3">学员</option>
											</select>
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
						</div>

						<script type="text/html" id="demoTable">

		<button class="layui-btn layui-btn-sm layui-btn-danger" lay-event="delData">删除选中行数据</button>
	</script>

						<script type="text/html" id="barDemo">
	  <a class="layui-btn layui-btn-xs" lay-event="edit">编辑密码</a>
	  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除账号</a>
	</script>

						<script type="text/javascript">


							layui.use('table', function () {
								var table = layui.table;

								table.render({
									elem: '#test'
									, url: 'userController/list' //数据接口
									, method: 'post'
									, parseData: function (res) { //res 即为原始返回的数据
										return {
											"code": res.code, //解析接口状态
											"msg": res.msg, //解析提示文本
											"count": res.total, //解析数据长度
											"data": res.data //解析数据列表
										};
									}
									, toolbar: '#demoTable' //开启头部工具栏，并为其绑定左侧模板
									, defaultToolbar: ['filter', 'exports', 'print', { //自定义头部工具栏右侧图标。如无需自定义，去除该参数即可
										title: '提示'
										, layEvent: 'LAYTABLE_TIPS'
										, icon: 'layui-icon-tips'
									}]
									, title: '用户数据表'
									, cols: [[
										{ type: 'checkbox', fixed: 'left' }
										, { field: 'id', title: 'ID', fixed: 'left', unresize: true, sort: true }
										, { field: 'userName', title: '用户名' }
										, { field: 'password', title: '密码' }
										, {
											field: 'role.id', title: '角色ID', templet: function (res) {
												return '<em>' + res.role.id + '</em>'
											}
										}
										, {
											field: 'role.name', title: '角色名', templet: function (res) {
												return '<em>' + res.role.name + '</em>'
											}
										}
										, { field: 'time', title: '注册时间' }
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
														url: "userController/deleteAll",
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
											layer.alert('这里是用户管理');
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
											user_delete(data.id);
											layer.close(index);
										});
									} else if (obj.event === 'edit') {
										layer.prompt({
											formType: 0
											, title: '请输入新密码'
											, value: data.password
										}, function (value, index) {
											console.log(data);
											obj.update({
												password: value
											});
											data.password = value;
											console.log(data);
											edit_submit(data)
											layer.close(index);
										});
									}
								});
							});

							function user_delete(id) {
								$.ajax({
									url: "userController/delete",
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
									url: "userController/update?action=all",
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
									title: "添加用户",
									area: ["460px", "600px"],
									content: $("#addBox")
								});
							}
							function ShowAddText() {
								layer.open({
									type: 1,
									title: "添加用户",
									area: ["460px", "500px"],
									content: $("#addBox")
								});
							}

							layui.use('form', function () {
								var form = layui.form;
								var table = layui.table;

								form.on('submit(form1)', function (data) {
									var formdata = data.field;
									$.ajax({
										type: "POST",
										url: "userController/insert",
										data: { "userName": formdata.userName, "password": formdata.password, "role.id": formdata.role },
										dataType: "JSON",
										success: function (data) {
											if (data.result == "yes") {
												layer.closeAll();
												layer.msg('添加成功');
												table.reload('testReload');
											} else {
												layer.msg("该用户名已存在");
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