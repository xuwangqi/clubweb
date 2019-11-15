<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>大学生社团管理系统-管理员</title>
<meta name="viewport"
	content="width=device-width,height=device-height,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
</head>
<body>
	<%@include file="/head.jsp"%>
	<div class="container theme-showcase" role="main">
		<ol class="breadcrumb" style="margin-top: 80px">
			<li><a href="/">首页</a></li>
			<li class="active">管理员</li>
		</ol>
		
		<%@include file="adminMenu.jsp"%>
		
		<div style="margin-top: 50px; display: hidden;" role="alert"></div>
		<div class="btn-group">
			<button type="button" class="btn btn-primary" onclick="deleteon()">删除所选栏目</button>
			<button type="button" class="btn btn-success" onclick="selectall()">全选选择</button>
			<button type="button" class="btn btn-info" onclick="selectnone()">全部不选</button>
			<button type="button" class="btn btn-warning" onclick="selectunder()">反向选择</button>
		</div>
		<table class="table table-striped">
			<thead>
				<tr>
					<th>标记</th>
					<th>学号</th>
					<th>姓名</th>
					<th>性别</th>
					<th class="hidden-xs hidden-sm">班级</th>
					<th class="hidden-xs hidden-sm">QQ</th>
					<th class="hidden-xs hidden-sm">长号</th>
					<th>权限</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${page.totalPage>0}">
					<c:forEach items="${page.products}" var="user">
						<tr>
							<td><input type="checkbox" class="checkUser"
								value="${user.userUid}"></td>
							<td>${user.userId}</td>
							<td>${user.userName}</td>

							<td><c:if test="${user.userGender=='man'}" scope="page"
									var="sex">
					男
					 </c:if> <c:if test="${!sex}">
					女
					 </c:if></td>
							<td class="hidden-xs hidden-sm">${user.userClass}
							</td>
							<td class="hidden-xs hidden-sm">${user.userQQ}</td>
							<td class="hidden-xs hidden-sm">${user.userPhone}</td>
							<td><c:if test="${user.userType==1}" scope="page"
									var="admin">
					 管理员
					 </c:if> <c:if test="${!admin}">
					 普通成员
					 </c:if></td>

							<td> <a href="/user/edit/${user.userUid}" class="btn btn-success">编辑</a>
							</td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>

		</table>
		<div class="col-center-block">
			<ul class="pager">

				<c:if test="${page.currentPage > 1 }">
					<li><a
						href="/page/admin/usermanage?page=${page.currentPage-1}">&laquo;</a></li>
					<li><a href="/page/admin/usermanage?page=1">1</a></li>
					<li><a href="#">...</a></li>
				</c:if>
				<li><a href="#">${page.currentPage}</a></li>
				<c:if test="${page.totalPage>page.currentPage}">
					<li><a href="#">...</a></li>
					<li><a href="/page/admin/usermanage?page=${page.totalPage}">${page.totalPage}</a></li>
				</c:if>
			</ul>
		</div>
	</div>
	<script type="text/javascript">
		var deleteon = function() {
			var ids = "";
			var num = 0;
			$('input:checkbox[class=checkUser]:checked').each(function(i) {
				if (0 == i) {
					ids = $(this).val();
				} else {
					ids += ("," + $(this).val());
				}
				num += 1;
			});
			if (num <= 0)
				return;
			var confirms = window.confirm("是否删除这" + num + "个用户");
			if (confirms) {
				$.get("/user/delete?ids=" + ids, function(reslut) {
					if (reslut.status == 200) {
						alert("删除用户成功");
						window.location.reload();
					} else {
						alert("删除用户失败");
					}
				});
			}
		}
	</script>
	<script type="text/javascript" src="${app} /js/choose_common.js"></script>
</body>
</html>