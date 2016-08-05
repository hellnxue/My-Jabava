
$(function(){
		
	// 申请开通
	function addOutsourcing(){
		if( $("#telephoneNumber").val()=='') {			  
			  alert("请输入联系电话");
			  return false;
		 }else	if($("#telephoneNumber").val().length>20){
			 alert("电话号码不能超过五十个字符");
			  return false; 
		 }
		  if(isNaN($("#telephoneNumber").val())){
			  alert("电话号码只能是数字");
			  return false;
		  }
 		if( $("#addRemark").val()!='' && $("#contactEmp").val().length>500) {			  
			  alert("备注不能超过五百个字符");
			  return false;
		 }
 		$('#addOutsourcing').attr('disabled',"true");
 		
		  $.ajax({
				type : 'post',
				url : "outsourcing/addOutsourcing",
				data : $("#addOutsourcingForm").serialize(),
				async : false,
				dataType: "json",
				success : function(result) {
					if(result.success){
						swal({
							type: 'success',
							title: '新增成功!'
						},function(){
							if(result.isPerfected){
								window.location.href ="client/list_protocol_query";
							}else{
								window.location.href ="to_perfect";
							}
						})
					}else{
						swal({
							type: 'error',
							title: '新增失败!'
						})
					}
					$('#addOutsourcing').attr('disabled',"false");
				}
			});
	}
	
	//校验
	var validators = {
			framework: 'bootstrap',
		    err: {
		        container: 'tooltip'
		    },
		    icon: {
		        valid: 'glyphicon glyphicon-ok',
		        invalid: 'glyphicon glyphicon-remove',
		        validating: 'glyphicon glyphicon-refresh'
		    },
		    locale: 'zh_CN',
		    fields: {
		    	contactEmp: {
		    		validators: {
		    			notEmpty: {
		                    message: '请填写必填项目'
		                },
		                stringLength: {
		                	message: '不能超过五十个字符',
		                	max: 50
		                }
		    		}
		    	},
		        telephoneNumber: {
		            validators: {
		                notEmpty: {
		                    message: '请填写必填项目'
		                },
		                phone: {
		                	message: '请输入有效的电话号码',
		                	country: 'CN'
		                }
		            }
		        }
		    }
		}

	$('#addOutsourcingForm')
	.formValidation(validators)
	.on('success.form.fv',function(e){
		e.preventDefault();
		addOutsourcing()
	})

});


