<%@ page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html>
<head>
	<base href="${pageContext.request.contextPath}/">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <!-- Page title -->
    <title>员工个人信息</title>
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
                        <span>员工个人信息</span>
                    </li>
                    <li class="active">
                        <span></span>
                    </li>
                </ol>
            </div>
            <h2 class="font-light m-b-xs">
                员工个人信息
            </h2>
            <small>待定</small>
        </div>
    </div>
</div>

<!-- 放主要内容 -->
		<div class="w w1 header clearfix">
         <div style=" position:relative; width:990px; height:70px; overflow:hidden;">
             <div id="#sflex03" class="stepflex">
             	<dl class="first done">
                <dt class="s-num"><a href="#" class="order"><i class=" pe-7s-browser"></i></a></dt>
                <dd class="s-text">
                    <a href="#" class="order">订单</a>
                <s></s>
                <b></b>
                </dd>
                </dl>
                <dl class="normal">
                <dt class="s-num"><a href="#" class="bill"><i class="pe-7s-ticket black"></i></a></dt>
                <dd class="s-text">
                    <a href="#" class="bill">账单</a>
                <s></s>
                <b></b>
                </dd>
                </dl>
                <dl class="normal">
                <dt class="s-num"><a href="#" class="personal"><i class=" pe-7s-user black"></i></a></dt>
                <dd class="s-text">
                    <a href="#" class="personal">个人信息</a>
                <s></s>
                <b></b>
                </dd>
                </dl>
            <dl class="normal ">
            <dt class="s-num"><a href="#" class="essential"><i class="pe-7s-pin"></i></a></dt>
            <dd class="s-text">
            <a href="#" class="essential">基本信息</a>
            <s></s>
            <b></b>
            </dd>
            </dl>
            <dl class="normal ">
            <dt class="s-num"><a href="#" class="position"><i class="pe-7s-id"></i></a></dt>
            <dd class="s-text">
            <a href="#" class="position">岗位信息</a>
            <s></s>
            <b></b>
            </dd>
            </dl>
            <dl class="normal ">
            <dt class="s-num"><a href="#" class="transfer"><i class="pe-7s-way"></i></a></dt>
            <dd class="s-text">
            <a href="#" class="transfer">岗位调动</a>
            <s></s>
            <b></b>
            </dd>
            </dl>
            <dl class="normal ">
            <dt class="s-num"><a href="#" class="educational"><i class="pe-7s-study"></i></a></dt>
            <dd class="s-text">
            <a href="#" class="educational">教育背景</a>
            <s></s>
            <b></b>
            </dd>
            </dl>
            
             <dl class="normal">
            <dt class="s-num"><a href="#" class="hands"><i class="pe-7s-portfolio"></i></a></dt>
            <dd class="s-text">
            <a href="#" class="hands">工作经验</a>
            <s></s>
            <b></b>
            </dd>
            </dl>
            <dl class="normal ">
            <dt class="s-num"><a href="#" class="project"><i class="pe-7s-network"></i></a></dt>
            <dd class="s-text">
            <a href="#" class="project">项目经验</a>
            <s></s>
            <b></b>
            </dd>
            </dl>
            <dl class="normal ">
            <dt class="s-num"><a href="#" class="obtain"><i class="pe-7s-cash"></i></a></dt>
            <dd class="s-text">
            <a href="#" class="obtain">获得证书</a>
            <s></s>
            <b></b>
            </dd>
            </dl>
            <dl class="normal " >
            <dt class="s-num"><a href="#"  class="training"><i class="pe-7s-notebook"></i></a></dt>
            <dd class="s-text">
            <a href="#" class="training">培训经历</a>
            <s></s>
            <b></b>
            </dd>
            </dl>
            <dl class="normal " >
            <dt class="s-num"><a href="#" class="member"><i class="pe-7s-users"></i></a></dt>
            <dd class="s-text">
            <a href="#" class="member">家庭成员</a>
            <s></s>
            <b></b>
            </dd>
            </dl>
           <dl class="normal " >
            <dt class="s-num"><a href="#" class="contract"><i class="pe-7s-news-paper"></i></a></dt>
            <dd class="s-text">
            <a href="#" class="contract">劳动合同</a>
            <s></s>
            <b></b>
            </dd>
            </dl> 
            <dl class="normal " >
            <dt class="s-num"><a href="#" class="trial"><i class="pe-7s-note2"></i></a></dt>
            <dd class="s-text">
            <a href="#" class="trial">试用情况</a>
            <s></s>
            <b></b>
            </dd>
            </dl>
            <dl class="normal ">
            <dt class="s-num"><a href="#" class="performance"><i class="pe-7s-graph3"></i></a></dt>
            <dd class="s-text">
            <a href="#" class="performance">绩效考核</a>
            <s></s>
            <b></b>
            </dd>
            </dl>
            <dl class="normal ">
            <dt class="s-num"><a href="#" class="reward"><i class="pe-7s-medal"></i></a></dt>
            <dd class="s-text">
            <a href="#" class="reward">奖惩记录</a>
            <s></s>
            <b></b>
            </dd>
            </dl>
            <dl class="normal ">
            <dt class="s-num"><a href="#" class="attendance"><i class="pe-7s-wristwatch"></i></a></dt>
            <dd class="s-text">
            <a href="#" class="attendance">考勤记录</a>
            <s></s>
            <b></b>
            </dd>
            </dl>
            <dl class="normal ">
            <dt class="s-num"><a href="#" class="turnover"><i class="pe-7s-delete-user"></i></a></dt>
            <dd class="s-text">
            <a href="#" class="turnover">离职管理</a>
            <s></s>
            <b></b>
            </dd>
            </dl>
          </div>
         </div>
          <a data-slide="prev" href="javascript:;" class="left carousel-control" id="lefts">‹</a>
          <a data-slide="next" href="javascript:;" class="right carousel-control" id="rights">›</a>
        </div>
        
                      <!--订单-->
                    	 <!-- 开始 -->
              <div class="row ding_dan" style="display:none ">
                <div class="content animate-panel">
               <div class="row">
            	<div class="order_infor_head clearfix">
                	<button class="btn btn-info btn-xs dingdan_one" type="button">
                    	<i class=""></i> <span class="bold">订单信息</span>
                    </button>
                    &nbsp;
                    <button class="btn information_button btn-xs dingdan_two" type="button">
                    	<i class=""></i> <span class="bold">订单费用信息</span>
                    </button>
                    <!--<a class="right_ruturn" href="#"><button class="btn btn-success btn-xs"><i class=""></i> 返 回</button></a>--><!--连接到订单-->
                </div>
                <!--订单信息开始-->
                <div class="order_infor">
                <!-- 订单信息头部开始 -->
                        <div class="well well-lg well_bg" >
                            <!--<h5 class="font-bold"><i class="fa pe-7s-search"></i> 查询</h5>-->
                        	<div class="row">
                                  <form role="form" class="search-form"> 
                                  <div class="clearfix">
                                  
                                     <div class="form-group search-unit">
                                       <label for="exampleInputName2" class="col-lg-4 rost_sousuo">员工姓名：</label>
                                        <div class="col-lg-8">
                                        <input type="text" class="form-control" id="exampleInputName2" value="张三">
                                        </div>
                                    </div>
                                    <div class="form-group search-unit">
                                        <label class="col-lg-4 rost_sousuo">证件类型：</label>
                                        <div class="col-lg-8">
                                        <select class="form-control " name="account" placeholder="身份证">
                                            <option>身份证</option>
                                            <option>军人证</option>
                                            <option>港澳身份证</option>
                                            <option>台胞证</option>
                                            <option>护照</option>
                                            <option>其它</option>
                                        </select>
                                        </div>
                                    </div> 
                                    <div class="form-group search-unit">
                                      <label for="exampleInputName3" class="col-lg-4 rost_sousuo">证件号码：</label>
                                        <div class="col-lg-8">
                                       	  <input type="text" class="form-control" id="exampleInputName3" value="321623198802283426">
                                        </div>
                                    </div>
                                    <div class="form-group search-unit">
                                        <label for="exampleInputName4" class="col-lg-4 rost_sousuo">员工工作地：</label>
                                        <div class="col-lg-8">
                                        	<input type="text" class="form-control" id="exampleInputName4" value="安吉">
                                        </div>
                                    </div>
                                    <div class="form-group search-unit">
                                      <label for="exampleInputName5" class="col-lg-4 rost_sousuo">手机：</label>
                                        <div class="col-lg-8">
                                       	  <input type="text" class="form-control" id="exampleInputName5" value="13800598364">
                                        </div>
                                    </div>
                                    <div class="form-group search-unit">
                                        <label for="exampleInputName6" class="col-lg-4 rost_sousuo">电话：</label>
                                        <div class="col-lg-8">
                                        	<input type="text" class="form-control" id="exampleInputName6" value="13800598364">
                                        </div>
                                    </div>
                                    <div class="form-group search-unit">
                                        <label for="exampleInputName7" class="col-lg-4 rost_sousuo">员工编号：</label>
                                        <div class="col-lg-8">
                                        	<input type="text" class="form-control" id="exampleInputName7" value="13800598364">
                                        </div>
                                    </div>  
                                    <div class="form-group search-unit">
                                        <label class="col-lg-4 rost_sousuo">员工雇佣状态：</label>
                                        <div class="col-lg-8">
                                        <select class="form-control " name="account" placeholder="入职中">
                                            <option>入职中</option>
                                            <option>在职</option>
                                            <option>离职中</option>
                                            <option>离职</option>
                                        </select>
                                        </div>
                                    </div> 
                                    <div class="form-group search-unit">
                                        <label class="col-lg-4 rost_sousuo">入职日期：</label>
                                        <div class="input-group date col-lg-8 rost_time">
                                             <input type="text" class="form-control">
                                             <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                        </div>
                                    </div>
                                    <div class="form-group search-unit">
                                        <label for="exampleInputName8" class="col-lg-4 rost_sousuo">入职备注：</label>
                                        <div class="col-lg-8">
                                       	  <input type="text" class="form-control" id="exampleInputName8" value="">
                                        </div>
                                    </div>
                                    <div class="form-group search-unit">
                                        <label class="col-lg-4 rost_sousuo">离职日期：</label>
                                        <div class="input-group date col-lg-8 rost_time">
                                             <input type="text" class="form-control">
                                             <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                        </div>
                                    </div>
                                    <div class="form-group search-unit">
                                        <label for="exampleInputName9" class="col-lg-4 rost_sousuo">离职原因：</label>
                                        <div class="col-lg-8">
                                       	  <input type="text" class="form-control" id="exampleInputName9" value="">
                                        </div>
                                    </div>
                                    <div class="form-group search-unit">
                                        <label for="exampleInputName9" class="col-lg-4 rost_sousuo">离职备注：</label>
                                      <div class="col-lg-8">
                                        	<input type="text" class="form-control" id="exampleInputName9" value="">
                                        </div>
                                    </div>
                                    <div class="form-group search-unit">
                                        <label for="exampleInputName10" class="col-lg-4 rost_sousuo">户籍性质：</label>
                                        <div class="col-lg-8">
                                       	  <input type="text" class="form-control" id="exampleInputName10" value="外地城镇">
                                        </div>
                                    </div>
                                    <div class="form-group search-unit">
                                      <label for="exampleInputName11" class="col-lg-4 rost_sousuo">家财险地址：</label>
                                        <div class="col-lg-8">
                                       	  <input type="text" class="form-control" id="exampleInputName11" value="">
                                        </div>
                                    </div>
                                    <div class="form-group search-unit">
                                        <label class="col-lg-4 rost_sousuo">家财险起始日期：</label>
                                        <div class="input-group date col-lg-8 rost_time">
                                             <input type="text" class="form-control">
                                             <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                        </div>
                                    </div>
                                    <div class="form-group search-unit">
                                        <label class="col-lg-4 rost_sousuo">订单创建日期：</label>
                                        <div class="input-group date col-lg-8 rost_time">
                                             <input type="text" class="form-control">
                                             <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                        </div>
                                    </div>
                                    <div class="form-group search-unit">
                                        <label for="exampleInputName12" class="col-lg-4 rost_sousuo">订单创建人：</label>
                                        <div class="col-lg-8">
                                        	<input type="text" class="form-control" id="exampleInputName12" value="">
                                        </div>
                                    </div>
                                    <div class="form-group search-unit">
                                        <label class="col-lg-4 rost_sousuo">订单状态：</label>
                                        <div class="col-lg-8">
                                        <select class="form-control " name="account" placeholder="员工未提交">
                                            <option>员工未提交</option>
                                            <option>减员已确认</option>
                                            <option>减员后变更已提交</option>
                                            <option>撤单已确认</option>
                                            <option>增员已提交</option>
                                            <option>增员已完善</option>
                                            <option>撤单已确认</option>
                                            <option>变更未提交</option>
                                            <option>变更已提交</option>
                                            <option>变更已确认</option>
                                            <option>减员未提交</option>
                                            <option>减员已提交</option>
                                        </select>
                                        </div>
                                    </div> 
                                    <div class="form-group search-unit">
                                      <label for="exampleInputName12" class="col-lg-4 rost_sousuo">报减员：</label>
                                        <div class="col-lg-8">

                                       	  <input type="text" class="form-control" id="exampleInputName12" value="">
                                        </div>
                                    </div> 
                                    <div class="form-group search-unit">
                                        <label class="col-lg-4 rost_sousuo">报减时间：</label>
                                        <div class="input-group date col-lg-8 rost_time">
                                             <input type="text" class="form-control">
                                             <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                        </div>
                                    </div>
                                    <div class="form-group search-unit">
                                      <label for="exampleInputName13" class="col-lg-4 rost_sousuo">客服：</label>
                                      <div class="col-lg-8">
                                        	<input type="text" class="form-control" id="exampleInputName13" value="">
                                        </div>
                                    </div>
                                    
                                    </div>
                                  </form>
                          </div>
                  </div>
                <!-- 订单信息头部结束 -->
                
                <!--社保公险金开始-->
                
                 <div class="panel-heading panel-heading_two">社保公积金</div>
                <div class="panel-body panel-body_two">
                <table id="example2" class="table table-striped table-bordered table-hover rost_table" >
                <thead>
                <tr>
                    <th>险种</th>
                    <th>服务起始月</th>
                    <th>服务结束月</th>
                    <th>账单起始月</th>
                    <th>企业基数</th>
                    <th>个人基数</th>
                    <th>企业比例</th>
                    <th>个人比例</th>
                    <th>企业附加额</th>
                    <th>个人附加额</th>
                    <th>企业金额</th>
                    <th>个人金额</th>
                    <th>缴纳金额</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>住房公积金</td>
                    <td>201601</td>
                    <td>&nbsp;</td>
                    <td>201410</td>
                    <td>805</td>
                    <td>805</td>
                    <td>10%</td>
                    <td>10%</td>
                    <td>0</td>
                    <td>0</td>
                    <td>80.5</td>
                    <td>81</td>
                    <td>161.5</td>
                </tr>
                
                <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>80.5</td><!--合计-->
                    <td>81</td><!--合计-->
                    <td>161.5</td><!--合计-->
                </tr>
              	
                </tbody>
                </table>
                </div>
                <!--社保公险金开结束-->
               
               
               
                <br><br> 
                <!--非社保公险金开始-->
                <div class="panel-heading panel-heading_two">非社保公积金</div>
                <div class="panel-body panel-body_two">
                <table id="example3" class="table table-striped table-bordered table-hover rost_table" >
                <thead>
                <tr>
                    <th>报价单名称</th>
                    <th>产品名称</th>
                    <th>服务起始月</th>
                    <th>服务起始日</th>
                    <th>服务结束月</th>
                    <th>服务结束日</th>
                    <th>账单起始月</th>
                    <th>缴纳金额</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>20151229报价单cfytest</td>
                    <td>商务子女险</td>
                    <td>201601</td>
                    <td>01</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>201410</td>
                    <td>10.00</td>
                </tr>
                <tr>
                    <td>20151229报价单cfytest</td>
                    <td>附加子女险</td>
                    <td>201601</td>
                    <td>01</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>201410</td>
                    <td>10.00</td>
                </tr>
                <tr>
                    <td>20151229报价单cfytest</td>
                    <td>子女险（子非）</td>
                    <td>201601</td>
                    <td>01</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>201410</td>
                    <td>10.00</td>
                </tr>
                
                <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>30.00</td><!--合计-->
                </tr>
              	
                </tbody>
                </table>
                </div>
                <!--非社保公险金开结束-->
                <br><br>
                </div>
            <!--订单信息结束-->
                 <!--订单费用信息开始-->
            	<div class="order_infor_two">
                <!-- 账单费用信息头部开始 -->
                        <div class="well well-lg well_bg" >
                            <!--<h5 class="font-bold"><i class="fa pe-7s-search"></i> 查询</h5>-->
                        	<div class="row">
                                  <form role="form" class="search-form"> 
                                  <div class="clearfix">
                                  
                                    <div class="form-group search-unit">
                                        <label class="col-lg-4 rost_sousuo">账单月起止时间：</label>
                                        <div class="input-group date col-lg-8 rost_time">
                                             <input type="text" class="form-control">
                                             <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                        </div>
                                    </div>
                                    
                                    <div class="form-group search-unit search-unit_two">
                                        <div class="input-group date col-lg-12 rost_time">
                                             <input type="text" class="form-control">
                                             <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                        </div>
                                    </div>
                                    
                                    <div class="form-group search-unit">
                                        <button class="btn btn-info zhangdan" type="button">
                    						<i class=""></i> <span class="bold">切换为账单月视图</span>
                    					</button>
                                        <button class="btn btn-info fuwu" type="button">
                    						<i class=""></i> <span class="bold">切换为服务月视图</span>
                    					</button>
                                    </div>
                                    
                                    </div>
                                    <center>
                                    	<button class="btn btn-info" type="button">
                    						<i class=""></i> <span class="bold">查 询</span>
                    					</button>
                                    </center>
                                  </form>
                          </div>
                  </div>
                <!-- 账单费用信息头部结束 -->
                
                <!--社保公险金开始-->
                <!--服务月视图开始-->
                <div class="panel-body panel-body_two panel-body_third">
                <table id="example4" class="table table-striped table-bordered table-hover rost_table widths" >
                <thead>
                <tr>
                    <th class="delete">服务月</th>
                    <th>险种</th>
                    <th>金额</th>
                    <th>服务月</th>
                    <th>账单月</th>
                    <th>企业基数</th>
                    <th>个人基数</th>
                    <th>企业比例</th>
                    <th>个人比例</th>
                    <th>是否预收</th>
                    <th>付费频率</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>201601</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                </tr>
                
                <tr>
                    <td>&nbsp;</td>
                    <td>住房公积金</td>
                    <td>161.5</td>
                    <td>201601</td>
                    <td>201601</td>
                    <td>805</td>
                    <td>805</td>
                    <td>0.1</td>
                    <td>0.1</td>
                    <td>否</td>
                    <td>每月收</td>
                </tr>
                
                <tr>
                    <td>&nbsp;</td>
                    <td>商保子女险</td>
                    <td>10</td>
                    <td>201601</td>
                    <td>201601</td>
                    <td>0</td>
                    <td>0</td>
                    <td>0</td>
                    <td>0</td>
                    <td>否</td>
                    <td>每月收</td><!--合计-->
                </tr>
                
                <tr>
                    <td>&nbsp;</td>
                    <td>附加子女险</td>
                    <td>10</td>
                    <td>201601</td>
                    <td>201601</td>
                    <td>0</td>
                    <td>0</td>
                    <td>0</td>
                    <td>0</td>
                    <td>否</td>
                    <td>每月收</td><!--合计-->
                </tr>
                
                <tr>
                    <td>&nbsp;</td>
                    <td>子女险（子非）</td>
                    <td>10</td>
                    <td>201601</td>
                    <td>201601</td>
                    <td>0</td>
                    <td>0</td>
                    <td>0</td>
                    <td>0</td>
                    <td>否</td>
                    <td>每月收</td><!--合计-->
                </tr>
                
                <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>191.5</td><!--合计-->
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                </tr>
                
                
                <tr>
                    <td>201602</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                </tr>
                
                <tr>
                    <td>&nbsp;</td>
                    <td>住房公积金</td>
                    <td>161.5</td>
                    <td>201601</td>
                    <td>201601</td>
                    <td>805</td>
                    <td>805</td>
                    <td>0.1</td>
                    <td>0.1</td>
                    <td>否</td>
                    <td>每月收</td>
                </tr>
                
                <tr>
                    <td>&nbsp;</td>
                    <td>商保子女险</td>
                    <td>10</td>
                    <td>201601</td>
                    <td>201601</td>
                    <td>0</td>
                    <td>0</td>
                    <td>0</td>
                    <td>0</td>
                    <td>否</td>
                    <td>每月收</td><!--合计-->
                </tr>
                
                <tr>
                    <td>&nbsp;</td>
                    <td>附加子女险</td>
                    <td>10</td>
                    <td>201601</td>
                    <td>201601</td>
                    <td>0</td>
                    <td>0</td>
                    <td>0</td>
                    <td>0</td>
                    <td>否</td>
                    <td>每月收</td><!--合计-->
                </tr>
                
                <tr>
                    <td>&nbsp;</td>
                    <td>子女险（子非）</td>
                    <td>10</td>
                    <td>201601</td>
                    <td>201601</td>
                    <td>0</td>
                    <td>0</td>
                    <td>0</td>
                    <td>0</td>
                    <td>否</td>
                    <td>每月收</td><!--合计-->
                </tr>
                
                <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>191.5</td><!--合计-->
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                </tr>
              	
                </tbody>
                </table>
                </div>
                <!--服务月视图结束-->
                
                <!--账单月视图开始-->
                <div class="panel-body panel-body_two panel-body_four">
                <table id="example5" class="table table-striped table-bordered table-hover rost_table" >
                <thead>
                <tr>
                    <th class="delete">账单月</th>
                    <th>险种</th>
                    <th>金额</th>
                    <th>服务月</th>
                    <th>账单月</th>
                    <th>企业基数</th>
                    <th>个人基数</th>
                    <th>企业比例</th>
                    <th>个人比例</th>
                    <th>是否预收</th>
                    <th>付费频率</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>201601</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                </tr>
                
                <tr>
                    <td>&nbsp;</td>
                    <td>住房公积金</td>
                    <td>161.5</td>
                    <td>201601</td>
                    <td>201601</td>
                    <td>805</td>
                    <td>805</td>
                    <td>0.1</td>
                    <td>0.1</td>
                    <td>否</td>
                    <td>每月收</td>
                </tr>
                
                <tr>
                    <td>&nbsp;</td>
                    <td>商保子女险</td>
                    <td>10</td>
                    <td>201601</td>
                    <td>201601</td>
                    <td>0</td>
                    <td>0</td>
                    <td>0</td>
                    <td>0</td>
                    <td>否</td>
                    <td>每月收</td><!--合计-->
                </tr>
                
                <tr>
                    <td>&nbsp;</td>
                    <td>附加子女险</td>
                    <td>10</td>
                    <td>201601</td>
                    <td>201601</td>
                    <td>0</td>
                    <td>0</td>
                    <td>0</td>
                    <td>0</td>
                    <td>否</td>
                    <td>每月收</td><!--合计-->
                </tr>
                
                <tr>
                    <td>&nbsp;</td>
                    <td>子女险（子非）</td>
                    <td>10</td>
                    <td>201601</td>
                    <td>201601</td>
                    <td>0</td>
                    <td>0</td>
                    <td>0</td>
                    <td>0</td>
                    <td>否</td>
                    <td>每月收</td><!--合计-->
                </tr>
                
                <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>191.5</td><!--合计-->
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                </tr>
                
                
                <tr>
                    <td>201602</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                </tr>
                
                <tr>
                    <td>&nbsp;</td>
                    <td>住房公积金</td>
                    <td>161.5</td>
                    <td>201601</td>
                    <td>201601</td>
                    <td>805</td>
                    <td>805</td>
                    <td>0.1</td>
                    <td>0.1</td>
                    <td>否</td>
                    <td>每月收</td>
                </tr>
                
                <tr>
                    <td>&nbsp;</td>
                    <td>商保子女险</td>
                    <td>10</td>
                    <td>201601</td>
                    <td>201601</td>
                    <td>0</td>
                    <td>0</td>
                    <td>0</td>
                    <td>0</td>
                    <td>否</td>
                    <td>每月收</td><!--合计-->
                </tr>
                
                <tr>
                    <td>&nbsp;</td>
                    <td>附加子女险</td>
                    <td>10</td>
                    <td>201601</td>
                    <td>201601</td>
                    <td>0</td>
                    <td>0</td>
                    <td>0</td>
                    <td>0</td>
                    <td>否</td>
                    <td>每月收</td><!--合计-->
                </tr>
                
                <tr>
                    <td>&nbsp;</td>
                    <td>子女险（子非）</td>
                    <td>10</td>
                    <td>201601</td>
                    <td>201601</td>
                    <td>0</td>
                    <td>0</td>
                    <td>0</td>
                    <td>0</td>
                    <td>否</td>
                    <td>每月收</td><!--合计-->
                </tr>
                
                <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>191.5</td><!--合计-->
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                </tr>
              	
                </tbody>
                </table>
                </div>
                <!--账单月视图结束-->
                <!--社保公险金开结束-->
                              
                <br><br> 
                </div>
            <!--订单费用信息结束-->
                </div>
                </div>
                </div>
                <!--订单结束-->
                  <!--账单--> 
         			<div class="zhang_dan" style=" ">
                     <div class="panel-body " style="background:#fff; margin:32px 22px 0;">
                            <h4 class="font-light m-b-xs">
                               账单查看
                            </h4>
                            <small class="zhangdan_nianyues">账单年月</small><small class="zhangdan_nianyues">201510月</small>
                        </div>
                        <div class="zhifu">
                            <button class="btn btn-success " type="button" >支付</button>
                            <button class="btn btn-default " type="button"  style="display:none;">已支付</button>
                        </div>
                   <div>
                        <div class="panel-body panel-bag">
                            <table id="example6" class="table table-striped table-bordered table-hover">
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
                 <!--账单结束-->             
                <!--个人信息-->
                 <!-- 开始 -->
                 		<div class="panel-body grxx" style=" display:none;">
                        <div class="row">
                        	<div style="border-bottom:1px solid #33CCFF; margin:20px; margin-bottom:0; line-height:32px; overflow:hidden;">
                        	<h4 style="float:left; margin-left:0; font-weight:normal;">个人信息</h4>
                            <div style="float:right">
                              <button type="button" class="btn btn-success btn-xs jbenxinxi"><span class="bold">基本信息&gt;&gt;</span></button>
                            </div>
                            </div>
                            <div class="hpanel">
                            <div class="panel-body">
                            <!--个人信息保存-->
                     		<div class="gerens" >
                             	<ul >
                                	<li>2010/9-2015/7</li>
                                    <li>西安科技大学</li>
                                    <li>电气工程及其自动化</li>
                                    <li>本科</li>
                                </ul>
                                <div class="xiushan">
                               	   <a href="#" class="pe-7s-note pe-2x xiugai"></a>
                                   <a href="#" class="pe-7s-trash pe-2x demo4"></a>
                                </div>
                            </div>
                     <!--个人信息保存 end-->
                              <form role="form" class="searchs-form form-horizontal geren_form" style="display:none;"> 
                              <!--姓名-->
                              <div class="form-group searchs-unit">
                                 <label for="exampleInputName4" class="col-lg-4">姓名：</label>
                                 <div class="input-group col-lg-8">
                                  <label class="control-label" style=" text-align:left;">李想</label>
                                   </div>
                                   </div>
                              <!--手机-->
                              <div class="form-group searchs-unit">
                                 <label for="exampleInputName4" class="col-lg-4">手机：</label>
                                 <div class="input-group col-lg-8">
                                  <label class="control-label" style=" text-align:left;">12345678901</label>
                                   </div>
                                   </div>
                                   
                                   <!--email-->
                              <div class="form-group searchs-unit" style=" width:90%;">
                                 <label for="exampleInputName4" class="col-lg-2">Email：</label>
                                 <div class="input-group col-lg-10">
                                  <label class="control-label" style=" text-align:left;">1234567890</label>
                                   </div>
                                   </div>
                                   
                                    <!--性别-->
                              		<div class="form-group searchs-unit">
                                    <label for="exampleInputName4" class="col-lg-4">性别：</label>
                                    <div class="col-lg-4" >
                                      <div class="sex_style" style=" text-align:left">
                                      <span class="sex sex_third">男</span>
                                  <input class="new_style_four sex" type="radio" id="optionsRadios1"  name="optionsRadios" value="option1" checked="" />
                                    <span class="sex sex_four">女</span>
                                        <input class="new_style_four sex" type="radio" id="optionsRadios2"  name="optionsRadios" value="option1" checked="" />
                                      </div>
                                    </div>
                                    </div>
                              <!--联系电话-->
                                <div class="form-group searchs-unit" style="width:62%;">
                                 <label for="exampleInputName4" class="col-lg-3">联系电话：</label>
                                 <div class="input-group col-lg-6">
                                  <input type="text" class="form-control" id="exampleInputName2">
                                   </div>
                                   </div>
                                
                                    
                                    <!--出生日期-->
                                  <div class="form-group searchs-unit" >
                                 <label for="exampleInputName4" class="col-lg-4 ">出生日期：</label>
                                 <div class="input-group date col-lg-8">
                                  <input type="text" class="form-control" id="exampleInputName2">
                                  <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                 </div>
                                 </div>
                                    <!--证件类型-->  
                                    <div class="form-group searchs-unit">
                                    <label for="exampleInputName4" class="col-lg-4">证件类型：</label>
                                    <div class="col-lg-8" style="padding:0;">
                                    <select class="form-control " name="account" placeholder="全部">
                                        <option>身份证</option>
                                        <option>其他</option>
                                        <option>普通用户</option>
                                    </select>
                                    </div>
                                </div>
                                    
                                   <!-- 身份证号-->
                                  <div class="form-group searchs-unit">
                                 <label for="exampleInputName4" class="col-lg-4">身份证号：</label>
                                 <div class="input-group col-lg-8">
                                  <input type="text" class="form-control" id="exampleInputName2">
                                   <!--<span class="reds">*</span>-->
                                   </div>
                                   </div>
                                   <!--证件类型 电子版-->   
                                   <div class="form-group searchs-unit" style=" width:92%;">
                                    <label  class="col-lg-2">证件类型电子版：</label>
                                    <input id="lefile" type="file" style="display:none;">
                                    <div class="col-lg-10 input-group" style="text-align:left;">
                                      <button type="button" class="btn btn-primary" onClick="$('input[id=lefile]').click();">浏览</button>
                                      <input id="photoCover" class="input-large input-dianzis" type="text">
                                    </div>
                                    </div>
                                    
                                <!--民族-->	
                                 <div class="form-group searchs-unit">
                                 <label for="exampleInputName4" class="col-lg-4">民族：</label>
                                 <div class="input-group col-lg-8">
                                  <input type="text" class="form-control" id="exampleInputName2">
                                   </div>
                                   </div>
                               		<!--籍贯-->
                                  <div class="form-group searchs-unit">
                                 <label for="exampleInputName4" class="col-lg-4">籍贯：</label>
                                 <div class="input-group col-lg-8">
                                  <input type="text" class="form-control" id="exampleInputName2">
                                   </div>
                                   </div>
                               <!--婚姻状况-->
                                 <div class="form-group searchs-unit">
                                    <label for="exampleInputName4" class="col-lg-4">婚姻状况：</label>
                                    <div class="col-lg-8" style="padding:0;">
                                    <select class="form-control " name="account" placeholder="未婚">
                                        <option>已婚</option>
                                        <option>其他</option>
                                        <option>未婚</option>
                                    </select>
                                    </div>
                                </div>
                               <!--户籍类型-->
                               	<div class="form-group searchs-unit">
                                    <label for="exampleInputName4" class="col-lg-4">户籍类型：</label>
                                    <div class="col-lg-8" style="padding:0;">
                                    <select class="form-control " name="account" placeholder="城镇">
                                        <option>城镇</option>
                                        <option>农村</option>
                                        <option>普通用户</option>
                                    </select>
                                    </div>
                                </div>
                               <!--户口所在地-->
                               <div class="form-group searchs-unit">
                                 <label for="exampleInputName4" class="col-lg-4">户口所在地：</label>
                                 <div class="input-group col-lg-8">
                                  <input type="text" class="form-control" id="exampleInputName2">
                                   </div>
                                   </div>
                               <!--现居住地-->
                               <div class="form-group searchs-unit">
                                 <label for="exampleInputName4" class="col-lg-4">现居住地：</label>
                                 <div class="input-group col-lg-8">
                                  <input type="text" class="form-control" id="exampleInputName2">
                                   </div>
                                   </div>
                               <!--家庭住址-->
                               	<div class="form-group searchs-unit">
                                 <label for="exampleInputName4" class="col-lg-4">家庭地址：</label>
                                 <div class="input-group col-lg-8">
                                  <input type="text" class="form-control" id="exampleInputName2">
                                   </div>
                                   </div>
                            <!--邮编-->
                            <div class="form-group searchs-unit">
                                 <label for="exampleInputName4" class="col-lg-4">邮编：</label>
                                 <div class="input-group col-lg-8">
                                  <input type="text" class="form-control" id="exampleInputName2">
                                  </div>
                            </div>
                             <!--最高学历--> 
                              <div class="form-group searchs-unit">
                                    <label for="exampleInputName4" class="col-lg-4">最高学历：</label>
                                    <div class="col-lg-8" style="padding:0;">
                                    <select class="form-control " name="account" placeholder="高中">
                                        <option>大专</option>
                                        <option>本科</option>
                                        <option>高中</option>
                                    </select>
                                    </div>
                                </div>
                               <!--最高学位-->
                               <div class="form-group searchs-unit">
                                    <label for="exampleInputName4" class="col-lg-4">最高学位：</label>
                                    <div class="col-lg-8" style="padding:0;">
                                    <select class="form-control " name="account" placeholder="学士">
                                        <option>硕士</option>
                                        <option>博士</option>
                                        <option>学士</option>
                                    </select>
                                    </div>
                                </div>
                                 <!--学历证明-->   
                                   <div class="form-group searchs-unit" style=" width:92%;">
                                    <label for="exampleInputName3" class="col-lg-2">学历证明：</label>
                                    <input id="lefile1" type="file" style="display:none;">
                                    <div class="input-group col-lg-10" style=" text-align:left;">
                                      <button type="button" class="btn btn-primary" onClick="$('input[id=lefile1]').click();">浏览</button>
                                      <input id="photoCover1" class="input-large input-dianzis" type="text">
                                    </div>
                                    </div>
                               <!--参加工作时间-->
                               	<div class="form-group searchs-unit">
                                     <label for="exampleInputName4" class="col-lg-4">参加工作时间：</label>
                                     <div class="input-group date col-lg-8">
                                      <input type="text" class="form-control" id="exampleInputName2">
                                      <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                     </div>
                                 </div>
                               <!--档案存放处-->
                               	  <div class="form-group searchs-unit">
                                 <label for="exampleInputName4" class="col-lg-4">档案存放处：</label>
                                 <div class="input-group col-lg-8">
                                  <input type="text" class="form-control" id="exampleInputName2">
                                  </div>
                            </div>
                            <!--是否外籍--> 
                                 <div class="form-group searchs-unit">
                                   <label for="exampleInputName4" class="col-lg-4">是否外籍：</label>
                                    <div class="col-lg-4">
                                      <div class="sex_style" style=" text-align:left">
                                      <span class="sex sex_third">是</span>
                                  <input class="new_style_four sex" type="radio" id="optionsRadios1"  name="optionsRadios" value="option1" checked="" />
                                    <span class="sex sex_four">否</span>
                                        <input class="new_style_four sex" type="radio" id="optionsRadios2"  name="optionsRadios" value="option1" checked="" />
                                      </div>
                                    </div>
                                    </div>
                                  <!--是否海外留学-->
                                  <div class="form-group searchs-unit">
                                    <label for="exampleInputName4" class="col-lg-4">是否海外留学：</label>
                                    <div class="col-lg-4">
                                      <div class="sex_style" style=" text-align:left">
                                      <span class="sex sex_third">是</span>
                                  <input class="new_style_four sex" type="radio" id="optionsRadios3"  name="optionsRadios1" value="option2" checked="" />
                                    <span class="sex sex_four">否</span>
                                        <input class="new_style_four sex" type="radio" id="optionsRadios4"  name="optionsRadios1" value="option2" checked="" />
                                      </div>
                                    </div>
                                    </div>
                              <!--开户行--> 
                               <div class="form-group searchs-unit">
                                 <label for="exampleInputName4" class="col-lg-4">开户行：</label>
                                 <div class="input-group col-lg-8">
                                  <input type="text" class="form-control" id="exampleInputName2">
                                  </div>
                            </div>
                             <!--开户支行-->
                              <div class="form-group searchs-unit">
                                 <label for="exampleInputName4" class="col-lg-4">开户支行：</label>
                                 <div class="input-group col-lg-8">
                                  <input type="text" class="form-control" id="exampleInputName2">
                                  </div>
                            </div>
                             <!--工资卡号-->
                                <div class="form-group searchs-unit">
                                 <label for="exampleInputName4" class="col-lg-4">工资卡号：</label>
                                 <div class="input-group col-lg-8">
                                  <input type="text" class="form-control" id="exampleInputName2">
                                  </div>
                            </div>
                               <!--电子简历-->
                                  <div class="form-group searchs-unit">
                                    <label for="exampleInputName3" class="col-lg-4">电子简历：</label>
                                    <input id="lefile2" type="file" style="display:none;">
                                    <div class="input-group col-lg-8" style=" text-align:left;">
                                      <button type="button" class="btn btn-primary" onClick="$('input[id=lefile2]').click();">浏览</button>
                                      <input id="photoCover2" class="input-large input-dianzi" type="text">
                                    </div>
                                    </div>
                                <!--户口薄-->
                                   <div class="form-group searchs-unit">
                                    <label for="exampleInputName3" class="col-lg-4">户口簿：</label>
                                    <input id="lefile3" type="file" style="display:none;">
                                    <div class="input-group col-lg-8" style=" text-align:left;">
                                      <button type="button" class="btn btn-primary" onClick="$('input[id=lefile3]').click();">浏览</button>
                                      <input id="photoCover3" class="input-large input-dianzi" type="text">
                                    </div>
                                    </div>
                                 <!--离职证明-->
                                  <div class="form-group searchs-unit">
                                    <label for="exampleInputName3" class="col-lg-4">离职证明：</label>
                                    <input id="lefile4" type="file" style="display:none;">
                                    <div class="input-group col-lg-8" style=" text-align:left;">
                                      <button type="button" class="btn btn-primary" onClick="$('input[id=lefile4]').click();">浏览</button>
                                      <input id="photoCover4" class="input-large input-dianzi" type="text">
                                    </div>
                                    </div> 
                                 <!--健康证明-->
                                   <div class="form-group searchs-unit">
                                    <label for="exampleInputName3" class="col-lg-4">健康证明：</label>
                                    <input id="lefile5" type="file" style="display:none;">
                                    <div class="input-group col-lg-8" style=" text-align:left;">
                                      <button type="button" class="btn btn-primary" onClick="$('input[id=lefile5]').click();">浏览</button>
                                      <input id="photoCover5" class="input-large input-dianzi" type="text">
                                    </div>
                                    </div>
                                  <!--薪资证明-->
                                   <div class="form-group searchs-unit">
                                    <label for="exampleInputName3" class="col-lg-4">薪资证明：</label>
                                    <input id="lefile6" type="file" style="display:none;">
                                    <div class="input-group col-lg-8" style=" text-align:left;">
                                      <button type="button" class="btn btn-primary" onClick="$('input[id=lefile6]').click();">浏览</button>
                                      <input id="photoCover6" class="input-large input-dianzi" type="text">
                                    </div>
                                    </div>
                                  
                               <!--保存 删除-->
                               <center style="clear:both; padding-top:30px;">
                               		<button type="button" class="btn btn-info">保存</button>
                                    <button type="button" class="btn btn-danger demo4">删除</button>
                               </center>
                                 <!--图片--> 
                      <div class="pucter" style=" position:absolute; left:74%; top:3%;">
                      		<a href="#"><img src="img/touxiang.jpg" width="114" height="170"></a>
                      </div>     
                           
                              </form>
                              </div>
                              </div>
                              </div>
                              </div>
                              
                               
                     <!--个人信息 end--> 
                <!--结束 -->
                 <!--基本信息-->
                 <!-- 开始 -->
                 <div class="panel-body jben_xx" style=" display:none;">
                        <div class="row ">
                        	<div style="border-bottom:1px solid #33CCFF; margin:20px; line-height:32px; overflow:hidden;">
                        	<h4 style="float:left; font-weight:normal;">基本信息</h4>
                            <div style="float:right">
                               <button type="button" class="btn btn-success btn-xs gwxx"><span class="bold">岗位信息&gt;&gt;</span></button> 
                            </div>
                            </div>
                            <div class="hpanel">
                            <div class="panel-body">
                            <!--岗位信息保存-->
                     		<div class="jbxxs" style="display:none;">
                             	<ul >
                                	<li>2010/9-2015/7</li>
                                    <li>西安科技大学</li>
                                    <li>电气工程及其自动化</li>
                                    <li>本科</li>
                                </ul>
                                <div class="xiushan">
                               	   <a href="#" class="pe-7s-note pe-2x xiugai"></a>
                                   <a href="#" class="pe-7s-trash pe-2x demo4"></a>
                                </div>
                            </div>
                     <!--岗位信息保存 end-->
                              <form role="form" class="searchs-form form-horizontal jbxx jbxx_form"> 
                                  <!--单位名称-->
                                  <div class="form-group searchs-unit">
                                 <label for="exampleInputName4" class="col-lg-4">单位名称：</label>
                                 <div class="input-group col-lg-8">
                                  <input type="text" class="form-control" id="exampleInputName2">
                                   </div>
                                   </div>
                                    <!--入职时间-->
                                    <div class="form-group searchs-unit">
                                     <label for="exampleInputName4" class="col-lg-4">入职时间：</label>
                                     <div class="input-group date col-lg-8">
                                      <input type="text" class="form-control" id="exampleInputName2">
                                      <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                     </div>
                                 </div>
                                   <!--转正时间-->
                                    <div class="form-group searchs-unit">
                                     <label for="exampleInputName4" class="col-lg-4">转正时间：</label>
                                     <div class="input-group date col-lg-8">
                                      <input type="text" class="form-control" id="exampleInputName2">
                                      <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                     </div>
                                 </div>
                                    <!--工作地-->
                                    <div class="form-group searchs-unit">
                                 <label for="exampleInputName4" class="col-lg-4">工作地：</label>
                                 <div class="input-group col-lg-8">
                                  <input type="text" class="form-control" id="exampleInputName2">
                                   </div>
                                   </div>
                                    <!--发薪地-->
                                    <div class="form-group searchs-unit">
                                 <label for="exampleInputName4" class="col-lg-4">发薪地：</label>
                                 <div class="input-group col-lg-8">
                                  <input type="text" class="form-control" id="exampleInputName2">
                                   </div>
                                   </div>
                                    <!--社保缴纳地-->
                                     <div class="form-group searchs-unit">
                                 <label for="exampleInputName4" class="col-lg-4">社保缴纳地：</label>
                                 <div class="input-group col-lg-8">
                                  <input type="text" class="form-control" id="exampleInputName2">
                                   </div>
                                   </div>
                                   <!--停发标志-->
                                    <div class="form-group searchs-unit">
                                    <label for="exampleInputName4" class="col-lg-4">停发标志：</label>
                                    <div class="col-lg-8" style="padding:0;">
                                    <select class="form-control " name="account" placeholder="停发">
                                        <option>再发</option>
                                        <option>停发</option>
                                    </select>
                                    </div>
                                </div>
                                    <!--员工状态-->
                                      <div class="form-group searchs-unit">
                                    <label for="exampleInputName4" class="col-lg-4">员工状态：</label>
                                    <div class="col-lg-8" style="padding:0;">
                                    <select class="form-control " name="account" placeholder="在职">
                                        <option>在职</option>
                                        <option>离职</option>
                                    </select>
                                    </div>
                                </div>
                                   <!--团队-->
                                     <div class="form-group searchs-unit">
                                    <label for="exampleInputName4" class="col-lg-4">团队：</label>
                                    <div class="col-lg-8" style="padding:0;">
                                    <select class="form-control " name="account" placeholder="服务类">
                                        <option>服务类</option>
                                        <option>管理类</option>
                                    </select>
                                    </div>
                                </div>
                                    <!--招聘渠道-->
                                     <div class="form-group searchs-unit">
                                 <label for="exampleInputName4" class="col-lg-4">招聘渠道：</label>
                                 <div class="input-group col-lg-8">
                                  <input type="text" class="form-control" id="exampleInputName2">
                                   </div>
                                   </div>
                               <!--保存 删除-->
                               <center style=" clear:both; padding-top:30px;">
                               		<button type="button" class="btn btn-info">保存</button>
                                    <button type="button" class="btn btn-danger demo4">删除</button>
                               </center>
                              </form>
                              </div>
                              </div>
                              </div>
                              </div>
                     <!--基本信息 end--> 
                <!--结束 -->
                
                <!--岗位信息-->
                 <!-- 开始 -->
                  <div class="panel-body gwei_xx" style=" display:none;">
                        <div class="row">
                        	<div style="border-bottom:1px solid #33CCFF; margin:20px; line-height:32px; overflow:hidden;">
                        	<h4 style="float:left; font-weight:normal;">岗位信息</h4>
                            <div style="float:right">
                               <button type="button" class="btn btn-success btn-xs gwdd"><span class="bold">岗位调动&gt;&gt;</span></button>
                            </div>
                            </div>
                            <div class="hpanel">
                            <div class="panel-body">
                             <!--岗位信息保存-->
                     		<div class="gw_dds" style="display:none;">
                             	<ul >
                                	<li>2010/9-2015/7</li>
                                    <li>西安科技大学</li>
                                    <li>电气工程及其自动化</li>
                                    <li>本科</li>
                                </ul>
                                <div class="xiushan">
                               	   <a href="#" class="pe-7s-note pe-2x xiugai"></a>
                                   <a href="#" class="pe-7s-trash pe-2x demo4"></a>
                                </div>
                            </div>
                     <!--岗位信息保存 end-->
                              <form role="form" class="searchs-form form-horizontal gw_dd gw_dd-form" > 
                                  <!--部门名称-->
                                    <div class="form-group searchs-unit">
                                 <label for="exampleInputName4" class="col-lg-4">部门名称：</label>
                                 <div class="input-group col-lg-8">
                                  <input type="text" class="form-control" id="exampleInputName2">
                                   </div>
                                   </div>
                                    <!--成本中心-->
                                     <div class="form-group searchs-unit">
                                    <label for="exampleInputName4" class="col-lg-4">成本中心：</label>
                                    <div class="col-lg-8" style="padding:0;">
                                    <select class="form-control " name="account" placeholder="人才">
                                        <option>人才</option>
                                        <option>管理类</option>
                                    </select>
                                    </div>
                                </div>
                                   <!--岗位名称-->
                                    <div class="form-group searchs-unit">
                                 <label for="exampleInputName4" class="col-lg-4">岗位名称：</label>
                                 <div class="input-group col-lg-8">
                                  <input type="text" class="form-control" id="exampleInputName2">
                                   </div>
                                   </div>
                                    <!--工号-->
                                     <div class="form-group searchs-unit">
                                 <label for="exampleInputName4" class="col-lg-4">工号：</label>
                                 <div class="input-group col-lg-8">
                                  <input type="text" class="form-control" id="exampleInputName2">
                                   </div>
                                   </div>
                                   <!--用工性质-->
                                    <div class="form-group searchs-unit">
                                    <label for="exampleInputName4" class="col-lg-4">用工性质：</label>
                                    <div class="col-lg-8" style="padding:0;">
                                    <select class="form-control " name="account" placeholder="实习">
                                        <option>实习</option>
                                        <option>合同</option>
                                    </select>
                                    </div>
                                </div>
                                    <!--职级-->
                                     <div class="form-group searchs-unit">
                                    <label for="exampleInputName4" class="col-lg-4">职级：</label>
                                    <div class="col-lg-8" style="padding:0;">
                                    <select class="form-control " name="account" placeholder="中级">
                                        <option>中级</option>
                                        <option>高级</option>
                                    </select>
                                    </div>
                                </div>
                                    <!--是否关键岗位--> 
                                  <div class="form-group searchs-unit">
                                    <label for="exampleInputName4" class="col-lg-4">是否关键岗位：</label>
                                    <div class="col-lg-4">
                                      <div class="sex_style">
                                      <span class="sex sex_third">是</span>
                                  <input class="new_style_four sex" type="radio" id="optionsRadios3"  name="optionsRadios" value="option1" checked="" />
                                    <span class="sex sex_four">否</span>
                                        <input class="new_style_four sex" type="radio" id="optionsRadios4"  name="optionsRadios" value="option1" checked="" />
                                      </div>
                                    </div>
                                    </div>
                                  <!--汇报对象-->
                                  	 <div class="form-group searchs-unit">
                                 <label for="exampleInputName4" class="col-lg-4">汇报对象：</label>
                                 <div class="input-group col-lg-8">
                                  <input type="text" class="form-control" id="exampleInputName2">
                                   </div>
                                   </div>
                               <!--保存 删除-->
                               <center style=" clear:both; padding-top:30px;">
                               		<button type="button" class="btn btn-info">保存</button>
                                    <button type="button" class="btn btn-danger demo4">删除</button>
                               </center>
                              </form>
                              </div>
                              </div>
                              </div>
                              </div>
                     <!--岗位信息 end--> 
                <!--结束 -->
                
                 <!--岗位调动-->
                 <!-- 开始 -->
                  <div class="panel-body gangwei_dd" style=" display:none;">
                        <div class="row ">
                        	<div style="border-bottom:1px solid #33CCFF; margin:20px; line-height:32px; overflow:hidden;">
                        	<h4 style="float:left; font-weight:normal;">岗位调动</h4>
                            <div style="float:right">
                            	<button type="button" class="btn btn-success btn-xs lets"><i class="fa fa-plus"></i><span class="bold">继续添加</span></button>
                                <button type="button" class="btn btn-success btn-xs lets_update"  style="display:none;"><i class="fa fa-plus"></i><span class="bold">继续添加</span></button>
                               
                               <button type="button" class="btn btn-success btn-xs jy_beijing"><span class="bold">教育背景&gt;&gt;</span></button> 
                            </div>
                            </div>
                            <div class="hpanel">
                            <div class="panel-body" id="gangwei_body">
                            	 <!--岗位调动保存-->
                     		<div class="gangweis" style="display:none;">
                             	<ul >
                                	<li>2010/9-2015/7</li>
                                    <li>上海智阳科技有限公司</li>
                                    <li>工作岗位：高级软件工程师</li>
                                    <li>职责描述：负责后台接口开发</li>
                                    <li>证明人：李想</li>
                                </ul>
                                <div class="xiushan">
                               	   <a href="#" class="pe-7s-note pe-2x xiugai"></a>
                                   <a href="#" class="pe-7s-trash pe-2x demo4"></a>
                                </div>
                            </div>
                     <!--岗位调动保存 end-->
                              <form role="form" class="searchs-form form-horizontal gw_diaodong gangwei_form"> 
                                  <!--原部门-->
                                    <div class="form-group searchs-unit">
                                 <label for="exampleInputName4" class="col-lg-4">原部门：</label>
                                 <div class="input-group col-lg-8">
                                  <input type="text" class="form-control" id="exampleInputName2">
                                   </div>
                                   </div>
                                    <!--原岗位-->
                                    <div class="form-group searchs-unit">
                                 <label for="exampleInputName4" class="col-lg-4">原岗位：</label>
                                 <div class="input-group col-lg-8">
                                  <input type="text" class="form-control" id="exampleInputName2">
                                   </div>
                                   </div>
                                   <!--原职级-->
                                    <div class="form-group searchs-unit">
                                    <label for="exampleInputName4" class="col-lg-4">原职级：</label>
                                    <div class="col-lg-8" style="padding:0;">
                                    <select class="form-control " name="account" placeholder="中级">
                                        <option>中级</option>
                                        <option>高级</option>
                                    </select>
                                    </div>
                                </div>
                                    <!--原岗位任职期限-->
                                     <div class="form-group searchs-unit">
                                 <label for="exampleInputName4" class="col-lg-4">原岗位任职期限：</label>
                                 <div class="input-group col-lg-8">
                                  <input type="text" class="form-control" id="exampleInputName2">
                                   </div>
                                   </div>
                                    <!--原汇报对象-->
                                   <div class="form-group searchs-unit">
                                 <label for="exampleInputName4" class="col-lg-4">原汇报对象：</label>
                                 <div class="input-group col-lg-8">
                                  <input type="text" class="form-control" id="exampleInputName2">
                                   </div>
                                   </div>
                                    <!--原工作地-->
                                     <div class="form-group searchs-unit">
                                 <label for="exampleInputName4" class="col-lg-4">原工作地：</label>
                                 <div class="input-group col-lg-8">
                                  <input type="text" class="form-control" id="exampleInputName2">
                                   </div>
                                   </div>
                                   <!--原成本中心-->
                                    <div class="form-group searchs-unit">
                                    <label for="exampleInputName4" class="col-lg-4">原成本中心：</label>
                                    <div class="col-lg-8" style="padding:0;">
                                    <select class="form-control " name="account" placeholder="财务部">
                                        <option>财务部</option>
                                        <option>产品部</option>
                                    </select>
                                    </div>
                                </div>
                                   <!--现部门-->
                                    <div class="form-group searchs-unit">
                                 <label for="exampleInputName4" class="col-lg-4">现部门：</label>
                                 <div class="input-group col-lg-8">
                                  <input type="text" class="form-control" id="exampleInputName2">
                                   </div>
                                   </div>
                                    <!--现岗位-->
                                    <div class="form-group searchs-unit">
                                 <label for="exampleInputName4" class="col-lg-4">现岗位：</label>
                                 <div class="input-group col-lg-8">
                                  <input type="text" class="form-control" id="exampleInputName2">
                                   </div>
                                   </div>
                                    <!--现职级-->
                                    <div class="form-group searchs-unit">
                                    <label for="exampleInputName4" class="col-lg-4">现职级：</label>
                                    <div class="col-lg-8" style="padding:0;">
                                    <select class="form-control " name="account" placeholder="中级">
                                        <option>中级</option>
                                        <option>高级</option>
                                    </select>
                                    </div>
                                </div>
                                    <!--当前汇报对象-->
                                     <div class="form-group searchs-unit">
                                 <label for="exampleInputName4" class="col-lg-4">当前汇报对象：</label>
                                 <div class="input-group col-lg-8">
                                  <input type="text" class="form-control" id="exampleInputName2">
                                   </div>
                                   </div>
                                   <!--当前工作地-->
                                    <div class="form-group searchs-unit">
                                 <label for="exampleInputName4" class="col-lg-4">当前工作地：</label>
                                 <div class="input-group col-lg-8">
                                  <input type="text" class="form-control" id="exampleInputName2">
                                   </div>
                                   </div>
                                    <!--当前成本中心-->
                                     <div class="form-group searchs-unit">
                                    <label for="exampleInputName4" class="col-lg-4">当前本中心：</label>
                                    <div class="col-lg-8" style="padding:0;">
                                    <select class="form-control " name="account" placeholder="财务部">
                                        <option>财务部</option>
                                        <option>产品部</option>
                                    </select>
                                    </div>
                                </div>
                                   <!--岗位调整日期-->
                                   <div class="form-group searchs-unit">
                                     <label for="exampleInputName4" class="col-lg-4">岗位调整日期：</label>
                                     <div class="input-group date col-lg-8">
                                      <input type="text" class="form-control" id="exampleInputName2">
                                      <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                     </div>
                                 </div>
                                  <!--是否调薪--> 
                                    <div class="form-group searchs-unit">
                                    <label for="exampleInputName4" class="col-lg-4">是否调薪：</label>
                                    <div class="col-lg-4">
                                      <div class="sex_style">
                                      <span class="sex sex_third">是</span>
                                  <input class="new_style_four sex" type="radio" id="optionsRadios3"  name="optionsRadios" value="option1" checked="" />
                                    <span class="sex sex_four">否</span>
                                        <input class="new_style_four sex" type="radio" id="optionsRadios4"  name="optionsRadios" value="option1" checked="" />
                                      </div>
                                    </div>
                                    </div>
                                  <!--岗位离职原因-->
                              <div class="form-group searchs-unit">
                                 <label for="exampleInputName4" class="col-lg-4">岗位离职原因：</label>
                                 <div class="input-group col-lg-8">
                                  <input type="text" class="form-control" id="exampleInputName2">
                                   </div>
                               </div>
                               <!--保存 删除-->
                               <center style=" clear:both; padding-top:30px;">
                               		<button type="button" class="btn btn-info">保存</button>
                                    <button type="button" class="btn btn-danger demo4">删除</button>
                               </center>
                              </form>
                              </div>
                              </div>
                              </div>
                              </div>
                     <!--岗位调动 end--> 
                <!--结束 -->
               
                     <!--教育背景-->
                     <div class="panel-body jiaoyu" style=" display:none">
                      <div class="row ">
                        	<div style="border-bottom:1px solid #33CCFF; margin:20px; line-height:32px; overflow:hidden;">
                        	<h4 style="float:left; font-weight:normal;">教育背景</h4>
                            <div style="float:right">
                            	<button type="button" class="btn btn-success btn-xs adds"><i class="fa fa-plus"></i><span class="bold">继续添加</span></button>
                                <button type="button" class="btn btn-success btn-xs adds_update" style="display:none;"><i class="fa fa-plus"></i><span class="bold">继续添加</span></button>
                                <button type="button" class="btn btn-success btn-xs gzjy"><span class="bold">工作经验&gt;&gt;</span></button>
                            </div>
                            </div>
                            <div class="hpanel">
                            <div class="panel-body" id="teach_body">
                              <form role="form" class="searchs-form form-horizontal teach shows teach_form"> 
                                    <!--入学时间-->
                                    <div class="form-group searchs-unit">
                                     <label for="exampleInputName4" class="col-lg-4">入学时间：</label>
                                     <div class="input-group date col-lg-8">
                                      <input type="text" class="form-control" id="exampleInputName2">
                                      <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                     </div>
                                 </div>
                                    <!--毕业时间-->
                                     <div class="form-group searchs-unit">
                                     <label for="exampleInputName4" class="col-lg-4">毕业时间：</label>
                                     <div class="input-group date col-lg-8">
                                      <input type="text" class="form-control" id="exampleInputName2">
                                      <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                     </div>
                                 </div>
                                    <!--毕业学校-->
                                    <div class="form-group searchs-unit">
                                 <label for="exampleInputName4" class="col-lg-4">毕业学校：</label>
                                 <div class="input-group col-lg-8">
                                  <input type="text" class="form-control" id="exampleInputName2">
                                   </div>
                               </div>
                                    <!--所学专业-->
                                <div class="form-group searchs-unit">
                                 <label for="exampleInputName4" class="col-lg-4">所学专业：</label>
                                 <div class="input-group col-lg-8">
                                  <input type="text" class="form-control" id="exampleInputName2">
                                   </div>
                               </div>
                                   <!--学习形式--> 
                                   <div class="form-group searchs-unit">
                                    <label for="exampleInputName4" class="col-lg-4">学习形式：</label>
                                    <div class="col-lg-6">
                                      <div class="sex_style">
                                      <span class="sex sex_third">全日制</span>
                                     <input class="new_style_four sex" type="radio" id="optionsRadios1" name="optionsRadios" value="option1" checked="" />
                                    <span class="sex sex_four">非全日制</span>
                                    <input class="new_style_four sex" type="radio" id="optionsRadios2"  name="optionsRadios" value="option1" checked="" />
                                      </div>
                                    </div>
                                    </div>
                                <!--学制-->
                                 <div class="form-group searchs-unit">
                                 <label for="exampleInputName4" class="col-lg-4">学制：</label>
                                 <div class="input-group col-lg-8">
                                  <input type="text" class="form-control" id="exampleInputName2"><span class="nian">年</span>
                                   </div>
                               </div>
                                 <!--学历-->  
                                   <div class="form-group searchs-unit">
                                  <label for="exampleInputName4" class="col-lg-4">学历：</label>
                                    <div class="col-lg-8" style="padding:0;">
                                    <select class="form-control" name="account" placeholder="本科">
                                        <option>本科</option>
                                        <option>大专</option>
                                        <option>高中</option>
                                        <option>初中</option>
                                        <option>小学</option>
                                        <option>博士</option>
                                    </select>
                                    </div>
                                    </div>  
                                  <!--证书编号-->  
                                   <div class="form-group searchs-unit">
                                 <label for="exampleInputName4" class="col-lg-4">证书编号：</label>
                                 <div class="input-group col-lg-8">
                                  <input type="text" class="form-control" id="exampleInputName2">
                                   </div>
                               </div>
                               <!--保存 删除-->
                               <center style=" clear:both; padding-top:30px;">
                               		<button type="button" class="btn btn-info">保存</button>
                                    <button type="button" class="btn btn-danger demo4">删除</button>
                               </center>
                              </form>
                              
                              <!--教育背景保存-->
                     		<div class="teachs" style="display:none;">
                             	<ul >
                                	<li>2010/9-2015/7</li>
                                    <li>西安科技大学</li>
                                    <li>电气工程及其自动化</li>
                                    <li>本科</li>
                                </ul>
                                <div class="xiushan">
                               	   <a href="#" class="pe-7s-note pe-2x xiugai"></a>
                                   <a href="#" class="pe-7s-trash pe-2x demo4"></a>
                                </div>
                            </div>
                     <!--教育背景保存 end-->
                              </div>
                              </div>
                              </div>
                              </div>
                     <!--教育背景结束-->
                     
                     <!--工作经验-->
                     <div class="panel-body gongzuojy" style=" display:none;">
                     	<div class="row ">
                        	<div style="border-bottom:1px solid #33CCFF; margin:20px; line-height:32px; overflow:hidden;">
                        	<h4 style="float:left; font-weight:normal">工作经验</h4>
                            <div style="float:right">
                            	<button type="button" class="btn btn-success btn-xs gz_jy"><i class="fa fa-plus"></i><span class="bold">继续添加</span></button>
                                <button type="button" class="btn btn-success btn-xs gz_jy_update" style=" display:none;"><i class="fa fa-plus"></i><span class="bold">继续添加</span></button>
                                 <button type="button" class="btn btn-success btn-xs xmjy"><span class="bold">项目经验&gt;&gt;</span></button>
                        
                            </div>
                            </div>
                            <div class="hpanel">
                            <div class="panel-body" id="panelBody">
                          <!--工作经验保存-->
                     		<div class="jobs" style="display:none;">
                             	<ul >
                                	<li>2010/9-2015/7</li>
                                    <li>上海智阳科技有限公司</li>
                                    <li>工作岗位：高级软件工程师</li>
                                    <li>职责描述：负责后台接口开发</li>
                                    <li>证明人：李想</li>
                                </ul>
                                <div class="xiushan">
                               	   <a href="#" class="pe-7s-note pe-2x xiugai"></a>
                                   <a href="#" class="pe-7s-trash pe-2x demo4"></a>
                                </div>
                            </div>
                     <!--工作经验保存 end-->
                              <form role="form" class="searchs-form form-horizontal gongzuo_1 operation"> 
                                    <!--起始时间-->
								 <div class="form-group searchs-unit " >
                                     <label for="exampleInputName4" class="col-lg-4">起始时间：</label>
                                     <div class="input-group date col-lg-8 dateff" >
                                      <input type="text" class="form-control" id="exampleInputName2">
                                      <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                     </div>
                                    </div>
								
                                    <!--终止时间-->
								  <div class="form-group searchs-unit">
                                     <label for="exampleInputName4" class="col-lg-4">终止时间：</label>
                                     <div class="input-group date col-lg-8">
                                      <input type="text" class="form-control" id="exampleInputName2">
                                      <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                     </div>
                                 </div>
                                    <!--工作单位-->
                                    <div class="form-group searchs-unit">
                                 <label for="exampleInputName4" class="col-lg-4">工作单位：</label>
                                 <div class="input-group col-lg-8">
                                  <input type="text" class="form-control" id="exampleInputName2">
                                   </div>
                               </div>
                                    <!--工作岗位-->
                                    <div class="form-group searchs-unit">
                                 <label for="exampleInputName4" class="col-lg-4">工作岗位：</label>
                                 <div class="input-group col-lg-8">
                                  <input type="text" class="form-control" id="exampleInputName2">
                                   </div>
                               </div>
                                    
                                  <!--证明人-->  
                                   <div class="form-group searchs-unit">
                                 <label for="exampleInputName4" class="col-lg-4">证明人：</label>
                                 <div class="input-group col-lg-8">
                                  <input type="text" class="form-control" id="exampleInputName2">
                                   </div>
                               </div>
                                 <!--职责描述-->
                                 <div class="form-group searchs-unit">
                                     <label for="exampleInputName4" class="col-lg-4">职责描述：</label>
                                    <div class="col-lg-8" style="padding:0;">
                                    <textarea class="form-control" id="exampleInputName3"></textarea>
                                    </div>
                                 </div> 
                               <!--保存 删除-->
                               <center style=" clear:both; padding-top:30px;">
                               		<button type="button" class="btn btn-info">保存</button>
                                    <button type="button" class="btn btn-danger demo4">删除</button>
                               </center>
                              </form>
                       
                               
                              </div>
                              </div>
                              </div>
                              </div>
                     <!--工作经验结束-->
                     
        			 <!--项目经验-->
                      <div class="panel-body xiangmujy" style=" display:none;">
        				<div class="row ">
                        	<div style="border-bottom:1px solid #33CCFF; margin:20px; line-height:32px; overflow:hidden;">
                        	<h4 style="float:left; font-weight:normal;">项目经验</h4>
                            <div style="float:right">
                            	<button type="button" class="btn btn-success btn-xs xm_jy"><i class="fa fa-plus"></i><span class="bold">继续添加</span></button>
                                <button type="button" class="btn btn-success btn-xs xm_jy_update" style="display:none"><i class="fa fa-plus"></i><span class="bold">继续添加</span></button>
                                <button type="button" class="btn btn-success btn-xs hdzs"><span class="bold">获得证书&gt;&gt;</span></button>
                           
                            </div>
                            </div>
                            <div class="hpanel">
                            <div class="panel-body" id="xiangmu_body">
                            <!--项目经验保存-->
                     		<div class="xiangmu_1s" style="display:none;">
                             	<ul >
                                	<li>2010/9-2015/7</li>
                                    <li>上海智阳科技有限公司</li>
                                    <li>工作岗位：高级软件工程师</li>
                                    <li>职责描述：负责后台接口开发</li>
                                    <li>证明人：李想</li>
                                </ul>
                                <div class="xiushan">
                               	   <a href="#" class="pe-7s-note pe-2x xiugai"></a>
                                   <a href="#" class="pe-7s-trash pe-2x demo4"></a>
                                </div>
                            </div>
                     <!--项目经验保存 end-->
                              <form role="form" class="searchs-form form-horizontal xiangmu_1 xiangmu_1_form"> 
                                    <!--项目起始时间-->
                                     <div class="form-group searchs-unit">
                                     <label for="exampleInputName4" class="col-lg-4">项目起始时间：</label>
                                     <div class="input-group date col-lg-8">
                                      <input type="text" class="form-control" id="exampleInputName2">
                                      <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                     </div>
                                 </div>
                                    <!--项目终止时间-->
                                     <div class="form-group searchs-unit">
                                     <label for="exampleInputName4" class="col-lg-4">项目终止时间：</label>
                                     <div class="input-group date col-lg-8">
                                      <input type="text" class="form-control" id="exampleInputName2">
                                      <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                     </div>
                                 </div>
                                    <!--项目名称-->
                                     <div class="form-group searchs-unit">
                                 <label for="exampleInputName4" class="col-lg-4">项目名称：</label>
                                 <div class="input-group col-lg-8">
                                  <input type="text" class="form-control" id="exampleInputName2">
                                   </div>
                               </div>
                                    <!--项目金额-->
                                     <div class="form-group searchs-unit">
                                 <label for="exampleInputName4" class="col-lg-4">项目金额：</label>
                                 <div class="input-group col-lg-8">
                                  <input type="text" class="form-control" id="exampleInputName2">
                                   </div>
                               </div>
                                 <!--项目职责--> 
                                  <div class="form-group searchs-unit">
                                    <label for="exampleInputName4" class="col-lg-4">项目职责：</label>
                                    <div class="col-lg-8" style="padding:0;">
                                    <textarea class="form-control" id="exampleInputName3"></textarea>
                                    </div>
                                 </div> 
                                 <!--职责描述-->
                                 <div class="form-group searchs-unit">
                                    <label for="exampleInputName4" class="col-lg-4">职责描述：</label>
                                    <div class="col-lg-8" style="padding:0;">
                                    <textarea class="form-control" id="exampleInputName3"></textarea>
                                    </div>
                                 </div> 
                               <!--保存 删除-->
                               <center style=" clear:both; padding-top:30px;">
                               		<button type="button" class="btn btn-info">保存</button>
                                    <button type="button" class="btn btn-danger demo4">删除</button>
                               </center>
                              </form>
                              </div>
                              </div>
                              </div>
                              </div>
        			 <!--项目经验结束-->
                     
                     <!--获得证书-->
                      <div class="panel-body huode" style=" display:none;">
                     	<div class="row ">
                        	<div style="border-bottom:1px solid #33CCFF; margin:20px; line-height:32px; overflow:hidden;">
                        	<h4 style="float:left; font-weight:normal">获得证书</h4>
                            <div style="float:right">
                            	<button type="button" class="btn btn-success btn-xs hd_zs"><i class="fa fa-plus"></i><span class="bold">继续添加</span></button>
                                <button type="button" class="btn btn-success btn-xs hd_zs_update" style="display:none;"><i class="fa fa-plus"></i><span class="bold">继续添加</span></button>
                               <button type="button" class="btn btn-success btn-xs pxjl"><span class="bold">培训经历&gt;&gt;</span></button> 
                            
                            </div>
                            </div>
                            <div class="hpanel">
                            <div class="panel-body" id="zhengshu_body">
                            <!--获得证书保存-->
                     		<div class="zhengshus" style="display:none">
                             	<ul >
                                	<li>2010/9-2015/7</li>
                                    <li>上海智阳科技有限公司</li>

                                    <li>工作岗位：高级软件工程师</li>
                                    <li>职责描述：负责后台接口开发</li>
                                    <li>证明人：李想</li>
                                </ul>
                                <div class="xiushan">
                               	   <a href="#" class="pe-7s-note pe-2x xiugai"></a>
                                   <a href="#" class="pe-7s-trash pe-2x demo4"></a>
                                </div>
                            </div>
                     <!--获得证书保存 end-->
                             <form role="form" class="searchs-form form-horizontal zhengshu zhengshu_form" > 
                                    <!--取得证书时间-->
                                    <div class="form-group searchs-unit">
                                     <label for="exampleInputName4" class="col-lg-4">取得证书时间：</label>
                                     <div class="input-group date col-lg-8">
                                      <input type="text" class="form-control" id="exampleInputName2">
                                      <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                     </div>
                                 </div>
                                    <!--证书有效期-->
                                     <div class="form-group searchs-unit">
                                 <label for="exampleInputName4" class="col-lg-4">证书有效期：</label>
                                 <div class="input-group col-lg-8">
                                  <input type="text" class="form-control" id="exampleInputName2">
                                   </div>
                               </div>
                                    <!--证书名称-->
                                    <div class="form-group searchs-unit">
                                 <label for="exampleInputName4" class="col-lg-4">证书名称：</label>
                                 <div class="input-group col-lg-8">
                                  <input type="text" class="form-control" id="exampleInputName2">
                                   </div>
                               </div>
                                 <!--电子版上传--> 
                                  <div class="form-group searchs-unit">
                                    <label for="exampleInputName3" class="col-lg-4">电子版上传：</label>
                                    <input id="lefile7" type="file" style="display:none;">
                                    <div class="input-group col-lg-8" style=" text-align:left;">
                                      <button type="button" class="btn btn-primary" onClick="$('input[id=lefile7]').click();">浏览</button>
                                      <input id="photoCover7" class="input-large input-dianzi" type="text">
                                    </div>
                                    </div>  
                                 <!--备注-->
                                 <div class="form-group searchs-unit">
                                   <label for="exampleInputName4" class="col-lg-4">备注：</label>
                                    <div class="col-lg-8" style="padding:0;">
                                    <textarea class="form-control" id="exampleInputName3"></textarea>
                                    </div>
                                 </div> 
                               <!--保存 删除-->
                               <center style=" clear:both; padding-top:30px;">
                               		<button type="button" class="btn btn-info">保存</button>
                                    <button type="button" class="btn btn-danger demo4">删除</button>
                               </center>
                              </form>
                              </div>
                              </div>
                              </div>
                         </div>
                     <!--获得证书 end-->
                     <!--培训经历-->
                     <div class="panel-body peixun_jl" style=" display:none;">
        			    <div class="row ">
                        	<div style="border-bottom:1px solid #33CCFF; margin:20px; line-height:32px; overflow:hidden;">
                        	<h4 style="float:left; font-weight:normal">培训经历</h4>
                            <div style="float:right">
                            	<button type="button" class="btn btn-success btn-xs px_jl"><i class="fa fa-plus"></i><span class="bold">继续添加</span></button>
                                <button type="button" class="btn btn-success btn-xs px_jl_update" style="display:none;"><i class="fa fa-plus"></i><span class="bold">继续添加</span></button>
                               <button type="button" class="btn btn-success btn-xs jtcy"><span class="bold">家庭成员&gt;&gt;</span></button> 
                           
                            </div>
                            </div>
                            <div class="hpanel">
                            <div class="panel-body" id="peixun_body" style="display:none">
                            	<!--培训经历保存-->
                     		<div class="peixun_1s">
                             	<ul >
                                	<li>2010/9-2015/7</li>
                                    <li>上海智阳科技有限公司</li>
                                    <li>工作岗位：高级软件工程师</li>
                                    <li>职责描述：负责后台接口开发</li>
                                    <li>证明人：李想</li>
                                </ul>
                                <div class="xiushan">
                               	   <a href="#" class="pe-7s-note pe-2x xiugai"></a>
                                   <a href="#" class="pe-7s-trash pe-2x demo4"></a>
                                </div>
                            </div>
                     <!--培训经历保存 end-->
                              <form role="form" class="searchs-form form-horizontal peixun_1 peixun_1_form" > 
                                    <!--培训开始时间-->
                                   <div class="form-group searchs-unit">
                                     <label for="exampleInputName4" class="col-lg-4">培训开始时间：</label>
                                     <div class="input-group date col-lg-8">
                                      <input type="text" class="form-control" id="exampleInputName2">
                                      <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                     </div>
                                 </div>
                                    <!--培训结束时间-->
                                      <div class="form-group searchs-unit">
                                     <label for="exampleInputName4" class="col-lg-4">培训结束时间：</label>
                                     <div class="input-group date col-lg-8">
                                      <input type="text" class="form-control" id="exampleInputName2">
                                      <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                     </div>
                                 </div>
                                    <!--课程名称-->
                                     <div class="form-group searchs-unit">
                                 <label for="exampleInputName4" class="col-lg-4">课程名称：</label>
                                 <div class="input-group col-lg-8">
                                  <input type="text" class="form-control" id="exampleInputName2">
                                   </div>
                               </div>
                                    <!--培训费用-->
                                     <div class="form-group searchs-unit">
                                 <label for="exampleInputName4" class="col-lg-4">培训费用：</label>
                                 <div class="input-group col-lg-8">
                                  <input type="text" class="form-control" id="exampleInputName2">
                                   </div>
                               </div>
                                   <!--培训机构-->
                                    <div class="form-group searchs-unit">
                                 <label for="exampleInputName4" class="col-lg-4">培训机构：</label>
                                 <div class="input-group col-lg-8">
                                  <input type="text" class="form-control" id="exampleInputName2">
                                   </div>
                               </div>
                                    <!--培训课时-->
                                      <div class="form-group searchs-unit">
                                 <label for="exampleInputName4" class="col-lg-4">培训课时：</label>
                                 <div class="input-group col-lg-8">
                                  <input type="text" class="form-control" id="exampleInputName2">
                                   </div>
                               </div>
                                 <!--所得证书--> 
                                   <div class="form-group searchs-unit">
                                 <label for="exampleInputName4" class="col-lg-4">所得证书：</label>
                                 <div class="input-group col-lg-8">
                                  <input type="text" class="form-control" id="exampleInputName2">
                                   </div>
                               </div>
                               <!--保存 删除-->
                               <center style=" clear:both; padding-top:30px;">
                               		<button type="button" class="btn btn-info">保存</button>
                                    <button type="button" class="btn btn-danger demo4">删除</button>
                               </center>
                              </form>
                              </div>
                              </div>
                              </div>
                              </div>
                     <!--培训经历 end-->
                           
        			 <!--家庭成员-->
                     <div class="panel-body famliy_cy" style=" display:none;">
                        <div class="row">
                        	<div style="border-bottom:1px solid #33CCFF; margin:20px; line-height:32px; overflow:hidden;">
                        	<h4 style="float:left; font-weight:normal;">家庭成员</h4>
                            <div style="float:right">
                            	<button type="button" class="btn btn-success btn-xs fm_cy"><i class="fa fa-plus"></i><span class="bold">继续添加</span></button>
                                <button type="button" class="btn btn-success btn-xs fm_cy_update" style=" display:none;"><i class="fa fa-plus"></i><span class="bold">继续添加</span></button>
                              <button type="button" class="btn btn-success btn-xs ld_ht"><span class="bold">劳动合同&gt;&gt;</span></button>  
                            </div>
                            </div>
                            <div class="hpanel">
                            <div class="panel-body" id="jiating_body">
                            	<!--培训经历保存-->
                     		<div class="famliy_1s" style="display:none;">
                             	<ul >
                                	<li>2010/9-2015/7</li>
                                    <li>上海智阳科技有限公司</li>
                                    <li>工作岗位：高级软件工程师</li>
                                    <li>职责描述：负责后台接口开发</li>
                                    <li>证明人：李想</li>
                                </ul>
                                <div class="xiushan">
                               	   <a href="#" class="pe-7s-note pe-2x xiugai"></a>
                                   <a href="#" class="pe-7s-trash pe-2x demo4"></a>
                                </div>
                            </div>
                     <!--培训经历保存 end-->
                              <form role="form" class="searchs-form form-horizontal famliy_1 famliy_1_form"> 
                                    <!--成员姓名-->
                                    <div class="form-group searchs-unit">
                                 <label for="exampleInputName4" class="col-lg-4">成员姓名：</label>
                                 <div class="input-group col-lg-8">
                                  <input type="text" class="form-control" id="exampleInputName2">
                                   </div>
                               </div>
                                    <!--与本人关系-->
                                     
                                    <div class="form-group searchs-unit">
                                   <label for="exampleInputName4" class="col-lg-4">与本人关系：</label>
                                    <div class="col-lg-8" style="padding:0;">
                                    <select class="form-control " name="account" placeholder="父母">
                                     <option>父母</option>
                                     <option>配偶</option>
                                     <option>儿子</option>
                                     <option>女儿</option>
                                     <option>其他</option>
                                    </select>
                                    </div>
                                    </div>  
                                   <!--居住城市-->
                                    <div class="form-group searchs-unit">
                                 <label for="exampleInputName4" class="col-lg-4">居住城市：</label>
                                 <div class="input-group col-lg-8">
                                  <input type="text" class="form-control" id="exampleInputName2">
                                   </div>
                               </div>
                                    <!--出生日期-->
                                     <div class="form-group searchs-unit">
                                     <label for="exampleInputName4" class="col-lg-4">出生时间：</label>
                                     <div class="input-group date col-lg-8">
                                      <input type="text" class="form-control" id="exampleInputName2">
                                      <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                     </div>
                                 </div>
                                   <!--联系电话-->
                                   <div class="form-group searchs-unit">
                                 <label for="exampleInputName4" class="col-lg-4">联系电话：</label>
                                 <div class="input-group col-lg-8">
                                  <input type="text" class="form-control" id="exampleInputName2">
                                   </div>
                               </div>
                                   <!--身份证号--> 
                                  <div class="form-group searchs-unit">
                                 <label for="exampleInputName4" class="col-lg-4">身份证号：</label>
                                 <div class="input-group col-lg-8">
                                  <input type="text" class="form-control" id="exampleInputName2">
                                   </div>
                               </div>
                                 <!--工作单位--> 
                                  <div class="form-group searchs-unit">
                                 <label for="exampleInputName4" class="col-lg-4">工作单位：</label>
                                 <div class="input-group col-lg-8">
                                  <input type="text" class="form-control" id="exampleInputName2">
                                   </div>
                               </div>
                               <!--保存 删除-->
                               <center style=" clear:both; padding-top:30px;">
                               		<button type="button" class="btn btn-info">保存</button>
                                    <button type="button" class="btn btn-danger demo4">删除</button>
                               </center>
                              </form>
                              </div>
                              </div>
                              </div>
                              </div>
                     <!--家庭成员 end-->
                     <!--劳动合同-->
                     <div class="panel-body laodong" style="display:none; ">
                     	<div class="row ">
                        	<div style="border-bottom:1px solid #33CCFF; margin:20px; line-height:32px; overflow:hidden;">
                        	<h4 style="float:left; font-weight:normal">劳动合同</h4>
                            <div style="float:right">
                            	<button type="button" class="btn btn-success btn-xs laodong_ht"><i class="fa fa-plus"></i><span class="bold">继续添加</span></button>
                                <button type="button" class="btn btn-success btn-xs laodong_ht_update" style=" display:none;"><i class="fa fa-plus"></i><span class="bold">继续添加</span></button>
                               <button type="button" class="btn btn-success btn-xs shi_yqk"><span class="bold">试用情况&gt;&gt;</span></button> 
                            
                            </div>
                            </div>
                            <div class="hpanel">
                            <div class="panel-body" id="hetong_body">
                            <!--培训经历保存-->
                     		<div class="hetongs"  style="display:none;">
                             	<ul >
                                	<li>2010/9-2015/7</li>
                                    <li>上海智阳科技有限公司</li>
                                    <li>工作岗位：高级软件工程师</li>
                                    <li>职责描述：负责后台接口开发</li>
                                    <li>证明人：李想</li>
                                </ul>
                                <div class="xiushan">
                               	   <a href="#" class="pe-7s-note pe-2x xiugai"></a>
                                   <a href="#" class="pe-7s-trash pe-2x demo4"></a>
                                </div>
                            </div>
                     <!--培训经历保存 end-->
                              <form role="form" class="searchs-form form-horizontal hetong hetong_form" > 
                                    <!--合同开始时间-->
                                    <div class="form-group searchs-unit">
                                     <label for="exampleInputName4" class="col-lg-4">合同开始时间：</label>
                                     <div class="input-group date col-lg-8">
                                      <input type="text" class="form-control" id="exampleInputName2">
                                      <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                     </div>
                                 </div>
                                    <!--合同结束时间-->
                                     <div class="form-group searchs-unit">
                                     <label for="exampleInputName4" class="col-lg-4">合同结束时间：</label>
                                     <div class="input-group date col-lg-8">
                                      <input type="text" class="form-control" id="exampleInputName2">
                                      <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                     </div>
                                 </div>
                                   <!--合同类别-->
                                    <div class="form-group searchs-unit" >
                                    	<label for="exampleInputName4" class="col-lg-4">合同类别：</label>
                                        <div class="input-group col-lg-8">
                                         <select class="form-control " name="account" placeholder="固定期限">
                                           <option>固定期限</option>
                                           <option>不固定</option>
                                          </select>
                                       </div>
                                    </div>
                                    <!--合同性质-->
                                     
                                      	<div class="form-group searchs-unit" >
                                    	<label for="exampleInputName4" class="col-lg-4">合同性质：</label>
                                        <div class="input-group  col-lg-8">
                                         <select class="form-control" name="account" placeholder="首签">
                                           <option>首签</option>
                                           <option>续签</option>
                                          </select>
                                       </div>
                                    </div>
                                 <!--试用期-->
                                    <div class="form-group searchs-unit">
                                 <label for="exampleInputName4" class="col-lg-4">试用期：</label>
                                 <div class="input-group col-lg-8">
                                  <input type="text" class="form-control" id="exampleInputName2">
                                   </div>
                               </div>
                                    <!--主体-->
                                    
                                      	<div class="form-group searchs-unit" >
                                    	<label for="exampleInputName4" class="col-lg-4">主体：</label>
                                        <div class="input-group  col-lg-8">
                                         <select class="form-control" name="account" placeholder="事业部">
                                           <option>事业部</option>
                                           <option>百度人才</option>
                                          </select>
                                       </div>
                                   </div>  
                                 <!--备注-->
                                 <div class="form-group searchs-unit">
                                    <label for="exampleInputName4" class="col-lg-4">备注：</label>
                                    <div class="col-lg-8" style="padding:0;">
                                    <textarea class="form-control" id="exampleInputName3"></textarea>
                                    </div>
                                 </div> 
                               <!--保存 删除-->
                               <center style=" clear:both; padding-top:30px;">
                               		<button type="button" class="btn btn-info">保存</button>
                                    <button type="button" class="btn btn-danger demo4">删除</button>
                               </center>
                              </form>
                              </div>  
                              </div>
                              </div>
                              </div>     
                            <!--劳动合同 end-->
                            
                        <!--试用情况-->
                        <div class="panel-body shi_yong" style=" display:none;">
                     	<div class="row">
                        	<div style="border-bottom:1px solid #33CCFF; margin:20px; line-height:32px; overflow:hidden;">
                        	<h4 style="float:left; font-weight:normal;">试用情况</h4>
                            <div style="float:right">
                            	<button type="button" class="btn btn-success btn-xs shiyong"><i class="fa fa-plus"></i><span class="bold">继续添加</span></button>
                                <button type="button" class="btn btn-success btn-xs shiyong_update" style="display:none;"><i class="fa fa-plus"></i><span class="bold">继续添加</span></button>
                               <button type="button" class="btn btn-success btn-xs ji_xkh"><span class="bold">绩效考核&gt;&gt;</span></button> 
                            
                            </div>
                            </div>
                            <div class="hpanel">
                            <div class="panel-body" id="shiyong_body">
                            <!--试用情况保存-->
                     		<div class="qing_kuangs" style="display:none;">
                             	<ul >
                                	<li>2010/9-2015/7</li>
                                    <li>上海智阳科技有限公司</li>
                                    <li>工作岗位：高级软件工程师</li>
                                    <li>职责描述：负责后台接口开发</li>
                                    <li>证明人：李想</li>
                                </ul>
                                <div class="xiushan">
                               	   <a href="#" class="pe-7s-note pe-2x xiugai"></a>
                                   <a href="#" class="pe-7s-trash pe-2x demo4"></a>
                                </div>
                            </div>
                     <!--试用情况保存 end-->
                              <form role="form" class="searchs-form form-horizontal qing_kuang qing_kuang_form" > 
                                    <!--试用开始时间-->
                                     <div class="form-group searchs-unit">
                                     <label for="exampleInputName4" class="col-lg-4">试用开始时间：</label>
                                     <div class="input-group date col-lg-8">
                                      <input type="text" class="form-control" id="exampleInputName2">
                                      <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                     </div>
                                 </div>
                                    <!--转正时间-->
                                      <div class="form-group searchs-unit">
                                     <label for="exampleInputName4" class="col-lg-4">转正时间：</label>
                                     <div class="input-group date col-lg-8">
                                      <input type="text" class="form-control" id="exampleInputName2">
                                      <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                     </div>
                                 </div>
                                   <!--转正性质-->
                                    <div class="form-group searchs-unit" >
                                    	 <label for="exampleInputName4" class="col-lg-4">转正性质：</label>
                                        <div class="input-group col-lg-8">
                                         <select class="form-control" name="account" placeholder="按期">
                                           <option>延长</option>
                                           <option>提前</option>
                                          </select>
                                       </div>
                                    </div>
                                    <!--考评结果-->
                                  <div class="form-group searchs-unit">
                                 <label for="exampleInputName4" class="col-lg-4">考评结果：</label>
                                 <div class="input-group col-lg-8">
                                  <input type="text" class="form-control" id="exampleInputName2">
                                   </div>
                               </div>
                                 
                                 <!--试用备注-->
                                 <div class="form-group searchs-unit">
                                    <label for="exampleInputName4" class="col-lg-4">试用备注：</label>
                                    <div class="col-lg-8" style="padding:0;">
                                    <textarea class="form-control" id="exampleInputName3"></textarea>
                                    </div>
                                 </div> 
                               <!--保存 删除-->
                               <center style=" clear:both; padding-top:30px;">
                               		<button type="button" class="btn btn-info">保存</button>
                                    <button type="button" class="btn btn-danger demo4">删除</button>
                               </center>
                              </form>
                              </div>
                              </div>
                              </div>
                              </div>       
                            <!--试用情况 end-->   
                             
                           <!--绩效考核-->
                           <div class="panel-body ji_xiao" style=" display:none;">
                     	<div class="row ">
                        	<div style="border-bottom:1px solid #33CCFF; margin:20px; line-height:32px; overflow:hidden;">
                        	<h4 style="float:left; font-weight:normal">绩效考核</h4>
                            <div style="float:right">
                            	<button type="button" class="btn btn-success btn-xs kaohe"><i class="fa fa-plus"></i><span class="bold">继续添加</span></button>
                                <button type="button" class="btn btn-success btn-xs kaohe_update" style="display:none;"><i class="fa fa-plus"></i><span class="bold">继续添加</span></button>
                               <button type="button" class="btn btn-success btn-xs jc_jl"><span class="bold">奖惩记录&gt;&gt;</span></button> 
                            
                            </div>
                            </div>
                             <div class="hpanel">
                            <div class="panel-body" id="kaohe_body">
                             <!--绩效考核保存-->
                     		<div class="kao_hes" style="display:none;">
                             	<ul >
                                	<li>2010/9-2015/7</li>
                                    <li>上海智阳科技有限公司</li>
                                    <li>工作岗位：高级软件工程师</li>
                                    <li>职责描述：负责后台接口开发</li>
                                    <li>证明人：李想</li>
                                </ul>
                                <div class="xiushan">
                               	   <a href="#" class="pe-7s-note pe-2x xiugai"></a>
                                   <a href="#" class="pe-7s-trash pe-2x demo4"></a>
                                </div>
                            </div>
                     <!--绩效考核保存 end-->
                              <form role="form" class="searchs-form form-horizontal kao_he kao_he_form" > 
                                    <!--考核年度-->
                                    <div class="form-group searchs-unit">
                                     <label for="exampleInputName4" class="col-lg-4">考核年度：</label>
                                     <div class="input-group date col-lg-8">
                                      <input type="text" class="form-control" id="exampleInputName2">
                                      <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                     </div>
                                 </div>
                                    <!--考核结果-->
                                     <div class="form-group searchs-unit">
                                 <label for="exampleInputName4" class="col-lg-4">考核结果：</label>
                                 <div class="input-group col-lg-8">
                                  <input type="text" class="form-control" id="exampleInputName2">
                                   </div>
                               </div>
                                   <!--考核人-->
                                    <div class="form-group searchs-unit">
                                 <label for="exampleInputName4" class="col-lg-4">考核人：</label>
                                 <div class="input-group col-lg-8">
                                  <input type="text" class="form-control" id="exampleInputName2">
                                   </div>
                               </div>
                                    <!--考核等级-->
                                    <div class="form-group searchs-unit">
                                 <label for="exampleInputName4" class="col-lg-4">考核等级：</label>
                                 <div class="input-group col-lg-8">
                                  <input type="text" class="form-control" id="exampleInputName2">
                                   </div>
                               </div>
                                 
                                 <!--考核评价-->
                                 <div class="form-group searchs-unit">
                                    <label for="exampleInputName4" class="col-lg-4">考核评价：</label>
                                    <div class="col-lg-8" style="padding:0;">
                                    <textarea class="form-control" id="exampleInputName3"></textarea>
                                    </div>
                                 </div> 
                               <!--保存 删除-->
                               <center style=" clear:both; padding-top:30px;">
                               		<button type="button" class="btn btn-info">保存</button>
                                    <button type="button" class="btn btn-danger demo4">删除</button>
                               </center>
                              </form>
                              </div>  
                              </div>
                              </div>
                              </div>     
                            <!--绩效考核 end-->  
                              
                           <!--奖惩记录-->
                           <div class="panel-body jiang_cheng" style=" display:none;">
                     	     <div class="row ">
                        	<div style="border-bottom:1px solid #33CCFF; margin:20px; line-height:32px; overflow:hidden;">
                        	<h4 style="float:left; font-weight:normal;">奖惩记录</h4>
                            <div style="float:right">
                            	<button type="button" class="btn btn-success btn-xs jilu"><i class="fa fa-plus"></i><span class="bold">继续添加</span></button>
                                <button type="button" class="btn btn-success btn-xs jilu_update" style="display:none;"><i class="fa fa-plus"></i><span class="bold">继续添加</span></button>
                               <button type="button" class="btn btn-success btn-xs kq_jl"><span class="bold">考勤记录&gt;&gt;</span></button> 
                            
                            </div>
                            </div>
                            <div class="hpanel">
                            <div class="panel-body" id="jilu_body">
                            <!--绩效考核保存-->
                     		<div class="jc_jilus" style="display:none;">
                             	<ul >
                                	<li>2010/9-2015/7</li>
                                    <li>上海智阳科技有限公司</li>
                                    <li>工作岗位：高级软件工程师</li>
                                    <li>职责描述：负责后台接口开发</li>
                                    <li>证明人：李想</li>
                                </ul>
                                <div class="xiushan">
                               	   <a href="#" class="pe-7s-note pe-2x xiugai"></a>
                                   <a href="#" class="pe-7s-trash pe-2x demo4"></a>
                                </div>
                            </div>
                     <!--绩效考核保存 end-->
                              <form role="form" class="searchs-form form-horizontal jc_jilu jc_jilu_form" > 
                                    <!--奖惩时间-->
                                    <div class="form-group searchs-unit">
                                     <label for="exampleInputName4" class="col-lg-4">奖惩时间：</label>
                                     <div class="input-group date col-lg-8">
                                      <input type="text" class="form-control" id="exampleInputName2">
                                      <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                     </div>
                                 </div>
                                    <!--奖惩类别-->
                                    <div class="form-group searchs-unit" >
                                    	<label for="exampleInputName4" class="col-lg-4">奖惩类别：</label>
                                        <div class="input-group  col-lg-8">
                                          <select class="form-control" name="account" placeholder="奖励">
                                           <option>奖励</option>
                                           <option>惩罚</option>
                                          </select>
                                    </div>
                                   </div>
                                   <!--奖惩金额-->
                                    <div class="form-group searchs-unit">
                                 <label for="exampleInputName4" class="col-lg-4">考核等级：</label>
                                 <div class="input-group col-lg-8">
                                  <input type="text" class="form-control" id="exampleInputName2">
                                   </div>
                               </div>
                                    <!--奖惩描述-->
                                     <div class="form-group searchs-unit">
                                 <label for="exampleInputName4" class="col-lg-4">考核等级：</label>
                                 <div class="input-group col-lg-8">
                                  <input type="text" class="form-control" id="exampleInputName2">
                                   </div>
                               </div>
                               <!--保存 删除-->
                               <center style=" clear:both; padding-top:30px;">
                               		<button type="button" class="btn btn-info">保存</button>
                                    <button type="button" class="btn btn-danger demo4">删除</button>
                               </center>
                              </form>
                              </div>  
                              </div>
                              </div>
                              </div>     
                            <!--奖惩记录 end-->  
                            
                          <!--考勤记录-->
                     	     <div class="row kao_qin" style=" display:none;">
                        	<div style="border-bottom:1px solid #33CCFF; margin:20px; line-height:32px; overflow:hidden;">
                        	<h4 style="float:left">考勤记录</h4>
                            <div style="float:right">
                               <button type="button" class="btn btn-success btn-xs lz_gl"><span class="bold">离职管理&gt;&gt;</span></button> 
                            </div>
                           </div>
                                <div class="panel-body" id="allcheck">
                                <table id="example2" class="table table-striped table-bordered table-hover" >
                                <thead>
                                <tr>
                                    <th>月份</th>
                                    <th>迟到次数</th>
                                    <th>早退次数</th>
                                    <th>事假天数</th>
                                    <th>病假天数</th>
                                    <th>出差天数</th>
                                    <th>平日加班(h)</th>
                                    <th>周末加班(h)</th>
                                    <th>节假日加班(h)</th>
                                    <th>调休天数</th>
                                    <th>夜班天数</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                     <td>2015-10</td>
                                     <td>20</td>
                                     <td>60</td>
                                     <td>10</td>
                                     <td>10</td>
                                     <td>10</td>
                                     <td>10</td>
                                     <td>10</td>
                                     <td>10</td>   
                                     <td>10</td>
                                     <td>10</td>
                                </tr>
                              <tr>
                                     <td>2016-01</td>
                                     <td>5</td>
                                     <td>20</td>
                                     <td>10</td>
                                     <td>10</td>
                                     <td>10</td>
                                     <td>10</td>
                                     <td>10</td>
                                     <td>10</td>   
                                     <td>10</td>
                                     <td>10</td>
                                </tr>
                                <tr>
                                     <td>2016-02</td>
                                     <td>10</td>
                                     <td>30</td>
                                     <td>10</td>
                                     <td>10</td>
                                     <td>10</td>
                                     <td>10</td>
                                     <td>10</td>
                                     <td>10</td>   
                                     <td>10</td>
                                     <td>10</td>
                                </tr>
                                </tbody>
                                </table>
                              </div>  
                      </div>     
                            <!--考勤记录 end-->  
                            
                              
                            <!--离职管理-->
                            <div class="panel-body li_zhi" style=" display:none;">
                     	     <div class="row ">
                        	<div style="border-bottom:1px solid #33CCFF; margin:20px; line-height:32px; overflow:hidden;">
                        	<h4 style="float:left; font-weight:normal">离职管理</h4>
                            <div style="float:right">
                               <button type="button" class="btn btn-success btn-xs sb_xx"><span class="bold">社保信息&gt;&gt;</span></button> 
                            </div>
                           </div>
                           <div class="hpanel">
                            <div class="panel-body">
                            <!--绩效考核保存-->
                     		<div class="lizhi_gls" style="display:none;">
                             	<ul >
                                	<li>2010/9-2015/7</li>
                                    <li>上海智阳科技有限公司</li>
                                    <li>工作岗位：高级软件工程师</li>
                                    <li>职责描述：负责后台接口开发</li>
                                    <li>证明人：李想</li>
                                </ul>
                                <div class="xiushan">
                               	   <a href="#" class="pe-7s-note pe-2x xiugai"></a>
                                   <a href="#" class="pe-7s-trash pe-2x demo4"></a>
                                </div>
                            </div>
                     <!--绩效考核保存 end-->
                         <form role="form" class="searchs-form form-horizontal lizhi_gl lizhi_gl_form"> 
                                    <!--离职时间-->
                                    <div class="form-group searchs-unit">
                                     <label for="exampleInputName4" class="col-lg-4">离职时间：</label>
                                     <div class="input-group date col-lg-8">
                                      <input type="text" class="form-control" id="exampleInputName2">
                                      <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                     </div>
                                 </div>
                                    <!--薪资结算日-->
                                     <div class="form-group searchs-unit">
                                     <label for="exampleInputName4" class="col-lg-4">薪资结算日：</label>
                                     <div class="input-group date col-lg-8">
                                      <input type="text" class="form-control" id="exampleInputName2">
                                      <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                     </div>
                                 </div>
                                   <!--离职原因-->
                                    <div class="form-group searchs-unit" >
                                    	<label for="exampleInputName4" class="col-lg-4">离职原因：</label>
                                        <div class="input-group col-lg-8">
                                         <select class="form-control" name="account" placeholder="辞职">
                                           <option>辞退</option>
                                           <option>协议离职</option>
                                          </select>
                                       </div>
                                    </div>
                                    <!--备注-->
                                     <div class="form-group searchs-unit" >
                                    	<label for="exampleInputName4" class="col-lg-4">备注：</label>
                                        <div class="input-group  col-lg-8">
                                         <input type="text" class="form-control" id="exampleInputName2">
                                       </div>
                                    </div>
                               <!--保存 删除-->
                               <center style=" clear:both; padding-top:30px;">
                               		<button type="button" class="btn btn-info">保存</button>
                                    <button type="button" class="btn btn-danger demo4">删除</button>
                               </center>
                              </form>
                              </div>
                              </div>
                              </div>
                              </div>       
                            <!--离职管理 end-->
        

    <!-- Footer-->
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

            $('#firstname').editable({
                validate: function(value) {
                    if($.trim(value) == '') return 'This field is required';
                }
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

        });

    </script>
<!--证件类型 电子版-->
<script type="text/javascript">
$('input[id=lefile]').change(function() {
$('#photoCover').val($(this).val());
});
</script>
<!--学历证明-->
<script type="text/javascript">
$('input[id=lefile1]').change(function() {
$('#photoCover1').val($(this).val());
});
</script>
<!--电子简历-->
<script type="text/javascript">
$('input[id=lefile2]').change(function() {
$('#photoCover2').val($(this).val());
});
</script>
<!--户口籍-->
<script type="text/javascript">
$('input[id=lefile3]').change(function() {
$('#photoCover3').val($(this).val());
});
</script>
<!--离职证明-->
<script type="text/javascript">
$('input[id=lefile4]').change(function() {
$('#photoCover4').val($(this).val());
});
</script>
<!--健康证明-->
<script type="text/javascript">
$('input[id=lefile5]').change(function() {
$('#photoCover5').val($(this).val());
});
</script>
<!--薪资证明-->
<script type="text/javascript">
$('input[id=lefile6]').change(function() {
$('#photoCover6').val($(this).val());
});
</script>
<!--电子版上传-->
<script type="text/javascript">
$('input[id=lefile7]').change(function() {
$('#photoCover7').val($(this).val());
});
</script>
<script>
   $(function () {

	/*var table = $('#example2').dataTable() */
    	
      	var table = $('#example6').dataTable({
    		"dom":
    		
    		"<'row'<'col-sm-12'l>>" +
    		"<'row'<'col-sm-12'tr>>" +
    		"<'row'<'col-sm-5'i><'col-sm-7'p>>",
    		
    	 	 "language": {
    			 "search": "",
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
				
    	
    }); 

</script>

<script>
   $(function () {

	/*var table = $('#example2').dataTable() */
    	
      	var table = $('#example4').dataTable({
    		"dom":
    		
    		"<'row'<'col-sm-12'l>>" +
    		"<'row'<'col-sm-12'tr>>" +
    		"<'row'<'col-sm-5'i><'col-sm-7'p>>",
    		
    	 	 "language": {
    			 "search": "",
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
				
    	
    }); 

</script>
<script>
   $(function () {

	/*var table = $('#example2').dataTable() */
    	
      	var table = $('#example5').dataTable({
    		"dom":
    		
    		"<'row'<'col-sm-12'l>>" +
    		"<'row'<'col-sm-12'tr>>" +
    		"<'row'<'col-sm-5'i><'col-sm-7'p>>",
    		
    	 	 "language": {
    			 "search": "",
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
				
    	
    }); 

</script>

<!--删除-->
<script>
    $(function () {
        $('.demo4').click(function () {
            swal({
                        title: "确定要作废该协议吗?",
                        type: "warning",
                        showCancelButton: true,
                        confirmButtonColor: "#DD6B55",
                        confirmButtonText: "是,请作废该协议!",
                        cancelButtonText: "不, 放弃此操作!",
                        closeOnConfirm: false,
                        closeOnCancel: false },
                    function (isConfirm) {
                        if (isConfirm) {
                            swal("作废成功!", "该协议已经被作废.", "success");
                        } else {
                            swal("已取消", "该协议未作废。你这逗我玩呢 :)", "error");
                        }
                    });
        });

    

    });

</script>

<script>
  $(".guanbi").click(function(){
	  
	 $("#myModal7").modal('hide');
	  
  })
</script>
 <!--订单信息与订单费用信息-->
 <script>
 
 $(function(){
	 
	$(".dingdan_one").click(function(){
		
		$(".dingdan_two").removeClass("btn-info").addClass("information_button");
		$(".dingdan_one").removeClass("information_button").addClass("btn-info");
		$(".order_infor").show();
		$(".order_infor_two").hide();
		
	}) 
	 
 })
 
 </script>
 <script>
 $(function(){
	 
	$(".dingdan_two").click(function(){
		
		$(".dingdan_two").removeClass("information_button").addClass("btn-info");
		$(".dingdan_one").removeClass("btn-info").addClass("information_button");
		$(".order_infor").hide();
		$(".order_infor_two").show();
		
	}) 
	 
 })
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
			window.open(moban);
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
      
<!--去除升序-->
<!--<script>
 $(function (){
	 
	 $(".delete").removeClass("sorting_asc").removeClass("sorting_desc");
	 
 })
</script>-->

<!--服务月与账单月切换-->
<script>
	$(function (){
		
		$(".zhangdan").click(function(){
			
			$(".fuwu").show();
			$(".zhangdan").hide();
			$(".panel-body_third").hide();
			$(".panel-body_four").show();
			
		});
		
		$(".fuwu").click(function(){
			
			$(".zhangdan").show();
			$(".fuwu").hide();
			$(".panel-body_third").show();
			$(".panel-body_four").hide();
			
		});
		
	})
	
</script>
</body>
</html>