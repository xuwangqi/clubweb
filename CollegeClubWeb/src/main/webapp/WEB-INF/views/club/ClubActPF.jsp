<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<title>大学生社团管理系统-社团活动评价</title>
<meta name="viewport"
	content="width=device-width,height=device-height,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
</head>
<body>
	<%@include file="/head.jsp"%>
	<div class="container theme-showcase" role="main">

		<ol class="breadcrumb" style="margin-top: 80px">
			<li><a href="/">首页</a></li>
			<li class="active">社团活动评论和评分</li>
		</ol>

		<div class="container">

			<div class="page-header">
				<h1 id="clubName">${clubact.caTitle}-活动评价和评分</h1>
			</div>
			<div class="col-md-8">
				<c:if test="${page.totalPage>0}">
					<c:forEach items="${page.products}" var="user">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h3 class="panel-title">${user.userId}/${user.userName}</h3>
							</div>
							<div class="panel-body">
								<input type="number" class="rating pf_start" min=0 max=10 step=1
									data-size="md" data-show-caption="false" data-show-clear="false" readonly
									value="${user.caScore}" />
									<h5>${user.cauMsg}</h5>
							</div>
						</div>

					</c:forEach>
				</c:if>

				<div class="col-center-block">
					<ul class="pager">
						<c:if test="${page.currentPage > 1 }">
							<li><a
								href="/club/act/query/user/pf?caUid=${clubact.caUid}&page=${page.currentPage-1}">&laquo;</a></li>
							<li><a
								href="/club/act/query/user/pf?caUid=${clubact.caUid}&page=1">1</a></li>
							<li><a href="#">...</a></li>
						</c:if>
						<li><a href="#">${page.currentPage}</a></li>
						<c:if test="${page.totalPage>page.currentPage}">
							<li><a href="#">...</a></li>
							<li><a
								href="/club/act/query/user/pf?caUid=${clubact.caUid}&page=${page.totalPage}">${page.totalPage}</a></li>
						</c:if>
					</ul>
				</div>


			</div>



			<div class="col-md-4">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title">操作</h3>
					</div>
					<div class="panel-body">
						<button class="btn btn-primary" onclick="reto()">返回</button>
					</div>
				</div>
			</div>

		</div>

	</div>
	<link href="${app} /css/bootstrap/star-rating.min.css" media="all"
		rel="stylesheet" type="text/css" />
	<script src="${app} /js/star-rating.min.js" type="text/javascript"></script>
	<script type="text/javascript" src="${app}/js/club_common.js">
		
	</script>
	<script type="text/javascript">
		$(function() {
			$(".rating .pf_start").each(function() {
				$(this).rating();
			});
		});
	</script>
</body>
</html>