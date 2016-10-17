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
    <title>社保公积金账户管理-账户详情</title>
    <jsp:include flush="true" page="../common/styles.jsp"/>
    <link rel="stylesheet" href="static/css/user.css">
    <link rel="stylesheet" href="static/js/plugins/form.validation/css/formValidation.css">
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
                            查看社保公积金账户
                        </h4>
                    </div>

                    <div class="panel-body">
                        <form class="form-horizontal" data-form="addAccounts" autocomplete="off">
                            <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6 col-md-offset-3">
                                <div class="form-group">
                                    <label class="control-label col-xs-4 col-sm-4 col-md-4 col-lg-4">账户名称：</label>
                                    <div class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
                                        <p class="form-control-static" data-name="accountName"></p>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6 col-md-offset-3">
                                <div class="form-group">
                                    <label class="control-label col-xs-4 col-sm-4 col-md-4 col-lg-4">账户类型：</label>
                                    <div class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
                                        <p data-name="accountType" data-type="select" class="form-control-static">
                                            <span aria-value="0" class="hidden">企业</span>
                                            <span aria-value="1" class="hidden">外包</span>
                                        </p>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6 col-md-offset-3">
                                <div class="form-group">
                                    <label class="control-label col-xs-4 col-sm-4 col-md-4 col-lg-4">社保账户编号 ：</label>
                                    <div class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
                                        <p data-name="socialSecurityCode" class="form-control-static"></p>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6 col-md-offset-3">
                                <div class="form-group">
                                    <label class="control-label col-xs-4 col-sm-4 col-md-4 col-lg-4">公积金账户编号 ：</label>
                                    <div class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
                                        <p class="form-control-static" data-name="accumulationFundCode"></p>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="hpanel">
                    
                    <nav class="navbar navbar-default" data-container="tabnv">
                        <ul class="nav nav-tabs m-l-lg m-r-lg" role="tablist">
                            <li role="presentation">
                                <a href="javascript://" aria-controls="addTab" role="tab" data-toggle="addTab" disabled="disabled" aria-disabled="true">
                                    ( <span class="fa fa-plus"></span> )
                                </a>
                            </li>
                        </ul>
                        <a href="javascript://" class="tab-prev tab-arrow disabled"><span class="fa fa-chevron-left fa-2x"></span></a>
                        <a href="javascript://" class="tab-next tab-arrow disabled"><span class="fa fa-chevron-right fa-2x"></span></a>
                    </nav>
                    <div class="panel-body tab-content">

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
<div class="modal fade hmodal-success" tabindex="-1" role="dialog" data-modal="rules">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="color-line"></div>
            <div class="modal-body">
                <fieldset>
                    <legend class="text-success">政策推荐</legend>
                    <ul class="list-unstyled" data-list="platform">
                    </ul>
                </fieldset>
                <fieldset>
                    <legend class="text-success">供应商政策</legend>
                    <ul class="list-unstyled" data-list="vendor">
                    </ul>
                </fieldset>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">返回</button>
                <button type="button" class="btn btn-primary" data-modal-action="confirm">确认</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog --> 
</div><!-- /.modal -->
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
<!-- alert -->
<script src="static/bootstrap/vendor/sweetalert/lib/sweet-alert.min.js"></script>
<script src="static/js/plugins/form.validation/js/formValidation.js"></script>
<script src="static/js/plugins/form.validation/js/framework/bootstrap.js"></script>
<script src="static/js/plugins/form.validation/js/language/zh_CN.js"></script>
<script src="static/js/plugins/form.validation/js/formValidationExtend.js"></script>
<!-- App scripts -->
<script src="static/bootstrap/scripts/homer.js"></script>
<script src="static/js/socialaccumulation/detailAccounts.js"></script>
</body>
</html>