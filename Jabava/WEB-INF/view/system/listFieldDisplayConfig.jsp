<%@ page contentType="text/html; charset=utf-8" %>

<!DOCTYPE html>
<html>
<head>
	<base href="${pageContext.request.contextPath}/">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <!-- Page title -->
    <title>组织人事-显示设置</title>
    <jsp:include flush="true" page="../common/styles.jsp"></jsp:include>
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
  
<!-- Main Wrapper -->
<div id="wrapper">
    <div class="small-header transition animated fadeIn">
        <div class="hpanel">
            <div class="panel-body text-center">
                组织人事-显示设置
            </div>
        </div>
    </div>
    <!-- 放主要内容 -->
    <div class="content animate-panel">
        <div class="row">
            <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
                <div class="hpanel">
                    <div class="panel-heading text-center">
                        列表不显示
                    </div>
                    <div class="panel-body" data-id="noShowList">
                        <script type="text/html" id="noListShow">
                            <div class="dd" data-id="nestable">
                                <ol class="dd-list">
                                    {{each hiddenCol as $item}}
                                        <li class="dd-item">
                                            <div class="dd-handle" data-columnName="{{$item.columnName}}">{{$item.displayName}}</div>
                                        </li>
                                    {{/each}}
                                </ol>
                            </div>
                        </script>
                    </div>
                </div>
            </div>
            <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
                <div class="hpanel">
                    <div class="panel-heading text-center">
                        列表显示
                    </div>
                    <div class="panel-body" data-id="showList">
                        <script type="text/html" id="listShow">
                            <div class="dd" data-id="nestable">
                                <ol class="dd-list">
                                    {{each displayCol as $item}}
                                        <li class="dd-item">
                                            <div class="dd-handle" data-columnName="{{$item.columnName}}">{{$item.displayName}}</div>
                                        </li>
                                    {{/each}}
                                </ol>
                            </div>
                        </script>
                    </div>
                </div>
            </div>
            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-center m-b-lg">
                <button type="button" class="btn btn-success m-r" data-action="save">保存</button>
                <button type="button" class="btn btn-default hidden" data-action="cancel">取消</button>
            </div>
        </div>
    </div> 

    <!-- 放页脚  开始-->
    <jsp:include flush="true" page="../common/foot.div.jsp"></jsp:include>
    <!-- 放页脚  结束-->
</div>

<!-- Vendor scripts -->
<script src="static/bootstrap/vendor/jquery/dist/jquery.min.js"></script>
<script src="static/bootstrap/vendor/jquery-ui/jquery-ui.min.js"></script>
<script src="static/bootstrap/vendor/slimScroll/jquery.slimscroll.min.js"></script>
<script src="static/bootstrap/vendor/bootstrap/dist/js/bootstrap.min.js"></script>
<script src="static/bootstrap/vendor/metisMenu/dist/metisMenu.min.js"></script>
<script src="static/bootstrap/vendor/iCheck/icheck.min.js"></script>
<script src="static/bootstrap/vendor/sparkline/index.js"></script>
<script src="static/bootstrap/vendor/nestable/jquery.nestable.js"></script>
<script src="static/bootstrap/vendor/moment/moment.js"></script>
<script src="static/bootstrap/scripts/homer.js"></script>
<!-- alert -->
<script src="static/bootstrap/vendor/sweetalert/lib/sweet-alert.min.js"></script>
<script src="static/js/template.js"></script>

<script>
	(function($){
        var listData;
        function renderTemplate(){
            $.ajax({
                url: 'system/listFieldDisplayConfig',
                type: 'post',
                dataType: 'json',
                async: false,
                data: {"keyTable":"ehr_person", "keyField":"person_id", "function":"EmployeeList", "module":"Organization"}
            })
            .done(function(d) {
                listData = {
                    displayCol: d.displayCol,
                    hiddenCol: d.hiddenCol
                };
                return listData;
            });
        };
        function initNestable(){
            var strShowListHtml = template('listShow', listData),
                strNoshowListHtml = template('noListShow', listData);

            $('[data-id="showList"]').prepend(strShowListHtml);
            $('[data-id="noShowList"]').prepend(strNoshowListHtml);

        	$('[data-id="nestable"]').nestable({
                group: 1,
                maxDepth: 1
            });
        };
        function save(){
            $('[data-action="save"]').on('click', function(e) {
                var gerShowList = $('[data-id="showList"]').find('ol li .dd-handle'),
                    ShowListAttr = [];

                gerShowList.each(function(index, el) {
                    ShowListAttr.push($(el).attr('data-columnname'));
                    return ShowListAttr;
                });
                var a=JSON.stringify(ShowListAttr);
                var getShowListAttrLength = ShowListAttr.length;
                var postAjax = function(){
                    $.ajax({
                        url: 'system/addDisplayCol',
                        type: 'POST',
                        dataType: 'json',
                        async: false,
                        data: {"list1": a, "function":"EmployeeList", "module":"Organization"}
                    })
                    .done(function(d) {
                        if(d.success){
                            swal({
                                title: d.msg,
                                type: "success"
                            })
                        }else{
                            swal({
                                title: d.msg,
                                type: "warning"
                            })
                        }
                    })
                }
                if(getShowListAttrLength <= 0){
                    swal({
                            title: "列表显示字段为空，是否保存?",
                            type: "warning",
                            showCancelButton: true,
                            confirmButtonColor: "#DD6B55",
                            confirmButtonText: "保存",
                            cancelButtonText: "取消"
                        },function(){
                            postAjax();
                        });
                }else{
                    postAjax();
                }
            });
        };
        function cancel(){
            $('[data-action="cancel"]').on('click', function(e) {
                $('ol.dd-list').empty();
                initNestable(); 
            });
        }
        function init(){
            renderTemplate();
            initNestable();
            save();
            cancel();
        };
        init();
    })(jQuery)
</script>
</body>
</html>
