<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<title>大学生社团管理系统-社团详情</title>
<meta name="viewport"
	content="width=device-width,height=device-height,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
</head>
<body>
	<%@include file="/head.jsp"%>
	<div class="container theme-showcase" role="main">

		<ol class="breadcrumb" style="margin-top: 80px">
			<li><a href="/">首页</a></li>
			<li class="active">社团详情</li>
		</ol>
		<!-- 社团UID -->
		<input id="clubUid" type="hidden" value="${club.clubUid}" />
		<div class="container">

			<div class="page-header">
				<h1 id="clubName">${club.clubName}</h1>
			</div>
			<div class="col-md-8">
				<div class="well">${club.clubSay}</div>

				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">社团编号</h3>
					</div>
					<div class="panel-body">${club.clubId}</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">成立时间</h3>
					</div>
					<div class="panel-body">
						<fmt:formatDate value="${club.clubTime}" pattern="yyyy年MM月dd日" />
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">社长</h3>
					</div>
					<div class="panel-body">
						<c:if test="${empty loginsuit}" scope="page" var="clubuser">
					     ${club.userName}
					</c:if>
						<c:if test="${!clubuser}">
							<a href="/user/detail/${club.userId}">${club.userName}</a>
						</c:if>
					</div>

				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">社团人数/容量</h3>
					</div>
					<div class="panel-body">${club.clubNownum}/${club.clubTotalnum}</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">社团详情</h3>
					</div>
					<div class="panel-body">${club.clubDesc}</div>
				</div>
			</div>

			<div class="col-md-4">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title">社团操作</h3>
					</div>
					<div class="panel-body">
						<c:if test="${empty loginsuit}" scope="page" var="loginin">
					     请先登录!
					</c:if>
						<c:if test="${!loginin}">
							<c:if test="${empty clubbelong}" scope="page" var="clubin">			
							你还不是该社团成员
							<c:if test="${club.clubNownum<club.clubTotalnum}" scope="page"
									var="number">
									<div class="row" style="text-align: center">
										<div class="col-sm-2">
											<button class="btn btn-primary" onclick="joinclub()">加入社团</button>
										</div>
									</div>
								</c:if>
								<c:if test="${!number}">
							人数达到限制
							
							</c:if>
							</c:if>
							<c:if test="${!clubin}">
						           你已经是该社团成员
							<div class="row" style="text-align: center">
									<div class="col-sm-2">
										<button class="btn btn-primary" onclick="goclub()">查看社团成员</button>
									</div>
								</div>
							</c:if>
						</c:if>
					</div>
				</div>
				<div class="panel">
					<c:if test="${not empty club.clubLogourl}" scope="page" var="img">
						<div>
							<img src="${club.clubLogourl}"
								width="100%" alt="${club.clubName}" />
						</div>
					</c:if>
				</div>

			</div>
		</div>

	</div>
	<script type="text/javascript">
		var joinclub = function() {
			var clubUid = $("#clubUid").val();
			$.get("/clubchoose/join/" + clubUid, function(reslut) {
				alert(reslut.msg);
				window.location.reload();
			});
		};

		var goclub = function() {
			var clubUid = $("#clubUid").val();
			var clubName = $("#clubName").text();
			window.location.href = " /club/user/" + clubUid 
					+ "?page=1";
		};
	</script>
</body>
</html>