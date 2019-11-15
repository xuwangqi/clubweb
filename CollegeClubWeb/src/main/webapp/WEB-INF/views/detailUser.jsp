<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>大学生社团管理系统-用户详情</title>
<meta name="viewport" content="width=device-width,height=device-height,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
</head>
<body>
	<%@include file="/head.jsp"%>
	<div class="container theme-showcase" role="main">

		<ol class="breadcrumb" style="margin-top: 80px">
			<li><a href="/">首页</a></li>
			<li class="active">用户详情</li>
		</ol>
		<!-- 社团UID -->
		<input id="clubUid" type="hidden" value="${user.userUid}" />
		<div class="container">

			<div class="page-header">
				<h1>${user.userName}</h1>
			</div>
			<div class="col-md-8">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">学号</h3>
					</div>
					<div class="panel-body">
					<h3>${user.userId}</h3>
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">性别</h3>
					</div>
					<div class="panel-body">
					<c:if test="${user.userGender=='man'}" scope="page"
									var="sex">
					男
					 </c:if> <c:if test="${!sex}">
					女
					 </c:if>
					
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">班级</h3>
					</div>
					<div class="panel-body">${user.userClass}</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">Email邮箱</h3>
					</div>
					<div class="panel-body">${user.userEmail}</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">QQ号</h3>
					</div>
					<div class="panel-body">${user.userQQ}</div>
				</div>
				
					<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">Email邮箱</h3>
					</div>
					<div class="panel-body">${user.userEmail}</div>
				</div>
				
				
					<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">手机号</h3>
					</div>
					<div class="panel-body">${user.userPhone}</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">手机短号</h3>
					</div>
					<div class="panel-body">${user.userPhonemin}</div>
				</div>
				
				
					<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">学分</h3>
					</div>
					<div class="panel-body">${user.userGrade}</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">用户类型</h3>
					</div>
					<div class="panel-body">
					<c:if test="${user.userType==1}" scope="page"
									var="admin">
					 管理员
					 </c:if> <c:if test="${!admin}">
					 普通成员
					 </c:if>
					
					</div>
				</div>
			</div>

			<div class="col-md-4">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title">用户操作</h3>
					</div>
					<div class="panel-body">
						<div class="row" style="text-align: center">
									<div class="row">
										<button class="btn btn-primary" onClick="reto()">返回</button>									
										<c:if test="${not empty editin}" scope="page"
									var="editor">
										<a href="/user/edit/${user.userUid}" class="btn btn-primary" >修改信息</a>									
									</c:if>
									</div>
						</div>
					</div>
				</div>
				
			</div>
		</div>

	</div>
	<script type="text/javascript">
	var reto=function(){
	  history.go(-1)
	}
	
	</script>
</body>
</html>