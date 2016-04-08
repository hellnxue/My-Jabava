<%@page import="com.jabava.utils.Constants"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html>
<base href="<%=basePath%>">
<meta charset="utf-8">
<meta name="rootport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge">

<!-- Page title -->
<title>JabavaV1.0 智阳网络</title>
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
<link rel="stylesheet" href="static/css/logins.css">
<script>
	
<%session.removeAttribute(Constants.LOGIN_USER);
			if (request.getAttribute("rootInfo") != null)
				out.println("alert(\"" + request.getAttribute("rootInfo")
						+ "\");");%>
	
</script>

</head>
<body class="blank" style="background:#f6f6f6;">
	
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
	<div class="login-bg">
	<!-- <div class="back-link">
    <a href="index.html" class="btn btn-primary">Back to Dashboard</a>
</div> -->
	<form action="login" method="post" id="LoginForm" class="rights">
    	
		<div class="login-container">
			<div class="row">
				<div class="col-md-12">
					<div class="text-center m-b-md">
						<h3 style="line-height:20px;"> <img src="static/img/jabava.png" width="88" height="20" > 您的专业HR</h3>
						<small>专业的HR系统</small>
					</div>
					<div class="hpanel">
						<div class="panel-body">

<!-- 							<div class="form-group"> -->
<!-- 								<label class="control-label" for="companyCode">企业号</label> <input -->
<!-- 									type="text" placeholder="请输入企业号" title="请输入企业号" required="" -->
<!-- 									value="" name="companyCode" id="companyCode" -->
<!-- 									class="form-control"> -->
<!-- 							</div> -->
							<div class="form-group">
								<label class="control-label" for="userCode">帐号</label> <input
									type="text" placeholder="请输入用户名/手机/邮箱" required value=""
									name="userCode" id="userCode" class="form-control">
							</div>
							<div class="form-group">
								<label class="control-label" for="password">密码</label> <input
									type="password" title="请输入密码" placeholder="******" required
									value="" name="password" id="password" class="form-control">
							</div>
							<div class="checkbox clearfix">
								<div class="login_fl">
									<input type="checkbox" class="i-checks" checked> 记住登录
								</div>
								<div class="login_fr">
									<a href="to_forgetPwd">忘记密码?</a>
								</div>
							</div>
							<button class="btn btn-success btn-block"
								onClick="javascript:loginClick();">登录</button>
							<a class="btn btn-default btn-block"
								href="to_register">注册</a>

						</div>
                        <!--文字-->
    	<div class="title">
           <h1><img src="static/img/jabava.png" width="380" height="65"></h1>
           <p>当看到你在为繁杂的事务性工作愁眉不展，只想给你一个大大的拥抱， 当听到你仍旧为查询、审
           核、社保、工资拖累，只想对你说: Hey，让我来吧，我是你的jabava.</p>
        </div>
        <!--文字end-->
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
    
    <!--底部-->
    <div class="way">
           <div class="imgs">
            <a href="http://www.ezhiyang.com" target="_blank"><img src="static/img/zhiyang.png" width="100" height="57"></a><span><img src="static/img/shuxian.png" width="1" height="55"></span><a href="http://www.hrofirst.com" target="_blank"><img src="static/img/dyrl.png" width="153" height="49"><span><img src="static/img/shuxian.png" width="1" height="55"></span></a><a href="http://www.haoji-hr.com" target="_blank"><img src="static/img/hjrl.png" width="169" height="54"></a>
           </div>
           <div class="weixin">
              <dl>
                <dt><img src="static/img/qibs.png"></dt>
                <dd>企帮手</dd>
              </dl>
              
              <dl>
                <dt><img src="static/img/HR.png"></dt>
                <dd>HR帮手</dd>
              </dl>
              
              <dl>
                <dt><img src="static/img/ygbs.png"></dt>
                <dd>员工帮手</dd>
              </dl>
              
              <dl>
                <dt><img src="static/img/diyirenli.png"></dt>
                <dd>第一人力</dd>
              </dl>
           </div>
        </div>
        <!--<div style="position:absolute; top:670px; background:#f6f6f6; width:100%; height:250px"></div>-->
</div>
	</div>
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
			document.LoginForm.submit();
		}
	</script>
</body>
</html>
