<%@ page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html>
<head>
	<base href="${pageContext.request.contextPath}/">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <!-- Page title -->
    <title>参保方案</title>
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
    <div class="small-header transition animated fadeIn">
        <div class="hpanel">
            <div class="panel-body text-center">
                参保方案
            </div>
        </div>
    </div>
    <!-- 放主要内容 -->
    <div class="content animate-panel">
        <div class="row">
            <div class="col-lg-12">
                <div class="hpanel">
                    <div class="panel-body">
                        <form data-id="osSecurityplan" action="outsourcing/toApplyOpen" method="post">
                            <input type="hidden" name="productName">
                            <input type="hidden" name="providerName" value="昊基人力">
                            <div class="row">
                                <div class="form-group clearfix">
                                    <div class="col-lg-12">  
                                        <p class="os-text-head">产品</p>
                                    </div> 
                                </div>
                                <div class="form-group clearfix">
                                    <div class="col-lg-offset-2 col-lg-10"> 
                                        <div class="col-lg-6"> 
                                            <a href="javascript://" data-disabled="true" data-type="osBtn" class="btn btn-default os-btn" data-text="社保/公积金代理服务 - 35元/人/月">
                                                社保 / 公积金代理服务  -  35元 / 人 / 月
                                                <i class="hidden os-icon"></i>
                                            </a>
                                        </div>
                                        <div class="col-lg-6"> 
                                            <a href="javascript://" data-disabled="true" data-type="osBtn" class="btn btn-default os-btn" data-text="工资代缴服务1880/年+1元/人/月">
                                                工资代缴服务 1880/年+1元/人/月
                                                <i class="hidden os-icon"></i>
                                            </a>
                                        </div>
                                    </div> 
                                </div>
                                <div class="form-group clearfix">
                                    <div class="col-lg-offset-2 col-lg-10"> 
                                        <div class="col-lg-6"> 
                                            <a href="javascript://" data-disabled="true" data-type="osBtn" class="btn btn-default os-btn" data-text="社保/公积金代理服务20元/人/月+单项收费">
                                                社保/公积金代理服务20元/人/月+单项收费
                                                <i class="hidden os-icon"></i>
                                            </a>
                                        </div>
                                        <div class="col-lg-6"> 
                                            <a href="javascript://" data-disabled="true" data-type="osBtn" class="btn btn-default os-btn" data-text="工资代缴服务10元/人/月">
                                                工资代缴服务 10元/人/月
                                                <i class="hidden os-icon"></i>
                                            </a>
                                        </div>
                                    </div> 
                                </div>
                            </div>
                            <div class="row">
                                <div class="form-group clearfix">
                                    <div class="col-lg-12">  
                                        <p class="os-text-head">服务商</p>
                                    </div> 
                                </div>
                                <div class="form-group clearfix">
                                    <div class="col-lg-offset-2 col-lg-10"> 
                                        <div class="col-lg-6"> 
                                            <a href="javascript://" class="os-display note-editor">
                                                <img src="static/img/os_securityplan_img01.png" alt="">
                                            </a>
                                        </div>
                                    </div> 
                                </div>   
                            </div>
                            <div class="text-right">
                                <button type="button" class="btn btn-info btn-sm">下一步</button>
                            </div>
                        </form>
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

<script>
	(function($){
        function onlyChoice(){
            $('[data-type="osBtn"]')
            .each(function(index, el) {
                $(this)
                .on('click', function(e){
                    var getThisText = $(this).attr('data-text');

                    $('[data-type="osBtn"]')
                    .removeClass('os-active')
                    .attr('data-disabled', false)
                    .find('i')
                    .addClass('hidden');

                    $(this)
                    .addClass('os-active')
                    .attr('data-disabled', true)
                    .find('i')
                    .removeClass('hidden');

                    $('[data-id="osSecurityplan"]')
                    .find('[name=productName]')
                    .val(getThisText);
                });
            });
        }
        function notChoice(){
            $('[data-id="osSecurityplan"]')
            .find('button')
            .on('click', function(e){
                var getProductName = $('[name=productName]').val(),
                    getProviderName = $('[name=providerName]').val();
                if(getProductName==''){
                    swal({
                        title: "请选择产品",
                        type: "error"
                    });
                }else if(getProviderName==''){
                    swal({
                        title: "请选择服务商",
                        type: "error"
                    });
                }else{
                    applyOpen();
                }
            });
        }
        function init(){
        	$('[name=productName]').val('${requestScope.hroPactInfo.productName }');
        	$('[name=providerName]').val('昊基人力');
        	var getActiveName = $('[name=productName]').val();
            $('[data-text="'+getActiveName+'"]')
            .addClass('os-active')
            .find('i')
            .removeClass('hidden')
            .show();
            onlyChoice();
            notChoice();
        }
        init();
    })(jQuery)
    
    function applyOpen(){
		$.ajax({
			type : 'post',
			url : "syncCertificateStatus",
			//data : {},
			dataType: "json",
			success : function(data) {
				 if(data.success){
				 	if(data.certificateStatus == 0){
				 		//转向认证页面
				 		swal({
							title: '您尚未进行用户认证，请先完成认证',
							showCancelButton: true,
							cancelButtonText: '以后认证',
							confirmButtonText: '现在认证',
				 			type: 'error'
						},function(isConfirm){
							if(isConfirm){
		                        window.location.href = '/common/link/outerlink-uc-certificate';
							}else{
								
							}
						});
				 	}else if(data.certificateStatus == 1){
				 		//转向申请页面
                         $('[data-id=osSecurityplan]').submit();
				 	}else if(data.certificateStatus == 2){
				 		//提示信息
				 		swal({
                             title: '企业认证中，请耐心等待',
                             type: "success"
                        }); 
				 	}
				 }else{
                     swal({
                         title: data.msg,
                         type: "error"
                     });
				 }
			}
		});
	}
</script>

</body>
</html>
