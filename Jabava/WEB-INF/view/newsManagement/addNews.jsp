<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<base href="${pageContext.request.contextPath}/">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge">

<!-- Page title  -->
<title>资讯管理界面界面</title>
<jsp:include flush="true" page="../common/styles.jsp"></jsp:include>
<link rel="stylesheet" href="static/bootstrap/vendor/summernote/dist/summernote.css" />
<link rel="stylesheet" href="static/bootstrap/vendor/summernote/dist/summernote-bs3.css" />
<!--for 临时改变-->
<link rel="stylesheet" href="static/css/user.css">

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
                                <a class="btn btn-default btn-sm pull-left btn-return m-r-md" type="button" href="weixinnews/weixinnewsMain">返回</a>
                                <span class="news-title">新增资讯</span>
                            </h4>
                        </div>
                            <input type="hidden" id="newsId" value="<%=request.getParameter("newsId")%>"  />
                        <div class="panel-body">                            
                            <div class="hpanel email-compose">
                                <div class="panel-heading no-padding clearfix">
                                    <form method="get" class="form-horizontal">
                                      <div class="form-group">
                                          <label class="col-sm-1 control-label"><strong>标题:</strong></label>
                                          <div class="col-sm-11 col-md-11 col-lg-11">
                                              <input type="text" id="newsTitle" class="form-control" >
                                          </div>
                                      </div>
                                    </form>
                                </div>
                                <div class="panel-body no-padding">
                                    <div class="summernote" id="summernote">
                                    </div>
                                </div>
                                <div class="panel-footer clearfix">
                                  <div class="col-md-6 m-t-sm"><small>数据已于11:43保存</small></div>
                                    <div class="col-md-6 text-right">
                                        <button type="button" class="btn btn-success" onclick="addNews()">发布</button>
                                    </div>
                                </div>
                            </div>
                        </div>                        
                    </div>
                </div>
            </div>
        </div> 
        <!--主要内容结束-->

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
    <script src="static/bootstrap/vendor/summernote/dist/summernote.min.js"></script>
    <script src="static/bootstrap/vendor/summernote/lang/summernote-zh-CN.js"></script>
    
    <script src="static/bootstrap/vendor/sweetalert/lib/sweet-alert.min.js"></script>
    <!-- App scripts -->
    <script src="static/bootstrap/scripts/homer.js"></script>
    <script src="static/bootstrap/scripts/charts.js"></script>
    <script>
    function addNews(){
    	  var newsId = $("#newsId").val();
    	  if($('#newsTitle').val()==''||$('.note-editable').html()==''){
    		  swal({
                  title:'请输入标题和正文',
                  type: "error"
                  })
    	  }else{

           if(newsId!="null" && newsId!=''){
             $.ajax({
                url:"weixinnews/updateNews",        
                type : "POST",
                dataType:'json',
                data:{
                  newsId:newsId,
                  newsTitle:$('#newsTitle').val(),
                  newsContent:$('.note-editable').text(),
                  newsBody:$('.note-editable').html()
                  },
                error: function(XMLHttpRequest, textStatus, errorThrown){
                   alert(textStatus);
                },
                success: function(data){
                swal({
                        title:'修改成功',
                        type: "success"
                        },function(){
                          window.location.href="weixinnews/weixinnewsMain";
                    })
                }
              });
           }else{//新增
             $.ajax({
                url:"weixinnews/addNews",       
                type : "POST",
                dataType:'json',
                data:{
                  newsTitle:$('#newsTitle').val(),
                  newsContent:$('.note-editable').text(),
                  newsBody:$('.note-editable').html()
                  },
                error: function(XMLHttpRequest, textStatus, errorThrown){
                   alert(textStatus);
                },
                success: function(data){
                swal({
                        title:'新增成功',
                        type: "success"
                        },function(){
                          window.location.href="weixinnews/weixinnewsMain";
                    })
                }
              });
           }

        }

  	 
    }
        $(function () {

            // Initialize summernote plugin
            $('.summernote').summernote({
                height: 400,
                lang: 'zh-CN',
                  onImageUpload: function(files, editor, $editable) {
                    data = new FormData();
                    data.append("myfiles", files[0]);      
                    $.ajax({
                            data: data,
                            type: "POST",
                            processData: false,
                            contentType: false,
                            cache: false,
                            dataType:'text',
                            url: 'weixinnews/uploadFile',
                            success: function(data) {
                              editor.insertImage($editable, data);
                            }
                        });
                  }
            });
            
           
            var newsId = $("#newsId").val();
            if(newsId!="null" && newsId!=''){  
            	
            	$(".news-title").text("修改资讯");
            	$.ajax({
            		  url:"weixinnews/newsDetail",    		
            		  type : "POST",
            		  dataType:'json',
            		  data:{newsId:newsId},
            		  error: function(XMLHttpRequest, textStatus, errorThrown){
            			   alert(textStatus);
            		  },
            		  success: function(data){
            			 $('#newsTitle').val(data.newsTitle);
            			//$('.dateTime').text(data.createDate);
            			 $('.note-editable').html(data.newsBody)
            		  }
            		});
            }else{
            	$(".news-title").text("新增资讯");
            }

        });

    </script>
</body>
</html>
