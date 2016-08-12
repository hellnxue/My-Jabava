<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="com.jabava.utils.RequestUtil"%>
<!DOCTYPE html>
<html>
<head>
<base href="${pageContext.request.contextPath}/">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge">

<!-- Page title -->
<title>Jabava上传增减变表界面</title>
<jsp:include flush="true" page="../common/styles.jsp"></jsp:include>
<!--for 临时改变-->
<link rel="stylesheet" href="static/js/plugins/form.validation/css/formValidation.css">
<link rel="stylesheet" href="static/css/user.css">

<script>
 
  function downloadFile(path) {
		window.open(path);
	}
</script>
</head>
<body>

	<!--splash screen-->
    <jsp:include flush="true" page="../common/splashscreen.div.jsp"></jsp:include>

	<!--引入头文件 开始--> 
    <jsp:include flush="true" page="../common/header.div.jsp"></jsp:include>
    <!--引入头文件 结束-->
     
    <!--引入菜单文件 开始--> 
    <jsp:include flush="true" page="../common/menu.div.jsp"></jsp:include>
    <!--引入菜单文件 结束--> 

	<!-- 放主要内容  开始-->
	<!-- Main Wrapper -->
	<div id="wrapper">

		<!-- 放主要内容 -->
        <div class="content animate-panel">
            <div class="row">
                <div class="col-lg-12">
                    <div class="hpanel clearfix">
					  <ul class="nav nav-tabs" role="tablist">
					    <li role="presentation" class="active"><a id="upTab" href="#home" aria-controls="home" role="tab" data-toggle="tab">上传增减变表</a></li>
					    <li role="presentation"><a id="okTab" href="#profile" aria-controls="profile" role="tab" data-toggle="tab">一键外包</a></li>
					  </ul>

					  <!-- Tab panes -->
					  <div class="tab-content">
					    <div role="tabpanel" class="tab-pane active" id="home">
					    	<div class="panel-heading hbuilt jabava-built p-lg">
					    		<h4 class="m-n font-bold">
					    		<p class="small font-light m-t-md">企业可下载增减变表模版，将该月需要或不需要缴纳社保人员信息以及需要更改社保人员信息等整理到模版中，上传到HRO客户端，无需联系客服操作。</p>
					    		</h4>
					    	</div> 
					        <div class="panel-body">
					            <div class="col-md-7 col-md-offset-2">
					            	<form class="form-horizontal" id="uploadForm">
					            		<input type="hidden" id="fileUrl" name="attachment">
					            		<div class="form-group">
									        <label for="" class="control-label col-lg-3">上传增减变表：</label>
									        <div class="col-lg-9">
									        	<div class="input-group" data-toggle="upload:file">
									        	    <input type="text" class="form-control" readonly="readonly">
									        	    <div class="input-group-btn">
									        	        <span class="btn btn-default">浏览...
									        	        <input type="file" class="sr-only"  id='myfiles' name='myfiles'  accept=".xlsx" ></span>
									        	    </div>
									        	</div>
									        </div>
									    </div>
					            		<div class="form-group">
					            			<label for="inputPassword3" class="col-lg-3 control-label font-bold">备注：</label>
					            			<div class="col-lg-9">
					            				<textarea type="text" class="form-control" id="addRemark" name="remark" ></textarea>
					            			</div>
					            		</div>
					            		<% if(RequestUtil.hasPower("ascupload_ul")){ %>
					            		<div class="form-group">
					            			<div class="col-lg-9 col-lg-offset-3">
					            				<button id="upload" class="btn btn-block btn-success demo2" type="button">上　传</button>
					            			</div>
					            		</div>
					            		<% } %>
					            		<div class="form-group">
					                		<div class="col-lg-9 col-lg-offset-3 text-right">
					            				<a class="btn btn-link inc_dowmload" onclick="downloadFile('static/xls/template.xlsx');">下载增减变模板</a>
					                		</div>
					            		</div>
					            	</form>
					            </div>
					        </div>                        
					    </div>
					    <div role="tabpanel" class="tab-pane" id="profile">
					        <div class="panel-body">
					            <div class="col-md-7 col-md-offset-2">
					            	<form class="form-horizontal" data-form="validator" id="oneKeyForm">
					            		<input type="hidden" id="reportId" name="reportId" value="13">
					            		<div class="form-group">
									        <label for="" class="control-label col-lg-3">月份：</label>
									        <div class="col-lg-7 form-required">
										        <div class="input-group date">
						                          <input type="text" class="form-control" id="m" name="month">
						                          <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
						                        </div>
					                    	</div>
									    </div>
					            		<div class="form-group">
					            			<label for="inputPassword3" class="col-lg-3 control-label font-bold">社保账户：</label>
					            			<div class="col-lg-7">
					            				<select class="form-control" id="ssAccountId" name="socialSecurityAccountId" >
					            					<option value="">请选择</option>
					            					<c:forEach items="${requestScope.ssAccountList }" var="ssAccount">
					            					<option value="${ssAccount.socialSecurityAccountId }">${ssAccount.socialSecurityAccountName }</option>
					            					</c:forEach>
					            				</select>
					            			</div>
					            			<div class="col-lg-2">
					            				<p class="form-control-static"><a href="javascript://" data-for="socialSecurityAccountId" data-rel="month">查看明细</a></p>
					            			</div>
					            		</div>
					            		<div class="form-group">
					            			<label for="inputPassword3" class="col-lg-3 control-label font-bold">公积金账户：</label>
					            			<div class="col-lg-7">
					            				<select class="form-control" id="afAccountId" name="accumulationFundAccountId" >
					            					<option value="">请选择</option>
					            					<c:forEach items="${requestScope.afAccountList }" var="afAccount">
					            					<option value="${afAccount.accumulationFundAccountId }">${afAccount.accumulationFundAccountName }</option>
					            					</c:forEach>
					            				</select>
					            			</div>
					            			<div class="col-lg-2">
					            				<p class="form-control-static"><a href="javascript://" data-for="accumulationFundAccountId" data-rel="month">查看明细</a></p>
					            			</div>
					            		</div>
					            		<% if(RequestUtil.hasPower("ascupload_ok")){ %>
					            		<div class="form-group">
					            			<div class="col-lg-9 col-lg-offset-3">
					            				<button class="btn btn-block btn-success demo2" type="submit">一键外包</button>
					            			</div>
					            		</div>
					            		<% } %>
					            	</form>
					            </div>
					        </div>
					    </div>
					  </div>
       				</div>
        		</div>
        	</div>
        </div> 
              
        <!--主要内容结束-->

		<!-- Footer-->
		<!-- 放页脚  开始-->
        <jsp:include flush="true" page="../common/foot.div.jsp"></jsp:include>
        <!-- 放页脚  结束-->

	</div>
	<form class="sr-only" id="paymentBillForm" method="post">
		<input type="hidden" id="from" name="oneKey">
		<input type="hidden" id="ssPaymentBillId" name="ssPaymentBillId">
		<input type="hidden" id="afPaymentBillId" name="afPaymentBillId">
	</form>

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
	<script src="static/bootstrap/vendor/bootstrap-datepicker-master/dist/js/bootstrap-datepicker.js"></script>

	<!-- alert -->
	<script src="static/bootstrap/vendor/sweetalert/lib/sweet-alert.min.js"></script>
    
	<!-- App scripts -->
	<script src="static/bootstrap/scripts/homer.js"></script>
    <script src="static/bootstrap/scripts/charts.js"></script>
    <!-- 表单验证 -->
	<script src="static/js/plugins/form.validation/js/formValidation.js"></script>
	<script src="static/js/plugins/form.validation/js/framework/bootstrap.js"></script>
	<script src="static/js/plugins/form.validation/js/language/zh_CN.js"></script>
    
    <!--jabava-->
    <script src="static/js/change/ajaxfileupload.js"></script>
    <script src="static/js/change/change.js"></script>

<script>
jQuery.prototype.serializeObject=function(){  
    var obj=new Object();  
    $.each(this.serializeArray(),function(index,param){  
      if(!(param.name in obj)){  
        obj[param.name]=param.value;  
      }  
    });  
    return obj;  
};

function getFileType(obj){
	
    photoExt=obj.value.substr(obj.value.lastIndexOf(".")).toLowerCase();//获得文件后缀名	
    if(photoExt!='.xlsx'){
        alert("请上传后缀名为xlsx的文件!");
        return false;
    }
   
}
$(function (){
	 var socialSecurityAccountId = '${requestScope.socialSecurityAccountId }';
	 var accumulationFundAccountId = '${requestScope.accumulationFundAccountId }';
	 if(socialSecurityAccountId || accumulationFundAccountId){
		 if(socialSecurityAccountId){
			 $('#ssAccountId').val(socialSecurityAccountId);
		 }
		 if(accumulationFundAccountId){
			 $('#afAccountId').val(accumulationFundAccountId);
		 }
		 $('#m').val('${requestScope.month }');
		 //$('#okTab').addClass('active');
		 $('#okTab').click();
	 }else{
		 //$('#upTab').addClass('active');
	 }
	
	 $.ajax({
			type : 'post',
			url : "change/getProtocolCode",			
			async : false,
			success : function(result) {
				if(result!='error'){					
	            	$("#showProcotolCode").val(result);	
				}else{
					alert('该公司没有协议号!');
					//返回到上一页
					window.location.href=document.referrer;
				}
				
			}
		}); 

		 $('#upload').on('click', function() {
			 
			var fileName = $('[data-toggle="upload:file"] :file').val();
			
			if(fileName==''){
				alert('请选择文件');
				return false;
			}
			 photoExt=fileName.substr(fileName.lastIndexOf(".")).toLowerCase();//获得文件后缀名	
			    if(photoExt!='.xlsx'){
			        alert("请上传后缀名为xlsx的文件!");
			        return false;
			    }
		    	  $.ajaxFileUpload({
		            url: 'change/uploadFile', //用于文件上传的服务器端请求地址
		            secureuri: false, //是否需要安全协议，一般设置为false
		            fileElementId: ['myfiles'], //文件上传域的ID    
		            async:false,
		            dataType:'text',
		            type:"POST",
		            success: function (result){  //服务器成功响应处理函数
		            	
		            	$("#fileUrl").val(result.fileUrl);			            	
		            	 $.ajax({
		     				type : 'post',
		     				url : "change/addChange",
		     				data : $("#uploadForm").serialize(),
		     				async : false,
		     				dataType:'json',
		     				success : function(message) {
		     				 
		     					if(message.message=='success'){
		     						
		     						swal({
		    		                    title: "恭喜您上传成功!",
		    		                    text: "",
		    		                    type: "success",
		    		    				confirmButtonText: "确定"
		    		                }, function(){
			     						window.location.href="client/list_query_change";
		    		                });


		     					}else{
		     						swal({
		    		                    title: "上传失败!",
		    		                    text: message.message,
		    		                    type: "error",
		    		    				confirmButtonText: "确定"
		    		                });
		     					}
		     				}
		     			});           		
		            } 
	      		}); 
	    	});

		//一键上传验证
		var validators = {
		 	err: {
		          container: 'tooltip'
		    },
		    icon: {
		        valid: 'glyphicon glyphicon-ok',
		        invalid: 'glyphicon glyphicon-remove',
		        validating: 'glyphicon glyphicon-refresh'
		    },
		    fields: {
		        month: {
		            validators: {
		                notEmpty: {
		                    message: '日期格式不正确',
                            regexp: /^\d{1,2}$/
		                }
		            }
		        },
		        //protocolCode: {
		        //    validators: {
		        //        notEmpty: {
		        //            message: '请填写必填项目'
		        //        }
		        //    }
		        //},
		        socialSecurityAccountId: {
		        	// enabled: false,
		            validators: {
		                notEmpty: {
		                    message: '社保账号不能为空'
		                }
		            }
		        },
		        accumulationFundAccountId: {
		        	// enabled: false,
		            validators: {
		                notEmpty: {
		                    message: '公积金账号不能为空'
		                }
		            }
		        }
		    }
		}

		$('.input-group.date')
		.datepicker({
		    format: "yyyymm",
		    minViewMode: 1,
		    language: "zh-CN",
		    autoclose: true
		})
		.on('changeDate',function(e){
			var getEleName = $(e.target).find(':text').attr('name');
            $('[data-form="validator"]').formValidation('revalidateField', getEleName);
		});

		$('[data-form="validator"]').formValidation(validators)
		.on('success.form.fv', function(e){
			e.preventDefault();
			/*var getSecurityVal = $('[name="socialSecurityAccountId"]').val(),
				getFundVal = $('[name="accumulationFundAccountId"]').val()

			if(getSecurityVal == '' && getFundVal == ''){
				$('[data-form="validator"]')
				.formValidation('enableFieldValidators', 'socialSecurityAccountId', true)
				.formValidation('revalidateField','socialSecurityAccountId');
				return false;
			}*/
			
			 $.ajax({
				type : 'post',
				url : "change/generateAddChange",
				data : $('#oneKeyForm').serializeObject(),
                dataType:'json',
				success : function(result) {
					if(result.success){
 						
 						swal({
		                    title: "恭喜您上传成功!",
		                    text: "",
		                    type: "success",
		    				confirmButtonText: "确定"
		                }, function(){
     						window.location.href="client/list_query_change";
		                });

 					}else{
 						swal({
		                    title: "上传失败!",
		                    text: result.msg,
		                    type: "error",
		    				confirmButtonText: "确定"
		                });
 						
 					}
				}
			});
		})
		$('[data-for]').on('click', function(event){
			event.preventDefault();
			var $getRel = $('[name="'+$(this).data('rel')+'"]');
			var $getTarget = $('[name="'+$(this).data('for')+'"]');
			if($getRel.val() == ''){
				swal({
					title: '请选择月份',
					type: 'error'
				});
			}else if( $getTarget.val() == '' ){
				swal({
					title: $(this).data('for') == 'socialSecurityAccountId' ? '请选择社保账户' : '请选择公积金账户',
					type: 'error'
				});
			}else{
				if($(this).data('for') == 'socialSecurityAccountId'){
					showSsDetail();
				}else{
					showAfDetail();
				}
			}
		});
	});
	
	function showSsDetail(){
		$.ajax({
			type : 'post',
			url : "ssPaymentBill/checkPaymentBill",
			data : {month: $('#m').val(), socialSecurityAccountId: $('#ssAccountId').val()},
            dataType:'json',
			success : function(result) {
				if(result.success){
					$('#ssPaymentBillId').val(result.ssPaymentBillId);
					$('#afPaymentBillId').val('');
					$('#paymentBillForm').attr('action', 'ssPaymentBill/toListPaymentBillPerson');
					$('#paymentBillForm').submit();
				}else{
					swal({
	                    title: result.msg,
	                    type: "error"
	                });
				}
			}
		});
	}
	
	function showAfDetail(){
		$.ajax({
			type : 'post',
			url : "afPaymentBill/checkPaymentBill",
			data : {month: $('#m').val(), accumulationFundAccountId: $('#afAccountId').val()},
            dataType:'json',
			success : function(result) {
				if(result.success){
					$('#ssPaymentBillId').val('');
					$('#afPaymentBillId').val(result.afPaymentBillId);
					$('#paymentBillForm').attr('action', 'afPaymentBill/toListPaymentBillPerson');
					$('#paymentBillForm').submit();
				}else{
					swal({
	                    title: result.msg,
	                    type: "error"
	                });
				}
			}
		});
	}
	
	$('[data-toggle="upload:file"] :file').on('change.file:selected', function(event){
	    var oEventTarget = $(this),
	    	oFile = $(this).val()
	    	fileExt = oFile.substr(oFile.lastIndexOf(".")).toLowerCase()
	
	    if(fileExt!='.xlsx'){
	        alert("请上传后缀名为xlsx的文件!");
	        return;
	    }
	
	    oEventTarget.parents('[data-toggle="upload:file"]')
	    .find(':text').val( oFile )
	})
	
	$('[data-toggle="upload:file"]').on('click', function(event){
	    $(this).find(':file').trigger('click.file:selected')
	});

</script>

</body>
</html>