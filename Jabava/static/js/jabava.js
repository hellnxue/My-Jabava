// JavaScript Document
//按钮权限-菜单权限转换
function f_show_one(obj){
	
	if(obj==button_authority){
	  
	 		 $("#nestable").show();
			 $("#nestable_tow").hide();
			 $("#button_authority").css({border:"1px solid #ddd",borderBottom:"none",lineHeight:"20px"});
			 $("#menu_permissions").css({border:"1px solid #ddd",borderLeft:"none",lineHeight:"19px"})
	  
	}
	if(obj==menu_permissions){
		
		     $("#nestable").hide();
			 $("#nestable_tow").show();
			 $("#button_authority").css({border:"1px solid #ddd",borderRight:"none",lineHeight:"19px"});
			 $("#menu_permissions").css({border:"1px solid #ddd",borderBottom:"none",lineHeight:"19px"})
		
	}
}


//按钮权限树状图
    $(function () {

        var updateOutput = function (e) {
            var list = e.length ? e : $(e.target),
                    output = list.data('output');
            if (window.JSON) {
                output.val(window.JSON.stringify(list.nestable('serialize')));//, null, 2));
            } else {
                output.val('JSON browser support required for this demo.');
            }
        };
        // activate Nestable for list 1
        $('#nestable').nestable({
            group: 1
        }).on('change', updateOutput);
		$('#nestable_tow').nestable({
            group: 1
        }).on('change', updateOutput);
		updateOutput($('#nestable').data('output', $('#nestable-output')));
		updateOutput($('#nestable_tow').data('output', $('#nestable_tow-output')));
	})
	
	
	
	//复位
		$("#dellabel").data("key",$("#dellabel").html());
		$("#dellabel_one").data("key",$("#dellabel_one").html());
		
		$(".fuwei").on("click",function(){

			$("#dellabel").html($("#dellabel").data("key"));
			$("#dellabel_one").html($("#dellabel_one").data("key"));
		});		
				
			
		//已选人员
		$("#dellabel").delegate("label","click",function(){
			$(this).find("span").removeClass().addClass("pe-7s-check role_span_margin");
			$("#dellabel_one").append($(this).clone());
			$(this).remove();
		});
		
		
		
		
		//未选人员
		$("#dellabel_one").delegate("label","click",function(){
		    
			$(this).find("span").removeClass().addClass("pe-7s-close-circle role_span_margin");
			$("#dellabel").append($(this).clone());
			 
			$(this).remove();
		
		});

    
	

