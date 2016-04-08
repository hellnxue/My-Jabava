<%@ page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html>
<head>
	<base href="${pageContext.request.contextPath}/">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    
    <!-- Page title -->
    <title>社保</title>
    <jsp:include flush="true" page="../common/styles.jsp"></jsp:include>
    <link rel="stylesheet" href="static/css/user_bata.css">
    <link rel="stylesheet" href="static/css/bill.css">
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
<!-- 放主要内容 -->
<div class="content animate-panel check_hide">
    <div class="row">
        <div class="col-lg-12">
            <div class="hpanel">
                <div class="panel-heading">
                    <h4 class="text-center font-bold">
                    <a href="shebao/shebaoPageIndex" class="btn btn-success btn-sm pull-left">　返回　</a>
                    <span data-shebao="date">@shebaoDate 社保明细</span>
                    </h4>
                </div> 
                <div class="panel-body">
	                <table id="billTable" class="table table-bordered table-hover" width="100%">
		                <thead>
			                <tr>
			                    <th>employeeId</th>
			                    <th>年月</th>
			                    <th>员工姓名</th>
			                    <th>证件号码</th>
			                    <th>雇佣状态</th>
			                    <th>社保企业金额</th>
			                    <th>社保个人金额</th>
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
<script src="static/bootstrap/vendor/bootstrap-datepicker-master/dist/js/bootstrap-datepicker.js"></script>
<script src="static/bootstrap/vendor/bootstrap-datepicker-master/dist/locales/bootstrap-datepicker.zh-CN.min.js"></script>

<!-- for datatable -->
<script src="static/bootstrap/vendor/datatables/media/js/jquery.dataTables.min.js"></script>
<script src="static/bootstrap/vendor/datatables_plugins/integration/bootstrap/3/dataTables.bootstrap.min.js"></script>
<!-- alert -->
<script src="static/bootstrap/vendor/sweetalert/lib/sweet-alert.min.js"></script>

<!-- App scripts -->
<script src="static/bootstrap/scripts/homer.js"></script>
<script>
    var search=location.search;
    var reportMonth=search.split("=")[1];
    
	var table, detailTable;
	var datepickerObject={
	        	format: 'yyyymm', 
	            weekStart: 1, 
	            autoclose: true, 
	            startView: 1, maxViewMode: 1,minViewMode:1,
	            forceParse: false, 
	            language: 'zh-CN'
   	         };
	var paramsObject={
			policyGroupType:1,
		    billYm:reportMonth
			
	};
	
	 
	var date=reportMonth;
	
    
    function loadTable(params){
    	
    	if(table){
    		table.destroy();
    	}
      	table = $('#billTable').DataTable({
    		"dom":
    		
    		//"<'row'<'col-sm-3 col-md-5 col-lg-5'l><'col-sm-5 col-md-4 col-lg-4'f><'col-sm-4 col-md-3 col-lg-3 text-right'<'toolbar'>>>" +
            "<'row'<'col-sm-3 col-md-5 col-lg-5'l><'col-sm-9 col-md-7 col-lg-7'f>>" +
            "<'row'<'col-sm-12 table-responsive'tr>>" +
            "<'row'<'col-sm-5'i><'col-sm-7'p>>",
			
			//json
		    "processing": true,
       		"serverSide": true,
     		"bDestroy": true,
			"ajax": {
				//"url":"efArap/billListPage",//initSheBaoInfo
				"url":"shebao/initSheBaoInfo",
				"type":"POST",
				"async":false,
				"data": params ? params : {}
				
			},
			"columns": [
			  	{ "data": "employeeId","visible":false },  
				{ "data": "reportMonth", 'render': function( data, type, row, meta ){
					$('[data-shebao=date]').text( $('[data-shebao=date]').text().replace('@shebaoDate', data) )
					return data;
				} },
				{ "data": "employeeName" },
				{ "data": "cardId" },
				{ "data": "hireStatus", "render": function render( data, type, row, meta ){
					switch(data){
					case "1":
						return "入职中";
						break;
					case "2":
						return "在职";
					case "3":
						return "离职中";
						break;
					case "4":
						return "离职";
						break;
					}
				} },
				{ "data": "companySum" },
				{ "data": "individealSum" }, 
				{ "render": function render( data, type, row, meta ){
	            	//	return '<td><button class="btn btn-info btn-xs" type="button">支付</button></td>';
						return "<a href='shebao/shebaoDetailPage?employeeId="+row.employeeId+"&employeeName="+row.employeeName+"&reportMonth="+row.reportMonth+"' ><button class='btn btn-success btn-xs dropdown-toggle' type='button'  title='查看'>查看</button></a>";//连接到社保详情界面
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
		
    	$("#billTable").css('width', '100%');

		$("div.toolbar").html('<div class=\"form-group search-unit\">'+
                         '<div class=\"input-group date col-lg-12\" data-date-format=\"yyyymm\">'+
                            '<input type=\"text\" class=\"form-control\" id=\"billYm\"  value=\"'+date+'\">'+  
                          '<span class=\"input-group-addon\"><i class=\"glyphicon glyphicon-th\"></i></span>'+
                       '</div></div>');
		
		 $('.input-group.date input').datepicker(datepickerObject);
 		 $('.input-group.date span').datepicker(datepickerObject);  
 		 
 		  $("#billYm").on("change",function () {
			
	   		      if($('#billYm').val()){
	   		    	 paramsObject.billYm= $('#billYm').val();
	   		    	 date=$('#billYm').val();
	   		         $(this).unbind("change");
	   		    	 loadTable(paramsObject);  
	   		     }
	   	   });   
    }
    
   
</script>

<!--日历-->
<script>
    $(function(){
    	loadTable(paramsObject);
        $('.input-group.date').datepicker(datepickerObject);
        
        
    });
</script>

</body>
</html>
