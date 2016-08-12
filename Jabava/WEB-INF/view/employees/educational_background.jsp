<%@ page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html>
<head>
	<base href="${pageContext.request.contextPath}/">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
     <title>教育信息</title>
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
        <div id="hbreadcrumb" class="m-t-xs m-b-xs">
          <h2 class="font-normal m-b-xs text-center">
            员工资料
          </h2>
        </div>
      </div>
    </div>
  </div>

<!-- 放主要内容 -->
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
            <jsp:param value="educational" name="type"/>
        </jsp:include>
        <!--引入员工信息导航 结束-->          
        <!--教育背景-->
        
        <div class="panel-body">
          <div class="panel-heading">
                  <h4 class="text-center font-bold">教育信息</h4>
             </div>
          <div class="row ">
            <div class="hpanel" id="education_div"></div>
            <div class="" id="teach_body" data-form-target="content">
              <form role="form" name="edu_form" method="post" class="form-horizontal col-md-12 col-lg-12 teach teach_form hidden" id="create_new" data-form-type="base" data-form-validator="validator"> 
                <div class="col-md-6 col-lg-6">
                  <div class="form-group">
                    <label for="" class="control-label col-md-4 col-lg-4">在校时间：</label>
                    <div class="col-md-8 col-lg-8 form-required">
                      <div class="input-group input-daterange" data-toggle="datepicker">
                        <input type="text" class="form-control" name="entranceDate" value="">
                        <span class="input-group-addon">&mdash;</span>
                        <input type="text" class="form-control"  name="graduateDate" value="">
                      </div>
                    </div>
                  </div>
                </div>
                <div class="col-md-6 col-lg-6">
                  <div class="form-group">
                    <label for="graduateSchool" class="control-label col-md-4 col-lg-4">学校名称：</label>
                    <div class="col-md-8 col-lg-8 form-required">
                      <input type="text" class="form-control" name="graduateSchool" value="" required="required">
                    </div>
                  </div>
                </div>
                <div class="col-md-6 col-lg-6">
                  <div class="form-group">
                    <label for="" class="control-label col-md-4 col-lg-4">学历：</label>
                    <div class="col-md-8 col-lg-8 form-required">
                      <select class="form-control" name="education" base-data-type="education">
                      </select>
                    </div>
                  </div>
                </div>
                <div class="col-md-6 col-lg-6">
                  <div class="form-group">
                    <label for="" class="control-label col-md-4 col-lg-4">专业：</label>
                    <div class="col-md-8 col-lg-8">
                      <input type="text" class="form-control" name="major" value="">
                    </div>
                  </div>
                </div>
                <div class="col-md-6 col-lg-6">
                  <div class="form-group">
                    <label for="" class="control-label col-md-4 col-lg-4">学位：</label>
                    <div class="col-md-8 col-lg-8">
                      <select class="form-control" name="degree" base-data-type="degree">
                      </select>
                    </div>
                  </div>
                </div>
                <div class="col-md-6 col-lg-6">
                  <div class="form-group">
                    <label for="" class="control-label col-md-4 col-lg-4">备注：</label>
                    <div class="col-md-8 col-lg-8">
                        <input type="text" class="form-control" name="memo" value="">
                    </div>
                  </div>
                </div>
                <div class="col-lg-12 col-md-12 text-right">
                  <button  class="btn btn-success" >保存</button>
                  <button type="button" class="btn btn-default" data-action-motive="remove" >取消</button>
                </div>
              </form>
            </div>
          </div>
          <!--教育背景结束-->
       
        <!--模板区域-->
       <script type="text/html" id="education">
       
      {{each list}}

        <div class="col-md-12 page-header m-t-md{{if $index == list.length-1}} no-borders{{/if}}"">
          <div class="panel-tools action-group">
            <ul class="list-inline">
               <li>
              <a href="javascript://" data-action-motive="hideOrshow" data-action-id="{{$value.educationId}}"><i class="pe-7s-angle-down-circle pe-2x text-info"></i><span class="sr-only">隐藏</span></a>
              </li>
              <li>
              <a href="javascript://" data-action-motive="edit" data-action-id="{{$value.educationId}}"><i class="pe-7s-note pe-2x text-info">
              </i><span class="sr-only">修改</span></a>
              </li>
              <li>
                <a href="javascript://" data-action-motive="del" data-action-id="{{$value.educationId}}"><i class="pe-7s-trash pe-2x text-info"></i><span class="sr-only">删除</span></a>
              </li>
            </ul>
          </div>
          <ul class="list-inline">
            <li><div class="p form-control-static text-info font-bold">{{$value.entranceDate | dateFormat }}&nbsp;</div></li>
            <li class="m-r"><p class="form-control-static text-info font-bold">至</p></li>
            <li><p class="form-control-static text-info font-bold">{{$value.graduateDate | dateFormat}}&nbsp;</p></li>
            <li> <p class="form-control-static text-info font-bold">{{$value.graduateSchool}}&nbsp;</p></li>
			<li> <p class="form-control-static text-info font-bold"> 
							{{each educationList as b i}}
                            {{if (b.baseDataCode == $value.education)}}
                              {{b.baseDataName}}
                            {{/if}}
                            {{/each}}&nbsp;</p></li>
            <li><p class="form-control-static text-info font-bold">{{$value.major}}&nbsp;</p></li>
          </ul>
        </div>

      <form data-form="{{$value.educationId}}" role="form" method="post" name="edu_form" class="searchs-form form-horizontal col-md-12 col-lg-12  {{if $index>0}} m-t-xl{{/if}}" id="myForm{{$index}}">
        <input type="hidden" name="educationId" value="{{$value.educationId}}" />



        <!-- <input type="hidden" name="educationId" value="{{$value.educationId}}" /> -->
        <div class="col-md-6 col-lg-6">
          <div class="form-group">
            <label for="" class="control-label col-md-4 col-lg-4">在校时间：</label>
            <div class="col-md-8 col-lg-8">
              <div class="input-group-static">
                <div class="p form-control-static">
                {{$value.entranceDate | dateFormat }}&mdash;{{$value.graduateDate | dateFormat}}
                &nbsp;</div>
              </div>
              <div class="form-required">
                <div class="input-group input-daterange" data-toggle="datepicker">
                  <input type="text" class="form-control" name="entranceDate" value="{{$value.entranceDate | dateFormat}}">
                  <span class="input-group-addon">&mdash;</span>
                  <input type="text" class="form-control" name="graduateDate" value="{{$value.graduateDate | dateFormat}}">
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="col-md-6 col-lg-6">
          <div class="form-group">
            <label for="graduateSchool" class="control-label col-md-4 col-lg-4">学校名称：</label>
            <div class="col-md-8 col-lg-8">
              <div class="input-group-static">
                <p class="form-control-static">{{$value.graduateSchool}}&nbsp;</p>
              </div>
              <div class="form-required">
                <input type="text" class="form-control" name="graduateSchool" value="{{$value.graduateSchool}}">
              </div>
            </div>
          </div>
        </div>
        <div class="col-md-6 col-lg-6">
          <div class="form-group">
            <label for="" class="control-label col-md-4 col-lg-4">学历：</label>
            <div class="col-md-8 col-lg-8">

				 <div class="input-group-static">
                <p class="form-control-static"> 

 				{{each educationList as b i}}
                            {{if (b.baseDataCode == $value.education)}}
                              {{b.baseDataName}}
                            {{/if}}
                            {{/each}}
				&nbsp;
				</p>
              </div> 
              <div class="form-required">
                <select class="form-control" name="education">
                {{each educationList as $item}}
                  <option value="{{$item.baseDataCode}}"{{if $value.education == $item.baseDataCode}} selected="selected"{{/if}}>{{$item.baseDataName}}</option>
                {{/each}}                
                </select>
              </div>
              
            </div>
          </div>
        </div>
        <div class="col-md-6 col-lg-6">
          <div class="form-group">
            <label for="" class="control-label col-md-4 col-lg-4">专业：</label>
            <div class="col-md-8 col-lg-8">
              <div class="input-group-static">
                <p class="form-control-static">{{$value.major}}&nbsp;</p>
              </div> 
              <input type="text" class="form-control" name="major" value="{{$value.major}}">
            </div>
          </div>
        </div>
        <div class="col-md-6 col-lg-6">
          <div class="form-group">
            <label for="" class="control-label col-md-4 col-lg-4">学位：</label>
            <div class="col-md-8 col-lg-8">
             
 			<div class="input-group-static">
                <p class="form-control-static"> 
						    {{each degreeList as b i}}
                            {{if (b.baseDataCode == $value.degree)}}
                              {{b.baseDataName}}
                            {{/if}}
                            {{/each}}
				&nbsp;</p>
              </div> 
              <div class="form-required">
                <select class="form-control" name="education">
                {{each degreeList as $item}}
                  <option value="{{$item.baseDataCode}}"{{if $value.degree == $item.baseDataCode}} selected="selected"{{/if}}>{{$item.baseDataName}}</option>
                {{/each}}
                </select>
              </div>
			
            </div>
          </div>
        </div>
        <div class="col-md-6 col-lg-6">
          <div class="form-group">
            <label for="" class="control-label col-md-4 col-lg-4">备注：</label>
            <div class="col-md-8 col-lg-8">
              <div class="input-group-static">
                <p class="form-control-static">{{}}&nbsp;</p>
              </div> 
              <input type="text" class="form-control" id="" name="" value="{{}}">
            </div>
          </div>
        </div>
        <div class="col-lg-12 col-md-12 text-center form-action">
          <button  class="btn btn-info"  >保存</button>
          <button type="button" class="btn btn-default" data-action-motive="cancel">取消</button>
        </div>
      </form>
      {{/each}}
    </script>
    <div class="text-center m-t-lg">
      <a class="adds" data-form-action="add" href="javascript://"><i class=" pe-7s-plus pe-5x text-muted"></i><br>
      <span class=" text-muted">添加新记录</span></a>
    </div>
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

<script src="static/js/plugins/form.validation/js/formValidation.js"></script>
<script src="static/js/plugins/form.validation/js/framework/bootstrap.js"></script>
<script src="static/js/plugins/form.validation/js/language/zh_CN.js"></script> 
<!-- App scripts -->
<script src="static/bootstrap/scripts/homer.js"></script>
<script src="static/js/educational_background.js"></script>
<script src="static/js/template.js"></script>
<script src="static/js/yuangong.js"></script>
<script src="static/js/commonH3.js"></script>
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
//定义添加表单中所用的基础数据信息，数组内容是下拉列表的name，后台的数据list格式为此处的name+List
var baseDataTypeObj=["education", "degree"];
//var templateType="educational";
//var metaKey="";

  //templateFillData('education', $('#education_div'), 'static/json/educational.json','employee/delJobpost.do?postId=');
 
//日历

  $(function(){
    
	  templateFillData(templateID, containerID, listURL,delsURL,"","",baseDataTypeObj);
	  
	  
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
          entranceDate: {
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
          graduateDate: {
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
          graduateSchool: {
              validators: {
                  notEmpty: {
                      message: '请填写必填项目'
                  }
              }
          },
          learnType: {
              validators: {
                  notEmpty: {
                      message: '请填写必填项目'
                  }
              }
          },
          schoolTime: {
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
          }
      }
  };
</script>

</body>
</html>