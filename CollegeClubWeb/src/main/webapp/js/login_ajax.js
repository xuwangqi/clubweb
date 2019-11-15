//@ sourceURL=login_ajax.js
$(function() {
	// 记住用户名
	$("form input[name=userId]").val(getCookie("userId"));

	// 给form表单添加submit事件
	$("#loginsubmit").click(function() {
		return login();
	});

});

function checkNameAndPassword(userName, userPassword) {
	if (userName == "") {
		alert("用户名不能为空");
		return false;
	}
	if (userPassword == "") {
		alert("密码不能为空");
		return false;
	}
	return true;
}

function login() {
	// 获取页面数据
	var userId = $("form input[name=userId]").val();
	var userPassword = $("form input[name=userPassword]").val();
	if (checkNameAndPassword(userId, userPassword) == false) {
		return false;
	}
	
	// 发送异步请求
	$.ajax({
		url : "http://web.collegeclub.xyz/user/loginuser",
		type : "post",
		data : {
			"userId" : userId,
			"userPassword" : userPassword
		},
		dataType : "json",
		success : function(result) {
			// result是服务端返回的数据

			if (result.status == 1) {
			    addCookie("userId", userId, 5);
			    window.location.reload();
			} else {
				alert(result.msg);
			}
		},
		error : function() {
			alert("请求失败!");
		}
	});

}
