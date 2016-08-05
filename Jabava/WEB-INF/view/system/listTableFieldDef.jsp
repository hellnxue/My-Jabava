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
<title>自定义报表</title>
<jsp:include flush="true" page="../common/styles.jsp"></jsp:include>
<link rel="stylesheet" href="static/js/plugins/form.validation/css/formValidation.css">
<!--for 临时改变-->
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
        
        
        <!-- 放主要内容 -->
        <div class="content animate-panel">
            <div class="row">
                <div class="col-lg-12">
                    <div class="hpanel">
                        <div class="panel-heading">
                            <!-- <h4 class="text-center font-bold">
                                员工薪酬档案管理
                            </h4> -->
                            <div class="text-right">
                               <button class="btn btn-info btn-sm" type="button" data-action="add" data-target="[data-modal=addSalary]" data-toggle="modal">新增</button>
                               <button class="btn btn-info btn-sm" type="button" data-action="edit" data-target="[data-modal=alterSalary]" data-toggle="modal">修改</button>
                               <button class="btn btn-danger btn-sm del" type="button">删除</button>
                            </div> 
                        </div>

                        <div class="panel-body">
                            <table id="customizeTable" class="table table-striped table-condensed table-hover table-bordered dataTable" width="100%">
                                <thead>
                                <tr>
                                    <th>序号</th>
                                    <th>信息项名称</th>
                                    <th>信息项类型</th>
                                    <th>参照项</th>
                                    <th>是否必填</th>
                                    <th>是否启用</th>
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

        <!-- 新增弹框 -->
        <div class="modal fade hmodal-success form-row" data-modal="addSalary" tabindex="-1">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="color-line"></div>
                    <div class="modal-header">
                        <div class="row" data-id="add" data-target="clearHtml">
                            <script type="text/html" id="addFieldDisplayConfig">
                                <form class="form-horizontal" id="addForm" action="">
                                     <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                        <div class="form-group">
                                            <label class="control-label col-xs-3 col-sm-3 col-md-3 col-lg-2">关联表：</label>
                                            <div class="col-xs-3 col-sm-3 col-md-3 col-lg-4 ">
                                                <select class="form-control" id="tableDefId" name="tableDefId">
                                                    <option value="">----------</option>
                                                    {{each linkList as $item}}
                                                    <option value="{{$item.tableDefId}}">{{$item.memo}}</option>
                                                    {{/each}}
                                                </select>
                                            </div>   
                                        </div>
                                    </div>
                                    <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
                                        <div class="form-group">
                                            <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-4">信息项名称：</label>                                   
                                                <div class="col-xs-6 col-sm-6 col-md-6 col-lg-8 ">
                                                <input type="text" class="form-control" name="displayName" id="displayName">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
                                        <div class="form-group">
                                            <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-4">信息项类型：</label>
                                            <div class="col-xs-6 col-sm-6 col-md-6 col-lg-8 ">
                                                <select class="form-control" id="dataType" name="dataType">
                                                    <option value="">----------</option>
                                                    {{each commonDataList as $item}}
                                                    <option value="{{$item.commonDataCode}}">{{$item.commonDataName}}</option>
                                                    {{/each}}
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
                                        <div class="form-group">
                                            <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-4">参照项：</label>
                                            <div class="col-xs-6 col-sm-6 col-md-6 col-lg-8 ">
                                                <select class="form-control" id="refId" name="refId">
                                                    <option value="">----------</option>
                                                    {{each consultList as $item}}
                                                    <option value="{{$item.baseDataType}}">{{$item.baseDataTypeName}}</option>
                                                    {{/each}}
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                     <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
                                        <div class="form-group">
                                            <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-4">是否必填：</label>
                                            <div class="col-xs-6 col-sm-6 col-md-6 col-lg-8 ">
                                                <select class="form-control" id="isNecessary" name="isNecessary">
                                                    <option value="">----------</option>
                                                    <option value="1">是</option>
                                                    <option value="0">否</option>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
                                        <div class="form-group">
                                            <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-4">是否启用：</label>
                                            <div class="col-xs-6 col-sm-6 col-md-6 col-lg-8 ">
                                                <select class="form-control" id="isUsing" name="isUsing">
                                                    <option value="">----------</option>
                                                    <option value="1">是</option>
                                                    <option value="0">否</option>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-center">
                                        <button type="submit" class="btn btn-info btn-sm" data-action="sure">确　定</button>
                                        <button type="button" class="btn btn-info btn-sm" data-id="cancel">取　消</button>
                                    </div>
                                </form>
                            </script>
                        </div>   
                    </div>
                </div>
            </div>
        </div>
        <!-- 新增弹框结束 -->
        <!-- 修改弹框 -->
        <div class="modal fade hmodal-success form-row" data-modal="alterSalary" tabindex="-1">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="color-line"></div>
                    <div class="modal-header">
                        <div class="row" data-id="" data-target="clearHtml">
                            <form class="form-horizontal" id="modifyForm" action="">
                                 <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                    <div class="form-group">
                                        <label class="control-label col-xs-3 col-sm-3 col-md-3 col-lg-2">关联表：</label>
                                        <div class="col-xs-3 col-sm-3 col-md-3 col-lg-4 ">
                                            <select class="form-control" id="tableDefId" name="tableDefId" disabled="disabled">
                                                <option value="">----------</option>
                                                <option>个人信息</option>
                                                <option>工作信息</option>
                                            </select>
                                        </div>   
                                    </div>
                                </div>
                                <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
                                    <div class="form-group">
                                        <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-4">信息项名称：</label>                                   
                                            <div class="col-xs-6 col-sm-6 col-md-6 col-lg-8 ">
                                            <input type="text" class="form-control" name="displayName"  readonly="">
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
                                    <div class="form-group">
                                        <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-4">信息项类型：</label>
                                        <div class="col-xs-6 col-sm-6 col-md-6 col-lg-8 ">
                                            <select class="form-control" id="dataType" name="dataType" disabled="disabled">
                                                <option value="">----------</option>
                                                <option>字符</option>
                                                <option>参照</option>
                                                <option>日期</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
                                    <div class="form-group">
                                        <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-4">参照项：</label>
                                        <div class="col-xs-6 col-sm-6 col-md-6 col-lg-8 ">
                                            <select class="form-control" id="refId" name="refId"  disabled="disabled">
                                                <option value="">----------</option>
                                                <option>字符</option>
                                                <option>参照</option>
                                                <option>日期</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                 <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
                                    <div class="form-group">
                                        <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-4">是否必填：</label>
                                        <div class="col-xs-6 col-sm-6 col-md-6 col-lg-8 ">
                                            <select class="form-control" id="isNecessary" name="isNecessary">
                                                <option value="">----------</option>
                                                <option value="1">是</option>
                                                <option value="0">否</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
                                    <div class="form-group">
                                        <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-4">是否启用：</label>
                                        <div class="col-xs-6 col-sm-6 col-md-6 col-lg-8 ">
                                            <select class="form-control" id="isUsing" name="isUsing" >
                                                <option value="">----------</option>
                                                <option value="1">是</option>
                                                <option value="0">否</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-center">
                                    <button type="submit" class="btn btn-info btn-sm">确　定</button>
                                    <button type="button" class="btn btn-info btn-sm" data-id="cancel">取　消</button>
                                </div>
                            </form>
                        </div>   
                    </div>
                </div>
            </div>
        </div>
        <!-- 修改弹框结束 -->
      
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
    <script src="static/bootstrap/vendor/toastr/build/toastr.min.js"></script>

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
    <script src="static/js/template.js"></script>
    
<script type="text/javascript">
	
    $(function (){
    	$.fn.extend({
            serializeObject:function(){
                   if(this.length>1){
                          return false;
                   }
                   var arr=this.serializeArray();
                   var obj=new Object;
                   $.each(arr,function(k,v){
                          obj[v.name]=v.value;
                   });
                   return obj;
            }
        });
    	loadTable();
        editformValidation();
        add();
        remove();
    });

    // 新增表单
    function add(){
        $('[data-action="add"]').on('click', function(e) {
            $.ajax({
                url: 'system/addTableFieldDef',
                type: 'POST',
                dataType: 'json',
                async: false
            })
            .done(function(d) {
                var data = {
                     commonDataList : d.commonDataList,
                     consultList : d.consultList,
                     linkList : d.linkList
                },
                html = template('addFieldDisplayConfig', data);
                 $('[data-id="add"]').prepend(html);
                 cancel();
                 $('#addForm')
                 .formValidation({
                        err: {
                            container: 'tooltip'
                        },
                        icon: {
                            valid: 'glyphicon glyphicon-ok',
                            invalid: 'glyphicon glyphicon-remove',
                            validating: 'glyphicon glyphicon-refresh'
                        },
                        fields: {
                            tableDefId: {
                                validators: {
                                    notEmpty: {
                                        message: '请填写必填项'
                                    }
                                }
                            },
                            displayName: {
                                validators: {
                                    notEmpty:{
                                        message: '请填写必填项'
                                    }
                                }
                            },
                            dataType: {
                                validators: {
                                    notEmpty:{
                                        message: '请填写必填项'
                                    }
                                }
                            },
                            refId: {
                                validators: {
                                    notEmpty:{
                                        message: '请填写必填项'
                                    }
                                }
                            },
                            isNecessary: {
                                validators: {
                                    notEmpty:{
                                        message: '请填写必填项'
                                    }
                                }
                            },
                            isUsing: {
                                validators: {
                                    notEmpty:{
                                        message: '请填写必填项'
                                    }
                                }
                            }

                        }
                }) 
                .on('success.fv.form', function(e) {
                    e.preventDefault();
                    var getThisForm = $(e.target).serializeObject();
                    console.log(getThisForm)
                    $.ajax({
                        url: 'system/addOrUpdataFieldDef',
                        type: 'POST',
                        dataType: 'json',
                        async: false,
                        data: {'tableFieldDef': getThisForm}
                    })
                    .done(function(d) {
                        console.log(d)
                    })
                });
            })
        });
    }
    // 取消按钮
    function cancel(){
        $('[data-id="cancel"]').on('click', function(e) {
           $(e.target).parents('.modal').modal('hide');
        });
        $('[data-modal]').on('hide.bs.modal', function(e) {
            var getTargetClear = $(e.target).find('[data-target=clearHtml]');

            getTargetClear.empty();
            // console.log(getTargetClear)
        });
    }
    // 修改表单验证
     function editformValidation(){ 
        $('#modifyForm').formValidation({
            err: {
                container: 'tooltip'
            },
            icon: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                isNecessary: {
                    validators: {
                        notEmpty:{
                            message: '请填写必填项'
                        }
                    }
                },
                isUsing: {
                    validators: {
                        notEmpty:{
                            message: '请填写必填项'
                        }
                    }
                } 

            }
        })   
     }
    function loadTable(params){
    	table = $('#customizeTable').DataTable({
            "dom": 
                "<'row'<'col-sm-6'l><'col-sm-2'<'toolbar text-right'>>>" +
                "<'row'<'col-sm-12 table-responsive'tr>>" +
                "<'row'<'col-sm-5'i><'col-sm-7'p>>",
            
            //json
            "processing": true,
            "serverSide": true,
            "bDestroy": true,
            "ordering": false,
            "ajax": {
            	"url":"system/listTableFieldDef",
            	"type":"post",
            	"dataType": "json"
            },
            "columns": [
	           { "data": "tableDefId", render: function(data, type, row, meta){
                        var strHtml = '<input type="checkbox" name="" data-check="item" value="'+data+'">';
                        return strHtml;
                }},
	            { "data": "displayName" },
	            { "data": "dataType",render: function(data, type, row, meta){
	            	if(data == 19){
	            		return "text";
	            	}else if(data == 20){
	            		return "select";
	            	}else if(data == 21){
	            		return "datepicker";
	            	}
	            } },
	            { "data": "refName"},
	            { "data": "isNecessary", render: function(data, type, row, meta){
	            	if(data == 1){
	            		return "是";
	            	}else{
	            		return "否";
	            	}
	            } },
	             { "data": "isUsing",render: function(data, type, row, meta){
	            	 if(data == 1){
		            		return "是";
		            	}else{
		            		return "否";
		            	}
	             } }
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
    }
    // 删除
    function remove(){
      $('.del').click(function () {
            swal({
                    title: "确定要删除此用户吗?",
                    text: "注意：用户删除后将不可登录!",
                    type: "warning",
                    showCancelButton: true,
                    confirmButtonColor: "#DD6B55",
                    confirmButtonText: "是,请删除该用户!",
                    cancelButtonText: "不, 放弃此操作!",
                    closeOnConfirm: false,
                    closeOnCancel: false },
                function (isConfirm) {
                    if (isConfirm) {
                        swal("删除成功!", "该用户已经被删除.", "success");
                    } else {
                        swal("已取消", "用户未删除。你这逗我玩呢 :)", "error");
                    }
                });
        });  
    }
</script>

</body>
</html>
