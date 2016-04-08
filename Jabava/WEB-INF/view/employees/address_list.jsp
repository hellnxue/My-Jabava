<%@ page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html>
<head>
	<base href="${pageContext.request.contextPath}/">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <!-- Page title -->
    <title>通讯录</title>
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
<div class="normalheader transition animated fadeIn small-header">
    <div class="hpanel">
        <div class="panel-body">
            <div id="hbreadcrumb" class="pull-right m-t-lg">
                <ol class="hbreadcrumb breadcrumb">
                    <li><a href="to_index?jump=1">首页</a></li>
                    <li>
                        <span>通讯录</span>
                    </li>
                    <li class="active">
                        <span>通讯录</span>
                    </li>
                </ol>
            </div>
            <h2 class="font-light m-b-xs">
               通讯录
            </h2>
            <small>待定</small>
        </div>
    </div>
</div>

<!-- 放主要内容 -->

<div class="content animate-panel">
    <div class="row">
        <div class="col-lg-12">
            <div class="hpanel">
                <div class="panel-heading ">
                   <div class="pull-right">
					 <button class="btn btn-success btn-xs" type="button" data-target="#myModal7" data-toggle="modal" onclick="downMB('xls/导出通讯录.xls');" title="导出通讯录模板">
                       <i class="fa fa-group"></i> <span class="bold">导出通讯录</span>
                     </button>
                   </div>
                    记录集 
            	<div>
            </div>
         </div>
                <div class="panel-body">
                <table id="example2" class="table table-striped table-bordered table-hover" style="width:100%">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>工号</th>
                    <th>姓名</th>
                    <th>所属部门</th>
                    <th>职位</th>
                    <th>电话</th>
                    <th>邮箱</th>
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
<jsp:include flush="true" page="../common/js.jsp"></jsp:include>
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

<!-- for datatable -->
<script src="static/bootstrap/vendor/datatables/media/js/jquery.dataTables.min.js"></script>
<script src="static/bootstrap/vendor/datatables_plugins/integration/bootstrap/3/dataTables.bootstrap.min.js"></script>
<!-- alert -->
<script src="static/bootstrap/vendor/sweetalert/lib/sweet-alert.min.js"></script>

<!-- App scripts -->
<script src="static/bootstrap/scripts/homer.js"></script>
	<script>
   $(function () {
      	var table = $('#example2').dataTable({
    		"dom":
    		
    		"<'row'<'col-sm-3'l><'col-sm-9'f><'col-sm-1'<'toolbar'>>>" +
    		"<'row'<'col-sm-12'tr>>" +
    		"<'row'<'col-sm-5'i><'col-sm-7'p>>",
    		
    	 	//json
			    "processing": true,
        		"serverSide": true,
				"ajax": "static/json/adress.json",
				"columns": [
					{ "data": "personId" },
					{ "data": "jobNumber" },
			    	{ "data": "employeeName" },
					{ "data": "org" },
					{ "data": "post" },
					{ "data": "phone" },
					{ "data": "email" },
					
				 
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
<!--导出通讯录-->
<script>
    function downMB(moban) {
        window.open(moban);
    }
    function sendOrderMail() {
        if (document.getElementById("file").value == "") {
            alert("请选择要上传的附件");
            return false;
        }
        var path = document.getElementById("file").value;
        var isIE = (document.all) ? true : false; 3           
        var isIE9 = isIE && (navigator.userAgent.indexOf('MSIE 9.0') != -1);  
        var isIE10 = isIE && (navigator.userAgent.indexOf('MSIE 10.0') != -1);  
        var isIE11 = isIE && (navigator.userAgent.indexOf('MSIE 11.0') != -1); 
        var isChrome = window.navigator.userAgent.indexOf("Chrome") !== -1 
        if(isIE9 || isIE10 || isIE11 || isChrome){
            path = path.substring(path.lastIndexOf("\\")+1,path.length);
        }
        document.OrderSendForm.saction.value = "sendMail";
        document.OrderSendForm.attachment.value = path;
        document.OrderSendForm.action = "hroorderSend.do";
        document.OrderSendForm.submit();
    }
</script> 
</body>
</html>