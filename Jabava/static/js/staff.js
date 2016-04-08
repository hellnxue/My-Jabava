/*添加员工并发送邮件*/	
	$(function(){
		
		var styleFlag="";
		
		 $(".hellow label").filter(".staff_email,.staff_email_tow").mouseover(function(){
					 
				  if($(this).css("background-color")==="rgb(213, 213, 213)"){//灰色

						styleFlag=$(this).css("background-color");//灰色
						 
						$(this).css("background-color","#62cb31");//绿色
						 
						 
				   }else{
					   
						styleFlag="";
						
				    }
			
			}) .mouseout(function(){
			 
				  if(styleFlag!==""){//灰色
						 
						
					 $(this).css("background-color", styleFlag);
					 console.log("out.........."+styleFlag);
						 
				   } 
				
		   }).on("click",function(){
				  
				 styleFlag="";
				 $(this).css("background-color","#62cb31");//绿色;
				 if($(this).hasClass("staff_email_tow")){
					   
					   $(".staff_email").css("background-color","#d5d5d5"); 
					   
				 }else{
					 
					 $(".staff_email_tow").css("background-color","#d5d5d5"); 
				
				}
				
			   });

		});
		
/*浮层继续添加员工*/		
		$(function(){
		
		var styleFlage="";
		
		 $(".hellow_two label").filter(".staff_email_five,.staff_email_six").mouseover(function(){
					 
				  if($(this).css("background-color")==="rgb(213, 213, 213)"){//灰色

						styleFlage=$(this).css("background-color");//灰色
						 
						$(this).css("background-color","#62cb31");//绿色
						 
						 
				   }else{
					   
						styleFlage="";
						
				    }
			
			}) .mouseout(function(){
			 
				  if(styleFlage!==""){//灰色
						 
						
					 $(this).css("background-color", styleFlage);
					 console.log("out.........."+styleFlage);
						 
				   } 
				
		   }).on("click",function(){
				  
				 styleFlage="";
				 $(this).css("background-color","#62cb31");//绿色;
				 if($(this).hasClass("staff_email_six")){
					   
					   $(".staff_email_five").css("background-color","#d5d5d5"); 
					   
				 }else{
					 
					 $(".staff_email_six").css("background-color","#d5d5d5"); 
				
				}
				
			   });

		});
		
/*开与关*/
	$(".staff_open").click(function(){
		
		$(".staff_open").hide();
		$(".staff_close").show();
	    $(".staff_email").hide();
		$(".staff_email_tow").hide();
		$(".staff_email_third").show();
		$(".staff_email_third").css("display","inline-block")
									
	});
	$(".staff_close").click(function(){
									
		$(".staff_close").hide();
		$(".staff_open").show();
		$(".staff_email").show();
		$(".staff_email_tow").show();
		$(".staff_email_third").hide();
									
	});
	
