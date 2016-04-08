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
    <title>信息公告</title>
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

    <!--  请在这放主要内容 ，比如：导航条,搜索块，列表等  -->
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
            <button class="btn btn-success btn-xs" type="button" onclick="$('#addAnnouncementForm')[0].reset();" data-target="#addModal" data-toggle="modal">
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
            <div class="row">                              
              <form role="form"    id="searchForm"  class="search-form"> 
                <div class="clearfix">
                  <div class="form-group search-unit">
                    <label for="exampleInputName2" class="col-lg-4">信息标题：</label>
                    <div class="col-lg-8">
                      <input type="text" class="form-control"  id="queryTitle" name="informationTitle" >
                    </div>
                  </div>
                  <!--开始时间-->
                  <div class="form-group search-unit">
                    <label for="exampleInputName4" class="col-lg-4">开始时间：</label>
                    <div class="input-group date col-lg-8" data-date-format="yyyy-mm-dd">
                      <input type="text" class="form-control"  id="searchStartDate" name="startDate">
                      <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                    </div>
                  </div>
                  <!--结束时间-->
                  <div class="form-group search-unit">
                    <label for="exampleInputName4" class="col-lg-4">结束时间：</label>
                    <div class="input-group date col-lg-8" data-date-format="yyyy-mm-dd">
                      <input type="text" class="form-control"  id="searchFinishDate"name="finishDate">
                      <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                    </div>
                  </div>

                </div>
              </form>
              <center style=" margin-top:10px;">
                <button class="btn btn-info" type="button"  id="showList" >查询</button>
              </center>
            </div>
          </div>
        </div>
        <!-- 复杂查询结束 -->

        <!--发布公告弹框-->    
        <div class="modal fade hmodal-success form-row" id="addModal" tabindex="-1" role="dialog"  aria-hidden="true">
          <div class="modal-dialog ">
            <div class="modal-content">
              <div class="color-line"></div>
              <div class="modal-header ">
                <div class="row">
                  <h4 style="border-bottom:1px solid #00CCFF; margin-bottom:20px; height:25px;">发布公告</h4>
                  <div class="col-sm-11">
                    <form role="form" id="addAnnouncementForm" class=" form-horizontal formclass">
                      <div class="form-group lmaigin">
                        <label for="exampleInputName2" class="col-lg-3">信息标题：</label>
                        <div class="col-lg-8">
                          <input type="text" class="form-control" id="addInformationTitle"  name="informationTitle" >
                        </div>
                        <span class="col-lg-1" style="color:red; vertical-align:middle; margin-top:8px; display:inline-block; padding-left:0;">*</span>
                      </div>
                      <div>
                        <!--开始时间-->
                        <div class="form-group pull-left">
                          <label class=" pull-left" style=" padding-left:51px;">开始时间：</label>
                          <div class="input-group date pull-right" data-date-format="yyyy-mm-dd" style="width:178px; padding-left:35px; -moz-display:inline-table; -webkit-display:inline-table;">
                            <input id="addStartDate" type="text" class="form-control"  name="startDate">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                          </div>
                        </div>

                        <!--结束时间-->
                        <div class="form-group" >
                          <label class=" pull-left" style=" margin-right:9px; margin-left:35px;">结束时间：</label>
                          <div class="input-group date " data-date-format="yyyy-mm-dd" style=" width:140px;">
                            <input id="addFinishDate" type="text" class="form-control " name="finishDate">
                            <span class="input-group-addon" ><i class="glyphicon glyphicon-th"></i></span>
                          </div>
                          <span style="color:red; position: absolute; top:59px; z-index:999; right:-15px;">*</span>
                        </div>

                      </div>
                      <!--radio-->  
                      <div style="clear:both">
                        <div class="radio_left" style="float:left; width:50%;">
                          <div class="form-group">
                            <label class=" pull-left" style="margin-left:50px;">是否前排：</label>
                            <div class="col-lg-6 pull-right">
                              <label style="vertical-align:middle; margin-right:20px;">
                                <input  id="optionsRadios1" type="radio" name="isPriority" value="1"  checked="" style="vertical-align:middle; margin-top:-2px; margin-right:6px;">是</label>
                                <label style="vertical-align:middle;">
                                  <input  id="optionsRadios1" type="radio" name="isPriority" value="0"  checked="" style="vertical-align:middle; margin-top:-2px; margin-right:6px;">否</label>
                                  <span style="color:red; margin-left:10px;">*</span>
                                </div>
                              </div>
                            </div>
                            <!--radio_left end-->

                            <div class="radio_right" style="float:right;width:50%;">
                              <div class="form-group">
                                <label class=" pull-left" style="margin-left:50px;">是否有正文：</label>
                                <div class="col-lg-6 pull-right">
                                  <label style="vertical-align:middle; margin-right:20px;">
                                    <input type="radio"  id="addHasDetail1" name="hasDetail" value="1" style="vertical-align:middle; margin-top:-2px; margin-right:6px;">是</label>
                                    <label style="vertical-align:middle;">
                                      <input type="radio"  id="addHasDetail2" name="hasDetail" value="0" checked="true" style="vertical-align:middle; margin-top:-2px; margin-right:6px;">否</label>
                                      <span style="color:red; margin-left:10px;">*</span>
                                    </div>
                                  </div>
                                </div>
                              </div>
                              <!--radio end--> 
                              <div class="form-group">
                                <label for="exampleInputName8" class="col-lg-3">备注：</label>
                                <div class="col-lg-8">
                                  <textarea type="text" class="form-control" id="addRemark"  name="remark"></textarea>
                                </div>
                              </div>

                              <div class="form-group">
                                <label for="exampleInputName8" class="col-lg-3">正文：</label>
                                <div class="col-lg-8">
                                  <textarea type="text" class="form-control" id="addInformationContent" name="informationContent" readonly="true"></textarea>
                                </div>
                              </div>
                              <center>
                                <button class="btn btn-info" type="button" id="addAnouncement">发布</button>&nbsp;&nbsp;
                                <button class="btn btn-info guanbi" id="AddCancel" type="button" data-dismiss="modal">取消</button>
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
                <div class="modal fade hmodal-success form-row" id="updateModal" tabindex="-1" role="dialog"  aria-hidden="true">
                  <div class="modal-dialog">
                    <div class="modal-content">
                      <div class="color-line"></div>
                      <div class="modal-header">
                        <div class="row">
                          <h4 style="border-bottom:1px solid #00CCFF; margin-bottom:20px; height:25px;">修改公告</h4>
                          <div class="col-sm-11">
                            <form role="form" id="updateInformationForm" class=" form-horizontal formclass">
                              <input type="hidden" id="updateInformationId" name="informationId" />
                              <div class="form-group lmaigin">
                                <label for="exampleInputName2" class="col-lg-3">信息标题：</label>
                                <div class="col-lg-8">
                                  <input type="text" class="form-control" id="updateInformationTitle"  name="informationTitle">
                                </div>
                                <span class="col-lg-1" style="color:red; vertical-align:middle; margin-top:8px; display:inline-block; padding-left:0;">*</span>
                              </div>
                              <div class="">
                                <!--开始时间-->
                                <div class="form-group pull-left">
                                  <label class=" pull-left" style=" padding-left:51px;">开始时间：</label>
                                  <div class="input-group date pull-right" data-date-format="yyyy-mm-dd" style="width:178px; padding-left:35px; -moz-display:inline-table; -webkit-display:inline-table;">
                                    <input type="text" class="form-control" id="updateStartDate" name="startDate">
                                    <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                  </div>
                                </div>
                                <!--结束时间-->
                                <div class="form-group" >
                                  <label class=" pull-left" style=" margin-right:9px; margin-left:35px;">结束时间：</label>
                                  <div class="input-group date "  data-date-format="yyyy-mm-dd" style=" width:140px;">
                                    <input type="text" class="form-control "  id="updateFinishDate" name="finishDate">
                                    <span class="input-group-addon" ><i class="glyphicon glyphicon-th"></i></span>
                                  </div>
                                  <span style="color:red; position: absolute; top:59px; z-index:999; right:-15px;">*</span>
                                </div>
                              </div>
                              <!--radio-->  
                              <div style="clear:both">
                                <div class="radio_left" style="float:left; width:50%;">
                                  <div class="form-group">
                                    <label class=" pull-left" style="margin-left:50px;">是否前排：</label>
                                    <div class="col-lg-6 pull-right">
                                      <label style="vertical-align:middle; margin-right:20px;">
                                        <input  id="updateIsPriority1" type="radio" name="isPriority" value="1"  style="vertical-align:middle; margin-top:-2px; margin-right:6px;">是</label>
                                        <label style="vertical-align:middle;">
                                          <input  id="updateIsPriority2" type="radio" name="isPriority" value="0"  style="vertical-align:middle; margin-top:-2px; margin-right:6px;">否</label>
                                          <span style="color:red; margin-left:10px;">*</span>
                                        </div>
                                      </div>
                                    </div>
                                    <!--radio_left end-->

                                    <div class="radio_right" style="float:right;width:50%;">
                                      <div class="form-group">
                                        <label class=" pull-left" style="margin-left:50px;">是否有正文：</label>
                                        <div class="col-lg-6 pull-right">
                                          <label style="vertical-align:middle; margin-right:20px;">
                                            <input  id="updateHasDetail1" type="radio" name="hasDetail" value="1"  style="vertical-align:middle; margin-top:-2px; margin-right:6px;">是</label>
                                            <label style="vertical-align:middle;">
                                              <input  id="updateHasDetail2" type="radio" name="hasDetail" value="0"  style="vertical-align:middle; margin-top:-2px; margin-right:6px;">否</label>
                                              <span style="color:red; margin-left:10px;">*</span>
                                            </div>
                                          </div>
                                        </div>
                                      </div>
                                      <!--radio end--> 
                                      <div class="form-group">
                                        <label for="exampleInputName8" class="col-lg-3">备注：</label>
                                        <div class="col-lg-8">
                                          <textarea type="text" class="form-control" id="updateRemark" name="remark" ></textarea>
                                        </div>
                                      </div>

                                      <div class="form-group">
                                        <label for="exampleInputName8" class="col-lg-3">正文：</label>
                                        <div class="col-lg-8">
                                          <textarea type="text" class="form-control" id="updateInformationContent" name="informationContent" ></textarea>
                                        </div>
                                      </div>
                                      <center>
                                        <button class="btn btn-info" type="button" id="updateAnouncement" >修改</button>&nbsp;&nbsp;
                                        <button class="btn btn-info guanbi" type="button" id="updateCancel"  data-dismiss="modal">取消</button>
                                      </center>
                                    </form>
                                  </div>
                                </div>   
                              </div>
                            </div>
                          </div>
                        </div>

                        <!-- 查看公告详细信息弹窗  -->
                        <div class="modal fade hmodal-success form-row" id="showModal" tabindex="-1" role="dialog"  aria-hidden="true">
                          <div class="modal-dialog">
                            <div class="modal-content">
                              <div class="color-line"></div>
                              <div class="modal-header">
                                <div class="row">
                                  <h4 style="border-bottom:1px solid #00CCFF; margin-bottom:20px; height:25px;">查看公告</h4>
                                  <div class="col-sm-11">
                                    <form role="form" id="showInformationForm" class=" form-horizontal">
                                      <input type="hidden" id="showInformationId" name="informationId" />
                                      <div class="form-group">
                                        <label for="" class="control-label col-md-3 col-lg-3">信息标题：</label>
                                        <div class="col-lg-9">
                                          <p class="form-control-static" id="showInformationTitle"></p>
                                        </div>
                                      </div>
                                      <div class="form-group">
                                        <!--开始时间-->
                                        <div class="col-md-6 col-lg-6">
                                          <label class="control-label col-md-6 col-lg-6">开始时间：</label>
                                          <div class="col-md-6 col-lg-6">
                                            <p class="form-control-static" id="showStartDate"></p>
                                          </div>
                                        </div>
                                        <!--结束时间-->
                                        <div class="col-md-6 col-lg-6" >
                                          <label class="control-label col-md-6 col-lg-6">结束时间：</label>
                                          <div class="col-md-6 col-lg-6">
                                            <p class="form-control-static" id="showFinishDate"></p>
                                          </div>
                                        </div>
                                      </div>

                                      <div class="form-group">
                                        <label for="" class="control-label col-lg-3">正文：</label>
                                        <div class="col-lg-8">
                                          <p class="form-control-static" id="showInformationContent"></p>
                                        </div>
                                      </div>                                   
                                    </form>
                                  </div>
                                </div>   
                              </div>
                            </div>
                          </div>
                        </div>

                        <!--信息列表 -->   
                        <div class="panel-body">
                          <table id="example2" class="table table-striped table-bordered table-hover" width="100%">
                            <thead>
                              <tr>
                                <th>ID</th>
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

    <script src="static/bootstrap/vendor/jquery/dist/jquery.min.js"></script>	
    <script src="static/bootstrap/vendor/jquery-ui/jquery-ui.min.js"></script>
    <script src="static/bootstrap/vendor/jquery-ui/jquery-ui.js"></script>

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

    <script src="static/bootstrap/vendor/bootstrap-datepicker-master/dist/js/bootstrap-datepicker.js"></script>
    <script src="static/bootstrap/vendor/bootstrap-datepicker-master/dist/locales/bootstrap-datepicker.zh-CN.min.js"></script>


    <!-- for editable -->
    <script src="static/bootstrap/vendor/moment/moment.js"></script>

    <!-- for datatable -->
    <script src="static/bootstrap/vendor/datatables/media/js/jquery.dataTables.min.js"></script>
    <script src="static/bootstrap/vendor/datatables_plugins/integration/bootstrap/3/dataTables.bootstrap.min.js"></script>
    <!-- alert -->
    <script src="static/bootstrap/vendor/sweetalert/lib/sweet-alert.min.js"></script>

    <!-- App scripts -->
    <script src="static/bootstrap/scripts/homer.js"></script>

    <script src="static/js/validate.js"></script>
	
    <script>
      $(function () {
        var grid = $('#example2').dataTable({
          "dom":

          "<'row'<'col-sm-3'l><'col-sm-8'f><'col-sm-1'<'toolbar'>>>" +
          "<'row'<'col-sm-12 table-responsive'tr>>" +
          "<'row'<'col-sm-5'i><'col-sm-7'p>>",


//json
"processing": true,
"serverSide": true,
"ajax": "announcement/announcementList",
"sServerMethod": "POST",
"columns": [
{ "data": "last_modify_date", "visible": false },
{ "data": "information_id" },
{ "data": "informationTitle", "render": function(data, type, row, meta){
  return '<div style="width:100%;"><a onclick="showUpdate(' + row.information_id + ');" data-target="#showModal" data-toggle="modal">' + row.information_title + '</a></div>';
} },
{ "render": function render( data, type, row, meta ){						
  return '<div class="btn-group">'+
  '<button data-toggle="dropdown" class="btn btn-info btn-xs dropdown-toggle">操作<span class="caret"></span></button>'+
  '<ul class="dropdown-menu">'+
  "<li><a onclick='showUpdate("+row.information_id+")' data-target='#updateModal' data-toggle='modal'><i class='fa fa-check'></i>修改</a></li>"+
  "<li><a onclick='deleteInformation("+row.information_id+")' class='demo4 '><i class='fa fa-times '></i> 删除</a></li>"+
  "</ul></div>";

} }
],

"language": {
  "search": "过滤:",
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
}

}); 

        $("div.toolbar").html('<button class="btn btn-info  btn-sm pull-right" type="button" data-toggle="collapse" data-target="#collapseExample" aria-expanded="false" aria-controls="collapseExample"><i class="fa">高级搜索</i></button>');
        jQuery.prototype.serializeObject=function(){  
          var obj=new Object();  
          $.each(this.serializeArray(),function(index,param){  
            if(!(param.name in obj)){  
              obj[param.name]=param.value;  
            }  
          });  
          return obj;  
        };  
        $('#showList').click(function () {
          if($("#searchStartDate").val()!='' && $("#searchFinishDate").val()!=''){
            if(dateCompare($("#searchStartDate").val(),$("#searchFinishDate").val()) == 1){
              alert("开始时间大于结束时间");
              return false;
            }
          }

          var grid = $('#example2').dataTable({
            "dom":
            "<'row'<'col-sm-3'l><'col-sm-8'f><'col-sm-1'<'toolbar'>>>" +
            "<'row'<'col-sm-12 table-responsive'tr>>" +
            "<'row'<'col-sm-5'i><'col-sm-7'p>>",

//json
"processing": true,
"serverSide": true,
"bDestroy":true, 
"sServerMethod": "POST",
"ajax":{
  "url": "announcement/announcementList",	 		  	        	
  "data": function ( d ) {

    if( $("#searchStartDate").val()==''){
      $("#searchStartDate").val(null);
    }
    if( $("#searchFinishDate").val()==''){
      $("#searchFinishDate").val(null);
    }
    d.data = JSON.stringify($("#searchForm").serializeObject());								

  }
},
"columns": [
{ "data": "last_modify_date", "visible": false },
{ "data": "information_id" },
{ "data": "informationTitle", "render": function(data, type, row, meta){
  return '<div style="width:100%;"><a onclick="showUpdate(' + row.information_id + ');" data-target="#showModal" data-toggle="modal">' + row.information_title + '</a></div>';
} },
{ "render": function render( data, type, row, meta ){						
  return '<div class="btn-group">'+
  '<button data-toggle="dropdown" class="btn btn-info btn-xs dropdown-toggle">操作<span class="caret"></span></button>'+
  '<ul class="dropdown-menu">'+
  "<li><a onclick='showUpdate("+row.information_id+")' data-target='#updateModal' data-toggle='modal'><i class='fa fa-check'></i>修改</a></li>"+
  "<li><a onclick='deleteInformation("+row.information_id+")' class='demo4 '><i class='fa fa-times '></i> 删除</a></li>"+
  "</ul></div>";

} }
],

"language": {
  "search": "过滤:",
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
}   
}); 

          $("div.toolbar").html('<button class="btn btn-info  btn-sm pull-right" type="button" data-toggle="collapse" data-target="#collapseExample" aria-expanded="false" aria-controls="collapseExample"><i class="fa">高级搜索</i></button>');
        });

// 发布公告
$('#addAnouncement').bind("click",function () {
  if( $("#addInformationTitle").val()==''){
    alert("请输入标题");
    return false;
  }
  if( $("#addStartDate").val()==''){
    alert("请输入开始时间");
    return false;
  }
  if( $("#addFinishDate").val()==''){
    alert("请输入结束时间");
    return false;
  }
//if($("#addFinishDate").val()!=''){
  if(dateCompare($("#addStartDate").val(),$("#addFinishDate").val()) == 1){
    alert("开始时间大于结束时间");
    return false;
  }
//}
if($("#addHasDetail1").prop('checked')){//是否有详细信息 
  if( $("#addInformationContent").val()=='') {			  
    alert("请输入内容");
    return false;
  }	
}

$.ajax({
  type : 'post',
  url : "announcement/addAnnouncement",
  data : $("#addAnnouncementForm").serialize(),
  async : false,
  dataType: "json",
  success : function(result) {
    if(result.msg == 'ins_success'){
      $("#addModal").modal('hide');
      clearAdd();
      alert('新增成功 !');
      window.location.reload();

    }else{
      alert('新增失败!');
    }
  }
});
});

//修改公告
$('#updateAnouncement').click(function () {
  if( $("#updateInformationTitle").val()==''){
    alert("请输入标题");
    return false;
  }
  if( $("#updateStartDate").val()==''){
    alert("请输入开始时间");
    return false;
  }
  if( $("#updateFinishDate").val()==''){
    alert("请输入结束时间");
    return false;
  }
//if($("#updateFinishDate").val()!=''){
  if(dateCompare($("#updateStartDate").val(),$("#updateFinishDate").val()) == 1){
    alert("开始时间大于结束时间");
    return false;
  }
//}
if($("#updateHasDetail1").prop('checked')){//是否有详细信息 
  if( $("#updateInformationContent").val()=='') {			  
    alert("请输入内容");
    return false;
  }	
}	
$.ajax({
  type : 'post',
  url : "announcement/updateAnnouncement",
  data : $("#updateInformationForm").serialize(),
  async : false,
  dataType: "json",
  success : function(result) {
    if(result.msg == 'upd_success'){
      $("#updateModal").modal('hide');
      clearUpdate();
      alert('修改成功 !');
      window.location.reload();
    }else{
      alert('修改失败!');
    }
  }
});
});


//新增窗口的取消按钮
$('#AddCancel').click(function () {
// $("#addModal").modal('hide');		
clearAdd();
});

//修改窗口的取消按钮
$('#updateCancel').click(function () {
// $("#updateModal").modal('hide');		
clearUpdate();
});

//新增窗口是否有正文
$('#addHasDetail1').bind('click',function(){
  $('#addInformationContent').attr("readonly",false);
});
$('#addHasDetail2').bind('click',function(){
  $('#addInformationContent').attr("readonly",true);
  $('#addInformationContent').val('');
});

$('#updateHasDetail1').bind('click',function(){
  $('#updateInformationContent').attr("readonly",false);
});
$('#updateHasDetail2').bind('click',function(){
  $('#updateInformationContent').attr("readonly",true);
  $('#updateInformationContent').val('');
});
}); 

function clearAdd(){
  $("#addInformationTitle").val('');
  $("#addRemark").val('');
  $("#addInformationContent").val('');
}

function clearUpdate(){
  $("#updateInformationTitle").val('');
  $("#updateRemark").val('');
  $("#updateInformationContent").val('');
}
</script>
<!--恢复-->
<script>
  function formatDate(time){
//var JsonDateValue = new Date(time);		
//var date = JsonDateValue.getFullYear()+"-"+(JsonDateValue.getMonth()+1)+"-"+JsonDateValue.getDate();
//return date
if(time && time.length >= 10){
  return time.substring(0,10);
}else{
  return time;
}
}
//显示修改窗口
function  showUpdate(informationId){
  $.ajax({
    type : 'post',
    url : "announcement/showUpdate",
    data : {informationId:informationId},
    async : false,
    dataType: "json",
    success : function(result) {
      $("#updateInformationId").val(result.informationId);		
      $("#updateInformationTitle").val(result.informationTitle);	
      $("#updateStartDate").val(formatDate(result.startDate));			 	
      $("#updateFinishDate").val(formatDate(result.finishDate));
      $("#updateRemark").val(result.remark);
      $("#updateInformationContent").val(result.informationContent);

if(result.isPriority==1){//是否优先排列 
  $("#updateIsPriority1").click();
}else{
  $("#updateIsPriority2").click();
}

if(result.hasDetail==1){
//$("#updateHasDetail1").attr("checked",true);
$('#updateHasDetail1').click();
//$("#updateInformationContent").attr("readonly",false);
}else{
//$("#updateHasDetail2").attr("checked",false);
$('#updateHasDetail2').click();
//$("#updateInformationContent").attr("readonly",true);
}
}
});
}
//删除
function  deleteInformation(informationId){
  swal({
    title: "确定要删除此公告吗?",
    text: "注意：公告删除后将不可恢复!",
    type: "warning",
    showCancelButton: true,
    confirmButtonColor: "#DD6B55",
    confirmButtonText: "是,请删除该公告!",
    cancelButtonText: "不, 放弃此操作!",
    closeOnConfirm: false,
    closeOnCancel: false },
    function (isConfirm) {
      if (isConfirm) {
        $.ajax({
          type : 'post',
          url : "announcement/deleteAnnouncement",
          data : {"informationId":informationId},
          async : false,
          dataType: "json",
          success : function(result) {
            if(result.msg == 'del_success'){
              swal("删除成功!", "该公告已经被删除", "success");
              window.location.reload();
            }else{
              swal("未删除成功", "删除失败", "error");
            }

          }
        });

      } else {
        swal("已取消", "公告未删除。你这逗我玩呢 :)", "error");
      }
    });
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

//turn to inline mode
$.fn.editable.defaults.mode = 'inline';

//defaults
$.fn.editable.defaults.url = '#';

});
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