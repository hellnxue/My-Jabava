<%@ page contentType="text/html; charset=utf-8" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <!-- Page title -->
    <title>个人信息</title>
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
    <!--个人信息-->
    <!-- 开始 -->
    <div class="content animate-panel">
      <div class="row">
        <div class="col-lg-12">
          <div class="hpanel hblue">
            <!--引入员工信息导航 开始--> 
            <jsp:include flush="true" page="employee_nav.jsp">
            <jsp:param value="personal" name="type"/>
          </jsp:include>
          <!--引入员工信息导航 结束-->
          <div class="panel-heading hbuilt">
            <div class="panel-tools">
              <button type="button" class="btn btn-success btn-xs jbenxinxi"><span class="bold">基本信息&gt;&gt;</span></button>
            </div>
            <h4>个人信息</h4>
          </div>
          <div class="panel-body" id="personal_div">
            <script type="text/html" id="personal">
              <form action="employee/updatePerson" id="personalForm" class="searchs-form form-horizontal geren_form" method="POST" method="post" enctype="multipart/form-data">
                <input type="hidden" name="personId" value="{{person.personId}}"/> 
                <!--姓名-->
                <div class="form-group searchs-unit">
                  <label for="exampleInputName4" class="col-lg-4">姓名：</label>
                  <div class="input-group col-lg-8">
                   
					<!-- <label class="control-label" style=" text-align:left;">{{person.employeeName}}</label>-->
 					<input type="text" class="form-control" name="employeeName" value="{{person.employeeName}}">
                  </div>
                </div>
                <!--手机-->
                <div class="form-group searchs-unit">
                  <label for="exampleInputName4" class="col-lg-4">手机：</label>
                  <div class="input-group col-lg-8">
                    <label class="control-label" style=" text-align:left;">{{person.mobile}}</label>
                  </div>
                </div>

                <!--email-->
                <div class="form-group searchs-unit " style=" width:90%;">
                  <label for="exampleInputName4" class="col-lg-2">Email：</label>
                  <div class="input-group col-lg-10">
                    <label class="control-label" style=" text-align:left;">{{person.email}}</label>
                  </div>
                </div>

                <!--性别-->
                <div class="form-group searchs-unit">
                  <label for="exampleInputName4" class="col-lg-4">性别：</label>
                  <div class="col-lg-4" >
                    <div class="sex_style" style=" text-align:left">
                      <span class="sex sex_third">男</span>
                      <input class="new_style_four sex" type="radio" name="sex" value="0" {{if (person.sex == 0)}}checked="true"{{/if}} />
                      <span class="sex sex_four">女</span>
                      <input class="new_style_four sex" type="radio" name="sex" value="1" {{if (person.sex == 1)}}checked="true"{{/if}} />
                    </div>
                  </div>
                </div>
                <!--联系电话-->
                <div class="form-group searchs-unit" style="width:62%;">
                  <label for="exampleInputName4" class="col-lg-3">联系电话：</label>
                  <div class="input-group col-lg-6">
                    <input type="text" class="form-control" name="phone" value="{{person.phone}}">
                  </div>
                </div>

                <!--出生日期-->
                <div class="form-group searchs-unit" >
                  <label for="exampleInputName4" class="col-lg-4 ">出生日期：</label>
                  <div class="input-group date col-lg-8" data-date-format="yyyy-mm-dd" >
                    <input type="text" class="form-control" name="birthDate1" value="{{person.birthDate | dateFormat}}" required="required">
                    <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                  </div>
                </div>
                <!--证件类型-->  
                <div class="form-group searchs-unit">
                  <label for="exampleInputName4" class="col-lg-4">证件类型：</label>
                  <div class="col-lg-8" style="padding:0;">
                    <select class="form-control " name="certType">
                      <option value="0" {{if (person.certType == 0) }}selected="selected"{{/if}}>身份证</option>
                      <option value="1" {{if (person.certType == 1) }}selected="selected"{{/if}}>军官证</option>
                      <option value="2" {{if (person.certType == 2) }}selected="selected"{{/if}}>护照</option>
                      <option value="3" {{if (person.certType == 3) }}selected="selected"{{/if}}>其他</option>
                    </select>
                  </div>
                </div>

                <!-- 身份证号-->
                <div class="form-group searchs-unit">
                  <label for="exampleInputName4" class="col-lg-4">身份证号：</label>
                  <div class="input-group col-lg-8">
                    <input type="text" class="form-control" name="certId" value="{{person.certId}}" required="required">
                    <!--<span class="reds">*</span>-->
                  </div>
                </div>
                <!--证件类型 电子版-->   
                <div class="form-group searchs-unit" style=" width:92%;">
                  <label  class="col-lg-2">证件类型电子版：</label>
                  <input id="lefile" type="file" style="display:none;" name="certTypeFile" accept=".png,.gif,.jpeg" multiple>
                  <div class="col-lg-10 input-group" style="text-align:left;">
                    <button type="button" class="btn btn-primary" onClick="$('input[id=lefile]').click();">浏览</button>
                    {{if (shenfen != null && shenfen.length > 0)}}
                    <span style="color:green;">√</span>
                    <input id="photoCover" class="input-large input-dianzis" style="display:none;" type="text">
                    {{else }}
                    <input id="photoCover" class="input-large input-dianzis" type="text">
                    {{/if}}
                  </div>
                </div>

                <!--民族-->	
                <div class="form-group searchs-unit">
                  <label for="exampleInputName4" class="col-lg-4">民族：</label>
                  <div class="input-group col-lg-8">
                    <input type="text" class="form-control" name="nationality" value="{{person.nationality}}">
                  </div>
                </div>
                <!--籍贯-->
                <div class="form-group searchs-unit">
                  <label for="exampleInputName4" class="col-lg-4">籍贯：</label>
                  <div class="input-group col-lg-8"> 
                    <input type="text" class="form-control" name="originPlace" value="{{person.originPlace}}">
                  </div>
                </div>
                <!--婚姻状况-->
                <div class="form-group searchs-unit">
                  <label for="exampleInputName4" class="col-lg-4">婚姻状况：</label>
                  <div class="col-lg-8" style="padding:0;">
                    <select class="form-control" name="marital">
                      <option {{if (person.marital == 0)}}selected="true"{{/if}} value="0">未婚</option>
                      <option {{if (person.marital == 1)}}selected="true"{{/if}} value="1">已婚</option>
                      <option {{if (person.marital == 2)}}selected="true"{{/if}} value="2">离异</option>
                      <option {{if (person.marital == 3)}}selected="true"{{/if}} value="3">丧偶</option>
                    </select>
                  </div>
                </div>
                <!--户籍类型-->
                <div class="form-group searchs-unit">
                  <label for="exampleInputName4" class="col-lg-4">户籍类型：</label>
                  <div class="col-lg-8" style="padding:0;">
                    <select class="form-control" name="registerType">
                      <option {{if (person.registerType == 0)}}selected="true"{{/if}} value="0">城镇户口</option>
                      <option {{if (person.registerType == 1)}}selected="true"{{/if}} value="1">农业户口</option>
                    </select>
                  </div>
                </div>
                <!--户口所在地-->
                <div class="form-group searchs-unit">
                  <label for="exampleInputName4" class="col-lg-4">户口所在地：</label>
                  <div class="input-group col-lg-8">
                    <input type="text" class="form-control" name="registerLocation" value="{{person.registerLocation}}">
                  </div>
                </div>
                <!--现居住地-->
                <div class="form-group searchs-unit">
                  <label for="exampleInputName4" class="col-lg-4">现居住地：</label>
                  <div class="input-group col-lg-8">
                    <input type="text" class="form-control" name="contactAddress" value="{{person.contactAddress}}">
                  </div>
                </div>
                <!--家庭住址-->
                <div class="form-group searchs-unit">
                  <label for="exampleInputName4" class="col-lg-4">家庭地址：</label>
                  <div class="input-group col-lg-8">
                    <input type="text" class="form-control" name="familyAddress" value="{{person.familyAddress}}" required="required">
                  </div>
                </div>
                <!--邮编-->
                <div class="form-group searchs-unit">
                  <label for="exampleInputName4" class="col-lg-4">邮编：</label>
                  <div class="input-group col-lg-8">
                    <input type="text" class="form-control" name="postCode" value="{{person.postCode}}">
                  </div>
                </div>
                <!--最高学历--> 
                <div class="form-group searchs-unit">
                  <label for="exampleInputName4" class="col-lg-4">最高学历：</label>
                  <div class="col-lg-8" style="padding:0;">
                    <select class="form-control" name="degree">
                      <option {{if (person.degree == "小学")}}selected="true"{{/if}} value="小学">小学</option>
                      <option {{if (person.degree == "初中")}}selected="true"{{/if}} value="初中">初中</option>
                      <option {{if (person.degree == "高中")}}selected="true"{{/if}} value="高中">高中</option>
                      <option {{if (person.degree == "大专")}}selected="true"{{/if}} value="大专">大专</option>
                      <option {{if (person.degree == "本科")}}selected="true"{{/if}} value="本科">本科</option>
                      <option {{if (person.degree == "硕士")}}selected="true"{{/if}} value="硕士">硕士</option>
                      <option {{if (person.degree == "博士")}}selected="true"{{/if}} value="博士">博士</option>
                    </select>
                  </div>
                </div>
                <!--最高学位-->
                <div class="form-group searchs-unit">
                  <label for="exampleInputName4" class="col-lg-4">最高学位：</label>
                  <div class="col-lg-8" style="padding:0;">
                    <select class="form-control" name="education">
                      <option {{if (person.education == "" || person.education == null)}}selected="true"{{/if}} value=""></option>
                      <option {{if (person.education == "学士")}}selected="true"{{/if}} value="学士">学士</option>
                      <option {{if (person.education == "硕士")}}selected="true"{{/if}} value="硕士">硕士</option>
                      <option {{if (person.education == "博士")}}selected="true"{{/if}} value="博士">博士</option>
                    </select>
                  </div>
                </div>
                <!--学历证明-->   
                <div class="form-group searchs-unit" style=" width:92%;">
                  <label for="exampleInputName3" class="col-lg-2">学历证明：</label>
                  <input id="lefile1" type="file" style="display:none;" name="educationFile" multiple>
                  <div class="input-group col-lg-10" style=" text-align:left;">
                    <button type="button" class="btn btn-primary" onClick="$('input[id=lefile1]').click();">浏览</button>
                    {{if (xueli != null && xueli.length > 0)}}
                    <span style="color:green;">√</span>
                    <input id="photoCover1" class="input-large input-dianzis" style="display:none;" type="text">
                    {{else }}
                    <input id="photoCover1" class="input-large input-dianzis" type="text">
                    {{/if}}
                  </div>
                </div>
                <!--参加工作时间-->
                <div class="form-group searchs-unit">
                  <label for="exampleInputName4" class="col-lg-4">参加工作时间：</label>
                  <div class="input-group date col-lg-8" data-date-format="yyyy-mm-dd">
                    <input type="text" class="form-control" name="firstjobDate1" value="{{person.firstjobDate | dateFormat}}">
                    <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                  </div>
                </div>
                <!--档案存放处-->
                <div class="form-group searchs-unit">
                  <label for="exampleInputName4" class="col-lg-4">档案存放处：</label>
                  <div class="input-group col-lg-8">
                    <input type="text" class="form-control" name="fileLocation" value="{{person.fileLocation}}">
                  </div>
                </div>
                <!--是否外籍--> 
                <div class="form-group searchs-unit">
                  <label for="exampleInputName4" class="col-lg-4">是否外籍：</label>
                  <div class="col-lg-4">
                    <div class="sex_style" style=" text-align:left">
                      <span class="sex sex_third">是</span>
                      <input class="new_style_four sex" type="radio" name="isForeign" value="1" {{if (person.isForeign == 1)}}checked="true"{{/if}} />
                      <span class="sex sex_four">否</span>
                      <input class="new_style_four sex" type="radio" name="isForeign" value="0" {{if (person.isForeign == 0)}}checked="true"{{/if}} />
                    </div>
                  </div>
                </div>
                <!--是否海外留学-->
                <div class="form-group searchs-unit">
                  <label for="exampleInputName4" class="col-lg-4">是否海外留学：</label>
                  <div class="col-lg-4">
                    <div class="sex_style" style=" text-align:left">
                      <span class="sex sex_third">是</span>
                      <input class="new_style_four sex" type="radio" name="studyAbroad" value="1" {{if (person.studyAbroad == 1)}}checked="true"{{/if}} />
                      <span class="sex sex_four">否</span>
                      <input class="new_style_four sex" type="radio" name="studyAbroad" value="0" {{if (person.studyAbroad == 0)}}checked="true"{{/if}} />
                    </div>
                  </div>
                </div>
                <!--开户行--> 
                <div class="form-group searchs-unit">
                  <label for="exampleInputName4" class="col-lg-4">开户行：</label>
                  <div class="input-group col-lg-8">
                    <select class="form-control" name="bankName">
                      {{each bank as b i}}
                      <option {{if (b.baseDataId == person.bankName)}}selected="true"{{/if}} value="{{b.baseDataId}}">{{b.baseDataName}}</option>
                      {{/each}}
                    </select>
                  </div>
                </div>
                <!--开户支行-->
                <div class="form-group searchs-unit">
                  <label for="exampleInputName4" class="col-lg-4">开户支行：</label>
                  <div class="input-group col-lg-8">
                    <select class="form-control" name="subbank">
                      {{each bank as ba k}}
                      <option {{if (ba.baseDataId == person.subbank)}}selected="true"{{/if}} value="{{ba.baseDataId}}">{{ba.baseDataName}}</option>
                      {{/each}}
                    </select>
                  </div>
                </div>
                <!--工资卡号-->
                <div class="form-group searchs-unit">
                  <label for="exampleInputName4" class="col-lg-4">工资卡号：</label>
                  <div class="input-group col-lg-8">
                    <input type="text" class="form-control" name="salaryCard" value="{{person.salaryCard}}">
                  </div>
                </div>
                <!--电子简历-->
                <div class="form-group searchs-unit">
                  <label for="exampleInputName3" class="col-lg-4">电子简历：</label>
                  <input id="lefile2" type="file" style="display:none;" name="resumeFile" multiple>
                  <div class="input-group col-lg-8" style=" text-align:left;">
                    <button type="button" class="btn btn-primary" onClick="$('input[id=lefile2]').click();">浏览</button>
                    {{if (jianli != null && jianli.length > 0)}}
                    <span style="color:green;">√</span>
                    <input id="photoCover2" class="input-large input-dianzi" style="display:none;" type="text">
                    {{else }}
                    <input id="photoCover2" class="input-large input-dianzi" type="text">
                    {{/if}}
                  </div>
                </div>
                <!--户口薄-->
                <div class="form-group searchs-unit">
                  <label for="exampleInputName3" class="col-lg-4">户口簿：</label>
                  <input id="lefile3" type="file" style="display:none;" name="residenceFile" multiple>
                  <div class="input-group col-lg-8" style=" text-align:left;">
                    <button type="button" class="btn btn-primary" onClick="$('input[id=lefile3]').click();">浏览</button>
                    {{if (hukou !=null && hukou.length > 0)}}
                    <span style="color:green;">√</span>
                    <input id="photoCover3" class="input-large input-dianzi" style="display:none;" type="text">
                    {{else }}
                    <input id="photoCover3" class="input-large input-dianzi" type="text">
                    {{/if}}
                  </div>
                </div>
                <!--离职证明-->
                <div class="form-group searchs-unit">
                  <label for="exampleInputName3" class="col-lg-4">离职证明：</label>
                  <input id="lefile4" type="file" style="display:none;" name="jobpostFile" multiple>
                  <div class="input-group col-lg-8" style=" text-align:left;">
                    <button type="button" class="btn btn-primary" onClick="$('input[id=lefile4]').click();">浏览</button>
                    {{if (lizhi != null && lizhi.length > 0)}}
                    <span style="color:green;">√</span>
                    <input id="photoCover4" class="input-large input-dianzi" style="display:none;" type="text">
                    {{else }}
                    <input id="photoCover4" class="input-large input-dianzi" type="text">
                    {{/if}}
                  </div>
                </div> 
                <!--健康证明-->
                <div class="form-group searchs-unit">
                  <label for="exampleInputName3" class="col-lg-4">健康证明：</label>
                  <input id="lefile5" type="file" style="display:none;" name="healthFile" multiple>
                  <div class="input-group col-lg-8" style=" text-align:left;">
                    <button type="button" class="btn btn-primary" onClick="$('input[id=lefile5]').click();">浏览</button>
                    {{if (jiankang !=null && jiankang.length > 0)}}
                    <span style="color:green;">√</span>
                    <input id="photoCover5" class="input-large input-dianzi" style="display:none;" type="text">
                    {{else }}
                    <input id="photoCover5" class="input-large input-dianzi" type="text">
                    {{/if}}
                  </div>
                </div>
                <!--薪资证明-->
                <div class="form-group searchs-unit">
                  <label for="exampleInputName3" class="col-lg-4">薪资证明：</label>
                  <input id="lefile6" type="file" style="display:none;" name="salaryFile" multiple>
                  <div class="input-group col-lg-8" style=" text-align:left;">
                    <button type="button" class="btn btn-primary" onClick="$('input[id=lefile6]').click();">浏览</button>
                    {{if (xinzi != null && xinzi.length > 0)}}
                    <span style="color:green;">√</span>
                    <input id="photoCover6" class="input-large input-dianzi" style="display:none;" type="text">
                    {{else }}
                    <input id="photoCover6" class="input-large input-dianzi" type="text">
                    {{/if}}
                  </div>
                </div>

                <!--保存 删除-->
                <center style="clear:both; padding-top:30px;">
                  <button type="button" class="btn btn-info" onclick="updatePerson();">保存</button>
                </center>
                <!--图片--> 
                <div class="pucter" style=" position:absolute; left:74%; top:3%;">
                  <a href="javascript:"><img src="static/img/touxiang.jpg" width="114" height="170"></a>
                </div>     
              </form>
            </script>
          </div>
        </div>
      </div>
    </div>
  </div>
  <!--个人信息 end--> 
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
  function showPersonInfo(){
	  $.ajax({
	        cache: true,
	        type: "POST",
	        url:"employee/personalInfo.do",
	        data:{"personId":"${personId}"}, 
	        dataType: 'json',
	        async: false,
	        success: function(data) {
	        	$("#personal_div").html(template('personal', data));
	        	$('.input-group.date').datepicker({
	       		 format: "yyyy-mm-dd"
	              });
	        }
		});
	  
  }
  
  	function updatePerson(){
  		var object=$('#personalForm').serializeObject();
  		var json=JSON.stringify(object);
  		//上传图片
  	      $.ajax({
		    url: "employee/exportPerson.do",
		    type: 'POST',
		    cache: false,
		    dataType: 'json',
		    data: new FormData($('#personalForm')[0]),
		    processData: false,
		    contentType: false
		  }).done(function(data) {  
				$.ajax({
		  			type: "post",
		  		    url: "employee/updatePerson",
		  		    data: json,
		  		    contentType:"application/json",
		  		    dataType: "json",
		  		    async: false,
		  			success:function(data){
		  				//alert(data.msg);
		  				if(data.success == true){
		  					swal(data.msg, "", "success"); 	
		  					$("#personal_div").html();
		  					showPersonInfo();
		  					fileInputEvent();
		  				}else{
		  					swal(data.msg, "", "error"); 
		  				}
		  			}
		  		}); 
		 });       
		  
  	};
    $(function(){
      showPersonInfo();
      fileInputEvent();
  	 
  	
    });
    
    function fileInputEvent(){
    	 $(":file").change(function() {
    	  		$(this).siblings(".input-group").find("input").val($(this).val());
    	  	  });
    }
</script>
</body>
</html>