//empBatchAddUpload.js
;(function($){
	'use strict'; 
	
	var baseErrorList=null;
	var baseUpdateList=null;
	var posErrorList=null;
	var posUpdateList=null;
	
    
    $('[data-action]').on('shown.bs.tab', function(e){
        var getActType = $(this).data('action');
        if(getActType=="personal"){
        	if(baseUpdateList!=null){
        		$('.empBatchUpdateTrueMsg').text(baseUpdateList.length);
        	}
        	if(baseErrorList!=null){
        		$('.empBatchUpdatefalseMsg').text(baseErrorList.length);
        	}
        }
        if(getActType=="job"){ 
        	if(posUpdateList!=null){
        		$('.empBatchUpdateTrueMsg').text(posUpdateList.length);
        	}
        	if(posErrorList!=null){
        		$('.empBatchUpdatefalseMsg').text(posErrorList.length);
        	}
        }
    });
    
    $.ajax({
		url:  "employee/getUpdateInfos",
		type: 'POST',
		cache: false,
		dataType: 'json',
		processData: false,
		contentType: false
	}).done(function(result){
		if(result!=null && result.success){
			baseErrorList=result.basError;
			baseUpdateList=result.basUpdate;
			posErrorList=result.posError;
			posUpdateList=result.posUpdate;
			
			if(baseErrorList.length >0 || posErrorList.length >0){
				$('#empBatchUpdateImport').prop("disabled",true);
				$('#empBatchUpdateImport').removeClass('btn-info');
				$('#empBatchUpdateImport').css("background-color","#DCDCDC");
			}
			$('#empUpdateFileName').text(result.fileName);
			if(baseUpdateList!=null){
				//个人信息导入正确数据
				basePersonGrid(baseUpdateList);
				$('.empBatchUpdateTrueMsg').text(baseUpdateList.length);
				
			}
			if(baseErrorList!=null){
				//错误个人信息
				baseErrorPersonGrid(baseErrorList);
				$('.empBatchUpdatefalseMsg').text(baseErrorList.length);
			}
			
			//工作信息导入正确信息
			if(posUpdateList!=null){
				postPersonGrid(posUpdateList);
			}
			
			//错误工作信息
			if(posErrorList!=null){
				postErrorPersonGrid(posErrorList);
			}
		}
	}).fail(function(data){
		swal({
            title: '批量导入失败',
            type: 'error'
        });
	});   
    
    var basePersonGrid=function(data){
    	$('#basePersonUpdateInfo').DataTable({
			dom: "<'row'<'col-sm-12 table-responsive'tr>>",
	        data: data,
	        bSort: false, 
	        paging:false,
	        columns: [
	            { data: "index" },
	            { data: "jobNumber" },
	            { data: "employeeName" },
	            { data: "sex",
	              render:function(data){
                		 if(data!=null){
                			 if(data=='1'){
                				 return '男';
                			 }else{
                				 return '女';
                			 }
                		 }else{
							 return  "";
						 }
                	 }
	            },
	            { data: "isForeign",
	              render:function(data){
	            	  if(null!=data){
	            		  if(data=='1'){
	            			  return '是';
	            		  }else{
	            			  return '否';
	            		  }
	            	  }else{
						  return "";
					  }
	              }
	            },
	            { data: "firstjobDate",
	              render:function(data){
	            	  if(data!=null && data!=''){
	            		  return data.replace("00:00:00","").trim();
	            	  }
	              }
	            },
	            { data: "certTypeName"},
	            { data: "certId" },
	            { data: "bankName" }, 
	            { data: "subbank" },
	            { data: "salaryCard" },
	            { data: "degreeName" },
	            { data: "educationName" },
	            { data: "studyAbroad",
	              render:function(data){
	            	  if(data!=null){
	            		  if(data=='1'){
	            			  return '是';
	            		  }else{
	            			  return '否';
	            		  }
	            	  }
	              }
	            },
	            { data: "registerLocation" }, 
	            { data: "registerType",
	              render:function(data){
	            	  var result='';
	            	  if(data!=null){
	            		  if(data=='1'){
	            			  result='本地城镇';
	            		  }else if(data=='2'){
	            			  result='本地农村';
	            		  }else if(data=='1'){
	            			  result='本地城镇';
	            		  }else if(data=='3'){
	            			  result='外地城镇';
	            		  }else if(data=='4'){
	            			  result='外地农村';
	            		  }
	            	  }
	            	  return result;
	              }
	            },
	            { data: "originPlace" },
	            { data: "nationality" },
	            { data: "mobile" },
	            { data: "phone" },
	            { data: "email" },
	            { data: "contactAddress" },
	            { data: "familyAddress" },
	            { data: "postCode" },
	            { data: "fileLocation" },
	            { data: "birthDate1"},
	            { data: "marital",
	              render:function(data){
	            	  var result='';
	            	  if(data!=null){
	            		  if(data=='1'){
	            			  result='未婚';
	            		  }else if(data=='2'){
	            			  result='已婚';
	            		  }
	            	  }
	            	  return result;
	              }
		         },
	            { data: "resume" }],
	       language:{
	                "zeroRecords": "暂无数据",
	                "infoEmpty": "暂无数据"
	    		   }
	    });
    };
    
    var postPersonGrid=function(data){
    	$('#postPersonUpdateInfo').DataTable({
			dom: "<'row'<'col-sm-12 table-responsive'tr>>",
	        bSort: false, 
	        paging:false,
	        data: data,
	        columns: [
	            { data: "index" },
	            { data: "jobNumber" },
	            { data: "employeeName" },
	            { data: "certId" },
	            { data: "organizationName"},
	            { data: "postName"},
	            { data: "status",
	              render:function(data){
	            	  var result='';
	            	  if(data!=null){
	            		if(data=='1'){
	            			result='在职';
	            		}else if(data=='2'){
	            			result='离职';
	            		}else if(data=='3'){
	            			result='停职';
	            		}else if(data=='4'){
	            			result='退休';
	            		}else if(data=='5'){
	            			result='再入职';
	            		}
	            	}
	            	  return result;
	              }
	            },
	            { data: "entryDate",
	              render:function(data){
	            	  if(data!=null && data!=''){
	            		  return data.replace("00:00:00","").trim();
	            	  }
	              }	
	            },
	            { data: "workPost",
	              render:function(data){
	            	  var result='';
	            	  if(data!=null){
	            		  if(data=='1'){
	            			  result='入职';
	            		  }else if(data=='2'){
	            			  result='转正';
	            		  }else if(data=='3'){
	            			  result='调配';
	            		  }else if(data=='4'){
	            			  result='离职';
	            		  }
		            	}
	            	  return result;
	              }
	            },
	            { data: "positiveDate",
	              render:function(data){
	            	  if(data!=null && data!=''){
	            		  return data.replace("00:00:00","").trim();
	            	  }
	              }	
	            },
	            { data: "employeeTypeName"},
	            { data: "rankName"},
	            { data: "keyPerson",
	              render:function(data){
	            	  var result='';
	            	  if(data!=null){
	            		  if(data=='1'){
	            			  result='是';
	            		  }else if(data=='0'){
	            			  result='否';
	            		  }
	            	  }
	            	  return result;
	              }
	            },
	            { data: "reportUser"},
	            { data: "workName"},
	            { data: "payName"}, 
	            { data: "securityName"},
	            { data: "isPayrollFlag",
	              render:function(data){
	            	  var result='';
	            	  if(data!=null){
	            		  if(data=='1'){
	            			  result='停发';
	            		  }else if(data=='0'){
	            			  result='未停发';
	            		  }
	            	  }
	            	  return result;
	              }
	            }, 
	            { data: "teamName"}],
	      language:{
	         "zeroRecords": "暂无数据",
	         "infoEmpty": "暂无数据"
	       }
	    });
    };
    
    var baseErrorPersonGrid=function(data){
    	$('#baseErrorPersonUpdateDet').DataTable({
			dom: "<'row'<'col-sm-12 table-responsive'tr>>",
	        data: data,
	        bSort: false, 
	        paging:false,
	        columns: [
	            { data: "index" },
	            { data: "jobNumber" },
	            { data: "errorMsg"},
	            { data: "employeeName" },
	            { data: "sex",
	              render:function(data){
                		 if(data!=null){
                			 if(data=='1'){
                				 return '男';
                			 }else{
                				 return '女';
                			 }
                		 }else{
							 return "";
						 }
                	 }
	            },
	            { data: "isForeign",
	              render:function(data){
	            	  if(data!=null){
	            		  if(data=='1'){
	            			  return '是';
	            		  }else{
	            			  return '否';
	            		  }
	            	  }else{
						  return "";
					  }
	              }
	            },
	            { data: "firstjobDate",
	              render:function(data){
	            	  if(data!=null && data!=''){
	            		  return data.replace("00:00:00","").trim();
	            	  }
	              }
	            },
	            { data: "certTypeName"},
	            { data: "certId" },
	            { data: "bankName" }, 
	            { data: "subbank" },
	            { data: "salaryCard" },
	            { data: "degreeName" },
	            { data: "educationName" },
	            { data: "studyAbroad",
	              render:function(data){
	            	  if(data!=null){
	            		  if(data=='1'){
	            			  return '是';
	            		  }else{
	            			  return '否';
	            		  }
	            	  }
	              }
	            },
	            { data: "registerLocation" }, 
	            { data: "registerType",
	              render:function(data){
	            	  var result='';
	            	  if(data!=null){
	            		  if(data=='1'){
	            			  result='本地城镇';
	            		  }else if(data=='2'){
	            			  result='本地农村';
	            		  }else if(data=='1'){
	            			  result='本地城镇';
	            		  }else if(data=='3'){
	            			  result='外地城镇';
	            		  }else if(data=='4'){
	            			  result='外地农村';
	            		  }
	            	  }
	            	  return result;
	              }
	            },
	            { data: "originPlace" },
	            { data: "nationality" },
	            { data: "mobile" },
	            { data: "phone" },
	            { data: "email" },
	            { data: "contactAddress" },
	            { data: "familyAddress" },
	            { data: "postCode" },
	            { data: "fileLocation" },
	            { data: "birthDate1"},
	            { data: "marital",
	              render:function(data){
	            	  var result='';
	            	  if(data!=null){
	            		  if(data=='1'){
	            			  result='未婚';
	            		  }else if(data=='2'){
	            			  result='已婚';
	            		  }
	            	  }
	            	  return result;
	              }
		         },
	            { data: "resume" }],
	       language:{
	   	      "zeroRecords": "暂无数据",
	   	      "infoEmpty": "暂无数据"
	   	   }
	    });
    };
    
    var postErrorPersonGrid=function(data){
    	$('#postErrorPersonUpdateDet').DataTable({
    		dom: "<'row'<'col-sm-12 table-responsive'tr>>",
	        bSort: false, 
	        paging:false,
	        data: data,
	        columns: [
	            { data: "index" },
	            { data: "jobNumber" },
	            { data: "errorMsg"},
	            { data: "employeeName" },
	            { data: "certId" },
	            { data: "organizationName"},
	            { data: "postName"},
	            { data: "status",
	              render:function(data){
	            	  var result='';
	            	  if(data!=null){
	            		if(data=='1'){
	            			result='在职';
	            		}else if(data=='2'){
	            			result='离职';
	            		}else if(data=='3'){
	            			result='停职';
	            		}else if(data=='4'){
	            			result='退休';
	            		}else if(data=='5'){
	            			result='再入职';
	            		}
	            	}
	            	  return result;
	              }
	            },
	            { data: "entryDate",
	              render:function(data){
	            	  if(data!=null && data!=''){
	            		  return data.replace("00:00:00","").trim();
	            	  }
	              }	
	            },
	            { data: "workPost",
	              render:function(data){
	            	  var result='';
	            	  if(data!=null){
	            		  if(data=='1'){
	            			  result='入职';
	            		  }else if(data=='2'){
	            			  result='转正';
	            		  }else if(data=='3'){
	            			  result='调配';
	            		  }else if(data=='4'){
	            			  result='离职';
	            		  }
		            	}
	            	  return result;
	              }
	            },
	            { data: "positiveDate",
	              render:function(data){
	            	  if(data!=null && data!=''){
	            		  return data.replace("00:00:00","").trim();
	            	  }
	              }	
	            },
	            { data: "employeeTypeName"},
	            { data: "rankName"},
	            { data: "keyPerson",
	              render:function(data){
	            	  var result='';
	            	  if(data!=null){
	            		  if(data=='1'){
	            			  result='是';
	            		  }else if(data=='0'){
	            			  result='否';
	            		  }
	            	  }
	            	  return result;
	              }
	            },
	            { data: "reportUser"},
	            { data: "workName"},
	            { data: "payName"}, 
	            { data: "securityName"},
	            { data: "isPayrollFlag",
	              render:function(data){
	            	  var result='';
	            	  if(data!=null){
	            		  if(data=='1'){
	            			  result='停发';
	            		  }else if(data=='0'){
	            			  result='未停发';
	            		  }
	            	  }
	            	  return result;
	              }
	            }, 
	            { data: "teamName"}],
	      language:{
	         "zeroRecords": "暂无数据",
	         "infoEmpty": "暂无数据"
	       }
	    });
    };
    
    
    
    $('#empBatchUpdateImport').on('click',function(){
    	if(baseErrorList.length==0 && posErrorList.length==0){
    		$.ajax({
    			type : 'post',
    	        url : "employee/importBaseInfo",
    	        data : {"type":"update"},
    	        async : false,
    	        dataType: "json",
    	        success : function(result){
    	        	if(result.success){
    	        		window.location.href="employee/empImportSuccess?total="+result.msg+"&type=update";
    	            }else{
    	            	swal({
        	                title: '导入失败',
        	                text: result.msg,
        	                type: 'error'
        	            });
    	            }
    	        },
    	        error:function(data){
    	        	swal({
    	                title: '导入失败',
    	                text: "导入数据超时",
    	                type: 'error'
    	            });
    	        }
    	   });

    	}else{
    		swal({
                title: '导入失败',
                text: "导入的文件内容有误,请修改后重新导入",
                type: 'error'
            });
    	}
    });
    
})(jQuery);