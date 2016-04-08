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
<title>工资变动管理</title>
<jsp:include flush="true" page="../common/styles.jsp"></jsp:include>
<!--for 临时改变-->
<link rel="stylesheet" href="static/js/plugins/form.validation/css/formValidation.css">
<link rel="stylesheet" href="static/css/user.css">
<script>
 
  function downloadFile(path) {
        window.open(path);
    }
</script>

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
                                工资变动管理
                            </h4>
                        </div>
                        <div class="panel-body"> 
                            <form class="form-horizontal" id="editForm">
                            	<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
                                         <div class="form-group">
                                            <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-3">年度：</label>
                                            <div class="input-group date col-xs-6 col-sm-6 col-md-6 col-lg-7"> 
                                                    <input type="text" class="form-control" name="" />
                                                    <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
                                        <div class="form-group">
                                            <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-3">工资项目：</label>
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
                                        <label class="control-label col-lg-3">上传文件：</label>
                                        <div class="col-lg-7">
                                            <div class="input-group" data-toggle="upload:file">
                                                <input type="text" class="form-control" readonly="readonly">
                                                <div class="input-group-btn">
                                                    <span class="btn btn-default">浏览...
                                                    <input type="file" class="sr-only" name='myfiles'  accept=".xlsx" ></span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    </div>
                                 <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-center">
                                     <button class="btn btn-info btn-sm btn-edit-rate" type="submit">导入</button>
                                    <button class="btn btn-info btn-sm btn-edit-rate" type="submit">查看</button>
                                </div>
                            </form>
                        </div>  
                        <!-- table -->
                        <div class="panel-body m-t-md"> 
                            <table id="projectTable" class="table table-condensed table-hover table-bordered dataTable table-responsive" width="100%">
                                <thead>
                                <tr>
                                    <th>税率ID</th>
                                    <th>税级距下线</th>
                                    <th>税级距上线</th>
                                    <th>税率</th>
                                    <th>速算扣除数</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                    <td>
                                     <a href="#" data-type="text" data-pk="1" data-placement="right" data-placeholder="Required" data-title="Enter your firstname" class="editable editable-click editable-empty">张三</a>
                                    </td>
                                   <td>
                                     <a href="#" data-type="text" data-pk="1" data-placement="right" data-placeholder="Required" data-title="Enter your firstname" class="editable editable-click editable-empty">张三</a>
                                    </td>
                                    <td>
                                     <a href="#" data-type="text" data-pk="1" data-placement="right" data-placeholder="Required" data-title="Enter your firstname" class="editable editable-click editable-empty">张三</a>
                                    </td>
                                    <td>
                                     <a href="#" data-type="text" data-pk="1" data-placement="right" data-placeholder="Required" data-title="Enter your firstname" class="editable editable-click editable-empty">张三</a>
                                    </td>
                                    <td>
                                     <a href="#" data-type="text" data-pk="1" data-placement="right" data-placeholder="Required" data-title="Enter your firstname" class="editable editable-click editable-empty">张三</a>
                                    </td>
                                    <td>
                                       <button class="btn btn-danger btn-xs btn-return btn-save-template del-button" type="button">删除</button>
                                    </td>
                                </tr>
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
    <script src="static/bootstrap/vendor/xeditable/bootstrap3-editable/js/bootstrap-editable.min.js"></script>
    <!-- for datatable -->
    <script src="static/bootstrap/vendor/datatables/media/js/jquery.dataTables.min.js"></script>
    <script src="static/bootstrap/vendor/datatables_plugins/integration/bootstrap/3/dataTables.bootstrap.min.js"></script>
    <script src="static/bootstrap/vendor/bootstrap-datepicker-master/dist/js/bootstrap-datepicker.js"></script>
    <script src="static/bootstrap/vendor/bootstrap-datepicker-master/dist/locales/bootstrap-datepicker.zh-CN.min.js"></script>
    <!-- alert -->
    <script src="static/bootstrap/vendor/sweetalert/lib/sweet-alert.min.js"></script>
    
    <!-- App scripts -->
    <script src="static/bootstrap/scripts/homer.js"></script>
    <script src="static/bootstrap/scripts/charts.js"></script>
    <!-- 上传 -->
    <script src="static/js/change/ajaxfileupload.js"></script>
    <script src="static/js/change/change.js"></script>
    <script type="text/javascript">

        // 修改
          $.fn.editable.defaults.mode = 'inline';
            $('.editable').editable({
                validate: function(value) {
                    if($.trim(value) == ''){
                         return 'This field is required';
                        }
                }
            });
    // 上传
        $('[data-toggle="upload:file"] :file').on('change.file:selected', function(event){
            var oEventTarget = $(this),
                oFile = $(this).val()
                fileExt = oFile.substr(oFile.lastIndexOf(".")).toLowerCase()

            if(fileExt!='.xlsx'){
                alert("请上传后缀名为xlsx的文件!");
                return;
            }

            oEventTarget.parents('[data-toggle="upload:file"]')
            .find(':text').val( oFile )
        })

        $('[data-toggle="upload:file"]').on('click', function(event){
            $(this).find(':file').trigger('click.file:selected')
        });

        // table
 		table = $('#projectTable').DataTable({
    		"dom": 
    			"<'row'<'col-sm-6'l><'col-sm-6'f><'col-sm-0'<'toolbar text-right'>>>" +
                "<'row'<'col-sm-12 table-responsive'tr>>",
			
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

        // $("div.toolbar").html('<button class="btn btn-info btn-sm" data-target="[data-modal=addTaxRate]" data-toggle="modal">新  增</button>');
        // 日历
	 $(function(){
        $('.input-group.date').datepicker({
            format: 'yyyymm', 
            weekStart: 1, 
            autoclose: true, 
            startView: 1,
            maxViewMode: 1,
            minViewMode:1,
            forceParse: false, 
            language: 'zh-CN'
        });

    });
    // 删除
    $(function () {
        $('.del-button').click(function () {
            swal({
                        title: "确定要删除此用户吗?",
                        text: "注意：用户删除后将不可登录!",
                        type: "warning",
                        showCancelButton: true,
                        confirmButtonColor: "#DD6B55",
                        confirmButtonText: "是,请删除该用户!",
                        cancelButtonText: "不, 放弃此操作!",
                        closeOnConfirm: false,
                        closeOnCancel: false },
                    function (isConfirm) {
                        if (isConfirm) {
                            swal("删除成功!", "该用户已经被删除.", "success");
                        } else {
                            swal("已取消", "用户未删除。你这逗我玩呢 :)", "error");
                        }
                    });
        });
    });
</script>
</body>
</html>