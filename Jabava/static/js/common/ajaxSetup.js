(function($){
//function setupAjax(){
	console.log("Ajax setup ......");
	$.ajaxSetup({
		error:function(xhr, textStatus, errorThrown){
            if((xhr.status == 0 && this.url) || (textStatus == "parsererror")){
            	window.location.reload();
            }else{
            	alert("出错了");
            }
		},
	    dataFilter : function(data, type){
	    	console.log("data:" + data);
	        if(data.indexOf("AjaxAuthFailed") != -1){
	        //if(data == "AjaxAuthFailed"){
	        	window.location.reload();
	        	//return false;
	        }else if(data.indexOf("NoPermission") != -1){
	           	window.location.href = "common/noPermission";
	           	//return false;
	        }else if(data.indexOf("OperationException") == 0){
	           	alert(data.substring(18));
	           	return '{"success":false, "data":[], "length":10, "start":0, "recordsTotal":0, "recordsFiltered":0}';
	        }else{
	            return data;
	        }
	    }
	    //complete : function(XMLHttpRequest, textStatus) {
	    //    var sessionstatus = XMLHttpRequest.getResponseHeader("sessionstatus");
	    //    if (sessionstatus == "timeout") {
	    //        window.location.replace("login.html");
	    //    }
	    //}
	});
//}

//setupAjax();
})(jQuery)
