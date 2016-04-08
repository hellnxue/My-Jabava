<!DOCTYPE html>
<html>
<head>
 <%@ page contentType="text/html; charset=utf-8" %>
    <meta charset="utf-8">
    
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <!-- Page title -->
    <title>社保计算器</title>

    <!-- Place favicon.ico and apple-touch-icon.png in the root directory -->
    <!--<link rel="shortcut icon" type="image/ico" href="favicon.ico" />-->

    <!-- Vendor styles -->
    <link rel="stylesheet" href="../bootstrap/vendor/fontawesome/css/font-awesome.css" />
    <link rel="stylesheet" href="../bootstrap/vendor/metisMenu/dist/metisMenu.css" />
    <link rel="stylesheet" href="../bootstrap/vendor/animate.css/animate.css" />
    <link rel="stylesheet" href="../bootstrap/vendor/bootstrap/dist/css/bootstrap.css" />
    <link rel="stylesheet" href="../bootstrap/vendor/xeditable/bootstrap3-editable/css/bootstrap-editable.css" />
    <link rel="stylesheet" href="../bootstrap/vendor/select2-3.5.2/select2.css" />
    <link rel="stylesheet" href="../bootstrap/vendor/select2-bootstrap/select2-bootstrap.css" />
    <link rel="stylesheet" href="../bootstrap/vendor/bootstrap-touchspin/dist/jquery.bootstrap-touchspin.min.css" />
    <link rel="stylesheet" href="../bootstrap/vendor/bootstrap-datepicker-master/dist/css/bootstrap-datepicker3.min.css" />
    <link rel="stylesheet" href="../bootstrap/vendor/awesome-bootstrap-checkbox/awesome-bootstrap-checkbox.css" />

      
    <!-- for data table -->
    <link rel="stylesheet" href="../bootstrap/vendor/datatables_plugins/integration/bootstrap/3/dataTables.bootstrap.css" />
    <!-- for alert -->
     <link rel="stylesheet" href="../bootstrap/vendor/sweetalert/lib/sweet-alert.css" />

    <!-- App styles -->
    <link rel="stylesheet" href="../bootstrap/fonts/pe-icon-7-stroke/css/pe-icon-7-stroke.css" />
    <link rel="stylesheet" href="../bootstrap/fonts/pe-icon-7-stroke/css/helper.css" />
    <link rel="stylesheet" href="../bootstrap/styles/style.css">
   <!-- <link rel="stylesheet" href="css/user.css">-->
    <link rel="stylesheet" href="../css/style.css">

</head>
<body >
<!--splash screen-->
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

<!----请在这放主要内容 ，比如：导航条,搜索块，列表等----->
<div class="normalheader transition animated fadeIn small-header">
    <div class="hpanel">
        <div class="panel-body"><!-- 
            <a class="small-header-action" href="">
                <div class="clip-header">
                    <i class="fa fa-arrow-up"></i>
                </div>
            </a> -->

            <div id="hbreadcrumb" class="pull-right m-t-lg">
                <ol class="hbreadcrumb breadcrumb">
                    <li><a href="to_index?jump=1">首页</a></li>
                    <li>
                        <span>社保计算器</span>
                    </li>
                    <li class="active">
                        <span></span>
                    </li>
                </ol>
            </div>
            <h2 class="font-light m-b-xs">
               社保计算器
            </h2>
            <small>待定</small>
        </div>
    </div>
</div>



 <!--社保计算器主要内容-->
    <div class="content animate-panel">
    	<div class="container">
                <div class="col-lg-8 boxs">
                    <div class="hpanel">
                    <div class="panel-heading">
                    <div class="panel-tools">
                    <a class="closebox">
                    <i class="fa fa-times"></i>
                    </a>
                    </div>
                    <span>社保计算器</span>
                    </div>
                    <!--右边-->
                  <center>  
        	       <span class="nosg">社保与公积金缴费明细</span>
                  </center>
                  <!--结束-->
           <div class="lr">
          <div class="left">
     <form role="form" class="form-horizontal left">
     <!--选择城市-->
     <div class="form-group form-width">
       <label class="col-sm-4 control-label noweight">选择城市</label>
         <div class="col-sm-8">
           <div class="input-group">
             <input class="form-control" type="text" id="get" placeholder="请选择">
               <div class="input-group-btn">
                <button class="btn btn-default dropdown-toggle drop-pro" type="button" data-toggle="dropdown">
                  <span class="caret"></span>
                </button>
        	   </div>
           </div>
        </div>
    </div>
    <!--省份/城市-->
    	<div class="star" style="display:none">
    		<div class="span-nav">
            	<span class="bor province" id="pro1">省份</span><span class="city" id="city">城市</span>
            </div>
            <!--省份-->
             <div class="select_city_con_wrap procity" >
                <div class="select_city_con actived prty" type="p">
                   <dl>
                     <dt>A-G</dt>
                       <dd class="dw" id="procity1">
                         <a href="javascript:;" attrid="3" title="安徽" class="current">安徽</a>
                         <a href="javascript:;" attrid="2" title="北京">北京</a>
                         <a href="javascript:;" attrid="4" title="福建">福建</a>
                         <a href="javascript:;" attrid="8" title="贵州">贵州</a>
                         <a href="javascript:;" attrid="6" title="广东">广东</a>
                         <a href="javascript:;" attrid="6" title="广东">广东</a>
                       </dd>
                    </dl>
                  </div>
            </div>
            <!--城市-->
            <div class="select_city_con_wrap sltcity" style="display:none; min-height:40px;">
                <div class="select_city_con actived stly" type="p">
                   <dl>
                     <dt>A-G</dt>
                       <dd class="dw" id="procity2">
                         <a href="javascript:;" attrid="3" title="安庆" class="current">安庆</a>
                         <a href="javascript:;" attrid="2" title="滁州">滁州</a>
                         <a href="javascript:;" attrid="4" title="亳州">亳州</a>
                         <a href="javascript:;" attrid="8" title="巢湖">巢湖</a>
                          <a href="javascript:;" attrid="8" title="巢湖">巢湖</a>
                           <a href="javascript:;" attrid="8" title="巢湖">巢湖</a>
                       </dd>
                    </dl>
                  </div>
            </div>
    	</div>
    <!--省份/城市 end-->
    
    <!--户籍性质-->
    <div class="form-group form-width">
       <label class="col-sm-4 control-label noweight">户籍性质</label>
         <div class="col-sm-8">
           <div class="input-group">
             <input id="get1" class="form-control" type="text" placeholder="请选择">
               <div class="input-group-btn">
                <button class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown">
                  <span class="caret"></span>
                </button>
                  <ul class="dropdown-menu pull-right sty widh" id="test">
                    <li><a href="#">本地城镇</a></li>
					<li><a href="#">本地农村</a></li>
                    <li><a href="#">外地城镇</a></li>
                    <li><a href="#">外地农村</a></li>
                    <li><a href="#">外地城镇</a></li>
                    <li><a href="#">外地农村</a></li>
                   </ul>
        	  </div>
           </div>
        </div>
    </div>   
    <!--户籍性质 end-->
    <!--社保基数-->
    <div class="form-group form-width">
       <label class="col-sm-4 control-label noweight">社保基数</label>
         <div class="col-sm-8">
           <div class="input-group col-sm-12">
             <input class="form-control" type="text" data-min="2060.00" data-max="13096.00" id="jishu">
           </div>
        </div>
    </div>
    <!--技术范围/是否缴纳-->
    <div class="form-group status form-width yc" style="display:none">
       <label class="col-sm-5 control-label js noyes">基数范围3271.00到16353.00</label>
       <label class="col-sm-5 control-label js yes" style="display:none"></label>
        <div class="gjjin">
          <label class="checkbox-inline checkbox">
            <input id="inlineCheckbox1" type="checkbox" value="option1" style=" margin-top:1px;">
    			是否缴纳公积金
         </label>
       </div>
   </div>
    <!--公积金基数-->
    <div class="form-group form-width yc" style="display:none">
       <label class="col-sm-4 control-label noweight">公积金基数</label>
         <div class="col-sm-8">
           <div class="input-group col-sm-12">
             <input class="form-control " type="text" data-min="930.00" data-max="11100.00" id="gongjijin">
           </div>
        </div>
    </div>
   <!--技术范围-->
    <div class="form-group status form-width yc" style="display:none">
       <label class="col-sm-5 control-label js noyes" >基数范围3271.00到16353.00</label>
       <label class="col-sm-5 control-label js yes" style="display:none"></label>
   </div> 
   <!--缴纳比例-->
   <div class="form-group form-width yc" style="display:none">
       <label class="col-sm-4 control-label noweight">缴纳比例</label>
       	<div class="col-sm-8">
         <div class="input-group col-sm-6 pull-left">
         	<span class="input-group-addon noback">个人</span>
            <input class="form-control" type="text" data-min="10.00" data-max="10.00" id="geren">
        </div>
        <div class="input-group col-sm-6 pull-right">
         	<span class="input-group-addon noback">公司</span>
            <input class="form-control" type="text" data-min="10.00" data-max="10.00" id="gongsi">
        </div>
        </div>
    </div>
   <!--比例范围-->
   <div class="form-group status form-width yc" style="display:none">
       <label class="col-sm-5 control-label bljs noyes" >比例范围7.00到7.00</label>
       <label class="col-sm-3 control-label bljss yes_1" style="display:none"></label>
       <div class="gjjin">
       <label class="col-sm-3 control-label bljss yes_2" style="display:none"></label>
          <label class="checkbox-inline checkbox noyes" >
    		比例范围7.00到7.00
         </label>
         
       </div>
   </div>
    <!--补充公积金基数-->
    <!--<div class="form-group form-width yc" style="display:none" >
       <label class="col-sm-4 control-label noweight">补充公积金基数</label>
         <div class="col-sm-8">
           <div class="input-group col-sm-12">
             <input class="form-control" type="text">
           </div>
        </div>
    </div>-->
   <!--技术范围-->
   <!-- <div class="form-group status form-width yc" style="display:none">
       <label class="col-sm-5 control-label bcjs">基数范围0.00到99999.00</label>
   </div> -->
    <!--缴纳比例-->
   <!--<div class="form-group form-width yc" style="display:none">
       <label class="col-sm-4 control-label noweight">缴纳比例</label>
       	<div class="col-sm-8">
         <div class="input-group col-sm-6 pull-left">
         	<span class="input-group-addon noback">个人</span>
            <input class="form-control" type="text" >
        </div>
        <div class="input-group col-sm-6 pull-right">
         	<span class="input-group-addon noback">公司</span>
            <input class="form-control" type="text" >
        </div>
        </div>
    </div>-->
   <!--比例范围-->
   <!--<div class="form-group status form-width yc" style="display:none">
       <label class="col-sm-5 control-label bljs">比例范围5.00到5.00</label>
       <div class="gjjin">
          <label class="checkbox-inline checkbox">
    		比例范围5.00到5.000
         </label>
       </div>
   </div>-->
   <!--计算/重置-->
   		<center style="padding-bottom:22px;">
        	<button class="btn btn-warning ec" type="button">计算</button>
            <button class="btn btn-default cz" type="button">重置</button>
        </center>
       </form>
    </div>
    <!--left-->   
    <div class="right">
    	<table class="calculator_result_table" border="1" style="height: 200px; margin-bottom:20px;">
            <thead class="thead">
                <tr>
                <td rowspan="2">缴纳项目</td>
                <td rowspan="2">社保基数</td>
                <td colspan="2">个人缴纳</td>
                <td colspan="2">企业缴纳</td>
                </tr>
                <tr>
                <td class="pading_r18">缴纳比例</td>
                <td class="pading_r18">缴纳金额(元)</td>
                <td class="pading_r18">缴纳比例</td>
                <td class="pading_r18">缴纳金额(元)</td>
                </tr>
            </thead>
            <tbody style="height:134px;" class="nullTip">
                <tr >
                   <td align="center" colspan="6">输入数据后才能生成明细哦~</td>
                </tr>
            </tbody>
            <tfoot class="tbody" style="display:none">
            <tr>
                <td align="right" colspan="6">
                总缴纳：
                <span class="total">1743.59</span>
                </td>
            </tr>
            </tfoot>
                <tbody class="tbody" style="display:none">
                    <tr>
                        <td>养老保险</td>
                        <td>2585.00</td>
                        <td class="pading_r18">8.00 %</td>
                        <td class="pading_r18">206.80</td>
                        <td class="pading_r18">20.00 %</td>
                        <td class="pading_r18">517.00</td>
                    </tr>
            		<tr>
                        <td>医疗保险</td>
                        <td>3878.00</td>
                        <td class="pading_r18">2.00 %+3.00 元</td>
                        <td class="pading_r18">80.56</td>
                        <td class="pading_r18">10.00 %</td>
                        <td class="pading_r18">387.80</td>
                    </tr>
                    <tr>
                        <td>失业保险</td>
                        <td>2585.00</td>
                        <td class="pading_r18">0.20 %</td>
                        <td class="pading_r18">5.17</td>
                        <td class="pading_r18">1.00 %</td>
                        <td class="pading_r18">25.85</td>
                    </tr>
                    <tr>
                        <td>工伤保险</td>
                        <td>2585.00</td>
                        <td class="pading_r18">0.20 %</td>
                        <td class="pading_r18">5.17</td>
                        <td class="pading_r18">1.00 %</td>
                        <td class="pading_r18">25.85</td>
                    </tr>
                    <tr>
                        <td>生育保险</td>
                        <td>2585.00</td>
                        <td class="pading_r18">0.20 %</td>
                        <td class="pading_r18">5.17</td>
                        <td class="pading_r18">1.00 %</td>
                        <td class="pading_r18">25.85</td>
                    </tr>
                    <tr>
                        <td>大病医疗</td>
                        <td>2585.00</td>
                        <td class="pading_r18">0.20 %</td>
                        <td class="pading_r18">5.17</td>
                        <td class="pading_r18">1.00 %</td>
                        <td class="pading_r18">25.85</td>
                    </tr>
                    <tr>
                        <td>公积金</td>
                        <td>2585.00</td>
                        <td class="pading_r18">0.20 %</td>
                        <td class="pading_r18">5.17</td>
                        <td class="pading_r18">1.00 %</td>
                        <td class="pading_r18">25.85</td>
                    </tr>
                    <tr>
                        <td>其他收费</td>
                        <td>2585.00</td>
                        <td class="pading_r18">0.20 %</td>
                        <td class="pading_r18">5.17</td>
                        <td class="pading_r18">1.00 %</td>
                        <td class="pading_r18">25.85</td>
                    </tr>
                    <tr>
                        <td>其他收费2</td>
                        <td>2585.00</td>
                        <td class="pading_r18">0.20 %</td>
                        <td class="pading_r18">5.17</td>
                        <td class="pading_r18">1.00 %</td>
                        <td class="pading_r18">25.85</td>
                    </tr>
                    <tr class="total_tr">
                        <td></td>
                        <td></td>
                        <td>个人共缴纳：</td>
                        <td class="total-personal">527.53</td>
                        <td>企业共缴纳：</td>
                        <td class="total-company">1216.06</td>
                    </tr>
            </tbody>
            </table>
			</div>
            </div>
            </div>
            </div>
    		</div>
   

    
    
    
    <!-- 放页脚  开始-->
<jsp:include flush="true" page="../comment/foot.div.jsp"></jsp:include>
	<!-- 放页脚  结束-->
</div>
<!-- 放主要内容  结束-->

<!-- Vendor scripts -->
	<script src="../bootstrap/vendor/jquery/dist/jquery.min.js"></script>
	<script src="../bootstrap/vendor/jquery-ui/jquery-ui.min.js"></script>
	<script src="../bootstrap/vendor/slimScroll/jquery.slimscroll.min.js"></script>
	<script src="../bootstrap/vendor/bootstrap/dist/js/bootstrap.min.js"></script>
	<script src="../bootstrap/vendor/jquery-flot/jquery.flot.js"></script>
	<script src="../bootstrap/vendor/jquery-flot/jquery.flot.resize.js"></script>
	<script src="../bootstrap/vendor/jquery-flot/jquery.flot.pie.js"></script>
	<script src="../bootstrap/vendor/flot.curvedlines/curvedLines.js"></script>
	<script src="../bootstrap/vendor/jquery.flot.spline/index.js"></script>
	<script src="../bootstrap/vendor/metisMenu/dist/metisMenu.min.js"></script>
	<script src="../bootstrap/vendor/iCheck/icheck.min.js"></script>
	<script src="../bootstrap/vendor/peity/jquery.peity.min.js"></script>
	<script src="../bootstrap/vendor/sparkline/index.js"></script>
	<script src="../bootstrap/vendor/xeditable/bootstrap3-editable/js/bootstrap-editable.min.js"></script>
    <script src="../bootstrap/vendor/select2-3.5.2/select2.min.js"></script>
    <script src="../bootstrap/vendor/bootstrap-touchspin/dist/jquery.bootstrap-touchspin.min.js"></script>
    <script src="../bootstrap/vendor/bootstrap-datepicker-master/dist/js/bootstrap-datepicker.min.js"></script>
	<!-- for editable -->
	<script src="../bootstrap/vendor/moment/moment.js"></script>
	 
	<!-- for datatable -->
	<script src="../bootstrap/vendor/datatables/media/js/jquery.dataTables.min.js"></script>
	<script src="../bootstrap/vendor/datatables_plugins/integration/bootstrap/3/dataTables.bootstrap.min.js"></script>
	<!-- alert -->
	<script src="../bootstrap/vendor/sweetalert/lib/sweet-alert.min.js"></script>

<!-- App scripts -->
    <script src="../bootstrap/scripts/homer.js"></script>
    <script src="../js/style.js"></script>
    <script>

    $(function () {

        /*
          Flot charts data and options
         */
        var data1 = [ [0, 55], [1, 48], [2, 40], [3, 36], [4, 40], [5, 60], [6, 50], [7, 51] ];
        var data2 = [ [0, 56], [1, 49], [2, 41], [3, 38], [4, 46], [5, 67], [6, 57], [7, 59] ];

        var chartUsersOptions = {
            series: {
                splines: {
                    show: true,
                    tension: 0.4,
                    lineWidth: 1,
                    fill: 0.4
                },
            },
            grid: {
                tickColor: "#f0f0f0",
                borderWidth: 1,
                borderColor: 'f0f0f0',
                color: '#6a6c6f'
            },
            colors: [ "#62cb31", "#efefef"],
        };

        $.plot($("#flot-line-chart"), [data1, data2], chartUsersOptions);

        /*
          Flot charts 2 data and options
         */
        var chartIncomeData = [
            {
                label: "line",
                data: [ [1, 10], [2, 26], [3, 16], [4, 36], [5, 32], [6, 51] ]
            }
        ];

        var chartIncomeOptions = {
            series: {
                lines: {
                    show: true,
                    lineWidth: 0,
                    fill: true,
                    fillColor: "#64cc34"

                }
            },
            colors: ["#62cb31"],
            grid: {
                show: false
            },
            legend: {
                show: false
            }
        };

        $.plot($("#flot-income-chart"), chartIncomeData, chartIncomeOptions);



    });

</script>
<script>
    (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
        (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
            m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
    })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

    ga('create', 'UA-4625583-2', 'webapplayers.com');
    ga('send', 'pageview');
		

</script>
	
</body>
</html>