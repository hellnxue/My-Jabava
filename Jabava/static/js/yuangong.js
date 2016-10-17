
// JavaScript Document
var mf=0;
$(function(){

	$('#lefts').click(function() {
		clickLeft ();
	});
	$('#rights').click(function() {
		clickRight ();
	});
	
	
	
	/**
	 * 继续添加表单---添加一个空表单 
	 */
	 $('[data-form-action="add"]').on('click', function(event) {
						mf++;

						var getBaseForm = $('[data-form-type="base"]'),
						strHtml = '',
						content = $('[data-form-target="content"]');
						 
						strHtml = getBaseForm.clone(true)
									.removeAttr( 'data-form-type')
									.removeClass("hidden")
									.addClass("jianju borders")
									.removeAttr( "style id disabled")
									.attr("name", "add_new_form");
						
						$(strHtml).find("[name=myfiles]").attr("id","myfiles" + mf);
						
						$(strHtml).find(":submit").off();//取消模板复制后的保存按钮click事件

						$(strHtml)[0].reset();//清空form
						$(strHtml).find('[data-toggle="select2"]').select2();
						 
						$(strHtml).find('[data-toggle="datepicker"]')
						.datepicker({
							format: "yyyy-mm-dd",
							autoclose: true
						})
						.on('changeDate', function(e){
							var $fv = $('[data-form-validator="validator"]');
							if($(this).hasClass('input-daterange')){
								$(e.target).each(function(index, el) {
									$fv.formValidation('revalidateField', $(this).attr('name'));
								});
								
							}else{
								var getEleName = $(e.target).find(':text').attr('name');
								$fv.formValidation('revalidateField', getEleName);
							}

						});
						
						content.append(strHtml);
						var $form = $(strHtml);
                    	$form.find('[data-action-motive="cancel"],[data-action-motive="remove"]').on('click', function(event) {
                    		console.log("取消");
                    		 event.preventDefault();
                    		$form.hide().remove();
                    	});
						$form.formValidation(validateOptions)
	                    .on('success.form.fv', function(e){
	                        e.preventDefault();
	                        if( $form.attr('data-form') != 'job-transfer' ){
	                        	commonSaveBefore($form);
	                        }else{
	                        	addJobPost($form);
	                        }
	                    });

						$(strHtml)[0].scrollIntoView();
					});
	
	 
	 

	/**
	 * 新增信息---页面待添加信息的表单添加
	 */
	
/*	if($("#create_new").attr('data-form-validator') == 'addDimission'){
		$("#create_new").formValidation(validateOptions)
		.on('success.form.fv',function(e){
			e.preventDefault();
			add($("#create_new").attr('id'))
		})
	}*/
	/**
	 * 添加表单模板的按钮保存
	 */
/*	$("#create_new").find("button")
	.not('[data-action-motive="dimission"],[data-action-motive="remove"],[data-action-motive="cancel"], input:submit')
	.on("click", function(event) {
		event.preventDefault();
		var $form = $("#create_new");
		if($form.attr('data-form-validator') != 'addDimission'){
			console.log("why go here........");
            $('[data-form-validator="validator"').formValidation(validateOptions)
            .on('success.form.fv', function(e){
                e.preventDefault();
				commonSaveBefore($form);
            });			
		}

	});*/
	
});

/**
 * 日期框格式设置
 */
function setCalenderFormat(){
	
	 $('.input-group.date').datepicker({
		format : "yyyy-mm-dd",
		language : "zh-CN",
		autoclose : true

	})
	 .on('changeDate', function(e){
          var getEleName = $(e.target).find(':text').attr('name');
          $('[data-form-validator="validator"]').formValidation('revalidateField', getEleName);
      });
}




function click(){
	$(".done").removeClass("done").next("dl").addClass("done");
	
	}

/*导航切换*/
$(".stepflex dl").click(function(){
		$(".stepflex dl").removeClass("done");
		$(this).addClass("done");
		});
		
		
/*左右箭头*/
       function clickLeft() {		
			var displacement = 165;
			var nowdistance = parseInt($('.stepflex').css('left'));
			if(nowdistance > -2000){
				nowdistance = (nowdistance - 165);
				$('.stepflex').css('left',nowdistance+'px')
			}			
		}
		function clickRight() {
			var displacement = 165;
			var nowdistance = parseInt($('.stepflex').css('left'));
			if(nowdistance < 0){
				nowdistance = (nowdistance + 165);
				$('.stepflex').css('left',nowdistance+'px')
			}
		}


/*订单*/
$(".order").click(function(){
	$(".ding_dan").show();
	$(".zhang_dan").hide();
	$(".grxx").hide();
	$(".jben_xx").hide();
	$(".gwei_xx").hide();
	$(".gangwei_dd").hide();
	$(".jiaoyu").hide();
	$(".huode").hide();
	$(".gongzuojy").hide();
	$(".xiangmujy").hide();
	$(".peixun_jl").hide();
	$(".famliy_cy").hide();
	$(".laodong").hide();
	$(".shi_yong").hide();
	$(".ji_xiao").hide();
	$(".jiang_cheng").hide();
	$(".kao_qin").hide();
	$(".li_zhi").hide();
	})
/*账单*/
$(".bill").click(function(){
	$(".zhang_dan").show();
	$(".ding_dan").hide();
	$(".grxx").hide();
	$(".jben_xx").hide();
	$(".gwei_xx").hide();
	$(".gangwei_dd").hide();
	$(".jiaoyu").hide();
	$(".huode").hide();
	$(".gongzuojy").hide();
	$(".xiangmujy").hide();
	$(".peixun_jl").hide();
	$(".famliy_cy").hide();
	$(".laodong").hide();
	$(".shi_yong").hide();
	$(".ji_xiao").hide();
	$(".jiang_cheng").hide();
	$(".kao_qin").hide();
	$(".li_zhi").hide();
	})
/*个人信息*/
$(".personal").click(function(){
	$(".grxx").show();
	$(".zhang_dan").hide();
	$(".ding_dan").hide();
	$(".jben_xx").hide();
	$(".gwei_xx").hide();
	$(".gangwei_dd").hide();
	$(".jiaoyu").hide();
	$(".huode").hide();
	$(".gongzuojy").hide();
	$(".xiangmujy").hide();
	$(".peixun_jl").hide();
	$(".famliy_cy").hide();
	$(".laodong").hide();
	$(".shi_yong").hide();
	$(".ji_xiao").hide();
	$(".jiang_cheng").hide();
	$(".kao_qin").hide();
	$(".li_zhi").hide();
	})
$(".jbenxinxi").click(function(){
	$(".jben_xx").show();
	$(".grxx").hide();
	click();
	
	})
/*基本信息*/
$(".essential").click(function(){
	$(".jben_xx").show();
	$(".ding_dan").hide();
	$(".zhang_dan").hide();
	$(".grxx").hide();
	$(".gwei_xx").hide();
	$(".gangwei_dd").hide();
	$(".jiaoyu").hide();
	$(".huode").hide();
	$(".gongzuojy").hide();
	$(".xiangmujy").hide();
	$(".peixun_jl").hide();
	$(".famliy_cy").hide();
	$(".laodong").hide();
	$(".shi_yong").hide();
	$(".ji_xiao").hide();
	$(".jiang_cheng").hide();
	$(".kao_qin").hide();
	$(".li_zhi").hide();
	})
$(".gwxx").click(function(){
	$(".gwei_xx").show();
	$(".jben_xx").hide();
	click();
	})
/*岗位信息*/
$(".position").click(function(){
	$(".gwei_xx").show();
	$(".ding_dan").hide();
	$(".zhang_dan").hide();
	$(".jben_xx").hide();
	$(".grxx").hide();
	$(".huode").hide();
	$(".gangwei_dd").hide();
	$(".jiaoyu").hide();
	$(".gongzuojy").hide();
	$(".xiangmujy").hide();
	$(".peixun_jl").hide();
	$(".famliy_cy").hide();
	$(".laodong").hide();
	$(".shi_yong").hide();
	$(".ji_xiao").hide();
	$(".jiang_cheng").hide();
	$(".kao_qin").hide();
	$(".li_zhi").hide();
	})
$(".gwdd").click(function(){
	$(".gangwei_dd").show();
	$(".gwei_xx").hide();
	click();
	})
/*岗位调动*/
$(".transfer").click(function(){
	$(".gangwei_dd").show();
	$(".ding_dan").hide();
	$(".zhang_dan").hide();
	$(".gwei_xx").hide();
	$(".jben_xx").hide();
	$(".grxx").hide();
	$(".jiaoyu").hide();
	$(".huode").hide();
	$(".gongzuojy").hide();
	$(".xiangmujy").hide();
	$(".peixun_jl").hide();
	$(".famliy_cy").hide();
	$(".laodong").hide();
	$(".shi_yong").hide();
	$(".ji_xiao").hide();
	$(".jiang_cheng").hide();
	$(".kao_qin").hide();
	$(".li_zhi").hide();
	})
/*教育背景*/
$(".educational").click(function(){
	$(".jiaoyu").show();
	$(".ding_dan").hide();
	$(".zhang_dan").hide();
	$(".jben_xx").hide();
	$(".gwei_xx").hide();
	$(".gangwei_dd").hide();
	$(".grxx").hide();
	$(".huode").hide();
	$(".gongzuojy").hide();
	$(".xiangmujy").hide();
	$(".peixun_jl").hide();
	$(".famliy_cy").hide();
	$(".laodong").hide();
	$(".shi_yong").hide();
	$(".ji_xiao").hide();
	$(".jiang_cheng").hide();
	$(".kao_qin").hide();
	$(".li_zhi").hide();
	})
$(".jy_beijing").click(function(){
	$(".jiaoyu").show();
	$(".gangwei_dd").hide();
	click();
	})	
  
/*手机端-教育背景保存并填写下一项*/
  $("#educationalSaveNext").click(function(){
  	var $form=$("#educational_form");
  	commonSave($form,"Mobile");
  	
  });
/*手机端-教育背景保存并关闭*/	
 $("#educationalSaveClose").click(function(){
	  	var $form=$("#educational_form");
	  	commonSave($form,"Mobile","saveClose");
	  	
 });	
	
	
	
/*工作经验*/
$(".hands").click(function(){
	$(".gongzuojy").show();
	$(".ding_dan").hide();
	$(".zhang_dan").hide();
	$(".jben_xx").hide();
	$(".gwei_xx").hide();
	$(".gangwei_dd").hide();
	$(".grxx").hide();
	$(".huode").hide();
	$(".jiaoyu").hide();
	$(".xiangmujy").hide();
	$(".peixun_jl").hide();
	$(".famliy_cy").hide();
	$(".ji_xiao").hide();
	$(".laodong").hide();
	$(".shi_yong").hide();
	$(".jiang_cheng").hide();
	$(".kao_qin").hide();
	$(".li_zhi").hide();
	})
$(".gzjy").click(function(){
	$(".gongzuojy").show();
	$(".jiaoyu").hide();
	click();
	})	
/*项目经验*/
$(".project").click(function(){
	$(".xiangmujy").show();
	$(".ding_dan").hide();
	$(".zhang_dan").hide();
	$(".jben_xx").hide();
	$(".gwei_xx").hide();
	$(".grxx").hide();
	$(".huode").hide();
	$(".gangwei_dd").hide();
	$(".jiaoyu").hide();
	$(".gongzuojy").hide();
	$(".peixun_jl").hide();
	$(".famliy_cy").hide();
	$(".laodong").hide();
	$(".shi_yong").hide();
	$(".ji_xiao").hide();
	$(".jiang_cheng").hide();
	$(".kao_qin").hide();
	$(".li_zhi").hide();
	})
$(".xmjy").click(function(){
	$(".xiangmujy").show();
	$(".gongzuojy").hide();
	click();
	})	
	
/*获得证书*/	
$(".obtain").click(function(){
	$(".huode").show();
	$(".ding_dan").hide();
	$(".zhang_dan").hide();
	$(".xiangmujy").hide();
	$(".jben_xx").hide();
	$(".gwei_xx").hide();
	$(".grxx").hide();
	$(".gangwei_dd").hide();
	$(".jiaoyu").hide();
	$(".gongzuojy").hide();
	$(".peixun_jl").hide();
	$(".famliy_cy").hide();
	$(".laodong").hide();
	$(".shi_yong").hide();
	$(".ji_xiao").hide();
	$(".jiang_cheng").hide();
	$(".kao_qin").hide();
	$(".li_zhi").hide();
	})
$(".hdzs").click(function(){
	$(".huode").show();
	$(".xiangmujy").hide();
	click();
	})		
/*培训经验*/
$(".training").click(function(){
	$(".peixun_jl").show();
	$(".ding_dan").hide();
	$(".zhang_dan").hide();
	$(".jben_xx").hide();
	$(".gwei_xx").hide();
	$(".grxx").hide();
	$(".huode").hide();
	$(".gangwei_dd").hide();
	$(".jiaoyu").hide();
	$(".gongzuojy").hide();
	$(".xiangmujy").hide();
	$(".famliy_cy").hide();
	$(".laodong").hide();
	$(".shi_yong").hide();
	$(".jiang_cheng").hide();
	$(".kao_qin").hide();
	$(".li_zhi").hide();
	})
$(".pxjl").click(function(){
	$(".peixun_jl").show();
	$(".huode").hide();
	click();
	})	

/*家庭成员*/
$(".member").click(function(){
	$(".famliy_cy").show();
	$(".ding_dan").hide();
	$(".zhang_dan").hide();
	$(".jben_xx").hide();
	$(".gangwei_dd").hide();
	$(".gwei_xx").hide();
	$(".grxx").hide();
	$(".huode").hide();
	$(".jiaoyu").hide();
	$(".gongzuojy").hide();
	$(".xiangmujy").hide();
	$(".peixun_jl").hide();
	$(".laodong").hide();
	$(".shi_yong").hide();
	$(".ji_xiao").hide();
	$(".jiang_cheng").hide();
	$(".kao_qin").hide();
	$(".li_zhi").hide();
	})

$(".jtcy").click(function(){
	$(".famliy_cy").show();
	$(".peixun_jl").hide();
	click();
	})
/*劳动合同*/	
$(".contract").click(function(){
	$(".laodong").show();
	$(".ding_dan").hide();
	$(".zhang_dan").hide();
	$(".famliy_cy").hide();
	$(".jben_xx").hide();
	$(".gangwei_dd").hide();
	$(".gwei_xx").hide();
	$(".grxx").hide();
	$(".huode").hide();
	$(".jiaoyu").hide();
	$(".gongzuojy").hide();
	$(".xiangmujy").hide();
	$(".peixun_jl").hide();
	$(".shi_yong").hide();
	$(".ji_xiao").hide();
	$(".jiang_cheng").hide();
	$(".kao_qin").hide();
	$(".li_zhi").hide();
	})
	
$(".ld_ht").click(function(){
	$(".laodong").show();
	$(".famliy_cy").hide();
	click();
	})		
	
/*试用情况*/	
$(".trial").click(function(){
	$(".shi_yong").show();
	$(".ding_dan").hide();
	$(".zhang_dan").hide();
	$(".laodong").hide();
	$(".famliy_cy").hide();
	$(".jben_xx").hide();
	$(".gangwei_dd").hide();
	$(".gwei_xx").hide();
	$(".grxx").hide();
	$(".huode").hide();
	$(".jiaoyu").hide();
	$(".gongzuojy").hide();
	$(".xiangmujy").hide();
	$(".peixun_jl").hide();
	$(".ji_xiao").hide();
	$(".jiang_cheng").hide();
	$(".kao_qin").hide();
	$(".li_zhi").hide();
	})
	
$(".shi_yqk").click(function(){
	$(".shi_yong").show();
	$(".laodong").hide();
	click();
	})	
/*绩效考核*/
$(".performance").click(function(){
	$(".ji_xiao").show();
	$(".ding_dan").hide();
	$(".zhang_dan").hide();
	$(".shi_yong").hide();
	$(".laodong").hide();
	$(".famliy_cy").hide();
	$(".jben_xx").hide();
	$(".gangwei_dd").hide();
	$(".gwei_xx").hide();
	$(".grxx").hide();
	$(".huode").hide();
	$(".jiaoyu").hide();
	$(".gongzuojy").hide();
	$(".xiangmujy").hide();
	$(".peixun_jl").hide();
	$(".jiang_cheng").hide();
	$(".kao_qin").hide();
	$(".li_zhi").hide();
	})
$(".ji_xkh").click(function(){
	$(".ji_xiao").show();
	$(".shi_yong").hide();
	click();
	})
/*奖惩记录*/
$(".reward").click(function(){
	$(".jiang_cheng").show();
	$(".ding_dan").hide();
	$(".zhang_dan").hide();
	$(".ji_xiao").hide();
	$(".shi_yong").hide();
	$(".laodong").hide();
	$(".famliy_cy").hide();
	$(".jben_xx").hide();
	$(".gangwei_dd").hide();
	$(".gwei_xx").hide();
	$(".grxx").hide();
	$(".huode").hide();
	$(".jiaoyu").hide();
	$(".gongzuojy").hide();
	$(".xiangmujy").hide();
	$(".peixun_jl").hide();
	$(".kao_qin").hide();
	$(".li_zhi").hide();
	})
$(".jc_jl").click(function(){
	$(".jiang_cheng").show();
	$(".ji_xiao").hide();
	click();
	})
/*考勤记录*/
$(".attendance").click(function(){
	$(".kao_qin").show();
	$(".ding_dan").hide();
	$(".zhang_dan").hide();
	$(".jiang_cheng").hide();
	$(".ji_xiao").hide();
	$(".shi_yong").hide();
	$(".laodong").hide();
	$(".famliy_cy").hide();
	$(".jben_xx").hide();
	$(".gangwei_dd").hide();
	$(".gwei_xx").hide();
	$(".grxx").hide();
	$(".huode").hide();
	$(".jiaoyu").hide();
	$(".gongzuojy").hide();
	$(".xiangmujy").hide();
	$(".peixun_jl").hide();
	$(".li_zhi").hide();
	})
$(".kq_jl").click(function(){
	$(".kao_qin").show();
	$(".jiang_cheng").hide();
	click();
	})
/*离职管理*/
$(".turnover").click(function(){
	$(".li_zhi").show();
	$(".ding_dan").hide();
	$(".zhang_dan").hide();
	$(".kao_qin").hide();
	$(".jiang_cheng").hide();
	$(".ji_xiao").hide();
	$(".shi_yong").hide();
	$(".laodong").hide();
	$(".famliy_cy").hide();
	$(".jben_xx").hide();
	$(".gangwei_dd").hide();
	$(".gwei_xx").hide();
	$(".grxx").hide();
	$(".huode").hide();
	$(".jiaoyu").hide();
	$(".gongzuojy").hide();
	$(".xiangmujy").hide();
	$(".peixun_jl").hide();
	})
$(".lz_gl").click(function(){
	$(".li_zhi").show();
	$(".kao_qin").hide();
	click();
	})
/*添加基本信息*/
$(".jb_xx").click(function(){
			$(".jbxx").after($(".jbxx").clone().removeClass("jbxx").addClass("jianju").addClass("borders"));
			deleteBtnInfo();
		})
/*添加岗位信息*/
$(".gw_xx").click(function(){
			$(".gw_dd").after($(".gw_dd").clone().removeClass("gw_dd").addClass("jianju").addClass("borders"));
			 deleteBtnInfo();

		})
/*添加岗位调动*/
$(".lets").click(function(){
		$(".gw_diaodong").removeClass('hidden').addClass("jianju borders add-gangwei-form");
		 // var html=$(".gw_diaodong").clone(true).removeClass("gw_diaodong gangwei_form hidden").addClass("jianju borders add-gangwei-form").removeAttr("style id");
			// $("#gangwei_body").append(html);
		    $('.input-group.date').datepicker({ });
			deleteBtnInfo();
		})
/*添加岗位修改界面---添加工*/
$(".gangwei_update").click(function(){
		$("#gangwei_body").append($(".gw_diaodong").clone().removeClass("gw_diaodong gangwei_form").addClass("jianju borders update-gangwei-form").removeAttr("style"));
		$('.input-group.date').datepicker({ });
		deleteBtnInfo();

	})			
/*教育背景修改界面---添加工*/
$(".teach_update").click(function(){
		$("#teach_body").append($(".teach").clone().removeClass("teach teach_form").addClass("jianju borders update-teach-form").removeAttr("style"));
		$('.input-group.date').datepicker({ });
		deleteBtnInfo();
	})		
 
	
/*工作经验修改界面---添加工*/
$(".gz_jy_update").click(function(){
		$("#panelBody").append($(".gongzuo_1").clone().removeClass("gongzuo_1 operation").addClass("jianju borders update-new-form").removeAttr("style"));
		$('.input-group.date').datepicker({ });
		deleteBtnInfo();
	})
 
/*项目经验修改界面---添加工*/
$(".xm_jy_update").click(function(){
		$("#xiangmu_body").append($(".xiangmu_1").clone().removeClass("xiangmu_1 xiangmu_1_form").addClass("jianju borders update-xiangmu-form").removeAttr("style"));
		$('.input-group.date').datepicker({ });
		deleteBtnInfo();
	})
 
/*获得证书修改界面---添加工*/
$(".hd_zs_update").click(function(){
		$("#zhengshu_body").append($(".zhengshu").clone().removeClass("zhengshu zhengshu_form").addClass("jianju borders update-zhengshu-form").removeAttr("style"));
		$('.input-group.date').datepicker({ });
		deleteBtnInfo();
	})
 
/*培训经历修改界面---添加工*/
$(".px_jl_update").click(function(){
		$("#peixun_body").append($(".peixun_1").clone().removeClass("peixun_1 peixun_1_form").addClass("jianju borders update-peixun-form").removeAttr("style"));
		$('.input-group.date').datepicker({ });
		deleteBtnInfo();
	})	
	
 
/*家庭成员修改界面---添加工*/
$(".fm_cy_update").click(function(){
		$("#jiating_body").append($(".famliy_1").clone().removeClass("famliy_1 famliy_1_form").addClass("jianju borders update-famliy-form").removeAttr("style"));
		$('.input-group.date').datepicker({ });
		deleteBtnInfo();
	})		
/*劳动合同*/
$(".laodong_ht").click(function(){
	var html=$(".hetong").clone().removeClass("hetong hetong_form hidden").addClass("jianju borders add-hetong-form").removeAttr("style id");
	$("#hetong_body").append(html);
	$(html).find('.input-group.date').datepicker();	
	deleteBtnInfo();
	})
	/*劳动合同修改界面---添加工*/
$(".laodong_ht_update").click(function(){
		$("#hetong_body").append($(".hetong").clone().removeClass("hetong hetong_form").addClass("jianju borders update-hetong-form").removeAttr("style"));
		$('.input-group.date').datepicker({ });
		deleteBtnInfo();
	})	
	
/*试用情况*/
$(".shiyong").click(function(){
	var html=$(".qing_kuang").clone().removeClass("qing_kuang qing_kuang_form hidden").addClass("jianju borders add-shiyong-form").removeAttr("style id");
	$("#shiyong_body").append(html);
	$('.input-group.date').datepicker({ });	
	deleteBtnInfo();
	})
	/*试用情况修改界面---添加工*/
$(".shiyong_update").click(function(){
		$("#shiyong_body").append($(".qing_kuang").clone().removeClass("qing_kuang qing_kuang_form").addClass("jianju borders update-shiyong-form").removeAttr("style"));
		$('.input-group.date').datepicker({ });
		deleteBtnInfo();
	})		
/*绩效考核*/
$(".kaohe").click(function(){
var html=$(".kao_he").clone().removeClass("kao_he kao_he_form hidden").addClass("jianju borders add-kaohe-form").removeAttr("style id");
	$("#kaohe_body").append(html);
	$('.input-group.date').datepicker({ });	
	deleteBtnInfo();	
	})
/*绩效考核修改界面---添加工*/
$(".kaohe_update").click(function(){
		$("#shiyong_body").append($(".kao_he").clone().removeClass("kao_he kao_he_form").addClass("jianju borders update-kaohe-form").removeAttr("style"));
		$('.input-group.date').datepicker({ });
		deleteBtnInfo();
	})		
/*奖惩记录*/
$(".jilu").click(function(){
var html=$(".jc_jilu").clone().removeClass("jc_jilu jc_jilu_form hidden").addClass("jianju borders add-jilu-form").removeAttr("style id");
$("#jilu_body").append(html);
	$('.input-group.date').datepicker({ });	
	deleteBtnInfo();
	})
	/*奖惩记录修改界面---添加工*/
$(".jilu_update").click(function(){
		$("#jilu_body").append($(".jc_jilu").clone().removeClass("jc_jilu jc_jilu_form").addClass("jianju borders update-jilu-form").removeAttr("style"));
		$('.input-group.date').datepicker({ });
		deleteBtnInfo();
	})	
/*添离职管理*/
/*$(".jb_xx").click(function(){
			$(".jbxx").after($(".jbxx").clone().removeClass("jbxx").addClass("jianju").addClass("borders"));
			deleteBtnInfo();
		})	
*/
	
	
/*修改*/
/*工作经验*/
function bianji(obj){

	//$(".shows").show();
	$(obj).parent().parent().parent().find("form").show();
	$(obj).parent().parent().hide();
	
	
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
     
 /*
  *  
  * 初始化添加表单模板的基础数据
  * @param jsonObj页面查询列表的json对象
  * @param baseDataTypeObj  页面添加表单收集的需要初始化的基础数据列表名称数组
  * 备注（后台的基础数据列表的key需要与页面上下拉列表的base-data-type值相等，后缀是List   例如base-data-type=education  后台基础数据的key: educationList）
  */
  function initBaseDataOptions(jsonObj,baseDataTypeObj){
	
	 $.each(baseDataTypeObj,function(i,item){
		 var baseType=item;
		 var html="";
		 var baseArry=jsonObj[baseType+"List"];
		 $("[base-data-type='"+baseType+"']").html("");
		 if(baseArry&&baseArry.length>0){
			 $.each(baseArry,function(i,obj){
				 $("[base-data-type='"+baseType+"']").append("<option value='"+obj.baseDataCode+"'>"+obj.baseDataName+"</option>");
				 
			 });
		 }
		 
		 
	 });
     
	
	 
 }    
  
  /**
	  * 入职审核-通过
	  */
	
	 function approved(personId){
		 $('[data-approved="approved"]').on('click', function(event) {
			  
			 var object={
					 personId:personId,
					 approvedStatus:3
			 };
			   
		      var json = {'personData': JSON.stringify(object) };
		      $.ajax({
		       type: "post",
		       url: "employee/updateApprovedStatus",
		       data: json,
		       dataType: "json",
		       async: false,
		       success:function(data){
		    				if(data.success == true){
		    					 
		    					swal({
		    		                title:"审核成功",
		    		                type: "success"
		    		                }, 
		    		                function(){
		    		    			 location.href="employees/audit_employee_todo";
		    		              });   
		    				}else{
		    					swal("审核失败！", "", "error"); 
		    				}
		    			}
		    		});   
		 });
	 }
 
     