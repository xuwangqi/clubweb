<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
			<li>管理员</li>
			<li>${title}</li>
		</ol>

    <%@include file="adminMenu.jsp"%>

		<div style="margin-top: 50px; display: hidden;" role="alert"></div>
		<div class="btn-group">
			<button type="button" class="btn btn-primary" onclick="deleteon()">删除所选栏目</button>
			<button type="button" class="btn btn-success" onclick="selectall()">全选选择</button>
			<button type="button" class="btn btn-info" onclick="selectnone()">全部不选</button>
			<button type="button" class="btn btn-warning" onclick="selectunder()">反向选择</button>
			<button type="button" class="btn btn-danger" onclick="passon()">通过所选栏目</button>
		</div>
		<table class="table table-striped">
			<thead>
				<tr>
					<th>标记</th>
					<th>社团</th>
					<th>活动标题</th>
					<th class="hidden-xs hidden-sm">活动时间</th>
					<th>活动人数/规定人数</th>
					<th class="hidden-xs hidden-sm">活动地点</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${page.totalPage>0}">
					<c:forEach items="${page.products}" var="club">
						<tr>
							<td><input type="checkbox" class="checkUser"
								value="${club.caUid}"></td>
							<td>${club.clubId}/${club.clubName}</td>
							<td>${club.caTitle}</td>
                            <td class="hidden-xs hidden-sm"><fmt:formatDate value="${club.caTime}" pattern="yyyy年MM月dd日  hh:mm" /></td>
                             <td>${club.caNownumber}/${club.caNumber}</td>
                            <td class="hidden-xs hidden-sm">${club.caAddress}</td>
							<td><a href="/club/act/edit?caUid=${club.caUid}" class="btn btn-success">编辑</a>
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
						href="/page/admin/clubact?type=${type}&page=${page.currentPage-1}">&laquo;</a></li>
					<li><a href="/page/admin/clubact?type=${type}&page=1">1</a></li>
					<li><a href="#">...</a></li>
				</c:if>
				<li><a href="/page/admin/clubact?type=${type}&page=${page.currentPage}">${page.currentPage}</a></li>
				<c:if test="${page.totalPage>page.currentPage}">
					<li><a href="#">...</a></li>
					<li><a href="/page/admin/clubact?type=${type}&page=${page.totalPage}">${page.totalPage}</a></li>
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
			var confirms = window.confirm("是否删除这" + num + "个社团活动");
			if (confirms) {
				$.get("/clubact/delete?ids=" + ids, function(reslut) {
					if (reslut.status == 200) {
						alert("删除社团活动成功");
						window.location.reload();
					} else {
						alert("删除社团活动失败");
					}
				});
			}
		}
		
		var passon=function(){
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
			var confirms = window.confirm("是通过这" + num + "个社团活动");
			if (confirms) {
				$.get("/clubact/updatepass?type=0&ids=" + ids, function(reslut) {
					if (reslut.status == 200) {
						alert("社团活动通过成功");
						window.location.reload();
					} else {
						alert("社团活动通过失败");
					}
				});
			}
	}
	</script>
	<script type="text/javascript" src="${app} /js/choose_common.js"></script>
</body>
</html>