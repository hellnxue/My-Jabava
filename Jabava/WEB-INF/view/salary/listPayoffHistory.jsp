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
<title>工资发放记录</title>
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
        <div class="normalheader transition animated fadeIn small-header">
            <div class="hpanel">
                <div class="panel-body">
                    <div id="hbreadcrumb" class="m-t-xs m-b-xs">
                        <h2 class="font-normal m-b-xs text-center">
                            工资发放记录
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
                        <div class="panel-heading m-b-lg">
                            <h4>
                                <a href="javascript: void(0);" type="button" class="btn btn-success btn-sm pull-left btn-back">返　回</a>
                            </h4>
                        </div>
                        <div class="out collapse in">
                            <div class="well well-lg">
                                <div class="row">
                                    <form id="searchForm" class="form-horizontal">
                                    	<input type="hidden" name="salaryId" value="${salary.salaryId }">
                                        <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
                                            <div class="form-group">
                                                <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-4">开始月份：</label>
                                                <div class="input-group date col-xs-6 col-sm-6 col-md-6 col-lg-8">
                                                    <input type="text" class="form-control" id="startMonth" name="startMonth" />
                                                    <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
                                            <div class="form-group">
                                                <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-4">结束月份：</label>
                                                <div class="input-group date col-xs-6 col-sm-6 col-md-6 col-lg-8">
                                                    <input type="text" class="form-control" id="endMonth" name="endMonth" />
                                                    <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-center">
                                            <button class="btn btn-info btn-payoff-search" type="button">查　询</button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                        <div class="panel-body">
                            <table id="payOffHistory" class="table table-striped table-condensed table-hover table-bordered dataTable" width="100%">
                                <thead>
                                <tr>
                                    <th>员工工号</th>
                                    <th>员工姓名</th>
                                    <th>月度</th>
                                    <th>发放状态</th>
                                    <th>应税工资</th>
                                    <th>代扣税</th>
                                    <th>税后工资</th>
                                    <th>实发工资</th>
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
	<div style="display:none;">
		<form id="detailForm" action="" method="post">
			<input type="hidden" name="salaryId" value="${salary.salaryId }">
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
      	loadTable();
      	
      	$(".btn-back").click(function(){
      		$('#detailForm').attr('action','salary/toAddSalary');
            $('#detailForm').submit();
      	});
    });
    
    function loadTable(){
    	if(table){
    		table.destroy();
    	}
    	table = $('#payOffHistory').DataTable({
    		"dom": 
                "<'row'<'col-sm-12'l>>" +
                "<'row'<'col-sm-12 table-responsive'tr>>"+
                "<'row'<'col-sm-5'i><'col-sm-7'p>>",

            //json
            "processing": true,
            "serverSide": true,
            "bDestroy": true,
            "ordering": false,
            "ajax": {
                "url":"salary/listPayoffHistoryPage",
                "data": $("#searchForm").serializeObject(),
                "type": "post",
            },
            "columns": [
                { "data": "job_number" },
                { "data": "employee_name" },
                { "data": "monthly" },
                { "data": "review_status", "render": function(data, type, row, meta){
                	if(data == 1){
                		return " 未审核";
                	}else if(data == 2){
                		return "已审核";
                	}else if(data == 3){
                		return "已发放";
                	}
                }},
                { "data": "taxable_income" },
                { "data": "withholding_tax" },
                { "data": "after_tax_income" },
                { "data": "take_home_income" }
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
    
    $(".btn-payoff-search").click(function(){
    	loadTable();
    });

    //日期
    $('.input-group.date').datepicker({
        format: 'yyyymm', 
        weekStart: 1, 
        autoclose: true, 
        startView: 1, maxViewMode: 1,minViewMode:1,
        forceParse: false, 
        language: 'zh-CN'
    });
</script>
</body>
</html>