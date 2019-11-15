var clubUid = $("#clubUid").val();
var leftclubconfirm = function() {
	var a = window.confirm("确定要离开社团吗?");
	if (a) {
		a = window.confirm("再考虑一下,真的确定要离开社团吗?");
		if (a) {
			leftclub();
		}
	}
};
var leftclub = function() {
	$.ajax({
		url : "/club/left/" + clubUid,
		type : "GET",
		success : function(data) {
			if (data.status == 200) {
				window.location.href = "/club/myclub";
			} else {
				alert("离开社团失败,请重新尝试");
			}
		},
		error : function() {
			alert('系统异常!!');
		}
	});

}

var ToleftClub = function(userUid, userName) {
	var pd = window.confirm("是否删除" + userName + "在这个社团内");
	if (pd) {
		$.get("/club/Toleft?userUid=" + userUid + "&clubUid=" + clubUid,
				function(reslut) {
					if (reslut.status == 200) {
						alert("删除" + userName + "成功!");
						window.location.reload();
					} else {
						alert("删除" + userName + "失败!");
					}

				});

	}
}

var reto = function() {
	location = document.referrer;
}

var applyClubAct = function() {
	var clubUid = $("#clubUid").val();
	var clubId = $("#clubId").text();
	var clubName = $("#clubName").text();
	var caTitle = $("form input[name=caTitle]").val();
	var caBody = $("form input[name=caBody]").val();
	var caAddress = $("form input[name=caAddress]").val();
	var caNumber = $("form input[name=caNumber]").val();
	var caTime = $("form input[name=caTime]").val();
	var caGrade = $("form input[name=caGrade]").val();
	var caFile = $("#caFile").val();

	var time = new Date(caTime);

	$.ajax({
		url : "/club/act/apply/submit",
		type : "GET",
		data : {
			"caUid" : 0,
			"clubUid" : clubUid,
			"clubId" : clubId,
			"clubName" : clubName,
			"caTitle" : caTitle,
			"caBody" : caBody,
			"caAddress" : caAddress,
			"caNownumber" : 0,
			"caNumber" : caNumber,
			"caTime" : time,
			"caGrade" : caGrade,
			"caFile" : caFile,
			"caType" : -1,
			"caRemsg" : ""
		},
		success : function(data) {
			if (data.status == 200) {
				alert(data.msg);
				reto();

			} else {
				alert(data.msg);
			}
		},
		error : function() {
			alert(data.msg);
		}
	});

}

var editClubAct = function() {

	var caUid = $("#caUid").val();
	var caTitle = $("form input[name=caTitle]").val();
	var caBody = $("form input[name=caBody]").val();
	var caAddress = $("form input[name=caAddress]").val();
	var caNumber = $("form input[name=caNumber]").val();
	var caTime = $("form input[name=caTime]").val();
	var caGrade = $("form input[name=caGrade]").val();
	var caFile = $("#caFile").val();

	var time = new Date(caTime);

	$.ajax({
		url : "/club/act/edit/submit",
		type : "GET",
		data : {
			"caUid" : caUid,
			"caTitle" : caTitle,
			"caBody" : caBody,
			"caAddress" : caAddress,
			"caNumber" : caNumber,
			"caTime" : time,
			"caGrade" : caGrade,
			"caFile" : caFile
		},
		success : function(data) {
			if (data.status == 200) {
				alert(data.msg);
				reto();

			} else {
				alert(data.msg);
			}
		},
		error : function() {
			alert(data.msg);
		}
	});
}

var joinAct = function(caUid) {
	var name = prompt("请输入'加入活动'后加入活动");
	if (name == "加入活动") {
		$.ajax({
			url : "/club/act/join?caUid=" + caUid,
			type : "GET",
			success : function(data) {
				if (data.status == 200) {
					alert("加入社团活动成功");
					window.location.reload();

				} else {
					alert("加入社团活动失败");
				}
			},
			error : function() {
				alert("系统错误");
			}
		});
	}else{
		alert("输入错误");
	}
}

var leftAct = function(caUid) {
	var name = prompt("请输入'退出活动'后退出活动");
	if (name == "退出活动") {
		$.ajax({
			url : "/club/act/left?caUid=" + caUid,
			type : "GET",
			success : function(data) {
				if (data.status == 200) {
					alert("退出社团活动成功");
					window.location.reload();
				} else {
					alert("退出社团活动失败");
				}
			},
			error : function() {
				alert("系统错误");
			}
		});
	}else{
		alert("输入错误");
	}
}


var CloseAct=function(caUid){
	var name = prompt("请输入'关闭活动'后退出活动");
	if (name == "关闭活动") {
		$.ajax({
			url : "/clubact/updatepass?type=2&ids=" + caUid,
			type : "GET",
			success : function(data) {
				if (data.status == 200) {
					alert("关闭社团活动成功");
					window.location.reload();
				} else {
					alert("关闭社团活动失败");
				}
			},
			error : function() {
				alert("系统错误");
			}
		});
	}else{
		alert("输入错误");
	}
	
	
}

var deleteClubAct=function(caUid){	
	var name = prompt("请输入'删除活动'后退出活动");
	if (name == "删除活动") {
		$.ajax({
			url : "/clubact/delete?ids=" + caUid,
			type : "GET",
			success : function(data) {
				if (data.status == 200) {
					alert("删除活动成功");
					window.location.reload();
				} else {
					alert("删除活动失败");
				}
			},
			error : function() {
				alert("系统错误");
			}
		});
	}else{
		alert("输入错误");
	}
		
}




