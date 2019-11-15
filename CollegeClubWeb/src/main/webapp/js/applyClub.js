
var applyClub = function() {
    
	var clubUid=$("form input[name=clubUid]").val();
	var clubName=$("form input[name=clubName]").val();
	var clubSay=$("form input[name=clubSay]").val();
	var clubId=$("form input[name=clubId]").val();
	var clubNownum=$("form input[name=clubNownum]").val();
	var clubTotalnum=$("form input[name=clubTotalnum]").val();
	var clubDesc=$("form input[name=clubDesc]").val();
	var clubLogourl=$("#userImage").val();
	
	var flag = formObj.checkForm();
	if(flag==false){}
	
		
	if (flag) {
		$.ajax({
			url : "/club/apply/submit",
			type : "post",
			data : {
				"clubUid" : clubUid,
				"clubName" : clubName,
				"clubSay" : clubSay,
				"clubId" : clubId,
				"clubNownum" : clubNownum,
				"clubTotalnum" : clubTotalnum,
				"clubDesc" : clubDesc,
				"clubLogourl" : clubLogourl,
				"userName" : 0,
				"userId" : 0
			},
			dataType : "json",
			success : function(result) {
				if (result.status == 200) {
					alert(result.msg);
					history.go(-1);
				} else {
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

var reto = function() {
	history.go(-1);
}


var formObj = {
		checkForm : function() {


			if (!this.checkNull("clubName", "社团名称不能为空"))
				return false;
			if (!this.checkNull("clubId", "社团编号不能为空"))
				return false;
			if (!this.checkNull("clubTotalnum", "社团容量不能为空"))
				return false;	
			if (!this.checkNull("clubDesc", "社团详情不能为空"))
				return false;

			return true;
		},
		checkNull : function(name, msg) {
			var value = $("input[name=" + name + "]").val();
			if ($.trim(value) == "") {
				alert(msg);
				return false;
			}
			return true;
		}	
	};