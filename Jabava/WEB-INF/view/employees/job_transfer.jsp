<%@ page contentType="text/html; charset=utf-8" %>
<%@page import="com.jabava.utils.RequestUtil"%>
<!DOCTYPE html>
<html>
<head>
	<base href="${pageContext.request.contextPath}/">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <!-- Page title -->
    <title>任职记录</title>
    <jsp:include flush="true" page="../common/styles.jsp"></jsp:include>
    <link rel="stylesheet" href="static/js/plugins/form.validation/css/formValidation.css">
    <link rel="stylesheet" href="static/css/user_bata.css">
    <link rel="stylesheet" href="static/css/style_1.css" media="screen">
    <link rel="stylesheet" href="static/css/bill.css">
    <link rel="stylesheet" href="static/css/zTreeStyle/zTreeStyle.css" type="text/css">
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

    <!--任职记录-->
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
            <jsp:include flush="true" page="employee_nav.jsp"> <jsp:param value="transfer" name="type"/></jsp:include>
            <!--引入员工信息导航 结束-->

            <div class="panel-body">
              <div class="panel-heading">
                <h4 class="text-center font-bold">任职记录</h4>
              </div>
              <div class="div_job">

              </div>
              <input type="hidden" name="personId" value="${personId}" id="personId">
              <!--岗位调动保存-->
              <script id="transfer" type="text/html">
                {{each list}}
                
                <div class="col-md-12 page-header m-t-md">             
                  <ul class="list-inline">
                    <li><p class="p form-control-static text-info font-bold">{{$value.recordStartDate | dateFormat }}</p></li>
                    <li><p class="form-control-static text-info font-bold">至</p></li>
                    <li><p class="form-control-static text-info font-bold">
                      {{if $value.recordEndDate}}
                        {{$value.recordEndDate | dateFormat}}
                      {{else}}
                        今
                      {{/if}}
                    </p></li>
                    <li>
                      <p class="form-control-static text-info font-bold">{{$value.companyName}}</p>
                    </li>
                    <li>
                      <p class="form-control-static text-info font-bold">
					   {{if $value.changeType == 3||$value.changeType == 1}}
					   {{each positionList as $item}}
                          {{if $value.newPostId && $value.newPostId==$item.baseDataId}}
                          {{$item.baseDataName}}
                          {{/if}}
                        {{/each}}

						{{else if $value.changeType == 4}}
						{{each positionList as $item}}
                          {{if $value.oldPostId && $value.oldPostId==$item.baseDataId}}
                          {{$item.baseDataName}}
                          {{/if}}
                        {{/each}}
						{{/if}}
                       
                      </p>
                    </li>
					  
 				 <li>
                      <p class="form-control-static text-info font-bold">
                        {{if $value.intervalDate!=null}}

                           {{$value.intervalDate}}月

                        
                        {{/if}}
                      </p>
                    </li>



                    <li>
                      <p class="form-control-static text-info font-bold">
                        {{if $value.changeType == 1}}
                          入职
                        {{else if $value.changeType == 2}}
                          转正
                        {{else if $value.changeType == 3}}
                          调动
                        {{else if $value.changeType == 4}}
                          离职
                        {{/if}}
                      </p>
                    </li>
                  </ul>
                </div>
                {{/each}}
              </script>
              <div id="gangweis">
              </div>
              <!--岗位调动保存 end-->
              <div id="gangwei_body" data-form-target="content">
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    
    <!--岗位调动 end--> 
    <!--结束 -->     

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


var personId = $("#personId").val();
// alert(personId+"  personId");

var listURL="employee/getAllRecordsByPersonId?personId="+personId;	//渲染URL
var delsURL="employee/delJobpost.do?postId=";		//删除URL
var addURL="employee/addJobpost";							//新增URL
var updateURL="employee/updateJobpost";					//修改URL
//var addFormSelector="[name=edu_add_form]";					//新增表单
//var initFormSelector="#create_new";							//新增表单id
var templateID="transfer";									//模板ID
var containerID= $('#gangweis');						//渲染结果显示区域
var url="employee/jobpostInfo?personId="+personId;
var uploadFileType="";

templateFillData(templateID, containerID, listURL,delsURL);


</script>
</body>
</html>