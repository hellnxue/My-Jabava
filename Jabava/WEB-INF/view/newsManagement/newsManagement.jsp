<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<base href="${pageContext.request.contextPath}/">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge">

<!-- Page title -->
<title>资讯管理界面界面</title>
<jsp:include flush="true" page="../common/styles.jsp"></jsp:include>
<link rel="stylesheet" href="static/css/zTreeStyle/zTreeStyle.css" type="text/css">
<!--for 临时改变-->
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
                        <div class="panel-heading">
                            <h4 class="text-center font-bold">
                                资讯管理
                            </h4>
                        </div>
                        <div class="panel-body"> 
                            <table id="example2" class="table table-striped table-condensed table-hover table-bordered dataTable" width="100%">
                                <thead>
                                <tr>
                                    <th>标题</th>
                                    <th>时间</th>
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
              
        <!--主要内容结束-->

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

    <!-- for datatable -->
    <script src="static/bootstrap/vendor/datatables/media/js/jquery.dataTables.min.js"></script>
    <script src="static/bootstrap/vendor/datatables_plugins/integration/bootstrap/3/dataTables.bootstrap.min.js"></script>

    <!-- alert -->
    <script src="static/bootstrap/vendor/sweetalert/lib/sweet-alert.min.js"></script>
    
    <!-- App scripts -->
    <script src="static/bootstrap/scripts/homer.js"></script>
    <script src="static/bootstrap/scripts/charts.js"></script>
    <script>
        $(function(){
            $('#example2').DataTable({
                "dom": 
                "<'row'<'col-sm-10'l><'col-sm-2'<'toolbar text-right'>>>" +
                "<'row'<'col-sm-12 table-responsive'tr>>" +
                "<'row'<'col-sm-5'i><'col-sm-7'p>>",
            	//json
    		    "processing": true,
           		"serverSide": true,
         		"bDestroy": true,
         		"ordering": false,
    			"ajax": {
    				"url":"weixinnews/findNewsPage",
    				"type":"post"
    			},
    			"columns": [    			   
    			    { "render": function render( data, type, row, meta ){
    			    	if(row.newsTitle){
    						return "<a href='newsManagement/newsDetail?newsId="+row.newsId+"'>"+row.newsTitle+"</a>";
    					}                           
					}
				  },
    			    {"data": "createDate" }
                 
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
            })
            $(".toolbar").html('<a class="btn btn-info btn-sm" href="newsManagement/addNews" >新增</a>');
        })
    </script>
</body>
</html>