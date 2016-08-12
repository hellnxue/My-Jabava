<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="com.jabava.utils.RequestUtil"%>
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

    <!-- Page title -->
    <title>jabava1.0查询增减表界面</title>
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
	
    <!-- 放主要内容 -->
    <div id="wrapper">
        <div class="normalheader transition animated fadeIn small-header">
        	<div class="hpanel">
            	<div class="panel-body">
                	<div id="hbreadcrumb" class="m-t-xs m-b-xs">
                    	<h2 class="font-normal m-b-xs text-center">
                        	订单增减变反馈
                    	</h2>
                	</div>
            	</div>
        	</div>
    	</div>
        <div class="content animate-panel">
            <div class="row">
                <div class="col-lg-12">
                    <div class="hpanel">
                        <div class="panel-heading ">
                            <div class="pull-right">
                                <a href="client/upload_change" class="btn btn-success btn-xs">
                                	<i class="fa fa-group"></i> <span class="bold">上传增减变表</span>
                                </a>                                                              
                            </div>
                            &nbsp; 
                            <div>
                            </div>
                        </div>

                        <div class="panel-body" id="allcheck">
                        <table id="changeTable" class="table table-bordered table-hover rost_table" width="100%">

                                <thead>
                                    <tr>
                                        <th>批次号</th>
                                        <th>上传时间</th>
                                        <th>增员人数</th>
                                        <th>减员人数</th>
                                        <th>变更人数</th>
                                        <th>备注</th>
                                        <th>附件</th>
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
	//下载excel 文件
	 function downloadExcel(hroOrderSendId) {		
		 var base ="${pageContext.request.contextPath}/change/downloadFile?hroOrderSendId="
		 var url = base+hroOrderSendId;
		 self.location.href=url;
      }
	function formatDate(time){
		//alert(time+'time');
		var JsonDateValue = new Date(time);	
		//alert(JsonDateValue+'json');
		var date = JsonDateValue.getFullYear()+"-"+(JsonDateValue.getMonth()+1)+"-"+JsonDateValue.getDate()+" "+JsonDateValue.getHours()+":"+JsonDateValue.getSeconds()+":"+JsonDateValue.getMinutes();
		
		return time
	}
   $(function () {
	   var table = $('#changeTable').dataTable({
				
			"dom" :

			"<'row'<'col-sm-3'l><'col-sm-9'f>>"
					+ "<'row'<'col-sm-12 table-responsive'tr>>"
					+ "<'row'<'col-sm-5'i><'col-sm-7'p>>",
					
			"processing": true,
	        "serverSide": true,
			"ajax": "change/findChange",
			"sServerMethod": "POST",
			"columns" : [
				{"data" : "batchCode" },
				{"render": function render( data, type, row, meta ){
						return formatDate(row.sendTime);
					}
				},
				{"data" : "numAdd" },
				{"data" : "numSubtract" },
				{"data" : "numChange" },
				{"data" : "remark" },
				{ "render": function render( data, type, row, meta ){
						var html ="<a onclick='downloadExcel(" +row.hroOrderSendId+")'"
							+"class='anxin'>"+row.attachment+" <span class='glyphicon glyphicon-download-alt text-success'></span></a>";
						return html;
					}
				},
				{"render": function render( data, type, row, meta ){
					var strHtml = '';
					<% if(RequestUtil.hasPower("ascquery_vi")){ %>
					strHtml += '<a href="change/listDetail/' + row.hroOrderSendId + '/1" class="btn btn-xs btn-success">明细</a>';
					<% } %>
						return strHtml;
					}
				}
			],

			"language" : {
				"search" : "过滤:",
				"lengthMenu" : "每页显示 _MENU_ 条记录",
				"zeroRecords" : "暂无数据 - 报歉啦〜",
				"info" : "显示 第 _PAGE_ 页 共 _PAGES_ 页",
				"infoEmpty" : "暂无数据",
				"infoFiltered" : "(筛选自 _MAX_ 条记录)",
				"paginate" : {
					"first" : "首页",
					"previous" : "前一页",
					"next" : "后一页",
					"last" : "尾页"
				}
			}

		});
    }); 

</script>
</body>
</html>