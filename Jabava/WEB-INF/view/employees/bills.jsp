<%@ page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html>
<head>
	<base href="${pageContext.request.contextPath}/">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <!-- Page title -->
    <title>员工信息</title>
    <jsp:include flush="true" page="../common/styles.jsp"></jsp:include>
    <link rel="stylesheet" href="static/css/user_bata.css">
    <link rel="stylesheet" href="static/css/style_1.css" media="screen">
    <link rel="stylesheet" href="static/css/bill.css">
   

    
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
<div id="wrapper" class="min-h">
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
                        <span></span>
                    </li>
                </ol>
            </div>
            <h2 class="font-light m-b-xs">
                员工信息
            </h2>
            <small>待定</small>
        </div>
    </div>
</div>

<!-- 放主要内容 -->
                
           <!--账单--> 
           <div class="content animate-panel">
            <div class="row">
                <div class="hpanel hblue">   
                      <!--引入员工信息导航 开始--> 
                      <jsp:include flush="true" page="employee_nav.jsp"></jsp:include>
                      <!--引入员工信息导航 结束-->
                    <div class="panel-heading hbuilt">
                        <div class="panel-tools panel-but">
                        <button class="btn btn-success " type="button" >支付</button>
                        <button class="btn btn-default " type="button"  style="display:none;">已支付</button>
                         </div>   
                         <h4 class="font-light m-b-xs">账单查看 </h4>
                           <small class="zhangdan_nianyues">账单年月</small><small class="zhangdan_nianyues">201510月</small>
                     </div>
                        <div class="panel-body panel-bag">
                            <table id="example2" class="table table-striped table-bordered table-hover" width="100%">
                                <thead>
                                    <tr>
                                        <th>雇员编号</th>
                                        <th>姓名</th>
                                        <th>证件号</th>
                                        <th>工作地</th>
                                        <th>账单年月</th>
                                        <th>服务年月</th>
                                        <th style=" padding:0;">
                                            <div class="ylbx">养老保险</div>
                                            <div class="baoxian">
                                                <ul>
                                                    <li>城市</li>
                                                    <li>金额</li>
                                                    <li>单位基数</li>
                                                    <li>单位比例</li>
                                                    <li>单位附加额</li>
                                                    <li>单位金额</li>
                                                    <li>个人基数</li>
                                                    <li>个人比例</li>
                                                    <li>个人附加额</li>
                                                    <li class="lasts">个人金额</li>
                                                </ul>
                                            </div>
                                        </th>
                                        <th style=" padding:0;">
                                            <div class="ylbxs">医疗保险</div>
                                            <div class="yiliao">
                                                <ul>
                                                    <li>城市</li>
                                                    <li>金额</li>
                                                    <li>单位基数</li>
                                                    <li>单位比例</li>
                                                    <li>单位附加额</li>
                                                    <li>单位金额</li>
                                                    <li>个人基数</li>
                                                    <li>个人比例</li>
                                                    <li>个人附加额</li>
                                                    <li class="lasts">个人金额</li>
                                                </ul>
                                            </div>
                                        </th>
                                        <th style=" padding:0;">
                                            <div class="ylbxs">失业保险</div>
                                            <div class="yiliao">
                                                <ul>
                                                    <li>城市</li>
                                                    <li>金额</li>
                                                    <li>单位基数</li>
                                                    <li>单位比例</li>
                                                    <li>单位附加额</li>
                                                    <li>单位金额</li>
                                                    <li>个人基数</li>
                                                    <li>个人比例</li>
                                                    <li>个人附加额</li>
                                                    <li class="lasts">个人金额</li>
                                                </ul>
                                            </div>
                                        </th>
                                        <th style=" padding:0;">
                                            <div class="ylbxs">工伤保险</div>
                                            <div class="yiliao">
                                                <ul>
                                                    <li>城市</li>
                                                    <li>金额</li>
                                                    <li>单位基数</li>
                                                    <li>单位比例</li>
                                                    <li>单位附加额</li>
                                                    <li>单位金额</li>
                                                    <li>个人基数</li>
                                                    <li>个人比例</li>
                                                    <li>个人附加额</li>
                                                    <li class="lasts">个人金额</li>
                                                </ul>
                                            </div>
                                        </th>
                                        <th style=" padding:0;">
                                            <div class="ylbxs">生育保险</div>
                                            <div class="yiliao">
                                                <ul>
                                                    <li>城市</li>
                                                    <li>金额</li>
                                                    <li>单位基数</li>
                                                    <li>单位比例</li>
                                                    <li>单位附加额</li>
                                                    <li>单位金额</li>
                                                    <li>个人基数</li>
                                                    <li>个人比例</li>
                                                    <li>个人附加额</li>
                                                    <li class="lasts">个人金额</li>
                                                </ul>
                                            </div>
                                        </th>
                                        <th style=" padding:0;">
                                            <div class="ylbxs">住房公积金</div>
                                            <div class="yiliao">
                                                <ul>
                                                    <li>城市</li>
                                                    <li>金额</li>
                                                    <li>单位基数</li>
                                                    <li>单位比例</li>
                                                    <li>单位附加额</li>
                                                    <li>单位金额</li>
                                                    <li>个人基数</li>
                                                    <li>个人比例</li>
                                                    <li>个人附加额</li>
                                                    <li class="lasts">个人金额</li>
                                                </ul>
                                            </div>
                                        </th>
                                        <th style=" padding:0;">
                                            <div class="fsb">非社保公积金</div>
                                            <div class="shebao">
                                                <ul>
                                                    <li class="lasts">个人金额</li>
                                                </ul>
                                            </div>
                                        </th>
                                        <th>单位小计</th>
                                        <th>个人小计</th>
                                        <th>合计</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                     <td>201512</td>
                                     <td>李四</td>
                                     <td>zd1234567890</td>
                                     <td>20</td>
                                     <td>25</td>
                                     <td>2015-12-04</td>
                                     <td style=" padding-top:1; padding-bottom:1; padding-left:0; padding-right:0;">
                                        <div class="baoxians">
                                            <ul>
                                                <li>北京</li>
                                                <li>111111</li>
                                                <li>22</li>
                                                <li>33</li>
                                                <li>44</li>
                                                <li>55</li>
                                                <li>66</li>
                                                <li>77</li>
                                                <li>88</li>
                                                <li class="lasts">99</li>
                                            </ul>
                                        </div>
                                    </td>
                                    <td style=" padding-top:1; padding-bottom:1; padding-left:0; padding-right:0;">
                                        <div class="baoxians">
                                            <ul>
                                                <li>北京</li>
                                                <li>11</li>
                                                <li>22</li>
                                                <li>33</li>
                                                <li>44</li>
                                                <li>55</li>
                                                <li>66</li>
                                                <li>77</li>
                                                <li>88</li>
                                                <li class="lasts">99</li>
                                            </ul>
                                        </div>
                                    </td>
                                    <td style=" padding-top:1; padding-bottom:1; padding-left:0; padding-right:0;">
                                        <div class="baoxians">
                                            <ul>
                                                <li>北京</li>
                                                <li>11</li>
                                                <li>22</li>
                                                <li>33</li>
                                                <li>44</li>
                                                <li>55</li>
                                                <li>66</li>
                                                <li>77</li>
                                                <li>88</li>
                                                <li class="lasts">99</li>
                                            </ul>
                                        </div>
                                    </td>
                                    <td style=" padding-top:1; padding-bottom:1; padding-left:0; padding-right:0;">
                                        <div class="baoxians">
                                            <ul>
                                                <li>北京</li>
                                                <li>11</li>
                                                <li>22</li>
                                                <li>33</li>
                                                <li>44</li>
                                                <li>55</li>
                                                <li>66</li>
                                                <li>77</li>
                                                <li>88</li>
                                                <li class="lasts">99</li>
                                            </ul>
                                        </div>
                                    </td>
                                    <td style=" padding-top:1; padding-bottom:1; padding-left:0; padding-right:0;">
                                        <div class="baoxians">
                                            <ul>
                                                <li>北京</li>
                                                <li>11</li>
                                                <li>22</li>
                                                <li>33</li>
                                                <li>44</li>
                                                <li>55</li>
                                                <li>66</li>
                                                <li>77</li>
                                                <li>88</li>
                                                <li class="lasts">99</li>
                                            </ul>
                                        </div>
                                    </td>
                                    <td style=" padding-top:1; padding-bottom:1; padding-left:0; padding-right:0;">
                                        <div class="baoxians">
                                            <ul>
                                                <li>北京</li>
                                                <li>11</li>
                                                <li>22</li>
                                                <li>33</li>
                                                <li>44</li>
                                                <li>55</li>
                                                <li>66</li>
                                                <li>77</li>
                                                <li>88</li>
                                                <li class="lasts">99</li>
                                            </ul>
                                        </div>
                                    </td>
                                    <td>20</td>
                                    <td>20</td>
                                    <td>25</td>
                                    <td>2015-12-04</td>
                                </tr> 
                                <tr>
                                 <td>201512</td>
                                 <td>张三</td>
                                 <td>zd1234567890</td>
                                 <td>20</td>
                                 <td>25</td>
                                 <td>2015-12-04</td>
                                 <td style=" padding-top:1; padding-bottom:1; padding-left:0; padding-right:0;">
                                    <div class="yl_baoxian">
                                        <ul>
                                            <li>北京</li>
                                            <li>11</li>
                                            <li>22</li>
                                            <li>33</li>
                                            <li>44</li>
                                            <li>55</li>
                                            <li>66</li>
                                            <li>77</li>
                                            <li>88</li>
                                            <li class="lasts">99</li>
                                        </ul>
                                    </div>
                                </td>
                                <td style=" padding-top:1; padding-bottom:1; padding-left:0; padding-right:0;">
                                    <div class="yl_baoxian">
                                        <ul>
                                            <li>北京</li>
                                            <li>11</li>
                                            <li>22</li>
                                            <li>33</li>
                                            <li>44</li>
                                            <li>55</li>
                                            <li>66</li>
                                            <li>77</li>
                                            <li>88</li>
                                            <li class="lasts">99</li>
                                        </ul>
                                    </div>
                                </td>
                                <td style=" padding-top:1; padding-bottom:1; padding-left:0; padding-right:0;">
                                    <div class="yl_baoxian">
                                        <ul>
                                            <li>北京</li>
                                            <li>11</li>
                                            <li>22</li>
                                            <li>33</li>
                                            <li>44</li>
                                            <li>55</li>
                                            <li>66</li>
                                            <li>77</li>
                                            <li>88</li>
                                            <li class="lasts">99</li>
                                        </ul>
                                    </div>
                                </td>
                                <td style=" padding-top:1; padding-bottom:1; padding-left:0; padding-right:0;">
                                    <div class="baoxians">
                                        <ul>
                                            <li>北京</li>
                                            <li>11</li>
                                            <li>22</li>
                                            <li>33</li>
                                            <li>44</li>
                                            <li>55</li>
                                            <li>66</li>
                                            <li>77</li>
                                            <li>88</li>
                                            <li class="lasts">99</li>
                                        </ul>
                                    </div>
                                </td>
                                <td style=" padding-top:1; padding-bottom:1; padding-left:0; padding-right:0;">
                                    <div class="baoxians">
                                        <ul>
                                            <li>北京</li>
                                            <li>11</li>
                                            <li>22</li>
                                            <li>33</li>
                                            <li>44</li>
                                            <li>55</li>
                                            <li>66</li>
                                            <li>77</li>
                                            <li>88</li>
                                            <li class="lasts">99</li>
                                        </ul>
                                    </div>
                                </td>
                                <td style=" padding-top:1; padding-bottom:1; padding-left:0; padding-right:0;">
                                    <div class="baoxians">
                                        <ul>
                                            <li>北京</li>
                                            <li>11</li>
                                            <li>22</li>
                                            <li>33</li>
                                            <li>44</li>
                                            <li>55</li>
                                            <li>66</li>
                                            <li>77</li>
                                            <li>88</li>
                                            <li class="lasts">99</li>
                                        </ul>
                                    </div>
                                </td>
                                <td>20</td>
                                <td>20</td>
                                <td>25</td>
                                <td>2015-12-04</td>
                            </tr>                                                                                              
                              </tbody>
                           </table>
                </div>
                        
                    
                </div>
            </div>
            </div>
                 <!--账单结束-->         
                        
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
<script src="static/bootstrap/vendor/moment/moment.js"></script>
<script src="static/bootstrap/vendor/xeditable/bootstrap3-editable/js/bootstrap-editable.min.js"></script>
<script src="static/bootstrap/vendor/select2-3.5.2/select2.min.js"></script>
<script src="static/bootstrap/vendor/bootstrap-touchspin/dist/jquery.bootstrap-touchspin.min.js"></script>
<script src="static/bootstrap/vendor/bootstrap-datepicker-master/dist/js/bootstrap-datepicker.min.js"></script>

<!-- for datatable -->
<script src="static/bootstrap/vendor/datatables/media/js/jquery.dataTables.min.js"></script>
<script src="static/bootstrap/vendor/datatables_plugins/integration/bootstrap/3/dataTables.bootstrap.min.js"></script>
<!-- alert -->
<script src="static/bootstrap/vendor/sweetalert/lib/sweet-alert.min.js"></script>

<!-- App scripts -->
<script src="static/bootstrap/scripts/homer.js"></script>
<script src="static/js/yuangong.js"></script>
<script>
   $(function () {

/* 	   var table = $('#example2').dataTable() */
    	
      	var table = $('#example2').dataTable({
    		"dom":
    		
    		"<'row'<'col-sm-3 col-md-5 col-lg-5'l><'col-sm-5 col-md-4 col-lg-7'f><'col-sm-4 col-md-3 col-lg-0 text-right'<'toolbar'>>>" +
            "<'row'<'col-sm-12 table-responsive'tr>>" +
             "<'row'<'col-sm-5'i><'col-sm-7'p>>",
    		
    	 	  "language": {
		            "lengthMenu": "每页显示 _MENU_ 条记录",
		            "zeroRecords": "暂无数据 - 报歉啦?",
		            "info": "显示 第 _PAGE_ 页 共 _PAGES_ 页",
		            "infoEmpty": "暂无数据",
		            "infoFiltered": "(筛选自 _MAX_ 条记录)"
    			 }  
    	    	}); 
    }); 

</script>

<script>
    <!--删除-->
     function deleteBtnInfo(){
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
	}
  <!--取消效果-->
  $(".guanbi").click(function(){
	  
	 $("#myModal7").modal('hide');
	 $("#myModal8").modal('hide');
	 $("#myModal9").modal('hide');
	  
  })
</script>
<!--日历-->
<script>

        $(function(){
			deleteBtnInfo();
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
</body>
</html>