<%@ page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html>
<head>
	<base href="${pageContext.request.contextPath}/">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <!-- Page title -->
    <title>劳动合同</title>
    <jsp:include flush="true" page="../common/styles.jsp"></jsp:include>
    <link rel="stylesheet" href="static/css/user_bata.css">
    <link rel="stylesheet" href="static/css/style_1.css" media="screen">
    <link rel="stylesheet" href="static/css/bill.css">
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
        <div class="panel-body"><!-- 
            <a class="small-header-action" href="">
                <div class="clip-header">
                    <i class="fa fa-arrow-up"></i>
                </div>
            </a> -->

            <div id="hbreadcrumb" class="pull-right m-t-lg">
                <ol class="hbreadcrumb breadcrumb">
                    <li><a href="to_index?jump=1">首页</a></li>
                    <li>
                        <span>员工个人信息</span>
                    </li>
                    <li class="active">
                        <span></span>
                    </li>
                </ol>
            </div>
            <h2 class="font-light m-b-xs">
                员工个人信息
            </h2>
            <small>待定</small>
        </div>
    </div>
</div>

<!-- 放主要内容 -->
  <!--引入员工信息导航 开始--> 
  <jsp:include flush="true" page="employee_nav.jsp"></jsp:include>
  <!--引入员工信息导航 结束-->

        
                     
               
                     <!--劳动合同-->
                     <div class="panel-body laodong">
                     	<div class="row ">
                        	<div style="border-bottom:1px solid #33CCFF; margin:20px; line-height:32px; overflow:hidden;">
                        	<h4 style="float:left; font-weight:normal">劳动合同</h4>
                            <div style="float:right">
                            	<button type="button" class="btn btn-success btn-xs laodong_ht"><i class="fa fa-plus"></i><span class="bold">继续添加</span></button>
                                <button type="button" class="btn btn-success btn-xs laodong_ht_update" style=" display:none;"><i class="fa fa-plus"></i><span class="bold">继续添加</span></button>
                               <button type="button" class="btn btn-success btn-xs shi_yqk"><span class="bold">试用情况&gt;&gt;</span></button> 
                            
                            </div>
                            </div>
                             <input type="hidden" name="personId" value="${personId}" id="personId">
<script type="text/html" id="laodongss">
{{each list}}
<form role="form" method="post" class="searchs-form form-horizontal col-md-12 col-lg-12  {{if $index>0}} jianju borders{{/if}}">
  <div class="text-right action-group">
    <a href="javascript://" class="pe-7s-note pe-2x" data-action-motive="edit" ><span class="sr-only">修改</span></a>
    <a href="javascript://" class="pe-7s-trash pe-2x" data-action-motive="del" data-action-id="{{$value.contractId}}"><span class="sr-only">删除</span></a>
  </div>
  <input type="hidden" name="contractId" value="{{$value.contractId}}">
  <!--合同开始时间-->
  <div class="col-md-4 col-lg-4">
    <div class="form-group">
      <label for="" class="control-label col-md-6 col-lg-6">合同开始时间：</label>
      <div class="col-md-6 col-lg-6">
        <div class="input-group-static">
          <p class="form-control-static">{{$value.contractStartDate.substr(0,10)}}&nbsp;</p>
        </div>
        <div class="input-group date" data-data-format="yyy-mm-dd">
          <input type="text" class="form-control" name="contractStartDate" value="{{$value.contractStartDate.substr(0,10)}}" required="required">
          <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
        </div>
      </div>
    </div>
  </div>
  <!--合同结束时间-->
  <div class="col-md-4 col-lg-4">
    <div class="form-group">
      <label for="" class="control-label col-md-6 col-lg-6">合同结束时间：</label>
      <div class="col-md-6 col-lg-6">
        <div class="input-group-static">
          <p class="form-control-static">{{$value.contractEndDate.substr(0,10)}}</p>
        </div>
        <div class="input-group date" data-data-format="yyy-mm-dd">
          <input type="text" class="form-control" name="contractEndDate" value="{{$value.contractEndDate.substr(0,10)}}" required="required">
          <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
        </div>
      </div>
    </div>
  </div>
  <!--合同类别-->
  <div class="col-md-4 col-lg-4">
    <div class="form-group">
      <label for="" class="control-label col-md-6 col-lg-6">合同类别：</label>
      <div class="col-md-6 col-lg-6">
        <div class="input-group-static">
          <p class="form-control-static">{{$value.contractTypeShow}}</p>
        </div>
        <select class="form-control " name="contractType">
          <option value="1"{{if $value.contractType == '1'}} selected="selected"{{/if}}>固定期限</option>
          <option value="2"{{if $value.contractType == '2'}} selected="selected"{{/if}}>无固定期限</option>
			<option value="3"{{if $value.contractType == '3'}} selected="selected"{{/if}}>以完成一定工作量的协议</option>
            <option value="4"{{if $value.contractType == '4'}} selected="selected"{{/if}}>劳务</option>
			<option value="5"{{if $value.contractType == '5'}} selected="selected"{{/if}}>派遣</option>
          <option value="6"{{if $value.contractType == '6'}} selected="selected"{{/if}}>实习</option>
        </select>
      </div>
    </div>
  </div>
  <!--合同性质-->
  <div class="col-md-4 col-lg-4">
    <div class="form-group">
      <label for="" class="control-label col-md-6 col-lg-6">合同性质：</label>
      <div class="col-md-6 col-lg-6">
        <div class="input-group-static">
          <p class="form-control-static">{{$value.contractPropertyShow}}</p>
        </div>
        <select class="form-control" name="contractProperty" >
          <option value="1"{{if $value.contractProperty=='1'}} selected="selected"{{/if}}>首签</option>
          <option value="2"{{if $value.contractProperty=='2'}} selected="selected"{{/if}}>续签</option>
        </select>
      </div>
    </div>
  </div>
  <!--试用期-->
  <div class="col-md-4 col-lg-4">
    <div class="form-group">
      <label for="" class="control-label col-md-6 col-lg-6">试用期：</label>
      <div class="col-md-6 col-lg-6">
        <div class="input-group-static">
          <p class="form-control-static">{{$value.trialMonth}}</p>
        </div>
        <input type="text" class="form-control" name="trialMonth" value="{{$value.trialMonth}}" required="required">
      </div>
    </div>
  </div>
  <!--主体-->
  <div class="col-md-4 col-lg-4">
    <div class="form-group">
      <label for="" class="control-label col-md-6 col-lg-6">主体：</label>
      <div class="col-md-6 col-lg-6">
        <div class="input-group-static">
          <p class="form-control-static">{{$value.ehrOrganization.organizationName}}</p>
        </div>
        <select class="form-control" name="contractSubject"> 
			{{each subjectList as key idx}}          
            <option value="{{key.organizationId}}"{{if $value.contractSubject==key.organizationId}} selected="selected"{{/if}}>{{key.organizationName}}</option>
		{{/each}}       
	 </select>
      </div>
    </div>
  </div>
  <!--备注-->
  <div class="col-md-12 col-lg-12">
    <div class="form-group">
      <label for="" class="control-label col-md-2 col-lg-2">备注：</label>
      <div class="col-md-10 col-lg-10">
        <div class="input-group-static">
          <p class="form-control-static">{{$value.memo}}</p>
        </div>
        <textarea class="form-control" name="memo">{{$value.memo}}</textarea>
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
                            <div class="hpanel" id="laodong_div">
                              </div>
                            <div class="panel-body" id="hetong_body">
<form role="form" method="post" class="form-horizontal col-md-12 col-lg-12 hetong hetong_form hidden" id="create_new"> 
  <!--合同开始时间-->
  <div class="col-md-4 col-lg-4">
    <div class="form-group">
      <label for="" class="control-label col-md-6 col-lg-6">合同开始时间：</label>
      <div class="col-md-6 col-lg-6">
        <div class="input-group date" data-data-format="yyy-mm-dd">
          <input type="text" class="form-control" name="contractStartDate" value="" required="required">
          <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
        </div>
      </div>
    </div>
  </div>
  <!--合同结束时间-->
  <div class="col-md-4 col-lg-4">
    <div class="form-group">
      <label for="" class="control-label col-md-6 col-lg-6">合同结束时间：</label>
      <div class="col-md-6 col-lg-6">
        <div class="input-group date" data-data-format="yyy-mm-dd">
          <input type="text" class="form-control" name="contractEndDate" value="" required="required">
          <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
        </div>
      </div>
    </div>
  </div>
  <!--合同类别-->
  <div class="col-md-4 col-lg-4">
    <div class="form-group">
      <label for="" class="control-label col-md-6 col-lg-6">合同类别：</label>
      <div class="col-md-6 col-lg-6">
        <select class="form-control " name="contractType">
          <option value="1">固定期限</option>
          <option value="2">无固定期限</option>
           <option value="3">以完成一定工作量的协议</option>
          <option value="4">劳务</option>
           <option value="5">派遣</option>
          <option value="6">实习</option>
        </select>
      </div>
    </div>
  </div>
  <!--合同性质-->
  <div class="col-md-4 col-lg-4">
    <div class="form-group">
      <label for="" class="control-label col-md-6 col-lg-6">合同性质：</label>
      <div class="col-md-6 col-lg-6">
        <select class="form-control" name="contractProperty" >
          <option value="1">首签</option>
          <option value="2">续签</option>
        </select>
      </div>
    </div>
  </div>
  <!--试用期-->
  <div class="col-md-4 col-lg-4">
    <div class="form-group">
      <label for="" class="control-label col-md-6 col-lg-6">试用期：</label>
      <div class="col-md-6 col-lg-6">
        <input type="text" class="form-control" name="trialMonth" value="" required="required">
      </div>
    </div>
  </div>
  <!--主体-->
  <div class="col-md-4 col-lg-4">
    <div class="form-group">
      <label for="" class="control-label col-md-6 col-lg-6">主体：</label>
      <div class="col-md-6 col-lg-6">
        <select class="form-control" id="contractSubject" name="contractSubject">
          <option>事业部</option>
          <option>百度人才</option>
        </select>
      </div>
    </div>
  </div>
  <!--备注-->
  <div class="col-md-12 col-lg-12">
    <div class="form-group">
      <label for="" class="control-label col-md-2 col-lg-2">备注：</label>
      <div class="col-md-10 col-lg-10">
        <textarea class="form-control" name="memo"></textarea>
      </div>
    </div>
  </div>
  <div class="col-lg-12 col-md-12 text-center">
    <button  class="btn btn-info">保存</button>
  </div>
</form>

                              </div>
                              </div>
                              </div>     
                            <!--劳动合同 end-->
                            
                       
        

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

<!-- App scripts -->
<script src="static/bootstrap/scripts/homer.js"></script>
<script src="static/js/template.js"></script>
<script src="static/js/yuangong.js"></script>
<script src="static/js/common.js"></script>
<script type="text/javascript">
var personId = $("#personId").val();
var url='employee/contractInfo?personId='+personId;

  templateFillData('laodongss', $('#laodong_div'), url,'employee/delJobpost.do?postId=');

</script>

<script>

    <!--删除-->
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

    </script>

<!--删除-->
<script>
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

</script>

<script>
  $(".guanbi").click(function(){
	  
	 $("#myModal7").modal('hide');
	  
  })
</script>
 
 <!--全部导出-->
    <script>
	  $(".jxdc").click(function(){
		 $(".modal-content").hide();
		 $(".modal-backdrop").hide();
	  })
	  </script>
	  
		<!--导出通讯录-->
			<script>
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