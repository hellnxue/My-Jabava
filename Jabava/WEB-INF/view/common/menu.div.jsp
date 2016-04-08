<%@page import="com.jabava.pojo.manage.EhrUser"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!-- menu -->
<aside id="menu">
	<div id="navigation">

		<div class="profile-picture">
			<img id="_ShowCompanyLogo"
				src="static/bootstrap/images/touxiang.jpg" class="img-circle m-b"
				alt="logo">

			<div class="stats-label text-color">
				<span id="_ShowCompanyName" class="font-extra-bold font-uppercase">智阳网络技术（上海） 有限公司</span>
				<!-- <div id="sparkline1" class="small-chart m-t-sm"></div> -->
				<div>
					<!-- <h4 id="_ShowCompanyEmployees" class="font-extra-bold m-b-xs h4-color">在职员工xx位</h4>
					<small class="text-muted">*.本月起北京进行调基。</small> -->
				</div>
			</div>
		</div>
		<ul class="nav" id="side-menu">


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
						var sno=i+1;
						menuhtml+='<li><a class="parentTree"><span class="nav-label">'+$(this).attr('text')+'</span><span class="fa arrow"></span></a>';
						menuhtml+='<ul class="nav nav-second-level">';
						$(this).children().each(function (k){
							var urlx=$(this).children().first().text();
							menuhtml+='<li><a href="javascript:tonclick(\''+urlx+'\');">'+$(this).attr('text')+'</a></li>';
						});
						menuhtml+='</ul></li>';
					});
					$('#side-menu').append(menuhtml);
				 
				
				init();
			}
		});
		
		var companyLogo = '${sessionScope.LOGIN_USER.company.companyLogo}';
		$('#_ShowCompanyName').text('${sessionScope.LOGIN_USER.company.companyName}');
		if(companyLogo != ''){
			$('#_ShowCompanyLogo').attr('src','${pageContext.request.contextPath}/static/upload/' + companyLogo);
		}
		//$('#_ShowCompanyEmployees').text('在职员工' + data.employeeCount + '位');
	})
	
	function tonclick(url)
	{
	    if(url!=null)
	    {
	    	var pos=url.indexOf("/pages/");
	    	if(pos>=0)
	    	    url="/gdshrreports"+url.substring(pos)+"?companyCode="+companyCode;
	    	window.location.href = url;
	    }
	}
	
	function init(){
		var currentFunc = "${currentFunc}";
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
</script>

