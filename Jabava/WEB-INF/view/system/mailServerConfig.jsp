<%@ page contentType="text/html; charset=utf-8" %>
<%
    System.out.println("OK");
%>
<!DOCTYPE html>
<html>
<head>
	<base href="${pageContext.request.contextPath}/">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <!-- Page title -->
    <title>组织人事-显示设置</title>
    <jsp:include flush="true" page="../common/styles.jsp"></jsp:include>
</head>
<body >
<!--splash screen-->
<jsp:include flush="true" page="../common/splashscreen.div.jsp"></jsp:include>
<!--引入头文件 开始--> 
<jsp:include flush="true" page="../common/header.div.jsp"></jsp:include>
<!--引入头文件 结束--> 
<!--引入菜单文件 开始--> 
<jsp:include flush="true" page="../common/menu.div.jsp"></jsp:include>
<!--引入菜单文件 结束--> 
  
<!-- Main Wrapper -->
<div id="wrapper">
    <div class="normalheader transition animated fadeIn small-header">
        <div class="hpanel">
            <div class="panel-body">
                <div id="hbreadcrumb" class="m-t-xs m-b-xs">
                    <h2 class="font-normal m-b-xs text-center">
                        发信箱设置
                    </h2>
                </div>
            </div>
        </div>
    </div>
    <!-- 放主要内容 -->
    <div class="content animate-panel">
        <div class="row">
            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                <div class="hpanel">
                    <div class="panel-heading hbuilt jabava-built p-lg text-center">
                        <h4 class="m-n font-bold">
                            <p class="small font-light m-t-md"> 
                                （该邮箱用于公司工资单推送消息同时发送给员工时的发件邮箱使用）
                            </p>
                        </h4>
                    </div>
                    <div class="panel-body">
                        <div class="col-sm-offset-3 col-md-offset-3 col-lg-offset-3 col-sm-6 col-md-6 col-lg-6">
                            <form class="form-horizontal" action="" data-id="validate" id="mailConfigId">
                                <div class="form-group">
                                    <label class="control-label col-sm-4 col-md-4 col-lg-4">邮箱类型：</label>
                                    <div class="col-sm-8 col-md-8 col-lg-8">
                                        <div class="input-group-static">
                                            <p class="form-control-static">SMTP</p>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4 col-md-4 col-lg-4">发信箱：</label>
                                    <div class="col-sm-8 col-md-8 col-lg-8 form-required">
                                        <input type="text" placeholder="请输入公司邮箱账号" name="sendTo" class="form-control">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4 col-md-4 col-lg-4">邮箱密码：</label>
                                    <div class="col-sm-8 col-md-8 col-lg-8 form-required">
                                        <input type="password" name="mailPassword" placeholder="请输入公司邮箱密码" class="form-control">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4 col-md-4 col-lg-4">发信箱服务器：</label>
                                    <div class="col-sm-8 col-md-8 col-lg-8 form-required">
                                        <input type="text" name="mailServer" placeholder="例如：smtp.163.com" class="form-control">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4 col-md-4 col-lg-4">是否使用安全协议SSL：</label>
                                    <div class="col-sm-8 col-md-8 col-lg-8">
                                        <div class="form-control-static">
                                          <div class="radio radio-info radio-inline">
                                              <input type="radio" value="1" name="safeFlag" class="form-control">
                                              <label for="inlineRadio1">是</label>
                                          </div>
                                          <div class="radio radio-info radio-inline">
                                              <input type="radio" value="0" name="safeFlag" class="form-control" checked="true">
                                              <label for="inlineRadio1">否</label>
                                          </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4 col-md-4 col-lg-4">端口：</label>
                                    <div class="col-sm-8 col-md-8 col-lg-8">
                                        <input type="text" name="mailPort" value="25" class="form-control">
                                    </div>
                                </div>
                                <div class="form-group text-center">
                                    <button class="btn btn-info m-r" type="submit">连接测试并保存</button>
                                    <button class="btn btn-success" type="button" data-action="reset">重置</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div> 

    <!-- 放页脚  开始-->
    <jsp:include flush="true" page="../common/foot.div.jsp"></jsp:include>
    <!-- 放页脚  结束-->
</div>

<!-- Vendor scripts -->
<script src="static/bootstrap/vendor/jquery/dist/jquery.min.js"></script>
<script src="static/bootstrap/vendor/jquery-ui/jquery-ui.min.js"></script>
<script src="static/bootstrap/vendor/slimScroll/jquery.slimscroll.min.js"></script>
<script src="static/bootstrap/vendor/bootstrap/dist/js/bootstrap.min.js"></script>
<script src="static/bootstrap/vendor/metisMenu/dist/metisMenu.min.js"></script>
<script src="static/bootstrap/vendor/iCheck/icheck.min.js"></script>
<script src="static/bootstrap/vendor/sparkline/index.js"></script>
<script src="static/bootstrap/vendor/moment/moment.js"></script>
<script src="static/bootstrap/scripts/homer.js"></script>
<!-- alert -->
<script src="static/bootstrap/vendor/sweetalert/lib/sweet-alert.min.js"></script>
<script src="static/js/template.js"></script>

<!-- 表单验证 -->
<script src="static/js/plugins/form.validation/js/formValidation.js"></script>
<script src="static/js/plugins/form.validation/js/framework/bootstrap.js"></script>
<script src="static/js/plugins/form.validation/js/language/zh_CN.js"></script> 

<script>
	(function($){
        function init(){
            var validateOptions = {
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
                    	sendTo: {
                            validators: {
                                notEmpty: {
                                    message: '请填写必填项目'
                                },
                                regexp: {
                                    message: '请输入有效的邮箱',
                                    regexp: /^([a-zA-Z0-9_\-\.])+@(\w)+((\.\w+)+)$/
                                }
                            }
                        },
                        mailPassword: {
                            validators: {
                                notEmpty: {
                                    message: '请填写必填项目'
                                }
                            }
                        },
                        mailServer: {
                            validators: {
                                notEmpty: {
                                    message: '请填写必填项目'
                                }
                            }
                        },
                        mailPort:{
                            validators: {
                                notEmpty: {
                                    message: '请填写必填项目'
                                }
                            }
                        }
                    }
                };

            $('[data-id="validate"]').formValidation(validateOptions).on('success.form.fv', function(event) {
                event.preventDefault();
                saveMail();
            });

            $('[data-action="reset"]').on('click', function(e) {
                var getForm = $('[data-id="validate"]');
                getForm.formValidation('resetForm');
                getForm[0].reset();
            });
            
            initMailMsg();
        };
        init();
    })(jQuery);
    
	function initMailMsg(){
		$.ajax({
            url : "system/loadMailConfig",
            dataType:'json',
            type : 'post',
            success : function(data) {
            	if(data.success){
            		var result=data.result;
            		var checked=data.safeFlag;
            		$("input[name='sendTo']").val(result.sendTo);
            		$("input[name='mailPassword']").val(result.mailPassword);
            		$("input[name='mailServer']").val(result.mailServer);
            		$("input[name='safeFlag'][value="+checked+"]").attr("checked",true); 
            		$("input[name='mailPort']").val(result.mailPort);
            	}
            	
            }
        });
	}
	
     function saveMail(){
            var resetForm = function(){
                var getForm = $('[data-id="validate"]');
                getForm.formValidation('resetForm');
            }
            $.ajax({
                url : "system/saveMailConfig",
                data : $("#mailConfigId").serialize(),
                dataType:'json',
                type : 'post',
                async : false
            })
            .done(function(data){
                if (data.success) {
                    swal({
                        title: '',
                        text: data.msg,
                        type: "success"
                    },function(d){
                        resetForm();
                    })
                }else{
                    swal({
                        title: '',
                        text: data.msg,
                        type: "error"
                    },function(d){
                        resetForm();
                    })
                }
            })
        };
</script>
</body>
</html>
