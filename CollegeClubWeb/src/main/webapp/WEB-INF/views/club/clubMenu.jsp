<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<nav class="navbar navbar-default" role="navigation">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="/club/detail/${club.clubUid}">${club.clubName}</a>
		</div>
		<div>
			<ul class="nav navbar-nav" id="club_nav">
				<li><a href="/club/user/${club.clubUid}?page=1">成员信息</a></li>
				<li><a class="dropdown-toggle" data-toggle="dropdown">社团的活动大全
						<b class="caret"></b>
				</a>
					<ul class="dropdown-menu" role="menu">
						<li><a href="/club/act/query/${club.clubUid}?type=0&page=1">可参与社团活动</a></li>
						<li><a href="/club/act/query/${club.clubUid}?type=1&page=1">过期的社团活动</a></li>
						<c:if test="${not empty clubAdmin}" var="clubAdmin" scope="page">
							<li class="divider"></li>
							<li><a href="/club/act/apply?clubUid=${club.clubUid}">申请社团活动</a></li>
							<li><a href="/club/act/query/${club.clubUid}?type=-1&page=1">查看申请的活动</a></li>
							<li><a href="/club/act/query/${club.clubUid}?type=2&page=1">查看待结算的活动</a></li>
						</c:if>
					</ul></li>
				<li><a class="dropdown-toggle" data-toggle="dropdown">我的社团活动
						<b class="caret"></b>

				</a>
					<ul class="dropdown-menu" role="menu">
						<li><a
							href="/club/act/query/myact/${club.clubUid}?type=0&page=1">待参与社团活动</a></li>
						<li><a
							href="/club/act/query/myact/${club.clubUid}?type=1&page=1">已结束的社团活动</a></li>
						<li><a
							href="/club/act/query/myact/${club.clubUid}?type=2&page=1">待结算的社团活动</a></li>
					</ul></li>
				<li><a href="/club/msg?clubUid=${club.clubUid}&page=1">社团留言</a></li>
				<c:if test="${clubAdmin}">
					<li><a class="dropdown-toggle" data-toggle="dropdown">社团管理员菜单
							<b class="caret"></b>
					</a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="/club/edit?clubUid=${club.clubUid}">编辑社团</a></li>
						</ul></li>

				</c:if>
				<c:if test="${!clubAdmin}">
					<li onclick="leftclubconfirm()"><a href="#" id="leftclub">退出社团</a></li>
				</c:if>

			</ul>
		</div>
	</div>
</nav>