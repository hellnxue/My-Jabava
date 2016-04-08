// JavaScript Document
/*计算器*/
/*选择城市*/
  var temp="no";
	$(document).ready(function(){
		//点击省份
		 $(".province").click(function(){
		  $(".sltcity").hide();
		  $(".procity").show();
		  });
		  
		//点击城市	
		  $(".city").click(function(){
		  $(".procity").hide();
		  if(temp=="yes"){
			   $(".sltcity").show();
			  }
	  });
		  
		  
		//是否缴纳公积金
		  $("#paymentStatu").on("click",function(){
			  
			  if($(this).prop("checked")){
				  $(".handlebox").show();
				  
			  }else{
				  $(".handlebox").hide();
			  }
		  });
		  
		  //社保基数验证
		  $("#jishu").on("keyup",function(){
			  jishuValidator("#shebaojishu",this);
			 
		  }); 
		  
		  //公积金验证
		  $("#gongjijin").on("keyup",function(){
			   jishuValidator("#gongjijinjishu",this);
				  
			  }); 
		  
	 });
	
	
		 
/*切换*/
	$(document).ready(function(){
		$("#calculatorHandle").click(function(){
			
			 porovinceAndCityPanel();
			 
			});
		 
	
		$("#get").click(function(){
			
			 porovinceAndCityPanel();
			 
			});
		 
		//政策包下拉处理 
		
		$('#get1, #policylist').on('click', function(event) {
			event.preventDefault();
			var statu=$("#test").attr("style");
			var getLength = $('#test > li').length
			
			if(statu==undefined||statu.indexOf("none")!=-1){
				console.log(getLength)
				if(getLength > 0){
					$("#test").show();
				}else{
					$('#test').dropdown('toggle')
				}
			}else{
				$("#test").hide();
			}
		});
		
		/*
		$("#get1").click(function(){
			var statu=$("#test").attr("style");
			console.log( $('#test > li').length )
			if(statu==undefined||statu.indexOf("none")!=-1){
				$("#test").show();
			}else{
				$("#test").hide();
			}
			 
			});
		//政策包下拉处理 
		$("#policylist").click(function(){
			var statu=$("#test").attr("style");
			console.log( $('#test > li').length )
			if(statu==undefined||statu.indexOf("none")!=-1){
				$("#test").show();
			}else{
				$("#test").hide();
			}
			 
			});
		*/
		 
		});
		
		 
/*计算 */
    $(document).ready(function(){
		 $(".ec").click(function(){
			/* if($("#get").val()!=""){
				
				$(".noyes").hide();
				$(".yes").show();
				$(".yes_1").show();
				$(".yes_2").show();
				$(".tbody").show();
				$(".yc").show();
				 }*/
				
				if(!$("#get").val()&&!resultDataArray){
					
					 porovinceAndCityPanel();
					 return false;
					 
				 }else if(!$("#get1").val()&&!resultDataArray){
					 
					  alert("当前没有参保类型！");
	 					
					  return false;
				 }
				 jishuValidator("#shebaojishu",$("#jishu")[0]);
				 if(isTrue){
					jishuValidator("#gongjijinjishu", $("#gongjijin")[0]);
				 }
				 
				 if($("#get").val()&&resultDataArray&&isTrue){
					 
					 $(".nullTip").hide();
					 $(".tbody").show();
					 policyCalculator(resultDataArray);
					 
				 }
			 
			
			 });
		});
	
	
 
/* 重置*/		
	$(document).ready(function(){
		 $(".cz").click(function(){
			 	$(".nullTip").show();
				$(".noyes").show();
				$(".yes").hide();
				$(".yes_1").hide();
				$(".yes_2").hide();
				$(".tbody").hide();
				$(".yc").hide();
				$("#get").val("");
				$("#get1").val("");
				$("#jishu").val("");
				$("#gongjijin").val("");
				$("#geren").val("");
				$("#gongsi").val("");
				
			     
 
			 })
		})
				
	$(document).ready(function(){
		//省份  城市 span
		$("#pro1").on("click",function(event){
			event.stopPropagation();
					 if(!$(this).hasClass("bor")){
						 $("#pro1").addClass("bor");
					     $("#city").removeClass("bor");
						 }
					
					}) 
					
		$("#city").on("click",function(event){
			event.stopPropagation();
			 if(!$(this).hasClass("bor")){
				 $("#city").addClass("bor");
			     $("#pro1").removeClass("bor");
				 }
			})

			//选择省份
			$("#provinceHandle").on('click', "dl a",function(event){
				event.stopPropagation();
				  $("#provinceHandle a").removeClass("current");
				  $(this).addClass("current");
				  $(".procity").hide();
			      $(".sltcity").show();
				  $("#get").val($(this).text());
				 /* $("#jishu").val("2060.00");
				  $("#gongjijin").val("930.00");
				  $("#geren").val("10.00");
				  $("#gongsi").val("10.00");*/
				  temp="yes";
				  /*span 切换*/
				 $("#city").addClass("bor");
				 $("#pro1").removeClass("bor");
				 
				 //城市查询
				 getCityInfo(this.dataset.pid);
				 
				 
				});
			
			//选择城市
		    $("#cityHandle").on('click', " dl a", function(event){
		    	event.stopPropagation();
				  $("#cityHandle a").removeClass("current");
				  $(this).addClass("current");
				  var p1=$("#provinceHandle .current").text();
				  var p2=$("#cityHandle .current").text();
				  $("#get").val(p1+" "+p2);
				  $(".star").css("display","none");
			      $(".nullTip").show();
				  $(".noyes").show();
				  $(".yes").hide();
				  $(".yes_1").hide();
				  $(".yes_2").hide();
				  $(".tbody").hide();
				  $(".yc").show();
				  $("#paymentStatu").prop("checked","checked");
					
					//处理政策包数据
					$("#test").html("");
					var dataObject=getPolicyInfo(this.dataset.cid);//根据城市获取政策信息
					var paramType="";//参保类型
					var shebaoBaseScope="";//社保基数范围（取公司的范围）
					var gongjijinBaseScope="";//公积金基数范围
					var comRatio="";//公司比例-公积金
					var indRatio="";//个人比例-公积金
					if(dataObject){
						
						dataObject.resultData.forEach(function(item,index,array){
							
							paramType+='<li><a >'+item.groupName+'</a></li>';
							
							if(index==0){
								  $("#get1").val(item.groupName);//参保类型
									
									//处理社保基数
									item.sbGroupDetail.forEach(function(item,index,array){
										 
										if(index==0){
											
											if(item.prodName.indexOf("公积金")==-1){
												
												 shebaoBaseScope=item.companyBaseScope;
												
											}
											
										}
										
										if(item.prodName.indexOf("公积金")!=-1){
											
											 comRatio=item.comRatio;
											 indRatio=item.indRatio;
											 gongjijinBaseScope=item.companyBaseScope;
											
									    }
										
									});
									
								
							}
							 
							
						});
						
						$("#test").append(paramType);//参保类型
						$("#jishu").val(shebaoBaseScope.split("~")[0]);//社保基数范围
						$("#jishu").attr("data-min",shebaoBaseScope.split("~")[0]);
						$("#jishu").attr("data-max",shebaoBaseScope.split("~")[1]);
						$("#shebaojishu").html("基数范围"+shebaoBaseScope.replace("~", "到"));
						$("#gongjijin").val(gongjijinBaseScope.split("~")[0]);//公积金基数范围
						
						$("#gongjijin").attr("data-min",gongjijinBaseScope.split("~")[0]);
						$("#gongjijin").attr("data-max",gongjijinBaseScope.split("~")[1]);
						
						$("#gongjijinjishu").html("基数范围"+gongjijinBaseScope.replace("~", "到"));
					
						$("#geren").val(comRatio);
						$("#gComRatio").html("比例范围"+comRatio+"到"+comRatio);
						$("#gongsi").val(indRatio);
						
						$("#gIndRatio").html("比例范围"+indRatio+"到"+indRatio);
						
						
					}else{
						$("#get1").val("");
						$("#jishu").val("");
						$("#gongjijin").val("");
						$("#geren").val("");
						$("#gongsi").val("");
						$("#shebaojishu").html("");
						$("#gongjijinjishu").html("");
						
					}
				  
				});
		    
		        
				/*参保类型*/	
			     $("#test").delegate("li","click",function(){
			    	 $("#test").hide();
					$("#get1").val($(this).text());//参保类型
					var groupName=$(this).text();
					var shebaoBaseScope="";//社保基数范围（取公司的范围）
					var gongjijinBaseScope="";//公积金基数范围
					var comRatio="";//公司比例-公积金
					var indRatio="";//个人比例-公积金
					
					resultDataArray.forEach(function(item,index,array){
						
						if(groupName==item.groupName){
							
							//处理社保基数
							item.sbGroupDetail.forEach(function(item,index,array){
								 
								if(index==0){
									
									if(item.prodName.indexOf("公积金")==-1){
										
										 shebaoBaseScope=item.companyBaseScope;
										
									}
									
								}
								
								if(item.prodName.indexOf("公积金")!=-1){
									
										 comRatio=item.comRatio;
										 indRatio=item.indRatio;
										 gongjijinBaseScope=item.companyBaseScope;
										
								}
								
							});
							
						}
						
					});
					$("#jishu").val(shebaoBaseScope.split("~")[0]);//社保基数范围
					$("#jishu").attr("data-min",shebaoBaseScope.split("~")[0]);
					$("#jishu").attr("data-max",shebaoBaseScope.split("~")[1]);
					$("#shebaojishu").html("基数范围"+shebaoBaseScope.replace("~", "到"));
					$("#gongjijin").val(gongjijinBaseScope.split("~")[0]);//公积金基数范围
					$("#gongjijin").attr("data-min",gongjijinBaseScope.split("~")[0]);
					$("#gongjijin").attr("data-max",gongjijinBaseScope.split("~")[1]);
					$("#gongjijinjishu").html("基数范围"+gongjijinBaseScope.replace("~", "到"));
					$("#geren").val(comRatio);
					$("#gComRatio").html("比例范围"+comRatio+"到"+comRatio);
					$("#gongsi").val(indRatio);
					
					$("#gIndRatio").html("比例范围"+indRatio+"到"+indRatio);
			    	 
				 });
		});

		 
		 
/*记录集*/	 
	$(document).ready(function(){
		$(".more").click(function(){
			$(".form-row").show();
			})
		})	 
		 
	 
	/*查询*/	 
 $(function(){
	  
	  $(".show_tow").click(function(){
		  
		  $(".nodisplay").show();
		  
	})
	  
})

	//基数验证
	function jishuValidator(id,thiss){
		 
		 var js=thiss.value;
		 var hjs=parseFloat(js);
		 var ijs=parseInt(js);
		 var fmin=parseFloat(thiss.dataset.min);
		 var fmax=parseFloat(thiss.dataset.max);
		 
		 var imin=parseFloat(thiss.dataset.min);
		 var imax=parseFloat(thiss.dataset.max);
		 var regInt=/^[0-9]*[1-9][0-9]*$/; //整数
		 var regFloat=/^\d{1,}\.{0,1}\d{1,}$/; //浮点
		 
		if(js==""){
			
			   validatorUtil(id,"请填写基数");
			   
		}else if( regInt.test(js)){
			//console.log("int");

			if(ijs<imin||ijs>imax){
				 
				validatorUtil(id,"基数范围"+fmin+"~"+fmax);
				
			}else{
				
				validatorUtil(id,fmin,fmax);
			}
			
			
		}else if(regFloat.test(js)){
			//console.log("float");
			if(hjs<fmin||hjs>fmax){
				
				validatorUtil(id,"基数范围"+fmin+"~"+fmax);
				
			}else{
				
				validatorUtil(id,fmin,fmax);
			}
			
		}else if(!regInt.test(js)&&!regFloat.test(js)){
			//console.log("not int , not float");
			validatorUtil(id,"基数范围"+fmin+"~"+fmax);
			
		}else{
			
			validatorUtil(id,fmin,fmax);
			
		}
		
	}
	
	//验证公共函数
	function validatorUtil(id,errMsg,fmax){
		
		if(arguments.length==2){
			isTrue=false;
			 
			$(id).html("<label style=\"color:#f60\">"+errMsg+"！<label>");
			
			return false;
			
		}else{
			$(id).html("基数范围"+errMsg+"到"+fmax);
			isTrue=true;
		}
		
		
	}	 