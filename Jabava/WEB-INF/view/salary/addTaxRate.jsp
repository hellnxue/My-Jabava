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
<title>修改税率</title>
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
                                <a class="btn btn-success btn-sm pull-left btn-return m-r-md" type="button" href="taxRate/toListTaxRate">返回</a>
                                修改税率
                            </h4>
                        </div>
                        <div class="panel-body"> 
                            <form class="form-horizontal" id="editForm">
                            	<input type="hidden" id="taxRateId" name="taxRateId" value="${taxRate.taxRateId }">
                                <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
                                    <div class="form-group">
                                        <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-4">税率名称：</label>
                                        <div class="col-xs-6 col-sm-6 col-md-6 col-lg-8 form-required">
                                            <input type="text" class="form-control" id="taxRateName" name="taxRateName" value="${taxRate.taxRateName }">
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
                                    <div class="form-group">
                                        <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-4">免征额：</label>
                                        <div class="col-xs-6 col-sm-6 col-md-6 col-lg-8 form-required">
                                            <input type="text" class="form-control" id="threshold" name="threshold" value="${taxRate.threshold }">
                                        </div>
                                    </div>
                                </div>
                               <div class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
                                    <div class="form-group">
                                        <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-2">描述：</label>
                                        <div class="col-xs-6 col-sm-6 col-md-6 col-lg-10">
                                            <textarea class="form-control" id="taxRateMemo" name="taxRateMemo">${taxRate.taxRateMemo }</textarea>
                                        </div>
                                    </div>
                                </div>
                                 <div class="col-xs-8 col-sm-8 col-md-8 col-lg-8 text-center">
                                    <button class="btn btn-info btn-sm btn-edit-rate" type="submit">保存</button>
                                </div>
                            </form>
                        </div>  
                        <!-- table -->
                        <div class="panel-body m-t-md"> 
                            <table id="projectTable" class="table table-condensed table-hover table-bordered dataTable" width="100%">
                                <thead>
                                <tr>
                                    <th>税率ID</th>
                                    <th>税级距下线</th>
                                    <th>税级距上线</th>
                                    <th>税率</th>
                                    <th>速算扣除数</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                
                                </tbody>
                            </table>
                        </div>           
                 <!--新增弹框-->    
                 <div class="modal fade hmodal-success form-row" data-modal="addTaxRate" tabindex="-1" role="dialog"  aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="color-line"></div>
                            <div class="modal-header">
                             <div class="row">
                             <div class="panel-body">
                              <form class="form-horizontal" id="levelForm">
                                <input type="hidden" id="taxRateIdForLevel" name="taxRateId" value="${taxRate.taxRateId }">
                                <input type="hidden" id="taxLevelId" name="taxLevelId">
                                <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
                                    <div class="form-group">
                                        <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-5">税级距下线：</label>
                                        <div class="col-xs-6 col-sm-6 col-md-6 col-lg-7">
                                            <input type="text" class="form-control" id="levelLower" name="levelLower">
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
                                    <div class="form-group">
                                        <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-5">税级距上线：</label>
                                        <div class="col-xs-6 col-sm-6 col-md-6 col-lg-7">
                                            <input type="text" class="form-control" id="levelLimit" name="levelLimit">
                                        </div>
                                    </div>
                                </div>
                               <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
                                    <div class="form-group">
                                        <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-5">税率：</label>
                                        <div class="col-xs-6 col-sm-6 col-md-6 col-lg-7">
                                            <input type="text" class="form-control" id="taxRate" name="taxRate">
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
                                    <div class="form-group">
                                        <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-5">运算扣除数：</label>
                                        <div class="col-xs-6 col-sm-6 col-md-6 col-lg-7">
                                            <input type="text" class="form-control" id="fastCalculationAmount" name="fastCalculationAmount">
                                        </div>
                                    </div>
                                </div>
                                 <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-center">
                                    <button type="submit" class="btn btn-info">提  交</button>
                                    <button type="button" class="btn btn-danger" data-dismiss="modal" aria-label="Close">取  消</button>
                                </div>
                            </form>
                              </div>
                            </div>   
                        </div>
                    </div>
                </div>
                </div>
                <!--新增弹框 end-->
                    </div>
                </div>
            </div>
        </div>      
        <!--主要内容结束-->
        <!-- 放页脚  开始-->
        <jsp:include flush="true" page="../common/foot.div.jsp"></jsp:include>
        <!-- 放页脚  结束-->
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
    	var table;
    	jQuery.prototype.serializeObject=function(){  
            var obj=new Object();  
            $.each(this.serializeArray(),function(index,param){  
              if(!(param.name in obj)){  
                obj[param.name]=param.value;  
              }  
            });  
            return obj;  
        };
        
	$(function(){

 		
 		
 		$('.btn-level-submit').click(function(){
			//校验
 			

 		});
 		
 		loadTable();

         //修改表单验证
        $('#editForm').formValidation({
            err: {
                container: 'tooltip'
            },
            icon: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                taxRateName: {
                    validators: {
                        notEmpty: {
                            message: '请填写必填项'
                        }
                    }
                },
                threshold: {
                    validators: {
                        notEmpty:{
                            message: '请填写必填项'
                        },
                        numeric:{
                            message: '请输入有效数字'
                        }
                    }
                }
                
            }
        })
        .on('success.form.fv', function(e){
            e.preventDefault();
            update();
        });
	});

    $('[data-modal=addTaxRate]').on('show.bs.modal', function(event){
        $('#levelForm input').not('#taxRateIdForLevel').val('');


    //新增、修改表单验证
        $('#levelForm').formValidation({
            err: {
                container: 'tooltip'
            },
            icon: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                levelLower: {
                    validators: {
                        notEmpty: {
                            message: '值不能为空'
                        }
                    }
                },
                levelLimit: {
                    validators: {
                        notEmpty: {
                            message: '值不能为空'
                        }
                    }
                },
                taxRate: {
                    validators: {
                        notEmpty: {
                            message: '值不能为空'
                        }
                    }
                },
                fastCalculationAmount: {
                    validators: {
                        notEmpty:{
                            message: '值不能为空'
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
            $.ajax({
                url : "taxRate/saveTaxLevel",
                data : $("#levelForm").serializeObject(),
                dataType:'json',
                type : 'post',
                success : function(message) {
                    if(message.success){
                        //swal({
                        //    title: "保存成功!",
                        //    text: "",
                        //    type: "success",
                        //  confirmButtonText: "确定"
                        //});
                        $('[data-modal="addTaxRate"]').modal('hide');
                        
                        loadTable();
                    }else{
                        swal({
                            title: "保存失败!",
                            text: message.msg,
                            type: "error",
                            confirmButtonText: "确定"
                        });
                    }
                }
            });
        });



    })
    .on('hidden.bs.modal', function(){
        $('#levelForm').formValidation('destroy')
    })


	function loadTable(){
		if(table){
			table.destroy();
		}
 		
 		table = $('#projectTable').DataTable({
    		"dom": 
    			"<'row'<'col-sm-6'l><'col-sm-4'f><'col-sm-2'<'toolbar text-right'>>>" +
                "<'row'<'col-sm-12 table-responsive'tr>>",
			
			//json
		    "processing": true,
       		"serverSide": true,
     		"bDestroy": true,
     		"ordering": false,
     		"bLengthChange": false, //改变每页显示数据数量  
     		"bFilter": false, 		//过滤功能
			"ajax": {
				"url":"taxRate/listTaxLevel",
				"data": {taxRateId: ${taxRate.taxRateId} },
				"type": "post"
			},
			"columns": [
				{ "data": "taxRateId", "visible": false },
				{ "data": "levelLower" },
				{ "data": "levelLimit"},
				{ "data": "taxRate"},
				{ "data": "fastCalculationAmount"},
				{ "render": function render( data, type, row, meta ){
						return '<button class="btn btn-success btn-xs" data-toggle="modal" data-target="[data-modal=addTaxRate]" type="button" onclick="mod(' + row.taxLevelId + ');">修改</button>&nbsp;' +
                        		'<button class="btn btn-danger btn-xs del-button" type="button" onclick="del(' + row.taxLevelId + ');">删除</button>';
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

        $("div.toolbar").html('<button class="btn btn-info btn-sm" data-target="[data-modal=addTaxRate]" data-toggle="modal">新  增</button>');
    };    
	function mod(id){
		$.ajax({
			url : "taxRate/loadTaxLevel",
			data : {id: id},
			dataType:'json',
			type : 'post',
			success : function(data) {
				if(data){
					$('#taxRateId').val(data.taxRateId);
					$('#taxLevelId').val(data.taxLevelId);
					$('#levelLower').val(data.levelLower);
					$('#levelLimit').val(data.levelLimit);
					$('#taxRate').val(data.taxRate);
					$('#fastCalculationAmount').val(data.fastCalculationAmount);
				}else{
					swal({
	                    title: "加载失败!",
	                    text: message.msg,
	                    type: "error",
	    				confirmButtonText: "确定"
	                });
				}
			}
		});
	}
	
	function del(id){
		swal(
    		{
	            title: "确定要删除此税率信息吗?",
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
	            	swal("已取消", "税率信息未删除", "error");
	            }
	        }
        );
    }
	    
    function deleteById(id){
    	$.ajax({
			url : "taxRate/deleteTaxLevel",
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
					
					loadTable();
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
    };

    function update(){
            //校验
            
            $.ajax({
                url : "taxRate/editTaxRate",
                data : $("#editForm").serializeObject(),
                dataType:'json',
                type : 'post',
                success : function(message) {
                    if(message.success){
                        swal({
                            title: "修改成功!",
                            text: "",
                            type: "success",
                            confirmButtonText: "确定"
                        });
                        
                        window.location.href='taxRate/toListTaxRate';
                    }else{
                        swal({
                            title: "修改失败!",
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