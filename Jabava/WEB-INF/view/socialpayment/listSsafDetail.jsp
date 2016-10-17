<%@ page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html>
<head>
	<base href="${pageContext.request.contextPath}/">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <!-- Page title -->
    <title>清单明细</title>
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
  <div class="content animate-panel">
    <div class="row">
        <div class="col-lg-12">
            <div class="hpanel">
                <div class="panel-heading">
                    <h4 class="text-center font-bold">
                      <a href="ssafPaymentBill/toListPaymentBill" type="button" class="btn btn-success btn-sm pull-left btn-return m-r-md">返回</a>
                        ${requestScope.ssPaymentBill.socialSecurityAccountName}${requestScope.ssPaymentBill.month}月清单明细
                        <a href="javascript:void(0);" type="button" class="btn btn-info btn-sm pull-right btn-return m-r-md btn-detail-view">明细</a>
                    </h4>
                </div>
                <div class="panel-body">
              <table id="detailTable" class="table table-bordered table-hover detailTable" width="100%">
                <thead>
                  <tr>
                      <td rowspan="2">内容</td>
                      <td colspan="2">人数</td>
                      <td colspan="2">企业基数</td>
                      <td colspan="2">个人基数</td>
                      <td colspan="2">缴费比例</td>
                      <td colspan="2">企业缴费金额</td>
                      <td colspan="2">个人缴费金额</td>
                      <td rowspan="2">小计</td>
                    </tr>
                    <tr>
                      <td>本期</td>
                      <td>补缴</td>
                      <td>本期</td>
                      <td>补缴</td>
                      <td>本期</td>
                      <td>补缴</td>
                      <td>企业</td>
                      <td>个人</td>
                      <td>本期</td>
                      <td>补缴</td>
                      <td>本期</td>
                      <td>补缴</td>
                    </tr>  
                </thead>
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
<div style="display:none;">
	<form id="personForm" action="" method="post">
		<input type="text" id="ssPaymentBillId" name="ssPaymentBillId" value="${requestScope.ssPaymentBill.id}">
		<input type="text" id="afPaymentBillId" name="afPaymentBillId" value="${requestScope.afPaymentBillId}">
		<input type="text" id="t" name="t" value="">
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
    	
    	//进入清单明细
    	$('.btn-detail-view').click(function(){
    		$('#personForm').attr('action','ssafPaymentBill/toListPaymentBillPerson');
            $('#personForm').submit();
    	});
    	
    	loadTable();
    });
    
    function loadTable(){
        $('#detailTable').DataTable({
	        "dom":
	        	"<'row'<'col-sm-12 table-responsive'tr>>",
	      
	        //json
	        "processing": true,
	        "serverSide": true,
	        "bDestroy": true,
	        "ordering": false,
	        "ajax": {
	        	"url":"ssafPaymentBill/listPaymentBillDetail",
	        	"data": {ssPaymentBillId: ${requestScope.ssPaymentBill.id},afPaymentBillId: ${requestScope.afPaymentBillId}}
	        },
	        "columns": [
	          { "data": "prod_name",
	            'createdCell': function(td, cellData, rowData, row , col){
	                  if( rowData.colspan && rowData.colspan > 0){
	                      $(td).attr({
	                        'colspan':rowData.colspan,
	                        'data-type':rowData.total
	                      });
	                  } 
	              }
	          },
	          { "data": "bq_num"},
	          { "data": "bj_num"},
	          { "data": "bq_base_e"}, 
	          { "data": "bj_base_e" },
	          { "data": "bq_base_p" },
	          { "data": "bj_base_p" },
	          { "data": "ratio_e" },
	          { "data": "ratio_p" },
	          { "data": "bq_amount_e" },
	          { "data": "bj_amount_e" },
	          { "data": "bq_amount_p" },
	          { "data": "bj_amount_p" },
	          { "data": "subtotal" }
	        ],
	        "columnDefs": [
	            {defaultContent: '', "targets": "_all"}
	        ],
	        "initComplete": function(settings, json) {
	        	
	        	 $('[data-type]').each(function(index,elem){
	        		  
	        		   $('[data-type='+ $(elem).data("type")+']').siblings().each(function(index, elem){
	   		        	
	   		            if(index < 8) $(elem).remove();
	   		            
	   		        });
	        		   
	        	 });
	        	 
	        	 
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
    }
</script>

</body>
</html>
