
// JavaScript Document
 
 
/*手机端-教育背景保存并填写下一项
  $("#educationalSaveNext").click(function(){
  	var $form=$("#educational_form");
  	commonSave($form,"Mobile");
  	
  });
手机端-教育背景保存并关闭	
 $("#educationalSaveClose").click(function(){
	  	var $form=$("#educational_form");
	  	commonSave($form,"Mobile","saveClose");
	  	
 });	
	*/

$(function(){
	
	setCalenderFormat();
	
	/**
	 * 员工录入页面公共的新增方法（保存并关闭 & 保存并填写下一项）
	 */

	 $("[data-click-target='onlySave']").add("[data-click-target='saveAndClose']").click(function(){
		
		
		 
		 var saveAction=$(this).data("clickTarget");
		 var $parentDiv=$(this).parents("div[id^='myModal']");
		 
		 var $hideModal=$("#"+$parentDiv.attr("id"));
		 
		 
		 var $form=$parentDiv.find("form");
		 
		 var $nextModal=$("#"+$parentDiv.data("nextModal"));
		 
		 var addURL=$form.data("addUrl");
		 
		 
		 onlyEnterSave($form,addURL,saveAction,$hideModal,$nextModal,personId);
		  
		 
		
	 });
});

 /**
 * 日期框格式设置
 */
function setCalenderFormat(){
	 $('.input-group.date').datepicker({
		format : "yyyy-mm-dd",
		language : "zh-CN",
		autoclose : true

	}); 
}



 
 
 
/**
 * 将毫秒格式化成年-月-日的格式
 * @param ms
 * @returns {String}
 */
function getHandleDate(ms){
	var date=new Date(ms);
	var month=Number(date.getMonth()+1)+"";
	if(month.length<2){
		month="0"+month;
	}
	var day=Number(date.getDate())+"";
	if(day.length<2){
		day="0"+day;
	}
	var handleDate=date.getFullYear()+"-"+month+"-"+day;
	return handleDate;
};
/**
 * 将格式为年月日时分秒的格式成年月日
 * @param ymdhhmmss 
 * @returns
 */
function getHandleDateYMD(ymdhhmmss){
	  
	  return ymdhhmmss.split(" ")[0];
	
}


/**
 * jQuery扩展插件，将form表单内容序列化为Object
 */
$.fn.extend({
    serializeObject:function(){
           if(this.length>1){
                  return false;
           }
           var arr=this.serializeArray();
           var obj=new Object;
           $.each(arr,function(k,v){
                  obj[v.name]=v.value;
           });
           return obj;
    }
});


/**
 * 弹框左上角的返回按钮
 */
	$(".modalHide").on("click",function(){
		var id=$(this).parents("div.modal").attr("id");
		$("#"+id).modal('hide');
		
	});
/**
 * 模板渲染时间格式化
 */
	template.helper('dateFormat', function(date){

	    date = getHandleDateYMD(date);

	    return date;
	});
 
	
/*
 * 删除
 */
     function deleteBtnInfo(){
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
	}