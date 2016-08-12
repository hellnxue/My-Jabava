<%@ page contentType="text/html; charset=utf-8" %>
<%@page import="com.jabava.utils.RequestUtil"%>
<!DOCTYPE html>
<html>
<head>
  <base href="${pageContext.request.contextPath}/">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <!-- Page title -->
    <title>订单</title>
    <!-- Vendor styles -->
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
  <div class="normalheader transition animated fadeIn small-header">
    <div class="hpanel">
      <div class="panel-body">
        <div id="hbreadcrumb" class="m-t-xs m-b-xs">
          <h2 class="font-normal m-b-xs text-center">
            ${year} 订单
          </h2>
        </div>
      </div>
    </div>
  </div>
  <!-- 放主要内容 -->
  <div class="content animate-panel check_hide">
    <div class="row">
      <div class="col-lg-12">
        <div class="hpanel">
          <div class="panel-body">
            <table id="billTable" class="table table-bordered table-hover" width="100%">
              <thead>
                <tr>
                  <th>年月</th>
                  <th>服务人数</th>
                  <th>增员人数</th>
                  <th>减员人数</th>
                  <th>操作</th>                         
                </tr>
              </thead>
              <tbody>                                                                                                                             
              </tbody>
            </table>
            <div class="text-right">
              <div class="btn-group">
                <button id="previous" class="btn btn-default btn-xs">上一年</button>
                <button id="year" class="btn btn-info btn-xs">${year}</button>
                <button id="next" class="btn btn-default btn-xs" disabled="disabled">下一年</button>
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
  var currentYear = "${year}";
  var table;
  $(function () {
    loadTable({"year": currentYear});

    $('#previous').click(function () {
        var year = $('#year').text();
        year = eval(year) - 1;
        $('#year').text(year);
       
        $('.orderYear').html(year+'订单');
        $('#next').attr('disabled',false);
        if(year == 1990){
          $('#previous').attr('disabled',true);  
        }
        loadTable({"year": year});
    });

    $('#next').click(function () {
        var year = $('#year').text();
        year = eval(year) + 1;
        $('#year').text(year);
        $('.orderYear').html(year+'订单');
        $('#previous').attr('disabled',false);
        if(year == currentYear){
          $('#next').attr('disabled',true);
        }
        loadTable({"year": year});
    });

  });

  function loadTable(params){
    if(table){
      table.destroy();
    }
    table = $('#billTable').DataTable({
      "dom":
      "<'row'<'col-sm-12 table-responsive'tr>>",
      //json
      "processing": true,
      "serverSide": true,
      "bDestroy": true,
      "ordering": false,
      "ajax": {
        "url":"order/findOrderListPageNew",
        "data":params?params:{}
        
      },
      "columns": [
      { "data": "yearmonth" },
      { "data": "serviceCount"},
      { "data": "addCount" },
      { "data": "delCount"},
      { "render": function render( data, type, row, meta ){
        var strHtml = '';
        <% if(RequestUtil.hasPower("order_od")){ %>
        strHtml += '<td><a class="btn btn-success btn-xs" type="button" href="order/toOrderList?yearmonth='+row.yearmonth+'">明细</a></td>';
        <% } %>
        return strHtml;
      } 
      }
      ],
      "columnDefs": [
      {defaultContent: '', targets: '_all'}
      ]

    });

  }
</script>
</body>
</html>
