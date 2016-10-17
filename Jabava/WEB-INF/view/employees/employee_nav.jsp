<%@ page contentType="text/html; charset=utf-8" %>
<%@page import="com.jabava.utils.RequestUtil"%>
<div class="panel-heading hbuilt clearfix">
    <div class="pull-left m-r">
        <img id="navEmployeePhoto" src="static/img/nobody.png">
    </div>  
     <div class="col-sm-10 col-md-10 col-lg-10 font-normal m-t fonts-sizes">
      <p>姓名：<span id="navEmployeeName"></span>
      <% if(RequestUtil.hasPower("roster_navi_iv")){ %>
        <a href="javascript://" class="btn btn-warning btn-xs m-l-xs hidden pull-right" id="perfectInfoLink" data-send-notify="${personId}">邀请用户完善信息</a>
      <% } %>
      </p>
      <p>手机：<span id="navEmployeePhone"></span></p>
     </div>
     <div class="board col-sm-10 col-md-10 col-lg-10">
        <div class="liner"></div>
        <div class="slicks">
            <!-- <div class="order">
                <a href="javascript:;" title="订单" data-link-type="slicks">
                    <span class="round-tabs">
                        <i class="pe-7s-browser"></i>
                    </span>
                    <span class="badge">订单</span>
                </a>
            </div> -->
            <!-- <div class="bill">
                <a href="javascript:;" title="账单" data-link-type="bill">
                    <span class="round-tabs">
                        <i class="pe-7s-ticket"></i>
                    </span>
                    <span class="badge">账单</span>
                </a>
            </div> -->
            <div class="personal">
                <a href="javascript:;" title="个人信息" data-link-type="personal">
                    <span class="round-tabs">
                        <i class="pe-7s-user"></i>
                    </span>
                    <span class="badge">个人信息</span>
                </a>
            </div>
            <div class="essential">
                <a href="javascript:;" title="工作信息" data-link-type="essential">
                    <span class="round-tabs">
                        <i class="pe-7s-news-paper"></i>
                    </span>
                    <span class="badge">工作信息</span>
                </a>
            </div>
            <div class="transfer">
                <a href="javascript:;" title="任职记录" data-link-type="transfer">
                    <span class="round-tabs">
                        <i class="pe-7s-way"></i>
                    </span>
                    <span class="badge">任职记录</span>
                </a>
            </div>
              <div class="socialsecurity">
                <a href="javascript:;" title="社保信息" data-link-type="socialsecurity">
                    <span class="round-tabs">
                        <i class="fa fa-building-o"></i>
                    </span>
                    <span class="badge">社保信息</span>
                </a>
            </div> 
            <!-- <div class="position">
                <a href="javascript:;" title="岗位信息" data-link-type="position">
                    <span class="round-tabs">
                        <i class="pe-7s-id"></i>
                    </span>
                    <span class="badge">岗位信息</span>
                </a>
            </div> -->
            <div class="experience">
                <a href="javascript:;" title="履历信息" data-link-type="experience">
                    <span class="round-tabs">
                        <i class="pe-7s-portfolio"></i>
                    </span>
                    <span class="badge">履历信息</span>
                </a>
            </div>
            <div class="educational">
                <a href="javascript:;" title="教育信息" data-link-type="educational">
                    <span class="round-tabs">
                        <i class="pe-7s-study"></i>
                    </span>
                    <span class="badge">教育信息</span>
                </a>
            </div>
            <!-- <div class="project">
                <a href="javascript:;" title="项目经验" data-link-type="project">
                    <span class="round-tabs">
                        <i class="pe-7s-network"></i>
                    </span>
                    <span class="badge">项目经验</span>
                </a>
            </div> -->
            <!-- <div class="obtain">
                <a href="javascript:;" title="获得证书" data-link-type="obtain">
                    <span class="round-tabs">
                        <i class="pe-7s-notebook"></i>
                    </span>
                    <span class="badge">获得证书</span>
                </a>
            </div> -->

            <!-- <div class="training">
                <a href="javascript:;" title="培训经历" data-link-type="training">
                    <span class="round-tabs">
                        <i class="pe-7s-science"></i>
                    </span>
                    <span class="badge">培训经历</span>
                </a>
            </div> -->

            <div class="contract">
                <a href="javascript:;" title="合同信息" data-link-type="contract">
                    <span class="round-tabs">
                        <i class="pe-7s-note2"></i>
                    </span>
                    <span class="badge">合同信息</span>
                </a>
            </div>

            <div class="member">
                <a href="javascript:;" title="社会关系" data-link-type="member">
                    <span class="round-tabs">
                        <i class="pe-7s-users"></i>
                    </span>
                    <span class="badge">社会关系</span>
                </a>
            </div>

            <div class="portfolio">
                <a href="javascript:;" title="资料情况" data-link-type="portfolio">
                    <span class="round-tabs">
                        <i class="pe-7s-photo-gallery"></i>
                    </span>
                    <span class="badge">资料情况</span>
                </a>
            </div>

            <!-- <div class="trial">
                <a href="javascript:;" title="试用情况" data-link-type="trial">
                    <span class="round-tabs">
                        <i class="pe-7s-info"></i>
                    </span>
                    <span class="badge">试用情况</span>
                </a>
            </div> -->

            <!-- <div class="performance">
                <a href="javascript:;" title="绩效考核" data-link-type="performance">
                    <span class="round-tabs">
                        <i class="pe-7s-display1"></i>
                    </span>
                    <span class="badge">绩效考核</span>
                </a>
            </div> -->

            <!-- <div class="reward">
                <a href="javascript:;" title="奖惩记录" data-link-type="reward">
                    <span class="round-tabs">
                        <i class="pe-7s-medal"></i>
                    </span>
                    <span class="badge">奖惩记录</span>
                </a>
            </div> -->

            <!-- <div class="attendance">
                <a href="javascript:;" title="考勤记录" data-link-type="attendance">
                    <span class="round-tabs">
                        <i class="pe-7s-date"></i>
                    </span>
                    <span class="badge">考勤记录</span>
                </a>
            </div> -->

            <!-- <div class="dimission">
                <a href="javascript:;" title="离职管理" data-link-type="dimission">
                    <span class="round-tabs">
                        <i class="pe-7s-delete-user"></i>
                    </span>
                    <span class="badge">离职管理</span>
                </a>
            </div> -->
        </div>
        <a href="javascript:;" class="prev">‹</a>
        <a href="javascript:;" class="next">›</a>
    </div>
</div>


<script src="static/bootstrap/vendor/toastr/build/toastr.min.js"></script>
<% 
	String type = request.getParameter("type");
%>
<script src="static/js/slick/slick.min.js"></script>
<script type="text/javascript">
//跳转到离职管理
function toDimission(personId){
		window.location.href="employee/toPageByLinkType?linkType=dimission&personId="+personId;
}
//跳转到花名册列表页面
function toEmployeeList(){
	window.location.href="employee/employeeList";
}

	(function($){
		var $target = $('[data-link-type]');
		var type = "<%=type%>",
		$targetItem = $('.'+type);
		
		buildSlick();
		setNavigagtorPersonalInfo();
		
		///////////////////////////////////////////
		
		/**
		 * 跳转到目标页面
		 */
		function moveToTargetPage(e){
			var linkType=$(this).data("linkType");
			var personId="${personId}";
			location.href="employee/toPageByLinkType?linkType="+linkType+'&personId='+personId;
		}
		
		/**
		 * 构建滑动导航
		 */
		function buildSlick(){
			// 给导航项目的每一个栏目编号，用于后续的精准定位
			var $slick = $('.slicks div');
            var itemCount = $slick.length;
			for(var idx = 0; idx <  itemCount; idx ++ ){
				var $item = $($slick[idx]);
				$item.data('index',idx);
			}
			
			var index = $targetItem.data('index');
            const maxLength = 8;
            var maxSlides = itemCount > maxLength?maxLength:itemCount;
			
			// 设置定位对象在导航条中的位置
			// 在导航条中，将当前选中的条目居中显示，左移半个总长度4（总长为 8）
            var pos = (index - maxSlides) < 0 ? 0 : (index - 3);
			$('.slicks').slick({
				slidesToShow: maxSlides,
				infinite: false,
				prevArrow: $('.board .prev'),
				nextArrow: $('.board .next'),
				focusOnSelect:true,
				initialSlide:pos,  
				responsive: [
					{breakpoint: 768},
					{breakpoint: 480}
				]
			});
			
			// 设置导航目标初始状态
			$targetItem.addClass('active').off().siblings().removeClass('active');
            $targetItem.find('[data-link-type]').addClass('current-item');
			$targetItem.find('.badge').addClass('badge-success');
			$targetItem.siblings().find('.badge').removeClass('badge-success');
			
			// 绑定的跳转事件
			$target.not( '.current-item' ).on('click',moveToTargetPage);
		}
		
		// 设置导航条的个人信息 （姓名/手机/电话）
		function setNavigagtorPersonalInfo(data){
			var pinfo  = data;
			if(pinfo === undefined){
				  $.ajax({
						cache: true,
						type: "POST",
						url:"employee/personalInfo.do",
						data:{"personId":"${personId}"}, 
						dataType: 'json',
						async: false,
						success: function(_data) {
							pinfo = _data;
						}
					});
			}
			
			$('#navEmployeeName').text(pinfo.person.employeeName);
			$('#navEmployeePhone').text(pinfo.person.mobile);
			//if(!pinfo.userType || pinfo.userType == 3){
				$('#perfectInfoLink').removeClass('hidden');
			//}
			
			/*$('#navEmployeePhoto').on('click',function(e){
				alert("=> Opps!尚不支持自定义的头像!!");
				e.preventDefault();
			});*/
		}

        // send notify
        $('[data-send-notify]').off().on('click', function(event){
            event.preventDefault();
            var getPersonID = $(this).attr('data-send-notify')
                $.ajax({
                       
                        type: "POST",
                        url:"employee/sendMessage",
                        data:{personId: getPersonID}, 
                        dataType: 'json',
                        async: true,
                        success: function(data) {
                            toastr.options = {
                                "debug": false,
                                "newestOnTop": false,
                                "positionClass": "toast-top-center",
                                "closeButton": false,
                                "toastClass": "animated fadeInDown",
                            };
                            toastr.info(data.msg)
                        }
                    });
        })
        


    })(jQuery)


</script>