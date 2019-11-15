<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<title>大学生社团管理系统-修改社团活动</title>
<meta name="viewport"
	content="width=device-width,height=device-height,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
</style>
</head>
<body>
	<%@include file="/head.jsp"%>
	<div class="container theme-showcase" role="main">

		<ol class="breadcrumb" style="margin-top: 80px">
			<li><a href="/">首页</a></li>
			<li class="active">修改社团活动</li>
		</ol>

		<form>

			<div class="col-md-8">
			    <input type="hidden" id="caUid" value="${clubact.caUid}">
				<input type="hidden" id="clubUid" value="${clubact.clubUid}">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">社团</h3>
					</div>
					<div class="panel-body">
						<span id="clubId">${clubact.clubId}</span>/ <span id="clubName">${clubact.clubName}</span>
					</div>
				</div>


				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">活动标题</h3>
					</div>
					<div class="panel-body">
						<input type="text" class="form-control" name="caTitle" value="${clubact.caTitle}"/>
					</div>
				</div>

				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">活动时间</h3>
					</div>
					<div class="panel-body">
						<div class="input-append date" id="datetimepicker"
							data-date="<fmt:formatDate value="${clubact.caTime}"
							pattern="yyyy-MM-dd hh:mm" />"
							data-date-format="yyyy-mm-dd hh:ii">
							<input size="16" type="text" value="<fmt:formatDate value="${clubact.caTime}"
							pattern="yyyy-MM-dd hh:mm" />"
								name="caTime" readonly> <span class="add-on"><i
								class="icon-th"></i></span>
						</div>
					</div>
				</div>

				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">活动内容</h3>
					</div>
					<div class="panel-body">
						<input type="text" class="form-control" name="caBody" value="${clubact.caBody}"/>
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">活动地点</h3>
					</div>
					<div class="panel-body">
						<input type="text" class="form-control" name="caAddress" value="${clubact.caAddress}"/>
					</div>
				</div>


				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">活动人数</h3>
					</div>
					<input type="hidden" value="0" name="clubNownum" />
					<div class="panel-body">
						<input type="text" class="form-control" name="caNumber" value="${clubact.caNumber}"
							onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"
							onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" 
							<c:if test="${clubact.caType >= 0}" var="catype" scope="page">
							readonly="readonly"
							</c:if>
							
							/>
					</div>
				</div>

				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">活动学分</h3>
					</div>
					<div class="panel-body">
						<input type="text" class="form-control" name="caGrade" value="${clubact.caGrade}"
							onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"
							onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"
						  <c:if test="${catype}">
							readonly="readonly"
							</c:if>
						 />
					</div>
				</div>

                <div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">文件</h3>
					</div>
					<div class="panel-body">
						<a href="${clubact.caFile}">活动文件</a>
					</div>
				</div>
            
			</div>
        
			<div class="col-md-4">
				<div>
					<div class="row">
						<label>文件上传(打包上传支持zip,tar.rar格式,最大超过50MB)</label>
						<div class="file-loading">
							<input id="file-fr" name="file" type="file">
						</div>
						<div>
							<input type="hidden" id="caFile" value="${clubact.caFile}" />
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-4" style="padding-top: 20px;">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title">操作</h3>
					</div>
					<div class="panel-body">
						<div class="row" style="text-align: center">
							<div class="row">
								<input type="button" class="btn btn-primary"
									onclick="editClubAct()" value="修改社团活动"></input> <input
									type="button" class="btn btn-primary" onclick="reto()"
									value="返回"></input>
							</div>
						</div>
					</div>
				</div>
			</div>
	</div>
	</form>
	</div>
	<!-- 图片上传即使预览插件 -->
	<link rel="stylesheet" href="${app} /css/bootstrap/fileinput.min.css">
	<link rel="stylesheet" href="${app} /css/bootstrap/fileinput.css">
	<link rel="stylesheet"
		href="${app} /css/bootstrap/bootstrap-datetimepicker.min.css">
	<script type="text/javascript" src="${app} /js/fileinput.min.js"></script>
	<!-- 这个是汉化-->
	<script type="text/javascript" src="${app} /js/zh.js"></script>
	<script type="text/javascript"
		src="${app} /js/bootstrap-datetimepicker.min.js"></script>
	<script type="text/javascript" src="${app} /js/fileuploadfile.js"></script>
	<script type="text/javascript" src="${app} /js/club_common.js"></script>
	<script type="text/javascript">
		$('#datetimepicker').datetimepicker();
	</script>
</body>
</html>