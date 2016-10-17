<%@ page contentType="text/html; charset=utf-8" %>
<%@page import="com.jabava.utils.RequestUtil" %>
<%
    String path = request.getContextPath();
    String basePath = "//" + request.getServerName() + ":" + request.getServerPort() + path + "/";
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

    <!--zTree-->
    <link rel="stylesheet" href="static/css/zTreeStyle/zTreeStyle.css" type="text/css">

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
                <div id="hbreadcrumb" class="m-t-xs m-b-xs">
                    <h2 class="font-normal m-b-xs text-center">
                        入职管理
                    </h2>
                </div>
            </div>
        </div>
    </div>
    <!-- 放主要内容 -->
    <div class="content animate-panel">
        <div class="row">
            <div class="col-lg-12">
                <div class="hpanel">
                    <!--添加成功并发送邮件弹框开始-->
                    <div class="modal fade hmodal-info form-row" id="myModal7" tabindex="-1" role="dialog"
                         aria-hidden="true">
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
                                                    <a onclick="toEmployeeInformation()" class="btn btn-info">完善员工信息</a>
                                                    <!--连接到完善员工信息-->
                                                    <a href="employee/to_addPerson.do"
                                                       class="btn btn-success">继续添加账号</a>
                                                    <!--连接到员工添加-->
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
                    <div class="modal fade hmodal-info form-row" id="myModal8" tabindex="-1" role="dialog"
                         aria-hidden="true">
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
                                                    <a onclick="toEmployeeInformation()" class="btn btn-info">完善员工信息</a>
                                                    <!--连接到完善员工信息-->
                                                    <a href="employee/to_addPerson.do"
                                                       class="btn btn-success">继续添加账号</a>
                                                    <!--连接到员工添加-->
                                                </center>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!--添加成功弹框结束-->

                    <!-- 组织结构树弹框 -->
                    <div class="modal fade form-row" id="myModal" tabindex="-1">
                        <div class="modal-dialog modal-sm">
                            <div class="modal-content">
                                <div class="color-line"></div>
                                <div class="text-center" style="background: #f7f9fa;padding: 5px 30px">
                                    <p><strong>组织结构树</strong></p>
                                </div>
                                <div class="modal-body" style="padding: 5px 30px">
                                    <div class="form-group">
                                        <div class="zTreeDemoBackground">
                                            <ul id="treeDemo" class="ztree"></ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- 组织结构树弹框结束 -->

                    <div class="panel-body">
                        <div class="col-sm-12 col-md-12 col-lg-12">
                            <div id="personal_div">
                                <script type="text/html" id="personal">
                                    <form action="employee/addPerson.do" id="personForm" class="form-horizontal">
                                        <div>
                                            <h4 class="m-n font-bold">
                                                <p class="small font-light m-t-md  addP-y">基本信息</p>
                                            </h4>
                                        </div>

                                        <div class="form-group">
                                            <label class="control-label col-sm-4 col-md-4 col-lg-4">姓名</label>
                                            <div class="col-sm-4 col-md-4 col-lg-4 form-required">
                                                <input type="text" class="form-control" name="employeeName">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-4 col-md-4 col-lg-4">手机</label>
                                            <div class="col-sm-4 col-md-4 col-lg-4 form-required">
                                                <input type="text" class="form-control" name="mobile">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-4 col-md-4 col-lg-4">Email</label>
                                            <div class="col-sm-4 col-md-4 col-lg-4">
                                                <input type="text" class="form-control" name="email">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-4 col-md-4 col-lg-4">证件类型</label>
                                            <div class="col-sm-4 col-md-4 col-lg-4 form-required">
                                                <select class="form-control" name="certType">
                                                    {{each certType as d i}}
                                                    <option value="{{d.commonDataCode}}">{{d.commonDataName}}
                                                    </option>
                                                    {{/each}}
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-4 col-md-4 col-lg-4">证件号码</label>
                                            <div class="col-sm-4 col-md-4 col-lg-4 form-required">
                                                <input type="text" class="form-control" name="certId">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-4 col-md-4 col-lg-4">户口类型</label>
                                            <div class="col-sm-4 col-md-4 col-lg-4 form-required">
                                                <select class="form-control" name="registerType">
                                                    <option value="1">本地城镇</option>
                                                    <option value="2">本地农村</option>
                                                    <option value="3">外地城镇</option>
                                                    <option value="4">外地农村</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-4 col-md-4 col-lg-4">户口所在地</label>
                                            <div class="col-sm-4 col-md-4 col-lg-4 form-required">
                                                <input type="text" class="form-control" name="registerLocation">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-4 col-md-4 col-lg-4">学历</label>
                                            <div class="col-sm-4 col-md-4 col-lg-4">
                                                <select class="form-control" name="degree">
                                                    {{each degree as d i}}
                                                    <option value="{{d.baseDataCode}}">{{d.baseDataName}}</option>
                                                    {{/each}}
                                                </select>
                                            </div>
                                        </div>
                                        <div class="page-header text-left m-t-md"></div>
                                        <div>
                                            <h4 class="m-n font-bold">
                                                <p class="small font-light m-t-md addP-y">工作信息</p>
                                            </h4>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-4 col-md-4 col-lg-4">员工编号</label>
                                            <div class="col-sm-4 col-md-4 col-lg-4 form-required">
                                                <input type="text" class="form-control" name="jobNumber">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-4 col-md-4 col-lg-4">入职时间</label>
                                            <div class="col-sm-4 col-md-4 col-lg-4">
                                                <div class="input-group date form-required">
                                                    <input type="text" class="form-control" name="entryDate">
                                                    <span class="input-group-addon"><i
                                                            class="glyphicon glyphicon-th"></i></span>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-4 col-md-4 col-lg-4">用工性质</label>
                                            <div class="col-sm-4 col-md-4 col-lg-4 ">
                                                <select class="form-control" name="employmentType">
                                                    {{each labor as d i}}
                                                    <option value="{{d.baseDataId}}">{{d.baseDataName}}</option>
                                                    {{/each}}
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-4 col-md-4 col-lg-4">部门名称</label>
                                            <div class="col-sm-4 col-md-4 col-lg-4 form-required">
                                                <input type="text" class="form-control" name="organizationName"
                                                       data-toggle="modal" data-target="#myModal">
                                                <input type="hidden" name="organizationId" value=""/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-4 col-md-4 col-lg-4">岗位名称</label>
                                            <div class="col-sm-4 col-md-4 col-lg-4 form-required">
                                                <select class="form-control" name="postId">
                                                    {{each post as d i}}
                                                    <option value="{{d.baseDataId}}">{{d.baseDataName}}</option>
                                                    {{/each}}
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-4 col-md-4 col-lg-4">职级</label>
                                            <div class="col-sm-4 col-md-4 col-lg-4">
                                                <select class="form-control" name="rankId">
                                                    {{each rank as d i}}
                                                    <option value="{{d.baseDataId}}">{{d.baseDataName}}</option>
                                                    {{/each}}
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-4 col-md-4 col-lg-4">汇报对象</label>
                                            <div class="col-sm-4 col-md-4 col-lg-4">
                                                <input type="hidden" name="reportObject">
                                                <input type="text" class="form-control" name="reportObjectName">
                                                <span class="help-block" data-help-for="reportObject"></span>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-4 col-md-4 col-lg-4">社保缴纳地</label>
                                            <div class="col-sm-4 col-md-4 col-lg-4 form-required">
                                                <select class="form-control"
                                                        style="float: left;width:47%;margin-right: 6%" name="provinceId"
                                                        id="province">
                                                    <option value="">请选择</option>
                                                    {{each province as d i}}
                                                    <option value="{{d.provinceId}}">{{d.provinceName}}</option>
                                                    {{/each}}
                                                </select>
                                                <select class="form-control" style="width:47%;"
                                                        name="socialSecurityLocation"
                                                        id="addLocationCity">
                                                    <option value="">请选择</option>
                                                    {{each city as d i}}
                                                    <option value="{{d.baseDataId}}">{{d.baseDataName}}</option>
                                                    {{/each}}
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-4 col-md-4 col-lg-4"></label>
                                            <div class="col-sm-4 col-md-4 col-lg-4">
                                                <label class="checkbox-inline">
                                                    <input type="checkbox" name="isOpen" id="isOpen" value="1"/>
                                                    邀请员工完善基本信息
                                                </label>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-4 col-md-4 col-lg-4"></label>
                                            <div class="col-sm-4 col-md-4 col-lg-4">
                                                <% if (RequestUtil.hasPower("entry_ae")) { %>
                                                <button id="zy-emp-add-user" type="submit"
                                                        class="btn btn-success btn-block"
                                                        data-text="staff">添加员工
                                                </button>
                                                <% } %>
                                            </div>
                                        </div>
                                    </form>
                                </script>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <input type="hidden" id="personId"/>
        <!--主要内容结束-->
        <!-- Footer-->
        <!-- 放页脚  开始-->
        <jsp:include flush="true" page="../common/foot.div.jsp"></jsp:include>
        <!-- 放页脚  结束-->
    </div>
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
<script src="static/bootstrap/vendor/bootstrap-datepicker-master/dist/js/bootstrap-datepicker.js"></script>

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

<!--zTree-->
<script type="text/javascript" src="static/js/zTreeJs/zTreeJs/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="static/js/zTreeJs/zTreeJs/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript" src="static/js/zTreeJs/zTreeJs/jquery.ztree.exedit-3.5.js"></script>

<!--jabava-->
<script src="static/js/staff.js"></script>

<script src="static/js/template.js"></script>

<!--ztree组织结构树-->
<script type="text/javascript">
    var setting = {
        view: {
            dblClickExpand: false,
            showLine: false
        },
        callback: {
            onClick: zTreeOnClick
        },
        data: {
            key: {
                name: "organizationName"
            },
            simpleData: {
                enable: true,
                idKey: "organizationId",
                pIdKey: "parentId"
            }
        }
    };


    function zTreeOnClick(event, treeId, treeNode) {
        $("input[name='organizationId']").val(treeNode.organizationId);
        $("input[name='organizationName']").val(treeNode.organizationName);
        $('#myModal').modal('hide');
        $('#personForm')
                .formValidation('revalidateField', 'organizationName');
    }
</script>
<!--汇报对象-->
<script>
    $(function () {

        $('[name="reportObjectName"]').on('keyup', function (event) {
            var markup = '<div class="list-group col-sm-11" data-help-for="reportObject">'

            $.ajax({
                url: 'employee/searchBySearch',
                type: 'POST',
                dataType: 'json',
                data: {search: $(this).val()},
                delay: 100
            })
                    .done(function (d) {
                        $.each(d, function (index, val) {
                            markup += '<a href="javascript://" class="list-group-item"  data-personId="' + val.personId + '" data-name="' + val.employeeName + '">';
                            markup += val.employeeName + '<span class="m-l-sm">' + (val.postName ? val.postName : '') + '</span>';
                            markup += '</a>'
                        });
                        markup += '</div>';
                        $('[data-help-for="reportObject"]').replaceWith(markup);

                    })
                    .always(function (d) {
                        $('[data-help-for="reportObject"]')
                                .css({
                                    position: 'absolute',
                                    zIndex: '996',
                                    backgroundColor: '#f7f9fa',
                                    maxHeight: '160px',
                                    overflow: 'auto',
                                    padding: 0
                                })
                                .find('.list-group-item')
                                .each(function (index, el) {
                                    $(this).off().on('click', function (event) {
                                        event.preventDefault();
                                        $(this).parent('[data-help-for="reportObject"]').hide();
                                        $('[name="reportObjectName"]').val($(this).attr('data-name'));
                                        $('[name="reportObject"]').val($(this).attr('data-personId'))
                                    });
                                });

                    });
        });
    });
</script>

<script>
    // onclick="savePerson(this)"
    function savePerson(e) {
        var infoEditable = $('#isOpen').is(':checked');
        var formData = $('#personForm').serialize();
        if (!infoEditable) {
            formData = formData + '&isOpen=0'
        }

        $.ajax({
            cache: true,
            type: "POST",
            url: "employee/addPerson.do",
            data: formData,
            dataType: 'json',
            async: false,
            success: function (data) {
                if (data.success == false) {
                    swal(data.msg, undefined, "error");
                    swal({
                        title: data.msg,
                        type: 'error'
                    }, function () {
                        $('#personForm').formValidation('resetForm');
                    })
                    //$THIS.on('click',savePerson);
                } else {
                    $("#personId").val(data.personId);
                    var name = $("#personForm").find("[name='employeeName']").val();
                    if (infoEditable) {
                        var email = $("#personForm").find("[name='email']").val();
                        $("#success_name").text("员工账号“" + name + "”已添加成功！");
                        $("#success_email").text("登录密码和网址已通过邮箱和手机发送给该员工。");
                        $('#myModal7').modal('show');
                    } else {
                        $("#success_name2").text("员工账号“" + name + "”已添加成功！");
                        $('#myModal8').modal('show');
                    }
                }
            }
        });
    }

    function toEmployeeInformation() {
        var personId = $("#personId").val();
        if (personId != "") {
            location.href = "<%=basePath%>employee/employeeInformation.do?personId=" + personId;
        }
    }

    $(function () {
        $(".guanbi").click(function () {
            $("#myModal7").modal('hide');
            $(".content_clear").val("");
        });

        $('#isOpen').on('change', function (e) {
            if ($('#isOpen').prop('checked')) {
                $('[data-text="staff"]').text('添加员工并发送邮件通知')
            } else {
                $('[data-text="staff"]').text('添加员工')
            }
        });

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
                organizationName: {
                    validators: {
                        notEmpty: {
                            message: '请填写必填项'
                        }
                    }
                },
                entryDate: {
                    validators: {
                        notEmpty: {
                            message: '请填写必填项'
                        }
                    }
                },
                employeeName: {
                    validators: {
                        notEmpty: {
                            message: '请填写必填项'
                        }
                    }
                },
                registerType: {
                    validators: {
                        notEmpty: {
                            message: '请填写必填项'
                        }
                    }
                },
                registerLocation: {
                    validators: {
                        notEmpty: {
                            message: '请填写必填项'
                        }
                    }
                },
                jobNumber: {
                    validators: {
                        notEmpty: {
                            message: '请填写必填项'
                        }
                    }
                },
                employmentType: {
                    validators: {
                        notEmpty: {
                            message: '请填写必填项'
                        }
                    }
                },
                organizationId: {
                    validators: {
                        notEmpty: {
                            message: '请填写必填项'
                        }
                    }
                },
                postId: {
                    validators: {
                        notEmpty: {
                            message: '请填写必填项'
                        }
                    }
                },
                provinceId: {
                    validators: {
                        notEmpty: {
                            message: '请填写必填项'
                        }
                    }
                },
                socialSecurityLocation: {
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
                certType: {
                    validators: {
                        notEmpty: {
                            message: '请填写必填项目'
                        }
                    }
                },
                certId: {
                    validators: {
                        notEmpty: {
                            message: '请填写必填项目'
                        },
                        callback: {
                            message: '请输入有效的身份证号',
                            callback: function (value, validator, $field) {
                                return IDValidator.isValid(value);
                            }
                        }
                    }
                }
            }
        })
                .on('success.form.fv', function (e) {
                    e.preventDefault();
                    savePerson()
                })
                .on('change', '[name="certType"]', function (event) {
                    if ($(event.target).val() == 1) {
                        $(event.target).parents('form')
                                .formValidation('enableFieldValidators', 'certId', true, 'callback')
                                .formValidation('revalidateField', 'certId');
                    } else {
                        $(event.target).parents('form')
                                .formValidation('enableFieldValidators', 'certId', false, 'callback')
                                .formValidation('revalidateField', 'certId');
                    }
                });

    });

    function showPersonInfo() {
        $.ajax({
            cache: true,
            type: "POST",
            url: "employee/dataDictionary.do",
            dataType: 'json',
            async: false,
            success: function (data) {
                $("#personal_div").html(template('personal', data));
                $.fn.zTree.init($("#treeDemo"), setting, data.orgs);
                var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
                var nodes = treeObj.getNodes();
                for (var i = 0; i < nodes.length; i++) { //设置节点展开
                    treeObj.expandNode(nodes[i], true, false, true);
                }
            }
        });
        $('.input-group.date')
                .datepicker({
                    format: "yyyy-mm-dd",
                    autoclose: true
                })
                .on('change', '[name="entryDate"]', function (event) {
                    $(event.target).parents('form').formValidation('revalidateField', 'entryDate');
                });


    }
    showPersonInfo();

    $(function () {
        //选择省份显示城市
        $("#province").change(function () {
            var provinceId = $(this).val();

            if (provinceId == '请选择') {
                return false;
            }

            $.ajax({
                type: "POST",
                url: "socialsecurityaccount/getCityByProvinceId",
                data: {provinceId: provinceId},
                dataType: "json",
                success: function (result) {
                    $("#addLocationCity").empty();
                    $("#addLocationCity").append('<option>请选择</option>');
                    for (var i = 0; i < result.length; i++) {
                        $("#addLocationCity").append("<option value='" + result[i].cityId + "'>" + result[i].cityName + "</option>");
                    }
                    $('#addLocationCity').select2('val', '请选择');
                }
            });
        });
    });

</script>

</body>
</html>