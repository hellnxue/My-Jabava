<%@page import="com.jabava.utils.RequestUtil"%>
<%@page import="com.jabava.pojo.manage.EhrUser"%>
<%@ page contentType="text/html; charset=utf-8" %>
<%
	EhrUser user = RequestUtil.getLoginUser(request);
	Long orgLoginId = (Long)session.getAttribute("orgLoginId");
%>
<div class="online-news">
<a href="common/online_frame">
    <img src="static/img/xuanfu.png" width="141" height="34">
</a>
</div>