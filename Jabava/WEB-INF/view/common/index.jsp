<%@page import="com.jabava.utils.RequestUtil"%>
<%@page import="com.jabava.pojo.manage.EhrUser"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
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
<base href="<%=basePath%>">
<head>
 <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <!-- Page title -->
    <title>JabavaDemo | V1.0</title>
    <jsp:include flush="true" page="styles.jsp"></jsp:include>
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
<div id="wrapper">
    <div class="content animate-panel">
        <div class="row">
            <div class="col-lg-12">
                <div class="hpanel">
                    <div class="panel-heading hbuilt jabava-built p-lg">
                        <h4 class="m-n font-bold">HRO客户端
                        <p class="small font-light m-t-md">HRO客户端可满足企业对公司人员的社保，公积金、工资等需求。在HRO客户端中可便捷、安全、有效地完成一系列人力资源工作。</p>
                        </h4>
                    </div>
                    <div class="panel-body">
                        <div class="text-center">
                            <img src="static/img/HRO-index.png">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Footer-->
    <footer class="footer">
        <span class="pull-right">
            智阳网络技术（上海）有限公司
        </span>
        Company 2015-2020
    </footer>

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

<!-- App scripts -->
<script src="static/bootstrap/scripts/homer.js"></script>
</body>
</html>
