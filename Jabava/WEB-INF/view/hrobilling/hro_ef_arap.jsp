<%@page import="com.jabava.core.config.JabavaPropertyCofigurer"%>
<%@ page contentType="text/html; charset=utf-8" %>
<%@page import="com.jabava.utils.RequestUtil"%>

<%
	String SERVER_URL = JabavaPropertyCofigurer.getProperty("SERVER_URL");
	if(!SERVER_URL.endsWith("/")){
		SERVER_URL += "/";
	}
%>
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
    <link rel="stylesheet" href="static/js/plugins/form.validation/css/formValidation.css">
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
            帐单
          </h2>
        </div>
      </div>
    </div>
  </div>
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
       	<th><input type="checkbox" id="selAll" data-check="checkAll"></th>
        <th>账单年月</th>
        <th>账单号</th>
        <th>账套名称</th>
        <th>状态</th>
        <th>出账时间</th>
        <th>社保总额</th>
        <th>服务总额</th>
        <th>其他</th>
        <th>应付总额</th>
        <th>约定付款日期</th>
        <th>付款状态</th>
        <th>付款时间</th>
        <th>核销状态</th>
        <th>核销时间</th>
        <th>备注</th>
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
            <div data-toolbar="billDetail" class="text-right m-t">
            	<a href="javascript://" class="btn btn-sm btn-info">确认账单</a>
            	<a href="javascript://" class="btn btn-sm btn-info">驳回账单</a>
            	<a href="javascript://" class="btn btn-sm btn-info">支付</a>
            </div>
            <a href="javascript://" onclick="javascript:showOrHide1();" data-toggle="showmore" data-showorhide="0">展开 ></a>
           </div>
       </div>
      </div>
    </div>
  </div>
</div>

<div class="modal fade" tabindex="-1" role="dialog" data-modal="reject">
  <div class="modal-dialog">
    <div class="modal-content">
    	<form action="" class="form-horizontal" data-form="reject">
	      <div class="modal-body">
	        <div class="form-group">
	        	<label class="control-label col-md-2 col-lg-2">驳回原因：</label>
	        	<div class="col-md-10 col-lg-10 form-required">
	        		<textarea class="form-control" name="rejectRemark" id="rejectRemark" rows="3"></textarea>
	        	</div>
	        </div>
	      </div>
	      <div class="modal-footer">
	        <button type="submit" class="btn btn-success">确定</button>
	        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
	      </div>
      </form>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


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
<script src="static/js/plugins/form.validation/js/formValidation.js"></script>
<script src="static/js/plugins/form.validation/js/framework/bootstrap.js"></script>
<!-- App scripts -->
<script src="static/bootstrap/scripts/homer.js"></script>
<script src="static/js/common/ajaxSetup.js"></script>
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
    		"<'row'<'col-sm-4'><'col-sm-4'><'col-sm-4'<'toolbar text-right'>>>",
			//json
	        "processing": true,
	        "serverSide": true,
	        "destroy": true,
	        "ordering": false,
	        "ajax": {
		        "url":"efArap/billListPage",
		        "data": params ? params : {}
			},
			"columns": [
				{ "data": "BILL_ID", "render": function(data, type, row, meta){
                    var html = '<input type="checkbox" value="' + data + '" data-id="' + data + '" name="itemId[]" data-check="item">';
					return html;
				}},
				{ "data": "BILL_YM" },
		    	{ "data": "BILL_CODE", "render": function render( data, type, row, meta ){
		              var htmlLink = '';
		              <% if(RequestUtil.hasPower("bill_bd")){ %>
		              htmlLink += '<td><a href="javascript://" onclick="showDetail(\'' + row.BILL_ID + '\',\'' + row.BILL_YM + '\',\'' + data + '\',\'' + row.HR_STATUS + '\',\'' + row.STATUS_PAYOFF + '\',\'' + (row.PAY_LINK ? row.PAY_LINK : '') + '\')">' + data + '</a></td>'
		              <% }else{ %>
		              htmlLink += '<td>' + data + '</td>';
		              <% } %>
            		  return htmlLink;
					} 
				},
				{ "data": "BILL_TEMPLATE_NAME" },	//账套名称
				{ "data": "HR_STATUS", "render": function( data, type, row, meta ){
					if(data == 1){
						return "未确认";
					}else if(data == 2){
						if(row.STATUS_PAYOFF == 1){
							return "已支付";
						}else{
							return "已确认";
						}
					}else if(data == 3){
						return "已驳回";
					}else if(data == 4){
						return "已作废";
					}else{
						return "";
					}
				}, 'createdCell': function(td, cellData, rowData, row , col){
		               
	                if( rowData.HR_STATUS == 1 ){
	                    $(td).parent('tr').attr({
	                    	'class': 'warning'
	                    });
	                }
	            } },		//账单状态
				{ "data": "HR_DATE", render: function( data, type, row, meta ){
					if(data && data.length >= 10){
						return data.substring(0,10);
					}
					return data;
				} },			//操作时间
				{ "data": "sbAmount" },			//社保总额
				{ "data": "serviceAmount" },	//服务总额
				{ "data": "otherAmount" },		//其他
				{ "data": "AMOUNT" },			//应付总额
				{ "data": "PAY_DAY" ,"render": function render( data, type, row, meta ){
						if(data){
							return data.substring(0,10);
						}
						
						return '';
					}
				},
				{ "data": "STATUS_PAYOFF", "visible": false, "render": function( data, type, row, meta ){
					if(data == 1){
						return "已支付";
					}else if(row.PAY_LINK && row.HR_STATUS == 2 && (row.STATUS_VERIFY == 1 || row.STATUS_VERIFY == 2)){
						return '<a href="javascript://" class="btn btn-xs btn-info" onclick="payoff(\'' + row.BILL_ID + '\',\'' + row.PAY_LINK + '\')">支付</a>';
					}else{
						//return "未支付 ";
						return '';
					}
				}},	//付款状态
				{ "data": "PAYOFF_DATE" },		//付款时间
				{ "data": "STATUS_VERIFY", "visible":false, "render": function( data, type, row, meta ){
					if(data == 1){
						return "未核销";
					}else if(data == 2){
						//return "部分核销";
						return "未核销";
					}else if(data == 3){
						return "已核销";
					}else{
						return "";
					}
				} },	//核销状态
				{ "data": "CONFIRM_DATE", "visible":false, render: function( data, type, row, meta ){
					if(row.STATUS_VERIFY == 3){
						return data;
					}else{
						return '';
					}
				} },	//核销时间
				{ "data": "REMARK" },			//备注
				{ "render": function render( data, type, row, meta ){
		            var strHtml = '';
                    var appendHtml = '';
                    <% if(RequestUtil.hasPower("bill_bd")){ %>
                    if(row.HR_STATUS == 1){ //状态为待确认
                    	appendHtml += '    <li><a href="javascript://" onclick="confirm(\'' + row.BILL_ID + '\', 2)">确认</a></li>';
                    	appendHtml += '    <li><a href="javascript://" onclick="toReject(\'' + row.BILL_ID + '\')">驳回</a></li>';
		            }else if(row.HR_STATUS == 2){
		            	//已确认、未支付...
		            	if(row.STATUS_PAYOFF != 1 && row.PAY_LINK && (row.STATUS_VERIFY == 1 || row.STATUS_VERIFY == 2)){
		            		appendHtml += '<li><a href="javascript://" onclick="payoff(\'' + row.BILL_ID + '\',\'' + row.PAY_LINK + '\')">支付</a>';
						}
		            }
					
                    if(appendHtml != ''){
			            strHtml += '<div class="btn-group">';
	                    strHtml += '  <button type="button" class="btn btn-info btn-xs dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">操作 <span class="caret"></span>';
	                    strHtml += '  </button>';
	                    strHtml += '  <ul class="dropdown-menu dropdown-menu-right">' + appendHtml + '</ul>';
	                    strHtml += '</div>';
                    }
		            <% } %>
              	    return strHtml;
				} }
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
       /* var strDatePicker = '<div class="input-group date"><input type="text" class="form-control"/><span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span></div>';
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
        });*/
        var strHtml = '';
            strHtml += '<button class="btn btn-warning btn-sm" type="button" data-toggle="export"><i class=""></i> <span class="bold">导&nbsp;&nbsp;出</span></button>';
        $("div.toolbar").html(strHtml);
        $('[data-check="checkAll"]').on('click', function(event) {
            var getState = $(this).prop('checked');
            var $getCheckItems = $('[data-check="item"]');
            $getCheckItems.prop('checked', getState);
        });
        $('[data-toggle="export"]').on('click', function(event) {
            event.preventDefault();
            var $getCheckItems = $('[data-check="item"]:checked');
            var bills = $.map($getCheckItems, function(item, index) {
                return $(item).val();
            });
            if(bills.length>0){
                bills = bills.join(',');
                //console.log(bills);
                $.ajax({
                    url: 'efArap/export',
                    type: 'POST',
                    dataType: 'json',
                    data: {billIds: bills}
                })
                .done(function(data) {
                    //console.log("success");
                    if(data.success){
                    	window.open('<%=SERVER_URL%>loadfile.action?fileNo=' + data.fileNo);
                    }else{
                    	alert(data.msg);
                    }
                })
                .fail(function() {
                    console.log("error");
                })
                .always(function() {
                    //console.log("complete");
                });
            }else{
            	alert("请至少选择一个账单导出");
            }
            
        });
    };
   
    function showDetail(billId,billYm,billCode,hrStatus,statusPayoff, payLink){
      	//console.log(hrStatus + "-" + statusPayoff + "-" + payLink);
       	$('[data-bill=code]').text( '账单号' + billCode + '明细' );
		$('#billYmShow').text(billYm);
    	
		//清除动态生成的表头
		//$('#detailHeader').empty();
		
    	loadHeader(billId);

     	$('[data-toggle="showmore"]').attr('data-showorhide', 0).text('展开 >');	//默认隐藏

    	$(".zhang_dan").show();
		$(".check_hide").hide();
		
		var getBillDetailToolbar = $('[data-toolbar="billDetail"]');
		var strHtml = '';
    	if(hrStatus == 1){
    		strHtml += '<a href="javascript://" class="btn btn-sm btn-info" onclick="confirm(' + billId + ');">确认账单</a>&nbsp;';
        	strHtml += '<a href="javascript://" class="btn btn-sm btn-info" onclick="toReject(' + billId + ');">驳回账单</a>&nbsp;';
    	}
    	if(statusPayoff != 1 && payLink){
        	strHtml += '<a href="javascript://" class="btn btn-sm btn-info" onclick="payoff(' + billId + ',\'' + payLink + '\');">支付</a>&nbsp;';
    	}

		getBillDetailToolbar.html(strHtml);
    }
	
    function loadHeader(billId){
    	$.ajax({
			url:"efArap/detailHeaderList",
			type : "POST",
			dataType:'json',
			data: {"billId": billId},
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
        
    	//console.log(columns);
    	
    	initTable(columns,billId);
    }
    
    function initTable(columns,billId){
    	detailTable = $('#detailTable').DataTable({
    		"dom":
	    		"<'row'<'col-sm-6'l><'col-sm-6'f>>" +
	    		"<'row'<'col-sm-12 table-responsive'tr>>" + 
	    		"<'row'<'col-sm-5'i><'col-sm-7'p>>",
				
		    "processing": true,
    		"serverSide": false,
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
    	 		"search": "过滤:",
	            "lengthMenu": "每页显示 _MENU_ 条记录",
	            "zeroRecords": "暂无数据",
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
		
    	//$("#detailTable").css('width', 'auto');
    	 $("table.table-bordered th:last-child, table.table-bordered td:last-child").css("border-right-width","1px");
    	 $(".xyz").css("border-top-width","1px");
    }
    
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
    
    function confirm(billId){
    	operate("efArap/operate", {"billId": billId, "type": 2});
    }

    function toReject(billId){
    	var getRejectModal = $('[ data-modal="reject"]');
    	getRejectModal.modal('show');
    	var validOpts = {
   	        err: {
   	            container: 'tooltip'
   	        },
   	        icon: {
   	            valid: 'glyphicon glyphicon-ok',
   	            invalid: 'glyphicon glyphicon-remove',
   	            validating: 'glyphicon glyphicon-refresh'
   	        },
   	        locale: 'zh_CN',
   	        fields: {
   	        	rejectRemark: {
   	                validators: {
   	                    notEmpty: {
   	                        message: '请填写必填项目'
   	                    }
   	                }
   	            }
   	        }
   	     };
    	$('[data-form="reject"]').formValidation(validOpts)
    	.on('success.form.fv', function(e){
            e.preventDefault();
            reject(billId, $('#rejectRemark').val());
        })
    }

    function reject(billId, rejectRemark){
    	operate("efArap/operate", {"billId": billId, "type": 3, "rejectRemark": rejectRemark});
    }
    
    function operate(url, params){
    	$.ajax({
			url: url,
			type : "POST",
			dataType:'json',
			data: params,
			success: function(data){
				if(data.success){
					swal({
	                    title: "操作成功!",
	                    text: "",
	                    type: "success",
	    				confirmButtonText: "确定"
	                }, function(){
	                	window.location.reload();
	                });
				}else{
					swal({
	                    title: "操作失败!",
	                    text: data.msg,
	                    type: "error",
	    				confirmButtonText: "确定"
	                });
				}
			}
    	});
    }
    
    function payoff(billId, payLink){
    	$.ajax({
			url:"efArap/payoff",
			type : "POST",
			dataType:'json',
			data: {"billId": billId},
			success: function(data){
				if(data.success){
					//弹出层
					swal({
						title: '',
						showCancelButton: true,
						cancelButtonText: '以后支付',
						confirmButtonText: '支付完成'
					},function(isConfirm){
						if(isConfirm){
							
						}else{
							
						}
						window.location.reload();
					});
					
					window.open(payLink);
					
				}else{
					alert(data.msg);
				}
			}
    	});
    }
</script>

</body>
</html>
