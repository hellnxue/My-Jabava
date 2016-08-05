<%@ page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html>
<head>
	<base href="${pageContext.request.contextPath}/">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <!-- Page title -->
    <title>培训经历</title>
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

    <!-- 放主要内容 -->


    <!--培训经历-->
    <div class="content animate-panel">
      <div class="row ">
        <div class="hpanel">
          <div class="panel-heading">
            <h4 class="text-center font-bold">
              <button class="btn btn-default btn-sm btn-absolute" type="button">　返回　</button>
              <span>员工资料</span>
            </h4>
          </div>
          <!--引入员工信息导航 开始--> 
          <jsp:include flush="true" page="employee_nav.jsp">
          <jsp:param value="training" name="type"/>
        </jsp:include>
        <!--引入员工信息导航 结束-->
<!-- <div class="panel-heading hbuilt">
<div class="panel-tools">
<button type="button" class="btn btn-success btn-xs px_jl" data-form-action="add"><i class="fa fa-plus"></i><span class="bold">继续添加</span></button>
<button type="button" class="btn btn-success btn-xs px_jl_update" style="display:none;"><i class="fa fa-plus"></i><span class="bold">继续添加</span></button>
<button type="button" class="btn btn-success btn-xs jtcy"><span class="bold">家庭成员&gt;&gt;</span></button> 
</div>
<h4>培训经历</h4>
</div> -->


<div class="panel-body">
  <div class="panel-heading">
    <h4 class="text-center font-bold">培训经历</h4>
  </div>

  <script type="text/html" id="training_exp">
    {{each list}}
    <div class="col-md-12 page-header m-t-md{{if $index == list.length-1}} no-borders{{/if}} ">
      <div class="panel-tools action-group">
        <ul class="list-inline">
          <li>
            <a href="javascript://" data-action-motive="hideOrshow" data-action-id="{{$value.trainId}}"><i class="pe-7s-angle-down-circle pe-2x text-info"></i><span class="sr-only">隐藏</span></a>
          </li>
          <li>
            <a href="javascript://" data-action-motive="edit" data-action-id="{{$value.trainId}}"><i class="pe-7s-note pe-2x text-info">
            </i><span class="sr-only">修改</span></a>
          </li>
          <li>
            <a href="javascript://" data-action-motive="del" data-action-id="{{$value.trainId}}"><i class="pe-7s-trash pe-2x text-info"></i><span class="sr-only">删除</span></a>
          </li>
        </ul>
      </div>
      <ul class="list-inline">
        <li><p class="form-control-static text-info font-bold">{{$value.trainStartDate | dateFormat}}</p></li>
        <li><p class="form-control-static text-info font-bold">至</p></li>
        <li class="m-r"><p class="form-control-static text-info font-bold">{{$value.trainEndDate | dateFormat}}</p></li>
        <li><p class="form-control-static text-info font-bold">{{$value.className}}</p></li>
      </ul>
    </div>
    <form data-form="{{$value.trainId}}" role="form" method="post" name="training_form" class="searchs-form form-horizontal col-md-12 col-lg-12  m-b-lg  {{if $index>0}} jianju borders{{/if}}">
<!-- <div class="text-right action-group">
<a href="javascript://" class="pe-7s-note pe-2x" data-action-motive="edit" ><span class="sr-only">修改</span></a>
<a href="javascript://" class="pe-7s-trash pe-2x" data-action-motive="del" data-action-id="{{$value.trainId}}"><span class="sr-only">删除</span></a>
</div> -->
<input type="hidden" name="trainId" value="{{$value.trainId}}">
<!--培训开始时间-->
<div class="col-md-6 col-lg-6">
  <div class="form-group">
    <label for="" class="control-label col-md-4 col-lg-4">开始时间：</label>
    <div class="col-md-8 col-lg-8">
      <div class="input-group-static">
        <p class="form-control-static">{{$value.trainStartDate | dateFormat}}&nbsp;</p>
      </div>
      <div class="form-required">
        <div class="input-group date">
          <input type="text" class="form-control" name="trainStartDate" value="{{$value.trainStartDate | dateFormat}}" required="required">
          <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
        </div>
      </div>
    </div>
  </div>
</div>
<!--培训机构-->
<div class="col-md-6 col-lg-6">
  <div class="form-group">
    <label for="" class="control-label col-md-4 col-lg-4">培训机构：</label>
    <div class="col-md-8 col-lg-8">
      <div class="input-group-static">
        <p class="form-control-static">{{$value.trainOrganization}}&nbsp;</p>
      </div>
      <input type="text" class="form-control" name="trainOrganization" value="{{$value.trainOrganization}}">
    </div>
  </div>
</div>
<!--培训结束时间-->
<div class="col-md-6 col-lg-6">
  <div class="form-group">
    <label for="" class="control-label col-md-4 col-lg-4">结束时间：</label>
    <div class="col-md-8 col-lg-8">
      <div class="input-group-static">
        <p class="form-control-static">{{$value.trainEndDate | dateFormat}}&nbsp;</p>
      </div>
      <div class="form-required">
        <div class="input-group date">
          <input type="text" class="form-control" name="trainEndDate" value="{{$value.trainEndDate | dateFormat}}" required="required">
          <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
        </div>
      </div>
    </div>
  </div>
</div>
<!--培训课时-->
<div class="col-md-6 col-lg-6">
  <div class="form-group">
    <label for="" class="control-label col-md-4 col-lg-4">培训课时：</label>
    <div class="col-md-8 col-lg-8">
      <div class="input-group-static">
        <p class="form-control-static">{{$value.trainHour}}&nbsp;</p>
      </div>
      <input type="text" class="form-control" name="trainHour" value="{{$value.trainHour}}">
    </div>
  </div>
</div>
<!--课程名称-->
<div class="col-md-6 col-lg-6">
  <div class="form-group">
    <label for="" class="control-label col-md-4 col-lg-4">课程名称：</label>
    <div class="col-md-8 col-lg-8">
      <div class="input-group-static">
        <p class="form-control-static">{{$value.className}}&nbsp;</p>
      </div>
      <div class="form-required">
        <input type="text" class="form-control" name="className" value="{{$value.className}}" required="required">
      </div>
    </div>
  </div>
</div>
<div class="col-md-6 col-lg-6">
  <div class="form-group">
    <label for="" class="control-label col-md-4 col-lg-4">服务到期时间：</label>
    <div class="col-md-8 col-lg-8">
      <div class="input-group-static">
        <p class="form-control-static">{{$value.serviceExpireDate | dateFormat}}&nbsp;</p>
      </div>
      <div class="form-required">
        <div class="input-group date">
          <input type="text" class="form-control" name="serviceExpireDate" value="{{$value.serviceExpireDate | dateFormat}}" required="required">
          <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
        </div>
      </div>
    </div>
  </div>
</div>

<!--培训费用-->
<div class="col-md-6 col-lg-6">
  <div class="form-group">
    <label for="" class="control-label col-md-4 col-lg-4">培训费用：</label>
    <div class="col-md-8 col-lg-8">
      <div class="input-group-static">
        <p class="form-control-static">{{$value.trainCost}}&nbsp;</p>
      </div>
      <input type="text" class="form-control" name="trainCost" value="{{$value.trainCost}}">
    </div>
  </div>
</div>


<!--所得证书--> 
<div class="col-md-6 col-lg-6">
  <div class="form-group">
    <label for="" class="control-label col-md-4 col-lg-4">所得证书：</label>
    <div class="col-md-8 col-lg-8">
      <div class="input-group-static">
        <p class="form-control-static">{{$value.isCertificate}}</p>
      </div>
      <input type="text" class="form-control" name="isCertificate" value="">
    </div>
  </div>
</div>
<!--保存 删除-->
<div class="col-lg-12 col-md-12 text-center form-action">
  <button type="button" class="btn btn-info" data-action-motive="save">保存</button>
  <button type="button" class="btn btn-default" data-action-motive="cancel">取消</button>
</div>
</form>
{{/each}}
</script>
<div class="hpanel" id="training_div"></div>
<div class="" id="peixun_body" data-form-target="content">
  <form role="form" method="post" class="form-horizontal col-md-12 col-lg-12 peixun peixun_form hidden" id="create_new" name="trin_form" data-form-type="base">
    <div class="col-md-6 col-lg-6">
      <div class="form-group">
        <label for="" class="control-label col-md-4 col-lg-4">开始时间：</label>
        <div class="col-md-8 col-lg-8 form-required">
          <div class="input-group date">
            <input type="text" class="form-control" name="trainStartDate" value="" required="required">
            <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
          </div>
        </div>
      </div>
    </div>
    <div class="col-md-6 col-lg-6">
      <div class="form-group">
        <label for="" class="control-label col-md-4 col-lg-4">培训机构：</label>
        <div class="col-md-8 col-lg-8">
          <input type="text" class="form-control" name="trainOrganization" value="">
        </div>
      </div>
    </div>
    <div class="col-md-6 col-lg-6">
      <div class="form-group">
        <label for="" class="control-label col-md-4 col-lg-4">结束时间：</label>
        <div class="col-md-8 col-lg-8 form-required">
          <div class="input-group date">
            <input type="text" class="form-control" name="trainEndDate" value="" required="required">
            <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
          </div>
        </div>
      </div>
    </div>
    <div class="col-md-6 col-lg-6">
      <div class="form-group">
        <label for="" class="control-label col-md-4 col-lg-4">培训课时：</label>
        <div class="col-md-8 col-lg-8">
          <input type="text" class="form-control" name="trainHour" value="">
        </div>
      </div>
    </div>
    <div class="col-md-6 col-lg-6">
      <div class="form-group">
        <label for="" class="control-label col-md-4 col-lg-4">课程名称：</label>
        <div class="col-md-8 col-lg-8 form-required">
          <input type="text" class="form-control" name="className" value="" required="required">
        </div>
      </div>
    </div>
    <div class="col-md-6 col-lg-6">
      <div class="form-group">
        <label for="" class="control-label col-md-4 col-lg-4">服务到期时间：</label>
        <div class="col-md-8 col-lg-8 form-required">
          <div class="input-group date">
            <input type="text" class="form-control" name="serviceExpireDate" value="" required="required">
            <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
          </div>
        </div>
      </div>
    </div>
    <div class="col-md-6 col-lg-6">
      <div class="form-group">
        <label for="" class="control-label col-md-4 col-lg-4">培训费用：</label>
        <div class="col-md-8 col-lg-8">
          <input type="text" class="form-control" name="trainCost" value="">
        </div>
      </div>
    </div>

    <div class="col-md-6 col-lg-6">
      <div class="form-group">
        <label for="" class="control-label col-md-4 col-lg-4">所得证书：</label>
        <div class="col-md-8 col-lg-8">
          <input type="text" class="form-control" name="isCertificate" value="">
        </div>
      </div>
    </div>
    <div class="col-lg-12 col-md-12 text-right">
      <button type="button" class="btn btn-success"  >保存</button>
      <button type="button" class="btn btn-default"  >取消</button>
    </div>
  </form>
</div>
<div class="text-center row">
    <a class="adds" data-form-action="add" href="javascript://"><i class=" pe-7s-plus pe-5x text-muted"></i><br><span class="text-muted">添加新记录</span></a>
  </div>
</div>
</div>
</div>
</div>
<!--培训经历 end-->

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
<script src="static/js/template.js"></script>
<script src="static/js/yuangong.js"></script>
<script src="static/js/commonH3.js"></script>
<script type="text/javascript">
/*   console.log("personId="+"${personId}");
var personId =1145;// $("#personId").val() 待改
 
 
  templateFillData('training_exp', $('#training_div'), 'static/json/training.json','employee/delJobpost.do?postId=');
   */
  
  var personId ="${personId}"; 
  var listURL="employee/trainInfo?personId="+ personId;
  var delsURL="employee/deleteTrain?trainId=";
  var updateURL="employee/updateTrain";
  var addFormSelector="[name=add_new_form]";
  var addURL="employee/addTrain";
  var templateID="training_exp";
  var containerID= $('#training_div');
  var initFormSelector="#create_new";
 
  
$(function(){
	
	 templateFillData(templateID, containerID, listURL,delsURL);
});

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
          trainStartDate: {
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
          trainEndDate: {
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
          className: {
              validators: {
                  notEmpty: {
                      message: '请填写必填项目'
                  }
              }
          },
          serviceExpireDate: {
              validators: {
                  notEmpty: {
                      message: '请填写必填项目'
                  },
                  date: {
                    format: 'YYYY-MM-DD',
                    message: '该日期是无效的'
                  }
              }
          }
      }
  };
 </script>
</body>
</html>