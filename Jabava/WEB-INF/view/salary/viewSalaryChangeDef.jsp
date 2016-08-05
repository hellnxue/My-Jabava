<%@ page contentType="text/html; charset=utf-8"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = "//"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html>
<head>
	<base href="${pageContext.request.contextPath}/">
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">

    <!-- Page title -->
    <title>查看工资变动定义</title>
    <jsp:include flush="true" page="../common/styles.jsp"></jsp:include>
    <link rel="stylesheet" href="static/css/user_bata.css">
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
                               <button class="btn btn-success btn-sm pull-left btn-return" type="button">返回</button>
                                工资变动表管理
                            </h4>
                        </div>
                        <div class="panel-body">
                            <div class="col-md-5 col-md-offset-3">
                                <form role="form" id="searchForm" class="form-horizontal"> 
                                    <div class="form-group">
                                        <label for="name" class="control-label col-sm-9 col-md-4 col-lg-4 font-bold">名称：</label>
                                        <div class="col-sm-8 col-md-8 col-lg-8">
                                            <input type="text" name="name" class="form-control" value="${salaryChangeDef.name }" readonly/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="keyInfo" class="control-label col-sm-9 col-md-4 col-lg-4 font-bold">身份识别列标题：</label>
                                        <div class="col-sm-8 col-md-8 col-lg-8">
                                            <input type="text" name="keyInfo" class="form-control" value="${salaryChangeDef.keyInfo }" readonly/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="keyType" class="control-label col-sm-9 col-md-4 col-lg-4 font-bold">主键类型：</label>
                                        <div class="col-sm-8 col-md-8 col-lg-8">
                                            <select class="form-control" id="keyType" name="keyType" data-field="component" disabled="disabled">
												<option value="0">--------</option>
												<option value="1">身份证</option>
												<option value="2">工号</option>
											</select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="keyType" class="control-label col-sm-9 col-md-4 col-lg-4 font-bold">是否月度信息：</label>
                                        <div class="col-sm-8 col-md-8 col-lg-8">
                                            <select class="form-control" id="isMonthly" name="isMonthly" data-field="component" disabled="disabled">
												<option value="1">是</option>
												<option value="0">否</option>
											</select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="description" class="control-label col-sm-9 col-md-4 col-lg-4 font-bold">描述：</label>
                                        <div class="col-sm-8 col-md-8 col-lg-8">
                                            <textarea class="form-control" id="description" name="description" readonly>${salaryChangeDef.description }</textarea>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div class="hpanel">
                        <div class="panel-body">
                            <table id="example2" class="table table-striped table-bordered table-hover"width="100%">
                                <thead>
                                    <tr>
                                        <th>字段名称</th>
                                        <th>显示名称</th>
                                        <th>数据类型</th>
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
   $(function () {
	   	$('.btn-return').click(function () {
	   		window.location.href = "salaryChangeDef/toListSalaryChangeDef";
	   	});
   	
      	var table = $('#example2').dataTable({
    		"dom":
    		
    		"<'row'<'col-sm-12 table-responsive'tr>>",
    		
			//json
		    "processing": true,
 	        "serverSide": true,
       		"ordering": false,
			"ajax": {
				"url":"salaryChangeDef/loadSalaryChangeDefItem",
				"type":"POST",
  	            "data": {id: ${salaryChangeDef.salaryChangeDefId }}
			},
			"columns": [
				{ "data": "columnName", "visible": false },
				{ "data": "displayName" },
		    	{ "data": "dataType", "render": function(data){
		    		if(data == 1){
		    			return "字符类型";
		    		}else{
		    			return "数值类型";
		    		}
		    	} },
				{ "render": function(){
					return ""; 
				}}
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
      	
      	$('#keyType').val(${salaryChangeDef.keyType });
      	$('#isMonthly').val(${salaryChangeDef.isMonthly });
    }); 

</script>

</body>
</html>