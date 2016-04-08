<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html>
<head>
  <base href="<%=basePath%>">
<%@ page contentType="text/html; charset=utf-8"%>
<meta charset="utf-8">

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge">

<!-- Page title -->
<title>系统定时任务查询</title>

    <!-- Place favicon.ico and apple-touch-icon.png in the root directory -->
    <!--<link rel="shortcut icon" type="image/ico" href="favicon.ico" />-->

    <!-- Vendor styles -->
    <link rel="stylesheet" href="static/bootstrap/vendor/fontawesome/css/font-awesome.css" />
    <link rel="stylesheet" href="static/bootstrap/vendor/metisMenu/dist/metisMenu.css" />
    <link rel="stylesheet" href="static/bootstrap/vendor/animate.css/animate.css" />
    <link rel="stylesheet" href="static/bootstrap/vendor/bootstrap/dist/css/bootstrap.css" />
    <link rel="stylesheet" href="static/bootstrap/vendor/xeditable/bootstrap3-editable/css/bootstrap-editable.css" />
    <link rel="stylesheet" href="static/bootstrap/vendor/select2-3.5.2/select2.css" />
    <link rel="stylesheet" href="static/bootstrap/vendor/select2-bootstrap/select2-bootstrap.css" />
    <link rel="stylesheet" href="static/bootstrap/vendor/bootstrap-touchspin/dist/jquery.bootstrap-touchspin.min.css" />
    <link rel="stylesheet" href="static/bootstrap/vendor/bootstrap-datepicker-master/dist/css/bootstrap-datepicker3.min.css" />
    <link rel="stylesheet" href="static/bootstrap/vendor/awesome-bootstrap-checkbox/awesome-bootstrap-checkbox.css" />
    
    <!-- for data table -->
    <link rel="stylesheet" href="static/bootstrap/vendor/datatables_plugins/integration/bootstrap/3/dataTables.bootstrap.css" />
    <!-- for alert -->
     <link rel="stylesheet" href="static/bootstrap/vendor/sweetalert/lib/sweet-alert.css" />

    <!-- App styles -->
    <link rel="stylesheet" href="static/bootstrap/fonts/pe-icon-7-stroke/css/pe-icon-7-stroke.css" />
    <link rel="stylesheet" href="static/bootstrap/fonts/pe-icon-7-stroke/css/helper.css" />
    <link rel="stylesheet" href="static/bootstrap/styles/style.css">
   <!-- <link rel="stylesheet" href="css/user.css">-->
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
        <div class="panel-body"><!-- 
            <a class="small-header-action" href="">
                <div class="clip-header">
                    <i class="fa fa-arrow-up"></i>
                </div>
            </a> -->

            <div id="hbreadcrumb" class="pull-right m-t-lg">
                <ol class="hbreadcrumb breadcrumb">
                    <li><a href="to_index?jump=1">首页</a></li>
                    <li>
                        <span>系统定时任务查询</span>
                    </li>
                    <li class="active">
                        <span></span>
                    </li>
                </ol>
            </div>
            <h2 class="font-light m-b-xs">
                系统定时任务查询
            </h2>
            <small>待定</small>
        </div>
    </div>
</div>

<!-- 放主要内容 -->

<div class="content animate-panel">
	<div>
		<form role="form" id="triggerForm" class="search-form"> 
            <div class="clearfix">
            	<div class="form-group search-unit">
                  <label for="triggerKey" class="col-lg-4">触发器：</label>
                  <div class="col-lg-8">
                  	<input id="triggerKey" type="text" name="triggerKey" value="" class="form-control"/>
                  </div>
                </div>
                
            	<!--开始时间-->
             	<div class="form-group search-unit">
                  <label for="cronExpression" class="col-lg-4">表达式：</label>
                  <div class="col-lg-8">
                  	<input id="cronExpression" type="text" name="cronExpression" value="" class="form-control"/>
                  </div>
                </div>
            </div>
            <center style=" margin-top:10px;">
                <button class="btn btn-info" type="button" onclick="modifyCronExp()" id="mod">修改</button>
                <button class="btn btn-info" type="button" onclick="triggerJob()" id="trig">触发</button>
            </center>
        </form>
        
        <table id="triggerTable" class="table table-bordered table-hover" width="100%">
	    <thead>
	      <tr>
	       	<th>触发器名称</th>
	        <th>时间表达式</th>
	       <th>操作</th>
	      </tr>
	    </thead>
	    <tbody>
	
	    </tbody>
	  </table>
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
	$(function(){
		loadTable();
	});

	function loadTable(){
      	var table = $('#triggerTable').DataTable({
    		"dom":
    		
            "<'row'<'col-sm-12 table-responsive'tr>>" ,
            
			//json
	        "processing": true,
	        "serverSide": true,
	        "destroy": true,
	        "ordering": false,
	        "ajax": {
		        "url":"system/loadScheduledJob"
			},
			"columns": [
				{ "data": "triggerKey", "render": function(data, type, row, meta){
					return "<div style='width:100%;'><a onclick='forUpdate(\"" + data + "\",\"" + row.cronExpression + "\");'>" + data + "</a>";
				} },
				{ "data": "cronExpression" },
				{ "visible": false, "render": function render( data, type, row, meta ){
	            		return '<td><button class="btn btn-info btn-xs" type="button">修改</button></td>';
					} 
				}
			],
    		"columnDefs": [
    			{defaultContent: '', targets: '_all'}
			],
			
   	 		"language": {
                 "search": "过滤:",
	            "lengthMenu": "每页显示 _MENU_ 条记录",
	            "zeroRecords": "木有找到 - 报歉啦?",
	            "info": "显示 第 _PAGE_ 页 共 _PAGES_ 页",
	            "infoEmpty": "木有数据",
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
    
    function forUpdate(triggerKey,cronExpression){
    	$('#triggerKey').val(triggerKey);
    	$('#cronExpression').val(cronExpression);
    }
    
  	function modifyCronExp(){
  		if($('#triggerKey').val() == ''){
  			alert("触发器为空");
  			return false;
  		}
  		
  		if($('#cronExpression').val() == ''){
  			alert("表达式为空");
  			return false;
  		}
  		
  		$.ajax({
  		    type : 'post',
  		    url : "system/modifyCronExpression",
  		    data : $("#triggerForm").serialize(),
  		    //async : false,
  		    dataType: "json",
  		    success : function(result) {
	  		    if(result.success){
	  		    	alert('修改成功 !');
	  		    	window.location.reload();
	  		    }else{
	  		    	alert('修改失败!');
	  		    }
  		    }
  		});
  	}

  	function triggerJob(){
  		if($('#triggerKey').val() == ''){
  			alert("触发器为空");
  			return false;
  		}
  		
  		$.ajax({
  		    type : 'post',
  		    url : "system/triggerJob",
  		    data : $("#triggerForm").serialize(),
  		    //async : false,
  		    dataType: "json",
  		    success : function(result) {
	  		    if(result.success){
	  		      alert('触发成功 !');
	  		    }else{
	  		      alert('触发失败!');
	  		    }
  		    }
  		});
  	}
</script>
</body>
</html>
