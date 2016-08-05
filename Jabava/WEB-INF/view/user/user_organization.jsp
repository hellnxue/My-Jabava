<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="com.jabava.utils.RequestUtil"%>
<!DOCTYPE html>
<html>
<head>
<base href="${pageContext.request.contextPath}/">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge">

<!-- Page title -->
<title>用户数据权限界面</title>
<jsp:include flush="true" page="../common/styles.jsp"></jsp:include>
<link rel="stylesheet" href="static/css/zTreeStyle/zTreeStyle.css" type="text/css">
<!--for 临时改变-->
<link rel="stylesheet" href="static/css/user.css">

</head>
<body>

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
        <!-- 放主要内容 -->
        <div class="content animate-panel">
            <div class="row">
                <div class="col-lg-12">
                    <div class="hpanel">
                        <div class="panel-heading">
                            <h4 class="text-center font-bold">
                                用户数据权限
                            </h4>
                        </div>
                        <div class="panel-body note-editor"> 
                            <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
                                <div class="panel-heading">
                                    <h5>hr人员清单</h5>
                                </div>
                                <div class="panel-body">
                                    <div class="list-group scrollspy-example m-b-none" data-panel="user">
                                      <button type="button" class="list-group-item hide" data-user="userId" data-list="user"></button>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
                                <div class="panel-heading">
                                    <h5>可查看数据</h5>
                                </div>
                                <div class="panel-body" style="display:none" data-panel="tree">
                                    <form id="form">
                                        <div class="dd" id="nestable">
                                            <ul id="treeDemo" class="ztree" data-tree="userId"></ul>
                                        </div>
                                        <div class="text-right">
                                            <% if(RequestUtil.hasPower("index_dataright_mr")){ %>
                                            <button type="button" class="btn btn-success" onclick="save($(this))">提　交</button>
                                            <% } %>
                                        </div>
                                    </form>
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
    
    <script src="static/bootstrap/vendor/nestable/jquery.nestable.js"></script>
    <script type="text/javascript" src="static/js/zTreeJs/jquery.ztree.core-3.5.js"></script>
    <script type="text/javascript" src="static/js/zTreeJs/jquery.ztree.excheck-3.5.js"></script>

    <script src="static/bootstrap/vendor/sweetalert/lib/sweet-alert.min.js"></script>
    <!-- App scripts -->
    <script src="static/bootstrap/scripts/homer.js"></script>
    <script src="static/bootstrap/scripts/charts.js"></script>
    
    <script type="text/javascript">
        $(function(){
            listUser();
            treeShow();
        });
        
        function listUser(){
        	$.ajax({
        		cache : true,
        		type : "POST",
        		url : "user/listUser",
        		dataType : 'json',
        		async : false,
        	})
            .done(function(data){
                if(data){
                    $.each(data, function(index, item) {
                        var objUser = $('[data-list="user"]')
                                      .clone()
                                      .removeAttr('data-list')
                                      .removeClass('hide')
                                      .text(item.userCode + " (" + item.userName + ")")
                                      .attr('data-user', item.userId);
                        $('[data-panel="user"]').append(objUser)
                    });
                }
            })
        }
        
        function loadUserOrganizationTree(userId){
            var setting = {
                check: {
                    enable: true,
                    chkStyle: "checkbox",
                    chkboxType: { "Y": "s", "N": "s" } //父级与子集的关系
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
            var zNodes =[];
        	$.ajax({
        		cache : true,
        		type : "GET",
        		url : "user/loadUserOrganizationTree/" + userId,
        		dataType : 'json',
        		async : false,
            })
            .done(function(data){
                if(data){
                    $.each(data,function(index, item) {
                        var obj = new Object();
                        obj.id = item.organizationId;
                        obj.pId = item.parentId;
                        obj.name = item.organizationName;
                        obj.code = item.organizationCode;
                        obj.memo = item.memo;
                        obj.checked = item.selected;
                        if(obj.pId == 0) obj.open = true;
                        zNodes.push(obj);
                    });
                    var treeObj = $.fn.zTree.init($("#treeDemo"), setting, zNodes);//初始化tree
                    $('#treeDemo').attr('data-tree', userId);
                }
            })
        }

        function save(obj){
            var treeObj = $.fn.zTree.getZTreeObj("treeDemo"),
                nodes = treeObj.getCheckedNodes(true),
                userId = obj.parents(form).find('#treeDemo').attr('data-tree'),
                orgIds = [];
                for(var i=0,len=nodes.length;i<len;i++){
                    var nodesId = nodes[i].id;
                    orgIds.push(nodesId)
                }
                
            $.ajax({
	            type: "post",
	            url: "user/saveUserOrganization",
	            dataType : "json",
	            data: { "userId" : userId, "orgIds" : orgIds.length == 0 ? [-1] : orgIds}
            })
            .done(function(data){
                if(data.success == true){
                    swal({
                        title: data.msg,
                        type: 'success'
                    })
                }
            })
        }

        function treeShow(){
            $('[data-panel=user]')
            .find('button')
            .each(function(index, el) {
                $(this).on('click',function(e){
                    var getUserData = $(e.target).attr('data-user');
                    $('[data-panel="tree"]').show()
                    loadUserOrganizationTree(getUserData)
                })
            });  
        }
    </script>
</body>
</html>