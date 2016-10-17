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
<title>月度工资管理</title>
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
                            月度工资管理
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
                                <form class="form-horizontal" id="msForm">
                                    <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
                                         <div class="form-group">
                                            <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-5">月份：</label>
                                            <div class="col-xs-6 col-sm-6 col-md-6 col-lg-7">
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
                                            <div class="col-xs-6 col-sm-6 col-md-6 col-lg-7">
                                                <select class="form-control" id="usageFlag" name="usageFlag">
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
                                            <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-5">有效版本：</label>
                                            <div class="col-xs-6 col-sm-6 col-md-6 col-lg-7">
                                                <select class="form-control" id="lastFlag" name="lastFlag">
                                                    <option value="1">是</option>
                                                    <option value="0">否</option>
                                                </select>
                                            </div>   
                                        </div>
                                    </div>
                                    <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4 ">
                                        <div class="form-group">
                                            <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-5">分公司：</label>
                                            <div class="col-xs-6 col-sm-6 col-md-6 col-lg-7">
                                                <select class="form-control" id="organizationId" name="organizationId">
                                                    <!--option value="">全部</option-->
                                                    <c:forEach var="org" items="${requestScope.orgList }" varStatus="status">
                                                    	<option value="${org.organizationId }">${org.organizationName }</option>
                                                    </c:forEach>
                                                </select>
                                            </div>   
                                        </div>
                                    </div>
                                      <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-center">
                                        <a class="btn btn-info btn-sm btn-ms-query">查　询</a>
                                        <button class="btn btn-default m-l" type="reset">重置</button>
                                    </div>
                                </form>
                            </div>
        
                        <div class="panel-body m-t-md"> 
                         <div>
                            <table id="projectTable" class="table table-condensed table-hover table-bordered dataTable" width="100%">
                                <thead>
                                <tr>
                                    <th>月份</th>
                                    <th>分公司</th>
                                    <th>用途</th>
                                    <th>版本号</th>
                                    <th>是否有效</th>
                                    <th>状态</th>
                                    <th>审核人</th>
                                    <th>审核时间</th>
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
        </div> 
              
        <!--主要内容结束-->

        <!-- 放页脚  开始-->
        <jsp:include flush="true" page="../common/foot.div.jsp"></jsp:include>
        <!-- 放页脚  结束-->

    </div>
    <div style="display:none;">
    	<form id="detailForm" action="" method="post">
    		<input type="hidden" id="monthlySalaryId" name="monthlySalaryId">
    	</form>
    </div>
    
    
    <div class="modal fade" data-modal="confReport">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">Modal title</h4>
        </div>
        <div class="modal-body">
            
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            <button type="button" class="btn btn-primary">Save changes</button>
        </div>
    </div><!-- /.modal-content -->
</div><!-- /.modal-dialog -->
</div><!-- /.modal -->

    
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
	jQuery.prototype.serializeObject=function(){  
        var obj=new Object();  
        $.each(this.serializeArray(),function(index,param){  
          if(!(param.name in obj)){  
            obj[param.name]=param.value;  
          }  
        });  
        return obj;  
    };


    
	
    $(function (){
    	$('#msForm')[0].reset();
    	
    	$('.btn-ms-query').click(function(){
    		//校验
    		
    		loadTable($('#msForm').serializeObject());
    	});
    	

    	
      	loadTable();

    });
    
    function loadTable(params){
        console.log(params);
    	if(table){
    		table.destroy();
    	}
    	table = $('#projectTable').DataTable({
    		"dom": 
                "<'row'<'col-sm-4'l><'col-sm-5'f><'col-sm-3'<'toolbar text-right'>>>" +
                "<'row'<'col-sm-12 table-responsive'tr>>" +
                "<'row'<'col-sm-5'i><'col-sm-7'p>>",
			 
			//json
		    "processing": true,
        	"serverSide": true,
      		"bDestroy": true,
      		"ordering": false,
			"dataType": 'json',
			"ajax": {
				"url":"monthlySalary/listMonthlySalaryPage",
				"type": "post",
				"data": params ? params : {}
			},
			"columns": [
				{ "data": "monthly" },
				{ "data": "organizationName" },
				{ "data": "usageFlagName" },
				{ "data": "version"},
				{ "data": "lastFlag", "render": function( data, type, row, meta ){
					return (data == 1) ? "是" : "否";
				} },
		    	{ "data": "reviewStatus", "visible": false },
                { "data": "reviewer", "visible": false },
                { "data": "reviewDate", "visible": false },
				{ "render": function render( data, type, row, meta ){
					<% if(RequestUtil.hasPower("monthlysalary_vm")){ %>
			 		return '<button class="btn btn-info btn-xs view-button" type="button" onclick="view(' + row.monthlySalaryId + ');">查看</button>';
			 		<% }else{ %>
			 		return '';
			 		<% } %>
			 	}}
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
        <% if(RequestUtil.hasPower("monthlysalary_gm")){ %>
        strHtml += '<a class="btn btn-info btn-sm btn-ms-generate m-r-sm">生  成</a>';
        <% } %>
        <% if(RequestUtil.hasPower("monthlysalary_ex")){ %>
        strHtml += '<a href="report/commonGenReport?reportId=5" class="btn btn-warning btn-sm" data-toggle="modal" data-target="[data-modal=confReport]">导出</a>';
        <% } %>
        $('.toolbar').html(strHtml);
        $('.btn-ms-generate').on('click', function(){
            window.location.href="monthlySalary/toGenerateMonthlySalary";
        });
    }
    
    function view(id){
    	$('#monthlySalaryId').val(id);
		$('#detailForm').attr('action','monthlySalary/viewMonthlySalary');
		$('#detailForm').submit();
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