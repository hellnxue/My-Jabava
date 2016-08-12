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
    <title>系统日志查询</title>
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
                    <div id="hbreadcrumb" class="m-t-xs m-b-xs">
                        <h2 class="font-normal m-b-xs text-center">
                            系统日志查询
                        </h2>
                    </div>
                </div>
            </div>
        </div>
        <!-- 放主要内容 -->
        <div class="content animate-panel">
            <div class="row">
                <div class="col-lg-12">
                    <div class="hpanel">
                        <!-- 复杂查询开始 -->
                        <div class="collapse out" id="collapseExample" aria-expanded="false" >
                            <div class="well well-lg " >
                                <div class="row">
                                    <form role="form" id="searchForm" class="search-form"> 
                                        <div class="clearfix">
                                            <div class="form-group search-unit">
                                                <label for="exampleInputName4" class="col-lg-4">用户：</label>
                                                <div class="col-lg-8">
                                                    <input type="text" name="userName" class="form-control"/>
                                                </div>
                                            </div>

                                            <!--开始时间-->
                                            <div class="form-group search-unit">
                                                <label for="exampleInputName4" class="col-lg-4">开始时间：</label>
                                                <div class="input-group date col-lg-8" data-date-format="yyyy-mm-dd">
                                                    <input type="text" name="startDate" class="form-control" id="exampleInputName2">
                                                    <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                                </div>
                                            </div>
                                            <!--结束时间-->
                                            <div class="form-group search-unit">
                                                <label for="exampleInputName4" class="col-lg-4">结束时间：</label>
                                                <div class="input-group date col-lg-8" data-date-format="yyyy-mm-dd">
                                                    <input type="text" name="endDate" class="form-control">
                                                    <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                                </div>
                                            </div>
                                            <div class="form-group search-unit">
                                                <label for="exampleInputName2" class="col-lg-4">操作信息：</label>
                                                <div class="col-lg-8">
                                                    <input type="text" class="form-control" name="operateInfo" id="exampleInputName2">
                                                </div>
                                            </div>
                                        </div>
                                        <center style=" margin-top:10px;">
                                            <button class="btn btn-info" type="button" onclick="search()" id="show">查询</button>
                                        </center>
                                    </form>
                                </div>
                            </div>
                        </div>
                        <!-- 复杂查询结束 -->
                        <div class="panel-body">
                            <table id="example2" class="table table-striped table-bordered table-hover"width="100%">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>用户</th>
                                        <th>操作信息</th>
                                        <th>操作时间</th>
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

/* 	   var table = $('#example2').dataTable() */
    	
      	var table = $('#example2').dataTable({
    		"dom":
    		
    		"<'row'<'col-sm-3'l><'col-sm-8'f><'col-sm-1'<'toolbar'>>>" +
    		"<'row'<'col-sm-12 table-responsive'tr>>" +
    		"<'row'<'col-sm-5'i><'col-sm-7'p>>",
				//json
			    "processing": true,
  	        	"serverSide": true,
        		"sort":true,
				"ajax": {
					"url":"system/dataTableSearch.do",
					"type":"POST",
	  	            "data": function (d) {
	  	                d.userName = $("#searchForm").find("[name='userName']").val();
	  	                d.startDate = $("#searchForm").find("[name='startDate']").val();
	  	                d.endDate = $("#searchForm").find("[name='endDate']").val();
	  	                d.operateInfo = $("#searchForm").find("[name='operateInfo']").val();
	  	            }
				},
				"columns": [
					{ "data": "sysLogId" },
					{ "data": "userName" },
			    	{ "data": "operateInfo" },
					{ "data": "operateDate" }
        		],
        		"aaSorting": [[ 3, "desc" ]],	//默认第4列降序排列
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

    	$("div.toolbar").html('<button class="btn btn-info  btn-sm pull-right" type="button" data-toggle="collapse" data-target="#collapseExample" aria-expanded="false" aria-controls="collapseExample"><i class="fa">高级搜索</i></button>');
    }); 
   
 //高级搜索
  	function search(){
  		$('#example2').dataTable().api().ajax.reload();
  	}

</script>

<!--日历-->
<script>

        $(function(){
            $('.input-group.date').datepicker({
                autoclose: true
            });
           
            $("#demo1").TouchSpin({
                min: 0,
                max: 100,
                step: 0.1,
                decimals: 2,
                boostat: 5,
                maxboostedstep: 10,
            });

            $("#demo2").TouchSpin({
                verticalbuttons: true
            });

            $("#demo3").TouchSpin({
                postfix: '%'
            });

            $("#demo4").TouchSpin({
                postfix: "a button",
                postfix_extraclass: "btn btn-default"
            });

            $(".js-source-states").select2();
            $(".js-source-states-2").select2();

            //turn to inline mode
            $.fn.editable.defaults.mode = 'inline';

            //defaults
            $.fn.editable.defaults.url = '#';

            //editables
            $('#username').editable({
                url: '#',
                type: 'text',
                pk: 1,
                name: 'username',
                title: 'Enter username'
            });

            $('#firstname').editable({
                validate: function(value) {
                    if($.trim(value) == '') return 'This field is required';
                }
            });

            $('#sex').editable({
                prepend: "not selected",
                source: [
                    {value: 1, text: 'Male'},
                    {value: 2, text: 'Female'}
                ],
                display: function(value, sourceData) {
                    var colors = {"": "gray", 1: "green", 2: "blue"},
                            elem = $.grep(sourceData, function(o){return o.value == value;});

                    if(elem.length) {
                        $(this).text(elem[0].text).css("color", colors[value]);
                    } else {
                        $(this).empty();
                    }
                }
            });

            $('#dob').editable();

            $('#event').editable({
                placement: 'right',
                combodate: {
                    firstItem: 'name'
                }
            });

            $('#comments').editable({
                showbuttons: 'bottom'
            });

            $('#fruits').editable({
                pk: 1,
                limit: 3,
                source: [
                    {value: 1, text: 'banana'},
                    {value: 2, text: 'peach'},
                    {value: 3, text: 'apple'},
                    {value: 4, text: 'watermelon'},
                    {value: 5, text: 'orange'}
                ]
            });

            $('#user .editable').on('hidden', function(e, reason){
                if(reason === 'save' || reason === 'nochange') {
                    var $next = $(this).closest('tr').next().find('.editable');
                    if($('#autoopen').is(':checked')) {
                        setTimeout(function() {
                            $next.editable('show');
                        }, 300);
                    } else {
                        $next.focus();
                    }
                }
            });

        });

    </script>

</body>
</html>