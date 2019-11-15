<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" type="text/css" href="${app }  /css/head.css">
<script type="text/javascript" src="${app} /js/jquery.min.js"></script>
<script type="text/javascript" src="${app }/js/cookie.js"
	charset="utf-8"></script>
<script type="text/javascript" src="${app }/js/jquery.cookie.js"
	charset="utf-8"></script>
<script type="text/javascript" src="${app }/js/login_ajax.js"
	charset="utf-8"></script>
<link href="${app} /css/bootstrap/bootstrap.min.css" rel="stylesheet" />
<link href="${app} /css/bootstrap/bootstrap-theme.min.css"
	rel="stylesheet" />
<script type="text/javascript" src="${app}/js/bootstrap.min.js"></script>
<style>
#loginname {
	font-size: 16px;
	color: #fff;
}

</style>
<script type="text/javascript">
	window.onload = function() {
		var _ticket = $.cookie("Club_Login");
		if (!_ticket) {
			//在未登录的情况下,在首页按下Enter键就会登录  13--enter
			$("body").keydown(function(event) {
				if (event.keyCode == 13) {
					login();
				}
			});
            $("#loginbar").show();
			return;
		}
		//当dataType类型为jsonp时，jQuery就会自动在请求链接上增加一个callback的参数
		$
				.ajax({
					url : "http://sso.collegeclub.xyz/user/query/" + _ticket,
					dataType : "jsonp",
					type : "GET",
					success : function(data) {
						if (data.status == 200) {
							var _data = JSON.parse(data.data);
							var html2 = '<input type="button" onclick="logout()" class="btn btn-success navbar-btn" value="退出"></input>';
							var html3 = '<input type="button"  class="btn btn-success navbar-btn" onclick="resetP()" value="修改密码"></input>';
							var html4 = '<input type="button"  class="btn btn-success navbar-btn" onclick="manager()" value="管理员入口"></input>';
							if (_data.userType == 1) {
								var html1 = _data.userName + "管理员  ";
								$("#loginname").html(html1);
								$("#loginbar").html(
									html2 + "  " + html3 + "  "+ html4);
							} else {
								var html1 = _data.userName + "同学  ";
								$("#loginname").html(html1);
								$("#loginbar").html(
								    html2+ "  " + html3);
							}
							var html_list1 = "<li><a href='/club/myclub'>我的社团</a></li>";
							var html_list11="<li><a href='/clubact/my?type=0&page=1'>我的活动</a></li>";
							var html_list2 = "<li><a href='/club/apply'>申请新社团</a></li>";
							var html_list3 = "<li><a href='/user/detail/"+_data.userUid+"'>我的信息</a></li>";
							$("#login-in-list").append(
									html_list1 + html_list11+html_list2 + html_list3);
							$("#loginbar").show();
						} else {
							$("#loginbar").show();
						}

					},
					error : function() {
						alert('系统异常!!');
						$("#loginbar").show();

					}
				});

	};

	var logout = function() {
		$.get("/user/logout", function(result) {
			window.location.reload();
		});

	}

	var regist = function() {
		window.location.href = "/page/regist";
	}

	var resetP = function() {
		window.location.href = "/page/reset";
	}

	var manager = function() {
		window.location.href = "/page/admin/usermanage?page=1";
	}
</script>

<nav class="navbar navbar-default navbar-fixed-top" role="navigation"
	style="background: #333;">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#example-navbar-collapse">
				<span class="sr-only"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span> <span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="/">大学生社团管理系统</a>
		</div>
		<div class="collapse navbar-collapse" id="example-navbar-collapse">
			<ul class="nav navbar-nav" id="login-in-list">
				<li><a href="/club/page?currentPage=1">全部社团</a></li>		
			</ul>

			<div class="nav navbar-nav navbar-right" id="loginbar"
				style="display: none;margin-right:2px;margin-left:2px;">
                <form class="navbar-form navbar-right" role="form"  style="margin-right:5px;margin-left:5px;">
					<div class="form-group">
						<input type="text" class="form-control" placeholder="学号"
							name="userId"> <input type="password"
							class="form-control" placeholder="密码" name="userPassword">
					</div>
					<input type="button" class="btn btn-success" value="登录"
						id="loginsubmit" />&nbsp;<input type="button"
						class="btn btn-success " value="注册" id="registsubmit"
						onclick="regist()" />
				</form>
			</div>
			<p class="navbar-text navbar-right" id="loginname" style="margin-right:2px;margin-left:2px;"></p>
		</div>




	</div>
</nav>
