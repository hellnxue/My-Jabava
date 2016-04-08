<%@ page contentType="text/html; charset=utf-8" %>
<%@page import="com.jabava.utils.JabavaPropertyCofigurer"%>
<%@page import="com.jabava.utils.RequestUtil"%>
<%
	String logoutUrl = null;
	if("1".equals(JabavaPropertyCofigurer.getSsoSwitch())){
		logoutUrl = "/j_spring_cas_security_logout";
	}else{
		//logoutUrl = "/logout";
		logoutUrl = "/j_spring_security_logout";
	}
%>
<!-- Header -->
    <!-- App styles -->
    <link rel="stylesheet" href="static/js/slick/slick.css">
    <link rel="stylesheet" href="static/js/slick/slick-theme.css">
    <link rel="stylesheet" href="static/css/user.css">
    <link rel="stylesheet" href="static/css/user_bata.css">
    <link rel="stylesheet" href="static/css/app.jabava.css">
    <style type="text/css">
      #logo {
        background-image: url(static/img/jabava-logo.png);
        background-position: center center;
        background-repeat: no-repeat;
      }
    </style>
<script src="static/bootstrap/vendor/jquery/dist/jquery.min.js"></script>
<script type="text/javascript">
$(function(){
	$.ajaxSetup({
        dataFilter : function(data, type){
            //console.log("data:" + data);
            if(data.indexOf("AjaxAuthFailed") != -1){
            //if(data == "AjaxAuthFailed"){
            	window.location.reload();
            	return false;
            }else{
                return data;
            }
        }
        //complete : function(XMLHttpRequest, textStatus) {
        //    var sessionstatus = XMLHttpRequest.getResponseHeader("sessionstatus");
        //    if (sessionstatus == "timeout") {
        //        window.location.replace("login.html");
        //    }
        //}
    });	

	//显示右上角的公告列表 只显示五条
	$('#dropdownInformation').click(function () {
		$("#informationList").html('');
		$.ajax({
			type : 'post',
			url : "announcement/informationList",			
			async : false,
			dataType:'json',
			success : function(result) {	
				if(result){
					for(var i=0;i<result.length;i++){
						$("#informationList").append("<li onclick='showDetail("+ result[i].informationId+")'><a >"+result[i].informationTitle+"</a></li>");
					}
					<%
						//if(RequestUtil.hasRoleOfName("企业管理员")){
						if(RequestUtil.hasRoleOfName("HR")){
					%>
						$("#informationList").append("<li class='summary'><a href='announcement/announcement_list'>查看所有消息</a></li>");
					<%
						}
					%>
				}
			}
		});
	});
	
	//获取未读取得数量
	/* $.ajax({
			type : 'post',
			url : "announcement/getListSize",			
			async : false,
			success : function(result) {
				if(result>0){
					$("#listSize").html(result);	
				}	
			}
		});
	*/
});

function formatDate2(time){
	//var JsonDateValue = new Date(time);	
	//var date = JsonDateValue.getFullYear()+"-"+(JsonDateValue.getMonth()+1)+"-"+JsonDateValue.getDate();
	//return date
	if(time && time.length >= 10){
		return time.substring(0,10);
	}else{
		return time;
	}
}
//显示详细信息窗口
function  showDetail(information_id){
	
	 $.ajax({
			type : 'post',
			url : "announcement/showUpdate",
			data : {informationId:information_id},
			async : false,
			dataType: "json",
			success : function(result) {
				$("#showInformationId").val(result.informationId);		
			 	$("#showInformationTitle").text(result.informationTitle);			 	
			 	 $("#showStartDate").text(formatDate2(result.startDate));				 	
			 	$("#showFinishDate").text(formatDate2(result.finishDate));

			 	$("#showInformationContent").text(result.informationContent);
			 	$("#showModal").modal('show');
			 	//获取未读取得数量
				 $.ajax({
						type : 'post',
						url : "announcement/getListSize",			
						async : false,
						success : function(result) {
							if(result>0){
								$("#listSize").html(result);	
								}
						 	
						}
						
						
					});
			}
			
			
		});
}
</script>
<div id="header">
    <div class="color-line">
    </div>
    <div id="logo" class="light-version">
        <span class="sr-only">
            Jabava logo
        </span>
    </div>
    <nav role="navigation">
        <div class="header-link hide-menu"><i class="fa fa-bars text-info"></i></div>
        <!-- 小屏时出现-->
        <div class="small-logo">
            <span class="text-primary">Jabava</span>
        </div>
        <form role="search" class="navbar-form-custom hidden" method="post" action="#">
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
    			
                <!--<li class="dropdown" id="dropdownInformation">
                    <a class="dropdown-toggle" href="#" data-toggle="dropdown">
                        <i class="pe-7s-speaker"></i>
                         <span class="label label-success" id="listSize" > </span>
                    </a>
                    <ul class="dropdown-menu hdropdown notification animated flipInX" id="informationList">
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
                </li>-->
                
                <!-- <li class="dropdown">
                    <a class="dropdown-toggle" href="#" data-toggle="dropdown">
                        <i class="pe-7s-keypad"></i>
                    </a>

                    <div class="dropdown-menu hdropdown bigmenu animated flipInX">
                        <table>
                            <tbody>
                            <tr>
                                <td>
                                    <a href="client/outsourcing">
                                        <i class="pe pe-7s-albums text-info"></i>
                                        <h5>开通服务</h5>
                                    </a>
                                </td>
                                <td>
                                    <a href="tools/calculator">
                                        <i class="pe pe-7s-monitor text-success"></i>
                                        <h5>社保计算器</h5>
                                    </a>
                                </td>
                                <td>
                                    <a href="system/searchBaseData">
                                        <i class="pe pe-7s-config text-info"></i>
                                        <h5>系统设置</h5>
                                    </a>
                                </td>
                            </tr>
                            <tr class="sr-only">
                                <td>
                                    <a href="employee/to_addPerson.do">
                                        <i class="pe pe-7s-add-user text-info"></i>
                                        <h5>添加员工</h5>
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
                </li> -->
                <li class="dropdown">
                    <a class="dropdown-toggle label-menu-corner" href="#" data-toggle="dropdown" title="我的名字">
                        <i class="pe-7s-user"></i>
                    </a>
                    <ul class="dropdown-menu hdropdown animated flipInX">
                        <li class="sr-only">
                            <a>
                               <h5> 我的账户</h5>
                            </a>
                        </li>
                        <li class="sr-only">
                            <a>
                               <h5> 修改联系人</h5>
                            </a>
                        </li>
                        <li class="sr-only">
                            <a>
                              <h5> 修改密码</h5>
                            </a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}<%=logoutUrl%>">
                              <h5>退出</h5>
                            </a>
                        </li>                        
                    </ul>
                </li>                
               <!--  <li>
                   <a href="#" id="sidebar" class="right-sidebar-toggle">
                       <i class="pe-7s-upload pe-7s-news-paper"></i>
                   </a>
               </li>
               <li class="dropdown">
                   <a href="login.html">
                       <i class="pe-7s-upload pe-rotate-90"></i>
                   </a>
               </li> -->
            </ul>
        </div>
    </nav>
</div>
<!-- 公告详细信息弹窗  -->
  <div class="modal fade hmodal-success form-row" id="showModal" tabindex="-1" role="dialog"  aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="color-line"></div>
                            <div class="modal-header">
                             <div class="row">
                             <h4 style="border-bottom:1px solid #00CCFF; margin-bottom:20px; height:25px;">查看公告</h4>
                              <div class="col-sm-11">
                                <form role="form" id="showInformationForm" class=" form-horizontal">
                                  <input type="hidden" id="showInformationId" name="informationId" />
                                  <div class="form-group">
                                    <label for="" class="control-label col-md-3 col-lg-3">信息标题：</label>
                                    <div class="col-lg-9">
                                      <p class="form-control-static" id="showInformationTitle"></p>
                                    </div>
                                  </div>
                                  <div class="form-group">
                                    <!--开始时间-->
                                    <div class="col-md-6 col-lg-6">
                                      <label class="control-label col-md-6 col-lg-6">开始时间：</label>
                                      <div class="col-md-6 col-lg-6">
                                        <p class="form-control-static" id="showStartDate"></p>
                                      </div>
                                    </div>
                                    <!--结束时间-->
                                    <div class="col-md-6 col-lg-6" >
                                      <label class="control-label col-md-6 col-lg-6">结束时间：</label>
                                      <div class="col-md-6 col-lg-6">
                                        <p class="form-control-static" id="showFinishDate"></p>
                                      </div>
                                    </div>
                                  </div>

                                  <div class="form-group">
                                    <label for="" class="control-label col-lg-3">正文：</label>
                                    <div class="col-lg-8">
                                      <p class="form-control-static" id="showInformationContent"></p>
                                    </div>
                                  </div>                                   
                                </form>
                              </div>
                            </div>   
                        </div>
                    </div>
                </div>
                </div>
