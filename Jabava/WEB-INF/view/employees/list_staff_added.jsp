<%@ page contentType="text/html; charset=utf-8" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge">

<!-- Page title -->
<title>Jabava员工添加界面</title>

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
		<div class="normalheader transition animated fadeIn small-header">
			<div class="hpanel">
				<div class="panel-body">
					<!-- 
            <a class="small-header-action" href="">
                <div class="clip-header">
                    <i class="fa fa-arrow-up"></i>
                </div>
            </a> -->

					<div id="hbreadcrumb" class="pull-right m-t-lg">
						<ol class="hbreadcrumb breadcrumb">
							<li><a href="to_index?jump=1">首页</a></li>
							<li><span>员工信息</span></li>
							<li class="active"><span>员工添加</span></li>
						</ol>
					</div>
					<h2 class="font-light m-b-xs">员工信息</h2>
					<small>待 定</small>
				</div>
			</div>
		</div>

		<!-- 放主要内容 -->
        <div class="content animate-panel">
            <div class="row">
                <div class="col-lg-12">
                    <div class="hpanel">
                        <div class="panel-heading org_padding_bottom">
                           <div class="pull-right org_margin_top">
                             &nbsp;
                           </div>
                           
                           <!--<div></div>-->
                        </div>
                        
                        
                        <!--添加成功并发送邮件弹框开始-->    
                        <div class="modal fade hmodal-success form-row" id="myModal7" tabindex="-1" role="dialog"  aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="color-line"></div>
                                    <div class="modal-header org_Tmargin">
                                    	<div class="row">
                                        	<div class="col-sm-12">
                                            	<form role="form" class=" form-horizontal">
                                                	<div class="form-group lmaigin" >
                                                    	<h4 class="col-lg-12 org_h4">员工添加成功</h4>
                                                    </div>
                                                    <div class="form-group staff_b5margin">
                                                    	<label class="col-lg-12 staff_success" id="success_name">员工账号“李想”已添加成功！</label>
                                                    </div>
                                                    <div class="form-group staff_nomargin">
                                                    	<label class="col-lg-12 staff_success_tow" id="success_email">开通邮件和初始密码已经发送到其邮箱“XXXXXX@163.com”</label>
                                                    </div>
                                                    <div class="form-group">
                                                    	<label class="col-lg-12 staff_success_tow">该员工可以通过账号和密码登录系统，完善其个人资料。</label>
                                                    </div>
                                                    <center class="hellow_two">
                                                        <a href="<%=basePath%>employee/to_addPerson.do"><label class="staff_email_conment staff_email_five">继续添加账号</label></a><!--连接到员工添加-->
                                            			<a onclick="toEmployeeInformation()"><label class="staff_email_conment staff_email_six">完善员工信息</label></a><!--连接到完善员工信息-->
                                                    </center>
                              					</form>
                                            </div>
                                        </div>   
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!--添加成功并发送邮件弹框结束--> 
                        
                        <!--添加成功弹框开始--> 
                        <div class="modal fade hmodal-success form-row" id="myModal8" tabindex="-1" role="dialog"  aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="color-line"></div>
                                    <div class="modal-header org_Tmargin">
                                    	<div class="row">
                                        	<div class="col-sm-12">
                                            	<form role="form" class=" form-horizontal">
                                                	<div class="form-group lmaigin" >
                                                    	<h4 class="col-lg-12 org_h4">员工添加成功</h4>
                                                    </div>
                                                    <div class="form-group staff_b5margin">
                                                    	<label class="col-lg-12 staff_success" id="success_name2">员工账号“李想”已添加成功！</label>
                                                    </div>
                                                    <center class="hellow_two">
                                                        <a href="<%=basePath%>employee/to_addPerson.do"><label class="staff_email_conment staff_email_five">继续添加账号</label></a><!--连接到员工添加-->
                                            			<a onclick="toEmployeeInformation()"><label class="staff_email_conment staff_email_six">完善员工信息</label></a><!--连接到完善员工信息-->
                                                    </center>
                              					</form>
                                            </div>
                                        </div>   
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!--添加成功弹框结束-->
         
                        <div class="panel-body">
                            <div class="col-sm-12 staff_new_margin">
                            <form action="employee/addPerson.do" id="personForm">
                                <div class="form-group clearfix">
                                	<label class="col-lg-3">&nbsp;</label>
                                    <label for="exampleInputName1" class="col-lg-1 staff_align">姓名：</label>
                                    <div class="col-lg-4">
                                    	<input type="text" class="form-control" name="employeeName">
                                    </div>
                                    <label class="col-lg-1 hong staff_padding_tow">*</label>
                                </div>
                                <div class="form-group clearfix">
                                	<label class="col-lg-3">&nbsp;</label>
                                    <label for="exampleInputName2" class="col-lg-1 staff_align">手机：</label>
                                    <div class="col-lg-4">
                                    	<input type="text" class="form-control" name="mobile">
                                    </div>
                                    <label class="col-lg-1 hong staff_padding_tow">*</label>
                                </div>
                                <div class="form-group clearfix">
                                	<label class="col-lg-3">&nbsp;</label>
                                    <label for="exampleInputName3" class="col-lg-1 staff_align">Email：</label>
                                    <div class="col-lg-4">
                                    	<input type="text" class="form-control" name="email">
                                    </div>
                                    <label class="col-lg-1 hong staff_padding_tow">*</label>
                                </div>
                            </div>
                            <div class="col-sm-12 staff_new_margin">
                                <div class="form-group clearfix">
                                	<label class="col-lg-4">&nbsp;</label>
                                    <div class="col-lg-4">
                                    	<!-- 按钮是开还是关，1为打开，0为关闭 -->
                                   		<input type="hidden" name="isOpen" id="isOpen" value="0"/>
                                       	<button class="staff_open" type="button" value="打开"></button>
                                        <button class="staff_close" type="button" value="关闭"></button>
                                        <span class="staff_margin">基本信息员工修改权限</span>
                                    </div>
                                </div>
                            </div>
                            
                            <div class="col-sm-12 staff_new_margin2">
                                <div class="form-group clearfix">
                                	<div class="col-lg-4">
                                		<label class="staff_email_four">&nbsp;</label>
                                    </div>
                                    <div class="col-lg-4 hellow">
                                        <label class="staff_email_conment staff_email" onclick="savePerson(1, this)" data-toggle="modal">添加员工并发送邮件通知</label>
                                        <!--<label class="staff_email_conment staff_email_tow" data-target="#myModal7" data-toggle="modal">邀请新员工并发送邮件通知</label>-->
                                        <label class="staff_email_conment staff_email_third" onclick="savePerson(0, this)" data-toggle="modal">添加员工</label>
                                    </div>
                                </div>
                            </div>
                        </form>    
                        </div>
                        
       				</div>
        		</div>
        	</div>
        </div>           
        <input type="hidden" id="personId" />
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
    <!--树状-->
    <script src="static/bootstrap/vendor/nestable/jquery.nestable.js"></script>
    <!--树状结束-->
    
	<!-- for datatable -->
	<script src="static/bootstrap/vendor/datatables/media/js/jquery.dataTables.min.js"></script>
	<script src="static/bootstrap/vendor/datatables_plugins/integration/bootstrap/3/dataTables.bootstrap.min.js"></script>

	<script src="static/bootstrap/vendor/jquery-validation/jquery.validate.min.js"></script>
	<!-- alert -->
	<script src="static/bootstrap/vendor/sweetalert/lib/sweet-alert.min.js"></script>
    
	<!-- App scripts -->
	<script src="static/bootstrap/scripts/homer.js"></script>
    <script src="static/bootstrap/scripts/charts.js"></script>
    
    <!--jabava-->
    <script src="static/js/staff.js"></script>
    
<!--取消效果-->
<script>
  $(".guanbi").click(function(){
	 $("#myModal7").modal('hide');
	 $(".content_clear").val("");
  })
  
  function savePerson(isOpen, btn){
	  if($("#personForm").validate().form()){
		  $("#isOpen").val(isOpen);
		  var b = false;
		  $.ajax({
		        cache: true,
		        type: "POST",
		        url:"employee/addPerson.do",
		        data:$('#personForm').serialize(),// form
		        dataType: 'json',
		        async: false,
		        success: function(data) {
		            b = data.success;
		            if(b == false){
		                alert(data.msg);
		            }else{
		            	$("#personId").val(data.personId);
		            }
		        }
	      });
		  if(b == true){//后台发邮件或短信不成功时，不弹出成功框
			  var name = $("#personForm").find("[name='employeeName']").val();
			  if(isOpen == 1){
				  var email = $("#personForm").find("[name='email']").val();
				  $("#success_name").text("员工账号“"+name+"”已添加成功！");
				  $("#success_email").text("开通邮件和初始密码已经发送到其邮箱“"+email+"”");
				  $(btn).attr("data-target", "#myModal7");
			  }else{
				  $("#success_name2").text("员工账号“"+name+"”已添加成功！");
				  $(btn).attr("data-target", "#myModal8");
			  }
		  }
	  }
  }
  
  function toEmployeeInformation(){
	  var personId = $("#personId").val();
	  if(personId != ""){
		  location.href = "<%=basePath%>employee/employeeInformation.do?personId="+personId;
	  }
  }
</script>

<script type="text/javascript">
	$(function(){
		var form = $("#personForm").validate({
			rules:{
				employeeName : "required",
				mobile:{
					required:true,
					digits:true,
					rangelength:[11,11]
				},
				email:{
					required:true,
					email:true
				}
			},
			messages:{
				employeeName : "请输入姓名",
				mobile:{
					required:"请输入手机号",
					digits:"手机号为11位的数字",
					rangelength:"手机号为11位的数字"
				},
				email:{
					required:"请输入email",
					email:"请输入正确的email格式"
				}
			}
		});
	})
</script>

</body>
</html>