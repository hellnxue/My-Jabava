<%@ page contentType="text/html; charset=utf-8" %>

<!DOCTYPE html>
<html>
<head>
	<base href="${pageContext.request.contextPath}/">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <!-- Page title -->
    <title>企业信息</title>
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
    <div class="normalheader transition animated fadeIn small-header">
        <div class="hpanel">
            <div class="panel-body">
                <div id="hbreadcrumb" class="m-t-xs m-b-xs">
                    <h2 class="font-normal m-b-xs text-center">
                        企业信息
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
                    <div class="panel-body">
                        <div class="col-lg-12">
                            <p><b>上传头像</b></p>
                        </div>
                        <div class="col-lg-12 m-t">
                            <form id="uploadForm" enctype="multipart/form-data">
                                <input type="hidden" id="companyId" name="companyId" value=""/>
                                <input type="hidden" id="companyLogo" name="companyLogo" value=""/>
                                <div class="col-lg-10 col-lg-offset-1 panel-body text-center p-xl">
                                    <div class="col-lg-10 col-lg-offset-1">
                                        <div class="col-lg-6">
                                            <div class="form-group">
                                                <input id="file-1" name="uploadFile" type="file" multiple class="file" data-overwrite-initial="false" data-min-file-count="1">
                                            </div>
                                        </div>
                                        <div class="col-lg-6">
                                            <div class="col-lg-12 m-t-xxxl text-center">
                                                <h6 class="col-lg-12 m-t">
                                                    <p>
                                                        <h6>LOGO 支持png、jpg文件；</h6>
                                                        <h6></h6>文件大小不超过2M；</h6>
                                                    </p>
                                                </div>
                                                <div class="col-lg-12 m-t-md">
                                                    <a id="saveLogo" class="btn w-xs btn-primary">保存</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
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
<script src="static/bootstrap/vendor/moment/moment.js"></script>
<script src="static/bootstrap/scripts/homer.js"></script>
<!-- alert -->
<script src="static/bootstrap/vendor/sweetalert/lib/sweet-alert.min.js"></script>
<script src="static/js/template.js"></script>

<script src="static/js/common/ajaxfileupload.js"></script>

<script src="static/js/fileinput.js"></script>
<script src="static/js/zh.js"></script>



<script>
    $(document).ready(function () {
        loadCompanyConfig();
    });

    //加载企业信息
    function loadCompanyConfig() {
        $.ajax({
            url : "system/loadCompanyConfig",
            async : false,
            dataType:'json',
            type : 'post',
            success : function(message) {
                $("#companyId").val(message.result.companyId);
                $("#companyLogo").val(message.result.companyLogo);
            }
        });
    }

    //初始化上传控件
    $("#file-1").fileinput({
        language: 'zh',
        uploadUrl: 'employee/uploadToHRO',
        uploadAsync: false,
        allowedFileExtensions : ['jpg', 'png'],
        overwriteInitial: false,
        maxFileSize: 2048,
        minFileCount: 1,
        maxFileCount: 1,
        showCaption: true,
        showPreview: true,
        showRemove: false,
        showUpload: false,
        showCancel: false,
        showClose: false,
        showBrowse: true,
        previewClass: 'preview',
        dropZoneTitle: '您可以选择一张本地照片</br>或公司 LOGO 设置头像；',
        browseIcon: '<i class="fa fa-upload m-r-xs"></i>',
        msgFilesTooMany: '只能上传<b>{m}</b>张图片，如需修改请您删除已上传的图片重新选择图片',
        showUploadedThumbs: false,
        fileActionSettings: {
            showUpload: false,
            showRemove: true,
            showZoom: true,
            showDrag: false
        },
        slugCallback: function(filename) {
            return filename.replace('(', '_').replace(']', '_');
        }
    });

    //上传成功
    $('#file-1').on('filebatchuploadsuccess', function(event, data, previewId, index) {
        var response = data.response;
        if (response.success) {
            $("#companyLogo").val(response.fileUrl);
            saveLogo();
        }
    }).on('change', function (event) {
        $('#saveLogo').removeAttr("disabled");
    });


    //保存Logo信息
    $('#saveLogo').click(function () {
        if ($(this).attr("disabled")) {
            return;
        }
        $('#file-1').fileinput('upload');
    });

    //更新后台logo数据
    function saveLogo() {
        $.ajax({
            url : "system/saveCompanyConfig",
            data : $("#uploadForm").serialize(),
            async : false,
            dataType:'json',
            type : 'post',
            success : function(message) {
                if(message.success){
                    $('#saveLogo').attr("disabled", "disabled");
                    $('#file-1').fileinput('lock').fileinput('disable');
                    swal({
                        title: "上传成功!",
                        text: message.msg,
                        type: "success",
                        confirmButtonText: "确定"
                    });
                }else{
                    $('#file-1').fileinput('clear');
                    swal({
                        title: "上传失败!",
                        text: message.msg,
                        type: "error",
                        confirmButtonText: "确定"
                    });
                }
            }
        });
    }
</script>
</body>
</html>
