<%@ page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html>
<head>
	<base href="${pageContext.request.contextPath}/">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0;" name="viewport"/>  
    <meta content="yes" name="apple-mobile-web-app-capable" />  
    <meta content="black" name="apple-mobile-web-app-status-bar-style" />  
    <meta content="telephone=no" name="format-detection" /> 

    <!-- Page title -->
    <title>上传附件界面</title>
    <jsp:include flush="true" page="../common/styles.jsp"></jsp:include>
    <!--for 临时改变-->
	<link rel="stylesheet" id="link" href="static/css/denglu.css" type="text/css">
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

<!--<div class="color-line"></div>-->
<!-- <div class="back-link">
    <a href="index.html" class="btn btn-primary">Back to Dashboard</a>
</div> -->
<div class="register-container containers">
    <div class="row">
        <div class="col-md-12">
            <div class="hpanel">
                <div class="panel-body">
                    <div class="row">
                    <!--头部-->
                        <div class="tuichu">
                        	<h5><a href="denglu.html"><img src="img/u4.png" width="20" height="20"></a><span>退出</span></h5>
                        </div>
                     <!--个人信息-->  
                     <div class="inform">
                             <div class="row">
                            <!--上传附件-->
                 		<div class="panel-body fujian" style=" background:none;">
                              <form role="form" class="form-horizontal formclass"> 
                              <!--身份证--> 
                                   <div class="form-group searchs-unit displays">
                                    <label for="exampleInputName3" class="col-lg-3 ">身&nbsp;&nbsp;份&nbsp;&nbsp;证：</label>
                                    <div class="input-group col-lg-3 displays" style="display:inline">
                                     <input type="file" class="displays" style="display:inline; width:50%;">
                                    </div>
                                    </div>
                                     <!--户口簿--> 
                                   <div class="form-group searchs-unit displays">
                                    <label for="exampleInputName3" class="col-lg-3">户&nbsp;&nbsp;口&nbsp;&nbsp;簿：</label>
                                    <div class="input-group col-lg-3 displays" style="display:inline">
                                     <input type="file" class="displays" style="display:inline; width:50%;">
                                    </div>
                                    </div>
                                   <!--学历证--> 
                                  <div class="form-group searchs-unit displays">
                                    <label for="exampleInputName3" class="col-lg-3">学&nbsp;&nbsp;历&nbsp;&nbsp;证：</label>
                                    <div class="input-group col-lg-3 displays" style="display:inline">
                                     <input type="file" class="displays" style="display:inline; width:50%;">
                                    </div>
                                    </div>
                                     <!--简历--> 
                                   <div class="form-group searchs-unit displays">
                                    <label for="exampleInputName3" class="col-lg-3">简&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;历：</label>
                                    <div class="input-group col-lg-3 displays" style="display:inline">
                                     <input type="file" class="displays" style="display:inline; width:50%;">
                                    </div>
                                    </div>
                                    <!--离职证明--> 
                                   <div class="form-group searchs-unit displays">
                                    <label for="exampleInputName3" class="col-lg-3 ">离职证明：</label>
                                    <div class="input-group col-lg-3 displays" style="display:inline">
                                     <input type="file" class="displays" style="display:inline; width:50%;">
                                    </div>
                                    </div>
                                   <!--健康证明--> 
                                   <div class="form-group searchs-unit displays">
                                    <label for="exampleInputName3" class="col-lg-3 ">健康证明：</label>
                                    <div class="input-group col-lg-3 displays" style="display:inline">
                                     <input type="file" class="displays" style="display:inline; width:50%;">
                                    </div>
                                    </div>
                                 <!--薪资证明--> 
                                  <div class="form-group searchs-unit displays">
                                    <label for="exampleInputName3" class="col-lg-3">薪资证明：</label>
                                    <div class="input-group col-lg-3 displays" style="display:inline">
                                     <input type="file" class="displays" style="display:inline; width:50%;">
                                    </div>
                                    </div> 
                               <!--保存-->
                               <center style="clear:both; padding-top:30px;">
                               		<button type="button" class="btn btn-info">保存并填写下一项</button>
                               </center>
                              </form>
                             
                              </div>
                     <!--上传附件 end--> 
                            </div>   
              
                <!--上传附件弹框 end-->
                     </div>
                     <!--inform end--> 
                    </div>
                </div>
            </div>
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
	<script src="static/bootstrap/vendor/xeditable/bootstrap3-editable/js/bootstrap-editable.min.js"></script>
    <script src="static/bootstrap/vendor/select2-3.5.2/select2.min.js"></script>
    <script src="static/bootstrap/vendor/bootstrap-touchspin/dist/jquery.bootstrap-touchspin.min.js"></script>
    <script src="static/bootstrap/vendor/bootstrap-datepicker-master/dist/js/bootstrap-datepicker.min.js"></script>
	<!-- for editable -->
	<script src="static/bootstrap/vendor/moment/moment.js"></script>
	 
	<!-- for datatable -->
	<script src="static/bootstrap/vendor/datatables/media/js/jquery.dataTables.min.js"></script>
	<script src="static/bootstrap/vendor/datatables_plugins/integration/bootstrap/3/dataTables.bootstrap.min.js"></script>
	<!-- alert -->
	<script src="static/bootstrap/vendor/sweetalert/lib/sweet-alert.min.js"></script>

<!-- App scripts -->
	<script src="static/bootstrap/scripts/homer.js"></script>
    <script src="static/bootstrap/scripts/charts.js"></script>

    <script type="text/javascript">  
    <!--  
    //平台、设备和操作系统  
    var system ={  
        win : false,  
        mac : false,  
        xll : false  
    };  
    //检测平台  
    var p = navigator.platform;
	/*alert(p);*/
    system.win = p.indexOf("Win") == 0;  
    system.mac = p.indexOf("Mac") == 0;  
    system.x11 = (p == "X11") || (p.indexOf("Linux") == 0);  
    //跳转语句  
    if(system.win||system.mac||system.xll){//转向后台登陆页面
	/*alert(1);*/
		$("#link").attr("href","static/css/denglu.css");
	  
    }else{  
	/*alert(2);*/
	 $("#link").attr("href","static/css/dengluiphone.css");
    }  
   
</script> 
</body>
</html>