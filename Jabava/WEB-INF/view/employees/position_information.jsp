<%@ page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html>
<head>
	<base href="${pageContext.request.contextPath}/">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- Page title -->
    <title>岗位信息</title>
    <jsp:include flush="true" page="../common/styles.jsp"></jsp:include>
    <link rel="stylesheet" href="static/css/user_bata.css">
    <link rel="stylesheet" href="static/css/style_1.css" media="screen">
    <link rel="stylesheet" href="static/css/zTreeStyle/zTreeStyle.css" type="text/css">
    
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

    <!--岗位信息-->
    <!-- 开始 -->
    <div class="content animate-panel">
      <div class="row">
        <div class="hpanel hblue">
          <!--引入员工信息导航 开始--> 
          <jsp:include flush="true" page="employee_nav.jsp"><jsp:param value="position" name="type"/></jsp:include>
          <!--引入员工信息导航 结束-->
          <div class="panel-heading hbuilt">
            <div class="panel-tools">
              <a href="employee/jobpost?personId=${personId}"><button type="button" class="btn btn-success btn-xs gwdd"><span  class="bold">岗位调动&gt;&gt;</span></button></a>
            </div>
            <h4>岗位信息</h4>
          </div>
          <div class="panel-body">
            <div class="div_position">

            </div>
            <div class="hpanel" id="gangwei_div">
              <input type="hidden" name="personId" id="personId" value="${personId}">
              <script type="text/html" id="gangwei">
                <div class="">
                  <form role="form" id="positionForm" class="searchs-form form-horizontal gw_dd gw_dd-form"> 
                    <div class="text-right action-group">
                      <a href="javascript://" class="pe-7s-note pe-2x" data-action-motive="edit" ><span class="sr-only">修改</span></a>
                    </div>
                    <input type="hidden" name="personId" value="{{ehrPositionVO.personId}}">
                    {{if ehrPositionVO!=null || ehrPositionVO.positionId !== null}}
                    <input type="hidden" name="positionId" value="{{ehrPositionVO.positionId}}">
                    {{else}}
                    <input type="hidden" id="positionId" name="positionId" >
                    {{/if}}
                    <!--部门名称-->
                    <div class="col-md-4 col-lg-4">
                      <div class="form-group">
                        <label for="" class="control-label col-md-4 col-lg-4">部门名称：</label>
                        <div class="col-md-8 col-lg-8">

                          <input type="hidden" id="departmentId"  name="departmentId" required="required">
                          {{if ehrPositionVO.departmentId !== null}}
                          <div class="input-group-static">
                            <p class="form-control-static">{{ehrPositionVO.department.organizationName}}</p>
                          </div>

                          <input type="text" class="form-control" 
                          data-tree-attr="getTreeNode" 
                          value="{{ehrPositionVO.department.organizationName}}" disabled="disabled">

                          {{else}}
                          <div class="input-group-static">
                            <p class="form-control-static">&nbsp;</p>
                          </div> 									
                          <div class="input-group">
                            <input type="text" class="form-control" 
                            data-tree-attr="getTreeNode" 
                            value="" required="required">
                            <span class="input-group-btn"><button type="button" class="btn btn-default" data-toggle="modal" data-target="#getTree"><i class="fa fa-search"></i></button></span>
                          </div>
                          {{/if}}
                        </div>
                      </div>
                    </div>
                    <!--岗位名称-->
                    <div class="col-md-4 col-lg-4">
                      <div class="form-group">
                        <label for="" class="control-label col-md-4 col-lg-4">岗位名称：</label>
                        <div class="col-md-8 col-lg-8">
                          <div class="input-group-static">
                            <p class="form-control-static">
                              {{each ehrPositionVO.posiName as key idx}}
                              {{if ehrPositionVO.positionName==key.baseDataId}}
                              {{key.baseDataName}}
                              {{/if}}
                              {{/each}}
                            </p>
                          </div>
                          {{if ehrPositionVO.positionName !== null}}
                          <select class="form-control"  disabled="disabled">
                            {{each ehrPositionVO.posiName as key idx}}
                            <option value="{{key.baseDataId}}" {{if ehrPositionVO.positionName==key.baseDataId}} selected{{/if}}>{{key.baseDataName}}</option>
                            {{/each}}
                          </select>
                          {{else}}
                          <select class="form-control"  name="positionName" >
                            {{each ehrPositionVO.posiName as key idx}}
                            <option value="{{key.baseDataId}}" {{if ehrPositionVO.positionName==key.baseDataId}} selected{{/if}}>{{key.baseDataName}}</option>
                            {{/each}}
                          </select>
                          {{/if}}
                        </div>
                      </div>
                    </div>
                    <!--职级-->
                    <div class="col-md-4 col-lg-4">
                      <div class="form-group">
                        <label for="" class="control-label col-md-4 col-lg-4">职级：</label>
                        <div class="col-md-8 col-lg-8">
                          <div class="input-group-static">
                            <p class="form-control-static">
                              {{each ehrPositionVO.level as r t}}
                              {{if (r.baseDataId == ehrPositionVO.levelId)}}
                              {{r.baseDataName}}
                              {{/if}}
                              {{/each}}
                            </p>
                          </div>
                          {{if ehrPositionVO.levelId !== null}}
                          <select class="form-control " name="levelId" disabled="disabled">
                            {{each ehrPositionVO.level as r t}}
                            <option {{if (r.baseDataId == ehrPositionVO.levelId)}}selected="selected"{{/if}} value="{{r.baseDataId}}">{{r.baseDataName}}</option>
                            {{/each}}
                          </select>
                          {{else}}
                          <select class="form-control " name="levelId" >
                            {{each ehrPositionVO.level as r t}}
                            <option {{if (r.baseDataId == ehrPositionVO.levelId)}}selected="selected"{{/if}} value="{{r.baseDataId}}">{{r.baseDataName}}</option>
                            {{/each}}
                          </select>
                          {{/if}}
                        </div>
                      </div>
                    </div> 
                    <!--工号-->
                    <div class="col-md-4 col-lg-4">
                      <div class="form-group">
                        <label for="" class="control-label col-md-4 col-lg-4">工号：</label>
                        {{if ehrPositionVO.employeeNumber !== null}}
                        <div class="col-md-8 col-lg-8">
                          <div class="input-group-static">
                            <p class="form-control-static">{{ehrPositionVO.employeeNumber}}</p>
                          </div>
                          <input type="text" class="form-control" id="employeeNumber" name="employeeNumber" value="{{ehrPositionVO.employeeNumber}}" required="required">
                        </div>
                        {{else}}
                        <div class="col-md-8 col-lg-8">
                          <div class="input-group-static">
                            <p class="form-control-static"></p>
                          </div>
                          <input type="text" class="form-control" id="addEmployeeNumber" name="employeeNumber" value="" required="required">
                        </div>
                        {{/if}}
                      </div>
                    </div>                                    
                    <!--用工性质-->
                    <div class="col-md-4 col-lg-4">
                      <div class="form-group">
                        <label for="" class="control-label col-md-4 col-lg-4">用工性质：</label>
                        <div class="col-md-8 col-lg-8">
                          <div class="input-group-static">
                            <p class="form-control-static">
                              {{each ehrPositionVO.labor as p s}}
                              {{if (p.baseDataId == ehrPositionVO.natureOfLabor)}}
                              {{p.baseDataName}}
                              {{/if}}
                              {{/each}}
                            </p>
                          </div>
                          <select class="form-control " name="natureOfLabor">
                            {{each ehrPositionVO.labor as p s}}
                            <option {{if (p.baseDataId == ehrPositionVO.natureOfLabor)}}selected="selected"{{/if}} value="{{p.baseDataId}}">{{p.baseDataName}}</option>
                            {{/each}}
                          </select>
                        </div>
                      </div>
                    </div>                                   
                    <!--是否关键岗位--> 
                    <div class="col-md-4 col-lg-4">
                      <div class="form-group">
                        <label for="" class="control-label col-md-4 col-lg-4">是否关键岗位：</label>
                        <div class="col-md-8 col-lg-8">
                          <div class="input-group-static">
                            <p class="form-control-static">
                              {{if (ehrPositionVO.keyPoint =="1")}}
                              是
                              {{else}}
                              否
                              {{/if}}
                            </p>
                          </div>
                          <div class="radio-inline-group form-control-static">
                            <label class="radio-inline">
                              <input type="radio" name="keyPoint" id="" value="1" {{if (ehrPositionVO.keyPoint =="1")}}checked="checked"{{/if}}> 是
                            </label>
                            <label class="radio-inline">
                              <input type="radio" name="keyPoint" id="" value="0" {{if (ehrPositionVO.keyPoint =="0")}}checked="checked"{{/if}}> 否
                            </label>
                          </div>
                        </div>
                      </div>
                    </div>
                    <!--汇报对象-->
                    <div class="col-md-4 col-lg-4">
                      <div class="form-group">
                        <label for="" class="control-label col-md-4 col-lg-4">汇报对象：</label>
                        {{if ehrPositionVO.reportId !== null}}
                        <div class="col-md-8 col-lg-8">
                          <div class="input-group-static">
                            <p class="form-control-static">{{ehrPositionVO.reportPerson.employeeName}}</p>
                          </div>
                          <input type="text" class="form-control" id="employeeName" name="employeeName" disabled="disabled"  value="{{ehrPositionVO.reportPerson.employeeName}}">
                        </div>
                        {{else}}
                        <div class="col-md-8 col-lg-8">
                          <div class="input-group-static">
                            <p class="form-control-static"></p>
                          </div>
                          <input type="text" class="form-control" id="addEmployeeName" name="employeeName" >
                        </div>
                        {{/if}}
                      </div>

                    </div>
                    <!--成本中心-->
                    <div class="col-md-4 col-lg-4">
                      <div class="form-group">
                        <label for="" class="control-label col-md-4 col-lg-4">成本中心：</label>
                        <div class="col-md-8 col-lg-8">
                          <div class="input-group-static">
                            <p class="form-control-static">
                              {{each ehrPositionVO.costCenter as c k}}
                              {{if (c.baseDataId == ehrPositionVO.costCenterId)}}
                              {{c.baseDataName}}
                              {{/if}}
                              {{/each}}

                            </p>
                          </div>
                          {{if ehrPositionVO.costCenter !== null}}
                          <select class="form-control " name="costCenterId" disabled="disabled" >
                            {{each ehrPositionVO.costCenter as c k}}
                            <option {{if (c.baseDataId == ehrPositionVO.costCenterId)}}selected="selected"{{/if}} value="{{c.baseDataId}}">{{c.baseDataName}}</option>
                            {{/each}}
                          </select>
                          {{else}}
                          <select class="form-control " name="costCenterId"  >
                            {{each ehrPositionVO.costCenter as c k}}
                            <option {{if (c.baseDataId == ehrPositionVO.costCenterId)}}selected="selected"{{/if}} value="{{c.baseDataId}}">{{c.baseDataName}}</option>
                            {{/each}}
                          </select>
                          {{/if}}
                        </div>

                      </div>
                    </form>
                    <!--保存 删除-->
                    <div class="col-lg-12 col-md-12 text-center form-action">
                      <button type="button" id="addPosition" onclick="submitPosition()" class="btn btn-info">保存</button>
                      <button type="button" class="btn btn-default" data-action-motive="cancel">取消</button>
                    </div>
                    <!--岗位信息保存-->
                    <!--岗位信息保存 end-->
                  </div>
                </script>
              </div>
            </div>
          </div>
        </div>
      </div>
      <!--岗位信息 end--> 
      <!--结束 -->
      <div class="modal fade hmodal-success" id="getTree" tabindex="-1" role="dialog"  aria-hidden="true">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="color-line"></div>
            <div class="modal-body">
              <div class="dd" id="nestable_third">

                <div class="zTreeDemoBackground">
                  <ul id="orgTree" class="ztree"></ul>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
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


<script type="text/javascript" src="static/js/zTreeJs/zTreeJs/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="static/js/zTreeJs/zTreeJs/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript" src="static/js/zTreeJs/zTreeJs/jquery.ztree.exedit-3.5.js"></script>
<script>
function submitPosition() {
	//如果是新增
	if($("#positionId").val()==''){
		if($("#departmentId").val()==''){
			alert('请选择部门');
			return false;
		}
		if($("#addEmployeeNumber").val()==''){
			alert('工号不可为空');
			return false;
		}
		if($("#addEmployeeName").val()==''){
			alert('汇报对象不可为空');
			return false;
		}
		
		
	}else{//如果是修改
		
		if($("#employeeNumber").val()==''){
			alert('工号不可为空');
			return false;
		}
		
		
	}

    $.ajax({
		type : 'post',
		url : "position/addPosition",
		data : $("#positionForm").serialize(),
		async : false,
		dataType: "json",
		success : function(result) {
			if(result=='success'){
				alert("  成 功 ");
			}			
		    window.location.reload();
		}
	});
 }
$(function (){
	 var personId= ${personId};
	  $.ajax({
		  url:"position/getEhrPositionByPersonId",
		  dataType:'json',
		  data:{personId:personId},
		  error: function(XMLHttpRequest, textStatus, errorThrown){
			   alert(textStatus);
		  },
		  success: function(data){

		
            var html = template('gangwei', data);
                html = $(html);

            // form init
            var getDomStaticTarget = html.find('.input-group-static'),
                getDomTarget = getDomStaticTarget.siblings(),
                getDomFormAction = html.find('.form-action');
                getDomActionGroup = html.find('.action-group');
            
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
                    case 'del':
                      var getActionId = $(this).data('actionId'),
                        // 请将delUrl替换成真实地址
                          delUrl = '';
                      
                      delUrl = delUrl+getActionId;
                      //console.log(delUrl)
                      // show modal
                        swal({
                            title: "确定要删除该条离职信息吗?",
                            text: "注意：用户删除后将不可恢复!",
                            type: "warning",
                            showCancelButton: true,
                            confirmButtonColor: "#DD6B55",
                            confirmButtonText: "是,请删除该信息!",
                            cancelButtonText: "不, 放弃此操作!",
                            closeOnConfirm: false,
                            closeOnCancel: false },
                            function (isConfirm){
                                if (isConfirm){
                                    $.post(delUrl, function(){
                                      swal("删除成功!", "该信息已经被删除.", "success");
                                        window.location.reload();
                                    })
                                } else {
                                    swal("已取消", "信息未删除。你这逗我玩呢 :)", "error");
                                }
                            });
                        break;
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
                    case 'cancel':
                        $getDomStaticTarget.show();
                        $getDomTarget.hide();
                        $getDomFormAction.hide();
                        positionForm
                        $("#employeeNumber").val('');
                        $('#positionForm')[0].reset();
                        break;
                }
            });


			 // $("#gangwei_div").html(template('gangwei', data));
       $("#gangwei_div").prepend(html);
			 $('.input-group.date').datepicker({});
			// 如果没有岗位信息 
     if(data.ehrPositionVO.positionName == null){
    	
        getDomStaticTarget.hide();
        getDomActionGroup.hide();
        getDomTarget.show();
        getDomFormAction.find('[data-action-motive=cancel]').hide();
        getDomFormAction.show();
      }
  

		  }
	   });
	   
	   
	})

</script>

<script>
  var setting = {
    view: {
      dblClickExpand: false,
      showLine:false
    },
    check: {
      enable: true
    },
    callback: {
      onClick: zTreeOnClick
    },
    data: {
      simpleData: {
        enable: true
      }
    },
    treeNode:{
      nocheck:true
    }
  };
  function zTreeOnClick(event, treeId, treeNode){
    var $modal = $('#getTree'),
        $sourceDepartment = $(domTreeRelatedTarget).parent().siblings('[data-tree-attr=getTreeNode]');
    $modal.modal('hide');
    $sourceDepartment.val( treeNode.name );
    
    $("#departmentId").val(treeNode.id);
  //   console.log(treeNode.id)
  };
  function loadData(targetDom){
    //组织架构树引用json
    var zNodes =[];
    var personId = $("#personId").val();
    $.ajax({
    	 url:"system/loadPersonTree",
         data:{personId:personId},
       type : "POST",
      dataType:'json',
      error: function(XMLHttpRequest, textStatus, errorThrown){
         alert(textStatus);
      },
      success: function(data){
        var strs = data.data;
        for(var i = 0; i < strs.length; i++){
          var obj = new Object();
          obj.id = strs[i].organizationId;
          obj.pId = strs[i].parentId;
          obj.name = strs[i].organizationName;
          obj.code = strs[i].organizationCode;
          obj.memo = strs[i].memo;
          obj.open = true;
          zNodes.push(obj);
        }
        $.fn.zTree.init($(targetDom), setting, zNodes);
      }
    });
  }
  var domTreeRelatedTarget = null;
  $('#getTree').on('show.bs.modal', function(e){
    // e.relatedTarget
    domTreeRelatedTarget = e.relatedTarget;
    var getTargetDom = $('#orgTree');
    loadData(getTargetDom);
  });

</script>

</body>
</html>