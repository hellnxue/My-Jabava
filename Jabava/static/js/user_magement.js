// JavaScript Document
/*设置权限新增与删除开始*/
$(function(){
	var rowNum=0; 
	
	//新增行 
	$("#addRow_tow").bind("click",function(){
		
		rowNum +=1; 
		var rowHtml="";
		rowHtml +="<div class=\"form-group quanxian_div clearfix quanxian_div_third\" id='"+rowNum+"'>";
		rowHtml +="<span class=\"col-lg-2 selcct_span\">";
		rowHtml +="<select class=\"form-control m-b nomargin_tow new_style_third\" name=\"account\" placeholder=\"默认\">";
		rowHtml +="<option>默认</option>";
		rowHtml +="<option>社保</option>";
		rowHtml +="</select>";
		rowHtml +="</span>";
		rowHtml +="<span class=\"col-lg-2 selcct_span\">";
		rowHtml +="<select class=\"form-control m-b nomargin_tow new_style_third\" name=\"account\" placeholder=\"字段条件项\">";
		rowHtml +="<option>字段条件项</option>";
		rowHtml +="<option>AND</option>";
		rowHtml +="<option>OR</option>";
		rowHtml +="</select>";
		rowHtml +="</span>";
		rowHtml +="<span class=\"col-lg-2 selcct_span\">";
		rowHtml +="<select class=\"form-control m-b nomargin_tow new_style_third\" name=\"account\" placeholder=\"工作地\">";
		rowHtml +="<option class=\"user_x\">工作地</option>";
		rowHtml +="<option class=\"user_x\">发薪地</option>";
		rowHtml +="<option class=\"user_x2\">部门</option>";
		rowHtml +="</select>";
		rowHtml +="</span>";
		rowHtml +="<span class=\"col-lg-2 selcct_span\">";
		rowHtml +="<select class=\"form-control m-b nomargin_tow new_style_third\" name=\"account\" placeholder=\"操作1\">";
		rowHtml +="<option>操作1</option>";
		rowHtml +="<option>操作2</option>";
		rowHtml +="<option>操作3</option>";
		rowHtml +="</select>";
		rowHtml +="</span>";
		rowHtml +="<span class=\"col-lg-2 selcct_span selcct_ddspan selcct_ddspan"+rowNum+"\">";
		rowHtml +="<select class=\"js-source-states-3 form-control m-b nomargin_tow new_style_third\" multiple=\"multiple\" placeholder=\"选项1\">";
		rowHtml +="<option value=\"选项1\">选项1</option>";
		rowHtml +="<option value=\"选项1\">选项2</option>";
		rowHtml +="<option value=\"选项1\">选项3</option>";
		rowHtml +="</select>";
		rowHtml +="</span>";
		rowHtml +="<span class=\"col-lg-2 selcct_span selcct_dspan selcct_dspan"+rowNum+"\">"
		rowHtml +="<input class=\"user_xinput\">"
		rowHtml +="</span>"
		rowHtml +="<span class=\"col-lg-2 selcct_span\">";
		rowHtml +="<button class=\"btn btn-info btn-xs dropdown-toggle user_demo button_height\" onClick=\"deleteRow("+rowNum+")\">";
		rowHtml +="<a class=\"wcolor\">"+"<i class=\"fa fa-times wcolor\">"+"</i>删除</a>";
		rowHtml +="</button>";
		rowHtml +="</span>";
		rowHtml +="</div>";
		$(".quanxian_div_five").after(rowHtml);
	    $(".js-source-states-3").select2();
		
		$(function(){
			
			var rowNum2=rowNum;
			$(".user_x").on("click",function(){
				
				$(".selcct_ddspan"+rowNum2).show();
				$(".selcct_dspan"+rowNum2).hide();
				
			});
			$(".user_x2").on("click",function(){
				
				$(".selcct_ddspan"+rowNum2).hide();
				$(".selcct_dspan"+rowNum2).show();
				
			});
	
		});
		/*$(".tianjia").eq(0).after($(".tianjia").eq(0).clone().addClass("hehe"+rowNum));*/
	});
	
});
//删除行 
function deleteRow(num){
	 
	$("#addRow_tow").parent().parent().parent().find("#"+num).remove();	

} 
function deleteRow2(){
	
	$(".quanxian_div_five").hide();
	
}

//多选下拉菜单
 $(".js-source-states-2").select2();
/*设置权限新增与删除结束*/


//工作地，发新地，部门切换
$(function(){
	
	$(".user_x").on("click",function(){
		
		$(".selcct_ddspan").show();
		$(".selcct_dspan").hide();
		
	});
	$(".user_x2").on("click",function(){
		
		$(".selcct_ddspan").hide();
		$(".selcct_dspan").show();
		
	});
	
})
