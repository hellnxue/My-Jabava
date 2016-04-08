<%@ page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html>
<head>
	<base href="${pageContext.request.contextPath}/">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <!-- Page title -->
    <title>员工个人信息</title>
    <jsp:include flush="true" page="../common/styles.jsp"></jsp:include>
    <link rel="stylesheet" href="static/css/user_bata.css">
    <link rel="stylesheet" href="static/css/style_1.css" media="screen">
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
 
<!-- 放主要内容  开始-->

<!-- Main Wrapper -->
<div id="wrapper" class="min-h">
<div class="normalheader transition animated fadeIn small-header">
    <div class="hpanel">
        <div class="panel-body">
            <div id="hbreadcrumb" class="pull-right m-t-lg">
                <ol class="hbreadcrumb breadcrumb">
                    <li><a href="to_index?jump=1">首页</a></li>
                    <li>
                        <span>员工个人信息</span>
                    </li>
                    <li class="active">
                        <span></span>
                    </li>
                </ol>
            </div>
            <h2 class="font-light m-b-xs">
                员工个人信息
            </h2>
            <small>待定</small>
        </div>
    </div>
</div>

<!-- 放主要内容 -->
<!--订单-->
<!-- 开始 -->
<div class="content animate-panel">
    <div class="row">
        <div class="col-lg-12">
            <div class="hpanel hblue">
                <!--引入员工信息导航 开始--> 
                <jsp:include flush="true" page="employee_nav.jsp"></jsp:include>
                <!--引入员工信息导航 结束-->
                <div class="panel-heading hbuilt">
                    <button class="btn btn-info btn-xs dingdan_one" type="button">
                        <i class=""></i> <span class="bold">订单信息</span>
                    </button>
                    &nbsp;
                    <button class="btn information_button btn-xs dingdan_two" type="button">
                        <i class=""></i> <span class="bold">订单费用信息</span>
                    </button>
                </div>
                <!--订单信息开始-->
                <div class="panel-body order_infor">
                    <!-- 订单信息头部开始 -->
                    <div class="well well-lg well_bg" >
                        <div class="row">
                            <form role="form" class="search-form"> 
                                <input type="hidden" id="orderId" name="orderId" value="${orderId}" />
                                <div class="clearfix">

                                    <div class="form-group search-unit">
                                        <label class="col-lg-4 rost_sousuo">姓名：</label>
                                        <div class="col-lg-8">
                                            <label type="text" class="form-control order_boborder" id="showEmployeeName" name="employeeName"></label>
                                        </div>
                                    </div>
                                    <div class="form-group search-unit">
                                        <label class="col-lg-4 rost_sousuo">编号：</label>
                                        <div class="col-lg-8">
                                            <label type="text" class="form-control order_boborder" id="showJobNumber" name="jobNumber"></label>
                                        </div>
                                    </div>  
                                    <div class="form-group search-unit">
                                        <label class="col-lg-4 rost_sousuo">身份证：</label>
                                        <div class="col-lg-8">
                                            <label type="text" class="form-control order_boborder" id="showCertType" name="certType"></label>
                                        </div>
                                    </div> 

                                    <div class="form-group search-unit">
                                        <label class="col-lg-4 rost_sousuo">工作地：</label>
                                        <div class="col-lg-8">
                                            <label type="text" class="form-control order_boborder" id="showWorkLocation" name="workLocation"></label>
                                        </div>
                                    </div>
                                    <div class="form-group search-unit">
                                        <label class="col-lg-4 rost_sousuo">手机：</label>
                                        <div class="col-lg-8">
                                            <label type="text" class="form-control order_boborder" id="showMobile" name="mobile"></label>
                                        </div>
                                    </div>
                                    <div class="form-group search-unit">
                                        <label class="col-lg-4 rost_sousuo">电话：</label>
                                        <div class="col-lg-8">
                                            <label type="text" class="form-control order_boborder" id="showPhone" name="phone"></label>
                                        </div>
                                    </div>
                                    <div class="form-group search-unit">
                                        <label class="col-lg-4 rost_sousuo">雇佣状态：</label>
                                        <div class="col-lg-8">
                                            <label class="form-control order_boborder" id="showStatus" name="status"></label>
                                        </div>
                                    </div> 
                                    <div class="form-group search-unit">
                                        <label class="col-lg-4 rost_sousuo">入职日期：</label>
                                        <div class="col-lg-8">
                                            <label type="text" class="form-control order_boborder" id="showEntryDate" name="entryDate"></label>
                                        </div>
                                    </div>
                                    <div class="form-group search-unit">
                                        <label class="col-lg-4 rost_sousuo">入职备注：</label>
                                        <div class="col-lg-8">
                                            <label type="text" class="form-control order_boborder" id="showMemo" name="memo"></label>
                                        </div>
                                    </div>
                                    <div class="form-group search-unit">
                                        <label class="col-lg-4 rost_sousuo">离职日期：</label>
                                        <div class="col-lg-8">
                                            <label type="text" class="form-control order_boborder" id="showLeftDate" name="leftDate"></label>
                                        </div>
                                    </div>
                                    <div class="form-group search-unit">
                                        <label for="showLeftCause" class="col-lg-4 rost_sousuo">离职原因：</label>
                                        <div class="col-lg-8">
                                            <label type="text" class="form-control order_boborder" id="showLeftCause" name="leftCause"></label>
                                        </div>
                                    </div>
                                    <div class="form-group search-unit">
                                        <label class="col-lg-4 rost_sousuo">离职备注：</label>
                                        <div class="col-lg-8">
                                            <label type="text" class="form-control order_boborder" id="showLeftMemo" name=""></label>
                                        </div>
                                    </div>
                                    <div class="form-group search-unit">
                                        <label class="col-lg-4 rost_sousuo">户籍性质：</label>
                                        <div class="col-lg-8">
                                            <label type="text" class="form-control order_boborder" id="showRegisterType" name="registerType"></label>
                                        </div>
                                    </div>
                                    <div class="form-group search-unit">
                                        <label class="col-lg-4 rost_sousuo">家财险地址：</label>
                                        <div class="col-lg-8">
                                            <label type="text" class="form-control order_boborder" id="showFamilyAddress" name="familyAddress"></label>
                                        </div>
                                    </div>
                                    <div class="form-group search-unit">
                                        <label class="col-lg-4 rost_sousuo">家财险起始日期：</label>
                                        <div class="col-lg-8">
                                            <label type="text" class="form-control order_boborder" id="showFamilyDate" name=""></label>
                                        </div>
                                    </div>
                                    <div class="form-group search-unit">
                                        <label class="col-lg-4 rost_sousuo">订单创建日期：</label>
                                        <div class="col-lg-8">
                                            <label type="text" class="form-control order_boborder" id="showCreateDate" name=""></label>
                                        </div>
                                    </div>
                                    <div class="form-group search-unit">
                                        <label for="exampleInputName12" class="col-lg-4 rost_sousuo">订单创建人：</label>
                                        <div class="col-lg-8">
                                            <label type="text" class="form-control order_boborder" id="showCreateUser" name=""></label>
                                        </div>
                                    </div>
                                    <div class="form-group search-unit">
                                        <label class="col-lg-4 rost_sousuo">订单状态：</label>
                                        <div class="col-lg-8">
                                            <label type="text" class="form-control order_boborder" id="showOrderStatus" name=""></label>
                                        </div>
                                    </div> 
                                    <div class="form-group search-unit">
                                        <label class="col-lg-4 rost_sousuo">报减员：</label>
                                        <div class="col-lg-8">
                                            <label type="text" class="form-control order_boborder" id="showReducestaff" name=""></label>
                                        </div>
                                    </div> 
                                    <div class="form-group search-unit">
                                        <label class="col-lg-4 rost_sousuo">报减时间：</label>
                                        <div class="col-lg-8">
                                            <label type="text" class="form-control order_boborder" id="showReducestaffUpdateDt" name=""></label>
                                        </div>
                                    </div>
                                    <div class="form-group search-unit">
                                        <label class="col-lg-4 rost_sousuo">客服：</label>
                                        <div class="col-lg-8">
                                            <label type="text" class="form-control order_boborder" id="showCreateUserTwo" name=""></label>
                                        </div>
                                    </div>

                                </div>
                            </form>
                        </div>
                    </div>
                    <!-- 订单信息头部结束 -->

                    <!--社保公险金开始-->

                    <div class="panel-heading panel-heading_two">社保公积金</div>
                    <div class="panel-body panel-body_two">
                        <table id="shebaogongjijin" class="table table-striped table-bordered table-hover rost_table" width="100%">
                            <thead>
                                <tr>
                                    <th>险种</th>
                                    <th>服务起始月</th>
                                    <th>服务结束月</th>
                                    <th>账单起始月</th>
                                    <th>企业基数</th>
                                    <th>个人基数</th>
                                    <th>企业比例</th>
                                    <th>个人比例</th>
                                    <th>企业附加额</th>
                                    <th>个人附加额</th>
                                    <th>企业金额</th>
                                    <th>个人金额</th>
                                    <th>缴纳金额</th>
                                </tr>
                            </thead>
                            <tbody>
                            </tbody>
                        </table>
                    </div>
                    <!--社保公险金开结束-->
                    <br><br> 
                    <!--非社保公险金开始-->
                    <div class="panel-heading panel-heading_two">非社保公积金</div>
                    <div class="panel-body panel-body_two">
                        <table id="feishebaolist" class="table table-striped table-bordered table-hover rost_table" width="100%">
                            <thead>
                                <tr>
                                    <th>报价单名称</th>
                                    <th>产品名称</th>
                                    <th>服务起始月</th>
                                    <th>服务起始日</th>
                                    <th>服务结束月</th>
                                    <th>服务结束日</th>
                                    <th>账单起始月</th>
                                    <th>缴纳金额</th>
                                </tr>
                            </thead>
                            <tbody>
                            </tbody>
                        </table>
                    </div>
                    <!--非社保公险金开结束-->
                    <br><br>
                </div>
                <!--订单信息结束-->
                <!--订单费用信息开始-->
                <div class="panel-body order_infor_two">
                    <!-- 账单费用信息头部开始 -->
                    <div class="well well-lg well_bg" >
                        <div class="row">
                            <form role="form" class="search-form"> 
                                <div class="clearfix">

                                    <div class="form-group search-unit zhangdan_one">
                                        <label class="col-lg-4 rost_sousuo">账单月起至时间：</label>
                                        <div class="input-group date col-lg-8 rost_time">
                                            <input type="text" class="form-control" id="" name="">
                                            <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                        </div>
                                    </div>

                                    <div class="form-group search-unit search-unit_two zhangdan_one">
                                        <div class="input-group date col-lg-12 rost_time">
                                            <input type="text" class="form-control" id="" name="">
                                            <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                        </div>
                                    </div>

                                    <div class="form-group search-unit fuwu_one" style="display:none;">
                                        <label class="col-lg-4 rost_sousuo">服务月起至时间：</label>
                                        <div class="input-group date col-lg-8 rost_time">
                                            <input type="text" class="form-control" id="" name="">
                                            <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                        </div>
                                    </div>

                                    <div class="form-group search-unit search-unit_two fuwu_one" style="display:none;">
                                        <div class="input-group date col-lg-12 rost_time">
                                            <input type="text" class="form-control" id="" name="">
                                            <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                        </div>
                                    </div>

                                    <div class="form-group search-unit">
                                        <button class="btn btn-info zhangdan" type="button">
                                            <i class=""></i> <span class="bold">切换为账单月视图</span>
                                        </button>
                                        <button class="btn btn-info fuwu" type="button">
                                            <i class=""></i> <span class="bold">切换为服务月视图</span>
                                        </button>
                                    </div>

                                </div>
                                <center>
                                    <button class="btn btn-info order_bupa" type="button">
                                        <i class=""></i> <span class="bold">查 询</span>
                                    </button>
                                </center>
                            </form>
                        </div>
                    </div>
                    <!-- 账单费用信息头部结束 -->

                    <!--社保公险金开始-->
                    <!--服务月视图开始-->
                    <div class="panel-body panel-body_two panel-body_third">
                        <table id="example4" class="table table-striped table-bordered table-hover rost_table" width="100%" >
                            <thead>
                                <tr>
                                    <th class="delete">服务月</th>
                                    <th>险种</th>
                                    <th>金额</th>
                                    <th>服务月</th>
                                    <th>账单月</th>
                                    <th>企业基数</th>
                                    <th>个人基数</th>
                                    <th>企业比例</th>
                                    <th>个人比例</th>
                                    <th>是否预收</th>
                                    <th>付费频率</th>
                                </tr>
                            </thead>
                            <tbody>
                            </tbody>
                        </table>
                    </div>
                    <!--服务月视图结束-->

                    <!--账单月视图开始-->
                    <div class="panel-body panel-body_two panel-body_four">
                        <table id="example5" class="table table-striped table-bordered table-hover rost_table" width="100%">
                            <thead>
                                <tr>
                                    <th class="delete">账单月</th>
                                    <th>险种</th>
                                    <th>金额</th>
                                    <th>服务月</th>
                                    <th>账单月</th>
                                    <th>企业基数</th>
                                    <th>个人基数</th>
                                    <th>企业比例</th>
                                    <th>个人比例</th>
                                    <th>是否预收</th>
                                    <th>付费频率</th>
                                </tr>
                            </thead>
                            <tbody>
                            </tbody>
                        </table>
                    </div>
                    <!--账单月视图结束-->
                    <!--社保公险金开结束-->

                    <br><br> 
                </div>
                <!--订单费用信息结束-->
            </div>
        </div>
    </div>
</div>

<!--订单结束-->
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
<script src="static/bootstrap/vendor/bootstrap-datepicker-master/dist/js/bootstrap-datepicker.min.js"></script>
<!-- for datatable -->
<script src="static/bootstrap/vendor/datatables/media/js/jquery.dataTables.min.js"></script>
<script src="static/bootstrap/vendor/datatables_plugins/integration/bootstrap/3/dataTables.bootstrap.min.js"></script>
<!-- alert -->
<script src="static/bootstrap/vendor/sweetalert/lib/sweet-alert.min.js"></script>

<!-- App scripts -->
<script src="static/bootstrap/scripts/homer.js"></script>
<script src="static/js/yuangong.js"></script>


<!--订单信息头部开始-->
<script>
$(function (){

  $.ajax({
	  url:"static/json/dingdanxiangqing.json",
      dataType:'json',
      error: function(XMLHttpRequest, textStatus, errorThrown){
           alert(textStatus);
      },
      success: function(data){      
		var array = null;
		if(data){	
		   array = data.data;
		   array.forEach(function(item,index,array){  
		   	   alert(item)
			   $('#showEmployeeName').text(item.ehrPerson.employeeName);
			   $('#showJobNumber').text(item.ehrPerson.jobNumber);
			   $('#showCertType').text(item.ehrPerson.certType);
			   $('#showWorkLocation').text(item.ehrPerson.workLocation);
			   $('#showMobile').text(item.ehrPerson.mobile);
			   $('#showPhone').text(item.ehrPerson.phone);
			   $('#showStatus').text(item.ehrPerson.status);
			   $('#showEntryDate').text(item.ehrPerson.entryDate);
			   $('#showMemo').text(item.ehrPerson.memo);
			   $('#showLeftDate').text(item.ehrPerson.leftDate);
			   $('#showLeftCause').text(item.ehrPerson.leftCause);
			   $('#showLeftMemo').text(item.ehrPerson.Memo);
			   $('#showRegisterType').text(item.ehrPerson.registerType);
			   $('#showFamilyAddress').text(item.ehrPerson.familyAddress);
			   $('#showFamilyDate').text(item.ehrPerson.leftDate);
			   $('#showCreateDate').text(item.createDate);
			   $('#showCreateUser').text(item.createUser);
			   $('#showOrderStatus').text(item.orderStatus);
			   $('#showReducestaff').text(item.reducestaff);
			   $('#showReducestaffUpdateDt').text(item.reducestaffUpdateDt);
			   $('#showCreateUserTwo').text(item.createUser);
		   }) 
		}
		   
		   
      }
   });
})
</script>
<!--社保公积金开始-->
<script>
	$(function () {
		
		var table = $('#shebaogongjijin').dataTable({
    		"dom":
    		
    		"<'row'<'col-sm-12 table-responsive'tr>>",
    		
			"processing": true,
			"serverSide": true,
			"ajax": "static/json/shebaogongjijin.json",
			"columns": [
            { "data": "prodName" },
            { "data": "paymentStartMonth" },
            { "data": "paymentEndMonth" },
            { "data": "billStartMonth" },
            { "data": "companyBase" },
            { "data": "individualBase" },
            { "data": "companyRatio" },
			{ "data": "individualRatio" },
			{ "data": "companyAppend" },
			{ "data": "individualAppend" },
			{ "data": "companySum" },
			{ "data": "individualSum" },
            { "data": "paySum" }
			]
			
		})
		
	})
</script>
<!--社保公积金结束-->
<!--非社保公积金开始-->
<script>
	$(function () {
		
		var table = $('#feishebaolist').dataTable({
    		"dom":
    		
    		"<'row'<'col-sm-12 table-responsive'tr>>",
    		
			"processing": true,
			"serverSide": true,
			"ajax": "static/json/feishebao.json",
			"columns": [
            { "data": "quotationName" },
            { "data": "prodName" },
            { "data": "paymentStartMonth" },
            { "data": "paymentStartMonth" },
            { "data": "paymentEndMonth" },
            { "data": "paymentEndMonth" },
            { "data": "billStartMonth" },
            { "data": "sum" }
			]
		})
		
	})
</script>
<!--非社保公积金结束-->

<script>
   $(function () {
      	var table = $('#example4').dataTable({
    		"dom":
    		
    		"<'row'<'col-sm-3 col-md-5 col-lg-5'l><'col-sm-5 col-md-4 col-lg-4'f><'col-sm-4 col-md-3 col-lg-3 text-right'<'toolbar'>>>" +
            "<'row'<'col-sm-12 table-responsive'tr>>" +
            "<'row'<'col-sm-5'i><'col-sm-7'p>>",
    		
			
			"processing": true,
			"serverSide": true,
			"ajax": "static/json/dingdanfeiyong.json",
			"columns": [
            { "data": "paymentMonth" },
            { "data": "prodName" },
            { "data": "paySum" },
            { "data": "paymentMonth" },
            { "data": "billMonth" },
            { "data": "companyBase" },
            { "data": "individualBase" },
            { "data": "companyRatio" },
			{ "data": "individualRatio" },
			{ "render": function render( data, type, row, meta ){
				
				if(row.ifPrepay == 1){
					
					return	"预收";
				}else{
					return	"不预收";
				}
				
				}},
			{ "data": "frequency" }
			],
			
    	 	 "language": {
    			 "search": "",
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
				
    	
    }); 

</script>
<script>
   $(function () {
      	var table = $('#example5').dataTable({
    		"dom":
    		
    		"<'row'<'col-sm-3 col-md-5 col-lg-5'l><'col-sm-5 col-md-4 col-lg-4'f><'col-sm-4 col-md-3 col-lg-3 text-right'<'toolbar'>>>" +
            "<'row'<'col-sm-12 table-responsive'tr>>" +
            "<'row'<'col-sm-5'i><'col-sm-7'p>>",
			
			
			
			"processing": true,
			"serverSide": true,
			"ajax": "static/json/dingdanfeiyong.json",
			"columns": [
            { "data": "billMonth" },
            { "data": "prodName" },
            { "data": "paySum" },
            { "data": "paymentMonth" },
            { "data": "billMonth" },
            { "data": "companyBase" },
            { "data": "individualBase" },
            { "data": "companyRatio" },
			{ "data": "individualRatio" },
			{ "render": function render( data, type, row, meta ){
				
				if(row.ifPrepay == 1){
					
					return	"预收";
				}else{
					return	"不预收";
				}
				
				}},
			{ "data": "frequency" }
			],    		
					
    	 	 "language": {
    			 "search": "",
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
			
    }); 

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
        $('.input-group.date').datepicker({ });
        $('.input-daterange').datepicker({ });

    });

</script>

 <!--订单信息与订单费用信息-->
 <script>
 
 $(function(){
	 
	$(".dingdan_one").click(function(){
		
		$(".dingdan_two").removeClass("btn-info").addClass("information_button");
		$(".dingdan_one").removeClass("information_button").addClass("btn-info");
		$(".order_infor").show();
		$(".order_infor_two").hide();
		
	}) 
	 
 })
 
 </script>
 <script>
 $(function(){
	 
	$(".dingdan_two").click(function(){
		
		$(".dingdan_two").removeClass("information_button").addClass("btn-info");
		$(".dingdan_one").removeClass("btn-info").addClass("information_button");
		$(".order_infor").hide();
		$(".order_infor_two").show();
		
	}) 
	 
 })
 </script>
<!--服务月与账单月切换-->
<script>
	$(function (){
		
		$(".zhangdan").click(function(){
			
			$(".fuwu").show();
			$(".zhangdan").hide();
			$(".panel-body_third").hide();
			$(".panel-body_four").show();
			$(".zhangdan_one").show();
			$(".fuwu_one").hide();
			
		});
		
		$(".fuwu").click(function(){
			
			$(".zhangdan").show();
			$(".fuwu").hide();
			$(".panel-body_third").show();
			$(".panel-body_four").hide();
			$(".zhangdan_one").hide();
			$(".fuwu_one").show();
			
		});
		
	})
	
</script>

</body>
</html>