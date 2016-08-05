<%@ page contentType="text/html; charset=utf-8" %>
<%@page import="com.jabava.utils.RequestUtil"%>
<%
String path = request.getContextPath();
String basePath = "//"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
<head>
	<base href="${pageContext.request.contextPath}/">
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">

	<!-- Page title -->
	<title>Jabava员工添加界面</title>
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
                    <div class="hpanel">
                        <div class="panel-heading hbuilt jabava-built p-lg">
                            <h4 class="m-n font-bold">入职管理
                                <p class="small font-light m-t-md">员工个人信息将邮件和短信通知员工填写，建议新入职员工使用。</p>
                            </h4>
                        </div>
                        <!--添加成功并发送邮件弹框开始-->
                        <div class="modal fade hmodal-info form-row" id="myModal7" tabindex="-1" role="dialog"  aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="color-line"></div>
                                    <div class="modal-header org_Tmargin">
                                        <div class="row">
                                            <div class="col-sm-12">
                                                <form role="form" class=" form-horizontal">
                                                    <div class="form-group m-b text-center">
                                                        <h4 class="font-bold" id="success_name">员工账号“李想”已添加成功！</h4>
                                                    </div>
                                                    <div class="col-lg-12 text-center">
                                                        <p id="success_email">开通邮件和初始密码已经发送到其邮箱“XXXXXX@163.com”</p>
                                                    </div>
                                                    <div class="col-lg-12 text-center m-b">
                                                        <p>该员工可以通过“手机+密码”登录系统，完善其个人资料。</p>
                                                    </div>
                                                    <div class="col-lg-12 text-center">
                                                        <a onclick="toEmployeeInformation()" class="btn btn-info">完善员工信息</a><!--连接到完善员工信息-->
                                                        <a href="employee/to_addPerson.do" class="btn btn-success">继续添加账号</a><!--连接到员工添加-->
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!--添加成功并发送邮件弹框结束-->

                        <!--添加成功弹框开始-->
                        <div class="modal fade hmodal-info form-row" id="myModal8" tabindex="-1" role="dialog"  aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="color-line"></div>
                                    <div class="modal-header org_Tmargin">
                                        <div class="row">
                                            <div class="col-sm-12">
                                                <form role="form" class=" form-horizontal">
                                                    <div class="form-group m-b text-center">
                                                        <h4 class="font-bold" id="success_name2">员工账号“李想”已添加成功！</h4>
                                                    </div>
                                                    <center class="hellow_two">
                                                        <a onclick="toEmployeeInformation()" class="btn btn-info">完善员工信息</a><!--连接到完善员工信息-->
                                                        <a href="employee/to_addPerson.do" class="btn btn-success">继续添加账号</a><!--连接到员工添加-->
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
                            <div class="col-sm-offset-3 col-md-offset-3 col-lg-offset-3 col-sm-6 col-md-6 col-lg-6">
                                <form action="employee/addPerson.do" id="personForm" class="form-horizontal">
                                    <div class="form-group">
                                        <label class="control-label col-sm-4 col-md-3 col-lg-3">姓名：</label>
                                        <div class="col-sm-8 col-md-9 col-lg-9 form-required">
                                            <input type="text" class="form-control" name="employeeName">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-sm-4 col-md-3 col-lg-3">手机：</label>
                                        <div class="col-sm-8 col-md-9 col-lg-9 form-required">
                                            <input type="text" class="form-control" name="mobile">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-sm-4 col-md-3 col-lg-3">Email：</label>
                                        <div class="col-sm-8 col-md-9 col-lg-9 form-required">
                                            <input type="text" class="form-control" name="email">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-sm-4 col-md-3 col-lg-3">身份证：</label>
                                        <div class="col-sm-8 col-md-9 col-lg-9 form-required">
                                            <input type="text" class="form-control" name="certId">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-offset-4 col-md-offset-3 col-lg-offset-3 col-sm-8 col-md-9 col-lg-9">
                                            <label class="checkbox-inline">
                                                <input type="checkbox" name="isOpen" id="isOpen" value="1"/>
                                                开通员工修改基本信息的权限
                                            </label>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-offset-4 col-md-offset-3 col-lg-offset-3 col-sm-8 col-md-9 col-lg-9">
                                            <% if(RequestUtil.hasPower("entry_ae")){ %>
                                            <button id="zy-emp-add-user" type="submit" class="btn btn-success btn-block" data-text="staff">添加员工</button>
                                            <% } %>
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
    
    <!-- 表单验证 -->
    <script src="static/js/plugins/form.validation/js/formValidation.js"></script>
    <script src="static/js/plugins/form.validation/js/framework/bootstrap.js"></script>
    <script src="static/js/plugins/form.validation/js/language/zh_CN.js"></script> 
    <script src="static/js/plugins/form.validation/js/formValidationExtend.js"></script> 
    <!--jabava-->
    <script src="static/js/staff.js"></script>

<script>
    // onclick="savePerson(this)"

    function savePerson(e){
            var infoEditable = $('#isOpen').is(':checked');
            var formData = $('#personForm').serialize();
            if(!infoEditable){
                formData = formData + '&isOpen=0'
            }
    
            //var $THIS = $(this);
            //$(this).off('click');

            $.ajax({
                cache: true,
                type: "POST",
                url:"employee/addPerson.do",
                data: formData,
                dataType: 'json',
                async: false,
                success: function(data){
                    if(data.success == false){
                        swal(data.msg, undefined, "error");
                        swal({
                            title: data.msg,
                            type: 'error'
                        },function(){
                            $('#personForm').formValidation('resetForm');
                        })
                        //$THIS.on('click',savePerson);
                    }else{
                        $("#personId").val(data.personId);
                        var name = $("#personForm").find("[name='employeeName']").val();
                        if(infoEditable){
                            var email = $("#personForm").find("[name='email']").val();
                            $("#success_name").text("员工账号“"+name+"”已添加成功！");
                            $("#success_email").text("登录密码和网址已通过邮箱和手机发送给该员工。");
                            $('#myModal7').modal('show');
                        }else{
                            $("#success_name2").text("员工账号“"+name+"”已添加成功！");
                            $('#myModal8').modal('show');
                        }
                    }
                }
            });
  }

  function toEmployeeInformation(){
      var personId = $("#personId").val();
      if(personId != ""){
          location.href = "<%=basePath%>employee/employeeInformation.do?personId="+personId;
      }
  }

  $(function(){
    $(".guanbi").click(function(){
       $("#myModal7").modal('hide');
       $(".content_clear").val("");
    })

    $('#isOpen').on('change',function(e){
        if($('#isOpen').prop('checked')){
          $('[data-text="staff"]').text('添加员工并发送邮件通知')
        } else{
          $('[data-text="staff"]').text('添加员工')
        }
    })

    $("#personForm").formValidation({
        framework: 'bootstrap',
        err: {
            container: 'tooltip'
        },
        icon: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            employeeName: {
                validators: {
                    notEmpty: {
                        message: '请填写必填项'
                    }
                }
            },
            mobile: {
                validators: {
                    notEmpty: {
                        message: '请填写必填项'
                    },
                    phone: {
                        country: 'CN',
                        message: '请输入中国区域的手机或电话号码'
                    }
                }
            },
            email: {
                validators: {
                    notEmpty: {
                        message: '请填写必填项'
                    },
                    regexp: {
                        message: '请输入有效的邮箱',
                        regexp: /^([a-zA-Z0-9_\-\.])+@(\w)+((\.\w+)+)$/
                    }
                }
            },
            certId: {
                validators: {
                    callback: {
                        message: '请输入有效的身份证号',
                        callback: function(value, validator, $field){
                            return IDValidator.isValid(value);
                        }
                    }
                }
            }
        }
    })
    .on('success.form.fv', function(e){
        e.preventDefault();
        savePerson()
    })

  })
</script>

</body>
</html>