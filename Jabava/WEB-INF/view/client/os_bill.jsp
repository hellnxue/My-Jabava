<%@page import="com.jabava.utils.RequestUtil"%>
<%@ page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html>
<head>
	<base href="${pageContext.request.contextPath}/">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <!-- Page title -->
    <title>服务单查询</title>
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
                        服务单查询
                    </h2>
                </div>
            </div>
        </div>
    </div>
    <!-- 放主要内容 -->
    <div class="content animate-panel">
        <div class="row">
            <div class="col-lg-12">
                <div class="hpanel">
                    <div class="panel-body">
                        <table id="osBill" class="table table-bordered table-hover rost_table" width="100%">                
                            <thead>
                                <tr>
                                    <th>协议号</th>
                                    <th>产品</th>
                                    <th>服务商</th>
                                    <th>状态</th>
                                    <th>联系人</th>
                                    <th>操作</th>
                                </tr>
                            </thead>
                            <tbody>
                               
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div> 
    
    <!-- model -->
    <div class="modal fade hmodal-success form-row in" data-id="edit">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="color-line"></div>
                <div class="modal-header">
                    <div class="row">
                        <div class="panel-body">
                            <div class="col-lg-12 m-b-lg">
                                协议号：<span id="pactCodeEdit">223232323232</span>
                                <div class="pull-right">
                                    协议状态：<span id="stateEdit">未启用</span>
                                </div>
                            </div>
                            <form class="form-horizontal" data-id="validator">
                            	<input type="hidden" id="idEdit" name="id">
                                <div class="col-lg-12">
                                    <div class="form-group">
                                        <label class="control-label col-sm-3 col-md-3 col-lg-3">产品</label>
                                        <div class="col-sm-8 col-md-8 col-lg-8 form-required">
                                            <select class="form-control" id="productNameEdit" name="productName">
                                                <option value="社保/公积金代理服务 - 35元/人/月">社保/公积金代理服务 - 35元/人/月</option>
                                                <option value="工资代缴服务1880/年+1元/人/月">工资代缴服务1880/年+1元/人/月</option>
                                                <option value="社保/公积金代理服务20元/人/月+单项收费">社保/公积金代理服务20元/人/月+单项收费</option>
                                                <option value="工资代缴服务10元/人/月">工资代缴服务10元/人/月</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-12">
                                    <div class="form-group">
                                        <label class="control-label col-sm-3 col-md-3 col-lg-3">服务商</label>
                                        <div class="col-sm-8 col-md-8 col-lg-8 form-required">
                                            <select class="form-control" id="providerNameEdit" name="providerName">
                                                <option value="昊基人力">昊基人力</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-12">
                                    <div class="form-group">
                                        <label class="control-label col-sm-3 col-md-3 col-lg-3">服务城市</label>
                                        <div class="col-sm-8 col-md-8 col-lg-8 form-required">
                                            <input class="form-control" type="text" id="serviceCityEdit" name="serviceCity">
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-12">
                                    <div class="form-group">
                                        <label class="control-label col-sm-3 col-md-3 col-lg-3">服务人数</label>
                                        <div class="col-sm-8 col-md-8 col-lg-8 form-required">
                                            <input class="form-control" type="text" id="servicePersonNumEdit" name="servicePersonNum">
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-12">
                                    <div class="form-group">
                                        <label class="control-label col-sm-3 col-md-3 col-lg-3">服务起始日期</label>
                                        <div class="col-sm-8 col-md-8 col-lg-8 form-required">
                                            <div class="input-group date">
                                                <input type="text" value="" id="serviceStartDateEdit" name="serviceStartDate" class="form-control">
                                                <span class="input-group-addon" style=""><i class="glyphicon glyphicon-th"></i></span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-12">
                                    <div class="form-group">
                                        <label class="control-label col-sm-3 col-md-3 col-lg-3">客户名称</label>
                                        <div class="col-sm-8 col-md-8 col-lg-8 form-required">
                                            <input class="form-control" readonly="readonly" type="text" id="companyNameEdit" name="companyName">
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-12">
                                    <div class="form-group">
                                        <label class="control-label col-sm-3 col-md-3 col-lg-3">联系人</label>
                                        <div class="col-sm-8 col-md-8 col-lg-8 form-required">
                                            <input class="form-control" type="text" id="contactEmpEdit" name="contactEmp">
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-12">
                                    <div class="form-group">
                                        <label class="control-label col-sm-3 col-md-3 col-lg-3">联系电话</label>
                                        <div class="col-sm-8 col-md-8 col-lg-8 form-required">
                                            <input class="form-control" type="text" id="telephoneNumberEdit" name="telephoneNumber">
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-12">
                                    <div class="form-group">
                                        <label class="control-label col-sm-3 col-md-3 col-lg-3">email</label>
                                        <div class="col-sm-8 col-md-8 col-lg-8">
                                            <input class="form-control" type="text" id="emailEdit" name="email">
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-12 text-center">
                                    <button type="submit" class="btn btn-success m-r-xs">修改</button>　
                                    <button type="button" class="btn btn-default guanbi">取消</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="modal fade hmodal-success form-row in" data-id="view">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="color-line"></div>
                <div class="modal-header">
                    <div class="row">
                        <div class="panel-body">
                            <div class="col-lg-12 m-b-lg">
                                协议号：<span id="pactCodeView">223232323232</span>
                                <div class="pull-right">
                                    协议状态：<span id="stateView">未启用</span>
                                </div>
                            </div>
                            <form class="form-horizontal">
                                <div class="col-lg-12">
                                    <div class="form-group">
                                        <label class="control-label col-sm-4 col-md-4 col-lg-4">产品</label>
                                        <div class="col-sm-8 col-md-8 col-lg-8">
                                            <div class="input-group-static">
                                                <p class="form-control-static" id="productNameView">
                                                    产品1
                                                </p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-12">
                                    <div class="form-group">
                                        <label class="control-label col-sm-4 col-md-4 col-lg-4">服务商</label>
                                        <div class="col-sm-8 col-md-8 col-lg-8">
                                            <div class="input-group-static">
                                                <p class="form-control-static" id="providerNameView">
                                                    服务商1
                                                </p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-12">
                                    <div class="form-group">
                                        <label class="control-label col-sm-4 col-md-4 col-lg-4">服务城市</label>
                                        <div class="col-sm-8 col-md-8 col-lg-8">
                                            <div class="input-group-static">
                                                <p class="form-control-static" id="serviceCityView">
                                                    上海
                                                </p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-12">
                                    <div class="form-group">
                                        <label class="control-label col-sm-4 col-md-4 col-lg-4">服务人数</label>
                                        <div class="col-sm-8 col-md-8 col-lg-8">
                                            <div class="input-group-static">
                                                <p class="form-control-static" id="servicePersonNumView">
                                                    11人
                                                </p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-12">
                                    <div class="form-group">
                                        <label class="control-label col-sm-4 col-md-4 col-lg-4">服务起始日期</label>
                                        <div class="col-sm-8 col-md-8 col-lg-8">
                                            <div class="input-group-static">
                                                <p class="form-control-static" id="serviceStartDateView">
                                                    2016年7月7号
                                                </p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-12">
                                    <div class="form-group">
                                        <label class="control-label col-sm-4 col-md-4 col-lg-4">公司名称</label>
                                        <div class="col-sm-8 col-md-8 col-lg-8">
                                            <div class="input-group-static">
                                                <p class="form-control-static" id="companyNameView">
                                                    上海锐五科技有限公司
                                                </p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-12">
                                    <div class="form-group">
                                        <label class="control-label col-sm-4 col-md-4 col-lg-4">联系人</label>
                                        <div class="col-sm-8 col-md-8 col-lg-8">
                                            <div class="input-group-static">
                                                <p class="form-control-static" id="contactEmpView">
                                                    张小姐
                                                </p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-12">
                                    <div class="form-group">
                                        <label class="control-label col-sm-4 col-md-4 col-lg-4">联系方式</label>
                                        <div class="col-sm-8 col-md-8 col-lg-8">
                                            <div class="input-group-static">
                                                <p class="form-control-static" id="telephoneNumberView">
                                                    11111111111
                                                </p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-12">
                                    <div class="form-group">
                                        <label class="control-label col-sm-4 col-md-4 col-lg-4">email</label>
                                        <div class="col-sm-8 col-md-8 col-lg-8">
                                            <div class="input-group-static">
                                                <p class="form-control-static" id="emailView">
                                                    0000@ezhiyang.com
                                                </p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-12 text-center">
                                    <button type="button" class="btn btn-success guanbi">关闭</button>　
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
<script src="static/bootstrap/vendor/bootstrap-datepicker-master/dist/js/bootstrap-datepicker.js"></script>
<!-- for datatable -->
<script src="static/bootstrap/vendor/datatables/media/js/jquery.dataTables.min.js"></script>
<script src="static/bootstrap/vendor/datatables_plugins/integration/bootstrap/3/dataTables.bootstrap.min.js"></script>
<!-- alert -->
<script src="static/bootstrap/vendor/sweetalert/lib/sweet-alert.min.js"></script>
<script src="static/bootstrap/scripts/homer.js"></script>
<!-- 表单验证 -->
<script src="static/js/plugins/form.validation/js/formValidation.js"></script>
<script src="static/js/plugins/form.validation/js/framework/bootstrap.js"></script>
<script src="static/js/plugins/form.validation/js/language/zh_CN.js"></script> 
<script>
	(function($){
        function initTable(){
            var table = $('#osBill').dataTable({
                "dom":
                    "<'row'<'col-sm-6'l><'col-sm-5'f><'col-sm-1'<'toolbar'>>>" +
                    "<'row'<'col-sm-12 table-responsible'tr>>" +
                    "<'row'<'col-sm-5'i><'col-sm-7'p>>",
                
                "processing": true,
       	        "serverSide": false,
       	        "bDestroy": true,
        		"sServerMethod": "POST",
       	        "ajax": {
       		        "url":"outsourcing/queryProtocol",
       			},
       			"columns": [
       				{ "data": "pactCode" },
	      			{ "data": "productName" },
	      			{ "data": "providerName" },
	      			{ "data": "state", render:function( data, type, row, meta ){
	      				if(data == -1){
	      					return '未提交';
	      				}else if(data == 0){
	      					return '未审核';
	      				}else if(data == 2){
	      					return '已审核';
	      				}else if(data == 3){
	      					return '停用';
	      				}
	      			} },
	      			{ "data": "contactEmp" },
	   				{ "render": function render( data, type, row, meta ){
	   		            var strHtml = '';
	                    <% if(RequestUtil.hasPower("protocolquery_vp")){ %>
	   	                strHtml += '<div class="btn-group">';
	   	                strHtml += '  <button type="button" class="btn btn-info btn-xs dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">操作 <span class="caret"></span>';
	   	                strHtml += '  </button>';
	   	                strHtml += '  <ul class="dropdown-menu dropdown-menu-right">';
	   	                strHtml += '    <li><a href="javascript://" onclick="view(\'' + row.id + '\')">查看</a></li>';
		                if(row.state == -1){ //状态为未提交
	   	                  	strHtml += '    <li><a href="javascript://" onclick="mod(\'' + row.id + '\')">修改</a></li>';
	   	                  	strHtml += '    <li><a href="javascript://" onclick="operate(\'' + row.id + '\',\'outsourcing/submitOutsourcing\')">提交</a></li>';
	   	                  	strHtml += '    <li><a href="javascript://" onclick="operate(\'' + row.id + '\',\'outsourcing/deleteOutsourcing\')">删除</a></li>';
		                }
	   	                strHtml += '  </ul>';
	   	                strHtml += '</div>';
	   		            <% } %>
	                 	return strHtml;
	   				} }
    			],
        		"columnDefs": [
        			{defaultContent: '', targets: '_all'}
    			],
                "language": {
                    "search": "过滤:",
                    "processing":"数据加载中",
                    "lengthMenu": "每页显示 _MENU_ 条记录",
                    "zeroRecords": "暂无数据 - 报歉啦〜",
                    "info": "显示 第 _PAGE_ 页 共 _PAGES_ 页",
                    "infoEmpty": "暂无数据",
                    "infoFiltered": "(筛选自 _MAX_ 条记录)",
                    "paginate":{
                        "first":"首页",
                        "previous":"前一页",
                        "next":"后一页",
                        "last":"尾页"
                    }
                 }
            })
            $("div.toolbar").html('<a href="outsourcing/toServiceOpen" class="btn btn-success  btn-sm"><i class="fa fa-users"></i> 申请开通</a>');
        }
        function dateInit(){
           $('.input-group.date').datepicker({
                format: "yyyy-mm-dd",
                autoclose: true
            }); 
        }
        function closeButton(){
            $(".guanbi").click(function(){
                $("[data-id=view]").modal('hide');
                $("[data-id=edit]").modal('hide');
            });
        }
        function remove(){
            $('.del-button').click(function () {
                swal({
                    title: "确定删除吗？",
                    type: "warning",
                    showCancelButton: true,
                    confirmButtonColor: "#DD6B55",
                    confirmButtonText: "是",
                    cancelButtonText: "不",
                    closeOnConfirm: false,
                    closeOnCancel: false },
                function (isConfirm) {
                    if (isConfirm) {
                        swal("已经删除");
                    } else {
                        swal("已经取消");
                    }
                });
            });
        }
        function validateForm(){
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
                        serviceStartDate: {
                            validators: {
                                notEmpty: {
                                    message: '请填写必填项目'
                                },
                                date: {
                                  format: 'YYYY-MM-DD',
                                  message: '该日期是无效的'
                                }
                            }
                        },
                        serviceCity: {
                            validators: {
                                notEmpty: {
                                    message: '请填写必填项目'
                                }
                            }
                        },
                        servicePersonNum: {
                            validators: {
                                notEmpty: {
                                    message: '请填写必填项目'
                                },
                                between: {
                                    message: '输入的值必须大于0',
                                    max: 10000000,
                                    min: 1
                                }
                            }
                        },
                        companyName: {
                            validators: {
                                notEmpty: {
                                    message: '请填写必填项目'
                                }
                            }
                        },
                        contactEmp: {
                            validators: {
                                notEmpty: {
                                    message: '请填写必填项目'
                                }
                            }
                        },
                        telephoneNumber: {
                            validators: {
                                notEmpty: {
                                    message: '请填写必填项目'
                                },
                                phone: {
                                    message: '请输入有效的电话号码',
                                    country: 'CN'
                                }
                            }
                        },
                        productName: {
                            validators: {
                                notEmpty: {
                                    message: '请填写必填项目'
                                }
                            }
                        },
                        providerName: {
                            validators: {
                                notEmpty: {
                                    message: '请填写必填项目'
                                }
                            }
                        },
                        email: {
                            enabled: false,
                            validators: {
                                notEmpty: {
                                    message: '请填写必填项'
                                },
                                regexp: {
                                    message: '请输入有效的邮箱',
                                    regexp: /^([a-zA-Z0-9_\-\.])+@(\w)+((\.\w+)+)$/
                                }
                            }
                        }
                    }
                }

                $('[data-id="validator"]')
                .formValidation(validateOptions)
                .on('keyup', '[name="email"]', function(e) {
                    e.preventDefault();
                    var isEmailEmpty = $(this).val() == '';

                    $('[data-id="validator"]')
                    .formValidation('enableFieldValidators', 'email', !isEmailEmpty);

                    if($(this).val().length == 1){
                        $('[data-id="validator"]').formValidation('validateField', 'email');
                    }
                })
                .on('success.form.fv', function(e){
                    e.preventDefault();
                    console.log(e.target);
                    update();
                });

                $('.input-group.date')
                .on('changeDate', function(e){
                    var getEleName = $(e.target).find(':text').attr('name');
                    $('[data-id="validator"]').formValidation('revalidateField', getEleName);
                });
                $('[data-id="edit"]').on('hide.bs.modal', function (e){
                    $('[data-id="validator"]')
                    .formValidation('resetForm')
                    [0].reset();
                })
            }
        function init(){
            initTable();
            dateInit();
            closeButton();
            remove();
            validateForm();
        }
        init();
    })(jQuery)

    function view(id){
		loadProtocol(id, function(data){
			console.log(data);
			$('#pactCodeView').text(data.pactCode);
			if(data.state == -1){
				$('#stateView').text('未提交');
			}else if(data.state == 0){
				$('#stateView').text('未审核');
			}else if(data.state == 2){
				$('#stateView').text('已审核');
			}else if(data.state == 3){
				$('#stateView').text('停用');
			}
			$('#productNameView').text(data.productName);
			$('#providerNameView').text(data.providerName);
			$('#serviceCityView').text(data.serviceCity);
			$('#servicePersonNumView').text(data.servicePersonNum);
			$('#serviceStartDateView').text(data.serviceStartDate);
			$('#companyNameView').text(data.companyName);
			$('#contactEmpView').text(data.contactEmp);
			$('#telephoneNumberView').text(data.telephoneNumber);
			$('#emailView').text(data.email);
		});
    	$('[data-id="view"]').modal('show');
    }
    
    function mod(id){
		loadProtocol(id, function(data){
			console.log(data);
			$('#pactCodeEdit').text(data.pactCode);
			if(data.state == -1){
				$('#stateEdit').text('未提交');
			}else if(data.state == 0){
				$('#stateEdit').text('未审核');
			}else if(data.state == 2){
				$('#stateEdit').text('已审核');
			}else if(data.state == 3){
				$('#stateEdit').text('停用');
			}
			$('#idEdit').val(data.id);
			$('#productNameEdit').val(data.productName);
			$('#providerNameEdit').val(data.providerName);
			$('#serviceCityEdit').val(data.serviceCity);
			$('#servicePersonNumEdit').val(data.servicePersonNum);
			$('#serviceStartDateEdit').val(data.serviceStartDate);
			$('#companyNameEdit').val(data.companyName);
			$('#contactEmpEdit').val(data.contactEmp);
			$('#telephoneNumberEdit').val(data.telephoneNumber);
			$('#emailEdit').val(data.email);
		});
    	$('[data-id="edit"]').modal('show');
    }
    
    function loadProtocol(id, callback){
    	$.ajax({
			type : 'post',
			url : 'outsourcing/getProtocolById',
			data : {id: id},
			dataType: "json",
			success : function(data) {
                if(data){
                	callback(data);
                }
			}
		});
    }
    
    function update(){
    	$.ajax({
			type : 'post',
			url : 'outsourcing/updateOutsourcing',
			data : $('[data-id="validator"]').serialize(),
			dataType: "json",
			success : function(data) {
                if(data.success){
                	window.location.href = 'outsourcing/toListProtocol';
                }else{
                	swal({
                        title: data.msg,
                        type: "error"
                    });
                }
			}
		});
    }
    
    function operate(id, url){
    	$.ajax({
			type : 'post',
			url : url,
			data : {id: id},
			dataType: "json",
			success : function(data) {
                if(data.success){
                	window.location.href = 'outsourcing/toListProtocol';
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
