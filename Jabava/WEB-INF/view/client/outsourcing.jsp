<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="${pageContext.request.contextPath}/">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge">

<!-- Page title -->
<title>Jabava人事外包服务界面</title>
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
                        <div class="panel-heading org_padding_bottom">
                            <div class="pull-right org_margin_top">
                                &nbsp;
                            </div>
                        </div>

                        <div class="panel-body service_new_one">
                            <div class="col-sm-12">
                                <form role="form" id="addOutsourcingForm" class="formclass">

                                    <div class="form-group service_none">
                                        <label class="col-lg-1 service_margin_b">&nbsp;</label>
                                    </div>

                                    <div class="form-group">
                                        <label for="exampleInputName1" class="col-lg-2 service_margin_b">客户名称：</label>
                                        <div class="col-lg-6 service_margin_b">
                                            <span id="companyName"></span>
                                        </div>
                                    </div>

                                    <div class="form-group service_none">
                                        <label class="col-lg-3
                                        service_margin_b">&nbsp;</label>
                                    </div>

                                    <div class="form-group service_none">
                                        <label class="col-lg-1 service_margin_b">&nbsp;</label>
                                    </div>

                                    <div class="form-group">
                                        <label for="exampleInputName3" class="col-lg-2 service_margin_b">联系人&nbsp;&nbsp;&nbsp;：</label>
                                        <div class="col-lg-3 service_margin_b">
                                            <input type="text" class="form-control" id="contactEmp" name="contactEmp">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label for="exampleInputName4" class="col-lg-2 service_margin_b">联系电话：</label>
                                        <div class="col-lg-3 service_margin_b">
                                            <input type="text" class="form-control" id="telephoneNumber" name="telephoneNumber">
                                        </div>
                                    </div>

                                    <div class="form-group service_none">
                                        <label class="col-lg-1 service_margin_b">&nbsp;</label>
                                    </div>


                                    <div class="form-group service_none">
                                        <label class="col-lg-1 service_margin_b">&nbsp;</label>
                                    </div>

                                    <div class="form-group">
                                        <label for="exampleInputName5" class="col-lg-2 service_margin_b">备注&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;：</label>
                                        <div class="col-lg-8 service_margin_b">
                                            <textarea type="text" class="form-control" id="addRemark" name="remark"></textarea>
                                        </div>
                                    </div>

                                    <div class="form-group service_none">
                                        <label class="col-lg-1 service_margin_b">&nbsp;</label>
                                    </div>
                                    <div class="form-submit">
                                        <div class="col-md-11 col-lg-11 text-right">
                                            <button class="btn btn-success" type="button" id="addOutsourcing">开通</button>
                                        </div>

                                    </div>
                                </form>
                            </div>

                        </div>

                        <div class="panel-body service_new_two"></div>

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
    <script src="static/js/outsourcing/outsourcing.js"></script>
    
<!--取消效果-->
<script>
$(function(){
	//获取公司名称
	 $.ajax({
			type : 'post',
			url : "outsourcing/getCompanyName",			
			async : false,
			dataType:'json',
			success : function(result) {
				console.log(typeof result);
				if(result){
					
					$("#companyName").html(result.companyName);					
					
				}
				
			}
		});	
	
})
</script>
</body>
</html>