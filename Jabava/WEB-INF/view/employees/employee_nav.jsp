<%@ page contentType="text/html; charset=utf-8" %>
<div class="board">
    <div class="liner"></div>
    <div class="slicks">
        <div class="order">
            <a href="javascript:;" title="订单" data-link-type="slicks">
                <span class="round-tabs">
                    <i class="pe-7s-browser"></i>
                </span>
                <span class="badge">订单</span>
            </a>
        </div>
        <div class="bill">
            <a href="javascript:;" title="账单" data-link-type="bill">
                <span class="round-tabs">
                    <i class="pe-7s-ticket"></i>
                </span>
                <span class="badge">账单</span>
            </a>
        </div>
        <div class="personal">
            <a href="javascript:;" title="个人信息" data-link-type="personal">
                <span class="round-tabs">
                    <i class="pe-7s-user"></i>
                </span>
                <span class="badge">个人信息</span>
            </a>
        </div>
        <div class="essential">
            <a href="javascript:;" title="基本信息" data-link-type="essential">
                <span class="round-tabs">
                    <i class="pe-7s-pin"></i>
                </span>
                <span class="badge">基本信息</span>
            </a>
        </div>
        <div class="position">
            <a href="javascript:;" title="岗位信息" data-link-type="position">
                <span class="round-tabs">
                    <i class="pe-7s-id"></i>
                </span>
                <span class="badge">岗位信息</span>
            </a>
        </div>
        <div class="transfer">
            <a href="javascript:;" title="岗位调动" data-link-type="transfer">
                <span class="round-tabs">
                    <i class="pe-7s-way"></i>
                </span>
                <span class="badge">岗位调动</span>
            </a>
        </div>
        <div class="educational">
            <a href="javascript:;" title="教育背景" data-link-type="educational">
                <span class="round-tabs">
                    <i class="pe-7s-study"></i>
                </span>
                <span class="badge">教育背景</span>
            </a>
        </div>
        <div class="experience">
            <a href="javascript:;" title="工作经验" data-link-type="experience">
                <span class="round-tabs">
                    <i class="pe-7s-portfolio"></i>
                </span>
                <span class="badge">工作经验</span>
            </a>
        </div>
        <div class="project">
            <a href="javascript:;" title="项目经验" data-link-type="project">
                <span class="round-tabs">
                    <i class="pe-7s-network"></i>
                </span>
                <span class="badge">项目经验</span>
            </a>
        </div>
        <div class="obtain">
            <a href="javascript:;" title="获得证书" data-link-type="obtain">
                <span class="round-tabs">
                    <i class="pe-7s-cash"></i>
                </span>
                <span class="badge">获得证书</span>
            </a>
        </div>
        <div class="training">
            <a href="javascript:;" title="培训经历" data-link-type="training">
                <span class="round-tabs">
                    <i class="pe-7s-notebook"></i>
                </span>
                <span class="badge">培训经历</span>
            </a>
        </div>
        <div class="member">
            <a href="javascript:;" title="家庭成员" data-link-type="member">
                <span class="round-tabs">
                    <i class="pe-7s-users"></i>
                </span>
                <span class="badge">家庭成员</span>
            </a>
        </div>
        <div class="contract">
            <a href="javascript:;" title="劳动合同" data-link-type="contract">
                <span class="round-tabs">
                    <i class="pe-7s-news-paper"></i>
                </span>
                <span class="badge">劳动合同</span>
            </a>
        </div>
        <div class="trial">
            <a href="javascript:;" title="试用情况" data-link-type="trial">
                <span class="round-tabs">
                    <i class="pe-7s-note2"></i>
                </span>
                <span class="badge">试用情况</span>
            </a>
        </div>
        <div class="performance">
            <a href="javascript:;" title="绩效考核" data-link-type="performance">
                <span class="round-tabs">
                    <i class="pe-7s-graph3"></i>
                </span>
                <span class="badge">绩效考核</span>
            </a>
        </div>
        <div class="reward">
            <a href="javascript:;" title="奖惩记录" data-link-type="reward">
                <span class="round-tabs">
                    <i class="pe-7s-medal"></i>
                </span>
                <span class="badge">奖惩记录</span>
            </a>
        </div>
        <div class="attendance">
            <a href="javascript:;" title="考勤记录" data-link-type="attendance">
                <span class="round-tabs">
                    <i class="pe-7s-wristwatch"></i>
                </span>
                <span class="badge">考勤记录</span>
            </a>
        </div>
        <div class="dimission">
            <a href="javascript:;" title="离职管理" data-link-type="dimission">
                <span class="round-tabs">
                    <i class="pe-7s-delete-user"></i>
                </span>
                <span class="badge">离职管理</span>
            </a>
        </div>
    </div>
    <a href="javascript:;" class="prev">‹</a>
    <a href="javascript:;" class="next">›</a>
</div>
<% 
	String type = request.getParameter("type");
%>
<script src="static/js/slick/slick.min.js"></script>
<script type="text/javascript">
 //console.log("personId===="+"${personId}");
  (function($){
	 var type = "<%=type%>",
     getSlicksItem = $('.'+type);
	 getSlicksItem.addClass('active').siblings().removeClass('active');
     getSlicksItem.find('.badge').addClass('badge-success');
     getSlicksItem.siblings().find('.badge').removeClass('badge-success');
	 
	 $('[data-link-type]').on("click",function(){
		 var linkType=$(this).data("linkType");
		 var personId="${personId}";//$("#personId").text();待改
		 location.href="employee/toPageByLinkType?linkType="+linkType+'&personId='+personId;
	 });
	 
	 
	 
     $('.slicks').slick({
      slidesToShow: 8,
      infinite: false,
      prevArrow: $('.board .prev'),
      nextArrow: $('.board .next'),
      responsive: [
        {
            breakpoint: 768
        },
        {
            breakpoint: 480
        }
       
      ]
    });

     
  })(jQuery)
</script>