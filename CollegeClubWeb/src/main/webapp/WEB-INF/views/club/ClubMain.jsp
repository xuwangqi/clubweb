<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<title>大学生社团管理系统-社团</title>
<meta name="viewport"
	content="width=device-width,height=device-height,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">

</head>
<body>
	<%@include file="/head.jsp"%>
	<div class="container theme-showcase" role="main">
		<ol class="breadcrumb" style="margin-top: 80px">
			<li><a href="/">首页</a></li>
			<li class="active">社团</li>
		</ol>
		
		<%@include file="clubMenu.jsp"%>

		<div style="margin-top: 50px; display: hidden;" role="alert"></div>
		<table class="table table-striped">
			<thead>
				<tr>
					<th>学号</th>
					<th>姓名</th>
					<th>班级</th>
					<th class="hidden-xs hidden-sm">加入时间</th>
					<th class="hidden-xs hidden-sm">QQ</th>
					<th class="hidden-xs hidden-sm">短号</th>
					<th>权限</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${not empty page.products}" scope="page" var="users">
					<c:forEach items="${page.products}" var="clubc">
						<c:if test="${not empty clubc.user.userUid}" scope="page"
							var="isexist">
							<tr>
								<td>${clubc.user.userId}</td>
								<td><a href="/user/detail/${clubc.user.userUid}">${clubc.user.userName}</a></td>
								<td>${clubc.user.userClass}</td>
								<td class="hidden-xs hidden-sm"><fmt:formatDate
										value="${clubc.cuTime}" pattern="yyyy年MM月dd日" /></td>
								<td class="hidden-xs hidden-sm">${clubc.user.userQQ}</td>
								<td class="hidden-xs hidden-sm">${clubc.user.userPhonemin}</td>
								<td><c:if test="${clubc.cuType==1}" scope="page"
										var="admin">
					 管理员
					 </c:if> <c:if test="${!admin}">
					 普通成员
					 </c:if></td>
								<td><a href="/user/detail/${clubc.user.userUid}"
									class="btn btn-success">详情</a>
								<c:if test="${clubAdmin}">
									<c:if test="${!admin}">
										<a href="javascript:void(0);"
											onclick="ToleftClub('${clubc.user.userUid}'
				                           	,'${clubc.user.userName}')"
											class="btn btn-danger">删除成员</a>
									</c:if>
								</c:if>
								</td>
							</tr>
						</c:if>
					</c:forEach>
				</c:if>

			</tbody>
		</table>
		<div class="col-center-block">
			<ul class="pager">

				<c:if test="${page.currentPage>1 }">
					<li><a
						href="/club/user/${club.clubUid}?page=${page.currentPage-1}">&laquo;</a></li>
					<li><a
						href="/club/user/${club.clubUid}?page=1">1</a></li>
					<li><a href="#">...</a></li>
				</c:if>
				<li><a href="#">${page.currentPage}</a></li>
				<c:if test="${page.totalPage>page.currentPage}">
					<li><a href="#">...</a></li>
					<li><a
						href="/club/user/${club.clubUid}?page=${page.totalPage}">${page.totalPage}</a></li>
					<li><a
						href="/club/user/${club.clubUid}?page=${page.currentPage+1}">&raquo;</a></li>
				</c:if>
			</ul>
		</div>

	</div>
	<input type="hidden" id="clubUid" value="${club.clubUid}" />
	<script type="text/javascript" src="${app}/js/club_common.js"></script>
</body>
</html>