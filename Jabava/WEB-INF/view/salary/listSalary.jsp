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
<title>员工工资管理</title>
<jsp:include flush="true" page="../common/styles.jsp"></jsp:include>
<link rel="stylesheet" href="static/js/plugins/form.validation/css/formValidation.css">
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
        <div class="normalheader transition animated fadeIn small-header">
            <div class="hpanel">
                <div class="panel-body">
                    <div id="hbreadcrumb" class="m-t-xs m-b-xs">
                        <h2 class="font-normal m-b-xs text-center">
                            员工薪酬档案管理
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
                        <div class="panel-heading">
                            <div class="text-right">
                                <% if(RequestUtil.hasPower("salary_ba")){ %>
                                <span class="text-defaults">请到薪酬方案管理-薪酬模板设置下载合适模板</span>
                                <button class="btn btn-success btn-xs" type="button" data-target="[data-modal=induce]" data-toggle="modal">
                                    <span class="bold">批量调薪</span>
                                </button>
                                <% } %>
                            </div>
                            
                        </div>

                        <!-- 高级搜索开始 -->
                        <div class="collapse out" data-toggle="search" aria-expanded="false" >
                          <div class="well well-lg" >
                            <div class="row">
                                <form class="form-horizontal" id="searchForm">
                                    <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
                                        <div class="form-group">
                                            <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-4">员工号：</label>
                                            <div class="col-xs-6 col-sm-6 col-md-6 col-lg-8">
                                                <input type="text" class="form-control" id="jobNumberForSearch" name="jobNumber">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
                                        <div class="form-group">
                                            <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-4">员工姓名：</label>
                                            <div class="col-xs-6 col-sm-6 col-md-6 col-lg-8">
                                                <input type="text" class="form-control" id="employeeName" name="employeeName">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
                                        <div class="form-group">
                                            <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-4">发放状态：</label>
                                            <div class="col-xs-6 col-sm-6 col-md-6 col-lg-8">
                                                <select class="form-control" id="status" name="status">
                                                    <option value="">全部</option>
                                                    <option value="1">发放</option>
                                                    <option value="0">停发</option>
                                                </select>
                                            </div>   
                                        </div>
                                    </div>
                                    <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
                                        <div class="form-group">
                                            <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-4">部门：</label>
                                            <div class="col-xs-6 col-sm-6 col-md-6 col-lg-8">
	                                            <select class="form-control" id="orgId" name="orgId">
	                                                <option value="">----------</option>
	                                                <c:forEach var="org" items="${requestScope.orgList }" varStatus="status">
	                                                	<option value="${org.organizationId }">${org.organizationName }</option>
	                                                </c:forEach>
	                                            </select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-center">
                                        <a class="btn btn-info btn-sm" onclick="doSearch();">查　询</a>
                                        <button class="btn btn-default m-l" type="reset">重置</button>
                                    </div>
                                </form>
                            </div>
                          </div>
                        </div>
                        <!-- 高级搜索结束 -->
                        <div class="panel-body">
                            <table id="salaryTable" class="table table-striped table-condensed table-hover table-bordered dataTable" width="100%">
                                <thead>
                                <tr>
                                    <th>员工号</th>
                                    <th>员工姓名</th>
                                    <th>工资模板</th>
                                    <th>用途</th>
                                    <th>发放状态</th>
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

         <select class="js-example-data-ajax form-control select2-hidden-accessible" tabindex="-1" aria-hidden="true">
          <option value="3620194" selected="selected">select2/select2</option>
        </select>
              
        <!--主要内容结束-->

        <!-- 放页脚  开始-->
        <jsp:include flush="true" page="../common/foot.div.jsp"></jsp:include>
        <!-- 放页脚  结束-->

        <!-- 新增弹框 -->
        <div class="modal fade hmodal-success form-row" data-modal="addSalary" tabindex="-1">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="color-line"></div>
                    <div class="modal-header">
                        <div class="row">
                            <form class="form-horizontal" id="addForm" action="">
                                <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
                                    <div class="form-group">
                                        <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-4">员工：</label>
                                            <input type="hidden" name="certId">
                                            <input type="hidden" name="jobNumber">                                        <div class="col-xs-6 col-sm-6 col-md-6 col-lg-8 form-required">
                                            <input type="text" class="form-control" name="employeeName">
                                            <span class="help-block" data-help-for="jobNumber" id="employeeNameSpan"></span>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
                                    <div class="form-group">
                                        <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-4">工资模板：</label>
                                        <div class="col-xs-6 col-sm-6 col-md-6 col-lg-8 form-required">
                                            <select class="form-control" id="salaryTemplateId" name="salaryTemplateId">
                                                <option value="">----------</option>
                                                <c:forEach var="template" items="${requestScope.templateList }" varStatus="status">
                                                	<option value="${template.salaryTemplateId }">${template.templateName }</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                    <div class="form-group">
                                        <label class="control-label col-xs-3 col-sm-3 col-md-3 col-lg-2">用途：</label>
                                        <div class="col-xs-3 col-sm-3 col-md-3 col-lg-4 form-required">
                                            <select class="form-control" id="usageFlagForAdd" name="usageFlag">
                                                <option value="">请选择</option>
                                                <c:forEach var="salaryType" items="${requestScope.salaryTypeList }" varStatus="status">
                                                	<option value="${salaryType.baseDataId }">${salaryType.baseDataName }</option>
                                                </c:forEach>
                                            </select>
                                        </div>   
                                    </div>
                                </div>
                                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                    <div class="form-group">
                                        <label class="control-label col-xs-3 col-sm-3 col-md-3 col-lg-2">备注：</label>
                                        <div class="col-xs-9 col-sm-9 col-md-9 col-lg-10">
                                            <textarea class="form-control" id="salaryMemo" name="salaryMemo"></textarea>
                                        </div>   
                                    </div>
                                </div>
                                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-center">
                                    <button type="submit" class="btn btn-info btn-sm">确　定</button>
                                </div>
                            </form>
                        </div>   
                    </div>
                </div>
            </div>
        </div>
        <!-- 新增弹框结束 -->

        <!--全部导入弹框-->    
        <div class="modal fade hmodal-success form-row" data-modal="induce" tabindex="-1" role="dialog" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="color-line"></div>
                    <div class="modal-header">
                        <div class="row">
                            <form class="form-horizontal" id="uploadForm">
                                <div class="col-lg-12">
                                    <div class="form-group">
                                    <label class="control-label col-xs-3 col-sm-3 col-md-3 col-lg-2">用途：</label>
                                        <div class="col-xs-9 col-sm-9 col-md-9 col-lg-10">
                                            <select class="form-control" id="usageFlagForImport" name="usageFlag">
                                                <option value="">请选择</option>
                                                <c:forEach var="salaryType" items="${requestScope.salaryTypeList }" varStatus="status">
                                                	<option value="${salaryType.baseDataId }">${salaryType.baseDataName }</option>
                                                </c:forEach>
                                            </select>
                                        </div>   
                                    </div>
                                </div>
                                <div class="col-lg-12">
                                    <div class="form-group">
                                        <label class="control-label col-xs-3 col-sm-3 col-md-3 col-lg-2">导入：</label>
                                        <div class="col-xs-9 col-sm-9 col-md-9 col-lg-10">
                                            <div class="input-group" data-toggle="upload:file">
                                                <input type="hidden" id="filePath" name="filePath">
                                                <input type="text" class="form-control" readonly="readonly">
                                                <div class="input-group-btn">
                                                    <span class="btn btn-default">浏览...
                                                    <input type="file" class="sr-only" id='uploadFile' name='uploadFile' accept=".xlsx" ></span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-center">
                                    <button id="upload" type="button" class="btn btn-info btn-sm">确　定</button>
                                </div>
                            </form>
                        </div>   
                    </div>
                </div>
            </div>
        </div>
        <!--全部导入弹框 end-->

        <!-- 批量新增modal -->
        <div class="modal fade hmodal-success form-row" data-modal="batchAdd" tabindex="-1">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="color-line"></div>
                    <div class="modal-header">
                        <div class="row">
                            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                <form class="form-horizontal" data-id="batchAdd" action="">
                                    <input type="hidden" name="personIds">
                                    <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
                                        <div class="form-group">
                                            <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-4">工资模板：</label>
                                            <div class="col-xs-6 col-sm-6 col-md-6 col-lg-8 form-required">
                                                <select class="form-control" id="batchSalaryTemplateId" name="salaryTemplateId">
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
                                            <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-4">用途：</label>
                                            <div class="col-xs-6 col-sm-6 col-md-6 col-lg-8 form-required">
                                                <select class="form-control" id="batchUsageFlag" name="usageFlag">
	                                                <option value="">请选择</option>
	                                                <c:forEach var="salaryType" items="${requestScope.salaryTypeList }" varStatus="status">
	                                                	<option value="${salaryType.baseDataId }">${salaryType.baseDataName }</option>
	                                                </c:forEach>
	                                            </select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4 text-center">
                                        <button type="submit" class="btn btn-success">确 定</button>
                                        <button class="btn btn-default m-l" type="reset">重置</button>
                                    </div>
                                </form>
                            </div>
                            
                        </div>
                    </div>
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                <table id="batchAddTable" class="table table-striped table-condensed table-hover table-bordered dataTable" width="100%">
                                    <thead>
                                    <tr>
                                        <th><input type="checkbox" id="selAll" data-check="checkAll"></th>
                                        <th>工号</th>
                                        <th>姓名</th>
                                        <th>所属部门</th>
                                        <th>职位</th>
                                        <th>在职状态</th>
                                        <th>入职时间</th>
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
        <!-- 批量新增modal end -->
        <!-- 高级搜索 -->
        <form role="form" class="form-horizontal hidden" data-template="advanced-search" id="highSearch">
            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                <div class="form-group">
                    <div id="adv-search" class="input-group">
                        <input type="text" placeholder="输入关键字，按回车搜索，点下拉按钮显示高级搜索" class="form-control" id="highInput">
                        <div class="input-group-btn dropdown dropdown-lg dropdownself" >
                            <button aria-expanded="false" aria-haspopup="true" class="btn btn-default dropdown-toggle dropdownselfbtn" type="button">筛选 <span class="caret"></span></button>
                            <div role="menu" class="dropdown-menu dropdown-menu-right">
                                <div class="col-sm-7 col-md-7 col-lg-7 m-t">
                                    <div class="form-group">
                                        <label class="col-xs-3 col-sm-3 col-md-3 col-lg-3 control-label" for="">入职时间</label>
                                        <div class="col-sm-9 col-md-9 col-lg-9 form-dash">
                                            <div class="input-group date" data-date-format="yyyy-mm-dd">
                                                <input type="text" value="" name="entryDateStart" class="form-control">
                                                <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-5 col-md-5 col-lg-5 m-t">
                                    <div class="form-group">
                                        <div class="col-sm-12 col-md-12 col-lg-12">
                                            <div class="input-group date" data-date-format="yyyy-mm-dd">
                                                <input type="text" value="" name="entryDateEnd" class="form-control">
                                                <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-12 col-md-12 col-lg-12" id="sopstatus">
                                    <div class="form-group">
                                        <label class="col-xs-2 col-sm-2 col-md-2 col-lg-2 control-label" for="">在职状态</label>
                                        <div class="col-sm-10 col-md-10 col-lg-10 form-control-static"  >
                                            <label class="checkbox-inline ">                            
                                                <input type="checkbox"   value="1" name="status"> 在职                        
                                            </label>                                   
                                            <label class="checkbox-inline ">                            
                                                <input type="checkbox"   value="2" name="status"> 离职
                                            </label>                                   
                                            <label class="checkbox-inline ">
                                                <input type="checkbox"   value="3" name="status"> 停职
                                            </label>
                                            <label class="checkbox-inline ">                            
                                                <input type="checkbox"   value="4" name="status"> 退休                            
                                            </label>                                   
                                            <label class="checkbox-inline ">                            
                                                <input type="checkbox"   value="5" name="status"> 再入职                           
                                            </label>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-12 col-md-12 col-lg-12 m-b text-right btn-myself">
                                    <button class="btn btn-success " type="submit">搜索</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>
    <div style="display:none;">
    	<form id="detailForm" action="" method="post">
    		<input type="hidden" id="salaryId" name="salaryId">
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
    <script src="static/bootstrap/vendor/bootstrap-datepicker-master/dist/js/bootstrap-datepicker.js"></script>
    <script src="static/bootstrap/vendor/toastr/build/toastr.min.js"></script>

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

    <script src="static/js/common/ajaxfileupload.js"></script>
    <script src="static/js/common/hashMap.js"></script>
    
<script type="text/javascript">
	var table, batchTable, salaryTypeMap = new HashMap();
	jQuery.prototype.serializeObject=function(){  
        var obj=new Object();  
        $.each(this.serializeArray(),function(index,param){  
          if(!(param.name in obj)){  
            obj[param.name]=param.value;  
          }  
        });  
        return obj;  
    };
	
	<c:forEach var="salaryType" items="${requestScope.salaryTypeList }" varStatus="status">
		salaryTypeMap.put(${salaryType.baseDataId }, "${salaryType.baseDataName }");
	</c:forEach>
	
	function getSalaryTypeName(type){
		if(type){
			return salaryTypeMap.get(type);
		}
		
		return '';
	}
	
    $(function (){
    	$('#addForm')[0].reset();
    	$('#uploadForm')[0].reset();
    	
    	loadTable();
    	
    	bindUpload();


        //新增表单校验
        $('#addForm').formValidation({
            err: {
                container: 'tooltip'
            },
            icon: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                employeeName: {
                    validators: {
                        notEmpty: {
                            message: '请填写必填项'
                        }
                    }
                },
                salaryTemplateId: {
                    validators: {
                        notEmpty:{
                            message: '请填写必填项'
                        }
                    }
                },
                usageFlag: {
                    validators: {
                        notEmpty:{
                            message: '请填写必填项'
                        }
                    }
                }
                
            }
        })
        .on('success.form.fv', function(e){
            e.preventDefault();
            $.ajax({
                url : "salary/editSalary",
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
                        },function(){
                            //window.location.href='salary/toListSalary';
                            mod(message.salaryId);
                        });
                        
                    }else{
                        swal({
                            title: "新增失败!",
                            text: message.msg,
                            type: "error",
                            confirmButtonText: "确定"
                        },function(){

                            $(e.target).formValidation('resetForm', true);
                        });

                    }
                }
            });
        })
        
    });
    
    $('[name="employeeName"]').on('keyup', function(event) {

        var markup = '<div class="list-group col-sm-12" data-help-for="jobNumber">'

        $.ajax({
            url: 'employee/searchBySearch',
            type: 'POST',
            dataType: 'json',
            data: {search: $(this).val()},
            delay: 100
        })
        .done(function(d){
            $.each(d, function(index, val){
                markup += '<a href="javascript://" class="list-group-item" data-cert="'+val.certId+'" data-jobnumber="'+val.jobNumber+'" data-name="'+val.employeeName+'">'
                markup += val.employeeName+'<span class="m-l-sm">'+(val.jobNumber?val.jobNumber:'')+'</span>'
                markup += '<p class="m-n">'+(val.certId?val.certId:'')+'</p>'
                markup += '</a>'
            });
            markup += '</div>'

            $('[data-help-for="jobNumber"]').replaceWith(markup);

        })
        .always(function(d){
            $('[data-help-for="jobNumber"]')
            .css({
                position: 'absolute',
                zIndex: '996',
                backgroundColor: '#fff',
                maxHeight: '320px',
                overflow: 'auto',
                padding: 0
            })
            .find('.list-group-item')
            .each(function(index, el) {
                $(this).off().on('click', function(event){
                    event.preventDefault()
                    $(this).parent('[data-help-for="jobNumber"]').hide()
                    $('[name=employeeName]').val( $(this).attr('data-name') )
                    $('[name=certId]').val( $(this).attr('data-cert') )
                    $('[name=jobNumber]').val( $(this).attr('data-jobnumber') )
                });
            });

        });
    });



    function doSearch(){
    	loadTable($('#searchForm').serializeObject());
    }
    
    function loadTable(params){
        if(table){
        	table.destroy();
        }
    	table = $('#salaryTable').DataTable({
            "dom": 
                "<'row'<'col-sm-5'l><'col-sm-4'f><'col-sm-3'<'toolbar text-right'>>>" +
                "<'row'<'col-sm-12 table-responsive'tr>>" +
                "<'row'<'col-sm-5'i><'col-sm-7'p>>",
            
            //json
            "processing": true,
            "serverSide": true,
            "bDestroy": true,
            "ordering": false,
            "ajax": {
            	"url":"salary/listSalaryPage",
            	"type":"post",
            	"data": params ? params : {}
            },
            "columns": [
	            { "data": "jobNumber" },
	            { "data": "employeeName" },
	            { "data": "templateName" },
	            { "data": "usageFlag", "render": function(data, type, row, meta){
	            	return getSalaryTypeName(data);
	            }},
	            { "data": "status", "render": function(data, type, row, meta){
	            	if(data == 1){
	            		return "在发";
	            	}else{
	            		return "停发";
	            	}
	            } },
	            { "render": function render( data, type, row, meta ){
	            	var payOff = '';
	            	if(row.status == 1){
	            		payOff = '停发';
	            	}else{
	            		payOff = '发放';
	            	}
                    var strHtml = '';
                    <% if(RequestUtil.hasPower("salary_rs")){ %>
                    strHtml += '<button class="btn btn-info btn-xs" type="button" onclick="changeStatus(' + row.salaryId + ',' + row.status + ');">' + payOff + '</button>&nbsp;';
                    <% } %>
                    <% if(RequestUtil.hasPower("salary_ms")){ %>
                    strHtml += '<button class="btn btn-success btn-xs" type="button" onclick="mod(' + row.salaryId + ');">修改</button>&nbsp;';
                    <% } %>
                    <% if(RequestUtil.hasPower("salary_ds")){ %>
                    strHtml += '<button class="btn btn-danger btn-xs del-button" type="button" onclick="del(' + row.salaryId + ');">删除</button>';
                    <% } %>
	                return strHtml;
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
        var strHtml = '';
        <% if(RequestUtil.hasPower("salary_as")){ %>
        strHtml += '<button class="btn btn-info btn-sm" type="button" data-target="[data-modal=addSalary]" data-toggle="modal">新增</button>&nbsp';
        <% } %>
        strHtml += '<button class="btn btn-success btn-sm" type="button" data-target="[data-modal=batchAdd]" data-modal-toggle="batchAdd"><i class="fa">批量新增</i></button>&nbsp';
        strHtml += '<button class="btn btn-info btn-sm" type="button" data-toggle="collapse" data-target="[data-toggle=search]" aria-expanded="false" aria-controls="collapseExample"><i class="fa">高级搜索</i></button>';
        $(".toolbar").html(strHtml);
        batchAdd();
    }
    
    function changeStatus(id,status){
    	$.ajax({
            url : "salary/updateStatus",
            data : {id:id, status: status ? 0 : 1},
            dataType:'json',
            type : 'post',
            success : function(message) {
                if(message.success){
                    swal({
                        title: "操作成功!",
                        text: "",
                        type: "success",
                        confirmButtonText: "确定"
                    });
                    
                    //mod(message.salaryId);
                    table.ajax.reload();
                }else{
                    swal({
                        title: "操作失败!",
                        text: message.msg,
                        type: "error",
                        confirmButtonText: "确定"
                    });
                }
            }
        });
    }
    
    function mod(id){
    	$('#salaryId').val(id);
		$('#detailForm').attr('action','salary/toAddSalary');
		$('#detailForm').submit();
    }
    
    function save(){
        //校验
        $.ajax({
            url : "salary/editSalary",
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
                    },function(){
                        window.location.href='salary/toListSalary';
                    });
                    
                }else{
                    console.log(message)
                    swal({
                        title: "新增失败!",
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
                title: "确定要删除此工资吗?",
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
                    swal("已取消", "工资未删除", "error");
                }
            }
        );
    }
    
    function deleteById(id){
        $.ajax({
            url : "salary/deleteSalary",
            data : {salaryId: id},
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
                    window.location.href="salary/toListSalary";
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

    //批量导入
    function getFileType(obj){
	    photoExt=obj.value.substr(obj.value.lastIndexOf(".")).toLowerCase();//获得文件后缀名   
	    if(photoExt!='.xlsx'){
	        alert("请上传后缀名为xlsx的文件!");
	        return false;
	    }
	}
    
	function bindUpload(){

        toastr.options = {
            "debug": false,
            "newestOnTop": false,
            "positionClass": "toast-top-center",
            "closeButton": false,
            "toastClass": "animated fadeInDown",
        };

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
                url: 'common/uploadFile', 	//用于文件上传的服务器端请求地址
                secureuri: false, 			//是否需要安全协议，一般设置为false
                fileElementId: ['uploadFile'], //文件上传域的ID    
                async:false,
                dataType:'text',
                type:"POST",
                success: function (result){  //服务器成功响应处理函数
                	if(!result.success){
                		alert(result.msg);
                		return false;
                	}
                
                	$("#filePath").val(result.filePath);
                	
                    $.ajax({
                        type : 'post',
                        url : "salary/adjustSalary",
                        data : $("#uploadForm").serialize(),
                        async : false,
                        dataType:'json',
                        success : function(message) {
                            if(message.success){
                                swal({
                                    title: "恭喜您上传成功!",
                                    text: "",
                                    type: "success",
                                    confirmButtonText: "确定"
                                });
                                window.location.href="salary/toListSalary";
                            }else{
                                swal({
                                    title: "上传失败!",
                                    text: message.msg,
                                    type: "error",
                                    confirmButtonText: "确定"
                                }, function(){
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
        });
	}

	$('[data-toggle="upload:file"] :file').on('change.file:selected', function(event){
	    var oEventTarget = $(this),
	        oFile = $(this).val()
	        fileExt = oFile.substr(oFile.lastIndexOf(".")).toLowerCase()
	
	    if(fileExt!='.xlsx'){
	        alert("请上传后缀名为xlsx的文件!");
	        return;
	    }
	
	    oEventTarget.parents('[data-toggle="upload:file"]')
	    .find(':text').val( oFile )
	});

	$('[data-toggle="upload:file"]').on('click', function(event){
	    $(this).find(':file').trigger('click.file:selected')
	});

    //批量新增
	var params = {};
    var batchAdd = function(){
        $('[data-modal-toggle="batchAdd"]').on('click', function(event) {
            var $modal = $($(this).attr('data-target'));
            $modal.modal('show');
            $('#highSearch')[0].reset();
            $('[data-id="batchAdd"]')[0].reset();
			params.searchValue=$("#highInput").val();
            params.searchData=JSON.stringify($('#highSearch').serializeObject()); 
            loadPersonForBatch(params);
        })
    };

    var fv;
    var batchAddFormvalidate = function(){
        $('[data-id="batchAdd"]').formValidation({
                err: {
                    container: 'tooltip'
                },
                icon: {
                    valid: 'glyphicon glyphicon-ok',
                    invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                },
                fields: {
                    usageFlag: {
                        validators: {
                            notEmpty: {
                                message: '请填写必填项'
                            }
                        }
                    },
                    salaryTemplateId: {
                        validators: {
                            notEmpty:{
                                message: '请填写必填项'
                            }
                        }
                    }
                }
            })
            .on('success.form.fv', function(e) {
                e.preventDefault();
                var len = $(":checked[name='checkAll']").length;
                    if(len == 0){
                        alert("请选择一个清单！");
                        return false;
                    }

                var formData = $(e.target).serializeObject();
                $.ajax({
                    url: 'salary/batchEditSalary',
                    type: 'POST',
                    dataType: 'json',
                    data: formData
                })
                .done(function(d) {
                    if(d.success){
                        swal({
                            type: 'success',
                            title: d.msg
                        }, function(){
                        	window.location.reload();
                        })
                    }else{
                       swal({
                            type: 'error',
                            title: d.msg
                        }) 
                    }
                    
                })
            });

        $('[data-modal="batchAdd"]').on('hide.bs.modal', function(event) {
            $('[data-id="batchAdd"]').formValidation('resetForm');
        });
        fv = $('[data-id="batchAdd"]').data('formValidation');
    }();
    
    function loadPersonForBatch(params){
        var getAdvancedSearchTemplate = $('[data-template=advanced-search]');
    	if(batchTable){
    		batchTable.destroy();
    	}
    	batchTable = $('#batchAddTable').DataTable({
            "dom": 
                "<'row'<'col-sm-4'l><'col-sm-8 advanced-search'f>>"+
                "<'row'<'col-sm-12 table-responsive'tr>>" +
                "<'row'<'col-sm-5'i><'col-sm-7'p>>",
            "processing": true,
            "serverSide": true,
            "bDestroy": true,
            "ordering": false,
            "ajax": {
                "url": "employee/empdataTableSearch",
                "type": "post",
                "data": params ? params : {}
            },
            "columns": [
                {"data": "person_id" ,
                    render: function(data, type, row, meta){
                        var strHtml = '<input type="checkbox" name="checkAll" data-check="item" value="'+data+'">';
                        return strHtml;
                    },
                    createdCell: function (td, cellData, rowData, row, col){
                        var ueSelected = function($o, bSelectLast){
                            var getAllVal = [];
                            $.each($('[data-check="item"]:checked'), function(index, item) {
                                var val = Number($(this).val())
                                getAllVal.push( val );
                            });
                            return getAllVal;
                        };
                        $('[data-check="checkAll"]').on('click', function(event) {
                            var getState = $(this).prop('checked'),
                                $getCheckItem = $(this),
                                $getCheckItems = $('[data-check="item"]');
                            $getCheckItems.prop('checked', getState);
                            checked($getCheckItem,$getCheckItems,ueSelected);
                        });
                        $(td).on('click', '[data-check="item"]', function(event) {
                            var $getCheckItem = $(this),
                                $getCheckItems = $getCheckItem;
                            checked($getCheckItem,$getCheckItems,ueSelected);
                        });
                    }
                },
                {"data": "job_number"},
                {"data": "employee_name"},
                {"data": "org"},
                {"data": "post"},
                {"data": "hstatus"},
                {"data": "entry_date"}
            ],
            "columnDefs": [
                {defaultContent: '', targets: '_all'}
            ],
            "language": {
                "search": "过滤:",
                "processing":"数据加载中",
                "lengthMenu": "每页显示 _MENU_ 条记录",
                "zeroRecords": "暂无数据 - 报歉啦〜",
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
        highSearch(getAdvancedSearchTemplate);
    }

    function checked($getCheckItem,$getCheckItems,ueSelected){
        var getSubmitButton = fv.getSubmitButton();
        if( $getCheckItem.prop('checked') ){
            var getIDs = ueSelected($getCheckItems);
            $('[data-id="batchAdd"]').find('[name="personIds"]').val(getIDs);
        }else{
            $('[data-id="batchAdd"]').find('[name="personIds"]').val('');
        }
        if(getSubmitButton && getSubmitButton.hasClass('disabled')) {
            fv.disableSubmitButtons(false);
        }
    }

    var highSearchSubmit = function(){
        $("#highSearch").on("submit",function(event){
            event.preventDefault();
            $('.dropdownself').removeClass('open');
    		params.searchValue=$("#highInput").val();
            params.searchData=JSON.stringify($('#highSearch').serializeObject());
            loadPersonForBatch(params);
        });
    }();
    
    function highSearch(getAdvancedSearchTemplate){
        $('.advanced-search').html( getAdvancedSearchTemplate ); 
        $('.input-group.date').datepicker({
            autoclose: true
        }); 
        $('[data-template=advanced-search]').removeClass('hidden'); 
    }
    //筛选按钮点击事件
    var dropdownselfbtnClick = function(){
        $('.dropdown').on('click', '.dropdownselfbtn', function(event) {
            event.preventDefault();
            var getParent = $(this).parents('.dropdown');
            //var getSibling = $(this).siblings();
            !getParent.hasClass('open') ? getParent.addClass('open'): getParent.removeClass('open');
            //getSibling.find('input:text').val('');
            //getSibling.find('input:checkbox').prop('checked',false);
        });  
    }();

    
</script>

</body>
</html>
