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
    <title>业务基础数据</title>
    <jsp:include flush="true" page="../common/styles.jsp"></jsp:include>
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
    <!--请在这放主要内容 ，比如：导航条,搜索块，列表等-->
    <div class="normalheader transition animated fadeIn small-header">
      <div class="hpanel">
        <div class="panel-body">
          <div id="hbreadcrumb" class="pull-right m-t-lg">
            <ol class="hbreadcrumb breadcrumb">
              <li><a href="to_index?jump=1">首页</a></li>
              <li><span>业务基础数据</span></li>
              <li class="active"><span></span></li>
            </ol>
          </div>
          <h2 class="font-light m-b-xs">
            业务基础数据
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
                <button class="btn btn-success btn-xs" type="button" data-target="#myModal7" data-toggle="modal">
                  <i class="fa fa-group"></i> <span class="bold">新增基础数据</span>
                </button>
              </div>
              记录集 
              <div>
              </div>
            </div>
            <!-- 复杂查询开始 -->
            <div class="collapse out" id="collapseExample" aria-expanded="false" >
              <div class="well well-lg " >
                <div class="row">
                  <form role="form" class="form-inline formclass" id="searchForm" >
                    <div class="form-group lmaigin">
                      <label for="exampleInputName2" class="col-lg-2">基础数据编号：</label>
                      <div class="col-lg-2">
                        <input type="text" class="form-control" name="baseDataCode" value="${baseDataCode}" id="exampleInputName2">
                      </div>
                  <!--</div>
                    <div class="form-group">-->
                      <label for="exampleInputName3" class="col-lg-2">基础数据名称：</label>
                      <div class="col-lg-2">
                        <input type="text" class="form-control" name="baseDataName" value="${baseDataName}"id="exampleInputName3" >
                      </div>
                  <!--</div>
                    <div class="form-group">-->
                      <label for="exampleInputName4" class="col-lg-2">基础数据类型：</label>
                      <div class="col-lg-2">
                        <select class="form-control m-b" name="baseDataType" id="search_Type" placeholder="全部">
                          <option value="">全部</option>

                        </select>
                      </div>
                  <!--</div>

                  <div class="form-group">-->

                    <label for="exampleInputName5" class="col-lg-2">是否有效：</label>
                    <div class="col-lg-2">
                      <select class="form-control m-b" name="isValid" placeholder="全部">
                        <option  value="">全部</option>
                        <option  value="1">有效</option>
                        <option  value="0">无效</option>
                      </select>
                    </div>
                  </div>
                  <center>
                    <button class="btn btn-info" type="button" onclick="search()">高级搜索</button>
                    <!--<a href="user_add.html"><button class="btn btn-primary" type="button">新增</button></a>-->
                  </center>
                </form>
                </div>
                </div>
                </div>
                <!-- 复杂查询结束 -->

<!--新增员工弹框-->    
<div class="modal fade hmodal-success form-row" id="myModal7" tabindex="-1" role="dialog"  aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="color-line"></div>
      <div class="modal-header">
        <div class="row">
          <div class="col-sm-11">
            <form role="form" class=" form-horizontal formclass" id="add_form" >
              <div class="form-group lmaigin">
                <label for="exampleInputName2" class="col-lg-3">基础数据编号：</label>
                <div class="col-lg-8">
                  <input type="text" class="form-control" name="baseDataCode" id="add_code">
                </div>
              </div>
              <div class="form-group">
                <label for="exampleInputName3" class="col-lg-3">基础数据名称：</label>
                <div class="col-lg-8">
                  <input type="text" class="form-control" name="baseDataName" id="add_name" >
                </div>
                <label class="col-lg-1 hong">*</label>
              </div>

              <div class="form-group">
                <label for="exampleInputName5" class="col-lg-3">基础数据类型：</label>
                <div class="col-lg-8">
                  <select class="form-control m-b nomargin_tow" name="baseDataType" id="add_baseDataType" placeholder="">

                  </select>
                </div>
                <label class="col-lg-1 hong">*</label>
              </div>
              <div class="form-group">
                <label for="exampleInputName8" class="col-lg-3">备注：</label>
                <div class="col-lg-8">
                  <textarea type="text" class="form-control" name="memo" id="exampleInputName8" ></textarea>
                </div>
              </div>
              <center>
                <!--<button class="btn btn-primary show_tow" type="button" id="show">查询</button>-->
                <button class="btn btn-info" type="button" onclick="addBtn()">保存</button>&nbsp;&nbsp;
                <button class="btn btn-info guanbi" type="button"  data-dismiss="modal">取消</button>
              </center>
            </form>
          </div>
        </div>   
      </div>
    </div>
  </div>
</div>
<!--新增员工弹框 end--> 
<!--修改弹框start-->

<div class="modal fade hmodal-success form-row" id="myModal8" tabindex="-1" role="dialog"  aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="color-line"></div>
      <div class="modal-header">
        <div class="row">
          <div class="col-sm-11">
            <form role="form" class=" form-horizontal formclass" id="add_form" >
              <div class="form-group lmaigin">
                <label for="exampleInputName2" class="col-lg-3">基础数据编号：</label>
                <div class="col-lg-8">
                  <input type="text" class="form-control" name="baseDataCode" id="add_code">
                </div>
              </div>
              <div class="form-group">
                <label for="exampleInputName3" class="col-lg-3">基础数据名称：</label>
                <div class="col-lg-8">
                  <input type="text" class="form-control" name="baseDataName" id="add_name" >
                </div>
                <label class="col-lg-1 hong">*</label>
              </div>

              <div class="form-group">
                <label for="exampleInputName5" class="col-lg-3">基础数据类型：</label>
                <div class="col-lg-8">
                  <select class="form-control m-b nomargin_tow" name="baseDataType" id="add_baseDataType" placeholder="">

                  </select>
                </div>
                <label class="col-lg-1 hong">*</label>
              </div>
              <div class="form-group">
                <label for="exampleInputName8" class="col-lg-3">备注：</label>
                <div class="col-lg-8">
                  <textarea type="text" class="form-control" name="memo" id="exampleInputName8" ></textarea>
                </div>
              </div>
              <center>
                <!--<button class="btn btn-primary show_tow" type="button" id="show">查询</button>-->
                <button class="btn btn-info" type="button" onclick="addBtn()">保存</button>&nbsp;&nbsp;
                <button class="btn btn-info guanbi" type="button"  data-dismiss="modal">取消</button>
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
  <table id="example2" class="table table-striped table-bordered table-hover" width="100%">
    <thead>
      <tr>
        <th>基础数据编号</th>
        <th>基础数据名称</th>
        <th>基础数据类型</th>
        <th>备注</th>
        <th>是否有效</th>
        <th>更新人</th>
        <th>更新日期</th>
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
	$(function (){
  $.ajax({
	  url:"system/selBaseDataType.do",
      dataType:'json',
      error: function(XMLHttpRequest, textStatus, errorThrown){
           alert(textStatus);
      },
      success: function(data){	 
	  		var list = data.dataList;
			var html = '';
			for(var i = 0; i < list.length; i++){
				html += '<option value="'+list[i].baseDataType+'">'+list[i].baseDataTypeName+'</option>';
			}
		   $('#update_Type').html(html);
		   $('#add_baseDataType').html(html);
		    $('#search_Type').append(html);
		   
      }
   });
})
	 
	$(document).ready(function() {
	    $('#example2').DataTable( {
	    "dom":

    		"<'row'<'col-sm-6'l><'col-sm-5'f><'col-sm-1'<'toolbar'>>>" +
    		"<'row'<'col-sm-12 table-responsive'tr>>" +
    		"<'row'<'col-sm-5'i><'col-sm-7'p>>",
	    	"processing": true,
	        "serverSide": true,
	        "sort":true,
	        
			"columns":[
						{ "data": "baseDataCode" },
						{ "data": "baseDataName" },
						{ "data": "baseDataTypeName" },
						{ "data": "memo" },
						{ "data": "valid" },
						{ "data": "lastModifyUserName" },
						{ "data": "lastModifyDate1"},
			            { "render": function render( data, type, row, meta ){
						             
									return   '<div class="btn-group"> <button data-toggle="dropdown" class="btn btn-info btn-xs dropdown-toggle">操作<span class="caret"></span></button>'+
											 '<ul class="dropdown-menu">'+
											 '<li><a onclick="update('+row.baseDataId+',this)" data-toggle="modal" ><i class="fa fa-check" ></i>修改</a></li>'+
											 '<li><a href="javascript:;" class="demo4 " onclick="del('+row.baseDataId+',0)" ><i class="fa fa-times "></i> 失效</a></li> '+
											 '<li><a href="javascript:;" class="demo4_1" onclick="resume('+row.baseDataId+',1)"><i class="fa fa-history "></i>生效</a></li> '+
										     '</ul></div>';
								    } }
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
   			 },
   			   "ajax":{
  	            "url": "system/besedataTableSearch",
  	            "type":"POST",
  	            "data": function ( d ) {
	    			d.data = JSON.stringify($('#searchForm').serializeObject());
  	            }
  	        }   
	    } );
	    	$("div.toolbar").html('<button class="btn btn-info  btn-sm pull-right" type="button" data-toggle="collapse" data-target="#collapseExample" aria-expanded="false" aria-controls="collapseExample"><i class="fa">高级搜索</i></button>');
	} );
	
	function search(){
		$('#example2').dataTable().api().ajax.reload();
	}
	
	//form表单转成json格式
    $.fn.serializeObject = function(){  
       var o = {};  
       var a = this.serializeArray();  
       $.each(a, function() {  
           if (o[this.name]) {  
               if (!o[this.name].push) {  
                   o[this.name] = [o[this.name]];  
               }  
               o[this.name].push(this.value || '');  
           } else {  
               o[this.name] = this.value || '';  
           }  
       });  
       return o;  
    };  
	
	</script>
	

<!--删除-->
<script>
/*   	删除 */
function del(id,isValid){
	swal({
       title: "确定要失效此基础数据吗?",
       type: "warning",
       showCancelButton: true,
       confirmButtonColor: "#DD6B55",
       confirmButtonText: "是,请失效该基础数据!",
       cancelButtonText: "不, 放弃此操作!",
       closeOnConfirm: false,
       closeOnCancel: false },
   	function(isConfirm){
	        if (isConfirm){
	        	$.ajax({
					type : "POST",
					url : "system/deleteBaseData.do",
					data : {"id": id,
							"isValid":isValid
					},
					dataType : "json",
					success : function(data) {
						if (data[0].success) {
							swal(data[0].msg, "该基础数据已经被失效.", "success");
							window.location.reload();
						}
					}
				});
	        } else {
	            swal("已取消", "基础数据未失效。你这逗我玩呢 :)", "error");
	        }
		
   });
}


/* 恢复 */
function  resume(id,isValid){
	  swal({
                   title: "确定要生效此基础数据吗?",
                   type: "warning",
                   showCancelButton: true,
                   confirmButtonColor: "#DD6B55",
                   confirmButtonText: "是,请生效该基础数据!",
                   cancelButtonText: "不, 放弃此操作!",
                   closeOnConfirm: false,
                   closeOnCancel: false },
              function(isConfirm){
			        if (isConfirm){
			        	$.ajax({
							type : "POST",
							url : "system/deleteBaseData.do",
							data : {
							"id": id,
							"isValid":isValid
							},
							dataType : "json",
							success : function(data) {
								if (data[0].success) {
									swal(data[0].msg, "该基础数据已经被生效.", "success");
									window.location.reload();
								}
							}
						});
                   } else {
                       swal("已取消", "基础数据未生效。你这逗我玩呢 :)", "error");
                   }
		
   });
}
/* 添加提交 */
function addBtn(){
	var baseDataName =$("#add_name").val();
	var baseDataType =$("#add_baseDataType").val();
	if(baseDataName ==""){
		alert("基础数据名称不能为空")
		return false;
	}else if(baseDataType == null){
		alert("基础数据类型不能为空")
		return false;
	}
	$.ajax({
		type : "POST",
		url : "system/insertBaseData.do",
		data : $("#add_form").serialize(),
		dataType : "json",
		success : function(data) {
			alert(data.msg);
			if (data.success == true) {
				window.location.href="<%=basePath%>system/searchBaseData.do";
			}
		}
	});


}
/* 修改提交 */
function subBtn(){
	var baseDataName =$("#update_name").val();
	var baseDataType =$("#update_Type").val();
	if(baseDataName ==""){
		alert("基础数据名称不能为空")
		return false;
	}
	else if(baseDataType ==""){
		alert("基础数据类型不能为空")
		return false;
	}
//	$("#form_2").submit();
$.ajax({
		type : "POST",
		url : "system/updateBaseData.do",
		data : $("#form_2").serialize(),
		dataType : "json",
		success : function(data) {
			alert(data.msg);
			if (data.success == true) {
				window.location.href="<%=basePath%>system/searchBaseData.do";
			}
		} 
	});

}
/*   得倒修改数据并弹窗 */
function update(id,btn){
	$.ajax({
		type : "POST",
		url : "system/selBaseDatabyId.do",
		data : {"id": id},
		dataType : "json",
		success : function(data) {
			$("#update_code").val(data.info.baseDataCode);
			$("#update_name").val(data.info.baseDataName);
			$("#update_Type").val(data.info.baseDataType);
			$("#update_memo").val(data.info.memo);
			$("#update_dataid").val(data.info.baseDataId);
		}  
	});
	$(btn).attr("data-target","#myModal8" );
	
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