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
    <title>总部上传界面</title>
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
                            京东管理平台
                        </h4>
                        <div class="text-right">
                           <button type="button" class="btn btn-info" data-action="add" data-toggle="attachments" data-target="[data-modal=attachments]">新　增</button>
                        </div> 
                        </div>
                    <div class="panel-body">
                        <table id="downLoad" class="table table-striped table-condensed table-hover table-bordered dataTable" width="100%">
                                <thead>
                                <tr>
                                    <th>
                                        <input type="checkbox">全选
                                    </th>
                                    <th>批次</th>
                                    <th>上传时间</th>
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

    <div class="modal fade hmodal-success" tabindex="-1" role="dialog" data-modal="attachments">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
        <div class="color-line"></div>
        <div class="panel panel-default">
            <div class="panel-heading">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4>上传总体人员名单</h4>
            </div>
            <div class="panel-footer bg-white">
                <form method="post" role="form" id="downLoadForm" class="form-horizontal" enctype="multipart/form-data">
                    <div class="form-group m-b-none">
                        <div class="col-xs-11 col-sm-11 col-md-11 col-lg-11">
                            <div class="form-group m-t">
                            <label class="control-label col-xs-3 col-sm-3 col-md-3 col-lg-2">批次号：</label>
                                <div class="col-xs-9 col-sm-9 col-md-9 col-lg-10">
                                    <input type="text" class="form-control" name="batchNumber">
                                </div>   
                            </div>
                        </div>
                        <div class="col-xs-11 col-sm-11 col-md-11 col-lg-11">
                            <div class="form-group">
                                <label class="control-label col-xs-3 col-sm-3 col-md-3 col-lg-2">上传：</label>
                                <div class="col-xs-9 col-sm-9 col-md-9 col-lg-10">
                                    <div class="input-group" data-toggle="upload:file">
                                        <input type="text" class="form-control">
                                        <div class="input-group-btn">
                                            <div class="btn btn-default">浏览总体人员名单并上传...
                                                <input type="file" class="sr-only" name='jobpostFile' accept=".png,.jpeg,.jpg" data-file-type="123">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="msg-cover"></div>
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
<script src="static/bootstrap/vendor/datatables/media/js/jquery.dataTables.min.js"></script>
<script src="static/bootstrap/vendor/datatables_plugins/integration/bootstrap/3/dataTables.bootstrap.min.js"></script>

<!-- alert -->
<script src="static/bootstrap/vendor/sweetalert/lib/sweet-alert.min.js"></script>
<script src="static/js/template.js"></script>
<!-- 表单验证 -->
<script src="static/js/plugins/form.validation/js/formValidation.js"></script>
<script src="static/js/plugins/form.validation/js/framework/bootstrap.js"></script>
<script src="static/js/plugins/form.validation/js/language/zh_CN.js"></script> 
<script src="static/js/individual/listRosterBatch.js"></script>
<script>

	 // $(function (){
  //       add();
  //       cancel();
  //       $('#downLoad').dataTable( {
  //           dom:
  //               "<'row'<'col-sm-6'l><'col-sm-6'f>>" +
  //               "<'row'<'col-sm-12 table-responsive'tr>>" +
  //               "<'row'<'col-sm-5'i><'col-sm-7'p>>",

  //           "language":{
  //               "search": "过滤:",
  //               "lengthMenu": "每页显示 _MENU_ 条记录",
  //               "zeroRecords": "暂无数据 - 报歉啦?",
  //               "info": "显示 第 _PAGE_ 页 共 _PAGES_ 页",
  //               "infoEmpty": "暂无数据",
  //               "infoFiltered": "(筛选自 _MAX_ 条记录)",
  //               "paginate":{
  //                   "first":"首页",
  //                   "previous":"前一页",
  //                   "next":"后一页",
  //                   "last":"尾页"
  //               }
  //           }
  //       })

         // 表单验证
         // function add(){
         //    $('[data-action="add"]').on('click', function(e) {
         //      $.ajax({
         //          url: 'system/addTableFieldDef',
         //          type: 'POST',
         //          dataType: 'json',
         //      })
         //      .done(function() {
         //          console.log("success");
         //      })

         //      $("#downLoadForm").formValidation({
         //            err: {
         //                container: 'tooltip'
         //            },
         //            icon: {
         //                valid: 'glyphicon glyphicon-ok',
         //                invalid: 'glyphicon glyphicon-remove',
         //                validating: 'glyphicon glyphicon-refresh'
         //            },
         //            fields: {
         //                batchNumber: {
         //                    validators: {
         //                        notEmpty: {
         //                            message: '请填写必填项'
         //                        }
         //                    }
         //                },
         //                jobpostFile: {
         //                    validators: {
         //                        notEmpty:{
         //                            message: '请填写必填项'
         //                        }
         //                    }
         //                }
         //            }
         //    }) 
              
         //    });
            
         //     $('[data-toggle="attachments"]').on('click', function(event){
         //        var getTarget = $(this).attr('data-target');
         //        var $modal = $(getTarget);
         //        var $uploadCompent = $modal.find('[data-toggle="upload:file"]');
         //        var $listAttachment = $('[data-list="attachments"] tbody');

         //        // init attachments list
         //        // init upload compent
         //        $uploadCompent.find(':file').on('change.file:selected', function(event){
         //            var oEventTarget = $(this),
         //            oFile = $(this).val(),
         //            getFileType = $(this).attr('data-file-type'),
         //            fileExt = oFile.substr(oFile.lastIndexOf(".")).toLowerCase(),
         //            // $getThumbZone = $(this).parents('[data-toggle="upload:file"]').prev('a'),
         //            // getTitle = $getThumbZone.attr('title'),
         //            getTitle = '附件资料',
         //            $getMsgCover = $(this).parents('[data-toggle="upload:file"]').next();

         //            oEventTarget.parents('[data-toggle="upload:file"]')
         //            .find(':text').val( oFile );

         //            $getMsgCover.css({
         //                height: $getMsgCover.parent().height(),
         //                lineHeight: $getMsgCover.parent().height()+'px',
         //                width: $getMsgCover.parent().width(),
         //                zIndex: 100
         //            }).text(getTitle+'上传中...').show();
         //            // upload file
         //            var formData=new FormData();
         //                formData.append('uploadFiles', $(this)[0].files[0]);
         //                formData.append('personId', '${personId}');
         //                //上传文件类型  需要将值设置在指定的上传文件表单中
         //                formData.append("fileType", getFileType);
                    
         //            var tmpl = [];
         //            tmpl.push('<tr>');
         //            tmpl.push('<td><a href="@imgUrl">@originName</a></td>');
         //            tmpl.push('<td>@uploadDate</td>');
         //            tmpl.push('<td>@uploader</td>');
         //            tmpl.push('</tr>');
         //            tmpl = tmpl.join('');
         //            $.ajax({
         //                url:  "employee/uploadSecurityFiles",
         //                type: 'POST',
         //                cache: false,
         //                dataType: 'json',
         //                data: formData,
         //                processData: false,
         //                contentType: false

         //            })
         //            .done(function(d){
         //                if(d.success){
         //                    tmpl = tmpl.replace('@imgUrl', d.imgUrl)
         //                        .replace('@originName', oFile)
         //                        .replace('@uploadDate', 'Thornton')
         //                        .replace('@uploader', '@fat');
         //                    $listAttachment.append(tmpl);
                            
         //                    $getMsgCover.text(getTitle+'上传成功。');
         //                    setTimeout(function(){ $getMsgCover.hide(); }, 1500);
         //                }else{
         //                    $getMsgCover.text(getTitle+'上传失败。');
         //                    setTimeout(function(){ $getMsgCover.hide(); }, 1500);
         //                }

         //            });

         //        });

         //        $uploadCompent.on('click', function(event){
         //            $(this).find(':file').trigger('click.file:selected');
         //        });
                 
         //        $modal.modal('show');

         //    });

         // }
    
        // 取消按钮
        // function cancel(){
    //         $('[data-id="cancel"]').on('click', function(e) {
    //            $(e.target).parents('.modal').modal('hide');
    //         });
    //         $('[data-modal]').on('hide.bs.modal', function(e) {
    //             var getTargetClear = $(e.target).find('[data-target=clearHtml]');
    //             getTargetClear.empty();
    //         });
    //     }
   
    // });
</script>
</body>
</html>
