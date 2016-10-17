//empBatchAddUpload.js
;(function($){
	'use strict'; 
    
	var educationList=null;
	var eduErrorList=null;
	var experienceList=null;
	var expErrorList=null;
	var contractList=null;
	var conErrorList=null;
	
	 
    $('[data-action]').on('shown.bs.tab', function(e){
        var getActType = $(this).data('action');
        if(getActType=="education"){
        	$('.subPersonTrueNums').text(educationList.length);
			$('.subPersonFalseNums').text(eduErrorList.length);
        }
        if(getActType=="record"){ 
        	$('.subPersonTrueNums').text(experienceList.length);
			$('.subPersonFalseNums').text(expErrorList.length);
        }
        if(getActType=="contract"){ 
        	$('.subPersonTrueNums').text(contractList.length);
			$('.subPersonFalseNums').text(conErrorList.length);
        }
    });
	
    $.ajax({
		url:  "employee/getSubPersonInfo",
		type: 'POST',
		cache: false,
		dataType: 'json',
		processData: false,
		contentType: false
	}).done(function(result){
		if(result!=null && result.success){
			educationList=result.education.volid;
			eduErrorList=result.education.error;
			experienceList=result.experience.volid;
			expErrorList=result.experience.error;
			contractList=result.contract.volid;
			conErrorList=result.contract.error;
			
			if(eduErrorList.length>0 || expErrorList.length>0 || conErrorList>0){
				$('#subPersonInfImport').prop("disabled",true);
				$('#subPersonInfImport').removeClass('btn-info');
				$('#subPersonInfImport').css("background-color","#DCDCDC");
			}
			$('.subPersonInfoFiles').text(result.fileName);

			if(educationList!=null){
				$('.subPersonTrueNums').text(educationList.length);
				educationGrid($('#educationTrueList'),educationList);
			}
			if(eduErrorList!=null){
				$('.subPersonFalseNums').text(eduErrorList.length);
				educationErrorGrid($('#educationFalseList'),eduErrorList);
			}
			if(experienceList!=null){
				experienceGrid($('#experienceTrueList'),experienceList);
			}
			if(expErrorList!=null){
				experienceErrorGrid($('#experienceFalseList'),expErrorList);
			}
			if(contractList!=null){
				contractGrid($('#contractTrueList'),contractList);
			}
			if(conErrorList!=null){
				contractErrorGrid($('#contractFalseList'),conErrorList);
			}
		}
	}).fail(function(data){
		swal({
            title: '批量导入失败',
            text:data.msg,
            type: 'error'
        });
	});   
    
    var educationGrid=function(Obj,data){
    	Obj.DataTable({
			dom: "<'row'<'col-sm-12 table-responsive'tr>>",
	        data: data,
	        paging:false,
	        bSort: false, 
	        columns: [
	            { data: "index" },
	            { data: "jobNum" },
	            { data: "employee" },
	            { data: "idCard"},
	            { data: "entranceDate",
	              render:function(data){
	            	  if(data!=null && data!=''){
	            		  return data.replace("00:00:00","").trim();
	            	  }
	            	  return '';
	              }
	            },
	            { data: "graduateDate",
	              render:function(data){
	            	  if(data!=null && data!=''){
	            		  return data.replace("00:00:00","").trim();
	            	  }
					  return '';
		          }	
	            },
	            { data: "graduateSchool" },
	            { data: "education" }, 
	            { data: "major" },
	            { data: "degree" },
	            { data: "memo" }],
	       language:{
	                "zeroRecords": "暂无数据",
	                "infoEmpty": "暂无数据"
	    		   }
	    });
    };
    var educationErrorGrid=function(Obj,data){
    	Obj.DataTable({
			dom: "<'row'<'col-sm-12 table-responsive'tr>>",
	        data: data,
	        paging:false,
	        bSort: false, 
	        columns: [
	            { data: "index" },
	            { data: "jobNum" },
	            { data: "errorMsg"},
	            { data: "employee"},
	            { data: "idCard"},
	            { data: "entranceDate",
	              render:function(data){
	            	  if(data!=null && data!=''){
	            		  return data.replace("00:00:00","").trim();
	            	  }
					  return '';
	              }
	            },
	            { data: "graduateDate",
	              render:function(data){
	            	  if(data!=null && data!=''){
	            		  return data.replace("00:00:00","").trim();
	            	  }
					  return '';
		          }	
	            },
	            { data: "graduateSchool" },
	            { data: "education" }, 
	            { data: "major" },
	            { data: "degree" },
	            { data: "memo" }],
	       language:{
	                "zeroRecords": "暂无数据",
	                "infoEmpty": "暂无数据"
	    		   }
	    });
    };
    
    var experienceGrid=function(Obj,data){
    	Obj.DataTable({
			dom: "<'row'<'col-sm-12 table-responsive'tr>>",
	        data: data,
	        paging:false,
	        bSort: false, 
	        columns: [
	            { data: "index" },
	            { data: "jobNum" },
	            { data: "employee" },
	            { data: "idCard"},
	            { data: "startDate",
	              render:function(data){
	            	  if(data!=null && data!=''){
	            		  return data.replace("00:00:00","").trim();
	            	  }
					  return '';
	              }
	            },
	            { data: "endDate",
	              render:function(data){
	            	  if(data!=null && data!=''){
	            		  return data.replace("00:00:00","").trim();
	            	  }
					  return '';
		          }	
	            },
	            { data: "employer" },
	            { data: "workPost" }, 
	            { data: "description" },
	            { data: "authenticator" },
	            { data: "proveMobile" },
	            { data: "backgroundInvestigation" },
	            { data: "memo" }],
	       language:{
	                "zeroRecords": "暂无数据",
	                "infoEmpty": "暂无数据"
	    		   }
	    });
    };
    
    var experienceErrorGrid=function(Obj,data){
    	Obj.DataTable({
			dom: "<'row'<'col-sm-12 table-responsive'tr>>",
	        data: data,
	        paging:false,
	        bSort: false, 
	        columns: [
	            { data: "index" },
	            { data: "jobNum" },
	            { data: "errorMsg"},
	            { data: "employee" },
	            { data: "idCard"},
	            { data: "startDate",
	              render:function(data){
	            	  if(data!=null && data!=''){
	            		  return data.replace("00:00:00","").trim();
	            	  }
					  return '';
	              }
	            },
	            { data: "endDate",
	              render:function(data){
	            	  if(data!=null && data!=''){
	            		  return data.replace("00:00:00","").trim();
	            	  }
					  return '';
		          }	
	            },
	            { data: "employer" },
	            { data: "workPost" }, 
	            { data: "description" },
	            { data: "authenticator" },
	            { data: "proveMobile" },
	            { data: "backgroundInvestigation" },
	            { data: "memo" }],
	       language:{
	                "zeroRecords": "暂无数据",
	                "infoEmpty": "暂无数据"
	    		   }
	    });
    };
    var contractGrid=function(Obj,data){
    	Obj.DataTable({
			dom: "<'row'<'col-sm-12 table-responsive'tr>>",
	        data: data,
	        bSort: false, 
	        paging:false,
	        columns: [
	            { data: "index" },
	            { data: "jobNumber" },
	            { data: "employeeName" },
	            { data: "idCard"},
	            { data: "contractType",
	              render: function(data){
	            	  var result='';
	            	  if(data!=null && data!=''){
	            		  if(data=='1'){
	            			  result='劳动合同';
	            		  }else if(data=='2'){
	            			  result='劳务协议';
	            		  }else if(data=='3'){
	            			  result='实习协议'; 
	            		  }else if(data=='4'){
	            			  result='保密协议';
	            		  }else if(data=='5'){
	            			  result='竞业限制协议';
	            		  }else if(data=='6'){
	            			  result='培训协议';
	            		  }else if(data=='1'){
	            			  result='';
	            		  }
	            	  }
	            	  return result;
	              }
	            },
	            { data: "contractSignDate",
	              render:function(data){
	            	  if(data!=null && data!=''){
	            		  return data.replace("00:00:00","").trim();
	            	  }
					  return '';
	              }
	            },
	            { data: "contractBussinessType",
		              render: function(data){
		            	  var result='';
		            	  if(data!=null && data!=''){
		            		  if(data=='1'){
		            			  result='签订';
		            		  }else if(data=='2'){
		            			  result='续签';
		            		  }
		            	  }
		            	  return result;
		              }
		        },
		        { data: "contractMainUnit" },
		        { data: "contractDeadlineType",
		              render: function(data){
		            	  var result='';
		            	  if(data!=null && data!=''){
		            		  if(data=='1'){
		            			  result='固定期限';
		            		  }else if(data=='2'){
		            			  result='无固定期限';
		            		  }else if(data=='3'){
		            			  result='以完成一定工作任务为期限';
		            		  }
		            	  }
		            	  return result;
		              }
		        },
		        { data: "contractStartDate",
		              render:function(data){
		            	  if(data!=null && data!=''){
		            		  return data.replace("00:00:00","").trim();
		            	  }
						  return '';
			          }	
		        },
		        { data: "contractMonth" },
	            { data: "contractEndDate",
	              render:function(data){
	            	  if(data!=null && data!=''){
	            		  return data.replace("00:00:00","").trim();
	            	  }
					  return '';
		          }	
	            },
	            { data: "isTrial",
		              render: function(data){
		            	  var result='';
		            	  if(data!=null && data!=''){
		            		  if(data=='1'){
		            			  result='Y';
		            		  }else if(data=='0'){
		            			  result='N';
		            		  }
		            	  }
		            	  return result;
		              }
		        },
	            { data: "trialSalary" },
	            { data: "positiveSalary" }, 
	            { data: "continueTime" },
	            { data: "memo" }],
	       language:{
	                "zeroRecords": "暂无数据",
	                "infoEmpty": "暂无数据"
	    		   }
	    });
    };
    
    var contractErrorGrid=function(Obj,data){
    	Obj.DataTable({
			dom: "<'row'<'col-sm-12 table-responsive'tr>>",
	        data: data,
	        bSort: false,
	        paging:false,
	        columns: [
	            { data: "index" },
	            { data: "jobNumber" },
	            { data: "errorMsg"},
	            { data: "employeeName" },
	            { data: "idCard"},
	            { data: "contractType",
	              render: function(data){
	            	  var result='';
	            	  if(data!=null && data!=''){
	            		  if(data=='1'){
	            			  result='劳动合同';
	            		  }else if(data=='2'){
	            			  result='劳务协议';
	            		  }else if(data=='3'){
	            			  result='实习协议'; 
	            		  }else if(data=='4'){
	            			  result='保密协议';
	            		  }else if(data=='5'){
	            			  result='竞业限制协议';
	            		  }else if(data=='6'){
	            			  result='培训协议';
	            		  }else if(data=='1'){
	            			  result='';
	            		  }
	            	  }
	            	  return result;
	              }
	            },
	            { data: "contractSignDate",
	              render:function(data){
	            	  if(data!=null && data!=''){
	            		  return data.replace("00:00:00","").trim();
	            	  }
					  return '';
	              }
	            },
	            { data: "contractBussinessType",
		              render: function(data){
		            	  var result='';
		            	  if(data!=null && data!=''){
		            		  if(data=='1'){
		            			  result='签订';
		            		  }else if(data=='2'){
		            			  result='续签';
		            		  }
		            	  }
		            	  return result;
		              }
		        },
		        { data: "contractMainUnit" },
		        { data: "contractDeadlineType",
		              render: function(data){
		            	  var result='';
		            	  if(data!=null && data!=''){
		            		  if(data=='1'){
		            			  result='固定期限';
		            		  }else if(data=='2'){
		            			  result='无固定期限';
		            		  }else if(data=='3'){
		            			  result='以完成一定工作任务为期限';
		            		  }
		            	  }
		            	  return result;
		              }
		        },
		        { data: "contractStartDate",
		              render:function(data){
		            	  if(data!=null && data!=''){
		            		  return data.replace("00:00:00","").trim();
		            	  }
						  return '';
			          }	
		        },
		        { data: "contractMonth" },
	            { data: "contractEndDate",
	              render:function(data){
	            	  if(data!=null && data!=''){
	            		  return data.replace("00:00:00","").trim();
	            	  }
					  return '';
		          }	
	            },
	            { data: "isTrial",
		              render: function(data){
		            	  var result='';
		            	  if(data!=null && data!=''){
		            		  if(data=='1'){
		            			  result='Y';
		            		  }else if(data=='0'){
		            			  result='N';
		            		  }
		            	  }
		            	  return result;
		              }
		        },
	            { data: "trialSalary" },
	            { data: "positiveSalary" }, 
	            { data: "continueTime" },
	            { data: "memo" }],
	       language:{
	                "zeroRecords": "暂无数据",
	                "infoEmpty": "暂无数据"
	    		   }
	    });
    };
    $('#subPersonInfImport').on('click',function(){
    	if(eduErrorList.length==0 && expErrorList.length==0 && conErrorList.length==0 ){
    		 $.ajax({
    				url:  "employee/importSubInfo",
    				type: 'POST',
    				cache: false,
    				dataType: 'json',
    				processData: false,
    				contentType: false
    			}).done(function(result){
    				if(result.success){
    					window.location.href="employee/empImportSuccess?total="+result.msg+"&type=sub";
    				}else{
    					swal({
        	                title: '导入失败',
        	                text: result.msg,
        	                type: 'error'
        	            });
    				}
    			}).fail(function(data){
    				swal({
    	                title: '导入失败',
    	                text: "导入数据超时",
    	                type: 'error'
    	            });
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