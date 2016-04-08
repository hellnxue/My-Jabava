<%@ page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html>
<head>
	<base href="${pageContext.request.contextPath}/">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <!-- Page title -->
    <title>家庭成员</title>
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
    <div class="normalheader transition animated fadeIn small-header">
      <div class="hpanel">
<div class="panel-body"><!-- 
<a class="small-header-action" href="">
<div class="clip-header">
<i class="fa fa-arrow-up"></i>
</div>
</a> -->

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


<!--家庭成员-->
<div class="content animate-panel">
<div class="row">
 <div class="hpanel hblue">
  <!--引入员工信息导航 开始--> 
<jsp:include flush="true" page="employee_nav.jsp"></jsp:include>
<!--引入员工信息导航 结束-->
  <div class="panel-heading hbuilt">
    <div class="panel-tools">
      <button type="button" class="btn btn-success btn-xs fm_cy"  data-form-action="add"><i class="fa fa-plus"></i><span class="bold">继续添加</span></button>
        <button type="button" class="btn btn-success btn-xs fm_cy_update" style=" display:none;"><i class="fa fa-plus"></i><span class="bold">继续添加</span></button>
        <button type="button" class="btn btn-success btn-xs ld_ht"><span class="bold">劳动合同&gt;&gt;</span></button>  
    </div>
    <h4>家庭成员</h4>
  </div>
<div class="panel-body" style=" ">
    <script type="text/html" id="famliy">
      {{each list}}
      <form role="form" method="post" class="searchs-form form-horizontal col-md-12 col-lg-12  {{if $index>0}} jianju borders{{/if}}">
        <div class="text-right action-group">
          <a href="javascript://" class="pe-7s-note pe-2x" data-action-motive="edit" ><span class="sr-only">修改</span></a>
          <a href="javascript://" class="pe-7s-trash pe-2x" data-action-motive="del" data-action-id="{{$value.familyId}}"><span class="sr-only">删除</span></a>
        </div>
        <input type="hidden" name="familyId" value="{{$value.familyId}}">
        <!--成员姓名-->
        <div class="col-md-4 col-lg-4">
          <div class="form-group">
            <label for="" class="control-label col-md-4 col-lg-4">成员姓名：</label>
            <div class="col-md-8 col-lg-8">
              <div class="input-group-static">
                <p class="form-control-static">{{$value.name}}&nbsp;</p>
              </div>
              <input type="text" required="required" class="form-control"  name="name" value="{{$value.name}}">
            </div>
          </div>
        </div>
        <!--与本人关系-->
        <div class="col-md-4 col-lg-4">
          <div class="form-group">
            <label for="" class="control-label col-md-4 col-lg-4">与本人关系：</label>
            <div class="col-md-8 col-lg-8">
              <div class="input-group-static">
                <p class="form-control-static">
                  {{if $value.relation=='1'}}
                  父母
                  {{else if $value.relation=='2' }}
                  配偶
                  {{else if $value.relation=='3' }}
                  儿子
                  {{else if $value.relation=='4' }}
                  女儿
                  {{else if $value.relation=='5' }}
                  其他
                  {{/if}}
                  &nbsp;
                </p>
              </div>
              <select class="form-control " name="relation">
                <option value="1"{{if $value.relation=='1'}} selected="selected"{{/if}}>父母</option>
                <option value="2"{{if $value.relation=='2'}} selected="selected"{{/if}}>配偶</option>
                <option value="3"{{if $value.relation=='3'}} selected="selected"{{/if}}>儿子</option>
                <option value="4"{{if $value.relation=='4'}} selected="selected"{{/if}}>女儿</option>
                <option value="5"{{if $value.relation=='5'}} selected="selected"{{/if}}>其他</option>
              </select>
            </div>
          </div>
        </div>
        <!--居住城市-->
        <div class="col-md-4 col-lg-4">
          <div class="form-group">
            <label for="" class="control-label col-md-4 col-lg-4">居住城市：</label>
            <div class="col-md-8 col-lg-8">
              <div class="input-group-static">
                <p class="form-control-static">{{$value.city}}&nbsp;</p>
              </div>
              <input type="text" required="required" class="form-control" name="city" value="{{$value.city}}">
            </div>
          </div>
        </div>
        <!--出生日期-->
        <div class="col-md-4 col-lg-4">
          <div class="form-group">
            <label for="" class="control-label col-md-4 col-lg-4">出生时间：</label>
            <div class="col-md-8 col-lg-8">
              <div class="input-group-static">
                <p class="form-control-static">
                  {{if $value.birthDate!=null}}
                  {{$value.birthDate | dateFormat}}
                  {{/if}}
                  &nbsp;</p>
                </div>
                <div class="input-group date">
                  <input type="text" required="required" class="form-control" name="birthDate" value="{{if $value.birthDate!=null}}{{$value.birthDate | dateFormat}}{{/if}}">
                  <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                </div>
              </div>
            </div>
          </div>
          <!--联系电话-->
          <div class="col-md-4 col-lg-4">
            <div class="form-group">
              <label for="" class="control-label col-md-4 col-lg-4">联系电话：</label>
              <div class="col-md-8 col-lg-8">
                <div class="input-group-static">
                  <p class="form-control-static">{{$value.phone}}&nbsp;</p>
                </div>
                <input type="text" required="required" class="form-control" name="phone" value="{{$value.phone}}">
              </div>
            </div>
          </div>
          <!--身份证号--> 
          <div class="col-md-4 col-lg-4">
            <div class="form-group">
              <label for="" class="control-label col-md-4 col-lg-4">身份证号：</label>
              <div class="col-md-8 col-lg-8">
                <div class="input-group-static">
                  <p class="form-control-static">{{$value.idCard}}&nbsp;</p>
                </div>
                <input type="text" class="form-control" name="idCard" value="{{$value.idCard}}">
              </div>
            </div>
          </div>
          <!--工作单位--> 
          <div class="col-md-4 col-lg-4">
            <div class="form-group">
              <label for="" class="control-label col-md-4 col-lg-4">工作单位：</label>
              <div class="col-md-8 col-lg-8">
                <div class="input-group-static">
                  <p class="form-control-static">{{$value.workUnit}}&nbsp;</p>
                </div>
                <input type="text" class="form-control" name="workUnit" value="{{$value.workUnit}}">
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
      <div class="hpanel" id="famliy_div">
      </div>
      <div class="" id="jiating_body" data-form-target="content">
        <form role="form" method="post" class="form-horizontal col-md-12 col-lg-12 famliy_1 famliy_1_form hidden" id="create_new" name="family_form" data-form-type="base"> 
          <div class="col-md-4 col-lg-4">
            <div class="form-group">
              <label for="" class="control-label col-md-4 col-lg-4">成员姓名：</label>
              <div class="col-md-8 col-lg-8">
                <input type="text" class="form-control"  name="name" value="">
              </div>
            </div>
          </div>
          <!--与本人关系-->
          <div class="col-md-4 col-lg-4">
            <div class="form-group">
              <label for="" class="control-label col-md-4 col-lg-4">与本人关系：</label>
              <div class="col-md-8 col-lg-8">
                <select class="form-control " name="relation">
                  <option value="1">父母</option>
                  <option value="2">配偶</option>
                  <option value="3">儿子</option>
                  <option value="4">女儿</option>
                  <option value="5">其他</option>
                </select>
              </div>
            </div>
          </div>
          <!--居住城市-->
          <div class="col-md-4 col-lg-4">
            <div class="form-group">
              <label for="" class="control-label col-md-4 col-lg-4">居住城市：</label>
              <div class="col-md-8 col-lg-8">
                <input type="text" class="form-control" name="city" value="">
              </div>
            </div>
          </div>
          <!--出生日期-->
          <div class="col-md-4 col-lg-4">
            <div class="form-group">
              <label for="" class="control-label col-md-4 col-lg-4">出生时间：</label>
              <div class="col-md-8 col-lg-8">
                <div class="input-group date">
                  <input type="text" class="form-control" name="birthDate" value="">
                  <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                </div>
              </div>
            </div>
          </div>
          <!--联系电话-->
          <div class="col-md-4 col-lg-4">
            <div class="form-group">
              <label for="" class="control-label col-md-4 col-lg-4">联系电话：</label>
              <div class="col-md-8 col-lg-8">
                <input type="text" class="form-control" name="phone" value="">
              </div>
            </div>
          </div>
          <!--身份证号--> 
          <div class="col-md-4 col-lg-4">
            <div class="form-group">
              <label for="" class="control-label col-md-4 col-lg-4">身份证号：</label>
              <div class="col-md-8 col-lg-8">
                <input type="text" class="form-control" name="idCard" value="">
              </div>
            </div>
          </div>
          <!--工作单位--> 
          <div class="col-md-4 col-lg-4">
            <div class="form-group">
              <label for="" class="control-label col-md-4 col-lg-4">工作单位：</label>
              <div class="col-md-8 col-lg-8">
                <input type="text" class="form-control" name="workUnit" value="">
              </div>
            </div>
          </div>
          <div class="col-lg-12 col-md-12 text-center">
            <button type="button" class="btn btn-info">保存</button>
          </div>
        </form>
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

<!-- App scripts -->
<script src="static/bootstrap/scripts/homer.js"></script>
<script src="static/js/template.js"></script>
<script src="static/js/yuangong.js"></script>
<script src="static/js/common.js"></script>
<script type="text/javascript">
/* var personId = $("#personId").val();
  templateFillData('famliy', $('#famliy_div'), 'static/json/member.json','employee/delJobpost.do?postId=');
   */
  
  var personId = "${personId}"; 
  var listURL="employee/familyInfo?personId="+personId;	//渲染URL
  var delsURL="employee/deleteFamily?familyId=";		//删除URL
  var addURL="employee/addFamily";							//新增URL
  var updateURL="employee/updateFamily";					//修改URL
  var addFormSelector="[name=add_new_form]";					//新增表单
  var initFormSelector="#create_new";							//新增表单id
  var templateID="famliy";									//模板ID
  var containerID= $('#famliy_div');						//渲染结果显示区域
  //var templateType="educational";
  //var metaKey="";

    //templateFillData('education', $('#education_div'), 'static/json/educational.json','employee/delJobpost.do?postId=');
    
</script>

<!--日历-->
	<script>
		$(function() {
			
			 templateFillData(templateID, containerID, listURL,delsURL);

		});
	</script>
</body>
</html>