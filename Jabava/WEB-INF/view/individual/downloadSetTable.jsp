<%@ page contentType="text/html; charset=utf-8" %>

<!DOCTYPE html>
<html>
<head>
    <base href="${pageContext.request.contextPath}/">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <!-- Page title -->
    <title>各地BP上传界面</title>
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
                    <div class="panel-heading text-center">
                        各地BP上传
                    </div>
                    <div class="panel-heading m-b">
					<h4>
						<a href="static/xls/Jabava_BP_template1.0.0.xlsx" class="pull-right small"><span class="text-info">下载BP上传模板</span></a>
					</h4>
				    </div>
                    <div class="panel-body">
                        <div class="col-xs-offset-3 col-sm-offset-3 col-md-offset-3 col-lg-offset-3 col-xs-6 col-sm-6 col-md-6 col-lg-6">
                            <form class="form-horizontal" id="" action="">
                                <input type="hidden" id="bpaddress1" value="${sessionScope.BP_address1 }"> 
                                  <input type="hidden" id="bpaddress2" value="${sessionScope.BP_address2 }"> 
                                    <input type="hidden" id="bpaddress3" value="${sessionScope.BP_address3 }"> 
                                <div class="form-group">
                                    <label class="control-label col-xs-4 col-sm-4 col-md-4 col-lg-3">总名单批次：</label>
                                    <div class="col-xs-8 col-sm-8 col-md-8 col-lg-9 ">
                                        <select class="form-control select2" data-toggle="select2" id="rosterbatch" name="">
                                            <option value="">请选择总名单批次</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-xs-4 col-sm-4 col-md-4 col-lg-3">本地名单：</label>
                                    <div class="col-xs-8 col-sm-8 col-md-8 col-lg-9 ">
                                        <div class="input-group" data-toggle="upload:file">
                                            <input type="text" class="form-control" readonly="readonly">
                                            <div class="input-group-btn">
                                                <div class="btn btn-default">
                                                    浏览附件资料并上传...
                                                </div>
                                            </div>
                                        </div>
                                        <input type="file" class="sr-only" name='' accept=".xls" data-action="file">
                                        <div class="msg-cover"></div>
                                    </div>
                                </div>
                                <div class="form-group text-center m-t-xxxl">
                                    <button type="button" class="btn btn-success" data-id="download" data-address="download">　下载　</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div> 
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
<!-- alert -->
<script src="static/bootstrap/vendor/sweetalert/lib/sweet-alert.min.js"></script>
<script src="static/bootstrap/vendor/select2-3.5.2/select2.min.js"></script>
<script type="text/javascript">
var individualType="${type}";//BP文件类型

 
</script>
<script src="static/js/individual/downloadSetTable.js"></script>
 
</body>
</html>
