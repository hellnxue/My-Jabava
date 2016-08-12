<%@page import="com.jabava.utils.JabavaDateUtils"%>
<%@page import="com.jabava.core.config.JabavaPropertyCofigurer"%>
<%@page import="com.jabava.utils.RequestUtil"%>
<%@page import="com.jabava.pojo.manage.EhrUser"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = "//"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	
	//EhrUser user = (EhrUser) session.getAttribute(Constants.LOGIN_USER);
	//if(user != null && user.getUserType() == 2){
	if(RequestUtil.hasRoleOfName("企业管理员")){
		//request.getRequestDispatcher("/index/company_index").forward(request, response);
//		response.sendRedirect(basePath + "index/company_index");
	//}else if(RequestUtil.hasRoleOfName("HR")){
	//	//request.getRequestDispatcher("/index/hr_index").forward(request, response);
	//	response.sendRedirect(basePath + "index/hr_index");
	}
	
	Date today = JabavaDateUtils.parseDate(JabavaDateUtils.formatDate("yyyy-MM-dd"), "yyyy-MM-dd");
	Date festival = JabavaDateUtils.parseDate("2016-09-15", "yyyy-MM-dd");
	long days = JabavaDateUtils.getDiffDays(today, festival);
%>

<!DOCTYPE HTML>
<html>
<head>
	<base href="${pageContext.request.contextPath}/">
 	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <!-- Page title -->
    <title>Jabava-人力资源管理云服务平台</title>
    <jsp:include flush="true" page="styles.jsp"></jsp:include>
    <link rel="stylesheet" href="static/css/user_bata.css">
</head>
<body>
 <!--splash screen-->
 <jsp:include flush="true" page="splashscreen.div.jsp"></jsp:include>

<!--引入头文件 开始--> 
  <jsp:include flush="true" page="header.div.jsp"></jsp:include>
  <!--引入头文件 结束--> 
  <!--引入菜单文件 开始--> 
  <jsp:include flush="true" page="menu.div.jsp"></jsp:include>
  <!--引入菜单文件 结束--> 

<!-- Main Wrapper -->
<div id="wrapper" class="jabava-home">
    <div class="content animate-panel">
        <div class="row">
            <div class="col-sm-9 col-md-9 col-lg-9">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="hpanel text-center">
                            <div id="myCarousel" class="carousel slide pad_010 b_k" data-ride="carousel">
                                <!-- 轮播（Carousel）项目 -->
                                <div class="carousel-inner bor_btm">
                                    <div class="item active" >
                                        <div class="pic">
                                            <a target="_blank" href="http://i.maka.im/pcviewer/BM3OWKDD"><img src="static/img/index/index-barner.png" width="100%"></a>
                                        </div>
                                    </div>
                                    <div class="item" >
                                        <div class="pic">
                                            <a href="http://mp.weixin.qq.com/s?__biz=MzI2NDI2OTc5MA==&mid=2247483927&idx=1&sn=d7855c9081f0b79a5906f18b51be105d&scene=0#wechat_redirect" target="_blank"><img src="static/img/index/index-barner-jingdong.png" width="100%"></a>
                                        </div>
                                    </div>
                                </div>
                                <!-- 轮播（Carousel）导航 -->
                                <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
                                    <i class="fa fa-angle-left"></i>
                                    <span class="sr-only">上一页</span>
                                </a>
                                <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
                                    <i class="fa fa-angle-right"></i>
                                    <span class="sr-only">下一页</span>
                                </a>  
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-6 col-md-6 col-lg-4">
                        <div class="hpanel m-b">
                            <div class="panel-body p-xs">
                                <div class="pull-left m-l-sm">
                                    <img src="static/img/index/icon_1.png" alt="">
                                </div>
                                <div class="text-center">
                                <a class="text-warning" href="javascript://" data-link-for="toBecomeMember"><span class="f-s-20" data-employee-count="toBecomeMember">0</span>人</a>
                                    <p class="m-t-xs m-b-none">10天内转正</p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-6 col-md-6 col-lg-4">
                        <div class="hpanel m-b">
                            <div class="panel-body p-xs">
                                <div class="pull-left m-l-sm">
                                    <img src="static/img/index/icon_2.png" alt="">
                                </div>
                                <div class="text-center">
                                    <a class="text-success" href="javascript://" data-link-for="entryThisMonth"><span class="f-s-20" data-employee-count="entryThisMonth">0</span>人</a>
                                    <p class="m-t-xs m-b-none">本月入职</p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-6 col-md-6 col-lg-4">
                        <div class="hpanel m-b">
                            <div class="panel-body p-xs">
                                <div class="pull-left m-l-sm">
                                    <img src="static/img/index/icon_3.png" alt="">
                                </div>
                                <div class="text-center">
                                    <a class="text-danger" href="javascript://" data-link-for="birthdayToday"><span class="f-s-20" data-employee-count="birthdayToday">0</span>人</a>
                                    <p class="m-t-xs m-b-none">今日生日</p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-6 col-md-6 col-lg-4">
                        <div class="hpanel m-b">
                            <div class="panel-body p-xs">
                                <div class="pull-left m-l-sm">
                                    <img src="static/img/index/icon_4.png" alt="">
                                </div>
                                <div class="text-center">
                                    <a class="text-warning" href="javascript://" data-link-for="contractExpiring"><span class="f-s-20" data-employee-count="contractExpiring">0</span>人</a>
                                    <p class="m-t-xs m-b-none">30天内合同到期</p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-6 col-md-6 col-lg-4">
                        <div class="hpanel m-b">
                            <div class="panel-body p-xs">
                                <div class="pull-left m-l-sm">
                                    <img src="static/img/index/icon_5.png" alt="">
                                </div>
                                <div class="text-center">
                                    <a class="text-success" href="javascript://" data-link-for="leaveThisMonth"><span class="f-s-20" data-employee-count="leaveThisMonth">0</span>人</a>
                                    <p class="m-t-xs m-b-none">本月离职</p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-6 col-md-6 col-lg-4">
                        <div class="hpanel m-b">
                            <div class="panel-body p-xs">
                                <div class="pull-left m-l-sm">
                                    <img src="static/img/index/icon_6.png" alt="">
                                </div>
                                <div class="text-center">
                                    <a class="text-danger" href="<%=JabavaPropertyCofigurer.getProperty("outerlink.rui")%>" target="_blank"><span class="f-s-20" data-employee-count="toNextFestival"><%=days %></span>天</a>
                                    <p class="m-t-xs m-b-none">距离中秋节 挑选节日福利</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-12">
                        <div class="hpanel">
                            <div class="panel-body p-xs">
                                <ul class="list-inline text-center">
                                    <li class="m-r-xl m-l">
                                        <a href="outsourcing/toServiceOpen"><img src="static/img/index/icon_7.png" alt=""></a>
                                        <p class="text-center m-t">社保代理</p>
                                    </li>
                                    <li class="m-r-xl m-l">
                                        <a href="outsourcing/toServiceOpen"><img src="static/img/index/icon_8.png" alt=""></a>
                                        <p class="text-center m-t">薪资代缴</p>
                                    </li>
                                    <li class="m-r-xl m-l">
                                        <a href="<%=JabavaPropertyCofigurer.getProperty("outerlink.rui")%>" target="_blank"><img src="static/img/index/icon_9.png" alt=""></a>
                                        <p class="text-center m-t">福利</p>
                                    </li>
                                    <li class="m-r-xl m-l">
                                       <a href="javascript://" onclick="gotoWenJuan();"><img src="static/img/index/icon_11.png" alt=""></a> 
                                       <p class="text-center m-t">测评</p>
                                    </li>
                                    <li class="m-r-xl m-l">
                                        <a href="javascript://" onclick="gotoFormtalk();"><img src="static/img/index/icon_13.png" alt=""></a>
                                        <p class="text-center m-t">云表单</p>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-sm-3 col-md-3 col-lg-3">
                <div class="hpanel">
                    <div class="panel-body text-center">
                        <div class="m-l m-b m-r">
                            <a href="<%=JabavaPropertyCofigurer.getProperty("outerlink.store")%>?hroOrgId=${sessionScope.RECEIVE_ORG_ID}" target="_blank">
                                <img src="static/img/index/zhen.png" width="70%" alt="">
                            </a>
                        </div>
                        <div class="m-l m-b m-r">
                            <a href="<%=JabavaPropertyCofigurer.getProperty("outerlink.rui")%>" target="_blank">
                                <img src="static/img/index/rui.png" width="70%" alt="">
                            </a>
                        </div>
                        <div class="m-l m-b m-r">
                            <a href="<%=JabavaPropertyCofigurer.getProperty("outerlink.quanxinbao")%>" target="_blank">
                                <img src="static/img/index/quan.png" width="70%" alt="">
                            </a>
                        </div>
                        <div class="m-l m-b m-r">
                            <a href="<%=JabavaPropertyCofigurer.getProperty("outerlink.zixun")%>" target="_blank">
                                <img src="static/img/index/HR.png" width="70%" alt="">
                            </a>
                        </div>
                        <div class="m-l m-r m-b-none">
                            <a href="<%=JabavaPropertyCofigurer.getProperty("outerlink.miaoshichang")%>" target="_blank">
                                <img src="static/img/index/mm.png" width="70%" alt="">
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Footer-->
    <jsp:include flush="true" page="../common/foot.div.jsp"></jsp:include>
</div>
<div style="display:none;">
	<form id="personForm" action="" method="post">
		<input type="hidden" id="countType" name="countType">
	</form>
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
<!-- alert -->
<script src="static/bootstrap/vendor/sweetalert/lib/sweet-alert.min.js"></script>
<!-- App scripts -->
<script src="static/bootstrap/scripts/homer.js"></script>

<script>
    (function($){
        function slide(){
            $('#myCarousel').carousel({
                interval: 10000,
                wrap: true
            });
        }
        function init(){
            slide();
        }
        
        init();
        
        $.get('employee/queryHintInfo', 'JSON')
        .done(function(data){
            data = $.parseJSON(data)
            $.each(data, function(key, val) {
                $('[data-employee-count="'+key+'"]').text(val)
            });
        });
        
        $('[data-link-for]').on('click', function(event){
        	var URI = 'employee/employeeList',
        		$form = $('#personForm')
        		
        		$('#countType').val( $(this).attr('data-link-for') )
				$form.attr('action', URI)
				$form.submit()
        		
        })
    })(jQuery)
    
    function gotoWenJuan(){
    	$.ajax({
    	    url : "hasOpenService",
    	    data : {systemId: 11},
    	    dataType:'json',
    	    type : 'post',
    	    success : function(message) {
    	    	if(message && message == "1"){
    	    		//location.href = 'common/hrwenjuan';
    	    		location.href = 'common/link/outerlink-hrwenjuan';
    	    	}else{
    	    		//openService();
    	    		location.href = 'common/plan/hrwenjuan';
    	    	}
    	    }
    	});
    }
    
    function gotoFormtalk(){
    	$.ajax({
    	    url : "hasOpenService",
    	    data : {systemId: 13},
    	    dataType:'json',
    	    type : 'post',
    	    success : function(message) {
    	    	if(message && message == "1"){
    	    		location.href = 'common/link/outerlink-formtalk';
    	    	}else{
    	    		//swal({
					//	title: '您尚未开通服务，请先完成服务开通',
					//	showCancelButton: true,
					//	cancelButtonText: '以后开通',
					//	confirmButtonText: '现在开通',
			 		//	type: 'error'
					//},function(isConfirm){
					//	if(isConfirm){
					//		openService(13, 'outerlink-formtalk');
					//	}
					//});
    	    		location.href = 'common/plan/formtalk';
    	    	}
    	    }
    	});
    }
    
    function openService(systemId, linkKey){
    	$.ajax({
            url : "openService",
            data : {systemId: systemId},
            dataType:'json',
            type : 'post',
            success : function(message) {
                if(message.success){
                    swal({
                        title: "开通成功!",
                        text: "",
                        type: "success",
                        confirmButtonText: "确定"
                    },function(){
                    	location.href = '/common/link/' + linkKey;
                    });
                }else{
                    swal({
                        title: "开通失败!",
                        text: message.msg,
                        type: "error",
                        confirmButtonText: "确定"
                    });
                }
            }
        });
    }
</script>
</body>
</html>
