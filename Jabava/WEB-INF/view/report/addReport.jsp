<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="com.jabava.utils.RequestUtil"%>
<%
String path = request.getContextPath();
String basePath = "//"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
    <base href="${pageContext.request.contextPath}/">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <!-- Page title -->
    <title>修改报表</title>
    <jsp:include flush="true" page="../common/styles.jsp"></jsp:include>
    <link rel="stylesheet" href="static/js/plugins/form.validation/css/formValidation.css">
    <link rel="stylesheet" href="static/css/user.css">
    <script>
      function downloadFile(path) {
          var data = JSON.stringify($('#searchOrderForm').serializeObject());
            window.open(path+data);
        }
</script>
</head>
<body >

    <!--splash screen-->
    <jsp:include flush="true" page="../common/splashscreen.div.jsp"></jsp:include>

    <!--引入头文件 开始--> 
    <jsp:include flush="true" page="../common/header.div.jsp"></jsp:include>
    <!--引入头文件 结束-->
     
    <!--引入菜单文件 开始--> 
    <jsp:include flush="true" page="../common/menu.div.jsp"></jsp:include>
    <!--引入菜单文件 结束--> 

<!-- Main Wrapper -->
<div id="wrapper">


<!-- 放主要内容 -->
<div class="content animate-panel">
    <div class="row">
        <div class="col-lg-12">
            <div class="hpanel">
                <div class="panel-heading">
                    <h4 class="text-center font-bold">
                    <a href="report/toListReport" class="btn btn-success btn-sm pull-left">　返回　</a>
                    <span>${requestScope.reportMonth}</span> <span>${requestScope.employeeName}</span> 修改报表
                    </h4>
                </div> 

                 <div class="panel-body"> 
                    <form id="editForm" class="form-horizontal" data-form="validator" data-form-type="edit">
                    	<input type="hidden" id="reportId" name="reportId" value="${report.reportId }">
                    	<input type="hidden" id="fileId" name="fileId" value="${report.fileId }">
                        <div class="col-xs-3 col-sm-3 col-md-3 col-lg-3">
                            <div class="form-group">
                                <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-5">报表类型：</label>
                                <div class="col-xs-6 col-sm-6 col-md-6 col-lg-7 form-required">
                                    <select class="form-control" id="reportType" name="reportType">
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
                                <div class="col-xs-6 col-sm-6 col-md-6 col-lg-7 form-required">
                                    <input type="text" class="form-control" id="reportName" name="reportName" value="${report.reportName }">
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-3 col-sm-3 col-md-3 col-lg-3">
                            <div class="form-group">
                                <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-5">范围类型：</label>
                                <div class="col-xs-6 col-sm-6 col-md-6 col-lg-7">
                                    <select class="form-control" id="scopeType" name="scopeType">
                                      <option value="1" selected="selected">全局</option>
                                      <option value="2">公司</option>
                                      <option value="3">平台</option>
                                    </select>
                                </div>   
                            </div>
                         </div>
                         <div class="col-xs-3 col-sm-3 col-md-3 col-lg-3">
                            <div class="form-group">
                                <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-5">应用范围：</label>
                                <div class="col-xs-6 col-sm-6 col-md-6 col-lg-7 form-required">
                                  <select class="form-control" id="scopeId" name="scopeId">
                                    <option value="">--------------------</option>
                                    <c:forEach items="${requestScope.companyList }" var="company">
                                    	<option value="${company.companyId }">${company.companyName }</option>
                                    </c:forEach>
                                  </select>
                                </div>
                            </div>
                        </div>
                        <% if(RequestUtil.hasPower("reportmaintain_mr")){ %>
                        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-center">
                            <button type="submit" class="btn btn-info btn-sm btn-add-rate">确  定</button>
                        </div>
                        <% } %>
                    </form>
                </div>  

                <ul class="nav nav-tabs m-t-md">
                    <li class="active"><a data-toggle="tab" href="#reportConfig" data-tab-rel="reportConfig">报表数据</a></li>
                    <li class=""><a data-toggle="tab" href="#reportParam" data-tab-rel="reportParam">报表参数</a></li>
                    <% if(RequestUtil.hasPower("reportmaintain_mr")){ %>
                    <li class="pull-right"><button type="button" class="btn btn-info btn-sm" data-ref="tab" data-motive="add" data-target="[data-modal=reportConfig]" data-toggle="modal">新增</button></li>
                    <% } %>
                </ul>
                <div class="tab-content">
                    <div id="reportConfig" class="tab-pane active">
                        <div class="panel-body" id="allcheck"> 
                            <table id="configList" class="table table-bordered table-hover rost_table" width="100%">
                            <thead>
                           <tr>
                                <th>数据集</th>
                                <th>父数据集</th>
                                <th>父数据集关联元素</th>
                                <th>子数据集封装方式</th>
                                <th>子数据集主键</th>
                                <th>是否作为分sheet数据集</th>
                                <th>是否作为分页数据集</th>
                                <th>每页显示行数</th>
                                <th>sql语句</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>                   
                            </tbody>
                            </table>
                        </div>
                    </div>
                    <div id="reportParam" class="tab-pane">
                        <div class="panel-body" id="allcheckTwo"> 
                            <table id="paramList" class="table table-bordered table-hover rost_table" width="100%">
                            <thead>
                           <tr>
                                <th>英文名</th>
                                <th>中文名</th>
                                <th>数据框类型</th>
                                <th>属性</th>
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
    </div>

    <!--新增弹框-->    
    <div class="modal fade hmodal-success form-row" data-modal="reportConfig" tabindex="-1" role="dialog" aria-hidden="true">
       <div class="modal-dialog">
           <div class="modal-content">
               <div class="color-line"></div>
               <div class="modal-header">
                <div class="row">
                <div class="panel-body">
                 <form id="editConfigForm" class="form-horizontal" autocomplete="off" data-form-type="edit" data-form="reportConfig">
                   <input type="hidden" id="idForConfig" name="id">
                   <input type="hidden" id="reportIdForConfig" name="reportId" value="${report.reportId }">
                   <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                       <div class="form-group has-feedback">
                           <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-4">数据集：</label>
                           <div class="col-xs-6 col-sm-6 col-md-6 col-lg-8 form-required">
                               <input type="text" id="reportDataset" name="reportDataset" class="form-control" >
                           </div>
                       </div>
                   </div>
                   <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                       <div class="form-group has-feedback">
                           <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-4">父数据集：</label>
                           <div class="col-xs-6 col-sm-6 col-md-6 col-lg-8 form-required">
                               <input type="text" id="parentReportDataset" name="parentReportDataset" class="form-control" >
                           </div>
                       </div>
                   </div>
                   <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                       <div class="form-group has-feedback">
                           <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-4">父数据集关联元素：</label>
                           <div class="col-xs-6 col-sm-6 col-md-6 col-lg-8 form-required">
                               <input type="text" id="parentAssociateElement" name="parentAssociateElement" class="form-control" >
                           </div>
                       </div>
                   </div>
                   <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                       <div class="form-group has-feedback">
                               <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-4">子数据集封装方式：</label>
                               <div class="col-xs-6 col-sm-6 col-md-6 col-lg-8 form-required">
                                   <select id="isDynamicColumn" name="isDynamicColumn" class="form-control" >
                                       <option value="0">List</option>
                                       <option value="1">Map</option>
                                   </select>
                               </div>   
                       </div>
                    </div>
                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                       <div class="form-group has-feedback">
                           <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-4">子数据集主键：</label>
                           <div class="col-xs-6 col-sm-6 col-md-6 col-lg-8 form-required">
                               <input type="text" id="dynamicColumnElement" name="dynamicColumnElement" class="form-control" >
                           </div>
                       </div>
                   </div>
                   <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                       <div class="form-group has-feedback">
                               <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-4">是否作为分sheet数据集：</label>
                               <div class="col-xs-6 col-sm-6 col-md-6 col-lg-8 form-required">
                                   <select id="isMultiSheetColumn" name="isMultiSheetColumn" class="form-control" >
                                           <option value="0">否</option>
                                           <option value="1">是</option>
                                   </select>
                               </div>   
                       </div>
                    </div>
                   <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                       <div class="form-group has-feedback">
                               <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-4">是否作为分页数据集：</label>
                               <div class="col-xs-6 col-sm-6 col-md-6 col-lg-8 form-required">
                                   <select id="isMultiPageColumn" name="isMultiPageColumn" class="form-control" >
                                           <option value="0">否</option>
                                           <option value="1">是</option>
                                   </select>
                               </div>   
                       </div>
                    </div>
                   <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                       <div class="form-group has-feedback">
                           <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-4">每页显示行数：</label>
                           <div class="col-xs-6 col-sm-6 col-md-6 col-lg-8 form-required">
                               <input type="text" id="maxResultPerSheet" name="maxResultPerSheet" class="form-control" >
                           </div>
                       </div>
                   </div>
                   <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                       <div class="form-group has-feedback">
                           <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-4">sql语句：</label>
                           <div class="col-xs-6 col-sm-6 col-md-6 col-lg-8 form-required">
                               <textarea id="sqlSentence" name="sqlSentence" class="form-control"></textarea>
                           </div>
                       </div>
                   </div>

                   <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-center">
                       <button type="submit" class="btn btn-info btn-sm btn-add-rate">确  定</button>
                   </div>
               </form>
                 </div>
               </div>   
           </div>
       </div>
   </div>
   </div>
   <!--新增弹框 end-->
   <!-- 报表参数 -->
    <!--新增弹框-->    
    <div class="modal fade hmodal-success form-row" data-modal="reportParam" tabindex="-1" role="dialog"  aria-hidden="true">
       <div class="modal-dialog">
           <div class="modal-content">
               <div class="color-line"></div>
               <div class="modal-header">
                <div class="row">
                <div class="panel-body">
                 <form id="editParamForm" class="form-horizontal" autocomplete="off" data-form-type="edit" data-form="reportParam">
                   <input type="hidden" id="idForParam" name="id">
                   <input type="hidden" id="reportIdForParam" name="reportId" value="${report.reportId }">
                   <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                       <div class="form-group has-feedback">
                           <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-4">英文名：</label>
                           <div class="col-xs-6 col-sm-6 col-md-6 col-lg-8 form-required">
                               <input type="text" id="englishName" name="englishName" id="" class="form-control" >
                           </div>
                       </div>
                   </div>
                   <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                       <div class="form-group has-feedback">
                           <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-4">中文名：</label>
                           <div class="col-xs-6 col-sm-6 col-md-6 col-lg-8 form-required">
                               <input type="text" id="chineseName" name="chineseName" id="" class="form-control" >
                           </div>
                       </div>
                   </div>
                   <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                       <div class="form-group has-feedback">
                               <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-4">数据框类型：</label>
                               <div class="col-xs-6 col-sm-6 col-md-6 col-lg-8 form-required">
                                   <select id="inputType" name="inputType" class="form-control" >
                                       <option value="">-----</option>
                                       <option value="001">Text-文本框</option>
                                       <option value="002">TextArea-文本域</option>
                                       <option value="003">Date-日期框</option>
                                       <option value="004">Month-月份</option>
                                       <option value="005">FirstLevelOrgSelect-一级部门选择框</option>
                                       <option value="006">SalaryUsageSelect-工资用途选择框</option>
                                       <option value="007">SocialSecurityAccountSelect-社保账户选择框</option>
                                       <option value="008">AccumulationFundAccountSelect-公积金账户选择框</option>
                                       <option value="009">SsAfSelect-社保公积金账户选择框</option>
                                   </select>
                               </div>   
                       </div>
                    </div>
                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                       <div class="form-group has-feedback">
                           <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-4">属性：</label>
                           <div class="col-xs-6 col-sm-6 col-md-6 col-lg-8">
                               <input type="text" id="elementProperty" name="elementProperty" class="form-control" >
                           </div>
                       </div>
                   </div>
                   <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-center">
                       <button type="submit" class="btn btn-info btn-sm btn-add-rate">确  定</button>
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

<!-- Footer-->
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
    <script src="static/bootstrap/vendor/xeditable/bootstrap3-editable/js/bootstrap-editable.min.js"></script>

    <script src="static/bootstrap/vendor/select2-3.5.2/select2.min.js"></script>
    <script src="static/bootstrap/vendor/bootstrap-touchspin/dist/jquery.bootstrap-touchspin.min.js"></script>
    <script src="static/bootstrap/vendor/bootstrap-datepicker-master/dist/js/bootstrap-datepicker.js"></script>
    <!-- for editable -->
    <script src="static/bootstrap/vendor/moment/moment.js"></script>
     
    <!-- for datatable -->
    <script src="static/bootstrap/vendor/datatables/media/js/jquery.dataTables.min.js"></script>
    <script src="static/bootstrap/vendor/datatables_plugins/integration/bootstrap/3/dataTables.bootstrap.min.js"></script>
    <!-- alert -->
    <script src="static/bootstrap/vendor/sweetalert/lib/sweet-alert.min.js"></script>

    <!-- App scripts -->
    <script src="static/bootstrap/scripts/homer.js"></script>
     <!-- 表单验证 -->
    <script src="static/js/plugins/form.validation/js/formValidation.js"></script>
    <script src="static/js/plugins/form.validation/js/framework/bootstrap.js"></script>
    
    <script src="static/js/common/hashMap.js"></script>
    <script src="static/js/common/predefinedComponent.js"></script>
<script>
	var tableConfig;
	var tableParam;
	jQuery.prototype.serializeObject = function(){
	    var obj = new Object();
	    $.each(this.serializeArray(),function(index,param){
	    	if(!(param.name in obj)){
	        	obj[param.name] = param.value;
	    	}
	    });
	    return obj;
	};

    $(function(){
    	$('#reportType').val(${report.reportType});
    	$('#scopeType').val(${report.scopeType});
    	$('#scopeId').val(${report.scopeId});
    	if('${report.scopeType}' == '1'){
    		$('#scopeId').prop('disabled', true);
    	}
		    
        loadTableFirst(); 
        loadTableSecond();  
	});
    
    function loadTableFirst(){
    	tableConfig = $('#configList').DataTable({
            "dom":
            "<'row'<'col-sm-12 table-responsive'tr>>",

            "processing": true,
            "serverSide": true,
            "ordering": false,
            "ajax":{
                "url": "report/listReportConfig",
                "async":false,
                "type":"POST",
                "data": {reportId: ${report.reportId }}
            },
            "columns": [
	            { "data": "reportDataset" },
	            { "data": "parentReportDataset" },
	            { "data": "parentAssociateElement" },
	            { "data": "isDynamicColumn", "render": function render( data, type, row, meta ){
	            		return data == 1 ? 'Map' : 'List';
	            	}
	            },
	            { "data": "dynamicColumnElement" },
	            { "data": "isMultiSheetColumn", "render": function render( data, type, row, meta ){
	            		return data == 1 ? '是' : '否';
	            	}
	            },
	            { "data": "isMultiPageColumn", "render": function render( data, type, row, meta ){
	            		return data == 1 ? '是' : '否';
	            	}
	            },
	            { "data": "maxResultPerSheet" },
	            { "data": "sqlSentence" },
	            { "render": function render( data, type, row, meta ){
                  var strHtml = '';
                  <% if(RequestUtil.hasPower("reportmaintain_mr")){ %>
                  strHtml += '<button class="btn btn-success btn-xs" type="button" data-motive="edit" data-target="[data-modal=reportConfig]" data-toggle="modal" data-record-id="' + row.id + '" >修改</button>&nbsp;';
                  strHtml += '<button class="btn btn-danger btn-xs del-button" type="button" onclick="del(\'config\',' + row.id + ')">删除</button>';
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
                "zeroRecords": "没有数据",
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
    };
    
    function loadTableSecond(){
    	tableParam = $('#paramList').DataTable({
            "dom":
            	"<'row'<'col-sm-12 table-responsive'tr>>",
            
            "processing": true,
            "serverSide": true,
            "ordering": false,
            "ajax":{
                "url": "report/listReportParam",
                "type":"POST",
                "async":false,
                "data": {reportId: ${report.reportId }}
            },
            "columns": [
                { "data": "englishName" },
                { "data": "chineseName" },
                { "data": "inputType", "render": function(data, type, row, meta){
                	return getInputTypeNameByCode(data);
                } },
                { "data": "elementProperty" },
                { "render": function render( data, type, row, meta ){
                    var strHtml = '';
                    <% if(RequestUtil.hasPower("reportmaintain_mr")){ %>
                    strHtml += '<button class="btn btn-success btn-xs" type="button" data-motive="edit" data-target="[data-modal=reportParam]" data-toggle="modal" data-record-id="' + row.id + '" >修改</button>&nbsp;';
                    strHtml += '<button class="btn btn-danger btn-xs del-button" type="button" onclick="del(\'param\',' + row.id + ')">删除</button>';
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
                "zeroRecords": "没有数据",
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
                    // enabled: false,
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
        },
        reportConfig: {
            err: {
                container: 'tooltip'
            },
            icon: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
            	reportDataset: {
                    validators: {
                        notEmpty: {
                            message: '请填写必填项'
                        }
                    }
                },
                parentReportDataset: {
                    enabled: false,
                    validators: {
                        notEmpty: {
                            message: '请填写必填项'
                        }
                    }
                },
                parentAssociateElement: {
                       enabled: false,
                    validators: {
                        notEmpty: {
                            message: '请填写必填项'
                        }
                    }
                },
                isDynamicColumn: {
                    validators: {
                        notEmpty: {
                            message: '请填写必填项'
                        }
                    }
                },
                dynamicColumnElement: {
                    enabled: false,
                    validators: {
                        notEmpty: {
                            message: '请填写必填项'
                        }
                    }
                }, 
                isMultiSheetColumn: {
                    validators: {
                        notEmpty: {
                            message: '请填写必填项'
                        }
                    }
                },
                isMultiPageColumn: {
                    validators: {
                        callback: {
                            message: 'Please specific the channel',
                            callback: function(value, validator, $field) {
                                return (value != 1)
                                        ? (value != '')
                                        : null;             
                            }
                        }
                    }
                }, 
                maxResultPerSheet: {
                    enabled:false,
                    validators: {
                        notEmpty: {
                            message: '请填写必填项'
                        }
                    }
                }, 
                sqlSentence: {
                    validators: {
                        notEmpty: {
                            message: '请填写必填项'
                        }
                    }
                } 
            }
        },
        reportParam: {
            err: {
                container: 'tooltip'
            },
            icon: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                englishName: {
                    validators: {
                        notEmpty: {
                            message: '请填写必填项'
                        }
                    }
                },
                chineseName: {
                    validators: {
                        notEmpty: {
                            message: '请填写必填项'
                        }
                    }
                },
                inputType: {
                    validators: {
                        notEmpty: {
                            message: '请填写必填项'
                        }
                    }
                }
            }
        }
    }

    $('#scopeType').on('change',function(e){
        var getScopeTypeValue = $(e.target).val();
        if(getScopeTypeValue == 1 || getScopeTypeValue == 3){
            $('#scopeId').prop('disabled',true).val('');
            $('#editForm')
            .formValidation('resetField','scopeId')
            .formValidation('enableFieldValidators','scopeId',false)
        }else{
           
            $('#scopeId').prop('disabled',false);
            $('#editForm')
            .formValidation('enableFieldValidators','scopeId',true)
            .formValidation('revalidateField','scopeId')
        }
    })

    $('[data-form="validator"]').formValidation(validatorOptions.validator).on('success.form.fv', function(e){
        e.preventDefault();

        //var getFormType = $(e.target).attr('data-form-type')
        saveReport();
    });

    $('[data-form="reportConfig"]').formValidation(validatorOptions.reportConfig)
    .on('success.field.fv', '[name=isMultiPageColumn]', function(e, data){
        var isEmpty=$(this).val()=='0';
        $('[data-form="reportConfig"]')
            .formValidation('enableFieldValidators','maxResultPerSheet',!isEmpty)
        if($(this).val()==1){
            $('[data-form="reportConfig"]')
            .formValidation('validateField','maxResultPerSheet')
        }
    })
    .on('success.form.fv', function(e){
        e.preventDefault();

        var getFormType = $(e.target).attr('data-form-type')
        save('config');
        $(e.target).data('formValidation').resetForm();

    });

    $('[data-form="reportParam"]').formValidation(validatorOptions.reportParam)
    .on('success.form.fv', function(e){
        e.preventDefault();

        var getFormType = $(e.target).attr('data-form-type')
        save('param');
        $(e.target).data('formValidation').resetForm();
    });

	// 新增、修改切换
    $('[data-toggle="tab"]').on('shown.bs.tab', function(e){
        var getTabRel = $(e.target).attr('data-tab-rel')
        var getRefBtn = $('[data-ref="tab"]')
        getRefBtn.attr('data-target', '[data-modal='+ getTabRel +']')
    });
	
	//set form-type : add || edit
	$('[data-modal]').on('show.bs.modal', function(e){
		var getRelatedTarget = $(e.relatedTarget);
		var getMotive = getRelatedTarget.attr('data-motive');
		var getValidateForm = $(this).find('[data-form]');
		var getFormName = getValidateForm.attr('data-form');
		
		getValidateForm.attr('data-form-type', getMotive);
		
		switch(getMotive){
			case 'add':
				// reset the form
				
				getValidateForm[0].reset();
				getValidateForm.find('[name="id"]').val('');
				break;
			case 'edit':
				var dataRecordId = getRelatedTarget.attr('data-record-id');
				$.ajax({
		            url : getFormName == 'reportConfig' ? "report/loadReportConfig" : "report/loadReportParam",
		            data : getFormName == 'reportConfig' ? {reportConfigId: dataRecordId} : {reportParamId: dataRecordId},
		            dataType:'json',
		            type : 'post',
		            success : function(message) {
		                if(message){

		                	$.each(message, function(key, value){
		                		//console.log([key, value])

		                		getValidateForm.find('[name="'+key+'"]').val(value)
		                	})
		                	
		                }
		            }
		        });
				break;
		}
	});
	
	// 删除数据
    function del(delType, id) {
        swal({
            title: "确定要删除该数据吗?",
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
                    url : delType == 'config' ? "report/deleteReportConfig" : "report/deleteReportParam",
                    data : delType == 'config' ? {reportConfigId: id} : {reportParamId: id},
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
                            if(delType == 'config'){
                            	tableConfig.ajax.reload();
                            }else{
                            	tableParam.ajax.reload();
                            }
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
                swal("已取消", "数据未删除 :)", "error");
            }
        });
    }

    function saveReport(){
        $.ajax({
            url : "report/editReport",
            data : $("#editForm").serializeObject(),
            dataType:'json',
            type : 'post',
            success : function(message) {
                if(message.success){
                    swal({
                        title: "保存成功!",
                        text: "",
                        type: "success",
                        confirmButtonText: "确定"
                    });
                    
                    window.location.href='report/toListReport';
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
    };

    function save(saveType){
        $.ajax({
            url : saveType == 'config' ? "report/saveReportConfig" : "report/saveReportParam",
            data : saveType == 'config' ? $("#editConfigForm").serializeObject() : $("#editParamForm").serializeObject(),
            dataType:'json',
            type : 'post',
            success : function(message) {
                if(message.success){
                    swal({
                        title: "保存成功!",
                        text: "",
                        type: "success",
                        confirmButtonText: "确定"
                    });
                    if(saveType == 'config'){
                    	tableConfig.ajax.reload();
                    }else{
                    	tableParam.ajax.reload();
                    }
                    var getModalType = '';
                    saveType == 'config' ? getModalType = 'reportConfig' : getModalType = 'reportParam';
                    $('[data-modal="'+getModalType+'"]').modal('hide');
                    
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
    };
</script>
</body>
</html>
