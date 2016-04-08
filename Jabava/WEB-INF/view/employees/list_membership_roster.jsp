<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
	<base href="<%=basePath%>">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <!-- Page title -->
    <title>jabava1.0花名册界面</title>
    <jsp:include flush="true" page="../common/styles.jsp"></jsp:include>
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

<!-- 放主要内容  开始-->
<!-- Main Wrapper -->
<div id="wrapper">
<div class="normalheader transition animated fadeIn small-header">
    <div class="hpanel">
        <div class="panel-body">
            <div id="hbreadcrumb" class="pull-right m-t-lg">
                <ol class="hbreadcrumb breadcrumb">
                    <li><a href="to_index?jump=1">首页</a></li>
                    <li>
                        <span>员工信息</span>
                    </li>
                    <li class="active">
                        <span>花名册</span>
                    </li>
                </ol>
            </div>
            <h2 class="font-light m-b-xs mem_h2">
               员工信息
            </h2>
               <small class="hong">友情提醒：截至今天，有<span class="zhuanzheng" id="zhuanzheng" onclick="positive()"></span>人将在10天内转正。有<span class="daoqi" id="daoqi" onclick="contract()"></span>人将在50天内合同到期。请及时处理！今天共有<span class="jshengri" id="birthD" onclick="birth(0)"></span>名员工生日。明天共有<span class="mshengri" id="birthT" onclick="birth(1)"></span>名员工生日。</small>
               <input type="hidden" id="birthInput" value="0" />
        </div>
    </div>
</div>

<!-- 放主要内容 -->

        <div class="content animate-panel mem_content">
            <div class="row">
                <div class="col-lg-12">
                    <div class="hpanel">
                    
                    <!-- 高级搜索 复杂查询开始 -->
                      <div class="collapse out" id="collapseExample" aria-expanded="false" >
                        <div class="well well-lg " >
                            <!--<h5 class="font-bold"><i class="fa pe-7s-search"></i> 查询</h5>-->
                        	<div class="row">
                                  <form role="form" class="search-form" id="searchForm"> 
                                  <div class="clearfix">
                                  
                                     <div class="form-group search-unit">
                                        <label for="exampleInputName2" class="col-lg-4 rost_sousuo">工号：</label>
                                        <div class="col-lg-8">
                                        <input type="text" class="form-control" id="exampleInputName2" name="jobNumber">
                                        </div>
                                    </div>
                                    <div class="form-group search-unit">
                                        <label for="exampleInputName3" class="col-lg-4 rost_sousuo">姓名：</label>
                                        <div class="col-lg-8">
                                            <input type="text" class="form-control" id="exampleInputName3" name="employeeName">
                                        </div>
                                    </div>
                                     <div class="form-group search-unit">
                                        <label class="col-lg-4 rost_sousuo">所属部门：</label>
                                        <div class="col-lg-8">
                                        	<input type="text" class="form-control" id="exampleInputName5" name="organizationName">
                                        </div>
                                    </div> 
                                    <div class="form-group search-unit">
                                        <label for="exampleInputName4" class="col-lg-4 rost_sousuo">工作地：</label>
                                        <div class="col-lg-8">
                                        <select class="form-control " name="workLocation" placeholder="上海" id="workLocationData">
                                             <option value="">全部</option>
                                        </select>
                                        </div>
                                    </div> 
                                    <div class="form-group search-unit">
                                        <label class="col-lg-4 rost_sousuo">发薪地：</label>
                                        <div class="col-lg-8">
                                        <select class="form-control " name="payrollLocation" placeholder="上海" id="payrollLocationData">
                                            <option value="">全部</option>
                                           
                                        </select>
                                        </div>
                                    </div> 
                                     <div class="form-group search-unit">
                                        <label class="col-lg-4 rost_sousuo">入职时间：</label>
                                        <div class="input-group date col-lg-8 rost_time">
                                             <div class="input-daterange input-group" id="datepicker">
                                                <input type="text" class="input-sm form-control" name="entryDateStart" />
                                                <span class="input-group-addon">-</span>
                                                <input type="text" class="input-sm form-control" name="entryDateEnd" />
                                			</div>
                                        </div>
                                    </div>
                                    <div class="form-group search-unit">
                                        <label class="col-lg-4 rost_sousuo">转正时间：</label>
                                        <div class="input-group date col-lg-8 rost_time">
                                             <div class="input-daterange input-group" id="datepicker2">
                                                <input type="text" class="input-sm form-control" name="positiveDateStart" />
                                                <span class="input-group-addon">-</span>
                                                <input type="text" class="input-sm form-control" name="positiveDateEnd" />
                                			</div>
                                            
                                        </div>
                                    </div>
                                    <div class="form-group search-unit">
                                        <label class="col-lg-4 rost_sousuo">合同到期时间：</label>
                                        <div class="input-group date col-lg-8 rost_time">
                                             <div class="input-daterange input-group" id="datepicker3">
                                                <input type="text" class="input-sm form-control" name="expireDateStart" />
                                                <span class="input-group-addon">-</span>
                                                <input type="text" class="input-sm form-control" name="expireDateEnd" />
                                			</div>
                                        </div>
                                    </div>
                                    <div class="form-group search-unit">
                                        <label class="col-lg-4 rost_sousuo">员工状态：</label>
                                        <div class="col-lg-8">
                                        <select class="form-control " name="status" placeholder="全部">
                                            <option value="">全部</option>
                                            <option value="0">在职</option>
                                            <option value="1">离职</option>
                                        </select>
                                        </div>
                                    </div> 
                                    <div class="form-group search-unit">
                                        <label class="col-lg-4 rost_sousuo">停发标志：</label>
                                        <div class="col-lg-8">
                                        <select class="form-control " name="isPayrollFlag" placeholder="全部">
                                            <option value="" >全部</option>
                                            <option value="0">在发</option>
                                            <option value="1">停发</option>
                                        </select>
                                        </div>
                                    </div> 
                                    
                                    <div class="form-group search-unit">
                                        <label for="exampleInputName4" class="col-lg-4 rost_sousuo">证书名称：</label>
                                        <div class="col-lg-8">
                                        <input type="text" class="form-control" id="exampleInputName4" name="certificate">
                                        </div>
                                    </div>
                                   
                                    </div>
                                    <center style=" margin-top:10px;">
                                     <button class="btn btn-info" type="button" id="show" onclick="search()">高级搜索</button>
                                     <!--<a href="user_add.html"><button class="btn btn-primary" type="button">新增</button></a>-->
                                   </center>
                                  </form>
                              </div>
                          </div>
                        </div>
                <!-- 高级搜索 复杂查询结束 -->
                      <form action="" id="fileForm" method="POST" enctype="multipart/form-data">    
                        <div class="panel-heading ">
                           <div class="pull-right">
                             <a href="employee/to_addPerson.do"><button class="btn btn-info btn-xs bfdc" type="button">         <!--连接到员工添加界面-->
                               <span class="pe-7s-add-user"></span> <span class="bold">添加员工</span>
                             </button></a>
                               <span class="">
                             <div class="btn btn-primary btn-xs  rost_piliang">
                                <i class="glyphicon"></i>
                                 批量导入
                                <input id="importFile" name="importFile" class="rost_daoru" type="file" onchange="upload();" multiple>
                              </div>
                              </span>
                              <button class="btn btn-warning btn-xs" type="button" data-target="#myModal7"  data-toggle="modal">
                               <i class=""></i> <span class="bold">全部导出</span>
                             </button>
                            
                           </div>
                            &nbsp; 
                        <div>
                    </div>
                 </div>
                 </form>
         
                 
         			
                     <!--全部导出弹框-->    
                   <div class="modal fade hmodal-success form-row" id="myModal7" tabindex="-1" role="dialog"  aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="color-line"></div>
                            <div class="modal-header">
                             <div class="row">
                              <div class="col-sm-11">
                              <form role="form" class=" form-horizontal formclass">
                                 	<img src="static/img/daochu.png" width="220" height="220" style=" margin-left:146px; margin-bottom:40px;">
                                    <center>
                                    <!--<button class="btn btn-primary show_tow" type="button" id="show">查询</button>-->
                                   <button class="btn btn-warning jxdc" type="button" onclick="return exportPerson();" title="员工信息导出模板">继续导出</button>&nbsp;&nbsp;
                                  
                                    <button class="btn btn-info guanbi" type="button">取消</button>
                                    </center>
                              </form>
                              </div>
                            </div>   
                        </div>
                    </div>
                </div>
                </div>
                <!--全部导出弹框 end--> 
         
                <div class="panel-body" id="allcheck">
                <table id="example2" class="table table-striped table-bordered table-hover rost_table" style=" width:100%;">
                
                
                <thead>
                <tr>
                    <th>ID</th>
                    <th>工号</th>
                    <th>姓名</th>
                    <th>所属部门</th>
                    <th>职位</th>
                    <th>电话</th>
                    <th>邮箱</th>
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
    
    <!--转正界面开始-->
    <div class="modal fade hmodal-success form-row" id="myModal8" tabindex="-1" role="dialog"  aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="color-line"></div>
                <div class="modal-header">
                 <div class="row">
                  <div class="col-sm-11">
                  <form role="form" class=" form-horizontal formclass">
                        <img src="static/img/daochu.png" width="220" height="220" style=" margin-left:146px; margin-bottom:40px;">
                        <center>
                        <!--<button class="btn btn-primary show_tow" type="button" id="show">查询</button>-->
                       <button class="btn btn-warning jxdc" type="button" onclick="downMB('employee/exportPositiveList');" title="员工信息导出模板">继续导出</button>&nbsp;&nbsp;
                      
                        <button class="btn btn-info guanbi" type="button">取消</button>
                        </center>
                  </form>
                  </div>
                </div>   
              </div>
          </div>
        </div>
    </div>


	<div class="content animate-panel mem_zhuanzheng">
    
            <div class="row">
                <div class="col-lg-12">
                    <div class="hpanel">
                    
                    	<div class="panel-heading clearfix">
                            <div class="pull-right">
                                <button class="btn btn-warning btn-xs" type="button" data-target="#myModal8" data-toggle="modal">
                                    <i class=""></i> <span class="bold">全部导出</span>
                                </button>
                                <button class="btn btn-primary btn-xs mem_fanhui" type="button">
                                    <i class=""></i> <span class="bold">返 回</span>
                                </button>
                            </div>
                        </div>
                        
                        <div class="panel-body" id="allcheck2">
                        	
                            <table id="example3" class="table table-striped table-bordered table-hover rost_table" style=" width:100%;">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>工号</th>
                                        <th>姓名</th>
                                        <th>所属部门</th>
                                        <th>职位</th>
                                        <th>电话</th>
                                        <th>邮箱</th>
                                        <th>转正日期</th>
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
<!--转正界面结束-->
    
    
<!--合同到期界面开始-->
	<div class="modal fade hmodal-success form-row" id="myModal9" tabindex="-1" role="dialog"  aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="color-line"></div>
                <div class="modal-header">
                 <div class="row">
                  <div class="col-sm-11">
                  <form role="form" class=" form-horizontal formclass">
                        <img src="static/img/daochu.png" width="220" height="220" style=" margin-left:146px; margin-bottom:40px;">
                        <center>
                        <!--<button class="btn btn-primary show_tow" type="button" id="show">查询</button>-->
                       <button class="btn btn-warning jxdc" type="button" onclick="downMB('employee/exportContractList');" title="员工信息导出模板">继续导出</button>&nbsp;&nbsp;
                      
                        <button class="btn btn-info guanbi" type="button">取消</button>
                        </center>
                  </form>
                  </div>
                </div>   
              </div>
          </div>
        </div>
    </div>


	<div class="content animate-panel mem_daoqi">
            <div class="row">
                <div class="col-lg-12">
                    <div class="hpanel">
                    	
                        <div class="panel-heading clearfix">
                            <div class="pull-right">
                                <button class="btn btn-warning btn-xs" type="button" data-target="#myModal9" data-toggle="modal">
                                    <i class=""></i> <span class="bold">全部导出</span>
                                </button>
                                <button class="btn btn-primary btn-xs mem_fanhui" type="button">
                                    <i class=""></i> <span class="bold">返 回</span>
                                </button>
                            </div>
                        </div>
                        
                        <div class="panel-body" id="allcheck3">
                            <table id="example4" class="table table-striped table-bordered table-hover rost_table" style=" width:100%;">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>工号</th>
                                        <th>姓名</th>
                                        <th>所属部门</th>
                                        <th>职位</th>
                                        <th>电话</th>
                                        <th>邮箱</th>
                                        <th>合同到期时间</th>
                                        <th>意见征询</th>
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
<!--合同到期界面结束-->
    
<!--今天员工生日界面开始-->

	<div class="modal fade hmodal-success form-row" id="myModal10" tabindex="-1" role="dialog"  aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="color-line"></div>
                <div class="modal-header">
                 <div class="row">
                  <div class="col-sm-11">
                  <form role="form" class=" form-horizontal formclass">
                        <img src="static/img/daochu.png" width="220" height="220" style=" margin-left:146px; margin-bottom:40px;">
                        <center>
                        <!--<button class="btn btn-primary show_tow" type="button" id="show">查询</button>-->
                       <button class="btn btn-warning jxdc" type="button" onclick="downMB('employee/birthdayList');" title="员工信息导出模板">继续导出</button>&nbsp;&nbsp;
                      
                        <button class="btn btn-info guanbi" type="button">取消</button>
                        </center>
                  </form>
                  </div>
                </div>   
              </div>
          </div>
        </div>
    </div>

	<div class="content animate-panel mem_jshengri">
            <div class="row">
                <div class="col-lg-12">
                    <div class="hpanel">
                    	
                        <div class="panel-heading clearfix">
                            <div class="pull-right">
                                <button class="btn btn-warning btn-xs" type="button" data-target="#myModal10" data-toggle="modal">
                                    <i class=""></i> <span class="bold">全部导出</span>
                                </button>
                                <button class="btn btn-primary btn-xs mem_fanhui" type="button">
                                    <i class=""></i> <span class="bold">返 回</span>
                                </button>
                            </div>
                        </div>
                        
                        <div class="panel-body" id="allcheck4">
                            <table id="example5" class="table table-striped table-bordered table-hover rost_table" style=" width:100%;">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>工号</th>
                                        <th>姓名</th>
                                        <th>所属部门</th>
                                        <th>职位</th>
                                        <th>电话</th>
                                        <th>邮箱</th>
                                        <th>出生日期</th>
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
<!--今天员工生日界面结束-->


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
    
	<script>
   $(function () {

	/*var table = $('#example2').dataTable() */
    	
      	var table = $('#example2').dataTable({
    		"dom":
    		
    		"<'row'<'col-sm-3'l><'col-sm-8'f><'col-sm-1'<'toolbar'>>>" +
    		"<'row'<'col-sm-12'tr>>" +
    		"<'row'<'col-sm-5'i><'col-sm-7'p>>",
			
			
			/*删除开始*/
			drawCallback:function(){
						$('.demo4').click(function () {
							var personId = $(this).attr("personId");
							swal({
										title: "确定要删除此用户信息吗?",
										text: "注意：用户信息删除后将不可修复!",
										type: "warning",
										showCancelButton: true,
										confirmButtonColor: "#DD6B55",
										confirmButtonText: "是,请删除该用户信息!",
										cancelButtonText: "不, 放弃此操作!",
										closeOnConfirm: false,
										closeOnCancel: false },
										function (isConfirm) {
											if (isConfirm) {
												$.ajax({
													type : "POST",
													url : "employee/deletePerson.do",
													data : {"personId": personId},
													dataType : "json",
													success : function(data) {
														if (data.success == 1) {
															swal("删除成功.", "该用户信息已经被删除.", "success");
															window.location.reload();
														}else if(data.success == 3){
															swal("a", "已有工资记录，删除失败.", "error");
														}
													}
												});
											} else {
												swal("已取消", "用户信息未删除。你这逗我玩呢 :)", "error");
											}
											
										});
							});
				
					
				
				},
			/*删除结束*/
    		"processing": true,
	        "serverSide": true,
	        "sort":true,
	        
			"columns": [
				{ "data": "personId" },
				{ "data": "jobNumber" },
				{ "data": "employeeName" },
				{ "data": "org" }, 
				{ "data": "post" },
				{ "data": "mobile" }, 
				{ "data": "email" },              
				{ "render": function render( data, type, row, meta ){
								return '<a href="#" class="rost_bt"><button data-toggle="dropdown" class="btn btn-info btn-xs demo5 dropdown-toggle" type="button" title="查看">查看</button></a>' +
									   '<a><button data-toggle="dropdown" personId="'+row.personId+'" class="btn btn-danger btn-xs demo4 dropdown-toggle rost_bt_two" type="button" title="删除"><i class="fa fa-trash-o"></i></button></a>';
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
  	            "url": "employee/empdataTableSearch",
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
    
 $(function (){
	  $.ajax({
		  url:"employee/searchBaseData.do",
	      dataType:'json',
	      error: function(XMLHttpRequest, textStatus, errorThrown){
	           alert(textStatus);
	      },
	      success: function(data){	 
		  		var list = data.data;
				var html = '';
				for(var i = 0; i < list.length; i++){
					html += '<option value="'+list[i].baseDataId+'">'+list[i].baseDataName+'</option>';
				}
			   $('#workLocationData').append(html);
			   $('#payrollLocationData').append(html);
			   
	      }
	   });
})
$(function(){
	 $.ajax({
	 	  cache:true,
	 	  type:"POST",
		  url:"employee/searchPositive.do",
	      dataType:'json',
	      async:false,
	      success: function(data){	 
				$("#zhuanzheng").html(data.positiveCount);
				$("#daoqi").html(data.contractCount);
				$("#birthD").html(data.birthCount);
				$("#birthT").html(data.birthTCount);
	      }
	   });

	//转正界面开始

 $('#example3').dataTable({
			"dom":
	    		"<'row'<'col-sm-12'tr>>",
				"processing": true,
        		"serverSide": true,
				"ajax":{
	  	            "url": "employee/searchPositiveList.do",
	  	            "type":"POST",
	  	            "data":function(d){
	  	            }
  	       		 },
				"columns": [
					{ "data": "personId" },
					{ "data": "jobNumber" },
					{ "data": "employeeName" },
					{ "data": "org"},
					{ "data": "post"},
					{ "data": "mobile" },
					{ "data": "email" },
					{ "data": "positiveDate1" }
					
				]
		
	});
	
	//合同到期界面开始
	$('#example4').dataTable({
			"dom":
	    		"<'row'<'col-sm-12'tr>>",
				"processing": true,
        		"serverSide": true,
				"ajax":{
	  	            "url": "employee/searchContractList.do",
	  	            "type":"POST",
	  	            "data":function(d){}
	  	       	},
				"columns": [
					{ "data": "personId" },
					{ "data": "jobNumber" },
					{ "data": "employeeName" },
					{ "data": "org"},
					{ "data": "post"},
					{ "data": "mobile" },
					{ "data": "email" },
					{ "data": "contractEndDate1" },
					{ "render": function render( data, type, row, meta ){
							return '<button class="btn btn-info btn-xs"  onclick="updateFalg('+row.contractId+')">完 成</button>';
					} }
				] 
		
	});
	
	
	
	
	

//今天生日界面开始
$('#example5').dataTable({
		"dom":
    		"<'row'<'col-sm-12'tr>>",
			"processing": true,
       		"serverSide": true,
			"ajax":{
  	            "url": "employee/searchBirthList.do",
  	            "type":"POST",
  	            "data": function(d){d.day = $("#birthInput").val();}
  	        },
			"columns": [
				{ "data": "personId" },
				{ "data": "jobNumber" },
				{ "data": "employeeName" },
				{ "data": "org" }, 
				{ "data": "post" },
				{ "data": "mobile" }, 
				{ "data": "email" },
				{ "data": "birthDate1" }
			]
	});
})


function positive(){
$('#example3').dataTable().api().ajax.reload();
	$(".content").hide();
	$(".mem_zhuanzheng").show();
	
	
}
//转正界面结束


	
function contract() {
	$('#example4').dataTable().api().ajax.reload();
	$(".content").hide();
	$(".mem_daoqi").show();
}
//合同到期界面结束 


function birth(day){
	console.log(day);
	$("#birthInput").val(day);
	$('#example5').dataTable().api().ajax.reload();
	$('.content').hide();
	$('.mem_jshengri').show();
}

$('.mem_fanhui').click(function(){

	$('.content').hide();
	$('.mem_content').show();
})

function updateFalg(id){
	 $.ajax({
		  url:"employee/updateFalg.do",
	      dataType:'json',
	      data:{"contractId":id},
	      success: function(data){	 
	     $('#example4').dataTable().api().ajax.reload();
		   var daoqi =  $('#daoqi').text();
		  var hetong = parseInt(daoqi);
		  $('#daoqi').text(hetong-1);
	      }
	   });
	}
	
function upload(){
	var ajaxCallUrl = "employee/employeeImport";
	 $.ajax({
		    url: ajaxCallUrl,
		    type: 'POST',
		    cache: false,
		    dataType: 'json',
		    data: new FormData($('#fileForm')[0]),
		    processData: false,
		    contentType: false
		}).done(function(data) {
			if(data.result){
				alert("批量导入成功");
				/* if(data.error.length > 0){//未导入数据
					swal({
			            title: "导入数据中有一下错误",
			            text: data.error,
			            type: "success",
						confirmButtonText: "确定"
					});
					$('.confirm').click(function(){
						location.reload();
					});
				}else{
					location.reload();
				} */
				if(data.error.length > 0){//未导入数据
					alert(data.error);
				}
				location.reload();
			}else{
				alert("批量导入失败");
			}
		}).fail(function(data) {
			alert("批量导入失败");
		});
	  
}
function exportPerson(){
	window.open("employee/employeeExport");
}
</script>




<script>
  $(".guanbi").click(function(){
	  
	 $("#myModal7").modal('hide');
	 $("#myModal8").modal('hide');
	 $("#myModal9").modal('hide');
	 $("#myModal10").modal('hide');
	 $("#myModal11").hide();
	  
  })
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
		});
		$(function(){

            $('#datepicker2').datepicker();
            $("#datepicker2").on("changeDate", function(event) {
                $("#my_hidden_input").val(
                        $("#datepicker2").datepicker('getFormattedDate')
                )
            });
		});

		$(function(){

            $('#datepicker3').datepicker();
            $("#datepicker3").on("changeDate", function(event) {
                $("#my_hidden_input").val(
                        $("#datepicker3").datepicker('getFormattedDate')
                )
            });
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
			var day = $("#birthInput").val();
			window.open(moban+"?day="+day);
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