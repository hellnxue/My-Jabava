<%@ page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html>
<head>
	<base href="${pageContext.request.contextPath}/">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <!-- Page title -->
    <title>岗位调动</title>
    <jsp:include flush="true" page="../common/styles.jsp"></jsp:include>
    <link rel="stylesheet" href="static/css/user_bata.css">
    <link rel="stylesheet" href="static/css/style_1.css" media="screen">
    <link rel="stylesheet" href="static/css/bill.css">
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
          <div id="hbreadcrumb" class="pull-right m-t-lg">
            <ol class="hbreadcrumb breadcrumb">
              <li><a href="to_index?jump=1">首页</a></li>
              <li>
                <span>员工信息</span>
              </li>
              <li class="active">
                <span></span>
              </li>
            </ol>
          </div>
          <h2 class="font-light m-b-xs">
            员工信息
          </h2>
          <small>待定</small>
        </div>
      </div>
    </div>

    <!-- 放主要内容 -->

    <!--岗位调动-->
    <!-- 开始 -->
    <div class="content animate-panel">
      <div class="row">
        <div class="hpanel hblue">
          <!--引入员工信息导航 开始--> 
          <jsp:include flush="true" page="employee_nav.jsp"> <jsp:param value="transfer" name="type"/></jsp:include>
          <!--引入员工信息导航 结束-->
          <div class="panel-heading hbuilt">
            <div class="panel-tools">
              <button type="button" class="btn btn-success btn-xs lets"><i class="fa fa-plus"></i><span class="bold">继续添加</span></button>
              <button type="button" class="btn btn-success btn-xs lets_update job_none"><i class="fa fa-plus"></i><span class="bold">继续添加</span></button>

              <button type="button" class="btn btn-success btn-xs jy_beijing"><span class="bold">教育背景&gt;&gt;</span></button> 
            </div>
            <h4>岗位调动</h4>
          </div>
          <div class="panel-body">
            <div class="div_job">

            </div>
            <input type="hidden" name="personId" value="${personId}" id="personId">
            <!--岗位调动保存-->
            <script id="transfer" type="text/html">
              {{each list}}
              <form role="form" method="post" class="form-horizontal col-lg-12 col-md-12  {{if $index>0}} jianju borders{{/if}}"  id="form-{{$value.postId}}">
                {{if list.length-1 == $index}}
                <div class="text-right action-group">
                  <a href="javascript://" class="pe-7s-note pe-2x" data-action-motive="edit" ><span class="sr-only">修改</span></a>
                </div>
                {{/if}}
                <input type="hidden" name="personId" value="{{$value.personId}}" >
                <input type="hidden" name="postId" value="{{$value.postId}}">
 				<input type="hidden" name="sourceDepartment" value="{{$value.departmentSource.organizationId}}">				
				<input type="hidden"  name="sourcePost"  >
				<input type="hidden"  name="sourceRank"  >
				<input type="hidden"  name="sourceCost"  >
                <div class="col-lg-4 col-md-4">
                  <div class="form-group">
                    <label for="" class="control-label col-lg-5 col-md-5">原部门：</label>
                    <div class="col-md-7 col-lg-7">
                      <div class="input-group-static">
                        <p class="form-control-static">{{$value.departmentSource.organizationName}}</p>
                      </div>
                      <input type="text" class="form-control" readonly="readonly"   value="{{$value.departmentSource.organizationName}}"  >
                    </div>
                  </div>
                </div>
                <div class="col-lg-4 col-md-4">
                  <div class="form-group">
                    <label for="" class="control-label col-lg-5 col-md-5">原岗位：</label>
                    <div class="col-md-7 col-lg-7">
                      <div class="input-group-static">
                        <p class="form-control-static">
                          {{each post as key idx}}
                          {{if $value.sourcePost==key.baseDataId}}
                          {{key.baseDataName}}
                          {{/if}}
                          {{/each}}
                        </p>
                      </div>
                      <select class="form-control"  name="updateSourcePost" disabled="disabled">
                        {{each post as key idx}}
                        <option value="{{key.baseDataId}}" {{if $value.sourcePost==key.baseDataId}} selected{{/if}}>{{key.baseDataName}}</option>
                        {{/each}}
                      </select>        
                    </div>                              
                  </div>
                </div>
                <div class="col-lg-4 col-md-4">
                  <div class="form-group">
                    <label for="" class="control-label col-md-5 col-lg-5">原职级：</label>
                    <div class="col-md-7 col-lg-7">
                      <div class="input-group-static">
                        <p class="form-control-static">
                          {{each rank as key idx}}
                          {{if $value.sourceRank==key.baseDataId}}
                          {{key.baseDataName}}
                          {{/if}}
                          {{/each}}
                        </p>
                      </div>

                      <select class="form-control"  name="updateSourceRank" disabled="disabled" >
                        {{each rank as key idx}}
                        <option value="{{key.baseDataId}}" {{if $value.sourceRank==key.baseDataName}}selected{{/if}}>{{key.baseDataName}}</option>
                        {{/each}}
                      </select>

                    </div>                              
                  </div>
                </div>
                <div class="col-lg-4 col-md-4">
                  <div class="form-group">
                    <label for="" class="control-label col-md-5 col-lg-5">原岗位任职期限：</label>
                    <div class="col-md-7 col-lg-7">
                      <div class="input-group-static">
                        <p class="form-control-static">{{$value.sourceLong}}</p>
                      </div>
                      <input type="text" class="form-control" name="sourceLong" value="{{$value.sourceLong}}">
                    </div>                              
                  </div>
                </div>
                <div class="col-lg-4 col-md-4">
                  <div class="form-group">
                    <label for="" class="control-label col-md-5 col-lg-5">原汇报对象：</label>
                    <div class="col-md-7 col-lg-7">
                      <div class="input-group-static">
                        <p class="form-control-static">{{$value.reportPersonSource.employeeName}}</p>
                      </div>
                      <input type="text" class="form-control" name="sourceReport" readonly="readonly" value="{{$value.reportPersonSource.employeeName}}"  >
                    </div>
                  </div>
                </div>
                <div class="col-lg-4 col-md-4">
                  <div class="form-group">
                    <label for="" class="control-label col-md-5 col-lg-5">原工作地：</label>
                    <div class="col-md-7 col-lg-7">
                      <div class="input-group-static">
                        <p class="form-control-static">
                          {{each city as key idx}}
                          {{if $value.sourceLocation==key.baseDataId}}
                          {{key.baseDataName}}
                          {{/if}}
                          {{/each}}
                        </p>
                      </div>
                      <select name="sourceLocation" class="form-control">
                        {{each city as key idx}}
                        <option value="{{key.baseDataId}}"{{if $value.sourceLocation==key.baseDataId}}selected{{/if}}>{{key.baseDataName}}</option>
                        {{/each}}
                      </select>
                    </div>                              
                  </div>
                </div>
                <div class="col-lg-4 col-md-4">
                  <div class="form-group">
                    <label for="" class="control-label col-md-5 col-lg-5">原成本中心：</label>
                    <div class="col-md-7 col-lg-7">
                      <div class="input-group-static">

                        <p class="form-control-static">
                          {{each cost as c k}}
                          {{if (c.baseDataId == $value.sourceCost)}}
                          {{c.baseDataName}}
                          {{/if}}
                          {{/each}}
                        </p>

                      </div>
                      <select  class="form-control" name="updateSourceCost"  disabled="disabled">
                        {{each cost as key idx}}
                        <option value="{{key.baseDataId}}" {{if $value.sourceCost==key.baseDataId}}selected{{/if}}>{{key.baseDataName}}</option>
                        {{/each}}
                      </select>
                    </div>                              
                  </div>
                </div>
                <div class="col-lg-4 col-md-4"> 
                  <div class="form-group">						   
                    <label for="" class="control-label col-md-5 col-lg-5">现部门：</label>
                    <div class="col-md-7 col-lg-7">
                      <div class="input-group-static">
                        <p class="form-control-static">{{$value.departmentNew.organizationName}}</p>
                      </div>    
                      <input type="hidden" data-department-id="" name="newDepartment" required="required" value="{{$value.newDepartment}}">
                      <div class="input-group">
                        <input type="text" class="form-control" readonly="readonly" value="{{$value.departmentNew.organizationName}}">
                        <span class="input-group-btn"><button type="button" class="btn btn-default" data-toggle="modal" data-target="#getTree"><i class="fa fa-search"></i></button></span>
                      </div>
                    </div>                              
                  </div>
                </div>
                <div class="col-lg-4 col-md-4">
                  <div class="form-group">
                    <label for="" class="control-label col-md-5 col-lg-5">现岗位：</label>
                    <div class="col-md-7 col-lg-7">
                      <div class="input-group-static">
                        <p class="form-control-static">
                          {{each post as key idx}}
                          {{if $value.newPost==key.baseDataId}}
                          {{key.baseDataName}}
                          {{/if}}
                          {{/each}}
                        </p>
                      </div>
                      <select class="form-control" name="newPost">
                        {{each post}}
                        <option value="{{$value.baseDataId}}">{{$value.baseDataName}}</option>
                        {{/each}}
                      </select>
                    </div>
                  </div>
                </div>
                <div class="col-lg-4 col-md-4">
                  <div class="form-group">
                    <label for="" class="control-label col-md-5 col-lg-5">现职级：</label>
                    <div class="col-md-7 col-lg-7">
                      <div class="input-group-static">
                        <p class="form-control-static">
                          {{each rank as r t}}
                          {{if (r.baseDataId ==$value.newRank)}}
                          {{r.baseDataName}}
                          {{/if}}
                          {{/each}}
                        </p>
                      </div>
                      <select class="form-control" name="newRank">
                        {{each rank}}
                        <option value="{{$value.baseDataId}}">{{$value.baseDataName}}</option>
                        {{/each}}
                      </select>
                    </div>
                  </div>
                </div>
                <div class="col-lg-4 col-md-4">
                  <div class="form-group">
                    <label for="" class="control-label col-md-5 col-lg-5">当前汇报对象：</label>
                    <div class="col-md-7 col-lg-7">
                      <div class="input-group-static">
                        <p class="form-control-static">{{$value.reportPersonNew.employeeName}}</p>
                      </div>
                      <input type="hidden" class="form-control"  value="{{$value.reportPersonNew.personId}}">
                      <input type="text" class="form-control"  name="newReport" value="{{$value.reportPersonNew.employeeName}}">
                    </div>
                  </div>
                </div>
                <div class="col-lg-4 col-md-4">
                  <div class="form-group">
                    <label for="" class="control-label col-md-5 col-lg-5">当前工作地：</label>
                    <div class="col-md-7 col-lg-7">
                      <div class="input-group-static">
                        <p class="form-control-static">
                          {{each city as r t}}
                          {{if (r.baseDataId ==$value.newLocation)}}
                          {{r.baseDataName}}
                          {{/if}}
                          {{/each}}
                        </p>

                      </div>
                      <select name="newLocation" class="form-control">
                        {{each city}}
                        <option value="{{$value.baseDataId}}">{{$value.baseDataName}}</option>
                        {{/each}}
                      </select>
                    </div>
                  </div>
                </div>
                <div class="col-lg-4 col-md-4">
                  <div class="form-group">
                    <label for="" class="control-label col-md-5 col-lg-5">当前成本中心：</label>
                    <div class="col-md-7 col-lg-7">
                      <div class="input-group-static">
                        <p class="form-control-static">
                          {{each cost as r t}}
                          {{if (r.baseDataId ==$value.newCost)}}
                          {{r.baseDataName}}
                          {{/if}}
                          {{/each}}
                        </p>
                      </div>
                      <select class="form-control" name="newCost">
                        {{each cost}}
                        <option value="{{$value.baseDataId}}">{{$value.baseDataName}}</option>
                        {{/each}}
                      </select>
                    </div>
                  </div>
                </div>
                <div class="col-lg-4 col-md-4">
                  <div class="form-group">
                    <label for="" class="control-label col-md-5 col-lg-5">岗位调整日期：</label>
                    <div class="col-md-7 col-lg-7 date">
                      <div class="input-group-static">
                        <p class="form-control-static">{{$value.newDate | dateFormat}}</p>
                      </div>
                      <div class="input-group date">
                        <input type="text" class="form-control" name="newDate" required="required" value="{{$value.newDate | dateFormat}}">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                      </div>
                    </div>
                  </div>
                </div>
                <div class="col-lg-4 col-md-4">
                  <div class="form-group">
                    <label for="" class="control-label col-md-5 col-lg-5">是否调薪：</label>
                    <div class="col-md-7 col-lg-7">
                      <div class="input-group-static">
                        <p class="form-control-static">
                          {{if $value.isChangeSalary=="1"}} 是{{else if $value.leftCause!="1"}}否 {{/if}}
                        </p>
                      </div>
                      <div class="radio-group" style="padding-bottom:8px;">
                        {{if $value.isChangeSalary=="1"}}
                        <label class="radio-inline">
                          <input type="radio" name="isChangeSalary" value="1" checked="checked"> 是
                        </label>
                        <label class="radio-inline">
                          <input type="radio" name="isChangeSalary" value="0"> 否
                        </label>
                        {{else if $value.leftCause!="1"}}
                        <label class="radio-inline">
                          <input type="radio" name="isChangeSalary" value="1"> 是
                        </label>
                        <label class="radio-inline">
                          <input type="radio" name="isChangeSalary" value="0" checked="checked"> 否
                        </label>
                        {{/if}}
                      </div>
                    </div>
                  </div>
                </div>
                <div class="col-lg-4 col-md-4">
                  <div class="form-group">
                    <label for="" class="control-label col-md-5 col-lg-5">岗位调动原因：</label>
                    <div class="col-md-7 col-lg-7">
                      <div class="input-group-static">
                        <p class="form-control-static">{{$value.cause}}</p>
                      </div>
                      <input type="text" class="form-control" name="cause" value="{{$value.cause}}">
                    </div>
                  </div>
                </div>

                <!--保存 删除-->
                <div class="col-lg-12 col-md-12 text-center form-action">
                  <button type="button"  class="btn btn-info" name ="updateJobPost" onclick="submitJobPost(this)"  >保存</button>
                  <button type="reset" class="btn btn-default" data-action-motive="cancel">取消</button>
                </div>
              </form>
              {{/each}}
              {{each ehrPositionList}}
              <form role="form" class="searchs-form form-horizontal col-md-12 col-lg-12 gw_diaodong gangwei_form hidden" id="create_new"> 
                <input type="hidden" name="personId" value="{{$value.personId}}" >
				
				<input type="hidden" id = "sourcePost" name="sourcePost"  >
				<input type="hidden" id = "sourceRank" name="sourceRank"  >
				<input type="hidden" id = "sourceCost" name="sourceCost"  >


                <!--原部门-->
                <div class="col-md-4 col-lg-4">
                  <div class="form-group">
                    <label for="" class="control-label col-md-5 col-lg-5">原部门：</label>
                    <div class="col-md-7 col-lg-7">
                      <input type="hidden" data-department-id=""  name="sourceDepartment" value="{{$value.departmentId}}" required="required" >
                      <div class="input-group">
                        <input type="text" required="required" readonly="readonly" class="form-control" 
                        value="{{if $value.department != null}}{{$value.department.organizationName}}{{/if}}">
                        <span class="input-group-btn"><button type="button" class="btn btn-default" data-toggle="modal" disabled="disabled"  data-target="#getTree"><i class="fa fa-search"></i></button></span>
                      </div>
                    </div>
                  </div>
                </div>
                <!--原岗位-->
                <div class="col-md-4 col-lg-4">
                  <div class="form-group">
                    <label for="" class="control-label col-md-5 col-lg-5">原岗位：</label>
                    <div class="col-md-7 col-lg-7">       
                      <select class="form-control"  id="addSourcePost" name="addSourcePost" disabled="disabled">
                        {{each post as key idx}}
                        <option value="{{key.baseDataId}}" {{if $value.positionName==key.baseDataId}} selected{{/if}}>{{key.baseDataName}}</option>
                        {{/each}}
                      </select>  
                    </div>
                  </div>
                </div>
                <!--原职级-->
                <div class="col-md-4 col-lg-4">
                  <div class="form-group">
                    <label for="" class="control-label col-md-5 col-lg-5">原职级：</label>
                    <div class="col-md-7 col-lg-7">
                      <select class="form-control" id="addSourceRank" name="addSourceRank" disabled="disabled" >
                        {{each rank as key idx}}
                        <option value="{{key.baseDataId}}"{{if $value.levelId==key.baseDataId}} selected="selected"{{/if}}>{{key.baseDataName}}</option>
                        {{/each}}
                      </select>
                    </div>
                  </div>
                </div>
                <!--原岗位任职期限-->
                <div class="col-md-4 col-lg-4">
                  <div class="form-group">
                    <label for="" class="control-label col-md-5 col-lg-5">原岗位任职期限：</label>
                    <div class="col-md-7 col-lg-7">
                      <input type="text" class="form-control" id="" name="sourceLong"  >
                    </div>
                  </div>
                </div>
                <!--原汇报对象-->
                <div class="col-md-4 col-lg-4">
                  <div class="form-group">
                    <label for="" class="control-label col-md-5 col-lg-5">原汇报对象：</label>
                    <div class="col-md-7 col-lg-7">
                      <input type="text" class="form-control" id="" name="sourceReport"  readonly = "readonly" 
                      value="{{if $value.reportPerson!=null}}{{$value.reportPerson.employeeName}}{{/if}}"  >
                    </div>
                  </div>
                </div>
                <!--原工作地-->
                <div class="col-md-4 col-lg-4">
                  <div class="form-group">
                    <label for="" class="control-label col-md-5 col-lg-5">原工作地：</label>
                    <div class="col-md-7 col-lg-7">
                      <select name="sourceLocation" class="form-control">
                        {{each city as key idx}}
                        <option value="{{key.baseDataId}}">{{key.baseDataName}}</option>
                        {{/each}}
                      </select>
                    </div>
                  </div>
                </div>
                <!--原成本中心-->
                <div class="col-md-4 col-lg-4">
                  <div class="form-group">
                    <label for="" class="control-label col-md-5 col-lg-5">原成本中心：</label>
                    <div class="col-md-7 col-lg-7">
                      <select class="form-control" id="addSourceCost" name="addSourceCost" disabled="disabled" >
                        {{each cost as key idx}}
                        <option value="{{key.baseDataId}}"{{if $value.costCenterId==key.baseDataId}} selected="selected"{{/if}}>{{key.baseDataName}}</option>
                        {{/each}}
                      </select>
                    </div>
                  </div>
                </div>
                <!--现部门-->
                <div class="col-md-4 col-lg-4">
                  <div class="form-group">
                    <label for="" class="control-label col-md-5 col-lg-5">现部门：</label>
                    <div class="col-md-7 col-lg-7">
                      <input type="hidden" data-department-id="" name="newDepartment" value="">
                      <div class="input-group">
                        <input type="text" class="form-control" readonly="readonly" crequired="required">
                        <span class="input-group-btn"><button type="button" class="btn btn-default" data-toggle="modal" data-target="#getTree"><i class="fa fa-search"></i></button></span>
                      </div>
                    </div>
                  </div>
                </div>
                <!--现岗位-->
                <div class="col-md-4 col-lg-4">
                  <div class="form-group">
                    <label for="" class="control-label col-md-5 col-lg-5">现岗位：</label>
                    <div class="col-md-7 col-lg-7">
                      <select class="form-control " name="newPost">
                        {{each post as key idx}}
                        <option value="{{key.baseDataId}}">{{key.baseDataName}}</option>
                        {{/each}}
                      </select>
                    </div>
                  </div>
                </div>
                <!--现职级-->
                <div class="col-md-4 col-lg-4">
                  <div class="form-group">
                    <label for="" class="control-label col-md-5 col-lg-5">现职级：</label>
                    <div class="col-md-7 col-lg-7">
                      <select class="form-control " name="newRank">
                        {{each rank as key idx}}
                        <option value="{{key.baseDataId}}">{{key.baseDataName}}</option>
                        {{/each}}
                      </select>
                    </div>
                  </div>
                </div>
                <!--当前汇报对象-->
                <div class="col-md-4 col-lg-4">
                  <div class="form-group">
                    <label for="" class="control-label col-md-5 col-lg-5">当前汇报对象：</label>
                    <div class="col-md-7 col-lg-7">
                      <input type="text" class="form-control" id="" name="newReport">
                    </div>
                  </div>
                </div>
                <!--当前工作地-->
                <div class="col-md-4 col-lg-4">
                  <div class="form-group">
                    <label for="" class="control-label col-md-5 col-lg-5">当前工作地：</label>
                    <div class="col-md-7 col-lg-7">
                      <select class="form-control " name="newLocation">
                        {{each city as key idx}}
                        <option value="{{key.baseDataId}}">{{key.baseDataName}}</option>
                        {{/each}}
                      </select>
                    </div>
                  </div>
                </div>
                <!--当前成本中心-->
                <div class="col-md-4 col-lg-4">
                  <div class="form-group">
                    <label for="" class="control-label col-md-5 col-lg-5">当前成本中心：</label>
                    <div class="col-md-7 col-lg-7">
                      <select class="form-control " name="newCost"> 
                        {{each cost as key idx}}
                        <option value="{{key.baseDataId}}">{{key.baseDataName}}</option>
                        {{/each}}
                      </select>
                    </div>
                  </div>
                </div>
                <!--岗位调整日期-->
                <div class="col-md-4 col-lg-4">
                  <div class="form-group">
                    <label for="" class="control-label col-md-5 col-lg-5">岗位调整日期：</label>
                    <div class="col-md-7 col-lg-7">
                      <div class="input-group date">
                        <input type="text" class="form-control" required="required" id="" name="newDate">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                      </div>
                    </div>
                  </div>
                </div>
                <!--是否调薪--> 
                <div class="col-md-4 col-lg-4">
                  <div class="form-group">
                    <label for="" class="control-label col-md-5 col-lg-5">是否调薪：</label>
                    <div class="col-md-7 col-lg-7">
                      <div class="radio-group form-control-static">
                        <label class="radio-inline">
                          <input type="radio" name="isChangeSalary" value="1" checked="checked"> 是
                        </label>
                        <label class="radio-inline">
                          <input type="radio" name="isChangeSalary" value="0"> 否
                        </label>
                      </div>
                    </div>

                  </div>
                </div>
                <!--岗位调动原因-->
                <div class="col-md-4 col-lg-4">
                  <div class="form-group">
                    <label for="" class="control-label col-md-5 col-lg-5">岗位调动原因：</label>
                    <div class="col-md-7 col-lg-7">
                      <input type="text" class="form-control" id="" name="cause">
                    </div>
                  </div>
                </div>
                <!--保存 删除-->
                <div class="col-lg-12 col-md-12 text-center form-action">
                  <button type="button"  class="btn btn-info" name="addJobPost" data-action-motive="submit"
                   data-action-callback="addJobPost|create_new">保存</button>
                </div>
              </form>
              {{/each}}
            </script>
            <div class="gangweis">
            </div>
            <!--岗位调动保存 end-->
            <div id="gangwei_body">
            </div>
          </div>
        </div>
      </div>
    </div>
    <!--岗位调动 end--> 
    <!--结束 -->     

    <!-- Footer-->
    <!-- 放页脚  开始-->
    <jsp:include flush="true" page="../common/foot.div.jsp"></jsp:include>
    <!-- 放页脚  结束-->
  </div>


  <div class="modal fade hmodal-success" id="getTree" tabindex="-1" role="dialog"  aria-hidden="true">
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
<script src="static/js/yuangong.js"></script>
<script src="static/js/common.js"></script>  
<script type="text/javascript" src="static/js/zTreeJs/zTreeJs/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="static/js/zTreeJs/zTreeJs/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript" src="static/js/zTreeJs/zTreeJs/jquery.ztree.exedit-3.5.js"></script>

<script type="text/javascript">
//修改
function submitJobPost(obj) {
	 var id = obj.form.id;
	 
	  var updateSourcePost = $('#'+id).find("[name='updateSourcePost']").val();
	 var updateSourceRank = $('#'+id).find("[name='updateSourceRank']").val();
	 var updateSourceCost = $('#'+id).find("[name='updateSourceCost']").val();
	 var sourceDepartment = $('#'+id).find("[name='sourceDepartment']").val();
	 var sourceReport = $('#'+id).find("[name='sourceReport']").val();
	 
	 $('#'+id).find("[name='sourcePost']").val(updateSourcePost);
	 $('#'+id).find("[name='sourceRank']").val(updateSourceRank);
	 $('#'+id).find("[name='sourceCost']").val(updateSourceCost);
	  console.log('updateSourcePost' +updateSourcePost);
	  console.log('updateSourceRank' +updateSourceRank);
	  console.log('updateSourceCost' +updateSourceCost);
	  console.log('sourceDepartment' +sourceDepartment);
	  console.log('sourceReport' +sourceReport);
 var newDepartment = $('#'+id).find("[name='newDepartment']").val();
		var newReport = $('#'+id).find("[name='newReport']").val();
		if(newDepartment==''){
			alert('请选择现部门');
			return false;
		}
		if(newReport==''){
			alert('请选择当前汇报对象');
			return false;
		}
		//保存按钮 设为disabled
		 $('#'+id).find("[name='updateJobPost']").attr("disabled","disabled");
       $.ajax({
		type : 'post',
		url : "employee/updateJobpost",
		data : $("#"+id).serialize(),
		async : true,
		dataType: "json",
		success : function(result) {
			alert(result.msg);
			  window.location.reload();
		}
	});   
 }


 //新增 
function addJobPost(obj) {
	 var id = obj[0];
	// alert($("#"+id).serialize());
//	alert(id +'iddddd');
var addSourcePost = $('#'+id).find("[name='addSourcePost']").val();
var addSourceRank = $('#'+id).find("[name='addSourceRank']").val();
var addSourceCost = $('#'+id).find("[name='addSourceCost']").val();

 $("#sourcePost").val(addSourcePost);
 $("#sourceRank").val(addSourceRank);
 $("#sourceCost").val(addSourceCost);

	var newDepartment = $('#'+id).find("[name='newDepartment']").val();
	var newReport = $('#'+id).find("[name='newReport']").val();
	if(newDepartment==''){
		alert('请选择现部门');
		return false;
	}
	if(newReport==''){
		alert('请选择当前汇报对象');
		return false;
	}
	//保存按钮 设为disabled
	 $('#'+id).find("[name='addJobPost']").attr("disabled","disabled");
	  $.ajax({
		type : 'post',
		url : "employee/addJobpost",
		data : $("#"+id).serialize(),
		async : true,
		dataType: "json",
		success : function(result) {
			alert(result.msg);
			window.location.reload();
		}
	}); 
}

var personId = $("#personId").val();
// alert(personId+"  personId");

var listURL="employee/jobpostInfo?personId="+personId;	//渲染URL
var delsURL="employee/delJobpost.do?postId=";		//删除URL
var addURL="employee/addEducation";							//新增URL
var updateURL="employee/updateEducation";					//修改URL
//var addFormSelector="[name=edu_add_form]";					//新增表单
//var initFormSelector="#create_new";							//新增表单id
var templateID="transfer";									//模板ID
var containerID= $('.gangweis');						//渲染结果显示区域
var url="employee/jobpostInfo?personId="+personId;
var uploadFileType="";

templateFillData(templateID, containerID, listURL,delsURL,'ehrPositionList');
//  templateFillData('transfer', $('.gangweis'), listURL,delsURL);


</script>

<script>
  function deleteBtnInfo(){
  };
    $(function(){
	deleteBtnInfo();
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

<script>
  var setting = {
    view: {
      dblClickExpand: false,
      showLine:false
    },
    check: {
      enable: true
    },
    callback: {
      onClick: zTreeOnClick
    },
    data: {
      simpleData: {
        enable: true
      }
    },
    treeNode:{
      nocheck:true
    }
  };
  function zTreeOnClick(event, treeId, treeNode){
    var $modal = $('#getTree'),
        $sourceDepartment = $(domTreeRelatedTarget).parent().siblings('.form-control');
    	$departmentId = $(domTreeRelatedTarget).parents('.input-group').siblings('[data-department-id]');
    $modal.modal('hide');
    $sourceDepartment.val( treeNode.name );
    $departmentId.val(treeNode.id);
    // console.log(treeNode.name)
  };
  function loadData(targetDom){
    //组织架构树引用json
    var zNodes =[];
    var personId = $("#personId").val();
    $.ajax({
       url:"system/loadPersonTree",
      data:{personId:personId},
       type : "POST",
      dataType:'json',
      error: function(XMLHttpRequest, textStatus, errorThrown){
         alert(textStatus);
      },
      success: function(data){
        var strs = data.data;
        for(var i = 0; i < strs.length; i++){
          var obj = new Object();
          obj.id = strs[i].organizationId;
          obj.pId = strs[i].parentId;
          obj.name = strs[i].organizationName;
          obj.code = strs[i].organizationCode;
          obj.memo = strs[i].memo;
          obj.open = true;
          zNodes.push(obj);
        }
        $.fn.zTree.init($(targetDom), setting, zNodes);
      }
    });
  }
  var domTreeRelatedTarget = null;
  $('#getTree').on('show.bs.modal', function(e){
    // e.relatedTarget
    domTreeRelatedTarget = e.relatedTarget;
    var getTargetDom = $('#orgTree');
    loadData(getTargetDom);
  });
</script>
</body>
</html>