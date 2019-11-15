<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<title>大学生社团管理系统-社团留言</title>
<meta name="viewport"
	content="width=device-width,height=device-height,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
</head>
<body>
	<%@include file="/head.jsp"%>
	<div class="container theme-showcase" role="main">

		<ol class="breadcrumb" style="margin-top: 80px">
			<li><a href="/">首页</a></li>
			<li class="active">社团留言</li>
		</ol>

		<div class="container">

			<div class="page-header">
				<h1 id="clubName">${club.clubName}-留言</h1>
			</div>


			<div class="col-md-8">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">社团留言</h3>
					</div>
					<div class="panel-body">
						<input type="text" class="form-control" id="msgtitle" placeholder="标题"></input>
						<textarea rows="10" cols="10" id="msgbody" class="form-control" placeholder="内容"></textarea>
						<div class="rows">
							<button class="btn btn-primary"
								onclick="sendMsg('${club.clubUid}')">提交留言</button>
						</div>
					</div>
				</div>

				<br>


				<c:if test="${page.totalPage>0}">
					<c:forEach items="${page.products}" var="user">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h3 class="panel-title">
									<h2>${user.userName}</h2>
									<h3>${user.cmTitle}</h3>
									时间
									<fmt:formatDate value="${user.cmTime}" pattern="yyyy年MM月dd日  hh:mm:ss "
										/>
								</h3>
							</div>
							<div class="panel-body">
								<h5>${user.cmBody}</h5>
							</div>
						</div>

					</c:forEach>
				</c:if>

				<div class="col-center-block">
					<ul class="pager">
						<c:if test="${page.currentPage > 1 }">
							<li><a
								href="/club/msg?clubUid=${club.clubUid}&page=${page.currentPage-1}">&laquo;</a></li>
							<li><a href="/club/msg?clubUid=${club.clubUid}&page=1">1</a></li>
							<li><a href="#">...</a></li>
						</c:if>
						<li><a href="#">${page.currentPage}</a></li>
						<c:if test="${page.totalPage>page.currentPage}">
							<li><a href="#">...</a></li>
							<li><a
								href="/club/msg?clubUid=${club.clubUid}&page=${page.totalPage}">${page.totalPage}</a></li>
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
						<a href="/club/user/${club.clubUid}?page=1"" class="btn btn-primary" >返回社团</a>
					</div>
				</div>
			</div>

		</div>

	</div>

	<script type="text/javascript" src="${app}/js/club_common.js">
		
	</script>
	<script type="text/javascript">
	
		function sendMsg(clubUid){
		var title=$("#msgtitle").val();
		var body=$("#msgbody").val();
		  $.ajax({
			url : "/club/msg/add",
			type : "post",
			data : {
				"clubUid" : clubUid,
				"title" : title,
				"body" : body
			},
			dataType : "json",
			success : function(result) {
				if (result.status == 200) {
					alert("留言成功");
					window.location.reload();
				
				} else{
					alert("留言失败");
				}
			},
			error : function() {
				alert("留言失败");
			}
		});
		
		}
	</script>
</body>
</html>