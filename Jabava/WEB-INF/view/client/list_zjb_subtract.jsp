<%@ page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html>
<head>
	<base href="${pageContext.request.contextPath}/">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <!-- Page title -->
    <title>本月减员</title>
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
  <div class="normalheader transition animated fadeIn small-header">
    <div class="hpanel">
      <div class="panel-body">
        <div id="hbreadcrumb" class="m-t-xs m-b-xs">
          <h2 class="font-normal m-b-xs text-center">
            批次号：${requestScope.hroOrderSend.batchCode }
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
                      <a href="client/list_query_change" type="button" class="btn btn-success btn-sm pull-left btn-return m-r-md">返回</a>
                    </h4>
                </div>
           <ul role="tabList" class="nav nav-tabs" id="">
              <li class="zy-ss-tab"><a href="change/listDetail/${requestScope.hroOrderSend.hroOrderSendId }/1">增员</a></li>
              <li class='zy-ss-tab active'><a href="javascript://">减员</a></li>
              <li class="zy-ss-tab"><a href="change/listDetail/${requestScope.hroOrderSend.hroOrderSendId }/2">变更</a></li>
          </ul>
      <div class="tab-content">
        <div id="tab-1" class="tab-pane active">
          <div class="panel-body"> 
              <table id="example2" class="table table-striped table-bordered table-hover" width="100%">
                <thead>
                <tr>
                    <th>姓名</th>
                    <th>证件号</th>
                    <th>政策包组合</th>
                    <th>离职日期</th>
                    <th>离职原因</th>
                    <th>服务结束月</th>
                    <th>账单月</th>
                    <th>状态</th>
                    <th>发送时间</th>
                    <th>备注</th>
                    <th>校验日志</th>
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

<script type="text/javascript">
$('#example2').DataTable({
       "dom":
       "<'row'<'col-sm-3'l><'col-sm-9'f>>" +
       "<'row'<'col-sm-12 table-responsive'tr>>" + 
       "<'row'<'col-sm-5'i><'col-sm-7'p>>",

  	"processing": true,
      "serverSide": false,
		"ajax": {
		    "url": "change/loadDetailPage",
		    "type":"POST",
		    "data": {hroOrderSendId: ${requestScope.hroOrderSend.hroOrderSendId}, type: "3"}
		},
		"lengthMenu": [[10, 20, 50, -1], [10, 20, 50, "All"]],
		"sServerMethod": "POST",
		"ordering": false,
		"columns" : [
			{"data" : "EMPLOYEE_NAME" },
			{"data" : "CARD_ID" },
			//{"data" : "CONTRACT_NAME" },
			{"data" : "SB_GROUP_NAME" },
			{"data" : "DIMISSION_DATE" },
			{"data" : "DIMISSION_TYPE_NAME" },
			{"data" : "PAYMENT_MONTH" },
			{"data" : "BILL_MONTH" },
			{"data" : "PROCESS_STATUS", "render": function(data, type, row, meta){
				var result = "";
				switch(data){
					case "0":
						result = "待处理"; break;
					case "1":
						result = "校验失败"; break;
					case "2":
						result = "校验通过"; break;
					case "3":
						result = ""; break;
					case "4":
						result = "已处理"; break;
					case "5":
						result = "已退单"; break;
					case "6":
						result = "已服务"; break;
					default:
						
				}
				return result;
			}, 'createdCell': function(td, cellData, rowData, row , col){
               
                if( rowData.PROCESS_STATUS === '1' ){
                    $(td).parent('tr').attr({
                    	'class': 'warning'
                    });
                }else if( rowData.PROCESS_STATUS === '5' ){
                	$(td).parent('tr').attr({
                    	'class': 'danger'
                    });
                }
            } },
			{"data" : "SEND_DATE" },
			{"data" : "RECEIVE_REMARK" },
			{"data" : "VALIDATE_LOG", 'createdCell': function(td, cellData, rowData, row , col){
               
                if( rowData.PROCESS_STATUS === '1'){
                    $(td).attr({
                    	'class': 'danger'
                    });
                }
            } }
		],
		"columnDefs": [
  		{defaultContent: '', targets: '_all'}
		],
  	
      "language": {
        "search": "过滤:",
        "lengthMenu": "每页显示 _MENU_ 条记录",
        "zeroRecords": "暂无数据 - 报歉啦〜",
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
</script>

</body>
</html>
