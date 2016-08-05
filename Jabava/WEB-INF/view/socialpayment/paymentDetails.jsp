<%@ page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html>
<head>
	<base href="${pageContext.request.contextPath}/">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <!-- Page title -->
    <title>汇缴信息</title>
    <jsp:include flush="true" page="../common/styles.jsp"></jsp:include>
    <link rel="stylesheet" href="static/css/user_bata.css">
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
  
<!-- Main Wrapper -->
<div id="wrapper">

  <!-- 放主要内容 -->
  <div class="content animate-panel">
    <div class="row">
        <div class="col-lg-12">
            <div class="hpanel">
                <div class="panel-heading">
                    <h4 class="text-center font-bold">
                      <a href="socialpayment/listSocialPay" type="button" class="btn btn-success btn-sm pull-left btn-return m-r-md">返回</a>
                     上海总部3月清单明细-汇缴信息
                    </h4>
                </div>
      <ul role="tabList" class="nav nav-tabs m-t-md" id="">
              <li role="payDetails" class="active">
                <a data-toggle="tab" href="#tab-1">汇缴信息</a>
              </li>
              <li role="payDetails">
                <a data-toggle="tab" href="#tab-2">补缴信息</a>
              </li>
              <li role="payDetails">
                <a data-toggle="tab" href="#tab-3">汇缴信息</a>
              </li>
              <li role="payDetails">
                <a data-toggle="tab" href="#tab-4">补缴信息</a>
              </li>
      </ul>
      <div class="tab-content">
        <div id="tab-1" class="tab-pane active">
          <div class="panel-body"> 
            <div class="table-responsive">
              <table id="detailTable" class="table table-bordered table-hover detailTable" width="100%">
              <thead>
                 <tr>
                  <th>月份</th>
                  <th>社保账号</th>
                  <th>公积金账号</th>
                  <th>缴纳总额</th>
                  <th>企业缴纳总额</th>
                  <th>个人缴纳总额</th>
                  <th>社保总额</th>
                  <th>公积金总额</th>
                  <th style=" padding:0;">
                      <div class="ylbx">养老保险</div>
                      <div class="baoxian">
                          <ul>
                          <li>企业基金</li>
                          <li>企业比例</li>
                          <li>企业金额</li>
                          <li>个人基金</li>
                          <li>个人比例</li>
                          <li class="lasts">个人金额</li>
                          </ul>
                      </div>
                  </th>
                  <th style=" padding:0;">
                      <div class="ylbxs">医疗保险</div>
                      <div class="yiliao">
                          <ul>
                          <li>企业基金</li>
                          <li>企业比例</li>
                          <li>企业金额</li>
                          <li>个人基金</li>
                          <li>个人比例</li>
                          <li class="lasts">个人金额</li>
                          </ul>
                      </div>
                  </th>
                  <th style=" padding:0;">
                      <div class="ylbxs">失业保险</div>
                      <div class="yiliao">
                          <ul>
                         <li>企业基金</li>
                          <li>企业比例</li>
                          <li>企业金额</li>
                          <li>个人基金</li>
                          <li>个人比例</li>
                          <li class="lasts">个人金额</li>
                          </ul>
                      </div>
                  </th>
                  <th style=" padding:0;">
                      <div class="ylbxs">工伤保险</div>
                      <div class="yiliao">
                          <ul>
                          <li>企业基金</li>
                          <li>企业比例</li>
                          <li>企业金额</li>
                          <li>个人基金</li>
                          <li>个人比例</li>
                          <li class="lasts">个人金额</li>
                          </ul>
                      </div>
                  </th>
                  <th style=" padding:0;">
                      <div class="ylbxs">生育保险</div>
                      <div class="yiliao">
                          <ul>
                          <li>企业基金</li>
                          <li>企业比例</li>
                          <li>企业金额</li>
                          <li>个人基金</li>
                          <li>个人比例</li>
                          <li class="lasts">个人金额</li>
                          </ul>
                      </div>
                  </th>
                  <th style=" padding:0;">
                      <div class="ylbxs">住房公积金</div>
                      <div class="yiliao">
                          <ul>
                          <li>企业基金</li>
                          <li>企业比例</li>
                          <li>企业金额</li>
                          <li>个人基金</li>
                          <li>个人比例</li>
                          <li class="lasts">个人金额</li>
                          </ul>
                      </div>
                  </th>
              </tr>
              </thead>
             <tbody>
              <tr>
                 <td>201512</td>
                 <td>李四</td>
                 <td>zd1234567890</td>
                 <td>20</td>
                 <td>25</td>
                 <td>20</td>
                 <td>25</td>
                 <td>2015-12-04</td>
                 <td style=" padding-top:1; padding-bottom:1; padding-left:0; padding-right:0;">
                      <div class="baoxians">
                          <ul>
                          <li>企业基金</li>
                          <li>企业比例</li>
                          <li>企业金额</li>
                          <li>个人基金</li>
                          <li>个人比例</li>
                          <li class="lasts">个人金额</li>
                      </div>
                 </td>
                 <td style=" padding-top:1; padding-bottom:1; padding-left:0; padding-right:0;">
                      <div class="baoxians">
                          <ul>
                          <li>企业基金</li>
                          <li>企业比例</li>
                          <li>企业金额</li>
                          <li>个人基金</li>
                          <li>个人比例</li>
                          <li class="lasts">个人金额</li>
                          </ul>
                      </div>
                 </td>
                 <td style=" padding-top:1; padding-bottom:1; padding-left:0; padding-right:0;">
                      <div class="baoxians">
                          <ul>
                          <li>企业基金</li>
                          <li>企业比例</li>
                          <li>企业金额</li>
                          <li>个人基金</li>
                          <li>个人比例</li>
                          <li class="lasts">个人金额</li>
                          </ul>
                      </div>
                 </td>
                 <td style=" padding-top:1; padding-bottom:1; padding-left:0; padding-right:0;">
                      <div class="baoxians">
                          <ul>
                          <li>企业基金</li>
                          <li>企业比例</li>
                          <li>企业金额</li>
                          <li>个人基金</li>
                          <li>个人比例</li>
                          <li class="lasts">个人金额</li>
                          </ul>
                      </div>
                 </td>
                 <td style=" padding-top:1; padding-bottom:1; padding-left:0; padding-right:0;">
                      <div class="baoxians">
                          <ul>
                          <li>企业基金</li>
                          <li>企业比例</li>
                          <li>企业金额</li>
                          <li>个人基金</li>
                          <li>个人比例</li>
                          <li class="lasts">个人金额</li>
                          </ul>
                      </div>
                 </td>
                 <td style=" padding-top:1; padding-bottom:1; padding-left:0; padding-right:0;">
                      <div class="baoxians">
                          <ul>
                          <li>企业基金</li>
                          <li>企业比例</li>
                          <li>企业金额</li>
                          <li>个人基金</li>
                          <li>个人比例</li>
                          <li class="lasts">个人金额</li>
                          </ul>
                      </div>
                 </td>
              </tr> 
               <tr>
                 <td>201512</td>
                 <td>张三</td>
                 <td>zd1234567890</td>
                 <td>20</td>
                 <td>25</td>
                 <td>20</td>
                 <td>25</td>
                 <td>2015-12-04</td>
                 <td style=" padding-top:1; padding-bottom:1; padding-left:0; padding-right:0;">
                      <div class="yl_baoxian">
                          <ul>
                          <li>企业基金</li>
                          <li>企业比例</li>
                          <li>企业金额</li>
                          <li>个人基金</li>
                          <li>个人比例</li>
                          <li class="lasts">个人金额</li>
                          </ul>
                      </div>
                 </td>
                 <td style=" padding-top:1; padding-bottom:1; padding-left:0; padding-right:0;">
                      <div class="yl_baoxian">
                          <ul>
                            <li>企业基金</li>
                            <li>企业比例</li>
                            <li>企业金额</li>
                            <li>个人基金</li>
                            <li>个人比例</li>
                            <li class="lasts">个人金额</li>
                          </ul>
                      </div>
                 </td>
                 <td style=" padding-top:1; padding-bottom:1; padding-left:0; padding-right:0;">
                      <div class="yl_baoxian">
                          <ul>
                            <li>企业基金</li>
                            <li>企业比例</li>
                            <li>企业金额</li>
                            <li>个人基金</li>
                            <li>个人比例</li>
                            <li class="lasts">个人金额</li>
                          </ul>
                      </div>
                 </td>
                 <td style=" padding-top:1; padding-bottom:1; padding-left:0; padding-right:0;">
                      <div class="baoxians">
                          <ul>
                           <li>企业基金</li>
                            <li>企业比例</li>
                            <li>企业金额</li>
                            <li>个人基金</li>
                            <li>个人比例</li>
                            <li class="lasts">个人金额</li>
                          </ul>
                      </div>
                 </td>
                 <td style=" padding-top:1; padding-bottom:1; padding-left:0; padding-right:0;">
                      <div class="baoxians">
                          <ul>
                            <li>企业基金</li>
                            <li>企业比例</li>
                            <li>企业金额</li>
                            <li>个人基金</li>
                            <li>个人比例</li>
                            <li class="lasts">个人金额</li>
                          </ul>
                      </div>
                 </td>
                 <td style=" padding-top:1; padding-bottom:1; padding-left:0; padding-right:0;">
                      <div class="baoxians">
                          <ul>
                            <li>企业基金</li>
                            <li>企业比例</li>
                            <li>企业金额</li>
                            <li>个人基金</li>
                            <li>个人比例</li>
                            <li class="lasts">个人金额</li>
                          </ul>
                      </div>
                 </td>
              </tr>  
              </tbody>
              </table>
            </div>
          </div>
        </div>  
      </div>
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
<script src="static/bootstrap/vendor/moment/moment.js"></script>
<script src="static/bootstrap/vendor/xeditable/bootstrap3-editable/js/bootstrap-editable.min.js"></script>
<script src="static/bootstrap/vendor/select2-3.5.2/select2.min.js"></script>
<script src="static/bootstrap/vendor/bootstrap-touchspin/dist/jquery.bootstrap-touchspin.min.js"></script>
<script src="static/bootstrap/vendor/bootstrap-datepicker-master/dist/js/bootstrap-datepicker.js"></script>
<script src="static/bootstrap/vendor/bootstrap-datepicker-master/dist/locales/bootstrap-datepicker.zh-CN.min.js"></script>

<!-- for datatable -->
<script src="static/bootstrap/vendor/datatables/media/js/jquery.dataTables.min.js"></script>
<script src="static/bootstrap/vendor/datatables_plugins/integration/bootstrap/3/dataTables.bootstrap.min.js"></script>
<!-- alert -->
<script src="static/bootstrap/vendor/sweetalert/lib/sweet-alert.min.js"></script>

<!-- App scripts -->
<script src="static/bootstrap/scripts/homer.js"></script>
<script>
	$(function () {
        $('.detailTable').dataTable( {
            dom: "<'row'<'col-sm-10'l><'col-sm-2'f>>tp"+
                 "<'row'<'col-sm-5'i>>",
                 ordering:  false,
            "language": {
                "search": "过滤:",
              "lengthMenu": "每页显示 _MENU_ 条记录",
              "zeroRecords": "暂无数据 - 报歉啦?",
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

    });
</script>

</body>
</html>
