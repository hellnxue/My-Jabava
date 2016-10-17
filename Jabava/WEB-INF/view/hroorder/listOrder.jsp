<%--suppress HtmlUnknownTarget --%>
<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="com.jabava.utils.RequestUtil" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<!DOCTYPE html>
<html>
<head>
    <base href="${pageContext.request.contextPath}/">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <!-- Page title -->
    <title>订单查询</title>
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
                        订单查询
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
                        <div class="row">
                            <form class="form-horizontal" data-form="queryList" autocomplete="off">
                                <div class="col-xs-5">
                                    <div class="form-group">
                                        <label class="control-label text-right col-xs-7">账单月</label>
                                        <div class="col-xs-5">
                                            <div class="input-group date">
                                                <input id='billMonth' type="text" class="form-control" value="<%=new SimpleDateFormat("yyyyMM").format(new Date())%>">
                                                <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xs-7">
                                    <div class="form-group">
                                        <label class="control-label text-left col-xs-2">社保公积金账户</label>
                                        <div class="col-xs-4">
                                            <select id="accountId" class="form-control">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <table id="projectTable" class="table table-condensed table-hover table-bordered dataTable"
                               width="100%">
                            <thead>
                            <tr>
                                <th>工号</th>
                                <th>姓名</th>
                                <th>证件号码</th>
                                <th>社保公积金缴纳地</th>
                                <th>社保缴纳规则</th>
                                <th>服务月</th>
                                <th>社保基数</th>
                                <th>公积金基数</th>
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
    $(document).ready(function () {
        renderDatePicker();
        renderAccounts();
        //加载table
        loadTable(_createQueryParams());
        //绑定按钮事件
        $("#billMonth,#accountId").on("change", function () {
            loadTable(_createQueryParams());
        });
    });

    //渲染日历控件
    function renderDatePicker() {
        $('.input-group.date').datepicker({
            format: "yyyymm",
            language: "zh-CN",
            autoclose: true,
            weekStart: 1,
            startView: 1, maxViewMode: 1,minViewMode:1,
            forceParse: false
        });
    }

    //渲染账户下拉列表
    function renderAccounts() {
        $.ajax({
            url : "socialAccumulationAccount/getAccountsByCompanyId",
            type : "GET",
            async : false,
            dataType : "json",
            success : function (data) {
                if (data.success) {
                    var _html = '';
                    $.each(data.result, function (idx, account) {
                        _html += '<option value="' + account.accountId + '">' +
                                account.accountName +
                                '</option>';
                    });
                    $("#accountId").empty().append(_html);
                } else {
                    swal(data.msg, "", "error");
                }
            }
        });
    }

    //组装动态表格查询参数
    function _createQueryParams(operateType) {
        var _obj = {};
        _obj.billMonth = $("#billMonth").val();
        _obj.accountId = $("#accountId").val();
        _obj.operateType = operateType ? operateType : '';
        return _obj;
    }

    //加载动态表格
    function loadTable(params) {
        if (table) {
            table.destroy();
        }
        table = $('#projectTable').DataTable({
            "dom": "<'row'<'col-sm-6'<'toolbar-l text-left'>><'col-sm-6'<'toolbar-r text-right'>>>" +
            "<'row'<'col-sm-12 table-responsive'tr>>" +
            "<'row'<'col-sm-5'l><'col-sm-7'p>>",
            //json
            "processing": true,
            "serverSide": true,
            "bDestroy": true,
            "ordering": false,
            "ajax": {
                "url": "order/findOrderSendPage",
                "type": "get",
                "data": params ? params : {}
            },
            "columns": [
                {"data": "jobNumber"},
                {"data": "employeeName"},
                {"data": "cardId"},
                {"data": "accountCityName"},
                {"data": "sbGroupName"},
                {"data": "paymentMonth"},
                {"data": "sbBase"},
                {"data": "gjjBase"}
            ],
            "columnDefs": [
                {defaultContent: '', targets: '_all'}
            ],

            "language": {
                "lengthMenu": "每页显示 _MENU_ 条记录",
                "zeroRecords": "暂无数据 - 报歉啦?",
                "info": "共 _PAGES_ 页",
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
        //拼装查询框
        var _html_l = '<a class="btn btn-link text-info" data-attr="1">增员</a>' +
                '<a class="btn btn-link text-info" data-attr="3">减员</a>' +
                '<a class="btn btn-link text-info" data-attr="2">变更</a>' +
                '<a class="btn btn-link text-info" data-attr="">全部</a>';
        //拼装按钮
        var _html_r = '<a class="btn btn-info" id="viewBill">查看当月账单</a>' +
                '<a class="btn btn-info m-l" id="exportList">导出列表</a>';
        $("div.toolbar-l").append(_html_l);
        $("div.toolbar-r").append(_html_r);

        //绑定工具条事件
        $("div.toolbar-l").children().on("click", function () {
            var operateType = $(this).attr("data-attr");
            loadTable(_createQueryParams(operateType));
        });
        //点击导出
        $("#exportList").on("click", function () {
            var form = $('<form style="display:none;" target="" method="post" action="order/exportOrderSend">');
            var inputBillMonth = $('<input type="hidden" name="billMonth" value="' + $("#billMonth").val() + '"/>');
            var inputAccountId = $('<input type="hidden" name="accountId" value="' + $("#accountId").val() + '"/>');
            $('body').append(form);
            form.append(inputBillMonth);
            form.append(inputAccountId);
            form.submit();
        });
        //点击查看当月账单
        $("#viewBill").on("click", function() {
            location.href = "/efArap/toBillList";
        });
    }
</script>
</body>
</html>