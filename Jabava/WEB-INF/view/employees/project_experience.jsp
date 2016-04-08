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
                
            <!--项目经验-->
            <div class="content animate-panel">
             <div class="row ">
              <div class="hpanel hblue">
              <!--引入员工信息导航 开始--> 
              <jsp:include flush="true" page="employee_nav.jsp">
                 <jsp:param value="project" name="type"/>
              </jsp:include>
              <!--引入员工信息导航 结束-->
                <div class="panel-heading hbuilt">
                  <div class="panel-tools">                    
                    <button type="button" class="btn btn-success btn-xs xm_jy" data-form-action="add"><i class="fa fa-plus"></i><span class="bold">继续添加</span></button>
                    <button type="button" class="btn btn-success btn-xs xm_jy_update project_none"><i class="fa fa-plus"></i><span class="bold">继续添加</span></button>
                    <button type="button" class="btn btn-success btn-xs hdzs"><span class="bold">获得证书&gt;&gt;</span></button>
                  </div>
                  <h4>项目经验</h4>
                 </div>
            <div class="panel-body">
              <div class="row">
                <div class="div_project">
                </div>
                <div class="hpanel" id="project_div">
                </div>
                <div class="" id="xiangmu_body" data-form-target="content">
                  <form role="form" method="post" name="project_form" class="searchs-form form-horizontal col-md-12 col-lg-12 xiangmu_1 xiangmu_1_form hidden" id="create_new" data-form-type="base">
                    <div class="col-md-4 col-lg-4">
                      <div class="form-group">
                        <label for="" class="control-label col-md-6 col-lg-6">项目起始时间：</label>
                        <div class="col-md-6 col-lg-6">
                          <div class="input-group date">
                            <input type="text" required="required" class="form-control" name="projectStartDate" value="">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                          </div>
                        </div>
                      </div>
                    </div>
                    <div class="col-md-4 col-lg-4">
                      <div class="form-group">
                        <label for="" class="control-label col-md-6 col-lg-6">项目终止时间：</label>
                        <div class="col-md-6 col-lg-6">
                          <div class="input-group date">
                            <input type="text" required="required" class="form-control" name="projectEndDate" value="">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                          </div>
                        </div>
                      </div>
                    </div>
                    <div class="col-md-4 col-lg-4">
                      <div class="form-group">
                        <label for="" class="control-label col-md-6 col-lg-6">项目名称：</label>
                        <div class="col-md-6 col-lg-6">
                          <input type="text" required="required" class="form-control" name="projectName" value="">
                        </div>
                      </div>
                    </div>
                    <div class="col-md-4 col-lg-4">
                      <div class="form-group">
                        <label for="" class="control-label col-md-6 col-lg-6">项目金额：</label>
                        <div class="col-md-6 col-lg-6">
                          <input type="text" class="form-control" name="projectCost" value="">
                        </div>
                      </div>
                    </div>
                    <div class="col-md-12 col-lg-12">
                      <div class="form-group">
                        <label for="" class="control-label col-md-2 col-lg-2">项目职责：</label>
                        <div class="col-md-10 col-lg-10">
                          <textarea class="form-control" required="required" name="projectDuty"></textarea>
                        </div>
                      </div>
                    </div>
                    <div class="col-md-12 col-lg-12">
                      <div class="form-group">
                        <label for="" class="control-label col-md-2 col-lg-2">职责描述：</label>
                        <div class="col-md-10 col-lg-10">
                          <textarea class="form-control" name="memo"></textarea>
                        </div>
                      </div>
                    </div>
                    <div class="col-lg-12 col-md-12 text-center">
                      <button type="button" class="btn btn-info"  >保存</button>
                    </div>
                  </form>
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
<form role="form" method="post" name="project_form" class="searchs-form form-horizontal col-md-12 col-lg-12  {{if $index>0}} jianju borders{{/if}}">
  <div class="text-right action-group">
    <a href="javascript://" class="pe-7s-note pe-2x" data-action-motive="edit" ><span class="sr-only" data-action-id="{{$value.projectId}}">修改</span></a>
    <a href="javascript://" class="pe-7s-trash pe-2x" data-action-motive="del" data-action-id="{{$value.projectId}}"><span class="sr-only">删除</span></a>
  </div>
  <input type="hidden" name="projectId" value="{{$value.projectId}}">
  <div class="col-md-4 col-lg-4">
    <div class="form-group">
      <label for="" class="control-label col-md-6 col-lg-6">项目起始时间：</label>
      <div class="col-md-6 col-lg-6">
        <div class="input-group-static">
          <p class="form-control-static">{{$value.projectStartDate | dateFormat}}</p>
        </div>
        <div class="input-group date">
          <input type="text" required="required" class="form-control" name="projectStartDate" value="{{$value.projectStartDate | dateFormat}}">
          <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
        </div>
      </div>
    </div>
  </div>
  <div class="col-md-4 col-lg-4">
    <div class="form-group">
      <label for="" class="control-label col-md-6 col-lg-6">项目终止时间：</label>
      <div class="col-md-6 col-lg-6">
        <div class="input-group-static">
          <p class="form-control-static">{{$value.projectEndDate | dateFormat}}</p>
        </div>
        <div class="input-group date">
          <input type="text" required="required" class="form-control" name="projectEndDate" value="{{$value.projectEndDate | dateFormat}}">
          <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
        </div>
      </div>
    </div>
  </div>
  <div class="col-md-4 col-lg-4">
    <div class="form-group">
      <label for="" class="control-label col-md-6 col-lg-6">项目名称：</label>
      <div class="col-md-6 col-lg-6">
        <div class="input-group-static">
          <p class="form-control-static">{{$value.projectName}}</p>
        </div>
        <input type="text" required="required" class="form-control" name="projectName" value="{{$value.projectName}}">
      </div>
    </div>
  </div>
  <div class="col-md-4 col-lg-4">
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
  <div class="col-md-12 col-lg-12">
    <div class="form-group">
      <label for="" class="control-label col-md-2 col-lg-2">项目职责：</label>
      <div class="col-md-10 col-lg-10">
        <div class="input-group-static">
          <p class="form-control-static">{{$value.projectDuty}}</p>
        </div>
        <textarea class="form-control" required="required" name="projectDuty">{{$value.projectDuty}}</textarea>
      </div>
    </div>
  </div>
  <div class="col-md-12 col-lg-12">
    <div class="form-group">
      <label for="" class="control-label col-md-2 col-lg-2">职责描述：</label>
      <div class="col-md-10 col-lg-10">
        <div class="input-group-static">
          <p class="form-control-static">{{$value.memo}}</p>
        </div>
        <textarea class="form-control" name="memo">{{$value.memo}}</textarea>
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

<!-- App scripts -->
<script src="static/bootstrap/scripts/homer.js"></script>
<script src="static/js/template.js"></script>
<script src="static/js/yuangong.js"></script>
<script src="static/js/common.js"></script>
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
 
  
</script>
 
<!--日历-->
<script>

        $(function(){
        	
        	 templateFillData(templateID, containerID, listURL,delsURL);

        });

    </script>
</body>
</html>