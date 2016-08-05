<%@ page contentType="text/html; charset=utf-8" %>
<%@page import="com.jabava.utils.RequestUtil"%>
<!DOCTYPE html>
<html>
<head>
	<base href="${pageContext.request.contextPath}/">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <!-- Page title -->
    <title>账单</title>
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
     <div class="text-right">
       
  <table id="billTable" class="table table-bordered table-hover" width="100%">
    <thead>
      <tr>
       	<th>账单ID</th>
        <th>账单年月</th>
        <!--th>账单约定</th-->
        <th>账单号</th>
        <!-- <th>约定账单生成日</th>
        <th>约定账单锁定日</th>
        <th>试算时间</th>
        <th>最后生成时间</th> -->
        <th>应付总额</th>
        <th>约定付款日期</th>
       <!--  <th>账单账单状态</th> -->
       <th>操作</th>
      </tr>
    </thead>
    <tbody>

    </tbody>
  </table>
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
                <span data-bill="code">账单号 @billCode 明细</span>
            </h4>
            <div class="pull-right">
              <button class="btn btn-success " type="button" style="display:none;">支付</button>
              <button class="btn btn-default " type="button"  style="display:none;">已支付</button>
            </div>
          </div>
          <div class="panel-body">
            <table id="detailTable" class="table table-bordered table-hover" width="100%">
              <thead id="detailHeader">
              </thead>
              <tbody>
              </tbody>
            </table>
            <a href="javascript://" onclick="javascript:showOrHide1();" data-toggle="showmore" data-showorhide="0">展开 ></a>
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
<script>
	var currentYear = ${requestScope.year};
	var table, detailTable;
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
    	
        toolbarsDatePicker();
    });
    
    function loadTable(params){
    	if(table){
    		table.destroy();
    	}
      	table = $('#billTable').DataTable({
    		"dom":
    		
    		
            "<'row'<'col-sm-12 table-responsive'tr>>" ,
            
			
			//json
	        "processing": true,
	        "serverSide": true,
	        "destroy": true,
	        "ajax": {
		        "url":"efArap/billListPage",
		        "data": params ? params : {}
			},
			"columns": [
				{ "data": "billId", "visible":false },
				{ "data": "billYm" },
		    	{ "data": "billCode","render": function render( data, type, row, meta ){
              var htmlLink = '';
              <% if(RequestUtil.hasPower("bill_bd")){ %>
              htmlLink += '<td><a href="javascript://" onclick="showDetail(\'' + row.billId + '\',\'' + row.billYm + '\',\''+data+'\')">' + data + '</a></td>'
              <% }else{ %>
              htmlLink += '<td>' + data + '</td>';
              <% } %>
            			return htmlLink;
					} 
				},

				{ "data": "amount" },
				// { "data": "amountTotal" },
				{ "data": "payDay" ,"render": function render( data, type, row, meta ){
						if(data){
							return data.substring(0,10);
						}
						
						return '';
					}
				},
				//{ "data": "statusBill", "render": function render( data, type, row, meta ){
				//		if(data == 'V'){
            	//			return '<td>已确认</td>';
				//		}else{
				//			return '<td>&nbsp;</td>';
				//		}
				//		//已试算 "n",已生成 "e",已发送 "s",已驳回 "r",已确认 "v",已作废 "c"
				//	} 
				//},
				{ "visible": false, "render": function render( data, type, row, meta ){
	            	//	return '<td><button class="btn btn-info btn-xs" type="button">支付</button></td>';
						return '<td>&nbsp;</td>'
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
		
    	
    };


    // insert datepicker html object
    var toolbarsDatePicker = function(){
        var strDatePicker = '<div class="input-group date"><input type="text" class="form-control"/><span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span></div>';
        var htmlDatePicker = $(".toolbar").empty().append(strDatePicker);
        // datepicker onchange
        $('.input-group.date').datepicker({
            format: 'yyyymm', 
            weekStart: 1, 
            autoclose: true, 
            startView: 1, maxViewMode: 1,minViewMode:1,
            forceParse: false, 
            language: 'zh-CN'
        })
        .on('changeDate', function(e){
            var getDate = $(this).find('.form-control').val();
            table.ajax.url( 'efArap/billListPage?billYm='+getDate ).load();
        });
    };
   
    function showDetail(billId,billYm,billCode){
      
       	$('[data-bill=code]').text( '账单号' + billCode + '明细' );
		$('#billYmShow').text(billYm);
    	
		//清除动态生成的表头
		//$('#detailHeader').empty();
		
    	loadHeader(billId);

     	$('[data-toggle="showmore"]').attr('data-showorhide', 0).text('展开 >');	//默认隐藏

    	$(".zhang_dan").show();
		$(".check_hide").hide();
		
    	//$('#billIdForDetail').val(billId);
    	//$('#billYmForDetail').val(billYm);
    }
	
    function loadHeader(billId){
    	$.ajax({
			url:"efArap/detailHeaderList",
			type : "POST",
			dataType:'json',
			data: {"billId": billId},
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
			    	
					initDetailTable(data,billId);
				}
			}
		});
    }
    
    function initDetailTable(data,billId){
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
    			//ele = '<th style=" padding:0;">';
                //ele += '<div class="ylbx">' + this.prodName + '</div>';
                //ele += '<div class="baoxian">';
                //ele += '<ul>';
                ele = '<th colspan="' + this.cols.length + '">' + this.prodName + '</th>';
                firstRow.append($(ele));
                    
    			for(var i = 0; i < this.cols.length; i ++){
    				//if(i < this.cols.length - 1){
    				//	ele += '<li>' + this.cols[i].showName + '</li>';
	    			//}else{
	    			//	ele += '<li class="lasts">' + this.cols[i].showName + '</li>';
	    			//}
    				secondRowEles.push(this.cols[i]);
    				//合并列初始隐藏
    				columns.push({"data":this.cols[i].fieldName, "visible":false});
    			};
    			
    			//ele += '</ul>';
    			//ele += '</div>';
    			//ele += '</th>';
    			
    			//$('#detailHeader').append($(ele));
    		}
    	});
    	
    	var secondRow = $("<tr></tr>");
    	$.each(secondRowEles,function(){
            secondRow.append($('<th class="xyz">' + this.showName + '</th>'));
    	});
    	
    	// append header to the table
    	var header = $("<thead></thead>");
    	//$('#detailHeader').append(firstRow);
    	//$('#detailHeader').append(secondRow);
    	header.append(firstRow);
    	header.append(secondRow);
    	$('#detailTable').append(header);
    	
    	//for(var i = 9; i < length; i ++){
    	//	columns[i].visible = false;
    	//}
    	//debugger
       console.log(columns) //
    	initTable(columns,billId);

    }
    
    function initTable(columns,billId){
    	detailTable = $('#detailTable').DataTable({
    		"dom":
	    		//"<'row'<'col-sm-12'l>>" +
	    		"<'row'<'col-sm-12 table-responsive'tr>>",
	    		//"<'row'<'col-sm-5'i><'col-sm-7'p>>",
				
		    "processing": true,
    		"serverSide": true,
    		"bDestroy": true,
    		"sServerMethod": "POST",
    		//"ordering": true,
    		//"bSort": true,
    		"columns": columns,
    		"columnDefs": [
    			{defaultContent: '', targets: '_all'}
			],
    		"ajax": {
    			"url": "efArap/detailListPage",
    			"data": {
    				"billId": billId
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
    	
    	//loadData();
    }
    
    //function loadData(){
    //	table.ajax.url("efArap/detailListPage").load();
    //}
    
    function backToBill(){

    	$(".check_hide").show();
		$(".zhang_dan").hide();
    }
    
    function showOrHide1(){
    	var oShowMore = $('[data-toggle="showmore"]'),
      visible = eval(oShowMore.attr('data-showorhide'))

    	if(visible){
   			oShowMore.text('展开 >')
    	}else{
    		oShowMore.text('隐藏 <')
    	}
		oShowMore.attr('data-showorhide', !visible);
    	
    	if(detailTable){
    		var length = detailTable.columns()[0].length;
    		for(var i = 9; i < length; i ++){
    			var column = detailTable.column(i);
    			column.visible(!column.visible());
    		}
    	}
    	
    	$("table.table-bordered th:last-child, table.table-bordered td:last-child").css("border-right-width","1px");
   		$(".xyz").css("border-top-width","1px");
    }
</script>

</body>
</html>
