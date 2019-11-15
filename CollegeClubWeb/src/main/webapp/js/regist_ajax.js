$(function() {

	$("input[name=userId]").blur(function() {
		checkUserId();
	});
	
	$("#regist1").show();
	$("#regist2").hide();
	
	$("#next-submit").click(function(){
		$("#regist1").hide();
		$("#regist2").show();
	});
	
	
	$("#before-submit").click(function(){
		$("#regist2").hide();
		$("#regist1").show();
	});
	
	
	$("#regist-submit").click(function() {
		register();
	});
	
});


setMsg = function(name, msg, oldmsg,page) {
	
	if(page==1){
		$("#regist2").hide();
		$("#regist1").show();
	}else{
		$("#regist1").hide();
		$("#regist2").show();
	}
	
	var test = msg + "</font>";
	$("#error_" + name).html("<font color='red'>" + test);
	var input = "input[name=" + name + "]";
	$('.login-center-input ' + input).focus();

	$(".login-center-input " + input).blur(function() {
		$("#error_" + name).html(oldmsg);
	})
	
	
};

checkUserId = function() {
	var userId = $("input[name=userId]").val();
	if (!formObj.checkNull("userId", "学号不能为空", "学号")) {

	} else {
		$.ajax({
			url : "/user/checkuserId",
			type : "post",
			data : {
				"userId" : userId
			},
			dataType : "json",
			success : function(result) {
				if (result.status == 1) {
					setMsg("userId", "学号已注册", "学号")
					return false;
				} else if (result.status == 0) {
                     return true;
				}
			},
			error : function() {
				return false;
			}
		});
	}
}

function register() {

	var userId = $("form input[name=userId]").val();
	var userPassword = $("form input[name=password]").val();
	var userPassword2 = $("form input[name=password2]").val();
	var userName = $("form input[name=userName]").val();
	var userGender = $("form option:selected").val();
	var userClass = $("form input[name=userClass]").val();
	var QQ = $("form input[name=QQ]").val();
	var userEmail = $("form input[name=userEmail]").val();
	var userPhone = $("form input[name=userPhone]").val();
	var userPhonemin = $("form input[name=userPhonemin]").val();
	
	var flag = formObj.checkForm();
	if (flag) {
		$.ajax({
			url : "/user/registuser",
			type : "post",
			data : {
				"userUid":0,
				"userId" : userId,
				"userPassword" : userPassword,
				"userName" : userName,
				"userGender" : userGender,
				"userClass" : userClass,
				"userQQ" : QQ,
				"userEmail" : userEmail,
				"userPhone" : userPhone,
				"userPhonemin" : userPhonemin,
				"userGrade":0,
				"userType":0
			},
			dataType : "json",
			success : function(result) {
				if (result.status == 1) {
					alert(result.msg);
					window.location.href = "/";
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
		if (!this.checkNull("userId", "学号不能为空!", "学号",1))
			return false;
		if (!this.checkNull("password", "密码不能为空", "密码",1))
			return false;
		if (!this.checkNull("password2", "确认密码不能为空", "确认密码",1))
			return false;
		if (!this.checkNull("userName", "真实姓名不能为空", "真实姓名",1))
			return false;
		if (!this.checkNull("userClass", "班级不能为空", "班级",1))
			return false;
		if (!this.checkNull("QQ", "QQ不能为空", "QQ",2))
			return false;	
		if (!this.checkNull("userPhone", "手机号码不能为空", "手机号码",2))
			return false;
		if (!this.checkNull("userEmail", "邮箱不能为空", "邮箱",2))
			return false;	
					
		if (!this.checkPassword("password", "两次密码不相同", "确认密码",1))
			return false;	
		if (!this.checkPhone("userPhone", "手机号码格式不正确", "手机号码",2))
			return false;			
		if (!this.checkEmail("userEmail", "邮箱格式不正确", "邮箱",2))
			return false;
		
		return true;
	},
	checkNull : function(name, msg, oldmsg,page) {
		var value = $("input[name=" + name + "]").val();
		if ($.trim(value) == "") {
			setMsg(name, msg, oldmsg,page)
			return false;
		}
		return true;
	},
	checkPassword : function(name, msg, oldmsg,page) {
		var pwd = $("input[name=" + name + "]").val();
		var pwd2 = $("input[name=" + name + "2]").val();
		if ($.trim(pwd) != "" && $.trim(pwd2) != "") {
			if (pwd != pwd2) {
				setMsg(name + "2", msg, oldmsg,page)
				return false;
			}
		}
		return true;
	},
	checkEmail : function(name, msg, oldmsg,page) {
		var value = $("input[name=" + name + "]").val();
		var reg = /^\w+@\w+(\.\w+)+$/;
		if (!reg.test(value)) {
			setMsg(name, msg, oldmsg,page);
			return false;
		}
		return true;
	},
	checkPhone : function(name, msg, oldmsg,page) {
		var value = $("input[name=" + name + "]").val();
		var reg = /^1[34578]\d{9}$/;
		if (!reg.test(value)) {
			setMsg(name, msg, oldmsg,page);
			return false;
		}
		return true;
	}
};