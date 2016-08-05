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
<title>修改工资模板</title>
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
                            <form class="form-horizontal" id="templateForm">
                        <div class="panel-heading">
                            <h4 class="text-center font-bold">
                                <a class="btn btn-success btn-sm pull-left btn-return m-r-md" type="button" href="salaryTemplate/toListSalaryTemplate">返回</a>
                                修改工资模板
                            </h4>
                        </div>
                   <div class="panel-body"> 

                            	<input type="hidden" id="ids" name="ids">
                            	<input type="hidden" id="salaryTemplateId" name="salaryTemplateId" value="${salaryTemplate.salaryTemplateId }">
                            <fieldset>
                                <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
                                    <div class="form-group">
                                        <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-4">模板名称：</label>
                                        <div class="col-xs-6 col-sm-6 col-md-6 col-lg-8 form-required">
                                            <input type="text" class="form-control" id="templateName" name="templateName" value="${salaryTemplate.templateName }">
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
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
                            </fieldset>

                               <div class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
                                    <div class="form-group">
                                        <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-2">描述：</label>
                                        <div class="col-xs-6 col-sm-6 col-md-6 col-lg-10">
                                            <textarea class="form-control" id="templateMemo" name="templateMemo">${salaryTemplate.templateMemo }</textarea>
                                        </div>
                                    </div>
                                </div>
                                <div>
                                    <table id="projectTable" class="table table-condensed table-hover table-bordered dataTable" width="100%">
                                        <thead>
                                        <tr>
                                            <th>
                                                <input type="checkbox" id="selAll" onclick="selectAll(this);"/>
                                            </th>
                                            <th>显示顺序</th>
                                            <th>项目名称</th>
                                            <th>变动表</th>
                                            <th>变动公式</th>
                                            <th>计税方法</th>
                                            <th>合并计算规则</th>
                                            <th>项目类别</th>
                                            <th>是否中间项</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                            <% if(RequestUtil.hasPower("salarytemplate_edit_st")){ %>
                            <div class="panel-footer text-right">
                               <button class="btn btn-info btn-sm btn-save-template" type="submit">保存</button>
                            </div>
                            <% } %>
                                </form>
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
		var salaryTemplateId = '${salaryTemplate.salaryTemplateId }';
		if(salaryTemplateId){
			$('#taxRateId').val('${salaryTemplate.taxRateId }');
		}

    	var table = $('#projectTable').DataTable({
    		"dom": 
                "<'row'<'col-sm-12 table-responsive'tr>>",

            "initComplete":function(settings, json){
                $.map(json.data, function(item, index) {
                    $('#templateForm').formValidation('addField', $('[data-id='+item.salaryItemId+']'));
                });
            },
			
			//json
		    "processing": true,
       		"serverSide": true,
     		"bDestroy": true,
     		"ordering": false,
			"ajax": {
				"url":"salaryTemplate/listSalaryItem",
				"type": "post",
				"data": {salaryTemplateId: ${salaryTemplate.salaryTemplateId }}
			},
			"columns": [
				{ "data": "salaryItemId", "render": function(data, type, row, meta){
                    var html = '<input type="checkbox" value="' + data + '" ' + (row.inTemplate ? 'checked' : '') + ' data-id="'+data+'" name="itemId[]" data-checked="checkAll">';
					return html;
				} },
				{ "data": "displayOrder" },
				{ "data": "salaryItemName" },
				{ "data": "salaryChangeDefName"},
				{ "data": "changeFormula" },
				{ "data": "taxRule", "render": function( data, type, row, meta ){
					if(data == 1){
						return "合并计税";
					}else if(data == 2){
						return "独立计税";
					}
				}},
				{ "data": "calculateRule", "render": function( data, type, row, meta ){
					if(data == 1){
						return "税前计入";
					}else if(data == 2){
						return "税后计入";
					}
				}},
				{ "data": "itemType", "render": function( data, type, row, meta ){
					if(data == 1){
						return "加项";
					}else if(data == 2){
						return "减项";
					}
				}},
				{ "data": "isTransition", "render": function( data, type, row, meta ){
					if(data){
						return "是";
					}else{
						return "否";
					}
				}}
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

//修改表单验证
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
                'templateName': {
                    validators: {
                        notEmpty: {
                            message: '请填写必填项'
                        }
                    }
                },
                'taxRateId': {
                    validators: {
                        notEmpty:{
                            message: '请填写必填项'
                        },
                        digits:{
                            message: '请输入有效数字'
                        }
                    }
                },
                'itemId[]': {
                    validators: {
                        choice:{
                            min: 1,
                            message: '请至少选择 %s 个工资项目'
                        }
                    }
                }
                
            }
        })
        .on('err.field.fv', function(e, data){
            if( data.field == 'itemId[]' ){
                $('[data-fv-icon-for="'+data.field+'"]').remove()
                swal({
                    title: "修改失败!",
                    text: data.result.message,
                    type: "error",
                    confirmButtonText: "确定"
                });
            }
        })
        .on('success.field.fv', function(e, data) {
            if( data.field == 'itemId[]' ) $('[data-fv-icon-for="'+data.field+'"]').remove()
        })
        .on('success.form.fv', function(e){
            e.preventDefault();

            var getFormData = $("#templateForm").serialize();

            $.ajax({
                url : "salaryTemplate/editSalaryTemplate",
                data : getFormData,
                dataType:'json',
                type : 'post',
                success : function(message) {
                    if(message.success){
                        swal({
                            title: "修改成功!",
                            text: "",
                            type: "success",
                            confirmButtonText: "确定"
                        }, function(){
                            window.location.href='salaryTemplate/toListSalaryTemplate';                            
                        });
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
        })

	});
// 全选 反选
    function selectAll(){
        var obj = document.getElementsByName("ids");
        if(document.getElementById("selAll").checked == false) {
	        for(var i=0; i<obj.length; i++) {
	          obj[i].checked=false;
	        }
        }else {
	        for(var i=0; i<obj.length; i++) {
	          obj[i].checked=true;
	        }
	     } 
	 }

    var selAll = document.getElementById("selAll");
    function selectAll( relatedTarget ){
      var obj = $('[data-checked="checkAll"]');
      $(relatedTarget).prop('checked') ? obj.prop('checked', true): obj.prop('checked', false)
}

</script>
</body>
</html>