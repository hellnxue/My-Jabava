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
    <title>角色管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  <jsp:include flush="true" page="../common/styles.jsp"></jsp:include>
	<!--zTree-->
	<link rel="stylesheet" href="static/css/zTreeStyle/zTreeStyle.css" type="text/css">
	<!--for 临时改变-->
	<link rel="stylesheet" href="static/css/user.css">
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
  <!-- Main Wrapper -->
  <div id="wrapper">
    <div class="normalheader transition animated fadeIn small-header">
      <div class="hpanel">
        <div class="panel-body">
          <div id="hbreadcrumb" class="pull-right m-t-lg">
            <ol class="hbreadcrumb breadcrumb">
              <li><a href="to_index?jump=1">首页</a></li>
              <li>
                <span>用户管理</span>
              </li>
              <li class="active">
                <span>角色管理</span>
              </li>
            </ol>
          </div>
          <h2 class="font-light m-b-xs">
            角色管理
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
                  <i class="fa fa-group"></i> <span class="bold">新增角色</span>
                </button>
              </div>
              &nbsp;
              <!--<div></div>-->
            </div>
            <!-- 复杂查询开始 -->
            <div class="collapse out" id="collapseExample" aria-expanded="false" >
              <div class="well well-lg " >
                <!--<h5 class="font-bold"><i class="fa pe-7s-search"></i> 查询</h5>-->
                <div class="row">
                  <div class="col-sm-11">
                    <form role="form" class=" form-horizontal formclass" action="acl/ehrRoleSearch.do" method="post">
                      <div class="form-group lmaigin">

                        <label for="exampleInputName" class="col-lg-3 jianju">角色名称：</label>
                        <div class="col-lg-9">
                          <input type="text" class="form-control"  name="roleName" value="${roleName}" id="exampleInputName">
                        </div>
                      </div>
                      <center>
                        <button class="btn btn-info" type="submit">高级搜索</button>
                      </center>
                    </form>
                  </div>
                </div>
              </div>
            </div>
            <!-- 复杂查询结束 -->

            <div class="modal fade hmodal-success form-row" id="myModal7" tabindex="-1" role="dialog"  aria-hidden="true">
              <div class="modal-dialog">
                <div class="modal-content">
                  <div class="color-line"></div>
                  <div class="modal-header">
                    <div class="row">
                      <div class="col-sm-11">
                        <form role="form" class=" form-horizontal formclass" id="add_form">
                          <div class="form-group">
                            <label class="col-lg-3 new_padding" for="roleNames">角色名称：</label>
                            <div class="col-lg-8">
                              <input type="text" class="form-control" id="role_name" name="roleName" value="${roleName}">
                            </div>
                            <label class="col-lg-1 hong">*</label>
                          </div> 

                          <div class="form-group">
                            <label class="col-lg-3 new_padding">备注：</label>
                            <div class="col-lg-8">
                              <input type="text" class="form-control" name="memo" id="meno" value="${memo }">
                            </div>
                          </div>
                          <center>
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

            <!--修改弹框start-->

            <div class="modal fade hmodal-success form-row" id="myModal8" tabindex="-1" role="dialog"  aria-hidden="true">
              <div class="modal-dialog">
                <div class="modal-content">
                  <div class="color-line"></div>
                  <div class="modal-header clearfix">
                    <div class="row">
                      <div class="col-sm-12">
                        <form role="form" class=" form-horizontal formclass" id="update_form" method="post">
                          <input type="hidden"  id="update_roleId" name="roleId" >
                          <div class="form-group">
                            <label class="col-lg-3 new_padding" for="roleNames">角色名称：</label>
                            <div class="col-lg-8">
                              <input type="text" class="form-control" id="update_roleName" name="roleName" value="">
                            </div>

                          </div> 

                          <div class="form-group">
                            <label class="col-lg-3 new_padding">备注：</label>
                            <div class="col-lg-8">
                              <input type="text" class="form-control" name="memo" id="update_memo" value="">
                            </div>

                          </div> 

                          <center>
                            <button class="btn btn-primary" type="button" onclick="updateBtn()">保存</button>
                            &nbsp;
                            <button class="btn btn-primary guanbi" type="button" data-dismiss="modal">取消</button>
                          </center>
                        </form>
                      </div>
                    </div> 
                  </div>
                </div>
              </div>
            </div>

            <!--分配人员弹窗开始-->
            <div class="modal fade hmodal-success form-row" id="myModal10" tabindex="-1" role="dialog"  aria-hidden="true">
              <div class="modal-dialog">
                <div class="modal-content">
                  <div class="color-line clearfix role_guanbi_size"><span class="pe-7s-close-circle margin_top margin_right role_fr guanbi"></span></div>
                  <div class="modal-header">
                    <div class="row">

                      <div class="col-sm-12 role_algin">
                        <input type="hidden" id="ru_roleId"/>
                        <div class="form-group">
                          <label class="col-lg-3 role_algin_tow" id="ru_roleName"></label>
                          <label class="col-lg-9">&nbsp;</label>
                        </div>
                        <div class="form-group clearfix role_jianjie">
                          <label class="col-lg-12 role_algin_tow role_font_weight" id="ru_memo"></label>
                        </div>
                      </div>

                      <div class="col-sm-12">
                        <form method="get" id="iceform" class="role_position_marginb iceform_gl">
                          <fieldset form="iceform" class="role_fieldset">
                            <legend class="role_legend">已选人员</legend>

                            <div class="form-group clearfix">
                              <label for="exampleInputName2" class="col-lg-7">&nbsp;</label>
                              <label for="exampleInputName20" class="col-lg-2 role_right">过滤：</label>
                              <div class="col-lg-3">
                                <input type="text" class="form-control role_search" id="exampleInputName20" value="">
                              </div>
                            </div>

                            <div class="form-group role_algin clearfix" id="dellabel">

                            </div>
                          </fieldset>
                        </form>

                        <form method="get" id="iceform" class="iceform_gl">
                          <fieldset form="iceform" class="role_fieldset">
                            <legend class="role_legend">未选人员</legend>

                            <div class="form-group clearfix">
                              <label for="exampleInputName2" class="col-lg-7">&nbsp;</label>
                              <label for="exampleInputName20" class="col-lg-2 role_right">过滤：</label>
                              <div class="col-lg-3">
                                <input type="text" class="form-control role_search" id="exampleInputName20" value="">
                              </div>
                            </div>

                            <div class="form-group role_algin clearfix" id="dellabel_one">

                            </div>
                          </fieldset>
                        </form>
                        <center class="center_top role_jianjie">
                          <button class="btn btn-primary" type="button" onclick="roleUserSave()">保存</button>
                          &nbsp;
                          <button class="btn btn-primary fuwei" type="button">复位</button>
                        </center>
                      </div>

                    </div>
                  </div>
                </div>
              </div>
            </div>
            <!--分配人员弹窗结束-->

            <!--设置权限弹框start-->
            <div class="modal fade hmodal-success form-row" id="myModal9" tabindex="-1" role="dialog"  aria-hidden="true">
              <div class="modal-dialog">
                <div class="modal-content">
                  <div class="color-line clearfix role_guanbi_size"><span class="pe-7s-close-circle margin_top margin_right role_fr guanbi"></span></div>
                  <div class="modal-header">
                    <div class="row">

                      <div class="col-sm-12 role_algin">
                        <input type="hidden" id="power_roleId"/>
                        <div class="form-group">
                          <label class="col-lg-3 role_algin_tow" id="power_roleName"></label>
                          <label class="col-lg-9">&nbsp;</label>
                        </div>
                        <div class="form-group clearfix role_jianjie">
                          <label class="col-lg-12 role_algin_tow role_font_weight" id="power_memo"></label>
                        </div>
                        <div class="form-group">
                          <label id="button_authority" class="col-lg-2 role_nomargin role_dborder role_blank_tow role_padding" onClick="f_show_one(button_authority)">按钮权限</label>
                          <label id="menu_permissions" class="col-lg-2 role_nomargin role_border_tow role_padding" onClick="f_show_one(menu_permissions)">菜单权限</label>
                          <label class="col-lg-8 role_border role_nomargin role_blank role_padding">&nbsp;</label>
                        </div>
                      </div>

                      <div class="col-sm-12">
                        <div class="col-lg-12 role_border_third">
                          <!--按钮权限开始-->
                          <div class="dd" id="nestable">
                            <ul id="treeDemo" class="ztree"></ul>
                          </div>
                          <!--按钮权限结束-->
                          <!--菜单权限开始-->
                          <div class="dd role_tree" id="nestable_tow">
                            <ul id="treeDemo2" class="ztree"></ul>
                          </div>

                          <center class="center_top role_jianjie">
                            <button class="btn btn-primary" type="button" onclick="rolePowerSave()">保存</button>
                            &nbsp;
                            <button class="btn btn-primary guanbi" type="button">取消</button>
                          </center>
                          <!--菜单权限结束-->
                        </div>
                      </div> 

                    </div>
                  </div>
                </div>
              </div>
            </div>
            <!--设置权限弹框end-->

            <div class="panel-body">
              <table id="example2" class="table table-striped table-bordered table-hover" width="100%">
                <thead>
                  <tr>
                    <th>角色名称</th>
                    <th>备注</th>
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
  <!--树状-->
  <script src="static/bootstrap/vendor/nestable/jquery.nestable.js"></script>
  <!--树状结束-->

  <!--zTree-->
  <script type="text/javascript" src="static/js/zTreeJs/jquery.ztree.core-3.5.js"></script>
  <script type="text/javascript" src="static/js/zTreeJs/jquery.ztree.excheck-3.5.js"></script>

  <!--删除-->
  <script src="static/bootstrap/vendor/jquery-validation/jquery.validate.min.js"></script>

  <!-- for datatable -->
  <script src="static/bootstrap/vendor/datatables/media/js/jquery.dataTables.min.js"></script>
  <script src="static/bootstrap/vendor/datatables_plugins/integration/bootstrap/3/dataTables.bootstrap.min.js"></script>


  <!-- alert -->
  <script src="static/bootstrap/vendor/sweetalert/lib/sweet-alert.min.js"></script>

  <!-- App scripts -->
  <script src="static/bootstrap/scripts/homer.js"></script>
  <script src="static/bootstrap/scripts/charts.js"></script>
    
    
  <!--jabava-->
  <script src="static/js/jabava.js"></script>
  <script>
    $.fn.dataTable.pipeline = function(opts) {
// Configuration options
var conf = $.extend({
pages: 5, // number of pages to cache
url : '', // script url
data : null, // function or object with parameters to send to the
// server
// matching how `ajax.data` works in DataTables
method : 'GET' // Ajax HTTP method
}, opts);

// Private variables for storing the cache
var cacheLower = -1;
var cacheUpper = null;
var cacheLastRequest = null;
var cacheLastJson = null;

return function(request, drawCallback, settings) {
  var ajax = false;
  var requestStart = request.start;
  var drawStart = request.start;
  var requestLength = request.length;
  var requestEnd = requestStart + requestLength;

  if (settings.clearCache) {
// API requested that the cache be cleared
ajax = true;
settings.clearCache = false;
}

else if (cacheLower < 0 || requestStart < cacheLower
  || requestEnd > cacheUpper) {
// outside cached data - need to make a request
ajax = true;
} else if (JSON.stringify(request.order) !== JSON
  .stringify(cacheLastRequest.order)
  || JSON.stringify(request.columns) !== JSON
  .stringify(cacheLastRequest.columns)
  || JSON.stringify(request.search) !== JSON
  .stringify(cacheLastRequest.search)) {
// properties changed (ordering, columns, searching)
ajax = true;
}

// Store the request for checking next time around
cacheLastRequest = $.extend(true, {}, request);

if (ajax) {
// Need data from the server
if (requestStart < cacheLower) {
  requestStart = requestStart
  - (requestLength * (conf.pages - 1));

  if (requestStart < 0) {
    requestStart = 0;
  }
}

cacheLower = requestStart;
cacheUpper = requestStart + (requestLength * conf.pages);

request.start = requestStart;
request.length = requestLength * conf.pages;

// Provide the same `data` options as DataTables.
if ($.isFunction(conf.data)) {
// As a function it is executed with the data object as an arg
// for manipulation. If an object is returned, it is used as the
// data object to submit
var d = conf.data(request);
if (d) {
  $.extend(request, d);
}
} else if ($.isPlainObject(conf.data)) {
// As an object, the data given extends the default
$.extend(request, conf.data);
}

settings.jqXHR = $.ajax({
  "type" : conf.method,
  "url" : conf.url,
  "data" : request,
  "dataType" : "json",
  "cache" : false,
  "success" : function(json) {
    cacheLastJson = $.extend(true, {}, json);

    if (cacheLower != drawStart) {
      json.data.splice(0, drawStart - cacheLower);
    }
    json.data.splice(requestLength, json.data.length);

    drawCallback(json);
  }
});
} else {
  json = $.extend(true, {}, cacheLastJson);
json.draw = request.draw; // Update the echo for each response
json.data.splice(0, requestStart - cacheLower);
json.data.splice(requestLength, json.data.length);

drawCallback(json);
}
}
};

// Register an API method that will empty the pipelined data, forcing an Ajax
// fetch on the next draw (i.e. `table.clearPipeline().draw()`)
$.fn.dataTable.Api.register('clearPipeline()', function() {
  return this.iterator('table', function(settings) {
    settings.clearCache = true;
  });
});

$(document).ready(function() {
  $('#example2').DataTable( {
    "processing": true,
    "serverSide": true,
    "sort":true,
    "columns": [
    /*{ "data": "userId" },*/
    { "data": "roleName" },
    { "data": "memo" },
    { "data": "lastModifyUserName" },
    { "data": "lastModifyDate1"},
    { "render": function render( data, type, row, meta ){

      return   '<div class="btn-group"><button data-toggle="dropdown" class="btn btn-info btn-xs dropdown-toggle">操作<span class="caret"></span></button>'+
      '<ul class="dropdown-menu">'+
      ' <li><a href="#" data-target="#myModal10" data-toggle="modal" onclick="roleUserEnter('+row.roleId+',this)"><i class="fa fa-group"></i> 分配人员</a></li>'+
      ' <li><a href="#" data-target="#myModal9" data-toggle="modal" onclick="rolePowerEnter('+row.roleId+',this)"><i class="fa fa-gear "></i> 权限设置</a></li>'+
      ' <li><a href="#" data-target="#myModal8" data-toggle="modal" onclick="updateEnter('+row.roleId+')"><i class="fa fa-check"></i> 修改</a></li>'+
      ' <li><a class="demo4"  onclick="delRole('+row.roleId+')"> <i class="fa fa-times"></i> 删除</a></li>'+                      
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
      "url": "acl/dataTableSearch.do",
      "type":"POST",
      "data": function ( d ) {
      }
    }

  } );
} );

function addBtn(){
  var roleName = $("#role_name").val();

  if(roleName == null || roleName ==""){
    alert("角色名称不能为空")
    return false;
  }
// 		$("#add_form").submit();
$.ajax({
  type : "POST",
  url : "acl/insertRole.do",
  data : $("#add_form").serialize(),
  dataType : "json",
  success : function(data) {
    alert(data[0].msg);
    if (data[0].success == true) {
      window.location.href="acl/ehrRoleSearch.do";
    }
  }
});
}

function updateEnter(roleId, a){
// 			 var obj = new Object();
// 	    	 obj.data = JSON.stringify({"roleId":roleId});
$.ajax({
  type:"POST",
  url : "acl/selRoleId.do",
  data:{"roleId":roleId},
  dataType : "json",
  success: function(data){
    console.log(data);
    $("#update_roleId").val(data.roleId);
    $("#update_roleName").val(data.roleName);
    $("#update_memo").val(data.memo);
  }
});
$(a).attr("data-target","#myModal8")
}

function updateBtn(){
  var roleName =$("#update_roleName").val();

  if(roleName == null || roleName ==""){
    alert("角色名称不能为空")
    return false;
  }
  $.ajax({
    type : "POST",
    url : "acl/updatetRole.do",
    data : $("#update_form").serialize(),
    dataType : "json",
    success : function(data) {
      alert(data[0].msg);
      if (data[0].success == true) {
        window.location.href="acl/ehrRoleSearch.do";
      }
    }
  });
}

function delRole(roleId){
  swal({
    title: "确定要删除该角色吗?",
    text: "注意：角色删除后将不可修复!",
    type: "warning",
    showCancelButton: true,
    confirmButtonColor: "#DD6B55",
    confirmButtonText: "是,请删除该角色!",
    cancelButtonText: "不, 放弃此操作!",
    closeOnConfirm: false,
    closeOnCancel: false},
    function (isConfirm) {
      if (isConfirm) {
        $.ajax({
          type : "POST",
          url : "acl/deleteRole.do",
          data : {"roleId": roleId},
          dataType : "json",
          success : function(data) {
            if (data[0].flag) {
              swal(data[0].msg, "该行数据已经被删除.", "success");
              window.location.reload();
            }else{
              swal(data[0].msg, "删除遇到问题.", "error");
            }
          }
        });
      } else {
        swal("已取消", "角色未删除。你这逗我玩呢 :)", "error");
      }
    });
}

//匹配人员
function roleUserEnter(roleId, a){
  $.ajax({
    type : "POST",
    url : "acl/memberAllotEnter.do",
    data : {"roleId": roleId},
    dataType : "json",
    success : function(data) {
      $("#ru_roleId").val(data.roleInfo.roleId);
      $("#ru_roleName").text(data.roleInfo.roleName);
      $("#ru_memo").text(data.roleInfo.memo);
      var selectHtml = "";
      var notSelectHtml = '';
      for(var i = 0; i < data.userSelected.length; i++){
        selectHtml += '<label class="col-lg-3">'+data.userSelected[i].userName+'<span class="pe-7s-close-circle role_span_margin" onclick="delhtml(i)"></span><input type="hidden" name="ru_select" value="'+data.userSelected[i].userId+'" /></label>';
      }
      for(var i = 0; i < data.userNotSelected.length; i++){
        notSelectHtml += '<label class="col-lg-3">'+data.userNotSelected[i].userName+'<span class="pe-7s-check role_span_margin" onclick="delhtml_one(i)"></span><input type="hidden" name="ru_select" value="'+data.userNotSelected[i].userId+'" /></label>';
      }
      $("#dellabel").html(selectHtml);
      $("#dellabel_one").html(notSelectHtml);
      $("#dellabel").data("key",$("#dellabel").html());
      $("#dellabel_one").data("key",$("#dellabel_one").html());
    }
  });
  $(a).attr("data-target", "#myModal10")
}

function roleUserSave(){
  var roleId = $("#ru_roleId").val();
  var userIds = "";
  $("#dellabel").find("[name='ru_select']").each(function(i){
    userIds += $(this).val()+",";
  })
  $.ajax({
    type : "POST",
    url : "acl/memberAllot.do",
    data : {"roleId":roleId, "userIds":userIds},
    dataType : "text",
    success : function(data) {
      alert(data);
      $("#myModal10").modal('hide');
    }
  });
}

var setting = {
  check: {
    enable: true,
    chkboxType: { "Y": "ps", "N": "ps" }
  },
  data: {
    simpleData: {
      enable: true
    }
  },
  view:{
    showLine:false
  }
};
var treeDemo = new Array();//按钮权限
var treeDemo2 = new Array();//菜单权限
function rolePowerEnter(id, btn){
  $.ajax({
    type : "POST",
    url : "acl/rolePowerEnter.do",
    data : {"id":id},
    dataType : "json",
    async : false,
    success : function(data) {
treeDemo = new Array();//按钮权限置空
treeDemo2 = new Array();//菜单权限置空
//角色
$("#power_roleId").val(data.role.roleId);
$("#power_roleName").text(data.role.roleName);
$("#power_memo").text(data.role.memo);
//按钮权限
var button = data.button;
if(button != null && button != "undefined"){
  if(button.nodes != null && button.nodes != '' && button.nodes != 'undefined'){
    buttonTree(button.nodes);
  }
  delete button.nodes;
  delete button.menuType;
  treeDemo.push(button);
}
var treeObj=$.fn.zTree.init($("#treeDemo"), setting, treeDemo);//初始化tree
//根据子节点选中父节点
var nodes = treeObj.getCheckedNodes();
for (var i = 0; i < nodes.length; i++) {
  treeObj.checkNode(nodes[i], true, true);
}

//菜单权限
var menu = data.menu;
if(menu != null && menu != "undefined"){
  if(menu.nodes != null && menu.nodes != '' && menu.nodes != 'undefined'){
    menuTree(menu.nodes);
  }
  delete menu.nodes;
  delete menu.menuType;
  treeDemo2.push(menu);
}
var treeObj2 = $.fn.zTree.init($("#treeDemo2"), setting, treeDemo2);//初始化tree
//根据子节点选中父节点
var nodes2 = treeObj2.getCheckedNodes();
for (var i = 0; i < nodes2.length; i++) {
  treeObj2.checkNode(nodes2[i], true, true);
}
}
});
  $(btn).attr("data-target","#myModal9");
}
//按钮权限
function buttonTree(node){
  for(var i = 0; i < node.length; i++){
    if(node[i].nodes != null && node[i].nodes != '' && node[i].nodes != 'undefined'){
      buttonTree(node[i].nodes);
    }
    delete node[i].nodes;
    delete node[i].menuType;
    treeDemo.push(node[i]);
  }
}
//菜单权限
function menuTree(node){
  for(var i = 0; i < node.length; i++){
    if(node[i].nodes != null && node[i].nodes != '' && node[i].nodes != 'undefined'){
      menuTree(node[i].nodes);
    } 
    delete node[i].nodes;
    delete node[i].menuType;
    treeDemo2.push(node[i]);
  }
}

function rolePowerSave(){
  var roleId = $("#power_roleId").val();
var buttons = "";//按钮id
var menus = "";//菜单id
var treeObj = $.fn.zTree.getZTreeObj("treeDemo"),nodes=treeObj.getCheckedNodes(true);
for(var i = 0; i < nodes.length; i++){
  if(nodes[i].type == "button"){
    buttons += nodes[i].id + ",";
  }
}
var treeObj2 = $.fn.zTree.getZTreeObj("treeDemo2"), nodes2 = treeObj2.getCheckedNodes(true);
for(var i = 0; i < nodes2.length; i++){
  if(nodes2[i].type == "menu_30"){
    menus += nodes2[i].id + ",";
  }
}
if(buttons == "" && menus == ""){
  alert("请选择一个菜单或按钮！");
  return false;
}
//保存
$.ajax({
  type : "POST",
  url : "acl/rolePowerSave.do",
  data : {"roleId":roleId, "buttons":buttons, "menus":menus},
  dataType : "json",
  success : function(data) {
    alert(data.msg);
    if(data.success == true){
      $("#myModal9").modal('hide');
    }
  }
}); 
}
</script>

<script type="text/javascript">
  $(function (){

    $.ajax({
      url:"acls/selRoleId.do",
      dataType:'json',
      error: function(XMLHttpRequest, textStatus, errorThrown){
        alert(textStatus);
      },
      success: function(data){
        $(#update_roleId).val(data.roleId);
        $('#mingchen').val(data.roleName);
        $('#beizhu').val(data.memo);

      }
    });



  </script>

  <!--取消效果-->
  <script>
    $(".guanbi").click(function(){

// $("#myModal7").modal('hide');
// $("#myModal8").modal('hide');
$("#myModal9").modal('hide');
$("#myModal10").modal('hide');
$(".content_clear").val("");

})
</script>

<!--多选下拉-->
<script>
  $(".js-source-states").select2();
  $(".js-source-states-2").select2();
</script>

</body>
