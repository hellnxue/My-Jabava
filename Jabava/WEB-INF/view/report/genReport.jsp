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
<title>生成报表</title>
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
                                报表类型
                            </h4>
                        </div>
                        <div class="panel-body"> 
                            <form class="form-horizontal" id="reportForm">
                                <div class="col-xs-12" id="container">
                                    <div class="form-group">
                                        <label class="control-label col-xs-2">报表类型：</label>
                                        <div class="col-xs-10">
                                            <select class="form-control" id="reportType" name="reportType" onchange="loadReport();">
                                                <option value="">请选择</option>
                                                <c:forEach var="reportType" items="${requestScope.reportTypeList }" varStatus="status">
                                                <option value="${reportType.commonDataId }">${reportType.commonDataName }</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group" id="reportIdDiv">
                                        <label class="control-label col-xs-2">报表名：</label>
                                        <div class="col-xs-10">
                                            <select class="form-control" id="reportId" name="reportId" onchange="loadParam();">
                                                <option value="">请选择</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                            </form>
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
    
    
   <div class="modal fade" data-modal="">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">Modal title</h4>
      </div>
      <div class="modal-body">
        <p>One fine body&hellip;</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save changes</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
    
    
    
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

    <script src="static/bootstrap/vendor/bootstrap-datepicker-master/dist/js/bootstrap-datepicker.js"></script>
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
    <script type="text/javascript">
	var pdc = new PredefinedComponent();
	$(function(){
		$('#reportForm')[0].reset();

	});
	
	function loadReport(){
		//清除报表
		$('#reportId').empty();
		
		//清除原有的组件
		removeComponent();
		
		//加载报表
		var reportType = $('#reportType').val();
		if(reportType){
			$.ajax({
                url : "report/listReportByType",
                data : {reportType: reportType},
                dataType:'json',
                type : 'post',
                success : function(message) {
                    if(message){
                        $('#reportId').append('<option value="">请选择</option>');
                        $.each(message, function(){
                        	$('#reportId').append('<option value="' + this.reportId + '">' + this.reportName + '</option>');
                        });
                    }
                }
            });
		}
	}
	
	function loadParam(){
		
		//清除原有的组件
		removeComponent();
		
		var reportId = $('#reportId').val();
		if(!reportId){
			return;
		}
		
		$.ajax({
            url : "report/listReportParam",
            data : {reportId: reportId},
            dataType:'json',
            type : 'post',
            success : function(message) {
                if(message && message.data){
                	$.each(message.data, function(){
                    	var inputType = getInputTypeByCode(this.inputType);
                		pdc.append($('#container'),inputType,this.englishName,this.chineseName);
                	});
                    var strHtml = '';
                    <% if(RequestUtil.hasPower("reportgenerate_cg")){ %>
                    strHtml += '<div class="form-group text-center"><button class="btn btn-info" type="button" onclick="generateReport(' + reportId + ');">确　定</button></div>';
                    <% } %>
            		$('#container').append( strHtml );
            		
            		//日历
                	$('.input-group.date.common-date').datepicker({
                        format: "yyyy-mm-dd",
                        autoclose: true
                    });
            		
                	$('.input-group.date.only-month').datepicker({
                        format: "yyyymm",
                        weekStart: 1, 
                        autoclose: true, 
                        startView: 1, maxViewMode: 1,minViewMode:1,
                        forceParse: false, 
                        language: 'zh-CN'
                    });
                    //日历结束
                }
            }
		});
	}
    
	function removeComponent(){
		$('#reportIdDiv').nextAll().remove();
	}
	
	function generateReport(reportId){
		$.ajax({
            url : "report/generateReport",
            data : $('#reportForm').serialize(),
            dataType:'json',
            type : 'post',
            success : function(message) {
				if(message.success){
            		window.open('common/downloadFile?fileId=' + message.fileId);
				}else{
					swal({
                        title: "生成失败!",
                        text: message.msg,
                        type: "error",
                        confirmButtonText: "确定"
                    });
				}
            }
		});
	}
</script>
</body>
</html>