<%--suppress HtmlUnknownTarget --%>
<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <base href="${pageContext.request.contextPath}/">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <!-- Page title -->
    <title>社保公积金户管理</title>
    <jsp:include flush="true" page="../common/styles.jsp"/>
    <!--for 临时改变-->
    <link rel="stylesheet" href="static/js/plugins/form.validation/css/formValidation.css">
    <link rel="stylesheet" href="static/css/user.css">

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

<!-- 放主要内容  开始-->
<!-- Main Wrapper -->
<div id="wrapper">
    <div class="normalheader transition animated fadeIn small-header">
        <div class="hpanel">
            <div class="panel-body">
                <div id="hbreadcrumb" class="m-t-xs m-b-xs">
                    <h2 class="font-normal m-b-xs text-center">
                        社保公积金账户
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
                    <!--新增弹框-->

                    <!--新增弹框 end-->
                    <!-- 修改 -->

                    <!-- 修改end -->

                    <div class="panel-body m-t-md">
                        <table id="projectTable" class="table table-condensed table-hover table-bordered dataTable"
                               width="100%">
                            <thead>
                            <tr>
                                <th>社保公积金账户名称</th>
                                <th>类型</th>
                                <th>参保地</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>

                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!--主要内容结束-->

    <!-- 放页脚  开始-->
    <jsp:include flush="true" page="../common/foot.div.jsp"/>
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
<script src="static/bootstrap/vendor/bootstrap-datepicker-master/dist/js/bootstrap-datepicker.js"></script>
<script src="static/bootstrap/vendor/bootstrap-datepicker-master/dist/locales/bootstrap-datepicker.zh-CN.min.js"></script>

<!-- for datatable -->
<script src="static/bootstrap/vendor/datatables/media/js/jquery.dataTables.min.js"></script>
<script src="static/bootstrap/vendor/datatables_plugins/integration/bootstrap/3/dataTables.bootstrap.min.js"></script>


<!-- alert -->
<script src="static/bootstrap/vendor/sweetalert/lib/sweet-alert.min.js"></script>

<!-- App scripts -->
<script src="static/bootstrap/scripts/homer.js"></script>
<script src="static/bootstrap/scripts/charts.js"></script>

<script src="static/bootstrap/vendor/select2-3.5.2/select2.min.js"></script>
<!-- 表单验证 -->
<script src="static/js/plugins/form.validation/js/formValidation.js"></script>
<script src="static/js/plugins/form.validation/js/framework/bootstrap.js"></script>

<script type="text/javascript">


    var table;
    $(function () {
        //加载table
        loadTable();
    });
    function loadTable() {
        if (table) {
            table.destroy();
        }
        table = $('#projectTable').DataTable({
            "dom": "<'row'<'col-sm-6'l><'col-sm-4'f><'col-sm-2'<'toolbar text-right'>>>" +
            "<'row'<'col-sm-12 table-responsive'tr>>" +
            "<'row'<'col-sm-5'i><'col-sm-7'p>>",
            //json
            "processing": true,
            "serverSide": true,
            "bDestroy": true,
            "ordering": false,
            "ajax": {
                "url": "socialAccumulationAccount/findAccountPage",
                "type": "get"
            },
            "columns": [
                {
                    "render": function render(data, type, row, meta) {
                        return '<a href="socialaccumulation/viewAccounts?accountId=' + row.accountId + '" >' + row.accountName + '</a>';
                    }
                },
                {
                    "render": function render(data, type, row, meta) {
                        return row.accountType == 0 ? '企业' : '外包';
                    }
                },
                {"data": "cities"},
                {
                    "render": function render(data, type, row, meta) {
                        return '' +
                                '<button class="btn btn-info btn-xs" data-toggle="modal" type="button" onclick="detail(' + row.accountId + ');">详情</button>&nbsp;' +
                                <%--<% if(RequestUtil.hasPower("ssaccount_ma")){ %>--%>
                                '<button class="btn btn-success btn-xs" data-target="[data-modal=editSalaryDate]" data-toggle="modal" type="button" onclick="mod(' + row.accountId + ');">修改</button>&nbsp;' +
                                <%--<% } %>
                                <% if(RequestUtil.hasPower("ssaccount_da")){ %>--%>
                                '<button class="btn btn-danger btn-xs del-button" type="button" onclick="del(' + row.accountId + ');">删除</button>';
                        <%--<% } %>--%>
                    }
                }
            ],
            "columnDefs": [
                {defaultContent: '', targets: '_all'}
            ],

            "language": {
                "search": "过滤:",
                "lengthMenu": "每页显示 _MENU_ 条记录",
                "zeroRecords": "暂无数据，您可以点击新增添加社保公积金账户",
                "info": "显示 第 _PAGE_ 页 共 _PAGES_ 页",
                "infoEmpty": "暂无数据",
                "infoFiltered": "(筛选自 _MAX_ 条记录)",
                "paginate": {
                    "first": "首页",
                    "previous": "前一页",
                    "next": "后一页",
                    "last": "尾页"
                }
            }
        });
        $("div.toolbar").html('<a class="btn btn-info btn-sm" href="socialAccumulationAccount/toAddAccounts">新  增</a>');
    }


</script>
<script>
    /*业务模块*/
    function del(accountId) {
        swal(
                {
                    title: "确定要删除此账户吗?",
                    text: "",
                    type: "warning",
                    showCancelButton: true,
                    confirmButtonColor: "#DD6B55",
                    confirmButtonText: "确定删除!",
                    cancelButtonText: "放弃操作!",
                    closeOnConfirm: false,
                    closeOnCancel: false
                },
                function (isConfirm) {
                    if (isConfirm) {
                        deleteById(accountId);
                    } else {
                        swal("已取消", "账户删除已取消", "error");
                    }
                }
        );
    }
    //删除社保账户
    function deleteById(accountId) {
        $.ajax({
            url: "socialAccumulationAccount/deleteAccountById",
            data: {accountId: accountId},
            dataType: 'json',
            type: 'post',
            success: function (result) {
                if (result.resultCode == "0") {
                    swal({
                        title: "删除成功!",
                        text: "",
                        type: "success",
                        confirmButtonText: "确定"
                    });
                    loadTable();
                } else {
                    swal({
                        title: "删除失败!",
                        text: result.resultMsg,
                        type: "error",
                        confirmButtonText: "确定"
                    });
                }
            }
        });
    }

    function detail(accountId) {
        location.assign('socialaccumulation/detailAccounts?accountId=' + accountId);
    }

    function mod(accountId) {
        location.assign('socialaccumulation/editAccounts?accountId=' + accountId);
    }
</script>
</body>
</html>