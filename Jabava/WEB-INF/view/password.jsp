<%@ page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html>
<head>
	<base href="${pageContext.request.contextPath}/">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <!-- Page title -->
    <title>忘记密码</title>

    <!-- Place favicon.ico and apple-touch-icon.png in the root directory -->
    <!--<link rel="shortcut icon" type="image/ico" href="favicon.ico" />-->

    <!-- Vendor styles -->
    <link rel="stylesheet" href="static/bootstrap/vendor/fontawesome/css/font-awesome.css" />
    <link rel="stylesheet" href="static/bootstrap/vendor/metisMenu/dist/metisMenu.css" />
    <link rel="stylesheet" href="static/bootstrap/vendor/animate.css/animate.css" />
    <link rel="stylesheet" href="static/bootstrap/vendor/bootstrap/dist/css/bootstrap.css" />
    
    <!-- for data table -->
    <link rel="stylesheet" href="static/bootstrap/vendor/datatables_plugins/integration/bootstrap/3/dataTables.bootstrap.css" />
    
    <!-- for alert -->
    <link rel="stylesheet" href="static/bootstrap/vendor/sweetalert/lib/sweet-alert.css" />
    
    <!-- App styles -->
    <link rel="stylesheet" href="static/bootstrap/fonts/pe-icon-7-stroke/css/pe-icon-7-stroke.css" />
    <link rel="stylesheet" href="static/bootstrap/fonts/pe-icon-7-stroke/css/helper.css" />
    <link rel="stylesheet" href="static/bootstrap/styles/static_custom.css">
    <link rel="stylesheet" href="static/bootstrap/styles/style.css">
    
    <!--for 临时改变-->
	<link rel="stylesheet" href="static/css/user.css">
	<link rel="stylesheet" href="static/css/user_bata.css">
</head>
<body class="blank">

<!-- Simple splash screen-->
<div class="splash"> 
 <div class="color-line"></div><div class="splash-title">
<h1>Jabava V1.0 </h1>
<p>属于你的专业人事专员</p>
<img src="images/loading-bars.svg" width="64" height="64" /> </div> </div>

<!--[if lt IE 7]>
<p class="alert alert-danger">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
<![endif]-->

<div class="color-line"></div>
<!-- <div class="back-link">
    <a href="index.html" class="btn btn-primary">Back to Dashboard</a>
</div> -->
<div class="register-container">
    <div class="row">
        <div class="col-md-12">
            <div class="text-center m-b-md">
                <h3>忘记密码</h3>
            </div>
            <div class="hpanel">
                <div class="panel-body">
                	<div class="col-sm-12">
                        <form action="#" id="loginForm" method="post" class="formclass">
                            <div class="row">
                            <div class="form-group">
                            	<label class="col-lg-2">&nbsp;</label>
                                <label class="col-lg-2">手&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;机</label>
                                <div class="col-lg-6">
                                	<input type="" value="" id="mobile" class="form-control" name="mobile" placeholder="请输入联络人手机号">
                                	<input id="realCode" name="realCode" type="hidden"/>
                                </div>
                                <label class="col-lg-1 hong">*</label>
                                <label class="col-lg-1">&nbsp;</label>
                            </div>
                            
                            <div class="form-group">
                            	<label class="col-lg-2">&nbsp;</label>
                                <label class="col-lg-2">验&nbsp; 证&nbsp;码</label>
                                <div class="col-lg-4">
                                	<input type="" value="" id="checkCode" class="form-control" name="checkCode" placeholder="请输入验证码">
                                </div>
                                <div class="col-lg-2">
                                <button class="btn btn-success register_but" style="width:100%;" onclick="checkMobile();return false;">获取验证码</button>
                                </div>
                                <label class="col-lg-1 hong">*</label>
                                <label class="col-lg-1">&nbsp;</label>
                            </div>
                            <center style=" padding-top:76px;">
                                <button class="btn btn-success demo2" type="button" onclick="resetPwd();return false;" style="width:60%; margin-left:30px;">确定</button>
                            </center>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12 text-center">
            <strong>Jabava</strong> - 您的专业HR <br/> 2015 Copyright 智阳网络技术（上海）有限公司
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


<script>

function checkMobile(){
	if($("#mobile").val() == ''){
		alert("请输入手机号码");
		return false;
	}
	$.ajax({
		cache : true,
		type : "POST",
		url : "checkMobile",
		data : {"mobile":$("#mobile").val(),"flag": 1},
		dataType : 'json',
		async : false,
		success : function(data) {
			document.getElementById("realCode").value = data.checkCode;
		}
	});
}

function resetPwd(){
	if($("#mobile").val() == ''){
		alert("请输入手机号码");
		return false;
	}
	if($("#checkCode").val() == ''){
		alert("请输入校验码");
		return false;
	}
	if($("#checkCode").val() != $("#realCode").val()){
		alert("请输入正确的校验码");
		return false;
	}
	$.ajax({
		cache : true,
		type : "POST",
		url : "resetPwd",
		data : $('#loginForm').serialize(),
		dataType : 'json',
		async : false,
		success : function(data) {
			if (data.newPwd != '') {
				swal({
		            title: "您的登录密码已被系统重置 新密码为:"+data.newPwd,
		            text: "请尽快登录系统修改密码！",
		            type: "success",
					confirmButtonText: "确定"
				});
				$('.confirm').click(function(){
					$("#loginForm").attr("action", "to_login");
					$('#loginForm').submit();
				});
			} else {
				alert("注册用户失败");
				return false;
			}
		}
	});
}
</script>
</body>
</html>