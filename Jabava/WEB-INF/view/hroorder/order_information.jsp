<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = "//"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
	<base href="${pageContext.request.contextPath}/">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <jsp:include flush="true" page="../common/styles.jsp"></jsp:include>
    <link rel="stylesheet" href="static/css/user.css">

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
    <div id="wrapper">

        <!-- 放主要内容 -->

        <div class="content animate-panel">

            <div class="row">
                <div class="col-lg-12">
                    <div class="hpanel">
                        <div class="panel-heading">
                            <h4 class="text-center font-bold">
                            <a href="order/toOrderList?yearmonth=${paymentMonth}" class="btn btn-success btn-sm pull-left">　返回　</a>
                            <span data-order="date">@orderDate</span> <span data-order="name">@orderName</span> 个人订单明细
                            </h4>
                        </div>
                        <!--订单信息开始-->
                        <div class="panel-body" id="allcheckFive">
                            <!-- 订单信息头部开始 -->
                            <div class="well well-lg well_bg" >

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
                                            <label class="col-lg-4 rost_sousuo">证件类型：</label>
                                            <div class="col-lg-8">
                                                <label type="text" class="form-control order_boborder" id="showCertType" name="certType"></label>
                                            </div>
                                        </div> 
                                        <div class="form-group search-unit">
                                            <label class="col-lg-4 rost_sousuo">证件号码：</label>
                                            <div class="col-lg-8">
                                                <label type="text" class="form-control order_boborder" id="showJobNumber" name="jobNumber"></label>
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
                                    </div>
                                </form>
                            </div>
                            <!-- 订单信息头部结束 -->
                            <!--社保公险金开始-->
                            <form action="" method="POST" class="form-horizontal" role="form" id="monthForm">
                                <input type="hidden" data-switch-type="payment" name="type" value="payment" />
                            </form>
                            <div class="panel-heading panel-heading_two">社保公积金</div>

                            <div class="panel-body panel-body_two">
                                <table id="example4" class="table table-bordered table-hover rost_table" width="100%">
                                    <thead>
                                        <tr>
                                            <th>服务月</th>
                                            <th>险种</th>
                                            <th>金额</th>
                                            <th>企业基数</th>
                                            <th>个人基数</th>
                                            <th>企业比例</th>
                                            <th>个人比例</th>
                                            <th>企业金额</th>
                                            <th>个人金额</th>
                                            
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


<!--订单信息头部开始-->
<script>
//点击查询按钮
function serchList(){
	if($('#type').val()=='bill'){//账单月查询
		$('#example5').dataTable().api().ajax.reload();
	}else{
		$('#example4').dataTable().api().ajax.reload();
	}
		
}
$(function (){
var orderId = ${orderId};
var ordRecId =  ${ordRecId};
var shebaoUrl = "order/findOrderSB?type=bill&ordRecId="+ordRecId;
jQuery.prototype.serializeObject=function(){  
	    var obj=new Object();  
	    $.each(this.serializeArray(),function(index,param){  
	        if(!(param.name in obj)){  
	            obj[param.name]=param.value;  
	        }  
	    });  
	    return obj;  
	};
  $.ajax({
	  url:"order/findOrderDetail",
	  data:{orderId:orderId},
      dataType:'json',     
      success: function(orderDetail){      
			if(orderDetail){
				 $('#showEmployeeName').text(orderDetail.bdEmpBaseInfo.employeeName);
                 $('[data-order=name]').text(orderDetail.bdEmpBaseInfo.employeeName);
				   $('#showJobNumber').text(orderDetail.bdEmpBaseInfo.cardId);
				   $('#showCertType').text(orderDetail.cardTypeShow);
				   $('#showWorkLocation').text(orderDetail.bdEmpBaseInfo.workCity);
				   $('#showMobile').text(orderDetail.bdEmpBaseInfo.mobile);
				   $('#showPhone').text(orderDetail.bdEmpBaseInfo.phone);
				   $('#showStatus').text(orderDetail.hireStatusShow);
				   if(orderDetail.bdEmpRecInfo.hireDate){
					   $('#showEntryDate').text(orderDetail.bdEmpRecInfo.hireDate.substr(0,10));
				   }
				  
				   $('#showMemo').text(orderDetail.bdEmpRecInfo.hireRemark);
				   if(orderDetail.bdEmpRecInfo.dimissionDate){
					   $('#showLeftDate').text(orderDetail.bdEmpRecInfo.dimissionDate.substr(0,10));
				   }
				  
				   $('#showLeftCause').text(orderDetail.dimissionTypeShow);
				   $('#showLeftMemo').text(orderDetail.bdEmpRecInfo.dimissionRemark);
				   $('#showRegisterType').text(orderDetail.residentTypeShow);
				   $('#showFamilyAddress').text(orderDetail.bdEmpBaseInfo.familyInsuranceAddress);
				   $('#showFamilyDate').text(orderDetail.bdEmpBaseInfo.addressBeginDate);
				   $('#showCreateDate').text(orderDetail.createDate);
				   $('#showCreateUser').text(orderDetail.createUser);
				   $('#showOrderStatus').text(orderDetail.orderStatusShow);
				   $('#showReducestaff').text(orderDetail.reducestaff);
				   $('#showReducestaffUpdateDt').text(orderDetail.reducestaffUpdateDt);
				   $('#showCreateUserTwo').text(orderDetail.createUser);
			}	
		   
      }
   });
 
  
    var orderCostUrl = "order/findOrderCostPage?&orderId="+orderId+"&ordRecId="+ordRecId+"&paymentMonth=${paymentMonth}";     
	var table = $('#example4').dataTable({
			"dom":
			
			"<'row'<'col-sm-12 table-responsive'tr>>" ,
				
			"processing": true,
			"serverSide": true,
			 "ajax":{
	 	            "url": orderCostUrl,
	 	            "type":"POST",
	 	            "data": function ( d ) {
		    			d.data = JSON.stringify($('#monthForm').serializeObject());
	 	            }
				},			
			"columns": [
	        { "data": "headPayment" ,'render': function(data, type, row, meta){
                if(data!=null) $('[data-order=date]').text(data)
                return data;
            }},
	        { "data": "prodName" },
	        { "data": "paySum" },
	        { "data": "companyBase" },
	        { "data": "individualBase" },
	        { "data": "companyRatio" },
	        { "data": "individualRatio" },
	        { "data": "companySum" },
			{ "data": "individualSum" }
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
})
</script>
<!--订单信息头部结束-->

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
 
</body>
</html>