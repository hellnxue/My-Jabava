<%@ page contentType="text/html; charset=utf-8" %>
<%@page import="com.jabava.utils.RequestUtil"%>
<!DOCTYPE html>
<html>
<head>
    <base href="${pageContext.request.contextPath}/">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- Page title -->
    <title>社保信息</title>
    <jsp:include flush="true" page="../common/styles.jsp"></jsp:include>
    <link rel="stylesheet" href="static/js/plugins/form.validation/css/formValidation.css">
    <link rel="stylesheet" href="static/bootstrap/vendor/blueimp-gallery/css/blueimp-gallery.min.css">
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
        <!-- 放主要内容 -->
        <!--社保信息-->
        <!-- 开始 -->
        <div class="content animate-panel">
            <div class="row">
                <div class="col-lg-12">
                    <div class="hpanel">
                        <div class="panel-heading">
                            <h4 class="text-center font-bold">
                                <button onclick="toEmployeeList()" class="btn btn-default btn-sm btn-absolute" type="button">　返回　</button>
                                <span>员工资料</span>
                            </h4>
                        </div>
                        <!--引入员工信息导航 开始--> 
                        <jsp:include flush="true" page="employee_nav.jsp">
                        <jsp:param value="socialsecurity" name="type"/>
                    </jsp:include>
                    <!--引入员工信息导航 结束-->
                    <div class="panel-body">
                        <h4 class="text-center font-bold">社保公积金信息</h4>
                        <div data-container="socialsecurity">
                        <script type="text/html" id="socialsecurity">
                        <div class="">
                            <form method="post" role="form" class="form-horizontal" enctype="multipart/form-data" data-form="validator">
                                <input type="hidden" name="personid" value="{{socialsecurity.personid}}">
                                <input type="hidden" name="profileId" value="{{socialsecurity.profileId}}">
                                <fieldset>
                                    <legend class="text-info p-xs">社保信息</legend>
                                    <div class="col-md-6 col-lg-6">
                                        <div class="form-group">
                                            <label for="" class="control-label col-md-3 col-lg-3">参保城市：</label>
                                            <div class="col-md-9 col-lg-9">
                                                <div class="input-group-static hidden">
                                                    <p class="form-control-static">
                                                    {{each cityList as $item}}
                                                        {{if ($item.baseDataCode == socialsecurity.securityCity)}}
                                                            {{$item.baseDataName}}
                                                        {{/if}}
                                                    {{/each}}
                                                    </p>
                                                </div>
                                                <div class="form-required">
                                                    <select class="form-control select2" name="securityCity" data-toggle="select2">
                                                        <option value="">请选择</option>
                                                        {{each cityList as $item}}
                                                        <option value="{{$item.baseDataCode}}"{{if ($item.baseDataCode == socialsecurity.securityCity)}} selected="selected"{{/if}}>{{$item.baseDataName}}</option>
                                                        {{/each}}
                                                    </select>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6 col-lg-6">
                                        <div class="form-group">
                                            <label for="" class="control-label col-md-3 col-lg-3">社保缴纳方式：</label>
                                            <div class="col-md-9 col-lg-9">
                                            {{if socialsecurity.securityCreateType===0 || socialsecurity.securityCreateType===1 }}
                                                <div class="input-group-static">
                                                    <p class="form-control-static">
                                                    {{if socialsecurity.securityCreateType==0}}新开{{/if}}
                                                    {{if socialsecurity.securityCreateType==1}}续缴{{/if}}
                                                    </p>
                                                </div>
                                            {{else}}
                                                <div class="form-required">
                                                    <select class="form-control" name="securityCreateType" data-toggle="select2">
                                                        <option value="0">新开</option>
                                                        <option value="1">续缴</option>
                                                    </select>
                                                </div>
                                            {{/if}}
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6 col-lg-6">
                                        <div class="form-group">
                                            <label for="" class="control-label col-md-3 col-lg-3">社保账号：</label>
                                            <div class="col-md-9 col-lg-9">
                                            {{if socialsecurity.securityAccount}}
                                                <div class="input-group-static">
                                                    <p class="form-control-static">{{socialsecurity.securityAccount}}</p>
                                                </div>
                                            {{else}}
                                                <div class="form-required">
                                                    <input type="text" class="form-control"  name="securityAccount">
                                                </div>
                                            {{/if}}
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6 col-lg-6">
                                        <div class="form-group">
                                            <label for="" class="control-label col-md-3 col-lg-3">社保起缴月：</label>
                                            <div class="col-md-9 col-lg-9">
                                            {{if socialsecurity.securityStarttime}}
                                                <div class="input-group-static">
                                                    <p class="form-control-static">{{socialsecurity.securityStarttime | dateFormat}}</p>
                                                </div>
                                            {{else}}
                                                <div class="input-group date form-required" data-toggle="datepicker">
                                                    <input type="text" class="form-control"  name="securityStarttime" value="">
                                                    <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                                </div>
                                            {{/if}}
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6 col-lg-6">
                                        <div class="form-group">
                                            <label for="" class="control-label col-md-3 col-lg-3">社保办理月：</label>
                                            <div class="col-md-9 col-lg-9">
                                            {{if socialsecurity.securityActivatetime}}
                                                <div class="input-group-static">
                                                    <p class="form-control-static">{{socialsecurity.securityActivatetime | dateFormat}}</p>
                                                </div>
                                            {{else}}
                                                <div class="input-group date form-required" data-toggle="datepicker">
                                                    <input type="text" class="form-control"  name="securityActivatetime" value="">
                                                    <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                                </div>
                                            {{/if}}
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6 col-lg-6">
                                        <div class="form-group">
                                            <label for="" class="control-label col-md-3 col-lg-3">个人社保基数：</label>
                                            <div class="col-md-9 col-lg-9">
                                            {{if socialsecurity.securityBase}}
                                                <div class="input-group-static">
                                                    <p class="form-control-static">{{socialsecurity.securityBase}}</p>
                                                </div>
                                            {{else}}
                                                <input type="text" class="form-control" name="securityBase" value="">
                                            {{/if}}
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6 col-lg-6">
                                        <div class="form-group">
                                            <label for="" class="control-label col-md-3 col-lg-3">企业社保基数：</label>
                                            <div class="col-md-9 col-lg-9">
                                            {{if socialsecurity.securityOrgBase}}
                                                <div class="input-group-static">
                                                    <p class="form-control-static">{{socialsecurity.securityOrgBase}}</p>
                                                </div>
                                            {{else}}
                                                <input type="text" class="form-control" name="securityOrgBase" value="">
                                            {{/if}}
                                            </div>
                                        </div>
                                    </div>
                                </fieldset>
                                <fieldset data-container="hospital" class="hidden">
                                    <input type="hidden" name="hospitalState">
                                {{each [1,2,3,4]}}
                                    <div class="col-md-6 col-lg-6">
                                        <div class="form-group">
                                            <label for="" class="control-label col-md-3 col-lg-3">定点医院：</label>
                                            <div class="col-md-9 col-lg-9">
                                                <div class="input-group-static hidden">
                                                    <p class="form-control-static"></p>
                                                </div>
                                                <select name="hospital[]" class="form-control select2" data-toggle="select2">
                                                    <option value="">请选择定点医院</option>
                                                {{each hospital as $item}}
                                                    <option value="{{$item.baseDataCode}}">{{$item.baseDataName}}</option>
                                                {{/each}}
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                {{/each}}
                                </fieldset>
                                <fieldset>
                                    <legend class="text-info p-xs">公积金信息</legend>
                                    <div class="col-md-6 col-lg-6">
                                        <div class="form-group">
                                            <label for="" class="control-label col-md-3 col-lg-3">参保城市：</label>
                                            <div class="col-md-9 col-lg-9">
                                                <div class="input-group-static hidden">
                                                    <p class="form-control-static">
                                                    {{each cityList as $item}}
                                                        {{if ($item.baseDataCode == socialsecurity.gongjijinCity)}}
                                                            {{$item.baseDataName}}
                                                        {{/if}}
                                                    {{/each}}
                                                    </p>
                                                </div>
                                                <div class="form-required">
                                                    <select class="form-control select2" name="gongjijinCity" data-toggle="select2">
                                                        <option value="">请选择</option>
                                                    {{each cityList as $item}}
                                                        <option value="{{$item.baseDataCode}}"{{if ($item.baseDataCode == socialsecurity.gongjijinCity)}} selected="selected"{{/if}}>{{$item.baseDataName}}</option>
                                                    {{/each}}
                                                    </select>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6 col-lg-6">
                                        <div class="form-group">
                                            <label for="" class="control-label col-md-3 col-lg-3">公积金缴纳方式：</label>
                                            <div class="col-md-9 col-lg-9">
                                            {{if socialsecurity.gongjijinCreateType===0 || socialsecurity.gongjijinCreateType===1}}
                                                <div class="input-group-static">
                                                    <p class="form-control-static">
                                                        {{if socialsecurity.gongjijinCreateType==0}}新开{{/if}}
                                                        {{if socialsecurity.gongjijinCreateType==1}}续缴{{/if}}
                                                    </p>
                                                </div>
                                            {{else}}
                                                <div class="form-required">
                                                    <select class="form-control " name="gongjijinCreateType" data-toggle="select2">
                                                        <option value="0">新开</option>
                                                        <option value="1">续缴</option>
                                                    </select>
                                                </div>
                                            {{/if}}
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6 col-lg-6">
                                        <div class="form-group">
                                            <label for="" class="control-label col-md-3 col-lg-3">公积金账号：</label>
                                            <div class="col-md-9 col-lg-9">
                                            {{if socialsecurity.gongjijinAccount}}
                                                <div class="input-group-static">
                                                    <p class="form-control-static">{{socialsecurity.gongjijinAccount}}</p>
                                                </div>
                                            {{else}}
                                                <div class="form-required">
                                                    <input type="text" class="form-control"  name="gongjijinAccount">
                                                </div>
                                            {{/if}}
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6 col-lg-6">
                                        <div class="form-group">
                                            <label for="" class="control-label col-md-3 col-lg-3">公积金起缴月：</label>
                                            <div class="col-md-9 col-lg-9">
                                            {{if socialsecurity.gongjijinStarttime}}
                                                <div class="input-group-static">
                                                    <p class="form-control-static">{{socialsecurity.gongjijinStarttime | dateFormat}}</p>
                                                </div>
                                            {{else}}
                                                <div class="input-group date form-required" data-toggle="datepicker">
                                                    <input type="text" class="form-control"  name="gongjijinStarttime" value="">
                                                    <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                                </div>
                                            {{/if}}
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6 col-lg-6">
                                        <div class="form-group">
                                            <label for="" class="control-label col-md-3 col-lg-3">公积金办理月：</label>
                                            <div class="col-md-9 col-lg-9">
                                            {{if socialsecurity.gongjijinActivatetime}}
                                                <div class="input-group-static">
                                                    <p class="form-control-static">{{socialsecurity.gongjijinActivatetime | dateFormat}}</p>
                                                </div>
                                            {{else}}
                                                <div class="input-group date form-required"  data-toggle="datepicker">
                                                    <input type="text" class="form-control"  name="gongjijinActivatetime" value="">
                                                    <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                                </div>
                                            {{/if}}
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6 col-lg-6">
                                        <div class="form-group">
                                            <label for="" class="control-label col-md-3 col-lg-3">个人公积金基数：</label>
                                            <div class="col-md-9 col-lg-9">
                                            {{if socialsecurity.gongjijinBase}}
                                                <div class="input-group-static">
                                                    <p class="form-control-static">{{socialsecurity.gongjijinBase}}</p>
                                                </div>
                                            {{else}}
                                                <input type="text" class="form-control" name="gongjijinBase" value="">
                                            {{/if}}
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6 col-lg-6">
                                        <div class="form-group">
                                            <label for="" class="control-label col-md-3 col-lg-3">企业公积金基数：</label>
                                            <div class="col-md-9 col-lg-9">
                                            {{if socialsecurity.gongjijinOrgBase}}
                                                <div class="input-group-static">
                                                    <p class="form-control-static">{{socialsecurity.gongjijinOrgBase}}</p>
                                                </div>
                                            {{else}}
                                                <input type="text" class="form-control" name="gongjijinOrgBase" value="">
                                            {{/if}}
                                            </div>
                                        </div>
                                    </div>
                                </fieldset>
                                <fieldset>
                                    <legend class="text-info p-xs">证件上传</legend>
                                    <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
                                        <div class="form-group">
                                            <label for="" class="col-xs-3 col-sm-3 col-md-3 col-lg-3 control-label">身份证正反面：</label>
                                            <div class="col-xs-9 col-sm-9 col-md-9 col-lg-9">
                                            {{if photos['1']}}
                                                <a href="{{photos['1']}}" data-gallery="thumb-34" class="pull-left m-r-xs thumb-zone" title="身份证">
                                                    <img src="{{photos['1']}}" width="34px" alt="">
                                                </a>
                                            {{else}}
                                                <a href="javascript://" data-gallery="thumb-34" class="pull-left m-r-xs thumb-zone" title="身份证">
                                                    <img src="" width="34px" alt="">
                                                </a>
                                            {{/if}}
                                                <div class="input-group" data-toggle="upload:file">
                                                    <input type="text" class="form-control" readonly="readonly">
                                                    <div class="input-group-btn">
                                                        <div class="btn btn-default">浏览...
                                                            <input type="file" class="sr-only" name='jobpostFile' accept=".png,.jpeg,.jpg" data-file-type="1">
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="msg-cover"></div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
                                        <div class="form-group">
                                            <label for="" class="col-xs-3 col-sm-3 col-md-3 col-lg-3 control-label">银行卡：</label>
                                            <div class="col-xs-9 col-sm-9 col-md-9 col-lg-9">
                                            {{if photos['8']}}
                                                <a href="{{photos['8']}}" data-gallery="thumb-34" class="pull-left m-r-xs thumb-zone" title="银行卡">
                                                    <img src="{{photos['8']}}" width="34px" alt="">
                                                </a>
                                            {{else}}
                                                <a href="javascript://" data-gallery="thumb-34" class="pull-left m-r-xs thumb-zone" title="银行卡">
                                                    <img src="" width="34px" alt="">
                                                </a>
                                            {{/if}}
                                                <div class="input-group" data-toggle="upload:file">
                                                    <input type="text" class="form-control" readonly="readonly">
                                                    <div class="input-group-btn">
                                                        <div class="btn btn-default">浏览...
                                                            <input type="file" class="sr-only" name='jobpostFile' accept=".png,.jpeg,.jpg" data-file-type="8">
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="msg-cover"></div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
                                        <div class="form-group">
                                            <label for="" class="col-xs-3 col-sm-3 col-md-3 col-lg-3 control-label">免冠照：</label>
                                            <div class="col-xs-9 col-sm-9 col-md-9 col-lg-9">
                                            {{if photos['9']}}
                                                <a href="{{photos['9']}}" data-gallery="thumb-34" class="pull-left m-r-xs thumb-zone" title="免冠照">
                                                    <img src="{{photos['9']}}" width="34px" alt="">
                                                </a>
                                            {{else}}
                                                <a href="javascript://" data-gallery="thumb-34" class="pull-left m-r-xs thumb-zone" title="免冠照">
                                                    <img src="" width="34px" alt="">
                                                </a>
                                            {{/if}}
                                                <div class="input-group" data-toggle="upload:file">
                                                    <input type="text" class="form-control" readonly="readonly">
                                                    <div class="input-group-btn">
                                                        <div class="btn btn-default">浏览...
                                                            <input type="file" class="sr-only" name='jobpostFile' accept=".png,.jpeg,.jpg" data-file-type="9">
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="msg-cover"></div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
                                        <div class="form-group">
                                            <label for="" class="col-xs-3 col-sm-3 col-md-3 col-lg-3 control-label">户口本首页：</label>
                                            <div class="col-xs-9 col-sm-9 col-md-9 col-lg-9">
                                            {{if photos['2']}}
                                                <a href="{{photos['2']}}" data-gallery="thumb-34" class="pull-left m-r-xs thumb-zone" title="户口本首页">
                                                    <img src="{{photos['2']}}" width="34px" alt="">
                                                </a>
                                            {{else}}
                                                <a href="javascript://" data-gallery="thumb-34" class="pull-left m-r-xs thumb-zone" title="户口本首页">
                                                    <img src="" width="34px" alt="">
                                                </a>
                                            {{/if}}
                                                <div class="input-group" data-toggle="upload:file">
                                                    <input type="text" class="form-control" readonly="readonly">
                                                    <div class="input-group-btn">
                                                        <div class="btn btn-default">浏览...
                                                            <input type="file" class="sr-only" name='jobpostFile' accept=".png,.jpeg,.jpg" data-file-type="2">
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="msg-cover"></div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
                                        <div class="form-group">
                                            <label for="" class="col-xs-3 col-sm-3 col-md-3 col-lg-3 control-label">户口本本人页：</label>
                                            <div class="col-xs-9 col-sm-9 col-md-9 col-lg-9">
                                            {{if photos['10']}}
                                                <a href="{{photos['10']}}" data-gallery="thumb-34" class="pull-left m-r-xs thumb-zone" title="户口本本人页">
                                                    <img src="{{photos['10']}}" width="34px" alt="">
                                                </a>
                                            {{else}}
                                                <a href="javascript://" data-gallery="thumb-34" class="pull-left m-r-xs thumb-zone" title="户口本本人页">
                                                    <img src="" width="34px" alt="">
                                                </a>
                                            {{/if}}
                                                <div class="input-group" data-toggle="upload:file">
                                                    <input type="text" class="form-control" readonly="readonly">
                                                    <div class="input-group-btn">
                                                        <div class="btn btn-default">浏览...
                                                            <input type="file" class="sr-only" name='jobpostFile' accept=".png,.jpeg,.jpg" data-file-type="10">
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="msg-cover"></div>
                                            </div>
                                        </div>
                                    </div>
                                </fieldset>
                                <!--保存 删除-->
                                <div class="col-lg-12 col-md-12 text-center form-action">
                                    <button type="submit" class="btn btn-info">保存</button>
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
</div>

<!-- Footer-->
<!-- 放页脚  开始-->
<jsp:include flush="true" page="../common/foot.div.jsp"></jsp:include>
<!-- 放页脚  结束-->
</div>

<div id="blueimp-gallery" class="blueimp-gallery">
    <div class="slides"></div>
    <h3 class="title"></h3>
    <a class="prev">‹</a>
    <a class="next">›</a>
    <a class="close">×</a>
    <a class="play-pause"></a>
    <ol class="indicator"></ol>
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
<script src="static/bootstrap/vendor/blueimp-gallery/js/jquery.blueimp-gallery.min.js"></script>
<!-- for datatable -->
<script src="static/bootstrap/vendor/datatables/media/js/jquery.dataTables.min.js"></script>
<script src="static/bootstrap/vendor/datatables_plugins/integration/bootstrap/3/dataTables.bootstrap.min.js"></script>
<!-- alert -->
<script src="static/bootstrap/vendor/sweetalert/lib/sweet-alert.min.js"></script>

<!-- App scripts -->
<script src="static/bootstrap/scripts/homer.js"></script>
<script src="static/js/template.js"></script>

<!-- 表单验证 -->
<script src="static/js/plugins/form.validation/js/formValidation.js"></script>
<script src="static/js/plugins/form.validation/js/framework/bootstrap.js"></script>
<script src="static/js/plugins/form.validation/js/language/zh_CN.js"></script> 
<script src="static/js/yuangong.js"></script>
<script src="static/js/socialsecurity.js"></script>
<script type="text/javascript">
    window.personId = '${personId}';
</script>
</body>
</html>