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
    <title>批量编辑信息上传</title>
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
                                  <button type="button" class="btn btn-info btn-circle btn-lg active">1</button>
                                  <p class="m-t-sm">上传模板</p>
                               </div>
                               <div class="col-sm-4 col-md-4 col-lg-4">
                                  <button type="button" class="btn btn-default btn-circle btn-lg">2</button>
                                  <p class="m-t-sm">上传预览</p>
                               </div>
                               <div class="complete col-sm-4 col-md-4 col-lg-4">
                                  <button type="button" class="btn btn-default btn-circle btn-lg">3</button>
                                  <p class="m-t-sm">上传完成</p>
                               </div>
                           </div>
                       </div>
                       <div class="uploadPreview clearfix"> </div>
                       <!-- 模板 -->
                          <div class="clearfix m-t-sm empBatchUpdateUpload">
                              <h5 class="text-info m-t">步骤一：下载模板</h5>
                                <ul class="m-l m-t-sm">
                                    <li> 1、批量更新员工信息用于系统中已存在的员工，通过导入批量修改员工【个人信息】和【工作信息】；</li>
                                    <li> 2、excel表中为空的字段默认为不更新；</li>
                                    <li> 3、excel表中不为空的字段，按照相应内容更新对应字段数据；</li>
                                    <li> 4、导入时根据“姓名+身份证号码”判断联合唯一性；</li>
                                    <li> 5、系统不存在的员工，请通过新增员工导入；</li>
                                    <li>
                                        <a href="static/xls/base_info_person_template.xlsx" type="button" class="btn btn-info">
                                            <i class="pe pe-7s-bottom-arrow pe-lg"></i>
                                            <span>下载模板</sapn>
                                        </a>
                                    </li>
                                </ul>
                             <h5 class="text-info m-t">步骤二：上传文件</h5>
                               <div class="panels m-l">
                                  <span class="file-input">
                                    <div class="btn btn-info btn-md btn-file  m-t" data-toggle="upload:file">
                                      <i class="pe pe-7s-up-arrow pe-lg"></i>
                                       上传文件
                                      <input id="file" type="file" data-action="file">
                                    </div>
                                  </span>
                                </div>
                          </div>                              
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
<!-- 表单验证 
<script src="static/js/plugins/form.validation/js/formValidation.js"></script>
<script src="static/js/plugins/form.validation/js/framework/bootstrap.js"></script>
<script src="static/js/plugins/form.validation/js/language/zh_CN.js"></script> 
<!--  <script src="static/js/empBatchUpdateUpload.js"></script>-->
 <script type="text/javascript">
 
 $(function (){
	 var $uploadCompent = $('[data-toggle="upload:file"]'),
	    $fileInput = $('[data-action="file"]');

	    var fileUpload = function(){
	        $fileInput.on('change.file:selected', function(e){
	        	var uploadFile=$fileInput[0].files[0];
	        	
	        	if(uploadFile.name!=null && uploadFile.name!=''){
	        		var postfix=uploadFile.name.split('.')[1];
	        		if(postfix!='xlsx' && postfix!='xls' ){
	        			 swal({
	                         title: '上传失败',
	                         text: "上传的文件类型有误,请重新上传",
	                         type: 'error'
	                     });
	        		}else{
	        			var formData=new FormData();
	        	        formData.append("uploadFiles", uploadFile);
	        	        formData.append("fileName",uploadFile.name);
	        	        formData.append("type","update");
	        	    	$.ajax({
	        	    		url:  "employee/volidInsertImport",
	        	    		type: 'POST',
	        	    		cache: false,
	        	    		dataType: 'json',
	        	    		data: formData,
	        	    		processData: false,
	        	    		contentType: false
	        	    	}).done(function(result){
	        	    		if(result.success){
	        	    			window.location.href="employee/empUpdatePreview?type=update";
	        	    		}else{
	        	    			 swal({
	                                 title: '上传失败',
	                                 text: result.msg,
	                                 type: 'error'
	                             });
	        	    		}
	    			}).fail(function(data){
	    				 swal({
	                         title: '上传失败',
	                         text: "文件上传有误!",
	                         type: 'error'
	                     });
	    				
	    			});  
	        			
	        	 }
	        	}else{
	        		 swal({
	                     title: '上传失败',
	                     text: "上传的文件类型有误,请重新上传",
	                     type: 'error'
	                 });
	        	}
	 
	        });
	    };
	    
	    $uploadCompent.on('click', function(e){
	    	//$fileInput.trigger('click.file:selected');
	    	fileUpload();
	    });
	 
 });
 
 </script>
</body>
</html>
