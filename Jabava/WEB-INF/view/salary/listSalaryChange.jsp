<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="com.jabava.utils.RequestUtil"%>
<!DOCTYPE html>
<html>
<head>
<base href="${pageContext.request.contextPath}/">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge">

<!-- Page title -->
<title>工资变动管理</title>
<jsp:include flush="true" page="../common/styles.jsp"></jsp:include>
<!--for 临时改变-->
<link rel="stylesheet" href="static/js/plugins/form.validation/css/formValidation.css">
<link rel="stylesheet" href="static/css/user.css">

</head>
<body>

    <!--splash screen-->
    <jsp:include flush="true" page="../common/splashscreen.div.jsp"></jsp:include>

    <!--引入头文件 开始--> 
    <jsp:include flush="true" page="../common/header.div.jsp"></jsp:include>
    <!--引入头文件 结束-->
     
    <!--引入菜单文件 开始--> 
    <jsp:include flush="true" page="../common/menu.div.jsp"></jsp:include>
    <!--引入菜单文件 结束--> 

    <!-- 放主要内容  开始-->
    <!-- Main Wrapper -->
    <div id="wrapper">
        <div class="normalheader transition animated fadeIn small-header">
            <div class="hpanel">
                <div class="panel-body">
                    <div id="hbreadcrumb" class="m-t-xs m-b-xs">
                        <h2 class="font-normal m-b-xs text-center">
                            外部数据导入
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
                            <form class="form-horizontal" id="editForm">
                            	<input type="hidden" id="filePath" name="filePath">
                            	<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
                                         <div class="form-group">
                                            <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-3">月份：</label>
                                            <div class="col-xs-6 col-sm-6 col-md-6 col-lg-7">
                                            <div class="input-group date"> 
                                                <input type="text" class="form-control" id="monthly" name="monthly" />
                                                <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                            </div>
                                        </div>
                                        </div>
                                    </div>
                                    <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
                                        <div class="form-group">
                                            <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-3">变动表：</label>
                                            <div class="col-xs-6 col-sm-6 col-md-6 col-lg-7">
                                                <select class="form-control" id="salaryChangeDefId" name="salaryChangeDefId">
                                                    <option value="">请选择</option>
                                                    <c:forEach var="def" items="${requestScope.salaryChangeDefList }" varStatus="status">
                                                    	<option value="${def.salaryChangeDefId }" isMonthly="${def.isMonthly }">${def.name }</option>
                                                    </c:forEach>
                                                </select>
                                            </div>   
                                        </div>
                                    </div>
                                    <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
                                        <div class="form-group">
                                        <label class="control-label col-lg-4">上传文件：</label>
                                        <div class="col-lg-7">
                                            <div class="input-group" data-toggle="upload:file">
                                                <input type="text" class="form-control" readonly="readonly">
                                                <div class="input-group-btn">
                                                    <span class="btn btn-default">浏览...
                                                    <input type="file" class="sr-only" id="uploadFile" name='uploadFile' accept=".xlsx" ></span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    </div>
                                 <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-center">
                                    <% if(RequestUtil.hasPower("outerdataimport_dl")){ %>
                                     <button class="btn btn-info btn-sm" type="submit" data-motive="download">下载模板</button>
                                     <% } %>
                                     <% if(RequestUtil.hasPower("outerdataimport_im")){ %>
                                     <button class="btn btn-info btn-sm" type="submit" data-motive="import">导入</button>
                                     <% } %>
                                     <% if(RequestUtil.hasPower("outerdataimport_vd")){ %>
                                     <button class="btn btn-info btn-sm" type="submit" data-motive="view">查看</button>
                                     <% } %>
                                     <% if(RequestUtil.hasPower("outerdataimport_ed")){ %>
                                     <button class="btn btn-info btn-sm" type="submit" data-motive="clear">清空</button>
                                     <% } %>
                                     <% if(RequestUtil.hasPower("outerdataimport_ex")){ %>
                                     <button class="btn btn-info btn-sm" type="submit" data-motive="export">导出</button>
                                     <% } %>
                                </div>
                            </form>
                        </div>  
                        <!-- table -->
                        <div class="panel-body m-t-md"> 
                            <table id="changeTable" class="table table-condensed table-hover table-bordered dataTable table-responsive" width="100%">
                                <thead id="detailHeader">
                                </thead>
                                <tbody>
                                </tbody>
                            </table>
                        </div>           
                    </div>
                </div>
            </div>
        </div>      
        <!--主要内容结束-->
        <!-- 放页脚  开始-->
        <jsp:include flush="true" page="../common/foot.div.jsp"></jsp:include>
        <!-- 放页脚  结束-->
    </div>
    <div style="display:none;">
    	<form id="tempForm" action="" method="post">
    		<input type="hidden" id="tempId" name="salaryChangeDefId">
    		<input type="hidden" id="tempMonthly" name="monthly">
    	</form>
    	<form id="templateForm" action="" method="post">
    		<input type="hidden" id="changeDefId" name="id">
    	</form>
    </div>
    
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
    <script src="static/bootstrap/vendor/xeditable/bootstrap3-editable/js/bootstrap-editable.min.js"></script>
    <script src="static/bootstrap/vendor/toastr/build/toastr.min.js"></script>
    <!-- for datatable -->
    <script src="static/bootstrap/vendor/datatables/media/js/jquery.dataTables.min.js"></script>
    <script src="static/bootstrap/vendor/datatables_plugins/integration/bootstrap/3/dataTables.bootstrap.min.js"></script>
    <script src="static/bootstrap/vendor/bootstrap-datepicker-master/dist/js/bootstrap-datepicker.js"></script>
    <script src="static/bootstrap/vendor/bootstrap-datepicker-master/dist/locales/bootstrap-datepicker.zh-CN.min.js"></script>
    <!-- alert -->
    <script src="static/bootstrap/vendor/sweetalert/lib/sweet-alert.min.js"></script>
    
    <!-- App scripts -->
    <script src="static/bootstrap/scripts/homer.js"></script>
    <script src="static/bootstrap/scripts/charts.js"></script>
    <!-- 上传 -->
    <script src="static/js/common/ajaxfileupload.js"></script>
     <!-- 表单验证 -->
    <script src="static/js/plugins/form.validation/js/formValidation.js"></script>
    <script src="static/js/plugins/form.validation/js/framework/bootstrap.js"></script>
    <script src="static/js/plugins/form.validation/js/language/zh_CN.js"></script> 
<script type="text/javascript">
	var detailTable;

    // 文件上传域事件绑定
    var entUploader = function(){
        $('[data-toggle="upload:file"] :file').on('change.file:selected', function(event){

            var oEventTarget = $(this),
                oFile = $(this).val(),
                fileExt = oFile.substr(oFile.lastIndexOf(".")).toLowerCase()

            if(fileExt!='.xlsx'){
                alert("请上传后缀名为xlsx的文件!");
                return;
            }

            oEventTarget.parents('[data-toggle="upload:file"]')
            .find(':text').val( oFile )
        })

        $('[data-toggle="upload:file"]').on('click', function(event){
            $(this).find(':file').trigger('click.file:selected')

        });
    };
    
    $(function(){
    	$.ajaxSetup({
    	    dataFilter : function(data, type){
    	        //console.log("data:" + data);
    	        if(data.indexOf("AjaxAuthFailed") != -1){
    	        //if(data == "AjaxAuthFailed"){
    	        	window.location.reload();
    	        	return false;
    	        }else if(data.indexOf("NoPermission") != -1){
    	           	window.location.href = "common/noPermission";
    	           	return false;
    	        }else if(data.indexOf("OperationException") == 0){
    	           	alert(data.substring(18));
    	           	return '{"success":false, "data":[], "length":10, "start":0, "recordsTotal":0, "recordsFiltered":0}';
    	        }else{
    	            return data;
    	        }
    	    }
    	});
    	
        $('#editForm')[0].reset();
        entUploader();

        //表单验证
        var validators = {
            download: {
                err: {
                    container: 'tooltip'
                },
                icon: {
                    valid: 'glyphicon glyphicon-ok',
                    invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                }, 
                fields: {
                    salaryChangeDefId: {
                        validators: {
                            notEmpty:{
                                message: '请填写必填项'
                            }
                        }
                    }
                }
            },
            import: {
                err: {
                    container: 'tooltip'
                },
                icon: {
                    valid: 'glyphicon glyphicon-ok',
                    invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                }, 
                fields: {
                    /* monthly: {
                        validators: {
                            notEmpty: {
                                message: '日期格式不正确',
                                regexp:/^\d{4}\d{1,2}$/
                            },
                        }
                    }, */
                    salaryChangeDefId: {
                        validators: {
                            notEmpty:{
                                message: '请填写必填项'
                            }
                        }
                    },
                    uploadFile: {
                        validators: {
                            notEmpty:{
                                message: '请填写必填项'
                            }
                        }
                    },
                }
            },
            general: {
                err: {
                    container: 'tooltip'
                },
                icon: {
                    valid: 'glyphicon glyphicon-ok',
                    invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                }, 
                fields: {
                    /* monthly: {
                        validators: {
                            notEmpty: {
                                message: '日期格式不正确',
                                regexp:/^\d{4}\d{1,2}$/
                            },
                        }
                    }, */
                    salaryChangeDefId: {
                        validators: {
                            notEmpty:{
                                message: '请填写必填项'
                            }
                        }
                    }
                }
            }
        };

        $('[data-motive]').on('click', function(event){
            var getMotive = $(event.target).attr('data-motive');
            switch(getMotive){
                case 'download':
                    $('#editForm').formValidation(validators.download)
                    .on('success.form.fv', function(e){
                        e.preventDefault();
                        // console.log(['download', $('#salaryChangeDefId').val()]);
                        $('#changeDefId').val($('#salaryChangeDefId').val());
                        $('#templateForm').attr('action','salaryChangeDef/exportSalaryChangeDef');
                        $('#templateForm').submit();
                        $('#editForm').formValidation('destroy');
                    })
                    break;
                case 'import':
                    $('#editForm').formValidation(validators.import)
                    .on('success.form.fv', function(e){
                        e.preventDefault();
                        
                        if(!checkMonth()){
                        	return false;
                        }
                        
                        $.ajaxFileUpload({
                            url: 'common/uploadFile',       //用于文件上传的服务器端请求地址
                            secureuri: false,               //是否需要安全协议，一般设置为false
                            fileElementId: ['uploadFile'],  //文件上传域的ID
                            async:false,
                            dataType:'text',
                            type:"POST",
                            success: function (result){     //服务器成功响应处理函数
                                if(!result.success){
                                    alert(result.msg);
                                    return false;
                                }
                            
                                $("#filePath").val(result.filePath);
                                
                                $.ajax({
                                    url : "salaryChangeData/uploadSalaryChangeData",
                                    data : $("#editForm").serialize(),
                                    async : false,
                                    dataType:'json',
                                    type : 'post',
                                    success : function(message) {
                                        if(message.success){
                                            swal({
                                                title: "上传成功!",
                                                text: "",
                                                type: "success",
                                                confirmButtonText: "确定"
                                            });
                                            entUploader();
                                            loadHeader($('#monthly').val(), $('#salaryChangeDefId').val());
                                        }else{
                                            swal({
                                                title: "上传失败!",
                                                text: message.msg,
                                                type: "error",
                                                confirmButtonText: "确定"
                                            },function(){

                                                toastr.options = {
                                                    "debug": false,
                                                    "newestOnTop": false,
                                                    "positionClass": "toast-top-center",
                                                    "closeButton": false,
                                                    "toastClass": "animated fadeInDown",
                                                };
                                                var msgs = message.rowMessagge,
                                                    messager = []
                                                if( msgs ){
                                                    $.map(msgs, function(item, index){
                                                        messager[index] = '<p>第 '+item.row+' 行第 '+item.cell+' 列，'+item.msg+'</p>'
                                                    });
                                                    toastr.error(messager);
                                                }
                                            });
                                        }
                                    }
                                });
                            }
                        });
                        
                        $('#editForm').formValidation('destroy');
                    })
                    
                    break;
                case 'view':
                    $('#editForm').formValidation(validators.general)
                    .on('success.form.fv', function(e){
                        e.preventDefault();
                        
                        if(!checkMonth()){
                        	return false;
                        }

                        loadHeader($('#monthly').val(), $('#salaryChangeDefId').val());

                        $('#editForm').formValidation('destroy');
                    })
                    break;
                case 'clear':
                    $('#editForm').formValidation(validators.general)
                    .on('success.form.fv', function(e){
                        e.preventDefault();

                        if(!checkMonth()){
                        	return false;
                        }
                        
                        clear($('#monthly').val(), $('#salaryChangeDefId').val());

                        $('#editForm').formValidation('destroy');
                    })

                    break;
                case 'export':
                    $('#editForm').formValidation(validators.general)
                    .on('success.form.fv', function(e){
                        e.preventDefault();

                        if(!checkMonth()){
                        	return false;
                        }
                        
                        $('#tempId').val($('#salaryChangeDefId').val());

                        $('#tempMonthly').val($('#monthly').val());
                        $('#tempForm').attr('action','salaryChangeData/exportSalaryChangeData');
                        $('#tempForm').submit();

                        $('#editForm').formValidation('destroy');
                    })
                    
                    break;
            }
        });

        // 日历
        $('.input-group.date').datepicker({
            format: 'yyyymm', 
            weekStart: 1, 
            autoclose: true, 
            startView: 1,
            maxViewMode: 1,
            minViewMode:1,
            forceParse: false, 
            language: 'zh-CN'
        });
        /*.on('changeDate', function(e){
            var getEleName = $(e.target).find(':text').attr('name');
            $('#editForm').formValidation('revalidateField', getEleName);
        });*/

    }); //(function(){})()
	
    function clear(monthly, salaryChangeDefId){
    	swal(
    		{
	            title: "确定要清除数据吗?",
	            text: "",
	            type: "warning",
	            showCancelButton: true,
	            confirmButtonColor: "#DD6B55",
	            confirmButtonText: "确定删除!",
	            cancelButtonText: "放弃操作!",
	            closeOnConfirm: false,
	            closeOnCancel: false 
            },
        	function (isConfirm) {
	            if (isConfirm) {
	            	doClear(monthly, salaryChangeDefId);
	            }else{
	            	swal("已取消", "数据未清除", "error");
	            }
	        }
        );
    }
	
	function doClear(monthly, salaryChangeDefId){
		var url = "salaryChangeData/clearSalaryChangeData";
		var data = {"monthly": monthly, "salaryChangeDefId": salaryChangeDefId};
		if(salaryChangeDefId == -1){
			url = "employee/deleteByYearMonthRecord";
			data = {"yearMonthRecord": monthly.substring(0,4) + "-" + monthly.substring(4)};
		}
		
		$.ajax({
			url: url,
			type : "POST",
			dataType:'json',
			data: data,
			error: function(XMLHttpRequest, textStatus, errorThrown){
				alert(textStatus);
			},
			success: function(message){
				if(message.success){
					swal({
	                    title: "清除成功!",
	                    text: "",
	                    type: "success",
	    				confirmButtonText: "确定"
	                });
					
					if(detailTable){
						detailTable.ajax.reload();
					}
				}else{
					swal({
	                    title: "清除失败!",
	                    text: message.msg,
	                    type: "error",
	    				confirmButtonText: "确定"
	                });
				}
			}
		});
	}
	
	function loadHeader(monthly, salaryChangeDefId){
    	$.ajax({
			url:"salaryChangeData/loadHeaderList",
			type : "POST",
			dataType:'json',
			data: {"monthly": monthly, "salaryChangeDefId": salaryChangeDefId},
			error: function(XMLHttpRequest, textStatus, errorThrown){
				alert(textStatus);
			},
			success: function(data){
				if(data){
					//可能是拦截后的数据，不能使用!data.success
					if(data.success == false){
						return false;
					}
				
			    	if(detailTable){
			    		detailTable.destroy();
			    	}
			    	//in case the columns change
		    		$('#changeTable').empty();
			    	
					initDetailTable(data, monthly, salaryChangeDefId);
				}
			}
		});
    }
    
    function initDetailTable(data, monthly, salaryChangeDefId){
    	//读取数据，初始化表头及DataTables的columns
    	var columns = [];
    	
    	//动态添加表头
		var firstRow = $("<tr></tr>");
    	$.each(data,function(){
    		var fieldName = this.fieldName;
   			var ele = '<th>' + this.showName + '</th>';
   			firstRow.append($(ele));
   			if(this.showName == '身份证' || this.showName == '工号'){
	   			columns.push({"data":this.fieldName});
	    	}else{
	    		if(salaryChangeDefId == -1){
	    			columns.push({"data":this.fieldName, "render": function render( data, type, row, meta ){
	    				<% if(RequestUtil.hasPower("outerdataimport_md")){ %>
						return'<a id="' + fieldName + '" class="editable editable-click" data-placement="right" data-pk="' + row.attend_id + '" data-type="text" href="#" style="display: inline;">' + (data ? data : 0) + '</a>';
						<% }else{ %>
						return data ? data : 0;
						<% } %>
	    			}});
	    		}else{
		    		columns.push({"data":this.fieldName, "render": function render( data, type, row, meta ){
		    			<% if(RequestUtil.hasPower("outerdataimport_md")){ %>
		    			return'<a id="' + fieldName + '" class="editable editable-click" data-placement="right" data-pk="' + row.salary_change_data_id + '" data-type="text" href="#" style="display: inline;">' + (data ? data : 0) + '</a>';
		    			<% }else{ %>
						return data ? data : 0;
						<% } %>
		    		}});
	    		}
	    	}
    	});
    	
    	//增加删除列
    	firstRow.append($('<th>操作</th>'));
    	if(salaryChangeDefId == -1){
    		columns.push({ "render": function render( data, type, row, meta ){
    			<% if(RequestUtil.hasPower("outerdataimport_dd")){ %>
				return '<button class="btn btn-danger btn-xs del-button" type="button" onclick="del(' + salaryChangeDefId + ',' + row.attend_id + ');">删除</button>';
				<% }else{ %>
				return '';
				<% } %>
				}
			});
    	}else{
	    	columns.push({ "render": function render( data, type, row, meta ){
	    		<% if(RequestUtil.hasPower("outerdataimport_dd")){ %>
				return '<button class="btn btn-danger btn-xs del-button" type="button" onclick="del(' + salaryChangeDefId + ',' + row.salary_change_data_id + ');">删除</button>';
				<% }else{ %>
				return '';
				<% } %>
	    		}
			});
    	}
    	
    	// append header to the table
    	var header = $("<thead></thead>");
    	header.append(firstRow);
    	$('#changeTable').append(header);
    	
    	initTable(columns, monthly, salaryChangeDefId);
    }
    
    function initTable(columns, monthly, salaryChangeDefId){
    	var url = "salaryChangeData/listSalaryChangeDataPage";
    	var data = {"monthly": monthly, "salaryChangeDefId": salaryChangeDefId};
    	var callbackUrl = 'salaryChangeData/updateSalaryChangeData';
    	if(salaryChangeDefId == -1){
    		url = "employee/listAttentancePage";
        	data = {"yearMonthRecord": monthly.substring(0,4) + "-" + monthly.substring(4)};
        	callbackUrl = 'employee/updateAttendance';
    	}
    	
    	detailTable = $('#changeTable').DataTable({
    		"dom":
	    		"<'row'<'col-sm-12'l>>" +
	    		"<'row'<'col-sm-12 table-responsive'tr>>" + 
	    		"<'row'<'col-sm-5'i><'col-sm-7'p>>",
				
		    "processing": true,
    		"serverSide": true,
    		"bDestroy": true,
    		"sServerMethod": "POST",
    		"ordering": false,
    		//"bSort": true,
    		"columns": columns,
    		"columnDefs": [
    			{defaultContent: '', targets: '_all'}
			],
    		"ajax": {
    			"url": url,
    			"data": data
    		},
			drawCallback: function(){
    			$('.editable').editable({
    				url : callbackUrl,
    				validate : function(value) {
    					if ($.trim(value) == '') {
    						return 'This field is required';
    					}
    				},
    				error : function(response, newValue) {
    					if (response.status === 404) {
    						return '服务器问题（404），请联系管理员';
    					} else if (response.status === 500) {
    						return '服务器问题（500），请联系管理员';
    					}
    				},
    				success : function(response, newValue) {
    					var obj = JSON.parse(response);
    					if (!obj.success) {
    						return '修改失败';
    					}
    				}
    			});
			},
   	 		"language": {
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
    	});
		
    	//$("#detailTable").css('width', 'auto');
    	$("table.table-bordered th:last-child, table.table-bordered td:last-child").css("border-right-width","1px");
    }

    // 修改
    $.fn.editable.defaults.mode = 'inline';
   	$('.editable').editable({
       	validate: function(value) {
           	if($.trim(value) == ''){
               	return 'This field is required';
            }
        }
    });
   	
    function del(salaryChangeDefId, id){
    	swal(
    		{
	            title: "确定要删除此数据吗?",
	            text: "",
	            type: "warning",
	            showCancelButton: true,
	            confirmButtonColor: "#DD6B55",
	            confirmButtonText: "确定删除!",
	            cancelButtonText: "放弃操作!",
	            closeOnConfirm: false,
	            closeOnCancel: false 
            },
        	function (isConfirm) {
	            if (isConfirm) {
		            deleteById(salaryChangeDefId, id);
	            }else{
	            	swal("已取消", "数据未删除", "error");
	            }
	        }
        );
    }
    
    function deleteById(salaryChangeDefId, id){
    	$.ajax({
			url : salaryChangeDefId == -1 ? "employee/deleteById" : "salaryChangeData/deleteSalaryChangeData",
			data : salaryChangeDefId == -1 ? {attendId: id} : {salaryChangeDataId: id},
			dataType:'json',
			type : 'post',
			success : function(message) {
				if(message.success){
					swal({
	                    title: "删除成功!",
	                    text: "",
	                    type: "success",
	    				confirmButtonText: "确定"
	                });
					
					detailTable.ajax.reload();
				}else{
					swal({
	                    title: "删除失败!",
	                    text: message.msg,
	                    type: "error",
	    				confirmButtonText: "确定"
	                });
				}
			}
		});
    }

	function checkMonth(){
		var isMonthly = $("#salaryChangeDefId").find("option:selected").attr("isMonthly");
		if(isMonthly == "0"){
			return true;
		}
		
		if($("#monthly").val() == ''){
			alert("请选择月份");
			return false;
		}
		
		return true;
	}
</script>
</body>
</html>