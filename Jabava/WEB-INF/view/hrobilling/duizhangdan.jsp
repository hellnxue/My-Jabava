<%@ page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html>
<head>
	<base href="${pageContext.request.contextPath}/">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <!-- Page title -->
    <title>对账单</title>
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
                <div class="panel-body">
	                <table id="billTable" class="table table-bordered table-hover" width="100%">
		                <thead>
			                <tr>
			                    <th>账单年月</th>
			                    <th>应付金额</th>
			                    <th>实付金额</th>
			                    <th>余额</th>			                    
			                </tr>
		                </thead>
		                <tbody>		                                                                                                                          
		                </tbody>
	                </table>
                  <div class="text-right">
                        <div class="btn-group">
                            <button id="previous" class="btn btn-default btn-xs">上一年</button>
                            <button id="year" class="btn btn-info btn-xs">${requestScope.year}</button>
                            <button id="next" class="btn btn-default btn-xs" disabled="disabled">下一年</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>
</div>

<div class="zhang_dan" style=" display:none;">
 <div class="panel-body " style="display:none; background:#fff; margin:32px 38px 0;">
    <h4 class="font-light m-b-xs">
        账单查看
    </h4>
    <small class="zhangdan_nianyues">账单年月</small>
    <small class="zhangdan_nianyues" id="billYmShow">&nbsp;</small>
</div>
<div class="content animate-panel">
  <div class="row">
    <div class="col-lg-12">
        <div class="hpanel">
            <div class="panel-heading ">
               <h4 class="text-center font-bold">
                 <a class="btn btn-success btn-sm pull-left" onclick="backToBill();">　返回　</a>
                 账单号XXXX明细
               </h4>
               <div class="pull-right">
                 <button class="btn btn-success " type="button" style="display:none;">支付</button>
                 <button class="btn btn-default " type="button"  style="display:none;">已支付</button>
               </div>
            </div>
            <div class="panel-body panel-bag">
              <table id="detailTable" class="table table-bordered table-hover" width="100%">
                <thead id="detailHeader">
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

<!-- 放页脚  开始-->
<jsp:include flush="true" page="../common/foot.div.jsp"></jsp:include>
<!-- 放页脚  结束-->

</div>
<div style="display:none">
	<form id="detailForm" action="efReceiptOwned/toReceiptOwned" method="post">
		<input type="hidden" id="ym" name="ym">
		<input type="hidden" id="amount" name="amount">
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
	var currentYear = ${requestScope.year};
	var table;
    $(function () {
    	loadTable({"year": currentYear});
    	
    	$('#previous').click(function () {
   		   var year = $('#year').text();
   		   year = eval(year) - 1;
   		   $('#year').text(year);
   		   $('#next').attr('disabled',false);
   		   if(year == 1990){
   		       $('#previous').attr('disabled',true);  
   		   }
   		   loadTable({"year": year});
   	   });
    	
    	$('#next').click(function () {
   		   var year = $('#year').text();
   		   year = eval(year) + 1;
   		   $('#year').text(year);
		   $('#previous').attr('disabled',false);
		   if(year == currentYear){
		       $('#next').attr('disabled',true);
		   }
		   loadTable({"year": year});
   	   });
    });
    
    function loadTable(params){
    	if(table){
    		table.destroy();
    	}
      	table = $('#billTable').DataTable({
    		"dom":
    		
    		
    		"<'row'<'col-sm-12 table-responsive'tr>>",
    		
			
			//json
		    "processing": true,
       		"serverSide": true,
     		"bDestroy": true,
     		"ordering": false,
			"ajax": {
				"url":"efArap/balanceBillPage",
				"data": params ? params : {}
			},
			"columns": [
				{ "data": "ym" },
				{ "data": "payable","render": function render( data, type, row, meta ){
					if(data == undefined){
						return '';
					}else{
	            		return'<a href="javascript://" onclick="showDetail(1,\'' + row.ym + '\',\'' + row.payed + '\');">' + data + '</a>';
					}
	            }},
				{ "data": "payed","render": function render( data, type, row, meta ){
					if(data == undefined){
						return '';
					}else{
						return'<a href="javascript://" onclick="showDetail(2,\'' + row.ym + '\',\'' + row.payed + '\');">' + data + '</a>';
					}
	            } },
				{ "data": "balance" }
			],
    		"columnDefs": [
    			{defaultContent: '', targets: '_all'}
			],
			
   	 		"language": {
	            "lengthMenu": "每页显示 _MENU_ 条记录",
	            "zeroRecords": "暂无数据 - 报歉啦?",
	            "info": "显示 第 _PAGE_ 页 共 _PAGES_ 页",
	            "infoEmpty": "暂无数据",
	            "infoFiltered": "(筛选自 _MAX_ 条记录)",
				"paginate":{
					"first":"首页",
					"previous":"上一年",
					"next":"下一年",
					"last":"尾页"
				}
   			}
   	    });
		
		$("div.toolbar").html('<button class="btn btn-info  btn-sm pull-right" type="button" data-toggle="collapse" data-target="#collapseExample" aria-expanded="false" aria-controls="collapseExample"><i class="fa">高级搜索</i></button>');
    }
    
    //function loadData(){
    //	table.ajax.url("efArap/detailListPage").load();
    //}
    
    function showDetail(type, ym, amount){
    	if(type == 1){
	    	$('#detailForm').attr("action", "efArap/toBillOfYm");
    	}else{
    		$('#amount').val(amount);
	    	$('#detailForm').attr("action", "efReceiptOwned/toReceiptOwned");
    	}
    	$('#ym').val(ym);
    	$('#detailForm').submit();
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
