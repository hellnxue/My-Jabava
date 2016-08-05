<%@page import="org.apache.commons.lang.StringUtils"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isErrorPage="true"%>

<!DOCTYPE HTML>
<html>
	<head>
		<meta charset="utf-8" />
		<title>error</title>
</head>
<body>
<h3>出错了</h3>
<div>
	<%
		if(exception != null && !StringUtils.isEmpty(exception.getMessage())){
			out.println(exception.getMessage());
		}else{
			out.println("您请求的页面出了点小问题");
		}
	%>
</div>
</body>
</html>
