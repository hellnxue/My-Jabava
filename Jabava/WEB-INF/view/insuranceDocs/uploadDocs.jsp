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
<link rel="stylesheet" href="static/js/plugins/form.validation/css/formValidation.css">
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
							<li class="active">
								<a data-toggle="tab" href="#uploadInfo">修改信息</a>
							</li>
							<li>
								<a href="insuranceDocs/payManagementDocs">补缴管理</a>
							</li>
							<li>
								<a href="insuranceDocs/payDetailDocs">缴费明细</a>
							</li>
						</ul>
						<div class="tab-content">
							<div id="uploadInfo" class="tab-pane active">
								<div class="panel-body">
									<div class="row m-b-md">
										<div class="col-lg-12 m-b text-info">
											<h5>个人基础信息</h5>
										</div>
										<div class="col-xs-4">
											<div class="form-group">
												<label for="" class="control-label text-right col-xs-4">工号：</label>
												<div class="col-xs-8">
													<div class="input-group-static">
														<p></p>
													</div>
												</div>
											</div>
										</div>
										<div class="col-xs-4">
											<div class="form-group">
												<label for="" class="control-label text-right col-xs-4">姓名：</label>
												<div class="col-xs-8">
													<div class="input-group-static">
														<p></p>
													</div>
												</div>
											</div>
										</div>
										<div class="col-xs-4">
											<div class="form-group">
												<label for="" class="control-label text-right col-xs-4">所属部门：</label>
												<div class="col-xs-8">
													<div class="input-group-static">
														<p></p>
													</div>
												</div>
											</div>
										</div>
										<div class="col-xs-4">
											<div class="form-group">
												<label for="" class="control-label text-right col-xs-4">证件号码：</label>
												<div class="col-xs-8">
													<div class="input-group-static">
														<p></p>
													</div>
												</div>
											</div>
										</div>
										<div class="col-xs-4">
											<div class="form-group">
												<label for="" class="control-label text-right col-xs-4">户籍类型：</label>
												<div class="col-xs-8">
													<div class="input-group-static">
														<p></p>
													</div>
												</div>
											</div>
										</div>
										<div class="col-xs-4">
											<div class="form-group">
												<label for="" class="control-label text-right col-xs-4">入职时间：</label>
												<div class="col-xs-8">
													<div class="input-group-static">
														<p></p>
													</div>
												</div>
											</div>
										</div>
										<div class="col-xs-4">
											<div class="form-group">
												<label for="" class="control-label text-right col-xs-4">基本工资：</label>
												<div class="col-xs-8">
													<div class="input-group-static">
														<p></p>
													</div>
												</div>
											</div>
										</div>
									</div>

									<form class="form-horizontal" data-form="staffdocs" autocomplete="off">
										<div class="row m-b-md" >
											<fieldset>
												<legend>
													<div class="col-lg-12 m-b text-info">
														<h5>
															<label class="checkbox-inline">
																<input type="checkbox" data-checkbox-target="paySocialSecurity" checked="checked">
																是否缴纳社保
															</label>
														</h5>
													</div>
												</legend>
												<div class="row" data-box="paySocialSecurity">
													<div class="col-lg-12">
														<div class="col-xs-4">
															<div class="form-group">
																<label for="" class="control-label col-xs-4">社保账户：</label>
																<div class="col-xs-8">
																	<select class="form-control" name="">
																		<option value="">上海总公司</option>
																		<option value="">广州分公司</option>
																		<option value="">北京分公司</option>
																		<option value="">杭州分公司</option>
																		<option value="">江苏分公司</option>
																	</select>
																</div>
															</div>
														</div>
														<div class="col-xs-4">
															<div class="form-group">
																<label for="" class="control-label col-xs-4">社保账号：</label>
																<div class="col-xs-8">
																	<input type="text" class="form-control" name="socialSecurityAccounts">
																</div>
															</div>
														</div>
														<div class="col-xs-4">
															<div class="form-group">
																<label for="" class="control-label col-xs-4">缴纳方式：</label>
																<div class="col-xs-8">
																	<select class="form-control" name="">
																		<option value="">新开</option>
																		<option value="">续缴</option>
																	</select>
																</div>
															</div>
														</div>
														<div class="col-xs-4">
															<div class="form-group">
																<label for="" class="control-label col-xs-4">参保类型：</label>
																<div class="col-xs-8">
																	<select class="form-control" name="">
																		<option value="">上海(城保,五险)社保</option>
																		<option value="">续缴</option>
																	</select>
																</div>
															</div>
														</div>
														<div class="col-xs-4">
															<div class="form-group">
																<label for="" class="control-label col-xs-4">起缴月：</label>
																<div class="col-xs-8">
																	<div class="input-group date">
																		<input type="text" class="form-control" name="">
																		<span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
																	</div>
																</div>
															</div>
														</div>
														<div class="col-xs-4">
															<div class="form-group">
																<label for="" class="control-label col-xs-4">办理月：</label>
																<div class="col-xs-8">
																	<div class="input-group date">
																		<input type="text" class="form-control" name="">
																		<span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
																	</div>
																</div>
															</div>
														</div>
														<div class="col-xs-4">
															<div class="form-group">
																<label for="" class="control-label col-xs-4">停办月：</label>
																<div class="col-xs-8 no-gutter">
																	<div class="col-xs-6">
																		<select class="form-control">
																			<option value="">当月收费</option>
																			<option value="">当月不收费</option>
																		</select>
																	</div>
																	<div class="col-xs-6">
																		<div class="input-group date">
																			<input type="text" class="form-control" name="">
																			<span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
																		</div>
																	</div>
																</div>
															</div>
														</div>
													</div>
													<div class="col-lg-12">
														<div class="col-xs-4">
															<div class="form-group">
																<label for="" class="control-label col-xs-4">社保基数：</label>
																<div class="col-xs-8">
																	<input type="text" class="form-control">
																</div>
															</div>
														</div>
													</div>
													<div class="col-lg-12">
														<div class="col-xs-4">
															<div class="form-group">
																<label for="" class="control-label col-xs-4">养老保险：</label>
																<div class="col-xs-8">
																	<input type="text" class="form-control">
																</div>
															</div>
														</div>
														<div class="col-xs-4">
															<div class="form-group">
																<label for="" class="control-label col-xs-4">医疗保险：</label>
																<div class="col-xs-8">
																	<input type="text" class="form-control">
																</div>
															</div>
														</div>
														<div class="col-xs-4">
															<div class="form-group">
																<label for="" class="control-label col-xs-4">失业保险：</label>
																<div class="col-xs-8">
																	<input type="text" class="form-control">
																</div>
															</div>
														</div>
														<div class="col-xs-4">
															<div class="form-group">
																<label for="" class="control-label col-xs-4">工伤保险：</label>
																<div class="col-xs-8">
																	<input type="text" class="form-control">
																</div>
															</div>
														</div>
														<div class="col-xs-4">
															<div class="form-group">
																<label for="" class="control-label col-xs-4">生育保险：</label>
																<div class="col-xs-8">
																	<input type="text" class="form-control">
																</div>
															</div>
														</div>
													</div>
													<div class="col-lg-12">
														<div class="col-lg-12 m-b m-l text-info">
															<h5>
																<label class="checkbox-inline">
																	<input type="checkbox" data-checkbox-target="recordedSingle">
																	是否使用小秘书录单
																	<small>目前仅适用于北京，使用前请填写定点医院信息.</small>
																</label>
															</h5>
														</div>
														<div class="row" data-box="recordedSingle">
															<div class="col-lg-12">
																<div class="col-xs-4">
																	<div class="form-group">
																		<label for="" class="control-label col-xs-4">定点医院1：</label>
																		<div class="col-xs-8">
																			<input type="text" class="form-control" name="hospital">
																		</div>
																	</div>
																</div>
																<div class="col-xs-4">
																	<div class="form-group">
																		<label for="" class="control-label col-xs-4">定点医院2：</label>
																		<div class="col-xs-8">
																			<input type="text" class="form-control">
																		</div>
																	</div>
																</div>
																<div class="col-xs-4">
																	<div class="form-group">
																		<label for="" class="control-label col-xs-4">定点医院4：</label>
																		<div class="col-xs-8">
																			<input type="text" class="form-control">
																		</div>
																	</div>
																</div>
																<div class="col-xs-4">
																	<div class="form-group">
																		<label for="" class="control-label col-xs-4">定点医院4：</label>
																		<div class="col-xs-8">
																			<input type="text" class="form-control">
																		</div>
																	</div>
																</div>
																<div class="col-xs-4">
																	<div class="form-group">
																		<label for="" class="control-label col-xs-4">定点医院5：</label>
																		<div class="col-xs-8">
																			<input type="text" class="form-control">
																		</div>
																	</div>
																</div>
															</div>
														</div>
													</div>
												</div>
											</fieldset>
										</div>

										<div class="row m-b-md">
											<fieldset>
												<legend>
													<div class="col-lg-12 m-b text-info">
														<h5>
															<label class="checkbox-inline">
																<input type="checkbox" data-checkbox-target="payProvidentFund">
																是否缴纳公积金
															</label>
														</h5>
													</div>
												</legend>
												<div class="row" data-box="payProvidentFund">
													<div class="col-lg-12">
														<div class="col-xs-4">
															<div class="form-group">
																<label for="" class="control-label col-xs-4">公积金账户：</label>
																<div class="col-xs-8">
																	<select class="form-control">
																		<option value="">上海总部</option>
																		<option value="">广州分公司</option>
																		<option value="">北京分公司</option>
																		<option value="">杭州分公司</option>
																		<option value="">江苏分公司</option>
																	</select>
																</div>
															</div>
														</div>
														<div class="col-xs-4">
															<div class="form-group">
																<label for="" class="control-label col-xs-4">公积金账号：</label>
																<div class="col-xs-8">
																	<input type="text" class="form-control" name="ProvidentFundAccounts">
																</div>
															</div>
														</div>
														<div class="col-xs-4">
															<div class="form-group">
																<label for="" class="control-label col-xs-4">缴纳方式：</label>
																<div class="col-xs-8">
																	<select class="form-control">
																		<option value="">新开</option>
																		<option value="">续缴</option>
																	</select>
																</div>
															</div>
														</div>
														<div class="col-xs-4">
															<div class="form-group">
																<label for="" class="control-label col-xs-4">参保类型：</label>
																<div class="col-xs-8">
																	<select class="form-control">
																		<option value="">上海住房公积金</option>
																	</select>
																</div>
															</div>
														</div>
														<div class="col-xs-4">
															<div class="form-group">
																<label for="" class="control-label col-xs-4">起缴月：</label>
																<div class="col-xs-8">
																	<div class="input-group date">
																		<input type="text" class="form-control" name="">
																		<span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
																	</div>
																</div>
															</div>
														</div>
														<div class="col-xs-4">
															<div class="form-group">
																<label for="" class="control-label col-xs-4">办理月：</label>
																<div class="col-xs-8">
																	<div class="input-group date">
																		<input type="text" class="form-control" name="">
																		<span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
																	</div>
																</div>
															</div>
														</div>
														<div class="col-xs-4">
															<div class="form-group">
																<label for="" class="control-label col-xs-4">停办月：</label>
																<div class="col-xs-8 no-gutter">
																	<div class="col-xs-6">
																		<select class="form-control">
																			<option value="">当月收费</option>
																			<option value="">当月不收费</option>
																		</select>
																	</div>
																	<div class="col-xs-6">
																		<div class="input-group date">
																			<input type="text" class="form-control" name="">
																			<span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
																		</div>
																	</div>
																</div>
															</div>
														</div>
														<div class="col-xs-4">
															<div class="form-group">
																<label for="" class="control-label col-xs-4">公积金基数：</label>
																<div class="col-xs-8">
																	<input type="text" class="form-control">
																</div>
															</div>
														</div>
													</div>
												</div>
											</fieldset>
										</div>
										<div class="row">
											<div class="col-lg-12 text-center">
												<button class="btn btn-info" type="submit">确认参保并返回</button>
											</div>
										</div>
									</form>

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
	
	<!-- 表单验证 -->
	<script src="static/js/plugins/form.validation/js/formValidation.js"></script>
	<script src="static/js/plugins/form.validation/js/framework/bootstrap.js"></script>
	<script src="static/js/plugins/form.validation/js/language/zh_CN.js"></script> 
	<!-- App scripts -->
	<script src="static/bootstrap/scripts/homer.js"></script>
	<script src="static/bootstrap/scripts/charts.js"></script>

	<script>


		$(function(){
			var validateOptions = {
				err: {
				container: 'tooltip'
				},
				icon: {
					valid: 'glyphicon glyphicon-ok',
					invalid: 'glyphicon glyphicon-remove',
					validating: 'glyphicon glyphicon-refresh'
				},
				locale: 'zh_CN',
				fields: {
					socialSecurityAccounts: {
						enabled: true,
						validators: {
							notEmpty: {
								message: '请填写必填项目'
							}
						}
					},
					hospital: {
						enabled: false,
						validators: {
							notEmpty: {
								message: '请填写必填项目'
							}
						}
					},
					ProvidentFundAccounts: {
						enabled: false,
						validators: {
							notEmpty: {
								message: '请填写必填项目'
							}
						}
					}
				}
			};

			$('[data-form="staffdocs"]')
			.on('init.form.fv', function(e, data) {
				var getCheckbox = $('[data-checkbox-target]');
					getCheckbox.each(function(index, el){
						if(!$(this).prop('checked')){
							var getCheckboxTarget = $(this).data('checkboxTarget')
							$('[data-box='+getCheckboxTarget+']').hide();
						}
					});
			})
			.formValidation(validateOptions)
			.on('change','[data-checkbox-target]',function(e){
				var getCheckbox = $(this),
					getCheckboxTarget = getCheckbox.data('checkboxTarget'),
					getBox = $('[data-box='+getCheckboxTarget+']'),
					fv = $('[data-form="staffdocs"]').data('formValidation');

				console.log(fv)
				if(getCheckbox.prop('checked')){
					getBox.show();
					switch(getCheckboxTarget){
						case 'paySocialSecurity':
							fv
							.enableFieldValidators('socialSecurityAccounts',true)
							.revalidateField('socialSecurityAccounts');
							break;
						case 'recordedSingle':
							fv
							.enableFieldValidators('hospital',true)
							.revalidateField('hospital');
							break;
						case 'payProvidentFund':
							fv
							.enableFieldValidators('ProvidentFundAccounts',true)
							.revalidateField('ProvidentFundAccounts');
							break;
					}
				} else{
					switch(getCheckboxTarget){
						case 'paySocialSecurity':
							fv
							.enableFieldValidators('socialSecurityAccounts',false)
							.revalidateField('socialSecurityAccounts');
							break;
						case 'recordedSingle':
							fv
							.enableFieldValidators('hospital',false)
							.revalidateField('hospital');
							break;
						case 'payProvidentFund':
							fv
							.enableFieldValidators('ProvidentFundAccounts',false)
							.revalidateField('ProvidentFundAccounts');
							break;
					}
					getBox.hide()
				};
				
			})
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