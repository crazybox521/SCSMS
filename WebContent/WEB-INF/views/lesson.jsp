<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" %> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %> <%@ taglib prefix="fn"
uri="http://java.sun.com/jsp/jstl/functions" %> <% String
path=request.getContextPath(); String
basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
  <base href="<%=basePath%>" />
  <meta charset="UTF-8" />
  <title>课程</title>
  <script src="js/jquery.min.js"></script>
  <link rel="stylesheet" href="./layui/css/layui.css" />
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

    .display {
      position: absolute;
      top: 20px;
      right: 150px;
      bottom: 0px;
      left: 150px;
      height: 100%;
      margin: 0px auto;
      min-width: 1000px;
    }

    .layui-card {
      height: 300px;
    }

    .layui-card-header {
      color: #000;
      font-weight: bold;
      font-size: 24px;
    }

    #loginBox {
      display: none;
    }

    .btn {
      position: absolute;
      left: 20px;
      bottom: 20px;
    }
  </style>
</head>

<body>
  <div class="container">
    <div class="display">
      <div>
        <span class="layui-breadcrumb" id="daohang" lay-separator="/"> </span>
      </div>
      <div class="layui-row layui-col-space20" id="lessonlist"></div>
      <br />
      <br />
    </div>
    <div id="loginBox">
      <form
        class="layui-form layui-form-pane"
        action="login"
        method="post"
        target="_parent"
      >
        <div class="layui-form-item">
          <label class="layui-form-label">用户名</label>
          <div class="layui-input-block">
            <input
              type="text"
              name="userName"
              required
              lay-verify="required"
              placeholder="请输入用户名"
              autocomplete="off"
              class="layui-input"
            />
          </div>
        </div>
        <div class="layui-form-item">
          <label class="layui-form-label">密码</label>
          <div class="layui-input-block">
            <input
              type="password"
              name="password"
              required
              lay-verify="required"
              placeholder="请输入密码"
              autocomplete="off"
              class="layui-input"
            />
          </div>
        </div>
        <div class="layui-form-item">
          <div class="layui-input-block">
            <button
              class="layui-btn layui-btn-danger"
              lay-submit
              lay-filter="form1"
            >
              登录
            </button>
            <button
              type="reset"
              class="layui-btn layui-btn-primary layui-border-red"
            >
              重置
            </button>
          </div>
        </div>
      </form>
    </div>
  </div>
  <script type="text/javascript">
    layui.use("element", function () {
      var element = layui.element;
    });
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
            str +=
              "<div class='layui-col-md4' onclick='showGrade(" +
              item.id +
              ");'><div class='layui-panel'><div class='layui-card'>";
            str +=
              "<div class='layui-card-header' id='lesson" +
              item.id +
              "'>" +
              item.lessonName +
              "</div>";
            str += "<div class='layui-card-body'>";
            str += "学时:" + item.hours + "<br>";
            str += "说明:" + item.notes + "<br></div>";
            str +=
              "<div style='color:red;font-size:24px;position:absolute;right:20px;bottom:20px;font-weight:bold;'>¥" +
              item.price +
              "</div>";
            str += "</div></div></div>";
          });
          str += "</div>";

          $("#lessonlist").html(str);
        },
      });
    }

    function showGrade(id) {
      var lessonName = $("#lesson" + id).text();
      console.log(lessonName);
      var str2 =
        "<a href='lesson'>课程中心</a><span>/</span><a><cite>" +
        lessonName +
        "</cite></a>";
      $("#daohang").html(str2);
      $.ajax({
        url: "gradeController/gradelist",
        type: "post",
        data: { id: id },
        dataType: "json",
        success: function (data) {
          if (data.msg == "succes") {
            var str = "<div class='layui-row layui-col-space20'>";
            var str2 = "";
            $.each(data.data, function (i, item) {
              str +=
                "<div class='layui-col-md4' ><div class='layui-panel'><div class='layui-card'>";
              str +=
                "<div class='layui-card-header'>" + item.gradeName + "</div>";
              str += "<div class='layui-card-body'>";
              str += "授课老师:" + item.teacher.name + "<br>";
              str += "上课教室:" + item.room.roomName + "<br>";
              str += "地点:" + item.room.location + "<br>";
              str +=
                "上课时间:" + item.starttime + "至" + item.endtime + "<br>";
              str += "班级详情:" + item.notes + "</div>";
              str +=
                "<div class='btn'><button type='button' class='layui-btn layui-btn-danger' onclick='ShowLoginView();'>登录报名班级</button></div>";
              str +=
                "<div style='color:red;font-size:24px;position:absolute;right:20px;bottom:20px;font-weight:bold;'>¥" +
                item.lesson.price +
                "</div>";
              str += "</div></div></div>";
            });
            str += "</div>";
            $("#lessonlist").empty();
            $("#lessonlist").html(str);
          } else {
            $("#lessonlist").empty();
            layer.msg("当前时段该课程没有可报名班级!");
            $("#lessonlist").html("<h2>当前时段该课程没有可报名班级!</h2>");
          }
        },
      });
    }
    function ShowLoginView() {
      layer.open({
        type: 1,
        title: "登录",
        area: ["395px", "300px"],
        content: $("#loginBox"),
      });
    }
  </script>
</body>
</html>
