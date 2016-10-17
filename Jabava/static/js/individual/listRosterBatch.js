//listRosterBatch.js
;(function($){
	'use strict'; 
    var initTable = function(){
        $('#downLoad').dataTable({
            "ajax":{
                    "url": "individual/selectRosterBatch",
                    "type":"POST",
                    "data": {type:individualType}
            },  
            "processing": true,
            "serverSide": true,
            "bDestroy": true,
            "sort":false,
            "columns": [
                { "data": "batchCode",
                    render: function(data, type, row, meta){
                        var strHtml = '<input type="checkbox" name="checkAll" data-check="item" value="'+row.id+'">';
                        return strHtml;
                    },
                    createdCell: function (td, cellData, rowData, row, col){
                        var ueSelected = function($o, bSelectLast){
                            bSelectLast = bSelectLast === undefined? true : bSelectLast;
                            var getAllVal = [];
                            if(bSelectLast){
                                $('[data-check="item"]').not($o).prop('checked', false);
                                getAllVal.push( $o.val() );
                            }else{
                                $.each($('[data-check="item"]:checked'), function(index, item) {
                                    getAllVal.push( $(this).val() );
                                });
                            }
                            getAllVal = getAllVal.join(',');
                            return getAllVal;
                        };
                        $(td).on('click', '[data-check="item"]', function(event) {
                            var $getCheckItem = $(this);
                            if( $getCheckItem.prop('checked') ){
                                var getIDs = ueSelected($getCheckItem);
                                var getColumnName = rowData.columnName
                                $('[data-modals="formEdits"]').data('itemsId', getIDs);
                                $('[data-modals="formEdits"]').data('batchCode', rowData.batchCode);
                                $('[data-modals="formEdits"]').data('fileId', rowData.fileId);
                                $('[data-action="del"]').data('itemsId', getIDs);
                            }else{
                                $('[data-modals="formEdits"]').removeData('itemsId');
                                $('[data-modals="formEdits"]').removeData('fileId');
                                $('[data-action="del"]').removeData('itemsId');
                            }
                        });
                    }
                },
                { "data": "batchCode" , render: function( data, type, row, meta ){
                	var href =  row.filepath;
	           		 if(href){
	           			return '<a href="'+href+'">'+data+'</a>'; 
	           		 } else{
	           			return '<a href="javascript:void(0);">'+data+'</a>';
	           		 }
					
			    }
                
                },
                { "data": "updatedDate", 
                    render: function( data, type, row, meta ){
    					if(data && data.length >= 10){
    						return data.substring(0,10);
    					}
    					return data;
				    }
                }
            ],
            dom:
                "<'row'<'col-sm-6'l><'col-sm-6'f>>" +
                "<'row'<'col-sm-12 table-responsive'tr>>" +
                "<'row'<'col-sm-5'i><'col-sm-7'p>>",
            "language":{
                "search": "过滤:",
                "lengthMenu": "每页显示 _MENU_ 条记录",
                "zeroRecords": "暂无数据 - 报歉啦?",
                "info": "显示 第 _PAGE_ 页 共 _PAGES_ 页",
                "infoEmpty": "暂无数据",
                "infoFiltered": "(筛选自 _MAX_ 条记录)",
                "paginate":{
                    "first":"首页",
                    "previous":"前一页",
                    "next":"后一页",
                    "last":"尾页"
                }
            }
        })
    }();
    var addAndEdit = function(){
        var zxAction="";
        toastr.options = {
          "debug": false,
          "newestOnTop": false,
          "positionClass": "toast-top-center",
          "closeButton": false,
          "toastClass": "animated fadeInDown",
        };
        var validators = {
            err: {
                container: 'tooltip'
            },
            icon: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                batchNumber: {
                    validators: {
                        notEmpty: {
                            message: '请填写必填项'
                        }
                    }
                },
                jobpostFile: {
                    validators: {
                        notEmpty:{
                            message: '请填写必填项'
                        }
                    }
                }
            }
        };
        
        $("#downLoadForm").formValidation(validators)
        .on('success.form.fv', function(e){
        	e.preventDefault();
            var $modal = $(this).parents('[data-modal]');;
            var $uploadCompent = $modal.find('[data-toggle="upload:file"]');
            var getThisAction = $(zxAction).attr('data-action');
            var $file=$uploadCompent.find(":file");
            var $getMsgCover = $uploadCompent.find(":file").parents('[data-toggle="upload:file"]').siblings('.msg-cover');
            var getTitle = '总体人员名单';
            var $batchNumber = $(this).find('[name="batchNumber"]');
        	
            switch (getThisAction){
                case 'add':
                    $getMsgCover.css({
                        height: $getMsgCover.parent().height(),
                        lineHeight: $getMsgCover.parent().height()+'px',
                        width: $getMsgCover.parent().width(),
                        zIndex: 100
                    }).text(getTitle+'上传中...').show();
                    var formData=new FormData();

                    formData.append('importFile',$file[0].files[0]);
                    formData.append('batchCode', $batchNumber.val());
                    formData.append("type", individualType);
                    $.ajax({
                        url:  "individual/uploadIdvRosterBatchDetail",
                        type: 'POST',
                        cache: false,
                        dataType: 'json',
                        data: formData,
                        processData: false,
                        contentType: false
                    })
                    .done(function(d){
                        if(d.success){
                            $getMsgCover.text(getTitle+'上传成功。');
                            setTimeout(function(){ $getMsgCover.hide(); }, 1500);
                            swal({
                                title: '上传成功',
                                text: d.msg,
                                type: 'success'
                            },function(){
                                window.location.reload(true);
                            })
                        }else{
                            $getMsgCover.text(getTitle+'上传失败。');
                            setTimeout(function(){ $getMsgCover.hide(); }, 1500);

                            if(d.errorList != null){
                                var msgs = d.errorList,
                                messager = [];

                                if( msgs ){
                                    $.map(msgs, function(item, index) {
                                        messager[index] = '<p>"'+item+'"</p>';
                                    });
                                    toastr.success(messager);
                                }
                            }else{
                                swal({
                                    title: '上传失败',
                                    text: d.msg,
                                    type: 'error'
                                });
                            }


                        }
                    }); 
                    break;
                case 'edit':
                    var getItemsId = $modal.data('itemsId'),
                    getBatchCode = $modal.data('batchCode'),
                    getFileId = $modal.data('fileId');

                    $getMsgCover.css({
                        height: $getMsgCover.parent().height(),
                        lineHeight: $getMsgCover.parent().height()+'px',
                        width: $getMsgCover.parent().width(),
                        zIndex: 100
                    }).text(getTitle+'上传中...').show();
                    var formData=new FormData();

                    formData.append('importFile',$file[0].files[0]);
                    formData.append('batchCode', $batchNumber.val());
                    formData.append('id', getItemsId);
                    formData.append("type", individualType);
                    formData.append("fileId", getFileId);
                    $.ajax({
                        url:  "individual/updateIdvRosterBatchDetail",
                        type: 'POST',
                        cache: false,
                        dataType: 'json',
                        data: formData,
                        processData: false,
                        contentType: false
                    })
                    .done(function(d){
                        if(d.success){
                            $getMsgCover.text(getTitle+'上传成功。');
                            setTimeout(function(){ $getMsgCover.hide(); }, 1500);
                            swal({
                                title: '上传成功',
                                text: d.msg,
                                type: 'success'
                            },function(){
                                window.location.reload(true);
                            })
                        }else{
                            $getMsgCover.text(getTitle+'上传失败。');
                            setTimeout(function(){ $getMsgCover.hide(); }, 1500);

                            if(d.errorList != null){
                                var msgs = d.errorList,
                                messager = [];

                                if( msgs ){
                                    $.map(msgs, function(item, index) {
                                        messager[index] = '<p>"'+item+'"</p>';
                                    });
                                    toastr.success(messager);
                                }
                            }else{
                                swal({
                                    title: '上传失败',
                                    text: d.msg,
                                    type: 'error'
                                });
                            }


                        }
                    }); 
                    break;
            }
        	
        });
		$('[data-toggle="attachments"],[data-toggle="formEdits"]').on('click', function(event){
			zxAction=$(this);
            var getTarget = $(this).attr('data-target');
            var $modal = $(getTarget);
            var $uploadCompent = $modal.find('[data-toggle="upload:file"]');
            var getThisAction = $(this).attr('data-action');
            
            $uploadCompent.find(':file').on('change.file:selected', function(event){
                var oEventTarget = $(this),
                oFile = $(this).val();

                oEventTarget.parents('[data-toggle="upload:file"]')
                .find(':text').val( oFile );
            });
            $uploadCompent.on('click', function(event){
                $(this).find(':file').trigger('click.file:selected');
            });  
            if(getThisAction === 'edit'){
                var len = $(":checked[name='checkAll']").length;
                    if(len == 0 || len > 1){
                        alert("请选择一个清单！");
                        return false;
                    }
                var getItemsId = $modal.data('itemsId'),
                    getBatchCode = $modal.data('batchCode');
                    $modal.find('[name="batchNumber"]').val(getBatchCode);
            }
            $modal.modal('show');
        });
    }();
    var cancel = function(){
        $('[data-id="cancel"]').on('click', function(e) {
           $(e.target).parents('.modal').modal('hide');
        });
        $('[data-modal]').on('hide.bs.modal', function(e) {
            var getTargetClear = $(e.target).find('form');
            getTargetClear[0].reset();
            getTargetClear.formValidation('resetForm');
        });
    }();
    var del = function(){
        $('[data-action="del"]').click(function(){
            var len = $(":checked[name='checkAll']").length;
            if(len == 0 || len > 1){
                alert("请选择一个清单！");
             return false;
            }
            var getItemsId = $(this).data('itemsId');
            swal({
                title: "确定要删除此项吗?",
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "确定删除!",
                cancelButtonText: "放弃操作!",
                closeOnConfirm: false,
                closeOnCancel: false 
            },function(isConfirm){
                if(isConfirm){
                    $.ajax({
                        url: 'individual/deleteIdvRosterBatchDetail',
                        type: 'POST',
                        dataType: 'json',
                        data : {id: getItemsId},
                    })
                    .done(function(d){
                        if(d.success){
                            swal({
                                title: d.msg,
                                type: "success"
                            },function(){
                                window.location.reload(true);
                            })
                        }else{
                            swal({
                                title: d.msg,
                                type: "error"
                            })
                        }
                    })
                }else{
                    swal("已取消", "该项删除已取消", "error");
                }
            })
        })
    }();
})(jQuery)