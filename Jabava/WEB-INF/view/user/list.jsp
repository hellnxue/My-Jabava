<%@ page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html>
<head>
<base href="${pageContext.request.contextPath}/">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge">

<!-- Page title -->
<title>Jabava用户管理界面</title>

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

<!--多选下拉-->
<link rel="stylesheet" href="static/bootstrap/vendor/xeditable/bootstrap3-editable/css/bootstrap-editable.css" />
<link rel="stylesheet" href="static/bootstrap/vendor/select2-3.5.2/select2.css" />
<link rel="stylesheet" href="static/bootstrap/vendor/select2-bootstrap/select2-bootstrap.css" />
<link rel="stylesheet" href="static/bootstrap/vendor/bootstrap-touchspin/dist/jquery.bootstrap-touchspin.min.css" />
<link rel="stylesheet" href="static/bootstrap/vendor/bootstrap-datepicker-master/dist/css/bootstrap-datepicker3.min.css" />
<link rel="stylesheet" href="static/bootstrap/vendor/awesome-bootstrap-checkbox/awesome-bootstrap-checkbox.css" />	
    
<!-- App styles -->
<link rel="stylesheet" href="static/bootstrap/fonts/pe-icon-7-stroke/css/pe-icon-7-stroke.css" />
<link rel="stylesheet" href="static/bootstrap/fonts/pe-icon-7-stroke/css/helper.css" />
<link rel="stylesheet" href="static/bootstrap/styles/style.css">

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
					<!-- 
            <a class="small-header-action" href="">
                <div class="clip-header">
                    <i class="fa fa-arrow-up"></i>
                </div>
            </a> -->

					<div id="hbreadcrumb" class="pull-right m-t-lg">
						<ol class="hbreadcrumb breadcrumb">
							<li><a href="to_index?jump=1">首页</a></li>
							<li><span>用户管理</span></li>
							<li class="active"><span>用户管理</span></li>
						</ol>
					</div>
					<h2 class="font-light m-b-xs">用户管理</h2>
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
                       <i class="fa fa-group"></i> <span class="bold">新增员工</span>
                     </button>
                   </div>
                    &nbsp;
            	<div>
            </div>
         </div>
         
         
                 <!-- 高级搜索开始 -->
                  <div class="collapse out" id="collapseExample" aria-expanded="false" >
                    <div class="well well-lg " >
                         <div class="row">
                              <div class="col-sm-11">
                              <form role="form" class="formclass">
                                 <div class="form-group lmaigin">
                                    <label for="exampleInputName2" class="col-lg-2">用户名：</label>
                                    <div class="col-lg-2">
                                    <input type="text" class="form-control" id="exampleInputName2">
                                    </div>
                                 </div>
                                  <div class="form-group">
                                    <label for="exampleInputName3" class="col-lg-2">姓名：</label>
                                    <div class="col-lg-2">
                                    <input type="text" class="form-control" id="exampleInputName3">
                                    </div>
                                 </div>
                                  <div class="form-group">
                                    <label for="exampleInputName4" class="col-lg-2">类型：</label>
                                    <div class="col-lg-2">
                                    <select class="form-control m-b" name="account" placeholder="全部">
                                        <option>全部&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
                                        <option>公司系统管理员</option>
                                        <option>普通用户</option>
                                    </select>
                                    </div>
                                   </div>
                                   
                              </form>
                              </div>
                              <div class="col-sm-11">
                                <form role="form" class="formclass">
                                  <div class="form-group">
                                    <label for="exampleInputName5" class="col-lg-2">状态：</label>
                                    <div class="col-lg-2" >
                                      <div class="sex_style">
                                        <input class="new_style_four sex" type="radio" id="optionsRadios1" type="radio" name="optionsRadios" value="option1" checked="">
                                    
                                        <span class="sex sex_third">有效</span>
                                    
                                        <input class="new_style_four sex" type="radio" id="optionsRadios2" type="radio"  name="optionsRadios" value="option1" checked="">
                                    
                                        <span class="sex sex_four">无效</spsexan>
                                      </div>
                                    </div>
                                    </div>
                                </form>
                              </div>
                              <div class="col-sm-11">
                                <center>
                                  <button class="btn btn-info" type="button" id="show">高级搜索</button>
                                  <!--<a href="user_add.html"><button class="btn btn-primary" type="button">新增</button></a>-->
                                </center>
                              </div>
                           </div>
                       </div>
                   </div>
                <!-- 高级搜索结束 -->
                
       
                <!--新增员工弹框-->    
                   <div class="modal fade hmodal-success form-row" id="myModal7" tabindex="-1" role="dialog"  aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="color-line"></div>
                            <div class="modal-header clearfix">
                             <div class="row row_style_one">
                              <div class="col-sm-12">
                              <form role="form" class=" form-horizontal formclass" id="form">
                              
                                 <div class="form-group">
                                    <label class="col-lg-3 new_padding">用户名：</label>
                                    <div class="col-lg-8">
                                    <input type="text" class="form-control content_clear">
                                    </div>
                                    <label class="col-lg-1 hong">*</label>
                                    </div> 
                                 
                                 <div class="form-group">
                                 	<label class="col-lg-3 new_padding">姓名：</label>
                                    <div class="col-lg-8">
                                    <input type="text" class="form-control content_clear">
                                    </div>
                                    <label class="col-lg-1 hong">*</label>
                                 </div> 
                                    
                                 <div class="form-group">
                                 	<label class="col-lg-3 new_padding">手机：</label>
                                    <div class="col-lg-8">
                                    <input type="text" placeholder="请输入有效数字" class="form-control content_clear" name="number">
                                    </div>
                                    <label class="col-lg-1 hong">*</label>
                                 </div> 
                                    
                                 <div class="form-group">
                                 	<label class="col-lg-3 new_padding">Email：</label>
                                    <div class="col-lg-8">
                                    <input  class="form-control content_clear" type="email" required placeholder="请输入电子邮件" name="Email">
                                    
                                    </div>
                                    <label class="col-lg-1 hong">*</label>
                                </div> 

                                 
                                 <div class="form-group">
                                 	<label class="col-lg-3 new_padding">类型：</label>
                                    <div class="col-lg-8">
                                    <select class="form-control m-b nomargin_tow" name="account" placeholder="系统管理员">
                                        <option>系统管理员</option>
                                        <option>普通用户</option>
                                    </select>
                                    </div>
                                    <label class="col-lg-1 hong">*</label>
                                 </div> 
                              </form>
                              </div>
                            </div> 
                            
                            
                            <div class="row row_style_tow">
                              <div class="col-sm-12">
                              <form role="form" class=" form-horizontal formclass" id="form_one">
                              
                                 <div class="form-group">
                                 	<label class="col-lg-3 new_padding"></label>
                                    
                                    <label class="col-lg-2">&nbsp;</label>
                                 </div> 
                                 
                                 <div class="form-group">
                                 	<label class="col-lg-3 new_padding">性别：</label>
                                    <div class="col-lg-8">
                                      <div class="sex_style">
                                        <input class="new_style_four sex" type="radio" id="optionsRadios3" type="radio" name="optionsRadios" value="option1" checked="">
                                    
                                        <span class="sex sex_tow">男</span>
                                    
                                        <input class="new_style_four sex" type="radio" id="optionsRadios4" type="radio" name="optionsRadios" value="option1" checked="">
                                    
                                        <span class="sex sex_tow">女</spsexan>
                                      </div>
                                    </div>
                                 </div> 
                                 
                                 <div class="form-group">
                                 	<label class="col-lg-3 new_padding">电话：</label>
                                    <div class="col-lg-8">
                                    <input type="text" placeholder="请输入有效数字" class="form-control content_clear" name="number">
                                    </div>
                                    <!--<label class="col-lg-1 hong">*</label>-->
                                 </div> 
                                 
                                 <div class="form-group">
                                 	<label class="col-lg-3 new_padding">&nbsp;</label>
                                    <div class="col-lg-8">
                                    <!--<input type="text" class="form-control">-->
                                    </div>
                                    <!--<label class="col-lg-1 hong">*</label>-->
                                </div>
                                
                                <div class="form-group">
                                 	<label class="col-lg-3 new_padding_tow">用户区分：</label>
                                    <div class="col-lg-8">
                                    <select class="form-control m-b nomargin_tow" name="account" placeholder="用户区分">
                                        <option>不限</option>
                                        <option>GDSI</option>
                                        <option>GDSS</option>
                                    </select>
                                    </div>
                                    <!--<label class="col-lg-1 hong">*</label>-->
                                 </div>   
                              </form>
                              </div>
                            </div> 
                            
                            <div class="row">
                              <div class="col-sm-12">
                                <div class="form-group">
                                 	<label class="col-lg-2 beizhu">备注：</label>
                                    <div class="col-lg-10">
                                    <textarea type="text" class="form-control new_width content_clear" id="exampleInputName8" ></textarea>
                                    </div>
                                    <!--<label class="col-lg-1 hong">*</label>-->
                                 </div>
                                 <center>
                                  <!--<button class="btn btn-primary show_tow" type="button" id="show">查询</button>-->
                                  <button class="btn btn-primary" type="button">保存</button>
                                  &nbsp;
                                  <button class="btn btn-primary guanbi" type="button">取消</button>
                                </center>
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
                            <div class="modal-header clearfix">
                             <div class="row row_style_one">
                              <div class="col-sm-12">
                              <form role="form" class=" form-horizontal formclass" id="#form_2">
                              
                                 <div class="form-group">
                                    <label class="col-lg-3 new_padding">用户名：</label>
                                    <div class="col-lg-8">
                                    <input type="text" class="form-control" value="lixiangwu">
                                    </div>
                                    <label class="col-lg-1 hong">*</label>
                                    </div> 
                                 
                                 <div class="form-group">
                                 	<label class="col-lg-3 new_padding">姓名：</label>
                                    <div class="col-lg-8">
                                    <input type="text" class="form-control" value="李向武">
                                    </div>
                                    <label class="col-lg-1 hong">*</label>
                                 </div> 
                                    
                                 <div class="form-group">
                                 	<label class="col-lg-3 new_padding">手机：</label>
                                    <div class="col-lg-8">
                                    <input type="text" placeholder="请输入有效数字" class="form-control" name="number" value="13162715308">
                                    </div>
                                    <label class="col-lg-1 hong">*</label>
                                 </div> 
                                    
                                 <div class="form-group">
                                 	<label class="col-lg-3 new_padding">Email：</label>
                                    <div class="col-lg-8">
                                    <input  class="form-control" type="email" name="email"  placeholder="请输入电子邮件" value="117454@qq.com">

                                    
                                    </div>
                                    <label class="col-lg-1 hong">*</label>
                                </div> 
                                 
                                 <div class="form-group">
                                 	<label class="col-lg-3 new_padding">类型：</label>
                                    <div class="col-lg-8">
                                    <select class="form-control m-b nomargin_tow" name="account" placeholder="系统管理员">
                                        <option>系统管理员</option>
                                        <option>普通用户</option>
                                    </select>
                                    </div>
                                    <label class="col-lg-1 hong">*</label>
                                 </div> 
                              </form>
                              </div>
                            </div> 
                            
                            
                            <div class="row row_style_tow">
                              <div class="col-sm-12">
                              <form role="form" class=" form-horizontal formclass" id="form_tow">
                              
                                 <div class="form-group">
                                 	<label class="col-lg-3 new_padding">ID：</label>
                                    
                                    <label class="col-lg-2">000001</label>
                                 </div> 
                                 
                                 <div class="form-group">
                                 	<label class="col-lg-3 new_padding">性别：</label>
                                    <div class="col-lg-8">
                                      <div class="sex_style">
                                        <input class="new_style_four sex" type="radio" id="optionsRadios5" type="radio" name="optionsRadios" value="option1" checked="">
                                    
                                        <span class="sex sex_tow">男</span>
                                    
                                        <input class="new_style_four sex" type="radio" id="optionsRadios6" type="radio" name="optionsRadios" value="option1" checked="">
                                    
                                        <span class="sex sex_tow">女</spsexan>
                                      </div>
                                    </div>
                                 </div> 
                                 
                                 <div class="form-group">
                                 	<label class="col-lg-3 new_padding">电话：</label>
                                    <div class="col-lg-8">
                                    <input type="text" placeholder="请输入有效数字" class="form-control" name="number">
                                    </div>
                                    <!--<label class="col-lg-1 hong">*</label>-->
                                 </div> 
                                 
                                 <div class="form-group">
                                 	<label class="col-lg-3 new_padding">&nbsp;</label>
                                    <div class="col-lg-8">
                                    <!--<input type="text" class="form-control">-->
                                    </div>
                                    <!--<label class="col-lg-1 hong">*</label>-->
                                </div>
                                
                                <div class="form-group">
                                 	<label class="col-lg-3 new_padding_tow">用户区分：</label>
                                    <div class="col-lg-8">
                                    <select class="form-control m-b nomargin_tow" name="account" placeholder="用户区分">
                                        <option>不限</option>
                                        <option>GDSI</option>
                                        <option>GDSS</option>
                                    </select>
                                    </div>
                                    <!--<label class="col-lg-1 hong">*</label>-->
                                 </div>   
                              </form>
                              </div>
                            </div> 
                            
                            <div class="row">
                              <div class="col-sm-12">
                                <div class="form-group">
                                 	<label class="col-lg-2 beizhu">备注：</label>
                                    <div class="col-lg-10">
                                    <textarea type="text" class="form-control new_width" id="exampleInputName8" >您好您好您好您好！！！！</textarea>
                                    </div>
                                    <!--<label class="col-lg-1 hong">*</label>-->
                                 </div>
                                 <center>
                                  <!--<button class="btn btn-primary show_tow" type="button" id="show">查询</button>-->
                                  <button class="btn btn-primary" type="button">保存</button>
                                  &nbsp;
                                  <button class="btn btn-primary guanbi" type="button">取消</button>
                                </center>
                              </div>
                            </div> 
                        </div>
                    </div>
                </div>
                </div>
                <!--修改弹框end-->
                
                <!--设置权限弹框start-->
                
               <div class="modal fade hmodal-success form-row" id="myModal9" tabindex="-1" role="dialog"  aria-hidden="true">
                 <div class="modal-dialog">
                   <div class="modal-content">
                     <div class="color-line"></div>
                       <div class="modal-header">
                         <div class="row">
                         
                           <div class="col-sm-12">
                             <div class="form-group">
                               <label class="col-lg-6">权限设置</label>
                               <div class="col-lg-6 lalign">&nbsp;</div>
                             </div>
                           </div>
                           
                           <div class="col-sm-12" id="user_rowAdd">
                             <center class="quanxian_head">000001&nbsp; 李向武（lixiangwu）</center> 
                             <div class="form-group quanxian_div clearfix">
                               <span class="col-lg-2">功能点</span>
                               <span class="col-lg-2">类型</span>
                               <span class="col-lg-2">字段</span>
                               <span class="col-lg-2">操作</span>
                               <span class="col-lg-2">值</span>
                               <span class="col-lg-2">删除</span>
                             </div>
                             <div class="form-group quanxian_div clearfix quanxian_div_third quanxian_div_five tianjia">
                               <span class="col-lg-2 selcct_span">
                                 <select class="form-control m-b nomargin_tow new_style_third" name="account" placeholder="默认">
                                   <option>默认</option>
                                   <option>社保</option>
                                 </select>
                               </span>
                               <span class="col-lg-2 selcct_span">
                                 <select class="form-control m-b nomargin_tow new_style_third" name="account" placeholder="字段条件项">
                                   <option>字段条件项</option>
                                   <option>AND</option>
                                   <option>OR</option>
                                 </select>
                               </span>
                               <span class="col-lg-2 selcct_span">
                                 <select class="form-control m-b nomargin_tow new_style_third" name="account" placeholder="工作地">
                                   <option>工作地</option>
                                   <option>发薪地</option>
                                   <option>部门</option>
                                 </select>
                               </span>
                               <span class="col-lg-2 selcct_span">
                                 <select class="form-control m-b nomargin_tow new_style_third" name="account" placeholder="操作1">
                                   <option>操作1</option>
                                   <option>操作2</option>
                                   <option>操作3</option>
                                 </select>
                               </span>
                               <span class="col-lg-2 selcct_span ">
                                 <!--<select  id="i_ts_desc" class="form-control m-b nomargin_tow new_style_third" name="account" placeholder="选项1">-->
                                 <select class="js-source-states-2 form-control m-b nomargin_tow new_style_third" multiple="multiple" placeholder="选项1">
                                   <option value="选项1">选项1</option>
                                   <option value="选项2">选项2</option>
                                   <option value="选项3">选项3</option>
                                 </select> 
                                 
                                 
                               </span>
                               <span class="col-lg-2 selcct_span">
                                 <button class="btn btn-info btn-xs dropdown-toggle user_demo button_height" onClick="deleteRow2()">
                                   <a class="wcolor"><i class="fa fa-times wcolor"></i> 删除</a>
                                 </button>
                               </span>
                               
                               
                             </div>
                             
                             
                             <!--<div class="form-group quanxian_div clearfix quanxian_div_third quanxian_div_five">
                               <span class="col-lg-2 selcct_span">
                                 <select class="form-control m-b nomargin_tow new_style_third" name="account" placeholder="默认">
                                   <option>默认</option>
                                   <option>社保</option>
                                 </select>
                               </span>
                               <span class="col-lg-2 selcct_span">
                                 <select class="form-control m-b nomargin_tow new_style_third" name="account" placeholder="字段条件项">
                                   <option>字段条件项</option>
                                   <option>AND</option>
                                   <option>OR</option>
                                 </select>
                               </span>
                               <span class="col-lg-2 selcct_span">
                                 <select class="form-control m-b nomargin_tow new_style_third" name="account" placeholder="工作地">
                                   <option>工作地</option>
                                   <option>发薪地</option>
                                   <option>部门</option>
                                 </select>
                               </span>
                               <span class="col-lg-2 selcct_span">
                                 <select class="form-control m-b nomargin_tow new_style_third" name="account" placeholder="操作1">
                                   <option>操作1</option>
                                   <option>操作2</option>
                                   <option>操作3</option>
                                 </select>
                               </span>
                               <span class="col-lg-2 selcct_span">
                                 
                                 <select class="js-source-states-2 form-control m-b nomargin_tow new_style_third" multiple="multiple" placeholder="选项1">
                                   <option value="选项1">选项1</option>
                                   <option value="选项2">选项2</option>
                                   <option value="选项3">选项3</option>
                                 </select>
                                 
                               </span>
                               <span class="col-lg-2 selcct_span">
                                 <button class="btn btn-info btn-xs dropdown-toggle user_demo button_height" onClick="deleteRow2()">
                                   <a class="wcolor"><i class="fa fa-times wcolor"></i> 删除</a>
                                 </button>
                               </span>
                             </div>-->
                             
                             <div class="form-group quanxian_div quanxian_div_tow clearfix quanxian_div_four">
                             <span class="col-lg-2 span_noborder">
                               <button class="btn btn-info btn-xs dropdown-toggle button_height user_add" id="addRow_tow">
                                   <a class="wcolor"><i class="fa fa-plus wcolor"></i> 添加</a>
                               </button>
                             </span>
                             </div>
                             
                             <center class="center_top">
                               <button class="btn btn-primary show_tow" type="button" id="show">保存</button>
                               <button class="btn btn-primary guanbi" type="button">取消</button>
                             </center>
                           
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
                    <th>用户名</th>
                    <th>姓名</th>
                    <th>Email</th>
                    <th>类型</th>
                    <th>状态 </th>
                    <th>锁定</th>
                    <th>更新人</th>
                    <th>更新日期</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                   <td>lixiangwu1</td>
                    <td>张三</td>
                    <td>aaa@email.com</td>
                    <td>普通用户</td>
                    <td>有效</td>
                    <td>未锁定</td>
                     <td>系统管理员</td>
                    <td>2011－04－25 18:06</td>
                    <th>                
                    <div class="btn-group">
                      <button data-toggle="dropdown" class="btn btn-info btn-xs dropdown-toggle">操作<span class="caret"></span></button>
                      <ul class="dropdown-menu">
                          <li><a href="#" data-target="#myModal9" data-toggle="modal"><i class="fa pe-7s-config"></i> 设置权限</a></li>
                          <li><a href="#"><i class="fa fa-rotate-left"></i> 密码重置</a></li>
                          <li><a href="#" data-target="#myModal8" data-toggle="modal"><i class="fa fa-check"></i> 修改</a></li>
                          <li><a class="demo4"><i class="fa fa-times"></i> 删除</a></li>                        
                      </ul>
                    </div>
                </th>
                </tr>
                  <tr>
                   <td>lixiangwu2</td>
                    <td>李四</td>
                    <td>aaa@email.com</td>
                    <td>普通用户</td>
                    <td>有效</td>
                    <td>未锁定</td>
                     <td>系统管理员</td>
                    <td>2011－04－25 18:06</td>
                    <th>                
                    <div class="btn-group">
                      <button data-toggle="dropdown" class="btn btn-info btn-xs dropdown-toggle">操作<span class="caret"></span></button>
                      <ul class="dropdown-menu">
                          <li><a href="#"><i class="fa pe-7s-config"></i> 设置权限</a></li>
                          <li><a href="#"><i class="fa fa-rotate-left"></i> 密码重置</a></li>
                          <li><a href="#" data-target="#myModal8" data-toggle="modal"><i class="fa fa-check"></i> 修改</a></li>
                          <li><a class="demo4"  ><i class="fa fa-times "></i> 删除</a></li>                        
                      </ul>
                    </div>
                </th>
                </tr>
                <tr>
                  <td>lixiangwu3</td>
                  <td>张三</td>
                  <td>aaa@email.com</td>
                  <td>普通用户</td>
                  <td>有效</td>
                  <td>未锁定</td>
                  <td>系统管理员</td>
                  <td>2011－04－25 18:06</td>
                  <th>                
                    <div class="btn-group">
                      <button data-toggle="dropdown" class="btn btn-info btn-xs dropdown-toggle">操作<span class="caret"></span></button>
                      <ul class="dropdown-menu">
                          <li><a href="#"><i class="fa pe-7s-config"></i> 设置权限</a></li>
                          <li><a href="#"><i class="fa fa-rotate-left"></i> 密码重置</a></li>
                          <li><a href="#" data-target="#myModal8" data-toggle="modal"><i class="fa fa-check"></i> 修改</a></li>
                          <li><a class="demo4"  ><i class="fa fa-times "></i> 删除</a></li>                        
                      </ul>
                    </div>
                </th>
                </tr>
                <tr>
                   <td>lixiangwu4</td>
                    <td>张三</td>
                    <td>aaa@email.com</td>
                    <td>普通用户</td>
                    <td>有效</td>
                    <td>未锁定</td>
                     <td>系统管理员</td>
                    <td>2011－04－25 18:06</td>
                    <th>                
                    <div class="btn-group">
                      <button data-toggle="dropdown" class="btn btn-info btn-xs dropdown-toggle">操作<span class="caret"></span></button>
                      <ul class="dropdown-menu">
                          <li><a href="#"><i class="fa pe-7s-config"></i> 设置权限</a></li>
                          <li><a href="#"><i class="fa fa-rotate-left"></i> 密码重置</a></li>
                          <li><a href="#" data-target="#myModal8" data-toggle="modal"><i class="fa fa-check"></i> 修改</a></li>
                          <li><a class="demo4"  ><i class="fa fa-times "></i> 删除</a></li>                        
                      </ul>
                    </div>
                </th>
                </tr>
                <tr>
                   <td>lixiangwu5</td>
                    <td>张三</td>
                    <td>aaa@email.com</td>
                    <td>普通用户</td>
                    <td>有效</td>
                    <td>未锁定</td>
                     <td>系统管理员</td>
                    <td>2011－04－25 18:06</td>
                    <th>                
                    <div class="btn-group">
                      <button data-toggle="dropdown" class="btn btn-info btn-xs dropdown-toggle">操作<span class="caret"></span></button>
                      <ul class="dropdown-menu">
                          <li><a href="#"><i class="fa pe-7s-config"></i> 设置权限</a></li>
                          <li><a href="#"><i class="fa fa-rotate-left"></i> 密码重置</a></li>
                          <li><a href="#" data-target="#myModal8" data-toggle="modal"><i class="fa fa-check"></i> 修改</a></li>
                          <li><a class="demo4"  ><i class="fa fa-times "></i> 删除</a></li>                        
                      </ul>
                    </div>
                </th>
                </tr>
                <tr>
                   <td>lixiangwu6</td>
                    <td>张三</td>
                    <td>aaa@email.com</td>
                    <td>普通用户</td>
                    <td>有效</td>
                    <td>未锁定</td>
                     <td>系统管理员</td>
                    <td>2011－04－25 18:06</td>
                    <th>                
                    <div class="btn-group">
                      <button data-toggle="dropdown" class="btn btn-info btn-xs dropdown-toggle">操作<span class="caret"></span></button>
                      <ul class="dropdown-menu">
                          <li><a href="#"><i class="fa pe-7s-config"></i> 设置权限</a></li>
                          <li><a href="#"><i class="fa fa-rotate-left"></i> 密码重置</a></li>
                          <li><a href="#" data-target="#myModal8" data-toggle="modal"><i class="fa fa-check"></i> 修改</a></li>
                          <li><a class="demo4"  ><i class="fa fa-times "></i> 删除</a></li>                        
                      </ul>
                    </div>
                </th>
                </tr>
                 <tr>
                   <td>lixiangwu7</td>
                    <td>张三</td>
                    <td>aaa@email.com</td>
                    <td>普通用户</td>
                    <td>有效</td>
                    <td>未锁定</td>
                     <td>系统管理员</td>
                    <td>2011－04－25 18:06</td>
                    <th>                
                    <div class="btn-group">
                      <button data-toggle="dropdown" class="btn btn-info btn-xs dropdown-toggle">操作<span class="caret"></span></button>
                      <ul class="dropdown-menu">
                          <li><a href="#"><i class="fa pe-7s-config"></i> 设置权限</a></li>
                          <li><a href="#"><i class="fa fa-rotate-left"></i> 密码重置</a></li>
                          <li><a href="#" data-target="#myModal8" data-toggle="modal"><i class="fa fa-check"></i> 修改</a></li>
                          <li><a class="demo4"  ><i class="fa fa-times "></i> 删除</a></li>                        
                      </ul>
                    </div>
                </th>
                </tr>
                <tr>
                   <td>lixiangwu8</td>
                    <td>张三</td>
                    <td>aaa@email.com</td>
                    <td>普通用户</td>
                    <td>有效</td>
                    <td>未锁定</td>
                     <td>系统管理员</td>
                    <td>2011－04－25 18:06</td>
                    <th>                
                    <div class="btn-group">
                      <button data-toggle="dropdown" class="btn btn-info btn-xs dropdown-toggle">操作<span class="caret"></span></button>
                      <ul class="dropdown-menu">
                          <li><a href="#"><i class="fa pe-7s-config"></i> 设置权限</a></li>
                          <li><a href="#"><i class="fa fa-rotate-left"></i> 密码重置</a></li>
                          <li><a href="#" data-target="#myModal8" data-toggle="modal"><i class="fa fa-check"></i> 修改</a></li>
                          <li><a class="demo4"  ><i class="fa fa-times "></i> 删除</a></li>                        
                      </ul>
                    </div>
                </th>
                </tr>
				 <tr>
                   <td>lixiangwu9</td>
                    <td>张三</td>
                    <td>aaa@email.com</td>
                    <td>普通用户</td>
                    <td>有效</td>
                    <td>未锁定</td>
                     <td>系统管理员</td>
                    <td>2011－04－25 18:06</td>
                    <th>                
                    <div class="btn-group">
                      <button data-toggle="dropdown" class="btn btn-info btn-xs dropdown-toggle">操作<span class="caret"></span></button>
                      <ul class="dropdown-menu">
                          <li><a href="#"><i class="fa pe-7s-config"></i> 设置权限</a></li>
                          <li><a href="#"><i class="fa fa-rotate-left"></i> 密码重置</a></li>
                          <li><a href="#" data-target="#myModal8" data-toggle="modal"><i class="fa fa-check"></i> 修改</a></li>
                          <li><a class="demo4"  ><i class="fa fa-times "></i> 删除</a></li>                        
                      </ul>
                    </div>
                </th>
                </tr>
                <tr>
                   <td>lixiangwu11</td>
                    <td>张三</td>
                    <td>aaa@email.com</td>
                    <td>普通用户</td>
                    <td>有效</td>
                    <td>未锁定</td>
                     <td>系统管理员</td>
                    <td>2011－04－25 18:06</td>
                    <th>                
                    <div class="btn-group">
                      <button data-toggle="dropdown" class="btn btn-info btn-xs dropdown-toggle">操作<span class="caret"></span></button>
                      <ul class="dropdown-menu">
                          <li><a href="#"><i class="fa pe-7s-config"></i> 设置权限</a></li>
                          <li><a href="#"><i class="fa fa-rotate-left"></i> 密码重置</a></li>
                          <li><a href="#" data-target="#myModal8" data-toggle="modal"><i class="fa fa-check"></i> 修改</a></li>
                          <li><a class="demo4"  ><i class="fa fa-times "></i> 删除</a></li>                        
                      </ul>
                    </div>
                </th>
                </tr>
				<tr>
                   <td>lixiangwu12</td>
                    <td>张三</td>
                    <td>aaa@email.com</td>
                    <td>普通用户</td>
                    <td>有效</td>
                    <td>未锁定</td>
                     <td>系统管理员</td>
                    <td>2011－04－25 18:06</td>
                    <th>                
                    <div class="btn-group">
                      <button data-toggle="dropdown" class="btn btn-info btn-xs dropdown-toggle">操作<span class="caret"></span></button>
                      <ul class="dropdown-menu">
                          <li><a href="#"><i class="fa pe-7s-config"></i> 设置权限</a></li>
                          <li><a href="#"><i class="fa fa-rotate-left"></i> 密码重置</a></li>
                          <li><a href="#" data-target="#myModal8" data-toggle="modal"><i class="fa fa-check"></i> 修改</a></li>
                          <li><a class="demo4"  ><i class="fa fa-times "></i> 删除</a></li>                        
                      </ul>
                    </div>
                </th>
                </tr>
                <tr>
                   <td>lixiangwu13</td>
                    <td>张三</td>
                    <td>aaa@email.com</td>
                    <td>普通用户</td>
                    <td>有效</td>
                    <td>未锁定</td>
                     <td>系统管理员</td>
                    <td>2011－04－25 18:06</td>
                    <th>                
                    <div class="btn-group">
                      <button data-toggle="dropdown" class="btn btn-info btn-xs dropdown-toggle">操作<span class="caret"></span></button>
                      <ul class="dropdown-menu">
                          <li><a href="#"><i class="fa pe-7s-config"></i> 设置权限</a></li>
                          <li><a href="#"><i class="fa fa-rotate-left"></i> 密码重置</a></li>
                          <li><a href="#" data-target="#myModal8" data-toggle="modal"><i class="fa fa-check"></i> 修改</a></li>
                          <li><a class="demo4"  ><i class="fa fa-times "></i> 删除</a></li>                        
                      </ul>
                    </div>
                </th>
                </tr>
				<tr>
                   <td>lixiangwu14</td>
                    <td>张三</td>
                    <td>aaa@email.com</td>
                    <td>普通用户</td>
                    <td>有效</td>
                    <td>未锁定</td>
                     <td>系统管理员</td>
                    <td>2011－04－25 18:06</td>
                    <th>                
                    <div class="btn-group">
                      <button data-toggle="dropdown" class="btn btn-info btn-xs dropdown-toggle">操作<span class="caret"></span></button>
                      <ul class="dropdown-menu">
                          <li><a href="#"><i class="fa pe-7s-config"></i> 设置权限</a></li>
                          <li><a href="#"><i class="fa fa-rotate-left"></i> 密码重置</a></li>
                          <li><a href="#" data-target="#myModal8" data-toggle="modal"><i class="fa fa-check"></i> 修改</a></li>
                          <li><a class="demo4"  ><i class="fa fa-times "></i> 删除</a></li>                        
                      </ul>
                    </div>
                </th>
                </tr>
                 <tr>
                   <td>lixiangwu15</td>
                    <td>张三</td>
                    <td>aaa@email.com</td>
                    <td>普通用户</td>
                    <td>有效</td>
                    <td>未锁定</td>
                     <td>系统管理员</td>
                    <td>2011－04－25 18:06</td>
                    <th>                
                    <div class="btn-group">
                      <button data-toggle="dropdown" class="btn btn-info btn-xs dropdown-toggle">操作<span class="caret"></span></button>
                      <ul class="dropdown-menu">
                          <li><a href="#"><i class="fa pe-7s-config"></i> 设置权限</a></li>
                          <li><a href="#"><i class="fa fa-rotate-left"></i> 密码重置</a></li>
                          <li><a href="#" data-target="#myModal8" data-toggle="modal"><i class="fa fa-check"></i> 修改</a></li>
                          <li><a class="demo4"  ><i class="fa fa-times "></i> 删除</a></li>                        
                      </ul>
                    </div>
                </th>
                </tr>
				<tr>
                   <td>lixiangwu16</td>
                    <td>张三</td>
                    <td>aaa@email.com</td>
                    <td>普通用户</td>
                    <td>有效</td>
                    <td>未锁定</td>
                     <td>系统管理员</td>
                    <td>2011－04－25 18:06</td>
                    <th>                
                    <div class="btn-group">
                      <button data-toggle="dropdown" class="btn btn-info btn-xs dropdown-toggle">操作<span class="caret"></span></button>
                      <ul class="dropdown-menu">
                          <li><a href="#"><i class="fa pe-7s-config"></i> 设置权限</a></li>
                          <li><a href="#"><i class="fa fa-rotate-left"></i> 密码重置</a></li>
                          <li><a href="#" data-target="#myModal8" data-toggle="modal"><i class="fa fa-check"></i> 修改</a></li>
                          <li><a class="demo4"  ><i class="fa fa-times "></i> 删除</a></li>                        
                      </ul>
                    </div>
                </th>
                </tr>
                <tr>
                   <td>lixiangwu17</td>
                    <td>张三</td>
                    <td>aaa@email.com</td>
                    <td>普通用户</td>
                    <td>有效</td>
                    <td>未锁定</td>
                     <td>系统管理员</td>
                    <td>2011－04－25 18:06</td>
                    <th>                
                    <div class="btn-group">
                      <button data-toggle="dropdown" class="btn btn-info btn-xs dropdown-toggle">操作<span class="caret"></span></button>
                      <ul class="dropdown-menu">
                          <li><a href="#"><i class="fa pe-7s-config"></i> 设置权限</a></li>
                          <li><a href="#"><i class="fa fa-rotate-left"></i> 密码重置</a></li>
                          <li><a href="#" data-target="#myModal8" data-toggle="modal"><i class="fa fa-check"></i> 修改</a></li>
                          <li><a class="demo4"  ><i class="fa fa-times "></i> 删除</a></li>                        
                      </ul>
                    </div>
                </th>
                </tr>
				<tr>
                   <td>lixiangwu18</td>
                    <td>张三</td>
                    <td>aaa@email.com</td>
                    <td>普通用户</td>
                    <td>有效</td>
                    <td>未锁定</td>
                     <td>系统管理员</td>
                    <td>2011－04－25 18:06</td>
                    <th>                
                    <div class="btn-group">
                      <button data-toggle="dropdown" class="btn btn-info btn-xs dropdown-toggle">操作<span class="caret"></span></button>
                      <ul class="dropdown-menu">
                          <li><a href="#"><i class="fa pe-7s-config"></i> 设置权限</a></li>
                          <li><a href="#"><i class="fa fa-rotate-left"></i> 密码重置</a></li>
                          <li><a href="#" data-target="#myModal8" data-toggle="modal"><i class="fa fa-check"></i> 修改</a></li>
                          <li><a class="demo4"  ><i class="fa fa-times "></i> 删除</a></li>                        
                      </ul>
                    </div>
                </th>
                </tr>
                 <tr>
                   <td>lixiangwu19</td>
                    <td>张三</td>
                    <td>aaa@email.com</td>
                    <td>普通用户</td>
                    <td>有效</td>
                    <td>未锁定</td>
                     <td>系统管理员</td>
                    <td>2011－04－25 18:06</td>
                    <th>                
                    <div class="btn-group">
                      <button data-toggle="dropdown" class="btn btn-info btn-xs dropdown-toggle">操作<span class="caret"></span></button>
                      <ul class="dropdown-menu">
                          <li><a href="#"><i class="fa pe-7s-config"></i> 设置权限</a></li>
                          <li><a href="#"><i class="fa fa-rotate-left"></i> 密码重置</a></li>
                          <li><a href="#" data-target="#myModal8" data-toggle="modal"><i class="fa fa-check"></i> 修改</a></li>
                          <li><a class="demo4"  ><i class="fa fa-times "></i> 删除</a></li>                        
                      </ul>
                    </div>
                </th>
                </tr>
				<tr>
                   <td>lixiangwu21</td>
                    <td>张三</td>
                    <td>aaa@email.com</td>
                    <td>普通用户</td>
                    <td>有效</td>
                    <td>未锁定</td>
                     <td>系统管理员</td>
                    <td>2011－04－25 18:06</td>
                    <th>                
                    <div class="btn-group">
                      <button data-toggle="dropdown" class="btn btn-info btn-xs dropdown-toggle">操作<span class="caret"></span></button>
                      <ul class="dropdown-menu">
                          <li><a href="#"><i class="fa pe-7s-config"></i> 设置权限</a></li>
                          <li><a href="#"><i class="fa fa-rotate-left"></i> 密码重置</a></li>
                          <li><a href="#" data-target="#myModal8" data-toggle="modal"><i class="fa fa-check"></i> 修改</a></li>
                          <li><a class="demo4"  ><i class="fa fa-times "></i> 删除</a></li>                        
                      </ul>
                    </div>
                </th>
                </tr>
                <tr>
                   <td>lixiangwu22</td>
                    <td>张三</td>
                    <td>aaa@email.com</td>
                    <td>普通用户</td>
                    <td>有效</td>
                    <td>未锁定</td>
                     <td>系统管理员</td>
                    <td>2011－04－25 18:06</td>
                    <th>                
                    <div class="btn-group">
                      <button data-toggle="dropdown" class="btn btn-info btn-xs dropdown-toggle">操作<span class="caret"></span></button>
                      <ul class="dropdown-menu">
                          <li><a href="#"><i class="fa pe-7s-config"></i> 设置权限</a></li>
                          <li><a href="#"><i class="fa fa-rotate-left"></i> 密码重置</a></li>
                          <li><a href="#" data-target="#myModal8" data-toggle="modal"><i class="fa fa-check"></i> 修改</a></li>
                          <li><a class="demo4"  ><i class="fa fa-times "></i> 删除</a></li>                        
                      </ul>
                    </div>
                </th>
                </tr>
				<tr>
                   <td>lixiangwu23</td>
                    <td>张三</td>
                    <td>aaa@email.com</td>
                    <td>普通用户</td>
                    <td>有效</td>
                    <td>未锁定</td>
                     <td>系统管理员</td>
                    <td>2011－04－25 18:06</td>
                    <th>                
                    <div class="btn-group">
                      <button data-toggle="dropdown" class="btn btn-info btn-xs dropdown-toggle">操作<span class="caret"></span></button>
                      <ul class="dropdown-menu">
                          <li><a href="#"><i class="fa pe-7s-config"></i> 设置权限</a></li>
                          <li><a href="#"><i class="fa fa-rotate-left"></i> 密码重置</a></li>
                          <li><a href="#" data-target="#myModal8" data-toggle="modal"><i class="fa fa-check"></i> 修改</a></li>
                          <li><a class="demo4"  ><i class="fa fa-times "></i> 删除</a></li>                        
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
    
    
 
    <!--多选下拉-->
    <!--<script src="vendor/metisMenu/dist/metisMenu.min.js"></script>
    <script src="vendor/iCheck/icheck.min.js"></script>
    <script src="vendor/sparkline/index.js"></script>-->
    <script src="static/bootstrap/vendor/moment/moment.js"></script>
    <script src="static/bootstrap/vendor/xeditable/bootstrap3-editable/js/bootstrap-editable.min.js"></script>
    <script src="static/bootstrap/vendor/select2-3.5.2/select2.min.js"></script>
    <script src="static/bootstrap/vendor/bootstrap-touchspin/dist/jquery.bootstrap-touchspin.min.js"></script>
    <script src="static/bootstrap/vendor/bootstrap-datepicker-master/dist/js/bootstrap-datepicker.min.js"></script>
    <!--多选下拉结束-->

    <script src="static/bootstrap/vendor/jquery-validation/jquery.validate.min.js"></script>
    
    
	<!-- for datatable -->
	<script src="static/bootstrap/vendor/datatables/media/js/jquery.dataTables.min.js"></script>
	<script src="static/bootstrap/vendor/datatables_plugins/integration/bootstrap/3/dataTables.bootstrap.min.js"></script>


	<!-- alert -->
	<script src="static/bootstrap/vendor/sweetalert/lib/sweet-alert.min.js"></script>

	<!-- App scripts -->
	<script src="static/bootstrap/scripts/homer.js"></script>
    
    <!--私有-->
    <script src="static/js/user_magement.js"></script>
    
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
		            "infoFiltered": "(筛选自 _MAX_ 条记录)",
					"paginate":{
						
						"first":"首页",
						"previous":"前一页",
						"next":"后一页",
						"last":"尾页"
						
					}
    			 }   
    		
    	    	}); 

    	$("div.toolbar").html('<button class="btn btn-info  btn-sm new_padding" type="button" data-toggle="collapse" data-target="#collapseExample" aria-expanded="false" aria-controls="collapseExample"><i class="fa">高级搜索</i></button>');



    }); 

</script>

<!--删除效果-->
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
	  
	 $("#myModal7").modal('hide');
	 $("#myModal8").modal('hide');
	 $("#myModal9").modal('hide');
	 $(".content_clear").val("");
	  
  })
</script>

<!--多选下拉-->
<script>
 
</script>

</body>
</html>