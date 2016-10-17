<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<base href="${pageContext.request.contextPath}/">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0;"
	name="viewport" />
<meta content="yes" name="apple-mobile-web-app-capable" />
<meta content="black" name="apple-mobile-web-app-status-bar-style" />
<meta content="telephone=no" name="format-detection" />

<title>员工录入界面</title>
<jsp:include flush="true" page="../common/styles.jsp"></jsp:include>
<link rel="stylesheet"
	href="static/js/plugins/form.validation/css/formValidation.css">
<link rel="stylesheet" id="link"  href="static/css/denglu.css" type="text/css">
<style>

</style>
<script>
			var username = '${userName}';
			var personId = '${personId}';
			var path = '${pageContext.request.contextPath}';
			
			//console.log
		</script>
</head>
<body class="blank" style="position:static;">
	<div class="splash">
		<div class="color-line"></div>
		<div class="splash-title">
			<h1>Jabava V1.0</h1>
			<p>属于你的专业人事专员</p>
			<img src="static/bootstrap/images/loading-bars.svg" width="64"
				height="64" />
		</div>
	</div>


	<!-- 员工信息录入 -->
	<div id="zy-mobile-main" class="containers" data-container="index">
		<!--头部-->
		<div class="tuichu">
			<!-- 	<h5><a href="employee/login_mobile"><img src="static/img/u4.png" width="20" height="20"></a><span>退出</span></h5> -->
		</div>
		<div class="hpanel">
			<div class="panel-body">

				<!--个人信息-->
				<div class="inform">
					<div class="inleft">
						<div class="imgs">
							<a href="javascript:;"><img src="static/img/u23.png"
								width="100" height="100"></a>
						</div>
						<div class="xinxi">
							<p>
							<h5 id="mobileUserName"></h5>
							</p>
							<p class="qi" id="mobilePhone"></p>
							<p class="qi" id="mobileEmail"></p>
						</div>
					</div>
					<div class="inright"></div>
				</div>
				<!--inform end-->
			</div>
		</div>
		<!--列表-->
		<div class="liebiao">
			<ul>
				<li><a href="javascript://" id="button-person"
					data-target="[data-container=person]"
					data-relatedTarget="[data-container=index]"
					data-toggle="container-switch"><span>+</span>个人信息</a></li>
				<li><a href="javascript://" id="button-attach"
					data-target="[data-container=upload]"
					data-relatedTarget="[data-container=index]"
					data-toggle="container-switch"><span>+</span>上传附件</a></li>
			</ul>
		</div>
	</div>
	<!-- 个人信息 -->
	<div class=" containers hidden" data-container="person"
		data-request-url="api/addressBook/getPersonInfo.json">
		<div class="blue">
			<h5 class="text-center">
				<span class="gereninform">完善员工信息</span> <a class="pull-left"
					data-target="[data-container=index]"
					data-relatedTarget="[data-container=person]"
					data-toggle="container-switch"> <img src="static/img/u4.png"
					width="20" height="20"></a>
			</h5>
		</div>
		<div class="custom-head-line board">
			<div class="custom-line"></div>
			<ul>
				<li><a class="active" href="javascript:void(0);"><span>1</span><span>个人信息</span></a></li>
				<li><a href="javascript:void(0);"><span>2</span><span>履历信息</span></a></li>
				<li><a href="javascript:void(0);"><span>3</span><span>教育信息</span></a></li>
				<li><a href="javascript:void(0);"><span>4</span><span>社会关系</span></a></li>
			</ul>
		</div>

		<form role="form" class="form-horizontal formclass"
			data-form-validator="person" data-form="person"
			data-form-action="api/addressBook/savePersonInfo.json">
			<div class="modal-header" autocomplete="off">
				<div class="row">
					<div class="panel-body grxx" style="background:none;">
						<input type="hidden" name="personId"> <input type="hidden"
							name="approvedStatus">
						<div class="form-group">
							<label for="" class="col-xs-3 col-sm-3 control-label">姓名：</label>
							<div class="col-xs-8 col-sm-8 form-required">
								<input type="text" class="form-control" name="employeeName">
							</div>
						</div>
						<div class="form-group">
							<label for="" class="col-xs-3 col-sm-3 control-label">民族：</label>
							<div class="col-xs-8 col-sm-8 form-required">
								<input type="text" class="form-control" name="nationality">
							</div>
						</div>
						<div class="form-group">
							<label for="" class="col-xs-3 col-sm-3 control-label">性别：</label>
							<div class="col-xs-8 col-sm-8 form-required">
								<select class="form-control " name="sex">
									<option value="1">男</option>
									<option value="2">女</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label for="" class="col-xs-3 col-sm-3 control-label">出生日期：</label>
							<div class="col-xs-8 col-sm-8">
								<div class="input-group date">
									<input type="text" class="form-control" name="birthDate">
									<span class="input-group-addon "><i
										class="glyphicon glyphicon-th"></i></span>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-xs-3 col-sm-3 control-label" for="">政治面貌：</label>
							<div class="col-xs-8 col-sm-8">
								<select name="politicsStatus" class="form-control"
									data-base-data="politicsStatus">
								</select>
							</div>
						</div>
						<div class="form-group">
							<label for="" class="col-xs-3 col-sm-3 control-label">家庭地址：</label>
							<div class="col-xs-8 col-sm-8">
								<input type="text" class="form-control" name="familyAddress">
							</div>
						</div>
						<div class="form-group">
							<label for="" class="col-xs-3 col-sm-3 control-label">现居住地：</label>
							<div class="col-xs-8 col-sm-8">
								<input type="text" class="form-control" name="contactAddress">
							</div>
						</div>
						<div class="form-group">
							<label for="" class="col-xs-3 col-sm-3 control-label">邮编：</label>
							<div class="col-xs-8 col-sm-8">
								<input type="text" class="form-control" name="postCode">
							</div>
						</div>
						<div class="form-group">
							<label for="" class="col-xs-3 col-sm-3 control-label">联系电话：</label>
							<div class="col-xs-8 col-sm-8 form-required">
								<input type="text" class="form-control" name="phone">
							</div>
						</div>
						<div class="form-group ">
							<label for="" class="col-xs-3 col-sm-3 control-label">Email：</label>
							<div class="col-xs-8 col-sm-8 form-required">
								<input type="text" class="form-control" name="email" value="">
							</div>
						</div>
						<div class="form-group">
							<label for="" class="col-xs-3 col-sm-3 control-label">最高学历：</label>
							<div class="col-xs-8 col-sm-8  form-required">
								<select class="form-control " name="education"
									data-base-data="education">
								</select>
							</div>
						</div>
						<div class="form-group">
							<label for="" class="col-xs-3 col-sm-3 control-label">最高学位：</label>
							<div class="col-xs-8 col-sm-8 form-required">
								<select class="form-control" name="degree"
									data-base-data="degree">
								</select>
							</div>
						</div>
						<div class="form-group">
							<label for="" class="col-xs-3 col-sm-3 control-label">证件类型：</label>
							<div class="col-xs-8 col-sm-8 form-required">
								<select class="form-control" name="certType"
									data-base-data="certType">
								</select>
							</div>
						</div>
						<div class="form-group">
							<label for="" class="col-xs-3 col-sm-3 control-label">证件号：</label>
							<div class="col-xs-8 col-sm-8 form-required">
								<input type="text" class="form-control" name="certId">
							</div>
						</div>
						<div class="form-group">
							<label for="" class="col-xs-3 col-sm-3 control-label">户口类型：</label>
							<div class="col-xs-8 col-sm-8 form-required">
								<select class="form-control " name="registerType"
									data-base-data="registerType">
								</select>
							</div>
						</div>
						<div class="form-group">
							<label for="" class="col-xs-3 col-sm-3 control-label">户口所在地：</label>
							<div class="col-xs-8 col-sm-8 form-required">
								<input type="text" class="form-control" name="registerLocation">
							</div>
						</div>
						<div class="form-group">
							<label for="" class="col-xs-3 col-sm-3 control-label">工资卡号：</label>
							<div class="col-xs-8 col-sm-8 form-required">
								<input type="text" class="form-control" name="salaryCard">
							</div>
						</div>
						<div class="form-group">
							<label for="" class="col-xs-3 col-sm-3 control-label">开户行：</label>
							<div class="col-xs-8 col-sm-8 form-required">
								<select class="form-control " name="bankName"
									data-base-data="bankName">
								</select>
							</div>
						</div>
						<div class="form-group">
							<label for="" class="col-xs-3 col-sm-3 control-label">工资卡开户支行：</label>
							<div class="col-xs-8 col-sm-8 form-required">
								<input type="text" class="form-control" name="subbank">
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<div class="text-center">
					<button type="submit" class="btn btn-danger"
						data-target="[data-container=experience]"
						data-relatedTarget="[data-container=person]">保存并进入下一步</button>
				</div>
			</div>
		</form>
	</div>
	<div class="  containers hidden" data-container="experience"
		data-request-url="api/addressBook/getRecordInfo.json"
		data-delete-url="api/addressBook/deleteRecordInfo.json">
		<div class="blue">
			<h5 class="text-center">
				<span class="gereninform">完善员工信息</span> <a class="pull-left"
					data-target="[data-container=person]"
					data-relatedTarget="[data-container=experience]"
					data-toggle="container-switch"> <img src="static/img/u4.png"
					width="20" height="20"></a>
			</h5>
		</div>
		<div class="hpanel">
			<div class="panel-body">

				<div class="custom-head-line board">
					<div class="custom-line"></div>
					<ul>
						<li><a href="javascript:void(0);"><span>1</span><span>个人信息</span></a></li>
						<li><a class="active" href="javascript:void(0);"><span>2</span><span>履历信息</span></a></li>
						<li><a href="javascript:void(0);"><span>3</span><span>教育信息</span></a></li>
						<li><a href="javascript:void(0);"><span>4</span><span>社会关系</span></a></li>
					</ul>
				</div>
				<form role="form" class="form-horizontal formclass"
					data-form-validator="experience" data-form="experience"
					data-form-action="api/addressBook/saveRecordInfo.json"
					autocomplete="off">
					<div class="modal-header">
						<div class="row">
							<div class="panel-body grxx" style="background:none;">
								<h5 class="page-header m-t-n">
									<a class="pull-right text-danger" data-action="delete"
										href="javascript://">删除</a> <span class="slide-title"
										data-msg-tpl="工作履历{num}：">工作履历{num}</span>
								</h5>
								<input type="hidden" name="personId"> 
								<input type="hidden" name="experienceId">
								
								<div class="form-group">
									<label for="" class="col-xs-3 col-sm-3 control-label">工作起始时间：</label>
									<div class="col-xs-8 col-sm-8 form-required">
										<div class="input-group date" data-toggle="datepicker">
											<input type="text" class="form-control" name="startDate"
												value=""> <span class="input-group-addon "><i
												class="glyphicon glyphicon-th"></i></span>
										</div>
									</div>
								</div>
								<div class="form-group">
									<label for="" class="col-xs-3 col-sm-3 control-label">工作终止时间：</label>
									<div class="col-xs-8 col-sm-8 form-required">
										<div class="input-group date" data-toggle="datepicker">
											<input type="text" class="form-control" name="endDate"
												value=""> <span class="input-group-addon "><i
												class="glyphicon glyphicon-th"></i></span>
										</div>
									</div>
								</div>
								<div class="form-group">
									<label for="" class="col-xs-3 col-sm-3 control-label">单位名称：</label>
									<div class="col-xs-8 col-sm-8 form-required">
										<input type="text" class="form-control" name="company"
											value="" required="required">
									</div>
								</div>
								<div class="form-group">
									<label for="" class="col-xs-3 col-sm-3 control-label">担任职务：</label>
									<div class="col-xs-8 col-sm-8 form-required">
										<input type="text" class="form-control" name="workPost"
											value="">
									</div>
								</div>
								<div class="form-group">
									<label for="" class="col-xs-3 col-sm-3 control-label">主要职责：</label>
									<div class="col-xs-8 col-sm-8">
										<input type="text" class="form-control" name="duty" value="">
									</div>
								</div>
								<div class="form-group">
									<label for="" class="col-xs-3 col-sm-3 control-label">证明人：</label>
									<div class="col-xs-8 col-sm-8">
										<input type="text" class="form-control" name="authenticator"
											value="">
									</div>
								</div>
								<div class="form-group">
									<label for="" class="col-xs-3 col-sm-3 control-label">证明电话：</label>
									<div class="col-xs-8 col-sm-8">
										<input type="text" class="form-control" name="refPhone"
											value="">
									</div>
								</div>
								<div class="form-group">
									<label for="" class="col-xs-3 col-sm-3 control-label">备注：</label>
									<div class="col-xs-8 col-sm-8">
										<input type="text" class="form-control" name="remark" value="">
									</div>
								</div>
							</div>
							<div class="panel-header text-center add-more">
								<button type="button" class="btn btn-primary btn-outline">继续添加</button>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<div class="text-center">
							<button type="submit" class="btn btn-danger"
								data-target="[data-container=education]"
								data-relatedTarget="[data-container=experience]">保存并进入下一步</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<div class="containers hidden" data-container="education"
		data-request-url="api/addressBook/getEduInfo.json"
		data-delete-url="api/addressBook/deleteEduInfo.json">
		<div class="blue">
			<h5 class="text-center">
				<span class="gereninform">完善员工信息</span> <a class="pull-left"
					data-target="[data-container=experience]"
					data-relatedTarget="[data-container=education]"
					data-toggle="container-switch"> <img src="static/img/u4.png"
					width="20" height="20"></a>
			</h5>
		</div>
		<div class="hpanel">
			<div class="panel-body">
				<div class="row">

					<div class="custom-head-line board">
						<div class="custom-line"></div>
						<ul>
							<li><a href="javascript:void(0);"><span>1</span><span>个人信息</span></a></li>
							<li><a href="javascript:void(0);"><span>2</span><span>履历信息</span></a></li>
							<li><a class="active" href="javascript:void(0);"><span>3</span><span>教育信息</span></a></li>
							<li><a href="javascript:void(0);"><span>4</span><span>社会关系</span></a></li>
						</ul>
					</div>
					<form role="form" class="form-horizontal formclass"
						data-form-validator="education" data-form="education"
						data-form-action="api/addressBook/saveEduInfo.json"
						autocomplete="off">
						<div class="modal-header">
							<div class="row">
								<div class="panel-body grxx" style=" background:none;">
									<h5 class="page-header m-t-n">
										<a class="pull-right text-danger" data-action="delete"
											href="javascript://">删除</a> <span class="slide-title"
											data-msg-tpl="教育经历{num}：">教育经历{num}</span>
									</h5>
									<input type="hidden" name="personId"> <input
										type="hidden" name="educationId">
									<div class="form-group">
										<label for="" class="col-xs-3 col-sm-3 control-label">在校时间起：</label>
										<div class="col-xs-8 col-sm-8 form-required">
											<div class="input-group date" data-toggle="datepicker">
												<input type="text" class="form-control" name="entranceDate"
													value=""> <span class="input-group-addon "><i
													class="glyphicon glyphicon-th"></i></span>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label for="" class="col-xs-3 col-sm-3 control-label">在校时间止：</label>
										<div class="col-xs-8 col-sm-8 form-required">
											<div class="input-group date" data-toggle="datepicker">
												<input type="text" class="form-control" name="graduateDate"
													value=""> <span class="input-group-addon "><i
													class="glyphicon glyphicon-th"></i></span>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label for="" class="col-xs-3 col-sm-3 control-label">学校名称：</label>
										<div class="col-xs-8 col-sm-8 form-required">
											<input type="text" class="form-control" name="graduateSchool"
												value="" required="required">
										</div>
									</div>
									<div class="form-group">
										<label for="" class="col-xs-3 col-sm-3 control-label">学历：</label>
										<div class="col-xs-8 col-sm-8 form-required">
											<select class="form-control" name="education"
												data-base-data="education"></select>
										</div>
									</div>
									<div class="form-group">
										<label for="" class="col-xs-3 col-sm-3 control-label">专业：</label>
										<div class="col-xs-8 col-sm-8 form-required">
											<input type="text" class="form-control" name="major" value="">
										</div>
									</div>
									<div class="form-group">
										<label for="" class="col-xs-3 col-sm-3 control-label">学位：</label>
										<div class="col-xs-8 col-sm-8 form-required">
											<select class="form-control" name="degree"
												data-base-data="degree"></select>
										</div>
									</div>
									<div class="form-group">
										<label for="" class="col-xs-3 col-sm-3 control-label">备注：</label>
										<div class="col-xs-8 col-sm-8">
											<input type="text" class="form-control" name="memo" value="">
										</div>
									</div>
								</div>
								<div class="panel-header text-center add-more">
									<button type="button" class="btn btn-primary btn-outline">继续添加</button>
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<div class="text-center">
								<button type="submit" class="btn btn-danger"
									data-target="[data-container=family]"
									data-relatedTarget="[data-container=education]">保存并进入下一步</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<div class="  containers hidden" data-container="family"
		data-request-url="api/addressBook/getRelatedInfo.json"
		data-delete-url="api/addressBook/deleteRelatedInfo.json">
		<div class="blue">
			<h5 class="text-center">
				<span class="gereninform">完善员工信息</span> <a class="pull-left"
					data-target="[data-container=education]"
					data-relatedTarget="[data-container=family]"
					data-toggle="container-switch"> <img src="static/img/u4.png"
					width="20" height="20"></a>
			</h5>
		</div>
		<div class="hpanel">
			<div class="panel-body">
				<div class="row">

					<div class="custom-head-line board">
						<div class="custom-line"></div>
						<ul>
							<li><a href="javascript:void(0);"><span>1</span><span>个人信息</span></a></li>
							<li><a href="javascript:void(0);"><span>2</span><span>履历信息</span></a></li>
							<li><a href="javascript:void(0);"><span>3</span><span>教育信息</span></a></li>
							<li><a class="active" href="javascript:void(0);"><span>4</span><span>社会关系</span></a></li>
						</ul>
					</div>
					<form role="form" class="form-horizontal formclass"
						data-form-validator="family" data-form="family"
						data-form-action="api/addressBook/saveRelatedInfo.json"
						autocomplete="off">
						<div class="modal-header">
							<div class="row">
								<div class="panel-body grxx" style=" background:none;">
									<h5 class="page-header m-t-n">
										<a class="pull-right text-danger" data-action="delete"
											href="javascript://">删除</a> <span class="slide-title"
											data-msg-tpl="社会关系{num}：">社会关系{num}</span>
									</h5>
									<input type="hidden" name="personId"> <input
										type="hidden" name="familyId">
									<div class="form-group">
										<label for="" class="col-xs-3 col-sm-3 control-label">姓名：</label>
										<div class="col-xs-8 col-sm-8 form-required">
											<input type="text" class="form-control" name="userName">
										</div>
									</div>
									<div class="form-group">
										<label for="" class="col-xs-3 col-sm-3 control-label">与本人关系：</label>
										<div class="col-xs-8 col-sm-8 form-required">
											<select class="form-control" data-base-data="relation"
												name="relation">
											</select>
										</div>
									</div>
									<div class="form-group">
										<label for="" class="col-xs-3 col-sm-3 control-label">联系电话：</label>
										<div class="col-xs-8 col-sm-8 form-required">
											<input type="text" class="form-control" name="phone">
										</div>
									</div>
									<div class="form-group">
										<label for="" class="col-xs-3 col-sm-3 control-label">工作单位：</label>
										<div class="col-xs-8 col-sm-8 form-required">
											<input type="text" class="form-control" name="workUnit">
										</div>
									</div>
									<div class="form-group">
										<label for="" class="col-xs-3 col-sm-3 control-label">联系地址：</label>
										<div class="col-xs-8 col-sm-8">
											<input type="text" class="form-control" name="address">
										</div>
									</div>
									<div class="form-group">
										<label for="" class="col-xs-3 col-sm-3 control-label">职务：</label>
										<div class="col-xs-8 col-sm-8">
											<input type="text" class="form-control" name="position">
										</div>
									</div>
									<div class="form-group">
										<label for="" class="col-xs-3 col-sm-3 control-label">备注：</label>
										<div class="col-xs-8 col-sm-8">
											<input type="text" class="form-control" name="remark">
										</div>
									</div>
								</div>
								<div class="panel-header text-center add-more">
									<button type="button" class="btn btn-primary btn-outline">继续添加</button>
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<div class="text-center">
								<button type="submit" class="btn btn-danger"
									data-target="[data-container=index]"
									data-relatedTarget="[data-container=family]">提交</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>


	<!--上传附件弹框-->
	<div class="  containers hidden" id='zy-mobile-attach'
		data-container="upload">
		<div class="blue">
			<h5>
				<a href="javascript://" class="pull-right m-r-md"
					data-target="[data-container=index]"
					data-relatedTarget="[data-container=upload]"
					data-toggle="container-switch"> <span class="gereninform">关闭</span>
				</a> <a data-target="[data-container=index]"
					data-relatedTarget="[data-container=upload]"
					data-toggle="container-switch"> <img src="static/img/u4.png"
					width="20" height="20">
				</a> <span class="gereninform">上传附件</span>
			</h5>
		</div>
		<div class="hpanel">
			<div class="panel-body">
				<div class="row">

								<form role="form" class="form-horizontal formclass"
									data-add-url="">
									<div class="form-group">
										<label for="" class="col-xs-3 col-sm-3 control-label">身份证：</label>
										<div class="col-xs-8 col-sm-8">
											<input type="file" data-fileid="1" id="certidFileInput"
												class="form-control-static">
										</div>
									</div>
									<div class="form-group">
										<label for="" class="col-xs-3 col-sm-3 control-label">户口簿：</label>
										<div class="col-xs-8 col-sm-8">
											<input type="file" data-fileid="2"
												class="form-control-static">
										</div>
									</div>
									<div class="form-group">
										<label for="" class="col-xs-3 col-sm-3 control-label">学历证：</label>
										<div class="col-xs-8 col-sm-8">
											<input type="file" data-fileid="3"
												class="form-control-static">
										</div>
									</div>
									<div class="form-group">
										<label for="" class="col-xs-3 col-sm-3 control-label">简历：</label>
										<div class="col-xs-8 col-sm-8">
											<input type="file" data-fileid="4"
												class="form-control-static">
										</div>
									</div>
									<div class="form-group">
										<label for="" class="col-xs-3 col-sm-3 control-label ">离职证明：</label>
										<div class="col-xs-8 col-sm-8">
											<input type="file" data-fileid="5"
												class="form-control-static">
										</div>
									</div>
									<div class="form-group">
										<label for="" class="col-xs-3 col-sm-3 control-label ">健康证明：</label>
										<div class="col-xs-8 col-sm-8">
											<input type="file" data-fileid="6"
												class="form-control-static">
										</div>
									</div>
									<div class="form-group">
										<label for="" class="col-xs-3 col-sm-3 control-label">薪资证明：</label>
										<div class="col-xs-8 col-sm-8">
											<input type="file" data-fileid="7"
												class="form-control-static">
										</div>
									</div>
									<center style="clear:both; padding-top:30px;">
										<button type="button" id="zy-save-files" class="btn btn-info"
											data-click-target="onlySave"
											data-next-modal="[data-modal=personalInfo]">保存并退出</button>
									</center>
								</form>
				</div>
			</div>
		</div>
	</div>
	<script src="static/bootstrap/vendor/jquery/dist/jquery.min.js"></script>
	<script src="static/bootstrap/vendor/jquery-ui/jquery-ui.min.js"></script>
	<script
		src="static/bootstrap/vendor/slimScroll/jquery.slimscroll.min.js"></script>
	<script
		src="static/bootstrap/vendor/bootstrap/dist/js/bootstrap.min.js"></script>
	<script src="static/bootstrap/vendor/jquery-flot/jquery.flot.js"></script>
	<script src="static/bootstrap/vendor/jquery-flot/jquery.flot.resize.js"></script>
	<script src="static/bootstrap/vendor/jquery-flot/jquery.flot.pie.js"></script>
	<script src="static/bootstrap/vendor/flot.curvedlines/curvedLines.js"></script>
	<script src="static/bootstrap/vendor/jquery.flot.spline/index.js"></script>
	<script src="static/bootstrap/vendor/metisMenu/dist/metisMenu.min.js"></script>
	<script src="static/bootstrap/vendor/iCheck/icheck.min.js"></script>
	<script src="static/bootstrap/vendor/peity/jquery.peity.min.js"></script>
	<script src="static/bootstrap/vendor/sparkline/index.js"></script>
	<script
		src="static/bootstrap/vendor/xeditable/bootstrap3-editable/js/bootstrap-editable.min.js"></script>
	<script src="static/bootstrap/vendor/select2-3.5.2/select2.min.js"></script>
	<script
		src="static/bootstrap/vendor/bootstrap-touchspin/dist/jquery.bootstrap-touchspin.min.js"></script>
	<script
		src="static/bootstrap/vendor/bootstrap-datepicker-master/dist/js/bootstrap-datepicker.js"></script>
	<script src="static/bootstrap/vendor/moment/moment.js"></script>
	<script
		src="static/bootstrap/vendor/datatables/media/js/jquery.dataTables.min.js"></script>
	<script
		src="static/bootstrap/vendor/datatables_plugins/integration/bootstrap/3/dataTables.bootstrap.min.js"></script>
	<script src="static/bootstrap/vendor/sweetalert/lib/sweet-alert.min.js"></script>
	<script src="static/js/plugins/form.validation/js/formValidation.js"></script>
	<script
		src="static/js/plugins/form.validation/js/framework/bootstrap.js"></script>
	<script src="static/js/plugins/form.validation/js/language/zh_CN.js"></script>
	<script
		src="static/js/plugins/form.validation/js/formValidationExtend.js"></script>
	<script src="static/bootstrap/scripts/homer.js"></script>
	<script src="static/bootstrap/scripts/charts.js"></script>
	<script src="static/js/template.js"></script>
	<script src="static/js/zy-common.js"></script>
	<script src="static/js/apis.js"></script>
	<script src="static/js/employees/yuangongluru.js"></script>
	<script>
			var fileArray = [];
     
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

						fileArray.push(obj);
					}
				});
				xhr.open("POST", '${pageContext.request.contextPath}'+'/employee/uploadToHRO');
				xhr.send(fd);
			});

			$('#zy-save-files').on('click',function(){
				  $.ajax({
					type:"post",
					url:"employee/savePersonFiles",
					data:JSON.stringify(fileArray),
					dataType:"json",
					contentType:"application/json",
					success:function(data){
						if(data.status == 0){
							$('#zy-mobile-attach').addClass("hidden");
							$('#zy-mobile-main').removeClass("hidden");  
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

				$('.input-group.date').datepicker({
					format : "yyyy-mm-dd",
					language : "zh-CN",
					autoclose : true
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


				// 校验并保存
				/*$('[data-form-validator="personal"]').formValidation(validateOptions.personal)
				.on('success.form.fv', function(e){
					e.preventDefault();

					console.log('personal');
					return false;
					var data = $('#baseInfo').serializeObject();
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
				});*/



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