<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
	<base href="${pageContext.request.contextPath}/">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <!-- Page title -->
    <title>社保详情</title>
	<jsp:include flush="true" page="../common/styles.jsp"></jsp:include>
    <link rel="stylesheet" href="static/css/user.css">
	<script>
	  function downloadFile(path) {
		  var data = JSON.stringify($('#searchOrderForm').serializeObject());
			window.open(path+data);
		}
</script>
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
<div class="content animate-panel">
    <div class="row">
        <div class="col-lg-12">
            <div class="hpanel">
            	<div class="panel-heading">
                    <h4 class="text-center font-bold">
                    <a href="javascript:history.go(-1)" class="btn btn-success btn-sm pull-left">　返回　</a>
                    <span>${requestScope.reportMonth}</span> <span>${requestScope.employeeName}</span> 个人社保明细
                    </h4>
                </div> 
                <ul class="nav nav-tabs">
                    <li class="active"><a data-toggle="tab" href="#tab-1">汇缴信息</a></li>
                    <li class=""><a data-toggle="tab" href="#tab-2">补缴信息</a></li>
                    <li class=""><a data-toggle="tab" href="#tab-3">补差信息</a></li>
                </ul>
                <div class="tab-content">
                    <div id="tab-1" class="tab-pane active">
                        <div class="panel-body" id="allcheck"> 
                            <table id="orderList" class="table table-bordered table-hover rost_table" width="100%">
                            <thead>
                            <tr>
                                <th>年月</th>
                                <th>政策包</th>
                                <th>产品</th>
                                <th>社保月</th>
                                <th>付费月</th>
                                <th>企业基数</th>
                                <th>个人基数</th>
                                <th>企业比例</th>
                                <th>个人比例</th>
                                <th>企业附加额</th>
                                <th>个人附加额</th>
                                <th>企业金额</th>
                                <th>个人金额</th>
                            </tr>
                            </thead>
                            <tbody>                   
                            </tbody>
                            <tfoot>
                                <tr>
                                    <th colspan="11">合计</th>
                                    <th></th>
                                    <th></th>
                                </tr>
                            </tfoot>
                            </table>
                        </div>
                    </div>
                    <div id="tab-2" class="tab-pane">
                        <div class="panel-body" id="allcheckTwo"> 
                            <table id="orderListTwo" class="table table-bordered table-hover rost_table" width="100%">
                            <thead>
                            <tr>
                                <th>年月</th>
                                <th>政策包</th>
                                <th>产品</th>
                                <th>社保月</th>
                                <th>付费月</th>
                                <th>企业基数</th>
                                <th>个人基数</th>
                                <th>企业比例</th>
                                <th>个人比例</th>
                                <th>企业附加额</th>
                                <th>个人附加额</th>
                                <th>企业金额</th>
                                <th>个人金额</th>
                            </tr>
                            </thead>
                            <tbody>                   
                            </tbody>
                            <tfoot>
                                <tr>
                                    <th colspan="11">合计</th>
                                    <th></th>
                                    <th></th>
                                </tr>
                            </tfoot>
                            </table>
                        </div>
                    </div>
                    <div id="tab-3" class="tab-pane">
                        <div class="panel-body" id="allcheckThird"> 
                            <table id="orderListThird" class="table table-bordered table-hover rost_table" width="100%">
                            <thead>
                            <tr>
                                <th>年月</th>
                                <th>政策包</th>
                                <th>产品</th>
                                <th>社保月</th>
                                <th>付费月</th>
                                <th>企业基数</th>
                                <th>个人基数</th>
                                <th>企业比例</th>
                                <th>个人比例</th>
                                <th>企业附加额</th>
                                <th>个人附加额</th>
                                <th>企业金额</th>
                                <th>个人金额</th>
                            </tr>
                            </thead>
                            <tbody>                   
                            </tbody>
                            <tfoot>
                                <tr>
                                    <th colspan="11">合计</th>
                                    <th></th>
                                    <th></th>
                                </tr>
                            </tfoot>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

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
	<script src="static/bootstrap/vendor/xeditable/bootstrap3-editable/js/bootstrap-editable.min.js"></script>

    <script src="static/bootstrap/vendor/select2-3.5.2/select2.min.js"></script>
    <script src="static/bootstrap/vendor/bootstrap-touchspin/dist/jquery.bootstrap-touchspin.min.js"></script>
    <script src="static/bootstrap/vendor/bootstrap-datepicker-master/dist/js/bootstrap-datepicker.js"></script>
	<!-- for editable -->
	<script src="static/bootstrap/vendor/moment/moment.js"></script>
	 
	<!-- for datatable -->
	<script src="static/bootstrap/vendor/datatables/media/js/jquery.dataTables.min.js"></script>
	<script src="static/bootstrap/vendor/datatables_plugins/integration/bootstrap/3/dataTables.bootstrap.min.js"></script>
	<!-- alert -->
	<script src="static/bootstrap/vendor/sweetalert/lib/sweet-alert.min.js"></script>

    <!-- App scripts -->
    <script src="static/bootstrap/scripts/homer.js"></script>
    
	<script>
	var employeeId="${requestScope.employeeId}";
	var hjTable=null;
	var bjTable=null;
	var bcTable=null;
	
	var paramsObject={
			employeeId:employeeId,
			reportMonth:"${requestScope.reportMonth}",
			policyGroupType:1,// 1-社保  2-公积金
			payType:1  //1-汇缴  2-补缴
			 
	};
	
	function loadTableFirst(){
		 
		hjTable=$('#orderList').dataTable({
    		"dom":
    		"<'row'<'col-sm-12 table-responsive'tr>>",
			
			"processing": true,
			"serverSide": true,				
			 "ajax":{
 	            "url": "shebao/getSbGJJDetail",
 	            "async":false,
 	            "type":"POST",
 	            "data": paramsObject
			},
			"columns": [
            { "data": "reportMonth" },
            { "data": "policyName" },
            { "data": "prodName" },
            { "data": "sbMonth" },
            { "data": "reportMonth" },//付费月？？？
            { "data": "companyBase" },
            { "data": "individualBase" },
            { "data": "companyRatio" },
			{ "data": "individualRatio" },
			{ "data": "companyAppend" },
			{ "data": "individualAppend" },
			{ "data": "companySum" },
			{ "data": "individualSum" } 
			 
			
			],"columnDefs": [
			     			{defaultContent: '', targets: '_all'}
			    			],
			
   	 		"language": {
                 "search": "过滤:",
	            "lengthMenu": "每页显示 _MENU_ 条记录",
	            "zeroRecords": "没有数据",
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
		heji("#orderList");
		
	}
	
	function heji(ID){
		
		var x=0;
		var y=0;
		$(ID+' tbody tr td:nth-child(12)').each(function(i){
			
			x+=parseFloat($(this).html());
		});
		$(ID+' tbody tr td:nth-child(13)').each(function(i){
			
			y+=parseFloat($(this).html());
		});
		
		if(x){
			$(ID+' tfoot th:eq(1)').html(x.toFixed(2));
		}
		
		if(y){
			$(ID+' tfoot th:eq(2)').html(y.toFixed(2));
		}
	}
	
	  function loadTableSecond(){
		
		paramsObject.payType=2;
		 $('#orderListTwo').dataTable({
		    		"dom":
		    		"<'row'<'col-sm-12 table-responsive'tr>>",
					
					"processing": true,
					"serverSide": true,				
					 "ajax":{
		 	            "url": "shebao/getSbGJJDetail",
		 	            "type":"POST",
		 	            "async":false,
		 	            "data": paramsObject
					},
					"columns": [
					            { "data": "reportMonth" },//年月，付费月都用的是报表月吗？
					            { "data": "policyName" },
					            { "data": "prodName" },
					            { "data": "sbMonth" },
					            { "data": "reportMonth" },//付费月？？？
					            { "data": "companyBase" },
					            { "data": "individualBase" },
					            { "data": "companyRatio" },
								{ "data": "individualRatio" },
								{ "data": "companyAppend" },
								{ "data": "individualAppend" },
								{ "data": "companySum" },
								{ "data": "individualSum" } 
					
					],
					"columnDefs": [
					     			{defaultContent: '', targets: '_all'}
					    			],
					
		   	 		"language": {
		                 "search": "过滤:",
			            "lengthMenu": "每页显示 _MENU_ 条记录",
			            "zeroRecords": "没有数据",
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
		 heji("#orderListTwo");
	} 
	  
	  function loadTableThird(){
			paramsObject.payType=2;
			paramsObject.backpayType=2;
			 $('#orderListThird').dataTable({
			    		"dom":
			    		"<'row'<'col-sm-12 table-responsive'tr>>",
						
						"processing": true,
						"serverSide": true,				
						 "ajax":{
			 	            "url": "shebao/getSbGJJDetail",
			 	            "type":"POST",
			 	            "async":false,
			 	            "data": paramsObject
						},
						"columns": [
						            { "data": "reportMonth" },//年月，付费月都用的是报表月吗？
						            { "data": "policyName" },
						            { "data": "prodName" },
						            { "data": "sbMonth" },
						            { "data": "reportMonth" },//付费月？？？
						            { "data": "companyBase" },
						            { "data": "individualBase" },
						            { "data": "companyRatio" },
									{ "data": "individualRatio" },
									{ "data": "companyAppend" },
									{ "data": "individualAppend" },
									{ "data": "companySum" },
									{ "data": "individualSum" } 
						
						],
						"columnDefs": [
						    			{defaultContent: '', targets: '_all'}
									],
						
			   	 		"language": {
			                 "search": "过滤:",
				            "lengthMenu": "每页显示 _MENU_ 条记录",
				            "zeroRecords": "没有数据",
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
			 heji("#orderListThird");
		}   
	  
</script>
 
 <script>
 $(function(){
	 
	    	loadTableFirst(); 
	 	    loadTableSecond();  
	 	   	loadTableThird();    
 });
 </script>

</body>
</html>