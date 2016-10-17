<%@ page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <base href="${pageContext.request.contextPath}/">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <!-- Page title -->
    <title>劳动合同</title>
    <jsp:include flush="true" page="../common/styles.jsp"></jsp:include>
    <link rel="stylesheet" href="static/js/plugins/form.validation/css/formValidation.css">
    <link rel="stylesheet" href="static/css/user_bata.css">
    <link rel="stylesheet" href="static/css/style_1.css" media="screen">
    <link rel="stylesheet" href="static/css/bill.css">
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
    <div class="content animate-panel">
      <div class="row">
        <div class="col-lg-12">
          <div class="hpanel">
            <div class="panel-heading m-b-lg">
              <h4>
                <a onclick="toEmployeeList()"  type="button" class="btn btn-default btn-sm btn-absolute">返　回</a>
              </h4>
            </div>
            <!-- 放主要内容 -->
            <!--引入员工信息导航 开始--> 
            <jsp:include flush="true" page="employee_nav.jsp">
            <jsp:param value="contract" name="type"/>
          </jsp:include>
          <!--引入员工信息导航 结束-->

          <!--劳动合同-->
          <div class="panel-body">
            <div class="panel-heading">
                  <h4 class="text-center font-bold">劳动合同</h4>
            </div>
            <div class="row m-b-lg">
            <input type="hidden" name="personId" value="${personId}" id="personId">
            <script type="text/html" id="laodongss">
              {{each contractList}}
             <div class="col-md-12 page-header m-t-md">
                <div class="panel-tools action-group">
                  <ul class="list-inline">
                     <li>
                    <a href="javascript://" data-action-motive="hideOrshow" data-action-id="{{$value.contractId}}"><i class="pe-7s-angle-down-circle pe-2x text-info"></i><span class="sr-only">隐藏</span></a>
                    </li>
                    <li>
                    <a href="javascript://" data-action-motive="edit" data-action-id="{{$value.contractId}}"><i class="pe-7s-note pe-2x text-info">
                    </i><span class="sr-only">修改</span></a>
                    </li>
                    <li>
                      <a href="javascript://" data-action-motive="del" data-action-id="{{$value.contractId}}"><i class="pe-7s-trash pe-2x text-info"></i><span class="sr-only">删除</span></a>
                    </li>
                  </ul>
                </div>
                <ul class="list-inline">
                  <li><p class="form-control-static text-info font-bold">
                  {{if $value.contractStartDate}}
                  {{$value.contractStartDate.substr(0,10)}}
                  {{/if}}
                  &nbsp;</p></li>
                  <li class="m-r"><p class="form-control-static text-info font-bold">至</p></li>
                  <li><p class="form-control-static text-info font-bold">
                  {{if $value.contractEndDate}}
                  {{$value.contractEndDate.substr(0,10)}}
                  {{/if}}
                  </p></li>
                  <li><p class="form-control-static text-info font-bold">
                    {{each ['劳动合同','劳务协议','实习协议','保密协议','竞业限制协议','培训协议'] as $item $idx}}
                        {{if $value.contractType == $idx+1}}
                            {{$item}}
                        {{/if}}
                    {{/each}}
                  </p></li>
                  <li><p class="form-control-static text-info font-bold">
                    {{each ['固定期限','无固定期限','以完成一定工作任务为期限'] as $item $idx}}
                        {{if $value.contractDeadlineType == $idx+1}}
                            {{$item}}
                        {{/if}}
                    {{/each}}
                  </p></li>
                  <li><p class="form-control-static text-info font-bold">
                    {{each ['签订','续签'] as $item $idx}}
                        {{if $value.contractBussinessType == $idx+1}}
                            {{$item}}
                        {{/if}}
                    {{/each}}
                  </p></li>
               </ul>
              </div>

              <form role="form" data-form="{{$value.contractId}}" method="post" class="searchs-form form-horizontal col-md-12 col-lg-12">
                <input type="hidden" name="contractId" value="{{$value.contractId}}">
                  <!--合同类型-->
                    <div class="col-md-6 col-lg-6">
                      <div class="form-group">
                        <label for="" class="control-label col-md-4 col-lg-4">合同类型：</label>
                        <div class="col-md-8 col-lg-8">
                            <div class="input-group-static">
                                <p class="form-control-static">
                                {{each ['劳动合同','劳务协议','实习协议','保密协议','竞业限制协议','培训协议'] as $item $idx}}
                                    {{if $value.contractType == $idx+1}}
                                        {{$item}}
                                    {{/if}}
                                {{/each}}
                                &nbsp;</p>
                            </div>
                            <div class="form-required">
                                <select class="form-control " name="contractType">
                                {{each ['劳动合同','劳务协议','实习协议','保密协议','竞业限制协议','培训协议'] as $item $idx}}
                                    <option value="{{$idx+1}}"{{if $value.contractType == $idx+1}} selected="selected"{{/if}}>{{$item}}</option>
                                {{/each}}
                                </select>
                            </div>
                        </div>
                      </div>
                    </div>
                    <!--开始时间-->
                    <div class="col-md-6 col-lg-6">
                        <div class="form-group">
                            <label for="" class="control-label col-md-4 col-lg-4">签订日期：</label>
                            <div class="col-md-8 col-lg-8">
                                <div class="input-group-static">
                                    <p class="form-control-static">
                                    {{if $value.contractSignDate}}
                                    {{$value.contractSignDate.substr(0,10)}}
                                    {{/if}}
                                    &nbsp;</p>
                                </div>
                                <div class="form-required">
                                    <div class="input-group date" data-toggle="datepicker" data-data-format="yyy-mm-dd">
                                        <input type="text" class="form-control" name="contractSignDate" value="{{if $value.contractSignDate}}{{$value.contractSignDate.substr(0,10)}}{{/if}}">
                                        <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!--业务类型-->
                    <div class="col-md-6 col-lg-6">
                        <div class="form-group">
                            <label for="" class="control-label col-md-4 col-lg-4">业务类型：</label>
                            <div class="col-md-8 col-lg-8">
                                <div class="input-group-static">
                                    <p class="form-control-static">
                                    {{each ['签订','续签'] as $item $idx}}
                                        {{if $value.contractBussinessType == $idx+1}}
                                            {{$item}}
                                        {{/if}}
                                    {{/each}}
                                    &nbsp;</p>
                                </div>
                                <div class="form-required">
                                    <select class="form-control" name="contractBussinessType">
                                    {{each ['签订','续签'] as $item $idx}}
                                        <option value="{{$idx+1}}"{{if $value.contractBussinessType == $idx+1}} selected="selected"{{/if}}>{{$item}}</option>
                                    {{/each}}
                                    </select>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- 合同主体单位 -->
                    <div class="col-md-6 col-lg-6">
                        <div class="form-group">
                            <label for="" class="control-label col-md-4 col-lg-4">合同主体单位：</label>
                            <div class="col-md-8 col-lg-8">
                                <div class="input-group-static">
                                    <p class="form-control-static">{{$value.contractMainUnit}}&nbsp;</p>
                                </div>
                                <div class="form-required">
                                    <input type="text" class="form-control" name="contractMainUnit" value="{{$value.contractMainUnit}}">
                                </div>
                            </div>
                        </div>
                    </div>
                    <!--合同期限类型-->
                    <div class="col-md-6 col-lg-6">
                        <div class="form-group">
                            <label for="" class="control-label col-md-4 col-lg-4">合同期限类型：</label>
                            <div class="col-md-8 col-lg-8">
                                <div class="input-group-static">
                                    <p class="form-control-static">
                                    {{each ['固定期限','无固定期限','以完成一定工作任务为期限'] as $item $idx}}
                                        {{if $value.contractBussinessType == $idx+1}}
                                            {{$item}}
                                        {{/if}}
                                    {{/each}}
                                    &nbsp;</p>
                                </div>
                                <div class="form-required">
                                    <select class="form-control" name="contractDeadlineType">
                                    {{each ['固定期限','无固定期限','以完成一定工作任务为期限'] as $item $idx}}
                                    <option value="{{$idx+1}}"{{if $value.contractDeadlineType == $idx+1}} selected="selected"{{/if}}>{{$item}}</option>
                                    {{/each}}
                                    </select>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!--合同开始日期-->
                    <div class="col-md-6 col-lg-6">
                        <div class="form-group">
                            <label for="" class="control-label col-md-4 col-lg-4">合同开始日期：</label>
                            <div class="col-md-8 col-lg-8">
                                <div class="input-group-static">
                                    <p class="form-control-static">{{$value.contractStartDate.substr(0,10)}}&nbsp;</p>
                                </div>
                                <div class="form-required">
                                    <div class="input-group date" data-toggle="datepicker" data-data-format="yyy-mm-dd">
                                        <input type="text" class="form-control" name="contractStartDate" value="{{$value.contractStartDate.substr(0,10)}}">
                                        <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!--合同期限-->
                    <div class="col-md-6 col-lg-6">
                        <div class="form-group">
                            <label for="" class="control-label col-md-4 col-lg-4">合同期限：</label>
                            <div class="col-md-8 col-lg-8">
                                <div class="input-group-static">
                                    <p class="form-control-static">{{$value.contractMonth}} 个月&nbsp;</p>
                                </div>
                                <div class="input-group">
                                    <input type="text" class="form-control" name="contractMonth" value="{{$value.contractMonth}}">
                                    <span class="input-group-addon">个月</span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!--合同结束日期-->
                    <div class="col-md-6 col-lg-6">
                        <div class="form-group">
                            <label for="" class="control-label col-md-4 col-lg-4">合同结束日期：</label>
                            <div class="col-md-8 col-lg-8">
                                <div class="input-group-static">
                                    <p class="form-control-static">
									{{if $value.contractEndDate}}
										{{$value.contractEndDate.substr(0,10)}}
									{{/if}}&nbsp;
									</p>
                                </div>
                                <div class="form-required">
                                    <div class="input-group date" data-toggle="datepicker" data-data-format="yyy-mm-dd">
                                        <input type="text" class="form-control" name="contractEndDate" value="{{if $value.contractEndDate}}{{$value.contractEndDate.substr(0,10)}}{{/if}}">
                                        <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6 col-lg-6" data-action="trial">
                        <div class="form-group">
                            <div class="col-md-8 col-lg-8 col-md-offset-4 col-lg-offset-4">

                                <div class="input-group-static">
                                    <p class="form-control-static">
是否试用：
                                    {{if $value.isTrial == 1}}
                                    是
                                    {{else}}
                                    否
                                    {{/if}}
                                    &nbsp;</p>
                                </div>
                                <div>
                                <label class="checkbox-inline">
                                    <input type="checkbox" name="isTrial" value="1" data-action="trial"{{if $value.isTrial == 1}}checked="checked"{{/if}}>
                                    是否试用</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6 col-lg-6{{if $value.isTrial != 1}} hidden{{/if}}" data-action-for="trial">
                        <div class="form-group">
                            <label for="" class="control-label col-md-4 col-lg-4">试用期限：</label>
                            <div class="col-md-8 col-lg-8">
                                <div class="input-group-static">
                                    <p class="form-control-static">{{$value.trialMonth}} 个月&nbsp;</p>
                                </div>
                                <div class="form-required">
                                    <div class="input-group">
                                        <input type="text" class="form-control" name="trialMonth" value="{{$value.trialMonth}}">
                                        <span class="input-group-addon">个月</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!--计划转正时间-->
                    <div class="col-md-6 col-lg-6{{if $value.isTrial != 1}} hidden{{/if}}" data-action-for="trial">
                        <div class="form-group">
                            <label for="" class="control-label col-md-4 col-lg-4">计划转正时间：</label>
                            <div class="col-md-8 col-lg-8">
                                <div class="input-group-static">
                                    <p class="form-control-static">
                                    {{if $value.planPositiveDate}}
                                    {{$value.planPositiveDate.substr(0,10)}}
                                    {{/if}}
                                    &nbsp;</p>
                                </div>
                                <div class="form-required">
                                    <div class="input-group date" data-toggle="datepicker" data-data-format="yyy-mm-dd">
                                        <input type="text" class="form-control" name="planPositiveDate" value="{{if $value.planPositiveDate}}{{$value.planPositiveDate.substr(0,10)}}{{/if}}">
                                        <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!--实际转正时间-->
                    <div class="col-md-6 col-lg-6{{if $value.isTrial != 1}} hidden{{/if}}" data-action-for="trial">
                        <div class="form-group">
                            <label for="" class="control-label col-md-4 col-lg-4">实际转正时间：</label>
                            <div class="col-md-8 col-lg-8">
                                <div class="input-group-static">
                                    <p class="form-control-static">
                                    {{if $value.factPositiveDate}}
                                    {{$value.factPositiveDate.substr(0,10)}}
                                    {{/if}}
                                    &nbsp;</p>
                                </div>
                                <div class="input-group">
                                    <input type="text" class="form-control" name="factPositiveDate" readonly="readonly" value="{{if $value.factPositiveDate}}{{$value.factPositiveDate.substr(0,10)}}{{/if}}">
                                    <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!--试用期工资-->
                    <div class="col-md-6 col-lg-6">
                        <div class="form-group">
                            <label for="" class="control-label col-md-4 col-lg-4">试用期工资：</label>
                            <div class="col-md-8 col-lg-8">
                                <div class="input-group-static">
                                    <p class="form-control-static">{{$value.trialSalary}}&nbsp;</p>
                                </div>
                                <input type="text" class="form-control" name="trialSalary" value="{{$value.trialSalary}}">
                            </div>
                        </div>
                    </div>
                    <!--转正工资-->
                    <div class="col-md-6 col-lg-6">
                        <div class="form-group">
                            <label for="" class="control-label col-md-4 col-lg-4">转正工资：</label>
                            <div class="col-md-8 col-lg-8">
                                <div class="input-group-static">
                                    <p class="form-control-static">{{$value.positiveSalary}}&nbsp;</p>
                                </div>
                                <input type="text" class="form-control" name="positiveSalary" value="{{$value.positiveSalary}}">
                            </div>
                        </div>
                    </div>
                    <!--连续次数-->
                    <div class="col-md-6 col-lg-6">
                        <div class="form-group">
                            <label for="" class="control-label col-md-4 col-lg-4">连续次数：</label>
                            <div class="col-md-8 col-lg-8">
                                <div class="input-group-static">
                                    <p class="form-control-static">{{$value.continueTime}}&nbsp;</p>
                                </div>
                                <input type="text" class="form-control" name="continueTime" value="{{$value.continueTime}}">
                            </div>
                        </div>
                    </div>
                    <!--备注-->
                    <div class="col-md-6 col-lg-6">
                        <div class="form-group">
                            <label for="" class="control-label col-md-4 col-lg-4">备注：</label>
                            <div class="col-md-8 col-lg-8">
                                <div class="input-group-static">
                                    <p class="form-control-static">{{$value.memo}}&nbsp;</p>
                                </div>
                                <input type="text" class="form-control" name="memo" value="{{$value.memo}}">
                            </div>
                        </div>
                    </div>
                <div class="col-lg-12 col-md-12 text-center form-action">
                  <button type="submit" class="btn btn-info" data-action-motive="save">保存</button>
                  <button type="button" class="btn btn-default" data-action-motive="cancel">取消</button>
                </div>
              </form>
              {{/each}}
              <form role="form" method="post" data-form-type="base" class="form-horizontal col-md-12 col-lg-12 hetong hetong_form hidden" id="create_new" data-form-validator="validator">
                    <div class="col-md-12 col-lg-12">
                        <!--合同类型-->
                        <div class="col-md-6 col-lg-6">
                          <div class="form-group">
                            <label for="" class="control-label col-md-4 col-lg-4">合同类型：</label>
                            <div class="col-md-8 col-lg-8 form-required">
                              <select class="form-control " name="contractType">
                                {{each ['劳动合同','劳务协议','实习协议','保密协议','竞业限制协议','培训协议'] as $item $idx}}
                                    <option value="{{$idx+1}}">{{$item}}</option>
                                {{/each}}
                              </select>
                            </div>
                          </div>
                        </div>
                        <!--开始时间-->
                        <div class="col-md-6 col-lg-6">
                            <div class="form-group">
                                <label for="" class="control-label col-md-4 col-lg-4">签订日期：</label>
                                <div class="col-md-8 col-lg-8 form-required">
                                    <div class="input-group date" data-toggle="datepicker" data-data-format="yyy-mm-dd">
                                        <input type="text" class="form-control" name="contractSignDate" required="required">
                                        <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!--业务类型-->
                        <div class="col-md-6 col-lg-6">
                            <div class="form-group">
                                <label for="" class="control-label col-md-4 col-lg-4">业务类型：</label>
                                <div class="col-md-8 col-lg-8 form-required">
                                    <select class="form-control" name="contractBussinessType">
                                    {{each ['签订','续签'] as $item $idx}}
                                        <option value="{{$idx+1}}">{{$item}}</option>
                                    {{/each}}
                                    </select>
                                </div>
                            </div>
                        </div>
                        <!-- 合同主体单位 -->
                        <div class="col-md-6 col-lg-6">
                            <div class="form-group">
                                <label for="" class="control-label col-md-4 col-lg-4">合同主体单位：</label>
                                <div class="col-md-8 col-lg-8 form-required">
                                    <input type="text" class="form-control" name="contractMainUnit" required="required">
                                </div>
                            </div>
                        </div>
                        <!--合同期限类型-->
                        <div class="col-md-6 col-lg-6">
                            <div class="form-group">
                                <label for="" class="control-label col-md-4 col-lg-4">合同期限类型：</label>
                                <div class="col-md-8 col-lg-8 form-required">
                                    <select class="form-control" name="contractDeadlineType" >
                                        {{each ['固定期限','无固定期限','以完成一定工作任务为期限'] as $item $idx}}
                                        <option value="{{$idx+1}}">{{$item}}</option>
                                        {{/each}}
                                    </select>
                                </div>
                            </div>
                        </div>
                        <!--合同开始日期-->
                        <div class="col-md-6 col-lg-6">
                            <div class="form-group">
                                <label for="" class="control-label col-md-4 col-lg-4">合同开始日期：</label>
                                <div class="col-md-8 col-lg-8 form-required">
                                    <div class="input-group date" data-toggle="datepicker" data-data-format="yyy-mm-dd">
                                        <input type="text" class="form-control" name="contractStartDate">
                                        <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!--合同期限-->
                        <div class="col-md-6 col-lg-6">
                            <div class="form-group">
                                <label for="" class="control-label col-md-4 col-lg-4">合同期限：</label>
                                <div class="col-md-8 col-lg-8">
                                    <div class="input-group">
                                        <input type="text" class="form-control" name="contractMonth">
                                        <span class="input-group-addon">个月</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!--合同结束日期-->
                        <div class="col-md-6 col-lg-6">
                            <div class="form-group">
                                <label for="" class="control-label col-md-4 col-lg-4">合同结束日期：</label>
                                <div class="col-md-8 col-lg-8 form-required">
                                    <div class="input-group date" data-toggle="datepicker" data-data-format="yyy-mm-dd">
                                        <input type="text" class="form-control" name="contractEndDate">
                                        <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6 col-lg-6" data-action="trial">
                            <div class="form-group">
                                <div class="col-md-8 col-lg-8 col-md-offset-4 col-lg-offset-4">
                                    <label class="checkbox-inline">
                                        <input type="checkbox" name="isTrial" value="1">
                                        是否试用</label>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6 col-lg-6 hidden" data-action-for="trial">
                            <div class="form-group">
                                <label for="" class="control-label col-md-4 col-lg-4">试用期限：</label>
                                <div class="col-md-8 col-lg-8 form-required">
                                    <div class="input-group">
                                        <input type="text" class="form-control" name="trialMonth">
                                        <span class="input-group-addon">个月</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!--计划转正时间-->
                        <div class="col-md-6 col-lg-6 hidden" data-action-for="trial">
                            <div class="form-group">
                                <label for="" class="control-label col-md-4 col-lg-4">计划转正时间：</label>
                                <div class="col-md-8 col-lg-8 form-required">
                                    <div class="input-group date" data-toggle="datepicker" data-data-format="yyy-mm-dd">
                                        <input type="text" class="form-control" name="planPositiveDate">
                                        <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!--实际转正时间-->
                        <div class="col-md-6 col-lg-6 hidden" data-action-for="trial">
                            <div class="form-group">
                                <label for="" class="control-label col-md-4 col-lg-4">实际转正时间：</label>
                                <div class="col-md-8 col-lg-8">
                                    <div class="input-group">
                                        <input type="text" class="form-control" name="factPositiveDate" readonly="readonly">
                                        <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-12 col-lg-12">
                        <!--试用期工资-->
                        <div class="col-md-6 col-lg-6">
                            <div class="form-group">
                                <label for="" class="control-label col-md-4 col-lg-4">试用期工资：</label>
                                <div class="col-md-8 col-lg-8">
                                    <input type="text" class="form-control" name="trialSalary">
                                </div>
                            </div>
                        </div>
                        <!--转正工资-->
                        <div class="col-md-6 col-lg-6">
                            <div class="form-group">
                                <label for="" class="control-label col-md-4 col-lg-4">转正工资：</label>
                                <div class="col-md-8 col-lg-8">
                                    <input type="text" class="form-control" name="positiveSalary">
                                </div>
                            </div>
                        </div>
                        <!--连续次数-->
                        <div class="col-md-6 col-lg-6">
                            <div class="form-group">
                                <label for="" class="control-label col-md-4 col-lg-4">连续次数：</label>
                                <div class="col-md-8 col-lg-8">
                                    <input type="text" class="form-control" name="continueTime">
                                </div>
                            </div>
                        </div>
                        <!--备注-->
                        <div class="col-md-6 col-lg-6">
                            <div class="form-group">
                                <label for="" class="control-label col-md-4 col-lg-4">备注：</label>
                                <div class="col-md-8 col-lg-8">
                                    <input type="text" class="form-control" name="memo">
                                </div>
                            </div>
                        </div>
                    </div>
                <div class="col-lg-12 col-md-12 text-right">
                  <button  class="btn btn-success" type="submit">保存</button>
                  <button type="button" class="btn btn-default" data-action-motive="cancel">取消</button>
                </div>
              </form>
            </script>
            <div class="hpanel" id="laodong_div">
            </div>

            <div id="hetong_body" data-form-target="content"></div>
            </div>
            <!--添加-->
            <div class="text-center row">
              <a class="adds" data-form-action="add" href="javascript://"><i class=" pe-7s-plus pe-5x text-muted"></i><br><span class="text-muted">添加新记录</span></a>
            </div>
          </div> 
        </div>
      </div>
    </div>
  </div>    
  <!--劳动合同 end-->

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
<script src="static/bootstrap/vendor/bootstrap-datepicker-master/dist/locales/bootstrap-datepicker.zh-CN.min.js"></script>
<!-- for datatable -->
<script src="static/bootstrap/vendor/datatables/media/js/jquery.dataTables.min.js"></script>
<script src="static/bootstrap/vendor/datatables_plugins/integration/bootstrap/3/dataTables.bootstrap.min.js"></script>
<!-- alert -->
<script src="static/bootstrap/vendor/sweetalert/lib/sweet-alert.min.js"></script>

<script src="static/js/plugins/form.validation/js/formValidation.js"></script>
<script src="static/js/plugins/form.validation/js/framework/bootstrap.js"></script>
<script src="static/js/plugins/form.validation/js/language/zh_CN.js"></script> 
<!-- App scripts -->
<script src="static/bootstrap/scripts/homer.js"></script>
<script type="text/javascript" src="static/js/work_contract.js"></script>
<script src="static/js/template.js"></script>
<script src="static/js/yuangong.js"></script>
<script src="static/js/commonH3.js"></script>
<script>
var personId = $("#personId").val();
var url='employee/contractInfo?personId='+personId;
var addURL = 'employee/addContract';
var updateURL = 'employee/updateContract';
var containerID = $('#laodong_div');
var templateID = 'laodongss';
var listURL = url;
var delsURL = 'employee/delContract?contractId=';
var addFormSelector="[name=add_new_form]";
  templateFillData('laodongss', $('#laodong_div'), url,'employee/delContract?contractId=', 'contractList');
 


    //校验
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
            contractType: {
                validators: {
                    notEmpty: {
                        message: '请填写必填项目'
                    }
                }
            },
            contractBussinessType: {
                validators: {
                    notEmpty: {
                        message: '请填写必填项目'
                    }
                }
            },
            contractDeadlineType: {
                validators: {
                    notEmpty: {
                        message: '请填写必填项目'
                    }
                }
            },
            contractMainUnit: {
                validators: {
                    notEmpty: {
                        message: '请填写必填项目'
                    }
                }
            },
            contractSignDate: {
                validators: {
                    notEmpty: {
                        message: '请填写必填项目'
                    },
                    date: {
                        format: 'YYYY-MM-DD',
                        message: '该日期是无效的'
                    }
                }
            },
            contractStartDate: {
                validators: {
                    notEmpty: {
                        message: '请填写必填项目'
                    },
                    date: {
                      format: 'YYYY-MM-DD',
                      message: '该日期是无效的'
                    }
                }
            },
            contractEndDate: {
                validators: {
                    notEmpty: {
                        message: '请填写必填项目'
                    },
                    date: {
                      format: 'YYYY-MM-DD',
                      message: '该日期是无效的'
                    }
                }
            },
            planPositiveDate: {
                validators: {
                    notEmpty: {
                        message: '请填写必填项目'
                    },
                    date: {
                      format: 'YYYY-MM-DD',
                      message: '该日期是无效的'
                    }
                }
            },
            trialMonth: {
                validators: {
                    notEmpty: {
                        message: '请填写必填项目'
                    }
                }
            }
        }
    };




    //删除
     function deleteBtnInfo(){
      $('.demo4').click(function () {
            swal({
                        title: "确定要删除此用户吗?",
                        text: "注意：用户删除后将不可登录!",
                        type: "warning",
                        showCancelButton: true,
                        confirmButtonColor: "#DD6B55",
                        confirmButtonText: "是,请删除该用户!",
                        cancelButtonText: "不, 放弃此操作!",
                        closeOnConfirm: false,
                        closeOnCancel: false },
                    function (isConfirm) {
                        if (isConfirm) {
                            swal("删除成功!", "该用户已经被删除.", "success");
                        } else {
                            swal("已取消", "用户未删除。你这逗我玩呢 :)", "error");
                        }
                    });
        });
    }
  //取消效果
  $(".guanbi").click(function(){
      
     $("#myModal7").modal('hide');
     $("#myModal8").modal('hide');
     $("#myModal9").modal('hide');
      
  })

//日历

        $(function(){
            deleteBtnInfo();
            $('#datepicker').datepicker();
            $("#datepicker").on("changeDate", function(event) {
                $("#my_hidden_input").val(
                        $("#datepicker").datepicker('getFormattedDate')
                )
            });

            $('#datapicker2').datepicker();
            // $('.input-group.date').datepicker({ });
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

            //turn to inline mode
            $.fn.editable.defaults.mode = 'inline';

            //defaults
            $.fn.editable.defaults.url = '#';

            //editables
            $('#username').editable({
                url: '#',
                type: 'text',
                pk: 1,
                name: 'username',
                title: 'Enter username'
            });

            $('#firstname').editable({
                validate: function(value) {
                    if($.trim(value) == '') return 'This field is required';
                }
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

 //删除
    $(function () {
        $('.demo4').click(function () {
            swal({
                        title: "确定要作废该协议吗?",
                        type: "warning",
                        showCancelButton: true,
                        confirmButtonColor: "#DD6B55",
                        confirmButtonText: "是,请作废该协议!",
                        cancelButtonText: "不, 放弃此操作!",
                        closeOnConfirm: false,
                        closeOnCancel: false },
                    function (isConfirm) {
                        if (isConfirm) {
                            swal("作废成功!", "该协议已经被作废.", "success");
                        } else {
                            swal("已取消", "该协议未作废。你这逗我玩呢 :)", "error");
                        }
                    });
        });

    

    });


  $(".guanbi").click(function(){
      
     $("#myModal7").modal('hide');
      
  })

 //全部导出

      $(".jxdc").click(function(){
         $(".modal-content").hide();
         $(".modal-backdrop").hide();
      })

      
//导出通讯录

    function downMB(moban) {
            window.open(moban);
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