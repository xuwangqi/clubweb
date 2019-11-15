<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<title>大学生社团管理系统-社团活动详情</title>
<meta name="viewport"
	content="width=device-width,height=device-height,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
</head>
<body>
	<%@include file="/head.jsp"%>
	<div class="container theme-showcase" role="main">

		<ol class="breadcrumb" style="margin-top: 80px">
			<li><a href="/">首页</a></li>
			<li><a href="/club/detail/${clubact.clubUid}">${clubact.clubName}社团</a></li>
			<li><a href="#">活动详情</a></li>
		</ol>
		<!-- 社团UID -->
		<input id="caClub" type="hidden" value="${clubact.caUid}" />
		<div class="container">
			<div class="col-md-8">
				<div class="well">
					<h3>${clubact.caTitle}<h3>
				</div>
				<div class="col-md-4"></div>


				<c:if test="${clubact.caType == 1}" var="acttypeq1" scope="page">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">活动评分</h3>
						</div>
						<div class="panel-body">

							<input id="input-club" type="number" class="rating" min=0 max=10
								value="${clubact.caScore}" step=1 data-size="md" readonly
								data-show-caption="false">
						</div>
					</div>
				</c:if>


				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">活动内容</h3>
					</div>
					<div class="panel-body">${clubact.caBody}</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">活动时间</h3>
					</div>
					<div class="panel-body">
						<fmt:formatDate value="${clubact.caTime}"
							pattern="yyyy年MM月dd日  hh:mm" />
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">社团</h3>
					</div>
					<div class="panel-body">${clubact.clubId}/${clubact.clubName}</div>

				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">活动人数/容量</h3>
					</div>
					<div class="panel-body">${clubact.caNownumber}/${clubact.caNumber}</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">活动地点</h3>
					</div>
					<div class="panel-body">${clubact.caAddress}</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">活动文件</h3>
					</div>
					<div class="panel-body">
						<a href="${clubact.caFile}">活动文件</a>
					</div>
				</div>
			</div>

			<div class="col-md-4">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title">社团活动操作</h3>
					</div>
					<div class="panel-body">
						<div class="row" style="text-align: center">

							<c:if test="${not empty canjoin}" var="canjoins" scope="page">
								<c:if test="${empty joined}" var="joinin" scope="page">
									<c:if test="${clubact.caNownumber < clubact.caNumber}"
										var="loginnum" scope="page">
										<button class="btn btn-primary"
											onclick="joinAct('${clubact.caUid}')">加入活动</button>
									</c:if>
									<c:if test="${!loginnum}">
									 活动人数超过限制
									</c:if>

								</c:if>
								<c:if test="${!joinin}">
									<button class="btn btn-primary"
										onclick="leftAct('${clubact.caUid}')">退出活动</button>
								</c:if>
							</c:if>
							<c:if test="${clubact.caType == 1}" var="pfen" scope="page">
								<a class="btn btn-primary"
								 href="/club/act/query/user/pf?caUid=${clubact.caUid}&page=1">查看评论和评分</a>
							</c:if>
							<button class="btn btn-primary" onclick="reto()">返回</button>
                            <a href="/club/user/${clubact.clubUid}?page=1"" class="btn btn-primary" >返回社团</a>
						</div>
					</div>
				</div>
				<c:if test="${clubact.caType == 0 || clubact.caType == 2}"
					var="acttype02" scope="page">
					<c:if test="${not empty adminLogin}" var="adminLogin" scope="page">
						<div class="panel panel-primary">
							<div class="panel-heading">
								<h3 class="panel-title">社团管理员菜单</h3>
							</div>
							<div class="panel-body">
								<div class="row" style="text-align: center">
									<c:if test="${clubact.caType == 0}" scope="page" var="caType0">
										<button class="btn btn-primary"
											onclick="CloseAct('${clubact.caUid}')">活动关闭加入</button>
									</c:if>
									<c:if test="${clubact.caType == 2}" scope="page" var="caType0">
										<a class="btn btn-primary"
											href="/club/act/commit/score?caUid=${clubact.caUid}">活动结束并结算</a>
									</c:if>
								</div>
							</div>
						</div>
					</c:if>
				</c:if>

				<!-- 评分和评论 -->
				<c:if test="${not empty joined}" var="pfjoinin" scope="page">
					<c:if test="${clubact.caType == 1}" var="acttype1" scope="page">

						<div class="panel panel-primary">
							<div class="panel-heading">
								<h3 class="panel-title">评论和评分</h3>
							</div>
							<div class="panel-body">
								<div class="row" style="text-align: center">
									<input id="input-id" type="number" class="rating" min=0 max=10
										step=1 data-size="md" data-show-caption="false"
										<c:if test="${clubuser.caScore >= 0}" var="star_pf" scope="page">
										value="${clubuser.caScore}" readonly
										</c:if>>
								</div>
								<div class="row" style="text-align: center">
								<c:if test="${!star_pf}">
									<textarea rows="5" cols="10" class="form-control" id="pfmsg"
										></textarea>
										</c:if>
									<c:if test="${star_pf}">
									${clubuser.cauMsg}
									</c:if>
								</div>
								<c:if test="${!star_pf}">
									<div class="row" style="text-align: center">
										<a href="javascript:void(0)" class="btn btn-primary"
											onclick="sendPF()">评价提交</a>
									</div>
								</c:if>
								
								<c:if test="${star_pf}">
									<div class="row" style="text-align: center">
										你的评分和评价已经完成
									</div>
								</c:if>
							</div>
						</div>
					</c:if>
				</c:if>

				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title">加入活动名单</h3>
					</div>
					<div class="panel-body">
						<div class="row" style="text-align: center">
							<c:forEach items="${clubact.clubuser}" var="user">
								<a href="/user/detail/${user.userUid}">${user.userName}</a>
							</c:forEach>
						</div>
					</div>
				</div>



			</div>
		</div>

	</div>
	<script type="text/javascript" src="${app}/js/club_common.js">
		
	</script>
	<link href="${app} /css/bootstrap/star-rating.min.css" media="all"
		rel="stylesheet" type="text/css" />
	<script src="${app} /js/star-rating.min.js" type="text/javascript"></script>
	<script type="text/javascript">
		$("#input-id").rating({
			"showClear" : false
		});

		$("#input-club").rating({
			"showClear" : false
		});
		var sendPF = function() {
			var caUid = $("#caClub").val();
			var number = $("#input-id").val();
			var msg = $("#pfmsg").val();
			$.ajax({
				url : "/club/act/pf",
				type : "post",
				data : {
					"caUid" : caUid,
					"number" : number,
					"msg" : msg
				},
				dataType : "json",
				success : function(result) {
					if (result.status == 200) {
						alert("评分成功");
						window.location.reload();
					} else {
						alert("评分失败");
					}
				},
				error : function() {
					alert("系统错误");
				}
			});
		}
	</script>
</body>
</html>