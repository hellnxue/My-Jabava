<%@ page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html>
<head>
	<base href="${pageContext.request.contextPath}/">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
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
<div id="wrapper">
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
<div class="content animate-panel">
  <div class="row">
    <div class="col-lg-12">
      <div class="hpanel hblue">
        <!--引入员工信息导航 开始--> 
        <jsp:include flush="true" page="employee_nav.jsp">
            <jsp:param value="educational" name="type"/>
        </jsp:include>
        <!--引入员工信息导航 结束-->          
        <!--教育背景-->
        <div class="panel-heading hbuilt">
          <div class="panel-tools">
            <button type="button" class="btn btn-success btn-xs adds" data-form-action="add"><i class="fa fa-plus"></i><span class="bold">继续添加</span></button>
            <button type="button" class="btn btn-success btn-xs adds_update btn_none"><i class="fa fa-plus"></i><span class="bold">继续添加</span></button>
            <button type="button" class="btn btn-success btn-xs gzjy"><span class="bold">工作经验&gt;&gt;</span></button>
          </div>
          <h4>教育背景</h4>
        </div>
        <div class="panel-body">
          <div class="row ">
            <div class="hpanel" id="education_div"></div>
            <div class="" id="teach_body" data-form-target="content">
              <form role="form" name="edu_form" method="post" class="form-horizontal col-md-12 col-lg-12 teach teach_form hidden" id="create_new" data-form-type="base"> 
                <div class="col-md-4 col-lg-4">
                  <div class="form-group">
                    <label for="" class="control-label col-md-4 col-lg-4">入学时间：</label>
                    <div class="col-md-8 col-lg-8">
                      <div class="input-group date">
                        <input type="text" class="form-control" name="entranceDate" value="" required="required">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                      </div>
                    </div>
                  </div>
                </div>
                <div class="col-md-4 col-lg-4">
                  <div class="form-group">
                    <label for="" class="control-label col-md-4 col-lg-4">毕业时间：</label>
                    <div class="col-md-8 col-lg-8">
                      <div class="input-group date" data-date-format="yyyy-mm-dd">
                        <input type="text" class="form-control"  name="graduateDate" value="" required="required">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                      </div>
                    </div>
                  </div>
                </div>
                <div class="col-md-4 col-lg-4">
                  <div class="form-group">
                    <label for="graduateSchool" class="control-label col-md-4 col-lg-4">毕业学校：</label>
                    <div class="col-md-8 col-lg-8">
                      <input type="text" class="form-control" name="graduateSchool" value="" required="required">
                    </div>
                  </div>
                </div>
                <div class="col-md-4 col-lg-4">
                  <div class="form-group">
                    <label for="" class="control-label col-md-4 col-lg-4">所学专业：</label>
                    <div class="col-md-8 col-lg-8">
                      <input type="text" class="form-control" name="major" value="">
                    </div>
                  </div>
                </div>
                <div class="col-md-4 col-lg-4">
                  <div class="form-group">
                    <label for="" class="control-label col-md-4 col-lg-4">学习形式：</label>
                    <div class="col-md-8 col-lg-8">
                      <div class="radio-group">
                        <label class="radio-inline">
                          <input type="radio" name="learnType" value="全日制" checked="checked"> 全日制
                        </label>
                        <label class="radio-inline">
                          <input type="radio" name="learnType" value="非全日制"> 非全日制
                        </label>
                      </div>
                    </div>
                  </div>
                </div>
                <div class="col-md-4 col-lg-4">
                  <div class="form-group">
                    <label for="" class="control-label col-md-4 col-lg-4">学制：</label>
                    <div class="col-md-8 col-lg-8">
                      <div class="input-group">
                        <input type="text" class="form-control" name="schoolTime" value="" required="required">
                        <span class="input-group-addon">年</span>
                      </div>
                    </div>
                  </div>
                </div>
                <div class="col-md-4 col-lg-4">
                  <div class="form-group">
                    <label for="" class="control-label col-md-4 col-lg-4">学历：</label>
                    <div class="col-md-8 col-lg-8">
                      <select class="form-control" name="education">
                        <option value="小学">小学</option>
                        <option value="初中">初中</option>
                        <option value="高中">高中</option>
                        <option value="大专">大专</option>
                        <option value="本科">本科</option>
                        <option value="硕士">硕士</option>
                        <option value="博士">博士</option>
                      </select>
                    </div>
                  </div>
                </div>
                <div class="col-md-4 col-lg-4">
                  <div class="form-group">
                    <label for="" class="control-label col-md-4 col-lg-4">证书编号：</label>
                    <div class="col-md-8 col-lg-8">
                      <input type="text" class="form-control" id="" name="educationCertificate" value="">
                    </div>
                  </div>
                </div>
                <div class="col-lg-12 col-md-12 text-center">
                  <button type="button" class="btn btn-info"  >保存</button>
                </div>
              </form>
            </div>
          </div>
          <!--教育背景结束-->
       
        <!--模板区域-->
       <script type="text/html" id="education">
       
      {{each list}}
      <form role="form" method="post" name="edu_form" class="searchs-form form-horizontal col-md-12 col-lg-12  {{if $index>0}} jianju borders{{/if}}" id="myForm{{$index}}">
        <div class="text-right action-group">
          <a href="javascript://" class="pe-7s-note pe-2x" data-action-motive="edit" data-action-id="{{$value.educationId}}"><span class="sr-only">修改</span></a>
          <a href="javascript://" class="pe-7s-trash pe-2x" data-action-motive="del" data-action-id="{{$value.educationId}}"><span class="sr-only">删除</span></a>
        </div>

        <input type="hidden" name="educationId" value="{{$value.educationId}}" />
        <div class="col-md-4 col-lg-4">
          <div class="form-group">
            <label for="" class="control-label col-md-4 col-lg-4">入学时间：</label>
            <div class="col-md-8 col-lg-8">
              <div class="input-group-static">
                <div class="p form-control-static">{{$value.entranceDate | dateFormat }}&nbsp;</div>
              </div>
              <div class="input-group date">
                <input type="text" class="form-control" name="entranceDate" value="{{$value.entranceDate | dateFormat}}">
                <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
              </div>
            </div>
          </div>
        </div>
        <div class="col-md-4 col-lg-4">
          <div class="form-group">
            <label for="" class="control-label col-md-4 col-lg-4">毕业时间：</label>
            <div class="col-md-8 col-lg-8">
              <div class="input-group-static">
                <p class="form-control-static">{{$value.graduateDate | dateFormat}}&nbsp;</p>
              </div>
              <div class="input-group date">
                <input type="text" class="form-control" name="graduateDate" value="{{$value.graduateDate | dateFormat}}">
                <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
              </div>
            </div>
          </div>
        </div>
        <div class="col-md-4 col-lg-4">
          <div class="form-group">
            <label for="graduateSchool" class="control-label col-md-4 col-lg-4">毕业学校：</label>
            <div class="col-md-8 col-lg-8">
              <div class="input-group-static">
                <p class="form-control-static">{{$value.graduateSchool}}&nbsp;</p>
              </div>
              <input type="text" class="form-control" name="graduateSchool" value="{{$value.graduateSchool}}">
            </div>
          </div>
        </div>
        <div class="col-md-4 col-lg-4">
          <div class="form-group">
            <label for="" class="control-label col-md-4 col-lg-4">所学专业：</label>
            <div class="col-md-8 col-lg-8">
              <div class="input-group-static">
                <p class="form-control-static">{{$value.major}}&nbsp;</p>
              </div>
              <input type="text" class="form-control" name="major" value="{{$value.major}}">
            </div>
          </div>
        </div>
        <div class="col-md-4 col-lg-4">
          <div class="form-group">
            <label for="" class="control-label col-md-4 col-lg-4">学习形式：</label>
            <div class="col-md-8 col-lg-8">
              <div class="input-group-static">
                <p class="form-control-static">
                  {{$value.learnType}}&nbsp;
                </p>
              </div>
              <div class="radio-group">
                <label class="radio-inline">
                  <input type="radio" name="learnType" value="全日制"{{if $value.learnType == "全日制"}} checked="checked"{{/if}}> 全日制
                </label>
                <label class="radio-inline">
                  <input type="radio" name="learnType" value="非全日制"{{if $value.learnType == "非全日制"}} checked="checked"{{/if}}> 非全日制
                </label>
              </div>
            </div>
          </div>
        </div>
        <div class="col-md-4 col-lg-4">
          <div class="form-group">
            <label for="" class="control-label col-md-4 col-lg-4">学制：</label>
            <div class="col-md-8 col-lg-8">
              <div class="input-group-static">
                <p class="form-control-static">{{$value.schoolTime}}&nbsp;</p>
              </div>
              <div class="input-group">
                <input type="text" class="form-control" name="schoolTime" value="{{$value.schoolTime}}">
                <span class="input-group-addon">年</span>
              </div>
            </div>
          </div>
        </div>
        <div class="col-md-4 col-lg-4">
          <div class="form-group">
            <label for="" class="control-label col-md-4 col-lg-4">学历：</label>
            <div class="col-md-8 col-lg-8">
              <div class="input-group-static">
                <p class="form-control-static">{{$value.education}}&nbsp;</p>
              </div>
              <select class="form-control" name="education">
                <option {{if $value.education == "小学"}}selected="selected"{{/if}} value="小学">小学</option>
                <option {{if $value.education == "初中"}}selected="selected"{{/if}} value="初中">初中</option>
                <option {{if $value.education == "高中"}}selected="selected"{{/if}} value="高中">高中</option>
                <option {{if $value.education == "大专"}}selected="selected"{{/if}} value="大专">大专</option>
                <option {{if $value.education == "本科"}}selected="selected"{{/if}} value="本科">本科</option>
                <option {{if $value.education == "硕士"}}selected="selected"{{/if}} value="硕士">硕士</option>
                <option {{if $value.education == "博士"}}selected="selected"{{/if}} value="博士">博士</option>
              </select>
            </div>
          </div>
        </div>
        <div class="col-md-4 col-lg-4">
          <div class="form-group">
            <label for="" class="control-label col-md-4 col-lg-4">证书编号：</label>
            <div class="col-md-8 col-lg-8">
              <div class="input-group-static">
                <p class="form-control-static">{{$value.educationCertificate}}&nbsp;</p>
              </div>
              <input type="text" class="form-control" id="" name="educationCertificate" value="{{$value.educationCertificate}}">
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

  </div>
</div>
</div>
</div>
           
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
var personId = "${personId}"; 
var listURL="employee/educationInfo?personId="+personId;	//渲染URL
var delsURL="employee/deleteEducation?educationId=";		//删除URL
var addURL="employee/addEducation";							//新增URL
var updateURL="employee/updateEducation";					//修改URL
var addFormSelector="[name=add_new_form]";					//新增表单
var initFormSelector="#create_new";							//新增表单id
var templateID="education";									//模板ID
var containerID= $('#education_div');						//渲染结果显示区域
//var templateType="educational";
//var metaKey="";

  //templateFillData('education', $('#education_div'), 'static/json/educational.json','employee/delJobpost.do?postId=');
 
</script>
<!--日历-->
<script>
  $(function(){
    
	  templateFillData(templateID, containerID, listURL,delsURL);
    
  });
</script>

</body>
</html>