<%@page import="com.jabava.utils.RequestUtil"%>
<%@page import="com.jabava.pojo.manage.EhrUser"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = "//"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	EhrUser user = RequestUtil.getLoginUser(request);
	Long orgLoginId = (Long)session.getAttribute("orgLoginId");
	   
%>
<!-- menu -->
<aside id="menu">
	<div id="navigation">

		<div class="profile-picture">
			<img id="_ShowCompanyLogo" src="static/bootstrap/images/touxiang.png" class="img-circle m-b-md" alt="avatar">

			<div class="stats-label text-color">
				<span id="_ShowCompanyName" class="font-extra-bold">智阳 · 第一人力</span>
				<!-- <div id="sparkline1" class="small-chart m-t-sm"></div> -->
				<p class="text-center m-t-sm"><i id="auth-status" class="icon-vip"></i> <span>已认证</span></p>
				<!-- <p class="text-center m-t-md m-b-sm">余额积分：<span>0</span></p>
				<div class="text-center">
					<button type="button" class="btn btn-info btn-xs">充值</button>
				</div> -->
			</div>
		</div>
		<ul class="nav" id="side-menu">
			<!-- <li class="no-borders">
			<a href="//www.hrofirst.com/" target="_blank" style="padding-top:20px; padding-bottom: 0">
			<img src="static/img/HRO_LOGO_Navbar.png" alt="">
			</a>
			</li> -->
		   <!--  <li class="no-borders">  
			<a href="weixinnews/weixinnewsMain" target="_blank" style="padding-top:20px; padding-bottom: 0; color: #38a1bd">
			<img src="static/img/qxb_icon.png" alt="" class="m-r-sm">
			<strong class="nav-label">资讯管理</strong>
			</a>
			</li> -->
			<!-- <li class="no-borders">
			<a href="//www.hrofirst.com/shebao" target="_blank" style="padding-top:20px; padding-bottom: 0; color: #38a1bd">
			<img src="static/img/qxb_icon.png" alt="" class="m-r-sm">
			<strong class="nav-label">全心保</strong>
			</a>
			</li>
			<li class="no-borders">
			<a href="//www.hrofirst.com" target="_blank" style="padding-top:20px; padding-bottom: 0; color: #38a1bd">
			<img src="static/img/HRMIAO.png" alt="" class="m-r-sm">
			<strong class="nav-label">HRMIAO市场</strong>
			</a></li>
			<li class="no-borders">
			<a href="//shopzy.ezhiyang.com/shop/" target="_blank" style="padding-top:20px; padding-bottom: 0; color: #38a1bd">
			<img src="static/img/Fuli.png" alt="" class="fa m-r-sm">
			<strong class="nav-label">睿福利360</strong>
			</a></li>
			<li class="no-borders">
			<a href="//www.hrofirst.com/www/article/articleList_web.jhtml" target="_blank" style="padding-top:20px; padding-bottom: 0; color: #38a1bd">
			<img src="static/img/ZY_Information.png" alt="" class="fa m-r-sm">
			<strong class="nav-label">智阳资讯</strong>
			</a>
			</li> -->
		</ul>
	</div>
</aside>
<script type="text/javascript">
	$(document).ready(function() {
		$.ajax({
			url: 'menuTree1',
			dataType: 'xml',
			success: function(data){
			 
				var menuhtml="";
				var rnode=$(data).find('tree').children()[0];
				
				$(rnode).children().each(function (i){
					//var sno=i+1;
					if( $(this).children().length == 1 && $(this).children()[0].tagName == 'userdata'  ){
						//只有一级菜单
						var urlx=$(this).children().first().text();
						if(urlx.indexOf(':disabled') != -1 ){
							// :disabled
							menuhtml+='<li><a href="javascript://" class="disabled">'+$(this).attr('text')+'</a></li>';
						}else{
							menuhtml+='<li><a href="javascript:tonclick(\''+urlx+'\');">'+$(this).attr('text')+'</a></li>';
						}
					}else{
						menuhtml+='<li><a class="parentTree"><span class="nav-label">'+$(this).attr('text')+'</span><span class="fa arrow"></span></a>';
						menuhtml+='<ul class="nav nav-second-level collapse">';
						$(this).children().each(function (k){
							var urlx=$(this).children().first().text();
							menuhtml+='<li><a href="javascript:tonclick(\''+urlx+'\');">'+$(this).attr('text')+'</a></li>';
						});

						menuhtml+='</ul></li>';
					}
				});
				
				$('#side-menu').prepend(menuhtml);
				
				init();
			}
		});
		
		var companyLogo = '${sessionScope.LOGIN_USER.company.companyLogo}';
		$('#_ShowCompanyName').text('${sessionScope.LOGIN_USER.company.companyName}');
		if(companyLogo != ''){
			//$('#_ShowCompanyLogo').attr('src','${pageContext.request.contextPath}/static/upload/' + companyLogo);
			$('#_ShowCompanyLogo').attr('src', companyLogo);
		}
		//$('#_ShowCompanyEmployees').text('在职员工' + data.employeeCount + '位');
		
		<%
			if(user.getCompany().getCertificateStatus() == null || user.getCompany().getCertificateStatus() != 1){
		%>
		syncCertificateStatus();
		<%
			}
		%>

	})
	
	function tonclick(url) {
	    if(url!=null) {
	    	var pos=url.indexOf("/pages/");
	    	if(pos>=0)
	    	    url="/gdshrreports"+url.substring(pos)+"?companyCode="+companyCode;
	    	if(url.indexOf("http") > -1){//如果url 包含http
	    		url = url.substring(url.indexOf("http"));
    		    if(url.indexOf("storeIndex.jhtml") > -1){//HRO新品
	    			url +='${sessionScope.RECEIVE_ORG_ID}';
	    		}		    	
	    		window.open(url);
	    	}else if(url.indexOf("tax_calculator") > -1){
	    		window.open(url);
	    	}else{
	    		window.location.href = url;
	    	}
	    }
	}
	
	function init(){
		var currentFunc = "${sessionScope.currentFunc}";
		//var currentFunc = '';
		if(currentFunc != "" && currentFunc != null){
			$(".parentTree").each(function(){
				if($(this).text() == currentFunc){
					var li = $(this).parent();
					$(".parentTree").parent().not(li).removeClass("active");
					$(".nav-second-level").not(li.find(".nav-second-level")).removeClass("in");
					li.toggleClass("active");
					li.find(".nav-second-level").toggleClass("in");
				}
			});
		} 
		$(".parentTree").click(function(){
			$.ajax({
				url: 'menuTree2',
				type: "POST",
				dataType: 'text',
				data: {"currentFunc":$(this).text()},
				success: function(data) {
					
				}
	    	});
			 var li = $(this).parent();
				$(".parentTree").parent().not(li).removeClass("active");
				$(".nav-second-level").not(li.find(".nav-second-level")).removeClass("in");
				li.toggleClass("active");
				li.find(".nav-second-level").toggleClass("in");
		});
	}
	
	function syncCertificateStatus(){
		//显示认证按钮、、、
		$('#auth-status').removeClass('icon-vip').addClass('icon-noVip').siblings('span').text('未认证');
		$.ajax({
			url: "syncCertificateStatus",
			type : "POST",
			dataType:'json',
			error: function(XMLHttpRequest, textStatus, errorThrown){
				alert(textStatus);
			},
			success: function(data){
				if(data.success){
					if(data.certificateStatus == 1){
						//隐藏认证按钮、、、
						$('#auth-status').removeClass('icon-noVip').addClass('icon-vip').siblings('span').text('已认证');
					}else if(data.certificateStatus == 2){
						$('#auth-status').siblings('span').text('认证中');
					}
				}
			}
		});
	}
</script>

