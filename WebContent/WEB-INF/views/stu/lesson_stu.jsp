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
					<title>课程</title>
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
							bottom: -300px;
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
							top: 20px;
							right: 150px;
							bottom: -100px;
							left: 150px;
							margin: 0px auto;
							min-width: 1000px;

						}

						.layui-card {
							height: 300px;
						}

						.layui-card:hover {
							-webkit-transform: translateY(-3px);
							-ms-transform: translateY(-3px);
							transform: translateY(-3px);
							-webkit-box-shadow: 0 0 6px #999;
							box-shadow: 0 0 6px #999;
							-webkit-transition: all .5s ease-out;
							transition: all .5s ease-out;
						}

						.layui-card-header {
							color: #000;
							font-weight: bold;
							font-size: 24px;
						}

						.btn {
							position: absolute;
							left: 50px;
							bottom: 20px;
						}

						#checkBox {
							display: none;
						}
					</style>
				</head>

				<body>
					<c:if test="${empty sessionScope.student}">
						<div class="error">
							<h1>登录失效，点击返回主页重新登陆
								<a href="index" target="_parent"
									class="layui-btn layui-btn-primary layui-border-red">返回主页</a>
							</h1>
						</div>
					</c:if>
					<c:if test="${not empty sessionScope.student}">
						<div class="container">
							<div class="display">
								<div style="margin-bottom:10px;">
									<span class="layui-breadcrumb" id="daohang" lay-separator="/">

									</span>
								</div>
								<div id="list"></div>
							</div>
						</div>
						<div id="checkBox">
							<form class="layui-form layui-form-pane" action="payController/alipay" method="post"
								lay-filter="form1" target="_parent">
								<div class="layui-form-item">
									<label class="layui-form-label">班级编号</label>
									<div class="layui-input-block">
										<input type="number" name="id" required disabled autocomplete="off"
											class="layui-input">
									</div>
								</div>
								<div class="layui-form-item">
									<label class="layui-form-label">班级名</label>
									<div class="layui-input-block">
										<input type="text" name="gradeName" required disabled autocomplete="off"
											class="layui-input">
									</div>
								</div>
								<div class="layui-form-item">
									<label class="layui-form-label">课程名</label>
									<div class="layui-input-block">
										<input type="text" name="lessonName" required disabled autocomplete="off"
											class="layui-input">
									</div>

								</div>
								<div class="layui-form-item">
									<label class="layui-form-label">价格</label>
									<div class="layui-input-block">
										<input type="text" name="price" required disabled autocomplete="off"
											class="layui-input">
									</div>

								</div>
								<div class="layui-form-item">
									<div class="layui-input-block">
										<button class="layui-btn layui-btn-danger" lay-submit
											lay-filter="form1">确认购买报名</button>
									</div>
								</div>
							</form>
						</div>
						<script type="text/javascript">
							$(function () {
								list();
							});

							function list() {
								$.ajax({
									url: "lessonController/lessonlist",
									type: "post",
									data: {},
									dataType: "json",
									success: function (data) {
										var str = "<div class='layui-row layui-col-space20'>";
										$.each(data.data, function (i, item) {
											str += "<div class='layui-col-md4' onclick='showGrade(" + item.id + ");'><div class='layui-panel'><div class='layui-card'>";
											str += "<div class='layui-card-header' id='lesson" + item.id + "'>" + item.lessonName + "</div>";
											str += "<div class='layui-card-body'>";
											str += "学时:" + item.hours + "<br>";
											str += "说明:" + item.notes + "<br></div>";
											str += "<div style='color:red;font-size:24px;position:absolute;right:20px;bottom:20px;font-weight:bold;'>¥" + item.price + "</div>";
											str += "</div></div></div>";
										});
										str += "</div>"

										$("#list").html(str);
									}
								});
							}

							function showGrade(id) {
								var lessonName = $("#lesson" + id).text();
								console.log(lessonName);
								var str2 = "<a href='lesson'>课程中心</a><span>/</span><a><cite>" + lessonName + "</cite></a>"
								$("#daohang").html(str2);
								$.ajax({
									url: "gradeController/gradelist",
									type: "post",
									data: { "id": id },
									dataType: "json",
									success: function (data) {
										if (data.msg == "succes") {
											var str = "<div class='layui-row layui-col-space20'>";
											$.each(data.data, function (i, item) {
												str += "<div class='layui-col-md4' ><div class='layui-panel'><div class='layui-card'>";
												str += "<div class='layui-card-header'>" + item.gradeName + "</div>";
												str += "<div class='layui-card-body'>";
												str += "授课老师:" + item.teacher.name + "<br>";
												str += "上课教室:" + item.room.roomName + "<br>";
												str += "地点:" + item.room.location + "<br>";
												str += "上课时间:" + item.starttime + "至" + item.endtime + "<br>";
												str += "班级详情:" + item.notes + "</div>";
												str += "<div class='btn'>";
												str += "<button type='button' class='layui-btn layui-btn-danger' onclick='chooseLesson(" + item.id + ");'>生成订单报名</button></div>";
												str += "<div style='color:red;font-size:24px;position:absolute;right:20px;bottom:20px;font-weight:bold;'>¥" + item.lesson.price + "</div>";
												str += "</div></div></div>";
											});
											str += "</div>"
											$("#list").empty();
											$("#list").html(str);

										}

										else {
											$("#list").empty();
											layer.msg("当前时段该课程没有可报名班级!");
											$("#list").html("<h2>当前时段该课程没有可报名班级!</h2>");
										}
									}
								});

							}
							layui.use('form', function () {
								var form = layui.form;
								form.on('submit(form1)', function (data) {
									var formdata = data.field;
									$.ajax({
										type: "POST",
										url: "payController/alipay",
										data: { "id": formdata.id, "price": formdata.price },
										dataType: "JSON",
										success: function (data) {
										}
									});

								});
							});


							function chooseLesson(id) {
								layer.open({
									type: 1,
									title: "确认信息",
									area: ["480px", "400px"],
									content: $("#checkBox"),
								});
								layer.load();
								setTimeout(function () {
									layer.closeAll('loading');
								}, 200);
								$.ajax({
									url: "gradeController/info",
									type: "post",
									data: { "id": id },
									dataType: "json",
									success: function (data) {
										console.log(data);
										var form = layui.form;
										form.val("form1", {
											"id": data.id
											, "lessonName": data.lesson.lessonName
											, "price": data.lesson.price
											, "gradeName": data.gradeName
										});
										$.ajax({
											url: "payController/insert",
											type: "post",
											data: { "id": data.id },
											dataType: "json",
											success: function (data) {


											}
										});
										form.render();

									}
								});

							}

						</script>
					</c:if>
				</body>

				</html>