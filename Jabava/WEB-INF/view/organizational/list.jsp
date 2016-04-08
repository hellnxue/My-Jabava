<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<!DOCTYPE HTML>

<html>
<head>
 <base href="<%=basePath%>">
 <%@ page contentType="text/html; charset=utf-8" %>
    <meta charset="utf-8">
    
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <!-- Page title -->
    <title>组织架构基准</title>

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
                        <span>组织架构基准</span>
                    </li>
                    <li class="active">
                        <span></span>
                    </li>
                </ol>
            </div>
            <h2 class="font-light m-b-xs">
                组织架构基准
            </h2>
            <small>待定</small>
        </div>
    </div>
</div>

<div class="content animate-panel">
    <div class="row">
        <div class="col-lg-12">
            <div class="hpanel">
                <div class="panel-heading ">
                   <div class="pull-right">
					  <button class="btn btn-success btn-xs" type="button" onclick="downMB('static/xls/导出组织架构基准模板.xls');" title="导出组织架构基准模板">
                       <i class="fa fa-group"></i> <span class="bold">导出基准</span>
                     </button>
                      <button class="btn btn-success btn-xs" type="button" data-target="#myModa20" data-toggle="modal">
                       <i class="fa fa-group"></i> <span class="bold">生成基准</span>
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
                              
                             <form role="form" class="form-inline formclass" action="system/searchOrganization.do" method="post" >
                                 <div class="form-group lmaigin">
	                                    <label for="exampleInputName2" class="col-lg-2">组织编号：</label>
	                                    <div class="col-lg-2">
	                                   		 <input type="text" class="form-control" name="organizationCode" value="${organizationCode}" id="exampleInputName2">
	                                    </div>
                                 <!--</div>
                                  <div class="form-group">-->
	                                    <label for="exampleInputName3" class="col-lg-2">组织完整编号：</label>
	                                    <div class="col-lg-2">
	                                   		 <input type="text" class="form-control" name="totalOrganizationCode" value="${totalOrganizationCode}"id="exampleInputName3" >
	                                    </div>
	                                     <label for="exampleInputName6" class="col-lg-2">组织名称：</label>
	                                    <div class="col-lg-2">
	                                   		 <input type="text" class="form-control" name="organizationName" value="${organizationName}"id="exampleInputName6" >
	                                    </div>
	                                     <label for="exampleInputName7" class="col-lg-2">组织层级：</label>
	                                    <div class="col-lg-2">
	                                   		 <input type="text" class="form-control" name="organizationLevel" value="${organizationLevel}"id="exampleInputName7" >
	                                    </div>
                                 <!--</div>
                                  <div class="form-group">-->
	                                    <label for="exampleInputName4" class="col-lg-2">所属组织：</label>
	                                    <div class="col-lg-2">
	                                   		 <input type="text" class="form-control" name="parentId" value="${parentId}"id="exampleInputName7" >
	                                    </div>
                                    <!--</div>
                                    
                                  <div class="form-group">-->
                                    
	                                   
                                    </div>
                                    <center>
                                    <button class="btn btn-info" type="submit">高级搜索</button>
                                    <!--<a href="user_add.html"><button class="btn btn-primary" type="button">新增</button></a>-->
                                    </center>
                              </form>
                            
                              </div>
                    </div>
                   </div>
                <!-- 复杂查询结束 -->
                
                  <!--生成基准弹框-->    
                   <div class="modal fade hmodal-success form-row" id="myModa20" tabindex="-1" role="dialog"  aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="color-line"></div>
                            <div class="modal-header">
                             <div class="row">
                              <div class="col-sm-11">
                              <form role="form" class=" form-horizontal formclass" id="add_form" >
                                 <div class="form-group lmaigin">
                                    <label for="add_baselineName" class="col-lg-3">基准名称：</label>
                                    <div class="col-lg-8">
                                    <input type="text" class="form-control" name="baselineName" id="add_baselineName">
                                    </div>
                                     <label class="col-lg-1 hong">*</label>
                                 </div>
                                  <div class="form-group">
                                    <label for="exampleInputName3" class="col-lg-3">基准描述：</label>
                                    <div class="col-lg-8">
                                    <input type="text" class="form-control" name="baselineDesc" id="exampleInputName3" >
                                    </div>
                                 </div>
                                  <center>
                                    <!--<button class="btn btn-primary show_tow" type="button" id="show">查询</button>-->
                                    <button class="btn btn-info" type="button" onClick="addBtn()">保存</button>&nbsp;&nbsp;
                                    <button class="btn btn-info guanbi" type="button">取消</button>
                                  </center>
                              </form>
                              </div>
                            </div>   
                        </div>
                    </div>
                </div>
                </div>
                 <!--生成基准弹框--> 
                 <div class="panel-body">
                <table id="example2" class="table table-striped table-bordered table-hover">
                <thead>
                <tr>
	                  <th>组织编号</th>
	                  <th>组织完整编号</th>
	                  <th>组织名称</th>
	                  <th>组织层级</th>
	                  <th>父组织</th>
	                  <th>备注</th>
	                  <th>更新人</th>
	                  <th>更新日期</th>
	                  <th>操作</th>
	              </tr>
                </thead>
               <tbody>
	             <c:forEach var="list" items="${list}" >
	               <tr>
	                  <td>${list.organizationCode}</td>
	                   <td>${list.totalOrganizationCode}</td>
	                   <td>${list.organizationName}</td>
	                   <td>${list.organizationLevel}</td>
	                   <td>
	                    ${list.parentOrganization.organizationName}
	                   </td>
	                   <td>
	                     ${list.memo}
	                   </td>
	                   <c:if test="${list.createUserName == null }">
		                   <td>${list.createUserName}</td>
		                   <td> 
		                     <fmt:formatDate value="${list.createDate}" type="date" pattern="yyyy-MM-dd"/>
		          		   </td>
	          		   </c:if>
          		      <c:if test="${list.createUserName != null }">
		          		    <td>${list.lastModifyUserName}</td>
		                   <td> 
		                     <fmt:formatDate value="${list.lastModifyDate}" type="date" pattern="yyyy-MM-dd"/>
		          		   </td>
	          		   </c:if>
	          		   <td>
	          		<div class="btn-group">
                    <button data-toggle="dropdown" class="btn btn-info btn-xs dropdown-toggle">操作<span class="caret"></span></button>
                    <ul class="dropdown-menu">
                        <li> <a onClick="update(${list.organizationId},this)" data-toggle="modal" ><i class="fa fa-check" ></i>修改</a></li>
                         <li><a href="javascript:;" class="demo4 " onClick="del(${list.organizationId})" ><i class="fa fa-times "></i> 删除</a></li>                        
                    </ul>
                     </div>  
	          		  
	          		   </td>
	               </tr>
           		 </c:forEach>
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
  /*   	删除 */
	 function del(id){
	 	swal({
	        title: "确定要删除组织机构吗?",
	        type: "warning",
	        showCancelButton: true,
	        confirmButtonColor: "#DD6B55",
	        confirmButtonText: "是,请删除该组织机构!",
	        cancelButtonText: "不, 放弃此操作!",
	        closeOnConfirm: false,
	        closeOnCancel: false },
	    	function(isConfirm){
	        	if (isConfirm){
	        		$.ajax({
	    				type : "POST",
	    				url : "system/deleteOrganization.do",
	    				data : {"organizationId": id},
	    				dataType : "json",
	    				success : function(data) {
	    					if (data[0].success) {
	    						swal(data[0].msg, "该组织机构已经被删除.", "success");
	    						window.location.reload();
	    					}else{
	    						swal(data[0].msg, "删除失败.", "error");
	    					}
	    				}
	    			});
	        	}else {
                    swal("已取消", "基础数据未生效。你这逗我玩呢 :)", "error");
                }
		 }   
	)
     	
 }
/* 生成基准提交 */
	function addBtn(){
		var baselineName =$("#add_baselineName").val();
		if(baselineName ==""){
			alert("基础数据编号不能为空")
			return false;
		}else{
		$.ajax({
			type : "POST",
			url : "system/insertOrganizationHistory.do",
			data : $("#add_form").serialize(),
			dataType : "json",
			success : function(data) {
				alert(data[0].msg);
				if (data[0].success == true) {
					window.location.href="<%=basePath%>system/searchOrganization.do";
				}
			}
		});
		}
// 		$("#add_form").submit();
 }
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
	  
	 $("#myModal7").modal('hide');
	 $("#myModal8").modal('hide');
	 $("#myModal9").modal('hide');
	  
  })
</script>

</body>
</html>