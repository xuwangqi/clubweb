<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>大学生社团管理系统-重置密码</title>
<meta name="viewport" content="width=device-width,height=device-height,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<link rel="stylesheet" media="screen" href="${app}/css/style_reset.css">
<link rel="stylesheet" type="text/css" href="${app}/css/reset.css" />
</head>
<body>


	<div id="particles-js">
		<div class="login">
			<div class="login-top">修改密码</div>
			<form>

				<div class="login-center clearfix">
					<div class="login-center-img">
						<img src="${app}/img/name.png" />
					</div>
					<div class="login-center-input">
						<span id="userId" style="font-size:16px;" name="userId"></span>
					</div>
				</div>

				<div class="login-center clearfix">
					<div class="login-center-img">
						<img src="${app}/img/password.png" />
					</div>
					<div class="login-center-input">
						<input type="password" id="oldpassword" name="oldpassword" value=""
							placeholder="请输入您的旧密码" onfocus="this.placeholder=''"
							onblur="this.placeholder='请输入您的旧密码'" />
						<div class="login-center-input-text" id="error_oldpassword">旧密码</div>
					</div>
				</div>

				<div class="login-center clearfix">
					<div class="login-center-img">
						<img src="${app}/img/password.png" />
					</div>
					<div class="login-center-input">
						<input type="password" id="password" name="password" value=""
							placeholder="请输入您的新密码" onfocus="this.placeholder=''"
							onblur="this.placeholder='请输入您的新密码'" />
						<div class="login-center-input-text" id="error_password">新密码</div>
					</div>
				</div>
				<div class="login-center clearfix">
					<div class="login-center-img">
						<img src="${app}/img/password.png" />
					</div>
					<div class="login-center-input">
						<input type="password" id="password2" name="password2" value=""
							placeholder="重复输入您的新密码" onfocus="this.placeholder=''"
							onblur="this.placeholder='重复输入您的新密码'" />
						<div class="login-center-input-text" id="error_password">重复新密码</div>
					</div>
				</div>
				
				<div class="login-button" id="reset-submit">确定</div>
			</form>
		</div>


		<div class="sk-rotating-plane"></div>
	</div>

	<script src="${app}/js/particles.min.js"></script>
	<script src="${app}/js/app.js"></script>
	<script type="text/javascript" src="${app}/js/jquery-1.2.6.min.js"
		charset="utf-8"></script>
	<script type="text/javascript" src="${app}/js/cookie.js"></script>
	<script type="text/javascript" src="${app}/js/reset_ajax.js"></script>
	<script type="text/javascript" src="${app }/js/cookie.js"
	charset="utf-8"></script>
   <script type="text/javascript" src="${app }/js/jquery.cookie.js"
	charset="utf-8"></script>
	<script type="text/javascript">
	window.onload = function() {
		var _ticket = $.cookie("Club_Login");
		if (!_ticket) {
		   window.location.href = "/"
			return;
		}
		//当dataType类型为jsonp时，jQuery就会自动在请求链接上增加一个callback的参数
		$.ajax({
					url : "http://sso.collegeclub.xyz/user/query/" + _ticket,
					dataType : "jsonp",
					type : "GET",
					success : function(data) {
						if (data.status == 200) {
							var _data = JSON.parse(data.data);						
						    $("form #userId").text(_data.userId);					     
						}
					},
					error : function() {
						alert('系统异常!!');
					}
				});
	};
</script>
	
</body>


</html>