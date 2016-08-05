// common.js
// @creatBy junyi.liu
// @creatAt 2016-01-20
var addFormSelector="[name=add_new_form]";	//复制后用于新添加的表单
var initFormSelector="#create_new";			//用于复制添加表单的空模板

//基础数据变量定义-用于接收查询列表中的基础数据列表
var baseDataEducationList=null;//学历
var baseDataDegreeList=null;//学位

/*
 * 模板渲染 & 修改
* @param templateID     {String} 模板ID
* @param containerID    {jQuery Object} 渲染ID
* @param dataUrl        {String} 列表查询URL
* @param delUrl        {String}  删除URL
* @param metaKey        {String} 列表查询JSON关键字
* @param......自定义参数
* @param baseDataTypeObj        {Array} 基础数据类型-用于初始化添加表单模板的基础数据列表
 */

var templateFillData = function(templateID, containerID, dataUrl, delUrl, metaKey,uploadFileType,baseDataTypeObj){
    if(uploadFileType){
        tt=uploadFileType;
    }else{
        tt="";
    }

    $.getJSON(dataUrl, function(json, textStatus){
        if(json&&textStatus =='success'){

            //基础数据部分
            if(baseDataTypeObj&&baseDataTypeObj.length>0){
                initBaseDataOptions(json,baseDataTypeObj);
            }

            metaKey = metaKey || 'list';
            // console.log("metaKey=="+metaKey);
            if( json[metaKey] && json[metaKey].length < 1){//获取的list为空，默认复制一个添加表单
                    var getBaseForm = $('[data-form-type="base"]'),
                        strHtml = '',
                        content = $('[data-form-target="content"]');
                        strHtml = getBaseForm.clone(true)
                                    .removeAttr( 'data-form-type')
                                    .removeClass("hidden")
                                    .addClass("jianju borders")
                                    .removeAttr( "style id")
                                    .attr("name", "add_new_form");
                        $(strHtml).find(":submit").off();//取消模板复制后的保存按钮click事件
                        content.append(strHtml);
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
                        
                        var $form = $(strHtml);
                        $form.find('[data-action-motive="cancel"],[data-action-motive="remove"]')
                        .on('click', function(event) {
                            event.preventDefault();
                            $form.hide().remove();
                        });



                    $form
                    .formValidation(validateOptions)
                    .on('success.form.fv', function(e){
                        e.preventDefault();
                        commonSaveBefore($form);
                    });

                window.renderReady = true;
                return;
            }
            
           

            var html = template(templateID, json);
           
                html = $(html);
           //表单保存以及验证  
           var $forms=html.find("button").parents("form").not("[id=create_new]");
           $forms.each(function(){
            $(this).formValidation(validateOptions)
            .on('success.form.fv', function(e){
                e.preventDefault();
                commonUpdateSaveBefore($(this),metaKey,uploadFileType);
            });

        });
            // form init
            var  getDomStaticTarget = html.find('.input-group-static'),
                 getDomLabel = getDomStaticTarget.parent().siblings(),
                 getDomForm = getDomStaticTarget.parents('form'),
                 getDomTarget = getDomStaticTarget.siblings(),
                 getDomFormAction = html.find('.form-action');
            
            getDomForm.hide();
            getDomStaticTarget.hide();
            // getDomLabel.hide();
            getDomTarget.hide();
            getDomFormAction.hide();
            
            if(uploadFileType=="certificate"){
                imagePreview(html.find("img"));//图片放大
            }

            // @eventBind {action} .action-group
            html.find('.action-group [data-action-motive]').on('click', function(evt){
                var getAction = $(this).data('actionMotive'),
                    getActionId = $(this).attr('data-action-id'),
                    $form = $('[data-form='+getActionId+']'),
                    $getDomStaticTarget = $form.find('.input-group-static'),

                    $editableTarget = $getDomStaticTarget.next(),

                    $getDomTarget = $getDomStaticTarget.siblings().not('small,i'),
                    $getDomLabel = $form.find('label'),
                    $getDomFormAction = $form.find('.form-action');

                switch(getAction){
                    case 'del':
                        getActionId = $(this).data('actionId');

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
                        var oCancelPicture = $('[data-action-motive="hideOrshow"]').find('i');
                        if(oCancelPicture.hasClass('pe-7s-angle-up-circle')){
                            oCancelPicture
                            .removeClass('pe-7s-angle-up-circle')
                            .addClass('pe-7s-angle-down-circle');
                        }
                        
                        $form.show();
                        $getDomTarget.show();
                        $getDomFormAction.show();
                        $getDomStaticTarget.hide();
                        $getDomLabel.show();
                        break;
                    case 'hideOrshow':
                        var oCancelPicture = $(this).find('i');
                        //$form.formValidation('resetForm');
                       // $form.formValidation('destroy');
//                        $getDomTarget.hide();
//                        $getDomFormAction.hide();
                        if(oCancelPicture.hasClass('pe-7s-angle-down-circle')){
                            
                            oCancelPicture
                            .removeClass('pe-7s-angle-down-circle')
                            .addClass('pe-7s-angle-up-circle');
                            $getDomStaticTarget.show();
                            // $getDomLabel.show();
                            $editableTarget.hide();
                            $form.show();
                        }
                        else {
                            oCancelPicture
                            .addClass('pe-7s-angle-down-circle')
                            .removeClass('pe-7s-angle-up-circle');
                             $getDomStaticTarget.hide();
                             // $getDomLabel.hide();
                             $editableTarget.show();
                            $form.hide();
                        }
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
                    $getDomStaticTarget = $form.find('.input-group-static'),
                    $getDomTarget = $getDomStaticTarget.siblings().not('small,i'),
                    $getDomFormAction = $form.find('.form-action');
                var getAction = $(this).data('actionMotive');
                
              
                //修改表单部分
                switch(getAction){
                    
                   /* case 'save':
                    	alert("save");
                    	  $form.formValidation(validateOptions)
                          .on('success.form.fv', function(e){
                              e.preventDefault();
                              commonUpdateSaveBefore($form,metaKey,uploadFileType);
                          });
                    	  break;*/
                    case 'cancel':
                        var oCancelPicture = $('[data-action-motive="hideOrshow"]').find('i');
                        if(oCancelPicture.hasClass('pe-7s-angle-down-circle')){
                            oCancelPicture
                            .removeClass('pe-7s-angle-down-circle')
                            .addClass('pe-7s-angle-up-circle');
                        };
                        /*$form.formValidation('resetForm');
                        $form.formValidation('destroy');*/
                        $getDomStaticTarget.show();
                        $getDomTarget.hide();
                        $getDomFormAction.hide();
                        break;
                    case 'submit':
                    	var getActionCB = $(this).data('actionCallback');
                    	getActionCB = getActionCB.split('|');
                    	var params = $.map(getActionCB, function(item, index){
                    		if(index>0) return item
                    	});
                        params.unshift($(this).parents('form'))
                    	getActionCB = getActionCB[0];
                    	window[getActionCB](params);
                    	break;
                }
            });


            // insert DOM
            $(containerID).empty().prepend(html);
            $('[data-toggle="select2"]')
            .not('[data-form-type="base"] [data-toggle="select2"]').select2();

            $('[data-toggle="datepicker"]')
            .not('[data-form-type="base"] [data-toggle="datepicker"]')
            .not('[data-form-target="content"] [data-toggle="datepicker"]')
            .datepicker({
                format: "yyyy-mm-dd",
                autoclose: true
              })
            .on('changeDate', function(e){
                  var getEleName = $(e.target).find(':text').attr('name');
                  $('[data-form-validator="validator"]').formValidation('revalidateField', getEleName);
              });
            // setCalenderFormat();
            window.renderReady = true;

        }else{
           // if(json[metaKey].length < 1){
                $('#create_new').removeClass('hidden');
                return;
            //}
        }
    });


};

/*artTemplate模板辅助方法*/
template.helper('dateFormat', function(date){

    date = getHandleDateYMD(date);

    return date;
});

var tt="";//文件上传标识
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
			    			  commonSave(formObject,tt );
			    		 }
			      	 }       		
			      } 
				});
	    } else{
	    	 commonSave(formObject, null );
	    }
		  
		 
	}

 function commonSave(formObject, tt){
	 
		var jsonObject=JSON.stringify(formObject);
		$.ajax({
			type:"post",
			url:addURL,
			contentType:"application/json",
			data: jsonObject,
			dataType:"json",
			success:function(data){
				//console.log("data"+data);
				if(data&&data.success){
					
					 swal({
                        title: data.msg,
                        type: 'success'}, function(){
                            $(addFormSelector).remove();
                            $(initFormSelector).addClass("hidden");
                            containerID.html("");
                            location.reload();
                            //重新渲染
                            templateFillData(templateID, containerID, listURL,delsURL,"",tt);
                            
                        }); 
				    
				}else{
					swal('添加失败！', "", "error"); 
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
				 swal({
                    title: data.msg,
                    type: 'success'}, function(){
                        $(addFormSelector).remove();

                        containerID.html("");
                        //重新渲染
                        //templateFillData(templateID, containerID, listURL,delsURL,metaKey,imagePreview,uploadFileType);
                        location.reload();
                        
                    }); 
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
			 swal({
                title: data.msg,
                type: 'success'
             },
             function(){
                addFormSelector ? $(addFormSelector).remove():null;
                location.reload();
                containerID.html("");
                //重新渲染
                templateFillData(templateID, containerID, listURL,delsURL,metaKey,uploadFileType);
             }
            ); 
		}else{
			swal(data.msg, "", "error"); 
		}
	},"json" );
}