<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>大学生社团管理系统-用户编辑</title>
<meta name="viewport"
	content="width=device-width,height=device-height,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
</head>
<body>
	<%@include file="/head.jsp"%>
	<div class="container theme-showcase" role="main">

		<ol class="breadcrumb" style="margin-top: 80px">
			<li><a href="/">首页</a></li>
			<li>用户编辑</li>
		</ol>
		<!-- UID -->
		<input id="userUid" type="hidden" value="${user.userUid}" />
		<div class="container">
		
		<div class="col-md-8">
			<form>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">学号</h3>
					</div>
					<div class="panel-body">
						<span name="userId">${user.userId}</span>
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">姓名</h3>
					</div>
					<div class="panel-body">
						<input type="text" class="form-control" placeholder="请输入姓名"
							value="${user.userName}" name="userName" />
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">性别</h3>
					</div>
					<div class="panel-body">
						<select name="userGender" class="form-control">
						<c:if test="${user.userGender=='man'}"  scope="page" var="sex">
							<option value="man" selected>男</option>
							<option value="unman">女</option>
						</c:if>
							<c:if test="${!sex}"  scope="page" var="sex">
							<option value="man">男</option>
							<option value="unman" selected>女</option>
						</c:if>
						</select>

					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">班级</h3>
					</div>
					<div class="panel-body">
						<input type="text" class="form-control" placeholder="请输入班级"
							value="${user.userClass}" name="userClass" />
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">Email邮箱</h3>
					</div>
					<div class="panel-body">
						<input type="text" class="form-control" placeholder="请输入邮箱"
							value="${user.userEmail}" name="userEmail" />
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">QQ号</h3>
					</div>
					<div class="panel-body">
						<input type="text" class="form-control" placeholder="请输入QQ"
							value="${user.userQQ}" name="userQQ" />
					</div>
				</div>


				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">手机号</h3>
					</div>
					<div class="panel-body">
						<input type="text" class="form-control" placeholder="请输入手机号"
							value="${user.userPhone}" name="userPhone" />
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">手机短号</h3>
					</div>
					<div class="panel-body">
						<input type="text" class="form-control" placeholder="请输入手机短号"
							value="${user.userPhonemin}" name="userPhonemin" />
					</div>
				</div>

				<c:if test="${not empty adminin}" scope="page" var="admin">

					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">学分</h3>
						</div>
						<div class="panel-body">
						<input type="text" class="form-control" placeholder="请输入学分"
							value="${user.userGrade}" name="userGrade" />
					    </div>
					</div>


					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">用户类型</h3>
						</div>
						<div class="panel-body">
						<select name="userType" class="form-control">
						<c:if test="${user.userType==0}"  scope="page" var="type">
							<option value="0" selected>普通用户</option>
							<option value="1">管理员</option>
						</c:if>
							<c:if test="${!type}">
							<option value="0">普通用户</option>
							<option value="1" selected>管理员</option>
						</c:if>
						</select>
						
						</div>
					</div>

					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">登录密码修改</h3>
						</div>
						<div class="panel-body">
						<input type="text" class="form-control" placeholder="请输入密码"
							 name="userPassword" />
						</div>
					</div>
				</c:if>

				<c:if test="${!admin}">
					<input type="hidden" value="${user.userGrade}" name="userGrade" />
					<input type="hidden" value="${user.userType}" name="userType" />
				</c:if>
			</form>


		</div>

		<div class="col-md-4">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h3 class="panel-title">用户操作</h3>
				</div>
				<div class="panel-body">
					<div class="row" style="text-align: center">
						<div class="row">
						    <button class="btn btn-primary" onClick="resetUser()">提交信息</button>
							<button class="btn btn-primary" onClick="reto()">返回</button>
						</div>
					</div>
				</div>
			</div>

		</div>
	</div>

	</div>
	<script type="text/javascript">
		var reto = function() {
			history.go(-1);
		};
		$("body").keydown(function(event) {
				if (event.keyCode == 13) {
					resetUser();
				}
			})
						
	</script>
	<script type="text/javascript" src="${app}/js/resetUser.js"></script>
</body>
</html>