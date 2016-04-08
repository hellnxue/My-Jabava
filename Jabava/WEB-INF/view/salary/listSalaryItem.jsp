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
<title>工资项目管理</title>
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
                                工资项目管理
                            </h4>
                        </div>
                        <!-- 高级搜索开始 -->
                        <div class="collapse out" data-toggle="search" aria-expanded="false">
                          <div class="well well-lg" >
                            <div class="row">
                                <form class="form-horizontal" id="searchForm">
                                    <div class="col-xs-3 col-sm-3 col-md-3 col-lg-3">
                                        <div class="form-group">
                                            <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-5">项目名称：</label>
                                            <div class="col-xs-6 col-sm-6 col-md-6 col-lg-7">
                                                <input type="text" class="form-control" id="salaryItemName" name="salaryItemName">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-xs-3 col-sm-3 col-md-3 col-lg-3">
                                        <div class="form-group">
                                            <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-5">计算规则：</label>
                                            <div class="col-xs-6 col-sm-6 col-md-6 col-lg-7">
                                                <select class="form-control" id="calculateRule" name="calculateRule">
                                                    <option value="">全部</option>
                                                    <option value="1">税前计入</option>
                                                    <option value="2">税后计入</option>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-xs-3 col-sm-3 col-md-3 col-lg-3">
                                        <div class="form-group">
                                            <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-5">项目类别：</label>
                                            <div class="col-xs-6 col-sm-6 col-md-6 col-lg-7">
                                                <select class="form-control" id="itemType" name="itemType">
                                                    <option value="">全部</option>
                                                    <option value="1">加项</option>
                                                    <option value="2">减项</option>
                                                </select>
                                            </div>   
                                        </div>
                                    </div>
                                    <div class="col-xs-3 col-sm-3 col-md-3 col-lg-3">
                                        <div class="form-group">
                                            <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-5">是否中间项：</label>
                                            <div class="col-xs-6 col-sm-6 col-md-6 col-lg-7">
                                                <div class="radio radio-info radio-inline">
                                                    <input class="form-control" type="radio" name="isTransition" value="1">
                                                    <label for="inlineRadio1">是</label>
                                                </div>
                                                <div class="radio radio-info radio-inline">
                                                    <input class="form-control" type="radio" checked name="isTransition" value="0">
                                                    <label for="inlineRadio1">否</label>
                                                </div>
                                            </div>   
                                        </div>
                                    </div>
                                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-center">
                                        <a class="btn btn-info btn-sm btn-search">查　询</a>
                                    </div>
                                </form>
                            </div>
                          </div>
                        </div>
                        <!-- 高级搜索结束 -->

                        <div class="panel-body"> 
                            <table id="projectTable" class="table table-condensed table-hover table-bordered dataTable" width="100%">
                                <thead>
                                <tr>
                                    <th>项目ID</th>
                                    <th>显示顺序</th>
                                    <th>工资项目名称</th>
                                    <th>变动表</th>
                                    <th>计算公式</th>
                                    <th>计税方法</th>
                                    <th>计算规则</th>
                                    <th>项目类别</th>
                                    <th>项目标识</th>
                                    <th>备注</th>
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
              
        <!--主要内容结束-->

        <!-- 放页脚  开始-->
        <jsp:include flush="true" page="../common/foot.div.jsp"></jsp:include>
        <!-- 放页脚  结束-->

    </div>
    <div style="display:none;">
    	<form id="detailForm" action="" method="post">
    		<input type="hidden" id="salaryItemId" name="salaryItemId">
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
    
<script type="text/javascript">
	var table;
	jQuery.prototype.serializeObject=function(){  
        var obj=new Object();  
        $.each(this.serializeArray(),function(index,param){  
          if(!(param.name in obj)){  
            obj[param.name]=param.value;  
          }  
        });  
        return obj;  
    };
	
    $(function (){
    	$('.btn-search').click(function () {
    		var params = $("#searchForm").serializeObject();
    		console.log(params);
    		loadTable(params);
    	});
    	
    	loadTable();
    });
    
    function loadTable(params){
    	if(table){
    		table.destroy();
    	}
    	table = $('#projectTable').DataTable({
    		"dom": 
                "<'row'<'col-sm-6'l><'col-sm-4'f><'col-sm-2'<'toolbar text-center'>>>" +
                "<'row'<'col-sm-12 table-responsive'tr>>" +
                "<'row'<'col-sm-5'i><'col-sm-7'p>>",
			
			//json
		    "processing": true,
       		"serverSide": true,
     		"bDestroy": true,
     		"ordering": false,
     		"sServerMethod": "POST",
			"ajax": {
				"url":"salaryItem/listSalaryItemPage",
				"data": params ? params : {}
			},
			"columns": [
				{ "data": "salaryItemId", "visible":false },
				{ "data": "displayOrder" },
				{ "data": "salaryItemName" },
				//{ "data": "changeTableName" },
				{ "data": "salaryChangeDefName","render": function(data, type, row, meta){
					if(row.changeTableName == 'ehr_attendance'){
						return '考勤表';
					}else if(row.changeTableName == 'ehr_social_insurance'){
						return '社保表';
					}else{
						return data;
					}
				} },
		    	{ "data": "changeFormula" },
                { "data": "taxRule", "render": function(data, type, row, meta){
                	if(data == 1){
                		return "合并计税";
                	}else{
                		return "独立计税";
                	}
                } },
                { "data": "calculateRule", "render": function(data, type, row, meta){
                	if(data == 1){
                		return "税前计入";
                	}else{
                		return "税后计入";
                	}
                } },
                { "data": "itemType", "render": function(data, type, row, meta){
                	if(data == 1){
                		return "加项";
                	}else{
                		return "减项";
                	}
                } },
                { "data": "yearlyFlag", "render": function(data, type, row, meta){
                	if(data == 0){
                		return "普通项目";
                	}else if(data == 1){
                   		return "年终奖项目";
                   	}else if(data == 2){
                   		return "养老项目";
                   	}else if(data == 3){
                   		return "医疗项目";
                   	}else if(data == 4){
                   		return "失业项目";
                   	}else if(data == 5){
                   		return "公积金项目";
                   	}else{
                		return "";
                	}
                } },
                { "data": "itemMemo","visible": false },
				{ "render": function render( data, type, row, meta ){
						return '<button class="btn btn-success btn-xs" type="button" onclick="mod(' + row.salaryItemId + ');">修改</button>&nbsp;' +
                            '<button class="btn btn-danger btn-xs del-button" type="button" onclick="del(' + row.salaryItemId + ');">删除</button>';
					}
				}
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
   	    });

        $(".toolbar").html('<button class="btn btn-info btn-sm pull-right" type="button" data-toggle="collapse" data-target="[data-toggle=search]" aria-expanded="false" aria-controls="collapseExample"><i class="fa">高级搜索</i></button>&nbsp<button class="btn btn-info btn-sm btn-add-item">新　增</button>');

    	$('.btn-add-item').click(function () {
    		$('#salaryItemId').val('');
    		$('#detailForm').attr('action','salaryItem/toAddSalaryItem');
    		$('#detailForm').submit();
    	});
    }
    
    function mod(id){
    	$('#salaryItemId').val(id);
		$('#detailForm').attr('action','salaryItem/toAddSalaryItem');
		$('#detailForm').submit();
    }

    function del(id){
    	swal(
    		{
	            title: "确定要删除此项目吗?",
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
	            	swal("已取消", "项目未删除", "error");
	            }
	        }
        );
    }
    
    function deleteById(id){
    	$.ajax({
			url : "salaryItem/deleteSalaryItem",
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
					window.location.href="salaryItem/toListSalaryItem";
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
</script>
</body>
</html>