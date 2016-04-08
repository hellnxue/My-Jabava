<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge">

<!-- Page title -->
<title>Jabava V1.0 | 参数设置</title>
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
	<!-- Main Wrapper -->
	<div id="wrapper">
		<div class="normalheader transition animated fadeIn small-header">
			<div class="hpanel">
				<div class="panel-body">
					<div id="hbreadcrumb" class="pull-right m-t-lg">
						<ol class="hbreadcrumb breadcrumb">
							<li><a href="to_index?jump=1">首页</a></li>
							<li><span>系统管理</span></li>
							<li class="active"><span>参数设置</span></li>
						</ol>
					</div>
					<h2 class="font-light m-b-xs">参数设置</h2>
					<small>待&nbsp;定</small>
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
								<button class="btn btn-success btn-xs" type="button"
								data-target="#myModal7" data-toggle="modal">
								<i class="fa fa-group"></i> <span class="bold">新增参数</span>
							</div>
							系统参数配置
							<div></div>
						</div>
						<!--表格start-->
						<div class="panel-body">
							<table id="example2" class="table table-striped table-bordered table-hover text_align" width="100%">
								<thead>
									<tr>
										<th>ID</th>
										<th>key</th>
										<th>值</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="arguments" items="${arguments}" varStatus="index">
									<tr>
										<td><c:out value='${index.index+1}' /></td>
										<td>
											<c:out value='${arguments.argumentKey}' />
										</td>
										<td>
											<c:out value='${arguments.argumentValue}' />
										</td>
										<th>
											<button class="btn btn-danger btn-xs demo4 " type="button" onClick="delArgument('<c:out value='${arguments.id}' />');">
											<i class="fa fa-trash-o"></i>
										</button>
									</th>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<!--表格end-->
			</div>
		</div>
	</div>
</div>
<!--新增员工弹框-->
<div class="modal fade hmodal-success form-row" id="myModal7" tabindex="-1" role="dialog" aria-hidden="true">
	<form id="argumentForm" action="" method="">
		<input type="hidden" id="companyId" name="companyId" value="<c:out value='${companyId}' />" />
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="color-line"></div>
				<div class="modal-header">
					<div class="row">
						<div class="col-sm-11">
							<form role="form" class=" form-horizontal formclass">
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
									<button class="btn btn-info" type="button" onClick="saveArgument();">保存</button>
									&nbsp;&nbsp;
									<button class="btn btn-info guanbi" type="button">取消</button>
								</center>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>

	</form>
</div>
</body>
</html>

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

<!--取消效果-->
<script>
	$(".guanbi").click(function() {
		$("#myModal7").modal('hide');
	})

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
                                  	location.reload();
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
	$(function() {
		$('#example2').dataTable({
			"language" : {
				"search" : "过滤:",
				"infoEmpty" : "No entries to show"
			}
		})
	});
</script>
