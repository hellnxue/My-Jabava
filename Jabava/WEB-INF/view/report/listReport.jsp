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
<title>报表维护</title>
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

        <!-- 放主要内容 -->
        <div class="content animate-panel">
            <div class="row">
                <div class="col-lg-12">
                    <div class="hpanel">
                        <div class="panel-heading">
                            <h4 class="text-center font-bold">
                                报表维护
                            </h4>
                        </div>
                        
                        <div class="panel-body"> 
                            <form id="searchForm" class="form-horizontal">
                                <div class="col-xs-3 col-sm-3 col-md-3 col-lg-3">
                                    <div class="form-group">
                                        <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-5">报表类型：</label>
                                        <div class="col-xs-6 col-sm-6 col-md-6 col-lg-7">
                                            <select class="form-control" id="reportTypeForSearch" name="reportType">
                                                <option value="">全部</option>
                                                <c:forEach var="type" items="${requestScope.reportTypeList }" varStatus="status">
                                                <option value="${type.commonDataId }">${type.commonDataName }</option>
                                                </c:forEach>
                                            </select>
                                        </div>   
                                    </div>
                                </div>
                                <div class="col-xs-3 col-sm-3 col-md-3 col-lg-3">
                                    <div class="form-group">
                                        <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-5">报名表：</label>
                                        <div class="col-xs-6 col-sm-6 col-md-6 col-lg-7">
                                            <input type="text" class="form-control" id="reportNameForSearch" name="reportName">
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xs-3 col-sm-3 col-md-3 col-lg-3">
                                <div class="form-group">
                                    <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-5">范围类型：</label>
                                    <div class="col-xs-6 col-sm-6 col-md-6 col-lg-7">
                                        <select class="form-control" id="" name="scopeType" data-name="scopeType">
                                          <option value="" selected="selected">--------------------</option>
                                          <option value="1">全局</option>
                                          <option value="2">公司</option>
                                          <option value="3">平台</option>
                                        </select>
                                    </div>   
                                </div>
                             </div>
                             <div class="col-xs-3 col-sm-3 col-md-3 col-lg-3">
                                <div class="form-group">
                                    <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-5">应用范围：</label>
                                    <div class="col-xs-6 col-sm-6 col-md-6 col-lg-7">
                                      <select class="form-control" id="" name="scopeId" data-name="scopeId" disabled="disabled">
                                        <option value="">--------------------</option>
                                        <c:forEach items="${requestScope.companyList }" var="company">
                                        	<option value="${company.companyId }">${company.companyName }</option>
                                        </c:forEach>
                                      </select>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-center">
                                <button class="btn btn-info btn-sm btn-search-report" type="button" onclick="doSearch();">查  询</button>
                            </div>
                        </form>
                        </div>

                <!--新增弹框-->    
                 <div class="modal fade hmodal-success form-row" data-modal="addReport" tabindex="-1" role="dialog"  aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="color-line"></div>
                            <div class="modal-header">
                             <div class="row">
                             <div class="panel-body">
                              <form id="addForm" class="form-horizontal" data-form-type="add" data-form="validator">
                                <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
                                    <div class="form-group has-feedback">
                                        <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-4">报表类型：</label>
                                        <div class="col-xs-6 col-sm-6 col-md-6 col-lg-8 form-required">
                                            <select class="form-control" id="reportType" name="reportType">
                                                <option value="">请选择</option>
	                                            <c:forEach var="type" items="${requestScope.reportTypeList }" varStatus="status">
	                                            <option value="${type.commonDataId }">${type.commonDataName }</option>
	                                            </c:forEach>
	                                        </select>
                                        </div>   
                                    </div>
                                </div>
                                <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
                                    <div class="form-group has-feedback">
                                        <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-4">报名表：</label>
                                        <div class="col-xs-6 col-sm-6 col-md-6 col-lg-8 form-required">
                                            <input type="text" class="form-control" id="reportName" name="reportName">
                                        </div>
                                    </div>
                                </div>
                            <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
                            <div class="form-group">
                                <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-4">范围类型：</label>
                                <div class="col-xs-6 col-sm-6 col-md-6 col-lg-8">
                                    <select class="form-control" id="scopeType" name="scopeType">
                                      <option value="1" selected="selected">全局</option>
                                      <option value="2">公司</option>
                                      <option value="3">平台</option>
                                    </select>
                                </div>   
                            </div>
                         </div>
                         <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
                            <div class="form-group">
                                <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-4">应用范围：</label>
                                <div class="col-xs-6 col-sm-6 col-md-6 col-lg-8 form-required">
                                  <select class="form-control" id="scopeId" name="scopeId" disabled="disabled">
                                    <option value="">--------------------</option>
                                    <c:forEach items="${requestScope.companyList }" var="company">
                                    	<option value="${company.companyId }">${company.companyName }</option>
                                    </c:forEach>
                                  </select>
                                </div>
                            </div>
                        </div>
                                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-center">
                                    <button type="submit" class="btn btn-info btn-sm btn-add-report">确  定</button>
                                </div>
                            </form>
                              </div>
                            </div>   
                        </div>
                    </div>
                </div>
                </div>
                <!--新增弹框 end-->
                <!-- 上传 -->
                    <div class="modal fade hmodal-success" id="shangchuan" tabindex="-1" role="dialog"  aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="color-line"></div>
                            
                            <div class="modal-body">
                                <form class="form-horizontal" id="uploadForm">
                                    <input type="hidden" id="reportIdForUpload" name="reportId">
                                    <input type="hidden" id="fileId" name="fileId">
                                    <div class="form-group">
                                        <label for="" class="control-label col-xs-3 col-sm-3 col-md-3 col-lg-3">上传数据报表：</label>
                                        <div class="col-xs-9 col-sm-9 col-md-9 col-lg-9">
                                            <div class="input-group" data-toggle="upload:file">
                                                <input type="text" class="form-control" readonly="readonly">
                                                <div class="input-group-btn">
                                                    <span class="btn btn-default">浏览...
                                                    <input type="file" class="sr-only"  id='uploadFile' name='uploadFile'  accept=".xlsx" ></span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="text-center">
                                        <button id="upload" type="button" class="btn btn-info">上传</button>
                                    </div>
                                </form>

                            </div>
                            
                        </div>
                    </div>
                </div>
                <!-- 上传 end -->
                        <div class="panel-body m-t-md"> 
                            <table id="reportTable" class="table table-condensed table-hover table-bordered dataTable" width="100%">
                                <thead>
                                <tr>
                                    <th>报表类型</th>
                                    <th>报表名</th>
                                    <th>范围类型</th>
                                    <th>应用范围</th>
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
        </div> 
              
        <!--主要内容结束-->

        <!-- 放页脚  开始-->
        <jsp:include flush="true" page="../common/foot.div.jsp"></jsp:include>
        <!-- 放页脚  结束-->

    </div>
    <div style="display:none;">
    	<form id="detailForm" action="" method="post">
    		<input type="hidden" id="reportId" name="reportId">
    	</form>
    </div>
    
    <!-- Vendor scripts -->
    <script src="static/bootstrap/vendor/jquery/dist/jquery.min.js"></script>
    <script src="static/bootstrap/vendor/jquery-ui/jquery-ui.min.js"></script>
    <script src="static/bootstrap/vendor/slimScroll/jquery.slimscroll.min.js"></script>
    <script src="static/bootstrap/vendor/bootstrap/dist/js/bootstrap.min.js"></script>
    <script src="static/bootstrap/vendor/metisMenu/dist/metisMenu.min.js"></script>
    <script src="static/bootstrap/vendor/iCheck/icheck.min.js"></script>
    <script src="static/bootstrap/vendor/sparkline/index.js"></script>
    <script src="static/bootstrap/vendor/jquery-flot/jquery.flot.js"></script>
    <script src="static/bootstrap/vendor/jquery-flot/jquery.flot.resize.js"></script>
    <script src="static/bootstrap/vendor/jquery-flot/jquery.flot.pie.js"></script>
    <script src="static/bootstrap/vendor/flot.curvedlines/curvedLines.js"></script>
    <script src="static/bootstrap/vendor/jquery.flot.spline/index.js"></script>
    <script src="static/bootstrap/vendor/peity/jquery.peity.min.js"></script>
    
    <!-- for datatable -->
    <script src="static/bootstrap/vendor/datatables/media/js/jquery.dataTables.min.js"></script>
    <script src="static/bootstrap/vendor/datatables_plugins/integration/bootstrap/3/dataTables.bootstrap.min.js"></script>

    <!-- alert -->
    <script src="static/bootstrap/vendor/sweetalert/lib/sweet-alert.min.js"></script>
    
    <!-- App scripts -->
    <script src="static/bootstrap/scripts/homer.js"></script>
    <script src="static/bootstrap/scripts/charts.js"></script>

    <!-- 表单验证 -->
    <script src="static/js/plugins/form.validation/js/formValidation.js"></script>
    <script src="static/js/plugins/form.validation/js/framework/bootstrap.js"></script>
    
    <script src="static/js/common/hashMap.js"></script>
    <script src="static/js/common/predefinedComponent.js"></script>
    <!--jabava-->
    <script src="static/js/common/ajaxfileupload.js"></script>
    
<script type="text/javascript">
	var table;
	jQuery.prototype.serializeObject = function(){
        var obj = new Object();
        $.each(this.serializeArray(),function(index,param){
        	if(!(param.name in obj)){
            	obj[param.name] = param.value;
        	}
        });
        return obj;
    };
    
    $(function (){

        entUploader();
        
    	loadTable();
    	
        $('#upload').on('click', function() {
            var fileName = $('[data-toggle="upload:file"] :file').val();
            if(fileName==''){
                alert('请选择文件');
                return false;
            }
            photoExt=fileName.substr(fileName.lastIndexOf(".")).toLowerCase();//获得文件后缀名    
            if(photoExt!='.xlsx'){
                alert("请上传后缀名为xlsx的文件!");
                return false;
            }
            $.ajaxFileUpload({
            	url: 'common/uploadAndSaveFile',//用于文件上传的服务器端请求地址
                secureuri: false, 				//是否需要安全协议，一般设置为false
                fileElementId: ['uploadFile'],	//文件上传域的ID
                async:false,
                data: {fileId: $('#fileId').val(), fileClass: "200"},
                dataType:'text',
                type:"POST",
                success: function (result){  //服务器成功响应处理函数
                    entUploader();
                    $.ajax({
                        type : 'post',
                        url : "report/setReportTemplate",
                        data : {reportId: $('#reportIdForUpload').val(), fileId: result.fileId},
                        async : false,
                        dataType:'json',
                        success : function(message) {
                            if(message.success){
                                swal({
                                    title: "上传成功!",
                                    text: "",
                                    type: "success",
                                    confirmButtonText: "确定"
                                });
                                table.ajax.reload();
                                $('#shangchuan').modal('hide');
                            }else{
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
            }); 
        });
	});

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
    
    function fillReport(reportId, fileId){
    	$('#reportIdForUpload').val(reportId);
    	$('#fileId').val(fileId);
    }

    function loadTable(params){
    	if(table){
    		table.destroy();
    	}
	    table = $('#reportTable').DataTable({
	   		"dom": 
	               "<'row'<'col-sm-10'l><'col-sm-2'<'toolbar text-right'>>>" +
	               "<'row'<'col-sm-12 table-responsive'tr>>" +
	               "<'row'<'col-sm-5'i><'col-sm-7'p>>",
			
			//json
		    "processing": true,
	      	"serverSide": true,
	    	"bDestroy": true,
	    	"ordering": false,
			"ajax": {
				"url":"report/listReportPage",
				"data": params ? params : {},
				"type": "post"
			},
			"columns": [
				{ "data": "reportTypeName" },
				{ "data": "reportName" },
				{ "data": "scopeType", "render": function render( data, type, row, meta ){
						if(data == 1){
							return "全局";
						}else if(data == 2){
							return "公司";
						}else if(data == 3){
							return "平台";
						}else{
							return "";
						}
					}
				},
				{ "data": "scopeName"},
				{ "render": function render( data, type, row, meta ){
						var downloadButton = '';
						if(row.fileId){
							downloadButton = '<button class="btn btn-info btn-xs" type="button" onclick="downloadFile(' + row.fileId + ')">下载</button>&nbsp;';
						}
                        var strHtml = '';
                        <% if(RequestUtil.hasPower("reportmaintain_ur")){ %>
                        strHtml += '<button class="btn btn-info btn-xs" type="button" data-toggle="modal" data-target="#shangchuan" onclick="fillReport(' + row.reportId + ',' + row.fileId + ');">上传</button>&nbsp;';
                        <% } %>
                        <% if(RequestUtil.hasPower("reportmaintain_dl")){ %>
                        strHtml += downloadButton;
                        <% } %>
                        <% if(RequestUtil.hasPower("reportmaintain_mr")){ %>
                        strHtml += '<button class="btn btn-success btn-xs" type="button" onclick="mod(' + row.reportId + ');" >修改</button>&nbsp;';
                        <% } %>
                        <% if(RequestUtil.hasPower("reportmaintain_dr")){ %>
                        strHtml += '<button class="btn btn-danger btn-xs" type="button" onclick="del(' + row.reportId + ')">删除</button>';
                        <% } %>
						return strHtml;
					}
				}
			],
	   		"columnDefs": [
	   			{defaultContent: '', targets: '_all'}
			],
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
	   <% if(RequestUtil.hasPower("reportmaintain_ar")){ %>
	    $("div.toolbar").html('<button class="btn btn-info btn-sm" data-target="[data-modal=addReport]" data-toggle="modal"">新  增</button>');
        <% } %>
    }

    function getTableContent(){
    	var nTrs = table.fnGetNodes();
    	//fnGetNodes获取表格所有行，nTrs[i]表示第i行tr对象
    	for(var i = 0; i < nTrs.length; i++){
    		console.log('[获取数据]' + table.fnGetData(nTrs[i]));//fnGetData获取一行的数据
    	}
    }
    
    //表单验证
    var validatorOptions = {
        validator: {
            excluded: 'disabled',
            err: {
                container: 'tooltip'
            },
            icon: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                reportType: {
                    validators: {
                        notEmpty: {
                            message: '请填写必填项'
                        }
                    }
                },
                scopeId: {
                    validators: {
                        notEmpty: {
                            message: '请填写必填项'
                        }
                    }
                },
                reportName: {
                    validators: {
                        notEmpty:{
                            message: '请填写必填项'
                        }
                    }
                }
            }
        }, // end validator
    };

    $('[data-form="validator"]').formValidation(validatorOptions.validator)
    .on('success.form.fv', function(e){
        e.preventDefault();

        var getFormType = $(e.target).attr('data-form-type');
        switch(getFormType){
            case 'search':
                //do something
                break;
            case 'add':
                save();
                break;
        }
    });
    
    $('[name=scopeType]').on('change',function(e){
        var getScopeTypeValue = $(e.target).val();
        if(getScopeTypeValue == '' || getScopeTypeValue == 1 || getScopeTypeValue == 3){
        	if( $(this).attr('data-name')!='scopeType' ){
                $('#scopeId').prop('disabled',true).val('');
                $('#addForm')
                .formValidation('resetField','scopeId')
                .formValidation('enableFieldValidators','scopeId',false)
        	}else{
        		$('[data-name=scopeId]').prop('disabled', true).val('');
        	}
        }else{
        	if( $(this).attr('data-name')!='scopeType' ){
	            $('#scopeId').prop('disabled',false);
	            $('#addForm')
	            .formValidation('enableFieldValidators','scopeId',true)
	            .formValidation('revalidateField','scopeId')
        	}else{
        		$('[data-name=scopeId]').prop('disabled', false);
        	}
        }
    })


    function doSearch(){
    	loadTable($('#searchForm').serializeObject());
    }

    function del(reportId){
        swal({
               title: "确定要删除吗?",
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
            	   $.ajax({
                       url : "report/deleteReport",
                       data : {reportId: reportId},
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
                               
                               table.ajax.reload();
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
               } else {
                   swal("已取消", "未删除 :)", "error");
               }
        });
    };

    function save(){
        $.ajax({
            url : "report/editReport",
            data : $("#addForm").serialize(),
            dataType:'json',
            type : 'post',
            success : function(message) {
                if(message.success){
                    swal({
                        title: "新增成功!",
                        text: "",
                        type: "success",
                        confirmButtonText: "确定"
                    });
                    
                    mod(message.reportId);
                }else{
                    swal({
                        title: "新增失败!",
                        text: message.msg,
                        type: "error",
                        confirmButtonText: "确定"
                    });
                }
            }
        });
    };
    
    function mod(reportId){
    	$('#detailForm').attr("action","report/toAddReport");
    	$('#reportId').val(reportId);
    	$('#detailForm').submit();
    }
    
    function downloadFile(fileId){
    	window.open('common/downloadFile?fileId=' + fileId);
    }
</script>
</body>
</html>
