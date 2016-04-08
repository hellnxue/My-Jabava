<%@ page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html>
<head>
	<base href="${pageContext.request.contextPath}/">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <!-- Page title -->
    <title>信息公告</title>

    <!-- Place favicon.ico and apple-touch-icon.png in the root directory -->
    <!--<link rel="shortcut icon" type="image/ico" href="favicon.ico" />-->

    <!-- Vendor styles -->
    <link rel="stylesheet" href="static/bootstrap/vendor/fontawesome/css/font-awesome.css" />
    <link rel="stylesheet" href="static/bootstrap/vendor/metisMenu/dist/metisMenu.css" />
    <link rel="stylesheet" href="static/bootstrap/vendor/animate.css/animate.css" />
    <link rel="stylesheet" href="static/bootstrap/vendor/bootstrap/dist/css/bootstrap.css" />
    <link rel="stylesheet" href="static/bootstrap/vendor/xeditable/bootstrap3-editable/css/bootstrap-editable.css" />
    <link rel="stylesheet" href="static/bootstrap/vendor/select2-3.5.2/select2.css" />
    <link rel="stylesheet" href="static/bootstrap/vendor/select2-bootstrap/select2-bootstrap.css" />
    <link rel="stylesheet" href="static/bootstrap/vendor/bootstrap-touchspin/dist/jquery.bootstrap-touchspin.min.css" />
    <link rel="stylesheet" href="static/bootstrap/vendor/bootstrap-datepicker-master/dist/css/bootstrap-datepicker3.min.css" />
    <link rel="stylesheet" href="static/bootstrap/vendor/awesome-bootstrap-checkbox/awesome-bootstrap-checkbox.css" />

      
    <!-- for data table -->
    <link rel="stylesheet" href="static/bootstrap/vendor/datatables_plugins/integration/bootstrap/3/dataTables.bootstrap.css" />
    <!-- for alert -->
     <link rel="stylesheet" href="static/bootstrap/vendor/sweetalert/lib/sweet-alert.css" />

    <!-- App styles -->
    <link rel="stylesheet" href="static/bootstrap/fonts/pe-icon-7-stroke/css/pe-icon-7-stroke.css" />
    <link rel="stylesheet" href="static/bootstrap/fonts/pe-icon-7-stroke/css/helper.css" />
    <link rel="stylesheet" href="static/bootstrap/styles/style.css">
   <!-- <link rel="stylesheet" href="css/user.css">-->
    <link rel="stylesheet" href="static/css/user_bata.css">

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
<div id="wrapper">

<!----请在这放主要内容 ，比如：导航条,搜索块，列表等----->
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
                        <span>信息公告</span>
                    </li>
                    <li class="active">
                        <span></span>
                    </li>
                </ol>
            </div>
            <h2 class="font-light m-b-xs">
                信息公告
            </h2>
            <small>待定</small>
        </div>
    </div>
</div>

<!-- 放主要内容 -->

<div class="content animate-panel">
    <div class="row">
        <div class="col-lg-12">
            <div class="hpanel">
                <div class="panel-heading ">
                   <div class="pull-right">
					 <button class="btn btn-success btn-xs" type="button" data-target="#myModal7" data-toggle="modal">
                       <i class="fa fa-group"></i> <span class="bold">发布公告</span>
                     </button>
                   </div>
                    记录集 
            	<div>
            </div>
         </div>
         
         
                 <!-- 复杂查询开始 -->
                  <div class="collapse out" id="collapseExample" aria-expanded="false" >
                    <div class="well well-lg " >
                        <!--<h5 class="font-bold"><i class="fa pe-7s-search"></i> 查询</h5>-->
                        <div class="row">
                              
                              <form role="form" class="search-form"> 
                              <div class="clearfix">
                              
                              	 <div class="form-group search-unit">
                                    <label for="exampleInputName2" class="col-lg-4">信息标题：</label>
                                    <div class="col-lg-8">
                                    <input type="text" class="form-control" id="exampleInputName2">
                                    </div>
                                </div>
                                 <!--开始时间-->
                                 <div class="form-group search-unit">
                                 <label for="exampleInputName4" class="col-lg-4">开始时间：</label>
                                 <div class="input-group date col-lg-8">
                                  <input type="text" class="form-control" id="exampleInputName2">
                                  <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                               </div>
                               </div>
                                <!--结束时间-->
                                 <div class="form-group search-unit">
                                 <label for="exampleInputName4" class="col-lg-4">结束时间：</label>
                                 <div class="input-group date col-lg-8">
                                  <input type="text" class="form-control">
                                  <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                               </div>
                               </div>
                               
                                </div>
                                <center style=" margin-top:10px;">
                                 <button class="btn btn-info" type="button" id="show">查询</button>
                                 <!--<a href="user_add.html"><button class="btn btn-primary" type="button">新增</button></a>-->
                               </center>
                              </form>
                             
                              </div>
                    </div>
                   </div>
                <!-- 复杂查询结束 -->
         			
                     <!--发布公告弹框-->    
                   <div class="modal fade hmodal-success form-row" id="myModal7" tabindex="-1" role="dialog"  aria-hidden="true">
                    <div class="modal-dialog ">
                        <div class="modal-content">
                            <div class="color-line"></div>
                            <div class="modal-header ">
                             <div class="row">
                             <h4 style="border-bottom:1px solid #00CCFF; margin-bottom:20px; height:25px;">发布公告</h4>
                              <div class="col-sm-11">
                              <form role="form" class=" form-horizontal formclass">
                                 <div class="form-group lmaigin">
                                    <label for="exampleInputName2" class="col-lg-3">信息标题：</label>
                                    <div class="col-lg-9">
                                    <input type="text" class="form-control" id="exampleInputName2">
                                    </div>
                                 </div>
                                <div>
                                  <!--开始时间-->
                                 <div class="form-group pull-left">
                                 <label class=" pull-left" style=" padding-left:51px;">开始时间：</label>
                                 <div class="input-group date col-lg-6 pull-right" style=" margin-left:-69px">
                                  <input type="text" class="form-control">
                                  <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                               </div>
                               </div>
                                <!--结束时间-->
                                 <div class="form-group" >
                                 <label class=" pull-left" style=" margin-right:9px; margin-left:20px;">结束时间：</label>
                                 <div class="input-group date " style=" width:140px;">
                                  <input type="text" class="form-control ">
                                  <span class="input-group-addon" ><i class="glyphicon glyphicon-th"></i></span>
                               </div>
                               </div>
                              </div>
                              	<!--radio-->  
                                  <div style="clear:both">
                                   <div class="radio_left" style="float:left; width:50%;">
                                    <div class="form-group">
                                    <label class=" pull-left" style="margin-left:50px;">是否前排：</label>
                                    <div class="col-lg-6 pull-right">
                                    <label style="vertical-align:middle; margin-right:20px;"><input  id="optionsRadios1" type="radio" name="optionsRadios" value="option1" checked="" style="vertical-align:middle; margin-top:-2px; margin-right:6px;">是</label>
                                        <label style="vertical-align:middle;"><input  id="optionsRadios1" type="radio" name="optionsRadios" value="option1" checked="" style="vertical-align:middle; margin-top:-2px; margin-right:6px;">否</label>
                                    </div>
                                 </div>
                                   </div>
                                  <!--radio_left end-->
                                  
                                  <div class="radio_right" style="float:right;width:50%;">
                                  	 <div class="form-group">
                                    <label class=" pull-left" style="margin-left:50px;">是否有正文：</label>
                                    <div class="col-lg-6 pull-right">
                                    <label style="vertical-align:middle; margin-right:20px;"><input  id="optionsRadios1" type="radio" name="optionsRadios1" value="option1" checked="" style="vertical-align:middle; margin-top:-2px; margin-right:6px;">是</label>
                                        <label style="vertical-align:middle;"><input  id="optionsRadios1" type="radio" name="optionsRadios1" value="option1" checked="" style="vertical-align:middle; margin-top:-2px; margin-right:6px;">否</label>
                                    </div>
                                 </div>
                                  </div>
                                  </div>
                              <!--radio end--> 
                                    <div class="form-group">
                                    <label for="exampleInputName8" class="col-lg-3">备注：</label>
                                    <div class="col-lg-8">
                                    <textarea type="text" class="form-control" id="exampleInputName8" ></textarea>
                                    </div>
                                    </div>
                                    
                                     <div class="form-group">
                                    <label for="exampleInputName8" class="col-lg-3">正文：</label>
                                    <div class="col-lg-8">
                                    <textarea type="text" class="form-control" id="exampleInputName8" ></textarea>
                                    </div>
                                    </div>
                                    <center>
                                    <!--<button class="btn btn-primary show_tow" type="button" id="show">查询</button>-->
                                   <button class="btn btn-info" type="button">发布</button>&nbsp;&nbsp;
                                    <button class="btn btn-info guanbi" type="button" data-dismiss="modal">取消</button>
                                    </center>
                              </form>
                              </div>
                            </div>   
                        </div>
                    </div>
                </div>
                </div>
                <!--发布公告弹框 end--> 
                <!--修改公告弹框start-->
                        
                <div class="modal fade hmodal-success form-row" id="myModal8" tabindex="-1" role="dialog"  aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="color-line"></div>
                            <div class="modal-header">
                             <div class="row">
                             <h4 style="border-bottom:1px solid #00CCFF; margin-bottom:20px; height:25px;">修改公告</h4>
                              <div class="col-sm-11">
                              <form role="form" class=" form-horizontal formclass">
                                 <div class="form-group lmaigin">
                                    <label for="exampleInputName2" class="col-lg-3">信息标题：</label>
                                    <div class="col-lg-9">
                                    <input type="text" class="form-control" id="exampleInputName2">
                                    </div>
                                 </div>
                                <div class="">
                                  <!--开始时间-->
                                 <div class="form-group pull-left">
                                 <label class=" pull-left" style=" padding-left:51px;">开始时间：</label>
                                 <div class="input-group date col-lg-6 pull-right" style=" margin-left:-69px">
                                  <input type="text" class="form-control">
                                  <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                               </div>
                               </div>
                                <!--结束时间-->
                                 <div class="form-group" >
                                 <label class=" pull-left" style=" margin-right:9px; margin-left:20px;">结束时间：</label>
                                 <div class="input-group date " style=" width:140px;">
                                  <input type="text" class="form-control ">
                                  <span class="input-group-addon" ><i class="glyphicon glyphicon-th"></i></span>
                               </div>
                               </div>
                              </div>
                              	<!--radio-->  
                                  <div style="clear:both">
                                   <div class="radio_left" style="float:left; width:50%;">
                                    <div class="form-group">
                                    <label class=" pull-left" style="margin-left:50px;">是否前排：</label>
                                    <div class="col-lg-6 pull-right">
                                    <label style="vertical-align:middle; margin-right:20px;"><input  id="optionsRadios1" type="radio" name="optionsRadios" value="option1" checked="" style="vertical-align:middle; margin-top:-2px; margin-right:6px;">是</label>
                                        <label style="vertical-align:middle;"><input  id="optionsRadios1" type="radio" name="optionsRadios" value="option1" checked="" style="vertical-align:middle; margin-top:-2px; margin-right:6px;">否</label>
                                    </div>
                                 </div>
                                   </div>
                                  <!--radio_left end-->
                                  
                                  <div class="radio_right" style="float:right;width:50%;">
                                  	 <div class="form-group">
                                    <label class=" pull-left" style="margin-left:50px;">是否有正文：</label>
                                    <div class="col-lg-6 pull-right">
                                    <label style="vertical-align:middle; margin-right:20px;"><input  id="optionsRadios1" type="radio" name="optionsRadios1" value="option1" checked="" style="vertical-align:middle; margin-top:-2px; margin-right:6px;">是</label>
                                        <label style="vertical-align:middle;"><input  id="optionsRadios1" type="radio" name="optionsRadios1" value="option1" checked="" style="vertical-align:middle; margin-top:-2px; margin-right:6px;">否</label>
                                    </div>
                                 </div>
                                  </div>
                                  </div>
                              <!--radio end--> 
                                    <div class="form-group">
                                    <label for="exampleInputName8" class="col-lg-3">备注：</label>
                                    <div class="col-lg-8">
                                    <textarea type="text" class="form-control" id="exampleInputName8" ></textarea>
                                    </div>
                                    </div>
                                    
                                     <div class="form-group">
                                    <label for="exampleInputName8" class="col-lg-3">正文：</label>
                                    <div class="col-lg-8">
                                    <textarea type="text" class="form-control" id="exampleInputName8" ></textarea>
                                    </div>
                                    </div>
                                    <center>
                                    <!--<button class="btn btn-primary show_tow" type="button" id="show">查询</button>-->
                                   <button class="btn btn-info" type="button">修改</button>&nbsp;&nbsp;
                                    <button class="btn btn-info guanbi" type="button" data-dismiss="modal">取消</button>
                                    </center>
                              </form>
                              </div>
                            </div>   
                        </div>
                    </div>
                </div>
                </div>
                <!--修改弹框end-->   
                <div class="panel-body">
                <table id="example2" class="table table-striped table-bordered table-hover">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>信息标题</th>
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









    
    
    <!-- 放页脚  开始-->
<jsp:include flush="true" page="../common/foot.div.jsp"></jsp:include>
	<!-- 放页脚  结束-->
</div>
<!-- 放主要内容  结束-->

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
    <script src="static/bootstrap/vendor/bootstrap-datepicker-master/dist/js/bootstrap-datepicker.min.js"></script>
	<!-- for editable -->
	<script src="static/bootstrap/vendor/moment/moment.js"></script>
	 
	<!-- for datatable -->
	<script src="static/bootstrap/vendor/datatables/media/js/jquery.dataTables.min.js"></script>
	<script src="static/bootstrap/vendor/datatables_plugins/integration/bootstrap/3/dataTables.bootstrap.min.js"></script>
	<!-- alert -->
	<script src="static/bootstrap/vendor/sweetalert/lib/sweet-alert.min.js"></script>

<!-- App scripts -->
    <script src="static/bootstrap/scripts/homer.js"></script>
	<script>
   $(function () {

/* 	   var table = $('#example2').dataTable() */
    	
      	var table = $('#example2').dataTable({
    		"dom":
    		
    		"<'row'<'col-sm-6'l><'col-sm-5'f><'col-sm-1'<'toolbar'>>>" +
    		"<'row'<'col-sm-12'tr>>" +
    		"<'row'<'col-sm-5'i><'col-sm-7'p>>",
    		
    	 	 "language": {
    			 "search": "过滤:",
		            "lengthMenu": "每页显示 _MENU_ 条记录",
		            "zeroRecords": "暂无数据 - 报歉啦〜",
		            "info": "显示 第 _PAGE_ 页 共 _PAGES_ 页",
		            "infoEmpty": "暂无数据",
		            "infoFiltered": "(筛选自 _MAX_ 条记录)"
    			 }   
    		
    	    	}); 

    	$("div.toolbar").html('<button class="btn btn-info  btn-sm pull-right" type="button" data-toggle="collapse" data-target="#collapseExample" aria-expanded="false" aria-controls="collapseExample"><i class="fa">高级搜索</i></button>');

  

    }); 

</script>
<!--删除-->
<script>
    $(function () {
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

    

    });

</script>
<!--恢复-->
<script>
    $(function () {
        $('.demo4_1').click(function () {
            swal({
                        title: "确定要恢复此用户吗?",
                        text: "注意：用户恢复后可以登录!",
                        type: "warning",
                        showCancelButton: true,
                        confirmButtonColor: "#DD6B55",
                        confirmButtonText: "是,请恢复该用户!",
                        cancelButtonText: "不, 放弃此操作!",
                        closeOnConfirm: false,
                        closeOnCancel: false },
                    function (isConfirm) {
                        if (isConfirm) {
                            swal("恢复成功!", "该用户已经恢复.", "success");
                        } else {
                            swal("已取消", "用户未恢复。你这逗我玩呢 :)", "error");
                        }
                    });
        });
    });

</script>
<!--日历-->
<script>

        $(function(){

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
     <!--/*部分导出*/-->
    <script>
  $(".bfdc").click(function(){
	 $(".yincang").show();
  })
  </script>
  <!--全选 反选-->
  <script>
  	 var selAll = document.getElementById("selAll");
function selectAll()
{
  var obj = document.getElementsByName("checkAll");
  if(document.getElementById("selAll").checked == false)
  {
  for(var i=0; i<obj.length; i++)
  {
    obj[i].checked=false;
  }
  }else
  {
  for(var i=0; i<obj.length; i++)
  {  
    obj[i].checked=true;
  }
  } 
  }

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
<!--取消效果-->
<script>
  $(".guanbi").click(function(){
	  
	 // $("#myModal7").modal('hide');
	 // $("#myModal8").modal('hide');
	 $("#myModal9").modal('hide');
	  
  })
</script>
<script>
    	 /*修改*/
		  $.fn.editable.defaults.mode = 'inline';
            $('.editable').editable({
                validate: function(value) {
                    if($.trim(value) == ''){
						 return 'This field is required';
						}
                }
            });
			 
        /*修改 end*/
    </script>
</body>
</html>