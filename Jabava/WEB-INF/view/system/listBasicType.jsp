<%@ page contentType="text/html; charset=utf-8" %>

<!DOCTYPE html>
<html>
<head>
	<base href="${pageContext.request.contextPath}/">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <!-- Page title -->
    <title>基础数据类型</title>
    <jsp:include flush="true" page="../common/styles.jsp"></jsp:include>
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
  
<!-- Main Wrapper -->
<div id="wrapper">
    <!-- 放主要内容 -->
    <div class="content animate-panel">
        <div class="row">
            <div class="col-lg-12">
                <div class="hpanel">
                    <div class="panel-heading">
                        <h4 class="text-center font-bold">
                            基础数据类型
                        </h4>
                        <div class="text-right">
                           <button type="button" class="btn btn-info" data-action="add" data-toggle="modal" data-target="[data-modal=formAdd]">新　增</button>
                           <button type="button" class="btn btn-info" data-action="edit" data-toggle="modal" data-target="[data-modal-form=formEdit]">修　改</button>
                        </div> 
                        </div>
                    <div class="panel-body">
                        <table id="downLoad" class="table table-striped table-condensed table-hover table-bordered dataTable" width="100%">
                                <thead>
                                <tr>
                                    <th>单选</th>
                                    <th>编码</th>
                                    <th>名称</th>
                                    <th>操作人</th>
                                    <th>操作时间</th>
                                    <th>备注</th>
                                </tr>
                                </thead>
                                <tbody>                          
                                </tbody>
                            </table>
                    </div>
                   
                </div>
            </div>
        </div>
<!-- 新增基础数据类型 -->
    <div class="modal fade hmodal-success" tabindex="-1" role="dialog" data-modal="formAdd">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
        <div class="color-line"></div>
        <div class="panel panel-default">
            <div class="panel-heading">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4>基础数据类型新增</h4>
            </div>
            <div class="panel-footer bg-white">
                <form method="post" role="form" id="addForm" data-form-type="add" class="form-horizontal" enctype="multipart/form-data" data-form="validator">
                    <div class="form-group m-b-none">
                         <div class="col-xs-11 col-sm-11 col-md-11 col-lg-11">
                            <div class="form-group m-t">
                            <label class="control-label col-xs-3 col-sm-3 col-md-3 col-lg-2">名称：</label>
                                <div class="col-xs-9 col-sm-9 col-md-9 col-lg-10">
                                    <input type="text" class="form-control" name="baseDataTypeName">
                                </div>   
                            </div>
                        </div>
                         <div class="col-xs-11 col-sm-11 col-md-11 col-lg-11">
                            <div class="form-group m-t">
                            <label class="control-label col-xs-3 col-sm-3 col-md-3 col-lg-2">备注：</label>
                                <div class="col-xs-9 col-sm-9 col-md-9 col-lg-10">
                                    <textarea  name="memo" class="form-control"></textarea>
                                </div>   
                            </div>
                        </div>
                        <div class="text-center col-xs-12 col-sm-12 col-md-12 col-lg-12 m-t">
                            <button type="submit" class="btn btn-info">确　定</button>
                            <button type="button" class="btn btn-default" data-id="cancel">取　消</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        </div><!-- /.modal-content -->
      </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->

    <!-- 修改基础数据类型 -->
    <div class="modal fade hmodal-success" tabindex="-1" role="dialog" data-modal-form="formEdit">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
        <div class="color-line"></div>
        <div class="panel panel-default">
            <div class="panel-heading">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4>基础数据类型修改</h4>
            </div>
            <div class="panel-footer bg-white">
                <form method="post" role="form" id="editForm" data-form-type="edit" class="form-horizontal" enctype="multipart/form-data" data-form="validator">
                    <input type="hidden" name="companyId" id="companyId">
                    <div class="form-group m-b-none">
                        <div class="col-xs-11 col-sm-11 col-md-11 col-lg-11">
                            <div class="form-group m-t">
                            <label class="control-label col-xs-3 col-sm-3 col-md-3 col-lg-2">编码：</label>
                                <div class="col-xs-9 col-sm-9 col-md-9 col-lg-10">
                                    <input type="text" class="form-control" name="baseDataType" readonly="readonly" data-dataType="editType">
                                </div>   
                            </div>
                        </div>
                         <div class="col-xs-11 col-sm-11 col-md-11 col-lg-11">
                            <div class="form-group m-t">
                            <label class="control-label col-xs-3 col-sm-3 col-md-3 col-lg-2">名称：</label>
                                <div class="col-xs-9 col-sm-9 col-md-9 col-lg-10">
                                    <input type="text" class="form-control" name="baseDataTypeName">
                                </div>   
                            </div>
                        </div>
                         <div class="col-xs-11 col-sm-11 col-md-11 col-lg-11">
                            <div class="form-group m-t">
                            <label class="control-label col-xs-3 col-sm-3 col-md-3 col-lg-2">备注：</label>
                                <div class="col-xs-9 col-sm-9 col-md-9 col-lg-10">
                                    <textarea  name="memo" class="form-control"></textarea>
                                </div>   
                            </div>
                        </div>
                        <div class="text-center col-xs-12 col-sm-12 col-md-12 col-lg-12 m-t">
                            <button type="submit" class="btn btn-info">确　定</button>
                            <button type="button" class="btn btn-default" data-id="cancel">取　消</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        </div><!-- /.modal-content -->
      </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->

    <!-- 放页脚  开始-->
    <jsp:include flush="true" page="../common/foot.div.jsp"></jsp:include>
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
<script src="static/js/system/listBasicType.js"></script>
</body>
</html>
