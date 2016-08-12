<%@ page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html>
<head>
	<base href="${pageContext.request.contextPath}/">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <!-- Page title -->
    <title>本月增员</title>
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
            ${requestScope.ssPaymentBill.socialSecurityAccountName}${requestScope.ssPaymentBill.month}月清单明细-本月增员
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
                  <a href="javascript: void(0);" type="button" class="btn btn-success btn-sm pull-left btn-return m-r-md">返回</a>
                </h4>
              </div>
      <ul role="tabList" class="nav nav-tabs m-t-md" id="">
         <li role="payDetails">
          <a href="javascript: void(0);" onclick="viewPerson('');">全部清单</a>
        </li>
        <li role="payDetails" class="active">
          <a href="#tab-1">本月增员</a>
        </li>
        <li role="payDetails">
          <a href="javascript: void(0);" onclick="viewPerson('JY');">本月减员</a>
        </li>
        <li role="payDetails">
          <a href="javascript: void(0);" onclick="viewPerson('BG');">本月变更</a>
        </li>
      </ul>
      <div class="tab-content">
        <div id="tab-1" class="tab-pane active">
          <div class="panel-body"> 
            <div class="table-responsive">
              <table id="detailTable" class="table table-bordered table-hover detailTable" width="100%">
              <thead>
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
  </div> 

  <!-- 放页脚  开始-->
  <jsp:include flush="true" page="../common/foot.div.jsp"></jsp:include>
  <!-- 放页脚  结束-->
</div>
<div style="display:none;">
	<form id="detailForm" action="" method="post">
		<input type="hidden" name="ssPaymentBillId" value="${requestScope.ssPaymentBill.id}">
	</form>
	<form id="personForm" action="" method="post">
		<input type="hidden" name="ssPaymentBillId" value="${requestScope.ssPaymentBill.id}">
		<input type="hidden" id="t" name="t" value="ZY">
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

<script type="text/javascript">
	var ssPaymentBillId = ${requestScope.ssPaymentBill.id};
	var detailTable;
	$(function () {
		$('.m-r-md').click(function(){
			 $('#detailForm').attr('action','ssPaymentBill/toListPaymentBillDetail');
		     $('#detailForm').submit();
		});
	
		showDetail();
    });
   
    function showDetail(){
    	loadHeader();
    }
	
    function loadHeader(){
    	$.ajax({
			url:"ssPaymentBill/loadPaymentBillPersonZyHeader",
			type : "POST",
			dataType:'json',
			data: {"ssPaymentBillId": ssPaymentBillId},
			error: function(XMLHttpRequest, textStatus, errorThrown){
				alert(textStatus);
			},
			success: function(data){
				if(data){
			    	if(detailTable){
			    		detailTable.destroy();
			    	}
			    	//in case the columns change
		    		$('#detailTable').empty();
			    	
					initDetailTable(data);
				}
			}
		});
    }
    
    function initDetailTable(data){
    	//读取数据，初始化表头及DataTables的columns
    	var columns = [];
    	
    	//动态添加表头
    	var ele;
		var firstRow = $("<tr></tr>");
		var secondRowEles = [];
    	$.each(data,function(){
    		if(this.fieldName){
    			ele = '<th rowspan="2">' + this.showName + '</th>';
    			firstRow.append($(ele));
    			
    			columns.push({"data":this.fieldName});
    		}else{
                ele = '<th colspan="' + this.cols.length + '">' + this.prod_name + '</th>';
                firstRow.append($(ele));
                    
    			for(var i = 0; i < this.cols.length; i ++){
    				secondRowEles.push(this.cols[i]);
    				//合并列
    				columns.push({"data":this.cols[i].fieldName});
    			};
    		}
    	});
    	
    	if(secondRowEles.length == 0){
    		return false;
    	}
    	
    	var secondRow = $("<tr></tr>");
    	$.each(secondRowEles,function(){
            secondRow.append($('<th class="xyz">' + this.showName + '</th>'));
    	});
    	
    	// append header to the table
    	var header = $("<thead></thead>");
    	header.append(firstRow);
    	header.append(secondRow);
    	$('#detailTable').append(header);
    	
    	initTable(columns);
    }
    
    function initTable(columns){
    	detailTable = $('#detailTable').DataTable({
    		"dom":
	    		//"<'row'<'col-sm-12'l>>" +
	    		"<'row'<'col-sm-12'tr>>",
	    		//"<'row'<'col-sm-5'i><'col-sm-7'p>>",
				
		    "processing": true,
    		"serverSide": true,
    		"bDestroy": true,
    		"sServerMethod": "POST",
    		"ordering": false,
    		"columns": columns,
    		"columnDefs": [
    			{defaultContent: '', targets: '_all'}
			],
    		"ajax": {
    			"url": "ssPaymentBill/listPaymentBillPersonZy",
    			"data": {
    				"ssPaymentBillId": ssPaymentBillId
    			}
    		},
    	 	"language": {
	            //"lengthMenu": "每页显示 _MENU_ 条记录",
	            "zeroRecords": "暂无数据",
	            //"info": "显示 第 _PAGE_ 页 共 _PAGES_ 页",
	            "infoEmpty": "暂无数据"
	            //"infoFiltered": "(筛选自 _MAX_ 条记录)"
    		}
    	});
		
    	//$("#detailTable").css('width', 'auto');
    	 $("table.table-bordered th:last-child, table.table-bordered td:last-child").css("border-right-width","1px");
    	 $(".xyz").css("border-top-width","1px");
    }

    function viewPerson(type){
    	$('#t').val(type);
        $('#personForm').attr('action','ssPaymentBill/toListPaymentBillPerson');
        $('#personForm').submit();
    }
    
</script>

</body>
</html>
