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
<title>Jabava上传增减表界面</title>
<jsp:include flush="true" page="../common/styles.jsp"></jsp:include>
<!--for 临时改变-->
<link rel="stylesheet" href="static/css/user.css">

<script>
 
  function downloadFile(path) {
		window.open(path);
	}
</script>
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
                    	<div class="panel-heading hbuilt jabava-built p-lg">
                    		<h4 class="m-n font-bold">上传增减表
                    		<p class="small font-light m-t-md">企业可下载增减变表模版，将该月需要或不需要缴纳社保人员信息以及需要更改社保人员信息等整理到模版中，上传到HRO客户端，无需联系客服操作。</p>
                    		</h4>
                    	</div>
                        <div class="panel-body">
                            <div class="col-md-4 col-md-offset-3">
                            	<form class="form-horizontal">
                            		<div class="form-group">
								        <label for="" class="control-label col-lg-3">上传增减表：</label>
								        <div class="col-lg-9">
								        	<div class="input-group" data-toggle="upload:file">
								        	    <input type="text" class="form-control" readonly="readonly">
								        	    <div class="input-group-btn">
								        	        <span class="btn btn-default">浏览...
								        	        <input type="file" class="sr-only"  id='myfiles' name='myfiles'  accept=".xlsx" ></span>
								        	    </div>
								        	</div>
								        </div>
								    </div>
                            		<div class="form-group">
                            			<label for="inputPassword3" class="col-lg-3 control-label font-bold">备注：</label>
                            			<div class="col-lg-9">
                            				<textarea type="text" class="form-control" id="addRemark" name="remark" ></textarea>
                            			</div>
                            		</div>
                            		<div class="form-group">
                            			<div class="col-lg-9 col-lg-offset-3">
                            				<button id="upload" class="btn btn-block btn-success demo2" type="button">上　传</button>
                            			</div>
                            		</div>
                            		<div class="form-group">
	                            		<div class="col-lg-9 col-lg-offset-3 text-right">
                            				<a class="btn btn-link inc_dowmload" onclick="downloadFile('static/xls/template.xlsx');">下载增减变模板</a>
                            				<!-- <a class="btn btn-link inc_dowmload" onclick="downloadFile('static/xls/momey_template.xlsx');">下载薪酬模板</a> -->
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
    <script src="static/js/change/ajaxfileupload.js"></script>
    <script src="static/js/change/change.js"></script>

<script>
function getFileType(obj){
	
    photoExt=obj.value.substr(obj.value.lastIndexOf(".")).toLowerCase();//获得文件后缀名	
    if(photoExt!='.xlsx'){
        alert("请上传后缀名为xlsx的文件!");
        return false;
    }
   
}
$(function (){
	 $.ajax({
			type : 'post',
			url : "change/getProtocolCode",			
			async : false,
			success : function(result) {
				if(result!='error'){					
	            	$("#showProcotolCode").val(result);	
				}else{
					alert('该公司没有协议号!');
					//返回到上一页
					window.location.href=document.referrer;
				}
				
			}
		}); 

		 $('#upload').on('click', function() {  
			 
			var fileName = $('[data-toggle="upload:file"] :file').val();
			
			if(fileName==''){
				alert('请选择文件');
				return false;
			}
			 photoExt=fileName.substr(fileName.lastIndexOf(".")).toLowerCase();//获得文件后缀名	
			    if(photoExt!='.xlsx'){
			        alert("请上传后缀名为xlsx的文件!");
			        return false;
			    }
		    	  $.ajaxFileUpload({
		            url: 'change/uploadFile', //用于文件上传的服务器端请求地址
		            secureuri: false, //是否需要安全协议，一般设置为false
		            fileElementId: ['myfiles'], //文件上传域的ID    
		            async:false,
		            dataType:'text',
		            type:"POST",
		            success: function (result){  //服务器成功响应处理函数
		            	
		            	$("#fileUrl").val(result.fileUrl);			            	
		            	 $.ajax({
		     				type : 'post',
		     				url : "change/addChange",
		     				data : $("#uploadForm").serialize(),
		     				async : false,
		     				dataType:'json',
		     				success : function(message) {
		     				 
		     					if(message.message=='success'){
		     						
		     						swal({
		    		                    title: "恭喜您上传成功!",
		    		                    text: "",
		    		                    type: "success",
		    		    				confirmButtonText: "确定"
		    		                });
		     						window.location.href="client/list_query_change";

		     					}else{
		     						swal({
		    		                    title: "上传失败!",
		    		                    text: message.message,
		    		                    type: "error",
		    		    				confirmButtonText: "确定"
		    		                });
		     						
		     					}
		     					
		     				}
		     			});           		
		            } 
	      		}); 
	    	});
})

$('[data-toggle="upload:file"] :file').on('change.file:selected', function(event){
    var oEventTarget = $(this),
    	oFile = $(this).val()
    	fileExt = oFile.substr(oFile.lastIndexOf(".")).toLowerCase()

    if(fileExt!='.xlsx'){
        alert("请上传后缀名为xlsx的文件!");
        return;
    }

    oEventTarget.parents('[data-toggle="upload:file"]')
    .find(':text').val( oFile )
})

$('[data-toggle="upload:file"]').on('click', function(event){
    $(this).find(':file').trigger('click.file:selected')
});

</script>

</body>
</html>