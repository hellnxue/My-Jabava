<%@ page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html>
<head>
	<base href="${pageContext.request.contextPath}/">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <!-- Page title -->
    <title>资料情况</title>
    <jsp:include flush="true" page="../common/styles.jsp"></jsp:include>
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
<!-- 放主要内容 -->


<!--资料情况-->
<div class="content animate-panel">
<div class="row">
 <div class="hpanel">
    <div class="panel-heading">
          <h4 class="text-center font-bold">
              <button class="btn btn-default btn-sm btn-absolute" type="button">　返回　</button>
              <span>员工资料</span>
          </h4>
    </div>
  <!--引入员工信息导航 开始--> 
<jsp:include flush="true" page="employee_nav.jsp">
  <jsp:param value="portfolio" name="type"/>
</jsp:include>
<!--引入员工信息导航 结束-->
<div class="panel-body" style=" ">
     <div class="panel-heading">
          <h4 class="text-center font-bold">资料情况</h4>
     </div>
     <div class="row m-b-lg">
    <script type="text/html" id="famliy">
      {{each fileConditionList}}

              <div class="col-md-12 page-header m-t-md{{if $index == fileConditionList.length-1}} no-borders{{/if}} ">
                <div class="panel-tools action-group">
                  <ul class="list-inline">
                     <li>
                    <a href="javascript://" data-action-motive="hideOrshow" data-action-id="{{$value.fileConditionId}}"><i class="pe-7s-angle-down-circle pe-2x text-info"></i><span class="sr-only">隐藏</span></a>
                    </li>
                    <li>
                    <a href="javascript://" data-action-motive="edit" data-action-id="{{$value.fileConditionId}}"><i class="pe-7s-note pe-2x text-info">
                    </i><span class="sr-only">修改</span></a>
                    </li>
                  </ul>
                </div>
                <ul class="list-inline">
                  <li> <p class="form-control-static text-info font-bold">{{$value.fileName}}&nbsp;</p></li>
                  <li><p class="form-control-static text-info font-bold">
                  {{each fileStatusList as $item}}
                    {{if $item.baseDataCode == $value.fileStatus}}
                      {{$item.baseDataName}}
                    {{/if}}
                  {{/each}}
                  &nbsp;
                </p></li>
                  <li><p class="form-control-static text-info font-bold">{{$value.fileDate | dateFormat}}&nbsp;</p></li>
                  <li><p class="form-control-static text-info font-bold">{{$value.responsible}}&nbsp;</p></li>
               </ul>
              </div>

      <form role="form" data-form="{{$value.fileConditionId}}" method="post" class="searchs-form form-horizontal col-md-12 col-lg-12  {{if $index>0}} jianju borders{{/if}}">
        <input type="hidden" name="fileConditionId" value="{{$value.fileConditionId}}">
        <div class="col-md-6 col-lg-6">
          <div class="form-group">
            <label for="" class="control-label col-md-4 col-lg-4">资料名称：</label>
            <div class="col-md-8 col-lg-8">
              <div class="input-group-static">
                <p class="form-control-static">{{$value.fileName}}&nbsp;</p>
              </div>
              <div class="form-required">
                <input type="text" required="required" class="form-control"  name="fileName" value="{{$value.fileName}}">
              </div>
            </div>
          </div>
        </div>
        <div class="col-md-6 col-lg-6">
          <div class="form-group">
            <label for="" class="control-label col-md-4 col-lg-4">状态：</label>
            <div class="col-md-8 col-lg-8">
              <div class="input-group-static">
                <p class="form-control-static">
                  {{each fileStatusList as $item}}
                    {{if $item.baseDataCode == $value.fileStatus}}
                      {{$item.baseDataName}}
                    {{/if}}
                  {{/each}}
                  &nbsp;
                </p>
              </div>
              <div class="form-required">
                <select class="form-control" name="fileStatus">
                  {{each fileStatusList as $item}}
                    {{if $item.baseDataCode == $value.fileStatus}}
                      {{$item.baseDataName}}
                    {{/if}}
                  <option value="{{$item.baseDataCode}}" {{if $item.baseDataCode == $value.fileStatus}} selected="selected"{{/if}}>{{$item.baseDataName}}</option>
                  {{/each}}
                </select>
              </div>
            </div>
          </div>
        </div>
          <div class="col-md-6 col-lg-6">
            <div class="form-group">
              <label for="" class="control-label col-md-4 col-lg-4">日期：</label>
              <div class="col-md-8 col-lg-8">
                <div class="input-group-static">
                  <p class="form-control-static">{{$value.fileDate}}&nbsp;</p>
                </div>
                <div class="input-group date" data-toggle="datepicker">
                  <input type="text" class="form-control" name="fileDate" value="{{$value.fileDate | dateFormat}}">
                  <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                </div>
              </div>
            </div>
          </div>
         
          <div class="col-md-6 col-lg-6">
            <div class="form-group">
              <label for="" class="control-label col-md-4 col-lg-4">负责人：</label>
              <div class="col-md-8 col-lg-8">
                <div class="input-group-static">
                  <p class="form-control-static">{{$value.responsible}}&nbsp;</p>
                </div>
                <input type="text" class="form-control" name="responsible" value="{{$value.responsible}}">
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
          <div class="col-md-6 col-lg-6">
            <div class="form-group">
              <label for="" class="control-label col-md-4 col-lg-4">资料名称：</label>
              <div class="col-md-8 col-lg-8 form-required">
                <input type="text" class="form-control"  name="fileName" value="">
              </div>
            </div>
          </div>
          <div class="col-md-6 col-lg-6">
            <div class="form-group">
              <label for="" class="control-label col-md-4 col-lg-4">状态：</label>
              <div class="col-md-8 col-lg-8 form-required">
                <select class="form-control" name="fileStatus">
                {{each fileStatusList as $item}}
                  <option value="{{$item.baseDataCode}}">{{$item.baseDataName}}</option>
                {{/each}}
                </select>
              </div>
            </div>
          </div>
          <div class="col-md-6 col-lg-6">
            <div class="form-group">
              <label for="" class="control-label col-md-4 col-lg-4">日期：</label>
              <div class="col-md-8 col-lg-8">
                <div class="input-group date" data-toggle="datepicker">
                  <input type="text" class="form-control" name="fileDate" value="">
                  <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                </div>
              </div>
            </div>
          </div>
          <div class="col-md-6 col-lg-6">
            <div class="form-group">
              <label for="" class="control-label col-md-4 col-lg-4">负责人：</label>
              <div class="col-md-8 col-lg-8">
                <input type="text" class="form-control" name="responsible" value="">
              </div>
            </div>
          </div>
          <!--备注--> 
          <div class="col-md-6 col-lg-6">
            <div class="form-group">
              <label for="" class="control-label col-md-4 col-lg-4">备注：</label>
              <div class="col-md-8 col-lg-8">
                <input type="text" class="form-control" name="memo" value="">
              </div>
            </div>
          </div>
          <div class="col-lg-12 col-md-12 text-right">
            <button type="submit" class="btn btn-success">保存</button>
            <button type="button" class="btn btn-default" data-action-motive="cancel">取消</button>
          </div>
        </form>
      </script>
      <div class="hpanel" id="famliy_div">
      </div>
      <div class="" id="jiating_body" data-form-target="content">
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
  <!--家庭成员 end-->    

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
  var personId = "${personId}"; 
  var listURL="employee/fileConditionInfo?personId="+personId;	//渲染URL
  var delsURL="employee/deleteFileCondition?fileConditionId=";		//删除URL
  var addURL="employee/addFileCondition";							//新增URL
  var updateURL="employee/updateFileCondition";					//修改URL
  var addFormSelector="[name=add_new_form]";					//新增表单
  var initFormSelector="#create_new";							//新增表单id
  var templateID="famliy";									//模板ID
  var containerID= $('#famliy_div');						//渲染结果显示区域
  //var templateType="educational";
  //var metaKey="";

    //templateFillData('education', $('#education_div'), 'static/json/educational.json','employee/delJobpost.do?postId=');
    

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
          fileName: {
              validators: {
                  notEmpty: {
                      message: '请填写必填项目'
                  }
              }
          },
          fileStatus: {
              validators: {
                  notEmpty: {
                      message: '请填写必填项目'
                  }
              }
          }
      }
  };
//日历
		$(function() {
			
			 templateFillData(templateID, containerID, listURL,delsURL);

		});
	</script>
</body>
</html>