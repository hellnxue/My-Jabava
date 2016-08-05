<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<base href="${pageContext.request.contextPath}/">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge">

<!-- Page title    -->
<title>资讯管理界面界面</title>
<jsp:include flush="true" page="../common/styles.jsp"></jsp:include>
<link rel="stylesheet" href="static/css/zTreeStyle/zTreeStyle.css" type="text/css">
<!--for 临时改变-- >
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
                            <a class="btn btn-default btn-sm text-left" type="button" href="weixinnews/weixinnewsMain">返　回</a>
                        </div>
                      
                        <div class="panel-body"> 
                            <div class="hpanel">
                                <div class="panel-heading">
                                    <div class="text-center">
                                        <h4 class="panelTitle"><span>【重磅头条】智阳网络与2016中国人力资源服务博览会成功牵手！</span>
                                            <div class="pull-right">
                                                <h6>
                                                    <a class="pe-7s-note pe-2x" href='newsManagement/addNews?newsId=<%=request.getParameter("newsId")%>' title="修改"></a>
                                                    <a class="pe-7s-trash pe-2x m-l-md m-r-md" onclick="delNews()" title="删除"></a>
                                                    <a class="pe-7s-share pe-2x" title="分享"  data-target="#myModal5" data-toggle="modal"></a>
                                                </h6>
                                            </div>
                                        </h4>
                                        <span class="dateTime">2016-05-19 14:49:02</span>
                                    </div>
                                </div>
                                <div class="panel-body no-borders" data-body="cover">
                                <div class="col-lg-12">
                                    <p>
                                        日前，智阳网络与浙江省人力资源服务协会达成全面战略合作意向，将成为其举办的2016中国人力资源服务博览会的协办单位与技术支持单位。智阳将为其提供会展策划、展商招募以及展示和交互平台等一揽子解决方案。 2016中国人力资源服务博览会将于10月20日在“G20首脑杭州峰会”举办地——杭州洲际酒店（国际会议中心）盛大举行
                                    </p>
                                </div>
                                <div class="col-lg-12 text-center m-t m-b">
                                    <img src="static/img/newsDetail.jpg" alt="">
                                </div>
                                <div class="col-lg-12">
                                    <p>
                                        本届博览会将秉承创新、活力、联动、包容的“G20峰会”精神，以“创新、开放、共享”为主题，集中组织和定向邀约各类互联网企业、世界500强、各大机构及浙江本土如阿里巴巴、娃哈哈等民营巨头和各类企业高管参会观展；同时诚邀各知名人力资源服务机构、教育培训组织，以及金融、健康、福利、保险、软件、互联网等各类人力资源服务产品供应商前来参展，交流洽谈，共同领略人力资源“共享经济”全新生态。
                                    </p>
                                    <p>
                                        智阳网络是中国顶尖的人力资源服务交互平台，是人力资源生态圈的倡导者和实践者。独创“四屏联动”服务模式，贯通平台、与人力资源外包服务商、企业用户和员工四方业务和信息流，向企业和服务提供商提供免费的HROSaaS和HRSaaS技术平台以及丰富优质的服务产品，实现高效的交易和交互管理以满足行业各类各层次的服务需求。 2016年对杭州来说是意义深远的一年，G20峰会及一系列配套国际会议的召开，将拥有秀美风景、丰富历史文化和发达的互联网经济的杭州展现在世界舞台上。
                                    </p>
                                </div>
                                </div>
                            </div>
                        </div> 
                        <div class="panel-foot">
                            <div class="pull-right">
                                <a class="m-r-md" href="javascript://">   </a>
                                <a class="m-r" href="javascript://">    </a>
                            </div> 
                        </div>

                        <!-- modal -->
                        <div aria-hidden="true" role="dialog" tabindex="-1" id="myModal5" class="modal fade in">
                            <div class="modal-dialog modal-lg">
                                <div class="modal-content">
                                    <div class="color-line"></div>
                                    <div class="modal-header p-sm boxed no-borders">
                                        <h4 class="text-center">推送至微信</h4>
                                    </div>
                                    <div class="modal-body">
                                        <div class="col-lg-5 boxed h-400 CodeMirror progress" >
                                            <div class="col-lg-12 m-t-lg m-b-md">
                                                <div class="input-group">
                                                    <input class="form-control" id="seachTreeNode" type="text" placeholder="输入部门名称或员工姓名">
                                                    <span class="input-group-btn">
                                                        <a class="btn btn-default" type="button">
                                                            <span class="pe pe-7s-search"></span>
                                                        </a>
                                                    </span>
                                                </div>
                                            </div>
                                            <div class="col-lg-12 scrollspy-example-weixin">
                                                <div class="dd" id="nestable">
                                                    <ul id="treeDemo" class="ztree" data-tree="userId"></ul>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-lg-2 h-400 text-center">
                                            <img class="news-push" src="static/img/newsPush.png" alt="">
                                        </div>
                                        <div class="col-lg-5 boxed h-400 CodeMirror progress">
                                        <form id="sendNews" >
                                          <input type="hidden" id="newsId" name="newsId" value="<%=request.getParameter("newsId")%>"  />
                                            <div class="col-lg-12 m-t-lg m-b-md">
                                                <p class="m-b-none m-t-sm">已添加</p>
                                            </div>
                                            <div class="col-lg-12">
                                                <ul class="list-group" data-container="treeCodeName">
                                                    <li class="list-group-item no-borders treeCodeName hide" data-treeCodeName="name">
                                                        <a class="btn pull-right no-padding no-borders" style="margin-top:-2px">
                                                            <span class="close">&times</span>
                                                        </a>
                                                        <span></span>
                                                    </li>
                                                </ul>
                                            </div>
                                           </form>
                                        </div>
                                    </div>
                                    <div class="modal-footer boxed no-borders no-padding m-r-lg">
                                        <div class="m-b-lg">
                                            <button data-dismiss="modal" class="btn btn-default" type="button">取消</button>
                                            <button class="btn btn-success" type="button" onclick="sendNews()">确定</button>
                                        </div> 
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
    
    <script src="static/bootstrap/vendor/nestable/jquery.nestable.js"></script>
    <script type="text/javascript" src="static/js/zTreeJs/jquery.ztree.core-3.5.js"></script>
    <script type="text/javascript" src="static/js/zTreeJs/jquery.ztree.excheck-3.5.js"></script>

    <script src="static/bootstrap/vendor/sweetalert/lib/sweet-alert.min.js"></script>
    <!-- App scripts -->
    <script src="static/bootstrap/scripts/homer.js"></script>
    <script src="static/bootstrap/scripts/charts.js"></script>
    
   <script>
   //发送给微信用户
    function sendNews(){
    	$.ajax({
  		  url:"weixinnews/sendNew",    		
  		  type : "POST",
  		  dataType:'json',
  		  data:$("#sendNews").serialize(),
  		  error: function(XMLHttpRequest, textStatus, errorThrown){
  			   alert(textStatus);
  		  },
  		  success: function(data){
  		//	myModal5.
  			alert("发送成功");
  			$("#myModal5").hide();
  			window.location.reload()
	  					
  		  }
  		});
    }
    function  delNews(){
        swal(
            {
                title: "确定要删除此定义吗?",
                text: "",
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "确定删除!",
                cancelButtonText: "放弃操作!",
                closeOnConfirm: false,
                closeOnCancel: false 
            },
            function (isConfirm) {
                if (isConfirm) {
                    $.ajax({
                        url:"weixinnews/delNews",         
                        type : "POST",
                        dataType:'json',
                        data:{newsId:$("#newsId").val()},
                        error: function(XMLHttpRequest, textStatus, errorThrown){
                             alert(textStatus);
                        },
                        success: function(data){ 
                            alert("删除成功");              
                            window.location.href="weixinnews/weixinnewsMain";
                        }
                    });
                }else{
                    swal("已取消", "定义未删除", "error");
                }

            }
        ) 
    }
    var setting = {
        check: {
            enable: true,
            nocheckInherit: true
        },
        data: {
            simpleData: {
                enable: true
            }
        },
        view:{
            showLine:false
        },
        callback: {
            onClick: zTreeOnClick
        }
    };
    //组织架构树引用json
	var zNodes =[];    
		
		$.ajax({
		    url:"weixinnews/loadTree",    		
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
                    obj.nocheck = true;
				    if(strs[i].organizationName){
					    obj.name = strs[i].organizationName;  
				    }else{
					    obj.name = strs[i].personName;
				    } 
				    obj.personId = strs[i].personId;
				 
				  
                    if( obj.pId == 0 ) obj.open = true;
				    zNodes.push(obj);
			    }
			    $.fn.zTree.init($("#treeDemo"), setting, zNodes);
                treeSeach();    			
		    }
		});
    	
    function zTreeOnClick(event, treeId, treeNode, clickFlag) {
        var getTreeNodeName = treeNode.name,
            getTreeNodeId = treeNode.id,
            personId = treeNode.personId,
            strTreeCode = $('.treeCodeName')
                          .clone()
                          .removeClass('hide')
                          .removeClass('treeCodeName')
                          .attr('data-treeCodeName',getTreeNodeId)
                          .mouseover(function(event) {
                              $(this).css({
                                  background: '#f5f5f5',
                              });
                          })
                          .mouseout(function(event) {
                              $(this).css({
                                  background: '#fff',
                              });
                          });
        
        strTreeCode.children('span').text(getTreeNodeName);
        if(personId!=null){
        	 strTreeCode.children('span').append("<input type='hidden' name=personId value='"+personId +"'  />");
        }
        if(getTreeNodeId!=null){
        	 strTreeCode.children('span').append("<input type='hidden' name=orgId value='"+getTreeNodeId +"'  //>");
        }
       
        strTreeCode
        .find('a')
        .on('click',function(e){
            $(this).parent().remove()
        })

        var $treeCodeName = $("[data-treeCodeName='"+getTreeNodeId+"']")
        if($treeCodeName.length == 0){
            $('[data-container="treeCodeName"]').append(strTreeCode)   
        }
    }   
       
    $(function () {
    	$.ajax({
    		  url:"weixinnews/newsDetail",    		
    		  type : "POST",
    		  dataType:'json',
    		  data:{newsId:$("#newsId").val()},
    		  error: function(XMLHttpRequest, textStatus, errorThrown){
    			   alert(textStatus);
    		  },
    		  success: function(data){
    			 $('.panelTitle').find('span').text(data.newsTitle);
    			 $('.dateTime').text(data.createDate);
    			 $('[data-body="cover"]').html(data.newsBody)
    		  }
    		});
    	
    });
    //模糊搜索
    function treeSeach(){
        $('#seachTreeNode').on('keyup',function(e){
            $('#treeDemo')
            .find('div')
            .hide()
            .filter(":contains('"+$(this).val()+"')")
            .show()
            .keyup();
        })
    }
    </script>
</body>
</html>