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
<title>缴费管理</title>
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
		background-color: #fff;
		width: 100%;
	}

	.content {
		margin: 5px auto;
		width: 100%;
		min-width: 800px;
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
				搜索商品订单号：
				<div class="layui-inline layui-form">
					<input class="layui-input" name="payid" id="demoReload" autocomplete="off"
						lay-verify="number">
				</div>
				<button class="layui-btn" data-type="reload"><i class="layui-icon">&#xe615;</i>
					搜索</button>
			</div>

			<table class="layui-hide" id="test" lay-filter="test"></table>

		</div>
	</div>
	<script type="text/html" id="stateTpl">
{{#  if(d.state === '未付款'){ }}
<span style="color: #FF5722;">{{ d.state }}</span>
{{#  } else { }}
{{ d.state }}
{{#  } }}
</script>

	<script type="text/html" id="barDemo">
<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除订单</a>
</script>

	<script type="text/javascript">


		layui.use('table', function () {
			var table = layui.table;

			table.render({
				elem: '#test'
				, url: 'payController/list' //数据接口
				, method: 'post'
				, toolbar: true
				, title: '订单表'
				, cols: [[
					{ type: 'checkbox', fixed: 'left' }
					, { field: 'id', title: 'ID', fixed: 'left', unresize: true, sort: true }
					, { field: 'payid', title: '商品订单号' }
					, { field: 'alipay', title: '支付宝订单号' }
					, {
						field: 'student.name', title: '操作人姓名', templet: function (res) {
							return '<em>' + res.student.name + '</em>'
						}
					}
					, {
						field: 'grade.gradeName', title: '商品名', templet: function (res) {
							return '<em>' + res.grade.gradeName + '</em>'
						}
					}
					, { field: 'number', title: '金额' }
					, { field: 'state', title: '状态', templet: '#stateTpl' }
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


			//监听行工具事件
			table.on('tool(test)', function (obj) {
				var data = obj.data;
				console.log(obj)
				if (obj.event === 'del') {
					layer.confirm('真的删除订单么', function (index) {
						obj.del();
						layer.close(index);
					});
				}
			});
		});
	</script>
</c:if>
</body>

</html>