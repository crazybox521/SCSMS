<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
			<% String path=request.getContextPath(); String
				basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; %>
				<!DOCTYPE html>
				<html>

				<head>
					<base href="<%=basePath%>">
					<meta charset="UTF-8">
					<title>通知</title>
					<script src="js/jquery.min.js"></script>
					<link rel="stylesheet" href="./layui/css/layui.css">
					<script src="layui/layui.js"></script>
					<style type="text/css">
						@charset "UTF-8";

						html,
						body {
							padding: 0px;
							margin: 0px;
							background-color: #000;
						}

						.container {
							position: absolute;
							top: 0px;
							right: 0px;
							bottom: 0px;
							left: 0px;
							min-width: 1200px;
							background-color: #fff;
							width: 100%;
						}

						.error {
							text-align: center;
							margin: 15% auto;
							width: 50%;
							min-width: 600px;
							padding: 20px;
							height: 100%;
						}

						.display {
							position: absolute;
							top: 0px;
							right: 100px;
							bottom: 0px;
							left: 100px;
							margin: 0px auto;
							min-width: 970px;

						}

						.title {
							line-height: 100px;
							font-size: 32px;
							font-weight: bold
						}
					</style>
				</head>

				<body>
					<c:if test="${empty sessionScope.teacher}">
						<div class="error">
							<h1>登录失效，点击返回主页重新登陆
								<a href="index" target="_parent"
									class="layui-btn layui-btn-primary layui-border-red">返回主页</a>
							</h1>
						</div>
					</c:if>
					<c:if test="${not empty sessionScope.teacher}">
						<div class="container">
							<div class="display">
								<div class="title">通知发布</div>
								<div class="demoTable">
									<form class="layui-form" action="" method="post" lay-filter="form_choosegrade">

										<div class="layui-form-item">
											<label class="layui-form-label">选择班级：</label>
											<div class="layui-input-inline">
												<select name="grade" lay-verify="required" id="choosegrade"
													lay-filter="choose">
													<option value="">请选择班级</option>
												</select>
											</div>
										</div>

									</form>
								</div>

								<table class="layui-hide" id="test" lay-filter="test"></table>

							</div>

						</div>
						<script type="text/html" id="headbar">
	  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="notice">发布通知</a>
</script>
						<script type="text/html" id="barDemo">
	  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="edit">修改通知内容</a>
		<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
						<script type="text/javascript">
							layui.use('form', function () {
								var form = layui.form;
								var table = layui.table;

								$.ajax({
									url: "gradeController/tea_listAll",
									type: "post",
									data: {},
									dataType: "json",
									success: function (data) {
										//使用循环遍历，给下拉列表赋值
										$.each(data.data, function (i, item) {
											// console.log(value.department_id);
											$('#choosegrade').append(new Option(item.gradeName, item.id));// 下拉菜单里添加元素
										});
										layui.form.render("select");//重新渲染 固定写法

									}
								});
								form.on('select(choose)', function (data) {
									var prarm = "?action=" + data.value;
									if (data.value != null && data.value != "") {
										table.render({
											elem: '#test'
											, url: 'noticeController/list' + prarm //数据接口
											, method: 'post'
											, toolbar: '#demoTable' //开启头部工具栏，并为其绑定左侧模板
											, defaultToolbar: ['filter', 'exports', 'print', { //自定义头部工具栏右侧图标。如无需自定义，去除该参数即可
												title: '提示'
												, layEvent: 'LAYTABLE_TIPS'
												, icon: 'layui-icon-tips'
											}]
											, title: '通知表'
											, cols: [[
												{ type: 'checkbox', fixed: 'left' }
												, { field: 'id', title: 'ID', fixed: 'left', unresize: true, sort: true, width: 100 }
												, { field: 'time', title: '发布时间', width: 160 }
												, { field: 'notes', title: '通知内容' }
												, { fixed: 'right', title: '操作', toolbar: '#barDemo', width: 200 }
											]]
											, id: 'testReload'
											, toolbar: '#headbar'
											, page: true
										});

										table.on('toolbar(test)', function (obj) {
											switch (obj.event) {
												case 'notice':
													layer.prompt({
														formType: 0
														, title: '发布通知'
														, formType: 2
													}, function (value, index) {
														var grade = $("#choosegrade").val();
														$.ajax({
															url: "noticeController/insert",
															type: "post",
															data: { "notes": value, "grade.id": grade },
															dataType: "json",
															success: function (data) {
																layer.msg("发布成功！");
																table.reload('testReload');
															}
														});
														layer.close(index);
													});



													break;

												//自定义头工具栏右侧图标 - 提示
												case 'LAYTABLE_TIPS':
													layer.alert('这里是通知');
													break;
											};
										});

										table.on('tool(test)', function (obj) {
											var data = obj.data;
											console.log(obj)
											if (obj.event === 'edit') {
												layer.prompt({
													formType: 0
													, title: '修改内容'
													, value: data.notes
													, formType: 2
												}, function (value, index) {
													console.log(data);
													obj.update({
														notes: value
													});
													data.notes = value;
													$.ajax({
														url: "noticeController/update",
														type: "post",
														data: { "notes": data.notes, "id": data.id },
														dataType: "json",
														success: function (data) {

														}
													});
													layer.close(index);
												});
											} else if (obj.event === 'del') {
												layer.confirm('真的删除行么', function (index) {
													obj.del();
													layer.close(index);
												});
											}
										});
									} else {
										layer.msg("还没有选择班级！");
									}
								});
							});

						</script>
					</c:if>

				</body>

				</html>