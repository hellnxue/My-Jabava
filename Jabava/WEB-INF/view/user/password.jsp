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
        <link rel="stylesheet" href="static/js/plugins/form.validation/css/formValidation.css">
    <!--for 临时改变-->
    <link rel="stylesheet" href="static/css/jabava.css">
	<!-- <link rel="stylesheet" href="static/css/user.css">
	<link rel="stylesheet" href="static/css/user_bata.css"> -->
</head>
<body class="blank">

<jsp:include flush="true" page="../common/splashscreen.div.jsp"></jsp:include>

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
                        <form id="loginForm" class="form-horizontal" data-form="validator">
                            <div class="row">
                            <div class="form-group">
                                <label class="col-sm-4 col-md-4 col-lg-4 control-label">手机</label>
                                <div class="col-sm-6 col-md-6 col-lg-6 form-required">
                                	<input type="" value="" id="mobile" class="form-control" name="mobile" placeholder="请输入联络人手机号">
                                	<input id="realCode" name="realCode" type="hidden"/>
                                </div>
                            </div>
                            <div class="form-group" >
                                <label class="col-sm-4 com-md-4 com-lg-4 control-label">新登录密码</label>
                                <div class="col-sm-6 com-md-6 com-lg-6 form-required">
                                    <input type="password" value="" id="newPassword" class="form-control" name="newPassword">
                                </div>
                                 <span class="help-block col-sm-8 com-md-8 com-lg-8 col-sm-offset-4 col-md-offset-4 col-lg-offset-4">密码由6-16个英文字母+数字组成,区分大小写(不包含空格).</span>
                               </div>
                                
                                 <!--确认新密码-->
                             <div class="form-group">
                                <label class="col-sm-4 com-md-4 com-lg-4 control-label">确认新密码</label>
                                <div class="col-sm-6 com-md-6 com-lg-6 form-required">
                                    <input type="password" value="" id="confirmNewPassword" class="form-control" name="confirmNewPassword">
                                </div>
                            </div>
                             <div class="form-group">
                                <label class="col-sm-4 col-md-4 col-lg-4 control-label">图片验证码</label>
                                <div class="col-sm-6 col-md-6 col-lg-6 form-required">
                                	<div class="input-group">
                                		<input value="" id="gCheckCode" class="form-control" name="gCheckCode" placeholder="请输入验证码">
                                		<span class="input-group-btn">
                                		<img src="kaptcha.jpg" height="34" id="kaptchaImage" alt="图片验证码" title="看不清？点击更换验证码" />
                                		</span>
                                	</div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 col-md-4 col-lg-4 control-label">短信验证码</label>
                                <div class="col-sm-6 col-md-6 col-lg-6 form-required">
                                	<div class="input-group">
                                		<input value="" id="mCheckCode" class="form-control" name="mCheckCode" placeholder="请输入验证码">
                                		<span class="input-group-btn">
                                		<button type="button" class="btn btn-success register_but" style="width:100%;" onclick="checkMobile();return false;">获取验证码</button>
                                		</span>
                                	</div>
                                </div>
                            </div>
                           <div class="col-sm-6 com-md-6 com-lg-6 col-sm-offset-4 col-md-offset-4 col-lg-offset-4">
                                <button class="btn btn-success update btn-block" type="submit">提交</button>
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
    <script src="static/js/plugins/countdown/jquery.countdown.min.js"></script>
    <!-- 表单验证 -->
    <script src="static/js/plugins/form.validation/js/formValidation.js"></script>
    <script src="static/js/plugins/form.validation/js/framework/bootstrap.js"></script>
    <script src="static/js/plugins/form.validation/js/language/zh_CN.js"></script> 
	<!-- App scripts -->
	<script src="static/bootstrap/scripts/homer.js"></script>
    <script src="static/bootstrap/scripts/charts.js"></script>
	
	 <script type="text/javascript" src="static/js/jquery.cityselect.js"></script>

<script>
$(function (){
	 
	
	$(".register_but, [name=mCheckCode]").prop('disabled', true).addClass('disabled');

    $('#kaptchaImage')
    .css({
    	cursor: 'pointer'
    })
    .on('click', function() {
        $(this).attr('src', 'kaptcha.jpg?' + Math.floor(Math.random()*100) );
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
    


} // checkMobile

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
            	mobile:{
            		validators: {
            			notEmpty: {
            				message: '请输入手机号码'
            			},
            			phone: {
                            message: '请输入有效的手机号码',
                            country: 'CN'
                        }
            		},
            		onSuccess: function(e, data){
            			if( $('#gCheckCode').val()!='' ) $(".register_but, [name=mCheckCode]").prop('disabled', false).removeClass('disabled')
            		},
            		onError: function(e, data){
            			$(".register_but, [name=mCheckCode]").prop('disabled', true).addClass('disabled')
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
                        },
                        identical: {
		                    field: 'confirmNewPassword',
		                    message: '两次密码输入不一致'
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
                },
                gCheckCode: {
                	validators: {
                		notEmpty: {
                			message: '请输入图片验证码'
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
                			message: '请输入短信验证码'
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
            url: 'newPassword',
            type: 'POST',
            dataType : 'json',
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
	                        location.href="to_index";
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
    


/*function resetPwd(){
	if($("#mobile").val() == ''){
		alert("请输入手机号码");
		return false;
	}
	if($("#newPassword").val() == ''){
		alert("请输入密码");
		return false;
	}
	if($("#confirmNewPassword").val() == ''){
		alert("请确认密码");
		return false;
	}
	if($("#newPassword").val() != $("#confirmNewPassword").val()){
		alert("两次输入密码不一致");
		return false;
	}
	if($("#gCheckCode").val() == ''){
		alert("请输入图片校验码");
		return false;
	}
	if($("#mCheckCode").val() == ''){
		alert("请输入短信校验码");
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
}*/
</script>
</body>
</html>