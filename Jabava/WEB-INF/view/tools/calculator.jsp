<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="ctxStatic">${ctx}/static</c:set>

<!DOCTYPE html>
<html>
<head>
    <base href="${pageContext.request.contextPath}/">
    <meta charset="utf-8">
    
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- Page title -->
    <title>社保计算器</title>

    <!-- Place favicon.ico and apple-touch-icon.png in the root directory -->
    <!--<link rel="shortcut icon" type="image/ico" href="favicon.ico" />-->

    <!-- Vendor styles -->
    <jsp:include flush="true" page="../common/styles.jsp"></jsp:include>
   <!-- <link rel="stylesheet" href="css/user.css">-->
    <link rel="stylesheet" href="static/css/styles.css">

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

<!-- 请在这放主要内容 ，比如：导航条,搜索块，列表等 -->
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
    	<div class="row">
                <div class="col-lg-12">
                    <div class="hpanel">
                    <div class="panel-heading">
                    社保计算器
                    </div>
                    <!--右边-->
            <div class="panel-body">
                  <!--结束-->
           <div class="lr">
           <div class="col-lg-offset-6 col-lg-6 m-b-sm nosg text-center">
           	社保与公积金缴费明细
           </div>
          <div class="col-lg-6">
     <form role="form" class="form-horizontal left" id="myForm">
     <!--选择城市-->
     <div class="form-group form-width">
       <label class="col-sm-4 control-label noweight">选择城市</label>
         <div class="col-sm-8">
           <div class="input-group">
             <input class="form-control" type="text" id="get" placeholder="请选择" readonly style="background-color: white;cursor:default">
               <div class="input-group-btn">
                <button class="btn btn-default dropdown-toggle drop-pro" type="button" data-toggle="dropdown" id="calculatorHandle">
                  <span class="caret"></span>
                </button>

    <!--省份/城市-->
      <div class="star dropdown-menu dropdown-menu-right" style="display:none">
        <div class="span-nav">
              <span class="bor province" id="pro1">省份</span><span class="city" id="city">城市</span>
            </div>
            <!--省份-->
             <div class="select_city_con_wrap procity" >
                <div class="select_city_con actived prty" type="p" id="provinceHandle">
                  <!-- province list -->
                  </div>
            </div>
            <!--城市-->
            <div class="select_city_con_wrap sltcity" style="display:none; min-height:40px;">
                <div class="select_city_con actived stly" type="p"  id="cityHandle">
                   <!-- city list -->
                  </div>
            </div>
      </div>
    <!--省份/城市 end-->                 
        	   </div>


           </div>
        </div>
       
    </div>

    
    <!--户籍性质-->
    <div class="form-group form-width">
       <label class="col-sm-4 control-label noweight">参保类型</label>
         <div class="col-sm-8">
           <div class="input-group">
             <input id="get1" class="form-control" type="text" placeholder="请选择" readonly style="background-color: white;cursor:default" >
               <div class="input-group-btn" >
                <button class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown" id="policylist">
                  <span class="caret" ></span>
                </button>
                  <ul class="dropdown-menu pull-right sty widh" id="test">
                   <!--  <li><a href="#">本地城镇</a></li>
					<li><a href="#">本地农村</a></li>
                    <li><a href="#">外地城镇</a></li>
                    <li><a href="#">外地农村</a></li>
                    <li><a href="#">外地城镇</a></li>
                    <li><a href="#">外地农村</a></li> -->
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
             <input class="form-control" type="text"   id="jishu" name="jishutest">
           </div>
        </div>
    </div>
    
    <!--基数范围/是否缴纳-->
    <div class="form-group status form-width yc" style="display:none">
       <label class="col-sm-5 control-label js noyes jss" id="shebaojishu"><!-- 基数范围3271到16353 --> </label>
        <div class="gjjin">
          <label class="checkbox-inline ">
            <input id="paymentStatu" type="checkbox" value="option1" style=" margin-top:1px;" checked="checked">
    			是否缴纳公积金
         </label>
       </div>
   </div>
    <!--公积金基数-->
    <div class="form-group form-width yc handlebox" style="display:none">
       <label class="col-sm-4 control-label noweight">公积金基数</label>
         <div class="col-sm-8">
           <div class="input-group col-sm-12">
             <input class="form-control " type="text"  id="gongjijin">
           </div>
        </div>
    </div>
   <!--技术范围-->
    <div class="form-group status form-width yc handlebox"  style="display:none">
       <label class="col-sm-5 control-label js noyes" id="gongjijinjishu"><!-- 基数范围3271.00到16353.00 --></label>
       <label class="col-sm-5 control-label js yes" style="display:none"></label>
   </div> 
   <!--缴纳比例-->
   <div class="form-group form-width yc handlebox" style="display:none">
       <label class="col-sm-4 control-label noweight">缴纳比例</label>
       	<div class="col-sm-8">
         <div class="input-group col-sm-6 pull-left">
         	<span class="input-group-addon noback">个人</span>
            <input class="form-control" type="text"   id="geren" readonly style="background-color: white;cursor:default">
        </div>
        <div class="input-group col-sm-6 pull-right">
         	<span class="input-group-addon noback">公司</span>
            <input class="form-control" type="text"  id="gongsi" readonly style="background-color: white;cursor:default">
        </div>
        </div>
    </div>
   <!--比例范围-->
   <div class="form-group status form-width yc handlebox" style="display:none">
       <label class="col-sm-5 control-label bljs noyes" id="gIndRatio"> </label>
       <label class="col-sm-3 control-label bljss yes_1" style="display:none"></label>
       <div class="gjjin">
       <label class="col-sm-3 control-label bljss yes_2" style="display:none"></label>
          <label class="checkbox-inline checkbox noyes" id="gComRatio"> </label>
       </div>
   </div>
   <!--计算/重置-->
   		<center style="padding-bottom:22px;">
        	<button class="btn btn-warning ec" type="button">计算</button>
            <button class="btn btn-default cz" type="button">重置</button>
        </center>
       </form>
    </div>
    <!--left-->   
    <div class="col-lg-6">
       <%-- <center>  
          <span class="nosg">社保与公积金缴费明细</span>
        </center> --%>
    	<table class="table calculator_result_table" border="1" style="height: 200px; margin-bottom:20px;">
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
                <span class="total" id="totalMoney">0.00</span>
                </td>
            </tr>
            </tfoot>  
                  <tbody class="tbody" style="display:none">
            </tbody>  
            </table>
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
<!-- 放主要内容  结束-->

<!-- Vendor scripts -->
 
	<script src="${ctxStatic }/bootstrap/vendor/jquery/dist/jquery.min.js"></script>
	<script src="${ctxStatic }/bootstrap/vendor/jquery-ui/jquery-ui.min.js"></script>
	<script src="${ctxStatic }/bootstrap/vendor/slimScroll/jquery.slimscroll.min.js"></script>
	<script src="${ctxStatic }/bootstrap/vendor/bootstrap/dist/js/bootstrap.min.js"></script>
	<script src="${ctxStatic }/bootstrap/vendor/jquery-flot/jquery.flot.js"></script>
	<script src="${ctxStatic }/bootstrap/vendor/jquery-flot/jquery.flot.resize.js"></script>
	<script src="${ctxStatic }/bootstrap/vendor/jquery-flot/jquery.flot.pie.js"></script>
	<script src="${ctxStatic }/bootstrap/vendor/flot.curvedlines/curvedLines.js"></script>
	<script src="${ctxStatic }/bootstrap/vendor/jquery.flot.spline/index.js"></script>
	<script src="${ctxStatic }/bootstrap/vendor/metisMenu/dist/metisMenu.min.js"></script>
	<script src="${ctxStatic }/bootstrap/vendor/iCheck/icheck.min.js"></script>
	<script src="${ctxStatic }/bootstrap/vendor/peity/jquery.peity.min.js"></script>
	<script src="${ctxStatic }/bootstrap/vendor/sparkline/index.js"></script>
	<script src="${ctxStatic }/bootstrap/vendor/xeditable/bootstrap3-editable/js/bootstrap-editable.min.js"></script>
    <script src="${ctxStatic }/bootstrap/vendor/select2-3.5.2/select2.min.js"></script>
    <script src="${ctxStatic }/bootstrap/vendor/bootstrap-touchspin/dist/jquery.bootstrap-touchspin.min.js"></script>
    <script src="${ctxStatic }/bootstrap/vendor/bootstrap-datepicker-master/dist/js/bootstrap-datepicker.min.js"></script>
	<!-- for editable -->
	<script src="${ctxStatic }/bootstrap/vendor/moment/moment.js"></script>
	 
	<!-- for datatable -->
	<script src="${ctxStatic }/bootstrap/vendor/datatables/media/js/jquery.dataTables.min.js"></script>
	<script src="${ctxStatic }/bootstrap/vendor/datatables_plugins/integration/bootstrap/3/dataTables.bootstrap.min.js"></script>
	<!-- alert -->
	<script src="${ctxStatic }/bootstrap/vendor/sweetalert/lib/sweet-alert.min.js"></script>

<!-- App scripts -->
    <script src="${ctxStatic }/bootstrap/scripts/homer.js"></script>
    <script src="${ctxStatic }/js/style.js"></script>
    
    <script>
	var ctx="${ctx}";//相对路径
	var resultDataArray=null;//政策包组合数组
	var isTrue=false;
    $(function () {
    	 getProvinceAndCity();//获取省份和城市
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
    
    
    
    //---------------------------------myself-------------------------------------
    //获取省份和城市
    function getProvinceAndCity(){
    	console.log("province and city");
    	var provinceList="";
    	
        $.ajax({
        	type:"post",
        	url:"${ctx}/tools/getProvinceInfo",
        	dataType:"json",
        	async:false,
        	success:function(data){
        		if(data&&data.reg!=undefined){
        			
        			data.reg.forEach(function(item,index,array){
        				
        				provinceList+="<dl><dt>"+item.key+"</dt><dd class=\"dw\">";
        				
        				item.value.forEach(function(item,index,array){
        					
        					provinceList+= '<a href="javascript:;" data-pid=\"'+item.PROVINCE_ID+'\" title=\"'+item.PROVINCE_NAME+'\">'+item.PROVINCE_NAME+'</a>' ;
        					
        				});
        				
        				provinceList+="</dd></dl>" ;
        				
        			});
        			
        			$("#provinceHandle").append(provinceList);
        			
        		} 
        		 
        	}
 
        });
    	
    	
    }
    
    //根据省份获取城市信息
    function getCityInfo(provinceId){
    	 console.log("ctx="+ctx);
		 var cityList="";
		 $("#cityHandle").html("");
		 $.ajax({
			 type:"post",
			 url:ctx+"/tools/getCityInfo",
			 data:{"provinceId":provinceId},
			 dataType:"json",
			 async:false,
			 success:function(data){
				 if(data&&data.reg!=undefined)
		        			
        			data.reg.forEach(function(item,index,array){
        				
        				 
        				cityList+="<dl><dt>"+item.key+"</dt><dd class=\"dw\">";
        				
        				item.value.forEach(function(item,index,array){
        					
        					cityList+= '<a href="javascript:;" data-cid=\"'+item.CITY_ID+'\" title=\"'+item.CITY_NAME+'\">'+item.CITY_NAME+'</a>' ;
        					
        				});
        				
        				cityList+="</dd></dl>" ;
        				
        			});
        			
        			$("#cityHandle").append(cityList);
		        			
			     }
			 
		 });
    	
    	
    }
    
    //根据城市id获取政策包信息
    function getPolicyInfo(cityId){
    	var dataObject=null;
    	$.ajax({
    		type:"post",
    		url:ctx+"/tools/getPolicyInfo",
    		data:{"cityId":cityId},
    		dataType:"json",
    		async:false,
    		success:function(data){
    			 
				if(data&&data.resultCode==0&&data.resultData!=""){
    				
    				dataObject= data;
    				resultDataArray=data.resultData;
    				
    			}else{
    				resultDataArray=null;
    			}
    			
    		}
    		
    		
    	});
    	return dataObject;
    }
    
    //社保计算
    function policyCalculator(resultDataArray){
    	var cbType=$("#get1").val();//参保类型
    	var shebaoInputBase=$("#jishu").val();//社保基数
    	var gongjijinInputBase=$("#gongjijin").val();//公积金基数
    	var indSumMoney=0;//个人共缴纳
    	var comSumMoney=0;//企业共缴纳
    	var sumMoney=0;   //总缴纳
    	var gjjObj=null;  //公积金object
    	var dataBody="";
    	//var tbody='<tbody class="tbody" style="display:none">';
    	
    	resultDataArray.forEach(function(item,index,array){
    		//根据参保类型计算
    		if(item.groupName==cbType){
    			 
    			item.sbGroupDetail.forEach(function(item,index,array){
					var prodName=item.prodName;//缴纳项目
    				
					
					if(prodName.indexOf("公积金")==-1){
						//var prodName=item.prodName;//社保基数
	    				
						var indRatio=item.indRatio; //缴纳比例	个人
	    				
							
						var indMoney=parseFloat(shebaoInputBase)*parseFloat(indRatio);//缴纳金额(元)	个人
						
	    				
						var comRatio=item.comRatio;//缴纳比例	公司
	    				
							
						var comMoney=parseFloat(shebaoInputBase)*parseFloat(comRatio);//缴纳金额(元  公司
						
	    				
						dataBody+=" <tr><td>"+prodName+"</td><td>"+shebaoInputBase+"</td><td>"+(indRatio==0?"\\":indRatio)+"</td><td>"+(indMoney==0?"\\":indMoney.toFixed(1))+"</td><td>"+(comRatio==0?"\\":comRatio)+"</td><td>"+(comMoney==0?"\\":comMoney.toFixed(2))+"</td></tr>";
    					
						indSumMoney+=indMoney;
						
						comSumMoney+=comMoney;
    					
    				}
					//公积金处理
    				if(prodName.indexOf("公积金")!=-1){
    					
    					gjjObj=item;
    					
    				}
    				
    			});
    		}
		 
			
			
		});
    	
      		
       //公积金
       if( $("#paymentStatu").prop("checked")){
    	   if(gjjObj){
   	       	//dataBody+=" <tr><td>"+gjjObj.prodName+"</td><td>"+ gongjijinInputBase +"</td><td>"+(gjjObj.indRatio==0?"\\":gjjObj.indRatio)+"</td><td>"+parseFloat(gongjijinInputBase)*parseFloat(gjjObj.indRatio)+"</td><td>"+gjjObj.comRatio+"</td><td>"+parseFloat(gongjijinInputBase)*parseFloat(gjjObj.comRatio)+"</td></tr>";
   	    	dataBody+=" <tr><td>"+gjjObj.prodName+"</td><td>"+ gongjijinInputBase +"</td><td>"+(gjjObj.indRatio==0?"\\":gjjObj.indRatio)+"</td><td>"+((parseFloat(gongjijinInputBase)*parseFloat(gjjObj.indRatio))==0?"\\":(parseFloat(gongjijinInputBase)*parseFloat(gjjObj.indRatio))).toFixed(1)+"</td><td>"+(gjjObj.comRatio==0?"\\":gjjObj.comRatio)+"</td><td>"+((parseFloat(gongjijinInputBase)*parseFloat(gjjObj.comRatio))==0?"\\":(parseFloat(gongjijinInputBase)*parseFloat(gjjObj.comRatio))).toFixed(2)+"</td></tr>";
   	    	indSumMoney+=parseFloat(gongjijinInputBase)*parseFloat(gjjObj.indRatio);
   	    	comSumMoney+=parseFloat(gongjijinInputBase)*parseFloat(gjjObj.comRatio);
   	       }
       }
	       
        //个人总缴纳&企业总缴纳
    	dataBody+=" <tr class=\"total_tr\"><td  colspan='2'></td><td>个人共缴纳：</td><td class=\"total-personal\">"+indSumMoney.toFixed(1)+"</td><td >企业共缴纳：</td><td class=\"total-personal\">"+comSumMoney.toFixed(2)+"</td></tr>";		
    			
    			
    	//tbody+="</tbody>";
    	$("tbody.tbody").html("");
    	$("tbody.tbody").append(dataBody);
    	$("#totalMoney").html((indSumMoney+comSumMoney).toFixed(2));//总金额
    	
    }
  
    //显示省份和城市信息面板
    function porovinceAndCityPanel(){
    	 $("#pro1").addClass("bor");
	     $("#city").removeClass("bor");
	     $(".procity").show();
	     $(".sltcity").hide();
		    $(".star").show();
	     $("#provinceHandle a").removeClass("current");
	     $("#cityHandle a").removeClass("current");
        $(window).on('click', function(event) {
          // event.preventDefault();
          $('.star').hide()
        });    	
    }

</script>	
</body>
</html>