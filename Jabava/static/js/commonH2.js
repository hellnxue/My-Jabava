// common.js
// @creatBy junyi.liu
// @creatAt 2016-01-20

/*
 * 模板渲染 & 修改
* @param templateID     {String} 模板ID
* @param containerID    {jQuery Object} 渲染ID
* @param dataUrl        {String} 列表查询URL
* @param metaKey        {String} 列表查询JSON关键字
 */

template.helper('dateFormat', function(date){

    date = getHandleDateYMD(date);

    return date;
});

var tt="";
var templateFillData = function(templateID, containerID, dataUrl, delUrl, metaKey,uploadFileType){
    if(uploadFileType){
    	
    	tt=uploadFileType;
    }else{
    	tt="";
    }

    $.getJSON(dataUrl, function(json, textStatus){
    	//console.log("textStatus======"+textStatus+"\n"+employeeType);
        if(json&&textStatus =='success'){

            // console.log(json)
            metaKey = metaKey || 'list';
            if(json[metaKey].length < 1){
                $('#create_new').removeClass('hidden');
                return;
            }

            var html = template(templateID, json);
                html = $(html);
            // form init
            // console.log($('[data-form]').length)
            var  getDomFormAction = html.find('.form-action'),
                 getDomForm =  getDomFormAction.parent('form');

            getDomForm.hide();
            getDomFormAction.hide();
            
            if(uploadFileType=="certificate"){
            	imagePreview(html.find("img"));//图片放大
            }

            // @eventBind {action} .action-group
            html.find('.action-group [data-action-motive]').on('click', function(evt){
                var getAction = $(this).data('actionMotive'),
                    getActionId = $(this).attr('data-action-id'),
                    // $form = $(this).parents('form'),
                    // $getDomStaticTarget = $form.find('.input-group-static'),
                    // $getDomTarget = $getDomStaticTarget.siblings(),
                    $getDomForm = $('[data-form='+getActionId+']'),
                    $getDomFormAction = $getDomForm.find('.form-action'),
                    $getActionGroupParent = $('[data-div='+getActionId+']');
                    // console.log(getActionId)
                switch(getAction){
                    case 'del':
                    	var getActionId = $(this).data('actionId');
                    	
                    	
                    	//console.log(delUrl)
                    	// show modal
                        swal({
                            title: "确定要删除该条信息吗?",
                            text: "注意：用户删除后将不可恢复!",
                            type: "warning",
                            showCancelButton: true,
                            confirmButtonColor: "#DD6B55",
                            confirmButtonText: "是,请删除该信息!",
                            cancelButtonText: "不, 放弃此操作!",
                            closeOnConfirm: false,
                            closeOnCancel: false },
                            function (isConfirm){
                                if (isConfirm){
                                	delUrl = delUrl+getActionId;

                                	commonDelete(delUrl,metaKey,uploadFileType);
                                	$('#form-'+getActionId).remove();

//                                 
                                } else {
                                    swal("已取消", "", "error");
                                }
                            });
                        break;
                    case 'edit':
                        // $getDomStaticTarget.hide();
                        // $getDomTarget.show();
                        $getDomForm.show();
                        $getDomFormAction.show();
                        $getActionGroupParent.hide();
                        break;
                }
                
                
                
            });


            // @eventBind {action} .form-action
            var getActionBtnLen = getDomFormAction.length;
            if( getDomFormAction.find('[data-action-motive=submit]').length>0 ){
            	getDomFormAction.eq(getActionBtnLen-1).show();
            }
            
            getDomFormAction.find('[data-action-motive]').on('click', function(evt){

                var $form = $(this).parents('form'),
                    // $getDomStaticTarget = $form.find('.input-group-static'),
                    // $getDomTarget = $getDomStaticTarget.siblings(),
                    $getDomFormAction = $form.find('.form-action'),
                    getActionId = $form.attr('data-form'),
                    $getActionGroupParent = $('[data-div='+getActionId+']');

                var getAction = $(this).data('actionMotive');
                switch(getAction){
                    
                    case 'save':
                    	
                    	// alert("save...");
                    	
                    	commonUpdateSaveBefore($form,metaKey,uploadFileType);
                    	 
                    	 break;
                    case 'cancel':
                        // $getDomStaticTarget.show();
                        // $getDomTarget.hide();
                        $form.hide();
                        $getDomFormAction.hide();
                        $getActionGroupParent.show();
                        break;
                    case 'submit':
                    	var getActionCB = $(this).data('actionCallback');
                    	getActionCB = getActionCB.split('|');
                    	var params = $.map(getActionCB, function(item, index){
                    		if(index>0) return item
                    	});
                    	getActionCB = getActionCB[0];
                    	window[getActionCB](params);
                    	break;
                }
            });


            // insert DOM
            $(containerID).empty().prepend(html);
            
            setCalenderFormat();

        }else{
           // if(json[metaKey].length < 1){
                $('#create_new').removeClass('hidden');
                return;
            //}
        }
    });


};
/**
 * 员工信息子模块公共添加方法
 */
 function commonSaveBefore($form){
		var fileID=$form.find(":file").attr("id");
		//console.log("fileID===="+fileID);
	    //文件上传
		var formObject=$form.serializeObject();
		//console.log("formObject="+formObject);
		
		formObject.personId=personId;
		
		//获得证书：证书电子版上传
	    if(tt=="certificate"){
	    	 $.ajaxFileUpload({
			      url: 'employee/certificateUploadFile', //用于文件上传的服务器端请求地址
			      secureuri: false, //是否需要安全协议，一般设置为false
			      fileElementId: [fileID ], //文件上传域的ID   
			      //fileElementId:  fileID , //文件上传域的ID   
			      async:false,
			      dataType:'text',
			      type:"POST",
			      success: function (data){  //服务器成功响应处理函数
			    	  //console.log(data);
			    	 if(data){
			    		 
			    		 if((data.realUrl&&data.success)||data.realUrl==""){
			    			 //console.log("upload success or noupload");
			    			 
			    			 formObject.filePath=data.realUrl;
			    			 
			    			 // console.log("formObject22="+JSON.stringify(formObject));
			    			  commonSave(formObject,tt);
			    		 }
			      	 }       		
			      } 
				});
	    	
	    } else{
	    	 commonSave(formObject);
	    }
		  
		 
	}

 function commonSave(formObject,tt){
		
		var jsonObject=JSON.stringify(formObject);
		//console.log("hehehehehe="+jsonObject);
		$.ajax({
			type:"post",
			url:addURL,
			contentType:"application/json",
			data:jsonObject,
			dataType:"json",
			success:function(data){
				//console.log("data"+data);
				if(data&&data.success){
					swal(data.msg, "", "success"); 
					
					//if(from=="PC"){//PC端处理
						
					 	 $(addFormSelector).remove();
						 $(initFormSelector).addClass("hidden");
						 containerID.html("");
						//重新渲染
						 templateFillData(templateID, containerID, listURL,delsURL,"",tt);
						
					/*}else{//手机端处理,填写下一项
						if(saveAction){
							//保存关闭
							location.replace("employees/wanchengluru");
						}else{
							//保存并跳转到下一项
							$("#myModal7").modal('hide');
						  	$("#myModal8").modal();
						}
						
					}*/
				    
				}else{
					swal(data.msg, "", "error"); 
				}
				
			}
			
		}); 
		  
		 
	}
 
 

/**
 * 公共修改保存方法条件处理
 */
function commonUpdateSaveBefore($form,metaKey,uploadFileType){
	
	var img=$form.find("img").attr("src");
	
	var formObject= $form.serializeObject() ;
	//console.log("formObject11="+JSON.stringify(formObject));
	var fileID=$form.find(":file").attr("id");//上传域ID
	//console.log("fileID===="+fileID);
    
	//获得证书：证书电子版上传
    if(uploadFileType=="certificate"){
    	if(img==""||img==undefined){
    		 $.ajaxFileUpload({
   		      url: 'employee/certificateUploadFile', //用于文件上传的服务器端请求地址
   		      secureuri: false, //是否需要安全协议，一般设置为false
   		      fileElementId: [fileID ], //文件上传域的ID   
   		      //fileElementId:  fileID , //文件上传域的ID   
   		      async:false,
   		      dataType:'text',
   		      type:"POST",
   		      success: function (data){  //服务器成功响应处理函数
   		    	  //console.log(data);
   		    	 if(data){
   		    		 
   		    		 if((data.realUrl&&data.success)||data.realUrl==""){
   		    			 console.log("upload success or noupload");
   		    			 
   		    			 delete formObject.myfiles;
   		    			 formObject.filePath=data.realUrl;
   		    			 
   		    			  //console.log("formObject22="+JSON.stringify(formObject));
   		    			  commonUpdateSave(formObject,metaKey,uploadFileType);
   		    		 }
   		      	 }       		
   		      } 
   			});
    	}else{
    		 commonUpdateSave(formObject,metaKey,uploadFileType);
    	}
    	
    } else{
    	commonUpdateSave(formObject);
    }
    
	
}
/**
 * 公共修改保存方法，最终保存方法
 */
function commonUpdateSave(formObject,metaKey,imagePreview,uploadFileType){
	
	//var formObject= $form.serializeObject() ;
	
	var jsonObject=JSON.stringify(formObject);
	//console.log(jsonObject);
	$.ajax({
		type:"post",
		url:updateURL,
		data:jsonObject,
		dataType:"json",
		contentType:"application/json",
		success:function(data){
			if(data&&data.success){
				 swal(data.msg, "", "success"); 
			 	 $(addFormSelector).remove();
				
				 containerID.html("");
				//重新渲染
				 templateFillData(templateID, containerID, listURL,delsURL,metaKey,imagePreview,uploadFileType);
			}else{
				swal(data.msg, "", "error"); 
			}
			
		}
	}); 
	
}
/**
 * 公共删除方法
 */
function commonDelete(delUrl,metaKey,uploadFileType){
	$.post(delUrl,function(data){
		 
		if(data&&data.success){
			//console.log("data="+data);
			 swal(data.msg, "", "success"); 
		 	 $(addFormSelector).remove();
			 containerID.html("");
			//重新渲染
			 templateFillData(templateID, containerID, listURL,delsURL,metaKey,uploadFileType);
		}else{
			swal(data.msg, "", "error"); 
		}
	},"json" );
}