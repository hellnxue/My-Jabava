<%@ page contentType="text/html; charset=utf-8" %>
<%@page import="com.jabava.utils.RequestUtil"%>
<div class="panel-heading hbuilt clearfix">
     <div class="board col-sm-12 col-md-12 col-lg-12">
        <div class="liner"></div>
        <div class="slicks">
            <div class="personal">
                <a href="javascript:;" title="个人信息" data-link-type="audit_personal">
                    <span class="round-tabs">
                        <i class="pe-7s-user"></i>
                    </span>
                    <span class="badge">个人信息</span>
                </a>
            </div>
            <div class="essential">
                <a href="javascript:;" title="工作信息" data-link-type="audit_essential">
                    <span class="round-tabs">
                        <i class="pe-7s-news-paper"></i>
                    </span>
                    <span class="badge">工作信息</span>
                </a>
            </div>
            <div class="experience">
                <a href="javascript:;" title="履历信息" data-link-type="audit_experience">
                    <span class="round-tabs">
                        <i class="pe-7s-portfolio"></i>
                    </span>
                    <span class="badge">履历信息</span>
                </a>
            </div>
            <div class="educational">
                <a href="javascript:;" title="教育信息" data-link-type="audit_educational">
                    <span class="round-tabs">
                        <i class="pe-7s-study"></i>
                    </span>
                    <span class="badge">教育信息</span>
                </a>
            </div>
            <div class="member">
                <a href="javascript:;" title="社会关系" data-link-type="audit_famliys">
                    <span class="round-tabs">
                        <i class="pe-7s-users"></i>
                    </span>
                    <span class="badge">社会关系</span>
                </a>
            </div>
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
	
	var approvedStatus="${approvedStatus}";
	if(approvedStatus==2){
		 href="employees/audit_employee_todo";
	}else if(approvedStatus==3){
		 href="employees/audit_employee_done";
	}
	window.location.href=href;
}

	(function($){
		var $target = $('[data-link-type]');
		var type = "<%=type%>",
		$targetItem = $('.'+type);
		
		buildSlick();
		
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
            const maxLength = 5;
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

    })(jQuery)


</script>