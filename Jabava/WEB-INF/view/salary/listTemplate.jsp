<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<base href="${pageContext.request.contextPath}/">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge">

<!-- Page title -->
<title>工资模板管理</title>
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
                                工资模板管理
                            </h4>
                        </div>
                    <!--新增弹框-->    
                 <div class="modal fade hmodal-success form-row" data-modal="addTemplate" tabindex="-1" role="dialog"  aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="color-line"></div>
                            <div class="modal-header">
                             <div class="row">
                             <div class="panel-body">
                              <form class="form-horizontal" id="templateForm">
                                    <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
                                        <div class="form-group">
                                            <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-4">模板名称：</label>
                                            <div class="col-xs-6 col-sm-6 col-md-6 col-lg-8 form-required">
                                                <input type="text" class="form-control" id="templateName" name="templateName">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
                                        <div class="form-group">
                                            <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-4">适用税率：</label>
                                            <div class="col-xs-6 col-sm-6 col-md-6 col-lg-8 form-required">
                                                <select class="form-control" id="taxRateId" name="taxRateId">
                                                    <option value="">全部</option>
                                                    <c:forEach var="taxRate" items="${requestScope.taxRateList }" varStatus="status">
                                                    	<option value="${taxRate.taxRateId }">${taxRate.taxRateName }</option>
                                                    </c:forEach>
                                                </select>
                                            </div>   
                                        </div>
                                    </div>
                                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                        <div class="form-group">
                                            <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-2">备注：</label>
                                            <div class="col-xs-6 col-sm-6 col-md-6 col-lg-10">
                                                <textarea class="form-control" id="templateMemo" name="templateMemo"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-center">
                                        <button type="submit" class="btn btn-info btn-sm btn-save-template">确	定</button>
                                    </div>
                                </form>
                              </div>
                            </div>   
                        </div>
                    </div>
                </div>
                </div>
                <!--新增弹框 end-->
                      <div class="panel-body"> 
                            <table id="projectTable" class="table table-condensed table-hover table-bordered dataTable" width="100%">
                                <thead>
                                <tr>
                                    <th>模板ID</th>
                                    <th>模板名称</th>
                                    <th>适用税率</th>
                                    <th>备注</th>
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
    		<input type="hidden" id="salaryTemplateId" name="salaryTemplateId">
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
    
    <script type="text/javascript">
	jQuery.prototype.serializeObject=function(){  
        var obj=new Object();  
        $.each(this.serializeArray(),function(index,param){  
          if(!(param.name in obj)){  
            obj[param.name]=param.value;  
          }  
        });  
        return obj;  
    };
    
    $(function (){
    	
    	
        $('[data-modal=addTemplate]').on('show.bs.modal', function(event){
            //$('#templateForm input,#templateForm select,#templateForm textarea').not('#taxRateIdForLevel').val('');
        	$('#templateForm input,#templateForm select,#templateForm textarea').val('');
        })
    	
      	var table = $('#projectTable').DataTable({
    		"dom": 
                "<'row'<'col-sm-6'l><'col-sm-4'f><'col-sm-2'<'toolbar text-right'>>>" +
                "<'row'<'col-sm-12 table-responsive'tr>>" +
                "<'row'<'col-sm-5'i><'col-sm-7'p>>",
			
			//json
		    "processing": true,
       		"serverSide": true,
     		"bDestroy": true,
     		"ordering": false,
			"ajax": {
				"url":"salaryTemplate/listSalaryTemplatePage",
				"type": "post"
			},
			"columns": [
				{ "data": "salaryTempateId", "visible":false },
				{ "data": "templateName" },
				{ "data": "taxRateName" },
				{ "data": "templateMemo"},
				{ "render": function render( data, type, row, meta ){
						return '<button class="btn btn-success btn-xs" type="button" onclick="mod(' + row.salaryTemplateId + ');">修改</button>&nbsp;' +
                            '<button class="btn btn-danger btn-xs del-button" type="button" onclick="del(' + row.salaryTemplateId + ');">删除</button>&nbsp;' +
                            '<button class="btn btn-info btn-xs" type="button" onclick="download(' + row.salaryTemplateId + ');">下载</button>';
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

        $("div.toolbar").html('<button class="btn btn-info btn-sm" data-target="[data-modal=addTemplate]" data-toggle="modal" onclick="add();">新  增</button>');
    });


        //表单验证
        $('#templateForm').formValidation({
            err: {
                container: 'tooltip'
            },
            icon: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                templateName: {
                    validators: {
                        notEmpty: {
                            message: '请填写必填项'
                        }
                    }
                },
                taxRateId: {
                    validators: {
                        notEmpty:{
                            message: '请填写必填项'
                        },
                        digits:{
                            message: '请输入有效数字'
                        }
                    }
                }
                
            }
        })
        .on('success.form.fv', function(e){
            e.preventDefault();
            save();
        });



    
    function mod(id){
    	$('#salaryTemplateId').val(id);
		$('#detailForm').attr('action','salaryTemplate/toAddSalaryTemplate');
		$('#detailForm').submit();
    }

    function del(id){
    	swal(
    		{
	            title: "确定要删除此模板吗?",
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
		            deleteById(id);
	            }else{
	            	swal("已取消", "模板未删除", "error");
	            }
	        }
        );
    }
    
    function deleteById(id){
    	$.ajax({
			url : "salaryTemplate/deleteSalaryTemplate",
			data : {id: id},
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
					window.location.href="salaryTemplate/toListSalaryTemplate";
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
    
    function download(id){
    	$('#salaryTemplateId').val(id);
		$('#detailForm').attr('action','salaryTemplate/exportSalaryTemplate');
		$('#detailForm').submit();
    };

   function save(){
            //校验
            
            $.ajax({
                url : "salaryTemplate/editSalaryTemplate",
                data : $("#templateForm").serializeObject(),
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
                        
                        mod(message.salaryTemplateId);
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
</script>
</body>
</html>