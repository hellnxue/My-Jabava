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
<title>员工社保档案</title>
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
	<div id="wrapper">
		<div class="content animate-panel">
			<div class="row">
				<div class="col-lg-12">
					<div class="hpanel">
						<div class="panel-heading">
							<h4 class="text-center font-bold">
								员工社保档案
							</h4>
						</div>
						
						<ul role="tabList" class="nav nav-tabs" id="">
							<li>
								<a href="insuranceDocs/uploadDocs">修改信息</a>
							</li>
							<li class="active">
								<a data-toggle="tab" href="#payManage">补缴管理</a>
							</li>
							<li>
								<a href="insuranceDocs/payDetailDocs">缴费明细</a>
							</li>
						</ul>
						<div class="tab-content">
							<div id="payManage" class="tab-pane active">
								<div class="panel-body">
									<div class="row m-b">
										<div class="col-lg-12">
											<h5 class="text-info"></h5>
											<p></p>
										</div>
										<div class="col-lg-12">
											<div class="col-lg-3">
												<div class="form-group">
													<label for="" class="control-label text-right col-xs-4 col-sm-4 col-md-4 col-lg-4 m-t-xs">工号：</label>
													<div class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
														<select class="form-control">
															<option value=""></option>
														</select>
													</div>
												</div>
											</div>
											<div class="col-lg-9 m-t">
												<a class="m-r-md" href="javascript:;" data-target="insuranceType" data-delate="insuranceAll"><i class="fa fa-plus"></i>添加保险类型</a>
												<a href="javascript:;" data-target="insuranceAll" data-delate="insuranceType"><i class="fa fa-plus"></i>添加全部保险</a>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-lg-12 m-b">
											<table id="payManamentTable" class="table table-condensed table-hover table-bordered dataTable" width="100%">
												<thead>
													<th>保险类型</th>
													<th>补缴起始月</th>
													<th>补缴结束月</th>
													<th>企业补缴金额</th>
													<th>个人补缴金额</th>
													<th>补缴说明</th>
													<th>企业滞纳金</th>
													<th>个人滞纳金</th>
													<th>操作</th>
												</thead>
												<tbody class="hidden" data-panel="insuranceType">
													<tr>
														<td>
															<select class="form-control" name="">
																<option value="">养老保险</option>
																<option value="">医疗保险</option>
																<option value="">失业保险</option>
																<option value="">工伤保险</option>
																<option value="">生育保险</option>
																<option value="">住房公积金</option>
															</select>
														</td>
														<td>
															<input class="form-control" type="text">
														</td>
														<td>
															<input class="form-control" type="text">
														</td>
														<td>
															<input class="form-control" type="text">
														</td>
														<td>
															<input class="form-control" type="text">
														</td>
														<td>
															<input class="form-control" type="text">
														</td>
														<td>
															<input class="form-control" type="text">
														</td>
														<td>
															<input class="form-control" type="text">
														</td>
														<td>
															<button type="buttton" class="btn btn-danger" data-action="remove">删除</button>
														</td>
													</tr>
												</tbody>
												<tbody class="hidden" data-panel="insuranceAll">
													<tr>
														<td>养老保险</td>
														<td>
															<input class="form-control" type="text">
														</td>
														<td>
															<input class="form-control" type="text">
														</td>
														<td>
															<input class="form-control" type="text">
														</td>
														<td>
															<input class="form-control" type="text">
														</td>
														<td>
															<input class="form-control" type="text">
														</td>
														<td>
															<input class="form-control" type="text">
														</td>
														<td>
															<input class="form-control" type="text">
														</td>
														<td>
															<button type="buttton" class="btn btn-danger" data-action="del">删除</button>
														</td>
													</tr>
													<tr>
														<td>医疗保险</td>
														<td>
															<input class="form-control" type="text">
														</td>
														<td>
															<input class="form-control" type="text">
														</td>
														<td>
															<input class="form-control" type="text">
														</td>
														<td>
															<input class="form-control" type="text">
														</td>
														<td>
															<input class="form-control" type="text">
														</td>
														<td>
															<input class="form-control" type="text">
														</td>
														<td>
															<input class="form-control" type="text">
														</td>
														<td>
															<button type="buttton" class="btn btn-danger" data-action="del">删除</button>
														</td>
													</tr>
													<tr>
														<td>失业保险</td>
														<td>
															<input class="form-control" type="text">
														</td>
														<td>
															<input class="form-control" type="text">
														</td>
														<td>
															<input class="form-control" type="text">
														</td>
														<td>
															<input class="form-control" type="text">
														</td>
														<td>
															<input class="form-control" type="text">
														</td>
														<td>
															<input class="form-control" type="text">
														</td>
														<td>
															<input class="form-control" type="text">
														</td>
														<td>
															<button type="buttton" class="btn btn-danger" data-action="del">删除</button>
														</td>
													</tr>
													<tr>
														<td>工伤保险</td>
														<td>
															<input class="form-control" type="text">
														</td>
														<td>
															<input class="form-control" type="text">
														</td>
														<td>
															<input class="form-control" type="text">
														</td>
														<td>
															<input class="form-control" type="text">
														</td>
														<td>
															<input class="form-control" type="text">
														</td>
														<td>
															<input class="form-control" type="text">
														</td>
														<td>
															<input class="form-control" type="text">
														</td>
														<td>
															<button type="buttton" class="btn btn-danger" data-action="del">删除</button>
														</td>
													</tr>
													<tr>
														<td>生育保险</td>
														<td>
															<input class="form-control" type="text">
														</td>
														<td>
															<input class="form-control" type="text">
														</td>
														<td>
															<input class="form-control" type="text">
														</td>
														<td>
															<input class="form-control" type="text">
														</td>
														<td>
															<input class="form-control" type="text">
														</td>
														<td>
															<input class="form-control" type="text">
														</td>
														<td>
															<input class="form-control" type="text">
														</td>
														<td>
															<button type="buttton" class="btn btn-danger" data-action="del">删除</button>
														</td>
													</tr>
													<tr>
														<td>住房公积金</td>
														<td>
															<input class="form-control" type="text">
														</td>
														<td>
															<input class="form-control" type="text">
														</td>
														<td>
															<input class="form-control" type="text">
														</td>
														<td>
															<input class="form-control" type="text">
														</td>
														<td>
															<input class="form-control" type="text">
														</td>
														<td>
															<input class="form-control" type="text">
														</td>
														<td>
															<input class="form-control" type="text">
														</td>
														<td>
															<button type="buttton" class="btn btn-danger" data-action="del">删除</button>
														</td>
													</tr>
												</tbody>
											</table>
										</div>
										<div class="col-lg-12 text-right">
											<button class="btn btn-default">取　消</button>
											<button class="btn btn-info">确　认</button>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<!-- 放页脚  开始-->
		<jsp:include flush="true" page="../common/foot.div.jsp"></jsp:include>
		<!-- 放页脚  结束-->
	</div>
	<!-- 放主要内容  结束-->

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
			//添加与删除
			$('[data-target]').on('click',function(e){
				var getAddTarget = $(this).attr('data-target');
				var getTemplate = $('[data-panel="'+getAddTarget+'"]').clone(true, true)
					.removeClass('hidden')
					.removeAttr('data-panel')
					.attr('data-panel-rel', getAddTarget)

				getTemplate.find('button').bind('click', function(event) {
					if(getAddTarget=='insuranceType'){
						$(this).parents('tbody').remove();
					} else{
						$(this).parents('tr').remove();
					}
				});

				$('[data-panel-rel]').not('[data-panel-rel="'+getAddTarget+'"]').remove()
				$('[data-panel='+getAddTarget+']').after(getTemplate)

			});
		})


		//日期
		$('.input-group.date')
		.datepicker({
			format: "yyyy-mm-dd",
			language: "zh-CN",
			autoclose: true
		})
	</script>
</body>
</html>