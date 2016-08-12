<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<base href="${pageContext.request.contextPath}/">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- Page title -->
    <title>应用中心</title>
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
	            		<div class="col-lg-12 " id="openedApp">
	            			<div class="ngRow m-b-lg">
	            				<p class="os-text-head"><strong>已开通</strong></p>
	            			</div>
		            		<div class="col-lg-12">
		            			<ul class="list-inline text-left" data-type="data">
		            				
		            			</ul>
		            		</div>
	            		</div>
	            		
	            		<div class="col-lg-12 m-t-lg" id="unOpenedApp">
	            			<div class="ngRow m-b-lg">
	            				<p class="os-text-head"><strong>待开通</strong></p>
	            			</div>
		            		<div class="col-lg-12">
		            			<ul class="list-inline text-left" data-type="data">
		            				
		            			</ul>
		            		</div>
	            		</div>
	            		
	            		<div class="col-lg-12 m-t-lg list" id="offLineApp">
	            			<div class="ngRow m-b-lg">
	            				<p class="os-text-head"><strong>敬请期待</strong></p>
	            			</div>
		            		<div class="col-lg-12">
		            			<ul class="list-inline text-left" data-type="data">
		            				
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

<!-- 模板区域 -->
<!-- 已开通应用显示模板 -->
<script id="appTemplateOpened" type="text/template">
	<li class="m-r-xl m-l" appId="{{appId}}">
		<a href="javascript:void(0);" onclick="gotoApp({{systemId}}, '{{openedUrl}}');">
			<img alt="{{appName}}" src="{{iconPath}}">
		</a>
		<p class="text-center m-t">{{appName}}</p>
		<!--<a href="javascript:void(0);" onclick="gotoApp({{systemId}}, '{{openedUrl}}');" class="btn btn-default btn-xs btn-block">进入应用</a>-->
	</li>
</script>
<!-- 未开通应用显示模板 -->
<script id="appTemplateUnOpened" type="text/template">
	<li class="m-r-xl m-l" appId="{{appId}}">
		<a href="javascript:void(0);" onclick="applyToOpenApp({{systemId}}, '{{unOpenedUrl}}');">
			<img alt="{{appName}}" src="{{iconPath}}">
		</a>
		<p class="text-center m-t">{{appName}}</p>
		<!--<a href="javascript:void(0);" onclick="applyToOpenApp({{systemId}}, '{{unOpenedUrl}}');" class="btn btn-default btn-xs btn-block">申请开通</a>-->
	</li>
</script>
<!-- 未上线应用显示模板 -->
<script id="appTemplateOffLine" type="text/template">
	<li class="m-r-xl m-l" appId="{{appId}}">
		<a href="javascript:void(0);">
			<img alt="{{appName}}" src="{{iconPath}}">
		</a>
		<p class="text-center m-t">{{appName}}</p>
		<!--<a href="javascript:void(0);" class="btn btn-default btn-xs btn-block">敬请期待</a>-->
	</li>
</script>

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
<!-- <script src="static/js/yuangong.js"></script> -->
<script>
	var $ele = ['openedApp', 'unOpenedApp', 'offLineApp'];
	//页面渲染时的操作
	(function($){
		//隐藏相关数据区域
		jQuery($ele).each(function(index){
			jQuery("#" + $ele[index]).hide();
		});
	})(jQuery);

	//页面加载完成后的操作
	jQuery(document).ready(function(){
		var apps = getAppData();
		if (apps) {
			showDataArea(apps);
		};
	});

	//显示数据区域
	function showDataArea(data) {
		if (!data) return;
		//克隆模板
		var openedAppTem = jQuery("#appTemplateOpened").clone(),
			offLineAppTem = jQuery("#appTemplateOffLine").clone(),
			unOpenedAppTem = jQuery("#appTemplateUnOpened").clone();

		//常量化字段替换符
		var repStrArr = ['appId', 'appName', 'iconPath', 'systemId', 'openedUrl', 'unOpenedUrl'];

		//遍历数据
		var offLineCount = 0, opendCount = 0, unOpenedCount = 0;
		jQuery(data).each(function(index, element) {
			var isOnline = element.isOnline;
			//敬请期待应用
			if (isOnline == 0) {				
				showDataDiv(++offLineCount, $ele[2]);
				jQuery("#offLineApp").find("ul[data-type='data']").append(
					replaceTemp(repStrArr, element, offLineAppTem.html()));
				return 1;
			};
			var systemId = element.systemId;
			if (hasOpenService(systemId)) {
				//已开通应用
				showDataDiv(++opendCount, $ele[0]);
				jQuery("#openedApp").find("ul[data-type='data']").append(
					replaceTemp(repStrArr, element, openedAppTem.html()));
				return 1;
			} else {
				//未开通应用
				showDataDiv(++unOpenedCount, $ele[1]);
				jQuery("#unOpenedApp").find("ul[data-type='data']").append(
					replaceTemp(repStrArr, element, unOpenedAppTem.html()));
				return 1;
			}
		});
	}

	//获取应用信息
	function getAppData() {
		var url = "appStore/getAppInfo";
		var params = {};
		return post(url, params);
	}

	//查询应用是否已经开通
	function hasOpenService(systemIdVal) {
		var url = "hasOpenService";
		var params = {systemId : systemIdVal};
		return post(url, params) == "1";
	}

	//进入应用申请开通页面
	function applyToOpenApp(systemId, unOpenedUrl) {
		window.location.href = unOpenedUrl;
	}

	//进入应用页面
	function gotoApp(systemId, openedUrl) {
		window.location.href = openedUrl;
	}

	//发送ajax-post请求
	function post(url, params) {
		var rslt = null;
		$.ajax({
			url : url,
			data : params,
			dataType : 'json',
			type : 'post',
			async : false,
			success : function(data) {
				rslt = data;
			},
			error : function(data) {
				swal({
					title : '网络连接失败！',
					text : '网络连接失败，请稍候重试！',
					type : 'error',
					confirmButtonText : '确定'
				});
			}
		});
		return rslt;
	}

	//替换相应模板中的占位符(占位符列表，实际替换数据对象，模板文本)
	function replaceTemp(repStrArr, valData, data) {
		var _data = data;
		jQuery(repStrArr).each(function(index, element) {
			var _name = repStrArr[index];
			var repStr = "{{" + _name + "}}";
			var repVal = valData[_name];
			_data = _data.replace(new RegExp(repStr, "gm"), repVal);
		});
		return _data;
	}

	//显示某项数据区域(应用个数，元素标记)
	function showDataDiv(count, element) {
		if (count == 1) {
			jQuery("#" + element).show();
		}
	}
</script>
</body>
</html>