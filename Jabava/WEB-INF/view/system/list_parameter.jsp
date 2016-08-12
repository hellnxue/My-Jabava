<%@ page contentType="text/html; charset=utf-8" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.jabava.utils.RequestUtil"%>
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
	<title>Jabava | V1.0（系统参数设置）</title> 
	<jsp:include flush="true" page="../common/styles.jsp"></jsp:include>
	<!-- for editable -->
    <link rel="stylesheet" href="static/bootstrap/vendor/xeditable/bootstrap3-editable/css/bootstrap-editable.css" />
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
		<div class="normalheader transition animated fadeIn small-header">
	        <div class="hpanel">
	            <div class="panel-body">
	                <div id="hbreadcrumb" class="m-t-xs m-b-xs">
	                    <h2 class="font-normal m-b-xs text-center">
	                        系统参数配置
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
						<div class="panel-heading ">
							<div class="text-right">
								<% if(RequestUtil.hasPower("index_parameter_ap")){ %>
								<button class="btn btn-success btn-xs" type="button" data-target="#myModal7" data-toggle="modal">
									<i class="fa fa-group"></i> <span class="bold">新增参数</span>
								</button>
								<% } %>
							</div>
						</div>
						<!--表格start-->
						<div class="panel-body">
							<table id="example2"class="table table-striped table-bordered table-hover text_align" width="100%">
								<thead>
									<tr>
										<th>ID</th>
										<th>key</th>
										<th>值</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>

								</tbody>
							</table>
						</div>
						<!--表格end-->
					</div>
				</div>
			</div>
		</div>
		<!-- Footer-->
		<!--主要内容结束-->

		<!-- 放页脚  开始-->
		<jsp:include flush="true" page="../common/foot.div.jsp"></jsp:include>
		<!-- 放页脚  结束-->
	</div>
	
	<!--新增员工弹框-->
	<div class="modal fade hmodal-success form-row" id="myModal7" tabindex="-1" role="dialog" aria-hidden="true">
		<form id="argumentForm" action="" method="" class=" form-horizontal formclass">
			<input type="hidden" id="companyId" name="companyId" value="<c:out value='${LOGIN_USER.companyId}' />" />
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="color-line"></div>
					<div class="modal-header">
						<div class="row">
							<div class="col-sm-11">
								<div class="form-group lmaigin">
									<label for="exampleInputName2" class="col-lg-3">KEY值：</label>
									<div class="col-lg-8">
										<input type="text" class="form-control" id="argumentKey" name="argumentKey">
									</div>
									<label class="col-lg-1 hong">*</label>
								</div>
								<div class="form-group">
									<label for="exampleInputName3" class="col-lg-3">VALUE值：</label>
									<div class="col-lg-8">
										<input type="text" class="form-control" id="argumentValue" name="argumentValue">
									</div>
									<label class="col-lg-1 hong">*</label>
								</div>
								<div class="form-group">
									<label for="exampleInputName8" class="col-lg-3">备注：</label>
									<div class="col-lg-8">
										<textarea type="text" class="form-control" id="remark" name="remark"></textarea>
									</div>
								</div>
								<center>
									<button class="btn btn-info" type="button"onClick="saveArgument();">保存</button>
									&nbsp;&nbsp;
									<button class="btn btn-info guanbi" type="button">取消</button>
								</center>
							</div>
						</div>
					</div>
				</div>
			</div>
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
	
	<!-- for editable -->
	<script src="static/bootstrap/vendor/moment/moment.js"></script>
	<script src="static/bootstrap/vendor/xeditable/bootstrap3-editable/js/bootstrap-editable.min.js"></script>
	<!-- for datatable -->
	<script src="static/bootstrap/vendor/datatables/media/js/jquery.dataTables.min.js"></script>
	<script src="static/bootstrap/vendor/datatables_plugins/integration/bootstrap/3/dataTables.bootstrap.min.js"></script>
	<!-- alert -->
	<script src="static/bootstrap/vendor/sweetalert/lib/sweet-alert.min.js"></script>
	<!-- App scripts -->
	<script src="static/bootstrap/scripts/homer.js"></script>
	<script>
	
	var keys = [];
	<%
	List<String> list = (List)request.getAttribute("keys");
	for (String key : list) {%>
		keys.push("<%=key%>");
	<% } %>    
	
    	$(document).ready(function() {
            $.fn.editable.defaults.mode = 'inline';
    	    $('#example2').DataTable( {
    	    	"dom":
    	   	   		"<'row'<'col-sm-6'l><'col-sm-5'f><'col-sm-1'<'toolbar'>>>" +
    	   	   		"<'row'<'col-sm-12 table-responsive'tr>>" +
    	   	   		"<'row'<'col-sm-5'i><'col-sm-7'p>>",
    	   	   	drawCallback:function(){
    				$('.editable').editable({
    					validate: function(value) {
    		            	if($.trim(value) == ''){ 
    		                	return 'This field is required';
    		                }else{
    		                	var id = $(this).attr("aid");
    		                	var flag = $(this).attr("flag");
    		                	var origKey = $(this).attr("origKey");
    		                	var argumentKey = null;
    		                	var argumentValue = null;
    		                	if(flag == "key"){
	   		                		argumentKey = value;
	    		                	for(var i=0;i<keys.length;i++){
	    		                		if(keys[i] == argumentKey && origKey != argumentKey){
	    		            				return "KEY值已存在:"+keys[i];
	    		            			}else if(origKey == argumentKey){
	    		            				return;
	    		            			}
	    		                	}
    		               		}
    		                	else if(flag == "value"){
    		                		argumentValue = value;
    							}
    		                	$.ajax({
    		            			cache : true,
    		            			type : "POST",
    		            			url : "system/updateArgument",
    		            			data : {"id":id, "argumentKey":argumentKey, "argumentValue":argumentValue},
    		            			dataType : 'json',
    		            			async : false,
    		            			success : function(data) {
    		            				if(argumentKey != null){
	    		            				for(var i=0;i<keys.length;i++){
	    		            					if(keys[i] == origKey){
	    		            						keys[i] = argumentKey;
	    		            					}
	    		            				}
    		            				}
    		            			}
    		            		});
    		                }
    		            }
              		});
    	   	   	},

    	   	   		
    	    	"processing": true,
    	        "serverSide": true,
    	        
    			"columns":[
		             {"data":"id"},
		             {"render":function render( data, type, row, meta ){
		             	<% if(RequestUtil.hasPower("index_parameter_ap")){ %>
		            	return "<a flag=\"key\" aid=\""+row.id+"\" origKey=\""+row.argumentKey+"\" data-type=\"text\" data-pk=\"1\" data-placement=\"right\" data-placeholder=\"Required\" class=\"editable editable-click\">"+row.argumentKey+"</a>";
		            	<% }else{ %>
	                    return row.argumentKey;
	                    <% } %>
		             }},
		             {"render":function render( data, type, row, meta ){
		             	<% if(RequestUtil.hasPower("index_parameter_ap")){ %>
			            return "<a flag=\"value\" aid=\""+row.id+"\" data-type=\"text\" data-pk=\"1\" data-placement=\"right\" data-placeholder=\"Required\" class=\"editable editable-click\">"+row.argumentValue+"</a>";
			            <% }else{ %>
	                    return row.argumentValue;
	                    <% } %>
		             }},
		             {"render":function render( data, type, row, meta ){
		             	<% if(RequestUtil.hasPower("index_parameter_mp")){ %>
			            return "<button class=\"btn btn-danger  btn-xs demo4\" type=\"button\" onClick=\"delArgument("+row.id+");\"><i class=\"fa fa-trash-o\"></i></button>"; 
			            <% }else{ %>
			            return '';
			            <% } %>
		             }},
    			 ],
                "language": {
       			 	"search": "过滤:",
       			 	"processing":"数据加载中",
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
       			 },   
       			
    	        "ajax": {
    	            "url": "system/argument.do",
      	            "type":"POST",
      	            "data": function ( d ) {
      	            	d.data = "aaa";
      	            } 
    	        }
    	    });
    	});


</script>
<script>
    function saveArgument() {
		var argumentKey = $("#argumentKey").val();
		var argumentValue = $("#argumentValue").val();
		if (argumentKey == '') {
			alert("请输入KEY值");
			return false;
		}
		if (argumentValue == '') {
			alert("请输入VALUE值");
			return false;
		}
		for(var i=0;i<keys.length;i++){
			if(keys[i] == $("#argumentKey").val()){
				alert("KEY值已存在:"+keys[i]);
				return false;
			}
		}
		$.ajax({
			cache : true,
			type : "POST",
			url : "system/addArgument",
			data : $('#argumentForm').serialize(),
			dataType : 'json',
			async : false,
			success : function(data) {
				alert(data.msg);
				if (data.result = true) {
					$("#argumentKey").val('');
					$("#argumentValue").val('');
					$("#remark").val('');
					location.reload();
				}
			}
		});
	}
    
    function delArgument(id){
        swal({
                    title: "确定要删除此数据吗?",
                    text: "注意：该数据删除后将不可恢复!",
                    type: "warning",
                    showCancelButton: true,
                    confirmButtonColor: "#DD6B55",
                    confirmButtonText: "是,请删除该数据!",
                    cancelButtonText: "不, 放弃此操作!",
                    closeOnConfirm: false,
                    closeOnCancel: false },
                function (isConfirm) {
                    if (isConfirm) {
                   	 $.ajax({
                			cache : true,
                			type : "POST",
                			url : "system/delArgument",
                			data : {"id":id},
                			dataType : 'json',
                			async : false,
                			success : function(data) {
                				 if (data.result = true) {
                                 	swal("删除成功!", "该数据已经被删除.", "success");
                    				$('.confirm').click(function(){
                    					location.reload();
                    				});
                            	 }else{
                            		 swal("删除失败!", "数据未删除.", "error");
                            	 }
                			}
                		});
                    } else {
                        swal("已取消", "数据未删除。你这逗我玩呢", "error");
                    }
                });
	}

</script>
<script>
  $(".guanbi").click(function(){
	  
	 $("#myModal7").modal('hide');
	 
  })
</script>
</body>
</html>