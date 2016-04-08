<%@ page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html>
<head>
	<base href="${pageContext.request.contextPath}/">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <!-- Page title -->
    <title>实付账单</title>
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
                    <a href="efArap/toBalanceBill" class="btn btn-success btn-sm pull-left">　返回　</a>
                    ${requestScope.ym} 实付金额
                    </h4>
                </div>     
                <div class="panel-body">
                    <table id="billTable" class="table table-bordered table-hover" width="100%">
		                <thead>
			                <tr>
			                    <th>账单年月</th>
			                    <th>服务人数</th>
			                    <th>总金额</th>
			                    <th>付款日期</th>
			                    <th>付款金额</th>
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
    $(function () {
    	loadTable();
    });
    
    function loadTable(params){
      	$('#billTable').DataTable({
    		"dom":
    	
    		"<'row'<'col-sm-12 table-responsive'tr>>",
			
			//json
		    "processing": true,
       		"serverSide": true,
     		"bDestroy": true,
            "ordering": false,
			"ajax": {
				"url":"efReceiptOwned/receiptOwnedListPage",
				"data": {
					"ym" : ${requestScope.ym},
					"amount": ${requestScope.amount}
				}
			},
            "columnDefs": [
                {"orderable": false, "targets": "_all"}
            ],
			"columns": [
				{ "data": "ym",
                  'createdCell': function(td, cellData, rowData, row , col){
                        /* 功能做完记得把这段删除
                        if( row != 0 && row % 2 == 0 ){
                            rowData.colspan = -1
                        }else if( row % 2 == 1 ){
                            rowData.colspan = 2
                        }else{
                            rowData.colspan = 1
                        }*/

                        if( rowData.rowspan && rowData.rowspan >= 1){
                            $(td).attr('rowspan', rowData.rowspan);
                        //}else if( rowData.colspan && rowData.colspan < 1){
                        }else{
                        	$(td).remove();
                        }
                    }
                },
                { "data": "serviceNums",
                  'createdCell': function(td, cellData, rowData, row , col){
                	  if( rowData.rowspan && rowData.rowspan >= 1){
                          $(td).attr('rowspan', rowData.rowspan);
                      //}else if( rowData.colspan && rowData.colspan < 1){
                      }else{
                      	$(td).remove();
                      }
                    }
                },
		    	{ "data": "amount",
                  'createdCell': function(td, cellData, rowData, row , col){
                	  if( rowData.rowspan && rowData.rowspan >= 1){
                          $(td).attr('rowspan', rowData.rowspan);
                      //}else if( rowData.colspan && rowData.colspan < 1){
                      }else{
                      	$(td).remove();
                      }
                    }
                }, 
				{ "data": "transDate" },
				{ "data": "matchAmount" }
			],
			
   	 		"language": {
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
</script>

<!--日历-->
<script>
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
