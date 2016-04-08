<%@ page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html>
<head>
<base href="${pageContext.request.contextPath}/">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge">

<!-- Page title -->
<title>Jabava角色管理界面</title>

<!-- Place favicon.ico and apple-touch-icon.png in the root directory -->
<!--<link rel="shortcut icon" type="image/ico" href="favicon.ico" />-->

<!-- Vendor styles -->
<link rel="stylesheet" href="static/bootstrap/vendor/fontawesome/css/font-awesome.css" />
<link rel="stylesheet" href="static/bootstrap/vendor/metisMenu/dist/metisMenu.css" />
<link rel="stylesheet" href="static/bootstrap/vendor/animate.css/animate.css" />
<link rel="stylesheet" href="static/bootstrap/vendor/bootstrap/dist/css/bootstrap.css" />

<!-- for data table -->
<link rel="stylesheet" href="static/bootstrap/vendor/datatables_plugins/integration/bootstrap/3/dataTables.bootstrap.css" />

<!-- for alert -->
<link rel="stylesheet" href="static/bootstrap/vendor/sweetalert/lib/sweet-alert.css" />

<!-- App styles -->
<link rel="stylesheet" href="static/bootstrap/fonts/pe-icon-7-stroke/css/pe-icon-7-stroke.css" />
<link rel="stylesheet" href="static/bootstrap/fonts/pe-icon-7-stroke/css/helper.css" />
<link rel="stylesheet" href="static/bootstrap/styles/static_custom.css">
<link rel="stylesheet" href="static/bootstrap/styles/style.css">

<!--zTree-->
<link rel="stylesheet" href="static/css/zTreeStyle/zTreeStyle.css" type="text/css">
<!--for 临时改变-->
<link rel="stylesheet" href="static/css/user.css">



</head>
<body>

	<!-- Simple splash screen-->
	<jsp:include flush="true" page="../comment/splashscreen.div.jsp"></jsp:include>
    <!--引入头文件 开始--> 
    <jsp:include flush="true" page="../comment/header.div.jsp"></jsp:include>
    <!--引入头文件 结束--> 
    <!--引入菜单文件 开始--> 
    <jsp:include flush="true" page="../comment/menu.div.jsp"></jsp:include>
    <!--引入菜单文件 结束--> 
    
    <!-- 放主要内容  开始-->
	<!-- Main Wrapper -->
	<div id="wrapper">
		<div class="normalheader transition animated fadeIn small-header">
			<div class="hpanel">
				<div class="panel-body">
				
					<div id="hbreadcrumb" class="pull-right m-t-lg">
						<ol class="hbreadcrumb breadcrumb">
							<li><a href="to_index?jump=1">首页</a></li>
							<li><span>用户管理</span></li>
							<li class="active"><span>角色管理</span></li>
						</ol>
					</div>
					<h2 class="font-light m-b-xs">角色管理</h2>
					<small>待 定</small>
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
         
                        <!--新增员工弹框-->    
                        <div class="modal fade hmodal-success form-row" id="myModal7" tabindex="-1" role="dialog"  aria-hidden="true">
                         <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="color-line"></div>
                                <div class="modal-header">
                                 <div class="row">
                                  <div class="col-sm-12">
                                  <form role="form" class=" form-horizontal formclass">
                                     <div class="form-group lmaigin">
                                        <label for="exampleInputName2" class="col-lg-3">角色名称：</label>
                                        <div class="col-lg-8">
                                        <input type="text" class="form-control" id="exampleInputName2" value="">
                                        </div>
                                     </div>
                                        <div class="form-group">
                                        <label for="exampleInputName8" class="col-lg-3">备注：</label>
                                        <div class="col-lg-8">
                                        <textarea type="text" class="form-control" id="exampleInputName8"></textarea>
                                        </div>
                                        </div>
                                        <center>
                                          <!--<button class="btn btn-primary show_tow" type="button" id="show">查询</button>-->
                                          <button class="btn btn-primary" type="button">保存</button>
                                          &nbsp;
                                          <button class="btn btn-primary" type="button" data-dismiss="modal">取消</button>
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
                                     <div class="col-sm-12">
                                     <form role="form" class=" form-horizontal formclass">
                                        <div class="form-group lmaigin">
                                           <label for="exampleInputName2" class="col-lg-3">角色名称：</label>
                                           <div class="col-lg-8">
                                           <input type="text" class="form-control" id="exampleInputName2" value="普通用户">
                                           </div>
                                        </div>
                                           <div class="form-group">
                                           <label for="exampleInputName8" class="col-lg-3">备注：</label>
                                           <div class="col-lg-8">
                                           <textarea type="text" class="form-control" id="exampleInputName8" >您好！！！</textarea>
                                           </div>
                                           </div>
                                           <center>
                                             <!--<button class="btn btn-primary show_tow" type="button" id="show">查询</button>-->
                                             <button class="btn btn-primary" type="button">保存</button>
                                             &nbsp;
                                             <button class="btn btn-primary" type="button" data-dismiss="modal">取消</button>
                                           </center>
                                     </form>
                                     </div>
                                   </div>   
                               </div>
                           </div>
                         </div>
                       </div>
                        <!--修改弹框end-->
                
                        <!--分配人员弹窗开始-->
                        <div class="modal fade hmodal-success form-row" id="myModal10" tabindex="-1" role="dialog"  aria-hidden="true">
                        <div class="modal-dialog">
                          <div class="modal-content">
                            <div class="color-line clearfix role_guanbi_size"><span class="pe-7s-close-circle margin_top margin_right role_fr guanbi"></span></div>
                            <div class="modal-header">
                              <div class="row">
                                 
                                <div class="col-sm-12 role_algin">
                                   <div class="form-group">
                                    <label class="col-lg-3 role_algin_tow">系统管理员</label>
                                    <label class="col-lg-9">&nbsp;</label>
                                  </div>
                                  <div class="form-group clearfix role_jianjie">
                                    <label class="col-lg-12 role_algin_tow role_font_weight">主要负网络的网络设备和服务器系统的设计、安装、配置、管理。。。</label>
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
                                        <label class="col-lg-3">张三<span class="pe-7s-close-circle role_span_margin" onClick="delhtml(0)"></span></label>
                                        <label class="col-lg-3">李四<span class="pe-7s-close-circle role_span_margin" onClick="delhtml(1)"></span></label>
                                        <label class="col-lg-3">王五<span class="pe-7s-close-circle role_span_margin" onClick="delhtml(2)"></span></label>
                                        <label class="col-lg-3">朱七<span class="pe-7s-close-circle role_span_margin" onClick="delhtml(3)"></span></label>
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
                                        <label class="col-lg-3">Jerry<span class="pe-7s-check role_span_margin" onClick="delhtml_one(0)"></span></label>
                                        <label class="col-lg-3">Tom<span class="pe-7s-check role_span_margin" onClick="delhtml_one(1)"></span></label>
                                        <label class="col-lg-3">Marry<span class="pe-7s-check role_span_margin" onClick="delhtml_one(2)"></span></label>
                                        <label class="col-lg-3">Jason<span class="pe-7s-check role_span_margin" onClick="delhtml_one(3)"></span></label>
                                      </div>
                                    </fieldset>
                                  </form>
                                  <center class="center_top role_jianjie">
                                    <button class="btn btn-primary" type="button">保存</button>
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
                                    <div class="form-group">
                                     <label class="col-lg-3 role_algin_tow">系统管理员</label>
                                     <label class="col-lg-9">&nbsp;</label>
                                   </div>
                                   <div class="form-group clearfix role_jianjie">
                                     <label class="col-lg-12 role_algin_tow role_font_weight">主要负网络的网络设备和服务器系统的设计、安装、配置、管理。。。</label>
                                     <!--<label class="col-lg-8">&nbsp;</label>-->
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
                                       <!--<div class="dd" id="nestable">
                                         <ol class="dd-list">
                                           <li class="dd-item" data-id="1">
                                             <div class="dd-handle1 role_hover">
                                               <label class="button_style"><input type="checkbox"></label>
                                               <label class="font_size"><span class="fa fa-folder-open"></span></label>
                                               系统菜单
                                             </div>
                                             <ol class="dd-list">
                                             
                                               <li class="dd-item" data-id="2">
                                                 <div class="dd-handle1 role_hover">
                                                   <label class="button_style"><input type="checkbox" value=""></label>
                                                   <label class="font_size"><span class="fa fa-folder-open"></span></label>
                                                   企动力客户端
                                                 </div>
                                                   <ol class="dd-list">
                                                     <li class="dd-item" data-id="3">
                                                       <div class="dd-handle1 role_hover">
                                                         <label class="button_style"><input type="checkbox" value=""></label>
                                                         <label class="font_size"><span class="fa fa-folder-open"></span></label>
                                                         合同
                                                       </div>
                                                       <ol class="dd-list">
                                                         <li class="dd-item" data-id="4">
                                                           <div class="dd-handle1 role_hover">
                                                             <label class="button_style"><input type="checkbox" value=""></label>
                                                            <label class="font_size_tow"><span class="fa fa-file-o"></span></label>
                                                             合同
                                                           </div>
                                                         </li>
                                                       </ol>
                                                     </li>
                                                     <li class="dd-item" data-id="4">
                                                       <div class="dd-handle1 role_hover">
                                                         <label class="button_style"><input type="checkbox" value=""></label>
                                                         <label class="font_size"><span class="fa fa-folder-open"></span></label>
                                                         材料收缴
                                                       </div>
                                                       <ol class="dd-list">
                                                         <li class="dd-item" data-id="5">
                                                           <div class="dd-handle1 role_hover">
                                                             <label class="button_style"><input type="checkbox" value=""></label>
                                                             <label class="font_size_tow"><span class="fa fa-file-o"></span></label>
                                                             材料收缴
                                                           </div>
                                                         </li>
                                                       </ol>
                                                     </li>
                                                     <li class="dd-item" data-id="6">
                                                       <div class="dd-handle1 role_hover">
                                                         <label class="button_style"><input type="checkbox" value=""></label>
                                                         <label class="font_size"><span class="fa fa-folder-open"></span></label>
                                                         雇员订单查询
                                                       </div>
                                                       <ol class="dd-list">
                                                         <li class="dd-item" data-id="7">
                                                           <div class="dd-handle1 role_hover">
                                                             <label class="button_style"><input type="checkbox" value=""></label>
                                                             <label class="font_size_tow"><span class="fa fa-file-o"></span></label>
                                                             雇员订单查询
                                                           </div>
                                                         </li>
                                                       </ol>
                                                     </li>
                                                     <li class="dd-item" data-id="8">
                                                       <div class="dd-handle1 role_hover">
                                                         <label class="button_style"><input type="checkbox" value=""></label>
                                                         <label class="font_size"><span class="fa fa-folder-open"></span></label>
                                                         雇员人数查询
                                                       </div>
                                                       <ol class="dd-list">
                                                         <li class="dd-item" data-id="9">
                                                           <div class="dd-handle1 role_hover">
                                                             <label class="button_style"><input type="checkbox" value=""></label>
                                                             <label class="font_size_tow"><span class="fa fa-file-o"></span></label>
                                                             雇员人数查询
                                                           </div>
                                                         </li>
                                                       </ol>
                                                     </li>
                                                     <li class="dd-item" data-id="11">
                                                       <div class="dd-handle1 role_hover">
                                                         <label class="button_style"><input type="checkbox" value=""></label>
                                                         <label class="font_size"><span class="fa fa-folder-open"></span></label>
                                                         账单查询
                                                       </div>
                                                     </li>
                                                     <li class="dd-item" data-id="12">
                                                       <div class="dd-handle1 role_hover">
                                                         <label class="button_style"><input type="checkbox" value=""></label>
                                                         <label class="font_size"><span class="fa fa-folder-open"></span></label>
                                                         雇员劳动合同查询
                                                       </div>
                                                     </li>
                                                     <li class="dd-item" data-id="13">
                                                       <div class="dd-handle1 role_hover">
                                                         <label class="button_style"><input type="checkbox" value=""></label>
                                                         <label class="font_size"><span class="fa fa-folder-open"></span></label>
                                                         工资单查询
                                                       </div>
                                                     </li>
                                                     <li class="dd-item" data-id="14">
                                                       <div class="dd-handle1 role_hover">
                                                         <label class="button_style"><input type="checkbox" value=""></label>
                                                         <label class="font_size"><span class="fa fa-folder-open"></span></label>
                                                         社保公积金
                                                       </div>
                                                     </li>
                                                     <li class="dd-item" data-id="15">
                                                       <div class="dd-handle1 role_hover">
                                                         <label class="button_style"><input type="checkbox" value=""></label>
                                                         <label class="font_size"><span class="fa fa-folder-open"></span></label>
                                                         政策包查询
                                                       </div>
                                                     </li>
                                                     
                                                   </ol>
                                               </li>
                                               <li class="dd-item" data-id="16">
                                                 <div class="dd-handle1 role_hover">
                                                   <label class="button_style"><input type="checkbox" value=""></label>
                                                   <label class="font_size"><span class="fa fa-folder-open"></span></label>
                                                   人事管理
                                                 </div>
                                               </li>
                                               <li class="dd-item" data-id="17">
                                                 <div class="dd-handle1 role_hover">
                                                   <label class="button_style"><input type="checkbox" value=""></label>
                                                   <label class="font_size"><span class="fa fa-folder-open"></span></label>
                                                   薪资管理
                                                 </div>
                                               </li>
                                               <li class="dd-item" data-id="18">
                                                 <div class="dd-handle1 role_hover">
                                                   <label class="button_style"><input type="checkbox" value=""></label>
                                                   <label class="font_size"><span class="fa fa-folder-open"></span></label>
                                                   统计报表
                                                 </div>
                                               </li>
                                             </ol>
                                           </li>
                                         </ol>
                                       </div>-->
                                       <!--按钮权限结束-->
                                       <!--菜单权限开始-->
                                       <div class="dd" id="nestable_tow" style="display:none">
                                         <ul id="treeDemo2" class="ztree"></ul>
                                         <!--<ol class="dd-list">
                                           <li class="dd-item" data-id="1">
                                             <div class="dd-handle1 role_hover">
                                               <label class="button_style"><input type="checkbox" value=""></label>
                                               <label class="font_size"><span class="fa fa-folder-open"></span></label>
                                               系统菜单
                                             </div>
                                             <ol class="dd-list">
                                             
                                               <li class="dd-item" data-id="2">
                                                 <div class="dd-handle1 role_hover">
                                                   <label class="button_style"><input type="checkbox" value=""></label>
                                                   <label class="font_size"><span class="fa fa-folder-open"></span></label>
                                                   企动力客户端
                                                 </div>
                                                   <ol class="dd-list">
                                                     <li class="dd-item" data-id="3">
                                                       <div class="dd-handle1 role_hover">
                                                         <label class="button_style"><input type="checkbox" value=""></label>
                                                         <label class="font_size_tow"><span class="fa fa-file-o"></span></label>
                                                         合同
                                                       </div>
                                                     </li>
                                                     <li class="dd-item" data-id="4">
                                                       <div class="dd-handle1 role_hover">
                                                         <label class="button_style"><input type="checkbox" value=""></label>
                                                         <label class="font_size_tow"><span class="fa fa-file-o"></span></label>
                                                         材料收缴
                                                       </div>
                                                     </li>
                                                     <li class="dd-item" data-id="6">
                                                       <div class="dd-handle1 role_hover">
                                                         <label class="button_style"><input type="checkbox" value=""></label>
                                                         <label class="font_size_tow"><span class="fa fa-file-o"></span></label>
                                                         雇员订单查询
                                                       </div>
                                                     </li>
                                                     <li class="dd-item" data-id="8">
                                                       <div class="dd-handle1 role_hover">
                                                         <label class="button_style"><input type="checkbox" value=""></label>
                                                         <label class="font_size_tow"><span class="fa fa-file-o"></span></label>
                                                         雇员人数查询
                                                       </div>
                                                     </li>
                                                     <li class="dd-item" data-id="11">
                                                       <div class="dd-handle1 role_hover">
                                                         <label class="button_style"><input type="checkbox" value=""></label>
                                                         <label class="font_size_tow"><span class="fa fa-file-o"></span></label>
                                                         账单查询
                                                       </div>
                                                     </li>
                                                     <li class="dd-item" data-id="12">
                                                       <div class="dd-handle1 role_hover">
                                                         <label class="button_style"><input type="checkbox" value=""></label>
                                                         <label class="font_size_tow"><span class="fa fa-file-o"></span></label>
                                                         雇员劳动合同查询
                                                       </div>
                                                     </li>
                                                     <li class="dd-item" data-id="13">
                                                       <div class="dd-handle1 role_hover">
                                                         <label class="button_style"><input type="checkbox" value=""></label>
                                                         <label class="font_size_tow"><span class="fa fa-file-o"></span></label>
                                                         工资单查询
                                                       </div>
                                                     </li>
                                                     <li class="dd-item" data-id="14">
                                                       <div class="dd-handle1 role_hover">
                                                         <label class="button_style"><input type="checkbox" value=""></label>
                                                         <label class="font_size_tow"><span class="fa fa-file-o"></span></label>
                                                         社保公积金
                                                       </div>
                                                     </li>
                                                     <li class="dd-item" data-id="15">
                                                       <div class="dd-handle1 role_hover">
                                                         <label class="button_style"><input type="checkbox" value=""></label>
                                                         <label class="font_size_tow"><span class="fa fa-file-o"></span></label>
                                                         政策包查询
                                                       </div>
                                                     </li>
                                                     
                                                   </ol>
                                               </li>
                                               <li class="dd-item" data-id="16">
                                                 <div class="dd-handle1 role_hover">
                                                   <label class="button_style"><input type="checkbox" value=""></label>
                                                   <label class="font_size"><span class="fa fa-folder-open"></span></label>
                                                   人事管理
                                                 </div>
                                               </li>
                                               <li class="dd-item" data-id="17">
                                                 <div class="dd-handle1 role_hover">
                                                   <label class="button_style"><input type="checkbox" value=""></label>
                                                   <label class="font_size"><span class="fa fa-folder-open"></span></label>
                                                   薪资管理
                                                 </div>
                                               </li>
                                               <li class="dd-item" data-id="18">
                                                 <div class="dd-handle1 role_hover">
                                                   <label class="button_style"><input type="checkbox" value=""></label>
                                                   <label class="font_size"><span class="fa fa-folder-open"></span></label>
                                                   统计报表
                                                 </div>
                                               </li>
                                             </ol>
                                           </li>
                                         </ol>-->
                                       </div>
                                       
                                       <center class="center_top role_jianjie">
                                         <button class="btn btn-primary" type="button">保存</button>
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
                         <table id="example2" class="table table-striped table-bordered table-hover text_align" style="width:100%">
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
                        <tr>
                            <td>普通用户</td>
                            <td>您好！！！</td>
                            <td>公司系统管理</td>
                            <td>2015-12-03 13:46</td>
                            <th>                
                            <div class="btn-group">
                              <button data-toggle="dropdown" class="btn btn-info btn-xs dropdown-toggle">操作<span class="caret"></span></button>
                              <ul class="dropdown-menu">
                                  <li><a href="#" data-target="#myModal10" data-toggle="modal"><i class="fa fa-group"></i> 分配人员</a></li>
                                  <li><a href="#" data-target="#myModal9" data-toggle="modal"><i class="fa fa-gear "></i> 权限设置</a></li>
                                  <li><a href="#" data-target="#myModal8" data-toggle="modal"><i class="fa fa-check"></i> 修改</a></li>
                                  <li><a class="demo4"><i class="fa fa-times"></i> 删除</a></li>                        
                              </ul>
                            </div>
                            </th>
                        </tr>
                        <tr>
                            <td>普通用户</td>
                            <td>&nbsp;</td>
                            <td>公司系统管理</td>
                            <td>2015-12-03 13:46</td>
                            <th>                
                            <div class="btn-group">
                              <button data-toggle="dropdown" class="btn btn-info btn-xs dropdown-toggle">操作<span class="caret"></span></button>
                              <ul class="dropdown-menu">
                                  <li><a href="#" data-target="#myModal10" data-toggle="modal"><i class="fa fa-group"></i> 分配人员</a></li>
                                  <li><a href="#" data-target="#myModal9" data-toggle="modal"><i class="fa fa-gear "></i> 权限设置</a></li>
                                  <li><a href="#" data-target="#myModal8" data-toggle="modal"><i class="fa fa-check"></i> 修改</a></li>
                                  <li><a class="demo4"><i class="fa fa-times"></i> 删除</a></li>                        
                              </ul>
                            </div>
                            </th>
                        </tr>
                        <tr>
                            <td>普通用户</td>
                            <td>&nbsp;</td>
                            <td>公司系统管理</td>
                            <td>2015-12-03 13:46</td>
                            <th>                
                            <div class="btn-group">
                              <button data-toggle="dropdown" class="btn btn-info btn-xs dropdown-toggle">操作<span class="caret"></span></button>
                              <ul class="dropdown-menu">
                                  <li><a href="#" data-target="#myModal10" data-toggle="modal"><i class="fa fa-group"></i> 分配人员</a></li>
                                  <li><a href="#" data-target="#myModal9" data-toggle="modal"><i class="fa fa-gear "></i> 权限设置</a></li>
                                  <li><a href="#" data-target="#myModal8" data-toggle="modal"><i class="fa fa-check"></i> 修改</a></li>
                                  <li><a class="demo4"><i class="fa fa-times"></i> 删除</a></li>                        
                              </ul>
                            </div>
                            </th>
                        </tr>
                        <tr>
                            <td>普通用户</td>
                            <td>&nbsp;</td>
                            <td>公司系统管理</td>
                            <td>2015-12-03 13:46</td>
                            <th>                
                            <div class="btn-group">
                              <button data-toggle="dropdown" class="btn btn-info btn-xs dropdown-toggle">操作<span class="caret"></span></button>
                              <ul class="dropdown-menu">
                                  <li><a href="#" data-target="#myModal10" data-toggle="modal"><i class="fa fa-group"></i> 分配人员</a></li>
                                  <li><a href="#" data-target="#myModal9" data-toggle="modal"><i class="fa fa-gear "></i> 权限设置</a></li>
                                  <li><a href="#" data-target="#myModal8" data-toggle="modal"><i class="fa fa-check"></i> 修改</a></li>
                                  <li><a class="demo4"><i class="fa fa-times"></i> 删除</a></li>                        
                              </ul>
                            </div>
                            </th>
                        </tr>
                        <tr>
                            <td>普通用户</td>
                            <td>&nbsp;</td>
                            <td>公司系统管理</td>
                            <td>2015-12-03 13:46</td>
                            <th>                
                            <div class="btn-group">
                              <button data-toggle="dropdown" class="btn btn-info btn-xs dropdown-toggle">操作<span class="caret"></span></button>
                              <ul class="dropdown-menu">
                                  <li><a href="#" data-target="#myModal10" data-toggle="modal"><i class="fa fa-group"></i> 分配人员</a></li>
                                  <li><a href="#" data-target="#myModal9" data-toggle="modal"><i class="fa fa-gear "></i> 权限设置</a></li>
                                  <li><a href="#" data-target="#myModal8" data-toggle="modal"><i class="fa fa-check"></i> 修改</a></li>
                                  <li><a class="demo4"><i class="fa fa-times"></i> 删除</a></li>                        
                              </ul>
                            </div>
                            </th>
                        </tr>
                        <tr>
                            <td>普通用户</td>
                            <td>&nbsp;</td>
                            <td>公司系统管理</td>
                            <td>2015-12-03 13:46</td>
                            <th>                
                            <div class="btn-group">
                              <button data-toggle="dropdown" class="btn btn-info btn-xs dropdown-toggle">操作<span class="caret"></span></button>
                              <ul class="dropdown-menu">
                                  <li><a href="#" data-target="#myModal10" data-toggle="modal"><i class="fa fa-group"></i> 分配人员</a></li>
                                  <li><a href="#" data-target="#myModal9" data-toggle="modal"><i class="fa fa-gear "></i> 权限设置</a></li>
                                  <li><a href="#" data-target="#myModal8" data-toggle="modal"><i class="fa fa-check"></i> 修改</a></li>
                                  <li><a class="demo4"><i class="fa fa-times"></i> 删除</a></li>                        
                              </ul>
                            </div>
                            </th>
                        </tr>
                         <tr>
                            <td>普通用户</td>
                            <td>&nbsp;</td>
                            <td>公司系统管理</td>
                            <td>2015-12-03 13:46</td>
                            <th>                
                            <div class="btn-group">
                              <button data-toggle="dropdown" class="btn btn-info btn-xs dropdown-toggle">操作<span class="caret"></span></button>
                              <ul class="dropdown-menu">
                                  <li><a href="#" data-target="#myModal10" data-toggle="modal"><i class="fa fa-group"></i> 分配人员</a></li>
                                  <li><a href="#" data-target="#myModal9" data-toggle="modal"><i class="fa fa-gear "></i> 权限设置</a></li>
                                  <li><a href="#" data-target="#myModal8" data-toggle="modal"><i class="fa fa-check"></i> 修改</a></li>
                                  <li><a class="demo4"><i class="fa fa-times"></i> 删除</a></li>                        
                              </ul>
                            </div>
                            </th>
                        </tr>
                        <tr>
                            <td>普通用户</td>
                            <td>&nbsp;</td>
                            <td>公司系统管理</td>
                            <td>2015-12-03 13:46</td>
                            <th>                
                            <div class="btn-group">
                              <button data-toggle="dropdown" class="btn btn-info btn-xs dropdown-toggle">操作<span class="caret"></span></button>
                              <ul class="dropdown-menu">
                                  <li><a href="#" data-target="#myModal10" data-toggle="modal"><i class="fa fa-group"></i> 分配人员</a></li>
                                  <li><a href="#" data-target="#myModal9" data-toggle="modal"><i class="fa fa-gear "></i> 权限设置</a></li>
                                  <li><a href="#" data-target="#myModal8" data-toggle="modal"><i class="fa fa-check"></i> 修改</a></li>
                                  <li><a class="demo4"><i class="fa fa-times"></i> 删除</a></li>                        
                              </ul>
                            </div>
                            </th>
                        </tr>
                         <tr>
                            <td>普通用户</td>
                            <td>&nbsp;</td>
                            <td>公司系统管理</td>
                            <td>2015-12-03 13:46</td>
                            <th>                
                            <div class="btn-group">
                              <button data-toggle="dropdown" class="btn btn-info btn-xs dropdown-toggle">操作<span class="caret"></span></button>
                              <ul class="dropdown-menu">
                                  <li><a href="#" data-target="#myModal10" data-toggle="modal"><i class="fa fa-group"></i> 分配人员</a></li>
                                  <li><a href="#" data-target="#myModal9" data-toggle="modal"><i class="fa fa-gear "></i> 权限设置</a></li>
                                  <li><a href="#" data-target="#myModal8" data-toggle="modal"><i class="fa fa-check"></i> 修改</a></li>
                                  <li><a class="demo4"><i class="fa fa-times"></i> 删除</a></li>                        
                              </ul>
                            </div>
                            </th>
                        </tr>
                        <tr>
                            <td>普通用户</td>
                            <td>&nbsp;</td>
                            <td>公司系统管理</td>
                            <td>2015-12-03 13:46</td>
                            <th>                
                            <div class="btn-group">
                              <button data-toggle="dropdown" class="btn btn-info btn-xs dropdown-toggle">操作<span class="caret"></span></button>
                              <ul class="dropdown-menu">
                                  <li><a href="#" data-target="#myModal10" data-toggle="modal"><i class="fa fa-group"></i> 分配人员</a></li>
                                  <li><a href="#" data-target="#myModal9" data-toggle="modal"><i class="fa fa-gear "></i> 权限设置</a></li>
                                  <li><a href="#" data-target="#myModal8" data-toggle="modal"><i class="fa fa-check"></i> 修改</a></li>
                                  <li><a class="demo4"><i class="fa fa-times"></i> 删除</a></li>                        
                              </ul>
                            </div>
                            </th>
                        </tr>
                        <tr>
                            <td>普通用户</td>
                            <td>&nbsp;</td>
                            <td>公司系统管理</td>
                            <td>2015-12-03 13:46</td>
                            <th>                
                            <div class="btn-group">
                              <button data-toggle="dropdown" class="btn btn-info btn-xs dropdown-toggle">操作<span class="caret"></span></button>
                              <ul class="dropdown-menu">
                                  <li><a href="#" data-target="#myModal10" data-toggle="modal"><i class="fa fa-group"></i> 分配人员</a></li>
                                  <li><a href="#" data-target="#myModal9" data-toggle="modal"><i class="fa fa-gear "></i> 权限设置</a></li>
                                  <li><a href="#" data-target="#myModal8" data-toggle="modal"><i class="fa fa-check"></i> 修改</a></li>
                                  <li><a class="demo4"><i class="fa fa-times"></i> 删除</a></li>                        
                              </ul>
                            </div>
                            </th>
                        </tr>
                        <tr>
                            <td>普通用户</td>
                            <td>&nbsp;</td>
                            <td>公司系统管理</td>
                            <td>2015-12-03 13:46</td>
                            <th>                
                            <div class="btn-group">
                              <button data-toggle="dropdown" class="btn btn-info btn-xs dropdown-toggle">操作<span class="caret"></span></button>
                              <ul class="dropdown-menu">
                                  <li><a href="#" data-target="#myModal10" data-toggle="modal"><i class="fa fa-group"></i> 分配人员</a></li>
                                  <li><a href="#" data-target="#myModal9" data-toggle="modal"><i class="fa fa-gear "></i> 权限设置</a></li>
                                  <li><a href="#" data-target="#myModal8" data-toggle="modal"><i class="fa fa-check"></i> 修改</a></li>
                                  <li><a class="demo4"><i class="fa fa-times"></i> 删除</a></li>                        
                              </ul>
                            </div>
                            </th>
                        </tr>
                        <tr>
                            <td>普通用户</td>
                            <td>&nbsp;</td>
                            <td>公司系统管理</td>
                            <td>2015-12-03 13:46</td>
                            <th>                
                            <div class="btn-group">
                              <button data-toggle="dropdown" class="btn btn-info btn-xs dropdown-toggle">操作<span class="caret"></span></button>
                              <ul class="dropdown-menu">
                                  <li><a href="#" data-target="#myModal10" data-toggle="modal"><i class="fa fa-group"></i> 分配人员</a></li>
                                  <li><a href="#" data-target="#myModal9" data-toggle="modal"><i class="fa fa-gear "></i> 权限设置</a></li>
                                  <li><a href="#" data-target="#myModal8" data-toggle="modal"><i class="fa fa-check"></i> 修改</a></li>
                                  <li><a class="demo4"><i class="fa fa-times"></i> 删除</a></li>                        
                              </ul>
                            </div>
                            </th>
                        </tr>
                         <tr>
                            <td>普通用户</td>
                            <td>&nbsp;</td>
                            <td>公司系统管理</td>
                            <td>2015-12-03 13:46</td>
                            <th>                
                            <div class="btn-group">
                              <button data-toggle="dropdown" class="btn btn-info btn-xs dropdown-toggle">操作<span class="caret"></span></button>
                              <ul class="dropdown-menu">
                                  <li><a href="#" data-target="#myModal10" data-toggle="modal"><i class="fa fa-group"></i> 分配人员</a></li>
                                  <li><a href="#" data-target="#myModal9" data-toggle="modal"><i class="fa fa-gear "></i> 权限设置</a></li>
                                  <li><a href="#" data-target="#myModal8" data-toggle="modal"><i class="fa fa-check"></i> 修改</a></li>
                                  <li><a class="demo4"><i class="fa fa-times"></i> 删除</a></li>                        
                              </ul>
                            </div>
                            </th>
                        </tr>
                        <tr>
                            <td>普通用户</td>
                            <td>&nbsp;</td>
                            <td>公司系统管理</td>
                            <td>2015-12-03 13:46</td>
                            <th>                
                            <div class="btn-group">
                              <button data-toggle="dropdown" class="btn btn-info btn-xs dropdown-toggle">操作<span class="caret"></span></button>
                              <ul class="dropdown-menu">
                                  <li><a href="#" data-target="#myModal10" data-toggle="modal"><i class="fa fa-group"></i> 分配人员</a></li>
                                  <li><a href="#" data-target="#myModal9" data-toggle="modal"><i class="fa fa-gear "></i> 权限设置</a></li>
                                  <li><a href="#" data-target="#myModal8" data-toggle="modal"><i class="fa fa-check"></i> 修改</a></li>
                                  <li><a class="demo4"><i class="fa fa-times"></i> 删除</a></li>                        
                              </ul>
                            </div>
                            </th>
                        </tr>
                        <tr>
                            <td>普通用户</td>
                            <td>&nbsp;</td>
                            <td>公司系统管理</td>
                            <td>2015-12-03 13:46</td>
                            <th>                
                            <div class="btn-group">
                              <button data-toggle="dropdown" class="btn btn-info btn-xs dropdown-toggle">操作<span class="caret"></span></button>
                              <ul class="dropdown-menu">
                                  <li><a href="#" data-target="#myModal10" data-toggle="modal"><i class="fa fa-group"></i> 分配人员</a></li>
                                  <li><a href="#" data-target="#myModal9" data-toggle="modal"><i class="fa fa-gear "></i> 权限设置</a></li>
                                  <li><a href="#" data-target="#myModal8" data-toggle="modal"><i class="fa fa-check"></i> 修改</a></li>
                                  <li><a class="demo4"><i class="fa fa-times"></i> 删除</a></li>                        
                              </ul>
                            </div>
                            </th>
                        </tr>
                        <tr>
                            <td>普通用户</td>
                            <td>&nbsp;</td>
                            <td>公司系统管理</td>
                            <td>2015-12-03 13:46</td>
                            <th>                
                            <div class="btn-group">
                              <button data-toggle="dropdown" class="btn btn-info btn-xs dropdown-toggle">操作<span class="caret"></span></button>
                              <ul class="dropdown-menu">
                                  <li><a href="#" data-target="#myModal10" data-toggle="modal"><i class="fa fa-group"></i> 分配人员</a></li>
                                  <li><a href="#" data-target="#myModal9" data-toggle="modal"><i class="fa fa-gear "></i> 权限设置</a></li>
                                  <li><a href="#" data-target="#myModal8" data-toggle="modal"><i class="fa fa-check"></i> 修改</a></li>
                                  <li><a class="demo4"><i class="fa fa-times"></i> 删除</a></li>                        
                              </ul>
                            </div>
                            </th>
                        </tr>
                         <tr>
                            <td>普通用户</td>
                            <td>&nbsp;</td>
                            <td>公司系统管理</td>
                            <td>2015-12-03 13:46</td>
                            <th>                
                            <div class="btn-group">
                              <button data-toggle="dropdown" class="btn btn-info btn-xs dropdown-toggle">操作<span class="caret"></span></button>
                              <ul class="dropdown-menu">
                                  <li><a href="#" data-target="#myModal10" data-toggle="modal"><i class="fa fa-group"></i> 分配人员</a></li>
                                  <li><a href="#" data-target="#myModal9" data-toggle="modal"><i class="fa fa-gear "></i> 权限设置</a></li>
                                  <li><a href="#" data-target="#myModal8" data-toggle="modal"><i class="fa fa-check"></i> 修改</a></li>
                                  <li><a class="demo4"><i class="fa fa-times"></i> 删除</a></li>                        
                              </ul>
                            </div>
                            </th>
                        </tr>
                        <tr>
                            <td>普通用户</td>
                            <td>&nbsp;</td>
                            <td>公司系统管理</td>
                            <td>2015-12-03 13:46</td>
                            <th>                
                            <div class="btn-group">
                              <button data-toggle="dropdown" class="btn btn-info btn-xs dropdown-toggle">操作<span class="caret"></span></button>
                              <ul class="dropdown-menu">
                                  <li><a href="#" data-target="#myModal10" data-toggle="modal"><i class="fa fa-group"></i> 分配人员</a></li>
                                  <li><a href="#" data-target="#myModal9" data-toggle="modal"><i class="fa fa-gear "></i> 权限设置</a></li>
                                  <li><a href="#" data-target="#myModal8" data-toggle="modal"><i class="fa fa-check"></i> 修改</a></li>
                                  <li><a class="demo4"><i class="fa fa-times"></i> 删除</a></li>                        
                              </ul>
                            </div>
                            </th>
                        </tr>
                        <tr>
                            <td>普通用户</td>
                            <td>&nbsp;</td>
                            <td>公司系统管理</td>
                            <td>2015-12-03 13:46</td>
                            <th>                
                            <div class="btn-group">
                              <button data-toggle="dropdown" class="btn btn-info btn-xs dropdown-toggle">操作<span class="caret"></span></button>
                              <ul class="dropdown-menu">
                                  <li><a href="#" data-target="#myModal10" data-toggle="modal"><i class="fa fa-group"></i> 分配人员</a></li>
                                  <li><a href="#" data-target="#myModal9" data-toggle="modal"><i class="fa fa-gear "></i> 权限设置</a></li>
                                  <li><a href="#" data-target="#myModal8" data-toggle="modal"><i class="fa fa-check"></i> 修改</a></li>
                                  <li><a class="demo4"><i class="fa fa-times"></i> 删除</a></li>                        
                              </ul>
                            </div>
                            </th>
                        </tr>
                        <tr>
                            <td>普通用户</td>
                            <td>&nbsp;</td>
                            <td>公司系统管理</td>
                            <td>2015-12-03 13:46</td>
                            <th>                
                            <div class="btn-group">
                              <button data-toggle="dropdown" class="btn btn-info btn-xs dropdown-toggle">操作<span class="caret"></span></button>
                              <ul class="dropdown-menu">
                                  <li><a href="#" data-target="#myModal10" data-toggle="modal"><i class="fa fa-group"></i> 分配人员</a></li>
                                  <li><a href="#" data-target="#myModal9" data-toggle="modal"><i class="fa fa-gear "></i> 权限设置</a></li>
                                  <li><a href="#" data-target="#myModal8" data-toggle="modal"><i class="fa fa-check"></i> 修改</a></li>
                                  <li><a class="demo4"><i class="fa fa-times"></i> 删除</a></li>                        
                              </ul>
                            </div>
                            </th>
                        </tr>            
                        </tbody>
                        </table>
                        </div>
       				 </div>
        		</div>
        	</div>
        </div>        
        <!--主要内容结束-->
        
		<!-- 放页脚  开始-->
        <jsp:include flush="true" page="../comment/foot.div.jsp"></jsp:include>
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
	
	<!-- for datatable -->
	<script src="static/bootstrap/vendor/datatables/media/js/jquery.dataTables.min.js"></script>
	<script src="static/bootstrap/vendor/datatables_plugins/integration/bootstrap/3/dataTables.bootstrap.min.js"></script>


	<!-- alert -->
	<script src="static/bootstrap/vendor/sweetalert/lib/sweet-alert.min.js"></script>
    
	<!-- App scripts -->
	<script src="static/bootstrap/scripts/homer.js"></script>
    <script src="static/bootstrap/scripts/charts.js"></script>
    
    
    <!--jabava-->
    <script src="static/js/jabava-1.0.js"></script>
	<script>
	
<!--树形结构-->
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

		var zNodes =[
			{ id:1, pId:0, name:"系统菜单", open:true},
			{ id:11, pId:1, name:" 企动力客户端 ", open:true},
			{ id:111, pId:11, name:"合同", open:true},
			{ id:1111, pId:111, name:"合同"},
			{ id:112, pId:11, name:" 材料收缴 ", open:true},
			{ id:1121, pId:112, name:" 材料收缴 "},
			{ id:113, pId:11, name:" 雇员订单查询 ", open:true},
			{ id:1131, pId:113, name:" 雇员订单查询 "},
			{ id:114, pId:11, name:"  雇员人数查询 ", open:true},
			{ id:1141, pId:114, name:"  雇员人数查询 "},
			{ id:115, pId:11, name:"  账单查询  "},
			{ id:116, pId:11, name:"  雇员劳动合同查询  "},
			{ id:117, pId:11, name:"  工资单查询  "},
			{ id:118, pId:11, name:"   社保公积金  "},
			{ id:119, pId:11, name:"   政策包查询  "},
			{ id:12, pId:1, name:"  人事管理 "},
			{ id:13, pId:1, name:"  薪资管理 "},
			{ id:14, pId:1, name:"  统计报表  "}
			
		];
		var zNodes2 =[
			{ id:1, pId:0, name:"系统菜单", open:true},
			{ id:11, pId:1, name:" 企动力客户端 ", open:true},
			{ id:111, pId:11, name:"合同"},
			{ id:112, pId:11, name:" 材料收缴 ",},
			{ id:113, pId:11, name:" 雇员订单查询 ",},
			{ id:114, pId:11, name:"  雇员人数查询 ",},
			{ id:115, pId:11, name:"  账单查询  "},
			{ id:116, pId:11, name:"  雇员劳动合同查询  "},
			{ id:117, pId:11, name:"  工资单查询  "},
			{ id:118, pId:11, name:"   社保公积金  "},
			{ id:119, pId:11, name:"   政策包查询  "},
			{ id:12, pId:1, name:"  人事管理 "},
			{ id:13, pId:1, name:"  薪资管理 "},
			{ id:14, pId:1, name:"  统计报表  "}
		];
<!--树形结构-->

   $(function () {

/* 	   var table = $('#example2').dataTable() */
    	
      	var table = $('#example2').dataTable({
    		"dom":
    		
    		"<'row'<'col-sm-6'l><'col-sm-5 role_fr'f>>" +
    		"<'row'<'col-sm-12'tr>>" +
    		"<'row'<'col-sm-5'i><'col-sm-7'p>>",
    		
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
				
				
				
	  <!--zTree-->
	  var treeObj=$.fn.zTree.init($("#treeDemo"), setting, zNodes);//初始化tree
	  var treeObj2=$.fn.zTree.init($("#treeDemo2"), setting, zNodes2);//初始化tree

    }); 

</script>

<!--删除效果-->
<script>

    $(function () {

        $('.demo4').click(function () {
            swal({
                        title: "确定要删除该角色吗?",
                        text: "注意：角色删除后将不可修复!",
                        type: "warning",
                        showCancelButton: true,
                        confirmButtonColor: "#DD6B55",
                        confirmButtonText: "是,请删除该角色!",
                        cancelButtonText: "不, 放弃此操作!",
                        closeOnConfirm: false,
                        closeOnCancel: false },
                    function (isConfirm) {
                        if (isConfirm) {
                            swal("删除成功!", "该角色已经被删除.", "success");
                        } else {
                            swal("已取消", "角色未删除。你这逗我玩呢 :)", "error");
                        }
                    });
        });

    });
	
	$(function () {

        $('.demo3').click(function () {
            swal({
                        title: "确定要删除此行吗?",
                        text: "注意：此行删除后不可修复!",
                        type: "warning",
                        showCancelButton: true,
                        confirmButtonColor: "#DD6B55",
                        confirmButtonText: "是,请删除此行!",
                        cancelButtonText: "不, 放弃此操作!",
                        closeOnConfirm: false,
                        closeOnCancel: false },
                    function (isConfirm) {
                        if (isConfirm) {
                            swal("删除成功!", "该行数据已经被删除.", "success");
                        } else {
                            swal("已取消", "该行未删除，吓坏宝宝啦.", "error");
                        }
                });
        });

    });

</script>
<!--表单验证-->
<script>

    $(function(){

        $("#form").validate({
            rules: {
                password: {
                    required: true,
                    minlength: 3
                },
                url: {
                    required: true,
                    url: true
                },
                number: {
                    required: true,
                    number: true
                },
                max: {
                    required: true,
                    maxlength: 4
                }
            },
			messages: {
				Email: {
                    required: "(Please enter your email)",
                    Email: "(请输入有效的电子邮件)"
                },
                number: {
                    required: "(Please enter your phone number)",
                    number: "(请输入有效的数字)"
                },
                last_name: {
                    required: "This is custom message for required",
                    minlength: "This is custom message for min length"
                }
            },
            submitHandler: function(form) {
                form.submit();
            }
        });
		
		$("#form_one").validate({
            rules: {
                password: {
                    required: true,
                    minlength: 3
                },
                url: {
                    required: true,
                    url: true
                },
                number: {
                    required: true,
                    number: true
                },
                max: {
                    required: true,
                    maxlength: 4
                }
            },
			messages: {
				Email: {
                    required: "(Please enter your email)",
                    Email: "(请输入有效的电子邮件)"
                },
                number: {
                    required: "(Please enter your phone number)",
                    number: "(请输入有效的数字)"
                },
                last_name: {
                    required: "This is custom message for required",
                    minlength: "This is custom message for min length"
                }
            },
            submitHandler: function(form) {
                form.submit();
            }
        });
		
		$("#form_tow").validate({
            rules: {
                password: {
                    required: true,
                    minlength: 3
                },
                url: {
                    required: true,
                    url: true
                },
                number: {
                    required: true,
                    number: true
                },
                max: {
                    required: true,
                    maxlength: 4
                }
            },
			messages: {
				Email: {
                    required: "(Please enter your email)",
                    Email: "(请输入有效的电子邮件)"
                },
                number: {
                    required: "(Please enter your phone number)",
                    number: "(请输入有效的数字)"
                },
                last_name: {
                    required: "This is custom message for required",
                    minlength: "This is custom message for min length"
                }
            },
            submitHandler: function(form) {
                form.submit();
            }
        });

        $("#form_2").validate({
            rules: {
                name: {
                    required: true,
                    minlength: 3
                },
                username: {
                    required: true,
                    minlength: 5
                },
                url: {
                    required: true,
                    url: true
                },
                number: {
                    required: true,
                    number: true
                },
                last_name: {
                    required: true,
                    minlength: 6
                }
            },
            messages: {
				Email: {
                    required: "(Please enter your email)",
                    Email: "(请输入有效的电子邮件)"
                },
                number: {
                    required: "(Please enter your phone number)",
                    number: "(请输入有效的数字)"
                },
                last_name: {
                    required: "This is custom message for required",
                    minlength: "This is custom message for min length"
                }
            },
            submitHandler: function(form) {
                form.submit();
            },
            errorPlacement: function(error, element) {
                $( element )
                        .closest( "form" )
                        .find( "label[for='" + element.attr( "id" ) + "']" )
                        .append( error );
            },
            errorElement: "span",
        });


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

</body>
</html>