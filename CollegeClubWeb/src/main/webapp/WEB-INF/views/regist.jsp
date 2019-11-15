<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>大学生社团管理系统-注册</title>
<meta name="viewport" content="width=device-width,height=device-height,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<link rel="stylesheet" media="screen" href="${app}/css/style_regist.css">
<link rel="stylesheet" type="text/css" href="${app}/css/reset.css" />
</head>
<body>


	<div id="particles-js">
		<div class="login" id="regist1">
			<div class="login-top">欢迎注册</div>
			<form>

				<div class="login-center clearfix">
					<div class="login-center-img">
						<img src="${app}/img/name.png" />
					</div>
					<div class="login-center-input">
						<input type="text" id="userId" name="userId" value=""
							placeholder="请输入您的学号" onfocus="this.placeholder=''"
							onblur="this.placeholder='请输入您的学号'" />
						<div class="login-center-input-text" id="error_userId">学号</div>
					</div>
				</div>

				<div class="login-center clearfix">
					<div class="login-center-img">
						<img src="${app}/img/password.png" />
					</div>
					<div class="login-center-input">
						<input type="password" id="password" name="password" value=""
							placeholder="请输入您的密码" onfocus="this.placeholder=''"
							onblur="this.placeholder='请输入您的密码'" />
						<div class="login-center-input-text" id="error_password">密码</div>
					</div>
				</div>

				<div class="login-center clearfix">
					<div class="login-center-img">
						<img src="${app}/img/password.png" />
					</div>
					<div class="login-center-input">
						<input type="password" id="password2" name="password2" value=""
							placeholder="请再次输入您的密码" onfocus="this.placeholder=''"
							onblur="this.placeholder='请再次输入您的密码'" />
						<div class="login-center-input-text" id="error_password2">确认密码</div>
					</div>
				</div>

				<div class="login-center clearfix">
					<div class="login-center-img">
						<img src="${app}/img/name.png" />
					</div>
					<div class="login-center-input">
						<input type="text" id="userName" name="userName" value=""
							placeholder="请输入真实姓名 " onfocus="this.placeholder=''"
							onblur="this.placeholder='请输入您的真实姓名'" />
						<div class="login-center-input-text" id="error_nickname">真实姓名</div>
					</div>
				</div>

				<div class="login-center clearfix">
					<div class="login-center-img">
						<img src="${app}/img/name.png" />
					</div>
					<div class="login-center-input">
						<select id="userGender" name="userGender"
							style="width: 100%; height: 30px;">
							<option name="userGender" value="man" selected="selected">男</option>
							<option name="userGender" value="unman">女</option>
						</select>
					</div>
				</div>

				<div class="login-center clearfix">
					<div class="login-center-img">
						<img src="${app}/img/phone.png" />
					</div>
					<div class="login-center-input">
						<input type="text" id="userClass" name="userClass" value=""
							placeholder="请输入您的班级" onfocus="this.placeholder=''"
							onblur="this.placeholder='请输入您的班级'" />
						<div class="login-center-input-text" id="error_userClass">班级</div>
					</div>
				</div>

				 <div class="login-button" id="next-submit">下一页</div>
			</form>
		</div>

		<div class="login" id="regist2" >
			<form>
				<div class="login-top">联系方式</div>
                
                <div class="login-center clearfix">
					<div class="login-center-img">
						<img src="${app}/img/email.png" />
					</div>
					<div class="login-center-input">
						<input type="text" id="QQ" name="QQ" value=""
							placeholder="请输入您的QQ" onfocus="this.placeholder=''"
							onblur="this.placeholder='请输入您的QQ'" />
						<div class="login-center-input-text" id="error_QQ">QQ</div>
					</div>
				</div>


				<div class="login-center clearfix">
					<div class="login-center-img">
						<img src="${app}/img/phone.png" />
					</div>
					<div class="login-center-input">
						<input type="text" id="userPhone" name="userPhone" value=""
							placeholder="请输入您的手机号码" onfocus="this.placeholder=''"
							onblur="this.placeholder='请输入您的手机号码'" />
						<div class="login-center-input-text" id="error_userPhone">电话</div>
					</div>
				</div>
				<div class="login-center clearfix">
					<div class="login-center-img">
						<img src="${app}/img/phone.png" />
					</div>
					<div class="login-center-input">
						<input type="text" id="userPhonemin" name="userPhonemin" value=""
							placeholder="请输入您的手机短号" onfocus="this.placeholder=''"
							onblur="this.placeholder='请输入您的手机短号'" />
						<div class="login-center-input-text" id="error_userPhonemin">手机短号(可选)</div>
					</div>
				</div>
				<div class="login-center clearfix">
					<div class="login-center-img">
						<img src="${app}/img/email.png" />
					</div>
					<div class="login-center-input">
						<input type="text" id="userEmail" name="userEmail" value=""
							placeholder="请输入您的邮箱" onfocus="this.placeholder=''"
							onblur="this.placeholder='请输入您的邮箱'" />
						<div class="login-center-input-text" id="error_userEmail">邮箱</div>
					</div>
				</div>			

                <div class="login-button" id="before-submit">上一页</div>
				<div class="login-button" id="regist-submit">注册</div>
			</form>
		</div>




		<div class="sk-rotating-plane"></div>
	</div>

	<script src="${app}/js/particles.min.js"></script>
	<script src="${app}/js/app.js"></script>
	<script type="text/javascript" src="${app}/js/jquery-1.2.6.min.js"
		charset="utf-8"></script>
	<script type="text/javascript" src="${app}/js/cookie.js"></script>
	<script type="text/javascript" src="${app}/js/regist_ajax.js"></script>
</body>
</html>