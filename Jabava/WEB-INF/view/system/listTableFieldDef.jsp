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
                               <button class="btn btn-info btn-sm" type="button" data-action="add" data-target="[data-modal=add]" data-toggle="modal">新增</button>
                               <button class="btn btn-info btn-sm" type="button" data-action="edit" data-target="[data-modal=edit]" data-toggle="modal">修改</button>
                               <button class="btn btn-danger btn-sm del" type="button" data-action="remove">删除</button>
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
                                    <th>关联表</th>
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
        <div class="modal fade hmodal-success form-row" data-modal="add" tabindex="-1">
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
                                                    <option value="ehr_person" keyField="person_id" relation="1">个人信息表</option>
                                                    <option value="ehr_position" keyField="post_id" relation="1">岗位信息表</option>
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
                                                    <option {{if $item.commonDataCode ==1}}selected="selected"{{/if}} value="{{$item.commonDataCode}}">{{$item.memo}}</option>
                                                    {{/each}}
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
                                        <div class="form-group">
                                            <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-4">参照项：</label>
                                            <div class="col-xs-6 col-sm-6 col-md-6 col-lg-8 ">
                                                <select class="form-control" id="refId" name="refId" disabled="disabled">
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
        <div class="modal fade hmodal-success form-row" data-modal-for="transfer" data-modal="edit" tabindex="-1">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="color-line"></div>
                    <div class="modal-header">
                        <div class="row" data-id="edit" data-target="clearHtml">
                          <script type="text/html" id="editFieldDisplayConfig">
                            <form class="form-horizontal" id="modifyForm" action="">
                                <input type="hidden" name="dataType" value="{{ehrTableFieldDef.dataType}}">
                                <input type="hidden" name="refId" value="{{ehrTableFieldDef.refId}}">
                                <input type="hidden" name="tableDefId" value="{{ehrTableFieldDef.tableDefId}}">
                                <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
                                    <div class="form-group">
                                        <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-4">信息项名称：</label>                                   
                                            <div class="col-xs-6 col-sm-6 col-md-6 col-lg-8 ">
                                            <input type="text" class="form-control" name="displayName" readonly="readonly" value="{{ehrTableFieldDef.displayName}}">
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
                                    <div class="form-group">
                                        <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-4">信息项类型：</label>
                                        <div class="col-xs-6 col-sm-6 col-md-6 col-lg-8 ">
                                            <select class="form-control" id="dataType" name="dataType" disabled="disabled">
                                                <option value="">----------</option>
                                                {{each commonDataList as $item}}
                                                    <option value="{{$item.commonDataCode}}" {{if $item.commonDataCode==ehrTableFieldDef.dataType}} selected="selected"{{/if}}>{{$item.memo}}</option>
                                                {{/each}}
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
                                                 {{each consultList as $item}}
                                                    <option value="{{$item.baseDataType}}" {{if $item.baseDataType==ehrTableFieldDef.refId}} selected="selected"{{/if}}>{{$item.baseDataTypeName}}</option>
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
                                                <option {{if (1 == ehrTableFieldDef.isNecessary)}}selected="selected"{{/if}} value="1">是</option>
                                                <option {{if (0 == ehrTableFieldDef.isNecessary)}}selected="selected"{{/if}} value="0">否</option>
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
                                                <option {{if (1 == ehrTableFieldDef.isUsing)}}selected="selected"{{/if}} value="1">是</option>
                                                <option {{if (0 == ehrTableFieldDef.isUsing)}}selected="selected"{{/if}} value="0">否</option>
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
        add();
        edit();
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
                                enabled: false,
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
                .on('change', '#dataType', function(e) {
                    var getThisValue = $(this).val(),
                        $getForm = $(this).parents('form'),
                        $getRefId = $getForm.find('[name="refId"]'),
                        fv = $getForm.data('formValidation');

                    if(getThisValue == 2){
                        $getRefId.prop({
                            disabled: false
                        });
                        fv.enableFieldValidators('refId', true)
                    }else{
                        fv
                        .resetField($getRefId)
                        .enableFieldValidators('refId', false)

                        $getRefId
                        .val('')
                        .prop({
                            disabled: true
                        })
                    }
                })
                .on('success.fv.form', function(e) {
                    e.preventDefault();
                    var getThisForm = $(e.target).serializeObject();
                    var $select=$("#tableDefId option:selected");
                    var ehrTableDef={
                        keyTable:$select.val(),
                        keyField:$select.attr("keyfield"),
                        relation:$select.attr("relation"),
                    }; 
                    getThisForm.ehrTableDef=ehrTableDef;
                    delete getThisForm.tableDefId;
                    $.ajax({
                        url: 'system/addOrUpdataFieldDef',
                        type: 'POST',
                        contentType: 'application/json', 
                        dataType: 'json',
                        async: false,
                        data: JSON.stringify(getThisForm)
                    })
                    .done(function(d) {
                        swal({
                            title: "新增字段成功!",
                            text: data.msg,
                            type: "success"
                        },function(){
                            window.location.reload();
                        });

                    })
                });
            })
        });
    }

    // 修改表单
    function edit(){
        $('[data-action="edit"]').on('click', function(e) {
            var len = $(":checked[name='checkAll']").length;
            if(len == 0 || len > 1){
                alert("请选择一个清单！");
             return false;
            }
            var getActionData = $(this).attr('data-action'),
                getEditItemsId = $('[data-modal="'+getActionData+'"]').data('itemsId');

            $.ajax({
                url: 'system/selectFieldDef',
                type: 'POST',
                dataType: 'json',
                async: false,
                data: {'tableFieldDefId': getEditItemsId}
            })
            .done(function(d) {
                var data = {
                    commonDataList: d.commonDataList,
                    consultList: d.consultList,
                    ehrTableFieldDef: d.ehrTableFieldDef
                };
                html = template('editFieldDisplayConfig', data);
                $('[data-id="edit"]').prepend(html);
                cancel();
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
                .on('success.fv.form', function(e) {
                    e.preventDefault();
                    var getThisForm = $(e.target).serializeObject();
                    var tableFieldId =  getEditItemsId;
                   
                    getThisForm.tableFieldDefId = tableFieldId;
                    $.ajax({
                        url: 'system/addOrUpdataFieldDef',
                        type: 'POST',
                        contentType: 'application/json', 
                        dataType: 'json',
                        async: false,
                        data: JSON.stringify(getThisForm)
                    })
                    .done(function(d) {
                        swal({
                            title: "修改字段成功!",
                            text: data.msg,
                            type: "success"
                        },function(){
                            window.location.reload();
                        });
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
        });
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
                // console.log(row.tableFieldDefId)
                        var strHtml = '<input type="checkbox" name="checkAll" data-check="item" value="'+row.tableFieldDefId+'">';
                        return strHtml;
                    },
                    createdCell: function (td, cellData, rowData, row, col){
                        var ueSelected = function($o, bSelectLast){
                            bSelectLast = bSelectLast === undefined? true : bSelectLast;
                            var getAllVal = [];
                            if(bSelectLast){
                                $('[data-check="item"]').not($o).prop('checked', false);
                                getAllVal.push( $o.val() );
                            }else{
                                $.each($('[data-check="item"]:checked'), function(index, item) {
                                    getAllVal.push( $(this).val() );
                                });
                            }
                            getAllVal = getAllVal.join(',');
                            return getAllVal;
                        };
                        $(td).on('click', '[data-check="item"]', function(event) {
                            var $getCheckItem = $(this);
                            if( $getCheckItem.prop('checked') ){
                                var getIDs = ueSelected($getCheckItem);
                                var getColumnName = rowData.columnName
                                $('[data-modal-for="transfer"]').data('itemsId', getIDs);
                                $('[data-action="remove"]').data('itemsId', getIDs);
                                $('[data-action="remove"]').data('columnName', getColumnName);
                            }else{
                                $('[data-modal-for="transfer"]').removeData('itemsId');
                                $('[data-action="remove"]').removeData('itemsId');
                                $('[data-action="remove"]').removeData('columnName');
                            }
                        });
                    }
                },
                { "data": "displayName" },
                { "data": "dataType",render: function(data, type, row, meta){
                    if(data == 1){
                        return "文本";
                    }else if(data == 2){
                        return "参照";
                    }else if(data == 3){
                        return "日期";
                    }
                } },
                { "data": "refName"},
                {"data":"keyTable",render: function(data, type, row, meta){
                    if(data =="ehr_person"){
                        return "个人信息表";
                    }else{
                        return "岗位信息表";
                    }
                }},
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
            var len = $(":checked[name='checkAll']").length;
                if(len == 0 || len > 1){
                    alert("请选择一个清单！");
                return false;
            }
            var getEditItemsId = $('[data-action="remove"]').data('itemsId');
            var getColumnName = $('[data-action="remove"]').data('columnName');
            
            
            swal({
                title: "确定要删除此信息项吗?",
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "是,请删除!",
                cancelButtonText: "不, 放弃此操作!",
                closeOnConfirm: false,
                closeOnCancel: false },
            	function (isConfirm) {
                    if(isConfirm){
                    	$.ajax({
                            url: 'system/deleteFieldDef',
                            type: 'POST',
                            dataType: 'json',
                            async: false,
                            data: { tableFieldDefId: getEditItemsId, columnName: getColumnName}
                        })
                        .done(function(data){
                        	if (data) {
                                if(data.success == true){
                                    swal({
                                        title: "删除成功!",
                                        text: data.msg,
                                        type: "success"
                                    },function(){
                                        window.location.reload();
                                    });
                                }else{
                                    swal({
                                        title: "删除失败!",
                                        text: data.msg,
                                        type: "warning"
                                    },function(){
                                        window.location.reload();
                                    });
                                }
                            }
                           
                        });
                    	
                    }else{
                    	
                    	 swal("已取消", "信息项未删除。", "error");
                    }
                    
            });
            
        });  
    }
</script>

</body>
</html>
