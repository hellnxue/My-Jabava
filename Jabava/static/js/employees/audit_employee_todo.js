//audit_employee_todo.js
;(function($){
	'use strict'
	var initTable = function(){
		$('#auditToDo').dataTable({
			"ajax":{
				"url":"employee/auditData",
				"type":"POST",
				"data":{type:1}
			},
			 "processing": true,
	         "serverSide": true,
	         "bDestroy": true,
	         "sort":false,
	         "columns":[
                {"data":"job_number"},
                {"data":"employee_name"},
                {"data":"organization_name"},
                {"data":"base_data_name"},
                {"data":"phone"},
                {"data":"email"},
                {"data":"approved_status", render:function(data, type, row, meta){
                	if(row.approved_status==1){
                		return "未提交";
                	}else if(row.approved_status==2){
                		return "未审核";
                	}
                }},
                {"data":"approved_status" ,render:function(data, type, row, meta){
                	if(row.approved_status==1){
                		var strHtml = '<button type="button" data-btn="remind" class="btn btn-info btn-xs" value="'+row.person_id+'">提醒</button>';
                		return strHtml;
                	}else if(row.approved_status==2){
                		var strHtml = '<button type="button" data-btn="approve" class="btn btn-info btn-xs" value="'+row.person_id+'">审核</button>';
                		return strHtml;
                	}
                	},
                    createdCell: function (td, cellData, rowData, row, col){
                        $(td).on('click', '[data-btn]', function(event) {
                           console.log(rowData.approved_status);
                           if(rowData.approved_status==1){
                               $.ajax({
                                   url : "system/sendMailAndMsg/" + rowData.person_id + "/1",
                                   async : false,
                                   dataType:'json',
                                   type : 'post',
                                   success : function(message) {
                                       if (message.success) {
                                           swal({
                                               title : "提醒成功",
                                               text : message.msg,
                                               type : "success",
                                               confirmButtonText : "确定"
                                           });
                                       } else {
                                           swal({
                                               title : "提醒失败",
                                               text : message.msg,
                                               type : "error",
                                               confirmButtonText : "确定"
                                           });
                                       }
                                   },
                                   error : function (message) {
                                       alert(message.msg);
                                   }
                               });
                           }else{
                        	   location.href="employee/toPageByLinkType?linkType=audit_personal&personId="+rowData.person_id;
                           }
                            	
                        });
                    }
                	}  
             ],
    		"columnDefs": [
    			{defaultContent: '', targets: '_all'}
			],
			dom:
                "<'row'<'col-sm-6'l><'col-sm-6 hidden'f>>" +
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
	}()
})(jQuery)