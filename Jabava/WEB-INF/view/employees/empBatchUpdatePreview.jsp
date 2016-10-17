<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import="com.jabava.utils.RequestUtil" %>
<%@ page import="com.jabava.pojo.manage.EhrPerson" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
	<base href="${pageContext.request.contextPath}/">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- Page title -->
    <title>批量编辑信息预览</title>
    <jsp:include flush="true" page="../common/styles.jsp"></jsp:include>
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
  
<!-- Main Wrapper -->
<div id="wrapper">
    <!-- 放主要内容 -->
    <div class="content animate-panel">
        <!-- 上传模板 -->
        <div class="row">
            <div class="col-lg-12">
                <div class="hpanel">
                    <div class="panel-heading">
                        <h4 class="text-center font-bold">
                           批量更新员工基本信息
                          <a class="btn btn-success btn-sm pull-left" type="button" href="employee/employeeList">返回</a>                       
                        </h4> 
                    </div>
                    <div class="panel-body">
                       <div class="col-sm-12 col-md-12 col-lg-12 text-center m-t-lg">
                        <div class="liner"></div>
                           <div class="slicks">
                               <div class="uploadModal col-sm-4 col-md-4 col-lg-4">
                                  <button type="button" class="btn btn-default btn-circle btn-lg">1</button>
                                  <p class="m-t-sm">上传模板</p>
                               </div>
                               <div class=" col-sm-4 col-md-4 col-lg-4">
                                  <button type="button" class="btn btn-info btn-circle btn-lg active">2</button>
                                  <p class="m-t-sm">上传预览</p>
                               </div>
                               <div class="complete col-sm-4 col-md-4 col-lg-4">
                                  <button type="button" class="btn btn-default btn-circle btn-lg">3</button>
                                  <p class="m-t-sm">上传完成</p>
                               </div>
                           </div>
                       </div>
                       <div class="uploadPreview clearfix"> </div>                                
                          <!-- 预览 -->
                           <div class="empBatchUpdatePreview">
                            <div class="tishi clearfix m-t">
                               <p class="m-l m-t-sm">已上传文件：<span id="empUpdateFileName" ></span></p>
                               <p class="m-l">正确可导入数据<span style='color:red;' class='empBatchUpdateTrueMsg'></span>条 <!-- <button class="btn btn-success btn-sm">  查看详情</button>--></p>   
                               <p class="m-l">错误不可导入数据 <span style='color:red;' class='empBatchUpdatefalseMsg'></span>条　<!-- <button class="btn btn-danger btn-sm"> 查看详情</button>--></p>  
                          </div>
                          <div class="hpanel m-t">
                            <ul class="nav nav-tabs">
                                <li class="active"><a data-action="sure" href="#right" data-toggle="tab">正确</a></li>
                                <li class=""><a data-action="error" href="#error" data-toggle="tab">错误</a></li>
                            </ul>
                            <div class="tab-content m-t-sm">
                              <div class="sure tab-pane active" id="right">
                                <ul class="nav nav-tabs">
                                      <li class="active"><a data-action="personal" href="#personal" data-toggle="tab">个人信息</a></li>
                                      <li class=""><a data-action="job" href="#job" data-toggle="tab">工作信息</a></li>
                                 </ul>
                                  <input type="hidden" id="results" value="${sessionScope.results}"> 
                                 <div class="tab-content">
                                     <div id="personal" class="tab-pane active">
                                        <div class="panel-body">
                                           <table id="basePersonUpdateInfo" class="table table-striped table-responsive table-condensed table-hover table-bordered dataTable" width="100%">
                                              <thead>
                                              	<tr>
                                                  <th>序号</th>
												  <th>工号</th>
												  <th>姓名</th>
												  <th>性别</th>
												  <th>是否外籍</th>
												  <th>参加工作时间</th>
												  <th>证件类型</th>
												  <th>证件号码</th>
												  <th>开户行</th>
												  <th>开户支行</th>
												  <th>工资卡号</th>
												  <th>最高学历</th>
												  <th>最高学位</th>
												  <th>是否海外留学</th>
												  <th>户口所在地</th>
										          <th>户口类型</th>
												  <th>籍贯</th>
												  <th>民族</th>
												  <th>手机</th>
                                                  <th>联系电话</th>
                                                  <th>电子邮件</th>
                                                  <th>现居住地</th>
                                                  <th>家庭住址</th>
                                                  <th>邮编</th>
                                                  <th>档案存放处</th>
                                                  <th>出生日期</th>
                                                  <th>婚姻状况</th>
                                                  <th>电子简历</th>
                                              	</tr>
                                              </thead>
                                              <tbody>
                                              </tbody>
                                          </table>
                                        </div>
                                    </div>
                                    <div id="job" class="tab-pane">
                                        <div class="panel-body">
                                           <table id="postPersonUpdateInfo" class="table table-striped table-condensed table-hover table-bordered dataTable" width="100%">
                                              <thead>
                                              	<tr>
                                                  	<th>序号</th>
												   	<th>工号</th>
												   	<th>姓名</th>
													<th>证件号码</th>
													<th>部门名称</th>
													<th>岗位名称</th>
													<th>员工状态</th>
													<th>入职时间</th>
													<th>异动类型</th>
													<th>转正时间</th>
													<th>用工性质</th>
													<th>职级</th>
													<th>是否关键岗位</th>
													<th>汇报对象</th>
													<th>工作地</th>
													<th>发薪地</th>
													<th>社保缴纳地</th>
													<th>停发标志</th>
													<th>团队</th>
                                              	</tr>
                                              </thead>
                                              <tbody>
                                              </tbody>
                                          </table>
                                        </div>
                                    </div>
                                 </div>
                              </div>    
                              <!-- 错误 -->
                               <div class="error tab-pane" id="error">
                                <ul class="nav nav-tabs">
                                      <li class="active"><a data-action="personal" href="#person" data-toggle="tab">个人信息</a></li>
                                      <li class=""><a data-action="job" href="#jobs" data-toggle="tab">工作信息</a></li>
                                 </ul>
                                 <div class="tab-content">
                                     <div id="person" class="tab-pane active">
                                        <div class="panel-body">
                                           <table id="baseErrorPersonUpdateDet" class="table table-striped table-responsive table-condensed table-hover table-bordered dataTable" width="100%">
                                              <thead>
                                              	<tr>
                                                  <th>序号</th>
												  <th>工号</th>
												  <th>错误信息</th>
												  <th>姓名</th>
												  <th>性别</th>
												  <th>是否外籍</th>
												  <th>参加工作时间</th>
												  <th>证件类型</th>
												  <th>证件号码</th>
												  <th>开户行</th>
												  <th>开户支行</th>
												  <th>工资卡号</th>
												  <th>最高学历</th>
												  <th>最高学位</th>
												  <th>是否海外留学</th>
												  <th>户口所在地</th>
										          <th>户口类型</th>
												  <th>籍贯</th>
												  <th>民族</th>
												  <th>手机</th>
                                                  <th>联系电话</th>
                                                  <th>电子邮件</th>
                                                  <th>现居住地</th>
                                                  <th>家庭住址</th>
                                                  <th>邮编</th>
                                                  <th>档案存放处</th>
                                                  <th>出生日期</th>
                                                  <th>婚姻状况</th>
                                                  <th>电子简历</th>
                                              	</tr>
                                              </thead>
                                              <tbody>
                                              </tbody>
                                          </table>
                                        </div>
                                    </div>
                                    <div id="jobs" class="tab-pane">
                                        <div class="panel-body">
                                           <table id="postErrorPersonUpdateDet" class="table table-striped table-condensed table-hover table-bordered dataTable" width="100%">
                                              <thead>
                                              	<tr>
                                                  	<th>序号</th>
												   	<th>工号</th>
												   	<th>错误信息</th>
												   	<th>姓名</th>
													<th>证件号码</th>
													<th>部门名称</th>
													<th>岗位名称</th>
													<th>员工状态</th>
													<th>入职时间</th>
													<th>异动类型</th>
													<th>转正时间</th>
													<th>用工性质</th>
													<th>职级</th>
													<th>是否关键岗位</th>
													<th>汇报对象</th>
													<th>工作地</th>
													<th>发薪地</th>
													<th>社保缴纳地</th>
													<th>停发标志</th>
													<th>团队</th>
                                              	</tr>
                                              </thead>
                                              <tbody>
                                              </tbody>
                                          </table>
                                        </div>
                                    </div>
                                 </div>
                              </div>
                            </div>
                        </div> 
                         <div class="buttons text-center">
                            <a href="employees/empBatchUpdateUpload?type=update" class="btn btn-default m-r">上一步</a>
                            <button class="btn btn-info" id="empBatchUpdateImport">开始导入</button>
                        </div>
                        </div>
                          <!-- 预览结束 -->
                    </div>
                   
                </div>
            </div>
        </div>
        <!-- 上传模板 end -->
    <!-- 放页脚  开始-->
    <jsp:include flush="true" page="../common/foot.div.jsp"></jsp:include>
    <!-- 放页脚  结束-->
</div>
<!-- Vendor scripts -->
<script src="static/bootstrap/vendor/jquery/dist/jquery.min.js"></script>
<script src="static/bootstrap/vendor/jquery-ui/jquery-ui.min.js"></script>
<script src="static/bootstrap/vendor/slimScroll/jquery.slimscroll.min.js"></script>
<script src="static/bootstrap/vendor/bootstrap/dist/js/bootstrap.min.js"></script>
<script src="static/bootstrap/vendor/metisMenu/dist/metisMenu.min.js"></script>
<script src="static/bootstrap/vendor/iCheck/icheck.min.js"></script>
<script src="static/bootstrap/vendor/sparkline/index.js"></script>
<script src="static/bootstrap/vendor/nestable/jquery.nestable.js"></script>
<script src="static/bootstrap/vendor/moment/moment.js"></script>
<script src="static/bootstrap/scripts/homer.js"></script>
<script src="static/bootstrap/vendor/datatables/media/js/jquery.dataTables.min.js"></script>
<script src="static/bootstrap/vendor/datatables_plugins/integration/bootstrap/3/dataTables.bootstrap.min.js"></script>

<!-- alert -->
<script src="static/bootstrap/vendor/sweetalert/lib/sweet-alert.min.js"></script>
<script src="static/bootstrap/vendor/toastr/build/toastr.min.js"></script>
<!-- 表单验证 -->
<script src="static/js/plugins/form.validation/js/formValidation.js"></script>
<script src="static/js/plugins/form.validation/js/framework/bootstrap.js"></script>
<script src="static/js/plugins/form.validation/js/language/zh_CN.js"></script> 
<script src="static/js/empBatchUpdateUpload.js"></script>
</body>
</html>
