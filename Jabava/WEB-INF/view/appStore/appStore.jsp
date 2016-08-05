<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<base href="${pageContext.request.contextPath}/">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- Page title -->
    <title>人员信息</title>
    <jsp:include flush="true" page="../common/styles.jsp"></jsp:include>
    <link rel="stylesheet" href="static/js/plugins/form.validation/css/formValidation.css">
    <link rel="stylesheet" href="static/bootstrap/vendor/blueimp-gallery/css/blueimp-gallery.min.css" />
    <link rel="stylesheet" href="static/css/user_bata.css">
	<link rel="stylesheet" href="static/css/style_1.css" media="screen">
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
	<!-- 这里临时隐藏掉了 -->
	<div class="normalheader transition animated fadeIn small-header">
		<div class="hpanel">
			<div class="panel-body">
				<div id="hbreadcrumb" class="m-t-xs m-b-xs">
					<h2 class="font-normal m-b-xs text-center">
						应用中心
					</h2>
				</div>
			</div>
		</div>
	</div>
	
	<div class="content animate-panel">
		<!-- 基础信息 -->
		<div class="row">
			<div class="col-lg-12">
				<div class="hpanel">
					<!-- 文本区域 主标题 -->
					<!-- <div class="panel-heading">
		                <h4 class="text-center font-bold">应用中心</h4>
	            	</div> -->
	            	
	            	<div class="panel-body">
	            		<div class="col-lg-12 ">
	            			<div class="ngRow m-b-lg">
	            				<p class="os-text-head"><strong>已开通</strong></p>
	            			</div>
		            		<div class="col-lg-12">
		            			<ul class="list-inline text-left">
		            				<li class="m-r-xl m-l">
		            					<a href="#">
		            						<img alt="社保代理" src="static/img/index/icon_7.png">
		            					</a>
		            					<p class="text-center m-t">社保代理</p>
		            					<a href="#" class="btn btn-default btn-xs btn-block">关闭服务</a>
		            				</li>
		            				<li class="m-r-xl m-l">
		            					<a href="#">
		            						<img alt="薪资代缴" src="static/img/index/icon_8.png">
		            					</a>
		            					<p class="text-center m-t">薪资代缴</p>
		            					<a href="#" class="btn btn-default btn-xs btn-block">关闭服务</a>
		            				</li>
		            			</ul>
		            		</div>
	            		</div>
	            		
	            		<div class="col-lg-12 m-t-lg">
	            			<div class="ngRow m-b-lg">
	            				<p class="os-text-head"><strong>待开通</strong></p>
	            			</div>
		            		<div class="col-lg-12">
		            			<ul class="list-inline text-left">
		            				<li class="m-r-xl m-l">
		            					<a href="#">
		            						<img alt="福利" src="static/img/index/icon_9.png">
		            					</a>
		            					<p class="text-center m-t">福利</p>
		            					<a href="#" class="btn btn-default btn-xs btn-block">关闭服务</a>
		            				</li>
		            				<li class="m-r-xl m-l">
		            					<a href="#">
		            						<img alt="测评" src="static/img/index/icon_11.png">
		            					</a>
		            					<p class="text-center m-t">测评</p>
		            					<a href="#" class="btn btn-default btn-xs btn-block">关闭服务</a>
		            				</li>
		            			</ul>
		            		</div>
	            		</div>
	            		
	            		<div class="col-lg-12 m-t-lg list">
	            			<div class="ngRow m-b-lg">
	            				<p class="os-text-head"><strong>敬请期待</strong></p>
	            			</div>
		            		<div class="col-lg-12">
		            			<ul class="list-inline text-left">
		            				<li class="m-r-xl m-l">
		            					<a href="#">
		            						<img alt="问卷调查" src="static/img/index/icon_13.png">
		            					</a>
		            					<p class="text-center m-t">问卷调查</p>
		            					<a href="#" class="btn btn-default btn-xs btn-block">敬请期待</a>
		            				</li>
		            				<li class="m-r-xl m-l">
		            					<a href="#">
		            						<img alt="招聘" src="static/img/index/icon_12.png">
		            					</a>
		            					<p class="text-center m-t">招聘</p>
		            					<a href="#" class="btn btn-default btn-xs btn-block">敬请期待</a>
		            				</li>
		            				<li class="m-r-xl m-l">
		            					<a href="#">
		            						<img alt="背景调查" src="static/img/index/icon_10.png">
		            					</a>
		            					<p class="text-center m-t">背景调查</p>
		            					<a href="#" class="btn btn-default btn-xs btn-block">敬请期待</a>
		            				</li>
		            			</ul>
		            		</div>
	            		</div>
	            	</div>
            	</div>
			</div>
		</div>
	</div>
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
<script src="static/bootstrap/vendor/bootstrap-datepicker-master/dist/js/bootstrap-datepicker.min.js"></script>

<!-- for datatable -->
<script src="static/bootstrap/vendor/datatables/media/js/jquery.dataTables.min.js"></script>
<script src="static/bootstrap/vendor/datatables_plugins/integration/bootstrap/3/dataTables.bootstrap.min.js"></script>
<!-- alert -->
<script src="static/bootstrap/vendor/sweetalert/lib/sweet-alert.min.js"></script>

<!-- App scripts -->
<script src="static/bootstrap/scripts/homer.js"></script>
<script src="static/js/yuangong.js"></script>
</body>
</html>