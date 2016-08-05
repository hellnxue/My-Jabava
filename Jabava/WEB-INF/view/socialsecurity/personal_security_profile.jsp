<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<base href="${pageContext.request.contextPath}/">
	<meta charset="utf-8">
	<title>员工社保档案</title>
	<jsp:include flush="true" page="../common/styles.jsp"></jsp:include>
	<script src="static/bootstrap/vendor/jquery/dist/jquery.min.js"></script>
</head>
<body>
	<h1>员工社保档案</h1>
	<button id="searchButton">Search</button>
	
	<!-- 修改信息 -->
	<!-- 补缴管理 -->
	<!-- 汇缴信息 -->
	<!-- 补缴信息-->
</body>
<script>
	$('#searchButton').on('click',function(){
		console.log('=== so the search');
		
		$.ajax({
			url:'employee/getSecurityProfileByCompanyId',
			type:'post',
			success:function(result){
				console.log("===== gettting the response right now ====");
				console.log(result);
			}
		})
	});
	////////////////////////////////////////////////////////////////
	var INTERFACE = {
		// 员工基本信息获取
		// 员工薪酬信息获取
		// 员工社保档案信息获取
		// 员工社保档案信息保存
		// 员工社保档案信息导出
		// 员工社保档案信息导入
	};
 	var personID = "1234";

	$(function(){
		_init(personID);

		console.log("========> init the work done ======");
	});

	/**
	 * 加载个人社保档案相关的信息
	 * @param  {[type]} personID [description]
	 * @return {[type]}          [description]
	 */
	function _init(personID){
		console.log("=========== init the work =======");
		console.log();
		// TODO　到人事模块查询基本信息
		// TODO  到薪酬模块查询薪资信息
		// TODO  查询本模块社保档案信息
		// TODO  呈现个人社保档案列表
	}

	/**
	 * 导出员工社保档案
	 * @return {[type]} [description]
	 */
	function _export(){
		//TODO　调用后台接口，导出员工社保档案
	}

	/**
	 * 导入员工社保档案
	 * @return {[type]} [description]
	 */
	function _import(){
		//TODO　调用后台接口导入员工社保档案
	}

</script>
</html>