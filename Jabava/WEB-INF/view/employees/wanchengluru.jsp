<%@ page contentType="text/html; charset=utf-8" %>
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
    <title>完成录入界面</title>
    <jsp:include flush="true" page="../common/styles.jsp"></jsp:include>
    <!--for 临时改变-->
    <link rel="stylesheet" href="static/js/plugins/form.validation/css/formValidation.css">
	  <link rel="stylesheet" id="link" href="static/css/denglu.css" type="text/css">
</head>
<body class="blank" style="position:static;">

<!-- Simple splash screen-->
<div class="splash"> 
 <div class="color-line"></div><div class="splash-title">
<h1>Jabava V1.0 </h1>
<p>属于你的专业人事专员</p>
<img src="static/bootstrap/images/loading-bars.svg" width="64" height="64" /> </div> </div>

<!--[if lt IE 7]>
<p class="alert alert-danger">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
<![endif]-->

<!--<div class="color-line"></div>-->
<!-- <div class="back-link">
    <a href="index.html" class="btn btn-primary">Back to Dashboard</a>
</div> -->
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
                        	<a href="#" data-target="[data-modal=personal]" data-toggle="modal"><img src="static/img/u327.png" width="41" height="39"></a>
                        </div>
                     </div>
                     <!--inform end--> 
                    </div>
                </div>
            </div>
        </div>
     </div>
     
    <!--个人信息-->
      <div class="liebiaos">
      	<h3>个人信息</h3>
        	<!--<span class="duihao">&radic;</span>-->
           <ul>
            	<li>电话0218534654</li>
                <li>出生日期1989-3-8</li>
                <li>证件类型身份证 3462891000078</li>
                <li>民族汉</li>
                <li>籍贯安徽</li>
                </ul>
                <ul class="nones">
                <li>婚姻状况已婚</li>
                <li>户口类型城镇</li>
                <li>户口所在地上海</li>
                <li>现居住地上海市源深路1088号</li>
                <li>家庭住址安徽省亳州市</li>
                <li> 邮编2000000</li>
                <li>最高学历本科</li>
                <li>最高学位硕士</li>
                <li>参加工作时间2010-10-11</li>
                <li>档案存放处安徽亳州</li>
                <li>是否外籍否</li>
                <li>是否海外留学否</li>
                <li>开户行工商银行</li>
                <li>开户支行上海市世纪大道支行</li>
           </ul>
           <div class="pe-7s-angle-down pe-3x showdown" style=" width:100%; text-align:center; color:#f00; cursor:pointer;"></div>
          <div class="pe-7s-angle-up pe-3x showup" style=" width:100%; text-align:center; color:#f00; cursor:pointer; display:none"></div>
       </div>
       
           <!--个人信息弹框-->   
           <div class="register-container containers " style="display:none;"> 
            <div class="row">
              <div class="col-lg-12">
              <div class="modal fade hmodal-success form-row" id="myModal7_1" data-modal="personal" tabindex="-1" role="dialog"  aria-hidden="true">
                  <div class="modal-dialog">
                    <div class="modal-content">
                      <div class="blue">
                        <h5><a href="yuangongluru.html"><img src="static/img/u4.png" width="20" height="20"></a><span class="gereninform">个人信息</span><span class="baocun">保存</span></h5>
                      </div>
                      <div class="modal-header">
                        <div class="row">
                          <!--个人信息-->
                          <div class="panel-body grxx" style=" background:none;">
                            <form role="form" class="form-horizontal formclass" data-form-validator="personal"> 
                              <!--联系电话-->
                              <div class="form-group searchs-unit displays">
                                <label for="exampleInputName4" class="col-lg-3 tongyong">联系电话：</label>
                                <div class="col-lg-8 displays">
                                  <input type="text" class="form-control" id="exampleInputName2">
                                </div>
                              </div>
                              <!--出生日期-->
                              <div class="form-group searchs-unit displays">
                                <label for="exampleInputName4" class="col-lg-3 tongyong">出生日期：</label>
                                <div class="col-lg-8">
                                  <div class="input-group date  displays">
                                    <input type="text" class="form-control displays" id="exampleInputName2">
                                    <span class="input-group-addon "><i class="glyphicon glyphicon-th"></i></span>
                                  </div>
                                </div>
                              </div>
                              <!-- 身份证号-->
                              <div class="form-group searchs-unit displays">
                                <label for="exampleInputName4" class="col-lg-3 tongyong">身&nbsp;&nbsp;份&nbsp;&nbsp;证：</label>
                                <div class="col-lg-8 displays form-required">
                                  <input type="text" name="certId" class="form-control" id="exampleInputName2">
                                </div>
                              </div>
                              <!--民族-->	
                              <div class="form-group searchs-unit displays">
                                <label for="exampleInputName4" class="col-lg-3 tongyong">民&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;族：</label>
                                <div class="col-lg-8 displays">
                                  <input type="text" class="form-control" id="exampleInputName2">
                                </div>
                              </div>
                              <!--籍贯-->
                              <div class="form-group searchs-unit displays">
                                <label for="exampleInputName4" class="col-lg-3 tongyong">籍&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;贯：</label>
                                <div class="col-lg-8 displays">
                                  <input type="text" class="form-control" id="exampleInputName2">
                                </div>
                              </div>
                              <!--婚姻状况-->
                              <div class="form-group searchs-unit displays">
                                <label for="exampleInputName4" class="col-lg-3 tongyong">婚姻状况：</label>
                                <div class="col-lg-8 displays">
                                  <select class="form-control " name="account" placeholder="未婚">
                                    <option>已婚</option>
                                    <option>其他</option>
                                    <option>未婚</option>
                                  </select>
                                </div>
                              </div>
                              <!--户籍类型-->
                              <div class="form-group searchs-unit displays" style=" display:block">
                                <label for="exampleInputName4" class="col-lg-3 tongyong">户籍类型：</label>
                                <div class="col-lg-8 displays form-required">
                                  <select class="form-control " name="registerType" placeholder="城镇">
                                    <option>城镇</option>
                                    <option>农村</option>
                                    <option>普通用户</option>
                                  </select>
                                </div>
                              </div>
                              <!--户口所在地-->
                              <div class="form-group searchs-unit displays">
                                <label for="exampleInputName4" class="col-lg-3 tongyong">户口所在：</label>
                                <div class="col-lg-8 displays">
                                  <input type="text" class="form-control" id="exampleInputName2">
                                </div>
                              </div>
                              <!--现居住地-->
                              <div class="form-group searchs-unit displays">
                                <label for="exampleInputName4" class="col-lg-3 tongyong">现居住地：</label>
                                <div class="col-lg-8 displays form-required">
                                  <input type="text" class="form-control" name="contactAddress" id="exampleInputName2">
                                </div>
                              </div>
                              <!--家庭住址-->
                              <div class="form-group searchs-unit displays">
                                <label for="exampleInputName4" class="col-lg-3 tongyong">家庭地址：</label>
                                <div class="col-lg-8 displays form-required">
                                  <input type="text" class="form-control" name="familyAddress" id="exampleInputName2">
                                </div>
                              </div>
                              <!--邮编-->
                              <div class="form-group searchs-unit displays">
                                <label for="exampleInputName4" class="col-lg-3 tongyong">邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;编：</label>
                                <div class="col-lg-8 displays">
                                  <input type="text" class="form-control" id="exampleInputName2">
                                </div>
                              </div>
                              <!--最高学历--> 
                              <div class="form-group searchs-unit displays">
                                <label for="exampleInputName4" class="col-lg-3 tongyong">最高学历：</label>
                                <div class="col-lg-8 displays form-required">
                                  <select class="form-control " name="degree" placeholder="高中">
                                    <option>大专</option>
                                    <option>本科</option>
                                    <option>高中</option>
                                  </select>
                                </div>
                              </div>
                              <!--最高学位-->
                              <div class="form-group searchs-unit displays" style=" display:block">
                                <label for="exampleInputName4" class="col-lg-3 tongyong">最高学位：</label>
                                <div class="col-lg-8 displays">
                                  <select class="form-control " name="account" placeholder="学士">
                                    <option>硕士</option>
                                    <option>博士</option>
                                    <option>学士</option>
                                  </select>
                                </div>
                              </div>
                              <!--档案存放处-->
                              <div class="form-group searchs-unit displays">
                                <label for="exampleInputName4" class="col-lg-3 tongyong">档案存放：</label>
                                <div class="col-lg-8 displays">
                                  <input type="text" class="form-control" id="exampleInputName2">
                                </div>
                              </div>
                              <!--参加工作时间-->
                              <div class="form-group searchs-unit displays">
                                <label for="exampleInputName4" class="col-lg-3 tongyong">工作时间：</label>
                                <div class="col-lg-8">
                                  <div class="input-group date displays">
                                    <input type="text" class="form-control" id="exampleInputName2">
                                    <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                  </div>
                                </div>
                              </div>

                              <!--是否外籍--> 
                              <div class="form-group searchs-unit displays">
                                <label for="exampleInputName4" class="col-lg-3 tongyong">是否外籍：</label>
                                <div class="col-lg-8 displays">
                                  <div class="sex_style" style=" text-align:left">
                                    <span class="sex sex_third">是</span>
                                    <input class="new_style_four sex" type="radio" id="optionsRadios1"  name="optionsRadios" value="option1" checked="" />
                                    <span class="sex sex_four">否</span>
                                    <input class="new_style_four sex" type="radio" id="optionsRadios2"  name="optionsRadios" value="option1" checked="" />
                                  </div>
                                </div>
                              </div>
                              <!--是否海外留学-->
                              <div class="form-group searchs-unit displays">
                                <label for="exampleInputName4" class="col-lg-3 tongyong">是否留学：</label>
                                <div class="col-lg-8 displays">
                                  <div class="sex_style" style=" text-align:left">
                                    <span class="sex sex_third">是</span>
                                    <input class="new_style_four sex" type="radio" id="optionsRadios3"  name="optionsRadios1" value="option2" checked="" />
                                    <span class="sex sex_four">否</span>
                                    <input class="new_style_four sex" type="radio" id="optionsRadios4"  name="optionsRadios1" value="option2" checked="" />
                                  </div>
                                </div>
                              </div>
                              <!--开户行--> 
                              <div class="form-group searchs-unit displays">
                                <label for="exampleInputName4" class="col-lg-3 tongyong">开&nbsp;&nbsp;户&nbsp;&nbsp;行：</label>
                                <div class="col-lg-8 displays">
                                  <input type="text" class="form-control" id="exampleInputName2">
                                </div>
                              </div>
                              <!--开户支行-->
                              <div class="form-group searchs-unit displays">
                                <label for="exampleInputName4" class="col-lg-3 tongyong">开户支行：</label>
                                <div class="col-lg-8 displays">
                                  <input type="text" class="form-control" id="exampleInputName2">
                                </div>
                              </div>
                              <!--工资卡号-->
                              <div class="form-group searchs-unit displays">
                                <label for="exampleInputName4" class="col-lg-3 tongyong">工资卡号：</label>
                                <div class="col-lg-8 displays">
                                  <input type="text" class="form-control" id="exampleInputName2">
                                </div>
                              </div>
                              <!--保存-->
                              <center style="clear:both; padding-top:30px;">
                                <button type="submit" class="btn btn-info">保存并填写下一项</button>
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
                
       <!--教育背景-->
       <!-- <div class="teach">
        <h3><span data-target="#myModal7" data-toggle="modal">+</span>教育背景</h3>
        <div class="teaches">
          <ul>
            <li>2010/9-2015/7</li>
            <li>西安科技大学</li>
            <li>电气工程及其自动化</li>
            <li>学制四年</li>
            <li>学历本科</li>
          </ul>
          <div class="xiushan">
            <a href="#" class="pe-7s-note pe-2x xiugai" data-target="#myModal7" data-toggle="modal"></a>
            <a href="#" class="pe-7s-trash pe-2x demo4"></a>
          </div>
        </div>
      </div> -->
      <!-- <div class="register-container containers" style="display:none;"> 
        <div class="row">
          <div class="col-lg-12"> -->
            <!--教育背景弹框-->  
            <!-- <div class="modal fade hmodal-success form-row" id="myModal7" tabindex="-1" role="dialog"  aria-hidden="true">
              <div class="modal-dialog">
                <div class="modal-content">
                  <div class="blue">
                    <h5><a href="yuangongluru.html"><img src="static/img/u4.png" width="20" height="20"></a><span class="gereninform">教育背景</span><span class="baocun">保存</span></h5>
                  </div>
                  <div class="modal-header">
                    <div class="row"> -->
                      <!--教育背景-->
                      <!-- <div class="panel-body jiaoyu" style=" background:none;">
                        <form role="form" class=" form-horizontal formclass">  -->
                          <!--入学时间-->
                          <!-- <div class="form-group searchs-unit displays">
                            <label for="exampleInputName4" class="col-lg-3 tongyong">入学时间：</label>
                            <div class="input-group date col-lg-8 displays formclass">
                              <input type="text" class="form-control" id="exampleInputName2">
                              <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                            </div>
                          </div> -->
                          <!--毕业时间-->
                         <!--  <div class="form-group searchs-unit displays">
                            <label for="exampleInputName4" class="col-lg-3 tongyong" >毕业时间：</label>
                            <div class="input-group date col-lg-8 displays">
                              <input type="text" class="form-control" id="exampleInputName2">
                              <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                            </div>
                          </div> -->
                          <!--毕业学校-->
                          <!-- <div class="form-group searchs-unit displays">
                            <label for="exampleInputName4" class="col-lg-3 tongyong">毕业学校：</label>
                            <div class="input-group col-lg-8 displays">
                              <input type="text" class="form-control" id="exampleInputName2">
                            </div>
                          </div> -->
                          <!--所学专业-->
                          <!-- <div class="form-group searchs-unit displays">
                            <label for="exampleInputName4" class="col-lg-3 tongyong">所学专业：</label>
                            <div class="input-group col-lg-8 displays">
                              <input type="text" class="form-control" id="exampleInputName2">
                            </div>
                          </div> -->
                          <!--学习形式--> 
                          <!-- <div class="form-group searchs-unit displays">
                            <label for="exampleInputName4" class="col-lg-3 tongyong">学习形式：</label>
                            <div class="col-lg-6 displays">
                              <div class="sex_style">
                                <span class="sex sex_third">全日制</span>
                                <input class="new_style_four sex" type="radio" id="optionsRadios1" name="optionsRadios" value="option1" checked="" />
                                <span class="sex sex_four">非全日制</span>
                                <input class="new_style_four sex" type="radio" id="optionsRadios2"  name="optionsRadios" value="option1" checked="" />
                              </div>
                            </div>
                          </div> -->
                          <!--学制-->
                          <!-- <div class="form-group searchs-unit displays">
                            <label for="exampleInputName4" class="col-lg-3 tongyong">学&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;制：</label>
                            <div class="input-group col-lg-8 displays">
                              <input type="text" class="form-control" id="exampleInputName2">
                            </div>
                          </div> -->
                          <!--学历-->  
                          <!-- <div class="form-group searchs-unit displays">
                            <label for="exampleInputName4" class="col-lg-3 tongyong">学&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;历：</label>
                            <div class="col-lg-8 displays" style="padding:0;">
                              <select class="form-control" name="account" placeholder="本科">
                                <option>本科</option>
                                <option>大专</option>
                                <option>高中</option>
                                <option>初中</option>
                                <option>小学</option>
                                <option>博士</option>
                              </select>
                            </div>
                          </div>  --> 
                          <!--证书编号-->  
                          <!-- <div class="form-group searchs-unit displays">
                            <label for="exampleInputName4" class="col-lg-3 tongyong">证书编号：</label>
                            <div class="input-group col-lg-8 displays">
                              <input type="text" class="form-control" id="exampleInputName2">
                            </div>
                          </div> -->
                          <!--保存-->
                          <!-- <center style="clear:both; padding-top:30px;">
                            <button type="submit" class="btn btn-info">保存并填写下一项</button>
                          </center>
                        </form>
                      </div>
                    </div>   
                  </div>
                </div>
              </div>
            </div> -->
              <!--教育背景 end--> 
          <!-- </div>
        </div>
      </div> -->
      <!--工作经验-->
      <!-- <div class="work">
        <h3><span data-target="#myModal1" data-toggle="modal">+</span>工作经验</h3>
        <div class="works">
          <ul>
            <li>2010/9-2015/7</li>
            <li>西安科技大学</li>
            <li>电气工程及其自动化</li>
            <li>学制四年</li>
            <li>学历本科</li>
          </ul>
          <div class="xiushan">
            <a href="#" class="pe-7s-note pe-2x xiugai" data-target="#myModal1" data-toggle="modal"></a>
            <a href="#" class="pe-7s-trash pe-2x demo4"></a>
          </div>
        </div>
      </div> -->
      <!--工作经验弹框-->  
     <!--  <div class="register-container containers" style="display:none;"> 
        <div class="row">
          <div class="col-lg-12">
            <div class="modal fade hmodal-success form-row" id="myModal1" tabindex="-1" role="dialog"  aria-hidden="true">
              <div class="modal-dialog">
                <div class="modal-content">
                  <div class="blue">
                    <h5><a href="yuangongluru.html"><img src="static/img/u4.png" width="20" height="20"></a><span class="gereninform">工作经验</span><span class="baocun">保存</span></h5>
                  </div>
                  <div class="modal-header">
                    <div class="row"> -->
                      <!--工作经验-->
                      <!-- <div class="panel-body gongzuojy" style=" background:none;">
                        <form role="form" class="form-horizontal formclass">  -->
                          <!--起始时间-->
                          <!-- <div class="form-group searchs-unit displays">
                            <label for="exampleInputName4" class="col-lg-3 tongyong">起始时间：</label>
                            <div class="input-group date col-lg-8 displays">
                              <input type="text" class="form-control" id="exampleInputName2">
                              <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                            </div>
                          </div> -->
                          <!--终止时间-->
                          <!-- <div class="form-group searchs-unit displays">
                            <label for="exampleInputName4" class="col-lg-3 tongyong">终止时间：</label>
                            <div class="input-group date col-lg-8 displays">
                              <input type="text" class="form-control" id="exampleInputName2">
                              <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                            </div>
                          </div> -->
                          <!--工作单位-->
                          <!-- <div class="form-group searchs-unit displays">
                            <label for="exampleInputName4" class="col-lg-3 tongyong">工作单位：</label>
                            <div class="input-group col-lg-8 displays">
                              <input type="text" class="form-control" id="exampleInputName2">
                            </div>
                          </div> -->
                          <!--工作岗位-->
                          <!-- <div class="form-group searchs-unit displays">
                            <label for="exampleInputName4" class="col-lg-3 tongyong">工作岗位：</label>
                            <div class="input-group col-lg-8 displays">
                              <input type="text" class="form-control" id="exampleInputName2">
                            </div>
                          </div> -->

                          <!--证明人-->  
                          <!-- <div class="form-group searchs-unit displays">
                            <label for="exampleInputName4" class="col-lg-3 tongyong">证&nbsp;&nbsp;明&nbsp;&nbsp;人：</label>
                            <div class="input-group col-lg-8 displays">
                              <input type="text" class="form-control" id="exampleInputName2">
                            </div>
                          </div> -->
                          <!--职责描述-->
                          <!-- <div class="form-group searchs-unit displays">
                            <label for="exampleInputName4" class="col-lg-3 tongyong">职责描述：</label>
                            <div class="col-lg-8 displays" style="padding:0;">
                              <textarea class="form-control" id="exampleInputName3"></textarea>
                            </div>
                          </div>  -->
                          <!--保存-->
                          <!-- <center style="clear:both; padding-top:30px;">
                            <button type="submit" class="btn btn-info">保存并填写下一项</button>
                          </center>
                        </form>
                      </div> -->
                      <!--工作经验 end--> 
                    <!-- </div>   
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div> -->
                
      <!--项目经验-->
      <!-- <div class="item">
        <h3><span data-target="#myModal8_1" data-toggle="modal">+</span>项目经验</h3>
        <div class="items">
          <ul>
            <li>2010/9-2015/7</li>
            <li>西安科技大学</li>
            <li>电气工程及其自动化</li>
            <li>学制四年</li>
            <li>学历本科</li>
          </ul>
          <div class="xiushan">
            <a href="#" class="pe-7s-note pe-2x xiugai" data-target="#myModal8_1" data-toggle="modal"></a>
            <a href="#" class="pe-7s-trash pe-2x demo4"></a>
          </div>
        </div>
      </div> -->
      <!--项目经验弹框-->  
      <!-- <div class="register-container containers" style="display:none;"> 
        <div class="row">
          <div class="col-lg-12">
            <div class="modal fade hmodal-success form-row" id="myModal8_1" tabindex="-1" role="dialog"  aria-hidden="true">
              <div class="modal-dialog">
                <div class="modal-content">
                  <div class="blue">
                    <h5><a href="yuangongluru.html"><img src="static/img/u4.png" width="20" height="20"></a><span class="gereninform">项目经验</span><span class="baocun">保存</span></h5>
                  </div>
                  <div class="modal-header">
                    <div class="row"> -->
                      <!--项目经验-->
                      <!-- <div class="panel-body xiangmujy" style=" background:none;">
                        <form role="form" class="form-horizontal formclass">  -->
                          <!--项目起始时间-->
                          <!-- <div class="form-group searchs-unit displays">
                            <label for="exampleInputName4" class="col-lg-3 tongyong">起始时间：</label>
                            <div class="input-group date col-lg-8 displays">
                              <input type="text" class="form-control" id="exampleInputName2">
                              <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                            </div>
                          </div> -->
                          <!--项目终止时间-->
                          <!-- <div class="form-group searchs-unit displays">
                            <label for="exampleInputName4" class="col-lg-3 tongyong">终止时间：</label>
                            <div class="input-group date col-lg-8 displays">
                              <input type="text" class="form-control" id="exampleInputName2">
                              <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                            </div>
                          </div> -->
                          <!--项目名称-->
                          <!-- <div class="form-group searchs-unit displays">
                            <label for="exampleInputName4" class="col-lg-3 tongyong">项目名称：</label>
                            <div class="input-group col-lg-8 displays">
                              <input type="text" class="form-control" id="exampleInputName2">
                            </div>
                          </div> -->
                          <!--项目金额-->
                          <!-- <div class="form-group searchs-unit displays">
                            <label for="exampleInputName4" class="col-lg-3 tongyong">项目金额：</label>
                            <div class="input-group col-lg-8 displays">
                              <input type="text" class="form-control" id="exampleInputName2">
                            </div>
                          </div> -->
                          <!--项目职责--> 
                          <!-- <div class="form-group searchs-unit displays">
                            <label for="exampleInputName4" class="col-lg-3 tongyong">项目职责：</label>
                            <div class="col-lg-8 displays" style="padding:0;">
                              <textarea class="form-control" id="exampleInputName3"></textarea>
                            </div>
                          </div> --> 
                          <!--职责描述-->
                          <!-- <div class="form-group searchs-unit displays">
                            <label for="exampleInputName4" class="col-lg-3 tongyong">职责描述：</label>
                            <div class="col-lg-8 displays" style="padding:0;">
                              <textarea class="form-control" id="exampleInputName3"></textarea>
                            </div>
                          </div>  -->
                          <!--保存-->
                          <!-- <center style="clear:both; padding-top:30px;">
                            <button type="submit" class="btn btn-info">保存并填写下一项</button>
                          </center>
                        </form>
                      </div> -->
                      <!--项目经验 end--> 
                    <!-- </div>   
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div> -->
                
                
      <!--培训经历-->
      <!-- <div class="cultivate">
        <h3><span data-target="#myModal9" data-toggle="modal">+</span>培训经历</h3>
        <div class="cultivates">
          <ul>
            <li>2010/9-2015/7</li>
            <li>西安科技大学</li>
            <li>电气工程及其自动化</li>
            <li>学制四年</li>
            <li>学历本科</li>
          </ul>
          <div class="xiushan">
            <a href="#" class="pe-7s-note pe-2x xiugai" data-target="#myModal9" data-toggle="modal"></a>
            <a href="#" class="pe-7s-trash pe-2x demo4"></a>
          </div>
        </div>
      </div> -->
      <!--培训经历弹框-->  
      <!-- <div class="register-container containers" style="display:none;"> 
        <div class="row">
          <div class="col-lg-12">
            <div class="modal fade hmodal-success form-row" id="myModal9" tabindex="-1" role="dialog"  aria-hidden="true">
              <div class="modal-dialog">
                <div class="modal-content">
                  <div class="blue">
                    <h5><a href="yuangongluru.html"><img src="static/img/u4.png" width="20" height="20"></a><span class="gereninform">培训经历</span><span class="baocun">保存</span></h5>
                  </div>
                  <div class="modal-header">
                    <div class="row"> -->
                      <!--培训经历-->
                      <!-- <div class="panel-body peixun_jl" style=" background:none;">
                        <form role="form" class="form-horizontal formclass">  -->
                          <!--培训开始时间-->
                          <!-- <div class="form-group searchs-unit displays">
                            <label for="exampleInputName4" class="col-lg-3 tongyong">开始时间：</label>
                            <div class="input-group date col-lg-8 displays">
                              <input type="text" class="form-control" id="exampleInputName2">
                              <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                            </div>
                          </div> -->
                          <!--培训结束时间-->
                          <!-- <div class="form-group searchs-unit displays">
                            <label for="exampleInputName4" class="col-lg-3 tongyong">结束时间：</label>
                            <div class="input-group date col-lg-8 displays">
                              <input type="text" class="form-control" id="exampleInputName2">
                              <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                            </div>
                          </div> -->
                          <!--课程名称-->
                          <!-- <div class="form-group searchs-unit displays">
                            <label for="exampleInputName4" class="col-lg-3 tongyong">课程名称：</label>
                            <div class="input-group col-lg-8 displays">
                              <input type="text" class="form-control" id="exampleInputName2">
                            </div>
                          </div> -->
                          <!--培训费用-->
                          <!-- <div class="form-group searchs-unit displays">
                            <label for="exampleInputName4" class="col-lg-3 tongyong">培训费用：</label>
                            <div class="input-group col-lg-8 displays">
                              <input type="text" class="form-control" id="exampleInputName2">
                            </div>
                          </div> -->
                          <!--培训机构-->
                          <!-- <div class="form-group searchs-unit displays">
                            <label for="exampleInputName4" class="col-lg-3 tongyong">培训机构：</label>
                            <div class="input-group col-lg-8 displays">
                              <input type="text" class="form-control" id="exampleInputName2">
                            </div>
                          </div> -->
                          <!--培训课时-->
                          <!-- <div class="form-group searchs-unit displays">
                            <label for="exampleInputName4" class="col-lg-3 tongyong">培训课时：</label>
                            <div class="input-group col-lg-8 displays">
                              <input type="text" class="form-control" id="exampleInputName2">
                            </div>
                          </div> -->
                          <!--所得证书--> 
                          <!-- <div class="form-group searchs-unit displays">
                            <label for="exampleInputName4" class="col-lg-3 tongyong">所得证书：</label>
                            <div class="input-group col-lg-8 displays">
                              <input type="text" class="form-control" id="exampleInputName2">
                            </div>
                          </div> -->
                          <!--保存-->
                          <!-- <center style="clear:both; padding-top:30px;">
                            <button type="submit" class="btn btn-info">保存并填写下一项</button>
                          </center>
                        </form>
                      </div> -->
                      <!--培训经历 end--> 
                    <!-- </div>   
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div> -->
       
       			
      <!--获得证书-->
      <!-- <div class="achieve">
        <h3><span data-target="#myModal9_1" data-toggle="modal">+</span>获得证书</h3>
        <div class="achieves">
          <ul>
            <li>2010/9-2015/7</li>
            <li>西安科技大学</li>
            <li>电气工程及其自动化</li>
            <li>学制四年</li>
            <li>学历本科</li>
          </ul>
          <div class="xiushan">
            <a href="#" class="pe-7s-note pe-2x xiugai" data-target="#myModal9_1" data-toggle="modal"></a>
            <a href="#" class="pe-7s-trash pe-2x demo4"></a>
          </div>
        </div>
      </div> -->
      <!--获得证书弹框-->  
      <!-- <div class="register-container containers" style="display:none;"> 
        <div class="row">
          <div class="col-lg-12">
            <div class="modal fade hmodal-success form-row" id="myModal9_1" tabindex="-1" role="dialog"  aria-hidden="true">
              <div class="modal-dialog">
                <div class="modal-content">
                  <div class="blue">
                    <h5><a href="yuangongluru.html"><img src="static/img/u4.png" width="20" height="20"></a><span class="gereninform">获得证书</span><span class="baocun">保存</span></h5>
                  </div>
                  <div class="modal-header">
                    <div class="row"> -->
                      <!--获得证书-->
                      <!-- <div class="panel-body huode" style=" background:none;">
                        <form role="form" class="form-horizontal formclass"> --> 
                          <!--取得证书时间-->
                          <!-- <div class="form-group searchs-unit displays">
                            <label for="exampleInputName4" class="col-lg-3 tongyong">证书时间：</label>
                            <div class="input-group date col-lg-8 displays">
                              <input type="text" class="form-control" id="exampleInputName2">
                              <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                            </div>
                          </div> -->
                          <!--证书有效期-->
                          <!-- <div class="form-group searchs-unit displays">
                            <label for="exampleInputName4" class="col-lg-3 tongyong">有&nbsp;&nbsp;效&nbsp;&nbsp;期：</label>
                            <div class="input-group col-lg-8 displays">
                              <input type="text" class="form-control" id="exampleInputName2">
                            </div>
                          </div> -->
                          <!--证书名称-->
                          <!-- <div class="form-group searchs-unit displays">
                            <label for="exampleInputName4" class="col-lg-3 tongyong">证书名称：</label>
                            <div class="input-group col-lg-8 displays">
                              <input type="text" class="form-control" id="exampleInputName2">
                            </div>
                          </div> -->
                          <!--电子版上传--> 
                          <!-- <div class="form-group searchs-unit displays">
                            <label for="exampleInputName3" class="col-lg-3 tongyong">电子上传：</label>
                            <div class="input-group col-lg-3 displays" style="display:inline">
                              <input type="file" class="displays" style="display:inline; width:50%;">
                            </div>
                          </div>  -->
                          <!--备注-->
                          <!-- <div class="form-group searchs-unit displays">
                            <label for="exampleInputName4" class="col-lg-3 tongyong">备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：</label>
                            <div class="col-lg-8 displays" style="padding:0;">
                              <textarea class="form-control" id="exampleInputName3"></textarea>
                            </div>
                          </div> --> 
                          <!--保存-->
                          <!-- <center style="clear:both; padding-top:30px;">
                            <button type="submit" class="btn btn-info">保存并填写下一项</button>
                          </center>
                        </form>

                      </div> -->
                      <!--获得证书 end--> 
                    <!-- </div>   
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div> -->
       			
                
      <!--家庭成员-->
      <!-- <div class="famliy">
        <h3>家庭成员</h3>
        <div class="famliys">
          <ul>
            <li>张楠</li>
            <li>老婆</li>
            <li>13888899908</li>
            <li>上海</li>
            <li>1986</li>
            <li>3462877472254353453573545</li>
            <li>上海华润科技有限公司</li>
          </ul>
          <div class="xiushan">
            <a href="#" class="pe-7s-note pe-2x xiugai" data-target="#myModal10" data-toggle="modal"></a>
            <a href="#" class="pe-7s-trash pe-2x demo4"></a>
          </div>
        </div>
      </div> -->
      <!--家庭成员弹框-->  
      <!-- <div class="register-container containers" style="display:none;"> 
        <div class="row">
          <div class="col-lg-12">
            <div class="modal fade hmodal-success form-row" id="myModal10" tabindex="-1" role="dialog"  aria-hidden="true">
              <div class="modal-dialog">
                <div class="modal-content">
                  <div class="blue">
                    <h5><a href="yuangongluru.html"><img src="static/img/u4.png" width="20" height="20"></a><span class="gereninform">家庭成员</span><span class="baocun">保存</span></h5>
                  </div>
                  <div class="modal-header">
                    <div class="row"> -->
                      <!--家庭成员-->
                      <!-- <div class="panel-body famliy_cy" style=" background:none;">
                        <form role="form" class="form-horizontal formclass"> --> 
                          <!--成员姓名-->
                          <!-- <div class="form-group searchs-unit displays">
                            <label for="exampleInputName4" class="col-lg-3 tongyong">成员姓名：</label>
                            <div class="input-group col-lg-8 displays">
                              <input type="text" class="form-control" id="exampleInputName2">
                            </div>
                          </div> -->
                          <!--与本人关系-->
                          <!-- <div class="form-group searchs-unit displays">
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
                          </div>  --> 
                          <!--居住城市-->
                          <!-- <div class="form-group searchs-unit displays">
                            <label for="exampleInputName4" class="col-lg-3 tongyong">居住城市：</label>
                            <div class="input-group col-lg-8 displays">
                              <input type="text" class="form-control" id="exampleInputName2">
                            </div>
                          </div> -->
                          <!--出生日期-->
                          <!-- <div class="form-group searchs-unit displays">
                            <label for="exampleInputName4" class="col-lg-3 tongyong">出生时间：</label>
                            <div class="input-group date col-lg-8 displays">
                              <input type="text" class="form-control" id="exampleInputName2">
                              <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                            </div>
                          </div> -->
                          <!--联系电话-->
                          <!-- <div class="form-group searchs-unit displays">
                            <label for="exampleInputName4" class="col-lg-3 tongyong">联系电话：</label>
                            <div class="input-group col-lg-8 displays">
                              <input type="text" class="form-control" id="exampleInputName2">
                            </div>
                          </div> -->
                          <!--身份证号--> 
                          <!-- <div class="form-group searchs-unit displays">
                            <label for="exampleInputName4" class="col-lg-3 tongyong">身份证号：</label>
                            <div class="input-group col-lg-8 displays">
                              <input type="text" class="form-control" id="exampleInputName2">
                            </div>
                          </div> -->
                          <!--工作单位--> 
                         <!--  <div class="form-group searchs-unit displays">
                            <label for="exampleInputName4" class="col-lg-3 tongyong">工作单位：</label>
                            <div class="input-group col-lg-8 displays">
                              <input type="text" class="form-control" id="exampleInputName2">
                            </div>
                          </div> -->
                          <!--保存-->
                          <!-- <center style="clear:both; padding-top:30px;">
                            <button type="submit" class="btn btn-info">保存并填写下一项</button>
                          </center>
                        </form>
                      </div> -->
                      <!--家庭成员 end--> 
                    <!-- </div>   
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div> -->
       
      <!--上传附件-->
      <div class="shangchuan">
        <h3>上传附件</h3>
        <div class="shangchuans">
          <div class="fujians">
            <span>身份证</span><img src="static/img/u106.png" width="50" height="23">
          </div>
          <div class="xiushan">
            <a href="#" class="pe-7s-note pe-2x xiugai" data-target="[data-modal=upload]" data-toggle="modal"></a>
            <a href="#" class="pe-7s-trash pe-2x demo4"></a>
          </div>
        </div>
      </div>
      <!--上传附件弹框-->  
      <div class="register-container containers" style="display:none;"> 
        <div class="row">
          <div class="col-lg-12">
            <div class="modal fade hmodal-success form-row" id="myModal2" data-modal="upload" tabindex="-1" role="dialog"  aria-hidden="true">
              <div class="modal-dialog">
                <div class="modal-content">
                  <div class="blue">
                    <h5><a href="yuangongluru.html"><img src="static/img/u4.png" width="20" height="20"></a><span class="gereninform">上传附件</span><span class="baocun">保存</span></h5>
                  </div>
                  <div class="modal-header">
                    <div class="row">
                      <!--上传附件-->
                      <div class="panel-body fujian" style=" background:none;">
                        <form role="form" class="form-horizontal formclass"> 
                          <!--身份证--> 
                          <div class="form-group searchs-unit displays">
                            <label for="exampleInputName3" class="col-lg-3 ">身&nbsp;&nbsp;份&nbsp;&nbsp;证：</label>
                            <div class="input-group col-lg-3 displays" style="display:inline">
                              <input type="file" class="displays" style="display:inline; width:50%;" accept=".png,.gif,.jpeg">
                            </div>
                          </div>
                          <!--户口簿--> 
                          <div class="form-group searchs-unit displays">
                            <label for="exampleInputName3" class="col-lg-3">户&nbsp;&nbsp;口&nbsp;&nbsp;簿：</label>
                            <div class="input-group col-lg-3 displays" style="display:inline">
                              <input type="file" class="displays" style="display:inline; width:50%;" accept=".png,.gif,.jpeg">
                            </div>
                          </div>
                          <!--学历证--> 
                          <div class="form-group searchs-unit displays">
                            <label for="exampleInputName3" class="col-lg-3">学&nbsp;&nbsp;历&nbsp;&nbsp;证：</label>
                            <div class="input-group col-lg-3 displays" style="display:inline">
                              <input type="file" class="displays" style="display:inline; width:50%;" accept=".png,.gif,.jpeg">
                            </div>
                          </div>
                          <!--简历--> 
                          <div class="form-group searchs-unit displays">
                            <label for="exampleInputName3" class="col-lg-3">简&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;历：</label>
                            <div class="input-group col-lg-3 displays" style="display:inline">
                              <input type="file" class="displays" style="display:inline; width:50%;" accept=".png,.gif,.jpeg">
                            </div>
                          </div>
                          <!--离职证明--> 
                          <div class="form-group searchs-unit displays">
                            <label for="exampleInputName3" class="col-lg-3 ">离职证明：</label>
                            <div class="input-group col-lg-3 displays" style="display:inline">
                              <input type="file" class="displays" style="display:inline; width:50%;" accept=".png,.gif,.jpeg">
                            </div>
                          </div>
                          <!--健康证明--> 
                          <div class="form-group searchs-unit displays">
                            <label for="exampleInputName3" class="col-lg-3 ">健康证明：</label>
                            <div class="input-group col-lg-3 displays" style="display:inline">
                              <input type="file" class="displays" style="display:inline; width:50%;" accept=".png,.gif,.jpeg">
                            </div>
                          </div>
                          <!--薪资证明--> 
                          <div class="form-group searchs-unit displays">
                            <label for="exampleInputName3" class="col-lg-3">薪资证明：</label>
                            <div class="input-group col-lg-3 displays" style="display:inline">
                              <input type="file" class="displays" style="display:inline; width:50%;" accept=".png,.gif,.jpeg">
                            </div>
                          </div> 
                          <!--保存-->
                          <center style="clear:both; padding-top:30px;">
                            <button type="submit" class="btn btn-info">保存并填写下一项</button>
                          </center>
                        </form>
                      </div>
                      <!--上传附件 end--> 
                    </div>   
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
       
    
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
  <script src="static/bootstrap/vendor/bootstrap-datepicker-master/dist/js/bootstrap-datepicker.min.js"></script>
  <!-- for editable -->
  <script src="static/bootstrap/vendor/moment/moment.js"></script>

  <!-- for datatable -->
  <script src="static/bootstrap/vendor/datatables/media/js/jquery.dataTables.min.js"></script>
  <script src="static/bootstrap/vendor/datatables_plugins/integration/bootstrap/3/dataTables.bootstrap.min.js"></script>
  <!-- alert -->
  <script src="static/bootstrap/vendor/sweetalert/lib/sweet-alert.min.js"></script>

  <script src="static/js/plugins/form.validation/js/formValidation.js"></script>
  <script src="static/js/plugins/form.validation/js/framework/bootstrap.js"></script>
  <script src="static/js/plugins/form.validation/js/language/zh_CN.js"></script> 
  <!-- App scripts -->
  <script src="static/bootstrap/scripts/homer.js"></script>
  <script src="static/bootstrap/scripts/charts.js"></script>
<!--删除-->
<script>
    //校验
    var validateOptions = {
        personal: {
            err: {
                container: 'tooltip'
            },
            icon: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            locale: 'zh_CN',
            
            fields: {
                familyAddress: {
                      validators: {
                          notEmpty: {
                              message: '请填写必填项目'
                          }
                      }
                  },
                  contactAddress: {
                      validators: {
                          notEmpty: {
                              message: '请填写必填项目'
                          }
                      }
                  },
                  degree: {
                      validators: {
                          notEmpty: {
                              message: '请填写必填项目'
                          }
                      }
                  },
                  certId: {
                      validators: {
                          notEmpty: {
                              message: '请填写必填项目'
                          },
                          id: {
                              country: 'CN',
                              message: '请输入有效的证件号码'
                          }
                      }
                  },
                  registerType: {
                      validators: {
                          notEmpty: {
                              message: '请填写必填项目'
                          }
                      }
                  }
            }
        }
    };
  $(function () {
    
    $('[data-form-validator="personal"]').formValidation(validateOptions.personal);

    $('.demo4').click(function () {
      swal({
        title: "确定要删除此用户吗?",
        text: "注意：用户删除后将不可登录!",
        type: "warning",
        showCancelButton: true,
        confirmButtonColor: "#DD6B55",
        confirmButtonText: "是,请删除该用户!",
        cancelButtonText: "不, 放弃此操作!",
        closeOnConfirm: false,
        closeOnCancel: false },
        function (isConfirm) {
          if (isConfirm) {
            swal("删除成功!", "该用户已经被删除.", "success");
          } else {
            swal("已取消", "用户未删除。你这逗我玩呢 :)", "error");
          }
        });
    });

  });


	$(".gereninform").click(function(){
			$(".grxx").show();
		})

        $(function(){

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



	$(".showdown").click(function(){
			$(".nones").show();
			$(this).hide();
			$(".showup").show();
		})
		$(".showup").click(function(){
			$(".nones").hide();
			$(this).hide();
			$(".showdown").show();
		})
</script>
</body>
</html>