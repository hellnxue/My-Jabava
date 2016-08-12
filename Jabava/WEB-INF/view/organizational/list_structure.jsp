<%@ page contentType="text/html; charset=utf-8" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="com.jabava.utils.RequestUtil"%>
<%
String path = request.getContextPath();
String basePath = "//"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML>
<html>
<head>
	<base href="${pageContext.request.contextPath}/">
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">

	<!-- Page title -->
	<title>Jabava组织架构界面</title>
	<jsp:include flush="true" page="../common/styles.jsp"></jsp:include>
	<!--zTree-->
	<link rel="stylesheet" href="static/css/zTreeStyle/zTreeStyle.css" type="text/css">
	<!--for 临时改变-->
	<link rel="stylesheet" href="static/css/user.css">

</head>

<body class="body">
  <div id="rMenu">
    <ul>
      <% if(RequestUtil.hasPower("organization_as")){ %>
      <li class="m_add" id="m_add" onclick="$('#addOrgForm')[0].reset();" data-target="#myModal_add" data-toggle="modal"><span class="fa fa-plus org_span_one"></span><span>增加子部门</span></li>
      <% } %>
      <% if(RequestUtil.hasPower("organization_dd")){ %>
      <li id="m_del" onclick="removeTreeNode();"><span class="fa fa-minus org_span_one"></span><span>删 除</span></li>
      <% } %>
      <% if(RequestUtil.hasPower("organization_md")){ %>
      <li class="m_check" id="m_mod" data-target="#myModal_xiugai" data-toggle="modal"><span class="fa fa-pencil org_span_one"></span><span>修 改</span></li>
      <% } %>
    </ul>
  </div>

  <!-- Simple splash screen-->
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
    <div class="normalheader transition animated fadeIn small-header">
      <div class="hpanel">
        <div class="panel-body">
          <div id="hbreadcrumb" class="m-t-xs m-b-xs">
            <h2 class="font-normal m-b-xs text-center">
              组织架构
            </h2>
          </div>
        </div>
      </div>
    </div>
    <!-- 放主要内容 -->
    <div class="content animate-panel">
      <div class="row">
        <div class="col-lg-12">
          <div class="hpanel">
            <!-- <div class="panel-heading">
              <div class="text-right">
                <button class="btn btn-success btn-xs" type="button" data-target="#myModal7" data-toggle="modal">
                  <i class="fa fa-group"></i> <span class="bold">生成基准</span>
                </button>
              </div>
            </div> -->

            <!--生成基准弹框-->    
            <div class="modal fade hmodal-success form-row" id="myModal7" tabindex="-1" role="dialog"  aria-hidden="true">
              <div class="modal-dialog">
                <div class="modal-content">
                  <div class="color-line"></div>
                  <div class="modal-header org_Tmargin">
                    <div class="row">
                      <div class="col-sm-12">
                        <form role="form" class=" form-horizontal formclass">
                          <div class="form-group lmaigin" >
                            <h4 class="col-lg-12 org_h4">生成组织架构基准</h4>
                          </div>
                          <div class="form-group lmaigin">
                            <label for="exampleInputName2" class="col-lg-3">基准名称：</label>
                            <div class="col-lg-7">
                              <input type="text" class="form-control" id="exampleInputName2" value="">
                            </div>
                            <label class="col-lg-1 hong">*</label>
                          </div>
                          <div class="form-group">
                            <label for="exampleInputName8" class="col-lg-3">基准描述：</label>
                            <div class="col-lg-7">
                              <input type="text" class="form-control" id="exampleInputName8">
                            </div>
                          </div>
                          <center>
                            <button class="btn btn-primary" type="button">保存</button>
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
            <!--生成基准弹框 end--> 

            <div class="panel-body">
              <div class="col-sm-12">
                <div class="form-group">
                  <p>选中组织架构，点击鼠标右键可进行添加、修改、删除操作。</p>
                </div>
                <div class="form-group clearfix">
                  <div class="col-lg-4">
                    <!--树状图开始-->
                    <div class="dd" id="nestable_third">

                      <div class="zTreeDemoBackground">
                        <ul id="treeDemo" class="ztree"></ul>
                      </div>
                    </div>
                    <!--树状图结束-->
                  </div>
                  <div class="col-lg-8 org_jianju org_information">
                    <table id="personTable" class="table table-striped table-bordered table-hover text_align" width="100%">
                      <thead>
                        <tr>
                          <th>&nbsp;</th>
                          <th>部门</th>
                          <th>职位</th>
                          <th>姓名</th>
                          <th>电话</th>
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
        </div>
        </div>
      <!--主要内容结束-->
      <!-- 放页脚  开始-->
      <jsp:include flush="true" page="../common/foot.div.jsp"></jsp:include>
      <!-- 放页脚  结束-->
    </div>

    <!--弹框-->
    <!--新增员工弹框-->    
    <div class="modal fade hmodal-success form-row" id="myModal_add" tabindex="-1" role="dialog"  aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="color-line"></div>
          <div class="modal-header">
            <div class="row">
              <div class="col-sm-12">
                <form role="form" id="addOrgForm" class=" form-horizontal formclass">
                  <div class="form-group lmaigin">
                    <label for="_OrganizationCode" class="col-lg-3">部门名称：</label>
                    <div class="col-lg-8 form-required">
                      <input type="text" class="form-control" id="_OrganizationName" value="" placeholder="请输入部门名称">
                    </div>
                  </div>
                  <div class="form-group lmaigin">
                    <label for="_OrganizationName" class="col-lg-3">部门编号：</label>
                    <div class="col-lg-8 form-required">
                      <input type="text" class="form-control" id="_OrganizationCode" value="" placeholder="请输入部门编号">
                    </div>
                  </div>
                  <div class="form-group">
                    <label for="_Memo" class="col-lg-3">备注：</label>
                    <div class="col-lg-8">
                      <textarea type="text" class="form-control" id="_Memo"></textarea>
                    </div>
                  </div>
                  <center>
                    <button id="m_add" class="btn btn-primary" type="button" onClick="addOrg();">确认</button>
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

<!--修改员工弹框-->    
<div class="modal fade hmodal-success form-row" id="myModal_xiugai" tabindex="-1" role="dialog"  aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="color-line"></div>
      <div class="modal-header">
        <div class="row">
          <div class="col-sm-12">
            <form role="form" class=" form-horizontal formclass">
              <div class="form-group lmaigin">
                <label for="orgNameForModify" class="col-lg-3">部门名称：</label>
                <div class="col-lg-8 form-required">
                  <input type="text" class="form-control" id="orgNameForModify" value="" placeholder="测试部">
                </div>
              </div>
              <div class="form-group lmaigin">
                <label for="orgCodeForModify" class="col-lg-3">部门编号：</label>
                <div class="col-lg-8 form-required">
                  <input type="text" class="form-control" id="orgCodeForModify" value="" placeholder="111">
                </div>
              </div>
              <div class="form-group">
                <label for="memoForModify" class="col-lg-3">备注：</label>
                <div class="col-lg-8">
                  <textarea type="text" class="form-control" id="memoForModify"></textarea>
                </div>
              </div>
              <center>
                <button id="m_check" class="btn btn-primary" type="button" onclick="editTreeNode();">确认</button>
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
  <script type="text/javascript" src="static/js/zTreeJs/zTreeJs/jquery.ztree.core-3.5.js"></script>
  <script type="text/javascript" src="static/js/zTreeJs/zTreeJs/jquery.ztree.excheck-3.5.js"></script>
  <script type="text/javascript" src="static/js/zTreeJs/zTreeJs/jquery.ztree.exedit-3.5.js"></script>

  <!-- for datatable -->
  <script src="static/bootstrap/vendor/datatables/media/js/jquery.dataTables.min.js"></script>
  <script src="static/bootstrap/vendor/datatables_plugins/integration/bootstrap/3/dataTables.bootstrap.min.js"></script>

  <!-- alert -->
  <script src="static/bootstrap/vendor/sweetalert/lib/sweet-alert.min.js"></script>

  <!-- App scripts -->
  <script src="static/bootstrap/scripts/homer.js"></script>
  <script src="static/bootstrap/scripts/charts.js"></script>

  <!--jabava-->
  <script src="static/js/organizational_structure.js"></script>
    
<!--树形结构开始-->
<script>
	var setting = {
		view: {
			dblClickExpand: false,
			showLine:false
		},
		check: {
			enable: true
		},
		callback: {
			onRightClick: OnRightClick,
			onClick: zTreeOnClick,
			beforeDrag: beforeDrag,
			beforeDrop: beforeDrop,
			beforeDragOpen: beforeDragOpen,
			onDrag: onDrag,
			onDrop: onDrop,
			onExpand: onExpand
		},
		edit: {
			drag: {
				autoExpandTrigger: true,
				//prev: dropPrev,
				inner: dropInner,
				next: dropNext
			},
			enable: true,
			showRemoveBtn: false,
			showRenameBtn: false
		},
		data: {
			simpleData: {
				enable: true
			}
		},
		treeNode:{
			nocheck:true
		}
	};
	
	var personTable;
	var currentNode;
	var log, className = "dark", curDragNodes, autoExpandNode;
	$(document).ready(function(){
		loadData();
	
		personTable = $('#personTable').DataTable({
    		"dom": 
    			   "<'row'<'col-sm-12 table-responsive'tr>>",
    			   
			"processing": true,
			"ordering": false,
        	"serverSide": true,
			"ajax": "system/loadPerson",
			"columns": [
				//{ "data": "personId", "visible": false },
				{"render": function render( data, type, row, meta ){
						if(row.sex == 1){
							return "<div><i class='pe-7s-user-female pe-2x' style='color:#f9c'></i></div>";
						}else{
							return "<div><i class='pe-7s-user pe-2x' style='color:#8e8efd'></i></div>";
						}
					}
				},
				{ "data": "org" },
				{ "data": "post" },
			    {"render": function render( data, type, row, meta ){
					if(row.employeeName){
						return "<a href='employee/employeeInformation?personId="+row.personId+"'>"+row.employeeName+"</a>";
					}
				}
			},
				{ "data": "mobile" }
        	],
   	 	 	"language": {
		        "zeroRecords": "暂无数据 ",
		        "infoEmpty": "暂无数据"
   			 }
    	});
	});
	
	//鼠标右键
	function OnRightClick(event, treeId, treeNode) {
		if(treeNode == null){
			return false;	
		}

    if( !treeNode.authorized ) return false;
	
		$('#_OrganizationCode').val('');
		$('#_OrganizationName').val('');
		$('#_Memo').val('');
		$('#orgCodeForModify').val(treeNode.code);
		$('#orgNameForModify').val(treeNode.name);
		$('#memoForModify').val(treeNode.memo);
		
		var e = event || window.event;
        var scrollX = document.documentElement.scrollLeft || document.body.scrollLeft;
        var scrollY = document.documentElement.scrollTop || document.body.scrollTop;
        var x = e.clientX + scrollX;
        var y = e.clientY + scrollY;
		
        var zTree = $.fn.zTree.getZTreeObj("treeDemo");
		//if (!treeNode && event.target.tagName.toLowerCase() != "button" && $(event.target).parents("a").length == 0) {
		if(treeNode.pId == null || treeNode.pId == 0){
			//zTree.cancelSelectedNode();
			currentNode = treeNode;
			showRMenu("root", x, y);
		} else if (treeNode && !treeNode.noR) {
			//zTree.selectNode(treeNode);
			currentNode = treeNode;
			showRMenu("node", x, y);
		}
	}

	function showRMenu(type, x, y) {
		$("#rMenu").show();
		if (type=="root") {
			//$("#m_add").hide();
			$("#m_del").hide();
			$("#m_mod").hide();
		} else {
			//$("#m_add").show();
			$("#m_del").show();
			$("#m_mod").show();
		}
		
		$("#rMenu").css({"top":y+"px", "left":x+"px", "visibility":"visible"});
					
		$("body").bind("mousedown", onBodyMouseDown);
		
		$("body").click(function(){
			$("#rMenu").hide();
		});
	}
	
	function hideRMenu() {
		//if (rMenu) rMenu.css({"visibility": "hide"});
		$("#rMenu").hide();
		$("body").unbind("mousedown", onBodyMouseDown);
	}
	
	function onBodyMouseDown(event){
		if (!(event.target.id == "rMenu" || $(event.target).parents("#rMenu").length>0)) {
			$("#rMenu").css({"visibility" : "hide"});
		}
	}
		
	//pid为空时，右面不显示
	function zTreeOnClick(event, treeId, treeNode) {
		if(treeNode.pId == 0 || treeNode.pId == null){	
			$(".org_information").hide();
		}else{
			$(".org_information").show();
			currentNode = treeNode
			personTable.ajax.url('system/loadPerson?organizationId=' + treeNode.id).load();
		}
	};

	function beforeDrag(treeId, treeNodes) {
    className = (className === "dark" ? "":"dark");
    //showLog("[ "+getTime()+" beforeDrag ]&nbsp;&nbsp;&nbsp;&nbsp; drag: " + treeNodes.length + " nodes." );
    for (var i=0,l=treeNodes.length; i<l; i++) {
			if (treeNodes[i].drag === false) {
				curDragNodes = null;
				return false;
			} else if (treeNodes[i].parentTId && treeNodes[i].getParentNode().childDrag === false) {
				curDragNodes = null;
				return false;
			}
		}
		curDragNodes = treeNodes;
		return true;
	}
	
	function beforeDragOpen(treeId, treeNode) {
		autoExpandNode = treeNode;
		return true;
	}
	
	function beforeDrop(treeId, treeNodes, targetNode, moveType, isCopy) {
    if(!targetNode.authorized) return false;
		if(targetNode.pId == null || targetNode.pId == 0){	//拖拽目标不能是父节点
			return false;
		}
		var msg;
		$.ajax({
			url:"system/moveOrg",
			type : "POST",
			dataType:'json',
			async: false,
			data: {"sourceId":treeNodes[0].id, "targetId":targetNode.id,"moveFlag":moveType},
			error: function(XMLHttpRequest, textStatus, errorThrown){
			    alert(textStatus);
			},
			success: function(data){
				 msg = data.msg;
			}
		});
		
		if(msg != 'upd_success'){
			return false;
		}
		
		className = (className === "dark" ? "":"dark");
		//showLog("[ "+getTime()+" beforeDrop ]&nbsp;&nbsp;&nbsp;&nbsp; moveType:" + moveType);
		//showLog("target: " + (targetNode ? targetNode.name : "root") + "  -- is "+ (isCopy==null? "cancel" : isCopy ? "copy" : "move"));
		return true;
	}
	
	function onDrag(event, treeId, treeNodes) {
		className = (className === "dark" ? "":"dark");
		//showLog("[ "+getTime()+" onDrag ]&nbsp;&nbsp;&nbsp;&nbsp; drag: " + treeNodes.length + " nodes." );
	}
	
	function onDrop(event, treeId, treeNodes, targetNode, moveType, isCopy) {

    var zTree = $.fn.zTree.getZTreeObj("treeDemo");
    zTree.updateNode(treeNodes[0]);


		className = (className === "dark" ? "":"dark");
		//showLog("[ "+getTime()+" onDrop ]&nbsp;&nbsp;&nbsp;&nbsp; moveType:" + moveType);
		//showLog("target: " + (targetNode ? targetNode.name : "root") + "  -- is "+ (isCopy==null? "cancel" : isCopy ? "copy" : "move"))
	}
	
	function onExpand(event, treeId, treeNode) {
		if (treeNode === autoExpandNode) {
			className = (className === "dark" ? "":"dark");
			//showLog("[ "+getTime()+" onExpand ]&nbsp;&nbsp;&nbsp;&nbsp;" + treeNode.name);
		}
	}
	
	//拖拽效果
	function dropPrev(treeId, nodes, targetNode) {
		var pNode = targetNode.getParentNode();
		if (pNode && pNode.dropInner === false) {
			return false;
		} else {
			for (var i=0,l=curDragNodes.length; i<l; i++) {
				var curPNode = curDragNodes[i].getParentNode();
				if (curPNode && curPNode !== targetNode.getParentNode() && curPNode.childOuter === false) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	function dropInner(treeId, nodes, targetNode) {
    if(!targetNode.authorized) return false;
		if (targetNode && targetNode.dropInner === false) {
			return false;
		} else {
			for (var i=0,l=curDragNodes.length; i<l; i++) {
				if (!targetNode && curDragNodes[i].dropRoot === false) {
					return false;
				} else if (curDragNodes[i].parentTId && curDragNodes[i].getParentNode() !== targetNode && curDragNodes[i].getParentNode().childOuter === false) {
					return false;
				}
			}
		}
		return true;
	}
	
	function dropNext(treeId, nodes, targetNode) {
    if(!targetNode.authorized) return false;
		var pNode = targetNode.getParentNode();
		if (pNode && pNode.dropInner === false) {
			return false;
		} else {
			for (var i=0,l=curDragNodes.length; i<l; i++) {
				var curPNode = curDragNodes[i].getParentNode();
				if (curPNode && curPNode !== targetNode.getParentNode() && curPNode.childOuter === false) {
					return false;
				}
			}
		}
		return true;
	}
	
	//新增
	function add(e) {
		//var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
		//isParent = e.data.isParent,
		//nodes = zTree.getSelectedNodes(),
		//treeNode = nodes[0];
		//if (treeNode) {
		//	treeNode = zTree.addNodes(treeNode, {id:(100 + newCount), pId:treeNode.id, isParent:isParent, name:"new node" + (newCount++)});
		//} else {
		//	treeNode = zTree.addNodes(null, {id:(100 + newCount), pId:0, isParent:isParent, name:"new node" + (newCount++)});
		//}
		//if (treeNode) {
		//	zTree.editName(treeNode[0]);
		//} else {
		//	zTree.editName(treeNode[0]);
		//}
		$("#myModal_add").modal('hide');
	};
	 //新增节点 
	function addOrg(){
		if($('#_OrganizationName').val() == ''){
			alert("部门名称不能为空");
			return false;
		}
		if($('#_OrganizationCode').val() == ''){
			alert("部门编号不能为空");
			return false;
		}
		
		var zTree = $.fn.zTree.getZTreeObj("treeDemo");
		//nodes = zTree.getSelectedNodes(); 
		  
		$.ajax({
			url:"system/addOrg",
			type : "POST",
			dataType:'json',
			data: {"parentId":currentNode.id, 
				"organizationCode":$('#_OrganizationCode').val(), 
				"organizationName":$('#_OrganizationName').val(),
				"memo":$('#_Memo').val()},
			error: function(XMLHttpRequest, textStatus, errorThrown){
				alert(textStatus);
			},
			success: function(data){
				if(data.msg == 'ins_success'){
					zTree.addNodes(currentNode, {
						id:data.org.organizationId, 
						pId:data.org.parentId, 
						name: data.org.organizationName,
						code: data.org.organizationCode,
						memo: data.org.memo
					});
					
					alert("新增成功");
					$("#myModal_add").modal('hide');
				}else if(data.msg == 'ins_error'){
					alert("新增失败");
				}else{
					alert(data.msg);
				}
				
			}
		});
	}
	//修改节点
	function editTreeNode(){
		if($('#orgNameForModify').val() == ''){
			alert("部门名称不能为空");
			return false;
		}
		if($('#orgCodeForModify').val() == ''){
			alert("部门编号不能为空");
			return false;
		}
		
	    var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	    //nodes = zTree.getSelectedNodes();
		
		$.ajax({
			  url:"system/renameOrg",
			  type : "POST",
			  dataType:'json',
			  data: {"organizationId":currentNode.id, 
				  "organizationName":$('#orgNameForModify').val(),
				  "organizationCode":$('#orgCodeForModify').val(),
				  "memo":$('#memoForModify').val()},
			  error: function(XMLHttpRequest, textStatus, errorThrown){
				   alert(textStatus);
			  },
			  success: function(data){
				  if(data.msg == 'upd_success'){
					  //zTree.editName(nodes[0]);
					  currentNode.name = $('#orgNameForModify').val();
					  currentNode.code = $('#orgCodeForModify').val();
					  currentNode.memo = $('#memoForModify').val();
					  zTree.updateNode(currentNode);					 
					  alert("修改成功");
					  $("#myModal_xiugai").modal('hide');					
					  //刷新人员列表
					  $('#personTable').dataTable().api().ajax.reload();
				  }else if(data.msg == 'upd_error'){
					  alert("修改失败");
				  }else{
					  alert(data.msg);
				  }
			  }
		});
    }
	
	function removeTreeNode() {
		hideRMenu();
		
	    var zTree = $.fn.zTree.getZTreeObj("treeDemo");
		//var nodes = zTree.getSelectedNodes();
		//if (nodes && nodes.length <= 0) {
		//	return false;
		//}
		
		if (currentNode.children && currentNode.children.length > 0) {
			alert("存在子组织,不能删除");
			return false;	//不能有子节点
		}
		
		if(!confirm('确定要删除吗？')){
			return false;
		}
		
		$.ajax({
			url:"system/deleteOrganization",
			type : "POST",
			dataType:'json',
			data: {"organizationId":currentNode.id},
			error: function(XMLHttpRequest, textStatus, errorThrown){
				alert(textStatus);
			},
			success: function(data){
				if(data.success){
					zTree.removeNode(currentNode);
				}
				
				alert(data.msg);
			}
		});
	}

	function loadData(){
		//组织架构树引用json
		var zNodes =[];
		
		$.ajax({
		  url:"system/loadTree",
		  //url:"static/json/system/loadTree.json",
		  type : "POST",
		  dataType:'json',
		  error: function(XMLHttpRequest, textStatus, errorThrown){
			   alert(textStatus);
		  },
		  success: function(data){
			  var strs = data.data;
			  for(var i = 0; i < strs.length; i++){
				  var obj = new Object();
				  obj.id = strs[i].organizationId;
				  obj.pId = strs[i].parentId;
				  obj.name = strs[i].organizationName;
				  obj.code = strs[i].organizationCode;
				  obj.memo = strs[i].memo;
          obj.authorized = strs[i].authorized?true:false;
          if( obj.pId == 0 ) obj.open = true;
				  zNodes.push(obj);
			  }
			  $.fn.zTree.init($("#treeDemo"), setting, zNodes);
			  //$("#m_add").bind("click", {isParent:false}, add);f
			  //$("#callbackTrigger").bind("change", {}, setTrigger);
			  appendSelectedEvent();
		  }
		});
	}
	
	function appendSelectedEvent(){
		//选中效果
		$(".dd-handle1").each(function() {
			$(this).click(function(){
				$(".dd-handle1").addClass("role_hover2").addClass("role_hover1").removeClass("role_hover3");
				$(this).addClass("role_hover3").removeClass("role_hover2").removeClass("role_hover1");
				/*$(".org_information").show();*/
			});
		});
	}

	//function showLog(str) {
	//	if (!log) log = $("#log");
	//	log.append("<li class='"+className+"'>"+str+"</li>");
	//	if(log.children("li").length > 8) {
	//		log.get(0).removeChild(log.children("li")[0]);
	//	}
	//}
	//function getTime() {
	//	var now= new Date(),
	//	h=now.getHours(),
	//	m=now.getMinutes(),
	//	s=now.getSeconds(),
	//	ms=now.getMilliseconds();
	//	return (h+":"+m+":"+s+ " " +ms);
	//}
	//function setTrigger() {
	//	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	//	zTree.setting.edit.drag.autoExpandTrigger = $("#callbackTrigger").attr("checked");
	//}
</script>
<!--树形结构结束-->

<!--取消效果-->
<script>
  $(".guanbi").click(function(){
	 $("#myModal7").modal('hide');
	 $(".content_clear").val("");
	 // $("#myModal_add").modal('hide');
	 // $("#myModal_xiugai").modal('hide');
  });
</script>
</body>
</html>