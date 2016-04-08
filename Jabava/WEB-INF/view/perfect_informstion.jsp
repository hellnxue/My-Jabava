<%@ page contentType="text/html; charset=utf-8" %>
 
<!DOCTYPE html>
<html>
<head>
 
	<base href="${pageContext.request.contextPath}/">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <!-- Page title -->
    <title>Jabava V1.0 | 完善信息</title>

    <!-- Place favicon.ico and apple-touch-icon.png in the root directory -->
    <!--<link rel="shortcut icon" type="image/ico" href="favicon.ico" />-->

    <!-- Vendor styles -->
    <link rel="stylesheet" href="static/bootstrap/vendor/fontawesome/css/font-awesome.css" />
    <link rel="stylesheet" href="static/bootstrap/vendor/metisMenu/dist/metisMenu.css" />
    <link rel="stylesheet" href="static/bootstrap/vendor/animate.css/animate.css" />
    <link rel="stylesheet" href="static/bootstrap/vendor/bootstrap/dist/css/bootstrap.css" />
    
    <!-- for data table -->
    <link rel="stylesheet" href="static/bootstrap/vendor/datatables_plugins/integration/bootstrap/3/dataTables.bootstrap.css" />
    
    <!-- for alert -->
    <link rel="stylesheet" href="static/bootstrap/vendor/sweetalert/lib/sweet-alert.css" />
    
    <!-- App styles -->
    <link rel="stylesheet" href="static/bootstrap/fonts/pe-icon-7-stroke/css/pe-icon-7-stroke.css" />
    <link rel="stylesheet" href="static/bootstrap/fonts/pe-icon-7-stroke/css/helper.css" />
    <link rel="stylesheet" href="static/bootstrap/styles/static_custom.css">
    <link rel="stylesheet" href="static/bootstrap/styles/style.css">
    
    <!--for 临时改变-->
	<link rel="stylesheet" href="static/css/user.css">

</head>
<body class="blank">

<!-- Simple splash screen-->
<div class="splash"> 
 <div class="color-line"></div><div class="splash-title">
<h1>Jabava V1.0 </h1>
<p>属于你的专业人事专员</p>
<img src="images/loading-bars.svg" width="64" height="64" /> </div> </div>

<!--[if lt IE 7]>
<p class="alert alert-danger">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
<![endif]-->

<div class="color-line"></div>
<!-- <div class="back-link">
    <a href="index.html" class="btn btn-primary">Back to Dashboard</a>
</div> -->
<div class="register-container">
    <div class="row">
        <div class="col-md-12">
            <div class="text-center m-b-md">
                <h3>完善企业信息</h3>
            </div>
            <div class="hpanel">
                <div class="panel-body">
                	<form action="perfect" id="loginForm" method="POST" class="formclass" enctype="multipart/form-data">
                		<input id="jump" type="hidden" name="jump" value="0">
                        <div class="col-sm-12 perfect_sm_margin">
                            <div class="row">
                                <div class="form-group">
                                    <label class="col-lg-1">&nbsp;</label>
                                    <label class="col-lg-2">公司名称</label>
                                    <div class="col-lg-7">
                                    <input type="hidden"  id="companyId" name="companyId" value="${company.companyId}"/>
                                    <input type="hidden"  id="userId" name="userId" value="${userId}"/>
                                    <input type="hidden"  id="password" name="password" value="${password}"/>
                                        <input type="text" value="${company.companyName}" id="companyName" class="form-control" name="companyName" placeholder="请输入公司名称">
                                    </div>
                                    <label class="col-lg-2">&nbsp;</label>
                                </div>
                            
                                <div class="form-group">
                                    <label class="col-lg-1">&nbsp;</label>
                                    <label class="col-lg-2">公司logo</label>
                                    <input id="lefile_two" name="lefile_two" class="perfect_input2_large" type="file" onchange="preivew(this);" />
                                   
                                    <div class="input-append">
                                        <div class="col-lg-2">
                                         <img id="uploadimage" height="80" width="80" src=""  alt=""/>
<!--                                         <input id="photoCover_two" class="input-large perfect_input_large perfect_input_large_two" type="text"> -->
                                        </div>
                                    </div>
                                    
                                    <label class="col-lg-7 perfect_label_large perfect_label_large_two"><a class="btn perfect_a_large" onclick="$('input[id=lefile_two]').click();">选择上传文件</a>
                                    	<br>
                                    	<span>仅支持jpg/gif/png等常见图片类型，且大小不大于2M</span>
                                    </label>
                                </div>
                            </div>
                        </div>
                        
                        <div class="col-sm-12">
                            <div class="row perfect_row">
                                <div class="form-group">
                                    <label class="col-lg-1">&nbsp;</label>
                                    <label class="col-lg-2">营业执照</label>
                                    <div class="col-lg-6">
                                        <input type="text" value="" id="license" class="form-control" name="license" placeholder="请输入营业执照号码">
                                    </div>
                                    <label class="col-lg-3">&nbsp;</label>
                                </div>
                                
                                <div class="form-group">
                                    <label class="col-lg-1">&nbsp;</label>
                                    <label class="col-lg-2">上传营业执照</label>
                                    <input id="lefile" name="lefile" class="perfect_input2_large" type="file" >
                                    <div class="input-append">
                                        <div class="col-lg-6">
                                        <input id="photoCover" value="" class="input-large perfect_input_large" type="text">
                                        </div>
                                    </div>
                                    <label class="col-lg-3 perfect_label_large"><a class="btn perfect_a_large" onclick="$('input[id=lefile]').click();">选择上传文件</a></label>
                                </div>
                                
                                <div class="form-group">
                                    <label class="col-lg-1">&nbsp;</label>
                                    <label class="col-lg-2">所在城市</label>
                                    <span id="all_city">
	                                <div class="col-lg-2">
	                                 	<select id="companyProv" name="companyProv" class="prov form-control" ></select> 
	                                </div>
	                                <div class="col-lg-2">
	                                	<select id="companyCity" name="companyCity"  class="city form-control"  ></select>
	                                </div>	
	                                <div class="col-lg-2">
	                                	<select id="companyDist" name="companyDist"   class="dist form-control"    ></select>
	                                </div>
                                </span>
                                    <label class="col-lg-3">&nbsp;</label>
                                </div>
                                
                                <div class="form-group">
                                    <label class="col-lg-1">&nbsp;</label>
                                    <label class="col-lg-2">公司地址</label>
                                    <div class="col-lg-6">
                                        <input type="" name="companyAddress"  id="companyAddress" class="form-control" name="companyAddress" placeholder="请输入公司地址">
                                    </div>
                                    <label class="col-lg-3">&nbsp;</label>
                                </div>
                                
                                <div class="form-group">
                                    <label class="col-lg-1">&nbsp;</label>
                                    <label class="col-lg-2">邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;编</label>
                                    <div class="col-lg-6">
                                        <input type="" name="companyPost"  id="companyPost" class="form-control" name="companyPost" placeholder="200001">
                                    </div>
                                    
                                    <label class="col-lg-3">&nbsp;</label>
                                </div>
                                
                                <div class="form-group">
                                    <label class="col-lg-1">&nbsp;</label>
                                    <label class="col-lg-2">网&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;址</label>
                                    <div class="col-lg-6">
                                        <input type="" name="companyNet" id="companyNet" class="form-control" name="companyNet" placeholder="">
                                    </div>
                                    
                                    <label class="col-lg-3">&nbsp;</label>
                                </div>
                            </div>
                             
                            <center>
                                <a href="#"><button class="btn btn-success demo2" type="submit">确 认</button></a><!--连接到首页-->
                                &nbsp;
                                <a href="#"><button class="btn btn-success" type="button" onclick="jumpIndex();return false;">跳 过</button></a><!--连接到首页-->
                            <center>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12 text-center">
            <strong>Jabava</strong> - 您的专业HR <br/> 2015 Copyright 智阳网络技术（上海）有限公司
       </div>
    </div>
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

	<script type="text/javascript" src="static/js/jquery.cityselect.js"></script>

<script>

function preivew(container){
    var path = getPath(container);
  	document.getElementById("uploadimage").src = path; 
  	document.getElementById("uploadimage").value = path;
  }
     
function getPath(obj) { 
    if (window.navigator.userAgent.indexOf("MSIE")>=1) {    
      obj.select();
      return document.selection.createRange().text;    
    }    
    else if(window.navigator.userAgent.indexOf("Firefox")>=1){    
     	return window.URL.createObjectURL(obj.files[0])
    }    
    return obj.value;   
}  

function jumpIndex(){
	$('#jump').val("1");
	$("#loginForm").attr("action", "to_index");
	$('#loginForm').submit();
}

$(function (){
	
	$('.demo2').click(function(){
		 
		$('#loginForm').submit();
		 
		 /* swal({
             title: "恭喜您注册成功，您的企业号为0001",
             text: "将用于系统登录，请您记住哦！",
             type: "success",
				confirmButtonText: "我记住了"
         }); */
    });
	 
	$("#all_city").citySelect({
		prov: "${company.companyProv}",
		city: "${company.companyCity}",
		dist: "${company.companyDist}",
		nodata:"none",
		required:false
	}); 

	
})
</script>

<!--上传图片-->
<script type="text/javascript">
$('input[id=lefile_two]').change(function() {
$('#photoCover_two').val($(this).val());
});
</script>

<!--上传文件-->
<script type="text/javascript">
$('input[id=lefile]').change(function() {
$('#photoCover').val($(this).val());
});
</script>

</body>
</html>