//audit_employee_done.js
;(function($){
	'use strict'
	var initTable = function(){
		$('#auditToDone').dataTable({
			"ajax":{
				"url":"employee/auditData",
				"type":"POST",
				"data":{type:2}
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
	                	if(row.approved_status==3){
	                		return "已审核";
	                	}
	                }
                },
                {"data":"approved_status" ,render:function(data, type, row, meta){
                		var strHtml = '<button type="button" data-btn="approve" class="btn btn-info btn-xs" value="'+row.person_id+'">查看</button>';
                		return strHtml;
                	},
                	createdCell: function (td, cellData, rowData, row, col){
                        
                        $(td).on('click', '[data-btn]', function(event) {
                        	
                           console.log(rowData.approved_status);
                    	   location.href="employee/toPageByLinkType?linkType=audit_personal&personId="+rowData.person_id;
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