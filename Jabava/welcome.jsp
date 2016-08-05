<%@page import="com.jabava.utils.Constants"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	if(session.getAttribute(Constants.LOGIN_USER) != null){
		//response.sendRedirect(request.getContextPath() + "/to_index");
		request.getRequestDispatcher("/to_index").forward(request, response);
	}else{
		//response.sendRedirect(request.getContextPath() + "/index/index");
		request.getRequestDispatcher("/index/index").forward(request, response);
	}
%>

<!DOCTYPE HTML>
<html>
<head>
</head>
<body>

</body>
</html>
