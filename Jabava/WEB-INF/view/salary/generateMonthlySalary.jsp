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
<title>月度工资管理-生成</title>
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
        
        <!-- 放主要内容 -->
        <div class="content animate-panel">
            <div class="row">
                <div class="col-lg-12">
                    <div class="hpanel">
                        <div class="panel-heading">
                            <h4 class="text-center font-bold">
                                生成
                                <a href="salary/listMonthlySalary" class="pull-left btn btn-success">
                                    返　回
                                </a>
                            </h4>     
                        </div>
                            <div class="panel-body">
                                <form class="form-horizontal" id="generateForm">
                                    <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
                                         <div class="form-group">
                                            <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-5">月份：</label>
                                            <div class="col-xs-6 col-sm-6 col-md-6 col-lg-7 form-required"> 
                                                <div class="input-group date">
                                                    <input type="text" class="form-control" id="monthly" name="monthly" />
                                                    <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                                </div>
                                            </div>

                                        </div>
                                    </div>
                                     <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
                                        <div class="form-group">
                                            <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-5">用途：</label>
                                            <div class="col-xs-6 col-sm-6 col-md-6 col-lg-7 form-required">
                                                <select class="form-control" id="usageFlag" name="usageFlag">
                                                    <c:forEach var="salaryType" items="${requestScope.salaryTypeList }" varStatus="status">
                                                    	<option value="${salaryType.baseDataId }">${salaryType.baseDataName }</option>
                                                    </c:forEach>
                                                </select>
                                            </div>   
                                        </div>
                                    </div>
                                    <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4 ">
                                        <div class="form-group">
                                            <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-5">分公司：</label>
                                            <div class="col-xs-6 col-sm-6 col-md-6 col-lg-7 form-required">
                                                <select class="form-control" id="organizationId" name="organizationId">
                                                    <!--option value="">全部</option-->
                                                    <c:forEach var="org" items="${requestScope.orgList }" varStatus="status">
                                                    	<option value="${org.organizationId }">${org.organizationName }</option>
                                                    </c:forEach>
                                                </select>
                                            </div>   
                                        </div>
                                    </div>
                                    <% if(RequestUtil.hasPower("monthlysalary_generate_cg")){ %>
                                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-center">
                                        <button type="submit" class="btn btn-info btn-sm btn-ms-generate">确　定</button>
                                    </div>
                                    <% } %>
                                </form>
                            </div>
        
                        <div class="panel-body m-t-md hidden"> 
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
    $(function (){
    	$('#generateForm')[0].reset();
    	

    	
        var table = $('#projectTable').DataTable({
            "dom": 
                "<'row'<'col-sm-3'l><'col-sm-9'f>>"+
                "<'row'<'col-sm-12 table-responsive'tr>>" +
                "<'row'<'col-sm-5'i><'col-sm-7'p>>",
            
            //json
         //    "processing": true,
   //           "serverSide": true,
   //           "bDestroy": true,
   //           "ordering": false,
            // "ajax": {
            //  "url":"salaryChangeDef/listSalaryChangeDef"
            // },
            // "columns": [
            //  { "data": "salaryChangeDefId", "visible":false },
            //  { "data": "name" },
            //  { "data": "keyInfo" },
            //  { "data": "keyType"},
         //     { "data": "description" },
   //              { "data": "description" },
   //              { "data": "description" },
   //              { "data": "description" },
            //  { "render": function render( data, type, row, met a ){
            //          return '<button class="btn btn-success btn-xs" type="button" onclick="">修改</button>&nbsp;' +
   //                          '<button class="btn btn-danger btn-xs del-button" type="button" onclick="del(' + row.salaryChangeDefId + ');">删除</button>';
            //      }
            //  }
            // ],
   //       "columnDefs": [
   //           {defaultContent: '', targets: '_all'}
            // ],
            
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

        $("div.toolbar").html('<button class="btn btn-info btn-sm pull-right" type="button" data-toggle="collapse" data-target="#collapseExample" aria-expanded="false" aria-controls="collapseExample"><i class="fa">高级搜索</i></button>');

              //表单验证
        $('#generateForm').formValidation({
            err: {
                container: 'tooltip'
            },
            icon: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                monthly: {
                    validators: {
                        notEmpty: {
                            message: '日期格式不正确',
                            regexp:/^\d{4}\d{1,2}$/
                        },
                    }
                },
                usageFlag: {
                    validators: {
                        notEmpty:{
                            message: '请填写必填项'
                        }
                    }
                },
                organizationId: {
                    validators: {
                        notEmpty:{
                            message: '请填写必填项'
                        }
                    }
                }
                
            }
        })
        .on('success.form.fv', function(e){
            e.preventDefault();
            
        	$.ajax({
                url : "monthlySalary/generateMonthlySalary",
                data : $('#generateForm').serialize(),
                dataType:'json',
                type : 'post',
                success : function(message) {
                    if(message.success){
                        swal({
                            title: "生成成功!",
                            text: "",
                            type: "success",
                            confirmButtonText: "确定"
                        });
                        window.location.href="monthlySalary/toListMonthlySalary";
                    }else{
                        toastr.options = {
                            "debug": false,
                            "timeOut": "50000",
                            "newestOnTop": false,
                            "positionClass": "toast-top-center",
                            "closeButton": true,
                            "toastClass": "animated fadeInDown",
                        };
                        swal({
                            title: "生成失败!",
                            text: message.msg,
                            type: "error",
                            confirmButtonText: "确定"
                        },function(){
                            $(e.target).formValidation('resetForm');
                        });
                    	var msgs = message.messageList,
	                        messager = []
	                    if( msgs ){
	                        $.map(msgs, function(item, index){
	                            messager[index] = '<p>' + item + '</p>';
	                        });
	                        toastr.error(messager);
	                    }
                    }
                }
            });
        });

    })
    

    function del(id){
        swal(
            {
                title: "确定要删除此定义吗?",
                text: "",
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "确定删除!",
                cancelButtonText: "放弃操作!",
                closeOnConfirm: false,
                closeOnCancel: false 
            },
            function (isConfirm) {
                if (isConfirm) {
                    deleteById(id);
                }else{
                    swal("已取消", "定义未删除", "error");
                }
            }
        );
    }
    
    function deleteById(id){
        $.ajax({
            url : "salaryChangeDef/deleteSalaryChangeDef",
            data : {id: id},
            dataType:'json',
            type : 'post',
            success : function(message) {
                if(message.success){
                    swal({
                        title: "删除成功!",
                        text: "",
                        type: "success",
                        confirmButtonText: "确定"
                    });
                    window.location.href="salaryChangeDef/toListSalaryChangeDef";
                }else{
                    swal({
                        title: "删除失败!",
                        text: message.msg,
                        type: "error",
                        confirmButtonText: "确定"
                    });
                }
            }
        });
    }
    // 日历
    $(function(){
        $('.input-group.date').datepicker({
            format: 'yyyymm', 
            //weekStart: 1, 
            autoclose: true, 
            //startView: 1, 
            //maxViewMode: 1,
            minViewMode:1,
            //forceParse: false, 
            language: 'zh-CN'
        })
        .on('changeDate', function(e){
            var getEleName = $(e.target).find(':text').attr('name');
            $('#generateForm').formValidation('revalidateField', getEleName);
        })
    });
          

</script>
</body>
</html>