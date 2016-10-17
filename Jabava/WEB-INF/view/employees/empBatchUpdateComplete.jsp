<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import="com.jabava.utils.RequestUtil" %>
<!DOCTYPE html>
<html>
<head>
	<base href="${pageContext.request.contextPath}/">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- Page title -->
    <title>批量编辑信息完成</title>
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
                                  <button type="button" class="btn btn-default btn-circle btn-lg">2</button>
                                  <p class="m-t-sm">上传预览</p>
                               </div>
                               <div class="complete col-sm-4 col-md-4 col-lg-4">
                                  <button type="button" class="btn btn-info btn-circle btn-lg active">3</button>
                                  <p class="m-t-sm">上传完成</p>
                               </div>
                           </div>
                       </div>
                       <div class="uploadPreview clearfix"> </div>                                               
                          <!-- 完成 -->
                          <div class="text-center clearfix m-t-sm empBatchUpdateComplete">
                             <p class="m-t-lg">已成功修改数据：${total}条</p>
                             <a href="employee/employeeList" class="btn btn-info">返回花名册</a>
                          </div>
                          <!-- 完成 结束 -->
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
<!-- <script src="static/js/empBatchUpdateUpload.js"></script>
 --></body>
</html>
