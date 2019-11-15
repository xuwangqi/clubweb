<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<title>大学生社团管理系统-我的社团</title>
<meta name="viewport" content="width=device-width,height=device-height,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<style>
.col-center-block {
    float: none;
    display: block;
    margin-left: auto;
    margin-right: auto;
}
</style>

</head>
<body>
	<%@include file="/head.jsp"%>
	<div class="container theme-showcase" role="main">
		<ol class="breadcrumb" style="margin-top: 80px">
			<li><a href="/">首页</a></li>
			<li class="active">我的社团</li>
		</ol>
		<div style="margin-top: 50px; display: hidden;" role="alert"></div>
		<table class="table table-striped">
			<thead>
				<tr>
					<th>社团编号</th>
					<th>社团名称</th>
					<th class="hidden-xs hidden-sm">成立时间</th>
					<th class="hidden-xs hidden-sm">创建者</th>
					<th>人数/策划人数</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${not empty club}">
					<c:forEach items="${club}" var="club">
						<tr>
							<td>${club.clubId}</td>
							<td>${club.clubName}</td>
							<td class="hidden-xs hidden-sm"><fmt:formatDate value="${club.clubTime}" pattern="yyyy年MM月dd日" /></td>
							<td class="hidden-xs hidden-sm"><a href="/user/detail/${club.userId}">${club.userName}</a></td>
							<td>${club.clubNownum}/${club.clubTotalnum}</td>
							<td><a href="/club/user/${club.clubUid}?page=1"
								class="btn btn-success">进入社团</a>
								</td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>

		</table>
		
	</div>

</body>
</html>