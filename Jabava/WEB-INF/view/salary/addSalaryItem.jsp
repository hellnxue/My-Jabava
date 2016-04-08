<%@page import="org.springframework.web.context.ContextLoader"%>
<%@page import="org.springframework.web.context.WebApplicationContext"%>
<%@page import="com.jabava.task.ImportBillTask"%>
<%@page import="com.jabava.task.GetOrder"%>
<%@page import="com.jabava.pojo.hro.HroPactInfo"%>
<%@page import="java.util.concurrent.ExecutorService"%>
<%@page import="java.util.concurrent.Executors"%>
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
<title>工资项目管理</title>
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
                                新增工资项目
                            </h4>
                        </div>
                        <div class="panel-body">
                            <form class="form-horizontal" id="itemForm">
                            	<input type="hidden" name="salaryItemId" value="${salaryItem.salaryItemId }">
                                <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
                                    <div class="form-group">
                                        <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-4">工资项目名称：</label>
                                        <div class="col-xs-6 col-sm-6 col-md-6 col-lg-8 form-required">
                                            <input type="text" class="form-control" name="salaryItemName" value="${salaryItem.salaryItemName}">
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
                                    <div class="form-group">
                                        <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-4">计税方法：</label>
                                        <div class="col-xs-6 col-sm-6 col-md-6 col-lg-8">
                                            <select class="form-control" id="taxRule" name="taxRule" onchange="relateTaxRate();">
                                                <option value="1">合并计税</option>
                                                <option value="2">独立计税</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
                                    <div class="form-group">
                                        <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-4">适用税率：</label>
                                        <div class="col-xs-6 col-sm-6 col-md-6 col-lg-8">
                                            <select class="form-control" id="taxRateId" name="taxRateId" disabled>
                                                <option value="">无</option>
                                                <c:forEach var="rate" items="${requestScope.taxRateList }" varStatus="status">     
												    <option value="${rate.taxRateId }">${rate.taxRateName }</option>     
												</c:forEach>
                                            </select>
                                        </div>   
                                    </div>
                                </div>
                                <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
                                    <div class="form-group">
                                        <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-4">计税规则：</label>
                                        <div class="col-xs-6 col-sm-6 col-md-6 col-lg-8">
                                            <select class="form-control" id="calculateRule" name="calculateRule">
                                                <option value="1">税前计入</option>
                                                <option value="2">税后计入</option>
                                            </select>
                                        </div>   
                                    </div>
                                </div>
                                <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
                                    <div class="form-group">
                                        <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-4">项目类别：</label>
                                        <div class="col-xs-6 col-sm-6 col-md-6 col-lg-8">
                                            <select class="form-control" id="itemType" name="itemType">
                                                <option value="1">加项</option>
                                                <option value="2">减项</option>
                                            </select>
                                        </div>   
                                    </div>
                                </div>
                                <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
                                    <div class="form-group">
                                        <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-4">项目标识：</label>
                                        <div class="col-xs-6 col-sm-6 col-md-6 col-lg-8">
                                            <select class="form-control" id="yearlyFlag" name="yearlyFlag">
                                                <option value="0">普通项目</option>
                                                <option value="1">年终奖项目</option>
                                                <option value="2">养老项目</option>
                                                <option value="3">医疗项目</option>
                                                <option value="4">事业项目</option>
                                                <option value="5">公积金项目</option>
                                            </select>
                                        </div>   
                                    </div>
                                </div>
                                <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
                                    <div class="form-group">
                                        <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-4">变动表：</label>
                                        <div class="col-xs-6 col-sm-6 col-md-6 col-lg-8">
                                            <select class="form-control" id="salaryChangeDefId" name="salaryChangeDefId" onchange="loadChangeDefItem();">
                                                <option value="" selected>无</option>
                                                <c:forEach var="def" items="${requestScope.changeDefList }" varStatus="status">     
												    <option value="${def.salaryChangeDefId }">${def.name }</option>     
												</c:forEach>
                                            </select>
                                        </div>   
                                    </div>
                                </div>
                                <div class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
                                    <div class="form-group">
                                        <label class="control-label col-xs-3 col-sm-3 col-md-3 col-lg-2">变动计算公式：</label>
                                        <div class="col-xs-9 col-sm-9 col-md-9 col-lg-10">
                                            <input class="form-control" type="text" name="changeFormula" value="${salaryItem.changeFormula}" placeholder="您可以使用下面通用项，员工项，中间项中的项目定义公式，注意不能循环引用。">
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
                                    <div class="form-group">
                                        <label class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-4">显示顺序：</label>
                                        <div class="col-xs-6 col-sm-6 col-md-6 col-lg-8 form-required">
                                            <input class="form-control" type="text" name="displayOrder" value="${salaryItem.displayOrder}">
                                        </div>   
                                    </div>
                                    
                                </div>
                                <div class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
                                    <div class="form-group">
                                        <label class="control-label col-xs-3 col-sm-3 col-md-3 col-lg-2">是否中间项：</label>
                                        <div class="col-xs-9 col-sm-9 col-md-9 col-lg-10">
                                            <div class="radio radio-info radio-inline">
                                                <input class="form-control" type="radio" id="isTransitionYes" name="isTransition" value="1">
                                                <label for="inlineRadio1">是</label>
                                            </div>
                                            <div class="radio radio-info radio-inline">
                                                <input class="form-control" type="radio" id="isTransitionNo" checked name="isTransition" value="0">
                                                <label for="inlineRadio1">否</label>
                                            </div>
                                        </div>   
                                    </div>
                                </div>
                                <div class="col-xs-8 col-sm-8 col-md-8 col-lg-8 col-lg-pull-4 col-md-pull-4 col-sm-pull-4 col-xs-pull-4">
                                    <div class="form-group">
                                        <label class="control-label col-xs-3 col-sm-3 col-md-3 col-lg-2">备注：</label>
                                        <div class="col-xs-9 col-sm-9 col-md-9 col-lg-10">
                                            <textarea class="form-control" type="text" name="itemMemo" value="${salaryItem.itemMemo}">${salaryItem.itemMemo}</textarea>
                                        </div>   
                                    </div>
                                </div>
                                <div class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
                                    <div class="form-group m-b-none">
                                        <label class="control-label col-xs-3 col-sm-3 col-md-3 col-lg-2">通用项：</label>
                                        <div class="col-xs-9 col-sm-9 col-md-9 col-lg-10">
                                            <p class="form-control-static">${requestScope.commonItem }</p>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
                                    <div class="form-group m-b-none">
                                        <label class="control-label col-xs-3 col-sm-3 col-md-3 col-lg-2">员工项：</label>
                                        <div class="col-xs-9 col-sm-9 col-md-9 col-lg-10">
                                            <p class="form-control-static">${requestScope.personItem }</p>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
                                    <div class="form-group">
                                        <label class="control-label col-xs-3 col-sm-3 col-md-3 col-lg-2">中间项：</label>
                                        <div class="col-xs-9 col-sm-9 col-md-9 col-lg-10">
                                            <p class="form-control-static">${requestScope.transitionItem }</p>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
                                    <div class="form-group">
                                        <label class="control-label col-xs-3 col-sm-3 col-md-3 col-lg-2 col-change-label"></label>
                                        <div class="col-xs-9 col-sm-9 col-md-9 col-lg-10">
                                            <p class="form-control-static p-change-content"></p>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-center">
                                    <button type="submit" class="btn btn-info btn-sm btn-edit-item">确　定</button>
                                    <a class="btn btn-default btn-sm" href="salaryItem/toListSalaryItem">取　消</a>
                                    
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
    	var salaryItemId = '${salaryItem.salaryItemId }';
    	if(salaryItemId){
    		$('#taxRule').val(${salaryItem.taxRule });
			$('#taxRateId').val(${salaryItem.taxRateId });
			$('#calculateRule').val(${salaryItem.calculateRule });
			$('#itemType').val(${salaryItem.itemType });
			$('#yearlyFlag').val(${salaryItem.yearlyFlag });
			$('#salaryChangeDefId').val(${salaryItem.salaryChangeDefId });
    		var isTransition = '${salaryItem.isTransition }';
        	console.log(isTransition);
    		if(isTransition == 'true'){
    			$('#isTransitionYes').prop('checked', true);
    		}else{
    			$('#isTransitionNo').prop('checked', true);
    		}
    		
    		//关联税率
    		relateTaxRate()
    		
    		//关联变动表项
    		loadChangeDefItem();
    	};


        //表单验证
        $('#itemForm').formValidation({
            err: {
                container: 'tooltip'
            },
            icon: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                salaryItemName: {
                    validators: {
                        notEmpty: {
                            message: '请填写必填项目'
                        }
                    }
                },
                displayOrder: {
                    validators: {
                        greaterThan: {
                            message: '请输入大于等于0的数值',
                            value: 0
                        },
                        notEmpty:{
                            message: '请填写必填项目'
                        }
                    }
                }
                
            }
        })
        .on('success.form.fv', function(e){
            e.preventDefault();
            save();
        });

    })
       
    function relateTaxRate(){
    	if($('#taxRule').val() == 2){
    		$('#taxRateId').attr('disabled', false);
    	}else{
    		$('#taxRateId').val('');
    		$('#taxRateId').attr('disabled', true);
    	}
    }
    
    function loadChangeDefItem(){
    	$('.col-change-label').html('');
    	$('.p-change-content').html('');
    
    	var id = $('#salaryChangeDefId').val();
    	if(!id){
    		return;
    	}
    	
    	$.ajax({
			url : "salaryChangeDef/loadSalaryChangeDefItem",
			data : {id: id},
			dataType:'json',
			type : 'post',
			success : function(result) {
				var detailNames = '';
				if(result && result.data){
					$.each(result.data,function(){
						detailNames += "、" + this.displayName;
					});
				}
				$('.col-change-label').html($('#salaryChangeDefId option:selected').text() + "：");
				$('.p-change-content').html(detailNames ? detailNames.substring(1) : "");
				
			}
		});
    }

	function save(){
		//校验
		
		$.ajax({
			url : "salaryItem/editSalaryItem",
			data : $("#itemForm").serializeObject(),
			dataType:'json',
			type : 'post',
			success : function(message) {
				if(message.success){
					swal({
	                    title: "保存成功!",
	                    text: "",
	                    type: "success",
	    				confirmButtonText: "确定"
	                },function(){
                        window.location.href="salaryItem/toListSalaryItem";
                    });
                     
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
	}
    </script>
    <script>

    
</script>

</body>
</html>