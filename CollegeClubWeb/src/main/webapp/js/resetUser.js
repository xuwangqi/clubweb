
setMsg = function(name, msg, oldmsg) {
	alert(msg);
};


function resetUser() {
    var userUid=$("#userUid").val();
	var userId = $("form span[name=userId]").text();
	var userPassword = $("form input[name=userPassword]").val();
	var userName = $("form input[name=userName]").val();
	var userGender = $("form select[name=userGender]  option:selected").val();
	var userClass = $("form input[name=userClass]").val();
	var QQ = $("form input[name=userQQ]").val();
	var userEmail = $("form input[name=userEmail]").val();
	var userPhone = $("form input[name=userPhone]").val();
	var userPhonemin = $("form input[name=userPhonemin]").val();
	var userGrade = $("form input[name=userGrade]").val();
	var userType = $("form select[name=userType]  option:selected").val();
	
	
	var flag = formObj.checkForm();
	if (flag) {
		$.ajax({
			url : "/user/update",
			type : "post",
			data : {
				"userUid":userUid,
				"userId" : userId,
				"userPassword" : userPassword,
				"userName" : userName,
				"userGender" : userGender,
				"userClass" : userClass,
				"userQQ" : QQ,
				"userEmail" : userEmail,
				"userPhone" : userPhone,
				"userPhonemin" : userPhonemin,
				"userGrade":userGrade,
				"userType":userType
			},
			dataType : "json",
			success : function(result) {
				if(result.status == 200) {
					alert(result.msg);
					location=document.referrer;
				}			
				else{
					alert(result.msg);
				}
			},
			error : function() {
				alert("请求失败！");
			}
		});
	}

}
var formObj = {
	checkForm : function() {


		if (!this.checkNull("userName", "真实姓名不能为空", "真实姓名"))
			return false;
		if (!this.checkNull("userClass", "班级不能为空", "班级"))
			return false;
		if (!this.checkNull("userQQ", "QQ不能为空", "QQ"))
			return false;	
		if (!this.checkNull("userPhone", "手机号码不能为空", "手机号码"))
			return false;
		if (!this.checkNull("userEmail", "邮箱不能为空", "邮箱"))
			return false;	
					
		if (!this.checkEmail("userEmail", "邮箱格式不正确", "邮箱"))
			return false;
		if (!this.checkPhone("userPhone", "手机号码格式不正确", "手机号码"))
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
	checkEmail : function(name, msg, oldmsg) {
		var value = $("input[name=" + name + "]").val();
		var reg = /^\w+@\w+(\.\w+)+$/;
		if (!reg.test(value)) {
			setMsg(name, msg, oldmsg);
			return false;
		}
		return true;
	},
	checkPhone : function(name, msg, oldmsg) {
		var value = $("input[name=" + name + "]").val();
		var reg = /^1[34578]\d{9}$/;
		if (!reg.test(value)) {
			setMsg(name, msg, oldmsg);
			return false;
		}
		return true;
	}
};