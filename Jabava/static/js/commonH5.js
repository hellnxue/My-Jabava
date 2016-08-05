// common.js
// @creatBy junyi.liu
// @creatAt 2016-01-20

/**
 * 将毫秒时间戳转换为ISO标准时间格式
 * @param  {number} ms   毫秒时间戳
 * @return {string} date ISO格式的日期字符串
 *
 * TODO　重构转化到Date的原型方法上
 */
// function getHandleDate(ms){
// 	var date=new Date(ms);
// 	var month=Number(date.getMonth()+1)+"";
// 	if(month.length<2){
// 		month="0"+month;
// 	}
// 	var day=Number(date.getDate())+"";
// 	if(day.length<2){
// 		day="0"+day;
// 	}

// 	return date.getFullYear()+"-"+month+"-"+day;
// };


// var templateFillData = function(templateID, containerID, dataUrl, delUrl, metaKey){
// 	$.getJSON(dataUrl, function(json, textStatus){
// 		//console.log("textStatus======"+textStatus+"\n"+employeeType);
// 		if(json&&textStatus =='success'){

// 			// console.log(json)
// 			metaKey = metaKey || 'list';
// 			if(json[metaKey].length < 1){
// 				$('#create_new').removeClass('hidden');
// 				return;
// 			}

// 			var html = template(templateID, json);
// 				html = $(html);
// 			// form init
// 			var getDomStaticTarget = html.find('.input-group-static'),
// 				getDomTarget = getDomStaticTarget.siblings(),
// 				getDomFormAction = html.find('.form-action');
			
// 			getDomTarget.hide();
// 			getDomFormAction.hide();
			
// 			if(uploadFileType=="certificate"){
// 				imagePreview(html.find("img"));//图片放大
// 			}

// 			// @eventBind {action} .action-group
// 			html.find('.action-group [data-action-motive]').on('click', function(evt){
// 				var getAction = $(this).data('actionMotive'),
// 					$form = $(this).parents('form'),
// 					$getDomStaticTarget = $form.find('.input-group-static'),
// 					$getDomTarget = $getDomStaticTarget.siblings(),
// 					$getDomFormAction = $form.find('.form-action');

// 				switch(getAction){
// 					case 'del':
// 						var getActionId = $(this).data('actionId');
						
						
// 						//console.log(delUrl)
// 						// show modal
// 						swal({
// 							title: "确定要删除该条信息吗?",
// 							text: "注意：用户删除后将不可恢复!",
// 							type: "warning",
// 							showCancelButton: true,
// 							confirmButtonColor: "#DD6B55",
// 							confirmButtonText: "是,请删除该信息!",
// 							cancelButtonText: "不, 放弃此操作!",
// 							closeOnConfirm: false,
// 							closeOnCancel: false },
// 							function (isConfirm){
// 								if (isConfirm){
// 									delUrl = delUrl+getActionId;

// 									commonDelete(delUrl);
// 									$('#form-'+getActionId).remove()

// //                                 
// 								} else {
// 									swal("已取消", "", "error");
// 								}
// 							});
// 						break;
// 					case 'edit':
// 						$getDomStaticTarget.hide();
// 						$getDomTarget.show();
// 						$getDomFormAction.show();
// 						break;
// 				}
				
				
				
// 			});


// 			// @eventBind {action} .form-action
// 			var getActionBtnLen = getDomFormAction.length;
// 			if( getDomFormAction.find('[data-action-motive=submit]').length>0 ){
// 				getDomFormAction.eq(getActionBtnLen-1).show();
// 			}
			
// 			getDomFormAction.find('[data-action-motive]').on('click', function(evt){

// 				var $form = $(this).parents('form'),
// 					$getDomStaticTarget = $form.find('.input-group-static'),
// 					$getDomTarget = $getDomStaticTarget.siblings(),
// 					$getDomFormAction = $form.find('.form-action');
				
// 				var getAction = $(this).data('actionMotive');
// 				switch(getAction){
					
// 					case 'save':
						
// 						// alert("save...");
						
// 						commonUpdateSaveBefore($form);
						 
// 						 break;
// 					case 'cancel':
// 						$getDomStaticTarget.show();
// 						$getDomTarget.hide();
// 						$getDomFormAction.hide();
// 						break;
// 					case 'submit':
// 						var getActionCB = $(this).data('actionCallback');
// 						getActionCB = getActionCB.split('|');
// 						var params = $.map(getActionCB, function(item, index){
// 							if(index>0) return item
// 						});
// 						getActionCB = getActionCB[0];
// 						window[getActionCB](params);
// 						break;
// 				}
// 			});


// 			// insert DOM
// 			$(containerID).empty().prepend(html);
			
// 			$('.input-group.date').datepicker({
// 				 format: "yyyy-mm-dd",
// 				 autoclose: true
// 			  });

// 		}else{
// 		   // if(json[metaKey].length < 1){
// 				$('#create_new').removeClass('hidden');
// 				return;
// 			//}
// 		}
// 	});


// };
// /**
//  * 员工信息子模块公共添加方法
//  */
//  function commonSaveBefore($form,from,saveAction){
// 		var fileID=$form.find(":file").attr("id");
// 		//console.log("fileID===="+fileID);
// 		//文件上传
// 		var formObject=$form.serializeObject();
// 		//console.log("formObject="+formObject);
		
// 		//PC端由页面获得personId,手机端直接在后台处理
// 		if(from=="PC"){
// 			formObject.personId=personId;
// 		}
		
// 		//获得证书：证书电子版上传
// 		if(uploadFileType=="certificate"){
// 			 $.ajaxFileUpload({
// 				  url: 'employee/certificateUploadFile', //用于文件上传的服务器端请求地址
// 				  secureuri: false, //是否需要安全协议，一般设置为false
// 				  fileElementId: [fileID ], //文件上传域的ID   
// 				  //fileElementId:  fileID , //文件上传域的ID   
// 				  async:false,
// 				  dataType:'text',
// 				  type:"POST",
// 				  success: function (data){  //服务器成功响应处理函数
// 					  //console.log(data);
// 					 if(data){
						 
// 						 if((data.realUrl&&data.success)||data.realUrl==""){
// 							 //console.log("upload success or noupload");
							 
// 							 formObject.filePath=data.realUrl;
							 
// 							 // console.log("formObject22="+JSON.stringify(formObject));
// 							  commonSave(formObject,from,saveAction);
// 						 }
// 					 }       		
// 				  } 
// 				});
			
// 		} else{
// 			 commonSave(formObject,from,saveAction);
// 		}
		  
		 
// 	}

 
//  /**
//   * 手机员工录入页面，仅保存
//   * @param formObject 表单对象
//   * @param saveAction 保存关闭&保存跳至下一项
//   */
//  function onlyEnterSave($form,addURL,saveAction,$hideModal,$nextModal,personId){
		
		
// 		var formObject=$form.serializeObject();
		
// 		//if(addURL=="employee/updatePerson"){
// 			 formObject.personId=personId;
// 		//}
		
// 		var jsonObject=JSON.stringify(formObject);
		
// 		 //console.log("jsonObject="+jsonObject);
// 		$.ajax({
// 			type:"post",
// 			url:addURL,
// 			contentType:"application/json",
// 			data:jsonObject,
// 			dataType:"json",
// 			success:function(data){
// 				if(data&&data.success){
// 					swal(data.msg, "", "success"); 
					
// 					  //手机端处理,填写下一项
// 						if(saveAction=="saveAndClose"){
// 							//保存关闭
// 							location.replace("employees/wanchengluru");
// 						}else{
// 							//保存并跳转到下一项
// 							$hideModal.modal('hide');
// 							$nextModal.modal();
// 						}
					
// 				}else{
// 					swal(data.msg, "", "error"); 
// 				}
				
// 			}
			
// 		});  
		  
		 
// 	}
 
//  /**
//   * 上传附件
//   */
//  function uploadFiles($form,saveAction,$hideModal,$nextModal){
// 		var object=$form.serializeObject();
		
// 		var json=JSON.stringify(object);
// 		//上传图片
// 		  $.ajax({
// 			url: "employee/exportPerson.do",
// 			type: 'POST',
// 			cache: false,
// 			dataType: 'json',
// 			data: new FormData($form[0]),
// 			processData: false,
// 			contentType: false
// 		  }).done(function(data) {
				 
// 			//alert(data.msg);
// 			if(data.success == true){
// 				swal(data.msg, "", "success"); 	
// 				 //手机端处理,填写下一项
// 				if(saveAction){
// 					//保存关闭
// 					location.replace("employees/wanchengluru");
// 				}else{
// 					//保存并跳转到下一项
// 					$hideModal.modal('hide');
// 					$nextModal.modal();
// 				}
// 			}else{
// 				swal(data.msg, "", "error"); 
// 			}
					 
				  
// 		});       
		  
// 	};
 
 
 

// /**
//  * 公共修改保存方法条件处理
//  */
// function commonUpdateSaveBefore($form){
	
// 	var img=$form.find("img").attr("src");
	
// 	var formObject= $form.serializeObject() ;
// 	//console.log("formObject11="+JSON.stringify(formObject));
// 	var fileID=$form.find(":file").attr("id");//上传域ID
// 	//console.log("fileID===="+fileID);
	
// 	//获得证书：证书电子版上传
// 	if(uploadFileType=="certificate"){
// 		if(img==""||img==undefined){
// 			 $.ajaxFileUpload({
// 			  url: 'employee/certificateUploadFile', //用于文件上传的服务器端请求地址
// 			  secureuri: false, //是否需要安全协议，一般设置为false
// 			  fileElementId: [fileID ], //文件上传域的ID   
// 			  //fileElementId:  fileID , //文件上传域的ID   
// 			  async:false,
// 			  dataType:'text',
// 			  type:"POST",
// 			  success: function (data){  //服务器成功响应处理函数
// 				  //console.log(data);
// 				 if(data){
					 
// 					 if((data.realUrl&&data.success)||data.realUrl==""){
// 						 console.log("upload success or noupload");
						 
// 						 delete formObject.myfiles;
// 						 formObject.filePath=data.realUrl;
						 
// 						  //console.log("formObject22="+JSON.stringify(formObject));
// 						  commonUpdateSave(formObject);
// 					 }
// 				 }       		
// 			  } 
// 			});
// 		}else{
// 			 commonUpdateSave(formObject);
// 		}
		
// 	} else{
// 		commonUpdateSave(formObject);
// 	}
	
	
// }
// /**
//  * 公共修改保存方法，最终保存方法
//  */
// function commonUpdateSave(formObject){
	
// 	//var formObject= $form.serializeObject() ;
	
// 	var jsonObject=JSON.stringify(formObject);
// 	//console.log(jsonObject);
// 	$.ajax({
// 		type:"post",
// 		url:updateURL,
// 		data:jsonObject,
// 		dataType:"json",
// 		contentType:"application/json",
// 		success:function(data){
// 			if(data&&data.success){
// 				 swal(data.msg, "", "success"); 
// 				 $(addFormSelector).remove();
				
// 				 containerID.html("");
// 				//重新渲染
// 				 templateFillData(templateID, containerID, listURL,delsURL);
// 			}else{
// 				swal(data.msg, "", "error"); 
// 			}
			
// 		}
// 	}); 
	
// }
// /**
//  * 公共删除方法
//  */
// function commonDelete(delUrl){
// 	$.post(delUrl,function(data){
		 
// 		if(data&&data.success){
// 			//console.log("data="+data);
// 			 swal(data.msg, "", "success"); 
// 			 $(addFormSelector).remove();
// 			 containerID.html("");
// 			//重新渲染
// 			 templateFillData(templateID, containerID, listURL,delsURL);
// 		}else{
// 			swal(data.msg, "", "error"); 
// 		}
// 	},"json" );
// }