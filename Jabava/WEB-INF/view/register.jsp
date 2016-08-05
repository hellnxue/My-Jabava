<%@page import="com.jabava.core.config.JabavaPropertyCofigurer"%>
<%@ page contentType="text/html; charset=utf-8" %>
<%
	String path = request.getContextPath();
	String basePath = "//"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	//response.sendRedirect(basePath + "common/maintain");
%>
<!DOCTYPE html>
<html>
<head>
	<base href="${pageContext.request.contextPath}/">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <!-- Page title -->
    <title>注册</title>

    <!-- Place favicon.ico and apple-touch-icon.png in the root directory -->
    <!--<link rel="shortcut icon" type="image/ico" href="favicon.ico" />-->

    <!-- Vendor styles -->
    <link rel="stylesheet" href="static/bootstrap/vendor/fontawesome/css/font-awesome.css" />
    <link rel="stylesheet" href="static/bootstrap/vendor/metisMenu/dist/metisMenu.css" />
    <link rel="stylesheet" href="static/bootstrap/vendor/animate.css/animate.css" />
    <link rel="stylesheet" href="static/bootstrap/vendor/bootstrap/dist/css/bootstrap.css" />
    
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
	<!-- <link rel="stylesheet" href="static/css/user.css"> -->
	
     

</head>
<body class="blank">

<jsp:include flush="true" page="./common/splashscreen.div.jsp"></jsp:include>

<div class="color-line"></div>
<!-- <div class="back-link">
    <a href="index.html" class="btn btn-primary">Back to Dashboard</a>
</div> -->
<div class="register-container">
    <div class="row">
        <div class="col-md-12">
            <div class="text-center m-b-md">
                <h3>注册</h3>
            </div>
            <div class="hpanel">
                <div class="panel-body">
                	<div class="col-sm-12">
                        <form action="#" id="loginForm" class="form-horizontal" method="post">
                            <div class="row">
                            <div class="form-group">
                                <label class="col-sm-2 col-md-2 col-lg-2 control-label">公司名称</label>
                                <div class="col-sm-9 col-md-9 col-lg-9 form-required">
                                	<input type="text" value="" id="companyName" class="form-control" name="companyName" placeholder="请输入公司名称">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 col-md-2 col-lg-2 control-label">联系人</label>
                                <div class="col-sm-9 col-md-9 col-lg-9 form-required">
                                	<input type="text" value="" id="contacts" class="form-control" name="contacts" placeholder="请输入联系人姓名">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 col-md-2 col-lg-2 control-label">所在城市</label>
                                <div id="all_city">
	                                <div class="col-sm-3 col-md-3 col-lg-3">
	                                 	<select id="companyProv" class="prov form-control" name="companyProv" ></select> 
	                                </div>
	                                <div class="col-sm-3 col-md-3 col-lg-3">
	                                	<select id="companyCity" class="city form-control" name="companyCity" disabled="disabled"></select>
	                                </div>	
	                                <div class="col-sm-3 col-md-3 col-lg-3">
	                                	<select id="companyDist" class="dist form-control" name="companyDist" disabled="disabled"></select>
	                                </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 col-md-2 col-lg-2 control-label">密码</label>
                                <div class="col-sm-9 col-md-9 col-lg-9 form-required">
                                	<input type="password" value="" id="password" class="form-control" name="password" placeholder="******">
                                	<span class="help-block col-sm-9 com-md-9 com-lg-9">密码由6-16个英文字母+数字组成,区分大小写(不包含空格).</span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 col-md-2 col-lg-2 control-label">确认密码</label>
                                <div class="col-sm-9 col-md-9 col-lg-9 form-required">
                                	<input type="password" value="" id="password2" class="form-control" name="password2" placeholder="******">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 col-md-2 col-lg-2 control-label">手机</label>
                                <div class="col-sm-9 col-md-9 col-lg-9 form-required">
                                	<input id="mobile" class="form-control" name="mobile" value="${param.mobile }" placeholder="请输入登录手机号">
                                </div>
                            </div>
                             <div class="form-group">
                                <label class="col-sm-2 col-md-2 col-lg-2 control-label">图片验证码</label>
                                <div class="col-sm-9 col-md-9 col-lg-9 form-required">
                                	<div class="input-group">
                                		<input value="" id="gCheckCode" class="form-control" name="gCheckCode" placeholder="请输入验证码">
                                		<span class="input-group-btn">
                                		<img src="<%=path %>/kaptcha.jpg" height="34" id="kaptchaImage" alt="图片验证码" title="看不清？点击更换验证码" />
                                		</span>
                                	</div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 col-md-2 col-lg-2 control-label">短信验证码</label>
                                <div class="col-sm-9 col-md-9 col-lg-9 form-required">
                                	<div class="input-group">
                                		<input value="" id="mCheckCode" class="form-control" name="mCheckCode" placeholder="请输入短信验证码">
                                		<span class="input-group-btn">
                                		<button class="btn btn-success register_but" style="width:100%;" onclick="checkMobile();return false;" type="button">获取验证码</button>
                                		</span>
                                	</div>
                                </div>
                            </div>
                            
                            <div class="form-group">
                                <div class="col-sm-9 col-md-9 col-lg-9 col-sm-offset-2 col-md-offset-2 col-lg-offset-2 checkbox">
									<div class="checkbox">
                                		<input type="checkbox" class="register_radio" id="agree" name="agree">
                                		<a href="static/docs/protocol.html" target="_blank" style="text-decoration: underline;" class="register_span">我已经阅读并同意遵守《Jabava服务条款》</a>
                                	</div>
                                </div>
                            </div>
                            </div>
                            <div class="form-group text-center">
                            	<button id="reg" class="btn btn-success demo2" type="submit" disabled="disabled">注 册</button>
                            </div>
                            
                             <input type="hidden"  id="userId" name="userId" value="${userId}"/>
                            <input type="hidden"  id="companyId" name="companyId"value="${companyId}"/>
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

	<!-- 表单验证 -->
	<script src="static/js/plugins/form.validation/js/formValidation.js"></script>
	<script src="static/js/plugins/form.validation/js/framework/bootstrap.js"></script>
	<script src="static/js/plugins/form.validation/js/language/zh_CN.js"></script> 
	<!-- alert -->
	<script src="static/bootstrap/vendor/sweetalert/lib/sweet-alert.min.js"></script>
    <script src="static/js/plugins/countdown/jquery.countdown.min.js"></script>
	<!-- App scripts -->
	<script src="static/bootstrap/scripts/homer.js"></script>
    <script src="static/bootstrap/scripts/charts.js"></script>
    
    <script type="text/javascript" src="static/js/jquery.cityselect.js"></script>
    <script type="text/javascript" src="static/js/validate.js"></script>
    
<script>
$(function (){
	
	$(".register_but, [name=mCheckCode]").prop('disabled', true).addClass('disabled');
	document.getElementById("loginForm").reset(); 
	
	$("#all_city").citySelect({
		nodata:"none",
		required:false
	}); 

    $('#kaptchaImage')
    .css({
    	cursor: 'pointer'
    })
    .on('click', function() {
        $(this).attr('src', '<%=path %>/kaptcha.jpg?' + Math.floor(Math.random()*100) );
    })

 	var validators = {
 			err: {
	            container: 'tooltip'
	        },
	        icon: {
	            valid: 'glyphicon glyphicon-ok',
	            invalid: 'glyphicon glyphicon-remove',
	            validating: 'glyphicon glyphicon-refresh'
	        },
	        locale: 'zh_CN',
	        
	        fields: {
	            companyName: {
	                validators: {
	                    notEmpty: {
	                        message: '请填写必填项目'
	                    }
	                }
	            },
	            contacts: {
	                validators: {
	                    notEmpty: {
	                        message: '请填写必填项目'
	                    }
	                }
	            },
	            mobile: {
	                validators: {
	                    notEmpty: {
	                        message: '请填写必填项目'
	                    },
	                    phone: {
	                    	message: '请输入正确格式的手机号码',
	                    	country: 'CN'
	                    }
	                },
	                onSuccess: function(e,data){
	                	if($('#gCheckCode').val()!='') $('.register_but, [name=mCheckCode]').prop('disabled', false).removeClass('disabled')
	                },
	            	onError: function(e,data){
	            		$('.register_but, [name=mCheckCode]').prop('disabled', true).addClass('disabled')
	            	}
	            },
	            gCheckCode: {
	                validators: {
	                    notEmpty: {
	                        message: '请填写必填项目'
	                    },
                		remote: {
		                    url: 'checkGraphics',
		                    type: 'POST',
		                    delay: 1000,
		                    data: function(validator, $field, value) {
							    return {
							        gCheckCode: value
							    };
							},
							validKey: 'success',
							message: '图形验证码不匹配'
		                }
	                },
	                onSuccess: function(e, data){
            			if($('#mobile').val()!='') $(".register_but, [name=mCheckCode]").prop('disabled', false).removeClass('disabled')
            		},
            		onError: function(e, data){
            			$(".register_but, [name=mCheckCode]").prop('disabled', true).addClass('disabled')
            		}
	            },
	            mCheckCode: {
	                validators: {
	                    notEmpty: {
	                        message: '请填写短信验证码'
	                    }
	                }
	            },
	            password: {
	                validators: {
	                    notEmpty: {
	                        message: '请填写必填项目'
	                    },
		                regexp: {
	                            regexp: /^[a-zA-Z0-9]{6,16}$/,
	                            message: '密码由6-16个英文字母+数字组成，区分大小写(不包含空格)'
	                    }
	                }
	            },
	            password2: {
	                validators: {
	                    notEmpty: {
	                        message: '请填写必填项目'
	                    },
	                    regexp: {
	                            regexp: /^[a-zA-Z0-9]{6,16}$/,
	                            message: '密码由6-16个英文字母+数字组成，区分大小写(不包含空格)'
	                    },
	                    identical: {
                            field: 'password',
                            message: '密码不一致，请重新输入密码'
                        }
	                }
	            },
	            agree: {
	                validators: {
	                    notEmpty: {
	                        message: '您必须同意《Jabava服务条款》，才能继续注册。'
	                    }
	                }
	            }
	        }
 		}


 	$('#loginForm')
 	.formValidation(validators)
 	.on('success.form.fv',function(e){
 		e.preventDefault();
		register()
 	})

	
})

function checkMobile(){
    var $countdownOptionEnd = $(".register_but");
    $.ajax({
		cache : true,
		type : "POST",
		url : "checkMobile",
		data : {"mobile":$("#mobile").val(), "gCheckCode":$('#gCheckCode').val(), "flag": 1},
		dataType : 'json',
		async : false,
		success : function(data) {
			if(!data.success){
				$countdownOptionEnd.prop('disabled', false).removeClass('disabled')
				swal({title:data.msg, type:'error'})
				return false;
			}else{
			    function get15dayFromNow() {
			    	return (new Date()).getTime() + 120 * 1000; // 1 minute later;
			    }
			    $countdownOptionEnd.countdown(get15dayFromNow(), function(event) {
			    	$(this).html(event.strftime('%M:%S 秒后重新发送'));
			    })
			    .on('finish.countdown', function(event){
			        $countdownOptionEnd.prop('disabled', false)
			        .removeClass('disabled')
			        .html('获取验证码');
			    })

			}
		}
	}); 
}

function agreeReg(){
	var agree = document.getElementById("agree");
	if(agree.checked){
		document.getElementById("reg").disabled = false; 	
	}else{
		document.getElementById("reg").disabled = true; 	
	}
}

function register(){
	$.ajax({
			cache : true,
			type : "POST",
			url : "register",
			data : $('#loginForm').serialize(),
			dataType : 'json',
			async : false,
			success : function(data) {
				if (data.result == true) {
					document.getElementById("userId").value = data.userId;
					document.getElementById("companyId").value = data.companyId;
					//console.log(data)
					if(data.url){
						//$("#loginForm").attr("action", data.url);
						location.href = data.url;
					}else{
						console.log(data)
						//$("#loginForm").attr("action", "to_perfect");
						//$("#loginForm").attr("action", "to_index");
						location.href = "to_index";
					}
				} else {
					alert("注册用户失败: " + data.msg);
					return false;
				}
			}
		});
	}
</script>
</body>
</html>