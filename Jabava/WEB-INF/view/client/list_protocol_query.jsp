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
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <!-- Page title -->
    <title>jabava1.0协议查询界面</title>
    <jsp:include flush="true" page="../common/styles.jsp"></jsp:include>
    <link rel="stylesheet" href="static/css/user.css">

</head>
<body>

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

                        <div class="panel-heading ">
                            <div class="pull-right">
                                <a href="client/outsourcing"><button class="btn btn-success btn-xs" type="button">    <!--连接到申请开通-->
                                    <i class="fa fa-group"></i> <span class="bold">申请开通</span>
                                </button></a>
                            </div>
                            &nbsp; 
                            <div>
                            </div>
                        </div>


                        <!--查看弹框开始-->
                        <div class="modal fade hmodal-success form-row" id="detailModal" tabindex="-1" role="dialog"  aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="color-line"></div>
                                    <div class="modal-header clearfix">
                                        <div class="row">
                                            <div class="col-sm-12">
                                                <div class="form-group col-lg-12 org_h5 clearfix" >
                                                    <h4 class="role_fl query_padding_down query_nomargin">协议信息</h4>
                                                    <h5 class="role_fr query_nomargin query_h5">协议号：  <span id="detailPactCode"></span></h5>
                                                </div>
                                            </div>

                                            <div class="col-sm-12 query_margin_down">
                                                <div class="form-group">
                                                    <span class="col-lg-2 query_span_padding query_span_align">客户名称：</span>
                                                    <span class="col-lg-4 query_span_padding" id="clientName" >上海润物科技有限公司</span>
                                                    <span class="col-lg-2 query_span_padding query_span_align">协议状态：</span>
                                                    <span class="col-lg-4 query_span_padding" id="zhuangtai"></span>
                                                </div>
                                            </div>

                                            <div class="col-sm-12 query_margin_down">
                                                <div class="form-group">
                                                    <span class="col-lg-2 query_span_padding query_span_align">联系人：</span>
                                                    <span class="col-lg-4 query_span_padding" id="detailContactEmp"></span>
                                                    <span class="col-lg-2 query_span_padding query_span_align">联系电话：</span>
                                                    <span class="col-lg-4 query_span_padding" id="detailTelephoneNumber"></span>
                                                </div>
                                            </div>
                                            <div class="col-sm-12 query_margin_down">
                                                <div class="form-group">
                                                    <span class="col-lg-2 query_span_padding query_span_align">备注：</span>
                                                    <span class="col-lg-10 query_span_padding" id="detailRemark"></span>
                                                </div>
                                            </div>
                                            <div class="form-submit">
                                                <div class="col-md-11 col-lg-11 text-right">
                                                    <button class="btn btn-success guanbi" type="button">关 闭</button>
                                                </div> 
                                            </div>
                                        </div> 
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!--查看弹框结束-->

                        <div class="panel-body" id="allcheck">
                            <table id="example_protocol" class="table table-bordered table-hover rost_table" width="100%">                
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>协议号</th>
                                        <th>状态</th>
                                        <th>联系人</th>
                                        <th>联系电话</th>
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
    <script src="static/bootstrap/vendor/bootstrap-datepicker-master/dist/js/bootstrap-datepicker.min.js"></script>
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
	function  showProcotol (id){
		
		 $.ajax({
		  url:"outsourcing/getProtocolById",
	      dataType:'json',
	      data:{id:id},
	      error: function(XMLHttpRequest, textStatus, errorThrown){
	           alert(textStatus);
	      },
	      success: function(data){
	    	  
	    	  $('#clientName').text(data.ehrCompany.companyName);
	           $('#detailPactCode').text(data.pactCode);
			//   $('#zhuangtai').text(data.state);
			//  alert(data.state);
			   if(data.state == 3){
				   $("#zhuangtai").text("停用");				 
				}if(data.state == 2){
					$("#zhuangtai").text("启用");
				}else if(data.state == 0){
					 $("#zhuangtai").text("未启用");
				}
			   
			   $('#detailContactEmp').text(data.contactEmp);
			   $('#detailTelephoneNumber').text(data.telephoneNumber);
			   $('#detailRemark').text(data.remark);
	      }
	      });
		}
   $(function () {
      	var table = $('#example_protocol').dataTable({
    		"dom":
    		
        		"<'row'<'col-sm-6'l><'col-sm-6'f>>" +
        		"<'row'<'col-sm-12 table-responsive'tr>>" +
        		"<'row'<'col-sm-5'i><'col-sm-7'p>>",
				
				
				"serverSide": true,
				"ajax": "outsourcing/queryProtocol",
				"columns": [
					{ "data": "id" },
					{ "data": "pactCode" },
					{ "render": function render( data, type, row, meta ){
						
									if( row.state ==3){
										return "停用";										
									}if(row.state == 2){
										return "启用";
									}else{
										return "未启用";
									}
						
						} },                
					{ "data": "contactEmp" },
					{ "data": "telephoneNumber" },              
					{ "render": function render( data, type, row, meta ){
									return "<button onclick='showProcotol("+row.id+")' class='btn btn-success btn-xs demo5 dropdown-toggle rost_bt'  data-target='#detailModal' data-toggle='modal' type='button' title='查看' id='detail_contractName' name='contractName'>查看</button>";
                    					  
						} }
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

    	
    }); 
 </script>
<script>
  $(".guanbi").click(function(){
	  $('#detailModal').modal('hide');
  })
</script>

 
</body>
</html>