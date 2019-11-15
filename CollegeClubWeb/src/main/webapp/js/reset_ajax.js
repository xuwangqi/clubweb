$(function() {

	$("#reset-submit").click(function() {
		return reset();
	});
});

setMsg = function(name, msg, oldmsg) {
	var test = msg + "</font>";
	$("#error_" + name).html("<font color='red'>" + test);
	var input = "input[name=" + name + "]";
	$('.login-center-input ' + input).focus();

	$(".login-center-input " + input).blur(function() {
		$("#error_" + name).html(oldmsg);
	})
};


function reset() {

	var userId = $("form span[name=userId]").text();
	var oldpassword=$("form input[name=oldpassword]").val();
	var userPassword = $("form input[name=password]").val();
	var userPassword2 = $("form input[name=password2]").val();
	
	
	var flag = formObj.checkForm();
	if (flag) {
		$.ajax({
			url : "/user/reset",
			type : "post",
			data : {
				"userId" : userId,
				"oldpassword" : oldpassword,
				"userPassword" : userPassword,
			},
			dataType : "json",
			success : function(result) {
				if (result.status == 1) {
					alert(result.msg);
					$.get("/user/logout",function(reslut){
						window.location.href="/";
					});
					
				} else if (result.status == 0) {
					alert(result.msg);
				}
			},
			error : function() {
				alert("请求失败！");
			}
		});
	}

	return false;
}
var formObj = {
	checkForm : function() {
		if($("form span[name=userId]").text()==""){
			alert("出现预料之外的错误,正在退出系统");
			window.location.href = "/user/logout";
		}		
		if (!this.checkNull("oldpassword", "旧密码不能为空", "旧密码"))
			return false;		
		if (!this.checkNull("password", "新密码不能为空", "新密码"))
			return false;
		if (!this.checkNull("password2", "重复新密码不能为空", "重复新密码"))
			return false;	
		if (!this.checkPassword("password", "新旧密码密码不相同", "新密码"))
			return false;
		if (!this.checkPassword("password", "重复输入两次密码不相同", "重复新密码"))
			return false;
		
	
		
		
		return true;
	},
	checkNull : function(name, msg, oldmsg) {
		var value = $("input[name=" + name + "]").val();
		if ($.trim(value) == "") {
			setMsg(name, msg, oldmsg)
			return false;
		}
		return true;
	},
	checkPassword : function(name, msg, oldmsg) {
		var pwd = $("input[name=" + name + "]").val();
		var pwd2 = $("input[name=" + name + "2]").val();
		if ($.trim(pwd) != "" && $.trim(pwd2) != "") {
			if (pwd != pwd2) {
				setMsg(name + "2", msg, oldmsg)
				return false;
			}
		}
		return true;
	},
	
	checkPasswordReset : function(name, msg, oldmsg) {
		var pwd = $("input[name=" + name + "]").val();
		var pwd2 = $("input[name=old" + name + "]").val();
		if ($.trim(pwd) != "" && $.trim(pwd2) != "") {
			if (pwd != pwd2) {
				setMsg(name, msg, oldmsg)
				return false;
			}
		}
		return true;
	}

};