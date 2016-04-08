<%@ page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html>
<head>
	<base href="${pageContext.request.contextPath}/">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <!-- Page title -->
    <title>未开通首页</title>

    <!-- Place favicon.ico and apple-touch-icon.png in the root directory -->
    <!--<link rel="shortcut icon" type="image/ico" href="favicon.ico" />-->

   <link rel="stylesheet" href="static/bootstrap/vendor/fontawesome/css/font-awesome.css" />
    <link rel="stylesheet" href="static/bootstrap/vendor/metisMenu/dist/metisMenu.css" />
    <link rel="stylesheet" href="static/bootstrap/vendor/animate.css/animate.css" />
    <link rel="stylesheet" href="static/bootstrap/vendor/bootstrap/dist/css/bootstrap.css" />

    <!-- App styles -->
    <link rel="stylesheet" href="static/bootstrap/fonts/pe-icon-7-stroke/css/pe-icon-7-stroke.css" />
    <link rel="stylesheet" href="static/bootstrap/fonts/pe-icon-7-stroke/css/helper.css" />
    <link rel="stylesheet" href="static/bootstrap/styles/style.css">
   <!-- <link rel="stylesheet" href="css/user.css">-->
    <link rel="stylesheet" href="static/css/user_bata.css">
</head>
<body >

<!-- Simple splash screen-->

<div class="splash"><!-- 小屏时出现-->
 <div class="color-line"></div><div class="splash-title">
<h1>Jabava V1.0 </h1>
<p>属于你的专业人事专员</p>
<img src="images/loading-bars.svg" width="64" height="64" /> </div> </div>

<!--[if lt IE 7]>
<p class="alert alert-danger">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
<![endif]-->

<!-- Header -->
<div id="header">
    <div class="color-line">
    </div>
    <div id="logo" class="light-version">
        <span>
            Jabava logo
        </span>
    </div>
    <nav role="navigation">
        <div class="header-link hide-menu"><i class="fa fa-bars"></i></div>
        <!-- 小屏时出现-->
        <div class="small-logo">
            <span class="text-primary">Jabava</span>
        </div>
        <form role="search" class="navbar-form-custom" method="post" action="#">
            <div class="form-group">
                <input type="text" placeholder="搜索员工" class="form-control" name="search">
            </div>
        </form>

        <div class="mobile-menu"><!-- 小屏时出现-->
            <button type="button" class="navbar-toggle mobile-menu-toggle" data-toggle="collapse" data-target="#mobile-collapse">
                <i class="fa fa-chevron-down"></i>
            </button>
            <div class="collapse mobile-navbar" id="mobile-collapse">
                <ul class="nav navbar-nav">
                    <li>
                        <a class="" href="login.html">登入</a>
                    </li>
                    <li>
                        <a class="" href="login.html">登出</a>
                    </li>
                    <li>
                        <a class="" href="profile.html">我的</a>
                    </li>
                </ul>
            </div>
        </div>
        <div class="navbar-right">
            <ul class="nav navbar-nav no-borders label-menu-corner">
    
                <li class="dropdown">
                    <a class="dropdown-toggle" href="#" data-toggle="dropdown">
                        <i class="pe-7s-speaker"></i>
                         <span class="label label-success">4</span>
                    </a>
                    <ul class="dropdown-menu hdropdown notification animated flipInX">
                        <li>
                            <a>
                                <span class="label label-success">NEW</span> It is a long established.
                            </a>
                        </li>
                        <li>
                            <a>
                                <span class="label label-warning">WAR</span> There are many variations.
                            </a>
                        </li>
                        <li>
                            <a>
                                <span class="label label-danger">ERR</span> Contrary to popular belief.
                            </a>
                        </li>
                        <li class="summary"><a href="#">See all notifications</a></li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a class="dropdown-toggle" href="#" data-toggle="dropdown">
                        <i class="pe-7s-keypad"></i>
                    </a>

                    <div class="dropdown-menu hdropdown bigmenu animated flipInX">
                        <table>
                            <tbody>
                            <tr>
                                <td>
                                    <a href="to_index?jump=1">
                                        <i class="pe pe-7s-portfolio text-info"></i>
                                        <h5>开通服务</h5>
                                    </a>
                                </td>
                                <td>
                                    <a href="to_index?jump=1">
                                        <i class="pe pe-7s-add-user text-info"></i>
                                        <h5>添加员工</h5>
                                    </a>
                                </td>
                                <td>
                                    <a href="to_index?jump=1">
                                        <i class="pe pe-7s-monitor text-success"></i>
                                        <h5>社保计算器</h5>
                                    </a>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <a href="to_index?jump=1">
                                        <i class="pe pe-7s-config text-info"></i>
                                        <h5>系统设置</h5>
                                    </a>
                                </td>
                                <td>
                                    
                                </td>
                                <td>
                                  
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </li>
                <li class="dropdown">
                    <a class="dropdown-toggle label-menu-corner" href="#" data-toggle="dropdown" title="我的名字">
                        <i class="pe-7s-user"></i>
                    </a>
                    <ul class="dropdown-menu hdropdown animated flipInX">
                        <li>
                            <a>
                               <h5> 我的账户</h5>
                            </a>
                        </li>
                        <li>
                            <a>
                               <h5> 修改联系人</h5>
                            </a>
                        </li>
                        <li>
                            <a>
                              <h5> 修改密码</h5>
                            </a>
                        </li>
                        <li>
                            <a>
                              <h5> 退出</h5>
                            </a>
                        </li>                        
                    </ul>
                </li>                
                <!-- <li>
                    <a href="#" id="sidebar" class="right-sidebar-toggle">
                        <i class="pe-7s-upload pe-7s-news-paper"></i>
                    </a>
                </li> -->
                <li class="dropdown">
                    <a href="login.html">
                        <i class="pe-7s-upload pe-rotate-90"></i>
                    </a>
                </li>
            </ul>
        </div>
    </nav>
</div>

<!-- Navigation -->
<aside id="menu">
    <div id="navigation">
    
        <div class="profile-picture">
            <a href="to_index?jump=1">
                <img src="images/profile.jpg" class="img-circle m-b" alt="logo">
            </a>

            <div class="stats-label text-color">
                <span class="font-extra-bold font-uppercase">智阳网络技术（上海） 有限公司</span>
                <div id="sparkline1" class="small-chart m-t-sm"></div>
                <div>
                    <h4 class="font-extra-bold m-b-xs">
                        在职员工xx位
                    </h4>
                    <small class="text-muted">*.本月起北京进行调基。</small>
                </div>
            </div>
        </div>
      
<ul class="nav" id="side-menu">
             <li class="active">
                <a href="#"> <span class="nav-label">参照模板</span> <span class="fa arrow"></span> </a>
                <ul class="nav nav-second-level">
                    <li><a href="to_index?jump=1">空框架带导航条</a></li>
                    <li><a href="1.frame_without_nav.html">空框架不带导航条</a></li>
                    <li><a href="1.datatable.html">列表页</a></li>

                </ul>
            </li>
            <li >
                <a href="#"> <span class="nav-label">员工信息</span> <span class="fa arrow"></span> </a>
                <ul class="nav nav-second-level">
                    <li><a href="#">花名册</a></li>
                    <li><a href="#">员工添加</a></li>
                    <li><a href="#">批量更新</a></li>
                </ul>
            </li>
            <li>
                <a href="#"> <span class="nav-label">组织架构</span> </a>
            </li>
            <li>
                <a href="#"><span class="nav-label">人事外包服务</span><span class="fa arrow"></span> </a>
                <ul class="nav nav-second-level">
                    <li><a href="#">申请开通</a></li>
                    <li><a href="#">协议查询</a></li>
                    <li><a href="#">上传增减表</a></li>
                </ul>
            </li>
            <li>
                <a href="#"><span class="nav-label">考勤管理</span></a>
            </li>
            <li>
                <a href="computer.html"><span class="nav-label">社保计算器</span></a>
            </li>
            <li>
                <a href="Information.html"><span class="nav-label">信息公告</span></a>
            </li>
            <li>
                <a href="#"><span class="nav-label">用户管理</span><span class="fa arrow"></span> </a>
                <ul class="nav nav-second-level">
                    <li><a href="1.user_magement.html">用户管理</a></li>
                    <li><a href="1.role_magement.html">角色管理</a></li>
                </ul>
            </li>
            <li>
                <a href="#"><span class="nav-label">系统管理</span><span class="fa arrow"></span> </a>
                <ul class="nav nav-second-level">
                    <li><a href="system.html">系统日志</a></li>
                    <li><a href="database.html">基础数据</a></li>
                    <li><a href="1.system_parameter_deploy.html">参数设置</a></li>
                </ul>
            </li>
        </ul>
        
  
    </div>
</aside>

<!-- Main Wrapper -->
<div id="wrapper">
<div class="normalheader transition animated fadeIn small-header">
    <div class="hpanel">
        <div class="panel-body">
            <div id="hbreadcrumb" class="pull-right m-t-lg">
                <ol class="hbreadcrumb breadcrumb">
                    <li><a href="to_index?jump=1">首页</a></li>
                    <li>
                        <span>范例</span>
                    </li>
                    <li class="active">
                        <span>空框架</span>
                    </li>
                </ol>
            </div>
            <h2 class="font-light m-b-xs">
                首页
            </h2>
            <small>待定</small>
        </div>
    </div>
</div>
<!--放主要内容-->
<div class="content animate-panel">
        <div class="row">
     <div class="row">
    <div class="col-lg-4">
        <div class="hpanel">
            <div class="panel-heading">
                开通协议
            </div>
            <div class="panel-body none_borders">
               <p class="kaitong">
               	 全国一流人力资源服务商联合推出的一款标准化社会保险和住房公积金全程代理服务。为您提供最省心，最安心，最放心的社保服务。
                 全国一流人力资源服务商联合推出的一款标准化社会保险和住房公积金全程代理服务。为您提供最省心，最安心，最放心的社保服务。
               </p>
               <div  class="div_right_s">
                	<a href="#">开通协议&gt;&gt;</a><!--链接到申请开通-->
                </div>
            </div>
        </div>
    </div>
    
       <div class="col-lg-4">
                <div class="hpanel stats">
                	<div class="panel-heading">
                       公司信息
                    </div>
                    <div class="panel-body h-200  none_borders">
                        <div class="stats-title">
                            <h5><a href="#">上海华润科技有限公司</a><span>在职人数204</span></h5><!--链接到完善企业信息-->
                        </div>
                        <div class="stats-icon ">
                            <i class="pe-7s-shuffle fa-4x"></i>
                        </div>
                        <div class="clearfix"></div>
                        <div class="flot-chart">
                            <div class="flot-chart-content" id="flot-pie-chart" style="height: 110px"></div>
                            <div class="div_rights">
                                <a href="#">完善企业信息&gt;&gt;</a><!--链接到完善企业信息-->
                            </div>
                </div>
                        </div>
                    </div>
                
            </div>
    
    
    <div class="col-lg-4">
        <div class="hpanel">
            <div class="panel-heading">
               员工提醒
            </div>
            <div class="panel-body  none_borders">
                <h5>友情提醒，点击具体数字时可进行相应处理哦！</h5>
                <ol>
                	<li class="green_birthday"><a href="#" class="green_birthday">3人</a>今日生日</li><!--都链接到花名册-->
                    <li><a href="#">4人</a>明日生日</li>
                    <li><a href="#">3人</a>10天内转正</li>
                    <li><a href="#">1人</a>50天内合同到期</li>
                </ol>
            </div>
        </div>
    </div>
</div>  
               <!--小-->
            <div class="row">
            <div class="col-lg-4">
        <div class="hpanel">
            <div class="panel-heading">
                上传增减变表
            </div>
            <div class="panel-body border_borders">
                <p>增员、减员、变更服务内容，请先上传增减 变表！</p>
                <div  class="div_right">
                	<a href="#">上传增减变表&gt;&gt;</a><!--链接到上传增减变表-->
                </div>
            </div>
        </div>
    </div>
    <div class="col-lg-4">
        <div class="hpanel">
            <div class="panel-heading">
               订单
            </div>
            <div class="panel-body border_borders">
               <p>您共创建3条订单！</p>
                <div  class="div_right">
                	<a href="#">点击查看&gt;&gt;</a><!--链接到订单-->
                </div>
            </div>
        </div>
    </div>
    <div class="col-lg-4">
        <div class="hpanel">
            <div class="panel-heading">
            	账单
            </div>
            <div class="panel-body border_borders">
                <p>您的账单已付款</p>
                <div  class="div_right">
                	<a href="#">点击查看&gt;&gt;</a><!--链接到账单-->
                </div>
            </div>
        </div>
    </div>
    
    <div class="row">
    <div class="col-lg-12">
        <div class="hpanel border_borders margin_hpanel">
            <div class="panel-body float-e-margins">
                &nbsp;

                </div>
            </div>
        </div>
    </div>

</div>
   </div>
    <!-- Footer-->
    <footer class="footer">
        <span class="pull-right">
            智阳网络技术（上海）有限公司
        </span>
        Company 2015-2020
    </footer>

</div>

<script src="static/bootstrap/vendor/jquery/dist/jquery.min.js"></script>
<script src="static/bootstrap/vendor/jquery-ui/jquery-ui.min.js"></script>
<script src="static/bootstrap/vendor/slimScroll/jquery.slimscroll.min.js"></script>
<script src="static/bootstrap/vendor/bootstrap/dist/js/bootstrap.min.js"></script>
<script src="static/bootstrap/vendor/jquery-flot/jquery.flot.js"></script>
<script src="static/js/jquery.flotsss.js"></script>
<script src="static/bootstrap/vendor/jquery-flot/jquery.flot.resize.js"></script>
<script src="static/bootstrap/vendor/jquery-flot/jquery.flot.pie.js"></script>
<script src="static/bootstrap/vendor/flot.curvedlines/curvedLines.js"></script>
<script src="static/bootstrap/vendor/jquery.flot.spline/index.js"></script>
<script src="static/bootstrap/vendor/metisMenu/dist/metisMenu.min.js"></script>
<script src="static/bootstrap/vendor/iCheck/icheck.min.js"></script>
<script src="static/bootstrap/vendor/peity/jquery.peity.min.js"></script>
<script src="static/bootstrap/vendor/sparkline/index.js"></script>
<!-- App scripts -->
<script src="static/bootstrap/scripts/charts.js"></script>
<!-- for datatable -->
<script src="static/bootstrap/vendor/datatables/media/js/jquery.dataTables.min.js"></script>
<script src="static/bootstrap/vendor/datatables_plugins/integration/bootstrap/3/dataTables.bootstrap.min.js"></script>
<!-- alert -->
<!--<script src="vendor/sweetalert/lib/sweet-alert.min.js"></script>-->

<!-- App scripts -->
<script src="static/bootstrap/scripts/homer.js"></script>
<script>

    $(function () {

       
         /* Pie Chart Data
         */
        var pieChartData = [
            { label: "公司员工", data: 80, color: "#62CB31", },
            { label: "本月入职", data: 5, color: "#01FF00", },
            { label: "本月离职", data: 8, color: "#FF0000", },
            { label: "本月入职", data: 7, color: "#C00000", }
        ];

        /**
         * Pie Chart Options
         */
        var pieChartOptions = {
            series: {
                pie: {
                    show: true
                }
            },
            grid: {
                hoverable: true
            },
            tooltip: true,
            tooltipOpts: {
                content: "%p.0%, %s", // show percentages, rounding to 2 decimal places
                shifts: {
                    x: 20,
                    y: 0
                },
                defaultTheme: false
            }
        };

        $.plot($("#flot-pie-chart"), pieChartData, pieChartOptions);
    });

</script>   

</body>
</html>