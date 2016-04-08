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
<title>工资变动模板列表</title>
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
                            <div class="text-right">
                               <!--button class="btn btn-success btn-sm btn-add-change" type="button">增加</button-->
                               <button class="btn btn-success btn-sm btn-upload-change" type="button">上传定义</button>
                               <button class="btn btn-success btn-sm btn-download-template" type="button">下载模板</button>
                            </div>
                         </div>
                        <div class="panel-body"> 
                         <div>
                            <table id="changeTable" class="table table-condensed table-hover table-bordered dataTable" style="width:100%">
                                <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>名称</th>
                                    <th>主键列</th>
                                    <th>主键类型</th>
                                    <th>描述</th>
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
        </div> 
              
        <!--主要内容结束-->

        <!-- Footer-->
        <!-- 放页脚  开始-->
        <jsp:include flush="true" page="../common/foot.div.jsp"></jsp:include>
        <!-- 放页脚  结束-->

    </div>
    <div style="display:none;">
    	<form id="changeForm" action="" method="post">
    		<input type="hidden" id="id" name="id">
    	</form>
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
    
    
    <!--jabava-->
    <script src="static/js/change/ajaxfileupload.js"></script>
    <script type="text/javascript">
    $(function () {
    	loadTable();
    	
    	//$('.btn-add-change').click(function () {
    	//	$('#id').val('');
    	//	$('#changeForm').attr('action','salaryChangeDef/toAddSalaryChangeDef');
    	//	$('#changeForm').submit();
    	//});
    	
    	$('.btn-upload-change').click(function () {
    		$('#changeForm').attr('action','salaryChangeDef/toUploadSalaryChangeDef');
    		$('#changeForm').submit();
    	});
    	
    	$('.btn-download-template').click(function () {
    		window.open("static/xls/salaryChangeDefTemplate.xlsx");;
    	});
    	
    });
    
    function loadTable(){
      	var table = $('#changeTable').DataTable({
    		"dom": "",
			
			//json
		    "processing": true,
       		"serverSide": true,
     		"bDestroy": true,
     		"ordering": false,
			"ajax": {
				"url":"salaryChangeDef/listSalaryChangeDef"
			},
			"columns": [
				{ "data": "salaryChangeDefId", "visible":false },
				{ "data": "name" },
				{ "data": "keyInfo" },
				{ "data": "keyType", "render": function(data, type, row, meta){
					if(data == 1){
						return "身份证";
					}else{
						return "工号";
					}
				} },
		    	{ "data": "description" },
				{ "render": function render( data, type, row, meta ){
						return '<button class="btn btn-danger btn-xs del-button" type="button" onclick="del(' + row.salaryChangeDefId + ');">删除</button>&nbsp;' + 
                        	'<button class="btn btn-success btn-xs" type="button" onclick="view(' + row.salaryChangeDefId + ');">查看</button>&nbsp;' +
                        	'<button class="btn btn-info btn-xs" type="button" onclick="download(' + row.salaryChangeDefId + ');">下载</button>';
					}
				}
			],
    		"columnDefs": [
    			{defaultContent: '', targets: '_all'}
			],
			
   	 		"language": {
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
    }

    function del(id){
    	swal(
    		{
	            title: "确定要删除此定义吗?",
	            text: "",
	            type: "warning",
	            showCancelButton: true,
	            confirmButtonColor: "#DD6B55",
	            confirmButtonText: "确定删除!",
	            cancelButtonText: "放弃操作!",
	            closeOnConfirm: false,
	            closeOnCancel: false 
            },
        	function (isConfirm) {
	            if (isConfirm) {
		            deleteById(id);
	            }else{
	            	swal("已取消", "定义未删除", "error");
	            }
	        }
        );
    }
    
    function deleteById(id){
    	$.ajax({
			url : "salaryChangeDef/deleteSalaryChangeDef",
			data : {id: id},
			dataType:'json',
			type : 'post',
			success : function(message) {
				if(message.success){
					swal({
	                    title: "删除成功!",
	                    text: "",
	                    type: "success",
	    				confirmButtonText: "确定"
	                });
					window.location.href="salaryChangeDef/toListSalaryChangeDef";
				}else{
					swal({
	                    title: "删除失败!",
	                    text: message.msg,
	                    type: "error",
	    				confirmButtonText: "确定"
	                });
				}
			}
		});
    }
    
    function mod(id){
    	$('#id').val(id);
		$('#changeForm').attr('action','salaryChangeDef/toAddSalaryChangeDef');
		$('#changeForm').submit();
    }
    
    function view(id){
    	$('#id').val(id);
		$('#changeForm').attr('action','salaryChangeDef/toViewSalaryChangeDef');
		$('#changeForm').submit();
    }
    
    function download(id){
    	$('#id').val(id);
		$('#changeForm').attr('action','salaryChangeDef/exportSalaryChangeDef');
		$('#changeForm').submit();
    }
</script>
</body>
</html>