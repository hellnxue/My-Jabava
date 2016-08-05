<%@ page contentType="text/html; charset=utf-8" %>
<%@page import="com.jabava.utils.RequestUtil"%>
<!DOCTYPE html>
<html>
<head>
	<base href="${pageContext.request.contextPath}/">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- Page title -->
    <title>离职管理</title>
    <jsp:include flush="true" page="../common/styles.jsp"></jsp:include>
    <link rel="stylesheet" href="static/js/plugins/form.validation/css/formValidation.css">
    <link rel="stylesheet" href="static/css/user_bata.css">
    <link rel="stylesheet" href="static/css/style_1.css" media="screen">
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

    <!-- 放主要内容  开始-->
    <!-- Main Wrapper -->
    <div id="wrapper" class="min-h">

<!-- 放主要内容 -->

<!--离职管理-->
<div class="content animate-panel">
    <div class="row ">
        <div class="hpanel">
        <div class="panel-heading">
            <h4 class="text-center font-bold">
              <button onclick="toEmployeeList()" class="btn btn-default btn-sm btn-absolute" type="button">　返回　</button>
              <span>员工资料</span>
            </h4>
        </div>
              <!--引入员工信息导航 开始--> 
              <jsp:include flush="true" page="employee_nav.jsp"><jsp:param value="dimission" name="type"/></jsp:include>
              <!--引入员工信息导航 结束-->
<div class="panel-body">
    <div class="panel-heading">
          <h4 class="text-center font-bold">离职管理</h4>
     </div>
        <div class="lizhi-form">
       <input type="hidden" id="personId" value="${personId}" />
            <script id="lizhi" type="text/html">
                {{each list}}
                <form role="form" method="post" class="form-horizontal col-lg-12 col-md-12 {{if $index>0}} jianju borders{{/if}}" id="form_{{$value.dimissionId}}" data-form-validator="updateDimission" data-form-id="{{$value.dimissionId}}">
					<div class="text-right action-group">
                        <a href="javascript://" class="pe-7s-note pe-2x" data-action-motive="edit"><span class="sr-only">修改</span></a>
                        <!--<a href="javascript://" class="pe-7s-trash pe-2x"  onclick="deleteBtnInfo({{$value.dimissionId}})" ><span class="sr-only">删除</span></a>-->
                    </div>
                    <input type="hidden" name="dimissionId" value="{{$value.dimissionId}}">
                    <div class="col-md-6 col-lg-6">
                        <div class="form-group">
                            <label for="" class="control-label col-lg-6 col-md-6">离职时间：</label>
                            <div class="col-md-6 col-lg-6">
                                <div class="input-group-static"  >
                                    <p class="form-control-static" data-fill-name="dimissionDate">
                                    {{if $value.dimissionDate}}
                                    {{$value.dimissionDate.substr(0,10)}}
                                    {{/if}}
                                    </p>
                                </div>
                                <div class="form-required">
                                    <div class="input-group date" data-date-format="yyyy-mm-dd">
                                        <input type="text" class="form-control" name="dimissionDate" 
                                        value="{{if $value.dimissionDate}}{{$value.dimissionDate.substr(0,10)}}{{/if}}" required="required">
                                        <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6 col-lg-6">
                        <div class="form-group">
                            <label for="" class="control-label col-lg-6 col-md-6">薪资结算日：</label>
                            <div class="col-md-6 col-lg-6">
                                <div class="input-group-static">
                                    <p class="form-control-static"  data-fill-name="salarySettleDate">
                                    {{if $value.salarySettleDate}}
                                    {{$value.salarySettleDate.substr(0,10)}}
                                    {{/if}}
                                    </p>
                                </div>
                                <div class="form-required">
                                    <div class="input-group date"  data-date-format="yyyy-mm-dd">
                                        <input type="text" class="form-control"  name="salarySettleDate" 
                                        value="{{if $value.salarySettleDate}}{{$value.salarySettleDate.substr(0,10)}}{{/if}}" required="required">
                                        <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6 col-lg-6">
                        <div class="form-group">
                            <label for="" class="control-label col-lg-6 col-md-6">离职原因：</label>
                            <div class="col-md-6 col-lg-6">
                                <div class="input-group-static">
                                    <p class="form-control-static" data-fill-name="dimissionCause">{{$value.dimissionCauseShow}}</p>
                                </div>
                                <div class="form-required">
                                    <select class="form-control" name="dimissionCause">
                                        {{if $value.dimissionCause=="1"}}
                                        <option value="1" selected="selected">辞职</option>
    									<option value="2">辞退</option>
                                        <option value="3">协议离职</option>
                                   		<option value="4">合同到期终止</option>
                                   		<option value="5">其他</option>
    									{{else if $value.dimissionCause=="2"}}
                                        <option value="1">辞职</option>
                                        <option value="2" selected="selected">辞退</option>
    								    <option value="3">协议离职</option>
                                   		<option value="4">合同到期终止</option>
                                   		<option value="5">其他</option>
    									{{else if $value.dimissionCause=="3"}}
                                        <option value="1">辞职</option>
                                        <option value="2">辞退</option>
    								    <option value="3"  selected="selected">协议离职</option>
                                   		<option value="4">合同到期终止</option>
                                   		<option value="5">其他</option>
                                        {{else if $value.dimissionCause=="4"}}
                                        <option value="1">辞职</option>
    									<option value="2">辞退</option>
    								    <option value="3">协议离职</option>
                                      <option value="4" selected="selected">合同到期终止</option>
    									<option value="5">其他</option>
    									 {{else if $value.dimissionCause=="5"}}
                                        <option value="1">辞职</option>
    									<option value="2">辞退</option>
    								    <option value="3">协议离职</option>
                                        <option value="4">合同到期终止</option>
    									<option value="5"  selected="selected">其他</option>
                                        {{/if}}
                                    </select>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6 col-lg-6">
                        <div class="form-group">
                            <label for="" class="control-label col-lg-6 col-md-6">备注：</label>
                            <div class="col-md-6 col-lg-6">
                                <div class="input-group-static" data-fill-name="memo">
                                    <p class="form-control-static">{{$value.memo}}</p>
                                </div>
                                <input type="text" class="form-control" name="memo" value="{{$value.memo}}">
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-12 col-md-12 text-center form-action">
                     <button type="submit" class="btn btn-info" data-action-motive="save" data-id="{{$value.dimissionId}}" >保存</button>
                        <button type="button" class="btn btn-default" data-action-motive="cancel">取消</button>
                    </div>
                </form>
                {{/each}}
            </script>
        </div> 
       
          
                <form role="form" method="post" class="searchs-form form-horizontal lizhi_gl lizhi_gl_form col-md-12 col-lg-12 hidden" id="create_new" data-form-validator="addDimission"> 
                     <input type="hidden" name="personId" value="${personId}" >
                    <!--离职时间-->
                    <div class="col-md-6 col-lg-6">
                        <div class="form-group">
                            <label for="" class="control-label col-lg-6 col-md-6">离职时间：</label>
                            <div class="col-md-6 col-lg-6 form-required">
                                <div class="input-group date" data-date-format="yyyy-mm-dd">
                                    <input type="text" class="form-control datepicker" id="dimissionDate" name="dimissionDate" required="required">
                                    <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!--薪资结算日-->
                    <div class="col-md-6 col-lg-6">
                        <div class="form-group">
                            <label for="" class="control-label col-lg-6 col-md-6">薪资结算日：</label>
                            <div class="col-md-6 col-lg-6 form-required">
                                <div class="input-group date" data-date-format="yyyy-mm-dd">
                                    <input type="text" class="form-control datepicker" id="salarySettleDate" name="salarySettleDate" required="required">
                                    <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                </div>
                            </div>
                        </div>
                    </div>                    
                    <!--离职原因-->
                    <div class="col-md-6 col-lg-6">
                        <div class="form-group">
                            <label for="" class="control-label col-lg-6 col-md-6">离职原因：</label>
                            <div class="col-md-6 col-lg-6 form-required">
                                <select class="form-control m-b" name="dimissionCause" id="dimissionCause" style=" margin-bottom:0;">
                                    <option value="1">辞退</option>
                                    <option value="2">辞退</option>
                                    <option value="3">协商解除</option>
                                    <option value="4">合同到期终止</option>
                                    <option value="5">其他</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <!--备注-->
                    <div class="col-md-6 col-lg-6">
                        <div class="form-group">
                            <label for="" class="control-label col-lg-6 col-md-6">备注：</label>
                            <div class="col-md-6 col-lg-6">
                                <input class="form-control" id="memo" name="memo">
                            </div>
                        </div>
                    </div>                    
                    <!--保存 删除-->
                    <div class="col-lg-12 col-md-12 text-right form-action">
                        <% if(RequestUtil.hasPower("roster_dimission_do")){ %>
                        <button type="submit" class="btn btn-success" data-action-motive="dimission">确定办理</button>
                        <% } %>
                    </div>
                </form>
    </div>
</div>
</div>
</div>
<!--离职管理 end-->

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
<script src="static/bootstrap/vendor/moment/moment.js"></script>
<script src="static/bootstrap/vendor/xeditable/bootstrap3-editable/js/bootstrap-editable.min.js"></script>
<script src="static/bootstrap/vendor/select2-3.5.2/select2.min.js"></script>
<script src="static/bootstrap/vendor/bootstrap-touchspin/dist/jquery.bootstrap-touchspin.min.js"></script>
  <script src="static/bootstrap/vendor/bootstrap-datepicker-master/dist/js/bootstrap-datepicker.js"></script>
<script src="static/bootstrap/vendor/bootstrap-datepicker-master/dist/locales/bootstrap-datepicker.zh-CN.min.js"></script>
<script src="static/bootstrap/vendor/bootstrap-datepicker-master/dist/js/bootstrap-datepicker.js"></script>
<!-- for datatable -->
<script src="static/bootstrap/vendor/datatables/media/js/jquery.dataTables.min.js"></script>
<script src="static/bootstrap/vendor/datatables_plugins/integration/bootstrap/3/dataTables.bootstrap.min.js"></script>
<!-- alert -->
<script src="static/bootstrap/vendor/sweetalert/lib/sweet-alert.min.js"></script>

<script src="static/js/plugins/form.validation/js/formValidation.js"></script>
<script src="static/js/plugins/form.validation/js/framework/bootstrap.js"></script>
<script src="static/js/plugins/form.validation/js/language/zh_CN.js"></script> 
<!-- App scripts -->
<script src="static/bootstrap/scripts/homer.js"></script>
<script src="static/js/template.js"></script>
<script src="static/js/yuangong.js"></script>
<script src="static/js/common.js"></script>


<script>
var personId = $("#personId").val();
var addURL="employee/addDimission"; 
var updateURL = "employee/updateDimission";
var addFormSelector=""
var containerID= $('.lizhi-form');
var templateID="lizhi";
var listURL='employee/dimissionInfo?personId='+personId;
var delsURL='employee/deleteDimission?dimissionId=';
templateFillData('lizhi', $('.lizhi-form'), 'employee/dimissionInfo?personId='+personId,'employee/deleteDimission?dimissionId=');

$('[data-action-motive=addNew]').on('click', function(event){
	$('#create_new').removeClass('hidden');
});

//新增
function add(id){
	$.ajax({
		type : "POST",
		url : "employee/addDimission",
		data : $("#"+id).serialize(),
		dataType : "json",
		success : function(data) {
			 //alert(data.msg);
			 swal({
                title: "新增成功!",
                type: "success"}, function(){
			     window.location.reload();
             });
			
		} 
	});
}

//修改
function save(id){
	$.ajax({
		type : "POST",
		url : "employee/updateDimission",
		data : $("#form_"+id).serialize(),
		dataType : "json",
		success : function(data) {
			// alert(data.msg);
			 swal("更新成功!", " ", "success");
			window.location.reload();
		} 
	});


}

//删除
function deleteBtnInfo(id){
  
    swal({
        title: "确定要删除该条离职信息吗?",
        text: "注意：用户删除后将不可恢复!",
        type: "warning",
        showCancelButton: true,
        confirmButtonColor: "#DD6B55",
        confirmButtonText: "是,请删除该信息!",
        cancelButtonText: "不, 放弃此操作!",
        closeOnConfirm: false,
        closeOnCancel: false },
        function (isConfirm) {
            if (isConfirm) {
            	$.ajax({
            		type : "POST",
            		url : "employee/deleteDimission",
            		data :{dimissionId:id},
            		dataType : "json",
            		success : function(data) {
            			 swal("删除成功!", "该信息已经被删除.", "success");
            			 window.location.reload();
            		} 
            	});
               
            } else {
                swal("已取消", "信息未删除。你这逗我玩呢 :)", "error");
            }
        });
}
//取消效果
$(".guanbi").click(function(){

  $("#myModal7").modal('hide');
  $("#myModal8").modal('hide');
  $("#myModal9").modal('hide');

})

//校验
var validateOptions = {
      err: {
          container: 'tooltip'
      },
      icon: {
          valid: 'glyphicon glyphicon-ok',
          invalid: 'glyphicon glyphicon-remove',
          validating: 'glyphicon glyphicon-refresh'
      },
      locale: 'zh_CN',
      
      fields: {
          dimissionDate: {
              validators: {
                  notEmpty: {
                      message: '请填写必填项目'
                  },
                  date: {
                    format: 'YYYY-MM-DD',
                    message: '该日期是无效的'
                  }
              }
          },
          salarySettleDate: {
              validators: {
                  notEmpty: {
                      message: '请填写必填项目'
                  },
                  date: {
                    format: 'YYYY-MM-DD',
                    message: '该日期是无效的'
                  }
              }
          },
          dimissionCause: {
              validators: {
                  notEmpty: {
                      message: '请填写必填项目'
                  }
              }
          }
      }
  };

//日历
$(function(){
   $('.input-group.date')
        .datepicker({
            format: "yyyy-mm-dd",
            autoclose: true
        })
        .on('changeDate', function(e){
            var getEleName = $(e.target).find(':text').attr('name');
            $('[data-form-validator]').formValidation('revalidateField', getEleName);
        });
});

</script>
</body>
</html>