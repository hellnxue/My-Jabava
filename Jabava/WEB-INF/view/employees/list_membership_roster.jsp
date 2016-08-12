<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="com.jabava.utils.RequestUtil"%>
<%
String path = request.getContextPath();
String basePath = "//"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
	<base href="${pageContext.request.contextPath}/">
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>员工花名册</title>
	<jsp:include flush="true" page="../common/styles.jsp"></jsp:include>
    <link rel="stylesheet" href="static/js/plugins/form.validation/css/formValidation.css">
	<link rel="stylesheet" href="static/css/user.css">
    <link rel="stylesheet" href="static/css/zTreeStyle/zTreeStyle.css" type="text/css">
</head>
<body >

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
                  员工花名册
                </h2>
              </div>
            </div>
          </div>
        </div>
<!-- 放主要内容 -->
		<div class="content animate-panel mem_content">
			<div class="row">
				<div class="col-lg-12">
					<div class="hpanel">
					
					<!-- 高级搜索 复杂查询开始 -->
					  <div class="collapse out" id="collapseExample" aria-expanded="false" >
						<div class="well well-lg " >
							<div class="row">
								  <form role="form" class="search-form" id="searchForm">
								 
								  <div class="clearfix">
								  
									 <div class="form-group search-unit">
										<label for="exampleInputName2" class="col-lg-4 rost_sousuo">工号：</label>
										<div class="col-lg-8">
										<input type="text" class="form-control" id="exampleInputName2" name="jobNumber">
										</div>
									</div>
									<div class="form-group search-unit">
										<label for="exampleInputName3" class="col-lg-4 rost_sousuo">姓名：</label>
										<div class="col-lg-8">
											<input type="text" class="form-control" id="exampleInputName3" name="employeeName">
										</div>
									</div>
									 <div class="form-group search-unit">
										<label class="col-lg-4 rost_sousuo">所属部门：</label>
										<div class="col-lg-8">
											<input type="text" class="form-control" id="exampleInputName5" name="organizationName">
										</div>
									</div> 
									<div class="form-group search-unit">
										<label for="" class="col-lg-4 rost_sousuo">工作地：</label>
										<div class="col-lg-8">
										<select class="form-control " name="workLocation" placeholder="上海" id="workLocationData">
											 <option value="">全部</option>
										</select>
										</div>
									</div> 
									<div class="form-group search-unit">
										<label class="col-lg-4 rost_sousuo">发薪地：</label>
										<div class="col-lg-8">
										<select class="form-control " name="payrollLocation" placeholder="上海" id="payrollLocationData">
											<option value="">全部</option>
										</select>
										</div>
									</div> 
									 <div class="form-group search-unit">
										<label class="col-lg-4 rost_sousuo">入职时间：</label>
										<div class="input-group date col-lg-8 rost_time">
											 <div class="input-daterange input-group" id="datepicker">
												<input type="text" class="input-sm form-control" name="entryDateStart" />
												<span class="input-group-addon">-</span>
												<input type="text" class="input-sm form-control" name="entryDateEnd" />
											</div>
										</div>
									</div>
									<div class="form-group search-unit">
										<label class="col-lg-4 rost_sousuo">转正时间：</label>
										<div class="input-group date col-lg-8 rost_time">
											 <div class="input-daterange input-group" id="datepicker2">
												<input type="text" class="input-sm form-control" name="positiveDateStart" />
												<span class="input-group-addon">-</span>
												<input type="text" class="input-sm form-control" name="positiveDateEnd" />
											</div>
											
										</div>
									</div>
									<div class="form-group search-unit">
										<label class="col-lg-4 rost_sousuo">合同到期时间：</label>
										<div class="input-group date col-lg-8 rost_time">
											 <div class="input-daterange input-group" id="datepicker3">
												<input type="text" class="input-sm form-control" name="expireDateStart" />
												<span class="input-group-addon">-</span>
												<input type="text" class="input-sm form-control" name="expireDateEnd" />
											</div>
										</div>
									</div>
									<div class="form-group search-unit">
										<label class="col-lg-4 rost_sousuo">在职状态：</label>
										<div class="col-lg-8">
										<select class="form-control " name="status" placeholder="在职">
											<option value="1">在职</option>
											<option value="2">离职</option>
											<option value="3">停职</option>
											<option value="4">退休</option>
											<option value="5">再入职</option>
										</select>
										</div>
									</div> 
									<div class="form-group search-unit">
										<label class="col-lg-4 rost_sousuo">停发标志：</label>
										<div class="col-lg-8">
										<select class="form-control " name="isPayrollFlag" placeholder="全部">
											<option value="" >全部</option>
											<option value="0">在发</option>
											<option value="1">停发</option>
										</select>
										</div>
									</div> 
									
									<div class="form-group search-unit">
										<label for="" class="col-lg-4 rost_sousuo">证书名称：</label>
										<div class="col-lg-8">
										<input type="text" class="form-control" id="" name="certificate">
										</div>
									</div>
								   
									</div>
									<center style=" margin-top:10px;">
									 <button class="btn btn-info" type="button" id="show" onclick="search()">高级搜索</button>
								  </form>
							  </div>
						  </div>
						</div>
				<!-- 高级搜索 复杂查询结束 -->

					 <!--全部导出弹框-->    
				   <div class="modal fade hmodal-success form-row" id="myModal7" tabindex="-1" role="dialog"  aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="color-line"></div>
							<div class="modal-header">
							 <div class="row">
							  <div class="col-sm-11">
							  <form role="form" class=" form-horizontal formclass">
									<img src="static/img/daochu.png" width="220" height="220" style=" margin-left:146px; margin-bottom:40px;">
									<center>
								   <button class="btn btn-warning jxdc" data-dismiss="modal" type="button" onclick="return exportPerson();" title="员工信息导出模板">继续导出</button>&nbsp;&nbsp;
								  
									<button class="btn btn-info guanbi" type="button">取消</button>
									</center>
							  </form>
							  </div>
							</div>   
						</div>
					</div>
				</div>
				</div>
				<!--全部导出弹框 end--> 
			  
				<div class="panel-heading m-b">
					<h4>
						<a href="static/xls/Jabava_roll_list_template.1.0.5.xlsx" class="pull-right small"><span class="text-info">下载花名册模板</span></a>
					</h4>
				</div>
				<div class="panel-body" id="allcheck">
				<table id="example2" class="table table-striped table-bordered table-hover rost_table" style=" width:100%;">
				<thead>
				<tr data-table='list'>
					<th>ID</th>
					<!-- <th>工号</th>
					<th>姓名</th>
					<th>所属部门</th>
					<th>职位</th>
					<th>电话</th>
					<th>邮箱</th> -->
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
	
	<!--转正界面开始-->
	<div class="modal fade hmodal-success form-row" id="myModal8" tabindex="-1" role="dialog"  aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="color-line"></div>
				<div class="modal-header">
				 <div class="row">
				  <div class="col-sm-11">
				  <form role="form" class=" form-horizontal formclass">
						<img src="static/img/daochu.png" width="220" height="220" style=" margin-left:146px; margin-bottom:40px;">
						<center>
					   <button class="btn btn-warning jxdc" data-dismiss="modal" type="button" onclick="downMB('employee/exportPositiveList');" title="员工信息导出模板">继续导出</button>&nbsp;&nbsp;
					  
						<button class="btn btn-info guanbi" type="button">取消</button>
						</center>
				  </form>
				  </div>
				</div>   
			  </div>
		  </div>
		</div>
	</div>


	<div class="content animate-panel mem_zhuanzheng">
	
			<div class="row">
				<div class="col-lg-12">
					<div class="hpanel">
					
						<div class="panel-heading clearfix">
							<div class="pull-right">
								<button class="btn btn-warning btn-xs" type="button" data-target="#myModal8" data-toggle="modal">
									<i class=""></i> <span class="bold">全部导出</span>
								</button>
								<button class="btn btn-primary btn-xs mem_fanhui" type="button">
									<i class=""></i> <span class="bold">返 回</span>
								</button>
							</div>
						</div>
						
						<div class="panel-body" id="allcheck2">
							
							<table id="example3" class="table table-striped table-bordered table-hover rost_table" style=" width:100%;">
								<thead>
									<tr>
										<th>ID</th>
										<th>工号</th>
										<th>姓名</th>
										<th>所属部门</th>
										<th>职位</th>
										<th>电话</th>
										<th>邮箱</th>
										<th>转正日期</th>
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
<!--转正界面结束-->
	
	
<!--合同到期界面开始-->
	<div class="modal fade hmodal-success form-row" id="myModal9" tabindex="-1" role="dialog"  aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="color-line"></div>
				<div class="modal-header">
				 <div class="row">
				  <div class="col-sm-11">
				  <form role="form" class=" form-horizontal formclass">
						<img src="static/img/daochu.png" width="220" height="220" style=" margin-left:146px; margin-bottom:40px;">
						<center>
					   <button class="btn btn-warning jxdc" type="button" data-dismiss="modal" onclick="downMB('employee/exportContractList');" title="员工信息导出模板">继续导出</button>&nbsp;&nbsp;
					  
						<button class="btn btn-info guanbi" type="button">取消</button>
						</center>
				  </form>
				  </div>
				</div>   
			  </div>
		  </div>
		</div>
	</div>

	<div class="content animate-panel mem_daoqi">
			<div class="row">
				<div class="col-lg-12">
					<div class="hpanel">
						
						<div class="panel-heading clearfix">
							<div class="pull-right">
								<button class="btn btn-warning btn-xs" type="button" data-target="#myModal9" data-toggle="modal">
									<i class=""></i> <span class="bold">全部导出</span>
								</button>
								<button class="btn btn-primary btn-xs mem_fanhui" type="button">
									<i class=""></i> <span class="bold">返 回</span>
								</button>
							</div>
						</div>
						
						<div class="panel-body" id="allcheck3">
							<table id="example4" class="table table-striped table-bordered table-hover rost_table" style=" width:100%;">
								<thead>
									<tr>
										<th>ID</th>
										<th>工号</th>
										<th>姓名</th>
										<th>所属部门</th>
										<th>职位</th>
										<th>电话</th>
										<th>邮箱</th>
										<th>合同到期时间</th>
										<th>意见征询</th>
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
<!--合同到期界面结束-->
	
<!--今天员工生日界面开始-->

	<div class="modal fade hmodal-success form-row" id="myModal10" tabindex="-1" role="dialog"  aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="color-line"></div>
				<div class="modal-header">
				 <div class="row">
				  <div class="col-sm-11">
				  <form role="form" class=" form-horizontal formclass">
						<img src="static/img/daochu.png" width="220" height="220" style=" margin-left:146px; margin-bottom:40px;">
						<center>
					   <button class="btn btn-warning jxdc" type="button" data-dismiss="modal" onclick="downMB('employee/birthdayList');" title="员工信息导出模板">继续导出</button>&nbsp;&nbsp;
					  
						<button class="btn btn-info guanbi" type="button">取消</button>
						</center>
				  </form>
				  </div>
				</div>   
			  </div>
		  </div>
		</div>
	</div>

	<div class="content animate-panel mem_jshengri">
			<div class="row">
				<div class="col-lg-12">
					<div class="hpanel">
						
						<div class="panel-heading clearfix">
							<div class="pull-right">
								<button class="btn btn-warning btn-xs" type="button" data-target="#myModal10" data-toggle="modal">
									<i class=""></i> <span class="bold">全部导出</span>
								</button>
								<button class="btn btn-primary btn-xs mem_fanhui" type="button">
									<i class=""></i> <span class="bold">返 回</span>
								</button>
							</div>
						</div>
						
						<div class="panel-body" id="allcheck4">
							<table id="example5" class="table table-striped table-bordered table-hover rost_table" style=" width:100%;">
								<thead>
									<tr>
										<th>ID</th>
										<th>工号</th>
										<th>姓名</th>
										<th>所属部门</th>
										<th>职位</th>
										<th>电话</th>
										<th>邮箱</th>
										<th>出生日期</th>
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
<!--今天员工生日界面结束-->

	<!-- 新增高级搜索 -->
	<form role="form" class="form-horizontal hidden" data-template="advanced-search" id="highSearch">
	     <input type="hidden" id="countType" name="countType" value="${param.countType }">
		<div class="col-md-12">
			<div class="form-group">
				<div id="adv-search" class="input-group">
					<input type="text" placeholder="输入关键字，按回车搜索，点下拉按钮显示高级搜索" class="form-control" id="highInput">
					<div class="input-group-btn dropdown dropdown-lg dropdownself" >
						<button aria-expanded="false" aria-haspopup="true" class="btn btn-default dropdown-toggle dropdownselfbtn" type="button">筛选 <span class="caret"></span></button>
						<div role="menu" class="dropdown-menu dropdown-menu-right">
							<div class="col-sm-7 col-md-7 col-lg-7 m-t">
								<div class="form-group">
									<label class="col-xs-3 col-sm-3 col-md-3 col-lg-3 control-label" for="">入职时间</label>
									<div class="col-sm-9 col-md-9 col-lg-9 form-dash">
										<div class="input-group date" data-date-format="yyyy-mm-dd">
											<input type="text" value="" name="entryDateStart" class="form-control">
											<span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
										</div>
									</div>
								</div>
							</div>
							<div class="col-sm-5 col-md-5 col-lg-5 m-t">
								<div class="form-group">
									<div class="col-sm-12 col-md-12 col-lg-12">
										<div class="input-group date" data-date-format="yyyy-mm-dd">
											<input type="text" value="" name="entryDateEnd" class="form-control">
											<span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
										</div>
									</div>
								</div>
							</div>
							<div class="col-sm-7 col-md-7 col-lg-7 m-t">
								<div class="form-group">
									<label class="col-xs-3 col-sm-3 col-md-3 col-lg-3 control-label" for="">转正时间</label>
									<div class="col-sm-9 col-md-9 col-lg-9 form-dash">
										<div class="input-group date" data-date-format="yyyy-mm-dd">
											<input type="text" value="" name="positiveDateStart" class="form-control">
											<span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
										</div>
									</div>
								</div>
							</div>
							<div class="col-sm-5 col-md-5 col-lg-5 m-t">
								<div class="form-group">
									<div class="col-sm-12 col-md-12 col-lg-12">
										<div class="input-group date" data-date-format="yyyy-mm-dd">
											<input type="text" value="" name="positiveDateEnd" class="form-control">
											<span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
										</div>
									</div>
								</div>
							</div>
							<div class="col-sm-7 col-md-7 col-lg-7 m-t">
								<div class="form-group">
									<label class="col-xs-3 col-sm-3 col-md-3 col-lg-3 control-label" for="">合同到期</label>
									<div class="col-sm-9 col-md-9 col-lg-9 form-dash">
										<div class="input-group date" data-date-format="yyyy-mm-dd">
											<input type="text" value="" name="expireDateStart" class="form-control">
											<span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
										</div>
									</div>
								</div>
							</div>
							<div class="col-sm-5 col-md-5 col-lg-5 m-t" >
								<div class="form-group">
									<div class="col-sm-12 col-md-12 col-lg-12">
										<div class="input-group date" data-date-format="yyyy-mm-dd">
											<input type="text" value="" name="expireDateEnd" class="form-control">
											<span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
										</div>
									</div>
								</div>
							</div>
							<div class="col-sm-8 col-md-8 col-lg-8 m-t" id="sopstatus">
								<div class="form-group">
									<label class="col-xs-3 col-sm-3 col-md-3 col-lg-3 control-label" for="">在职状态</label>
								<div class="col-sm-9 col-md-9 col-lg-9 form-control-static"  >
									<label class="checkbox-inline ">							
										<input type="checkbox"   value="1" name="status"> 在职						
									</label>								   
									<label class="checkbox-inline ">							
										<input type="checkbox"   value="2" name="status"> 离职
									</label>								   
									<label class="checkbox-inline ">
										<input type="checkbox"   value="3" name="status"> 停职
									</label>
									<label class="checkbox-inline ">							
										<input type="checkbox"   value="4" name="status"> 退休							
									</label>								   
									<label class="checkbox-inline ">							
										<input type="checkbox"   value="5" name="status"> 再入职							
									</label>
								</div>
							</div>
							</div>
							<div class="col-sm-8 col-md-8 col-lg-8 m-b">
								<div class="form-group">
									<label class="col-xs-3 col-sm-3 col-md-3 col-lg-3 control-label" for="">停发状态</label>
									<div class="col-sm-9 col-md-9 col-lg-9 form-control-static" >
										<label class="checkbox-inline ">									
											<input type="checkbox"   value="0" name="isPayrollFlag"> 未停发
										</label>										   
										<label class="checkbox-inline ">									
											<input type="checkbox"   value="1" name="isPayrollFlag"> 停发									
										</label>
									</div>
								</div>
	   						  </div>
							 
							<div class="col-sm-4 col-md-4 col-lg-4 m-b text-right btn-myself">
								<button class="btn btn-success " type="submit">搜索</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
    <!-- UI Components -->
    <div class="modal fade" data-modal="trial" data-modal-for="transfer">
        <div class="modal-dialog">
            <div class="modal-content">
                <form action="" method="POST" class="form-horizontal" role="form">
                    <input type="hidden" name="contractId" data-trial="contractID">
                    <input type="hidden" name="personId" data-trial="personID">
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-md-6 col-lg-6">
                                <div class="form-group">
                                    <label for="" class="control-label col-md-6">姓名：</label>
                                    <div class="col-md-6">
                                        <p class="form-control-static" data-trial="name"></p>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6 col-lg-6">
                                <div class="form-group">
                                    <label for="" class="control-label col-md-6">部门：</label>
                                    <div class="col-md-6">
                                        <p class="form-control-static" data-trial="orgName"></p>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6 col-lg-6">
                                <div class="form-group">
                                    <label for="" class="control-label col-md-6">手机：</label>
                                    <div class="col-md-6">
                                        <p class="form-control-static" data-trial="mobile"></p>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6 col-lg-6">
                                <div class="form-group">
                                    <label for="" class="control-label col-md-6">岗位：</label>
                                    <div class="col-md-6">
                                        <p class="form-control-static" data-trial="position" data-base-data="positionList" data-base-id="traverseId"></p>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-12 col-lg-12">
                                <div class="form-group">
                                    <label for="" class="control-label col-md-3">计划转正时间：</label>
                                    <div class="col-md-9">
                                        <div class="input-group">
                                            <input type="date" class="form-control" readonly="readonly" data-trial="planPositiveDate">
                                            <div class="input-group-addon"><i class="glyphicon glyphicon-th"></i></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-12 col-lg-12">
                                <div class="form-group">
                                    <label for="" class="control-label col-md-3">实际转正时间：</label>
                                    <div class="col-md-9 form-required">
                                        <div class="input-group date">
                                            <input type="text" class="form-control" name="factPositiveDate">
                                            <div class="input-group-addon"><i class="glyphicon glyphicon-th"></i></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-12 col-lg-12">
                                <div class="form-group">
                                    <label for="" class="control-label col-md-3">备注</label>
                                    <div class="col-md-9">
                                        <textarea class="form-control" name="memo"></textarea>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-success">确认办理</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <div class="modal fade" data-modal="jobtransfer" data-modal-for="transfer">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <form action="" method="POST" class="form-horizontal" role="form">
                	<input type="hidden" name="personId" data-jobtransfer="personId">
                    <div class="modal-body">
                        <div class="row">
                        	<div class="col-md-6 col-lg-6">
                        		<div class="col-md-12 col-lg-12">
                        			<div class="form-group">
                        				<label class="control-label col-md-4 col-lg-4" for="">原部门：</label>
                        				<div class="col-md-7 col-lg-7">
                        					<p class="form-control-static" data-jobtransfer="organizationName"></p>
                    					</div>
                    				</div>
                    			</div>
                    			<div class="col-md-12 col-lg-12">
                    				<div class="form-group">
                    					<label class="control-label col-md-4 col-lg-4" for="">原岗位：</label>
                    					<div class="col-md-7 col-lg-7">
                    						<p class="form-control-static" data-jobtransfer="positionId" data-base-data="post" data-base-id="traverseId"></p>
                    					</div>
                					</div>
                				</div>
                				<div class="col-md-12 col-lg-12">
                					<div class="form-group">
                						<label class="control-label col-md-4 col-lg-4" for="">原职级：</label>
                						<div class="col-md-7 col-lg-7">
                							<p class="form-control-static" data-jobtransfer="levelId" data-base-data="rank" data-base-id="traverseId"></p>
                						</div>
            						</div>
            					</div>
        						<div class="col-md-12 col-lg-12">
        							<div class="form-group">
        								<label class="control-label col-md-4 col-lg-4" for="">原汇报对象：</label>
        								<div class="col-md-7 col-lg-7">
        									<p class="form-control-static" data-jobtransfer="reportPerson"></p>
        								</div>
        							</div>
        						</div>
        						<div class="col-md-12 col-lg-12">
        							<div class="form-group">
        								<label class="control-label col-md-4 col-lg-4" for="">原工作地：</label>
        								<div class="col-md-7 col-lg-7">
            								<p class="form-control-static" data-jobtransfer="workLocation" data-base-data="city"></p>
            							</div>
            						</div>
            					</div>
            					<div class="col-md-12 col-lg-12">
            						<div class="form-group">
            							<label class="control-label col-md-4 col-lg-4" for="">原成本中心：</label>
            							<div class="col-md-7 col-lg-7">
            								<p class="form-control-static" data-jobtransfer="costCenterId" data-base-data="cost" data-base-id="traverseId"></p>
            							</div>
            						</div>
            					</div>
            				</div>
            				<div class="col-md-6 col-lg-6">
            					<div class="col-md-12 col-lg-12">
            						<div class="form-group">
            							<label class="control-label col-md-4 col-lg-4" for="">现部门：</label>
            							<div class="col-md-7 col-lg-7">
            								<div class="form-required">
            									<input type="hidden" value="" name="newDepartment" data-department-id="">
            									<div class="input-group">
            										<input type="text" readonly="readonly" name="addNewDepartment" class="form-control">
            										<span class="input-group-btn">
            											<button data-target="[data-modal=organization]" data-toggle="modal" class="btn btn-default" type="button"><i class="fa fa-search"></i></button>
            										</span>
            									</div>
            								</div>
            							</div>
            						</div>
            					</div>
            					<div class="col-md-12 col-lg-12">
            						<div class="form-group">
            							<label class="control-label col-md-4 col-lg-4" for="">现岗位：</label>
            							<div class="col-md-7 col-lg-7">
            								<select name="newPost" class="form-control" data-jobtransfer="newPost" data-base-data="post" data-base-id="traverseId">
            								</select>
            							</div>
            						</div>
            					</div>
            					<div class="col-md-12 col-lg-12">
            						<div class="form-group">
            							<label class="control-label col-md-4 col-lg-4" for="">现职级：</label>
            							<div class="col-md-7 col-lg-7">
            								<select name="newRank" class="form-control" data-jobtransfer="newRank" data-base-data="rank" data-base-id="traverseId">
            								</select>
            							</div>
            						</div>
            					</div>
            					<div class="col-md-12 col-lg-12">
            						<div class="form-group">
            							<label class="control-label col-md-4 col-lg-4" for="">当前汇报对象：</label>
            							<div class="col-md-7 col-lg-7">
            								<input type="text" name="newReport" id="" class="form-control">
            							</div>
            						</div>
            					</div>
            					<div class="col-md-12 col-lg-12">
            						<div class="form-group">
            							<label class="control-label col-md-4 col-lg-4" for="">岗位调整日期：</label>
            							<div class="col-md-7 col-lg-7">
            								<div class="input-group date" data-init-start-date="1">
            									<input type="text" name="newDate" id="" class="form-control">
            									<span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
            								</div>
            							</div>
            						</div>
            					</div>
            					<div class="col-md-12 col-lg-12">
            						<div class="form-group">
            							<label class="control-label col-md-4 col-lg-4" for="">当前工作地：</label>
            							<div class="col-md-7 col-lg-7">
            								<select data-toggle="select2" name="newLocation" class="form-control" data-jobtransfer="newLocation" data-base-data="city">
            								</select>
            							</div>
            						</div>
            					</div>
            					<div class="col-md-12 col-lg-12">
            						<div class="form-group">
            							<label class="control-label col-md-4 col-lg-4" for="">当前成本中心：</label>
            							<div class="col-md-7 col-lg-7">
            								<select name="newCost" class="form-control" data-jobtransfer="newCost" data-base-data="cost" data-base-id="traverseId"> 
            								</select>
            							</div>
            						</div>
            					</div>
                            </div>
                            <div class="col-md-12 col-lg-12">
            					<div class="col-md-12 col-lg-12">
            						<div class="form-group">
            							<label class="control-label col-md-2 col-lg-2" for="">是否调薪：</label>
            							<div class="col-md-10 col-lg-10">
            								<div class="radio-group form-control-static">
            									<label class="radio-inline">
            										<input type="radio" checked="checked" value="1" name="isChangeSalary"> 是
            									</label>
            									<label class="radio-inline">
            										<input type="radio" value="0" name="isChangeSalary"> 否
            									</label>
            								</div>
            							</div>
            						</div>
            					</div>
            					<div class="col-md-12 col-lg-12">
            						<div class="form-group">
            							<label class="control-label col-md-2 col-lg-2" for="">岗位调动原因：</label>
            							<div class="col-md-10 col-lg-10">
            								<textarea name="cause" class="form-control" rows="3"></textarea>
            							</div>
            						</div>
            					</div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-success">确认办理</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <div class="modal fade hmodal-success" data-modal="organization" data-modal-for="transfer" tabindex="-1" role="dialog"  aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="color-line"></div>
                <div class="modal-body">
                    <div class="dd" id="nestable_third">

                        <div class="zTreeDemoBackground">
                            <ul id="orgTree" class="ztree"></ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="modal fade" data-modal="dimission" data-modal-for="transfer">
        <div class="modal-dialog">
            <div class="modal-content">
                <form action="" method="POST" class="form-horizontal" role="form">
                	<input type="hidden" name="personId" data-dimission="personId">
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-md-6 col-lg-6">
                                <div class="form-group">
                                    <label for="" class="control-label col-md-6">姓名：</label>
                                    <div class="col-md-6">
                                        <p class="form-control-static" data-dimission="employeeName"></p>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6 col-lg-6">
                                <div class="form-group">
                                    <label for="" class="control-label col-md-6">部门：</label>
                                    <div class="col-md-6">
                                        <p class="form-control-static" data-dimission="organizationName"></p>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6 col-lg-6">
                                <div class="form-group">
                                    <label for="" class="control-label col-md-6">手机：</label>
                                    <div class="col-md-6">
                                        <p class="form-control-static" data-dimission="mobile"></p>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6 col-lg-6">
                                <div class="form-group">
                                    <label for="" class="control-label col-md-6">岗位：</label>
                                    <div class="col-md-6">
                                        <p class="form-control-static" data-dimission="postId" data-base-data="positionList" data-base-id="traverseId"></p>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-12 col-lg-12">
                                <div class="form-group">
                                    <label for="" class="control-label col-md-3">离职时间：</label>
                                    <div class="col-md-9 form-required">
                                        <div class="input-group date" data-init-start-date="1">
                                            <input type="text" class="form-control" name="dimissionDate">
                                            <div class="input-group-addon"><i class="glyphicon glyphicon-th"></i></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-12 col-lg-12">
                                <div class="form-group">
                                    <label for="" class="control-label col-md-3">薪资结算日：</label>
                                    <div class="col-md-9 form-required">
                                        <div class="input-group date">
                                            <input type="text" class="form-control" name="salarySettleDate">
                                            <div class="input-group-addon"><i class="glyphicon glyphicon-th"></i></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-12 col-lg-12">
                                <div class="form-group">
                                    <label for="" class="control-label col-md-3">离职原因：</label>
                                    <div class="col-md-9 form-required">
                                        <select name="dimissionCause" class="form-control" data-dimission="dimissionCause" data-base-data="dimissionCauseList" data-base-id="traverseId" > 
            							</select>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-12 col-lg-12">
                                <div class="form-group">
                                    <label for="" class="control-label col-md-3">备注</label>
                                    <div class="col-md-9">
                                        <textarea class="form-control" name="memo"></textarea>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-danger">确认办理</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <!-- // UI Components -->

    
	<!-- Footer-->
	<!-- 放页脚  开始-->
	<jsp:include flush="true" page="../common/foot.div.jsp"></jsp:include>
	<!-- 放页脚  结束-->
	
<!-- 员工停发标志 -->
 
</div>

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
<script src="static/bootstrap/vendor/bootstrap-datepicker-master/dist/locales/bootstrap-datepicker.zh-CN.min.js"></script>
<script src="static/bootstrap/vendor/moment/moment.js"></script>
<script src="static/bootstrap/vendor/datatables/media/js/jquery.dataTables.min.js"></script>
<script src="static/bootstrap/vendor/datatables_plugins/integration/bootstrap/3/dataTables.bootstrap.min.js"></script>
<script src="static/bootstrap/vendor/sweetalert/lib/sweet-alert.min.js"></script>
<script src="static/js/plugins/form.validation/js/formValidation.js"></script>
<script src="static/js/plugins/form.validation/js/framework/bootstrap.js"></script>
<script src="static/js/plugins/form.validation/js/language/zh_CN.js"></script> 
<script src="static/bootstrap/scripts/homer.js"></script>
	<script src="static/js/template.js"></script>
<script src="static/js/list_membership_roster.js"></script>
<script type="text/javascript" src="static/js/zTreeJs/zTreeJs/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="static/js/zTreeJs/zTreeJs/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript" src="static/js/zTreeJs/zTreeJs/jquery.ztree.exedit-3.5.js"></script>
	<script>
		var table;
		var initParamJson={};//搜索对象
		var searchColumn=null;//搜索时的动态列参数
		
		
		
		
		// 程序主入口
		$(function () {
			
			//获取动态初始化dataTable
			getDynamicFields();
			
			
			initParamJson.data=JSON.stringify($('#searchForm').serializeObject());
			initParamJson.searchData=JSON.stringify($('#highSearch').serializeObject());
			// 跳转到员工详情页
			$('#example2').on('click','.view-button',function(){
				var personId = $(this).data('personid');
				var href = 'employee/employeeInformation?personId='+personId;
				window.location.href=href;
			});
		
			/* table = buildExample2DataTable(initParamJson); */

			  importExport();  
			// 删除选中的ITEM
			$("div.toolbar").on('click','.del-button',function(e){
				var $items =$('#example2').find('input[name="checkAll"]:checked');
				var ids = [];
				$.each($items,function(idx,item){
					var $item = $(item);
					ids.push($item.val());
				});
			});


			$.ajax({
				cache:true,
				type:"POST",
				url:"employee/searchPositive.do",
				dataType:'json',
				async:false,
				success: function(data){	 
					$("#zhuanzheng").html(data.positiveCount);
					$("#daoqi").html(data.contractCount);
					$("#birthD").html(data.birthCount);
					$("#birthT").html(data.birthTCount);
			  	}
	    	});

			$('.mem_fanhui').click(function(){
				$('.content').hide();
				$('.mem_content').show();
			})

			$(".guanbi").click(function(){
				$("#myModal7").modal('hide');
				$("#myModal8").modal('hide');
				$("#myModal9").modal('hide');
				$("#myModal10").modal('hide');
				$("#myModal11").hide();
			});

			$('#datepicker').datepicker();
			$("#datepicker").on("changeDate", function(event) {
				$("#my_hidden_input").val(
					$("#datepicker").datepicker('getFormattedDate')
				)
			});

			$('#datepicker2').datepicker();
			$("#datepicker2").on("changeDate", function(event) {
				$("#my_hidden_input").val(
						$("#datepicker2").datepicker('getFormattedDate')
				)
			});

			$('#datepicker3').datepicker();
			$("#datepicker3").on("changeDate", function(event) {
				$("#my_hidden_input").val(
						$("#datepicker3").datepicker('getFormattedDate')
				)
			});

			$('#datapicker2').datepicker();
			$('.input-group.date').datepicker({
				autoclose: true
			});
			$('.input-daterange').datepicker({ });

			$("#demo1").TouchSpin({
				min: 0,
				max: 100,
				step: 0.1,
				decimals: 2,
				boostat: 5,
				maxboostedstep: 10,
			});
	
			$("#demo2").TouchSpin({
				verticalbuttons: true
			});
	
			$("#demo3").TouchSpin({
				postfix: '%'
			});
	
			$("#demo4").TouchSpin({
				postfix: "a button",
				postfix_extraclass: "btn btn-default"
			});
	
			$(".js-source-states").select2();
			$(".js-source-states-2").select2();
			$.fn.editable.defaults.mode = 'inline';
			$.fn.editable.defaults.url = '#';
	
			//editables
			$('#username').editable({
				url: '#',
				type: 'text',
				pk: 1,
				name: 'username',
				title: 'Enter username'
			});
			
			$('#sex').editable({
				prepend: "not selected",
				source: [
					{value: 1, text: 'Male'},
					{value: 2, text: 'Female'}
				],
				display: function(value, sourceData) {
					var colors = {"": "gray", 1: "green", 2: "blue"},
							elem = $.grep(sourceData, function(o){return o.value == value;});
	
					if(elem.length) {
						$(this).text(elem[0].text).css("color", colors[value]);
					} else {
						$(this).empty();
					}
				}
			});
	
			$('#dob').editable();
	
			$('#event').editable({
				placement: 'right',
				combodate: {
					firstItem: 'name'
				}
			});
	
			$('#comments').editable({
				showbuttons: 'bottom'
			});
	
			$('#fruits').editable({
				pk: 1,
				limit: 3,
				source: [
					{value: 1, text: 'banana'},
					{value: 2, text: 'peach'},
					{value: 3, text: 'apple'},
					{value: 4, text: 'watermelon'},
					{value: 5, text: 'orange'}
				]
			});
	
			$('#user .editable').on('hidden', function(e, reason){
				if(reason === 'save' || reason === 'nochange') {
					var $next = $(this).closest('tr').next().find('.editable');
					if($('#autoopen').is(':checked')) {
						setTimeout(function() {
							$next.editable('show');
						}, 300);
					} else {
						$next.focus();
					}
				}
			});
	

		});
		

		// 写到公共的库中去
		$.fn.serializeObject = function(){  
		    var o = {};  
		    var a = this.serializeArray();
	
		    $.each(a, function() {  
			   if (o[this.name]) {  
				   if (!o[this.name].push) {  
					   o[this.name] = [o[this.name]];  
				   }  
				   o[this.name].push(this.value || '');  
			   } else {  
				   o[this.name] = this.value || '';  
			   }
		    });
	
		    return o;  
		}; 
		
		/**
		  获取动态配置的显示列
		**/
		function getDynamicFields(){
			 
			$.getJSON("employee/getCustomFieldAndDisplayConfig").done(function(data){
				
				if(data&&data.Fileds){
					initDetailTable(data);
				}
			}) ;
			
		}
		/**
		 根据动态列组装表头和column
		**/
		function initDetailTable(filedDataObj){
			var columns=[
		                    { "data": "person_id", render: function(data, type, row, meta){
		                        var strHtml = '<input type="checkbox" name="" data-check="item" value="'+data+'">';
		                        return strHtml;
		                    },
		                    createdCell: function (td, cellData, rowData, row, col){
		                        var ueSelected = function($o, bSelectLast){
		                        	// bSelectLast: default true [single] (Other values: false [multiple] )
		                            bSelectLast = bSelectLast === undefined? true : bSelectLast;
		                            var getAllVal = [];
		                            if(bSelectLast){
		                                $('[data-check="item"]').not($o).prop('checked', false);
		                                getAllVal.push( $o.val() );
		                            }else{
		                                $.each($('[data-check="item"]:checked'), function(index, item) {
		                                	getAllVal.push( $(this).val() );
		                                });
		                            }
		                            getAllVal = getAllVal.join(',');
		                            return getAllVal;
		                        };
		                        $(td).on('click', '[data-check="item"]', function(event) {
		                            var $getCheckItem = $(this);
		                            if( $getCheckItem.prop('checked') ){
		                                var getIDs = ueSelected($getCheckItem);
		                            	$('[data-modal-for="transfer"]').data('itemsId', getIDs);
		                            }else{
		                            	$('[data-modal-for="transfer"]').removeData('itemsId');
		                            }
		                        });
		                    }},
						 
							             
						];
			
			
			//表头&列信息
			$.each(filedDataObj.Fileds,function(index,obj){
				
				$("[data-table='list']").append("<th>"+obj.displayName+"</th>");
				
				 var columnObj={
						
						data:obj.columnName
				};  
				
				//超链接字段设置
				if(filedDataObj.linkFiled&&(obj.columnName===filedDataObj.linkFiled)){
					columnObj.render=function(data, type, row, meta){
						var href = 'employee/employeeInformation?personId='+row.person_id;
               		<% if(RequestUtil.hasPower("roster_vi")){ %>
						return '<a href="'+href+'">'+row.employee_name+'</a>';
						<% }else{ %>
						return row.employee_name;
						<% } %>
					};
				}
				
				columns.push(columnObj);
				 
			});
			
			searchColumn=columns;
			table = buildExample2DataTable(initParamJson,columns);
		}
		
		//初始化列表
		// TODO 需要重构
		function buildExample2DataTable(params,column){
			var getAdvancedSearchTemplate = $('[data-template=advanced-search]');
			if(table){
				table.destroy();
				
			}
			table = $('#example2').DataTable({
				"dom":
                "<'row'<'col-sm-3'l><'col-sm-4 advanced-search'><'col-sm-5'<'toolbar text-right'>>>" +
                "<'row'<'col-sm-12'tr>>" +
                "<'row'<'col-sm-5'i><'col-sm-7'p>>",
				drawCallback:function(){
					$('.demo4').click(function () {
						var personId = $(this).attr("personId");
						swal({
							title: "确定要删除此用户信息吗?",
							text: "注意：用户信息删除后将不可修复!",
							type: "warning",
							showCancelButton: true,
							confirmButtonColor: "#DD6B55",
							confirmButtonText: "是,请删除该用户信息!",
							cancelButtonText: "不, 放弃此操作!",
							closeOnConfirm: false,
							closeOnCancel: false },
							function (isConfirm) {
								if (isConfirm) {
									$.ajax({
										type : "POST",
										url : "employee/deletePerson.do",
										data : {"personId": personId},
										dataType : "json",
										success : function(data) {
											if (data.success == 1) {
												swal("删除成功", "该用户信息已经被删除.", "success");
												window.location.reload();
											}else if(data.success == 3){
												swal("删除失败", "已有工资记录，删除失败.", "error");
											}
										}
									});
								} else {
									swal("已取消", "用户信息未删除。", "error");
								}
								
							});
						});
					},
				/*删除结束*/
				"processing": true,
				"serverSide": true,
		    	"bDestroy": true,
				"sort":false,
				"columns": column,
				"columnDefs": [
	    			{defaultContent: '', targets: '_all'}
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
				   "ajax":{
					"url": "employee/empdataTableSearch",
					"type":"POST",
					"data": params ? params : {}

				}   
			});
			
			highSearch(getAdvancedSearchTemplate);

			return table;
			
		}
		
		/**
		 筛选框搜索功能
		**/
		function highSearch(getAdvancedSearchTemplate){
			
			 //初始化筛选部分html
			 $('.advanced-search').html( getAdvancedSearchTemplate );
			 
			  
				$('.input-group.date').datepicker({
					autoclose: true
				});
				$('.input-daterange').datepicker({ });
			 
			  //筛选按钮点击事件
			  $('.dropdown').on('click', '.dropdownselfbtn', function(event) {
				event.preventDefault();
				var getParent = $(this).parents('.dropdown');
				!getParent.hasClass('open') ? getParent.addClass('open'): getParent.removeClass('open');
			});  
			$('[data-template=advanced-search]').removeClass('hidden');
			
			//高级搜索
			  $("#highSearch").on("submit",function(event){
					event.preventDefault();
					
					$('.dropdownself').removeClass('open');
					
				    initParamJson.searchValue=$("#highInput").val();
				    
				    var formJson=$("#highSearch").serializeObject();
				    
				    initParamJson.searchData=JSON.stringify(formJson);
				    
				    var $getToolbar = $('.toolbar').children();
				    buildExample2DataTable(initParamJson,searchColumn);
				    highSearch($('[data-template=advanced-search]'));
				    $('.toolbar').html($getToolbar);
				    
			}); 
			
			  importExport(); 
			
		}
		
		//导入导出按钮
		function importExport(){
			var strHtml = '';
                strHtml += '<div class="btn-group">';
                strHtml += '  <button type="button" class="btn btn-info btn-sm dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" data-modal-related="transfer">人员变动 <span class="caret"></span>';
                strHtml += '  </button>';
                strHtml += '  <ul class="dropdown-menu">';
                strHtml += '    <li><a href="javascript://" data-modal-toggle="manually" data-target="[data-modal=trial]">人员转正</a></li>';
                strHtml += '    <li><a href="javascript://" data-modal-toggle="manually" data-target="[data-modal=jobtransfer]">人员调配</a></li>';
                strHtml += '    <li><a href="javascript://" data-modal-toggle="manually" data-target="[data-modal=dimission]">人员离职</a></li>';
                strHtml += '  </ul>';
                strHtml += '</div>';
                strHtml += '	<div class="btn-group">';
                strHtml += '  <button type="button" class="btn btn-info btn-sm dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" data-modal-related="transfer">显示设置 <span class="caret"></span>';
                strHtml += '  </button>';
                strHtml += '  <ul class="dropdown-menu">';
                strHtml += '    <li><a href="system/toListFieldDisplayConfig">列表显示设置</a></li>';
                //strHtml += '    <li><a href="javascript://">卡片显示设置</a></li>';
                strHtml += '  </ul>';
                strHtml += '</div>';
            <% if(RequestUtil.hasPower("roster_im")){ %>
            strHtml += '&nbsp;<div class="btn btn-success btn-sm  rost_piliang">批量导入<form id="ppp"><input id="importFile" name="importFile" class="rost_daoru" type="file" onchange="upload();" multiple></form></div>&nbsp;';
            <% } %>
            <% if(RequestUtil.hasPower("roster_ex")){ %>
			strHtml += '<button class="btn btn-info btn-sm" type="button" data-target="#myModal7"  data-toggle="modal"><i class=""></i> <span class="bold">导　 出</span></button>';
			<% } %>
			 $("div.toolbar").html(strHtml);
			 window.renderReady = true;
		}

		
	    function search(){
			$('#example2').dataTable().api().ajax.reload();
		}

		function positive(){
			$('#example3').dataTable().api().ajax.reload();
			$(".content").hide();
			$(".mem_zhuanzheng").show();	
		}

		function contract() {
			$('#example4').dataTable().api().ajax.reload();
			$(".content").hide();
			$(".mem_daoqi").show();
		}

		function birth(day){
			console.log(day);
			$("#birthInput").val(day);
			$('#example5').dataTable().api().ajax.reload();
			$('.content').hide();
			$('.mem_jshengri').show();
		}

		function updateFalg(id){
			 $.ajax({
				url:"employee/updateFalg.do",
				dataType:'json',
				data:{"contractId":id},
				success: function(data){	 
					$('#example4').dataTable().api().ajax.reload();
					var daoqi =  $('#daoqi').text();
					var hetong = parseInt(daoqi);
					$('#daoqi').text(hetong-1);
				}
			});
		}
	
		function upload(){
			var ajaxCallUrl = "employee/employeeImport";

			 $.ajax({
					url: ajaxCallUrl,
					type: 'POST',
					cache: false,
					dataType: 'json',
					data: new FormData($('#ppp')[0]),
					processData: false,
					contentType: false
				}).done(function(data) {
					if(data.result){
						swal({
							title: "花名册导入成功!",
							text: data.msg,
							type: "success"
						},function(){
		                   	window.location.reload();
		                });
					}else{
						swal("花名册导入失败!",data.error,'error');
					}

					// 不管成功失败，都要把文件输入框内容情况，不然在不刷新页面的情况下，下次就不能触发上传了
					$('#importFile').val("");
				}).fail(function(data) {
					swal("花名册导入异常!");
					$('#importFile').val("");
				}); 
		}

		function exportPerson(){
			window.open("employee/employeeExport?countType=" + $('#countType').val());
		}

		function downMB(moban) {
			var day = $("#birthInput").val();
			window.open(moban+"?day="+day);
		}
	 	
		function sendOrderMail() {
			if (document.getElementById("file").value == "") {
				alert("请选择要上传的附件");
				return false;
			}
	
			var path = document.getElementById("file").value;
			var isIE = (document.all) ? true : false; 3           
			var isIE9 = isIE && (navigator.userAgent.indexOf('MSIE 9.0') != -1);  
			var isIE10 = isIE && (navigator.userAgent.indexOf('MSIE 10.0') != -1);  
			var isIE11 = isIE && (navigator.userAgent.indexOf('MSIE 11.0') != -1); 
			var isChrome = window.navigator.userAgent.indexOf("Chrome") !== -1 
			
			if(isIE9 || isIE10 || isIE11 || isChrome){
				path = path.substring(path.lastIndexOf("\\")+1,path.length);
			}
			
			document.OrderSendForm.saction.value = "sendMail";
			document.OrderSendForm.attachment.value = path;
			document.OrderSendForm.action = "hroorderSend.do";
			document.OrderSendForm.submit();
		}
</script>
</body>
</html>