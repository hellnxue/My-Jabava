<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<base href="${pageContext.request.contextPath}/">
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">

	<!-- Page title -->
	<title>Jabava添加新的模板界面</title>
	<jsp:include flush="true" page="../common/styles.jsp"></jsp:include>
	<link rel="stylesheet" href="static/bootstrap/vendor/bootstrap-touchspin/dist/jquery.bootstrap-touchspin.min.css">
	<link rel="stylesheet" href="static/css/user.css">


	<script>

		function downloadFile(path) {
			window.open(path);
		}
	</script>
</head>
<body>

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
	<div id="wrapper">

		<!-- 放主要内容 -->
		<div class="content animate-panel">
			<div class="row">
				<div class="col-lg-12">
					<div class="hpanel">
						<div class="panel-heading">
							<h4 class="text-center font-bold">
								添加新的模板
							</h4>
						</div>
						<div class="panel-body">
							<form class="form-horizontal">
								<div class="col-md-6 col-sm-6 col-xs-6">
									<div class="form-group">
										<label for="" class="control-label col-lg-4 col-sm-4 col-xs-4">模板名称：</label>
										<div class="col-lg-8 col-sm-8 col-xs-8">
											<input type="text" class="form-control">
										</div>
									</div>
								</div>
								<div class="col-md-12 col-sm-12 col-xs-12">
									<div class="form-group">
										<label for="inputPassword3" class="col-lg-2 col-sm-2 col-xs-2 control-label font-bold">模板描述：</label>
										<div class="col-lg-9 col-sm-9 col-xs-9">
											<textarea type="text" class="form-control" id="addRemark" name="remark" ></textarea>
										</div>
									</div>
								</div>
								<div class="col-md-12 col-sm-12 col-xs-12">
									<div class="form-group">
										<label for="" class="control-label col-lg-2 col-sm-2 col-xs-2">字段数量：</label>
										<div class="col-lg-9 col-sm-9 col-xs-9">
											<div class="input-group">
												<input data-field="count" type="text" value="" name="fields" class="form-control" autocomplete="false">
												<div class="input-group-btn">
													<button class="btn btn-primary" type="button" data-field-action="add">增加</button>
												</div>
											</div>
										</div>
									</div>
								</div>
								
								<div data-container="fields">
									<div class="col-md-10 col-sm-10 col-xs-10 col-md-offset-2 col-sm-offset-2 col-xs-offset-2">
										<div class="col-md-4 col-sm-4 col-xs-4"><label for="">字段名</label></div>
										<div class="col-md-4 col-sm-4 col-xs-4"><label for="">组件</label></div>
										<div class="col-md-4 col-sm-4 col-xs-4"><label for="">操作</label></div>
										
									</div>
									
									<div class="col-md-10 col-sm-10 col-xs-10 col-md-offset-2 col-sm-offset-2 col-xs-offset-2 hidden" data-field-gid="0" data-field-tpl="true">
										<div class="col-md-4 col-sm-4 col-xs-4">
											<div class="col-md-10">
												<div class="form-group">
													<input type="text" name="fieldName" class="form-control">
												</div>
											</div>
										</div>
										<div class="col-md-4 col-sm-4 col-xs-4">
											<div class="col-md-10">
												<div class="form-group">
													<select class="form-control" name="keyType" data-field="component">
														<option value="0">--------</option>
														<option value="1">身份证</option>
														<option value="2">工号</option>
													</select>
												</div>
											</div>
										</div>
										<div class="col-md-3 col-sm-3 col-xs-3">
											<div class="col-md-10">
												<div class="form-group">
													<button type="button" class="btn btn-danger" data-filed-action="delete" data-field-id="0">删除</button>
												</div>
											</div>
										</div>
									</div>
								</div>

								<div class="form-group">
									<div class="col-lg-6 col-sm-6 col-xs-6 col-lg-offset-5 col-sm-offset-5 col-xs-offset-5">
										<button class="btn btn-success" type="button" data-action="submit">提　交</button>
									</div>
								</div>
							</form>

						</div>                        
					</div>
				</div>
			</div>
		</div> 

		<!--主要内容结束-->

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
	<script src="static/bootstrap/vendor/bootstrap-touchspin/dist/jquery.bootstrap-touchspin.min.js"></script>


	<!-- for datatable -->
	<script src="static/bootstrap/vendor/datatables/media/js/jquery.dataTables.min.js"></script>
	<script src="static/bootstrap/vendor/datatables_plugins/integration/bootstrap/3/dataTables.bootstrap.min.js"></script>


	<!-- alert -->
	<script src="static/bootstrap/vendor/sweetalert/lib/sweet-alert.min.js"></script>

	<!-- App scripts -->
	<script src="static/bootstrap/scripts/homer.js"></script>
	<script src="static/bootstrap/scripts/charts.js"></script>


	<!--jabava-->
	<script src="static/js/change/ajaxfileupload.js"></script>
	<script src="static/js/change/change.js"></script>

	<script>
		(function($){
			var getContainer = $('[data-container="fields"]'),
				getFieldTemplate = $('[data-field-tpl="true"]'),
				getBtnFieldAdd = $('[data-field-action="add"]'),
				getBtnFiledDel = $('[data-filed-action="delete"]')
				

				getBtnFieldAdd.on('click.field:add', function(event, counter){
					var counter= $('[data-field="count"]').val()
					var setField = function(){
						var id = (Math.random()).toString().slice(2) * 1,
						strHtml = getFieldTemplate.clone(true, true)
						.removeAttr('data-field-tpl')
						.removeClass('hidden')
						strHtml = strHtml.attr('data-field-gid', id)
						strHtml.find('[data-filed-action]').attr('data-field-id', id)

						return strHtml;
					},
					setMultipleField = []

					if(counter && counter > 0){
						for(var i=0; i < counter; i++){
							setMultipleField[i] = setField();
						}
						getContainer.append( setMultipleField );
					}

				});

				getBtnFiledDel.on('click.field:del', function(event){
					var getGid = $(this).attr('data-field-id'),
						getFieldGroup = $('[data-field-gid='+getGid+']')

					if( !$(this).prop('disabled') ) $('[data-field=component]').prop('disabled', false)
					if( !(getFieldGroup.attr('data-field-tpl') == 'true') ) getFieldGroup.remove()
				})

				$('[data-field=component]').on('change', function(event){
					var keyType = $(this).val(),
						getAllComponents = $('[data-field=component]')
					if(keyType>0){
						getAllComponents.not($(this)).prop('disabled', true)
					}else{

						getAllComponents.prop('disabled', false)
					}
				});



   			$('[data-action="submit"]').on('click', function(event){
   				$('data-field-tpl="true"').remove()

   				var param = $('form').serialize()


   			});


    	})(jQuery)
    </script>

</body>
</html>