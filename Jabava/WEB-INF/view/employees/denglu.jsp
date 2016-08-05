<%@ page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html>
<head>
	<base href="${pageContext.request.contextPath}/">
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport"/>  
	<meta content="yes" name="apple-mobile-web-app-capable" />  
	<meta content="black" name="apple-mobile-web-app-status-bar-style" />  
	<meta content="telephone=no" name="format-detection" /> 

	<title>登录</title>

	<link rel="stylesheet" href="static/bootstrap/vendor/fontawesome/css/font-awesome.css" />
	<link rel="stylesheet" href="static/bootstrap/vendor/metisMenu/dist/metisMenu.css" />
	<link rel="stylesheet" href="static/bootstrap/vendor/animate.css/animate.css" />
	<link rel="stylesheet" href="static/bootstrap/vendor/bootstrap/dist/css/bootstrap.css" />

	<link rel="stylesheet" href="static/bootstrap/vendor/datatables_plugins/integration/bootstrap/3/dataTables.bootstrap.css" />
	
	<!-- for alert -->
	<link rel="stylesheet" href="static/bootstrap/vendor/sweetalert/lib/sweet-alert.css" />
	
	<!-- App styles -->
	<link rel="stylesheet" href="static/bootstrap/fonts/pe-icon-7-stroke/css/pe-icon-7-stroke.css" />
	<link rel="stylesheet" href="static/bootstrap/fonts/pe-icon-7-stroke/css/helper.css" />
	<link rel="stylesheet" href="static/bootstrap/styles/static_custom.css">
	<link rel="stylesheet" href="static/bootstrap/styles/style.css">
	
	<!--for 临时改变-->
	<link rel="stylesheet" id="link" href="static/css/denglu.css" type="text/css">
</head>
<body class="blank">

<!-- Simple splash screen-->
<div class="splash"> 
 <div class="color-line"></div><div class="splash-title">
<h1>Jabava V1.0 </h1>
<p>属于你的专业人事专员</p>
<img src="static/bootstrap/images/loading-bars.svg" width="64" height="64" /> </div> </div>

<!--[if lt IE 7]>
<p class="alert alert-danger">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
<![endif]-->

<div class="register-container containers ">
	<div class="row">
		<div class="col-md-12">
			<div class="hpanel">
				<div class="panel-body">
					<div class="row">
					<!-- ${pageContext.request.contextPath}/j_spring_security_check -->
						<form action="/employee/mobile_login" id="loginForm" class=" form-horizontal"  role="form" method="post">
							<center>
							<input type="text" value="" id="userCode" class="form-control" name="j_username" placeholder="请输入手机号" required="" style="width:60%; margin-left:30px;">
							</center>
							<center style=" padding-top:10px;">  
							<input type="password" name='j_password' value="" id="password" class="form-control" name="" placeholder="请输登录密码" style="width:60%; margin-left:30px;" required="">
							</center>
							<input type="hidden" name='channel' value="mobile"/></input>
							<center style=" padding-top:20px;">
							<!-- onClick="javascript:$('#LoginForm').submit();" -->
								<button id="mobileLoginButton" class="btn btn-success demo2 login_button" type="button" style="width:60%; margin-left:30px;" >登录</button>
							</center>
						</form>
						<div class="titles" style=" width:100%; text-align:center; margin-top:30px;">
						<p >如填写更多内容请去网页端填写</p>
						<img src="static/img/image001.png" width="223" height="128" style=" margin-top:20px;">
						<p style=" margin-top:40px;">智阳HR Saas(jabava)</p>
						</div>
					</div>
				</div>
			</div>
		</div>
		</div>
	</div>
</div>

	<!-- Vendor scripts -->
	<script src="static/bootstrap/vendor/jquery/dist/jquery.min.js"></script>
	<script src="static/bootstrap/vendor/jquery-ui/jquery-ui.min.js"></script>
	<script src="static/bootstrap/vendor/slimScroll/jquery.slimscroll.min.js"></script>
	<script src="static/bootstrap/vendor/bootstrap/dist/js/bootstrap.min.js"></script>
	<script src="static/bootstrap/vendor/jquery-flot/jquery.flot.js"></script>
	<script src="static/bootstrap/vendor/jquery-flot/jquery.flot.resize.js"></script>
	<script src="static/bootstrap/vendor/jquery-flot/jquery.flot.pie.js"></script>
	<script src="static/bootstrap/vendor/flot.curvedlines/curvedLines.js"></script>
	<script src="static/bootstrap/vendor/jquery.flot.spline/index.js"></script>
	<script src="static/bootstrap/vendor/metisMenu/dist/metisMenu.min.js"></script>
	<script src="static/bootstrap/vendor/iCheck/icheck.min.js"></script>
	<script src="static/bootstrap/vendor/peity/jquery.peity.min.js"></script>
	<script src="static/bootstrap/vendor/sparkline/index.js"></script>

	<!-- for datatable -->
	<script src="static/bootstrap/vendor/datatables/media/js/jquery.dataTables.min.js"></script>
	<script src="static/bootstrap/vendor/datatables_plugins/integration/bootstrap/3/dataTables.bootstrap.min.js"></script>

	<!-- alert -->
	<script src="static/bootstrap/vendor/sweetalert/lib/sweet-alert.min.js"></script>
	
	<!-- App scripts -->
	<script src="static/bootstrap/scripts/homer.js"></script>
	<script src="static/bootstrap/scripts/charts.js"></script>

<script type="text/javascript">
	$(function(){
		var $loginButton = $('#mobileLoginButton');
		
		$loginButton.on('click',function(){
			var user = $('#userCode').val();
			var pwd = $('#password').val();
			var obj = {
                user: user,
                pwd: pwd
			};

			$.ajax({
				type : 'post',
				url : "employee/mobile_login",
				async : false,
                dataType: "json",
                contentType:'application/json',
                data: JSON.stringify(obj),
				success : function(result) {
                    if(result.status == 0){
                        window.location.href= "employee/mobileInfoCollect/"+result.personId;
                    }else{
                        window.location = "employee/login_mobile";
                    }
				}
			});
			
		});
	});
	adjustPlatform();

	/**
	 * 检测平台in并应用相关样式
	 * @return {[type]} [description]
	 */
	function adjustPlatform(){
		var system ={  
			win : false,  
			mac : false,  
			xll : false  
		};
		//检测平台  
		var p = navigator.platform;
		system.win = p.indexOf("Win") == 0;  
		system.mac = p.indexOf("Mac") == 0;  
		system.x11 = (p == "X11") || (p.indexOf("Linux") == 0);  
		//跳转语句  
		if(system.win||system.mac||system.xll){
			$("#link").attr("href","static/css/denglu.css");
		}else{  
			$("#link").attr("href","static/css/dengluiphone.css");
		}
	}
</script> 
</body>
</html>