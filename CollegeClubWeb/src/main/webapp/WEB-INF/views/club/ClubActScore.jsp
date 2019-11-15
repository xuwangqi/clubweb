<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>大学生社团管理系统-管理员</title>
<meta name="viewport"
	content="width=device-width,height=device-height,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
</head>
<body>
	<%@include file="/head.jsp"%>
	<div class="container theme-showcase" role="main">

		<div class="well">${clubact.caTitle}活动结算</div>
				
		<div style="margin-top: 50px; display: hidden;" role="alert"></div>
		<div class="btn-group">
			<button type="button" class="btn btn-primary" onclick="deleteon()">提交所选栏目</button>
			<button type="button" class="btn btn-success" onclick="selectall()">全选选择</button>
			<button type="button" class="btn btn-info" onclick="selectnone()">全部不选</button>
			<button type="button" class="btn btn-warning" onclick="selectunder()">反向选择</button>
		</div>
		
		<table class="table table-striped">
			<thead>
				<tr>
					<th>标记</th>
					<th>学号</th>
					<th>姓名</th>
					<th>学分</th>
					<!-- <th>申请额外学分</th>
					<th>操作</th> -->
				</tr>
			</thead>
			<tbody>
				<c:if test="${not empty clubact}">
					<c:forEach items="${clubact.clubuser}" var="user">
						<tr>
							<td><input type="checkbox" class="checkUser"
								value="${user.userUid}"></td>
								<td>${user.userId}</td>
							<td>${user.userName}</td>
							<td>${clubact.caGrade}</td>
						<!-- 	<td>0</td>
							<td> <a href="#" class="btn btn-success">添加额外学分</a> -->
							</td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>

		</table>		
	</div>
	<script type="text/javascript">
		var deleteon = function() {
			var ids = "";
			var num = 0;
			$('input:checkbox[class=checkUser]:checked').each(function(i) {
				if (0 == i) {
					ids = $(this).val();
				} else {
					ids += ("," + $(this).val());
				}
				num += 1;
			});
			if (num <= 0)
				return;
			var confirms = window.confirm("是否结算当前活动");
			if (confirms) {
				$.post("/club/act/score/upload"
				,{"act":'${clubact.caUid}',"ids":ids}
				, function(reslut) {
					if (reslut.status == 200) {
						alert("活动结算成功");
						reto();
					} else {
						alert("活动结算失败");
					}
				});
			}
		}
	</script>
	<script type="text/javascript" src="${app} /js/choose_common.js"></script>
	<script type="text/javascript" src="${app} /js/club_common.js"></script>
</body>
</html>