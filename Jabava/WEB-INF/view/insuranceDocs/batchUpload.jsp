<%@ page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <base href="${pageContext.request.contextPath}/">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- Page title -->
    <title>批量上传模板</title>
    <jsp:include flush="true" page="../common/styles.jsp"/>
</head>
<body>
<!--splash screen-->
<jsp:include flush="true" page="../common/splashscreen.div.jsp"/>
<!--引入头文件 开始-->
<jsp:include flush="true" page="../common/header.div.jsp"/>
<!--引入头文件 结束-->
<!--引入菜单文件 开始-->
<jsp:include flush="true" page="../common/menu.div.jsp"/>
<!--引入菜单文件 结束-->

<!-- Main Wrapper -->
<div id="wrapper">
    <!-- 放主要内容 -->
    <div class="content animate-panel">
        <!-- 上传模板 -->
        <div class="row">
            <div class="col-lg-12">
                <div class="hpanel">
                    <div class="panel-heading">
                        <h4 class="text-center font-bold">
                            批量导入
                            <a class="btn btn-success btn-sm pull-left" type="button"
                               href="employee/securityProfile">返回</a>
                        </h4>
                    </div>
                    <div class="panel-body">
                        <div class="col-sm-12 col-md-12 col-lg-12 text-center m-t-lg">
                            <div class="liner"></div>
                            <div class="slicks">
                                <div class="uploadModal col-sm-4 col-md-4 col-lg-4">
                                    <button type="button" class="btn btn-info btn-circle btn-lg active">1</button>
                                    <p class="m-t-sm">上传模板</p>
                                </div>
                                <div class="uploadModal col-sm-4 col-md-4 col-lg-4">
                                    <button type="button" class="btn btn-default btn-circle btn-lg">2</button>
                                    <p class="m-t-sm">上传预览</p>
                                </div>
                                <div class="complete col-sm-4 col-md-4 col-lg-4">
                                    <button type="button" class="btn btn-default btn-circle btn-lg">3</button>
                                    <p class="m-t-sm">上传完成</p>
                                </div>
                            </div>
                        </div>
                        <div class="uploadPreview clearfix"></div>
                        <!-- 模板 -->
                        <ul class="clearfix m-t-sm empBatchAddUpload">
                            <h5 class="text-info m-t">步骤一：选择社保公积金账户</h5>
                            <ul class="m-l m-t-sm">
                                <li class="col-sm-12 col-md-12 col-lg-12"> 将模板内的人员批量增加到所选的社保公积金账户中进行管理。</li>
                                <li class="col-sm-12 col-md-12 col-lg-12">
                                    <label style="float: left;">社保公积金账户</label>
                                    <span class="col-sm-2 col-md-2 col-lg-2 form-required">
                                        <select style="width: 100%" id="accountSel">
                                            <option></option>
                                        </select>
                                    </span>
                                </li>
                            </ul>
                            <h5 class="text-info m-t">步骤二：下载导入模板</h5>
                            <ul class="m-l m-t-sm">
                                <li> 1、此模板为批量开通模板，调整和停缴请通过社保档案进行操作。</li>
                                <li> 2、规则对照为您企业中使用的社保公积金规则标准名称表，请按规则对照中的规则名称填入模板中。</li>
                                <li> 3、模板中的编号、工号、姓名、证件号码为必填项。</li>
                                <li> 4、如果上传过程中出现错误数据，请修改模板后重新上传。</li>
                                <li>
                                    <a href="static/xls/JabavaSocialAccumulation.xlsx" type="button"
                                       class="btn btn-info">
                                        <i class="pe pe-7s-bottom-arrow pe-lg"></i>
                                        <span>下载模板</span>
                                    </a>
                                    <a href="saEmployee/exportGroupRule" type="button" class="btn btn-info">
                                        <i class="pe pe-7s-bottom-arrow pe-lg"></i>
                                        <span>规则对照</span>
                                    </a>
                                </li>
                            </ul>
                            <h5 class="text-info m-t">步骤三：上传文件</h5>
                            <div class="panels m-l">
                                  <span class="file-input">
                                    <div class="btn btn-md btn-file  m-t" data-toggle="upload:file">
                                      <i class="pe pe-7s-up-arrow pe-lg"></i>
                                       上传文件
                                      <input id="file" type="file" data-action="file">
                                    </div>
                                  </span>
                            </div>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- 放页脚  开始-->
    <jsp:include flush="true" page="../common/foot.div.jsp"/>
    <!-- 放页脚  结束-->
</div>
<!-- 上传模板 end -->


<!-- Vendor scripts -->
<script src="static/bootstrap/vendor/jquery/dist/jquery.min.js"></script>
<script src="static/bootstrap/vendor/jquery-ui/jquery-ui.min.js"></script>
<script src="static/bootstrap/vendor/slimScroll/jquery.slimscroll.min.js"></script>
<script src="static/bootstrap/vendor/bootstrap/dist/js/bootstrap.min.js"></script>
<script src="static/bootstrap/vendor/metisMenu/dist/metisMenu.min.js"></script>
<script src="static/bootstrap/vendor/iCheck/icheck.min.js"></script>
<script src="static/bootstrap/vendor/sparkline/index.js"></script>
<script src="static/bootstrap/vendor/nestable/jquery.nestable.js"></script>
<script src="static/bootstrap/vendor/moment/moment.js"></script>
<script src="static/bootstrap/scripts/homer.js"></script>
<script src="static/bootstrap/vendor/datatables/media/js/jquery.dataTables.min.js"></script>
<script src="static/bootstrap/vendor/datatables_plugins/integration/bootstrap/3/dataTables.bootstrap.min.js"></script>

<!-- alert -->
<script src="static/bootstrap/vendor/sweetalert/lib/sweet-alert.min.js"></script>
<script src="static/bootstrap/vendor/toastr/build/toastr.min.js"></script>
<!-- 表单验证 -->
<script src="static/js/plugins/form.validation/js/formValidation.js"></script>
<script src="static/js/plugins/form.validation/js/framework/bootstrap.js"></script>
<script src="static/js/plugins/form.validation/js/language/zh_CN.js"></script>
<script src="static/js/common/ajaxfileupload.js"></script>
<script>

    var fileObj = $("#file");
    fileObj.attr("disabled", "disabled");
    fileObj.parent().attr("disabled", "disabled");
    fileObj.parent().css("background-color", "#CFCFCF");
    var accountObj = $("#accountSel");
    $(function () {
        $.ajax({
            type: "POST",
            url: "socialAccumulationAccount/getAccountByCompanyId",
            data: {},
            dataType: "json",
            success: function (result) {
                accountObj.empty();
                accountObj.append('<option value="">请选择</option>');
                var accounts = result.result.accountList;
                for (var i = 0; i < accounts.length; i++) {
                    accountObj.append("<option value='" + accounts[i].accountId + "'>" + accounts[i].accountName + "</option>");
                }
            }
        });
    });

    accountObj.change(function () {
        if (accountObj.val() != '') {
            fileObj.removeAttr("disabled");
            fileObj.parent().removeAttr("disabled");
            fileObj.parent().addClass("btn-info");
            fileObj.parent().css("background-color", "");
        } else {
            fileObj.attr("disabled", "disabled");
            fileObj.parent().attr("disabled", "disabled");
            fileObj.parent().removeClass("btn-info");
            fileObj.parent().css("background-color", "#CFCFCF");
        }

    });

</script>
<script>
    /*文件上传*/
    $(function () {
        var $uploadCompent = $('[data-toggle="upload:file"]'),
                $fileInput = $('[data-action="file"]');
        var fileUpload = function () {
            $fileInput.on('change.file:selected', function (e) {
                var uploadFile = $fileInput[0].files[0];
                if (uploadFile.name != null && uploadFile.name != '') {
                    var postfix = uploadFile.name.split('.')[1];
                    if (postfix != 'xlsx' && postfix != 'xls') {
                        swal({
                            title: '上传失败',
                            text: "上传的文件类型有误,请重新上传",
                            type: 'error'
                        });
                    } else {
                        var formData = new FormData();
                        formData.append("uploadFiles", uploadFile);
                        formData.append("fileName", uploadFile.name);
                        formData.append("accountName", $("#accountSel option:selected").text());
                        formData.append("accountId", $("#accountSel").val());
                        $.ajax({
                            url: "saEmployee/checkInsertImport",
                            type: 'POST',
                            cache: false,
                            dataType: 'json',
                            data: formData,
                            processData: false,
                            contentType: false
                        }).done(function (result) {
                            if (result.success) {
                                window.location.href = "saEmployee/toUploadPreview";
                            } else {
                                swal({
                                    title: '上传失败',
                                    text: result.msg,
                                    type: 'error'
                                }, function(){
                                    window.location.href = "insuranceDocs/batchUpload";
                                });
                            }
                        }).fail(function (data) {
                            swal({
                                title: '上传失败',
                                text: "文件上传有误!",
                                type: 'error'
                            });

                        });

                    }
                } else {
                    swal({
                        title: '上传失败',
                        text: "上传的文件类型有误,请重新上传",
                        type: 'error'
                    });
                }

            });
        };

        $uploadCompent.on('click', function (e) {
            fileUpload();
        });
    })
</script>
<script>

</script>
</body>
</html>
