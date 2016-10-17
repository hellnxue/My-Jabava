<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="com.jabava.utils.RequestUtil"%>
<!DOCTYPE html>
<html>
<head>
	<base href="${pageContext.request.contextPath}/">
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>员工社保档案</title>
	<jsp:include flush="true" page="../common/styles.jsp"/>
	<link rel="stylesheet" href="static/js/plugins/form.validation/css/formValidation.css">
	<link rel="stylesheet" href="static/css/user.css">
    <link rel="stylesheet" href="static/css/user_bata.css">
    <link rel="stylesheet" href="static/css/bill.css">
    <script type="text/javascript">
    window.power = {
    	securityprofile_im: <%=RequestUtil.hasPower("securityprofile_im")%>,
    	securityprofile_ex: <%=RequestUtil.hasPower("securityprofile_ex")%>,
    	securityprofile_mp: <%=RequestUtil.hasPower("securityprofile_mp")%>,
    	securityprofile_modify_ca: <%=RequestUtil.hasPower("securityprofile_modify_ca")%>,
    	securityprofile_supplement_do: <%=RequestUtil.hasPower("securityprofile_supplement_do")%>,
    	securityprofile_paydetail_ex: <%=RequestUtil.hasPower("securityprofile_paydetail_ex")%>
    }
    </script>
</head>
<body>
	<jsp:include flush="true" page="../common/splashscreen.div.jsp"/>
	<jsp:include flush="true" page="../common/header.div.jsp"/>
	<jsp:include flush="true" page="../common/menu.div.jsp"/>

	<div id="wrapper">
		<div class="normalheader transition animated fadeIn small-header">
          <div class="hpanel">
            <div class="panel-body">
              <div id="hbreadcrumb" class="m-t-xs m-b-xs">
                <h2 class="font-normal m-b-xs text-center">
                  员工社保档案
                </h2>
              </div>
            </div>
          </div>
        </div>
		<div id='zy-ss-profile-list' class="content animate-panel">
			<div class="row">
				<div class="col-lg-12">
					<div class="hpanel">
						<div class="panel-heading">
							<a href="socialAccumulationAccount/listAccounts" class="btn btn-info" id="ssSetUp">社保账户设置</a>
							<div class="pull-right">
								<div class="btn-group">
									<a href="insuranceDocs/batchUpload" class="btn btn-info" >
										批量开通
									</a>
								</div>
								<a class="btn btn-info" id="exportAccount">导出档案</a>
							</div>

							<%--<h4>
								<!-- <a class="pull-right small " data-toggle="modal" data-target="[data-modal=download]">
									<span class="text-info">下载社保档案模板</span>
								</a> -->
								<a href="static/xls/Jabava_person_security_profile_list_template.1.0.1.xlsx" class="pull-right small"><span class="text-info">下载员工社保档案模板</span></a>
							</h4>--%>
						</div>

						<div class="panel-body">
							<table id="projectTable" class="table table-condensed table-hover table-bordered dataTable" width="100%">
								<thead>
								<tr>
									<th>工号</th>
									<th>姓名</th>
									<th>所属部门</th>
									<!--<th>社保账户名称</th>
									<th>公积金账户名称</th>
									<th>户籍类型</th>-->
									<th>入职时间</th>
									<th>证件号码</th>
									<th>参保地</th>
									<th>社保公积金账户</th>
								<!--<th>基本工资</th> -->
									<th>社保状态</th>
									<th>公积金状态</th>
									<th>操作</th>
								</tr>
								</thead>
								<tbody></tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>

		 <!-- 下载社保档案模板 -->
        <div class="modal fade hmodal-success form-row" data-modal="download" tabindex="-1">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="color-line"></div>
                    <div class="modal-header">
                        <div class="row">
                            <form class="form-horizontal" id="addForm" action="">
                                <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
                                    <div class="form-group">
                                        <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-4">社保账户名称：</label>
                                        <div class="col-xs-6 col-sm-6 col-md-6 col-lg-8 ">
                                            <select class="form-control" name="">
                                                <option value="">----------</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
                                    <div class="form-group">
                                        <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-4">社保参保类型：</label>
                                        <div class="col-xs-6 col-sm-6 col-md-6 col-lg-8 ">
                                            <select class="form-control" name="">
                                                <option value="">----------</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
                                    <div class="form-group">
                                        <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-4">公积金账户名称：</label>
                                        <div class="col-xs-6 col-sm-6 col-md-6 col-lg-8 ">
                                            <select class="form-control" name="">
                                                <option value="">请选择</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
                                    <div class="form-group">
                                        <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-4">公积金参保类型：</label>
                                        <div class="col-xs-6 col-sm-6 col-md-6 col-lg-8 ">
                                            <select class="form-control"  name="">
                                                <option value="">请选择</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
	                           		<div class="form-group">
	                                        <label class="control-label col-sm-4 col-md-5 col-lg-2 ">上传模板：</label>
	                                        <div class="col-sm-8 col-md-7 col-lg-10 ">
	                                            <div data-toggle="upload:file" class="input-group">
	                                                <input type="text" readonly="readonly" class="form-control">
	                                                <input type="hidden" name="filePath" id="filePath">
	                                                <div class="input-group-btn">
	                                                    <span class="btn btn-default">浏览...
	                                                    <input type="file" accept=".xlsx" name="uploadFile" id="uploadFile" class="sr-only"></span>
	                                                </div>
	                                            </div>
	                                        </div>
	                                    </div>
                                </div>
                                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
	                                <div class="form-group">
	                                        <div class="col-sm-5 col-md-5 col-lg-7 col-lg-offset-2">
	                                            <button type="button" class="btn btn-block btn-success" id="upload">上&#12288;传</button>
	                                        </div>
	                                        <div class="col-sm-3 col-md-3 col-lg-3">
	                                            <button type="button" class="btn btn-block btn-default" id="cancel">取&#12288;消</button>
	                                        </div>
	                                    </div>
	                            </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- 下载社保档案模板结束 -->

		<div id='zy-ss-profile-editor' class="content animate-panel" style="display: none;">
			<div class="row">
				<div class="col-lg-12">
					<div class="hpanel">
						<div class="panel-heading">
							<h4 class="text-center font-bold">
								员工社保档案配置
							</h4>
						</div>

						<ul role="tabList" class="nav nav-tabs" >
							<li class="zy-ss-tab active"><a data-toggle="tab" href="#profileEdit">修改信息</a></li>
							<%--<li class='zy-ss-tab' id="zy-ss-supplement-tab"><a data-toggle="tab" href="#supplementPayment">补缴管理</a></li>--%>
							<li class="zy-ss-tab"><a data-toggle="tab" href="#paymentDetailedInfo" data-tab-name="paymentDetailedInfo">缴费明细</a></li>
						</ul>

						<div class="tab-content">
							<div id="supplementPayment" class="tab-pane">
								<div class="panel-body">
									<div class="row m-b">
										<div class="col-lg-12">
											<h5 id="zy-ss-user-name" class="text-info"></h5>
											<p>社保参保规则：<span id='zy-ss-security-rule-name'></span> &nbsp;&nbsp;&nbsp;公积金参保规则： <span id='zy-ss-gongjijin-rule-name'></span></p>
										</div>
										<div class="col-lg-12">
											<div class="col-sm-6 col-md-6 col-lg-4">
												<div class="form-group">
													<label class="control-label text-right col-xs-4 col-sm-4 col-md-4 col-lg-5 m-t-xs">补缴办理月：</label>
													<div class="col-xs-8 col-sm-8 col-md-8 col-lg-7">
                                                        <div class="input-group date">
                                                            <input id='zy-ss-payment-month' type="text" class="form-control" name="">
                                                            <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                                        </div>
													</div>
												</div>
											</div>
											<div class="col-sm-6 col-md-6 col-lg-8 m-t">
												<a id="zy-ss-add-security" class="m-r-md" href="javascript:;" data-target="insuranceType" data-delate="insuranceAll"><i class="fa fa-plus"></i>添加保险类型</a>
												<a id="zy-ss-add-all-security" href="javascript:;" data-target="insuranceAll" data-delate="insuranceType"><i class="fa fa-plus"></i>添加全部保险</a>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-lg-12 m-b table-responsive">
											<table id="payManamentTable" class="table table-condensed table-hover table-bordered dataTable">
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
												<tbody id='zy-ss-supplement-payment' class="hidden-1" data-panel="insuranceType"></tbody>
											</table>
										</div>
										<div class="col-lg-12 text-right">
											<button id='zy-ss-cancel-payment' class="btn btn-default">取　消</button>
                 							<% if(RequestUtil.hasPower("securityprofile_supplement_do")){ %>
											<button id='zy-ss-confirm-payment' class="btn btn-info">确　认</button>
											<% } %>
										</div>
									</div>
							</div>
							</div>

							<!-- 缴费明细  -->
							<div id="paymentDetailedInfo" class="tab-pane" >
							    <div id='zz-ss-security-fee-detail'  class="panel-body">

                                   	<form id="paymentListForm" action="" class="form-horizontal">
                                   		<input type="hidden" id="personIdForPaymentList" name="personId">
										<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
	                                         <div class="form-group">
	                                            <label class="control-label col-xs-4 col-sm-4 col-md-4 col-lg-3">开始月份：</label>
	                                            <div class="col-xs-6 col-sm-6 col-md-6 col-lg-7">
	                                            <div class="input-group date ">
	                                                    <input type="text" class="form-control" name="startMonth" id="startMonthForPaymentList">
	                                                    <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
	                                            </div>
	                                            </div>
	                                        </div>
                                        </div>
										<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
	                                         <div class="form-group">
	                                            <label class="control-label col-xs-4 col-sm-4 col-md-4 col-lg-3">结束月份：</label>
	                                            <div class="col-xs-6 col-sm-6 col-md-6 col-lg-7">
	                                            <div class="input-group date ">
	                                                    <input type="text" class="form-control" name="endMonth" id="endMonthForPaymentList">
	                                                    <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
	                                            </div>
	                                            </div>
	                                        </div>
                                        </div>
										<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-center">
	                                        <button type="button" class="btn btn-info btn-sm btn-search-paymentlist">查询</button>
	                                    </div>
                                   	</form>
                                   	<div class="text-right m-b-sm">
                 						<% if(RequestUtil.hasPower("securityprofile_paydetail_ex")){ %>
                                   		<button class="btn btn-success btn-sm" type="button" data-motive="exportPayment">导 出</button>
                                   		<% } %>
                                   	</div>
                                    <table id="detailTable" class="table table-bordered table-hover detailTable dataTable" width="100%" data-cache="nocache">
                                            <thead>
                                               <tr>
                                               	<th rowspan="2" colspan="1">月份</th>
                                               	<th rowspan="2" colspan="1">社保账号</th>
                                               	<th rowspan="2" colspan="1">公积金账号</th>
                                               	<th rowspan="2" colspan="1">汇缴总额</th>
                                               	<th rowspan="2" colspan="1">企业汇缴总额</th>
                                               	<th rowspan="2" colspan="1">个人汇缴总额</th>
                                               	<th rowspan="2" colspan="1">补缴总额</th>
                                               	<th rowspan="2" colspan="1">企业补缴总额</th>
                                               	<th rowspan="2" colspan="1">个人补缴总额</th>
                                               </tr>
                                            </thead>
                                            <tbody>

                                            </tbody>
                                        </table>
                                </div>
							</div>
							<!-- 社保档案编辑 -->
							<div id="profileEdit" class="tab-pane active">
								<div class="panel-body">
									<form class="form-horizontal" id="zy-ss-security-profile-form" data-form="staffdocs" autocomplete="off">
										<div class="row">
											<fieldset>
												<div class="col-lg-12 m-b text-info">
													<h5>个人基础信息</h5>
												</div>
												<div class="col-xs-12">
													<div class="form-group">
														<div class="col-xs-12">
															<div class="form-group">
																<div class="col-xs-4">
																	<span class="text-right col-xs-6">工号：</span>
																	<div class="col-xs-6" id="zy-ss-base-job-number">
																	</div>
																</div>
																<div class="col-xs-4">
																	<span class="text-right col-xs-6">姓名：</span>
																	<div class="col-xs-6" id='zy-ss-base-name'>
																	</div>
																</div>
																<div class="col-xs-4">
																	<span class="text-right col-xs-6">所属部门：</span>
																	<div class="col-xs-6" id='zy-ss-base-organization'>
																	</div>
																</div>
															</div>
														</div>
														<div class="col-xs-12">
															<div class="form-group">
																<div class="col-xs-4">
																	<span class="text-right col-xs-6">证件号码：</span>
																	<div class="col-xs-6" id='zy-ss-base-cert-id'>
																	</div>
																</div>
																<div class="col-xs-4">
																	<span class="text-right col-xs-6">户籍类型：</span>
																	<div class="col-xs-6" id='zy-ss-base-register-type'>
																	</div>
																</div>
																<div class="col-xs-4">
																	<span class="text-right col-xs-6">入职时间：</span>
																	<div class="col-xs-6" id='zy-ss-base-entry-date'>
																	</div>
																</div>
															</div>
														</div>
														<div class="col-xs-12">
															<div class="col-xs-5">
																<div class="form-group shebao">
																	<div class="col-xs-10">
																		<label class="control-label text-right col-xs-6">社保公积金账户：</label>
																		<div class="col-xs-6">
																			<select id='zy-ss-base_account_selector' edit-role="open modify" class="form-control" name=""></select>
																		</div>
																	</div>
																</div>
															</div>
															<div class="col-xs-7">
																<div class="form-group shebao1">
																	<div class="col-xs-12 col-xs-pull-2">
																		<label class="control-label text-right col-xs-4">社保公积金缴纳地：</label>
																		<div class="col-xs-8 no-gutter">
																			<div class="col-xs-6">
																				<select id='zy-ss-base_province_selector' edit-role="open modify" class="form-control" name=""></select>
																			</div>
																			<div class="col-xs-6">
																				<select id='zy-ss-base_city_selector' edit-role="open modify" class="form-control" name=""></select>
																			</div>
																		</div>
																	</div>
																</div>
															</div>
														</div>
													</div>
												</div>
											</fieldset>
										</div>

										<div class="row m-b-md" >
											<fieldset>
												<legend>
													<div class="col-lg-12 m-b text-info">
														<h5>
															<label class="checkbox-inline">
																<input id='zy-ss-security-switch' type="checkbox" edit-role="open modify" data-checkbox-target="paySocialSecurity" checked="checked">
																是否缴纳社保
															</label>
														</h5>
													</div>
												</legend>
												<div id="zy-ss-security-pane"class="row" data-box="paySocialSecurity">
													<div class="col-lg-12">
														<div class="form-group">
															<div class="col-xs-12">
																<div class="form-group">
																	<div class="col-xs-4" style="display: none;">
																		<label class="control-label col-xs-6">社保账户：</label>
																		<div class="col-xs-6">
																			<select id="zy-ss-security-org-account-selector" edit-role="open modify" class="form-control" name="securityOrgAccount">
																			</select>
																		</div>
																	</div>
																	<div class="col-xs-4">
																		<div class="form-group">
																			<label class="control-label col-xs-6">参保类型：</label>
																			<div class="col-xs-6 form-required">
																				<select id='zy-ss-security_type_selector' edit-role="open modify" class="form-control" name="securityType"></select>
																			</div>
																		</div>
																	</div>
																	<div class="col-xs-4">
																		<label class="control-label col-xs-6">社保账号：</label>
																		<div class="col-xs-6">
																			<input id='zy-ss-security-account' type="text" edit-role="open modify" class="form-control" name="securityAccount">
																		</div>
																	</div>
																	<div class="col-xs-4">
																		<div class="form-group">
																			<label class="control-label col-xs-6">缴纳方式：</label>
																			<div class="col-xs-6 form-required">
																				<select id='zy-ss-security-create-type' edit-role="open modify" class="form-control" name="securityCreateType">
																					<option value="0">新开</option>
																					<option value="1">续缴</option>
																				</select>
																			</div>
																		</div>
																	</div>
																</div>
															</div>
															<div class="col-xs-12">
																<div class="form-group">
																	<div class="col-xs-4">
																		<div class="form-group">
																			<label class="control-label col-xs-6">起缴月：</label>
																			<div class="col-xs-6 form-required">
																				<div class="input-group date" edit-role="open modify">
																					<input id='zy-ss-security-start-time' type="text" class="form-control" name="securityStartTime">
																					<span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
																				</div>
																			</div>
																		</div>
																	</div>
																	<div class="col-xs-4">
																		<div class="form-group">
																			<label class="control-label col-xs-6">办理月：</label>
																			<div class="col-xs-6 form-required">
																				<div class="input-group date" edit-role="open modify">
																					<input id='zy-ss-security-activate-time' type="text" class="form-control" name="securityActivateTime">
																					<span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
																				</div>
																			</div>
																		</div>
																	</div>
																</div>
															</div>
															<div class="col-xs-12">
																<div class="form-group">
																	<div class="col-xs-4">
																		<label class="control-label col-xs-6">个人社保基数：</label>
																		<div class="col-xs-6 form-required">
																			<input id='zy-ss-security-base' edit-role="open modify" type="text" class="form-control" name="securityIndividualBase">
																		</div>
																	</div>
																	<div class="col-xs-4">
																		<label class="control-label col-xs-6">企业社保基数：</label>
																		<div class="col-xs-6 form-required">
																			<input id='zy-ss-enterprise-base' edit-role="open modify" type="text" class="form-control" name="securityOrgBase">
																		</div>
																	</div>
																</div>
															</div>
															<div class="col-xs-12">
																<div class="form-group">
																	<label class="control-label col-xs-2">停办月：</label>
																	<div class="col-xs-6 no-gutter">
																		<div class="col-xs-6">
																			<select id='zy-ss-security-end-time-status' edit-role="stop" class="form-control">
																				<option value="0">当月收费</option>
																				<option value="1">当月不收费</option>
																			</select>
																		</div>
																		<div class="col-xs-6">
																			<div class="input-group date" edit-role="stop">
																				<input id='zy-ss-security-end-time' type="text" class="form-control" name="securityEndTime">
																				<span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
																			</div>
																		</div>
																	</div>
																</div>
															</div>
															<div id="zy-ss-security-rules-details" class="col-lg-12" style="display:none;"></div>
															<div id="zy-ss-input-helper-pane" class="col-lg-12">
																<div class="form-group">
																	<div class="col-xs-4">
																		<label class="control-label col-xs-6">定点医院：</label>
																		<div class="col-xs-6">
																			<select class="form-control " edit-role="open modify" name="socialSecurityLocation" data-toggle="select2"></select>
																		</div>
																	</div>

																	<div class="col-xs-4">
																		<label class="control-label col-xs-6">定点医院：</label>
																		<div class="col-xs-6">
																			<select class="form-control " edit-role="open modify" name="socialSecurityLocation" data-toggle="select2"></select>
																		</div>
																	</div>

																	<div class="col-xs-4">
																		<label class="control-label col-xs-6">定点医院：</label>
																		<div class="col-xs-6">
																			<select class="form-control " edit-role="open modify" name="socialSecurityLocation" data-toggle="select2"></select>
																		</div>
																	</div>
																</div>
																<div class="form-group">
																	<div class="col-xs-4">
																		<label class="control-label col-xs-6">定点医院：</label>
																		<div class="col-xs-6">
																			<select class="form-control " edit-role="open modify" name="socialSecurityLocation" data-toggle="select2"></select>
																		</div>
																	</div>

																	<div class="col-xs-4">
																		<label class="control-label col-xs-6">定点医院：</label>
																		<div class="col-xs-6">
																			<select class="form-control " edit-role="open modify" name="socialSecurityLocation" data-toggle="select2"></select>
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
																<input id='zy-ss-gongjijin-switch' edit-role="open modify" type="checkbox" data-checkbox-target="payProvidentFund" checked="checked">
																是否缴纳公积金
															</label>
														</h5>
													</div>
												</legend>
												<div id="zy-ss-gongjijin-pane" class="row" data-box="payProvidentFund">
													<div class="col-xs-12">
														<div class="form-group">
															<div class="col-lg-12">
																<div class="form-group">
																	<div class="col-xs-4" style="display: none;">
																		<label class="control-label col-xs-6">公积金账户：</label>
																		<div class="col-xs-6">
																			<select id="zy-ss-gongjijin-org-account-selector" edit-role="open modify" class="form-control" name="gongjijinOrgAccount">
																			</select>
																		</div>
																	</div>
																	<div class="col-xs-4">
																		<div class="form-group">
																			<label class="control-label col-xs-6">参保类型：</label>
																			<div class="col-xs-6 form-required">
																				<select id='zy-ss-gongjijin_type_selector' edit-role="open modify" class="form-control" name="gongjijinType"></select>
																			</div>
																		</div>
																	</div>
																	<div class="col-xs-4">
																		<div class="from-group">
																			<label class="control-label col-xs-6">公积金账号：</label>
																			<div class="col-xs-6">
																				<input id="zy-ss-gongjijin-account" edit-role="open modify" type="text" class="form-control" name="gongjijinAccount">
																			</div>
																		</div>
																	</div>
																	<div class="col-xs-4">
																		<div class="form-group">
																			<label class="control-label col-xs-6">缴纳方式：</label>
																			<div class="col-xs-6 form-required">
																				<select id='zy-ss-gongjijin-create-type' edit-role="open modify" class="form-control" name="gongjijinCreateType">
																					<option value="0">新开</option>
																					<option value="1">续缴</option>
																				</select>
																			</div>
																		</div>
																	</div>
																</div>
															</div>
															<div class="col-xs-12">
																<div class="form-group">
																	<div class="col-xs-4">
																		<div class="form-group">
																			<label class="control-label col-xs-6">起缴月：</label>
																			<div class="col-xs-6 form-required">
																				<div class="input-group date" edit-role="open modify" >
																					<input id='zy-ss-gongjijin-start-time' type="text" class="form-control" name="gongjijinStartTime">
																					<span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
																				</div>
																			</div>
																		</div>
																	</div>
																	<div class="col-xs-4">
																		<div class="form-group">
																			<label class="control-label col-xs-6">办理月：</label>
																			<div class="col-xs-6 form-required">
																				<div class="input-group date" edit-role="open modify" >
																					<input id='zy-ss-gongjijin-activate-time' type="text" class="form-control" name="gongjijinActivateTime">
																					<span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
																				</div>
																			</div>
																		</div>
																	</div>
																</div>
															</div>
															<div class="col-xs-12">
																<div class="form-group">
																	<div class="col-xs-4">
																		<label class="control-label col-xs-6">个人公积金基数：</label>
																		<div class="col-xs-6 form-required">
																			<input id='zy-ss-gongjijin-base'type="text" edit-role="open modify" class="form-control" name="gongjijinIndividualBase">
																		</div>
																	</div>
																	<div class="col-xs-4">
																		<label class="control-label col-xs-6">企业公积金基数：</label>
																		<div class="col-xs-6 form-required">
																			<input id='zy-ss-org-gongjijin-base'type="text" edit-role="open modify" class="form-control" name="gongjijinOrgBase">
																		</div>
																	</div>
																</div>
															</div>
															<div class="col-xs-12">
																<div class="form-group">
																	<label class="control-label col-xs-2">停办月：</label>
																	<div class="col-xs-6 no-gutter">
																		<div class="col-xs-6">
																			<select id='zy-ss-gongjijin-end-time-status' edit-role="stop" class="form-control">
																				<option value="0">当月收费</option>
																				<option value="1">当月不收费</option>
																			</select>
																		</div>
																		<div class="col-xs-6">
																			<div class="input-group date" edit-role="stop">
																				<input id='zy-ss-gongjijin-end-time' type="text" class="form-control" name="">
																				<span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
																			</div>
																		</div>
																	</div>
																</div>
															</div>
															<div id="zy-ss-gongjijin-rules-details" class="col-lg-12" style="display:none;"></div>
														</div>
													</div>
												</div>
											</fieldset>
										</div>
										<div class="row m-b-md">
											<fieldset>
												<div class="row" id="securityPhotoPanel">
													<script type="text/html" id="socialSecurityPhoto">
														<div class="col-xs-12">
															<legend>
																<div class="col-lg-12 m-b m-t text-info">
																	<h5>
																		证件上传
																	</h5>
																</div>
															</legend>
															<div class="form-group">
																<div class="col-xs-12">
																	<div class="form-group">
																		<label class="control-label col-xs-2">身份证正反面：</label>
																		<div class="col-xs-6 no-gutter">
																			{{if shenfenPhoto}}
																			<a href="{{shenfenPhoto}}" data-gallery="thumb-34" class="pull-left m-r-xs thumb-zone" title="身份证">
																				<img src="{{shenfenPhoto}}" width="34px" alt="">
																			</a>
																			{{else}}
																			<a href="javascript://" data-gallery="thumb-34" class="pull-left m-r-xs thumb-zone" title="身份证">
																				<img src="" width="34px" alt="">
																			</a>
																			{{/if}}
																			<div class="input-group" data-toggle="upload:file" edit-role="open modify">
																				<input type="text" class="form-control" readonly="readonly">
																				<div class="input-group-btn">
																					<div class="btn btn-default">浏览...
																						<input type="file" class="sr-only" name='jobpostFile' accept=".png,.jpeg,.jpg" data-file-type="1">
																					</div>
																				</div>
																			</div>
																			<div class="msg-cover"></div>
																		</div>
																	</div>
																</div>
																<div class="col-xs-12">
																	<div class="form-group">
																		<label class="col-xs-2 control-label">户口本首页：</label>
																		<div class="col-xs-6">
																			{{if hukouIndexPhoto}}
																			<a href="{{hukouIndexPhoto}}" data-gallery="thumb-34" class="pull-left m-r-xs thumb-zone" title="户口本首页">
																				<img src="{{hukouIndexPhoto}}" width="34px" alt="">
																			</a>
																			{{else}}
																			<a href="javascript://" data-gallery="thumb-34" class="pull-left m-r-xs thumb-zone" title="户口本首页">
																				<img src="" width="34px" alt="">
																			</a>
																			{{/if}}
																			<div class="input-group" data-toggle="upload:file" edit-role="open modify">
																				<input type="text" class="form-control" readonly="readonly">
																				<div class="input-group-btn">
																					<div class="btn btn-default">浏览...
																						<input type="file" class="sr-only" name='jobpostFile' accept=".png,.jpeg,.jpg" data-file-type="2">
																					</div>
																				</div>
																			</div>
																			<div class="msg-cover"></div>
																		</div>
																	</div>
																</div>
																<div class="col-xs-12">
																	<div class="form-group">
																		<label class="col-xs-2 control-label">户口本本人页：</label>
																		<div class="col-xs-6">
																			{{if hukouSelfPhoto}}
																			<a href="{{hukouSelfPhoto}}" data-gallery="thumb-34" class="pull-left m-r-xs thumb-zone" title="户口本本人页">
																				<img src="{{hukouSelfPhoto}}" width="34px" alt="">
																			</a>
																			{{else}}
																			<a href="javascript://" data-gallery="thumb-34" class="pull-left m-r-xs thumb-zone" title="户口本本人页">
																				<img src="" width="34px" alt="">
																			</a>
																			{{/if}}
																			<div class="input-group" data-toggle="upload:file" edit-role="open modify">
																				<input type="text" class="form-control" readonly="readonly">
																				<div class="input-group-btn">
																					<div class="btn btn-default">浏览...
																						<input type="file" class="sr-only" name='jobpostFile' accept=".png,.jpeg,.jpg" data-file-type="10">
																					</div>
																				</div>
																			</div>
																			<div class="msg-cover"></div>
																		</div>
																	</div>
																</div>
															</div>
														</div>
													</script>
												</div>
											</fieldset>
										</div>
										<div class="row">
											<div class="col-lg-12 text-center">
                 								<% if(RequestUtil.hasPower("securityprofile_modify_ca")){ %>
												<button id='zy-ss-confirm-save-profile' class="btn btn-info" type="submit">确认参保并返回</button>
												<% } %>
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

		<jsp:include flush="true" page="../common/foot.div.jsp"/>
	</div>
	
	 <!--批量导入弹框myself-->
	<div class="modal fade hmodal-success form-row" id="bachImportModal"
		tabindex="-1" role="dialog" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<!-- <div class="color-line"></div> -->
				<div class="modal-header clearfix">


					<form role="form" class=" form-horizontal formclass" id="importFileForm">
						<div class="form-group">
							<label class="control-label col-xs-4 col-sm-4">社保账户名称：</label>
							<div class=" col-xs-8 col-sm-8">
								<select class="form-control" id="security-account-name" name="securityOrgAccount">
									<option value="">----------</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-xs-4 col-sm-4">社保参保类型：</label>
							<div class="col-xs-8 col-sm-8">
								<select class="form-control" id="security-account-ref-type" name="securityType">
									<option value="">----------</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-xs-4 col-sm-4">公积金账户名称：</label>
							<div class=" col-xs-8 col-sm-8">
								<select class="form-control" id="gjj-account-name" name="gongjijinOrgAccount">
									<option value="">----------</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-xs-4 col-sm-4">公积金参保类型：</label>
							<div class="col-xs-8 col-sm-8">
								<select class="form-control" id="gjj-account-ref-type" name="gongjijinType">
									<option value="">----------</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-lg-4" >上传社保\公积金档案表：</label>
							<div class="col-lg-8">
								<div class="input-group" data-toggle="upload:file">
									<input class="form-control" type="text" readonly="readonly">
									<div class="input-group-btn">
										<span class="btn btn-default"> 
											浏览... 
											<input id="importFile" class="sr-only" type="file" accept=".xlsx" name="importFile">
										</span>
									</div>
								</div>
							</div>
						</div>
						<div class="text-center">
						<button class="btn btn-success" type="button" id="securityFileUpload">上传</button>
						</div>
					</form>

				</div>
			</div>
		</div>
	</div>
	<!--end-->

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
	<script src="static/bootstrap/vendor/datatables/media/js/jquery.dataTables.min.js"></script>
	<script src="static/bootstrap/vendor/datatables_plugins/integration/bootstrap/3/dataTables.bootstrap.min.js"></script>
	<script src="static/bootstrap/vendor/sweetalert/lib/sweet-alert.min.js"></script>
	<script src="static/js/plugins/form.validation/js/formValidation.js"></script>
	<script src="static/js/plugins/form.validation/js/framework/bootstrap.js"></script>
	<script src="static/js/plugins/form.validation/js/language/zh_CN.js"></script>
	<script src="static/bootstrap/scripts/homer.js"></script>
	<script src="static/bootstrap/scripts/charts.js"></script>
	<script src="static/bootstrap/vendor/select2-3.5.2/select2.min.js"></script>
	<script src="static/util/jquery.zhiyang.js"></script>

	<script src="static/js/template.js"></script>

	<script src="static/platform/socialsecurity/securityprofile.js"></script>
	<script src="static/platform/socialsecurity/employeePaymentList.js"></script>
</body>
</html>