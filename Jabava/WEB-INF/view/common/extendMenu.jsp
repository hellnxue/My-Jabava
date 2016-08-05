<%@page import="com.jabava.utils.RequestUtil"%>
<%@page import="com.jabava.pojo.manage.EhrUser"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = "//" + request.getServerName() + ":" + request.getServerPort() + path;
	EhrUser user = RequestUtil.getLoginUser(request);
	Long orgLoginId = (Long)session.getAttribute("orgLoginId");
%>
    <div class="small-header transition animated fadeIn extend-menu hidden">
        <div class="hpanel">
            <div class="panel-body">
                <ol class="hbreadcrumb breadcrumb"></ol>
            </div>
        </div>
    </div>
<% 
	String type = request.getParameter("type");
%>
<script type="text/javascript">
	$.ajax({
		url: 'selectAuthorizedChildren',
		type: 'POST',
		dataType: 'json',
		data: {parentId: 21},
	})
	.done(function(d){
		var extendMenu = '',
			type = "<%=type%>"

		if(d.length > 0){
			$.each(d, function(index, val) {
				var classCurrent = (val.menuUrl).indexOf(type) != -1 ?'current':''
				extendMenu += '<li class="'+classCurrent+'" data-uri="'+val.menuUrl+'">'
				extendMenu += '<a href="<%=basePath%>'+val.menuUrl+'">'+val.menuName+'</a></li>'
			});

			$('.extend-menu .breadcrumb').append(extendMenu)
			$('.extend-menu').removeClass('hidden')
			var $width = $('.extend-menu .breadcrumb').width() / d.length
			$('.extend-menu .breadcrumb').find('li').css({
				width: $width
			})
		}
		$('.extend-menu .current').prev().addClass('current-prev')

	})
</script>