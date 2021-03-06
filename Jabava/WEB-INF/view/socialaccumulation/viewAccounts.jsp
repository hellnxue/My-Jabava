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
    <title>社保公积金账户管理-查看</title>
    <jsp:include flush="true" page="../common/styles.jsp"/>
    <!--for 临时改变-->
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
    <!-- 放主要内容 -->
    <div class="content animate-panel">
        <div class="row">
            <div class="col-lg-12">
                <div class="hpanel">
                    <div class="panel-heading">
                        <h4 class="text-center font-bold">
                            <a href="socialAccumulationAccount/listAccounts" type="button"
                               class="btn btn-success btn-sm pull-left btn-return m-r-md">返回</a>
                            社保公积金账户管理-查看
                        </h4>
                    </div>

                    <div class="panel-body">
                        <input type="hidden" id="accountId" value="<%=request.getParameter("accountId")%>">

                        <form id="msForm" class="form-horizontal">
                            <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-4">社保公积金账户：</label>
                                    <div class="col-xs-6 col-sm-6 col-md-6 col-lg-8">
                                        <p id="accountName" class="form-control-static"></p>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-4">类型：</label>
                                    <div class="col-xs-6 col-sm-6 col-md-6 col-lg-8">
                                        <p id="accountType" class="form-control-static"></p>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-4">参保地：</label>
                                    <div class="col-xs-6 col-sm-6 col-md-6 col-lg-8">
                                        <p id="cityLabel" class="form-control-static"></p>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>

                    <div class="panel-body m-t-md">
                        <table id="projectTable" class="table table-condensed table-hover table-bordered dataTable"
                               width="100%">
                            <thead>
                            <tr>
                                <th>员工姓名</th>
                                <th>参保地</th>
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
<script type="text/javascript">
    var accountId = $('#accountId').val();

    $.ajax({
        type: "get",
        url: "socialAccumulationAccount/findAccountById",
        data: {accountId: accountId},
        dataType: "json",
        success: function (d){
            if( !Boolean(d.resultCode*1) ){
                var data=d.resultData.dataList[0];
                $("#accountName").html(data.accountName);
                $("#accountType").html(data.accountType == 0 ? '企业' : '外包');
                //参保地的 城市
                $("#cityLabel").html(data.cities);
                //参保规则
                $("#securityCode").html(data.socialSecurityCode);
                $("#accumulationCode").html(data.accumulationFundCode);
            }
        }
    });

    var table;
    $(function () {
        loadTable();
    });

    function loadTable() {
        if (table) {
            table.destroy();
        }
        table = $('#projectTable').DataTable({
            "dom": "<'row'<'col-sm-12'l>>" +
            "<'row'<'col-sm-12 table-responsive'tr>>" +
            "<'row'<'col-sm-5'i><'col-sm-7'p>>",

            //json
            "processing": true,
            "serverSide": true,
            "bDestroy": true,
            "ordering": false,
            "ajax": {
                "url": "socialAccumulationAccount/findPersonListPage?accountId=" + accountId,
                "type": "POST"
            },
            "columns": [

                {"data": "employeeName"},
                {"data": "cityName"}
            ],
            "columnDefs": [
                {defaultContent: '', targets: '_all'}
            ],

            "language": {
                "search": "过滤:",
                "lengthMenu": "每页显示 _MENU_ 条记录",
                "zeroRecords": "暂无数据 - 报歉啦?",
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
    }
</script>
</body>
</html>