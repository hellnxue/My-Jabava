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
    <title>jabava1.0我的订单界面</title>
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
                    <a href="order/toOrderMain" class="btn btn-success btn-sm pull-left">　返回　</a>
                    ${yearmonth} 订单明细
                    </h4>
                </div>
                <ul class="nav nav-tabs">
                    <li class="active"><a data-toggle="tab" href="#tab-1" data-action="all">全部订单</a></li>
                    <li class=""><a data-toggle="tab" href="#tab-2" data-action="add">本月增员</a></li>
                    <li class=""><a data-toggle="tab" href="#tab-3" data-action="del">本月减员</a></li>
                    <li class=""><a data-toggle="tab" href="#tab-4" data-action="change">本月变更</a></li>
                </ul>
                <div class="tab-content">
                    <div id="tab-1" class="tab-pane active">
                        <div class="panel-body" id="allcheck"> 
                        <input type="hidden"  id="yearmonth" value="${yearmonth}"/>
                            <table id="orderList" class="table table-bordered table-hover rost_table" width="100%">
                            <thead>
                            <tr>
                                <th>姓名</th>
                                <th>证件类型</th>
                                <th>证件号码</th>
                                <th>性别</th>
                                <th>员工类型</th>
                                <th>工作地</th>
                                <th>户籍性质</th>
                                <th>入职日期</th>
                                <th>雇佣状态</th>
                                <th>社保基数</th>
                                <th>公积金基数</th>
                                <th>社保状态</th>
                                <th>公积金状态</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>                   
                            </tbody>
                            </table>
                        </div>
                    </div>
                    <div id="tab-2" class="tab-pane">
                        <div class="panel-body" id="allcheckTwo"> 
                            <table id="orderListTwo" class="table table-bordered table-hover rost_table" width="100%">
                            <thead>
                            <tr>
                                <th>姓名</th>
                                <th>证件类型</th>
                                <th>证件号码</th>
                                <th>性别</th>
                                <th>员工类型</th>
                                <th>工作地</th>
                                <th>户籍性质</th>
                                <th>入职日期</th>
                                <th>雇佣状态</th>
                                <th>社保基数</th>
                                <th>公积金基数</th>
                                <th>社保状态</th>
                                <th>公积金状态</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>                   
                            </tbody>
                            </table>
                        </div>
                    </div>
                    <div id="tab-3" class="tab-pane">
                        <div class="panel-body" id="allcheckThird"> 
                            <table id="orderListThird" class="table table-bordered table-hover rost_table" width="100%">
                            <thead>
                            <tr>
                                <th>姓名</th>
                                <th>证件类型</th>
                                <th>证件号码</th>
                                <th>性别</th>
                                <th>员工类型</th>
                                <th>工作地</th>
                                <th>户籍性质</th>
                                <th>入职日期</th>
                                <th>雇佣状态</th>
                                <th>社保基数</th>
                                <th>公积金基数</th>
                                <th>社保状态</th>
                                <th>公积金状态</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>                   
                            </tbody>
                            </table>
                        </div>
                    </div>
                    <div id="tab-4" class="tab-pane">
                        <div class="panel-body" id="allcheckFour"> 
                            <table id="orderListFour" class="table table-bordered table-hover rost_table" width="100%">
                            <thead>
                            <tr>
                                <th>姓名</th>
                                <th>证件类型</th>
                                <th>证件号码</th>
                                <th>性别</th>
                                <th>员工类型</th>
                                <th>工作地</th>
                                <th>户籍性质</th>
                                <th>入职日期</th>
                                <th>雇佣状态</th>
                                <th>社保基数</th>
                                <th>公积金基数</th>
                                <th>社保状态</th>
                                <th>公积金状态</th>
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
    <!-- <script src="static/bootstrap/vendor/bootstrap-datepicker-master/dist/js/bootstrap-datepicker.js"></script> -->
    <script src="//cdn.bootcss.com/bootstrap-datepicker/1.5.1/js/bootstrap-datepicker.min.js"></script>
    <script src="http://eternicode.github.io/bootstrap-datepicker/bootstrap-datepicker/js/locales/bootstrap-datepicker.zh-CN.js"></script>
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
   $(function () {

	$('#searchWorkLocation').html='';
	$('#searchWorkLocation').append("<option value='' >全部</option>");
	//显示工作地
	 $.ajax({
		  url:"employee/searchBaseData.do",
	      dataType:'json',
	      success: function(data){	 
		  		var list = data.data;
				var html = '';
				for(var i = 0; i < list.length; i++){
					html += '<option value="'+list[i].baseDataId+'">'+list[i].baseDataName+'</option>';
				}
			   $('#searchWorkLocation').append(html);			  
			   
	      }
	   });
	   jQuery.prototype.serializeObject=function(){  
   	    var obj=new Object();  
   	    $.each(this.serializeArray(),function(index,param){  
   	        if(!(param.name in obj)){  
   	            obj[param.name]=param.value;  
   	        }  
   	    });  
   	    return obj;  
   	};

    var oOrderTable = [];
    var orderList = [];
    orderList['all'] = function(actionType){
        var getDate = $('[data-action='+actionType+']').attr('aria-date') || '';
        oOrderTable['all'] = $('#orderList').DataTable({
    		"dom":
    		
            "<'row'<'col-sm-3 col-md-5 col-lg-5'l><'col-sm-9 col-md-7 col-lg-7'f>>" +
    		"<'row'<'col-sm-12 table-responsive'tr>>" +
    		"<'row'<'col-sm-5'i><'col-sm-7'p>>",
			
			"processing": true,
            "destroy": true,
			"serverSide": true,				
			 "ajax":{
 	            "url": "order/findOrderListPage?type=all&date="+"${yearmonth}",
 	            "type":"POST"
 	            

			},
			"columns": [
            { "data": "bdEmpBaseInfo.employeeName" },
            { "data": "cardTypeShow" },
            { "data": "bdEmpBaseInfo.cardId" },
            { "data": "genderShow", "render": function render( data, type, row, meta ){
				if(data){
					return data;
				}else{
					return '未知';
				}
			}},
            { "data": "contractTypeShow" },
            { "data": "bdEmpBaseInfo.workCity" },
            { "data": "residentTypeShow" },
			{ "data": "bdEmpRecInfo.hireDate",  "render": function render( data, type, row, meta ){
                if(data){
                    return data.substring(0,10);
                }
            }},
			{ "data": "hireStatusShow" },
			{ "data": "hsEmpOrdRec.socialInsuranceIndividualBase" },
			{ "data": "hsEmpOrdRec.reserveIndividualBase" },
			{ "data": "sbStatusShow" },
			{ "data": "gjjStatusShow" },
			{ "render": function render( data, type, row, meta ){
				
					return	"<a href='order/toOrderDetail?paymentMonth=${yearmonth}"+"&orderId="+row.orderId+"&ordRecId="+row.hsEmpOrdRec.ordRecId+"' ><button class='btn btn-success btn-xs dropdown-toggle' type='button'  title='明细'>明细</button></a>";
				}
				}
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
    
  };
    // ....
orderList['add'] = function(actionType){
    var getDate = $('[data-action='+actionType+']').attr('aria-date') || '';
    oOrderTable['add'] = $('#orderListTwo').DataTable({
        "dom":
            
            "<'row'<'col-sm-3 col-md-5 col-lg-5'l><'col-sm-9 col-md-7 col-lg-7'f>>" +
            "<'row'<'col-sm-12 table-responsive'tr>>" +
            "<'row'<'col-sm-5'i><'col-sm-7'p>>",

            "processing": true,
            "destroy": true,
            "serverSide": true,             
             "ajax":{
                "url": "order/findOrderListPage?type=add&date="+"${yearmonth}",
                "type":"POST"

            },
            "columns": [
             { "data": "bdEmpBaseInfo.employeeName" },
            { "data": "cardTypeShow" },
            { "data": "bdEmpBaseInfo.cardId" },
            { "data": "genderShow", "render": function render( data, type, row, meta ){
                if(data){
                    return data;
                }else{
                    return '未知';
                }
            }},
            { "data": "contractTypeShow" },
            { "data": "bdEmpBaseInfo.workCity" },
            { "data": "residentTypeShow" },
            { "data": "bdEmpRecInfo.hireDate",  "render": function render( data, type, row, meta ){
                if(data){
                    return data.substring(0,10);
                }
            }},
            { "data": "hireStatusShow" },
            { "data": "hsEmpOrdRec.socialInsuranceIndividualBase" },
            { "data": "hsEmpOrdRec.reserveIndividualBase" },
            { "data": "sbStatusShow" },
            { "data": "gjjStatusShow" },
            { "render": function render( data, type, row, meta ){
                
                    return  "<a href='order/toOrderDetail?paymentMonth=${yearmonth}"+"&orderId="+row.orderId+"&ordRecId="+row.hsEmpOrdRec.ordRecId+"' ><button class='btn btn-success btn-xs dropdown-toggle' type='button'  title='明细'>明细</button></a>";
                }
                }
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
}


   //.....
orderList['del'] = function(actionType){
    var getDate = $('[data-action='+actionType+']').attr('aria-date') || '';
    oOrderTable['del'] = $('#orderListThird').DataTable({
        "dom":
            
            "<'row'<'col-sm-3 col-md-5 col-lg-5'l><'col-sm-9 col-md-7 col-lg-7'f>>" +
            "<'row'<'col-sm-12 table-responsive'tr>>" +
            "<'row'<'col-sm-5'i><'col-sm-7'p>>",

            "processing": true,
            "destroy": true,
            "serverSide": true,             
             "ajax":{
                "url": "order/findOrderListPage?type=del&date="+"${yearmonth}",
                "type":"POST"

            },
            "columns": [
			 { "data": "bdEmpBaseInfo.employeeName" },
            { "data": "cardTypeShow" },
            { "data": "bdEmpBaseInfo.cardId" },
            { "data": "genderShow", "render": function render( data, type, row, meta ){
                if(data){
                    return data;
                }else{
                    return '未知';
                }
            }},
            { "data": "contractTypeShow" },
            { "data": "bdEmpBaseInfo.workCity" },
            { "data": "residentTypeShow" },
            { "data": "bdEmpRecInfo.hireDate",  "render": function render( data, type, row, meta ){
                if(data){
                    return data.substring(0,10);
                }
            }},
            { "data": "hireStatusShow" },
            { "data": "hsEmpOrdRec.socialInsuranceIndividualBase" },
            { "data": "hsEmpOrdRec.reserveIndividualBase" },
            { "data": "sbStatusShow" },
            { "data": "gjjStatusShow" },
            { "render": function render( data, type, row, meta ){
                
                    return  "<a href='order/toOrderDetail?paymentMonth=${yearmonth}"+"&orderId="+row.orderId+"&ordRecId="+row.hsEmpOrdRec.ordRecId+"' ><button class='btn btn-success btn-xs dropdown-toggle' type='button'  title='明细'>明细</button></a>";
                }
                }
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
}

orderList['change'] = function(actionType){
    var getDate = $('[data-action='+actionType+']').attr('aria-date') || '';
    oOrderTable['change'] = $('#orderListFour').DataTable({
        "dom":
            
            "<'row'<'col-sm-3 col-md-5 col-lg-5'l><'col-sm-9 col-md-7 col-lg-7'f>>" +
            "<'row'<'col-sm-12 table-responsive'tr>>" +
            "<'row'<'col-sm-5'i><'col-sm-7'p>>",

            "processing": true,
            "destroy": true,
            "serverSide": true,             
             "ajax":{
                "url": "order/findOrderListPage?type=change&date="+"${yearmonth}",
                "type":"POST"

            },
            "columns": [
             { "data": "bdEmpBaseInfo.employeeName" },
            { "data": "cardTypeShow" },
            { "data": "bdEmpBaseInfo.cardId" },
            { "data": "genderShow", "render": function render( data, type, row, meta ){
                if(data){
                    return data;
                }else{
                    return '未知';
                }
            }},
            { "data": "contractTypeShow" },
            { "data": "bdEmpBaseInfo.workCity" },
            { "data": "residentTypeShow" },
            { "data": "bdEmpRecInfo.hireDate",  "render": function render( data, type, row, meta ){
                if(data){
                    return data.substring(0,10);
                }
            }},
            { "data": "hireStatusShow" },
            { "data": "hsEmpOrdRec.socialInsuranceIndividualBase" },
            { "data": "hsEmpOrdRec.reserveIndividualBase" },
            { "data": "sbStatusShow" },
            { "data": "gjjStatusShow" },
            { "render": function render( data, type, row, meta ){
                
                    return  "<a href='order/toOrderDetail?paymentMonth=${yearmonth}"+"&orderId="+row.orderId+"&ordRecId="+row.hsEmpOrdRec.ordRecId+"' ><button class='btn btn-success btn-xs dropdown-toggle' type='button'  title='明细'>明细</button></a>";
                }
                }
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
   
}

    // insert datepicker html object
    var toolbarsDatePicker = function(actionType){
        var ariaDate = $('[data-action='+actionType+']').attr('aria-date') || '';
        var strDatePicker = '<div class="input-group date"><input type="text" class="form-control" value="'+ariaDate+'"/><span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span></div>';
        var htmlDatePicker = $(".toolbars").empty().append(strDatePicker);
        htmlDatePicker.each(function(index, el){
            $(el).find('.input-group.date').attr('aria-action', actionType)
        });
        // datepicker onchange
        $('.input-group.date').datepicker({
            format: "yyyy-mm",
            minViewMode: 1,
            language: "zh-CN",
            autoclose: true
        })
        .on('changeDate', function(e){
            var getDate = $(this).find('.form-control').val();
            var getActType = $(this).attr('aria-action');
            $('[data-action]').attr('aria-date', getDate);
            oOrderTable[getActType].ajax.url( 'order/findOrderListPage?type='+getActType+'&date='+getDate ).load();
        });
    }

    
    // tab onclick;
    $('[data-action]').on('shown.bs.tab', function(e){
        var getActType = $(this).data('action');
        orderList[getActType](getActType);
        toolbarsDatePicker(getActType);

    })
    
    $('[data-action=all]').trigger('shown.bs.tab')

})
 
</script>
</body>
</html>