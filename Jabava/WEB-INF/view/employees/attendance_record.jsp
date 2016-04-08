<%@ page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html>
<head>
	<base href="${pageContext.request.contextPath}/">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <!-- Page title -->
    <title>考勤记录</title>
    <jsp:include flush="true" page="../common/styles.jsp"></jsp:include>
    <link rel="stylesheet" href="static/css/user_bata.css">
    <link rel="stylesheet" href="static/css/style_1.css" media="screen">

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
 
<!-- 放主要内容  开始-->

<!-- Main Wrapper -->
<div id="wrapper" class="min-h">
    <div class="normalheader transition animated fadeIn small-header">
        <div class="hpanel">
            <div class="panel-body">
                <div id="hbreadcrumb" class="pull-right m-t-lg">
                    <ol class="hbreadcrumb breadcrumb">
                        <li><a href="to_index?jump=1">首页</a></li>
                        <li>
                            <span>员工信息</span>
                        </li>
                        <li class="active">
                            <span></span>
                        </li>
                    </ol>
                </div>
                <h2 class="font-light m-b-xs">
                    员工信息
                </h2>
                <small>待定</small>
            </div>
        </div>
    </div>

<!-- 放主要内容 -->
        
        <!--考勤记录-->
        <div class="content animate-panel">
            <div class="row">
                <div class="col-lg-12">
                    <div class="hpanel hblue">          
                      <!--引入员工信息导航 开始--> 
                      <jsp:include flush="true" page="employee_nav.jsp"></jsp:include>
                      <!--引入员工信息导航 结束-->
                        <div class="panel-heading hbuilt">
                            <div class="panel-tools">
                           		<button type="button" class="btn btn-success btn-xs lz_gl"><span class="bold">离职管理&gt;&gt;</span></button>
                            </div>
                            <h4>考勤记录</h4>          
                    	</div>
                        <div class="panel-body" id="allcheck">
                            <table id="attendance_table" class="table table-striped table-bordered table-hover" width="100%">
                                <thead>
                                <tr>
                                    <th>月份</th>
                                    <th>迟到次数</th>
                                    <th>早退次数</th>
                                    <th>事假天数</th>
                                    <th>病假天数</th>
                                    <th>出差天数</th>
                                    <th>平日加班(h)</th>
                                    <th>周末加班(h)</th>
                                    <th>节假日加班(h)</th>
                                    <th>调休天数</th>
                                    <th>夜班天数</th>
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
  		<!--考勤记录 end-->  
      
   	    <!-- Footer-->
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
<script src="static/bootstrap/vendor/moment/moment.js"></script>
<script src="static/bootstrap/vendor/xeditable/bootstrap3-editable/js/bootstrap-editable.min.js"></script>
<script src="static/bootstrap/vendor/select2-3.5.2/select2.min.js"></script>
<script src="static/bootstrap/vendor/bootstrap-touchspin/dist/jquery.bootstrap-touchspin.min.js"></script>
<!-- for datatable -->
<script src="static/bootstrap/vendor/datatables/media/js/jquery.dataTables.min.js"></script>
<script src="static/bootstrap/vendor/datatables_plugins/integration/bootstrap/3/dataTables.bootstrap.min.js"></script>
<!-- alert -->
<script src="static/bootstrap/vendor/sweetalert/lib/sweet-alert.min.js"></script>

<!-- App scripts -->
<script src="static/bootstrap/scripts/homer.js"></script>
<script src="static/js/yuangong.js"></script>
<script>
   $(function () {
      	var table = $('#attendance_table').dataTable({
    		"dom":
			
    		"<'row'<'col-sm-12 table-responsive'tr>>",
			
    		"processing": true,
			"serverSide": true,
			"ajax": "static/json/attendance.json",
			"columns": [
            { "data": "yearMonthRecord" },
            { "data": "lateTimes" },
            { "data": "leaveEarlyTimes" },
            { "data": "allLeave" },
            { "data": "sickLeave" },
            { "data": "business" },
            { "data": "workOvertime" },
			{ "data": "weekOvertime" },
			{ "data": "holidaysOvertime" },
			{ "data": "adjustDay" },
			{ "data": "nightShift" }
			]
    	   }); 
				
    	
    }); 

</script>
</body>
</html>