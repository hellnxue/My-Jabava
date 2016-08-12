<%@ page contentType="text/html; charset=utf-8" %>
<%
    System.out.println("OK");
%>
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
                    <div class="panel-body">
                        <div class="col-xs-offset-3 col-sm-offset-3 col-md-offset-3 col-lg-offset-3 col-xs-6 col-sm-6 col-md-6 col-lg-6">
                            <form class="form-horizontal" id="" action="">
                                <div class="form-group">
                                    <label class="control-label col-xs-4 col-sm-4 col-md-4 col-lg-3">总名单批次：</label>
                                    <div class="col-xs-8 col-sm-8 col-md-8 col-lg-9 ">
                                        <select class="form-control select2" data-toggle="select2" id="" name="">
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
                                    </div>
                                </div>
                                <div class="form-group text-center m-t-xxxl">
                                    <button type="button" class="btn btn-success" data-id="download">　下载　</button>
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
<script src="static/js/individual/downloadSetTable.js"></script>

<!-- <script>
    (function($){
        function upload(){
            $('[data-toggle="attachments"]').on('click', function(event){
                var getTarget = $(this).attr('data-target');
                var $modal = $(getTarget);
                var $uploadCompent = $modal.find('[data-toggle="upload:file"]');
                var $listAttachment = $('[data-list="attachments"] tbody');

                // init attachments list
                // init upload compent
                $uploadCompent.find(':file').on('change.file:selected', function(event){
                    var oEventTarget = $(this),
                    oFile = $(this).val(),
                    getFileType = $(this).attr('data-file-type'),
                    fileExt = oFile.substr(oFile.lastIndexOf(".")).toLowerCase(),
                    // $getThumbZone = $(this).parents('[data-toggle="upload:file"]').prev('a'),
                    // getTitle = $getThumbZone.attr('title'),
                    getTitle = '附件资料',
                    $getMsgCover = $(this).parents('[data-toggle="upload:file"]').next();

                    oEventTarget.parents('[data-toggle="upload:file"]')
                    .find(':text').val( oFile );

                    $getMsgCover.css({
                        height: $getMsgCover.parent().height(),
                        lineHeight: $getMsgCover.parent().height()+'px',
                        width: $getMsgCover.parent().width(),
                        zIndex: 100
                    }).text(getTitle+'上传中...').show();
                    // upload file
                    var formData=new FormData();
                        formData.append('uploadFiles', $(this)[0].files[0]);
                        formData.append('personId', '${personId}');
                        //上传文件类型  需要将值设置在指定的上传文件表单中
                        formData.append("fileType", getFileType);
                    
                    var tmpl = [];
                    tmpl.push('<tr>');
                    tmpl.push('<td><a href="@imgUrl">@originName</a></td>');
                    tmpl.push('<td>@uploadDate</td>');
                    tmpl.push('<td>@uploader</td>');
                    tmpl.push('</tr>');
                    tmpl = tmpl.join('');
                    $.ajax({
                        url:  "employee/uploadSecurityFiles",
                        type: 'POST',
                        cache: false,
                        dataType: 'json',
                        data: formData,
                        processData: false,
                        contentType: false

                    })
                    .done(function(d){
                        if(d.success){
                            tmpl = tmpl.replace('@imgUrl', d.imgUrl)
                                .replace('@originName', oFile)
                                .replace('@uploadDate', 'Thornton')
                                .replace('@uploader', '@fat');
                            $listAttachment.append(tmpl);
                            
                            $getMsgCover.text(getTitle+'上传成功。');
                            setTimeout(function(){ $getMsgCover.hide(); }, 1500);
                        }else{
                            $getMsgCover.text(getTitle+'上传失败。');
                            setTimeout(function(){ $getMsgCover.hide(); }, 1500);
                        }

                    });

                });

                $uploadCompent.on('click', function(event){
                    $(this).find(':file').trigger('click.file:selected');
                });
                
                
                $modal.modal('show');

            });
        }
        function init(){
            upload();
            $('[data-toggle="select2"]').select2();
        };
        init();
    })(jQuery)
</script> -->
</body>
</html>
