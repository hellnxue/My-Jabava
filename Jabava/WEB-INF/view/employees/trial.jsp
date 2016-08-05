<%@ page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html>
<head>
	<base href="${pageContext.request.contextPath}/">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <!-- Page title -->
    <title>试用情况</title>
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
    <div class="content animate-panel">
      <div class="row">
        <div class="col-lg-12">
          <div class="hpanel">
            <div class="panel-heading">
              <h4 class="text-center font-bold">员工资料
                <a href="" type="button" class="btn btn-default btn-sm btn-absolute">返　回</a>
              </h4>
            </div>
            <!-- 放主要内容 -->
            <!--引入员工信息导航 开始--> 
            <jsp:include flush="true" page="employee_nav.jsp">
            <jsp:param value="trial" name="type"/>
          </jsp:include>
          <!--引入员工信息导航 结束-->



          <!--试用情况-->
          <div class="panel-body">
            <div class="panel-heading">
                  <h4 class="text-center font-bold">试用情况</h4>
            </div>
            <div class="row m-b-lg">
            <input type="hidden" id="personId" value="${personId}" />
            <script type="text/html" id="shiyongss">
              {{each list}}

                  <div class="col-md-12 page-header m-t-md{{if $index == list.length-1}} no-borders{{/if}} ">
                      <div class="panel-tools action-group">
                        <ul class="list-inline">
                           <li>
                          <a href="javascript://" data-action-motive="hideOrshow" data-action-id="{{$value.trialId}}"><i class="pe-7s-angle-down-circle pe-2x text-info"></i><span class="sr-only">隐藏</span></a>
                          </li>
                          <li>
                          <a href="javascript://" data-action-motive="edit" data-action-id="{{$value.trialId}}"><i class="pe-7s-note pe-2x text-info">
                          </i><span class="sr-only">修改</span></a>
                          </li>
                          <li>
                            <a href="javascript://" data-action-motive="del" data-action-id="{{$value.trialId}}"><i class="pe-7s-trash pe-2x text-info"></i><span class="sr-only">删除</span></a>
                          </li>
                        </ul>
                      </div>
                      <ul class="list-inline">
                        <li><p class="form-control-static text-info font-bold">
                            {{if $value.trialStartDate!=null}}
                            {{$value.trialStartDate.substr(0,10)}}
                            {{/if}}
                            &nbsp;</p></li>
                        <li class="m-r"><p class="form-control-static text-info font-bold">至</p></li>
                        <li><p class="form-control-static text-info font-bold">
                                {{if $value.trialEndDate!=null}}
                                {{$value.trialEndDate.substr(0,10)}}
                                {{/if}}
                                &nbsp;</p></li>
                        <li> <p class="form-control-static text-info font-bold">{{$value.positiveTypeShow}}&nbsp;</p></li>    
                     </ul>
                  </div>

              <form role="form" data-form="{{$value.trialId}}" method="post" class="searchs-form form-horizontal col-md-12 col-lg-12  {{if $index>0}} jianju borders{{/if}}">
                <input type="hidden" name="trialId" value="{{$value.trialId}}">

                 <!--开始时间-->
                <div class="row">
                  <div class="col-md-6 col-lg-6">
                  <div class="col-md-12 col-lg-12">
                    <div class="form-group">
                      <label for="" class="control-label col-md-4 col-lg-4">开始时间：</label>
                      <div class="col-md-8 col-lg-8">
                        <div class="input-group-static">
                          <p class="form-control-static">
                            {{if $value.trialStartDate!=null}}
                            {{$value.trialStartDate.substr(0,10)}}
                            {{/if}}
                            &nbsp;</p>
                          </div>
                          <div class="form-required">
                            <div class="input-group date">
                              <input type="text" class="form-control" required="required" name="trialStartDate" value="{{if $value.trialStartDate!=null}}{{$value.trialStartDate.substr(0,10)}}{{/if}}">
                              <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                      <!--转正时间-->
                      <div class="col-md-12 col-lg-12">
                        <div class="form-group">
                          <label for="" class="control-label col-md-4 col-lg-4">转正时间：</label>
                          <div class="col-md-8 col-lg-8">
                            <div class="input-group-static">
                              <p class="form-control-static">
                                {{if $value.trialEndDate!=null}}
                                {{$value.trialEndDate.substr(0,10)}}
                                {{/if}}
                              &nbsp;</p>
                            </div>
                            <div class="form-required">
                              <div class="input-group date">
                                <input type="text" required="required" class="form-control" name="trialEndDate" value="{{if $value.trialEndDate!=null}}{{$value.trialEndDate.substr(0,10)}}{{/if}}">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                              </div>
                            </div>
                          </div>
                        </div>
                      </div>

                        <!--转正性质-->
                        <div class="col-md-12 col-lg-12">
                          <div class="form-group">
                            <label for="" class="control-label col-md-4 col-lg-4">转正性质：</label>
                            <div class="col-md-8 col-lg-8">
                              <div class="input-group-static">
                                <p class="form-control-static">
                                  {{$value.positiveTypeShow}}
                                  &nbsp;</p>
                                </div>
                                <div class="form-required">
                                  <select class="form-control" name="positiveType">
                                    <option value="1"{{if $value.positiveType=='1'}} selected="selected"{{/if}}>提前</option>
                                    <option value="2"{{if $value.positiveType=='2'}} selected="selected"{{/if}}>按期</option>
                                    <option value="3"{{if $value.positiveType=='3'}} selected="selected"{{/if}}>延长</option>
                                  </select>
                                </div>
                              </div>
                            </div>
                          </div>
                    </div>
                 
                    <!--考评结果-->
                    <div class="col-md-6 col-lg-6">
                    <div class="col-md-12 col-lg-12">
                      <div class="form-group">
                        <label for="" class="control-label col-md-4 col-lg-4">考评结果：</label>
                        <div class="col-md-8 col-lg-8">
                          <div class="input-group-static">
                            <p class="form-control-static">
                              {{$value.evaluationResult}}
                              &nbsp;</p>
                          </div>
                          <div class="form-required">
                            <input type="text" required="required" class="form-control" name="evaluationResult" value="{{$value.evaluationResult}}">
                          </div>
                        </div>
                      </div>
                    </div>
                    <!--试用备注-->
                  <div class="col-md-12 col-lg-12">
                      <div class="form-group">
                        <label for="" class="control-label col-md-4 col-lg-4">试用备注：</label>
                        <div class="col-md-8 col-lg-8">
                          <div class="input-group-static">
                            <p class="form-control-static">
                              {{$value.memo}}
                              &nbsp;</p>
                            </div>
                            <textarea class="form-control" name="memo" rows="3">{{$value.memo}}</textarea>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>

                          <div class="col-lg-12 col-md-12 text-center form-action">
                            <button type="submit" class="btn btn-info" data-action-motive="save">保存</button>
                            <button type="button" class="btn btn-default" data-action-motive="cancel">取消</button>
                          </div>
                        </form>
                        {{/each}}
                      </script>
                      <div class="hpanel" id="shiyong_div" data-form-target="content">
                      
                      </div>
                      <div id="shiyong_body">
                        <form role="form" method="post"  data-form-type="base"
                         class="form-horizontal col-md-12 col-lg-12 qing_kuang qing_kuang_form hidden" id="create_new"> 
                          <!--开始时间-->
                          <div class="row m-b-lg">
                           <div class="col-md-6 col-lg-6">
                            <div class="col-md-12 col-lg-12">
                              <div class="form-group">
                                <label for="" class="control-label col-md-4 col-lg-4">开始时间：</label>
                                <div class="col-md-8 col-lg-8 form-required">
                                  <div class="input-group date">
                                    <input type="text" class="form-control" name="trialStartDate" value="" required="required">
                                    <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                  </div>
                                </div>
                              </div>
                            </div>

                            <!--转正时间-->
                         
                              <div class="col-md-12 col-lg-12">
                                <div class="form-group">
                                  <label for="" class="control-label col-md-4 col-lg-4">转正时间：</label>
                                  <div class="col-md-8 col-lg-8 form-required">
                                    <div class="input-group date">
                                      <input type="text" class="form-control" name="trialEndDate" value="" required="required">
                                      <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                    </div>
                                  </div>
                                </div>
                              </div>

                              <!--转正性质-->
                              <div class="col-md-12 col-lg-12">
                                <div class="form-group">
                                  <label for="" class="control-label col-md-4 col-lg-4">转正性质：</label>
                                  <div class="col-md-8 col-lg-8 form-required">
                                    <select class="form-control" name="positiveType">                                    
                                       <option value="1">提前</option>
                                      <option value="2">按期</option>
                                       <option value="3">延长</option>
                                    </select>
                                  </div>
                                </div>
                              </div>
                         </div>
                          <!--考评结果-->
                         <div class="col-md-6 col-lg-6">
                            <div class="col-md-12 col-lg-12">
                              <div class="form-group">
                                <label for="" class="control-label col-md-4 col-lg-4">考评结果：</label>
                                <div class="col-md-8 col-lg-8 form-required">
                                  <input type="text" class="form-control" name="evaluationResult" value="" required="required">
                                </div>
                              </div>
                            </div>
                             <!--试用备注-->
                            <div class="col-md-12 col-lg-12">
                              <div class="form-group">
                                <label for="" class="control-label col-md-4 col-lg-4">试用备注：</label>
                                <div class="col-md-8 col-lg-8">
                                  <textarea class="form-control" name="memo" rows="3"></textarea>
                                </div>
                              </div>
                            </div>
                         </div>                        
                          <div class="col-lg-12 col-md-12 text-right">
                            <button type="button" class="btn btn-info btn-success" >保存</button>
                            <button type="button" class="btn btn-default" >取消</button>
                          </div>
                        </form>
                        </div>
                      </div>
                    <!--添加--> 
                    <div class="text-center row">
                      <a class="adds" data-form-action="add"><i class=" pe-7s-plus pe-5x text-muted"></i><br><span class="text-muted">添加新记录</span></a>
                    </div>
                    </div> 
                  </div>
                </div>
              </div>
            </div>      
            </div>

            <!--试用情况 end-->   



            <!-- Footer-->
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
<script src="static/js/commonH3.js"></script>
<script type="text/javascript">
var personId = $("#personId").val(); 
var addURL="employee/addTrial";
var listURL="employee/trialInfo?personId="+personId;
var delsURL="employee/delTrial?trialId=";
var updateURL="employee/updateTrial";
var addFormSelector="[name=add_new_form]";
var initFormSelector="#create_new";
var templateID="shiyongss";
var containerID=$('#shiyong_div');
  templateFillData(templateID, containerID, listURL,delsURL);

//新增
/* function addTrial(obj){
	 var id = obj.form.id;
	
	$.ajax({
		type : "POST",
		url : "employee/addTrial",
		data : $("#"+id).serialize(),
		dataType : "json",
		success : function(data) {
			alert(data.msg);
			window.location.reload();
			
		} 
	});
} */
//修改
/* function updateTrial(id){
	$.ajax({
		type : "POST",
		url : "employee/updateTrial",
		data : $("#form_"+id).serialize(),
		dataType : "json",
		success : function(data) {
			alert(data.msg);
			window.location.reload();
		} 
	});
}
 */

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
          trialStartDate: {
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
          evaluationResult: {
              validators: {
                  notEmpty: {
                      message: '请填写必填项目'
                  }
              }
          },
          trialEndDate: {
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
          positiveType: {
              validators: {
                  notEmpty: {
                      message: '请填写必填项目'
                  }
              }
          }
      }
  };

    //删除
     function deleteBtnInfo(){
	  $('.demo4').click(function () {
            swal({
                        title: "确定要删除此用户吗?",
                        text: "注意：用户删除后将不可登录!",
                        type: "warning",
                        showCancelButton: true,
                        confirmButtonColor: "#DD6B55",
                        confirmButtonText: "是,请删除该用户!",
                        cancelButtonText: "不, 放弃此操作!",
                        closeOnConfirm: false,
                        closeOnCancel: false },
                    function (isConfirm) {
                        if (isConfirm) {
                            swal("删除成功!", "该用户已经被删除.", "success");
                        } else {
                            swal("已取消", "用户未删除。你这逗我玩呢 :)", "error");
                        }
                    });
        });
	}
  //取消效果
  $(".guanbi").click(function(){
	  
	 $("#myModal7").modal('hide');
	 $("#myModal8").modal('hide');
	 $("#myModal9").modal('hide');
	  
  })

//日历
        $(function(){
			deleteBtnInfo();
            $('#datepicker').datepicker();
            $("#datepicker").on("changeDate", function(event) {
                $("#my_hidden_input").val(
                        $("#datepicker").datepicker('getFormattedDate')
                )
            });

            $('#datapicker2').datepicker();
            $('.input-group.date').datepicker({
              format: "yyyy-mm-dd"
             });
            $('.input-daterange').datepicker({ });
        });

//删除
    $(function () {
        $('.demo4').click(function () {
            swal({
                        title: "确定要作废该协议吗?",
                        type: "warning",
                        showCancelButton: true,
                        confirmButtonColor: "#DD6B55",
                        confirmButtonText: "是,请作废该协议!",
                        cancelButtonText: "不, 放弃此操作!",
                        closeOnConfirm: false,
                        closeOnCancel: false },
                    function (isConfirm) {
                        if (isConfirm) {
                            swal("作废成功!", "该协议已经被作废.", "success");
                        } else {
                            swal("已取消", "该协议未作废。你这逗我玩呢 :)", "error");
                        }
                    });
        });

    

    });

  $(".guanbi").click(function(){
	  
	 $("#myModal7").modal('hide');
	  
  })


 
//全部导出
	  $(".jxdc").click(function(){
		 $(".modal-content").hide();
		 $(".modal-backdrop").hide();
	  })
	  
//导出通讯录

	function downMB(moban) {
			window.open(moban);
		}
		function sendOrderMail() {
			if (document.getElementById("file").value == "") {
				alert("请选择要上传的附件");
				return false;
			}
			var path = document.getElementById("file").value;
			var isIE = (document.all) ? true : false; 3           
			var isIE9 = isIE && (navigator.userAgent.indexOf('MSIE 9.0') != -1);  
			var isIE10 = isIE && (navigator.userAgent.indexOf('MSIE 10.0') != -1);  
			var isIE11 = isIE && (navigator.userAgent.indexOf('MSIE 11.0') != -1); 
			var isChrome = window.navigator.userAgent.indexOf("Chrome") !== -1 
			if(isIE9 || isIE10 || isIE11 || isChrome){
				path = path.substring(path.lastIndexOf("\\")+1,path.length);
			}
			document.OrderSendForm.saction.value = "sendMail";
			document.OrderSendForm.attachment.value = path;
			document.OrderSendForm.action = "hroorderSend.do";
			document.OrderSendForm.submit();
		}
</script>
      
</body>
</html>