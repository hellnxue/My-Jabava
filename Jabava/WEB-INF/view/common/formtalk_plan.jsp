<%@page import="com.jabava.core.config.JabavaPropertyCofigurer"%>
<%@page import="com.jabava.utils.RequestUtil"%>
<%@page import="com.jabava.pojo.manage.EhrUser"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = "//"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html>
<head>
	<base href="${pageContext.request.contextPath}/">
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <!-- Page title -->
    <title>云表单</title>
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
<div id="wrapper">
    <div class="content animate-panel">
        <div class="row">
            <div class="col-lg-12">
                <div class="hpanel">
                    <div class="panel-body thumbnail">
                        <img src="static/img/formtalk.png" alt="">
                        <a href="javascript://" class="btn btn-info" data-toggle="tooltip">申请开通</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Footer-->
    <jsp:include flush="true" page="../common/foot.div.jsp"></jsp:include>

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
<script type="text/javascript">
    var $width = $('[data-toggle="tooltip"]').outerWidth()
    $('[data-toggle="tooltip"]').css({
      position: 'absolute',
      bottom: 40,
      left: '50%',
      marginLeft: -($width/2)
    })
    .on('click', function(event){
    	$.ajax({
            url : "openService",
            data : {systemId: 13},
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
                    	location.href = 'common/link/outerlink-formtalk';
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
    })

    var getTooltip = $('[data-toggle="tooltip"]').tooltip({
      trigger: 'manual',
      container: 'body',
      title: '您尚未开通云表单功能，是否现在开通？'
    })
    .tooltip('show')
    
</script>
</body>
</html>
