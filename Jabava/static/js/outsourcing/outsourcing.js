
$(function(){
		
	// 申请开通
	  $('#addOutsourcing').click(function () {
		  
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
	 
 		if( $("#contactEmp").val()=='') {			  
			  alert("请输入联系人");
			  return false;
		 }else if($("#contactEmp").val().length>50){
			 alert("不能超过五十个字符");
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
						alert('新增成功 !');
						window.location.href ="client/list_protocol_query";

					}else{
						alert('新增失败!');
					}
					$('#addOutsourcing').attr('disabled',"false");
				}
			});
	  })

	  
	
});


