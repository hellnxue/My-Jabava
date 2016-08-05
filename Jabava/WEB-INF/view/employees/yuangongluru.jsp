<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<base href="${pageContext.request.contextPath}/">
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0;" name="viewport"/>
		<meta content="yes" name="apple-mobile-web-app-capable" />
		<meta content="black" name="apple-mobile-web-app-status-bar-style" />
		<meta content="telephone=no" name="format-detection" />

		<title>员工录入界面</title>
		<jsp:include flush="true" page="../common/styles.jsp"></jsp:include>
		<link rel="stylesheet" href="static/js/plugins/form.validation/css/formValidation.css">
		<link rel="stylesheet" id="link" href="static/css/denglu.css" type="text/css">

		<script>
			var username = '${userName}';
			var personId = '${personId}';
			console.log("====================================");
			console.log("personId:" + personId);
			console.log("userName:" + username);
			var path = '${pageContext.request.contextPath}';
			
			//console.log
		</script>
	</head>
	<body class="blank" style="position:static;">
		<div class="splash">
			<div class="color-line"></div>
			<div class="splash-title">
				<h1>Jabava V1.0 </h1>
				<p>属于你的专业人事专员</p>
				<img src="static/bootstrap/images/loading-bars.svg" width="64" height="64" />
			</div>
		</div>

		<!-- 员工信息录入 -->
		<div class="register-container containers" id="zy-mobile-main" data-container="index">
			<div class="row">
				<div class="col-md-12">
					<div class="hpanel">
						<div class="panel-body">
							<div class="row">
								<!--头部-->
								<div class="tuichu">
									<h5><a href="employee/login_mobile"><img src="static/img/u4.png" width="20" height="20"></a><span>退出</span></h5>
								</div>
								<!--个人信息-->
								<div class="inform">
									<div class="inleft">
										<div class="imgs">
											<a href="javascript:;"><img src="static/img/u23.png" width="100" height="100"></a>
										</div>
										<div class="xinxi">
											<p><h5 id="mobileUserName"></h5></p>
											<p class="qi" id="mobilePhone"></p>
											<p class="qi" id="mobileEmail"></p>
										</div>
									</div>
									<div class="inright">
										<a href="javascript://" data-target="[data-container=personalInfo]" data-relatedTarget="[data-container=index]" data-toggle="container-switch"><img src="static/img/u327.png" width="41" height="39"></a>
									</div>
								</div>
								<!--inform end-->
							</div>
						</div>
					</div>
				</div>
			</div>
			<!--列表-->
			<div class="liebiao">
				<ul>
					<li>
						<a href="javascript://" id="button-person" data-target="[data-container=personalInfo]" data-relatedTarget="[data-container=index]" data-toggle="container-switch"><span >+</span>个人信息</a>
					</li>
					<li>
						<a href="javascript://" id="button-attach" data-target="[data-container=upload]" data-relatedTarget="[data-container=index]" data-toggle="container-switch"><span >+</span>上传附件</a>
					</li>
				</ul>
			</div>
		</div>
		<!-- 个人信息 -->
		<div class="register-container containers" id="zy-mobile-person" data-container="personalInfo">
			<div class="row">
				<div class="col-md-12">
					<div class="hpanel">
						<div class="panel-body">
							<div class="row">
								<form role="form" id="baseInfoForm"  class="form-horizontal formclass" data-form-validator="personal" data-add-url="employee/updatePerson"  data-form="personalInfo">
									<div class="blue">
										<h5>
										<a href="javascript://" class="pull-right m-r-md" data-target="[data-container=index]" data-relatedTarget="[data-container=personalInfo]" data-toggle="container-switch">
											<span class="gereninform">关闭</span>
										</a>
										<a class="modalHide" data-target="[data-container=index]" data-relatedTarget="[data-container=personalInfo]" data-toggle="container-switch">
										<img src="static/img/u4.png" width="20" height="20"></a>
										<span class="gereninform">个人信息</span>
										</h5>
									</div>

									<div class="modal-header">
										<div class="row">
											<div class="panel-body grxx" style=" background:none;">
												<div class="form-group">
													<label for="" class="col-xs-4 col-sm-4">联系电话：</label>
													<div class="col-xs-8 col-sm-8">
														<input id="zy-mobile-phone" type="text" class="form-control"  name="phone">
													</div>
												</div>
												<div class="form-group">
													<label for="" class="col-xs-4 col-sm-4">出生日期：</label>
													<div class="col-xs-8 col-sm-8">
														<div class="input-group date">
															<input id="zy-mobile-birth-date" type="text" class="form-control displays"   name="birthDate1">
															<span class="input-group-addon "><i class="glyphicon glyphicon-th"></i></span>
														</div>
													</div>
												</div>
												<div class="form-group">
													<label for="" class="col-xs-4 col-sm-4">性　　别：</label>
													<div class="col-xs-8 col-sm-8">
														<select id='zy-mobile-sex' class="form-control " name="sex" placeholder="性别">
															<option value="1">男</option>
															<option value="2">女</option>
														</select>
													</div>
												</div>
												<div class="form-group">
													<label for="" class="col-xs-4 col-sm-4">身份证：</label>
													<div class="col-xs-8 col-sm-8 form-required">
														<input id="zy-mobile-cert-id" type="text" class="form-control"  name="certId">
													</div>
												</div>
												<div class="form-group">
													<label for="" class="col-xs-4 col-sm-4">民　　族：</label>
													<div class="col-xs-8 col-sm-8">
														<input id="zy-mobile-nationality" type="text" class="form-control"   name="nationality">
													</div>
												</div>

												<div class="form-group">
													<label for="" class="col-xs-4 col-sm-4">籍　　贯：</label>
													<div class="col-xs-8 col-sm-8">
														<input id="zy-mobile-original-place" type="text" class="form-control"  name="originPlace">
													</div>
												</div>

												<div class="form-group">
													<label for="" class="col-xs-4 col-sm-4">婚姻状况：</label>
													<div class="col-xs-8 col-sm-8">
														<select id="zy-mobile-marriage-status" class="form-control " name="marital" placeholder="未婚">
														    <option   value="1" selected="true" >未婚</option>
							  								<option  value="2">已婚</option>
														</select>
													</div>
												</div>

												<div class="form-group" style=" display:block">
													<label for="" class="col-xs-4 col-sm-4">户籍类型：</label>
													<div class="col-xs-8 col-sm-8 form-required">
														<select id="zy-mobile-register-type" class="form-control " name="registerType" placeholder="本地城镇">
															<option value="1" selected="true" >本地城镇</option>
															<option value="2">本地农村</option>
															<option value="3">外地城镇</option>
															<option value="4">外地农村</option>
														</select>
													</div>
												</div>

												<div class="form-group">
													<label for="" class="col-xs-4 col-sm-4">户口所在：</label>
													<div class="col-xs-8 col-sm-8">
														<input id="zy-mobile-register-location" type="text" class="form-control" name="registerLocation">
													</div>
												</div>

												<div class="form-group">
													<label for="" class="col-xs-4 col-sm-4">现居住地：</label>
													<div class="col-xs-8 col-sm-8 form-required">
														<input id='zy-mobile-contact-address' type="text" class="form-control" name="contactAddress">
													</div>
												</div>

												<div class="form-group">
													<label for="" class="col-xs-4 col-sm-4">家庭地址：</label>
													<div class="col-xs-8 col-sm-8 form-required">
														<input id="zy-mobile-family-address" type="text" class="form-control" name="familyAddress">
													</div>
												</div>

												<div class="form-group">
													<label for="" class="col-xs-4 col-sm-4">邮　　编：</label>
													<div class="col-xs-8 col-sm-8">
														<input id='zy-mobile-post-code' type="text" class="form-control" name="postCode">
													</div>
												</div>

												<div class="form-group">
													<label for="" class="col-xs-4 col-sm-4">最高学历：</label>
													<div class="col-xs-8 col-sm-8  form-required">
														<select id="zy-mobile-education" class="form-control " name="education">
														</select>
													</div>
												</div>

												<div class="form-group" style=" display:block">
													<label for="" class="col-xs-4 col-sm-4">最高学位：</label>
													<div class="col-xs-8 col-sm-8">
														<select id="zy-mobile-degree" class="form-control" name="degree">
														</select>
													</div>
												</div>

												<div class="form-group">
													<label for="" class="col-xs-4 col-sm-4">档案存放：</label>
													<div class="col-xs-8 col-sm-8">
														<input id="zy-mobile-file-location" type="text" class="form-control" name="fileLocation">
													</div>
												</div>

												<div class="form-group">
													<label for="" class="col-xs-4 col-sm-4">工作时间：</label>
													<div class="col-xs-8 col-sm-8">
														<div class="input-group date">
															<input id="zy-mobile-first-job-time" type="text" class="form-control" name="firstjobDate1">
															<span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
														</div>
													</div>
												</div>
												<div class="form-group">
													<label for="" class="col-xs-4 col-sm-4">是否外籍：</label>
													<div class="col-xs-8 col-sm-8">
														<div class="sex_style" style=" text-align:left">
															<span class="sex sex_third">是</span>
															<input id="zy-mobile-f1" class="new_style_four sex" type="radio" id="optionsRadios1"  name="isForeign" value="1" checked="" />
															<span class="sex sex_four">否</span>
															<input id="zy-mobile-f2" class="new_style_four sex" type="radio" id="optionsRadios2" name="isForeign" value="2" checked="" />
														</div>
													</div>
												</div>
	<!--
												<div class="form-group">
													<label for="" class="col-xs-4 col-sm-4">是否留学：</label>
													<div class="col-xs-8 col-sm-8">
														<div class="sex_style" style=" text-align:left">
															<span class="sex sex_third">是</span>
															<input class="new_style_four sex" type="radio" id="optionsRadios3"  name="studyAbroad" value="1" checked="" />
															<span class="sex sex_four">否</span>
															<input class="new_style_four sex" type="radio" id="optionsRadios4"  name="studyAbroad" value="0" checked="" />
														</div>
													</div>
												</div>
	-->
												<div class="form-group">
													<label for="" class="col-xs-4 col-sm-4">开户行：</label>
													<div class="col-xs-8 col-sm-8">
														<select id="zy-mobile-bank" class="form-control" name="bankName">
														</select>
													</div>
												</div>
												<div class="form-group">
													<label for="" class="col-xs-4 col-sm-4">开户支行：</label>
													<div class="col-xs-8 col-sm-8">
														<input id="zy-mobile-sub-bank" type="text" class="form-control" name="subbank">
													</div>
												</div>
												<div class="form-group">
													<label for="" class="col-xs-4 col-sm-4">工资卡号：</label>
													<div class="col-xs-8 col-sm-8">
														<input id='zy-mobile-salary-card' type="text" class="form-control" name="salaryCard">
													</div>
												</div>
												<center style="clear:both; padding-top:30px;">
													<button type="submit" id="saveBaseInfoButton" class="btn btn-info" data-click-target="onlySave">保存并填写下一项</button>
												</center>
											</div>
										</div>
									</div>
								</form>

							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- 		<div class="register-container containers" id="baseInfoCollect">
			<div class="row">
				<div class="col-lg-12">
						<div class="modal fade hmodal-success form-row" id="myModal7_1" tabindex="-1" role="dialog" data-modal="personalInfo" aria-hidden="true">
								<div class="modal-dialog">
										<div class="modal-content">
										</div>
								</div>
						</div>
				</div>
			</div>
		</div> -->
		<!--上传附件弹框-->
		<div class="register-container containers" id ='zy-mobile-attach' data-container="upload">
			<div class="row">
				<div class="col-md-12">
					<div class="hpanel">
						<div class="panel-body">
							<div class="row">
								<div class="blue">
									<h5>
									<a href="javascript://" class="pull-right m-r-md" data-target="[data-container=index]" data-relatedTarget="[data-container=upload]" data-toggle="container-switch">
										<span class="gereninform">关闭</span>
									</a>
									<a class="modalHide" data-target="[data-container=index]" data-relatedTarget="[data-container=upload]" data-toggle="container-switch">
										<img src="static/img/u4.png" width="20" height="20">
									</a>
									<span class="gereninform">上传附件</span>
									</h5>
								</div>
								<div class="modal-header">
									<div class="row">
										<div class="panel-body fujian" style=" background:none;">
											<form role="form" class="form-horizontal formclass" data-add-url="">
												<div class="form-group">
													<label for="" class="col-xs-4 col-sm-4">身份证：</label>
													<div class="col-xs-8 col-sm-8">
														<input type="file" data-fileid="1" id="certidFileInput" class="form-control-static"  >
													</div>
												</div>
												<div class="form-group">
													<label for="" class="col-xs-4 col-sm-4">户口簿：</label>
													<div class="col-xs-8 col-sm-8">
														<input type="file"  data-fileid="2" class="form-control-static"  >
													</div>
												</div>
												<div class="form-group">
													<label for="" class="col-xs-4 col-sm-4">学历证：</label>
													<div class="col-xs-8 col-sm-8">
														<input type="file"  data-fileid="3" class="form-control-static"  >
													</div>
												</div>
												<div class="form-group">
													<label for="" class="col-xs-4 col-sm-4">简　　历：</label>
													<div class="col-xs-8 col-sm-8">
														<input type="file" data-fileid="4" class="form-control-static"  >
													</div>
												</div>
												<div class="form-group">
													<label for="" class="col-xs-4 col-sm-4 ">离职证明：</label>
													<div class="col-xs-8 col-sm-8">
														<input type="file" data-fileid="5" class="form-control-static"  >
													</div>
												</div>
												<div class="form-group">
													<label for="" class="col-xs-4 col-sm-4 ">健康证明：</label>
													<div class="col-xs-8 col-sm-8">
														<input type="file" data-fileid="6" class="form-control-static"  >
													</div>
												</div>
												<div class="form-group">
													<label for="" class="col-xs-4 col-sm-4">薪资证明：</label>
													<div class="col-xs-8 col-sm-8">
														<input type="file" data-fileid="7" class="form-control-static"  >
													</div>
												</div>
												<center style="clear:both; padding-top:30px;">
												<button type="button" id="zy-save-files" class="btn btn-info" data-click-target="onlySave" data-next-modal="[data-modal=personalInfo]" >保存并退出</button>
												</center>
											</form>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<!-- <div class="fade modal hmodal-success form-row" tabindex="-1" role="dialog" data-modal="upload"  aria-hidden="true" id="attachUploadPane">
			<div class="modal-dialog">
				<div class="modal-content">
				</div>
			</div>
		</div> -->
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
		<script src="static/bootstrap/vendor/select2-3.5.2/select2.min.js"></script>
		<script src="static/bootstrap/vendor/bootstrap-touchspin/dist/jquery.bootstrap-touchspin.min.js"></script>
		<script src="static/bootstrap/vendor/bootstrap-datepicker-master/dist/js/bootstrap-datepicker.js"></script>
		<script src="static/bootstrap/vendor/moment/moment.js"></script>
		<script src="static/bootstrap/vendor/datatables/media/js/jquery.dataTables.min.js"></script>
		<script src="static/bootstrap/vendor/datatables_plugins/integration/bootstrap/3/dataTables.bootstrap.min.js"></script>
		<script src="static/bootstrap/vendor/sweetalert/lib/sweet-alert.min.js"></script>
		<script src="static/js/plugins/form.validation/js/formValidation.js"></script>
		<script src="static/js/plugins/form.validation/js/framework/bootstrap.js"></script>
		<script src="static/js/plugins/form.validation/js/language/zh_CN.js"></script>
		<script src="static/js/plugins/form.validation/js/formValidationExtend.js"></script>
		<script src="static/bootstrap/scripts/homer.js"></script>
		<script src="static/bootstrap/scripts/charts.js"></script>
		<script src="static/js/template.js"></script>
		<script src="static/js/zy-common.js"></script>
		<script src="static/js/apis.js"></script>
		<script>
			var fileArray = [];

			$('#zy-mobile-attach').hide();
			$('#zy-mobile-person').hide();
			$("#zy-mobile-main").show();

			/*$("#button-attach").on('click',function(){
				$('#zy-mobile-attach').show();
				$('#zy-mobile-person').hide();
				$("#zy-mobile-main").hide();
			});

			$("#button-person").on('click',function(){
				$('#zy-mobile-attach').hide();
				$('#zy-mobile-person').show();
				$("#zy-mobile-main").hide();
			});*/

 			$('[data-toggle="container-switch"]').on('click', function(event) {
 				event.preventDefault();
 				var getTarget = $(this).attr('data-target'),
 					getRelatedTarget = $(this).attr('data-relatedTarget');
 				$(getTarget).show();
 				$(getRelatedTarget).hide();
 			});


			// =========== 文件上传的功能 ============
			$('.form-control-static').on('change',function(e){
				var file = e.target.files[0];
				var fd = new FormData();
				fd.append("uploadFile", file);

				var xhr = new XMLHttpRequest();
				var fileId = $(this).data("fileid");

				xhr.upload.addEventListener("progress", function(){
				}, false);
				xhr.addEventListener("load", function(){
				}, false);
				xhr.addEventListener("error", function(){
				}, false);
				xhr.addEventListener("abort", function(){
				}, false);
				xhr.addEventListener("readystatechange",function(){
					if(xhr.readyState != 4) return;
					var data = xhr.responseText;

					var result = JSON.parse(data);
					console.log(result);
					 
					if(!result.success){
						swal(result.msg, "", "error");
					}

					if(result.success == true){
						var obj = {
							personId: personId,
							fileType: fileId,
							filePath: result.fileUrl,
							from:1
						};

						//console.log(result.fileUrl);

						fileArray.push(obj);
					}
				});
				xhr.open("POST", '${pageContext.request.contextPath}'+'/employee/uploadToHRO');
				xhr.send(fd);
			});

			$('#zy-save-files').on('click',function(){
				console.log("===== run the saving ====");
				$.ajax({
					type:"post",
					url:"employee/savePersonFiles",
					data:JSON.stringify(fileArray),
					dataType:"json",
					contentType:"application/json",
					success:function(data){
						if(data.status == 0){
							$('#zy-mobile-attach').hide();
							$('#zy-mobile-main').show();
						}
					}
				});

			});

			/**
			* 将格式为年月日时分秒的格式成年月日
			* @param ymdhhmmss
			* @returns
			*/
			function getHandleDateYMD(ymdhhmmss){
				return ymdhhmmss.split(" ")[0];
			}

			//校验
			var validateOptions = {
			personal: {
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
					familyAddress: {
						validators: {
							notEmpty: {
								message: '请填写必填项目'
							}
						}
					},
					'phone': {
						enabled: false,
						validators: {
							notEmpty: {
								message: '请输入中国区域的手机或电话号码'
							},
							phone: {
		                        country: 'CN',
		                        message: '请输入中国区域的手机或电话号码'
		                    }
						}
					},
					salaryCard: {
						enabled: false,
						validators: {
							notEmpty: {
								message: '请输入有效的银行卡号'
							},
							integer: {
								message: '请输入有效的银行卡号'
							}
						}
					},
					contactAddress: {
						validators: {
							notEmpty: {
								message: '请填写必填项目'
							}
						}
					},
					education: {
						validators: {
							notEmpty: {
								message: '请填写必填项目'
							}
						}
					},
					certId: {
						validators: {
							notEmpty: {
								message: '请填写必填项目'
							},
							callback: {
		                        message: '请输入有效的身份证号',
		                        callback: function(value, validator, $field){
		                            return IDValidator.isValid(value);
		                        }
		                    }
						}
					},
					registerType: {
						validators: {
							notEmpty: {
								message: '请填写必填项目'
							}
						}
					}
				}
			}
			};

			const $saveBaseInfoButton = $('#saveBaseInfoButton');

			function saveBaseInfo(data){
				var jsonObject=JSON.stringify(data);
				$.ajax({
					type:"post",
					url:updateURL,
					data:jsonObject,
					dataType:"json",
					contentType:"application/json",
					success:function(data){
						if(data&&data.success){
							swal(data.msg, "", "success");
							$(addFormSelector).remove();
							containerID.html("");
							//重新渲染
							templateFillData(templateID, containerID, listURL,delsURL);
						}else{
							swal(data.msg, "", "error");
						}
					}
				});
			}

			$(function (){
				////////////////////////////////////////////////////////////////////////
				console.log("===== 来 load 数据  ======");
				$.ajax({
					type: "post",
					url: "employee/mobileBaseData/" + personId,
					//data: JSON.stringify(data),
					contentType:"application/json",
					dataType: "json",
					async: false,
					success:function(data){
						var person = data.person;
						$("#mobileUserName").text(person.employeeName);
						$("#mobilePhone").text(person.mobile);
						$("#mobileEmail").text(person.email);
						/* var $registerType = $('#zy-mobile-register-type');
						$registerType.empty();
						$.each(data.registerType,function(idx,item){
							var $item = $('<option value='+item.baseDataCode+'>'+item.baseDataName+'</option>');
							$registerType.append($item);
						}); */
						var $degree = $('#zy-mobile-degree');
						$degree.empty();
						$.each(data.degree,function(idx,item){
							var $item = $('<option value='+item.baseDataCode+'>'+item.baseDataName+'</option>');
							$degree.append($item);
						});
						var $education = $('#zy-mobile-education');
						$education.empty();
						$.each(data.education,function(idx,item){
							var $item = $('<option value='+item.baseDataCode+'>'+item.baseDataName+'</option>');
							$education.append($item);
						});
						var $bank = $('#zy-mobile-bank');
						$bank.empty();
						$.each(data.bank,function(idx,item){
							var $item = $('<option value='+item.baseDataCode+'>'+item.baseDataName+'</option>');
							$bank.append($item);
						});
						/* var $marrige = $("#zy-mobile-marriage-status");
						$marrige.empty();
						$.each(data.marriage,function(idx,item){
							var $item = $('<option value='+item.baseDataCode+'>'+item.baseDataName+'</option>');
							$marrige.append($item);
						}); */

						console.log("========================= person data loaded ==============");
						console.log(person);

						var birthDate = person.birthDate.substr(0,10);
						var phone = person.phone;
						var certId = person.certId;
						var nationality = person.nationality;
						var originalPlace = person.originPlace;
						var registerLocation = person.registerLocation;
						var contactAddress = person.contactAddress;
						var familyAddress = person.familyAddress;
						var postCode = person.postCode;
						var fileLocation = person.fileLocation;
						var firstjobDate = person.firstjobDate.substr(0,10);
						var subBack = person.subbank;
						var salaryCard = person.salaryCard;

						$('#zy-mobile-phone').val(phone);
						$('#zy-mobile-birth-date').val(birthDate);
						$('#zy-mobile-cert-id').val(certId);
						$('#zy-mobile-nationality').val(nationality);
						$('#zy-mobile-original-place').val(originalPlace);
						$('#zy-mobile-register-location').val(registerLocation);
						$('#zy-mobile-contact-address').val(contactAddress);
						$('#zy-mobile-family-address').val(familyAddress);
						$('#zy-mobile-post-code').val(postCode);
						$('#zy-mobile-file-location').val(fileLocation);
						$('#zy-mobile-first-job-time').val(firstjobDate);
						$('#zy-mobile-sub-bank').val(subBack);
						$('#zy-mobile-salary-card').val(salaryCard);

						$('#zy-mobile-sex').val(person.sex);
						$('#zy-mobile-marriage-status').val(person.marital);
						$('#zy-mobile-register-type').val(person.registerType);
						$('#zy-mobile-education').val(person.education);
						$('#zy-mobile-degree').val(person.degree);
						$('#zy-mobile-bank').val(person.bankName);

						console.log("外籍状态:" + person.isForeign);
						console.log()
						if(person.isForeign == 0){
							$('#zy-mobile-f1').prop('checked',true);
							$('#zy-mobile-f2').prop('checked',false);
						}else{
							$('#zy-mobile-f1').prop('checked',false);
							$('#zy-mobile-f2').prop('checked',true);
						}
					}
				});

				////////////////////////////////////////////////////////////////////////
				$('.input-group.date').datepicker({
					format : "yyyy-mm-dd",
					language : "zh-CN",
					autoclose : true
				});

				//弹框左上角的返回按钮
				$(".modalHide").on("click",function(){
					var id=$(this).parents("div.modal").attr("id");
					$("#"+id).modal('hide');
				});

				template.helper('dateFormat', function(date){
					date = getHandleDateYMD(date);
					return date;
				});

				$('.demo2').click(function(){
					swal({
						title: "您的登录密码已被系统重置 新密码为XXXX",
						text: "请尽快登录系统修改密码！",
						type: "success",
						confirmButtonText: "确定"
					});
				});

				// $saveBaseInfoButton.on('click',function(){
				// 	$('[data-form-validator="personal"]').trigger('success.form.fv');
				// });
				// 校验并保存
				$('[data-form-validator="personal"]').formValidation(validateOptions.personal)
				.on('keyup', '[name=phone], [name=salaryCard]', function(event) {
					event.preventDefault();
					var getField = $(this).attr('name');
					var isEmpty = $(this).val()=='';
					$('[data-form-validator="personal"]')
					.formValidation('enableFieldValidators', getField, !isEmpty)
					.formValidation('revalidateField', getField);
				})
				.on('success.form.fv', function(e){
					e.preventDefault();

					var data = $('#baseInfoForm').serializeObject();
					// TODO 这个personID 需要通过接口取到
					data['personId'] = personId;

					$.ajax({
						type: "post",
						url: "employee/updatePerson",
						data: JSON.stringify(data),
						contentType:"application/json",
						dataType: "json",
						async: false,
						success:function(data){
							if(data.success == true){
								$('[data-container=personalInfo]').hide();
								$('#zy-mobile-attach').show();
							}else{
								swal("数据保存失败", "", "success");
							}
						}
					});
				});
			});

			adjustPlatform();

			/**
			* 检测平台in并应用相关样式
			* @return {[type]} [description]
			*/
			function adjustPlatform(){
				var system ={
					win : false,
					mac : false,
					xll : false
				};
				//检测平台
				var p = navigator.platform;
				system.win = p.indexOf("Win") == 0;
				system.mac = p.indexOf("Mac") == 0;
				system.x11 = (p == "X11") || (p.indexOf("Linux") == 0);
				//跳转语句
				if(system.win||system.mac||system.xll){
					$("#link").attr("href","static/css/denglu.css");
				}else{
					$("#link").attr("href","static/css/dengluiphone.css?_t="+(new Date()).getTime());
				}
			}

		</script>
	</body>
</html>