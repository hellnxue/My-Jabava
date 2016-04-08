<%@ page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html>
<head>
	<base href="${pageContext.request.contextPath}/">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <!-- Page title -->
    <title>考勤</title>
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
  
  
  <!-- Main Wrapper -->
  <div id="wrapper">
    <div class="normalheader transition animated fadeIn small-header">
      <div class="hpanel">
        <div class="panel-body">
          <div id="hbreadcrumb" class="pull-right m-t-lg">
            <ol class="hbreadcrumb breadcrumb">
              <li><a href="to_index?jump=1">首页</a></li>
              <li>
                <span>考勤</span>
              </li>
              <li class="active"></li>
            </ol>
          </div>
          <h2 class="font-light m-b-xs">
            考勤
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
                <span class="file-input ">
                </span>
                <button class="btn btn-primary btn-xs" type="button" data-target="#myModal7_1" data-toggle="modal">
                  <i class=""></i> <span class="bold">导入考勤</span>
                </button>
                <button class="btn btn-warning btn-xs" type="button" data-target="#myModal7" data-toggle="modal">
                  <i class=""></i> <span class="bold">全部导出</span>
                </button>
                <button class="btn btn-info btn-xs bfdc" type="button">
                  <i class=""></i> <span class="bold">部分导出</span>
                </button>
              </div>
              &nbsp;&nbsp;
              <div>
              </div>
            </div>     <!-- 高级搜索 复杂查询开始 -->
            <div class="collapse out" id="collapseExample" aria-expanded="false" >
              <div class="well well-lg " >
                <div class="row">
                  <form role="form" class="search-form" id="searchForm"> 
                    <div class="clearfix">
                      <div class="form-group search-unit">
                        <label for="exampleInputName2" class="col-lg-4">工号：</label>
                        <div class="col-lg-8">
                          <input type="text" class="form-control" id="job_number" name="job_number">
                        </div>
                      </div>
                      <div class="form-group search-unit">
                        <label for="exampleInputName2" class="col-lg-4">姓名：</label>
                        <div class="col-lg-8">
                          <input type="text" class="form-control" id="employee_name" name="employee_name">
                        </div>
                      </div>
                      <!--开始时间-->
                      <div class="form-group search-unit">
                        <label class="col-lg-4">考勤年月：</label>
                        <div class="input-group date col-lg-8">
                          <div class="input-group date col-lg-12" data-date-format="yyyy-mm"
                          data-date-start-view="1" data-date-min-view-mode="1" data-date-language="zh-CN">
                          <input type="text" class="form-control" id="year_month_record" name="year_month_record">
                          <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                        </div>
                      </div>
                    </div>
                    <!--所属部门-->
                    <div class="form-group search-unit">
                      <label for="exampleInputName4" class="col-lg-4">所属部门：</label>
                      <div class="col-lg-8">
                        <select class="form-control " id="organization_id" name="organization_id" placeholder="全部">
                          <option value="">全部</option>
                          <option value="1">人事</option>
                          <option value="2">行政</option>
                          <option value="3">无效</option>
                        </select>
                      </div>
                    </div> 
                    <div class="form-group search-unit">
                      <label for="exampleInputName2" class="col-lg-4">工作地：</label>
                      <div class="col-lg-8">
                        <input type="text" class="form-control" id="work_location" name="work_location">
                      </div>
                    </div>
                  </div>
                  <center style=" margin-top:10px;">
                    <button class="btn btn-info" type="button" id="show" onclick="serchTime()">查询</button>
                  </center>
                </form>
              </div>
            </div>
          </div>
          <!-- 高级搜索 复杂查询结束 -->
          <div class="panel-body" id="allcheck">
            <table id="example2" class="table table-striped table-bordered table-hover" width="100%">
              <thead>
                <tr>
                  <th style=" width:50px;">
                    <input type="checkbox" id="selAll" onclick="selectAll();" class="yincang"/>
                    <button  class="btn btn-info btn-xs  yincang" type="button" data-target="#myModal8" data-toggle="modal"  style="height:16px; vertical-align:top; margin-top:3px; margin-left:10px;"><i class="pe pe-7s-download"></i></button>
                  </th>
                  <th>工号</th>
                  <th>姓名</th>
                  <th>月份</th>
                  <th>迟到次数</th>
                  <th>早退次数</th>
                  <th>事假天数人</th>
                  <th>病假天数</th>
                  <th>出差天数</th>
                  <th>平日加班(h)</th>
                  <th>周末加班(h)</th>
                  <th>节假日加班(h)</th>
                  <th>调休天数</th>
                  <th>夜班天数</th>
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
  <!-- Footer-->
  <jsp:include flush="true" page="../common/foot.div.jsp"></jsp:include>
</div>
<!--全部导入弹框-->    
<div class="modal fade hmodal-success form-row" id="myModal7_1" tabindex="-1" role="dialog" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="color-line"></div>
      <div class="modal-header">
        <div class="row">
          <div class="col-sm-11">
            <iframe width="100%" src="time/daoru" style="border:0;"></iframe>
          </div>
        </div>   
      </div>
    </div>
  </div>
</div>
<!--全部导入弹框 end--> 
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
                <button class="btn btn-warning jxdc" type="button" onclick="downMB('time/downTimeExcelAll');" title="考勤导出模板">继续导出</button>
                &nbsp;&nbsp;
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
<!--部分导出弹框-->    
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
                <button class="btn btn-warning jxdc" type="button" onclick="downMBP('time/downTimeExcelPart');" title="考勤导出模板">继续导出</button>
                &nbsp;&nbsp;
                <button class="btn btn-info guanbi" type="button" data-dismiss="modal">取消</button>
              </center>
            </form>
          </div>
        </div>   
      </div>
    </div>
  </div>
</div>
<!--部分导出弹框 end--> 
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

/* 	   var table = $('#example2').dataTable() */
   	$.fn.editable.defaults.mode = 'inline';
     	var table = $('#example2').dataTable({
   		"dom":
   		"<'row'<'col-sm-3'l><'col-sm-8'f><'col-sm-1'<'toolbar'>>>" +
   		"<'row'<'col-sm-12 table-responsive'tr>>" +
   		"<'row'<'col-sm-5'i><'col-sm-7'p>>",
   		//修改
   		drawCallback:function(){
    			$('.editable').editable({
    				url : 'time/updAttend',
    				validate : function(value) {
    					if ($.trim(value) == '') {
    						return 'This field is required';
    					}
    				},
    				error : function(response, newValue) {
    					if (response.status === 404) {
    						return '服务器问题（404），请联系管理员';
    					} else if (response.status === 500) {
    						return '服务器问题（500），请联系管理员';
    					}
    				},
    				success : function(response, newValue) {
    					var obj = JSON.parse(response); 
    					if (obj.msg != 'upd_success') {
    						return '修改失败';
    					}
    				}
    			});
			},
		//json
		 "processing": true,
       		"serverSide": true,
			"columns": [
			{"render": function render( data, type, row, meta ){
				return'<input type="checkbox" class="yincang" name="checkAll" id="checkAll'+row.attend_id+'" value="'+row.attend_id+'" >';
			},"orderable": false },
			{"data": "job_number"},
			{"data": "employee_name" },
			{"data": "year_month_record","render": function render( data, type, row, meta ){
			return'<a id="year_month_record" class="editable editable-click" data-title="Enter your firstname" data-placeholder="Required" data-placement="right" data-pk="'+row.attend_id+'" data-type="text" href="#" style="display: inline;">'+row.year_month_record+'</a>';
			} },
			{"data": "late_times","render": function render( data, type, row, meta ){
			return'<a id="late_times" class="editable editable-click" data-title="Enter your firstname" data-placeholder="Required" data-placement="right" data-pk="'+row.attend_id+'" data-type="text" href="#" style="display: inline;">'+row.late_times+'</a>';
			} },
			{"data": "leave_early_times","render": function render( data, type, row, meta ){
			return'<a id="leave_early_times" class="editable editable-click" data-title="Enter your firstname" data-placeholder="Required" data-placement="right" data-pk="'+row.attend_id+'" data-type="text" href="#" style="display: inline;">'+row.leave_early_times+'</a>';
			} },
			{"data": "all_leave","render": function render( data, type, row, meta ){
			return'<a id="all_leave" class="editable editable-click" data-title="Enter your firstname" data-placeholder="Required" data-placement="right" data-pk="'+row.attend_id+'" data-type="text" href="#" style="display: inline;">'+row.all_leave+'</a>';
			} },
			{"data": "sick_leave","render": function render( data, type, row, meta ){
			return'<a id="sick_leave" class="editable editable-click" data-title="Enter your firstname" data-placeholder="Required" data-placement="right" data-pk="'+row.attend_id+'" data-type="text" href="#" style="display: inline;">'+row.sick_leave+'</a>';
			} },
			{"data": "sick_leave","render": function render( data, type, row, meta ){
			return'<a id="business" class="editable editable-click" data-title="Enter your firstname" data-placeholder="Required" data-placement="right" data-pk="'+row.attend_id+'" data-type="text" href="#" style="display: inline;">'+row.business+'</a>';
			} },
			{"data": "work_overtime","render": function render( data, type, row, meta ){
			return'<a id="work_overtime" class="editable editable-click" data-title="Enter your firstname" data-placeholder="Required" data-placement="right" data-pk="'+row.attend_id+'" data-type="text" href="#" style="display: inline;">'+row.work_overtime+'</a>';
			} },
			{"data": "week_overtime","render": function render( data, type, row, meta ){
			return'<a id="week_overtime" class="editable editable-click" data-title="Enter your firstname" data-placeholder="Required" data-placement="right" data-pk="'+row.attend_id+'" data-type="text" href="#" style="display: inline;">'+row.week_overtime+'</a>';
			} },
			{"data": "holidays_overtime","render": function render( data, type, row, meta ){
			return'<a id="holidays_overtime" class="editable editable-click" data-title="Enter your firstname" data-placeholder="Required" data-placement="right" data-pk="'+row.attend_id+'" data-type="text" href="#" style="display: inline;">'+row.holidays_overtime+'</a>';
			} },
			{"data": "adjust_day","render": function render( data, type, row, meta ){
			return'<a id="adjust_day" class="editable editable-click" data-title="Enter your firstname" data-placeholder="Required" data-placement="right" data-pk="'+row.attend_id+'" data-type="text" href="#" style="display: inline;">'+row.adjust_day+'</a>';
			} },
			{"data": "night_shift","render": function render( data, type, row, meta ){
			return'<a id="night_shift" class="editable editable-click" data-title="Enter your firstname" data-placeholder="Required" data-placement="right" data-pk="'+row.attend_id+'" data-type="text" href="#" style="display: inline;">'+row.night_shift+'</a>';
			} },
			{ "render": function render( data, type, row, meta ){
			return'<button data-toggle="dropdown" class="btn btn-danger btn-xs demo4 dropdown-toggle" type="button" onclick="delAttend(\''+row.attend_id+'\')">'+
                       '<i class="fa fa-trash-o"></i>'+
                   '</button>';
			},"orderable": false }
			 
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
   			 },
   			 "ajax":{
	  	            "url": "time/getTimeListPage",
	  	            "type":"POST",
	  	            "data": function ( d ) {
		    			d.data = JSON.stringify($('#searchForm').serializeObject());
	  	            }
				}
   	    	}); 

    	$("div.toolbar").html('<button class="btn btn-info  btn-sm pull-right" type="button" data-toggle="collapse" data-target="#collapseExample" aria-expanded="false" aria-controls="collapseExample"><i class="fa">高级搜索</i></button>');
    }); 
    function serchTime(){
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
    function delAttend(attend_id) {
		swal({
			title : "确定要删除此考勤记录吗?",
			text : "注意：考勤记录删除后将不可恢复!",
			type : "warning",
			showCancelButton : true,
			confirmButtonColor : "#DD6B55",
			confirmButtonText : "是,请删除该考勤记录!",
			cancelButtonText : "不, 放弃此操作!",
			closeOnConfirm : false,
			closeOnCancel : false
		}, function(isConfirm) {
			if (isConfirm) {
				$.ajax({
					type : "POST",
					url : "time/delAttend",
					data : {
						"attend_id" : attend_id
					},
					success : function(data) {
						if (data.msg == 'del_success') {
							swal("删除成功!", "该考勤记录已经被删除.", "success");
							$('#example2').dataTable().api().ajax.reload();
						} else {
							swal("删除失败", "删除失败.", "error");
						}
					}
				});
			} else {
				swal("已取消", "该考勤记录未删除.", "error");
			}
		});
	}
</script>
<script>
$(".guanbi").click(function(){
	 $('#myModal7_1').modal('hide');  
	 $('#myModal7').modal('hide');
	 $("#myModal8").modal('hide');
});
</script>
<!--日历-->
 <script>

        $(function(){

            $('#datepicker').datepicker();
            $("#datepicker").on("changeDate", function(event) {
                $("#my_hidden_input").val($("#datepicker").datepicker('getFormattedDate'))
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
function selectAll()
{
  	 var selAll = document.getElementById("selAll");
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
  
	<!--导出考勤-->
	<script>
	function downMB(moban) {
		$('#myModal7').modal('hide');
		window.open(moban);
	}
	function downMBP(moban) {
		$('#myModal8').modal('hide');
		var param='';
		var obj = document.getElementsByName("checkAll");
		for(var i=0; i<obj.length; i++){
			if(obj[i].checked){
				if(param==''){
					param+=obj[i].value;
				}
				else{
					param+=','+obj[i].value;
				}
			}
		}
		window.open(moban+'?params='+param);
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