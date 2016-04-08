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
<title>月度工资查看</title>
<jsp:include flush="true" page="../common/styles.jsp"></jsp:include>
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
                                <a class="btn btn-success btn-sm pull-left btn-return m-r-md" type="button" href="salary/listMonthlySalary">返回</a>
                                月度工资查看
                            </h4>     
                        </div>
                            <div class="panel-body">
                                <form class="form-horizontal">
                                    <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
                                         <div class="form-group">
                                            <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-5">年度：</label>
                                            <div class="input-group date col-xs-6 col-sm-6 col-md-6 col-lg-7"> 
                                                    <input type="text" class="form-control" name="" />
                                                    <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
                                        <div class="form-group">
                                            <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-5">用途：</label>
                                            <div class="col-xs-6 col-sm-6 col-md-6 col-lg-7">
                                                <select class="form-control">
                                                    <option value="">全部</option>
                                                    <option value="">工资</option>
                                                </select>
                                            </div>   
                                        </div>
                                    </div>
                                    <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
                                        <div class="form-group">
                                            <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-5">版本号：</label>
                                            <div class="col-xs-6 col-sm-6 col-md-6 col-lg-7">
                                               <input type="text" class="form-control" name="" />
                                            </div>   
                                        </div>
                                    </div>
                                    <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
                                         <div class="form-group">
                                            <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-5">审核时间：</label>
                                            <div class="input-group date col-xs-6 col-sm-6 col-md-6 col-lg-7"> 
                                                    <input type="text" class="form-control" name="" />
                                                    <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                            </div>

                                        </div>
                                    </div>
                                    <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
                                        <div class="form-group">
                                            <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-5">审核人：</label>
                                            <div class="col-xs-6 col-sm-6 col-md-6 col-lg-7">
                                               <input type="text" class="form-control" name="" />
                                            </div>   
                                        </div>
                                    </div>
                                    <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4 ">
                                        <div class="form-group">
                                            <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-5">审核状态：</label>
                                            <div class="col-xs-6 col-sm-6 col-md-6 col-lg-7">
                                                <select class="form-control">
                                                    <option value="">全部</option>
                                                    <option value="">已审核</option>
                                                    <option value="">未审核</option>
                                                </select>
                                            </div>   
                                        </div>
                                    </div>
                                </form>
                            </div>
                        <div class="panel-heading">
                            <h4 class="text-center font-bold">
                                <button class="btn btn-info btn-sm pull-right btn-return" type="button">明细表</button>
                            </h4>     
                        </div>
                        <div class="panel-body m-t-lg"> 
                         <div>
                            <table id="projectTable" class="table table-condensed table-hover table-bordered dataTable table-responsive" width="100%">
                                <thead>
                                <tr>
                                    <th>员工工号</th>
                                    <th>员工姓名</th>
                                    <th>应税工资</th>
                                    <th>代扣税</th>
                                    <th>税后工资</th>
                                    <th>实发工资</th>
                                </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>ssssssss</td>
                                        <td>ssssssss</td>
                                        <td>ssssssss</td>
                                        <td>ssssssss</td>
                                        <td>ssssssss</td>
                                        <td>ssssssss</td>
                                    </tr>
                                </tbody>
                            </table>
                          </div>
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
    <script src="static/bootstrap/vendor/bootstrap-datepicker-master/dist/js/bootstrap-datepicker.js"></script>
    <script src="static/bootstrap/vendor/bootstrap-datepicker-master/dist/locales/bootstrap-datepicker.zh-CN.min.js"></script>

    <!-- for datatable -->
    <script src="static/bootstrap/vendor/datatables/media/js/jquery.dataTables.min.js"></script>
    <script src="static/bootstrap/vendor/datatables_plugins/integration/bootstrap/3/dataTables.bootstrap.min.js"></script>


    <!-- alert -->
    <script src="static/bootstrap/vendor/sweetalert/lib/sweet-alert.min.js"></script>
    
    <!-- App scripts -->
    <script src="static/bootstrap/scripts/homer.js"></script>
    <script src="static/bootstrap/scripts/charts.js"></script>
    
    <script type="text/javascript">
    $(function (){
      	var table = $('#projectTable').DataTable({
    		"dom": 
                "<'row'<'col-sm-3'l><'col-sm-9'f>>"+
                "<'row'<'col-sm-12 table-responsive'tr>>" +
                "<'row'<'col-sm-5'i><'col-sm-7'p>>",
			
			//json
		 //    "processing": true,
   //     		"serverSide": true,
   //   		"bDestroy": true,
   //   		"ordering": false,
			// "ajax": {
			// 	"url":"salaryChangeDef/listSalaryChangeDef"
			// },
			// "columns": [
			// 	{ "data": "salaryChangeDefId", "visible":false },
			// 	{ "data": "name" },
			// 	{ "data": "keyInfo" },
			// 	{ "data": "keyType"},
		 //    	{ "data": "description" },
   //              { "data": "description" },
   //              { "data": "description" },
   //              { "data": "description" },
			// 	{ "render": function render( data, type, row, met a ){
			// 			return '<button class="btn btn-success btn-xs" type="button" onclick="">修改</button>&nbsp;' +
   //                          '<button class="btn btn-danger btn-xs del-button" type="button" onclick="del(' + row.salaryChangeDefId + ');">删除</button>';
			// 		}
			// 	}
			// ],
   //  		"columnDefs": [
   //  			{defaultContent: '', targets: '_all'}
			// ],
			
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
   	    });
        
         $("div.toolbar").html('<button class="btn btn-info btn-sm pull-right" type="button" data-toggle="collapse" data-target="#collapseExample" aria-expanded="false" aria-controls="collapseExample"><i class="fa">高级搜索</i></button>');
    });

   
    // 日历
    $(function(){
        $('#datepicker').datepicker();
        $("#datepicker").on("changeDate", function(event) {
            $("#my_hidden_input").val(
                    $("#datepicker").datepicker('getFormattedDate')
            )
        });

        $('#datapicker2').datepicker();
        $('.input-group.date').datepicker({
            format: 'yyyymm', 
            weekStart: 1, 
            autoclose: true, 
            startView: 1, maxViewMode: 1,minViewMode:1,
            forceParse: false, 
            language: 'zh-CN'
        });
        $('.input-daterange').datepicker({ });
    });

</script>
</body>
</html>