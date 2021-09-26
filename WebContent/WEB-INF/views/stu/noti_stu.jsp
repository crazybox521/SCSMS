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
							background-color: #fff;
						}

						.container {
							position: absolute;
							top: 0px;
							right: 0px;
							bottom: 0px;
							left: 0px;
							min-width: 1200px;
							background-color: #F2F2F2;
							width: 100%;
						}

						.display {
							position: absolute;
							top: 0px;
							right: 0px;
							bottom: 0px;
							left: 0px;
							margin: 0px auto;
							padding-left: 20%;
							padding-right: 20%;

						}

						.content {
							position: absolute;
							top: 0px;
							right: 150px;
							bottom: 0px;
							left: 150px;
							margin: 15px auto;
							padding-left: 10%;
							padding-right: 10%;
							height: 100%;
							background-color: #fff;
							border: 1px solid #dddddd;
							box-shadow: 0px 0px 7px 6px #e2e2e2;

						}

						.layui-form-item .layui-input-block input {
							width: 250px;
						}

						.title {
							line-height: 100px;
							font-size: 32px;
							font-weight: bold
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
								<div class="content">
									<div class="title">通知</div>
									<button class="layui-btn layui-btn-primary" style="margin:10px;"
										onclick="list1()">风格1</button>
									<button class="layui-btn layui-btn-primary" style="margin:10px;"
										onclick="list()">风格2</button>
									<div id="list">
									</div>
								</div>
							</div>
						</div>

						<script type="text/javascript">

							$(function () {
								list1();
							});
							layui.use('element', function () {
								var element = layui.element;

							});

							function list() {
								$.ajax({
									url: "noticeController/stu_noticelist",
									type: "post",
									data: {},
									dataType: "json",
									success: function (data) {
										if (data.msg == "succes") {
											var str = "<div class='layui-collapse' lay-accordion>";
											$.each(data.data, function (i, item) {
												str += "<div class='layui-colla-item'>";
												str += "<h2 class='layui-colla-title'>" + item.grade.gradeName + "&nbsp; &nbsp; &nbsp; &nbsp; " + item.time + "</h2>";
												str += "<div class='layui-colla-content layui-show'>";
												str += "" + item.notes + "<br>";
												str += "</div></div>";
											});
											str += "</div>"

											$("#list").html(str);
											layui.use('element', function () {
												var element = layui.element;
												element.render('collapse');
											});
										} else {
											$("#list").html("<h2>没有通知呢！</h2>");
										}
									}
								});
							}

							function list1() {
								$.ajax({
									url: "noticeController/stu_noticelist",
									type: "post",
									data: {},
									dataType: "json",
									success: function (data) {
										if (data.msg == "succes") {
											var str = "<div class='layui-row layui-col-space15'>";
											$.each(data.data, function (i, item) {
												str += "<div class='layui-col-md12' ><div class='layui-panel'><div class='layui-card'>";
												str += "<div class='layui-card-header'>" + item.grade.gradeName + "&nbsp; &nbsp; &nbsp; &nbsp; " + item.time + "</div>";
												str += "<div class='layui-card-body'>";
												str += "" + item.notes + "<br>";
												str += "</div></div></div></div>";
											});
											str += "</div>"

											$("#list").html(str);
										} else {
											$("#list").html("<h2>没有通知呢！</h2>");
										}

									}
								});
							}


						</script>
					</c:if>

				</body>

				</html>