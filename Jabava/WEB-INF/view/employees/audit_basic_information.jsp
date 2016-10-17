<%@ page contentType="text/html; charset=utf-8" %>
<%@page import="com.jabava.utils.RequestUtil"%>
<!DOCTYPE html>
<html>
<head>
    <base href="${pageContext.request.contextPath}/">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- Page title -->
    <title>工作信息</title>
    <jsp:include flush="true" page="../common/styles.jsp"></jsp:include>
    <link rel="stylesheet" href="static/js/plugins/form.validation/css/formValidation.css">
    <link rel="stylesheet" href="static/css/user_bata.css">
    <link rel="stylesheet" href="static/css/style_1.css" media="screen">
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
<div id="wrapper" class="min-h">
	<div class="normalheader transition animated fadeIn small-header">
      <div class="hpanel">
        <div class="panel-body">
          <div id="hbreadcrumb" class="m-t-xs m-b-xs">
            <h2 class="font-normal m-b-xs text-center">
              员工资料
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
                  <div class="panel-heading m-b-lg">
                      <h4>
                          <button onclick="toEmployeeList()" class="btn btn-default btn-sm btn-absolute" type="button">　返回　</button>
                      </h4>
                  </div>
                  <!--引入员工信息导航 开始--> 
                  <jsp:include flush="true" page="audit_employee_nav.jsp">
                  <jsp:param value="essential" name="type"/>
                  </jsp:include>
                  <!--引入员工信息导航 结束-->
                  <div class="panel-body">
                    <h4 class="text-center font-bold">工作信息</h4>
                    <div id="jiben_div">
                    <script type="text/html" id="jiben">
                        <form method="post" role="form" name="jiben_form" class="searchs-form form-horizontal jbxx jbxx_form" id="personalForm" enctype="multipart/form-data" data-form="validator">
                            <div class="text-right action-group">
                                <% if(RequestUtil.hasPower("roster_essential_mp")){ %>
                                <a href="javascript://" class="pe-7s-note pe-2x" data-action-motive="edit" ><span class="sr-only">修改</span></a>
                                <% } %>
                            </div>
                            <input type="hidden" name="personId" value="{{person.personId}}">
                            <!--单位名称-->
                            <div class="col-md-6 col-lg-6">
                                <div class="form-group">
                                    <label for="" class="control-label col-md-3 col-lg-3">单位名称：</label>
                                    <div class="col-md-9 col-lg-9">
                                        <div class="input-group-static">
                                            <p class="form-control-static">{{person.companyName}}</p>
                                        </div>
                                        <input type="text" class="form-control" name="companyName" value="{{person.companyName}}" readonly="readonly">
                                    </div>
                                </div>
                            </div>
                            <!--入职时间-->
                            <div class="col-md-6 col-lg-6">
                                <div class="form-group">
                                    <label for="" class="control-label col-md-3 col-lg-3">入职时间：</label>
                                    <div class="col-md-9 col-lg-9">
                                        <div class="input-group-static">
                                            <p class="form-control-static">{{person.entryDate | dateFormat}}</p>
                                        </div>
                                        <div class="input-group date form-required">
                                            <input type="text" class="form-control"  name="entryDate" value="{{person.entryDate | dateFormat}}">
                                            <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!--停发标志-->
                            <div class="col-md-6 col-lg-6">
                                <div class="form-group">
                                    <label for="" class="control-label col-md-3 col-lg-3">停发标志：</label>
                                    <div class="col-md-9 col-lg-9">
                                        <div class="input-group-static">
                                            <p class="form-control-static">
                                                {{if person.isPayrollFlag == 0}}
                                                未停发
                                                {{else if person.isPayrollFlag == 1}}
                                                停发
                                                {{/if}}
                                            </p>
                                        </div>
                                        <div class="form-required">
                                            <select class="form-control" name="isPayrollFlag">
                                                <option {{if (person.isPayrollFlag == 0)}} selected="selected" {{/if}} value="0">未停发</option>
                                                <option {{if (person.isPayrollFlag == 1)}} selected="selected" {{/if}} value="1">停发</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!--转正时间-->
                            <div class="col-md-6 col-lg-6">
                                <div class="form-group">
                                    <label for="" class="control-label col-md-3 col-lg-3">转正时间：</label>
                                    <div class="col-md-9 col-lg-9">
                                        <div class="input-group-static">
                                            <p class="form-control-static">{{person.positiveDate | dateFormat}}</p>
                                        </div>
                                        <input type="text" class="form-control" name="positiveDate" value="{{person.positiveDate | dateFormat}}" readonly="readonly">
                                    </div>
                                </div>
                            </div>
                            <!--员工状态-->
                            <div class="col-md-6 col-lg-6">
                                <div class="form-group">
                                    <label for="" class="control-label col-md-3 col-lg-3">员工状态：</label>
                                    <div class="col-md-9 col-lg-9">
                                        <div class="input-group-static">
                                            <p class="form-control-static">
                                                
                                                {{if (1== person.status)}}
                                                 	  在职   
                                                {{/if}}
                                                {{if (2== person.status)}}
                                                 	  离职   
                                                {{/if}}
												{{if (3== person.status)}}
                                                 	  停职  
                                                {{/if}}
												{{if (4== person.status)}}
                                                 	 退休   
                                                {{/if}}
												{{if (5== person.status)}}
                                                 	  在入职   
                                                {{/if}}
                                            </p>
                                        </div>
                                        <select class="form-control " name="status">
                                            <option {{if (1 == person.status)}}selected="true"{{/if}} value="1">在职</option>
 											<option {{if (2 == person.status)}}selected="true"{{/if}} value="2">离职</option>
										    <option {{if (3 == person.status)}}selected="true"{{/if}} value="3">停职</option>
 											<option {{if (4 == person.status)}}selected="true"{{/if}} value="4">退休</option>
											<option {{if (5 == person.status)}}selected="true"{{/if}} value="5">再入职</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <!--工作地-->
                            <div class="col-md-6 col-lg-6">
                                <div class="form-group">
                                    <label for="" class="control-label col-md-3 col-lg-3">工作地：</label>
                                    <div class="col-md-9 col-lg-9">
                                        <div class="input-group-static">
                                            <p class="form-control-static">
                                                {{each city as c k}}
                                                {{if (c.baseDataCode == person.workLocation)}}
                                                {{c.baseDataName}}
                                                {{/if}}
                                                {{/each}}
                                            </p>
                                        </div>
                                        <div class="form-required">
                                            <select class="form-control " name="workLocation" data-toggle="select2">
                                                {{each city as c k}}
                                                <option {{if (c.baseDataCode == person.workLocation)}}selected="true"{{/if}} value="{{c.baseDataCode}}">{{c.baseDataName}}</option>
                                                {{/each}}
                                            </select>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!--团队-->
                            <div class="col-md-6 col-lg-6">
                                <div class="form-group">
                                    <label for="" class="control-label col-md-3 col-lg-3">团队：</label>
                                    <div class="col-md-9 col-lg-9">
                                        <div class="input-group-static">
                                            <p class="form-control-static">
                                                {{each team as t k}}
                                                {{if (t.baseDataId == person.team)}}
                                                {{t.baseDataName}}
                                                {{/if}}
                                                {{/each}}
                                            </p>
                                        </div>
                                        <select class="form-control " name="team">
                                            {{each team as t k}}
                                            <option {{if (t.baseDataId == person.team)}}selected="true"{{/if}} value="{{t.baseDataId}}">{{t.baseDataName}}</option>
                                            {{/each}}
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <!--发薪地-->
                            <div class="col-md-6 col-lg-6">
                                <div class="form-group">
                                    <label for="" class="control-label col-md-3 col-lg-3">发薪地：</label>
                                    <div class="col-md-9 col-lg-9">
                                        <div class="input-group-static">
                                            <p class="form-control-static">
                                                {{each city as ci j}}
                                                {{if (ci.baseDataCode == person.payrollLocation)}}
                                                {{ci.baseDataName}}
                                                {{/if}}
                                                {{/each}}
                                            </p>
                                        </div>
                                        <div class="form-required">
                                            <select class="form-control " name="payrollLocation" data-toggle="select2">
                                                {{each city as ci j}}
                                                <option {{if (ci.baseDataCode == person.payrollLocation)}}selected="true"{{/if}} value="{{ci.baseDataCode}}">{{ci.baseDataName}}</option>
                                                {{/each}}
                                            </select>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!--招聘渠道-->
                            <div class="col-md-6 col-lg-6">
                                <div class="form-group">
                                    <label for="" class="control-label col-md-3 col-lg-3">招聘渠道：</label>
                                    <div class="col-md-9 col-lg-9">
                                        <div class="input-group-static">
                                            <p class="form-control-static">{{person.recruit}}</p>
                                        </div>
                                        <input type="text" class="form-control"  name="recruit" value="{{person.recruit}}">
                                    </div>
                                </div>
                            </div>
                            <!--部门名称-->
                            <div class="col-md-6 col-lg-6">
                                <div class="form-group">
                                    <label for="" class="control-label col-md-3 col-lg-3">部门名称：</label>
                                    <div class="col-md-9 col-lg-9">

                                        <input type="hidden" id="departmentId"  name="departmentId" required="required">
                                        {{if ehrPositionVO.departmentId !== null}}
                                        <div class="input-group-static">
                                            <p class="form-control-static">{{ehrPositionVO.department.organizationName}}</p>
                                        </div>

                                        <div class="form-required">
                                            <input type="text" class="form-control" 
                                            data-tree-attr="getTreeNode" 
                                            value="{{ehrPositionVO.department.organizationName}}" disabled="disabled" name="organizationName">
                                        </div>
                                        {{else}}
                                        <div class="input-group-static">
                                            <p class="form-control-static">&nbsp;</p>
                                        </div>  

                                        <div class="form-required">
                                            <input type="hidden" value="" name="organizationName" data-department-id="">
                                            <div class="input-group">
                                                <input type="text" readonly="readonly" name="addNewDepartment" class="form-control">
                                                <span class="input-group-btn"><button type="button" class="btn btn-default" data-toggle="modal" data-target="[data-modal=organization]"><i class="fa fa-search"></i></button></span>
                                            </div>
                                        </div>
                                        {{/if}}
                                    </div>
                                </div>
                            </div>
                            <!--用工性质-->
                            <div class="col-md-6 col-lg-6">
                                <div class="form-group">
                                    <label for="" class="control-label col-md-3 col-lg-3">用工性质：</label>
                                    <div class="col-md-9 col-lg-9">
                                        <div class="input-group-static">
                                            <p class="form-control-static">
                                                {{each ehrPositionVO.labor as p s}}
                                                {{if (p.baseDataCode == ehrPositionVO.natureOfLabor)}}
                                                {{p.baseDataName}}
                                                {{/if}}
                                                {{/each}}
                                            </p>
                                        </div>
                                        <div class="form-required">
                                            <select class="form-control " name="natureOfLabor">
                                                {{each ehrPositionVO.labor as p s}}
                                                <option {{if (p.baseDataCode == ehrPositionVO.natureOfLabor)}}selected="selected"{{/if}} value="{{p.baseDataCode}}">{{p.baseDataName}}</option>
                                                {{/each}}
                                            </select>
                                        </div>
                                    </div>
                                </div>
                            </div>      
                            <!--岗位名称-->
                            <div class="col-md-6 col-lg-6">
                                <div class="form-group">
                                    <label for="" class="control-label col-md-3 col-lg-3">岗位名称：</label>
                                    <div class="col-md-9 col-lg-9">
                                        <div class="input-group-static">
                                            <p class="form-control-static">
                                                {{each ehrPositionVO.posiName as key idx}}
                                                {{if ehrPositionVO.positionName==key.baseDataId}}
                                                {{key.baseDataName}}
                                                {{/if}}
                                                {{/each}}
                                            </p>
                                        </div>
                                        {{if ehrPositionVO.positionName !== null}}
                                        <div class="form-required">
                                            <select class="form-control"  disabled="disabled" name="positionName">
                                                {{each ehrPositionVO.posiName as key idx}}
                                                <option value="{{key.baseDataId}}" {{if ehrPositionVO.positionName==key.baseDataId}} selected{{/if}}>{{key.baseDataName}}</option>
                                                {{/each}}
                                            </select>
                                        </div>
                                        <span class="help-block">如无此岗位，请先至“组织架构基准管理”中添加岗位信息</span>
                                        {{else}}
                                        <div class="form-required">
                                            <select class="form-control"  name="positionName">
                                                {{each ehrPositionVO.posiName as key idx}}
                                                <option value="{{key.baseDataCode}}" {{if ehrPositionVO.positionName==key.baseDataCode}} selected{{/if}}>{{key.baseDataName}}</option>
                                                {{/each}}
                                            </select>
                                        </div>
                                        {{/if}}
                                    </div>
                                </div>
                            </div>
                            <!--是否关键岗位--> 
                            <div class="col-md-6 col-lg-6">
                                <div class="form-group">
                                    <label for="" class="control-label col-md-3 col-lg-3">是否关键岗位：</label>
                                    <div class="col-md-9 col-lg-9">
                                        <div class="input-group-static">
                                            <p class="form-control-static">
                                                {{if ehrPositionVO.keyPoint ==1 }}
                                                是
                                                {{else if ehrPositionVO.keyPoint ==0}} 
                                                否
                                                {{/if}}
                                            </p>
                                        </div>
                                        <div class="radio-inline-group form-control-static">
                                            <label class="radio-inline">
                                                <input type="radio" name="keyPoint" id="" value="1" {{if  ehrPositionVO.keyPoint ==1 }}checked="checked"{{/if}}> 是
                                            </label>
                                            <label class="radio-inline">
                                                <input type="radio" name="keyPoint" id="" value="0" {{if  ehrPositionVO.keyPoint ==0 }}checked="checked"{{/if}}> 否
                                            </label>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!--汇报对象-->
                            <div class="col-md-6 col-lg-6">
                                <div class="form-group">
                                    <label for="" class="control-label col-md-3 col-lg-3">汇报对象：</label>
                                    {{if ehrPositionVO.reportId !== null}}
                                    <div class="col-md-9 col-lg-9">
                                        <div class="input-group-static">
                                            <p class="form-control-static">{{ehrPositionVO.reportPerson.employeeName}}</p>
                                        </div>
                                        <input type="text" class="form-control" id="reportEmployeeName" name="reportEmployeeName" disabled="disabled"  value="{{ehrPositionVO.reportPerson.employeeName}}">
                                    </div>
                                    {{else}}
                                    <div class="col-md-9 col-lg-9">
                                        <div class="input-group-static">
                                            <p class="form-control-static"></p>
                                        </div>
                                        <input type="text" class="form-control" id="addReportEmployeeName" name="reportEmployeeName" >
                                    </div>
                                    {{/if}}
                                </div>
                            </div>
                            <!--职级-->
                            <div class="col-md-6 col-lg-6">
                                <div class="form-group">
                                    <label for="" class="control-label col-md-3 col-lg-3">职级：</label>
                                    <div class="col-md-9 col-lg-9">
                                        <div class="input-group-static">
                                            <p class="form-control-static">
                                                {{each ehrPositionVO.level as r t}}
                                                {{if (r.baseDataId == ehrPositionVO.levelId)}}
                                                {{r.baseDataName}}
                                                {{/if}}
                                                {{/each}}
                                            </p>
                                        </div>
                                        {{if ehrPositionVO.levelId === null}}
                                        <select class="form-control " name="levelId">
                                            {{each ehrPositionVO.level as r t}}
                                            <option value="{{r.baseDataId}}">{{r.baseDataName}}</option>
                                            {{/each}}
                                        </select>
                                        {{else}}
                                        <select class="form-control " name="levelId" disabled="disabled">
                                            {{each ehrPositionVO.level as r t}}
                                            <option{{if ehrPositionVO.levelId==r.baseDataId}} selected="selected"{{/if}} value="{{r.baseDataId}}">{{r.baseDataName}}</option>
                                            {{/each}}
                                        </select>
                                        {{/if}}
                                    </div>
                                </div>
                            </div> 
                            <!--工号-->
                            <div class="col-md-6 col-lg-6">
                                <div class="form-group">
                                    <label for="" class="control-label col-md-3 col-lg-3">工号：</label>
                                    {{if ehrPositionVO.employeeNumber !== null}}
                                    <div class="col-md-9 col-lg-9">
                                        <div class="input-group-static">
                                            <p class="form-control-static">{{ehrPositionVO.employeeNumber}}</p>
                                        </div>
                                        <div class="form-required">
                                            <input type="text" class="form-control" id="employeeNumber" name="employeeNumber" value="{{ehrPositionVO.employeeNumber}}" required="required">
                                        </div>
                                    </div>
                                    {{else}}
                                    <div class="col-md-9 col-lg-9 ">
                                        <div class="input-group-static">
                                            <p class="form-control-static"></p>
                                        </div>
                                        <div class="form-required">
                                            <input type="text" class="form-control" id="addEmployeeNumber" name="employeeNumber" value="" required="required">
                                        </div>
                                    </div>
                                    {{/if}}
                                </div>
                            </div>
                            <div class="col-md-6 col-lg-6">
                                <div class="form-group">
                                    <label for="" class="control-label col-md-3 col-lg-3">推荐人：</label>
                                    <div class="col-md-9 col-lg-9">
                                        <div class="input-group-static">
                                            <p class="form-control-static">{{person.referrer}}</p>
                                        </div>
                                        <input type="text" class="form-control" name="referrer" value="{{person.referrer}}">
                                    </div>
                                </div>
                            </div>

                            

                            <!--保存 删除-->
                            <div class="col-lg-12 col-md-12 text-center ">

                                <button type="submit" class="btn btn-info form-action">保存</button>
                                <button type="button" class="btn btn-default form-action" data-action-motive="cancel">取消</button>
 							     
 							   {{if person.approvedStatus==2}}
                                  <button type="button" class="btn btn-primary input-group-static" data-approved="approved">通过</button>
                               {{/if}}

                            </div>
                        </form>
                    </script>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>

    <div class="modal fade hmodal-success" data-modal="organization" tabindex="-1" role="dialog"  aria-hidden="true" data-items-id="${personId}">
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


          <!-- Footer-->
          <!-- 放页脚  开始-->
          <jsp:include flush="true" page="../common/foot.div.jsp"></jsp:include>
          <!-- 放页脚  结束-->
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
<script src="static/bootstrap/vendor/moment/moment.js"></script>
<script src="static/bootstrap/vendor/xeditable/bootstrap3-editable/js/bootstrap-editable.min.js"></script>
<script src="static/bootstrap/vendor/select2-3.5.2/select2.min.js"></script>
<script src="static/bootstrap/vendor/bootstrap-touchspin/dist/jquery.bootstrap-touchspin.min.js"></script>
<script src="static/bootstrap/vendor/bootstrap-datepicker-master/dist/js/bootstrap-datepicker.js"></script>

<!-- for datatable -->
<script src="static/bootstrap/vendor/datatables/media/js/jquery.dataTables.min.js"></script>
<script src="static/bootstrap/vendor/datatables_plugins/integration/bootstrap/3/dataTables.bootstrap.min.js"></script>
<!-- alert -->
<script src="static/bootstrap/vendor/sweetalert/lib/sweet-alert.min.js"></script>

<!-- App scripts -->
<script src="static/bootstrap/scripts/homer.js"></script>
<script src="static/js/template.js"></script>

<!-- 表单验证 -->
<script src="static/js/plugins/form.validation/js/formValidation.js"></script>
<script src="static/js/plugins/form.validation/js/framework/bootstrap.js"></script>
<script src="static/js/plugins/form.validation/js/language/zh_CN.js"></script> 
<script src="static/js/yuangong.js"></script>
<script src="static/js/basic_information.js"></script>
<script type="text/javascript" src="static/js/zTreeJs/zTreeJs/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="static/js/zTreeJs/zTreeJs/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript" src="static/js/zTreeJs/zTreeJs/jquery.ztree.exedit-3.5.js"></script>
<script>
$(function(){
	
  function showBaseInfo(){
	  
	    var empty={
                message: '请填写必填项目'
        };
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
            entryDate: {
                validators: {
                    notEmpty:empty,
                    date: {
                      format: 'YYYY-MM-DD',
                      message: '该日期是无效的'
                    }
                }
            },
            workLocation: {
                validators: {
                    notEmpty: empty
                }
            },
            payrollLocation: {
                validators: {
                    notEmpty: empty
                }
            },
            isPayrollFlag: {
                validators: {
                    notEmpty: empty
                }
            },
            addNewDepartment: {
                validators: {
                    notEmpty: empty
                }
            }
        }
      };

      $.ajax({
          type: "POST",
          url:"employee/essentialInfo.do",
          data:{"personId":"${personId}"}, 
          dataType:'json',
          error: function(XMLHttpRequest, textStatus, errorThrown){
                  $('#create_new').removeClass('hidden');
          },
          success: function(data){

              var html = template('jiben', data);
                  html = $(html);

              // form init
              _initForm(html,validateOptions,data);
              
             

          }
     });
  }
  
 function _initForm(html,validateOptions,data){
	 
	 var getDomStaticTarget = html.find('.input-group-static'),
     getDomTarget = getDomStaticTarget.siblings(),
     getDomFormAction = html.find('.form-action');
 
	 getDomTarget.hide();
	 getDomFormAction.hide();
	
	 // @eventBind {action} .action-group
	 html.find('.action-group [data-action-motive]').on('click', function(evt){
	     var getAction = $(this).data('actionMotive'),
	         $form = $(this).parents('form'),
	         $getDomStaticTarget = $form.find('.input-group-static'),
	         $getDomTarget = $getDomStaticTarget.siblings().not('small,i'),
	         $getDomFormAction = $form.find('.form-action');
	
	         
	         
	     switch(getAction){
	         case 'edit':
	             $('[data-toggle="select2"]').select2();
	             $form.formValidation(validateOptions);
	             $getDomStaticTarget.hide();
	             $getDomTarget.show();
	             $getDomFormAction.show();
	             break;
	     }
	 });
	
	
	 // @eventBind {action} .form-action
	 getDomFormAction.filter('[data-action-motive]').on('click', function(evt){
	
	     var $form = $(this).parents('form'),
	         $getDomStaticTarget = $form.find('.input-group-static'),
	         $getDomTarget = $getDomStaticTarget.siblings().not('small,i'),
	         $getDomFormAction = $form.find('.form-action');
	
	     switch($(this).data('actionMotive')){
	        // case 'save':
	        //      // updateSave($form);
	        // break;
	         case 'cancel':
	             //$form.formValidation('resetForm');
	             $form[0].reset();
	             //$form.formValidation('destroy');
	             $getDomStaticTarget.show();
	             $getDomTarget.hide();
	             $getDomFormAction.hide();
	             break;
	          
	     }
	 });
	
	 $("#jiben_div").prepend(html);
	
	 $('[data-form="validator"]').formValidation(validateOptions)
	 .on('success.form.fv', function(e){
	   e.preventDefault();
	   updateSave( $('[data-form="validator"]') );
	 })
	
	   $('.input-group.date')
	   .datepicker({
	       format: "yyyy-mm-dd",
	       autoclose: true
	   })
	   .on('changeDate', function(e){
	     var getEleName = $(e.target).find(':text').attr('name');
	     $('[data-form="validator"]').formValidation('revalidateField', getEleName);
	   });
	
	 if(data.person.personId == ''){
	     getDomFormAction.parent('form').hide();
	     $('#create_new').removeClass('hidden');
	     return;
	 }
	 
	 approved("${personId}");
	 
 }
  //修改
  function updateSave($form){
    
    var formObject= $form.serializeObject() ;
  
    //岗位信息
    var positionFormObj={
            personId:formObject.personId,
            departmentId:formObject.organizationName, //部门
            positionName:formObject.positionName,//岗位信息id
            natureOfLabor:formObject.natureOfLabor,//用工性质
            keyPoint:formObject.keyPoint,//是否关键岗位
            employeeNumber:formObject.employeeNumber,//员工工号  
            levelId:formObject.levelId//职级
    };
    var jsonPosition=JSON.stringify(positionFormObj);
    delete formObject.employeeNumber;  
    delete formObject.levelId;   

    
    
    customDataJson = JSON.stringify([]);
    var jsonBasic=JSON.stringify(formObject);

        $.ajax({
            type: "post",
            url: "employee/updateBasicInfo",
            data: "basicData="+jsonBasic+"&positionData="+jsonPosition+"&reportEmployeeName="+formObject.reportEmployeeName+'&customData='+customDataJson,
            dataType: "json",
            async: false,
            success:function(data){
                if(data.success == true){
                   /*  swal(data.msg, "", "success"); 
                     $("#jiben_div").html(""); */
                 	swal({
                        title:data.msg,
                        type: "success"
                        }, 
                        function(){
                        	 $("#jiben_div").html("");
                        	showBaseInfo();
                      });
                     //showBaseInfo();
                     
                }else{
                    swal(data.msg, "", "error"); 
                }
            }
        });   
     
  }

  showBaseInfo();//初始化页面

  $('#datepicker').datepicker();
  $("#datepicker").on("changeDate", function(event) {
      $("#my_hidden_input").val(
              $("#datepicker").datepicker('getFormattedDate')
      )
  });

  $('#datapicker2').datepicker();
  $('.input-group.date').datepicker({ });
  $('.input-daterange').datepicker({ });


});

</script>

</body>
</html>