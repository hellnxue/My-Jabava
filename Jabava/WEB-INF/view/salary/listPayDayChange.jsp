<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<base href="${pageContext.request.contextPath}/">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge">

<!-- Page title -->
<title>计薪日变动管理</title>
<jsp:include flush="true" page="../common/styles.jsp"></jsp:include>
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
                            <h4 class="text-center font-bold">
                                计薪日变动管理
                            </h4>
                        </div>
                        <!--div class="panel-body">
                        	<form class="form-horizontal">
                        	<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
                                 <div class="form-group">
                                     <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-3">年度：</label>
                                     <div class="input-group date col-xs-6 col-sm-6 col-md-6 col-lg-8"> 
                                             <input type="text" class="form-control" name="" />
                                             <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                     </div>
                                 </div>
                                 
                              </div>
                              </form>
                        </div-->
                        <!--新增弹框-->    
                 <div class="modal fade hmodal-success form-row" data-modal="addSalaryDate" tabindex="-1" role="dialog"  aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="color-line"></div>
                            <div class="modal-header">
                             <div class="row">
                             <div class="panel-body">
                              <form class="form-horizontal" id="editForm">
                              	<input type="hidden" id="salaryDateId" name="salaryDateId">
                                    <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
                                        <div class="form-group">
                                            <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-3">日期：</label>
                                            <div class="input-group date col-xs-6 col-sm-6 col-md-6 col-lg-8 form-required" data-date-format="yyyy-mm-dd"> 
                                                    <input type="text" class="form-control" id="changeDate" name="changeDate" />
                                                    <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
                                        <div class="form-group">
                                        <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-4">变更类型：</label>
                                        <div class="col-xs-6 col-sm-6 col-md-6 col-lg-8 form-required">
                                            <select class="form-control" id="changeType" name="changeType">
                                                <option value="">请选择</option>
                                                <option value="1">工作日</option>
                                                <option value="2">休息日</option>
                                            </select>
                                        </div>
                                    </div>
                                    </div>
                                      <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-center">
                                        <a class="btn btn-info btn-sm btn-save-change">保	存</a>
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
                                    <th>日期</th>
                                    <th>变更类型</th>
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
    
<script type="text/javascript">
    var table;
    $(function (){
    	loadTable();
    });
    
    function loadTable(){
      	if(table){
      		table.destroy();
      	}
    	table = $('#projectTable').DataTable({
    		"dom": 
                "<'row'<'col-sm-6'l><'col-sm-4'f><'col-sm-2'<'toolbar text-right'>>>" +
                "<'row'<'col-sm-12 table-responsive'tr>>" +
                "<'row'<'col-sm-5'i><'col-sm-7'p>>",
			
			//json
		    "processing": true,
       		"serverSide": true,
     		"bDestroy": true,
     		"ordering": false,
			"ajax": {
				"url":"salaryDate/listSalaryDatePage",
				"type":"post"
			},
			"columns": [
				{ "data": "changeDate", "render": function render( data, type, row, meta ){
					if(data){
						return data.substring(0,10);
					}
				}},
				{ "data": "changeType", "render": function render( data, type, row, meta ){
					if(data == 1){
						return "工作日";
					}else if(data == 2){
						return "休息日";
					}
				}},
				{ "render": function render( data, type, row, meta ){
						return '<button class="btn btn-success btn-xs" data-target="[data-modal=addSalaryDate]" data-toggle="modal" type="button" onclick="mod(' + row.salaryDateId + ',\'' + row.changeDate + '\',' + row.changeType + ');">修改</button>&nbsp;' +
                            '<button class="btn btn-danger btn-xs del-button" type="button" onclick="del(' + row.salaryDateId + ');">删除</button>';
					}
				}
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

         $("div.toolbar").html('<button class="btn btn-info btn-sm" data-target="[data-modal=addSalaryDate]" data-toggle="modal" onclick="add();">新  增</button>');
    }
    
    $('.btn-save-change').click(function(){
        $.ajax({
            url : "salaryDate/editSalaryDate",
            data : $("#editForm").serialize(),
            dataType:'json',
            type : 'post',
            success : function(message) {
                if(message.success){
                    //swal({
                    //    title: "保存成功!",
                    //    text: "",
                    //    type: "success",
                    //  confirmButtonText: "确定"
                    //});
                    $('[data-modal="addSalaryDate"]').modal('hide');
                    
                    loadTable();
                }else{
                    swal({
                        title: "保存失败!",
                        text: message.msg,
                        type: "error",
                        confirmButtonText: "确定"
                    });
                }
            }
        });
    });
    
    function add(){
    	$('#editForm input,#editForm select').val('');
    }
    
    function mod(salaryDateId, changeDate, changeType){
    	$('#salaryDateId').val(salaryDateId);
    	if(changeDate){
    		$('#changeDate').val(changeDate.substring(0,10));
    	}else{
    		$('#changeDate').val('');
    	}
    	$('#changeType').val(changeType);
    }

    function del(id){
    	swal(
    		{
	            title: "确定要删除此变动吗?",
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
	            	swal("已取消", "变动未删除", "error");
	            }
	        }
        );
    }
    
    function deleteById(id){
    	$.ajax({
			url : "salaryDate/deleteSalaryDate",
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
					//window.location.href="salaryDate/toListSalaryDate";
					loadTable();
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
        $('#datepicker').datepicker();
        $("#datepicker").on("changeDate", function(event) {
            $("#my_hidden_input").val($("#datepicker").datepicker('getFormattedDate'))
        });

        $('#datapicker2').datepicker();
        $('.input-group.date').datepicker({ });
        $('.input-daterange').datepicker({ });
    });
</script>
</body>
</html>