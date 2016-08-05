<%@ page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html>
<head>
	<base href="${pageContext.request.contextPath}/">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <!-- Page title -->
    <title>绩效考核</title>
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
    <!-- 放主要内容 -->
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
            <jsp:param value="performance" name="type"/>
            </jsp:include>
            <!--引入员工信息导航 结束-->

            <!--绩效考核-->
            <div class="panel-body">
                <div class="panel-heading">
                  <h4 class="text-center font-bold">绩效考核</h4>
                </div>
                <div class="row m-b-lg">
                <input type="hidden" name="personId" value="${personId}" id="personId">
                <script type="text/html" id="jixiaoss">
                  {{each list}}

                  <div class="col-md-12 page-header m-t-md{{if $index == list.length-1}} no-borders{{/if}} ">
                      <div class="panel-tools action-group">
                        <ul class="list-inline">
                           <li>
                          <a href="javascript://" data-action-motive="hideOrshow" data-action-id="{{$value.assessId}}"><i class="pe-7s-angle-down-circle pe-2x text-info"></i><span class="sr-only">隐藏</span></a>
                          </li>
                          <li>
                          <a href="javascript://" data-action-motive="edit" data-action-id="{{$value.assessId}}"><i class="pe-7s-note pe-2x text-info">
                          </i><span class="sr-only">修改</span></a>
                          </li>
                          <li>
                            <a href="javascript://" data-action-motive="del" data-action-id="{{$value.assessId}}"><i class="pe-7s-trash pe-2x text-info"></i><span class="sr-only">删除</span></a>
                          </li>
                        </ul>
                      </div>
                      <ul class="list-inline">
                        <li><p class="form-control-static text-info font-bold">{{if $value.createDate!=null}}{{$value.createDate.time | dateFormat}}{{/if}}&nbsp;</p></li>   
                        <li class="m-r"><p class="form-control-static text-info font-bold">{{$value.evaluationLevel}}&nbsp;</p></li>      
                        <li> <p class="form-control-static text-info font-bold">{{$value.evaluationResult}}&nbsp;</p></li>    
                     </ul>
                    </div>

                  <form data-form="{{$value.assessId}}" role="form" method="post" class="searchs-form form-horizontal col-md-12 col-lg-12  {{if $index>0}} jianju borders{{/if}}">
                    <input type="hidden" name="assessId" value="{{$value.assessId}}">

                    <!--考核年度-->
                    <div class="row">
                      <div class="col-md-6 col-lg-6">
                        <div class="col-md-12 col-lg-12">
                          <div class="form-group">
                            <label for="" class="control-label col-md-4 col-lg-4">考核年度：</label>
                            <div class="col-md-8 col-lg-8">
                              <div class="input-group-static">
                                <p class="form-control-static">
                                  {{if $value.createDate!=null}}
                                  {{$value.createDate.time | dateFormat}}
                                  {{/if}}
                                  &nbsp;
                                </p>
                              </div>
                              <div class="form-required">
                                <div class="input-group date" data-date-format="yyyy" data-date-min-view-mode="2">
                                  <input type="text" required="required" class="form-control" name="createDate" value="{{if $value.createDate!=null}}{{$value.createDate.time | dateFormat}}{{/if}}">
                                  <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                </div>
                              </div>
                            </div>
                          </div>
                        </div>
                      </div>
                      <!--考核等级-->
                      <div class="col-md-6 col-lg-6">
                        <div class="col-md-12 col-lg-12">
                          <div class="form-group">
                            <label for="" class="control-label col-md-4 col-lg-4">考核等级：</label>
                            <div class="col-md-8 col-lg-8">
                              <div class="input-group-static">
                                <p class="form-control-static">
                                  {{$value.evaluationLevel}}
                                  &nbsp;
                                </p>
                              </div>
                              <input type="text" class="form-control" name="evaluationLevel" value="{{$value.evaluationLevel}}">
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                    <!--考核结果-->
                    <div class="row">
                      <div class="col-md-6">
                        <div class="col-md-12 col-lg-12">
                          <div class="form-group">
                            <label for="" class="control-label col-md-4 col-lg-4">考核结果：</label>
                            <div class="col-md-8 col-lg-8">
                              <div class="input-group-static">
                                <p class="form-control-static">
                                  {{$value.evaluationResult}}
                                  &nbsp;
                                </p>
                              </div>
                              <div class="form-required">
                                <input type="text" required="required" class="form-control" name="evaluationResult" value="{{$value.evaluationResult}}">
                              </div>
                            </div>
                          </div>
                        </div>
                        
                        <!--考核人-->
                        <div class="col-md-12 col-lg-12">
                          <div class="form-group">
                            <label for="" class="control-label col-md-4 col-lg-4">考核人：</label>
                            <div class="col-md-8 col-lg-8">
                              <div class="input-group-static">
                                <p class="form-control-static">
                                  {{$value.assesser}}
                                  &nbsp;
                                </p>
                              </div>
                              <div class="form-required">
                                <input type="text" required="required" class="form-control" name="assesser" value="{{$value.assesser}}">
                              </div>
                            </div>
                          </div>
                        </div>
                      </div>
                      <!--考核评价-->
                      <div class="col-md-6 col-lg-6">
                        <div class="col-md-12 col-lg-12">
                          <div class="form-group">
                            <label for="" class="control-label col-md-4 col-lg-4">考核评价：</label>
                            <div class="col-md-8 col-lg-8">
                              <div class="input-group-static">
                                <p class="form-control-static">
                                  {{$value.description}}
                                  &nbsp;
                                </p>
                              </div>
                              <textarea class="form-control" name="description" rows="3">{{$value.description}}</textarea>
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                    <div class="col-lg-12 col-md-12 text-center form-action">
                      <button type="button" class="btn btn-info" data-action-motive="save">保存</button>
                      <button type="button" class="btn btn-default" data-action-motive="cancel">取消</button>
                    </div>
                  </form>
                  {{/each}}
                </script> 
                <div class="hpanel" id="jixiao_div">
                </div> 

                <div id="kaohe_body" data-form-target="content">
                  <form role="form" method="post" data-form-type="base" class="form-horizontal col-md-12 col-lg-12 kao_he kao_he_form hidden" id="create_new"> 
                    <!--考核年度-->
                    <div class="row">
                      <div class="col-md-6 col-lg-6">
                        <div class="col-md-12 col-lg-12">
                          <div class="form-group">
                            <label for="" class="control-label col-md-4 col-lg-4">考核年度：</label>
                            <div class="col-md-8 col-lg-8 form-required">
                              <div class="input-group date">
                                <input type="text" required="required" class="form-control" name="createDate" value="">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                              </div>
                            </div>
                          </div>
                        </div>
                        <!--考核结果-->
                        <div class="col-md-12 col-lg-12">
                          <div class="form-group">
                            <label for="" class="control-label col-md-4 col-lg-4">考核结果：</label>
                            <div class="col-md-8 col-lg-8 form-required">
                              <input type="text" required="required" class="form-control" name="evaluationResult" value="">
                            </div>
                          </div>
                        </div>
                        <!--考核人-->
                      <div class="col-md-12 col-lg-12">
                        <div class="form-group">
                          <label for="" class="control-label col-md-4 col-lg-4">考核人：</label>
                          <div class="col-md-8 col-lg-8 form-required">
                            <input type="text" required="required" class="form-control" name="assesser" value="">
                          </div>
                        </div>
                      </div>
                      </div>

                      <!--考核等级-->
                      <div class="col-md-6 col-lg-6">
                        <div class="col-md-12 col-lg-12">
                          <div class="form-group">
                            <label for="" class="control-label col-md-4 col-lg-4">考核等级：</label>
                            <div class="col-md-8 col-lg-8">
                              <input type="text" class="form-control" name="evaluationLevel" value="">
                            </div>
                          </div>
                        </div>
                         <!--考核评价-->
                        <div class="col-md-12 col-lg-12">
                        <div class="form-group">
                          <label for="" class="control-label col-md-4 col-lg-4">考核评价：</label>
                          <div class="col-md-8 col-lg-8">
                            <textarea class="form-control" name="description" rows="3"></textarea>
                          </div>
                        </div>
                      </div>
                      </div>
                    </div>
                    <!--保存-->
                    <div class="col-lg-12 col-md-12 text-right">
                      <button type="submit" class="btn btn-success">保存</button>
                      <button type="button" class="btn btn-default" data-action-motive="cancel">取消</button>
                    </div>
                  </form>
                </div>
                </div>
                <!--添加-->
              <div class="text-center row">
                <a class="adds" data-form-action="add" href="javascript://"><i class=" pe-7s-plus pe-5x text-muted"></i><br><span class="text-muted">添加新记录</span></a>
              </div>
            </div> 
        </div>
      </div>
    </div>
  </div>

  <!--绩效考核 end-->  

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
<script src="static/bootstrap/vendor/bootstrap-datepicker-master/dist/locales/bootstrap-datepicker.zh-CN.min.js"></script>
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
var url="employee/assessInfo?personId="+personId;
  templateFillData('jixiaoss', $('#jixiao_div'), url,'employee/delJobpost.do?postId=');

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
          createDate: {
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
          assesser: {
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
            $('.input-group.date').datepicker({ });
            $('.input-daterange').datepicker({ });

            $("#demo1").TouchSpin({
                min: 0,
                max: 100,
                step: 0.1,
                decimals: 2,
                boostat: 5,
                maxboostedstep: 10,
            });

            $("#demo2").TouchSpin({
                verticalbuttons: true
            });

            $("#demo3").TouchSpin({
                postfix: '%'
            });

            $("#demo4").TouchSpin({
                postfix: "a button",
                postfix_extraclass: "btn btn-default"
            });

            $(".js-source-states").select2();
            $(".js-source-states-2").select2();

            //turn to inline mode
            $.fn.editable.defaults.mode = 'inline';

            //defaults
            $.fn.editable.defaults.url = '#';

            //editables
            $('#username').editable({
                url: '#',
                type: 'text',
                pk: 1,
                name: 'username',
                title: 'Enter username'
            });

            $('#firstname').editable({
                validate: function(value) {
                    if($.trim(value) == '') return 'This field is required';
                }
            });

            $('#sex').editable({
                prepend: "not selected",
                source: [
                    {value: 1, text: 'Male'},
                    {value: 2, text: 'Female'}
                ],
                display: function(value, sourceData) {
                    var colors = {"": "gray", 1: "green", 2: "blue"},
                            elem = $.grep(sourceData, function(o){return o.value == value;});

                    if(elem.length) {
                        $(this).text(elem[0].text).css("color", colors[value]);
                    } else {
                        $(this).empty();
                    }
                }
            });

            $('#dob').editable();

            $('#event').editable({
                placement: 'right',
                combodate: {
                    firstItem: 'name'
                }
            });

            $('#comments').editable({
                showbuttons: 'bottom'
            });

            $('#fruits').editable({
                pk: 1,
                limit: 3,
                source: [
                    {value: 1, text: 'banana'},
                    {value: 2, text: 'peach'},
                    {value: 3, text: 'apple'},
                    {value: 4, text: 'watermelon'},
                    {value: 5, text: 'orange'}
                ]
            });

            $('#user .editable').on('hidden', function(e, reason){
                if(reason === 'save' || reason === 'nochange') {
                    var $next = $(this).closest('tr').next().find('.editable');
                    if($('#autoopen').is(':checked')) {
                        setTimeout(function() {
                            $next.editable('show');
                        }, 300);
                    } else {
                        $next.focus();
                    }
                }
            });

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