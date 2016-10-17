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
            <jsp:include flush="true" page="audit_employee_nav.jsp">
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
               
                <div class="col-lg-12 text-center action-group">
				  <button type="submit" class="btn btn-info " data-btn="save">保存</button>
                  <button type="button" class="btn btn-default form-action" data-action-motive="cancel" data-btn="cancel">取消</button>
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
      var customDataJson = [];
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
              });   
    				}else{
    					swal(data.msg, "", "error"); 
    				}
    			}
    		});   
           
  };

  var _initForm = function(){
    var  html = $('#personalForm');
    // form init
    
    var  getDomStaticTarget = html.find('.input-group-static'),
         getDomLabel = getDomStaticTarget.parent().siblings(),
         getDomTarget = getDomStaticTarget.siblings(),
         getDomBtn=html.find('.action-group [data-btn]');
    
	    getDomStaticTarget.show();
	    getDomLabel.show();
	    getDomTarget.hide();
	    getDomBtn.hide();
	    
	    html.find('.action-group [data-action-motive]').on('click', function(evt){
	        var getAction = $(this).data('actionMotive'),
	            $form = $(this).parents('form'),
	            $getDomStaticTarget = $form.find('.input-group-static'),
	            $getDomTarget = $getDomStaticTarget.siblings().not('small,i'),
	            $getDomLabel = $form.find('label');
	
	        switch(getAction){
	            case 'edit':
	                $form.show();
	                $getDomStaticTarget.hide();
	                $getDomTarget.show();
	                $getDomLabel.show();
	                getDomBtn.show();
	                break;
	            case 'cancel':
	            	$form[0].reset();
	                $getDomStaticTarget.show();
	                $getDomTarget.hide();
	                getDomBtn.hide();
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
      
      approved("${personId}");

  }; // _initForm()

  function fileInputEvent(){
  	 $(":file").change(function() {
  	  		$(this).siblings(".input-group").find("input").val($(this).val());
  	  	  });
  };

  showPersonInfo();
  fileInputEvent();
      
});  
</script>
</body>
</html>
