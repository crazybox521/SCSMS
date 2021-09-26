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
					<title>班级</title>
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
									<div class="title">我加入的班级</div>
									<div id="list"></div>
								</div>
							</div>

						</div>
						<script type="text/javascript">
							$(function () {
								list();
							});

							function list() {
								$.ajax({
									url: "gradeController/mygradelist",
									type: "post",
									data: {},
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
												str += "状态:" + item.state + "<br>";
												str += "班级详情:" + item.notes + "</div>";
												str += "</div></div></div>";
											});
											str += "</div>"

											$("#list").html(str);
										}
										else {

											$("#list").empty();
											layer.msg("还未报名班级!");
											$("#list").html("<h2>还未报名班级!</h2>");
										}
									}
								});
							}

						</script>
					</c:if>

				</body>

				</html>