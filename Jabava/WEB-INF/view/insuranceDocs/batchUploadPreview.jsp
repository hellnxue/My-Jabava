<%@ page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <base href="${pageContext.request.contextPath}/">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- Page title -->
    <title>批量开通预览</title>
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
                                    <button type="button" class="btn btn-default btn-circle btn-lg">1</button>
                                    <p class="m-t-sm">上传模板</p>
                                </div>
                                <div class="uploadModal col-sm-4 col-md-4 col-lg-4">
                                    <button type="button" class="btn btn-info btn-circle btn-lg active">2</button>
                                    <p class="m-t-sm">上传预览</p>
                                </div>
                                <div class="complete col-sm-4 col-md-4 col-lg-4">
                                    <button type="button" class="btn btn-default btn-circle btn-lg">3</button>
                                    <p class="m-t-sm">上传完成</p>
                                </div>
                            </div>
                        </div>
                        <div class="uploadPreview clearfix"></div>
                        <!-- 预览 -->
                        <div class="empBatchAddPreview">
                            <div class="tishi clearfix m-t">
                                <p class="m-l m-t-sm">已上传文件：<span class="insetBaseInfoFileName"></span></p>
                                <p class="m-l">正确可导入数据<span style="color:#00CC00;" class="insertBaseInfoTrueMsg"></span>条
                                    <a href="javascript:void(0);" id="showDetail">查看详情>></a>
                                </p>
                                <p class="m-l">错误不可导入数据<span style="color:red;" class="insertBaseInfoFalseMsg"></span>条
                                    　 <!-- <button class="btn btn-danger btn-sm">查看详情</button> --></p>
                            </div>
                            <div class="m-l shebao-shangchuanmoban">
                                <table id="baseSocialInfo"
                                       class="table table-striped table-responsive table-condensed table-hover table-bordered dataTable"
                                       width="100%">
                                    <thead>
                                    <tr>
                                        <th>编号</th>
                                        <th>工号</th>
                                        <th>姓名</th>
                                        <th>证件号码</th>
                                        <th>社保公积金账户</th>
                                        <th>社保缴纳方式</th>
                                        <th>社保起缴月</th>
                                        <th>社保办理月</th>
                                        <th>个人社保基数</th>
                                        <th>社保企业基数</th>
                                        <th>社保规则</th>
                                        <th>公积金缴纳方式</th>
                                        <th>公积金起缴月</th>
                                        <th>公积金办理月</th>
                                        <th>个人公积金基数</th>
                                        <th>企业公积金基数</th>
                                        <th>公积金规则</th>
                                    </tr>
                                    </thead>
                                    <tbody>

                                    </tbody>
                                </table>
                            </div>
                            <div class="buttons text-center">
                                <a href="insuranceDocs/batchUpload" class="btn btn-default m-r">上一步</a>
                                <button class="btn btn-info" id="importData">提交</button>
                                <button class="btn btn-info" id="goBack" style="display: none">返回</button>
                            </div>
                        </div>
                        <!-- 预览结束 -->
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- 上传模板 end -->
    <!-- 放页脚  开始-->
    <jsp:include flush="true" page="../common/foot.div.jsp"/>
    <!-- 放页脚  结束-->
</div>
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


<script>
    $(function () {
        $.ajax({
            type: "POST",
            url: "saEmployee/getUploadInfo",
            data: {},
            dataType: "json",
            success: function (result) {
                $('.insetBaseInfoFileName').text(result.fileName);
                $('.insertBaseInfoTrueMsg').text(result.rightCount);
                $('.insertBaseInfoFalseMsg').text(result.errorCount);
                var insertBaseInfoList = result.rowData;
                var allCheck = result.allCheck;
                if (!allCheck) {
                    var importDate = $("#importData");
                    importDate.attr("disabled", "disabled");
                    importDate.css("background-color", "grey");
                    importDate.css("border-color", "grey");
                }
                insertBaseInfo($('#baseSocialInfo'), insertBaseInfoList);
            }
        });
    });

    var insertBaseInfo = function (obj, data) {

        //下面使用each进行遍历
        var rowHtml = "";
        $.each(data, function (n, value) {
            var rowTr = '<tr ';
            var tr = '';
            $.each(value, function (i, cell) {
                if ((typeof cell) == "string") {
                    tr += formatTdHtml(cell);
                } else if (i == "isOpen" && cell) {
                    /*员工已开通过cell=true*/
                    rowTr += ' style="background-color: yellow" ';
                } else if (i == "rowCheck" && !cell) {
                    /*rowCheck true 正确数据*/
                    rowTr += ' rowCheck="false" ';
                }
            });
            rowHtml += (rowTr + ">" + tr);
        });
        obj.find("tbody").html(rowHtml);
    };

    function formatTdHtml(cell) {
        if (cell.indexOf("_") > -1) {
            var strs = cell.split("_");
            var showText = strs[0].trim();
            if (strs[1].trim() == 'false') {
                var titleText = strs[2].trim();
                return '<td style="color:red" title=' + titleText + '>' + showText + '</td>';
            } else {
                return '<td>' + showText + '</td>';
            }
        } else {
            return '<td>' + cell + '</td>';
        }
    }
    $("#showDetail").click(function () {
        $("[rowCheck='false']").hide();
        $("#importData").hide();
        $("#goBack").show();
    });

    $("#goBack").click(function () {
        $("[rowCheck='false']").show();
        $("#importData").show();
        $("#goBack").hide();
    });

    $("#importData").click(function () {
        $.ajax({
            type: "POST",
            url: "saEmployee/installImport",
            data: {},
            dataType: "json",
            success: function (result) {
                if (result.resultCode == "0") {
                    window.location.href = "insuranceDocs/batchUploadComplete?total=" + result.resultTotal;
                } else {
                    swal({
                        title: '导入失败',
                        text: result.resultMsg,
                        type: 'error'
                    });
                }
            },
            error: function (data) {
                swal({
                    title: '导入失败',
                    text: "导入数据超时",
                    type: 'error'
                });
            }
        });
    });
</script>
</body>
</html>
