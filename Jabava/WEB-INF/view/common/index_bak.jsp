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
            <div class="col-lg-12">
                <div class="hpanel">
                    <div class="panel-heading hbuilt jabava-built">
                        <h4 class="m-n font-bold">消息提醒</h4>
                    </div>
                    <div class="panel-body panel-top">
                        <div class="col-xs-3 col-sm-3 col-md-3 col-lg-3 card ">
                            <div class="bg m-b-lg bg-warning">
                                <p class="text-center header"><i class="fa fa-briefcase text-warning"></i>&nbsp;&nbsp;10天内转正</p>
                                <p class="text-center ">
                                    <a href="javascript://" data-link-for="toBecomeMember"><strong class="text-success" data-employee-count="toBecomeMember"></strong><span>人</span></a>
                                </p>
                            </div>
                        </div>
                        <div class="col-xs-3 col-sm-3 col-md-3 col-lg-3 card">
                            <div class="bg m-b-lg bg-danger">
                                <p class="text-center header"><i class="fa fa-gift text-danger"></i>&nbsp;&nbsp;今天生日</p>
                                <p class="text-center ">
                                    <a href="javascript://" data-link-for="birthdayToday"><strong class="text-success" data-employee-count="birthdayToday"></strong><span>人</span></a>
                                </p>
                            </div>
                        </div>
                        <div class="col-xs-3 col-sm-3 col-md-3 col-lg-3 card">
                            <div class="bg m-b-lg bg-info">
                                <p class="text-center header"><i class="fa fa-users text-info"></i>&nbsp;&nbsp;本月入职人数</p>
                                <p class="text-center ">
                                    <a href="javascript://" data-link-for="entryThisMonth"><strong class="text-success" data-employee-count="entryThisMonth">11</strong><span>人</span></a>
                                </p>
                            </div>
                        </div>
                        <div class="col-xs-3 col-sm-3 col-md-3 col-lg-3 card">
                            <div class="bg m-b-lg bg-default">
                                <p class="text-center header"><i class="fa fa-minus-square text-default"></i>&nbsp;&nbsp;本月离职人数</p>
                                <p class="text-center">
                                    <a href="javascript://" data-link-for="leaveThisMonth"><strong class="text-success" data-employee-count="leaveThisMonth"></strong><span>人</span></a>
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
                
                 <div class="hpanel">
                    <div class="panel-heading hbuilt jabava-built">
                        <h4 class="m-n font-bold">HR百宝箱</h4>
                    </div>
                    <div class="panel-body panel-top">
                        <div class="col-xs-3 col-sm-3 col-md-3 col-lg-3">
                            <a href="tools/calculator" class="thumbnail no-borders">
                              <img alt="社保计算器" src="static/img/sbjsq.png">
                            </a>
                        </div>
                        <div class="col-xs-3 col-sm-3 col-md-3 col-lg-3">
                            <a href="http://ehr.ezhiyang.com/e-cloud/nosec/actionPage/caltool.e" target="_blank" class="thumbnail no-borders">
                              <img src="static/img/ssjsq.png" alt="个税计算器">
                            </a>
                        </div>
                        <div class="col-xs-3 col-sm-3 col-md-3 col-lg-3">
                            <a href="common/excel_than_artifact" class="thumbnail no-borders">
                              <img alt="Excel 对比神器" src="static/img/duibi.png">
                            </a>
                        </div>
                        <div class="col-xs-3 col-sm-3 col-md-3 col-lg-3 sr-only">
                            <a href="common/recruitment_tool" class="thumbnail no-borders">
                              <img alt="智阳录单工具" src="static/img/ludan.png">
                            </a>
                        </div>
                        <div class="col-xs-3 col-sm-3 col-md-3 col-lg-3">
                            <a href="javascript:void(0);" onclick="gotoWenJuan();" class="thumbnail no-borders">
                              <img alt="HR 问卷" src="static/img/wenjuan.png">
                            </a>
                        </div>
                    </div>
                </div>


                <div class="hpanel">
                    <div class="panel-heading hbuilt jabava-built">
                        <h4 class="m-n font-bold">其他服务</h4>
                    </div>
                    <div class="panel-body panel-top">
                        <div class="col-xs-3 col-sm-3 col-md-3 col-lg-3">
                            <a href="<%=JabavaPropertyCofigurer.getProperty("outerlink.hrofirst")%>" class="thumbnail no-borders " target="_blank">
                              <img src="static/img/Banner01_anxinshebao.png">
                            </a>
                        </div>
                        <div class="col-xs-3 col-sm-3 col-md-3 col-lg-3">
                            <a href="<%=JabavaPropertyCofigurer.getProperty("outerlink.rui")%>" class="thumbnail no-borders " target="_blank">
                              <img src="static/img/Banner02_Fuli.png">
                            </a>
                        </div>
                        <div class="col-xs-3 col-sm-3 col-md-3 col-lg-3">
                            <a href="<%=JabavaPropertyCofigurer.getProperty("outerlink.zixun")%>" class="thumbnail no-borders " target="_blank">
                              <img src="static/img/Banner03_Info.png">
                            </a>
                        </div>
                        <div class="col-xs-3 col-sm-3 col-md-3 col-lg-3">
                            <a href="<%=JabavaPropertyCofigurer.getProperty("outerlink.store")%>?hroOrgId=${sessionScope.RECEIVE_ORG_ID}" class="thumbnail no-borders " target="_blank">
                              <img src="static/img/Banner04_BestSell.png">
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
    	    		location.href = 'common/hrwenjuan';
    	    	}else{
    	    		//openService();
    	    		location.href = 'common/hrwenjuan_plan';
    	    	}
    	    }
    	});
    }
    
    /* function openService() {
        swal({
            title: "申请开通",
            text: "",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "确定开通",
            cancelButtonText: "取消开通",
            closeOnConfirm: false,
            closeOnCancel: false  
            },
        function (isConfirm) {
            if (isConfirm) {
           		location.href = "common/hrwenjuan_plan";
            }else{
            	swal("已取消", "", "error");
            }
        });
    } */
</script>
</body>
</html>
