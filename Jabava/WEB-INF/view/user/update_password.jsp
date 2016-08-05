<%@page import="com.jabava.core.config.JabavaPropertyCofigurer"%>
<%@ page contentType="text/html; charset=utf-8" %>
<%
	String logoutUrl = null;
	if("1".equals(JabavaPropertyCofigurer.getSsoSwitch())){
		logoutUrl = "/j_spring_cas_security_logout";
	}else{
		//logoutUrl = "/logout";
		logoutUrl = "/j_spring_security_logout";
	}
%>
<!DOCTYPE html>
<html>
<head>
    <base href="${pageContext.request.contextPath}/">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <!-- Page title -->
    <title>修改密码</title>

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
    <link rel="stylesheet" href="static/js/plugins/form.validation/css/formValidation.css">
    <!--for 临时改变-->
    <link rel="stylesheet" href="static/css/jabava.css">
   <!--  <link rel="stylesheet" href="static/css/user.css">
    <link rel="stylesheet" href="static/css/user_bata.css"> -->
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

<div class="color-line"></div>
<!-- <div class="back-link">
    <a href="index.html" class="btn btn-primary">Back to Dashboard</a>
</div> -->
<div class="register-container">
    <div class="row">
        <div class="col-md-12">
            <div class="text-center m-b-md">
                <h3>修改密码</h3>
            </div>
            <div class="hpanel">
                <div class="panel-body">
                    <div class="col-sm-12">
                        <form id="loginForm" class="form-horizontal" data-form="validator" data-form-type="add">
                            <div class="row">
                            <!--当前登录密码-->
                            <div class="form-group">
                                <label class="col-sm-4 com-md-4 com-lg-4 control-label">当前登录密码：</label>
                                <div class="col-sm-6 com-md-6 com-lg-6 form-required">
                                    <input type="password" value="" id="" class="form-control" name="oldPassword" >
                                </div>
                            </div>
                            
                            <!--新密码-->
                            <div class="form-group" >
                                <label class="col-sm-4 com-md-4 com-lg-4 control-label">新密码：</label>
                                <div class="col-sm-6 com-md-6 com-lg-6 form-required">
                                    <input type="password" value="" id="" class="form-control" name="newPassword">
                                </div>
                                    <span class="help-block col-sm-8 com-md-8 com-lg-8 col-sm-offset-4 col-md-offset-4 col-lg-offset-4">密码由6-16个英文字母+数字组成,区分大小写(不包含空格).</span>
                                </div>
                                
                                 <!--确认新密码-->
                             <div class="form-group">
                                <label class="col-sm-4 com-md-4 com-lg-4 control-label">确认新密码：</label>
                                <div class="col-sm-6 com-md-6 com-lg-6 form-required">
                                    <input type="password" value="" id="" class="form-control" name="confirmNewPassword">
                                </div>
                            </div>
                           <div class="col-sm-6 com-md-6 com-lg-6 col-sm-offset-4 col-md-offset-4 col-lg-offset-4">
                                <button class="btn btn-success update btn-block" type="submit">确定</button>
                            </div>
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
    <!-- 表单验证 -->
    <script src="static/js/plugins/form.validation/js/formValidation.js"></script>
    <script src="static/js/plugins/form.validation/js/framework/bootstrap.js"></script>
    <!-- App scripts -->
    <script src="static/bootstrap/scripts/homer.js"></script>
    <script src="static/bootstrap/scripts/charts.js"></script>


<script>

    //表单验证
    var validatorOptions = {
        validator: {
            excluded: 'disabled',
            err: {
                container: 'tooltip'
            },
            icon: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
            	oldPassword: {
                    validators: {
                        notEmpty: {
                            message: '请输入旧密码'
                        }
                    }
                },
                newPassword: {
                    validators: {
                        notEmpty: {
                            message: '请填写必须项'
                        },
                        regexp: {
                            regexp: /^[a-zA-Z0-9]{6,16}$/,
                            message: '密码由6-16个英文字母+数字组成，区分大小写(不包含空格)'
                        }
                    }
                },
                confirmNewPassword: {
                    validators: {
                        notEmpty:{
                            message: '请填写必填项'
                        },
                        regexp: {
                            regexp: /^[a-zA-Z0-9]{6,16}$/,
                            message: '密码由6-16个英文字母+数字组成，区分大小写(不包含空格)'
                        },
                        identical: {
                            field: 'newPassword',
                            message: '两次密码输入不一致'
                        }
                    }
                }
            }
        }, // end validator
    };

    $('[data-form="validator"]').formValidation(validatorOptions.validator)
    .on('success.form.fv', function(e){
        e.preventDefault();
        $.ajax({
            url: 'changePassword',
            type: 'POST',
            dataType: 'json',
            data: $('[data-form="validator"]').serialize(),
        })
        .done(function(d) {
        	if(d.success){
                swal({
                    title: "恭喜您已修改成功，请牢记新的登录密码！",
                    type: "success",
                    confirmButtonText: "重新登录"
	                },
	                function(){
	                        location.href="${pageContext.request.contextPath}<%=logoutUrl%>";
	                }
	            );
            }else{
                swal({
                    title: d.msg,
                    type: "error"
	                }
	            );
            }

        })
        
    });
    
</script>
</body>
</html>