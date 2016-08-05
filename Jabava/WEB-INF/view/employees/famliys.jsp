<%@ page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html>
<head>
	<base href="${pageContext.request.contextPath}/">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <!-- Page title -->
    <title>社会关系</title>
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


<!--社会关系-->
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
  <jsp:param value="member" name="type"/>
</jsp:include>
<!--引入员工信息导航 结束-->
<div class="panel-body" style=" ">
     <div class="panel-heading">
          <h4 class="text-center font-bold">社会关系</h4>
     </div>
     <div class="row m-b-lg">
    <script type="text/html" id="famliy">
      {{each list}}

              <div class="col-md-12 page-header m-t-md{{if $index == list.length-1}} no-borders{{/if}} ">
                <div class="panel-tools action-group">
                  <ul class="list-inline">
                     <li>
                    <a href="javascript://" data-action-motive="hideOrshow" data-action-id="{{$value.familyId}}"><i class="pe-7s-angle-down-circle pe-2x text-info"></i><span class="sr-only">隐藏</span></a>
                    </li>
                    <li>
                    <a href="javascript://" data-action-motive="edit" data-action-id="{{$value.familyId}}"><i class="pe-7s-note pe-2x text-info">
                    </i><span class="sr-only">修改</span></a>
                    </li>
                  </ul>
                </div>
                <ul class="list-inline">
                  <li> <p class="form-control-static text-info font-bold">{{$value.name}}&nbsp;</p></li>
                  <li class="m-r"><p class="form-control-static text-info font-bold">
                  {{if $value.relation=='1'}}
                  父母
                  {{else if $value.relation=='2' }}
                  配偶
                  {{else if $value.relation=='3' }}
                  子女
                  {{else if $value.relation=='4' }}
                  兄弟姐妹
                  {{else if $value.relation=='5' }}
                  同事
                  {{else if $value.relation=='6' }}
                  朋友
                  {{else if $value.relation=='7' }}
                  其他
                  {{/if}}
                  &nbsp;
                </p></li>
                  <li><p class="form-control-static text-info font-bold">{{$value.phone}}&nbsp;</p></li>    
               </ul>
              </div>

      <form role="form" data-form="{{$value.familyId}}" method="post" class="searchs-form form-horizontal col-md-12 col-lg-12  {{if $index>0}} jianju borders{{/if}}">
        <input type="hidden" name="familyId" value="{{$value.familyId}}">
        <!--姓名-->
        <div class="col-md-6 col-lg-6">
          <div class="form-group">
            <label for="" class="control-label col-md-4 col-lg-4">姓名：</label>
            <div class="col-md-8 col-lg-8">
              <div class="input-group-static">
                <p class="form-control-static">{{$value.name}}&nbsp;</p>
              </div>
              <div class="form-required">
                <input type="text" required="required" class="form-control"  name="name" value="{{$value.name}}">
              </div>
            </div>
          </div>
        </div>
        <!--与本人关系-->
        <div class="col-md-6 col-lg-6">
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
                  子女
                  {{else if $value.relation=='4' }}
                  兄弟姐妹
                  {{else if $value.relation=='5' }}
                  同事
                  {{else if $value.relation=='6' }}
                  朋友
                  {{else if $value.relation=='7' }}
                  其他
                  {{/if}}
                  &nbsp;
                </p>
              </div>
              <div class="form-required">
                <select class="form-control " name="relation">
                  <option value="2"{{if $value.relation=='2'}} selected="selected"{{/if}}>配偶</option>
                  <option value="3"{{if $value.relation=='3'}} selected="selected"{{/if}}>子女</option>
                  <option value="1"{{if $value.relation=='1'}} selected="selected"{{/if}}>父母</option>
                  <option value="4"{{if $value.relation=='4'}} selected="selected"{{/if}}>兄弟姐妹</option>
                  <option value="6"{{if $value.relation=='5'}} selected="selected"{{/if}}>同事</option>
                  <option value="7"{{if $value.relation=='6'}} selected="selected"{{/if}}>朋友</option>
                  <option value="5"{{if $value.relation=='7'}} selected="selected"{{/if}}>其他</option>
                </select>
              </div>
            </div>
          </div>
        </div>
          <!--联系电话-->
          <div class="col-md-6 col-lg-6">
            <div class="form-group">
              <label for="" class="control-label col-md-4 col-lg-4">联系电话：</label>
              <div class="col-md-8 col-lg-8">
                <div class="input-group-static">
                  <p class="form-control-static">{{$value.phone}}&nbsp;</p>
                </div>
                <div class="form-required">
                  <input type="text" required="required" class="form-control" name="phone" value="{{$value.phone}}">
                </div>
              </div>
            </div>
          </div>
         
          <!--工作单位--> 
          <div class="col-md-6 col-lg-6">
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
        <!--居住城市-->
        <div class="col-md-6 col-lg-6">
          <div class="form-group">
            <label for="" class="control-label col-md-4 col-lg-4">联系地址：</label>
            <div class="col-md-8 col-lg-8">
              <div class="input-group-static">
                <p class="form-control-static">{{$value.address}}&nbsp;</p>
              </div>
                <input type="text" class="form-control" name="address" value="{{$value.address}}">
            </div>
          </div>
        </div>
         <!--职务--> 
          <div class="col-md-6 col-lg-6">
            <div class="form-group">
              <label for="" class="control-label col-md-4 col-lg-4">职务：</label>
              <div class="col-md-8 col-lg-8">
                <div class="input-group-static">
                  <p class="form-control-static">{{$value.position}}&nbsp;</p>
                </div>
                <input type="text" class="form-control" name="position" value="{{$value.position}}">
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
      </script>
      <div class="hpanel" id="famliy_div">
      </div>
      <div class="" id="jiating_body" data-form-target="content">
        <form role="form" method="post" class="form-horizontal col-md-12 col-lg-12 famliy_1 famliy_1_form hidden" id="create_new" name="family_form" data-form-type="base"> 
          <div class="col-md-6 col-lg-6">
            <div class="form-group">
              <label for="" class="control-label col-md-4 col-lg-4">姓名：</label>
              <div class="col-md-8 col-lg-8 form-required">
                <input type="text" class="form-control"  name="name" value="">
              </div>
            </div>
          </div>
          <!--与本人关系-->
          <div class="col-md-6 col-lg-6">
            <div class="form-group">
              <label for="" class="control-label col-md-4 col-lg-4">与本人关系：</label>
              <div class="col-md-8 col-lg-8 form-required">
                <select class="form-control " name="relation">
                  <option value="2">配偶</option>
                  <option value="3">子女</option>
                  <option value="1">父母</option>
                  <option value="4">兄弟姐妹</option>
                  <option value="5">同事</option>
                  <option value="6">朋友</option>
                  <option value="7">其他</option>
                </select>
              </div>
            </div>
          </div>
          <!--联系电话-->
          <div class="col-md-6 col-lg-6">
            <div class="form-group">
              <label for="" class="control-label col-md-4 col-lg-4">联系电话：</label>
              <div class="col-md-8 col-lg-8 form-required">
                <input type="text" class="form-control" name="phone" value="">
              </div>
            </div>
          </div>
          <!--工作单位--> 
          <div class="col-md-6 col-lg-6">
            <div class="form-group">
              <label for="" class="control-label col-md-4 col-lg-4">工作单位：</label>
              <div class="col-md-8 col-lg-8">
                <input type="text" class="form-control" name="workUnit" value="">
              </div>
            </div>
          </div>
          <!--联系地址-->
          <div class="col-md-6 col-lg-6">
            <div class="form-group">
              <label for="" class="control-label col-md-4 col-lg-4">联系地址：</label>
              <div class="col-md-8 col-lg-8">
                <input type="text" class="form-control" name="address" value="">
              </div>
            </div>
          </div>
          <!--职务--> 
          <div class="col-md-6 col-lg-6">
            <div class="form-group">
              <label for="" class="control-label col-md-4 col-lg-4">职务：</label>
              <div class="col-md-8 col-lg-8">
                <input type="text" class="form-control" name="position" value="">
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
            <button type="submit" class="btn btn-success" >保存</button>
            <button type="button" class="btn btn-default" data-action-motive="cancel">取消</button>
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
          name: {
              validators: {
                  notEmpty: {
                      message: '请填写必填项目'
                  }
              }
          },
          relation: {
              validators: {
                  notEmpty: {
                      message: '请填写必填项目'
                  }
              }
          },
          phone: {
              validators: {
                  notEmpty: {
                      message: '请填写必填项目'
                  },
                  phone: {
                    country: 'CN',
                    message: '请输入有效的手机或电话号码'
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