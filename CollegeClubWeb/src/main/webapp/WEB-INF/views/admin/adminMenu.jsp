<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
		<nav class="navbar navbar-default" role="navigation">
			<div class="container-fluid">
				<div class="navbar-header">
					<a class="navbar-brand" href="#">管理员菜单</a>
				</div>
				<div>
					<ul class="nav navbar-nav">
						<li><a href="/page/admin/usermanage?page=1">用户管理</a></li>
						<li><a href="/page/admin/clubmanage?page=1">社团管理</a></li>
						<li><a href="/club/clubpass?page=1">社团申请管理</a></li>
						<li><a class="dropdown-toggle" data-toggle="dropdown">社团活动管理
								<b class="caret"></b>
						</a>
							<ul class="dropdown-menu" role="menu">
								<li><a href="/page/admin/clubact?type=0&page=1">可参与社团活动管理</a></li>
								<li><a href="/page/admin/clubact?type=1&page=1">过期的社团活动管理</a></li>
						    </ul>			
						</li>
						<li><a href="/page/admin/clubact?type=-1&page=1">社团活动申请管理</a></li>
					</ul>
				</div>
			</div>
		</nav>