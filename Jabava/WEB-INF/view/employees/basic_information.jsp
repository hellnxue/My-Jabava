<%@ page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html>
<head>
	<base href="${pageContext.request.contextPath}/">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <!-- Page title -->
    <title>基本信息</title>
    <jsp:include flush="true" page="../common/styles.jsp"></jsp:include>
    <link rel="stylesheet" href="static/css/user_bata.css">
    <link rel="stylesheet" href="static/css/style_1.css" media="screen">
   

    
</head>
<body >

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
<div id="wrapper" class="min-h">
<div class="normalheader transition animated fadeIn small-header">
    <div class="hpanel">
        <div class="panel-body">
            <div id="hbreadcrumb" class="pull-right m-t-lg">
                <ol class="hbreadcrumb breadcrumb">
                    <li><a href="to_index?jump=1">首页</a></li>
                    <li>
                        <span>员工信息</span>
                      </li>
                      <li class="active">
                        <span></span>
                      </li>
                    </ol>
                  </div>
                  <h2 class="font-light m-b-xs">
                    员工信息
                  </h2>
                  <small>待定</small>
                </div>
              </div>
            </div>

            <!-- 放主要内容 -->

          <!--基本信息-->
          <!-- 开始 -->
          <div class="content animate-panel">
            <div class="row ">
              <div class="col-lg-12">
                <div class="hpanel hblue">
                  <!--引入员工信息导航 开始--> 
                  <jsp:include flush="true" page="employee_nav.jsp">
                  <jsp:param value="essential" name="type"/>
                  </jsp:include>
                  <!--引入员工信息导航 结束-->
                  <div class="panel-heading hbuilt">
                    <div class="panel-tools">
                      <button type="button" class="btn btn-success btn-xs gwxx"><span class="bold">岗位信息&gt;&gt;</span></button> 
                    </div>
                    <h4>基本信息</h4>
                  </div>
                  <div class="panel-body" id="jiben_div">
                    <script type="text/html" id="jiben">
                      <div class="">
                        <form method="post" role="form" name="jiben_form" class="searchs-form form-horizontal jbxx jbxx_form" id="personalForm" enctype="multipart/form-data">
                          <div class="text-right action-group">
                            <a href="javascript://" class="pe-7s-note pe-2x" data-action-motive="edit" ><span class="sr-only">修改</span></a>
                          </div>
                          <input type="hidden" name="personId" value="{{person.personId}}">
                          <!--单位名称-->
                          <div class="col-md-4 col-lg-4">
                            <div class="form-group">
                              <label for="" class="control-label col-md-4 col-lg-4">单位名称：</label>
                              <div class="col-md-8 col-lg-8">
                                <div class="input-group-static">
                                  <p class="form-control-static">{{person.companyName}}</p>
                                </div>
                                <input type="text" required="required" class="form-control" name="companyName" value="{{person.companyName}}" readonly="readonly">
                              </div>
                            </div>
                          </div>
                          <!--入职时间-->
                          <div class="col-md-4 col-lg-4">
                            <div class="form-group">
                              <label for="" class="control-label col-md-4 col-lg-4">入职时间：</label>
                              <div class="col-md-8 col-lg-8">
                                <div class="input-group-static">
                                  <p class="form-control-static">{{person.entryDate | dateFormat}}</p>
                                </div>
                                <div class="input-group date">
                                  <input type="text" required="required" class="form-control"  name="entryDate" value="{{person.entryDate | dateFormat}}">
                                  <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                </div>
                              </div>
                            </div>
                          </div>
                          <!--转正时间-->
                          <div class="col-md-4 col-lg-4">
                            <div class="form-group">
                              <label for="" class="control-label col-md-4 col-lg-4">转正时间：</label>
                              <div class="col-md-8 col-lg-8">
                                <div class="input-group-static">
                                  <p class="form-control-static">{{person.positiveDate | dateFormat}}</p>
                                </div>
                                <div class="input-group date">
                                  <input type="text" required="required" class="form-control" name="positiveDate" value="{{person.positiveDate | dateFormat}}">
                                  <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                </div>
                              </div>
                            </div>
                          </div>
                          <!--工作地-->
                          <div class="col-md-4 col-lg-4">
                            <div class="form-group">
                              <label for="" class="control-label col-md-4 col-lg-4">工作地：</label>
                              <div class="col-md-8 col-lg-8">
                                <div class="input-group-static">
                                  <p class="form-control-static">
                                    {{each city as c k}}
                                    {{if (c.baseDataId == person.workLocation)}}
                                    {{c.baseDataName}}
                                    {{/if}}
                                    {{/each}}
                                  </p>
                                </div>
                                <select class="form-control " name="workLocation">
                                  {{each city as c k}}
                                  <option {{if (c.baseDataId == person.workLocation)}}selected="true"{{/if}} value="{{c.baseDataId}}">{{c.baseDataName}}</option>
                                  {{/each}}
                                </select>
                              </div>
                            </div>
                          </div>
                          <!--发薪地-->
                          <div class="col-md-4 col-lg-4">
                            <div class="form-group">
                              <label for="" class="control-label col-md-4 col-lg-4">发薪地：</label>
                              <div class="col-md-8 col-lg-8">
                                <div class="input-group-static">
                                  <p class="form-control-static">
                                    {{each city as ci j}}
                                    {{if (ci.baseDataId == person.payrollLocation)}}
                                    {{ci.baseDataName}}
                                    {{/if}}
                                    {{/each}}
                                  </p>
                                </div>
                                <select class="form-control " name="payrollLocation">
                                  {{each city as ci j}}
                                  <option {{if (ci.baseDataId == person.payrollLocation)}}selected="true"{{/if}} value="{{ci.baseDataId}}">{{ci.baseDataName}}</option>
                                  {{/each}}
                                </select>
                              </div>
                            </div>
                          </div>
                          <!--社保缴纳地-->
                          <div class="col-md-4 col-lg-4">
                            <div class="form-group">
                              <label for="" class="control-label col-md-4 col-lg-4">社保缴纳地：</label>
                              <div class="col-md-8 col-lg-8">
                                <div class="input-group-static">
                                  <p class="form-control-static">
                                    {{each city as ci j}}
                                    {{if (ci.baseDataId == person.socialSecurityLocation)}}
                                    {{ci.baseDataName}}
                                    {{/if}}
                                    {{/each}}
                                  </p>
                                </div>
                                <select class="form-control " name="socialSecurityLocation">
                                  {{each city as ci j}}
                                  <option {{if (ci.baseDataId == person.socialSecurityLocation)}}selected="true"{{/if}} value="{{ci.baseDataId}}">{{ci.baseDataName}}</option>
                                  {{/each}}
                                </select>
                              </div>
                            </div>
                          </div>
                          <!--停发标志-->
                          <div class="col-md-4 col-lg-4">
                            <div class="form-group">
                              <label for="" class="control-label col-md-4 col-lg-4">停发标志：</label>
                              <div class="col-md-8 col-lg-8">
                                <div class="input-group-static">
                                  <p class="form-control-static">
                                    {{if person.isPayrollFlag == 0}}
                                    未停发
                                    {{else}}
                                    停发
                                    {{/if}}
                                  </p>
                                </div>
                                <select class="form-control" name="isPayrollFlag">
                                  <option {{if (person.isPayrollFlag == 0)}} select="true" {{/if}} value="0">未停发</option>
                                  <option {{if (person.isPayrollFlag == 1)}} select="true" {{/if}} value="1">停发</option>
                                </select>
                              </div>
                            </div>
                          </div>
                          <!--员工状态-->
                          <div class="col-md-4 col-lg-4">
                            <div class="form-group">
                              <label for="" class="control-label col-md-4 col-lg-4">员工状态：</label>
                              <div class="col-md-8 col-lg-8">
                                <div class="input-group-static">
                                  <p class="form-control-static">
                                    {{if person.status == 0}}
                                    在职
                                    {{else if person.status == 1}}
                                    离职
                                    {{else person.status == 2}}
                                    再入职
                                    {{/if}}
                                  </p>
                                </div>
                                <select class="form-control " name="status" placeholder="在职">
                                  <option {{if (person.status == 0)}} select="true" {{/if}} value="0">在职</option>
                                  <option {{if (person.status == 1)}} select="true" {{/if}} value="1">离职</option>
                                  <option {{if (person.status == 2)}} select="true" {{/if}} value="2">再入职</option>
                                </select>
                              </div>
                            </div>
                          </div>
                          <!--团队-->
                          <div class="col-md-4 col-lg-4">
                            <div class="form-group">
                              <label for="" class="control-label col-md-4 col-lg-4">团队：</label>
                              <div class="col-md-8 col-lg-8">
                                <div class="input-group-static">
                                  <p class="form-control-static">
                                    {{each team as t k}}
                                    {{if (t.baseDataId == person.team)}}
                                    {{t.baseDataName}}
                                    {{/if}}
                                    {{/each}}
                                  </p>
                                </div>
                                <select class="form-control " name="team">
                                  {{each team as t k}}
                                  <option {{if (t.baseDataId == person.team)}}selected="true"{{/if}} value="{{t.baseDataId}}">{{t.baseDataName}}</option>
                                  {{/each}}
                                </select>
                              </div>
                            </div>
                          </div>
                          <!--招聘渠道-->
                          <div class="col-md-4 col-lg-4">
                            <div class="form-group">
                              <label for="" class="control-label col-md-4 col-lg-4">招聘渠道：</label>
                              <div class="col-md-8 col-lg-8">
                                <div class="input-group-static">
                                  <p class="form-control-static">{{person.recruit}}</p>
                                </div>
                                <input type="text" class="form-control"  name="recruit" value="{{person.recruit}}">
                              </div>
                            </div>
                          </div>
                          <!--保存 删除-->
                          <div class="col-lg-12 col-md-12 text-center form-action">
                            <button type="button" class="btn btn-info" data-action-motive="save">保存</button>
                            <button type="button" class="btn btn-default" data-action-motive="cancel">取消</button>
                          </div>
                        </form>
                      </div>
                    </script>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <!--基本信息 end--> 
          <!--结束 -->

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
<script src="static/bootstrap/vendor/moment/moment.js"></script>
<script src="static/bootstrap/vendor/xeditable/bootstrap3-editable/js/bootstrap-editable.min.js"></script>
<script src="static/bootstrap/vendor/select2-3.5.2/select2.min.js"></script>
<script src="static/bootstrap/vendor/bootstrap-touchspin/dist/jquery.bootstrap-touchspin.min.js"></script>
<script src="static/bootstrap/vendor/bootstrap-datepicker-master/dist/js/bootstrap-datepicker.js"></script>

<!-- for datatable -->
<script src="static/bootstrap/vendor/datatables/media/js/jquery.dataTables.min.js"></script>
<script src="static/bootstrap/vendor/datatables_plugins/integration/bootstrap/3/dataTables.bootstrap.min.js"></script>
<!-- alert -->
<script src="static/bootstrap/vendor/sweetalert/lib/sweet-alert.min.js"></script>

<!-- App scripts -->
<script src="static/bootstrap/scripts/homer.js"></script>
<script src="static/js/template.js"></script>
<script src="static/js/yuangong.js"></script>
<script>
function showBaseInfo(){
	
	  $.ajax({
    	  type: "POST",
	  url:"employee/essentialInfo.do",
	  data:{"personId":"${personId}"}, 
	  dataType:'json',
	  error: function(XMLHttpRequest, textStatus, errorThrown){
            $('#create_new').removeClass('hidden');
	  },
	  success: function(data){

        var html = template('jiben', data);
            html = $(html);

        // form init
        
        var getDomStaticTarget = html.find('.input-group-static'),
            getDomTarget = getDomStaticTarget.siblings(),
            getDomFormAction = html.find('.form-action');
        
        getDomTarget.hide();
        getDomFormAction.hide();

        // @eventBind {action} .action-group
        html.find('.action-group [data-action-motive]').on('click', function(evt){
            var getAction = $(this).data('actionMotive'),
                $form = $(this).parents('form'),
                $getDomStaticTarget = $form.find('.input-group-static'),
                $getDomTarget = $getDomStaticTarget.siblings(),
                $getDomFormAction = $form.find('.form-action');

            switch(getAction){
                case 'edit':
                    $getDomStaticTarget.hide();
                    $getDomTarget.show();
                    $getDomFormAction.show();
                    break;
            }
        });


        // @eventBind {action} .form-action
        getDomFormAction.find('[data-action-motive]').on('click', function(evt){

            var $form = $(this).parents('form'),
                $getDomStaticTarget = $form.find('.input-group-static'),
                $getDomTarget = $getDomStaticTarget.siblings(),
                $getDomFormAction = $form.find('.form-action');

            switch($(this).data('actionMotive')){
               case 'save':
            	   
            	   updateSave($form);
            	 
            	 break;
                case 'cancel':
                    $getDomStaticTarget.show();
                    $getDomTarget.hide();
                    $getDomFormAction.hide();
                    break;
                 
            }
        });

         $("#jiben_div").prepend(html);
     	$('.input-group.date').datepicker({
   		 format: "yyyy-mm-dd"
          });
        if(data.person.personId == ''){
            getDomFormAction.parent('form').hide();
            $('#create_new').removeClass('hidden');
            return;
        }


      }
   });  
}

//修改
function updateSave($form){
	
	var formObject= $form.serializeObject() ;
	
	var jsonObject=JSON.stringify(formObject);
	
	$.ajax({
			type: "post",
		    url: "employee/updatePerson",
		    data: jsonObject,
		    dataType: "json",
		    async: false,
		    contentType:"application/json",
			success:function(data){
				//alert(data.msg);
				if(data.success == true){
					swal(data.msg, "", "success"); 
					 $("#jiben_div").html("");
					 showBaseInfo();
					 
				}else{
					swal(data.msg, "", "error"); 
				}
			}
		}); 
	
}
$(function (){
	showBaseInfo();//初始化页面
	  
	});

</script>

<!--日历-->
<script>

        $(function(){
			deleteBtnInfo();
            $('#datepicker').datepicker();
            $("#datepicker").on("changeDate", function(event) {
                $("#my_hidden_input").val(
                        $("#datepicker").datepicker('getFormattedDate')
                )
            });

            $('#datapicker2').datepicker();
            $('.input-group.date').datepicker({ });
            $('.input-daterange').datepicker({ });
        });

    </script>
</body>
</html>