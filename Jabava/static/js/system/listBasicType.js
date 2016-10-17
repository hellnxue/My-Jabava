//listBasicType.js
;(function($){
	'use strict'; 
    var initTable = function(){
        $('#downLoad').dataTable( {
            "ajax":{
                    "url": "system/selectByPage",
                    "type":"POST",
                }, 
                "processing": true,
                "serverSide": true,
                "bDestroy": true,
                "sort":false,
                "columns": [
                        { "data": "baseDataType",
                            render: function(data, type, row, meta){
                                var strHtml = '<input type="checkbox" name="checkAll" data-check="item" data-id="'+row.companyId+'" value="'+row.baseDataType+'">';
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
                                        var getColumnName = rowData.columnName;
                                        var getCompanyId = $getCheckItem.attr('data-id');
                                        $('[data-dataType="editType"]').val(getIDs);
                                        $('#editForm').find('#companyId').val(getCompanyId)
                                    }
                                });
                            }},
                        { "data": "baseDataType"},
                        { "data": "baseDataTypeName",render: function( data, type, row, meta ){
                            var href = "system/searchBaseData?baseDataType="+ row.baseDataType;
                           if(href){
                            return '<a href="'+href+'">'+data+'</a>'; 
                           } else{
                            return '<a href="javascript:void(0);">'+data+'</a>';
                           }  
                           }},
                        { "data": "lastModifyUserName"},
                        { "data": "lastModifyDate"},
                        { "data": "memo"}                   
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

     var add = function(){        
        var validateOptions = {
          framework: 'bootstrap',
          err: {
              container: 'tooltip'
          },
          icon: {
              valid: 'glyphicon glyphicon-ok',
              invalid: 'glyphicon glyphicon-remove',
              validating: 'glyphicon glyphicon-refresh'
          },
          locale: 'zh_CN',        
          fields: {
            baseDataTypeName: {
                validators: {
                    notEmpty: {
                        message: '请填写必填项目'
                    }
                }
            }
        }   
        }
        $('[data-form="validator"]')
        .formValidation(validateOptions)
        .on('success.form.fv',function(e){
          e.preventDefault();
          var getFormType = $(e.target).attr('data-form-type');
              switch (getFormType)
                {
                case 'add':
                    $.ajax({
                      url: 'system/addAndUpdateBaseDataType',
                      type: 'POST',
                      dataType: 'json',
                      data : $("#addForm").serialize(),
                      success : function(result) {
                            if(result.success){
                                swal({
                                    title: "新增成功!",
                                    text: result.msg,
                                    type: "success"                                  
                                },function(){
                                window.location.reload();
                            }); 
                            }else{
                                 swal({
                                    title: "新增失败!",
                                    text: result.msg,
                                    type: "error"
                                });               
                            }   
                        } 
                    });              
                  break;
                case 'edit':   
                    $.ajax({
                      url: 'system/addAndUpdateBaseDataType',
                      type: 'POST',
                      dataType: 'json',
                      data: $("#editForm").serialize(),
                      success : function(result) {
                    	  console.log(result.msg);
                            if(result.success){
                                swal({
                                    title: "修改成功!",
                                    text: result.msg,
                                    type: "success"                                  
                                },function(){
                                window.location.reload();
                            }); 
                            }else{
                                 swal({
                                    title: "修改失败!",
                                    text: result.msg,
                                    type: "error"
                                });               
                            }   
                        } 
                    });   
                  break;
                };
        }) ;    
        
    }();


    }();

  

    // 取消
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

    var edit =function(){
      $('[data-action="edit"]').on('click', function(event){
           var len = $(":checked[name='checkAll']").length;
                if(len == 0 || len > 1){
                    alert("请选择一个清单！");
                    return false;
                };
        });
    }();
     
   
})(jQuery)