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
<title>员工工资管理</title>
<jsp:include flush="true" page="../common/styles.jsp"></jsp:include>
<!--for 临时改变-->
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
                                员工工资修改
                            </h4>
                            <div class="text-left">
                                <a href="salary/toListSalary" type="button" class="btn btn-success btn-sm">返　回</a>
                            </div>
                        </div>
                        
                        <div class="panel-body m-b-md">
                            <form class="form-horizontal" id="editForm">
                            	<input type="hidden" name="salaryId" value="${salary.salaryId }">
                            	<%--input type="hidden" name="personId" value="${salary.personId }"--%>
                            	<input type="hidden" name="jobNumber" value="${salary.jobNumber }">
                            	<input type="hidden" name="salaryTemplateId" value="${salary.salaryTemplateId }">
                            	<input type="hidden" name="usageFlag" value="${salary.usageFlag }">
                                <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
                                    <div class="form-group">
                                        <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-4">员工姓名：</label>
                                        <div class="col-xs-6 col-sm-6 col-md-6 col-lg-8">
                                            <input type="text" class="form-control" id="employeeName" name="employeeName" value="${salary.employeeName }" disabled="disabled">
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
                                    <div class="form-group">
                                        <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-4">工资模板：</label>
                                        <div class="col-xs-6 col-sm-6 col-md-6 col-lg-8">
                                            <select class="form-control" id="salaryTemplateId" disabled="disabled">
                                                <option value="">----------</option>
                                                <c:forEach var="template" items="${requestScope.templateList }" varStatus="status">
                                                	<option value="${template.salaryTemplateId }">${template.templateName }</option>
                                                </c:forEach>
                                            </select>
                                        </div>   
                                    </div>
                                </div>
                                <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
                                    <div class="form-group">
                                        <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-4">用途</label>
                                        <div class="col-xs-6 col-sm-6 col-md-6 col-lg-8">
                                            <select class="form-control" id="usageFlag" disabled="disabled">
                                                <option value="616">工资</option>
                                            </select>
                                        </div>   
                                    </div>
                                </div>
                                <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
                                    <div class="form-group">
                                        <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-4">状态：</label>
                                        <div class="col-xs-6 col-sm-6 col-md-6 col-lg-8">
                                            <select class="form-control" id="status" name="status">
                                                <option value="1">在发</option>
                                                <option value="0">停发</option>
                                            </select>
                                        </div>   
                                    </div>
                                </div>
                                <div class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
                                    <div class="form-group">
                                        <label class="control-label col-xs-3 col-sm-3 col-md-3 col-lg-2">备注：</label>
                                        <div class="col-xs-9 col-sm-9 col-md-9 col-lg-10">
                                            <textarea class="form-control" id="salaryMemo" name="salaryMemo">${salary.salaryMemo }</textarea>
                                        </div> 
                                    </div>
                                </div>
                                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-center">
                                    <button type="button" class="btn btn-info btn-sm" onclick="save();">保　存</button>
                                </div>
                            </form>
                        </div>
                        <div class="panel-body">
                            <table id="addSalaryTable" class="table table-striped table-condensed table-hover table-bordered dataTable" width="100%">
                                <thead>
                                <tr>
                                    <th>工资项目名称</th>
                                    <th>金额</th>
                                    <th>支付比例</th>
                                    <th>奖金基数</th>
                                    <th>奖金规则</th>
                                    <th>记录变更</th>
                                    <th>变更原因</th>
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

        <!--全部导入弹框-->    
        <div class="modal fade hmodal-success form-row" data-toggle="induce" tabindex="-1" role="dialog" aria-hidden="true">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="color-line"></div>
              <div class="modal-header">
                <div class="row">
                  <div class="col-sm-12">
                    <iframe class="no-borders" width="100%" src="time/daoru"></iframe>
                  </div>
                </div>   
              </div>
            </div>
          </div>
        </div>
        <!--全部导入弹框 end--> 

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

    <!-- for editable -->
    <script src="static/bootstrap/vendor/moment/moment.js"></script>
    <script src="static/bootstrap/vendor/xeditable/bootstrap3-editable/js/bootstrap-editable.min.js"></script>

    <!-- for datatable -->
    <script src="static/bootstrap/vendor/datatables/media/js/jquery.dataTables.min.js"></script>
    <script src="static/bootstrap/vendor/datatables_plugins/integration/bootstrap/3/dataTables.bootstrap.min.js"></script>

    <!-- alert -->
    <script src="static/bootstrap/vendor/sweetalert/lib/sweet-alert.min.js"></script>
    
    <!-- App scripts -->
    <script src="static/bootstrap/scripts/homer.js"></script>
    <script src="static/bootstrap/scripts/charts.js"></script>
    
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
    	$('#salaryTemplateId').val(${salary.salaryTemplateId });
    	$('#usageFlag').val(${salary.usageFlag });
    	$('#status').val(${salary.status });
    	
      	var table = $('#addSalaryTable').DataTable({
    		"dom": 
                "<'row'<'col-sm-12 table-responsive'tr>>",
			
			//json
		    "processing": true,
        	"serverSide": true,
      		"bDestroy": true,
      		"ordering": false,
			"ajax": {
			 	"url":"salary/listSalaryDetail",
			 	"type": "post",
			 	"data": {salaryId: ${salary.salaryId }}
			},
			"columns": [
			 	{ "data": "salaryItemName" },
			 	{ "data": "amount", "render": function render( data, type, row, meta ){
					return'<a id="amount" class="editable editable-click" data-title="Amount" data-placeholder="Required" data-placement="right" data-pk="' + row.salaryDetailId + '" data-type="text" href="#" style="display: inline;">' + data + '</a>';
				} },
			 	{ "data": "payRadio", "render": function render( data, type, row, meta ){
					return'<a id="pay_radio" class="editable editable-click" data-title="Pay Radio" data-placeholder="Required" data-placement="right" data-pk="' + row.salaryDetailId + '" data-type="text" href="#" style="display: inline;">' + data + '</a>';
				} },
			 	{ "data": "bonusBase", "render": function render( data, type, row, meta ){
					return'<a id="bonus_base" class="editable editable-click" data-title="Bonus Base" data-placeholder="Required" data-placement="right" data-pk="' + row.salaryDetailId + '" data-type="text" href="#" style="display: inline;">' + data + '</a>';
				} },
		     	{ "data": "bonusRule", "visible": false },
                { "visible": false },
                { "visible": false }
			],
     		"columnDefs": [
     			{defaultContent: '', targets: '_all'}
			],
			drawCallback: function(){
    			$('.editable').editable({
    				url : 'salary/updateSalaryDetail',
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
    });

    function save(){
        //校验
        
        $.ajax({
            url : "salary/editSalary",
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
                    
                    window.location.href='salary/toListSalary';
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

    $.fn.editable.defaults.mode = 'inline';
        $('.editable').editable({
            validate: function(value) {
                if($.trim(value) == '') return '不能为空值';
            }
        });
</script>
</body>
</html>