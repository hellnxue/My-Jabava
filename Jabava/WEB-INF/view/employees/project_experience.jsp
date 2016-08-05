<%@ page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html>
<head>
	<base href="${pageContext.request.contextPath}/">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <!-- Page title -->
    <title>项目经验</title>
    <jsp:include flush="true" page="../common/styles.jsp"></jsp:include>
    <!-- 表单验证 -->
    <link rel="stylesheet" href="static/js/plugins/form.validation/css/formValidation.css">
    <!-- 临时 -->
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
<!-- 放主要内容 -->
                
            <!--项目经验-->
            <div class="content animate-panel">
             <div class="row">
              <div class="hpanel">
                  <div class="panel-heading">
                    <h4 class="text-center font-bold">
                        <button onclick="toEmployeeList()" class="btn btn-default btn-sm btn-absolute" type="button">　返回　</button>
                        <span>员工资料</span>
                    </h4>
              </div>
              <!--引入员工信息导航 开始--> 
              <jsp:include flush="true" page="employee_nav.jsp">
                 <jsp:param value="project" name="type"/>
              </jsp:include>
              <!--引入员工信息导航 结束-->
                
            <div class="panel-body">
              <div class="panel-heading">
                  <h4 class="text-center font-bold">项目经验</h4>
             </div>
              <div class="row">
                <div class="div_project">
                </div>
                <div class="hpanel" id="project_div">
                </div>
                <div class="" id="xiangmu_body" data-form-target="content">
                  <form role="form" method="post" name="project_form" class="searchs-form form-horizontal col-md-12 col-lg-12 xiangmu_1 xiangmu_1_form hidden" id="create_new" data-form-type="base">
                    
                    <!-- 起始、终止时间 -->
                    <div class="row">
                      <div class="col-md-6">
                        <div class="col-md-12">
                          <div class="form-group">
                          <label for="" class="control-label col-md-6 col-lg-6">起始时间：</label>
                          <div class="col-md-6 col-lg-6 form-required">
                            <div class="input-group date">
                              <input type="text" required="required" class="form-control" name="projectStartDate" value="">
                              <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                            </div>
                          </div>
                        </div>
                        </div>
                        <div class="col-md-12">
                          <div class="form-group">
                          <label for="" class="control-label col-md-6 col-lg-6">终止时间：</label>
                          <div class="col-md-6 col-lg-6 form-required">
                            <div class="input-group date">
                              <input type="text" required="required" class="form-control" name="projectEndDate" value="">
                              <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                            </div>
                          </div>
                        </div>
                        </div>
                      </div>
                      <div class="col-md-6 col-lg-6">
                        <div class="form-group">
                          <label for="" class="control-label col-md-2 col-lg-3">职责描述：</label>
                          <div class="col-md-10 col-lg-9 ">
                            <textarea class="form-control" name="memo" rows="3"></textarea>
                          </div>
                        </div>
                      </div>
                    </div>
                    <!-- 项目名称 项目金额 -->
                    <div class="row">
                      <div class="col-md-6">
                        <div class="col-md-12">
                          <div class="form-group">
                          <label for="" class="control-label col-md-6 col-lg-6">项目名称：</label>
                          <div class="col-md-6 col-lg-6 form-required">
                            <input type="text" required="required" class="form-control" name="projectName" value="">
                          </div>
                        </div>
                        </div>
                        <div class="col-md-12">
                           <div class="form-group">
                          <label for="" class="control-label col-md-6 col-lg-6">项目金额：</label>
                          <div class="col-md-6 col-lg-6">
                            <input type="text" class="form-control" name="projectCost" value="" required="required">
                          </div>
                        </div>
                        </div>
                      </div>
                      <div class="col-md-6">
                        <div class="form-group">
                          <label for="" class="control-label col-md-2 col-lg-3">项目职责：</label>
                          <div class="col-md-10 col-lg-9 form-required">
                            <textarea class="form-control" required="required" name="projectDuty" rows="3"></textarea>
                          </div>
                        </div>
                      </div>
                    </div>
                                     
                    <div class="col-lg-12 col-md-12 text-right">
                      <button type="button" class="btn btn-success">保存</button>
                      <button type="button" class="btn btn-default">取消</button>
                    </div>
                  </form>
                </div>
                <div class="text-center">
                  <a class="adds" data-form-action="add" href="javascript://"><i class=" pe-7s-plus pe-5x text-muted"></i></a><br>
                    <a class=" text-muted">添加新记录</a>
                </div>
              </div>
              </div>
            </div>
          </div>
          </div>

         
     			 <!--项目经验结束-->
<!-- 渲染模板 -->     			 
<script type="text/html" id="project">
{{each list}}

  <div class="col-md-12 page-header m-t-md{{if $index == list.length-1}} no-borders{{/if}}">
    <div class="panel-tools action-group">
      <ul class="list-inline">
        <li>
          <a href="javascript://" data-action-motive="hideOrshow" data-action-id="{{$value.projectId}}"><i class="pe-7s-angle-down-circle pe-2x text-info"></i><span class="sr-only">隐藏</span></a>
        </li>
        <li>
        <a href="javascript://" data-action-motive="edit" data-action-id="{{$value.projectId}}"><i class="pe-7s-note pe-2x text-info"></i><span class="sr-only" >修改</span></a>
        </li>
        <li>
          <a href="javascript://" data-action-motive="del" data-action-id="{{$value.projectId}}"><i class="pe-7s-trash pe-2x text-info"></i><span class="sr-only">删除</span></a>
        </li>
      </ul>
    </div>
    <ul class="list-inline">
      <li><p class="form-control-static text-info font-bold">{{$value.projectStartDate | dateFormat}}</p></li>
      <li class="m-r"><p class="form-control-static text-info font-bold">至</p></li>
      <li><p class="form-control-static text-info font-bold">{{$value.projectEndDate | dateFormat}}</p></li>
      <li><p class="form-control-static text-info font-bold">{{$value.projectName}}</p></li>       
     </ul>
  </div>

<form data-form="{{$value.projectId}}" role="form" method="post" name="project_form" class="searchs-form form-horizontal col-md-12 col-lg-12 m-b-lg {{if $index>0}} m-t-xl{{/if}}">
  <input type="hidden" name="projectId" value="{{$value.projectId}}">
      <div class="row">
     <div class="col-md-6">
          <div class="col-md-12">
                <div class="form-group">
                <label for="" class="control-label col-md-6 col-lg-6">起始时间：</label>
                <div class="col-md-6 col-lg-6">
                  <div class="input-group-static">
                    <p class="form-control-static">{{$value.projectStartDate | dateFormat}}</p>
                  </div>
                  <div class="form-required">
                    <div class="input-group date">
                      <input type="text" required="required" class="form-control" name="projectStartDate" value="{{$value.projectStartDate | dateFormat}}">
                      <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                    </div>
                  </div>
                </div>
              </div>
      </div>
      <div class="col-md-12">
              <div class="form-group">
              <label for="" class="control-label col-md-6 col-lg-6">终止时间：</label>
              <div class="col-md-6 col-lg-6">
                <div class="input-group-static">
                  <p class="form-control-static">{{$value.projectEndDate | dateFormat}}</p>
                </div>
                <div class="form-required">
                  <div class="input-group date">
                    <input type="text" required="required" class="form-control" name="projectEndDate" value="{{$value.projectEndDate | dateFormat}}">
                    <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                  </div>
                </div>
              </div>
            </div>   
          </div>
    </div>
    <div class="col-md-6">
           <div class="form-group">
            <label for="" class="control-label col-md-3 col-lg-3">项目描述：</label>
            <div class="col-md-9 col-lg-9">
              <div class="input-group-static">
                <p class="form-control-static">{{$value.memo}}</p>
              </div>
              <textarea class="form-control" name="memo" rows="3">{{$value.memo}}</textarea>
            </div>
          </div>
    </div>
    </div>
    <div class="row">
     <div class="col-md-6">
        <div class="col-md-12">
             <div class="form-group">
              <label for="" class="control-label col-md-6 col-lg-6">项目名称：</label>
              <div class="col-md-6 col-lg-6">
                <div class="input-group-static">
                  <p class="form-control-static">{{$value.projectName}}</p>
                </div>
                <div class="form-required">
                  <input type="text" required="required" class="form-control" name="projectName" value="{{$value.projectName}}">
                </div>
              </div>
            </div>
          </div>
      <div class="col-md-12">
              <div class="form-group">
                <label for="" class="control-label col-md-6 col-lg-6">项目金额：</label>
                <div class="col-md-6 col-lg-6">
                  <div class="input-group-static">
                    <p class="form-control-static">{{$value.projectCost}}</p>
                  </div>
                  <input type="text" class="form-control" name="projectCost" value="{{$value.projectCost}}">
                </div>
              </div>
          </div>
    </div>
    <div class="col-md-6">
            <div class="form-group">
            <label for="" class="control-label col-md-3 col-lg-3">项目职责：</label>
            <div class="col-md-9 col-lg-9">
              <div class="input-group-static">
                <p class="form-control-static">{{$value.projectDuty}}</p>
              </div>
              <div class="form-required">
                <textarea class="form-control" required="required" name="projectDuty" rows="3">{{$value.projectDuty}}</textarea>
              </div>
            </div>
          </div>
    </div>
  </div>
  <div class="col-lg-12 col-md-12 text-center form-action">
    <button type="button" class="btn btn-info" data-action-motive="save">保存</button>
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
<!-- 表单验证 -->
<script src="static/js/plugins/form.validation/js/formValidation.js"></script>
<script src="static/js/plugins/form.validation/js/framework/bootstrap.js"></script>
<script src="static/js/plugins/form.validation/js/language/zh_CN.js"></script> 
<!-- App scripts -->
<script src="static/bootstrap/scripts/homer.js"></script>
<script src="static/js/template.js"></script>
<script src="static/js/yuangong.js"></script>
<script src="static/js/commonH3.js"></script>

<script type="text/javascript">
var personId ="${personId}";// $("#personId").val() 待改
var listURL="employee/projectInfo?personId="+ personId;
var delsURL="employee/delProject?projectId=";
var updateURL="employee/updateProject";
var addFormSelector="[name=add_new_form]";
var initFormSelector="#create_new";
var addURL="employee/addProject";
var templateID="project";
var containerID= $('#project_div');


 //templateFillData('project', $('#project_div'), 'static/json/project.json','employee/delJobpost.do?postId=');
 
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
          projectStartDate: {
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
          projectEndDate: {
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
          projectName: {
              validators: {
                  notEmpty: {
                      message: '请填写必填项目'
                  }
              }
          }
      }
  };
//日历
    $(function(){
    	
    	 templateFillData(templateID, containerID, listURL,delsURL);

    });

</script>
</body>
</html>