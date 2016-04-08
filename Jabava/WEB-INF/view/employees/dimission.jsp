<%@ page contentType="text/html; charset=utf-8" %>
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
        <div class="normalheader transition animated fadeIn small-header">
            <div class="hpanel">
        <div class="panel-body">
            <div id="hbreadcrumb" class="pull-right m-t-lg">
                <ol class="hbreadcrumb breadcrumb">
                    <li><a href="to_index?jump=1">首页</a></li>
                    <li>
                        <span>员工信息</span>
                    </li>
                    <li class="active">
                        <span></span>
                    </li>
                </ol>
            </div>
            <h2 class="font-light m-b-xs">
                员工信息
            </h2>
            <small>待定</small>
        </div>
    </div>
</div>

<!-- 放主要内容 -->

<!--离职管理-->
<div class="content animate-panel">
    <div class="row ">
        <div class="hpanel hblue">
              <!--引入员工信息导航 开始--> 
              <jsp:include flush="true" page="employee_nav.jsp"></jsp:include>
              <!--引入员工信息导航 结束-->
            <div class="panel-heading hbuilt">
               <div class="panel-tools">
            	<button type="button" class="btn btn-success btn-xs sb_xx" data-action-motive="addNew"><span class="bold">继续添加</span></button>
                <button type="button" class="btn btn-success btn-xs sb_xx"><span class="bold">社保信息&gt;&gt;</span></button> 
               </div>
              <h4>离职管理</h4>
            </div>
<div class="panel-body">
        <div class="lizhi-form">
       <input type="hidden" id="personId" value="${personId}" />
            <script id="lizhi" type="text/html">
                {{each list}}
                <form role="form" method="post" class="form-horizontal col-lg-12 col-md-12 {{if $index>0}} jianju borders{{/if}}" id="form_{{$value.leftId}}">
					<div class="text-right action-group">
                        <a href="javascript://" class="pe-7s-note pe-2x" data-action-motive="edit"><span class="sr-only">修改</span></a>
                        <a href="javascript://" class="pe-7s-trash pe-2x" data-action-motive="del" data-action-id="{{$value.leftId}}"><span class="sr-only">删除</span></a>
                    </div>
                    <input type="hidden" name="leftId" value="{{$value.leftId}}">
                    <div class="col-md-4 col-lg-4">
                        <div class="form-group">
                            <label for="" class="control-label col-lg-6 col-md-6">离职时间：</label>
                            <div class="col-md-6 col-lg-6">
                                <div class="input-group-static"  >
                                    <p class="form-control-static" data-fill-name="leftDate">
                                    {{$value.leftDate1}}</p>
                                </div>
                                <div class="input-group date" data-date-format="yyyy-mm-dd">
                                    <input type="text" class="form-control" name="leftDate1" value="{{$value.leftDate1}}" required="required">
                                    <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4 col-lg-4">
                        <div class="form-group">
                            <label for="" class="control-label col-lg-6 col-md-6">薪资结算日：</label>
                            <div class="col-md-6 col-lg-6">
                                <div class="input-group-static">
                                    <p class="form-control-static"  data-fill-name="salarySettleDate">{{$value.salarySettleDate1}}</p>
                                </div>
                                <div class="input-group date"  data-date-format="yyyy-mm-dd">
                                    <input type="text" class="form-control"  name="salarySettleDate1" value="{{$value.salarySettleDate1}}" required="required">
                                    <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4 col-lg-4">
                        <div class="form-group">
                            <label for="" class="control-label col-lg-6 col-md-6">离职原因：</label>
                            <div class="col-md-6 col-lg-6">
                                <div class="input-group-static">
                                    <p class="form-control-static" data-fill-name="leftCause">{{$value.leftCause}}</p>
                                </div>
                                <select class="form-control" name="leftCause">
                                    {{if $value.leftCause=="辞职"}}
                                    <option value="辞职" selected="selected">辞职</option>
									<option value="辞退">辞退</option>
                                    <option value="协议离职">协议离职</option>
                               		<option value="合同到期终止">合同到期终止</option>
                               		<option value="其他">其他</option>
									{{else if $value.leftCause=="辞退"}}
                                    <option value="辞职">辞职</option>
                                    <option value="辞退" selected="selected">辞退</option>
								    <option value="协议离职">协议离职</option>
                               		<option value="合同到期终止">合同到期终止</option>
                               		<option value="其他">其他</option>
									{{else if $value.leftCause=="协议离职"}}
                                    <option value="辞职">辞职</option>
                                    <option value="辞退">辞退</option>
								    <option value="协议离职"  selected="selected">协议离职</option>
                               		<option value="合同到期终止">合同到期终止</option>
                               		<option value="其他">其他</option>
                                    {{else if $value.leftCause=="合同到期终止"}}
                                    <option value="辞职">辞职</option>
									<option value="辞退">辞退</option>
								    <option value="协议离职">协议离职</option>
                                  <option value="合同到期终止" selected="selected">合同到期终止</option>
									<option value="其他">其他</option>
									 {{else if $value.leftCause=="其他"}}
                                    <option value="辞职">辞职</option>
									<option value="辞退">辞退</option>
								    <option value="协议离职">协议离职</option>
                                    <option value="合同到期终止">合同到期终止</option>
									<option value="其他"  selected="selected">其他</option>
                                    {{/if}}
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-12 col-lg-12">
                        <div class="form-group">
                            <label for="" class="control-label col-lg-2 col-md-2">备注：</label>
                            <div class="col-md-10 col-lg-10">
                                <div class="input-group-static" data-fill-name="memo">
                                    <p class="form-control-static">{{$value.memo}}</p>
                                </div>
                                <input type="text" class="form-control" name="memo" value="{{$value.memo}}">
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-12 col-md-12 text-center form-action">
                        <button type="button" class="btn btn-info" data-action-motive="save" onclick="save({{$value.leftId}})" >保存</button>
                        <button type="button" class="btn btn-default" data-action-motive="cancel">取消</button>
                    </div>
                </form>
                {{/each}}
            </script>
        </div> 
       
          
                <form role="form" method="post" class="searchs-form form-horizontal lizhi_gl lizhi_gl_form col-md-12 col-lg-12 hidden" id="create_new"> 
                     <input type="hidden" name="personId" value="${personId}" id="personId">
                    <!--离职时间-->
                    <div class="col-md-4 col-lg-4">
                        <div class="form-group">
                            <label for="" class="control-label col-lg-6 col-md-6">离职时间：</label>
                            <div class="col-md-6 col-lg-6">
                                <div class="input-group date" data-date-format="yyyy-mm-dd">
                                    <input type="text" class="form-control datepicker" id="leftDate" name="leftDate1" required="required">
                                    <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!--薪资结算日-->
                    <div class="col-md-4 col-lg-4">
                        <div class="form-group">
                            <label for="" class="control-label col-lg-6 col-md-6">薪资结算日：</label>
                            <div class="col-md-6 col-lg-6">
                                <div class="input-group date" data-date-format="yyyy-mm-dd">
                                    <input type="text" class="form-control datepicker" id="salarySettleDate" name="salarySettleDate1" required="required">
                                    <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                </div>
                            </div>
                        </div>
                    </div>                    
                    <!--离职原因-->
                    <div class="col-md-4 col-lg-4">
                        <div class="form-group">
                            <label for="" class="control-label col-lg-6 col-md-6">离职原因：</label>
                            <div class="col-md-6 col-lg-6">
                                <select class="form-control m-b" name="leftCause" id="leftCause" style=" margin-bottom:0;">
                                    <option value="辞退">辞退</option>
                                    <option value="辞职">辞退</option>
                                    <option value="协议离职">协商解除</option>
                                    <option value="合同到期终止">合同到期终止</option>
                                    <option value="其他">其他</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <!--备注-->
                    <div class="col-md-12 col-lg-12">
                        <div class="form-group">
                            <label for="" class="control-label col-lg-2 col-md-2">备注：</label>
                            <div class="col-md-10 col-lg-10">
                                <textarea class="form-control" id="memo" name="memo"></textarea>
                            </div>
                        </div>
                    </div>                    
                    <!--保存 删除-->
                    <center style=" clear:both; padding-top:30px;">
                        <button type="button" class="btn btn-info" onclick="add()" >保存</button>
                        <button type="button" class="btn btn-danger demo4">删除</button>
                    </center>
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
<script src="static/bootstrap/vendor/bootstrap-datepicker-master/dist/js/bootstrap-datepicker.min.js"></script>
<!-- for datatable -->
<script src="static/bootstrap/vendor/datatables/media/js/jquery.dataTables.min.js"></script>
<script src="static/bootstrap/vendor/datatables_plugins/integration/bootstrap/3/dataTables.bootstrap.min.js"></script>
<!-- alert -->
<script src="static/bootstrap/vendor/sweetalert/lib/sweet-alert.min.js"></script>

<!-- App scripts -->
<script src="static/bootstrap/scripts/homer.js"></script>
<script src="static/js/template.js"></script>
<script src="static/js/common.js"></script>


<script>
var personId = $("#personId").val();
templateFillData('lizhi', $('.lizhi-form'), 'employee/dimissionInfo?personId='+personId,'employee/deleteLeft.do?leftId=');

$('[data-action-motive=addNew]').on('click', function(event){
	$('#create_new').removeClass('hidden');
});

//新增
function add(){
	$.ajax({
		type : "POST",
		url : "employee/addLeft.do",
		data : $("#create_new").serialize(),
		dataType : "json",
		success : function(data) {
			alert(data.msg);
			window.location.reload();
			
		} 
	});
}

//修改
function save(id){
	$.ajax({
		type : "POST",
		url : "employee/updateLeft.do",
		data : $("#form_"+id).serialize(),
		dataType : "json",
		success : function(data) {
			alert(data.msg);
			window.location.reload();
		} 
	});


}

<!--删除-->
function deleteBtnInfo(){
   $('.demo4').click(function () {
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
                swal("删除成功!", "该信息已经被删除.", "success");
            } else {
                swal("已取消", "信息未删除。你这逗我玩呢 :)", "error");
            }
        });
});
}
<!--取消效果-->
$(".guanbi").click(function(){

  $("#myModal7").modal('hide');
  $("#myModal8").modal('hide');
  $("#myModal9").modal('hide');

})
</script>
<!--日历-->
<script>

$(function(){
   deleteBtnInfo();
   $('#datepicker').datepicker();
   $("#datepicker").on("changeDate", function(event) {
    $("#my_hidden_input").val(
        $("#datepicker").datepicker('getFormattedDate')
        )
});

   $('#datapicker2').datepicker();
   $('.input-group.date').datepicker({ });
   $('.input-daterange').datepicker({ });
        });

</script>
</body>
</html>