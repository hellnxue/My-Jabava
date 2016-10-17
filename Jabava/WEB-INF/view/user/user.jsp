<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="com.jabava.utils.RequestUtil"%>
<%
String path = request.getContextPath();
String basePath = "//"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
<head>
	<base href="${pageContext.request.contextPath}/">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!-- Page title -->
	<title>用户管理</title>
	<jsp:include flush="true" page="../common/styles.jsp"></jsp:include>
	<!--for 临时改变-->
	<link rel="stylesheet" href="static/js/plugins/form.validation/css/formValidation.css">
	<link rel="stylesheet" href="static/css/user.css">
</head>
  
<body>
  <!-- Simple splash screen-->
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
    <div class="normalheader transition animated fadeIn small-header">
        <div class="hpanel">
            <div class="panel-body">
                <div id="hbreadcrumb" class="m-t-xs m-b-xs">
                    <h2 class="font-normal m-b-xs text-center">
                        用户管理
                    </h2>
                </div>
            </div>
        </div>
    </div>
    <!-- 放主要内容 -->
    <div class="content animate-panel">
      <div class="row">
        <div class="col-lg-12">
          <div class="hpanel">
            <div class="panel-heading ">
              <div class="text-right">
                <% if(RequestUtil.hasPower("index_user_au")){ %>
                <button class="btn btn-success btn-xs" type="button" onclick="insertUserEnter(this);" data-toggle="modal">
                  <i class="fa fa-group"></i> <span class="bold">新增用户</span>
                </button>
                <% } %>
              </div>
            </div>
      <!-- 高级搜索开始 -->
          <div class="collapse out" id="collapseExample" aria-expanded="false">
            <div class="well well-lg" >
              <div class="row">
                  <form class="form-horizontal" action="user/userDataTableSearch.do" id="userForm">
                      <div class="col-xs-3 col-sm-3 col-md-3 col-lg-3">
                          <div class="form-group">
                              <label for="userCode" class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-5">用户名：</label>
                              <div class="col-xs-6 col-sm-6 col-md-6 col-lg-7">
                                  <input type="text" class="form-control" name="userCode" value="${userInfo.userCode}" />
                              </div>
                          </div>
                      </div>
                      <div class="col-xs-3 col-sm-3 col-md-3 col-lg-3">
                          <div class="form-group">
                              <label for="userName" class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-5">姓名：</label>
                              <div class="col-xs-6 col-sm-6 col-md-6 col-lg-7">
                                  <input type="text" class="form-control" name="userName" value="${userInfo.userName }" />
                              </div>
                          </div>
                      </div>
                      <div class="col-xs-3 col-sm-3 col-md-3 col-lg-3">
                          <div class="form-group">
                              <label for="userType" class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-5">用户类型：</label>
                              <div class="col-xs-6 col-sm-6 col-md-6 col-lg-7">
                                  <select class="form-control" name="userType">
                                    <option value="" <c:if test="${empty userInfo.userType }">selected="selected"</c:if>>全部</option>
                                    <option value="2" <c:if test="${userInfo.userType == 2 }">selected="selected"</c:if>>公司系统管理员</option>
                                    <option value="3" <c:if test="${userInfo.userType == 3 }">selected="selected"</c:if>>普通用户</option>
                                    <option value="4" <c:if test="${userInfo.userType == 4 }">selected="selected"</c:if>>HR</option>
                                  </select>
                              </div>
                          </div>
                      </div>
                      <div class="col-xs-3 col-sm-3 col-md-3 col-lg-3">
                          <div class="form-group">
                              <label for="isValid" class="control-label col-xs-6 col-sm-6 col-md-6 col-lg-5">是否有效：</label>
                              <div class="col-xs-6 col-sm-6 col-md-6 col-lg-7">
                                  <select class="form-control" name="isValid">
                                      <option value="" <c:if test="${empty userInfo.isValid }">selected="selected"</c:if>>全部</option>
                                      <option value="1" <c:if test="${userInfo.isValid == 1 }">selected="selected"</c:if>>有效</option>
                                      <option value="0" <c:if test="${userInfo.isValid == 0 }">selected="selected"</c:if>>无效</option>
                                  </select>
                              </div>   
                          </div>
                      </div>
                      <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-center">
                          <button class="btn btn-info" type="button" id="show" onclick="search()">高级搜索</button>
                          <button class="btn btn-default m-l" type="reset">重置</button>
                      </div>
                  </form>
              </div>
            </div>
          </div>
          <!-- 高级搜索结束 -->

            <!-- 高级搜索开始 -->
            <!-- <div class="collapse out" id="collapseExample" aria-expanded="false" >
              <div class="well well-lg " >
                <div class="row">
                  <div class="col-sm-11">
                    <form role="form" action="user/userDataTableSearch.do" id="userForm" class="form-inline formclass">
                      <div class="form-group lmaigin">
                        <label for="userCode" class="col-lg-2">用户名：</label>
                        <div class="col-lg-2">
                          <input type="text" class="form-control" name="userCode" value="${userInfo.userCode }"/>
                        </div>
                        <label for="userName" class="col-lg-2">姓名：</label>
                        <div class="col-lg-2">
                          <input type="text" class="form-control" name="userName" value="${userInfo.userName }" />
                        </div>
                        <label for="userType" class="col-lg-2">用户类型：</label>
                        <div class="col-lg-2">
                          <select class="form-control m-b" name="userType">
                            <option value="" <c:if test="${empty userInfo.userType }">selected="selected"</c:if>>全部</option>
                            <option value="2" <c:if test="${userInfo.userType == 2 }">selected="selected"</c:if>>公司系统管理员</option>
                            <option value="3" <c:if test="${userInfo.userType == 3 }">selected="selected"</c:if>>普通用户</option>
                            <option value="4" <c:if test="${userInfo.userType == 4 }">selected="selected"</c:if>>HR</option>
                          </select>
                        </div>
                        <label for="isValid" class="col-lg-2">是否有效：</label>
                        <div class="col-lg-2">
                          <select class="form-control m-b" name="isValid">
                            <option value="" <c:if test="${empty userInfo.isValid }">selected="selected"</c:if>>全部</option>
                            <option value="1" <c:if test="${userInfo.isValid == 1 }">selected="selected"</c:if>>有效</option>
                            <option value="0" <c:if test="${userInfo.isValid == 0 }">selected="selected"</c:if>>无效</option>
                          </select>
                        </div>
                      </div>
                      <center>
                        <button class="btn btn-info" type="button" id="show" onclick="search()">高级搜索</button>
                        <button class="btn btn-default m-l" type="reset">重置</button>
                      </center>
                    </form>
                  </div>
                </div>
              </div>
            </div> -->
            <!-- 高级搜索结束 -->

            <!--新增hr弹框-->    
            <div class="modal fade hmodal-success form-row" id="myModal10" tabindex="-1" role="dialog"  aria-hidden="true">
              <div class="modal-dialog">
                <div class="modal-content">
                  <div class="color-line"></div>
                  <div class="modal-header clearfix">
                    <form role="form" class=" form-horizontal formclass" id="insertHrForm">
                      <div class="row row_style_one">
                        <div class="col-sm-12">

                          <div class="form-group">
                            <label class="col-lg-3 new_padding">用户编号：</label>
                            <div class="col-lg-8">
                              <input type="text" class="form-control content_clear" name="userCode" id="userCodeHR">
                            </div>
                            <label class="col-lg-1 hong">*</label>
                          </div> 

                          <div class="form-group">
                            <label class="col-lg-3 new_padding">用户名称：</label>
                            <div class="col-lg-8">
                              <input type="text" name="userName" id="userNameHR" class="form-control content_clear">
                            </div>
                            <label class="col-lg-1 hong">*</label>
                          </div> 

                          <div class="form-group">
                            <label class="col-lg-3 new_padding">手机：</label>
                            <div class="col-lg-8">
                              <input type="text" name="mobile" id="mobileHR" placeholder="请输入有效数字" class="form-control content_clear" name="mobile">
                            </div>
                            <label class="col-lg-1 hong">*</label>
                          </div> 

                          <div class="form-group">
                            <label class="col-lg-3 new_padding">Email：</label>
                            <div class="col-lg-8">
                              <input  name="mailAddress" id="mailAddressHR" class="form-control content_clear" type="email" required placeholder="请输入电子邮件">
                            </div>
                            <label class="col-lg-1 hong">*</label>
                          </div> 

                          <div class="form-group">
                            <label class="col-lg-3 new_padding">用户类型：</label>
                            <div class="col-lg-8">
                              <select name="userType" id="userTypeHR" class="form-control m-b nomargin_tow" placeholder="系统管理员">
                                <c:if test="${LOGIN_USER.userType == 1 }">
                                <option value="1">平台系统管理员</option>
                              </c:if>
                              <option value="2">系统管理员</option>
                              <option value="3">普通用户</option>
                            </select>
                          </div>
                          <label class="col-lg-1 hong">*</label>
                        </div> 
                      </div>
                    </div> 


                    <div class="row row_style_tow">
                      <div class="col-sm-12">

                        <div class="form-group">
                          <label class="col-lg-3 new_padding"></label>

                          <label class="col-lg-2">&nbsp;</label>
                        </div> 

                        <div class="form-group">
                          <label class="col-lg-3 new_padding">性别：</label>
                          <div class="col-lg-8">
                            <div class="sex_style">
                              <input id="optionsRadios3HR" name="sex" class="new_style_four sex" type="radio" value="1" checked="true">
                              <span class="sex sex_tow">男</span>
                              <input id="optionsRadios4HR" name="sex" class="new_style_four sex" type="radio" value="2" checked="">
                              <span class="sex sex_tow">女</spsexan>
                              </div>
                            </div>
                          </div> 

                          <div class="form-group">
                            <label class="col-lg-3 new_padding">电话：</label>
                            <div class="col-lg-8">
                              <input type="text" name="telephone" placeholder="请输入有效数字" class="form-control content_clear">
                            </div>
                          </div> 

                          <div class="form-group">
                            <label class="col-lg-3 new_padding">&nbsp;</label>
                            <div class="col-lg-8">
                            </div>
                          </div>

                          <div class="form-group">
                            <c:if test="${LOGIN_USER.userType == 1 }">
                            <label class="col-lg-3 new_padding_tow">所属公司：</label>
                            <div class="col-lg-8">
                              <input type="text" name="companyName" id="companyNameHR" readonly value=""/>
                              <input type="hidden" name="companyId" id="companyIdHR" value=""/>
                              <input type="button" name="companySelect" value="选择" onclick="insertCompanyTree()">
                            </div>
                            <label class="col-lg-1 hong">*</label>
                          </c:if>
                          <c:if test="${LOGIN_USER.userType != 1 }">
                          <input type="hidden" name="companyId" value="${LOGIN_USER.companyId}"/>
                          <label class="col-lg-3 new_padding_tow">用户区分：</label>
                          <div class="col-lg-8">
                            <select name="userDistinguish" id="userDistinguishHR" class="form-control m-b nomargin_tow" placeholder="用户区分">
                              <option value="">不限</option>
                              <option value="A1">GDSI</option>
                              <option value="A2">GDSS</option>
                            </select>
                          </div>
                        </c:if>
                      </div> 
                    </div>
                  </div> 

                  <div class="row">
                    <div class="col-sm-12">
                      <div class="form-group">
                        <label class="col-lg-2 beizhu">备注：</label>
                        <div class="col-lg-10">
                          <textarea type="text" class="form-control new_width content_clear" name="memo" id="memoHR" ></textarea>
                        </div>
                      </div>
                      <center>
                        <button class="btn btn-primary" type="button" onclick="insertHr();">保存</button>
                        &nbsp;
                        <button class="btn btn-primary guanbi" type="button">取消</button>
                      </center>
                    </div>
                  </div>  
                </div> 
              </form>
            </div>
          </div>
        </div>


        <!--新增hr弹框 end--> 


        <!--新增员工弹框-->    
        <div class="modal fade hmodal-success form-row" data-panel="modal" id="myModal7" tabindex="-1" role="dialog"  aria-hidden="true">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="color-line"></div>
              <div class="modal-header clearfix">
                <form role="form" class=" form-horizontal formclass" id="insertForm" data-form="validator" data-action-motive="add">
                  <div class="row row_style_one">
                    <div class="col-sm-12">

                      <div class="form-group">
                        <label class="col-lg-3 new_padding">用户名：</label>
                        <div class="col-lg-9">
                          <input type="text" class="form-control content_clear" name="userCode" id="userCode" readonly="readonly">
                        </div>
                      </div> 

                      <div class="form-group">
                        <label class="col-lg-3 new_padding">姓名：</label>
                        <div class="col-lg-9 form-required">
                          <input type="text" name="userName" id="userName" class="form-control content_clear">
                        </div>
                      </div> 

                      <div class="form-group">
                        <label class="col-lg-3 new_padding">手机：</label>
                        <div class="col-lg-9 form-required">
                          <input type="text" name="mobile" id="mobile" placeholder="请输入有效数字" class="form-control content_clear" name="mobile">
                        </div>
                      </div> 

                      <div class="form-group">
                        <label class="col-lg-3 new_padding">Email：</label>
                        <div class="col-lg-9 form-required">
                          <input  name="mailAddress" id="mailAddress" class="form-control content_clear" type="email" required placeholder="请输入电子邮件">
                        </div>
                      </div> 

                      <div class="form-group">
                        <label class="col-lg-3 new_padding">用户类型：</label>
                        <div class="col-lg-9 form-required">
                          <select name="userType" id="userType" class="form-control m-b nomargin_tow" placeholder="系统管理员">
                            <c:if test="${LOGIN_USER.userType == 1 }">
                            <option value="1">平台系统管理员</option>
                          </c:if>
                          <option value="2">系统管理员</option>
                          <option value="4">HR</option>
                        </select>
                      </div>
                    </div> 
                  </div>
                </div> 


                <div class="row row_style_tow">
                  <div class="col-sm-12">

                    <div class="form-group">
                      <label class="col-lg-3 new_padding"></label>

                      <label class="col-lg-2">&nbsp;</label>
                    </div> 

                    <div class="form-group">
                      <label class="col-lg-3 new_padding">性别：</label>
                      <div class="col-lg-9">
                        <div class="sex_style">
                          <input id="optionsRadios3" name="sex" class="new_style_four sex" type="radio" value="1" checked="true">
                          <span class="sex sex_tow">男</span>
                          <input id="optionsRadios4" name="sex" class="new_style_four sex" type="radio" value="2" checked="">
                          <span class="sex sex_tow">女</spsexan>
                          </div>
                        </div>
                      </div> 

                      <div class="form-group">
                        <label class="col-lg-3 new_padding">电话：</label>
                        <div class="col-lg-9">
                          <input type="text" name="telephone" placeholder="请输入有效数字" class="form-control content_clear">
                        </div>
                      </div> 

                      <div class="form-group">
                        <label class="col-lg-3 new_padding">&nbsp;</label>
                        <div class="col-lg-9">
                        </div>
                      </div>

                      <div class="form-group">
                        <c:if test="${LOGIN_USER.userType == 1 }">
                        <label class="col-lg-3 new_padding_tow">所属公司：</label>
                        <div class="col-lg-8">
                          <input type="text" name="companyName" id="companyName" readonly value=""/>
                          <input type="hidden" name="companyId" id="companyId" value=""/>
                          <input type="button" name="companySelect" value="选择" onclick="insertCompanyTree()">
                        </div>
                        <label class="col-lg-1 hong">*</label>
                      </c:if>
                  </div> 
                </div>
              </div> 

              <div class="row">
                <div class="col-sm-12">
                  <div class="form-group">
                    <label class="col-lg-2 beizhu">备注：</label>
                    <div class="col-lg-10">
                      <textarea type="text" class="form-control new_width content_clear" name="memo" id="memo" ></textarea>
                    </div>
                  </div>
                  <center>
                    <button class="btn btn-primary" type="submit">保存</button>
                    &nbsp;
                    <button class="btn btn-primary guanbi" type="button"  data-dismiss="modal">取消</button>
                  </center>
                </div>
              </div>  
            </div> 
          </form>
        </div>
      </div>
    </div>


    <!--新增员工弹框 end--> 

    <!--修改弹框start-->
    <div class="modal fade hmodal-success form-row" data-panel="modal" id="myModal8" tabindex="-1" role="dialog"  aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="color-line"></div>
          <div class="modal-header clearfix">
            <form role="form" class=" form-horizontal formclass" id="updateForm" data-form="validator" data-action-motive="edit">
              <div class="row row_style_one">
                <div class="col-sm-12">
                  <input type="hidden" name="userId" id="update_userId"/>
                  <div class="form-group">
                    <label class="col-lg-3 new_padding">用户名：</label>
                    <div class="col-lg-9">
                      <input type="text" class="form-control content_clear" readonly="readonly" name="userCode" id="update_code">
                    </div>
                  </div> 

                  <div class="form-group">
                    <label class="col-lg-3 new_padding">姓名：</label>
                    <div class="col-lg-9 form-required">
                      <input type="text" name="userName" id="update_name" class="form-control content_clear">
                    </div>
                  </div> 

                  <div class="form-group">
                    <label class="col-lg-3 new_padding">手机：</label>
                    <div class="col-lg-9 form-required">
                      <input type="text" name="mobile" id="update_mobile" class="form-control content_clear" name="mobile">
                    </div>
                  </div> 

                  <div class="form-group">
                    <label class="col-lg-3 new_padding">Email：</label>
                    <div class="col-lg-9 form-required">
                      <input  name="mailAddress" id="update_mail" class="form-control content_clear" type="email" required>
                    </div>
                  </div> 
                </div>
              </div> 


              <div class="row row_style_tow">
                <div class="col-sm-12">
                  <div class="form-group">
                    <label class="col-lg-3 new_padding">ID：</label>
                    <label class="col-lg-2" id="update_id"></label>
                  </div>

                  <div class="form-group">
                    <label class="col-lg-3 new_padding">性别：</label>
                    <div class="col-lg-9">
                      <div class="sex_style">
                        <input id="optionsRadios5" name="sex" class="new_style_four sex" type="radio" value="1" checked="true">
                        <span class="sex sex_tow">男</span>
                        <input id="optionsRadios6" name="sex" class="new_style_four sex" type="radio" value="2" checked="">
                        <span class="sex sex_tow">女</spsexan>
                        </div>
                      </div>
                    </div> 

                    <div class="form-group">
                      <label class="col-lg-3 new_padding">电话：</label>
                      <div class="col-lg-9">
                        <input type="text" name="telephone" id="update_tel" class="form-control content_clear">
                      </div>
                    </div> 

                    <div class="form-group">
                      <label class="col-lg-3 new_padding">&nbsp;</label>
                      <div class="col-lg-9">
                      </div>
                    </div>
                  </div>
                </div> 

                <div class="row">
                  <div class="col-sm-12">
                    <div class="form-group">
                      <label class="col-lg-2 beizhu">备注：</label>
                      <div class="col-lg-10">
                        <textarea type="text" class="form-control new_width" name="memo" id="update_memo" ></textarea>
                      </div>
                    </div>
                    <center>
                      <button class="btn btn-primary" type="submit">保存</button>
                      &nbsp;
                      <button class="btn btn-primary guanbi" type="button" data-dismiss="modal">取消</button>
                    </center>
                  </div>
                </div> 
              </div>   
            </form>
          </div>
        </div>
      </div>
      <div class="panel-body">
        <table id="example2" class="table table-striped table-bordered table-hover text_align" width="100%">
          <thead>
            <tr>
              <!--<th>序号</th>-->
              <th>用户名</th>
              <th>姓名</th>
              <th>Email</th>
              <th>类型</th>
              <th>状态 </th>
              <!--<th>锁定</th>-->
              <th>更新人</th>
              <th>更新日期</th>
              <th>操作</th>
            </tr>
          </thead>
          <tbody>
          </tbody>
        </table>
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
  <!--多选下拉-->
  <script src="static/bootstrap/vendor/moment/moment.js"></script>
  <script src="static/bootstrap/vendor/xeditable/bootstrap3-editable/js/bootstrap-editable.min.js"></script>
  <script src="static/bootstrap/vendor/select2-3.5.2/select2.min.js"></script>
  <script src="static/bootstrap/vendor/bootstrap-touchspin/dist/jquery.bootstrap-touchspin.min.js"></script>
  <script src="static/bootstrap/vendor/bootstrap-datepicker-master/dist/js/bootstrap-datepicker.min.js"></script>
  <!--多选下拉结束-->
  <script src="static/bootstrap/vendor/jquery-validation/jquery.validate.min.js"></script>
  <!-- for datatable -->
  <script src="static/bootstrap/vendor/datatables/media/js/jquery.dataTables.min.js"></script>
  <script src="static/bootstrap/vendor/datatables_plugins/integration/bootstrap/3/dataTables.bootstrap.min.js"></script>
  <!-- alert -->
  <script src="static/bootstrap/vendor/sweetalert/lib/sweet-alert.min.js"></script>
  <!-- App scripts -->
  <script src="static/bootstrap/scripts/homer.js"></script>
  <!-- 表单验证 -->
  <script src="static/js/plugins/form.validation/js/formValidation.js"></script>
  <script src="static/js/plugins/form.validation/js/framework/bootstrap.js"></script>
  <script src="static/js/plugins/form.validation/js/language/zh_CN.js"></script> 
  <!--私有-->
  <script src="static/js/user_magement.js"></script>
  <script src="static/js/template.js"></script>

<script>
   $(function(){
	   var table = $('#example2').dataTable({
  	   		"dom":
	   	   		"<'row'<'col-sm-6'l><'col-sm-5'f><'col-sm-1'<'toolbar'>>>" +
	   	   		"<'row'<'col-sm-12'tr>>" +
	   	   		"<'row'<'col-sm-5'i><'col-sm-7'p>>",
  	    	"processing": true,
  	        "serverSide": true,
  	        
  	        "sort":true,
  	        "columns": [
				/*{ "data": "userId" },*/
				{ "data": "userCode" },
				{ "data": "userName" },
				{ "data": "mailAddress" },
				{ "data": "utype"},
				{ "data": "valid"},
				/*{ "data": "locked" },*/
				{ "data": "lastModifyUserName" },
				{ "data": "modifyDate" },
				{ "render": function render( data, type, row, meta ){
					var str = '<li><a class="demo4" onclick="userYDeleteClick('+row.userId+',0)"><i class="fa fa-times "></i> 无效</a></li>  ';
					if(row.isValid == 0){
						str = '<li><a class="demo4" onclick="userNDeleteClick('+row.userId+',1)"><i class="fa fa-times "></i> 生效</a></li>  ';
					}
					return   '<div class="btn-group"><button data-toggle="dropdown" class="btn btn-info btn-xs dropdown-toggle">操作<span class="caret"></span></button>'+
							 '<ul class="dropdown-menu">'+
							 /*' <li><a onclick="userPowerEnter('+row.userId+',this)" data-toggle="modal"><i class="fa pe-7s-config"></i> 设置权限</a></li>'+*/
                		<% if(RequestUtil.hasPower("index_user_pr")){ %>
							 ' <li><a class="demo1" onclick="resetPassword('+row.userId+')"><i class="fa fa-rotate-left"></i> 密码重置</a></li>'+
                		<% } %>
                		<% if(RequestUtil.hasPower("index_user_mu")){ %>
							 ' <li><a onclick="updateUserEnter('+row.userId+',this)" data-toggle="modal"><i class="fa fa-check"></i> 修改</a></li>'+
                		<% } %>
                		<% if(RequestUtil.hasPower("index_user_ms")){ %>
							 str+
                		<% } %>
                		<% if(RequestUtil.hasPower("index_user_du")){ %>
							 <%--'<li><a class="demo4" onclick="userDeleteUH('+row.userId+',1)"><i class="fa fa-times "></i> 删除</a></li>  '+--%> 
                		<% } %>
						     '</ul></div>';
				    } }
  				],
  	           "language": {
  	  			 	"search": "过滤:",
  	  			 	"processing":"数据加载中",
	   	            "lengthMenu": "每页显示 _MENU_ 条记录",
	   	            "zeroRecords": "暂无数据 - 报歉啦〜",
	   	            "info": "显示 第 _PAGE_ 页 共 _PAGES_ 页",
	   	            "infoEmpty": "暂无数据",
	   	            "infoFiltered": "(筛选自 _MAX_ 条记录)",
	   				"paginate":{
	   					"first":"首页",
	   					"previous":"前一页",
	   					"next":"后一页",
	   					"last":"尾页"
	   				}
  	  			 },   
  	        "ajax":{
  	            "url": "user/userDataTableSearch.do",
  	            "type":"POST",
  	            "data": function ( d ) {
  	                d.data = JSON.stringify($("#userForm").serializeObject());
  	            }
  	        }
  	    });

  	   	$("div.toolbar").html('<button class="btn btn-info  btn-sm new_padding_padding" type="button" data-toggle="collapse" data-target="#collapseExample" aria-expanded="false" aria-controls="collapseExample"><i class="fa">高级搜索</i></button>');

	   	$(".guanbi").click(function(){
	   		 // $("#myModal7").modal('hide');
	   		 // $("#myModal8").modal('hide');
	   		 $("#myModal9").modal('hide');
	   		 $("#myModal10").modal('hide');
	   		 $(".content_clear").val("");
	   	});

        //新增和修改的验证
        var validatorOptions = {
            framework: 'bootstrap',
            err: {
                container: 'tooltip'
            },
            icon: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                userName: {
                    validators: {
                        notEmpty: {
                            message: '请填写必填项'
                        },
                        callback: {
                          message: '英文字母 100 个字符；中文 50 个字符',
                          callback: function(value, validator, $field){
                            var valid = {
                              latin: {
                                reg: /^([A-Za-z])+$/,
                                max: 100,
                                min: 1
                              },
                              hans: {
                                reg: /^([\u4E00-\u9FA5])+$/,
                                max: 50,
                                min: 1
                              }
                            }
                            if( (valid.latin.reg).test(value) ){
                              if( value.length > ( valid.latin.max ) ) return false
                              return true;
                            }else if( (valid.hans.reg).test(value) ){
                              if( value.length > ( valid.hans.max ) ) return false
                              return true;
                            }

                            return false;
                          }
                        }
                    }
                },
                mobile: {
                    validators: {
                        notEmpty: {
                            message: '请填写必填项'
                        },
                        phone: {
                            country: 'CN',
                            message: '请输入中国区域的手机或电话号码'
                        }
                    }
                },
                mailAddress: {
                    validators: {
                        notEmpty: {
                            message: '请填写必填项'
                        },
                        stringLength: {
                            message: '请输入小于50个字符',
                            max: '50'
                        },
                        regexp: {
                            message: '请输入有效的邮箱',
                            regexp: /^([a-zA-Z0-9_\-\.])+@(\w)+((\.\w+)+)$/
                        }
                    }
                },
                userType: {
                    enabled: false, 
                    validators: {
                        notEmpty: {
                            message: '请填写必填项'
                        }
                    }
                }
            }
        };
      $('[data-form="validator"]')
      .formValidation(validatorOptions)
      .find('[name="userType"]')
      .each(function(index, el){
        var fv = $(el).parents('form').data('formValidation');
        fv.enableFieldValidators('userType', true).revalidateField('userType');
      })
      .end()
      .on('success.form.fv', function(e){
        e.preventDefault();
        var getActionMotive = $(e.target).attr('data-action-motive');
        switch(getActionMotive){
          case 'add':
            insertUser();
            break;
          case 'edit':
            updateUser();
            break;
        }
        
      });

      $('[data-panel="modal"]').on('hide.bs.modal', function (e) {
        $(e.target).find('form').formValidation('resetForm');
        $(e.target).find('form')[0].reset();
      })
    });
   
   	//高级搜索
   	function search(){
   		$('#example2').dataTable().api().ajax.reload();
   	}
    
    function userYDeleteClick(userId,isValid){
    	swal({
	        title: "确定要将此用户状态变更为无效?",
	        text: "注意：点击后，用户状态将变成无效!",
	        type: "warning",
	        showCancelButton: true,
	        confirmButtonColor: "#DD6B55",
	        confirmButtonText: "是,请变更该用户!",
	        cancelButtonText: "不, 放弃此操作!",
	        closeOnConfirm: false,
	        closeOnCancel: false },
        function (isConfirm) {
            if (isConfirm) {
            	$.ajax({
			        cache: true,
			        type: "POST",
			        url:"user/deleteUser.do",
			        data:{"userId":userId,
			       		  "isValid":isValid},
			        dataType: 'json',
			        async: false,
			        success: function(data) {
			            if(data.success == true){
			            	swal("操作成功!", "该用户状态变更为无效.", "success");
                			window.location.href="user/searchUser.do";
			            }else{
			            	swal("操作失败!", "遇到问题了.", "error");
                			window.location.href="user/searchUser.do";
			            }
			        }
		        });
            } else {
                swal("已取消", "用户未删除!", "error");
            }
        });
    }
    
     function userNDeleteClick(userId,isValid){
    	swal({
	        title: "确定要将此用户状态变更为有效?",
	        text: "注意：点击后，用户状态将变成有效!",
	        type: "warning",
	        showCancelButton: true,
	        confirmButtonColor: "#DD6B55",
	        confirmButtonText: "是,请变更该用户!",
	        cancelButtonText: "不, 放弃此操作!",
	        closeOnConfirm: false,
	        closeOnCancel: false },
        function (isConfirm) {
            if (isConfirm) {
            	$.ajax({
			        cache: true,
			        type: "POST",
			        url:"user/deleteUser.do",
			        data:{"userId":userId,
			       		  "isValid":isValid},
			        dataType: 'json',
			        async: false,
			        success: function(data) {
			            if(data.success == true){
			            	swal("操作成功!", "该用户状态变更为有效.", "success");
                			window.location.href="user/searchUser.do";
			            }else{
			            	swal("操作失败!", "遇到问题了.", "error");
                			window.location.href="user/searchUser.do";
			            }
			        }
		        });
            } else {
                swal("已取消", "用户未删除!", "error");
            }
        });
    }

    // 密码重置弹框
    function resetPassword(id,isDelete){
	    swal({
	        title: "确定要重置此用户的密码吗?",
	        type: "warning",
	        showCancelButton: true,
	        confirmButtonColor: "#DD6B55",
	        confirmButtonText: "是!",
	        cancelButtonText: "否",
	        closeOnConfirm: false,
	        closeOnCancel: false },
	        function (isConfirm) {
	            if (isConfirm) {
	            	$.ajax({
				        cache: true,
				        type: "GET",
				        url:"resetPwd/" + id,
				        dataType: 'json',
				        async: false,
				        success: function(data) {
				            if(data.success == true){
				            	swal("重置密码请求发送成功!", "请通知用户登录邮箱设置新密码.", "success");
				            }else{
				            	swal("重置密码请求发送失败!", data.msg, "error");
				            }
				        }
			        });
	            } else {
	                swal("已取消", "密码未重置!", "error");
	            }
	        }
	    );
    }

    function userDeleteUH(id,isDelete){
    	swal({
	        title: "确定要删除此用户吗?",
	        text: "注意：用户删除后，用户将不能恢复!",
	        type: "warning",
	        showCancelButton: true,
	        confirmButtonColor: "#DD6B55",
	        confirmButtonText: "是,请删除该用户!",
	        cancelButtonText: "不, 放弃此操作!",
	        closeOnConfirm: false,
	        closeOnCancel: false },
	        function (isConfirm) {
	            if (isConfirm) {
	            	$.ajax({
				        cache: true,
				        type: "POST",
				        url:"user/isDeleteUser.do",
				        data:{"userId":id,
				        	  "isDelete":isDelete},
				        dataType: 'json',
				        async: false,
				        success: function(data) {
				            if(data.success == true){
				            	swal("删除成功!", "该用户已经被删除.", "success");
	                			window.location.href="user/searchUser.do";
				            }else{
				            	swal("删除失败!", "遇到问题了.", "error");
	                			window.location.href="user/searchUser.do";
				            }
				        }
			        });
	            } else {
	                swal("已取消", "用户未删除!", "error");
	            }
	        }
	    );
    }

    //重置密码
    // function pwdResetClick(userId){
    // 	$.ajax({
	   //      cache: true,
	   //      type: "POST",
	   //      url:"user/pwdReset.do",
	   //      data:{"userId":userId},
	   //      dataType: 'json',
	   //      async: false,
	   //      success: function(data) {
	   //          alert(data.msg);
	   //      }
    //     });
    // }
    
    function insertUserEnter(btn){
    	console.log($("#insertForm").find("[name='companyId']"));
    	$("#insertForm").find("input[type='text']").each(function(){
			 $(this).val("");
		  });
  		$("#userType").val("2");
  		$("#optionsRadios3").attr("checked", "checked");
  		//$("#insertForm").find("#companyId").val("");
  		$("#userDistinguish").val("");
  		$("#memo").val("");//备注
  		$(btn).attr("data-target","#myModal7");
    }

    function insertHREnter(btn){
    	console.log($("#insertForm").find("[name='companyId']"));
    	$("#insertForm").find("input[type='text']").each(function(){
			 $(this).val("");
		  });
  		$("#userType").val("2");
  		$("#optionsRadios3").attr("checked", "checked");
  		//$("#insertForm").find("#companyId").val("");
  		$("#userDistinguish").val("");
  		$("#memo").val("");//备注
  		$(btn).attr("data-target","#myModal10");
    }
    
    function updateUserEnter(id, btn){
    	$.ajax({
	        cache: true,
	        type: "POST",
	        url:"user/updateUserEnter.do",
	        data:{"userId":id},
	        dataType: 'json',
	        async: false,
	        success: function(data) {
	        	$("#update_userId").val(data.user.userId);
	        	$("#update_id").text(data.user.userId);
	            $("#update_code").val(data.user.userCode);
	            $("#update_name").val(data.user.userName);
	            $("#update_mobile").val(data.user.mobile);
	            $("#update_mail").val(data.user.mailAddress);
	            $("#update_type").val(data.user.userType);
	            if(data.user.sex == 1){
	            	$("#optionsRadios5").attr("checked","checked");
	            }else{
	            	$("#optionsRadios6").attr("checked","checked");
	            }
	            $("#update_tel").val(data.user.telephone);
	            $("#update_locked").val(data.user.isLocked);
	            $("#update_valid").val(data.user.isValid);
 	            $("#companyName").val(data.user.companyName);
	            $("#updateForm").find("#companyId").val(data.user.companyId);
	            $("#update_istinguish").val(data.user.userDistinguish);
	            $("#update_memo").val(data.user.memo);
	        }
        });
    	$(btn).attr("data-target","#myModal8");
    }
    
    function insertUser(){
    	var userCode = $("#userCode").val();
  		var userName = $("#userName").val();
  		var mailAddress = $("#mailAddress").val();
  		var companyId = $("#insertForm").find("[name='companyId']").val();
  		var userType = $("#userType").val();
  		var mobile = $("#mobile").val();
  		if(check(userCode,userName,mailAddress,companyId,userType,mobile)){
  			$.ajax({
  		        cache: true,
  		        type: "POST",
  		        url:"user/addOrUpdateUser.do",
  		        data:$('#insertForm').serialize(),
  		        dataType: 'json',
  		        async: false,
  		        success: function(data) {
  		            alert(data.msg);
  		            if(data.success == true){
  		            	window.location.href="user/searchUser.do";
  		            }
  		        }
  	        });
  		}
    }
    
     function insertHr(){
    	//var userCode = $("#userCodeHR").val();
		var userName = $("#userNameHR").val();
		var mailAddress = $("#mailAddressHR").val();
		var companyId = $("#insertForm").find("[name='companyId']").val();
		var userType = $("#userTypeHR").val();
		var mobile = $("#mobileHR").val();
		if(check('a',userName,mailAddress,companyId,userType,mobile)){
			$.ajax({
		        cache: true,
		        type: "POST",
		        url:"user/addOrUpdateHR.do",
		        data:$('#insertHrForm').serialize(),
		        dataType: 'json',
		        async: false,
		        success: function(data) {
		            alert(data.msg);
		            if(data.success == true){
		            	window.location.href="user/searchUser.do";
		            }
		        }
	        });
		}
    }
    
    function updateUser(){
    	var userCode = $("#update_code").val();
		var userName = $("#update_name").val();
		var mailAddress = $("#update_mail").val();
		var companyId = $("#updateForm").find("[name='companyId']").val();
		var userType = $("#update_type").val();
		var mobile = $("#update_mobile").val();
		if(check(userCode,userName,mailAddress,companyId,userType,mobile)){
			$.ajax({
		        cache: true,
		        type: "POST",
		        url:"user/addOrUpdateUser.do",
		        data:$('#updateForm').serialize(),// form
		        dataType: 'json',
		        async: false,
		        success: function(data) {
		            alert(data.msg);
		            if(data.success == true){
		            	window.location.href="user/searchUser.do";
		            }
		        }
	        });
		}
    }
    
     function updateHR(){
    	var userCode = $("#update_code").val();
		var userName = $("#update_name").val();
		var mailAddress = $("#update_mail").val();
		var companyId = $("#updateForm").find("[name='companyId']").val();
		var userType = $("#update_type").val();
		var mobile = $("#update_mobile").val();
		if(check(userCode,userName,mailAddress,companyId,userType,mobile)){
			$.ajax({
		        cache: true,
		        type: "POST",
		        url:"user/addOrUpdateHR.do",
		        data:$('#updateForm').serialize(),// form
		        dataType: 'json',
		        async: false,
		        success: function(data) {
		            alert(data.msg);
		            if(data.success == true){
		            	window.location.href="user/searchUser.do";
		            }
		        }
	        });
		}
    }
    function check(userCode,userName,mailAddress,companyId,userType,mobile){
    	if(userCode == null || userCode == ""){
			alert("用户名不能为空！");
			return false;
		}
    	if(userName == null || userName == ""){
			alert("姓名不能为空！");
			return false;
		}
		if(mobile == null || mobile == ""){
			alert("手机不能为空！");
			return false;
		}
		if(mailAddress == null || mailAddress == ""){
			alert("Email不能为空！")
			return false;
		}
		if(!isEmail(mailAddress)){
		    alert("Email地址不合法");
		    return false;
		}
		<c:if test="${LOGIN_USER.userType == 1 }">
        if(companyId == null || companyId == ""){
            alert("所属公司不能为空");
            return false;
        }
        if(companyId == 0 && userType != 1){
            alert("平台用户只能是平台系统管理员");
            return false;
        }
        if(companyId != 0 && userType == 1){
            alert("非平台用户不能是平台系统管理员");
            return false;
        }
        </c:if>
        return true;
    }
    
    function isEmail(email){
	    if(email=="")
	        return true;
	    var str=/\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*/;
	    return (str).test(email);
	}
	
	function openCompanyTree(){
		var companyId = $("#companyId").val();
    	var t="view/user/companyTree.jsp?companyId="+companyId;
		showWindow(t,"550px", "450px");
    }
	
	function userPowerEnter(id, btn){
		$.ajax({
	        cache: true,
	        type: "POST",
	        url:"user/userPowerEnter.do",
	        data:{"userId":id},
	        dataType:'json',
	        async: false,
	        success: function(data) {
	       	    $("#userCodeName").html(data.user.userCode+" "+data.user.userName);
	        	$("#user_power").html(template('data', data));
	        	addPowerRow(data);
		    	$(".js-source-states-3").select2();
	        }
		});
		
		$(btn).attr("data-target","#myModal9");
	}
	
	function fieldIdChange(fieldId){
	    var fieldIds = document.getElementsByName("fieldId");
	    var fieldType = $(fieldId).find("option:selected").attr("fieldtype");
	    var operateType;
	    var fieldValue;
	    for(var i = 0; i < fieldIds.length; i++){
	        if(fieldId==fieldIds[i]){
	            operateType = document.getElementsByName("operateType")[i];
	            fieldValue = document.getElementsByName("field_value")[i];
	            break;
	        }
	    }
	    operateType.value = "";
	    //fieldValue.value = "";
	    var html = "";
	    if(fieldType >= 4){
	    	operateType.disabled = true;
	    	$.ajax({
		        cache: true,
		        type: "POST",
		        url:"user/getFieldValue.do",
		        data:{"fieldId":fieldId.value},
		        dataType:'json',
		        async: false,
		        success: function(data) {
		       	    html +="<select name=\"fieldValue\" class=\"js-source-states-3 form-control m-b nomargin_tow new_style_third\" multiple=\"multiple\">";
					for(var i = 0; i < data.length; i++){
						html += "<option value=\""+data[i][0]+"\">"+data[i][1]+"</option>";
					}
					html +="</select>";
		        }
			});
	    }else{
	    	operateType.disabled = false;
	    	html += "<input type=\"text\" name=\"fieldValue\" /> ";
	    }
	    $(fieldValue).html(html);
		$(".js-source-states-3").select2();
	}
	
	function fieldTypeChange(fieldType){
	    var fieldTypes=document.getElementsByName("fieldType");
	    var fieldId;
	    var operateType;
	    var fieldValue;
	    var fieldValueId;
	    for(var i=0;i<fieldTypes.length;i++){
	        if(fieldType == fieldTypes[i]){
	            fieldId=document.getElementsByName("fieldId")[i];
	            operateType=document.getElementsByName("operateType")[i];
	            fieldValue=document.getElementsByName("fieldValue")[i];
	            selectWin=document.getElementsByName("selectWin")[i];
	            break;
	        }
	    }
	    fieldId.value="";
	    operateType.value="";
	    $(fieldValue).children().removeAttr("selected");
	    fieldId.disabled=true;
	    operateType.disabled=true;
	    fieldValue.disabled=true;
	    if(fieldType.value == 1){
	        fieldId.disabled = false;
	    }
	}
	
	//权限设置-添加一行
	function addPowerRow(data){
		var rowNum = data.listPower.length; 
		var point = data.listFunctionPoint;
		var field = data.listField;
		var fvalue = data.fieldValue;
		//新增行 
		$("#addRow_tow").bind("click",function(){
			rowNum++; 
			var rowHtml="";
			rowHtml +="<div class=\"form-group quanxian_div clearfix quanxian_div_third\" id='"+rowNum+"'>";
			rowHtml +="<span class=\"col-lg-2 selcct_span\">";
			rowHtml +="<select class=\"form-control m-b nomargin_tow new_style_third\" name=\"functionPointCode\">";
			for(var i = 0; i < point.length; i++){
				rowHtml += "<option value=\""+point[i].functionPointCode+"\">"+point[i].functionPointName+"</option>";
			}
			rowHtml +="</select>";
			rowHtml +="</span>";
			rowHtml +="<span class=\"col-lg-2 selcct_span\">";
			rowHtml +="<select class=\"form-control m-b nomargin_tow new_style_third\" name=\"fieldType\" onchange=\"fieldTypeChange(this)\">";
			rowHtml +="<option value=\"\" selected=\"selected\"></option>";
			rowHtml +="<option value=\"1\">字段条件项</option>";
			rowHtml +="<option value=\"2\">AND</option>";
			rowHtml +="<option value=\"3\">OR</option>";
			rowHtml +="<option value=\"4\">(</option>";
			rowHtml +="<option value=\"5\">)</option>";
			rowHtml +="</select>";
			rowHtml +="</span>";
			rowHtml +="<span class=\"col-lg-2 selcct_span\">";
			rowHtml +="<select name=\"fieldId\" class=\"form-control m-b nomargin_tow new_style_third\" disabled=\"true\" onchange=\"fieldIdChange(this)\">";
			rowHtml += "<option fieldtype=\"\" value=\"\" selected=\"selected\"></option>";
			for(var i = 0; i < field.length; i++){
				rowHtml += "<option fieldtype=\""+field[i].fieldType+"\" value=\""+field[i].fieldId+"\">"+field[i].fieldDesc+"</option>";
			}
			rowHtml +="</select>";
			rowHtml +="</span>";
			rowHtml +="<span class=\"col-lg-2 selcct_span\">";
			rowHtml +="<select disabled=\"true\" class=\"form-control m-b nomargin_tow new_style_third\" name=\"operateType\" placeholder=\"操作1\">";
			rowHtml +="<option value=\"\"></option>";
			rowHtml +="<option value=\"1\">=</option>";
	 	    rowHtml +="<option value=\"2\" > ></option>";
	 	    rowHtml +="<option value=\"3\" > < </option>";
	 	    rowHtml +="<option value=\"4\" >>=</option>";
	 	    rowHtml +="<option value=\"5\" ><=</option>";
	 	    rowHtml +="<option value=\"6\" >左匹配</option>";
	 	    rowHtml +="<option value=\"7\" >右匹配</option>";
	 	    rowHtml +="<option value=\"8\" >任意匹配</option>";
	        rowHtml +="<option value=\"9\" >in</option>";
			rowHtml +="</select>";
			rowHtml +="</span>";
			rowHtml +="<span class=\"col-lg-2 selcct_span\" name=\"field_value\">";
			rowHtml +="<input type=\"text\" disabled=\"true\" name=\"fieldValue\" />";
			rowHtml +="</span>";
			rowHtml +="<span class=\"col-lg-2 selcct_span\">";
			rowHtml +="<button class=\"btn btn-info btn-xs dropdown-toggle user_demo button_height\" onClick=\"deleteRow("+rowNum+")\">";
			rowHtml +="<a class=\"wcolor\">"+"<i class=\"fa fa-times wcolor\">"+"</i>删除</a>";
			rowHtml +="</button>";
			rowHtml +="</span>";
			rowHtml +="</div>";
			$("#user_power").append(rowHtml);
		    //$(".js-source-states-3").select2();
		
			/*$(".tianjia").eq(0).after($(".tianjia").eq(0).clone().addClass("hehe"+rowNum));*/
		});
	}
	
	function doSubmitClick(){
   		var userId = $("#userId").val();
   		var list = "";
   		var b = true;
    	$("#user_power").find(".quanxian_div_third").each(function(i){
    		var functionPointCode = $(this).find("[name='functionPointCode']").val();
    		var fieldType = $(this).find("[name='fieldType']").val();
    		var fieldId = $(this).find("[name='fieldId']").val();
    		var operateType = $(this).find("[name='operateType']").val();
    		var fieldValue = $(this).find("[name='fieldValue']").val();
    		fieldId = fieldId == "" ? "-1" : fieldId;
    		operateType = operateType == "" ? "-1" : operateType;
    		fieldValue = (fieldValue == "" || fieldValue == null) ? "-1" : fieldValue;
    		if(fieldType == "" || fieldType == "undefined"){
                alert("第"+(i+1)+"行没有选择类型");
                b = false;
                return false;
            }
            if(fieldType == 1){
            	var fType = $(this).find("[name='fieldId']").find("option:selected").attr("fieldtype");
            	if(parseInt(fType) >= 4){
		    		if(fieldValue == "-1" || fieldValue == "undefined" || fieldValue == "null"){
		               alert("第"+(i+1)+"行没有选择值");
                	   b = false;
		               return false;
		       		}else{
			       		operateType = fieldValue;
			       		fieldValue = "";
			       		$(this).find("[name='fieldValue']").find("option:selected").each(function(i){
			       			fieldValue += $(this).text()+",";
			       		});
		       		}
                }else{
		       		if(operateType == "-1" || operateType == "undefined"){
		                alert("第"+(i+1)+"行没有选择操作");
                		b = false;
		                return false;
		        	}
		        	if(fieldValue == "-1"|| fieldValue == "undefined"){
		                alert("第"+(i+1)+"行没有选择值");
                		b = false;
		                return false;                    
		        	}
	        	}
	        }
    		list += functionPointCode+"#"+fieldType+"#"+fieldId+"#"+operateType+"#"+fieldValue+"/";
    	})
    	if(b){
	    	if(list == ""){
	    		alert("您没有添加权限！");
	    		return false;
	    	}
	    	$.ajax({
		        cache: true,
		        type: "POST",
		        url:"user/userPowerSave.do",
		        data:{"userId":userId, "field":list},
		        dataType: 'json',
		        async: false,
		        success: function(data) {
		            alert(data.msg);
		            if(data.success == true){
		            	$("#myModal9").modal('hide');
		            }
		        }
	        });
        }
	} 
	
	//form表单转成json格式
    $.fn.serializeObject = function(){  
       var o = {};  
       var a = this.serializeArray();  
       $.each(a, function() {  
           if (o[this.name]) {  
               if (!o[this.name].push) {  
                   o[this.name] = [o[this.name]];  
               }  
               o[this.name].push(this.value || '');  
           } else {  
               o[this.name] = this.value || '';  
           }  
       });  
       return o;  
    };  


	$("#mobile").blur(function(){
		var uc = $("#mobile").val();
		$("#userCode").val(uc);
	})
</script>
</body>
</html>
