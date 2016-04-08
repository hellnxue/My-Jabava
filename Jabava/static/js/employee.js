var personId = $("#personId").val();
personalAjax();

//个人信息ajax
function personalAjax(){
	$.ajax({
        cache: true,
        type: "POST",
        url:"employee/personalInfo.do",
        data:{"personId":personId},
        dataType: 'json',
        async: false,
        success: function(data) {
        	$(".grxx").html(template('personal', data));
        }
	})
}

//基本信息ajax
function essentialAjax(){
	$.ajax({
        cache: true,
        type: "POST",
        url:"employee/personalInfo.do",
        data:{"personId":personId},
        dataType: 'json',
        async: false,
        success: function(data) {
        	
        }
	})
}

//岗位信息ajax
function positionAjax(){
	$.ajax({
        cache: true,
        type: "POST",
        url:"employee/personalInfo.do",
        data:{"personId":personId},
        dataType: 'json',
        async: false,
        success: function(data) {
        	
        }
	})
}

//岗位调动ajax
function transferAjax(){
	$.ajax({
        cache: true,
        type: "POST",
        url:"employee/getJobpost.do",
        data:{"personId":personId},
        dataType: 'json',
        async: false,
        success: function(data) {
        	
        }
	})
}

//教育背景
function educationAjax(){
	$.ajax({
        cache: true,
        type: "POST",
        url:"employee/getEducation.do",
        data:{"personId":personId},
        dataType: 'json',
        async: false,
        success: function(data) {
        	
        }
	})
}

//工作经验
function handsAjax(){
	$.ajax({
        cache: true,
        type: "POST",
        url:"employee/getResume.do",
        data:{"personId":personId},
        dataType: 'json',
        async: false,
        success: function(data) {
        	
        }
	})
}

//项目经验
function projectAjax(){
	$.ajax({
        cache: true,
        type: "POST",
        url:"employee/getProject.do",
        data:{"personId":personId},
        dataType: 'json',
        async: false,
        success: function(data) {
        	
        }
	})
}

//获得证书
function obtainAjax(){
	$.ajax({
        cache: true,
        type: "POST",
        url:"employee/getCertificate.do",
        data:{"personId":personId},
        dataType: 'json',
        async: false,
        success: function(data) {
        	
        }
	})
}

//培训经历
function trainingAjax(){
	$.ajax({
        cache: true,
        type: "POST",
        url:"employee/getTrain.do",
        data:{"personId":personId},
        dataType: 'json',
        async: false,
        success: function(data) {
        	
        }
	})
}

//家庭成员
function memberAjax(){
	$.ajax({
        cache: true,
        type: "POST",
        url:"employee/getRelation.do",
        data:{"personId":personId},
        dataType: 'json',
        async: false,
        success: function(data) {
        	
        }
	})
}

//劳动合同
function contractAjax(){
	$.ajax({
        cache: true,
        type: "POST",
        url:"employee/getContract.do",
        data:{"personId":personId},
        dataType: 'json',
        async: false,
        success: function(data) {
        	
        }
	})
}

//试用情况
function trialAjax(){
	$.ajax({
        cache: true,
        type: "POST",
        url:"employee/getTrial.do",
        data:{"personId":personId},
        dataType: 'json',
        async: false,
        success: function(data) {
        	
        }
	})
}

//绩效考核
function performanceAjax(){
	$.ajax({
        cache: true,
        type: "POST",
        url:"employee/getAssess.do",
        data:{"personId":personId},
        dataType: 'json',
        async: false,
        success: function(data) {
        	
        }
	})
}

//奖惩记录
function rewardAjax(){
	$.ajax({
        cache: true,
        type: "POST",
        url:"employee/getReward.do",
        data:{"personId":personId},
        dataType: 'json',
        async: false,
        success: function(data) {
        	
        }
	})
}

//考勤记录
function attendanceAjax(){
	$.ajax({
        cache: true,
        type: "POST",
        url:"employee/getAttendance.do",
        data:{"personId":personId},
        dataType: 'json',
        async: false,
        success: function(data) {
        	
        }
	})
}

//离职管理
function turnoverAjax(){
	$.ajax({
        cache: true,
        type: "POST",
        url:"employee/getLeft.do",
        data:{"personId":personId},
        dataType: 'json',
        async: false,
        success: function(data) {
        	
        }
	})
}
