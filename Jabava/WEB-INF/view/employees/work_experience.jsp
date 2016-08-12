<%@ page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html>
<head>
	<base href="${pageContext.request.contextPath}/">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- Page title -->
    <title>工作履历</title>
    <jsp:include flush="true" page="../common/styles.jsp"></jsp:include>
    <link rel="stylesheet" href="static/js/plugins/form.validation/css/formValidation.css">
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
                
            <!--工作履历-->
          <div class="content animate-panel">
            <div class="row ">
               <div class="col-lg-12">
              <div class="hpanel">
                <div class="panel-heading m-b-lg">
                  <h4>
                    <a onclick="toEmployeeList()"  type="button" class="btn btn-default btn-sm btn-absolute">返　回</a>
                  </h4>
                </div>
                <!--引入员工信息导航 开始--> 
                <jsp:include flush="true" page="employee_nav.jsp">
                	<jsp:param value="experience" name="type"/>
                </jsp:include>
                <!--引入员工信息导航 结束-->
            <div class="panel-body">
                <div class="panel-heading">
                  <h4 class="text-center font-bold">工作履历</h4>
                </div>
              <div class="row m-b-lg">
                <div class="div_work">
                </div>
                <div class="hpanel" id="work_div">
                </div>
                <div id="panelBody" data-form-target="content">
                  <form role="form" method="post" name="name_div" class="searchs-form form-horizontal col-md-12 col-lg-12 gongzuo_1 operation hidden" id="create_new" data-form-type="base" data-form-validator="validator">
                    <!--起始、终止时间-->
                    <!--起始时间-->
                    <div class="col-md-6 col-lg-6">
                    <div class="form-group" >
                      <label for="" class="control-label col-md-3 col-lg-3">任职时间：</label>
                      <div class="col-md-9 col-lg-9 form-required">
                        <div class="input-group input-daterange" data-toggle="datepicker">
                          <input type="text" class="form-control" name="startDate" value="">
                          <span class="input-group-addon">&mdash;</span>
                          <input type="text" class="form-control" name="endDate" value="">
                        </div>
                      </div>
                    </div>
                    </div>
                    <!--工作单位-->
                    <div class="col-md-6 col-lg-6">
                    <div class="form-group">
                      <label for="" class="control-label col-md-3 col-lg-3">单位名称：</label>
                      <div class="col-md-9 col-lg-9 form-required">
                        <input type="text" class="form-control" name="employer" value="" required="required">
                      </div>
                    </div>
                    </div>
                    <!--担任职务-->
                    <div class="col-md-6 col-lg-6">
                        <div class="form-group">
                          <label for="" class="control-label col-md-3 col-lg-3">担任职务：</label>
                          <div class="col-md-9 col-lg-9 form-required">
                            <input type="text" class="form-control" name="workPost" value="">
                          </div>
                        </div>
                    </div>
                    <!--主要职责-->
                    <div class="col-md-6 col-lg-6">
                        <div class="form-group">
                          <label for="" class="control-label col-md-3 col-lg-3">主要职责：</label>
                          <div class="col-md-9 col-lg-9">
                            <input type="text" class="form-control" name="description" value="">
                          </div>
                        </div>
                    </div>
                     <!--证明人-->  
                    <div class="col-md-6 col-lg-6">
                      <div class="form-group">
                        <label for="" class="control-label col-md-3 col-lg-3">证明人：</label>
                        <div class="col-md-9 col-lg-9">
                          <input type="text" class="form-control" name="authenticator" value="">
                        </div>
                      </div>
                    </div>
                    <!--证明电话-->  
                    <div class="col-md-6 col-lg-6">
                      <div class="form-group">
                        <label for="" class="control-label col-md-3 col-lg-3">证明电话：</label>
                        <div class="col-md-9 col-lg-9">
                          <input type="text" class="form-control" name="proveMobile" value="">
                        </div>
                      </div>
                    </div>
                    <!--背景调查-->  
                    <div class="col-md-6 col-lg-6">
                      <div class="form-group">
                        <label for="" class="control-label col-md-3 col-lg-3">背景调查：</label>
                        <div class="col-md-9 col-lg-9">
                          <input type="text" class="form-control" name="backgroundInvestigation" value="">
                        </div>
                      </div>
                    </div>
                    <!--备注-->
                    <div class="col-md-6 col-lg-6">
                        <div class="form-group">
                          <label for="" class="control-label col-md-3 col-lg-3">备注：</label>
                          <div class="col-md-9 col-lg-9">
                            <input type="text" class="form-control" name="memo" value="">
                          </div>
                        </div>
                    </div>
                    <!--保存 删除-->
                    <div class="col-md-12 col-lg-12 text-right">
                      <button type="submit" class="btn btn-success">保存</button>
                      <button type="button" class="btn btn-default" data-action-motive="remove" >取消</button>
                    </div>
                  </form>                              
                </div>

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
        <!--工作经验结束-->
<!--模板渲染-->                     
<script type="text/html" id="work_exp">
{{each list}}

         <div class="col-md-12 page-header m-t-md{{if $index == list.length-1}} no-borders{{/if}} ">
          <div class="panel-tools action-group">
            <ul class="list-inline">
               <li>
              <a href="javascript://" data-action-motive="hideOrshow" data-action-id="{{$value.experienceId}}"><i class="pe-7s-angle-down-circle pe-2x text-info"></i><span class="sr-only">隐藏</span></a>
              </li>
              <li>
              <a href="javascript://" data-action-motive="edit" data-action-id="{{$value.experienceId}}"><i class="pe-7s-note pe-2x text-info">
              </i><span class="sr-only">修改</span></a>
              </li>
              <li>
                <a href="javascript://" data-action-motive="del" data-action-id="{{$value.experienceId}}"><i class="pe-7s-trash pe-2x text-info"></i><span class="sr-only">删除</span></a>
              </li>
            </ul>
          </div>
          <ul class="list-inline">
            <li><p class="form-control-static text-info font-bold">{{$value.startDate | dateFormat}}</p></li>
            <li><p class="form-control-static text-info font-bold">至</p></li>
            <li class="m-r"><p class="form-control-static text-info font-bold">{{$value.endDate | dateFormat}}&nbsp;</p></li>
            <li><p class="form-control-static text-info font-bold">{{$value.employer}}&nbsp;</p></li>
            <li><p class="form-control-static text-info font-bold">{{$value.workPost}}&nbsp;</p></li>
          </ul>
        </div>
        
        <form data-form="{{$value.experienceId}}" role="form" method="post" name="name_div" class="searchs-form form-horizontal col-md-12 col-lg-12  {{if $index>0}} jianju borders{{/if}}" data-form-validator="validator">
        
         <input type="hidden" name="experienceId" value="{{$value.experienceId}}" />
            <!--起始时间-->
            <div class="col-md-6 col-lg-6">
                <div class="form-group" >
                  <label for="" class="control-label col-md-3 col-lg-3">任职时间：</label>
                  <div class="col-md-9 col-lg-9">
                      <div class="input-group-static">
                      <p class="form-control-static">
                      {{$value.startDate | dateFormat}}&mdash;{{$value.endDate | dateFormat}}&nbsp;</p>
                      </div>
                      <div class="form-required">
                      <div class="input-group input-daterange" data-toggle="datepicker">
                      <input type="text" class="form-control" name="startDate" value="{{$value.startDate | dateFormat}}">
                      <span class="input-group-addon">&mdash;</span>
                      <input type="text" class="form-control" name="endDate" value="{{$value.endDate | dateFormat}}">
                      </div>
                      </div>
                  </div>
                </div>
            </div>
            <!--工作单位-->
            <div class="col-md-6 col-lg-6">
              <div class="form-group">
                  <label for="" class="control-label col-md-3 col-lg-3">工作单位：</label>
                  <div class="col-md-9 col-lg-9">
                      <div class="input-group-static">
                        <p class="form-control-static">{{$value.employer}}&nbsp;</p>
                      </div>
                      <div class="form-required">
                        <input type="text" class="form-control" name="employer" value="{{$value.employer}}">
                      </div>
                  </div>
              </div>
            </div>
            <!--担任职务-->
            <div class="col-md-6 col-lg-6">
                <div class="form-group">
                    <label for="" class="control-label col-md-3 col-lg-3">担任职务：</label>
                    <div class="col-md-9 col-lg-9">
                        <div class="input-group-static">
                            <p class="form-control-static">{{$value.workPost}}&nbsp;</p>
                        </div>
                        <div class="form-required">
                            <input type="text" class="form-control" name="workPost" value="{{$value.workPost}}">
                        </div>
                    </div>
                </div>
            </div>
            <!--主要职责-->
            <div class="col-md-6 col-lg-6">
                <div class="form-group">
                    <label for="" class="control-label col-md-3 col-lg-3">主要职责：</label>
                    <div class="col-md-9 col-lg-9">
                        <div class="input-group-static">
                            <p class="form-control-static">{{$value.description}}&nbsp;</p>
                        </div>
                        <input type="text" class="form-control" name="description" value="{{$value.description}}">
                    </div>
                </div>
            </div>
            <!--证明人-->  
            <div class="col-md-6 col-lg-6">
                <div class="form-group">
                    <label for="" class="control-label col-md-3 col-lg-3">证明人：</label>
                    <div class="col-md-9 col-lg-9">
                        <div class="input-group-static">
                            <p class="form-control-static">{{$value.authenticator}}&nbsp;</p>
                        </div>
                        <input type="text" class="form-control" name="authenticator" value="{{$value.authenticator}}">
                    </div>
                </div>
            </div>
            <!--证明电话-->  
            <div class="col-md-6 col-lg-6">
                <div class="form-group">
                    <label for="" class="control-label col-md-3 col-lg-3">证明电话：</label>
                    <div class="col-md-9 col-lg-9">
                        <div class="input-group-static">
                            <p class="form-control-static">{{$value.proveMobile}}&nbsp;</p>
                        </div>
                        <input type="text" class="form-control" name="proveMobile" value="{{$value.proveMobile}}">
                    </div>
                </div>
            </div>
            <!--背景调查-->  
            <div class="col-md-6 col-lg-6">
                <div class="form-group">
                    <label for="" class="control-label col-md-3 col-lg-3">背景调查：</label>
                    <div class="col-md-9 col-lg-9">
                        <div class="input-group-static">
                            <p class="form-control-static">{{$value.backgroundInvestigation}}&nbsp;</p>
                        </div>
                        <input type="text" class="form-control" name="backgroundInvestigation" value="{{$value.backgroundInvestigation}}">
                    </div>
                </div>
            </div>
            <!--备注-->
            <div class="col-md-6 col-lg-6">
                <div class="form-group">
                    <label for="" class="control-label col-md-3 col-lg-3">备注：</label>
                    <div class="col-md-9 col-lg-9">
                        <div class="input-group-static">
                            <p class="form-control-static">{{$value.memo}}&nbsp;</p>
                        </div>
                        <input type="text" class="form-control" name="memo" value="{{$value.memo}}">
                    </div>
                </div>
            </div>
        <!--保存 删除-->
        <div class="col-lg-12 col-md-12 text-center form-action">
          <button type="submit" class="btn btn-info"  >保存</button>
          <button type="button" class="btn btn-default" data-action-motive="cancel">取消</button>
        </div>
        </form>
     {{/each}}
    </script>
    
                 
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

<script src="static/js/plugins/form.validation/js/formValidation.js"></script>
<script src="static/js/plugins/form.validation/js/framework/bootstrap.js"></script>
<script src="static/js/plugins/form.validation/js/language/zh_CN.js"></script> 
<!-- App scripts -->
<script src="static/bootstrap/scripts/homer.js"></script>
<script src="static/js/work_experience.js"></script>
<script src="static/js/template.js"></script>
<script src="static/js/yuangong.js"></script>
<script src="static/js/commonH3.js"></script>
<script type="text/javascript">
var personId ="${personId}"; 
var listURL="employee/experienceInfo?personId="+ personId;
var delsURL="employee/deleteExperience?experienceId=";
var updateURL="employee/updateExperience";
var addFormSelector="[name=add_new_form]";
var initFormSelector="#create_new";
var addURL="employee/addExperience";
var templateID="work_exp";
var containerID= $('#work_div');
//var templateType="experience";
//var metaKey="";
  
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
        startDate: {
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
        endDate: {
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
        employer: {
            validators: {
                notEmpty: {
                    message: '请填写必填项目'
                }
            }
        },
        workPost: {
            validators: {
                notEmpty: {
                    message: '请填写必填项目'
                }
            }
        },
        proveMobile: {
            validators: {
                phone: {
                  enabled: false,
                  country: 'CN',
                  message: '请输入有效的手机或电话号码'
                }
            }
        }
    }
};
$(function(){

    templateFillData(templateID, containerID, listURL,delsURL);
});
</script>
</body>
</html>