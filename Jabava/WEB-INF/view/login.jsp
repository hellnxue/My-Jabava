<%@page import="com.jabava.utils.Constants"%>
<%@page import="com.jabava.core.config.JabavaPropertyCofigurer"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = "//"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	//已登录则不需要登录
	if(session != null && session.getAttribute(Constants.LOGIN_USER) != null){
		request.getRequestDispatcher("/to_index").forward(request, response);
	}
	
	//SSO环境不需要访问登录页面
	if("1".equals(JabavaPropertyCofigurer.getSsoSwitch())){
		//request.getRequestDispatcher("/to_index").forward(request, response);
		request.getRequestDispatcher("/").forward(request, response);
	}
%>

<!DOCTYPE HTML>
<html>
<head>
	<base href="${pageContext.request.contextPath}/">
	<meta charset="utf-8">
	<meta name="rootport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	
	<!-- Page title -->
	<title>Jabava V1.0 | 登录</title>
	<!-- Place favicon.ico and apple-touch-icon.png in the root directory -->
	<!--<link rel="shortcut icon" type="image/ico" href="favicon.ico" />-->
	
	<!-- Vendor styles -->
	<link rel="stylesheet"
		href="static/bootstrap/vendor/fontawesome/css/font-awesome.css"
		type="text/css" />
	<link rel="stylesheet"
		href="static/bootstrap/vendor/metisMenu/dist/metisMenu.css"
		type="text/css" />
	<link rel="stylesheet"
		href="static/bootstrap/vendor/animate.css/animate.css" type="text/css" />
	<link rel="stylesheet"
		href="static/bootstrap/vendor/bootstrap/dist/css/bootstrap.css"
		type="text/css" />
	
	<!-- App styles -->
	<link rel="stylesheet"
		href="static/bootstrap/fonts/pe-icon-7-stroke/css/pe-icon-7-stroke.css"
		type="text/css" />
	<link rel="stylesheet"
		href="static/bootstrap/fonts/pe-icon-7-stroke/css/helper.css"
		type="text/css" />
	<link rel="stylesheet" href="static/bootstrap/styles/style.css"
		type="text/css">
	<!--for 临时改变-->
	<link rel="stylesheet" href="static/css/user.css">

</head>
<body class="blank">

	<!-- Simple splash screen-->
	<div class="splash">
		<div class="color-line"></div>
		<div class="splash-title">
			<h1>Jabava V1.0</h1>
			<p>属于你的专业人事专员</p>
			<img src="static/bootstrap/images/loading-bars.svg" width="64"
				height="64" />
		</div>
	</div>

	<!--[if lt IE 7]>
<p class="alert alert-danger">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
<![endif]-->

	<div class="color-line"></div>

	<!-- <div class="back-link">
    <a href="index.html" class="btn btn-primary">Back to Dashboard</a>
</div> -->
	<form id="LoginForm" action="${pageContext.request.contextPath}/j_spring_security_check" method="post">
	<!--form action="login" method="post" id="LoginForm"-->
		<div class="login-container">
			<div class="row">
				<div class="col-md-12">
					<div class="text-center m-b-md">
						<h3>Jabava 您的专业HR</h3>
						<small>专业的HR系统</small>
					</div>
					<div class="hpanel">
						<div class="panel-body">
							<div class="form-group">
								<label class="control-label" for="userCode">帐号</label> <input
									type="text" placeholder="请输入用户名/手机/邮箱" required="" value=""
									name="j_username" id="userCode" class="form-control">
							</div>
							<input type="hidden" name='channel' value="pc"/></input>
							<div class="form-group">
								<label class="control-label" for="password">密码</label> <input
									type="password" title="请输入密码" placeholder="******" required=""
									value="" name="j_password" id="password" class="form-control">
							</div>
							<div class="checkbox clearfix">
								<div class="login_fl">
									<a href="user/password">忘记密码?</a>
								</div>
							</div>
							<!--button class="btn btn-success btn-block" onClick="javascript:loginClick();">登录</button-->
							<button class="btn btn-success btn-block" onClick="javascript:$('#LoginForm').submit();">登录</button>
							<a class="btn btn-default btn-block"
								href="to_register">注册</a>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12 text-center">
					<strong>Jabava</strong> - 您的专业HR <br /> 2015 Copyright
					智阳网络技术（上海）有限公司
				</div>
			</div>
		</div>
	</form>

	<!-- Vendor scripts -->
	<script src="static/bootstrap/vendor/jquery/dist/jquery.min.js"></script>
	<script src="static/bootstrap/vendor/jquery-ui/jquery-ui.min.js"></script>
	<script
		src="static/bootstrap/vendor/slimScroll/jquery.slimscroll.min.js"></script>
	<script
		src="static/bootstrap/vendor/bootstrap/dist/js/bootstrap.min.js"></script>
	<script src="static/bootstrap/vendor/metisMenu/dist/metisMenu.min.js"></script>
	<script src="static/bootstrap/vendor/iCheck/icheck.min.js"></script>
	<script src="static/bootstrap/vendor/sparkline/index.js"></script>

	<!-- App scripts -->
	<script src="static/bootstrap/scripts/homer.js"></script>

	<script>
		function loginClick() {
			/* if (LoginForm.companyCode.value == "") {
				window.alert("请输入企业号");
				LoginForm.companyCode.select();
				return false;
			} */
			if (LoginForm.userCode.value == "") {
				window.alert("请输入用户名/手机/邮箱");
				LoginForm.userCode.select();
				return false;
			}
			if (LoginForm.passwd.value == "") {
				window.alert("请输入密码");
				LoginForm.passwd.select();
				return false;
			}
			document.LoginForm.submit(function(){
				alert("==== 啊 啊 啊 ===");
			});
		}
		
		$(function(){
			console.log("=========== loading the work ==========");
			
			console.log($.fn.submit);
			//$.fn.submit();
		});
	</script>
</body>
</html>
