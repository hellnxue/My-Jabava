<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% %>
<!DOCTYPE html>
<html>
<head>
	<base href="${pageContext.request.contextPath}/">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0;" name="viewport"/>  
    <meta content="yes" name="apple-mobile-web-app-capable" />  
    <meta content="black" name="apple-mobile-web-app-status-bar-style" />  
    <meta content="telephone=no" name="format-detection" /> 

    <!-- Page title -->
    <title>员工录入界面</title>
    <link rel="stylesheet" href="static/bootstrap/vendor/fontawesome/css/font-awesome.css" />
    <link rel="stylesheet" href="static/bootstrap/vendor/metisMenu/dist/metisMenu.css" />
    <link rel="stylesheet" href="static/bootstrap/vendor/animate.css/animate.css" />
    <link rel="stylesheet" href="static/bootstrap/vendor/bootstrap/dist/css/bootstrap.css" />
    <link rel="stylesheet" href="static/bootstrap/vendor/xeditable/bootstrap3-editable/css/bootstrap-editable.css" />
    <link rel="stylesheet" href="static/bootstrap/vendor/select2-3.5.2/select2.css" />
    <link rel="stylesheet" href="static/bootstrap/vendor/select2-bootstrap/select2-bootstrap.css" />
    <link rel="stylesheet" href="static/bootstrap/vendor/bootstrap-touchspin/dist/jquery.bootstrap-touchspin.min.css" />
    <link rel="stylesheet" href="static/bootstrap/vendor/bootstrap-datepicker-master/dist/css/bootstrap-datepicker3.min.css" />
    <link rel="stylesheet" href="static/bootstrap/vendor/awesome-bootstrap-checkbox/awesome-bootstrap-checkbox.css" />

      
    <!-- for data table -->
    <link rel="stylesheet" href="static/bootstrap/vendor/datatables_plugins/integration/bootstrap/3/dataTables.bootstrap.css" />
    <!-- for alert -->
     <link rel="stylesheet" href="static/bootstrap/vendor/sweetalert/lib/sweet-alert.css" />

    <!-- App styles -->
    <link rel="stylesheet" href="static/bootstrap/fonts/pe-icon-7-stroke/css/pe-icon-7-stroke.css" />
    <link rel="stylesheet" href="static/bootstrap/fonts/pe-icon-7-stroke/css/helper.css" />
    <link rel="stylesheet" href="static/bootstrap/styles/style.css">
    <!--for 临时改变-->
	  <link rel="stylesheet" id="link" href="static/css/denglu.css" type="text/css">
	<style>
	body.modal-open {
    
}
	</style>
</head>
<body class="blank" style="position:static;">

<!-- Simple splash screen-->
<div class="splash"> 
 <div class="color-line"></div><div class="splash-title">
<h1>Jabava V1.0 </h1>
<p>属于你的专业人事专员</p>
<img src="static/bootstrap/images/loading-bars.svg" width="64" height="64" /> 
</div> 
</div>
<div class="register-container containers">
    <div class="row">
        <div class="col-md-12">
            <div class="hpanel">
                <div class="panel-body">
                    <div class="row">
                    <!--头部-->
                        <div class="tuichu">
                        	<h5><a href="denglu.html"><img src="static/img/u4.png" width="20" height="20"></a><span>退出</span></h5>
                        </div>
                     <!--个人信息-->  
                     <div class="inform">
                     	<div class="inleft">
                            <div class="imgs">
                            	<a href="#"><img src="static/img/u23.png" width="100" height="100"></a>
                            </div>
                            <div class="xinxi">
                            	<p><h5>陈俊男</h5></p>
                                <p class="qi">13816154943</p>
                                <p class="qi">709914199@qq.com</p>
                            </div>
                        </div>
                        <div class="inright">
                        	<a href="#" data-target="#myModal7_1" data-toggle="modal"><img src="static/img/u327.png" width="41" height="39"></a>
                        </div>
                     </div>
                     <!--inform end--> 
                    </div>
                </div>
            </div>
        </div>
     </div>
    <!--列表-->
      <div class="liebiao">
           <ul>
            	<li><span data-target="#myModal7_1" data-toggle="modal">+</span>个人信息</li>
                <li><span data-target="#myModal7" data-toggle="modal">+</span>教育背景</li>
                <li><span data-target="#myModal8" data-toggle="modal">+</span>工作经验</li>
                <li><span data-target="#myModal8_1" data-toggle="modal">+</span>项目经验</li>
                <li><span data-target="#myModal9" data-toggle="modal">+</span>培训经历</li>
                <li><span data-target="#myModal9_1" data-toggle="modal">+</span>获得证书</li>
                <li><span data-target="#myModal10" data-toggle="modal">+</span>家庭成员</li>
                <li><span data-target="#myModal10_1" data-toggle="modal">+</span>上传附件</li>
           </ul>
       </div>
       
       
       <!--个人信息弹框-->   
       			<div class="register-container containers"> 
                	<div class="row">
                    <div class="col-lg-12">
                 <div class="modal fade hmodal-success form-row" id="myModal7_1" data-next-modal="myModal7" tabindex="-1" role="dialog"  aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                             <div class="blue">
                        	 <h5><a class="modalHide"><img src="static/img/u4.png" width="20" height="20"></a><span class="gereninform">个人信息</span><span class="baocun" data-click-target="saveAndClose">保存关闭</span></h5>
                            </div>
                            <div class="modal-header">
                             <div class="row">
                            <!--个人信息-->
                 		<div class="panel-body grxx" style=" background:none;">
                              <form role="form" class="form-horizontal formclass" data-add-url="employee/updatePerson" > 
                              <!--联系电话-->
                                <div class="form-group searchs-unit displays">
                                 <label for="exampleInputName4" class="col-lg-3 tongyong">联系电话：</label>
                                 <div class="input-group col-lg-8 displays">
                                  <input type="text" class="form-control"   name="phone">
                                   </div>
                                   </div>
                                    <!--出生日期-->
                                  <div class="form-group searchs-unit displays">
                                 <label for="exampleInputName4" class="col-lg-3 tongyong">出生日期：</label>
                                 <div class="input-group date col-lg-8 displays">
                                  <input type="text" class="form-control displays"   name="birthDate1">
                                  <span class="input-group-addon "><i class="glyphicon glyphicon-th"></i></span>
                                 </div>
                                 </div>
                                   <!-- 身份证号-->
                                  <div class="form-group searchs-unit displays">
                                 <label for="exampleInputName4" class="col-lg-3 tongyong">身&nbsp;&nbsp;份&nbsp;&nbsp;证：</label>
                                 <div class="input-group col-lg-8 displays">
                                  <input type="text" class="form-control"  name="certId">
                                   <!--<span class="reds">*</span>-->
                                   </div>
                                   </div>
                                <!--民族-->	
                                 <div class="form-group searchs-unit displays">
                                 <label for="exampleInputName4" class="col-lg-3 tongyong">民&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;族：</label>
                                 <div class="input-group col-lg-8 displays">
                                  <input type="text" class="form-control"   name="nationality">
                                   </div>
                                   </div>
                               	<!--籍贯-->
                                  <div class="form-group searchs-unit displays">
                                 <label for="exampleInputName4" class="col-lg-3 tongyong">籍&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;贯：</label>
                                 <div class="input-group col-lg-8 displays">
                                  <input type="text" class="form-control"  name="originPlace">
                                   </div>
                                   </div>
                               <!--婚姻状况-->
                                 <div class="form-group searchs-unit displays">
                                    <label for="exampleInputName4" class="col-lg-3 tongyong">婚姻状况：</label>
                                    <div class="col-lg-8 displays" style="padding:0;">
                                    <select class="form-control " name="marital" placeholder="未婚">
                                      <option   value="0">未婚</option>
				                      <option   value="1">已婚</option>
				                      <option   value="2">离异</option>
				                      <option   value="3">丧偶</option>
                                    </select>
                                    </div>
                                </div>
                               <!--户籍类型-->
                               	<div class="form-group searchs-unit displays" style=" display:block">
                                    <label for="exampleInputName4" class="col-lg-3 tongyong">户籍类型：</label>
                                    <div class="col-lg-8 displays" style="padding:0;">
                                    <select class="form-control " name="registerType" placeholder="城镇户口">
                                        <option value="0">城镇户口</option>
                                        <option value="1">农村户口</option>
                                    </select>
                                    </div>
                                </div>
                               <!--户口所在地-->
                               <div class="form-group searchs-unit displays">
                                 <label for="exampleInputName4" class="col-lg-3 tongyong">户口所在：</label>
                                 <div class="input-group col-lg-8 displays">
                                  <input type="text" class="form-control" name="registerLocation">
                                   </div>
                                   </div>
                               <!--现居住地-->
                               <div class="form-group searchs-unit displays">
                                 <label for="exampleInputName4" class="col-lg-3 tongyong">现居住地：</label>
                                 <div class="input-group col-lg-8 displays">
                                  <input type="text" class="form-control" name="contactAddress">
                                   </div>
                                   </div>
                               <!--家庭住址-->
                               	<div class="form-group searchs-unit displays">
                                 <label for="exampleInputName4" class="col-lg-3 tongyong">家庭地址：</label>
                                 <div class="input-group col-lg-8 displays">
                                  <input type="text" class="form-control" name="familyAddress">
                                   </div>
                                   </div>
                            <!--邮编-->
                            <div class="form-group searchs-unit displays">
                                 <label for="exampleInputName4" class="col-lg-3 tongyong">邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;编：</label>
                                 <div class="input-group col-lg-8 displays">
                                  <input type="text" class="form-control" name="postCode">
                                  </div>
                            </div>
                             <!--最高学历--> 
                              <div class="form-group searchs-unit displays">
                                    <label for="exampleInputName4" class="col-lg-3 tongyong">最高学历：</label>
                                    <div class="col-lg-8 displays" style="padding:0;">
                                    <select class="form-control " name="degree" placeholder="本科">
					                      <option   value="小学">小学</option>
					                      <option   value="初中">初中</option>
					                      <option   value="高中">高中</option>
					                      <option   value="大专">大专</option>
					                      <option   value="本科">本科</option>
					                      <option   value="硕士">硕士</option>
					                      <option   value="博士">博士</option>
                                    </select>
                                    </div>
                                </div>
                               <!--最高学位-->
                               <div class="form-group searchs-unit displays" style=" display:block">
                                    <label for="exampleInputName4" class="col-lg-3 tongyong">最高学位：</label>
                                    <div class="col-lg-8 displays" style="padding:0;">
                                        <select class="form-control" name="education" placeholder="学士">
						                      <option   value=""></option>
						                      <option   value="学士">学士</option>
						                      <option   value="硕士">硕士</option>
						                      <option   value="博士">博士</option>
                                        </select>
                                    </div>
                                </div>
                                 <!--档案存放处-->
                               	  <div class="form-group searchs-unit displays">
                                 <label for="exampleInputName4" class="col-lg-3 tongyong">档案存放：</label>
                                 <div class="input-group col-lg-8 displays">
                                  <input type="text" class="form-control" name="fileLocation">
                                  </div>
                            </div>
                               <!--参加工作时间-->
                               	<div class="form-group searchs-unit displays">
                                     <label for="exampleInputName4" class="col-lg-3 tongyong">工作时间：</label>
                                     <div class="input-group date col-lg-8 displays">
                                      <input type="text" class="form-control" name="firstjobDate1">
                                      <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                     </div>
                                 </div>
                               
                            <!--是否外籍--> 
                                 <div class="form-group searchs-unit displays">
                                   <label for="exampleInputName4" class="col-lg-3 tongyong">是否外籍：</label>
                                    <div class="col-lg-8 displays">
                                      <div class="sex_style" style=" text-align:left">
                                      <span class="sex sex_third">是</span>
                                  <input class="new_style_four sex" type="radio" id="optionsRadios1"  name="isForeign" value="1" checked="" />
                                    <span class="sex sex_four">否</span>
                                        <input class="new_style_four sex" type="radio" id="optionsRadios2" name="isForeign" value="0" checked="" />
                                      </div>
                                    </div>
                                    </div>
                                  <!--是否海外留学-->
                                  <div class="form-group searchs-unit displays">
                                    <label for="exampleInputName4" class="col-lg-3 tongyong">是否留学：</label>
                                    <div class="col-lg-8 displays">
                                      <div class="sex_style" style=" text-align:left">
                                      <span class="sex sex_third">是</span>
                                  <input class="new_style_four sex" type="radio" id="optionsRadios3"  name="studyAbroad" value="1" checked="" />
                                    <span class="sex sex_four">否</span>
                                        <input class="new_style_four sex" type="radio" id="optionsRadios4"  name="studyAbroad" value="0" checked="" />
                                      </div>
                                    </div>
                                    </div>
                              <!--开户行--> 
                               <div class="form-group searchs-unit displays">
                                 <label for="exampleInputName4" class="col-lg-3 tongyong">开&nbsp;&nbsp;户&nbsp;&nbsp;行：</label>
                                 <div class="input-group col-lg-8 displays">
		                            <select class="form-control" name="bankName">
				                    <c:forEach items="${bank}" var="b">
				                         <option value="${b.baseDataId}">
				                         ${b.baseDataName}
				                         </option>
				                    </c:forEach>
				                    </select>
                                  </div>
                            </div>
                             <!--开户支行-->
                              <div class="form-group searchs-unit displays">
                                 <label for="exampleInputName4" class="col-lg-3 tongyong">开户支行：</label>
                                 <div class="input-group col-lg-8 displays">
                                 <select class="form-control" name="subbank">
                                    <c:forEach items="${bank}" var="b">
				                         <option value="${b.baseDataId}">
				                        ${b.baseDataName}
				                         </option>
				                    </c:forEach>
				                    </select>
                                  </div>
                            </div>
                             <!--工资卡号-->
                                <div class="form-group searchs-unit displays">
                                 <label for="exampleInputName4" class="col-lg-3 tongyong">工资卡号：</label>
                                 <div class="input-group col-lg-8 displays">
                                  <input type="text" class="form-control" name="salaryCard">
                                  </div>
                            </div>
                               <!--保存-->
                               <center style="clear:both; padding-top:30px;">
                               		<button type="button" class="btn btn-info" data-click-target="onlySave">保存并填写下一项</button>
                               </center>
                              </form>
                             
                              </div>
                     <!--个人信息 end--> 
                            </div>   
                        </div>
                    </div>
                </div>
                </div>
                </div>
                </div>
                </div>
                <!--个人信息弹框 end-->
                
                <!--教育背景弹框-->    
                 <div class="modal fade hmodal-success form-row" id="myModal7" data-next-modal="myModal8"  tabindex="-1" role="dialog"  aria-hidden="true" >
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="blue">
                        	 <h5><a  class="modalHide"><img src="static/img/u4.png" width="20" height="20"></a><span class="gereninform">教育背景</span><span class="baocun" data-click-target="saveAndClose">保存关闭</span></h5>
                            </div>
                            <div class="modal-header">
                             <div class="row">
                            <!--教育背景-->
                 		     <div class="panel-body jiaoyu" style=" background:none;">
                              <form role="form" class=" form-horizontal formclass" id="educational_form"  data-add-url="employee/addEducation"> 
                              <!--入学时间-->
                                    <div class="form-group searchs-unit displays">
                                     <label for="exampleInputName4" class="col-lg-3 tongyong">入学时间：</label>
                                     <div class="input-group date col-lg-8 displays formclass">
                                      <input type="text" class="form-control" name="entranceDate">
                                      <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                     </div>
                                 </div>
                                    <!--毕业时间-->
                                     <div class="form-group searchs-unit displays">
                                     <label for="exampleInputName4" class="col-lg-3 tongyong" >毕业时间：</label>
                                     <div class="input-group date col-lg-8 displays">
                                      <input type="text" class="form-control" name="graduateDate">
                                      <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                     </div>
                                 </div>
                                    <!--毕业学校-->
                                    <div class="form-group searchs-unit displays">
                                 <label for="exampleInputName4" class="col-lg-3 tongyong">毕业学校：</label>
                                 <div class="input-group col-lg-8 displays">
                                  <input type="text" class="form-control" name="graduateSchool">
                                   </div>
                               </div>
                                    <!--所学专业-->
                                <div class="form-group searchs-unit displays">
                                 <label for="exampleInputName4" class="col-lg-3 tongyong">所学专业：</label>
                                 <div class="input-group col-lg-8 displays">
                                  <input type="text" class="form-control" name="major">
                                   </div>
                               </div>
                                   <!--学习形式--> 
                                   <div class="form-group searchs-unit displays">
                                    <label for="exampleInputName4" class="col-lg-3 tongyong">学习形式：</label>
                                    <div class="col-lg-6 displays">
                                      <div class="sex_style">
                                      <span class="sex sex_third">全日制</span>
                                     <input class="new_style_four sex" type="radio" name="learnType" value="全日制" checked="" />
                                    <span class="sex sex_four">非全日制</span>
                                    <input class="new_style_four sex" type="radio" iname="learnType" value="非全日制" checked="" />
                                      </div>
                                    </div>
                                    </div>
                                <!--学制-->
                                 <div class="form-group searchs-unit displays">
                                 <label for="exampleInputName4" class="col-lg-3 tongyong">学&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;制：</label>
                                 <div class="input-group col-lg-8 displays">
                                  <input type="text" class="form-control" name="schoolTime">
                                   </div>
                               </div>
                                 <!--学历-->  
                                   <div class="form-group searchs-unit displays">
                                  <label for="exampleInputName4" class="col-lg-3 tongyong">学&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;历：</label>
                                    <div class="col-lg-8 displays" style="padding:0;">
                                    <select class="form-control" name="education" placeholder="本科">
                                        <option>本科</option>
                                        <option>大专</option>
                                        <option>高中</option>
                                        <option>初中</option>
                                        <option>小学</option>
                                        <option>博士</option>
                                    </select>
                                    </div>
                                    </div>  
                                  <!--证书编号-->  
                                   <div class="form-group searchs-unit displays">
                                 <label for="exampleInputName4" class="col-lg-3 tongyong">证书编号：</label>
                                 <div class="input-group col-lg-8 displays">
                                  <input type="text" class="form-control" name="educationCertificate">
                                   </div>
                               </div>
                               <!--保存-->
                               <center style="clear:both; padding-top:30px;">
                               		<button type="button" class="btn btn-info" id="educationalSaveNext" data-click-target="onlySave">保存并填写下一项</button>
                               </center>
                              </form>
                             
                              </div>
                     <!--教育背景 end--> 
                            </div>   
                        </div>
                    </div>
                </div>
                </div>
                <!--教育背景弹框 end-->
       			 <!--工作经验弹框-->    
                 <div class="modal fade hmodal-success form-row" id="myModal8" data-next-modal="myModal8_1" tabindex="-1" role="dialog"  aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="blue">
                        	 <h5><a class="modalHide"><img src="static/img/u4.png" width="20" height="20"></a><span class="gereninform">工作经验</span><span class="baocun" data-click-target="saveAndClose">保存关闭</span></h5>
                            </div>
                            <div class="modal-header">
                             <div class="row">
                            <!--工作经验-->
                 		<div class="panel-body gongzuojy" style=" background:none;">
                              <form role="form" class="form-horizontal formclass" data-add-url="employee/addExperience"> 
                              <!--起始时间-->
                                     <div class="form-group searchs-unit displays">
                                     <label for="exampleInputName4" class="col-lg-3 tongyong">起始时间：</label>
                                     <div class="input-group date col-lg-8 displays">
                                      <input type="text" class="form-control" name="startDate">
                                      <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                     </div>
                                 </div>
                                    <!--终止时间-->
                                      <div class="form-group searchs-unit displays">
                                     <label for="exampleInputName4" class="col-lg-3 tongyong">终止时间：</label>
                                     <div class="input-group date col-lg-8 displays">
                                      <input type="text" class="form-control" name="endDate">
                                      <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                     </div>
                                 </div>
                                    <!--工作单位-->
                                    <div class="form-group searchs-unit displays">
                                 <label for="exampleInputName4" class="col-lg-3 tongyong">工作单位：</label>
                                 <div class="input-group col-lg-8 displays">
                                  <input type="text" class="form-control" name="employer">
                                   </div>
                               </div>
                                    <!--工作岗位-->
                                    <div class="form-group searchs-unit displays">
                                 <label for="exampleInputName4" class="col-lg-3 tongyong">工作岗位：</label>
                                 <div class="input-group col-lg-8 displays">
                                  <input type="text" class="form-control" name="workPost">
                                   </div>
                               </div>
                                    
                                  <!--证明人-->  
                                   <div class="form-group searchs-unit displays">
                                 <label for="exampleInputName4" class="col-lg-3 tongyong">证&nbsp;&nbsp;明&nbsp;&nbsp;人：</label>
                                 <div class="input-group col-lg-8 displays">
                                  <input type="text" class="form-control" name="authenticator">
                                   </div>
                               </div>
                                 <!--职责描述-->
                                 <div class="form-group searchs-unit displays">
                                     <label for="exampleInputName4" class="col-lg-3 tongyong">职责描述：</label>
                                    <div class="col-lg-8 displays" style="padding:0;">
                                    <textarea class="form-control" name="description"></textarea>
                                    </div>
                                 </div> 
                               <!--保存-->
                               <center style="clear:both; padding-top:30px;">
                               		<button type="button" class="btn btn-info" data-click-target="onlySave">保存并填写下一项</button>
                               </center>
                              </form>
                             
                              </div>
                     <!--工作经验 end--> 
                            </div>   
                        </div>
                    </div>
                </div>
                </div>
                <!--工作经验弹框 end-->
                
                 <!--项目经验弹框-->    
                 <div class="modal fade hmodal-success form-row" id="myModal8_1" data-next-modal="myModal9" tabindex="-1" role="dialog"  aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="blue">
                        	 <h5><a class="modalHide"><img src="static/img/u4.png" width="20" height="20"></a><span class="gereninform">项目经验</span><span class="baocun" data-click-target="saveAndClose">保存关闭</span></h5>
                            </div>
                            <div class="modal-header">
                             <div class="row">
                            <!--项目经验-->
                 		<div class="panel-body xiangmujy" style=" background:none;">
                              <form role="form" class="form-horizontal formclass" data-add-url="employee/addProject"> 
                             <!--项目起始时间-->
                                     <div class="form-group searchs-unit displays">
                                     <label for="exampleInputName4" class="col-lg-3 tongyong">起始时间：</label>
                                     <div class="input-group date col-lg-8 displays">
                                      <input type="text" class="form-control" id="exampleInputName2">
                                      <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                     </div>
                                 </div>
                                    <!--项目终止时间-->
                                     <div class="form-group searchs-unit displays">
                                     <label for="exampleInputName4" class="col-lg-3 tongyong">终止时间：</label>
                                     <div class="input-group date col-lg-8 displays">
                                      <input type="text" class="form-control" id="exampleInputName2">
                                      <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                     </div>
                                 </div>
                                    <!--项目名称-->
                                     <div class="form-group searchs-unit displays">
                                 <label for="exampleInputName4" class="col-lg-3 tongyong">项目名称：</label>
                                 <div class="input-group col-lg-8 displays">
                                  <input type="text" class="form-control" id="exampleInputName2">
                                   </div>
                               </div>
                                    <!--项目金额-->
                                     <div class="form-group searchs-unit displays">
                                 <label for="exampleInputName4" class="col-lg-3 tongyong">项目金额：</label>
                                 <div class="input-group col-lg-8 displays">
                                  <input type="text" class="form-control" id="exampleInputName2">
                                   </div>
                               </div>
                                 <!--项目职责--> 
                                  <div class="form-group searchs-unit displays">
                                    <label for="exampleInputName4" class="col-lg-3 tongyong">项目职责：</label>
                                    <div class="col-lg-8 displays" style="padding:0;">
                                    <textarea class="form-control" id="exampleInputName3"></textarea>
                                    </div>
                                 </div> 
                                 <!--职责描述-->
                                 <div class="form-group searchs-unit displays">
                                    <label for="exampleInputName4" class="col-lg-3 tongyong">职责描述：</label>
                                    <div class="col-lg-8 displays" style="padding:0;">
                                    <textarea class="form-control" id="exampleInputName3"></textarea>
                                    </div>
                                 </div> 
                               <!--保存-->
                               <center style="clear:both; padding-top:30px;">
                               		<button type="button" class="btn btn-info" data-click-target="onlySave">保存并填写下一项</button>
                               </center>
                              </form>
                             
                              </div>
                     <!--项目经验 end--> 
                            </div>   
                        </div>
                    </div>
                </div>
                </div>
                <!--项目经验弹框 end-->
                
                <!--培训经历弹框-->    
                 <div class="modal fade hmodal-success form-row" id="myModal9" data-next-modal="myModal9_1" tabindex="-1" role="dialog"  aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="blue">
                        	 <h5><a class="modalHide"><img src="static/img/u4.png" width="20" height="20"></a><span class="gereninform">培训经历</span><span class="baocun" data-click-target="saveAndClose">保存关闭</span></h5>
                            </div>
                            <div class="modal-header">
                             <div class="row">
                            <!--培训经历-->
                 		<div class="panel-body peixun_jl" style=" background:none;">
                              <form role="form" class="form-horizontal formclass" data-add-url="employee/addTrain"> 
                              <!--培训开始时间-->
                                   <div class="form-group searchs-unit displays">
                                     <label for="exampleInputName4" class="col-lg-3 tongyong">开始时间：</label>
                                     <div class="input-group date col-lg-8 displays">
                                      <input type="text" class="form-control" id="exampleInputName2">
                                      <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                     </div>
                                 </div>
                                    <!--培训结束时间-->
                                      <div class="form-group searchs-unit displays">
                                     <label for="exampleInputName4" class="col-lg-3 tongyong">结束时间：</label>
                                     <div class="input-group date col-lg-8 displays">
                                      <input type="text" class="form-control" id="exampleInputName2">
                                      <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                     </div>
                                 </div>
                                    <!--课程名称-->
                                     <div class="form-group searchs-unit displays">
                                 <label for="exampleInputName4" class="col-lg-3 tongyong">课程名称：</label>
                                 <div class="input-group col-lg-8 displays">
                                  <input type="text" class="form-control" id="exampleInputName2">
                                   </div>
                               </div>
                                    <!--培训费用-->
                                     <div class="form-group searchs-unit displays">
                                 <label for="exampleInputName4" class="col-lg-3 tongyong">培训费用：</label>
                                 <div class="input-group col-lg-8 displays">
                                  <input type="text" class="form-control" id="exampleInputName2">
                                   </div>
                               </div>
                                   <!--培训机构-->
                                    <div class="form-group searchs-unit displays">
                                 <label for="exampleInputName4" class="col-lg-3 tongyong">培训机构：</label>
                                 <div class="input-group col-lg-8 displays">
                                  <input type="text" class="form-control" id="exampleInputName2">
                                   </div>
                               </div>
                                    <!--培训课时-->
                               <div class="form-group searchs-unit displays">
                                 <label for="exampleInputName4" class="col-lg-3 tongyong">培训课时：</label>
                                 <div class="input-group col-lg-8 displays">
                                  <input type="text" class="form-control" id="exampleInputName2">
                                   </div>
                               </div>
                                 <!--所得证书--> 
                                   <div class="form-group searchs-unit displays">
                                 <label for="exampleInputName4" class="col-lg-3 tongyong">所得证书：</label>
                                 <div class="input-group col-lg-8 displays">
                                  <input type="text" class="form-control" id="exampleInputName2">
                                   </div>
                               </div>
                               <!--保存-->
                               <center style="clear:both; padding-top:30px;">
                               		<button type="button" class="btn btn-info" data-click-target="onlySave">保存并填写下一项</button>
                               </center>
                              </form>
                             
                              </div>
                     <!--培训经历 end--> 
                            </div>   
                        </div>
                    </div>
                </div>
                </div>
                <!--培训经历弹框 end-->
                
                <!--获得证书弹框-->    
                 <div class="modal fade hmodal-success form-row" id="myModal9_1" data-next-modal="myModal10" tabindex="-1" role="dialog"  aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="blue">
                        	 <h5><a class="modalHide"><img src="static/img/u4.png" width="20" height="20"></a><span class="gereninform">获得证书</span><span class="baocun" data-click-target="saveAndClose">保存关闭</span></h5>
                            </div>
                            <div class="modal-header">
                             <div class="row">
                            <!--获得证书-->
                 		<div class="panel-body huode" style=" background:none;">
                              <form role="form" class="form-horizontal formclass" data-add-url="employee/addCertificate"> 
                              <!--取得证书时间-->
                                     <div class="form-group searchs-unit displays">
                                     <label for="exampleInputName4" class="col-lg-3 tongyong">证书时间：</label>
                                     <div class="input-group date col-lg-8 displays">
                                      <input type="text" class="form-control" id="exampleInputName2">
                                      <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                     </div>
                                 </div>
                                    <!--证书有效期-->
                                     <div class="form-group searchs-unit displays">
                                 <label for="exampleInputName4" class="col-lg-3 tongyong">有&nbsp;&nbsp;效&nbsp;&nbsp;期：</label>
                                 <div class="input-group col-lg-8 displays">
                                  <input type="text" class="form-control" id="exampleInputName2">
                                   </div>
                               </div>
                                    <!--证书名称-->
                                     <div class="form-group searchs-unit displays">
                                 <label for="exampleInputName4" class="col-lg-3 tongyong">证书名称：</label>
                                 <div class="input-group col-lg-8 displays">
                                  <input type="text" class="form-control" id="exampleInputName2">
                                   </div>
                               </div>
                                 <!--电子版上传--> 
                                  <div class="form-group searchs-unit displays">
                                    <label for="exampleInputName3" class="col-lg-3 tongyong">电子上传：</label>
                                    <div class="input-group col-lg-3 displays" style="display:inline">
                                     <input type="file" class="displays" style="display:inline; width:50%;">
                                    </div>
                                    </div> 
                                 <!--备注-->
                                <div class="form-group searchs-unit displays">
                                    <label for="exampleInputName4" class="col-lg-3 tongyong">备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：</label>
                                    <div class="col-lg-8 displays" style="padding:0;">
                                    <textarea class="form-control" id="exampleInputName3"></textarea>
                                    </div>
                                 </div> 
                               <!--保存-->
                               <center style="clear:both; padding-top:30px;">
                               		<button type="button" class="btn btn-info" data-click-target="onlySave">保存并填写下一项</button>
                               </center>
                              </form>
                             
                              </div>
                     <!--获得证书 end--> 
                            </div>   
                        </div>
                    </div>
                </div>
                </div>
                <!--获得证书弹框 end-->
                
                <!--家庭成员弹框-->    
                 <div class="modal fade hmodal-success form-row" id="myModal10" data-next-modal="myModal10_1" tabindex="-1" role="dialog"  aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="blue">
                        	 <h5><a class="modalHide"><img src="static/img/u4.png" width="20" height="20"></a><span class="gereninform">家庭成员</span><span class="baocun" data-click-target="saveAndClose">保存关闭</span></h5>
                            </div>
                            <div class="modal-header">
                             <div class="row">
                            <!--家庭成员-->
                 		<div class="panel-body famliy_cy" style=" background:none;">
                              <form role="form" class="form-horizontal formclass" data-add-url="employee/addFamily"> 
                              <!--成员姓名-->
                                    <div class="form-group searchs-unit displays">
                                 <label for="exampleInputName4" class="col-lg-3 tongyong">成员姓名：</label>
                                 <div class="input-group col-lg-8 displays">
                                  <input type="text" class="form-control" id="exampleInputName2">
                                   </div>
                               </div>
                                    <!--与本人关系-->
                                    <div class="form-group searchs-unit displays">
                                   <label for="exampleInputName4" class="col-lg-3 tongyong">本人关系：</label>
                                    <div class="col-lg-8 displays" style="padding:0;">
                                    <select class="form-control " name="account" placeholder="父母">
                                     <option>父母</option>
                                     <option>配偶</option>
                                     <option>儿子</option>
                                     <option>女儿</option>
                                     <option>其他</option>
                                    </select>
                                    </div>
                                    </div>  
                                   <!--居住城市-->
                                    <div class="form-group searchs-unit displays">
                                 <label for="exampleInputName4" class="col-lg-3 tongyong">居住城市：</label>
                                 <div class="input-group col-lg-8 displays">
                                  <input type="text" class="form-control" id="exampleInputName2">
                                   </div>
                               </div>
                                    <!--出生日期-->
                                     <div class="form-group searchs-unit displays">
                                     <label for="exampleInputName4" class="col-lg-3 tongyong">出生时间：</label>
                                     <div class="input-group date col-lg-8 displays">
                                      <input type="text" class="form-control" id="exampleInputName2">
                                      <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                     </div>
                                 </div>
                                   <!--联系电话-->
                                   <div class="form-group searchs-unit displays">
                                 <label for="exampleInputName4" class="col-lg-3 tongyong">联系电话：</label>
                                 <div class="input-group col-lg-8 displays">
                                  <input type="text" class="form-control" id="exampleInputName2">
                                   </div>
                               </div>
                                   <!--身份证号--> 
                                  <div class="form-group searchs-unit displays">
                                 <label for="exampleInputName4" class="col-lg-3 tongyong">身份证号：</label>
                                 <div class="input-group col-lg-8 displays">
                                  <input type="text" class="form-control" id="exampleInputName2">
                                   </div>
                               </div>
                                 <!--工作单位--> 
                                  <div class="form-group searchs-unit displays">
                                 <label for="exampleInputName4" class="col-lg-3 tongyong">工作单位：</label>
                                 <div class="input-group col-lg-8 displays">
                                  <input type="text" class="form-control" id="exampleInputName2">
                                   </div>
                               </div>
                               <!--保存-->
                               <center style="clear:both; padding-top:30px;">
                               		<button type="button" class="btn btn-info" data-click-target="onlySave">保存并填写下一项</button>
                               </center>
                              </form>
                             
                              </div>
                     <!--家庭成员 end--> 
                            </div>   
                        </div>
                    </div>
                </div>
                </div>
                <!--家庭成员弹框 end-->
                
                <!--上传附件弹框-->    
                 <div class="modal fade hmodal-success form-row" id="myModal10_1" data-next-modal="myModal7_1" tabindex="-1" role="dialog"  aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="blue">
                        	 <h5><a class="modalHide"><img src="static/img/u4.png" width="20" height="20"></a><span class="gereninform">上传附件</span><span class="baocun" data-click-target="saveAndClose">保存关闭</span></h5>
                            </div>
                            <div class="modal-header">
                             <div class="row">
                            <!--上传附件-->
                 		<div class="panel-body fujian" style=" background:none;">
                              <form role="form" class="form-horizontal formclass" data-add-url=""> 
                              <!--身份证--> 
                                   <div class="form-group searchs-unit displays">
                                    <label for="exampleInputName3" class="col-lg-3 ">身&nbsp;&nbsp;份&nbsp;&nbsp;证：</label>
                                    <div class="input-group col-lg-3 displays" style="display:inline">
                                     <input type="file" class="displays" style="display:inline; width:50%;">
                                    </div>
                                    </div>
                                     <!--户口簿--> 
                                   <div class="form-group searchs-unit displays">
                                    <label for="exampleInputName3" class="col-lg-3">户&nbsp;&nbsp;口&nbsp;&nbsp;簿：</label>
                                    <div class="input-group col-lg-3 displays" style="display:inline">
                                     <input type="file" class="displays" style="display:inline; width:50%;">
                                    </div>
                                    </div>
                                   <!--学历证--> 
                                  <div class="form-group searchs-unit displays">
                                    <label for="exampleInputName3" class="col-lg-3">学&nbsp;&nbsp;历&nbsp;&nbsp;证：</label>
                                    <div class="input-group col-lg-3 displays" style="display:inline">
                                     <input type="file" class="displays" style="display:inline; width:50%;">
                                    </div>
                                    </div>
                                     <!--简历--> 
                                   <div class="form-group searchs-unit displays">
                                    <label for="exampleInputName3" class="col-lg-3">简&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;历：</label>
                                    <div class="input-group col-lg-3 displays" style="display:inline">
                                     <input type="file" class="displays" style="display:inline; width:50%;">
                                    </div>
                                    </div>
                                    <!--离职证明--> 
                                   <div class="form-group searchs-unit displays">
                                    <label for="exampleInputName3" class="col-lg-3 ">离职证明：</label>
                                    <div class="input-group col-lg-3 displays" style="display:inline">
                                     <input type="file" class="displays" style="display:inline; width:50%;">
                                    </div>
                                    </div>
                                   <!--健康证明--> 
                                   <div class="form-group searchs-unit displays">
                                    <label for="exampleInputName3" class="col-lg-3 ">健康证明：</label>
                                    <div class="input-group col-lg-3 displays" style="display:inline">
                                     <input type="file" class="displays" style="display:inline; width:50%;">
                                    </div>
                                    </div>
                                 <!--薪资证明--> 
                                  <div class="form-group searchs-unit displays">
                                    <label for="exampleInputName3" class="col-lg-3">薪资证明：</label>
                                    <div class="input-group col-lg-3 displays" style="display:inline">
                                     <input type="file" class="displays" style="display:inline; width:50%;">
                                    </div>
                                    </div> 
                               <!--保存-->
                               <center style="clear:both; padding-top:30px;">
                               		<button type="button" class="btn btn-info" data-click-target="onlySave">保存并填写下一项</button>
                               </center>
                              </form>
                             
                              </div>
                     <!--上传附件 end--> 
                            </div>   
                        </div>
                    </div>
                </div>
                </div>
                <!--上传附件弹框 end-->
        
    
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
	<script src="static/bootstrap/vendor/xeditable/bootstrap3-editable/js/bootstrap-editable.min.js"></script>
    <script src="static/bootstrap/vendor/select2-3.5.2/select2.min.js"></script>
    <script src="static/bootstrap/vendor/bootstrap-touchspin/dist/jquery.bootstrap-touchspin.min.js"></script>
    <script src="static/bootstrap/vendor/bootstrap-datepicker-master/dist/js/bootstrap-datepicker.js"></script>
	<!-- for editable -->
	<script src="static/bootstrap/vendor/moment/moment.js"></script>
	 
	<!-- for datatable -->
	<script src="static/bootstrap/vendor/datatables/media/js/jquery.dataTables.min.js"></script>
	<script src="static/bootstrap/vendor/datatables_plugins/integration/bootstrap/3/dataTables.bootstrap.min.js"></script>
	<!-- alert -->
	<script src="static/bootstrap/vendor/sweetalert/lib/sweet-alert.min.js"></script>

<!-- App scripts -->
    <script src="static/bootstrap/scripts/homer.js"></script>
    <script src="static/bootstrap/scripts/charts.js"></script>
    <script src="static/js/template.js"></script>
	<script src="static/js/yuangongH5.js"></script>
    <script src="static/js/commonH5.js"></script>
<script>
var personId="${personId}";
$(function (){
	console.log("${personId}");
	
	
	$('.demo2').click(function(){
    	swal({
                title: "您的登录密码已被系统重置 新密码为XXXX",
                text: "请尽快登录系统修改密码！",
                type: "success",
				confirmButtonText: "确定"
            });
    });
	
	 

	
})
</script>
<script>
	$(".gereninform").click(function(){
			$(".grxx").show();
		})
</script>
<!--日历-->
<script>

        $(function(){
        	 
            

        	  /*
            $('.input-group.date').datepicker({ });
            $('.input-daterange').datepicker({ }); */

            $("#demo1").TouchSpin({
                min: 0,
                max: 100,
                step: 0.1,
                decimals: 2,
                boostat: 5,
                maxboostedstep: 10,
            });

            $("#demo2").TouchSpin({
                verticalbuttons: true
            });

            $("#demo3").TouchSpin({
                postfix: '%'
            });

            $("#demo4").TouchSpin({
                postfix: "a button",
                postfix_extraclass: "btn btn-default"
            });

            $(".js-source-states").select2();
            $(".js-source-states-2").select2();

            //turn to inline mode
            $.fn.editable.defaults.mode = 'inline';

            //defaults
            $.fn.editable.defaults.url = '#';

            //editables
            $('#username').editable({
                url: '#',
                type: 'text',
                pk: 1,
                name: 'username',
                title: 'Enter username'
            });
        
            $('#sex').editable({
                prepend: "not selected",
                source: [
                    {value: 1, text: 'Male'},
                    {value: 2, text: 'Female'}
                ],
                display: function(value, sourceData) {
                    var colors = {"": "gray", 1: "green", 2: "blue"},
                            elem = $.grep(sourceData, function(o){return o.value == value;});

                    if(elem.length) {
                        $(this).text(elem[0].text).css("color", colors[value]);
                    } else {
                        $(this).empty();
                    }
                }
            });

            $('#dob').editable();

            $('#event').editable({
                placement: 'right',
                combodate: {
                    firstItem: 'name'
                }
            });

            $('#comments').editable({
                showbuttons: 'bottom'
            });

            $('#fruits').editable({
                pk: 1,
                limit: 3,
                source: [
                    {value: 1, text: 'banana'},
                    {value: 2, text: 'peach'},
                    {value: 3, text: 'apple'},
                    {value: 4, text: 'watermelon'},
                    {value: 5, text: 'orange'}
                ]
            });

            $('#user .editable').on('hidden', function(e, reason){
                if(reason === 'save' || reason === 'nochange') {
                    var $next = $(this).closest('tr').next().find('.editable');
                    if($('#autoopen').is(':checked')) {
                        setTimeout(function() {
                            $next.editable('show');
                        }, 300);
                    } else {
                        $next.focus();
                    }
                }
            });

        });
    </script>
    <script type="text/javascript">  
    <!--  
    //平台、设备和操作系统  
    var system ={  
        win : false,  
        mac : false,  
        xll : false  
    };  
    //检测平台  
    var p = navigator.platform;
	/*alert(p);*/
    system.win = p.indexOf("Win") == 0;  
    system.mac = p.indexOf("Mac") == 0;  
    system.x11 = (p == "X11") || (p.indexOf("Linux") == 0);  
    //跳转语句  
    if(system.win||system.mac||system.xll){//转向后台登陆页面
	/*alert(1);*/
		$("#link").attr("href","static/css/denglu.css");
	  
    }else{  
	/*alert(2);*/
	 $("#link").attr("href","static/css/dengluiphone.css");
    }  
   
</script> 
</body>
</html>