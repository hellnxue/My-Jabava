<%@page import="com.jabava.pojo.manage.EhrUser"%>
<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="com.jabava.utils.RequestUtil"%>
<%@ page contentType="text/html; charset=utf-8" %>

<%
	String path = request.getContextPath();
	String basePath = "//"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	
	//String urlAfterRegister = (String)session.getAttribute("URL_AFTER_REGISTER");
	//if(!StringUtils.isEmpty(urlAfterRegister)){
	//	session.removeAttribute("URL_AFTER_REGISTER");
	//	response.sendRedirect(path + urlAfterRegister);
	//}
	
	//if(user != null && user.getUserType() == 2){
//	if(!RequestUtil.hasRoleOfName("企业管理员")){
//		if(RequestUtil.hasRoleOfName("HR")){
//			//request.getRequestDispatcher("/index/hr_index").forward(request, response);
//			response.sendRedirect(basePath + "index");
//		}else {
//			//request.getRequestDispatcher("/index").forward(request, response);
//			response.sendRedirect(basePath + "index");
//		}
//	}
	response.sendRedirect(basePath + "index");
	
	//Integer jump = (Integer)request.getAttribute("jump");
	//if(jump == null || jump.intValue() != 1){
	//	//如果未选择跳过，则转向完善信息页面
	//	EhrUser user = (EhrUser) session.getAttribute(Constants.LOGIN_USER);
	//	if(user.getCompany().getPerfectDate() == null){
	//		response.sendRedirect(path + "/to_perfect");
	//	}
	//}
%>
<!DOCTYPE html>
<html>
<head>
	<base href="${pageContext.request.contextPath}/">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <!-- Page title -->
    <title>jabava1.0企业首页界面</title>
    <jsp:include flush="true" page="../common/styles.jsp"></jsp:include>
    <link rel="stylesheet" href="static/css/user.css">
</head>
<body >

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
        <div class="normalheader transition animated fadeIn small-header">
            <div class="hpanel">
                <div class="panel-body company_border_two">
                    <div id="hbreadcrumb" class="pull-right m-t-lg">
                        <ol class="hbreadcrumb breadcrumb">
                            <li>企业首页</li>

                        </ol>
                    </div>
                    <h2 class="font-light m-b-xs mem_h2">
                        企业首页
                    </h2>
                    <small class="hong strong">温馨提示：建议给企业HR开通权限</small>
                </div>
            </div>
        </div>

        <!-- 放主要内容 -->

        <div class="content animate-panel">
            <div class="row">
                <div class="col-lg-12">
                    <div class="hpanel clearfix">
                        <div class="panel-body company_one company_border">
                            <form id="hrForm" role="form" class="search-form">
                                <div class="form-group">
                                    <label for="" class="col-lg-2 rost_sousuo company_padding">用户名：</label>
                                    <div class="col-lg-4 service_margin_b">
                                        <input type="text" class="form-control" id="userCode" name="userCode">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="" class="col-lg-2 rost_sousuo company_padding">&nbsp;</label>       
                                    <div class="col-lg-4 service_margin_b">
                                        <label class="form-control" style="border:none;">&nbsp;</label>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="" class="col-lg-2 rost_sousuo company_padding">姓名：</label>
                                    <div class="col-lg-4 service_margin_b">
                                        <input type="text" class="form-control" id="userName" name="userName">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="" class="col-lg-2 rost_sousuo company_padding">性别：</label>
                                    <div class="col-lg-4 service_margin_b">
                                        <div class="form-control form_con">
                                            <input type="radio" class="company_input" id="sex1" name="sex" value="1"><span class="company_sex company_margin_right">男</span>
                                            <input type="radio" class="company_input" id="sex2" name="sex" value="2"><span class="company_sex">女</span>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="" class="col-lg-2 rost_sousuo company_padding">手机：</label>
                                    <div class="col-lg-4 service_margin_b">
                                        <input type="text" class="form-control" id="mobile" name="mobile">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="" class="col-lg-2 rost_sousuo company_padding">电话：</label>
                                    <div class="col-lg-4 service_margin_b">
                                        <input type="text" class="form-control" id="telephone" name="telephone">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="" class="col-lg-2 rost_sousuo company_padding">Email：</label>
                                    <div class="col-lg-4 service_margin_b">
                                        <input type="text" class="form-control" id="mailAddress" name="mailAddress">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="" class="col-lg-2 rost_sousuo company_padding">用户类型：</label>
                                    <div class="col-lg-4 service_margin_b">
                                        <span class="form-control company_no">HR用户</span>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="" class="col-lg-2 rost_sousuo company_padding">备注：</label>
                                    <div class="col-lg-10 service_margin_b">
                                        <textarea type="text" class="form-control" id="memo" name="memo"></textarea>
                                    </div>
                                </div>

                                <center class="index_center">
                                    <button class="btn btn-info" type="button">保 存</button>
                                    &nbsp;
                                    <button class="btn" type="button">取 消</button>
                                </center>
                            </form>
                        </div>
                        <div class="panel-body company_two company_border">
                            &nbsp;
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
    
	<script>
   		$(function(){
			
			$("center button").each(function() {
                $(this).click(function(){
                	if($("#userCode").val()==''){
                		alert('请输入用户名');
                		return false;
                	}
                	if($("#userCode").length>30){
                		alert('用户名长度不能超过30');
                		return false;
                	}
                	if($("#userName").val()==''){
                		alert('请输入姓名 ');
                		return false;
                	}
                	if($("#userName").length>30){
                		alert('姓名长度不能超过30');
                		return false;
                	}
                	if($("#mobile").val()=='' ||isNaN($("#mobile").val())){
                		alert('请输入正确的手机号码');
                		return false;
                	}
                	if($("#mobile").length>30){
                		alert('手机号码长度不能超过20');
                		return false;
                	}
                	if($("#mailAddress").val()==''){
                		alert('请输入邮箱 ');
                		return false;
                	}
                	if($("#mailAddress").length>70){
                		alert('邮箱地址太长 ');
                		return false;
                	}
                	 if(!$("#sex1").prop('checked') && !$("#sex2").prop('checked')){//选择性别      		 				  
      					  alert("请选择性别");
      					  return false;      				  	
      				  }	
                	
                	
                	 $.ajax({
   					  url:"user/addOrUpdateHR",
   					  data:$("#hrForm").serialize(),
   				      dataType:'json',   
   				      type:'POST',
   				      success: function(result){      
   							if(result.success){
   								alert("新增成功");
   								//window.location.href="user/searchUser.do";
   								$('#hrForm')[0].reset(); 
   								window.location.reload();
   							}else{
   								alert(result.msg);
   							}
   						   
   				      }
   				   });
					$("center button").removeClass("btn-info");
					$(this).addClass("btn-info");
					
				})
            });
			
		})

	</script>
</body>
</html>