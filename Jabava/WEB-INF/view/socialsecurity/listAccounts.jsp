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
<title>社保账户管理</title>
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
                  社保账户管理
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
                        <!--新增弹框-->    
                 <div class="modal fade hmodal-success form-row" data-modal="addSalaryDate" tabindex="-1" role="dialog"  aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="color-line"></div>
                            <div class="modal-header">
                             <div class="row">
                             <div class="panel-body">
                              <form class="form-horizontal" id="addForm" data-form-type="add" data-form="validator">
                                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                        <div class="form-group">
                                            <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-3">社保账户名称：</label>
                                            <div class="col-xs-6 col-sm-6 col-md-6 col-lg-9 form-required">
                                                <input type="text" class="form-control" id="" name="socialSecurityAccountName" >
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                        <div class="form-group">
                                            <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-3">社保账户编号：</label>
                                            <div class="col-xs-6 col-sm-6 col-md-6 col-lg-9 form-required">
                                                <input type="text" class="form-control" id="" name="socialSecurityCode" >
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                        <div class="form-group">
                                        <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-3">参保地：</label>
                                        <div class="col-xs-6 col-sm-6 col-md-6 col-lg-4">
                                            <select class="form-control select2" id="province" >
                                                <option></option>
                                            </select>
                                        </div>
                                        <div class="col-xs-6 col-sm-6 col-md-6 col-lg-5 form-required">
                                            <select class="form-control select2" id="addLocationCity" name="locationCityId">
                                                <option ></option>                           
                                            </select>
                                        </div>
                                    </div>
                                    </div>
                                     <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                        <div class="form-group">
                                            <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-3">参保规则：</label>
                                            <div class="col-xs-6 col-sm-6 col-md-6 col-lg-9 form-required">
                                                <select class="form-control select2" id="addSocialSecurityRule" name="socialSecurityRule" multiple="multiple" data-toggle="select2">
                                                    <option ></option>                                                
                                                </select>
                                                <span class="help-block">请选择至少一项参保规则</span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                        <div class="form-group">
                                            <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-3">备注：</label>
                                            <div class="col-xs-6 col-sm-6 col-md-6 col-lg-9">
                                                <input type="text" class="form-control" id="" name="remark" >
                                            </div>
                                        </div>
                                    </div>
                                      <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-center">
                                        <button type="submit" class="btn btn-info btn-sm">保 存</button>
                                    </div>
                                </form>
                              </div>
                            </div>   
                        </div>
                    </div>
                </div>
                </div>
                <!--新增弹框 end-->
                <!-- 修改 -->
                   <div class="modal fade hmodal-success form-row" data-modal="editSalaryDate" tabindex="-1" role="dialog"  aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="color-line"></div>
                            <div class="modal-header">
                             <div class="row">
                             <div class="panel-body">
                             <form class="form-horizontal" id="editForm" data-form-type="edit" data-form="validator">
                             		<input type="hidden" id="socialSecurityAccountId" name="socialSecurityAccountId" />
                                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                        <div class="form-group">
                                            <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-3">社保账户名称：</label>
                                            <div class="col-xs-6 col-sm-6 col-md-6 col-lg-9 form-required">
                                                <input type="text" class="form-control" id="updateSocialSecurityAccountName" name="socialSecurityAccountName" >
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                        <div class="form-group">
                                            <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-3">社保账户编号：</label>
                                            <div class="col-xs-6 col-sm-6 col-md-6 col-lg-9 form-required">
                                                <input type="text" class="form-control" id="updateSocialSecurityCode" name="socialSecurityCode" >
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                        <div class="form-group">
                                        <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-3">参保地：</label>
                                        <div class="col-xs-6 col-sm-6 col-md-6 col-lg-4">
                                            <select class="form-control select2" id="updateProvince" >
                                                <option ></option>
                                            </select>
                                        </div>
                                        <div class="col-xs-6 col-sm-6 col-md-6 col-lg-5 form-required">
                                            <select class="form-control select2" id="updatelocationCity" name="locationCityId">
                                                <option > </option>
                                            </select>
                                        </div>
                                    </div>
                                    </div>
                                     <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                        <div class="form-group">
                                            <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-3">参保规则：</label>
                                            <div class="col-xs-6 col-sm-6 col-md-6 col-lg-9 form-required">
                                                <select class="form-control select2" id="updateSocialSecurityRule" name="socialSecurityRule" multiple="multiple" data-toggle="select2">
                                                    <option> </option>
                                                </select>
                                                <span class="help-block">请选择至少一项参保规则</span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                        <div class="form-group">
                                            <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-3">备注：</label>
                                            <div class="col-xs-6 col-sm-6 col-md-6 col-lg-9">
                                                <input type="text" class="form-control" id="updateRemark" name="remark" >
                                            </div>
                                        </div>
                                    </div>
                                      <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-center">
                                        <button type="submit" class="btn btn-info btn-sm">保 存</button>
                                    </div>
                                </form>
                              </div>
                            </div>   
                        </div>
                    </div>
                </div>
                </div> 
                <!-- 修改end -->

                        <div class="panel-body m-t-md"> 
                            <table id="projectTable" class="table table-condensed table-hover table-bordered dataTable" width="100%">
                                <thead>
                                <tr>
                                    <th>社保账户名称</th>
                                    <th>社保账户编号</th>
                                    <th>参保地</th>
                                    <th>参保规则</th>
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
    <script src="static/bootstrap/vendor/bootstrap-datepicker-master/dist/locales/bootstrap-datepicker.zh-CN.min.js"></script>

    <!-- for datatable -->
    <script src="static/bootstrap/vendor/datatables/media/js/jquery.dataTables.min.js"></script>
    <script src="static/bootstrap/vendor/datatables_plugins/integration/bootstrap/3/dataTables.bootstrap.min.js"></script>


    <!-- alert -->
    <script src="static/bootstrap/vendor/sweetalert/lib/sweet-alert.min.js"></script>
    
    <!-- App scripts -->
    <script src="static/bootstrap/scripts/homer.js"></script>
    <script src="static/bootstrap/scripts/charts.js"></script>

    <script src="static/bootstrap/vendor/select2-3.5.2/select2.min.js"></script>
    <!-- 表单验证 -->
    <script src="static/js/plugins/form.validation/js/formValidation.js"></script>
    <script src="static/js/plugins/form.validation/js/framework/bootstrap.js"></script>
    
<script type="text/javascript">
 function addSocialSecurityAccount(){
	
 }
 
 
	 $.ajax({
	    type : "POST",
	    url : "socialsecurityaccount/getProvinceInfo",	  
	    dataType : "json",
	    success : function(result) {
	     if(result){
	    	 $("#province").html(''); 
	    	 $("#updateProvince").html(''); 
	    	  $("#province").append("<option >请选择 </option>"); 
	    	  $("#updateProvince").append('<option>请选择 </option>'); 
	    	for(var i=0;i<result.length;i++){
	    		 $("#province").append("<option value='"+result[i].provinceId+"'>"+result[i].provinceName+"</option>");
	    		 $("#updateProvince").append("<option value='"+result[i].provinceId+"'>"+result[i].provinceName+"</option>");
	    	}
            $('#province').select2()
            $("#addLocationCity").select2();
         }
          
        } 
      }); 

    var table;
    $(function (){
        //新增页面的 选择省份显示城市
        $("#province").change(function(){           
            var provinceId = $(this).val();
            
            if(provinceId=='请选择'){
                return false;
            }
            
            $.ajax({
                type : "POST",
                url : "socialsecurityaccount/getCityByProvinceId",
                data :{provinceId:provinceId},
                dataType : "json",
                success : function(result) {
                    $("#addLocationCity").empty();                  
                    $("#addLocationCity").append('<option>请选择</option>');
                    for(var i=0;i<result.length;i++){
                         $("#addLocationCity").append("<option value='"+result[i].cityId+"'>"+result[i].cityName+"</option>");
                    }
                    $('#addLocationCity').select2('val','请选择');
 				} 
 			}); 
    	});
    	//新增页面的选择城市显示参保地
    	$("#addLocationCity").change(function(){    		
    		var cityId = $(this).val();
    		if(cityId=='请选择'){
    			return false;
    		}
    		  $.ajax({
 				type : "POST",
 				url : "socialsecurityaccount/getGroupByCityAndType",
 				data :{cityCode:cityId,policyGroupType:1},
 				dataType : "json",
 				success : function(result) {
 					  $("#addSocialSecurityRule").empty();
                      var _summary = '';
 					 if(result && result.length>0){
 						 for(var i=0,l=result.length;i<l;i++){
                            _summary = result[i].summary!==undefined?result[i].summary:'';
 	 			    		 $("#addSocialSecurityRule").append('<option value="'+result[i].id+'" data-summary="'+_summary+'">'+result[i].sbGroupName+'</option>');
 	 			    	}
                         
 					 } 
 					
 					
 					
 				} 
 			}); 
    	});
        //参保规则初始化
        $('.form-control.select2').css({
            paddingRight: 0
        });
    	// $('#addSocialSecurityRule').select2({
     //        formatNoMatches:' '
     //    });
        // $('#updateSocialSecurityRule').select2({
        //     formatNoMatches:' '
        // });
    	//修改 页面的 选择省份显示城市
    	$("#updateProvince").change(function(){    		
    		var provinceId = $(this).val();
    		if(provinceId=='请选择'){
    			return false;
    		}
    		  $.ajax({
 				type : "POST",
 				url : "socialsecurityaccount/getCityByProvinceId",
 				data :{provinceId:provinceId},
 				dataType : "json",
 				success : function(result) {
 					  $("#updatelocationCity").empty();
 					 $("#updatelocationCity").append("<option >请选择 </option>"); 		 	
 			    	for(var i=0;i<result.length;i++){
 			    		 $("#updatelocationCity").append('<option value="'+result[i].cityId+'">'+result[i].cityName+'</option>');
 			    	} 
 					
 				} 
 			}); 
    	});
    	//修改页面的选择城市显示参保地
    	$("#updatelocationCity").change(function(){    		
    		var cityId = $(this).val();
    		if(cityId=='请选择'){
    			return false;
    		}
    		  $.ajax({
 				type : "POST",
 				url : "socialsecurityaccount/getGroupByCityAndType",
 				data :{cityCode:cityId,policyGroupType:1},
 				dataType : "json",
 				success : function(result) {
 					  $("#updateSocialSecurityRule").empty();
                      var _summary = '';
 					 if(result && result.length>0){
 						 for(var i=0,l=result.length;i<l;i++){
                            _summary = result[i].summary!==undefined?result[i].summary:'';
 	 			    		 $("#updateSocialSecurityRule").append('<option value="'+result[i].id+'" data-summary="'+_summary+'">'+result[i].sbGroupName+'</option>');
 	 			    	}  
 					 } 
 					
 					
 					
 				} 
 			}); 
    	});
    	//加载table 
    	loadTable();

        //form重置
        $('[data-modal]').on('hide.bs.modal', function (e) {
            var getForm = $(this).find('[data-form-type]')
            getForm[0].reset();
            getForm.formValidation('resetForm');
            getForm.find('[name="socialSecurityRule"]').select2({
                formatNoMatches:' '
            })
        });

        var validatorOptions = {
            validator: {
                excluded: ':disabled',
                framework: 'bootstrap',
                err: {
                    container: 'tooltip'
                },
                icon: {
                    valid: 'glyphicon glyphicon-ok',
                    invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                },
                fields: {
                    socialSecurityAccountName: {
                        validators: {
                            notEmpty: {
                                message: '请填写必填项'
                            },
                            stringLength: {
                                'default': '请输入符合长度限制的值',
                                max: 30,
                                message: '最多只能输入 30 个字符'
                            }
                        }
                    },
                    socialSecurityCode: {
                        validators: {
                            notEmpty: {
                                message: '请填写必填项'
                            },
                            stringLength: {
                                'default': '请输入符合长度限制的值',
                                max: 30,
                                message: '最多只能输入 30 个字符'
                            }
                        }
                    },
                    locationCityId: {
                        validators: {
                            notEmpty: {
                                message: '请填写必填项'
                            }
                        }
                    },
                    socialSecurityRule: {
                        validators: {
                            callback: {
                                message: '请选择至少一项参保规则',
                                callback: function(value, validator, $field){
                                    var options = validator.getFieldElements('socialSecurityRule').val();
                                    return (options != null && options.length >= 1);
                                }
                            }
                        }
                    }
                }
            } // end validator
        };

        $('[data-form="validator"]')
        .find('[name="socialSecurityRule"]')
        .each(function(index, el) {
            $(el)
            .select2({
                formatNoMatches:' ',
                formatSelection: function(d){
                    var summary = $(d.element).attr('data-summary');
                        summary = summary !== ''?'<div class="select2-summary">'+summary+'</div>':'';
                    return d.text+summary;
                }
            })
            .change(function(e){
                $(e.target).parents('form').formValidation('revalidateField', 'socialSecurityRule');
            })
        })
        .end()
        .formValidation(validatorOptions.validator)
        .on('success.form.fv', function(e){
            e.preventDefault();
            var getFormType = $(e.target).attr('data-form-type');
            switch(getFormType){
                case 'add':
                     $.ajax({
                        type : "POST",
                        url : "socialsecurityaccount/addSocialSecurityAccount",
                        data : $("#addForm").serialize(),
                        dataType : "json",
                        success : function(result) {
                            if(result=='success'){
                                swal("新增成功", "", "success"); 
                            }else{
                                swal(result, "", "error");
                            }
                            window.location.reload();
                            
                        } 
                    });
                    break;
                case 'edit':
                    $.ajax({
                        type : "POST",
                        url : "socialsecurityaccount/updateSocialSecurityAccount",
                        data : $("#editForm").serialize(),
                        dataType : "json",
                        success : function(result) {
                            
                            if(result=='success'){
                                swal("修改成功", "", "success"); 
                            }else{
                            	
                                swal(result, "", "error",function (isConfirm) {
                    	            if (isConfirm) {
                    		            deleteById(socialSecurityAccountId);
                    	            }else{
                    	            	swal("已取消", "社保账户删除已取消", "error");
                    	            }
                    	        });
                            }
                             
                            window.location.reload();
                            
                        } 
                    });
                    break;
            }
        });
    });
    function showDetail(socialSecurityAccountId){
    	
    }
    function loadTable(){
      	if(table){
      		table.destroy();
      	}
    	table = $('#projectTable').DataTable({
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
				"url":"socialsecurityaccount/findSocialSecurityAccountPage",
				"type":"post"
			},
			"columns": [
			    {"data": "socialSecurityAccountName", "render": function render( data, type, row, meta ){
                        var htmlLink = '<td>';
                        <% if(RequestUtil.hasPower("ssaccount_va")){ %>
                        htmlLink += '<a href="socialsecurity/viewAccounts?socialSecurityAccountId=' + row.socialSecurityAccountId + '" >' + data + '</a>';
                        <% }else{ %>
                        htmlLink += data;
                        <% } %>
                        htmlLink += '</td>';
						  return htmlLink; 
                    } 
                },
                { "data": "socialSecurityCode" },
                { "data": "city.cityName" },
                { "data": "socialSecurityRuleShow" },
				{ "data": "remark" },
				{ "render": function render( data, type, row, meta ){
						return ''+
                        <% if(RequestUtil.hasPower("ssaccount_ma")){ %>
                        '<button class="btn btn-success btn-xs" data-target="[data-modal=editSalaryDate]" data-toggle="modal" type="button" onclick="mod(' + row.socialSecurityAccountId + ');">修改</button>&nbsp;' +
                        <% } %>
                        <% if(RequestUtil.hasPower("ssaccount_da")){ %>
                        '<button class="btn btn-danger btn-xs del-button" type="button" onclick="del(' + row.socialSecurityAccountId + ');">删除</button>';
                        <% } %>
                    }
				}
			],
    		"columnDefs": [
    			{defaultContent: '', targets: '_all'}
			],
			
   	 		"language": {
                "search": "过滤:",
	            "lengthMenu": "每页显示 _MENU_ 条记录",
	            "zeroRecords": "暂无数据，您可以点击新增添加社保账户",
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
        <% if(RequestUtil.hasPower("ssaccount_aa")){ %>
         $("div.toolbar").html('<button class="btn btn-info btn-sm" data-target="[data-modal=addSalaryDate]" data-toggle="modal">新  增</button>');
        <% } %>


    }
	//点击修改按钮
    function mod(socialSecurityAccountId){
	//	alert(socialSecurityAccountId);
  	  $.ajax({
			type : "POST",
			url : "socialsecurityaccount/getSsSocialSecurityAccountById",
			data :{socialSecurityAccountId:socialSecurityAccountId},
			dataType : "json",
			success : function(result) {
					if(result){
                        $("#socialSecurityAccountId").val(result.socialSecurityAccountId);
                        $("#updateSocialSecurityAccountName").val(result.socialSecurityAccountName);
                        $("#updateSocialSecurityCode").val(result.socialSecurityCode);
                        $("#updateRemark").val(result.remark);
                        //参保地的省份
                        $("#updateProvince").val(result.provinceId).select2();
					     //参保地的 城市		 		   
					     var locationCityId = result.locationCityId;
					     $.ajax({
                                type : "POST",
                                url : "socialsecurityaccount/getCityByProvinceId",
                                data :{provinceId:result.provinceId},
                                dataType : "json",
                                success : function(result) {
                                $("#updatelocationCity").empty();
                                $("#updatelocationCity").append("<option >请选择 </option>"); 	 	
                                for(var i=0;i<result.length;i++){
                                    $("#updatelocationCity").append('<option value="'+result[i].cityId+'">'+result[i].cityName+'</option>');
                                }
                                $("#updatelocationCity").val(locationCityId).select2();
			 				} 
				 		}); 
					     //参保规则
					     $("#updateSocialSecurityRule").empty();	
					     $.ajax({
				 				type : "POST",
				 				url : "socialsecurityaccount/getGroupByCityAndType",
				 				data :{cityCode:locationCityId,policyGroupType:1},
				 				dataType : "json",
				 				success : function(res) {
				 					
				 					 $("#updateSocialSecurityRule").empty();
                                     var _summary = '';
				 					 if(res && res.length>0){
				 						 for(var i=0,l=res.length;i<l;i++){
                                            _summary = res[i].summary!==undefined?res[i].summary:'';
				 	 			    		 $("#updateSocialSecurityRule").append('<option  value="'+res[i].id+'" data-summary="'+_summary+'">'+res[i].sbGroupName+'</option>');
				 	 			    		if(result && result.groupList && result.groupList.length>0){
				 	 			    			
					 	 			    		 for(var c=0;c<result.groupList.length;c++){
					 	 			    			// console.log(i+ ' res[i].id   '+ res[i].id   +"  "+c+ 'result.groupList[c].id  ' +result.groupList[c].id);
					 	 			    			if(res[i].id == result.groupList[c].id){														
							 			    			//参保规则为选中状态
							 			    			$("#updateSocialSecurityRule  option").eq(i).prop("selected", "selected");  
							 			    		    $("#updateSocialSecurityRule").select2({
                                                            formatNoMatches:' ',
                                                            formatSelection: function(d){
                                                                var summary = $(d.element).attr('data-summary');
                                                                    summary = summary !== ''?'<div class="select2-summary">'+summary+'</div>':'';
                                                                return d.text+summary;
                                                            }
                                                        })
							 			    		}
					 	 			    			 
						 	 			    	}
				 						  }  
				 					 }
				 				}	 	 			    	
				 					
				 				} 
				 			});					     
					
					}
				     
			} 
		}); 
    }

    function del(socialSecurityAccountId){
    	swal(
    		{
	            title: "确定要删除此社保账户吗?",
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
		            deleteById(socialSecurityAccountId);
	            }else{
	            	swal("已取消", "社保账户删除已取消", "error");
	            }
	        }
        );
    }
    //删除社保账户
    function deleteById(socialSecurityAccountId){
    	$.ajax({
			url : "socialsecurityaccount/delSocialSecurityAccount",
			data : {socialSecurityAccountId : socialSecurityAccountId},
			dataType:'json',
			type : 'post',
			success : function(result) {
				if(result =="success"){
					swal({
	                    title: "删除成功!",
	                    text: "",
	                    type: "success",
	    				confirmButtonText: "确定"
	                });
					//window.location.href="salaryDate/toListSalaryDate";
					loadTable();
				}else{
					swal({
	                    title: "删除失败!",
	                    text: result,
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