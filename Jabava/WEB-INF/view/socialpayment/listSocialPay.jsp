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
<title>社保汇缴清单</title>
<jsp:include flush="true" page="../common/styles.jsp"></jsp:include>
<!--for 临时改变-->
<link rel="stylesheet" href="static/js/plugins/form.validation/css/formValidation.css">
<link rel="stylesheet" href="static/css/user.css">

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
        <div class="normalheader transition animated fadeIn small-header">
          <div class="hpanel">
            <div class="panel-body">
              <div id="hbreadcrumb" class="m-t-xs m-b-xs">
                <h2 class="font-normal m-b-xs text-center">
                  社保汇缴清单
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
                                <form id="msForm" class="form-horizontal">
                                    <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
                                        <div class="form-group has-feedback">
                                            <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-4">社保账户：</label>
                                            <div class="col-xs-6 col-sm-6 col-md-6 col-lg-8">
                                                <select id="socialSecurityAccountIdForSearch" name="socialSecurityAccountId" class="form-control">
                                                    <option value="">全部</option>
                                                    <c:forEach var="account" items="${requestScope.ssAccountList }" varStatus="status">
                                                   		<option value="${account.socialSecurityAccountId }">${account.socialSecurityAccountName }</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
                                         <div class="form-group">
                                                <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-4">年月：</label>
                                                <div class="col-xs-6 col-sm-6 col-md-6 col-lg-8">
                                                    <div class="input-group date"> 
                                                        <input type="text" name="month" id="monthForSearch" class="form-control">
                                                        <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                                    </div>
                                                </div>
                                            </div>
                                    </div>
                                
                                    <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
                                        <div class="form-group">
                                            <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-5">处理状态：</label>
                                            <div class="col-xs-6 col-sm-6 col-md-6 col-lg-7">
                                                <select id="status" name="status" class="form-control">
                                                    <option value="">全部</option>
                                                    <option value="1">已生成</option>
                                                    <option value="2">已锁定</option>
                                                    <option value="3">已作废</option>
                                                </select>
                                            </div>   
                                        </div>
                                    </div>
                                      <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-center">
                                        <button class="btn btn-info btn-sm btn-pb-search" type="button">查询</button>
                                        <button class="btn btn-default m-l" type="reset">重置</button>
                                    </div>
                                </form>
                            </div>
                        <!--新增弹框-->
                         <div class="modal fade hmodal-success form-row" data-modal="build" tabindex="-1" role="dialog"  aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="color-line"></div>
                                    <div class="modal-header">
                                     <div class="row">
                                     <div class="panel-body">
                                      <form class="form-horizontal" id="addForm">
                                            <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
                                            <div class="form-group has-feedback">
                                                <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-4">社保账户：</label>
                                                <div class="col-xs-6 col-sm-6 col-md-6 col-lg-8 form-required">
                                                    <select id="socialSecurityAccountId" name="socialSecurityAccountId" class="form-control">
                                                        <option value="">全部</option>
                                                        <c:forEach var="account" items="${requestScope.ssAccountList }" varStatus="status">
	                                                   		<option value="${account.socialSecurityAccountId }">${account.socialSecurityAccountName }</option>
	                                                    </c:forEach>
                                                    </select>
                                                </div>
                                            </div>
                                          </div>
                                            <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
                                             <div class="form-group">
                                                <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-4">年月：</label>
                                                <div class="col-xs-6 col-sm-6 col-md-6 col-lg-8 form-required">
                                                    <div class="input-group date"> 
                                                        <input type="text" name="month" id="month" class="form-control">
                                                        <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                      
                                         <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-center">
                                            <button type="submit" class="btn btn-info btn-sm">确  定</button>
                                            <button type="button" class="btn btn-default btn-sm" data-close="build">取　消</button>
                                        </div>
                                    </form>
                                      </div>
                                    </div>   
                                </div>
                            </div>
                        </div>
                        </div>
                        <!--新增弹框 end-->
                        
                        <div class="panel-body m-t-md"> 
                            <table id="projectTable" class="table table-condensed table-hover table-bordered dataTable" width="100%">
                                <thead>
	                                <tr>
	                                    <th>选择</th>
	                                    <th>社保账户</th>
	                                    <th>年月</th>
	                                    <th>处理状态</th>
	                                    <th>企业缴费总额</th>
	                                    <th>个人缴费总额</th>
	                                    <th>汇缴人数</th>
	                                    <th>企业汇缴基数</th>
	                                    <th>个人汇缴基数</th>
	                                    <th>企业汇缴总额</th>
	                                    <th>个人汇缴总额</th>
	                                    <th>汇缴总额</th>
	                                    <th>补缴人数</th>
	                                    <th>企业补缴基数</th>
	                                    <th>个人补缴基数</th>
	                                    <th>企业补缴总额</th>
	                                    <th>个人补缴总额</th>
	                                    <th>补缴总额</th>
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
              
        <!--主要内容结束-->

        <!-- 放页脚  开始-->
        <jsp:include flush="true" page="../common/foot.div.jsp"></jsp:include>
        <!-- 放页脚  结束-->

    </div>

    <div class="modal fade" data-modal="confReport">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">Modal title</h4>
        </div>
        <div class="modal-body">
            <p>One fine body&hellip;</p>
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            <button type="button" class="btn btn-primary">Save changes</button>
        </div>
    </div><!-- /.modal-content -->
</div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<div style="display:none;">
	<form id="detailForm" action="" method="post">
		<input type="hidden" id="ssPaymentBillId" name="ssPaymentBillId">
	</form>
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
    <script src="static/bootstrap/vendor/bootstrap-datepicker-master/dist/js/bootstrap-datepicker.js"></script>
    <script src="static/bootstrap/vendor/bootstrap-datepicker-master/dist/locales/bootstrap-datepicker.zh-CN.min.js"></script>

    <!-- for datatable -->
    <script src="static/bootstrap/vendor/datatables/media/js/jquery.dataTables.min.js"></script>
    <script src="static/bootstrap/vendor/datatables_plugins/integration/bootstrap/3/dataTables.bootstrap.min.js"></script>

    <!-- alert -->
    <script src="static/bootstrap/vendor/sweetalert/lib/sweet-alert.min.js"></script>
    
    <!-- App scripts -->
    <script src="static/bootstrap/scripts/homer.js"></script>
    <script src="static/bootstrap/scripts/charts.js"></script>
    <!-- 表单验证 -->
    <script src="static/js/plugins/form.validation/js/formValidation.js"></script>
    <script src="static/js/plugins/form.validation/js/framework/bootstrap.js"></script>
    <script src="static/js/plugins/form.validation/js/language/zh_CN.js"></script> 

<script type="text/javascript">
    jQuery.prototype.serializeObject=function(){  
        var obj=new Object();  
        $.each(this.serializeArray(),function(index,param){  
          if(!(param.name in obj)){  
            obj[param.name]=param.value;  
          }  
        });  
        return obj;  
    };

    var table;
    $(function (){
        $('.btn-pb-search').click(function(){
            loadTable();
        });
        
        loadTable();
    });
    
    function loadTable(params){
        if(table){
            table.destroy();
        }
        table = $('#projectTable').DataTable({
            "dom": 
                "<'row'<'col-sm-9'l><'col-sm-3'<'toolbar text-right'>>>" +
                "<'row'<'col-sm-12 table-responsive'tr>>" +
                "<'row'<'col-sm-5'i><'col-sm-7'p>>",
            
            //json
            "processing": true,
            "serverSide": true,
            "bDestroy": true,
            "ordering": false,
            "ajax": {
                "url":"ssPaymentBill/listPaymentBillPage",
                "data": $('#msForm').serializeObject(),
                "type":"post"
            },
            "columns": [
                 {"render": function render( data, type, row, meta ){
                    return'<input type="checkbox" name="checkAll" id="checkAll'+row.id+'" value="'+row.id+'" >';
                  },"orderable": false },
                { "data": "socialSecurityAccountName",
                    "render": function render( data, type, row, meta ){
                        var strHtml = '<td>';
                        <% if(RequestUtil.hasPower("ssbill_vb")){ %>
                        strHtml += '<a href="javascript:void(0);" onclick="viewDetail(' + row.id + ')">' + data + '</a>';
                        <% }else{ %>
                        strHtml += data;
                        <% } %>
                        strHtml += '</td>';
                        return strHtml;
                    } 
                },
                { "data": "month" },
                { "data": "status", "render": function(data, type, row, meta){
                	var text = '';
                    if(data == 1){
                        text = "已生成";
                    }else if(data == 2){
                        text = "已锁定";
                    }else if(data == 3){
                        text = "已作废";
                    }
                    return '<p id="status' + row.id + '" data-status="' + data + '">' + text + '</p>';
                } },
                { "data": "amountE" },
                { "data": "amountP" },
                { "data": "hjNum" },
                { "data": "hjBaseE" },
                { "data": "hjBaseP" },
                { "data": "hjAmountE" },
                { "data": "hjAmountP" },
                { "data": "hjAmount" },
                { "data": "bjNum" },
                { "data": "bjBaseE" },
                { "data": "bjBaseP" },
                { "data": "bjAmountE" },
                { "data": "bjAmountP" },
                { "data": "bjAmount" }
            ],
            "columnDefs": [
                {defaultContent: '', targets: '_all'}
            ],
            
            "language": {
                "search": "过滤:",
                "lengthMenu": "每页显示 _MENU_ 条记录",
                "zeroRecords": "暂无数据 - 报歉啦?",
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
        });
        var strHtml = '';
        strHtml += '<a href="javascript://" class="btn btn-info btn-sm" data-toggle="ascupload">一键外包</a>&nbsp;';
        strHtml += '<form action="change/toUploadChange" method="post" data-form="ascupload" class="sr-only">';
        strHtml += '<input type="hidden" name="ssPaymentBillId"></form>';
        <% if(RequestUtil.hasPower("ssbill_ex")){ %>
        strHtml += '<a href="report/commonGenReport?reportId=8" class="btn btn-info btn-sm" data-toggle="modal" data-target="[data-modal=confReport]">导出</a>&nbsp;';
        <% } %>
        strHtml += '<div class="btn-group"><button data-toggle="dropdown" class="btn btn-success btn-sm dropdown-toggle">操作<span class="caret"></span></button><ul class="dropdown-menu">';
        <% if(RequestUtil.hasPower("ssbill_gb")){ %>
        strHtml += '<li><a href="#" data-target="[data-modal=build]" data-toggle="modal"><i class="fa fa-check-circle"></i> 生成</a></li>';
        <% } %>
        <% if(RequestUtil.hasPower("ssbill_lb")){ %>
        strHtml += '<li><a href="javascript:void(0)" onclick="doOperate(1)"><i class="fa fa-lock"></i> 锁定</a></li>';
        <% } %>
        <% if(RequestUtil.hasPower("ssbill_ub")){ %>
        strHtml += '<li><a href="javascript:void(0)" onclick="doOperate(2)"><i class="fa fa-unlock"></i> 解锁</a></li>';
        <% } %>
        <% if(RequestUtil.hasPower("ssbill_cb")){ %>
        strHtml += '<li><a class="demo4" href="javascript:void(0)" onclick="doOperate(3)"><i class="fa fa-minus-circle "></i> 作废</a></li>';
        <% } %>
        strHtml += '</ul></div>'
        $("div.toolbar").html(strHtml);

        $('[data-toggle="ascupload"]').on('click', function(event) {
            event.preventDefault();
            var $getItems = $('[name="checkAll"]:checked');
            if( $getItems.length < 1 || $getItems.length > 1 ){
                swal({
                    title: '请选择一个且只能选择选择一个汇缴清单',
                    type: 'info'
                });
            }else if($('#status' + $getItems.val()).attr('data-status') == '3'){
            	swal({
                    title: '请选择一个未作废的汇缴清单',
                    type: 'info'
                });
            }else{
                var $getAscuploadForm = $('[data-form="ascupload"]');
                $getAscuploadForm.find('[name="ssPaymentBillId"]').val($getItems.val());
                $getAscuploadForm.submit();
            }
        });
    }
    
    //表单验证
    $('#addForm').formValidation({
        err: {
            container: 'tooltip'
        },
        icon: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            socialSecurityAccountId: {
                validators: {
                    notEmpty: {
                        message: '请填写必填项'
                    }
                }
            },
            month: {
                validators: {
                    notEmpty: {
                        message: '请填写必填项'
                    },
                    regexp: {
                        message: '请填写正确的日期格式',
                        regexp: /^\d{4}\d{1,2}$/
                    }
                }
            }
        
        }
    })
     .on('success.form.fv', function(e){
        e.preventDefault();
        $.ajax({
			url:"ssPaymentBill/generatePaymentBill",
			type : "POST",
			dataType:'json',
			data: {"socialSecurityAccountId": $('#socialSecurityAccountId').val(), "month": $('#month').val()},
			error: function(XMLHttpRequest, textStatus, errorThrown){
				alert(textStatus);
			},
			success: function(data){
				if(data.success){
			    	table.ajax.reload();
			    	alert("操作成功");
				}else{
					alert(data.msg);
                    $(e.target).formValidation('resetForm');
				}
			}
		});
    });

    
    function viewDetail(id){
        $('#ssPaymentBillId').val(id);
        $('#detailForm').attr('action','ssPaymentBill/toListPaymentBillDetail');
        $('#detailForm').submit();
    }

    // 全选  反选
    function selectAll() {
        var selAll = document.getElementById("selAll");
        var obj = document.getElementsByName("checkAll");
        if(document.getElementById("selAll").checked == false){
            for(var i=0; i<obj.length; i++){
              obj[i].checked=false;
            }
        }else{
            for(var i=0; i<obj.length; i++){  
              obj[i].checked=true;
            }
        } 
    }
    
    // 日历
    $('.input-group.date').datepicker({
        format: 'yyyymm', 
        weekStart: 1, 
        autoclose: true, 
        startView: 1, maxViewMode: 1,minViewMode:1,
        forceParse: false, 
        language: 'zh-CN'
    })
    .on('changeDate', function(e){
        var getEleName = $(e.target).find(':text').attr('name');
        $('#addForm').formValidation('revalidateField', getEleName);
    });

    // clear remote modal cache
    $('[data-modal=confReport]').on('hidden.bs.modal', function(e){
        $(this).removeData('bs.modal').find('.modal-content').empty();
    });
    
	// 验证提示
    function doOperate(type){
		var len = $(":checked[name='checkAll']").length;
        if(len == 0 || len > 1){
            alert("请选择一个清单！");
            return false;
        }
        
        $.ajax({
			url:"ssPaymentBill/changeStatus",
			type : "POST",
			dataType:'json',
			data: {"id": $(":checked[name='checkAll']")[0].id.substring(8), "operType": type},
			error: function(XMLHttpRequest, textStatus, errorThrown){
				alert(textStatus);
			},
			success: function(data){
				if(data.success){
			    	table.ajax.reload();
			    	alert("操作成功");
				}else{
					alert(data.msg);
				}
			}
		});
	}


    //取消添加的浮层
    $('[data-close="build"]').on('click',function(e){
        $('[data-modal="build"]').modal('hide');
    })
</script>
</body>
</html>