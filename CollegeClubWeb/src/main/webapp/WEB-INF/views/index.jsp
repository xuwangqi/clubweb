<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>大学生社团管理系统-首页</title>
<meta name="viewport"
	content="width=device-width,height=device-height,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<style type="text/css">
body {
	padding-top: 60px;
	padding-bottom: 40px;
}

.leaderboard {
	padding: 60px;
	margin-bottom: 30px;
	background-image: url('${app}/image/homepage-bg.jpg');
	background-repeat: repeat;
	-webkit-border-radius: 6px;
	-moz-border-radius: 6px;
	border-radius: 6px;
	height: 400px;
}

.leaderboard h1 {
	font-size: 30px;
	margin-bottom: 5px;
	line-height: 1;
	letter-spacing: -1px;
	color: #FFF;
}

.leaderboard p {
	font-size: 18px;
	font-weight: 200;
	line-height: 27px;
	color: #FFF;
}
</style>
</head>

<body>

	<%@include file="/head.jsp"%>

	<div class="container">

		<div class="leaderboard">
			<h1>欢迎登陆大学生社团管理系统!</h1>
			<p>welcome to college' club manage system!</p>
		</div> 
	
	    <div class="text-center" ><h1>功能介绍</h1></div>
        <hr />
        <div class="row">
            <div class="col-md-4">
                <h2>系统须知</h2>
                <p>本系统采用了登录和注册机制，用户必须通过注册才能了解更多的内容
                此外，用户登录之后能修改系统密码，修改用户信息等功能，如果密码丢失请联系管理员QQ2713986880重置密码
                重置密码一般为123456，重置后请立即进入系统修改密码。</p>
            </div>
            <div class="col-md-4">
                <h2>社团机制</h2>
                <p>本系统采用用户申请系统，社团申请由管理员确认和修改后方能正常运行，另外申请成功的话，申请人会自动成为社团管理员和社长</p>
            </div>
            <div class="col-md-4">
                <h2>社团活动机制</h2>
                <p>注册用户可以加入社团参加活动，完成活动后即可活动社团学分，如果你是社长的话每次活动你都能获得想应得社团学分</p>
            </div>
        </div>
        <hr />
    </div>
	
	
	
</body>
</html>