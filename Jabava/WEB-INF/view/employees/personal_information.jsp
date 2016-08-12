<%@ page contentType="text/html; charset=utf-8" %>
<%@page import="com.jabava.utils.RequestUtil"%>
<%
String path = request.getContextPath();
String basePath = "//"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
<head>
	<base href="${pageContext.request.contextPath}/">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <!-- Page title -->
    <title>个人信息</title>
    <jsp:include flush="true" page="../common/styles.jsp"></jsp:include>
    <link rel="stylesheet" href="static/js/plugins/form.validation/css/formValidation.css">
    <link rel="stylesheet" href="static/bootstrap/vendor/blueimp-gallery/css/blueimp-gallery.min.css" />
    <link rel="stylesheet" href="static/css/user_bata.css">
    <link rel="stylesheet" href="static/css/style_1.css" media="screen">
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
    <!--个人信息-->
    <!-- 开始 -->
    <div class="content animate-panel">
      <div class="row">
        <div class="col-lg-12">
          <div class="hpanel">
            <div class="panel-heading m-b-lg">
            	<h4>
                	<a onclick="toEmployeeList()"  type="button" class="btn btn-default btn-sm btn-absolute">返　回</a>
                </h4>
            </div>
          
          <!--引入员工信息导航 开始--> 
            <jsp:include flush="true" page="employee_nav.jsp">
                <jsp:param value="personal" name="type"/>
            </jsp:include>
          <!--引入员工信息导航 结束-->
          
          <div class="panel-body">
            <h4 class="text-center m-b-md">个人信息</h4>
            <div id="personal_div">
            <script type="text/html" id="personal">

              <form data-form="{{person.personId}}" action="employee/updatePerson" id="personalForm" class="searchs-form form-horizontal geren_form" method="POST" method="post" enctype="multipart/form-data" data-form="validator">
                <div class="text-right action-group">
                <% if(RequestUtil.hasPower("roster_personal_mp")){ %>
                  <a href="javascript://" class="pe-7s-note pe-2x" data-action-id="{{person.personId}}" data-action-motive="edit" ><span class="sr-only">修改</span></a>
                <% } %>
                 </div>
                <input type="hidden" name="personId" value="{{person.personId}}"/> 
                <fieldset>
                  <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
                    <div class="form-group">
                      <label for="" class="col-xs-4 col-sm-4 col-md-4 col-lg-4 control-label">姓名：</label>
                      <div class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
                        <div class="input-group-static">
                          <p class="form-control-static">{{person.employeeName}}</p>
                        </div>
                        <div class="form-required">
                          <input type="text" class="form-control" name="employeeName" value="{{person.employeeName}}">
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
                    <div class="form-group">
                      <label for="" class="col-xs-4 col-sm-4 col-md-4 col-lg-4 control-label">性别：</label>
                      <div class="col-xs-8 col-sm-8 col-md-8 col-lg-8" >
                        <div class="input-group-static">
                          <p class="form-control-static">
                            {{if person.sex == 1}}
                            男
                            {{else if person.sex == 2}}
                            女
                            {{/if}}
                          </p>
                        </div>
                        <div class="form-control-static form-required">
                          <div class="radio radio-info radio-inline">
                              <input class="form-control" type="radio" name="sex" value="1" {{if (person.sex == 1)}}checked="true"{{/if}} >
                              <label for="inlineRadio1">男</label>
                          </div>
                          <div class="radio radio-info radio-inline">
                              <input class="form-control" type="radio" name="sex" value="2" {{if (person.sex == 2)}}checked="true"{{/if}}>
                              <label for="inlineRadio1">女</label>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
                    <div class="form-group">
                      <label for="" class="col-xs-4 col-sm-4 col-md-4 col-lg-4 control-label">家庭地址：</label>
                      <div class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
                        <div class="input-group-static">
                          <p class="form-control-static">{{person.familyAddress}}</p>
                        </div>
                        <div class="form-required">
                          <input type="text" class="form-control" name="familyAddress" value="{{person.familyAddress}}" required="required">
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
                    <div class="form-group" >
                      <label for="" class="col-xs-4 col-sm-4 col-md-4 col-lg-4 control-label">出生日期：</label>
                      <div class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
                        <div class="input-group-static">
                          <p class="form-control-static">{{person.birthDate | dateFormat}}</p>
                        </div>
                        <div class="input-group date" data-date-format="yyyy-mm-dd" >
                          <input type="text" class="form-control" name="birthDate" value="{{person.birthDate | dateFormat}}">
                          <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
                    <div class="form-group">
                      <label for="" class="col-xs-4 col-sm-4 col-md-4 col-lg-4 control-label">现居住地：</label>
                      <div class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
                        <div class="input-group-static">
                          <p class="form-control-static">{{person.contactAddress}}</p>
                        </div>
                        <div class="form-required">
                          <input type="text" class="form-control" name="contactAddress" value="{{person.contactAddress}}">
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
                    <div class="form-group">
                      <label for="" class="col-xs-4 col-sm-4 col-md-4 col-lg-4 control-label">联系电话：</label>
                      <div class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
                        <div class="input-group-static">
                          <p class="form-control-static">{{person.phone}}</p>
                        </div>
                        <input type="text" class="form-control" name="phone" value="{{person.phone}}">
                      </div>
                    </div>
                  </div>
                  <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
                    <div class="form-group">
                      <label for="" class="col-xs-4 col-sm-4 col-md-4 col-lg-4 control-label">邮编：</label>
                      <div class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
                         <div class="input-group-static">
                          <p class="form-control-static">{{person.postCode}}</p>
                        </div>
                        <input type="text" class="form-control" name="postCode" value="{{person.postCode}}">
                      </div>
                    </div>
                  </div>
                  <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
                    <div class="form-group ">
                      <label for="" class="col-xs-4 col-sm-4 col-md-4 col-lg-4 control-label">Email：</label>
                      <div class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
                        <div class="input-group-static">
                          <p class="form-control-static">{{person.email}}</p>
                        </div>
                        <input type="text" class="form-control" name="email" value="{{person.email}}">
                      </div>
                    </div>
                  </div>
                  <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
                    <div class="form-group">
                      <label for="" class="col-xs-4 col-sm-4 col-md-4 col-lg-4 control-label">最高学历：</label>
                      <div class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
                      <div class="input-group-static">
                          <p class="form-control-static">
                            {{each degree as b i}}
                            {{if (b.baseDataCode == person.degree)}}
                              {{b.baseDataName}}
                            {{/if}}
                            {{/each}}
                          </p>
                        </div>
                       	<div class="form-required">
                        <select class="form-control" name="degree">
                          {{each degree as b i}}
                          <option {{if (b.baseDataCode == person.degree)}}selected="true"{{/if}} value="{{b.baseDataCode}}">{{b.baseDataName}}</option>
                          {{/each}}
                        </select>
						</div>
                      </div>
                    </div>
                  </div>
                  <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
                    <div class="form-group">
                      <label for="" class="col-xs-4 col-sm-4 col-md-4 col-lg-4 control-label">证件类型：</label>
                      <div class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
                        <div class="input-group-static">
                          <p class="form-control-static">
                            {{each certType as b i}}
                            {{if (b.commonDataCode == person.certType)}}
                              {{b.commonDataName}}
                            {{/if}}
                            {{/each}}
                          </p>
                        </div>
                        <div class="form-required">
                          <select class="form-control" name="certType">
                            {{each certType as b i}}
                            <option {{if (b.commonDataCode == person.certType)}}selected="true"{{/if}} value="{{b.commonDataCode}}">{{b.commonDataName}}</option>
                            {{/each}}
                          </select>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
                    <div class="form-group">
                      <label for="" class="col-xs-4 col-sm-4 col-md-4 col-lg-4 control-label">最高学位：</label>
                      <div class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
                        <div class="input-group-static">
                          <p class="form-control-static">
                            {{each education as b i}}
                            {{if (b.baseDataCode == person.education)}}
                              {{b.baseDataName}}
                            {{/if}}
                            {{/each}}
                          </p>
                        </div>
						 <div class="form-required">
                          <select class="form-control" name="education">
                            {{each education as b i}}
                            <option {{if (b.baseDataCode == person.education)}}selected="true"{{/if}} value="{{b.baseDataCode}}">{{b.baseDataName}}</option>
                            {{/each}}
                          </select>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
                    <div class="form-group">
                      <label for="" class="col-xs-4 col-sm-4 col-md-4 col-lg-4 control-label">证件号：</label>
                      <div class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
                         <div class="input-group-static">
                          <p class="form-control-static">{{person.certId}}</p>
                        </div>
                        <div class="form-required">
                          <input type="text" class="form-control" name="certId" value="{{person.certId}}" required="required">
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
                    <div class="form-group">
                      <label for="" class="col-xs-4 col-sm-4 col-md-4 col-lg-4 control-label">开户行：</label>
                      <div class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
                        <div class="input-group-static">
                          <p class="form-control-static">
							{{person.bankName}}
                          </p>
                        </div>
                        <select class="form-control" name="bankName">
                          {{each bank as b i}}
                          <option {{if (b.baseDataName == person.bankName)}}selected="true"{{/if}} value="{{b.baseDataName}}">{{b.baseDataName}}</option>
                          {{/each}}
                        </select>
                      </div>
                    </div>
                  </div>
                  <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
                    <div class="form-group">
                      <label for="" class="col-xs-4 col-sm-4 col-md-4 col-lg-4 control-label">户口类型：</label>
                      <div class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
                        <div class="input-group-static">
                          <p class="form-control-static">
							 {{if person.registerType==1 }}
                                                               本地城镇
                            {{else if person.registerType==2 }}
								本地农村
                            {{else if person.registerType==3 }}
  								外地城镇
                            {{else if person.registerType==4 }}
								外地农村
							{{/if}}
                          </p>
                        </div>
                        <div class="form-required">
                          <select class="form-control" name="registerType">
							<option {{if (1 == person.registerType)}}selected="true"{{/if}} value="1">本地城镇</option>
							<option {{if (2 == person.registerType)}}selected="true"{{/if}} value="2">本地农村</option>
							<option {{if (3 == person.registerType)}}selected="true"{{/if}} value="3">外地城镇</option>
							<option {{if (4 == person.registerType)}}selected="true"{{/if}} value="4">外地农村</option>
                          </select>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
                    <div class="form-group">
                      <label for="" class="col-xs-4 col-sm-4 col-md-4 col-lg-4 control-label">开户支行：</label>
                      <div class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
                        <div class="input-group-static">
                          <p class="form-control-static">
                            {{person.subbank}}
                          </p>
                        </div>
                        <input type="text" name="subbank" class="form-control" value="{{person.subbank}}">
                      </div>
                    </div>
                  </div>
                  <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
                    <div class="form-group">
                      <label for="" class="col-xs-4 col-sm-4 col-md-4 col-lg-4 control-label">户口所在地：</label>
                      <div class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
                        <div class="input-group-static">
                          <p class="form-control-static">{{person.registerLocation}}</p>
                        </div>
                        <input type="text" class="form-control" name="registerLocation" value="{{person.registerLocation}}">
                      </div>
                    </div>
                  </div>
                  <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
                    <div class="form-group">
                      <label for="" class="col-xs-4 col-sm-4 col-md-4 col-lg-4 control-label">工资卡号：</label>
                      <div class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
                        <div class="input-group-static">
                          <p class="form-control-static">{{person.salaryCard}}</p>
                        </div>
                        <input type="text" class="form-control" name="salaryCard" value="{{person.salaryCard}}">
                      </div>
                    </div>
                  </div>
                  <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
                    <div class="form-group">
                      <label for="" class="col-xs-4 col-sm-4 col-md-4 col-lg-4 control-label">民族：</label>
                      <div class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
                        <div class="input-group-static">
                          <p class="form-control-static">{{person.nationality}}</p>
                        </div>
                        <div class="form-required">
                          <input type="text" class="form-control" name="nationality" value="{{person.nationality}}">
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
                    <div class="form-group">
                      <label for="" class="col-xs-4 col-sm-4 col-md-4 col-lg-4 control-label">政治面貌：</label>
                      <div class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
                        <div class="input-group-static">
                          <p class="form-control-static">
                          {{each politicsStatusList as $item}}
                            {{if $item.baseDataCode==person.politicsStatus}}
                              {{$item.baseDataName}}
                            {{/if}}
                          {{/each}}
                          &nbsp;
                          </p>
                        </div>
                        <select class="form-control" name="politicsStatus">
                        {{each politicsStatusList as $item}}
                          <option value="{{$item.baseDataCode}}"{{if $item.baseDataCode==person.politicsStatus}} selected="selected"{{/if}}>{{$item.baseDataName}}</option>
                        {{/each}}
                        </select>
                      </div>
                    </div>
                  </div>
                </fieldset>
                <div class="page-header text-right m-t-md">
                    <h4>
                      <a role="button" data-toggle="collapse" data-parent="#accordion" href="#showhide" aria-expanded="true" aria-controls="showhide">
                        <small class="text-info">收起选填信息 <i class="fa fa-chevron-up"></i></small>　
                    </a>
                    </h4>
                </div>
                <div id="showhide" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
                  <div class="row">
                    <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
                      <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                        <div class="form-group">
                          <label for="" class="col-xs-4 col-sm-4 col-md-4 col-lg-4 control-label">籍贯：</label>
                          <div class="col-xs-8 col-sm-8 col-md-8 col-lg-8"> 
                            <div class="input-group-static">
                              <p class="form-control-static">{{person.originPlace}}</p>
                             </div>
                            <input type="text" class="form-control" name="originPlace" value="{{person.originPlace}}">
                          </div>
                        </div>
                      </div>
                      <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                        <div class="form-group">
                          <label for="" class="col-xs-4 col-sm-4 col-md-4 col-lg-4 control-label">婚姻状况：</label>
                          <div class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
                            <div class="input-group-static">
                              <p class="form-control-static">
                                {{if person.marital==1 }}
                                  		 未婚                           	
                               {{else if person.marital==2 }}
										已婚
							   {{/if}}
                              </p>
                            </div>
                            <select class="form-control" name="marital">
							  <option {{if (1 == person.marital)}}selected="true"{{/if}} value="1">未婚</option>
							  <option {{if (2 == person.marital)}}selected="true"{{/if}} value="2">已婚</option>
                            </select>
                          </div>
                        </div>
                      </div>
                      <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                        <div class="form-group">
                          <label for="" class="col-xs-4 col-sm-4 col-md-4 col-lg-4 control-label">参加工作时间：</label>
                          <div class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
                            <div class="input-group-static">
                              <p class="form-control-static">{{person.firstjobDate | dateFormat}}</p>
                             </div>
                            <div class="input-group date">
                              <input type="text" class="form-control" name="firstjobDate"
                               value="{{person.firstjobDate | dateFormat}}">
                              <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                            </div>
                          </div>
                        </div>
                      </div>
                      <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                        <div class="form-group">
                          <label for="" class="col-xs-4 col-sm-4 col-md-4 col-lg-4 control-label">档案存放处：</label>
                          <div class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
                            <div class="input-group-static">
                              <p class="form-control-static">{{person.fileLocation}}</p>
                             </div>
                            <input type="text" class="form-control" name="fileLocation" value="{{person.fileLocation}}">
                          </div>
                        </div>
                      </div>
                      <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                        <div class="form-group">
                          <label for="" class="col-xs-4 col-sm-4 col-md-4 col-lg-4 control-label">是否外籍：</label>
                          <div class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
                            <div class="input-group-static">
                              <p class="form-control-static">
                                {{if (person.isForeign == 1)}}
                                是
                                {{else if person.isForeign == 0}}
                                否
                                {{/if}}
                              </p>
                             </div>
                            <div class="radio radio-info radio-inline">
                                <input class="form-control" type="radio" name="isForeign" value="1" {{if (person.isForeign == 1)}}checked="true"{{/if}} >
                                <label for="inlineRadio1">是</label>
                            </div>
                            <div class="radio radio-info radio-inline">
                                <input class="form-control" type="radio" name="isForeign" value="0" {{if (person.isForeign == 0)}}checked="true"{{/if}}>
                                <label for="inlineRadio1">否</label>
                            </div>
                          </div>
                        </div>
                      </div>
                      <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                        <div class="form-group">
                          <label for="" class="col-xs-4 col-sm-4 col-md-4 col-lg-4 control-label">是否海外留学：</label>
                          <div class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
                             <div class="input-group-static">
                              <p class="form-control-static">
                              {{if person.studyAbroad == 0}}
                              否
                              {{else if person.studyAbroad == 1}}
                              是
                              {{/if}}
                              </p>
                             </div>
                            <div class="radio radio-info radio-inline">
                                <input class="form-control" type="radio" name="studyAbroad" value="1" {{if (person.studyAbroad == 1)}}checked="true"{{/if}} >
                                <label for="inlineRadio1">是</label>
                            </div>
                            <div class="radio radio-info radio-inline">
                                <input class="form-control" type="radio" name="studyAbroad" value="0" {{if (person.studyAbroad == 0)}}checked="true"{{/if}} >
                                <label for="inlineRadio1">否</label>
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                    <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
                      <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                        <div class="form-group">
                          <label for="exampleInputName3" class="col-xs-4 col-sm-4 col-md-4 col-lg-4 control-label">上传电子简历：</label>
                          <div class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
                            <div class="input-group-static">
                              <p class="form-control-static">
                                {{if (jianli != null && jianli.length > 0)}}
                                  <a href="{{jianli}}" data-gallery="thumb-60" title="电子简历">
                                    <img src="{{jianli}}" width="60px" alt="">
                                  </a>
                                {{/if}}
                              </p>
                             </div>

                            {{if (jianli != null && jianli.length > 0)}}
                              <a href="{{jianli}}" data-gallery="thumb-34" class="pull-left m-r-xs">
                                <img src="{{jianli}}" width="34px" alt="">
                              </a>
                            {{/if}}
                            <div class="input-group" data-toggle="upload:file">
                              <input type="text" class="form-control" readonly="readonly">
                              <div class="input-group-btn">
                                  <span class="btn btn-default">浏览...
                                  <input type="file" class="sr-only" name='resumeFile' accept=".png,.gif,.jpeg,.jpg" id="lefile2"></span>
                              </div>
                           </div>

                          </div>
                        </div>
                      </div>
                      <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                        <div class="form-group">
                          <label for="exampleInputName3" class="col-xs-4 col-sm-4 col-md-4 col-lg-4 control-label">离职证明：</label>
                          <div class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
                            <div class="input-group-static">
                              <p class="form-control-static">
                                {{if (lizhi != null && lizhi.length > 0)}}
                                <a href="{{lizhi}}" data-gallery="thumb-60" title="离职证明">
                                  <img src="{{lizhi}}" width="60px" alt="">
                                </a>
                                {{/if}}
                              </p>
                             </div>

                            {{if (lizhi != null && lizhi.length > 0)}}
                              <a href="{{lizhi}}" data-gallery="thumb-34" class="pull-left m-r-xs">
                                <img src="{{lizhi}}" width="34px" alt="">
                              </a>
                            {{/if}}
                           <div class="input-group" data-toggle="upload:file">
                              <input type="text" class="form-control" readonly="readonly">
                              <div class="input-group-btn">
                                  <span class="btn btn-default">浏览...
                                  <input type="file" class="sr-only" name='jobpostFile' accept=".png,.gif,.jpeg,.jpg" id="lefile4"></span>
                              </div>
                           </div>

                          </div>
                        </div>
                      </div>

                      <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                        <div class="form-group">
                          <label for="exampleInputName3" class="col-xs-4 col-sm-4 col-md-4 col-lg-4 control-label">健康证明：</label>
                          <div class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
                            <div class="input-group-static">
                              <p class="form-control-static">
                                {{if (jiankang !=null && jiankang.length > 0)}}
                                  <a href="{{jiankang}}" data-gallery="thumb-60" title="健康证明">
                                    <img src="{{jiankang}}" width="60px" alt="">
                                  </a>
                                {{/if}}
                              </p>
                             </div>


                              {{if (jiankang !=null && jiankang.length > 0)}}
                                <a href="{{jiankang}}" data-gallery="thumb-34" class="pull-left m-r-xs">
                                  <img src="{{jiankang}}" width="34px" alt="">
                                </a>
                              {{/if}}
                              <div class="input-group" data-toggle="upload:file">
                                <input type="text" class="form-control" readonly="readonly">
                                <div class="input-group-btn">
                                    <span class="btn btn-default">浏览...
                                    <input type="file" class="sr-only" name='healthFile' accept=".png,.gif,.jpeg,.jpg" id="lefile5"></span>
                                </div>
                              </div>

                          </div>
                        </div>
                      </div>
                      <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                        <div class="form-group">
                          <label for="exampleInputName3" class="col-xs-4 col-sm-4 col-md-4 col-lg-4 control-label">学历证明：</label>
                          <div class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
                              <div class="input-group-static">
                              <p class="form-control-static">
                                {{if (xueli != null && xueli.length > 0)}}
                                <a href="{{xueli}}" data-gallery="thumb-60" title="学历证明">
                                  <img src="{{xueli}}" width="60px" alt="">
                                </a>
                                {{/if}}
                              </p>
                             </div>


                          {{if (xueli != null && xueli.length > 0)}}
                            <a href="{{xueli}}" data-gallery="thumb-34" class="pull-left m-r-xs">
                              <img src="{{xueli}}" width="34px" alt="">
                            </a>
                          {{/if}}
                           <div class="input-group" data-toggle="upload:file">
                              <input type="text" class="form-control" readonly="readonly">
                              <div class="input-group-btn">
                                  <span class="btn btn-default">浏览...
                                  <input type="file" class="sr-only" name='educationFile' accept=".png,.gif,.jpeg,.jpg" id="lefile1"></span>
                              </div>
                           </div>

                          </div>
                        </div>
                      </div>
                      <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                        <div class="form-group">
                          <label for="exampleInputName3" class="col-xs-4 col-sm-4 col-md-4 col-lg-4 control-label">薪资证明：</label>
                          <div class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
                            <div class="input-group-static">
                              <p class="form-control-static">
                                {{if (xinzi != null && xinzi.length > 0)}}
                                  <a href="{{xinzi}}" data-gallery="thumb-60" title="薪资证明">
                                    <img src="{{xinzi}}" width="60px" alt="">
                                  </a>
                                {{/if}}
                              </p>
                             </div>

                            {{if (xinzi != null && xinzi.length > 0)}}
                              <a href="{{xinzi}}" data-gallery="thumb-34" class="pull-left m-r-xs">
                                <img src="{{xinzi}}" width="34px" alt="">
                              </a>
                            {{/if}}
                            <div class="input-group" data-toggle="upload:file">
                              <input type="text" class="form-control" readonly="readonly">
                              <div class="input-group-btn">
                                  <span class="btn btn-default">浏览...
                                  <input type="file" class="sr-only" name='salaryFile' accept=".png,.gif,.jpeg,.jpg" id="lefile6"></span>
                              </div>
                           </div>

                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                  {{if customFieldList}}
                  <div class="row">
                  {{each customFieldList as $field}}
                    <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6{{if $field.isUsing!==1}} sr-only{{/if}}">
                      <div class="form-group">
                        <label for="" class="col-xs-4 col-sm-4 col-md-4 col-lg-4 control-label">{{$field.displayName}}：</label>
                        <div class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
                            <div class="input-group-static">
                              <p class="form-control-static">{{$field.displayValue}}&nbsp;</p>
                            </div>
                            {{if $field.isNecessary==1}}
                            <div class="form-required">
                            {{/if}}

                          {{if $field.dataType == 'text'}}
                            <input type="text" class="form-control" 
                            name="{{$field.columnName}}" 
                            value="{{$field.value}}" 
                            {{if $field.isNecessary==1}}
                            data-fv-notempty="true" 
                            data-fv-notempty-message="请填写必填项目" 
                            {{/if}}
                            data-custom-field="1" 
                            data-tabledataid="{{$field.tableDataId}}" 
                            data-keyvalue="{{person.personId}}" 
                            data-tablefielddefid="{{$field.tableFieldDefId}}">
                          {{/if}}


                          {{if $field.dataType == 'select'}}
                          <select class="form-control" 
                          name="{{$field.columnName}}" 
                          {{if $field.isNecessary==1}}
                          data-fv-notempty="true" 
                          data-fv-notempty-message="请填写必填项目" 
                          {{/if}}
                          data-custom-field="1" 
                          data-tabledataid="{{$field.tableDataId}}" 
                          data-keyvalue="{{person.personId}}" 
                          data-tablefielddefid="{{$field.tableFieldDefId}}">
                          {{if $field.refId}}
                            {{each $field.baseDataList as $item}}
                            <option{{if $item.baseDataCode == $field.value}} selected="selected"{{/if}} value="{{$item.baseDataCode}}">{{$item.baseDataName}}</option>
                            {{/each}}
                          {{/if}}
                          </select>
                          {{/if}}

                          {{if $field.dataType == 'datepicker'}}
                          <div class="input-group date">
                            <input type="text" class="form-control" 
                            name="{{$field.columnName}}" 
                            value="{{$field.value}}" 
                            {{if $field.isNecessary==1}}
                            data-fv-notempty="true" 
                            data-fv-notempty-message="请填写必填项目" 
                            {{/if}}
                            data-custom-field="1" 
                            data-tabledataid="{{$field.tableDataId}}" 
                            data-keyvalue="{{person.personId}}" 
                            data-tablefielddefid="{{$field.tableFieldDefId}}">
                            <span class="input-group-addon" style=""><i class="glyphicon glyphicon-th"></i></span>
                          </div>
                          {{/if}}


                          {{if $field.isNecessary==1}}
                          </div>
                          {{/if}}

                        </div>
                      </div>
                    </div>
                    {{/each}}
                  </div>
                  {{/if}}

                  <div class="col-lg-12 text-right">
                    <button type="submit" class="btn btn-success">保存&下一步</button>
                  </div>
                
                </div>

              </form>
            </script>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  <!--个人信息 end--> 
  <!--结束 -->
  
  <!-- Footer-->
  <!-- 放页脚  开始-->
  <jsp:include flush="true" page="../common/foot.div.jsp"></jsp:include>
  <!-- 放页脚  结束-->
</div>
<div id="blueimp-gallery" class="blueimp-gallery">
    <div class="slides"></div>
    <h3 class="title"></h3>
    <a class="prev">‹</a>
    <a class="next">›</a>
    <a class="close">×</a>
    <a class="play-pause"></a>
    <ol class="indicator"></ol>
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
<script src="static/bootstrap/vendor/blueimp-gallery/js/jquery.blueimp-gallery.min.js"></script>
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
<script src="static/js/plugins/form.validation/js/formValidationExtend.js"></script> 

<script src="static/js/yuangong.js"></script>
<script>
$(function(){

  // validation rule
  var validators = {
      err: {
          container: 'tooltip'
      },
      icon: {
          valid: 'glyphicon glyphicon-ok',
          invalid: 'glyphicon glyphicon-remove',
          validating: 'glyphicon glyphicon-refresh'
      },
      locale: 'zh-CN',
      fields: {
          familyAddress: {
              validators: {
                  notEmpty: {
                      message: '请填写必填项目'
                  }
              }
          },
          sex: {
              validators: {
                  notEmpty: {
                      message: '请填写必填项目'
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
          certType: {
              validators: {
                  notEmpty: {
                      message: '请填写必填项目'
                  }
              }
          },
          certId: {
              validators: {
                validators: {
                    notEmpty: {
                        message: '请填写必填项目'
                    }
                },
                callback: {
                  enabled: false,
                  message: '请输入有效的身份证号码',
                  callback: function(value, validator, $field){
                    return IDValidator.isValid(value)
                  }
                }
              }
          },
          employeeName: {
              validators: {
                  notEmpty: {
                      message: '请填写必填项目'
                  }
              }
          },
          registerType: {
              validators: {
                  notEmpty: {
                      message: '请填写必填项目'
                  }
              }
          },
          nationality: {
              validators: {
                  notEmpty: {
                      message: '请填写必填项目'
                  }
              }
          }
      }
  };

  function showPersonInfo(){
    $.ajax({
      cache: true,
      type: "POST",
      url:"employee/personalInfo.do",
      data:{"personId":"${personId}"},
      dataType: 'json',
      async: false,
      success: function(data) {
        var oFieldsTypeList = {};
        if(data.customFieldList){
          $.each(data.commonDataList, function(index, fieldType) {
            oFieldsTypeList[fieldType.commonDataCode] = fieldType;
          });
          data.commonDataList = oFieldsTypeList;
          // var _fieldType = ['select', 'text', 'datepicker'];
          data.customFieldList = $.map(data.customFieldList, function(item, index) {
            item.dataType = oFieldsTypeList[item.dataType].commonDataName;
            // item.dataType = _fieldType[index];
            if(item.customMeta){
              item.keyValue = item.customMeta.keyValue;
              item.displayValue = item.value = item.customMeta.value;
              if(item.dataType==='select' && item.baseDataList){
                $.each(item.baseDataList, function(index, baseData) {
                  if(baseData.baseDataCode==item.value){
                    item.displayValue = baseData.baseDataName;
                    return false;
                  }
                });
              }
              item.tableDataId = item.customMeta.tableDataId;
            }else{
              item.keyValue = null;
              item.value = null;
              item.tableDataId = null;
            }
            return item;
          });
          
        }
        $("#personal_div").html(template('personal', data));
        uploadCompent();
        _initForm();


      }
    });
  }

  var uploadCompent = function(){
    $('[data-toggle="upload:file"] :file').on('change.file:selected', function(event){
        var oEventTarget = $(this),
          oFile = $(this).val()
          fileExt = oFile.substr(oFile.lastIndexOf(".")).toLowerCase()

        oEventTarget.parents('[data-toggle="upload:file"]')
        .find(':text').val( oFile )
    })

    $('[data-toggle="upload:file"]').on('click', function(event){
        $(this).find(':file').trigger('click.file:selected')
    });
  };

  function updatePerson(){
    var object=$('#personalForm').serializeObject();
    //上传图片
    $.ajax({
      url: "employee/exportPerson.do",
      type: 'POST',
      cache: false,
      dataType: 'json',
      data: new FormData($('#personalForm')[0]),
      processData: false,
      contentType: false
    })
    .done(function(data){
      var $getCustomField = $('[data-custom-field="1" ]');
      var customDataJson = $.map($getCustomField, function(item, index){
        var _this = $(item);
        var itemData = {
          value: _this.val(),
          keyValue: _this.attr('data-keyvalue'),
          tableFieldDefId: _this.attr('data-tablefielddefid')
        };

        delete object[_this.attr('name')];
        
        if(_this.attr('data-tabledataid')){
          itemData['tableDataId'] = _this.attr('data-tabledataid');
        }
        return itemData;
      });
    var json = {'personData': JSON.stringify(object),'customData': JSON.stringify(customDataJson)};
     $.ajax({
       type: "post",
       url: "employee/updatePerson",
       data: json,
       dataType: "json",
       async: false,
       success:function(data){
    				if(data.success == true){
    					swal({
                title:data.msg,
                type: "success"
                }, 
                function(){
    					  showPersonInfo();
    					  fileInputEvent();
                location.href='employee/toPageByLinkType?linkType=essential&personId=${personId}';
              });   
    				}else{
    					swal(data.msg, "", "error"); 
    				}
    			}
    		});   
    });       
  };

  var _initForm = function(){
    var  html = $('#personalForm');
    // form init
    
    var  getDomStaticTarget = html.find('.input-group-static'),
         getDomLabel = getDomStaticTarget.parent().siblings(),
         getDomForm = getDomStaticTarget.parents('form'),
         getDomTarget = getDomStaticTarget.siblings();
    
    getDomStaticTarget.show();
    getDomLabel.show();
    getDomTarget.hide();
    
    html.find('.action-group [data-action-motive]').on('click', function(evt){
        var getAction = $(this).data('actionMotive'),
            getActionId = $(this).attr('data-action-id'),
            $form = $('[data-form='+getActionId+']'),
            $getDomStaticTarget = $form.find('.input-group-static'),
            $getDomTarget = $getDomStaticTarget.siblings().not('small,i'),
            $getDomLabel = $form.find('label');

        switch(getAction){
            case 'edit':
                $form.show();
                $getDomStaticTarget.hide();
                $getDomTarget.show();
                $getDomStaticTarget.hide();
                $getDomLabel.show();
                break;
        }
    });
    // do validation
    html.formValidation(validators)
    .on('success.form.fv', function(e){
      e.preventDefault();
      updatePerson();
    })
    .on('change','[name="certType"]',function(event){
      if($(event.target).val() == 808){
        $(event.target).parents('form')
        .formValidation('enableFieldValidators','certId',true,'callback')
        .formValidation('revalidateField', 'certId');
      }else{
        $(event.target).parents('form')
        .formValidation('enableFieldValidators','certId',false,'callback')
        .formValidation('revalidateField', 'certId');
      }
    });

      $('.input-group.date').datepicker({
        format: "yyyy-mm-dd",
        autoclose: true
      })
      .on('changeDate', function(e){
        var getEleName = $(e.target).find(':text').attr('name');
        html.formValidation('revalidateField', getEleName);
      });

  }; // _initForm()

  function fileInputEvent(){
  	 $(":file").change(function() {
  	  		$(this).siblings(".input-group").find("input").val($(this).val());
  	  	  });
  };

  showPersonInfo();
  fileInputEvent();

  $('#showhide')
  .on('hide.bs.collapse', function(e){
    var getTargetId = $(e.target).attr('id');

    $('[aria-controls='+ getTargetId +'] small')
    .html('展开选填信息 <i class="fa fa-chevron-down"></i>')
    $('.page-header').addClass('no-borders');
  })
  .on('show.bs.collapse', function (e){
    var getTargetId = $(e.target).attr('id');
    
    $('[aria-controls='+ getTargetId +'] small')
    .html('收起选填信息 <i class="fa fa-chevron-up"></i>')
    $('.page-header').removeClass('no-borders');
  });




      
}); //(function(){})()
</script>
</body>
</html>
