<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

        <!-- 放主要内容 -->
        <div class="content animate-panel">
            <div class="row">
                <div class="col-lg-12">
                    <div class="hpanel">
                        <div class="panel-heading">
                            <h4 class="text-center font-bold">
                                生成报表
                            </h4>
                        </div>

                        <form class="form-horizontal" id="reportForm">
                        	<input type="hidden" id="reportIdForGen" name="reportId" value="<%=request.getParameter("reportId")%>">
                            <div class="col-xs-12" id="container"></div>
                        </form>
                    </div>
                </div>
            </div>
        </div>      

    
   
    <script src="static/js/common/hashMap.js"></script>
    <script src="static/js/common/predefinedComponent.js"></script>
    <script type="text/javascript">
	var pdc = new PredefinedComponent();
	$(function(){
		$('#reportForm')[0].reset();
		
		loadParam();
	});
	
	function loadParam(){
		var reportId = $('#reportIdForGen').val();
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
                    	//console.log(inputType + '-' + this.englishName + '-' + this.chineseName);
                		pdc.append($('#container'),inputType,this.englishName,this.chineseName);
                	});

            		$('#container').append($('<div class="form-group text-center"><button class="btn btn-info" type="button" onclick="generateReport(' + reportId + ');">确　定</button>&nbsp;<button class="btn btn-info" type="button" data-dismiss="modal">关　闭</button></div>'));
            		
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