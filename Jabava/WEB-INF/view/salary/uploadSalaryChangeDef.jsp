<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<base href="${pageContext.request.contextPath}/">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge">

<!-- Page title -->
<title>工资变动表定义上传</title>
<jsp:include flush="true" page="../common/styles.jsp"></jsp:include>
<!--for 临时改变-->
<link rel="stylesheet" href="static/css/user.css">

</head>
<body>

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

        <!-- 放主要内容 -->
        <div class="content animate-panel">
            <div class="row">
                <div class="col-lg-12">
                    <div class="hpanel clearfix">
                        <div class="panel-heading">
                            <h4 class="text-center font-bold">
                                上传定义
                            </h4>
                        </div>
                        <div class="panel-body">
                            <div class="col-md-5 col-md-offset-3">
                                <form id="uploadForm" class="form-horizontal">
                                    <div class="form-group">
                                        <label for="name" class="col-sm-3 col-md-3 col-lg-3 control-label font-bold">名称：</label>
                                        <div class="col-sm-6 col-md-6 col-lg-6 ">
                                            <input type="text" class="form-control" id="name" name="name">
                                        </div>
                                        <label class="checkbox-inline form-control-static col-sm-3 col-md-3 col-lg-3">
                                            <input type="checkbox" id="cover" name="cover" value="1">
                                            同名覆盖                                     
                                        </label>
                                    </div>
                                   
                                    <div class="form-group">
                                        <label for="keyInfo" class="col-sm-3 col-md-3 col-lg-3 control-label font-bold">主键列：</label>
                                        <div class="col-sm-9 col-md-9 col-lg-9 ">
                                            <input type="text" class="form-control" id="keyInfo" name="keyInfo">
                                        </div>
                                        
                                    </div>
                                    <div class="form-group">
                                        <label for="keyType" class="col-sm-3 col-md-3 col-lg-3 control-label font-bold">主键类型：</label>
                                        <div class="col-sm-9 col-md-9 col-lg-9 ">
                                            <select class="form-control" id="keyType" name="keyType" data-field="component">
												<option value="0">--------</option>
												<option value="1">身份证</option>
												<option value="2">工号</option>
											</select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="description" class="col-sm-3 col-md-3 col-lg-3 control-label font-bold">描述：</label>
                                        <div class="col-sm-9 col-md-9 col-lg-9">
                                            <textarea class="form-control" id="description" name="description"></textarea>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="" class="control-label col-sm-3 col-md-5 col-lg-3 ">上传模板：</label>
                                        <div class="col-sm-9 col-md-7 col-lg-9 ">
                                            <div class="input-group" data-toggle="upload:file">
                                                <input type="text" class="form-control" readonly="readonly">
                                                <input type="hidden" id="filePath" name="filePath">
                                                <div class="input-group-btn">
                                                    <span class="btn btn-default">浏览...
                                                    <input type="file" class="sr-only" id='uploadFile' name='uploadFile'  accept=".xlsx" ></span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-6 col-md-6 col-lg-6 col-lg-offset-3">
                                            <button id="upload" class="btn btn-block btn-success" type="button">上　传</button>
                                        </div>
                                        <div class="col-sm-3 col-md-3 col-lg-3">
                                            <button id="cancel" class="btn btn-block btn-default" type="button">取　消</button>
                                        </div>
                                    </div>
                                
                                </form>
                            </div>
                        </div>                        
                    </div>
                </div>
            </div>
        </div> 
              
        <!--主要内容结束-->

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
    
    
    <!-- for datatable -->
    <script src="static/bootstrap/vendor/datatables/media/js/jquery.dataTables.min.js"></script>
    <script src="static/bootstrap/vendor/datatables_plugins/integration/bootstrap/3/dataTables.bootstrap.min.js"></script>


    <!-- alert -->
    <script src="static/bootstrap/vendor/sweetalert/lib/sweet-alert.min.js"></script>
    
    <!-- App scripts -->
    <script src="static/bootstrap/scripts/homer.js"></script>
    <script src="static/bootstrap/scripts/charts.js"></script>
    
    
    <!--jabava-->
    <script src="static/js/common/ajaxfileupload.js"></script>

<script>
	$('#uploadForm')[0].reset();

	$('[data-toggle="upload:file"] :file').on('change.file:selected', function(event){
	    var oEventTarget = $(this);
	    var oFile = $(this).val();
	    var fileExt = oFile.substr(oFile.lastIndexOf(".")).toLowerCase();
	
	    if(fileExt!='.xlsx'){
	        alert("请上传后缀名为xlsx的文件!");
	        return;
	    }
	
	    oEventTarget.parents('[data-toggle="upload:file"]').find(':text').val( oFile );
	})
	
	$('[data-toggle="upload:file"]').on('click', function(event){
	    $(this).find(':file').trigger('click.file:selected');
	});

	$('#upload').on('click', function() {
		if(!validateForm()){
			return false;
		}
		
    	$.ajaxFileUpload({
            url: 'upload/uploadFile',		//用于文件上传的服务器端请求地址
            secureuri: false, 				//是否需要安全协议，一般设置为false
            fileElementId: ['uploadFile'],	//文件上传域的ID
            async:false,
            dataType:'text',
            type:"POST",
            success: function (result){		//服务器成功响应处理函数
            	if(!result.success){
            		alert(result.msg);
            		return false;
            	}
            
            	$("#filePath").val(result.filePath);
            	
            	$.ajax({
     				url : "salaryChangeDef/uploadSalaryChangeDef",
     				data : $("#uploadForm").serialize(),
     				async : false,
     				dataType:'json',
     				type : 'post',
     				success : function(message) {
     					if(message.success){
     						swal({
    		                    title: "上传成功!",
    		                    text: "",
    		                    type: "success",
    		    				confirmButtonText: "确定"
    		                });
     						window.location.href="salaryChangeDef/toListSalaryChangeDef";
     					}else{
     						swal({
    		                    title: "上传失败!",
    		                    text: message.msg,
    		                    type: "error",
    		    				confirmButtonText: "确定"
    		                });
     					}
     				}
     			});
            }
     	});
   	});
		
	$('#cancel').on('click', function() {
		history.back(-1);
	});
	
	function validateForm(){
		if($('#name').val() == ''){
			alert("请输入名称");
			return false;
		}
		if($('#keyInfo').val() == ''){
			alert("请输入主键列");
			return false;
		}
		//if($('#keyType option:selected').text() == ''){
		if($('#keyType').val() == '0'){
			alert("请选择主键类型");
			return false;
		}
		
		var fileName = $('[data-toggle="upload:file"] :file').val();
		if(!fileName){
			alert('请选择文件');
			return false;
		}
		var photoExt=fileName.substr(fileName.lastIndexOf(".")).toLowerCase();//获得文件后缀名
	    if(photoExt!='.xlsx'){
	        alert("请上传后缀名为xlsx的文件!");
	        return false;
	    }
		
		return true;
	}
</script>

</body>
</html>