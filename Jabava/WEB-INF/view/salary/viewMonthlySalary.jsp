<%@page import="com.jabava.utils.RequestUtil"%>
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
<title>月度工资查看</title>
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
                                <a class="btn btn-success btn-sm pull-left btn-return m-r-md" type="button" href="monthlySalary/toListMonthlySalary">返回</a>
                                月度工资查看
                            </h4>     
                        </div>
                            <div class="panel-body">
                                <form class="form-horizontal">
                                    <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
                                         <div class="form-group">
                                            <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-5">月度：</label>
                                            <div class="input-group date col-xs-6 col-sm-6 col-md-6 col-lg-7"> 
                                                <input type="text" class="form-control" id="monthly" name="monthly" value="${monthlySalary.monthly }" disabled="disabled"/>
                                                <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
                                        <div class="form-group">
                                            <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-5">用途：</label>
                                            <div class="col-xs-6 col-sm-6 col-md-6 col-lg-7">
                                                <select class="form-control" id="usageFlag" name="usageFlag" disabled="disabled">
                                                    <option value="">全部</option>
                                                    <c:forEach var="salaryType" items="${requestScope.salaryTypeList }" varStatus="status">
                                                    	<option value="${salaryType.baseDataId }">${salaryType.baseDataName }</option>
                                                    </c:forEach>
                                                </select>
                                            </div>   
                                        </div>
                                    </div>
                                    <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
                                        <div class="form-group">
                                            <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-5">版本号：</label>
                                            <div class="col-xs-6 col-sm-6 col-md-6 col-lg-7">
                                               <input type="text" class="form-control" id="version" name="version" value="${monthlySalary.version }" disabled="disabled" />
                                            </div>   
                                        </div>
                                    </div>
                                    <!--div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
                                         <div class="form-group">
                                            <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-5">审核时间：</label>
                                            <div class="input-group date col-xs-6 col-sm-6 col-md-6 col-lg-7"> 
                                                    <input type="text" class="form-control" name="" />
                                                    <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                            </div>

                                        </div>
                                    </div>
                                    <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
                                        <div class="form-group">
                                            <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-5">审核人：</label>
                                            <div class="col-xs-6 col-sm-6 col-md-6 col-lg-7">
                                               <input type="text" class="form-control" name="" />
                                            </div>   
                                        </div>
                                    </div>
                                    <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4 ">
                                        <div class="form-group">
                                            <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-5">审核状态：</label>
                                            <div class="col-xs-6 col-sm-6 col-md-6 col-lg-7">
                                                <select class="form-control">
                                                    <option value="">全部</option>
                                                    <option value="">已审核</option>
                                                    <option value="">未审核</option>
                                                </select>
                                            </div>   
                                        </div>
                                    </div-->
                                </form>
                            </div>
                        <div class="panel-heading" style="display:none;">
                            <h4 class="text-center font-bold">
                                <button class="btn btn-info btn-sm pull-right btn-return" type="button">明细表</button>
                            </h4>     
                        </div>
                        <div class="panel-body m-t-lg"> 
                         <div>
                            <table id="detailTable" class="table table-condensed table-hover table-bordered dataTable table-responsive" width="100%">
                                <thead>
                                <tr>
                                    <th>员工工号</th>
                                    <th>员工姓名</th>
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
    	$('#usageFlag').val(${monthlySalary.usageFlag});
    	
    	loadHeader(${monthlySalary.monthlySalaryId});
    });
    
	function loadHeader(monthlySalaryId){
    	$.ajax({
			url:"monthlySalary/loadMonthlySalaryPersonHeader",
			type : "POST",
			dataType:'json',
			data: {monthlySalaryId: monthlySalaryId},
			error: function(XMLHttpRequest, textStatus, errorThrown){
				alert(textStatus);
			},
			success: function(data){
				if(data){
			    	if(table){
			    		detailTable.destroy();
			    	}
			    	//in case the columns change
		    		$('#detailTable').empty();
			    	
					initDetailTable(data, monthlySalaryId);
				}
			}
		});
    }
    
    function initDetailTable(data, monthlySalaryId){
    	//读取数据，初始化表头及DataTables的columns
    	var columns = [];
    	
    	//动态添加表头
		var firstRow = $("<tr></tr>");
    	$.each(data,function(){
   			var ele = '<th>' + this.showName + '</th>';
   			firstRow.append($(ele));
   			if(this.fieldName == 'monthlySalaryPersonId' || this.fieldName == 'monthlySalaryId' || this.fieldName == 'personId'){
   				columns.push({"data":this.fieldName, "visible":false});
   			}else{
   				columns.push({"data":this.fieldName});
   			}
    	});
    	
    	// append header to the table
    	var header = $("<thead></thead>");
    	header.append(firstRow);
    	$('#detailTable').append(header);
    	
    	initTable(columns, monthlySalaryId);
    }
    
    function initTable(columns, monthlySalaryId){
    	table = $('#detailTable').DataTable({
    		"dom":
	    		//"<'row'<'col-sm-12'l>>" +
	    		"<'row'<'col-sm-6'l><'col-sm-6'<'toolbar text-right'>>>" +
	    		"<'row'<'col-sm-12 table-responsive'tr>>" + 
	    		"<'row'<'col-sm-5'i><'col-sm-7'p>>",
				
		    "processing": true,
    		"serverSide": true,
    		"bDestroy": true,
    		"sServerMethod": "POST",
    		"ordering": false,
    		//"bSort": true,
    		"columns": columns,
    		"columnDefs": [
    			{defaultContent: '', targets: '_all'}
			],
    		"ajax": {
    			"url": "monthlySalary/loadMonthlySalaryPersonData",
    			"data": {
    				"monthlySalaryId": monthlySalaryId
    			}
    		},
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
    	if('${monthlySalary.lastFlag}' == '1'){
    		var html = '';
            html += '<button class="btn btn-info btn-sm" type="button" onclick="sendSlip();">发送工资条</button>&nbsp;';
    		<% if(RequestUtil.hasPower("monthlysalary_view_au")){ %>
    		//html += '<button class="btn btn-info btn-sm" type="button" onclick="audit();">审核</button>&nbsp;';
    		<% } %>
			<% if(RequestUtil.hasPower("monthlysalary_view_ok")){ %>
			html += '<button class="btn btn-info btn-sm" type="button" onclick="payoff();">一键发放</button>&nbsp;';
    		<% } %>
			<% if(RequestUtil.hasPower("monthlysalary_view_bp")){ %>
			html += '<button class="btn btn-info btn-sm" type="button" onclick="genReport(14);">银行报盘</button>&nbsp;';
    		<% } %>
			<% if(RequestUtil.hasPower("monthlysalary_view_tb")){ %>
			html += '<button class="btn btn-info btn-sm" type="button" onclick="genReport(15);"><i class="fa">税表</i></button>';
    		<% } %>
    		$(".toolbar").html(html);
    	}
    	//$("#detailTable").css('width', 'auto');
    	$("table.table-bordered th:last-child, table.table-bordered td:last-child").css("border-right-width","1px");
    }

    function genReport(reportId){
    	$.ajax({
            url : "report/generateReport",
            data : {reportId: reportId, monthlySalaryId: ${monthlySalary.monthlySalaryId}},
            dataType:'json',
            type : 'post',
            async : false,
            success : function(message) {
				if(message.success){
            		window.open('common/downloadFile?fileId=' + message.fileId);
				}else{
					swal({
                        title: "导出失败!",
                        text: message.msg,
                        type: "error",
                        confirmButtonText: "确定"
                    });
				}
            }
		});
    }

    function payoff(){
    	$.ajax({
            url : "monthlySalary/payoff",
            data : {monthlySalaryId: ${monthlySalary.monthlySalaryId}},
            dataType:'json',
            type : 'post',
            async : false,
            success : function(message) {
				alert(message.msg);
            }
		});
    }
    
    function sendSlip(){
    	$.ajax({
            url : "monthlySalary/sendSalarySlip",
            data : {monthlySalaryId: ${monthlySalary.monthlySalaryId}},
            dataType:'json',
            type : 'post',
            async : false,
            success : function(message) {
				alert(message.msg);
				if(message.success){
					window.location.reload();
				}
            }
		});
    }
   
    // 日历
    $(function(){
        $('#datepicker').datepicker();
        $("#datepicker").on("changeDate", function(event) {
            $("#my_hidden_input").val(
                    $("#datepicker").datepicker('getFormattedDate')
            )
        });

        $('#datapicker2').datepicker();
        $('.input-group.date').datepicker({
            format: 'yyyymm', 
            weekStart: 1, 
            autoclose: true, 
            startView: 1, maxViewMode: 1,minViewMode:1,
            forceParse: false, 
            language: 'zh-CN'
        });
        $('.input-daterange').datepicker({ });
    });

</script>
</body>
</html>