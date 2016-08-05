 function PredefinedComponent() {
     /** HashMap **/
     var _ComponentMap = new HashMap();   

     var str = '';
     
     //文本框
     str = "<div class='form-group'>" + 
	           "<label class='control-label col-xs-2' for='comp_%englishName%'>%chineseName%</label>" + 
	           "<div class='col-xs-10'><input class='form-control' id='comp_%englishName%' name='%englishName%'></div>" + 
           "</div>";
     _ComponentMap.put("Text", str);
     
     //Textarea
     str = "<div class='form-group'>" + 
			   "<label class='control-label col-xs-2' for='comp_%englishName%'>%chineseName%</label>" + 
			   "<div class='col-xs-10'><textarea class='form-control' id='comp_%englishName%' name='%englishName%'></textarea></div>" + 
		   "</div>";	
     _ComponentMap.put("Textarea", str);
     
     //FirstLevelOrgSelect
     str = "<div class='form-group'>" + 
		 	   "<label class='control-label col-xs-2' for='comp_%englishName%'>%chineseName%</label>" + 
	     	   "<div class='col-xs-10'><select class='form-control' id='comp_%englishName%' name='%englishName%' fetchUrl='system/listFirstLevel?withTop=1' valueProperty='organizationId' textProperty='organizationName'></select></div>" +
     	   "</div>";
     _ComponentMap.put("FirstLevelOrgSelect", str);
     
     //SalaryUsageSelect
     str = "<div class='form-group'>" + 
		 	   "<label class='control-label col-xs-2' for='comp_%englishName%'>%chineseName%</label>" + 
	     	   "<div class='col-xs-10'><select class='form-control' id='comp_%englishName%' name='%englishName%' fetchUrl='system/listBaseDataByType?baseDataType=4' valueProperty='baseDataId' textProperty='baseDataName'></select></div>" +
     	   "</div>";
     _ComponentMap.put("SalaryUsageSelect", str);
     
     //SocialSecurityAccountSelect
     str = "<div class='form-group'>" + 
		 	   "<label class='control-label col-xs-2' for='comp_%englishName%'>%chineseName%</label>" + 
	     	   "<div class='col-xs-10'><select class='form-control' id='comp_%englishName%' name='%englishName%' fetchUrl='socialsecurityaccount/listSocialSecurityAccount' valueProperty='socialSecurityAccountId' textProperty='socialSecurityAccountName'></select></div>" +
     	   "</div>";
     _ComponentMap.put("SocialSecurityAccountSelect", str);
     
     //AccumulationFundAccountSelect
     str = "<div class='form-group'>" + 
		 	   "<label class='control-label col-xs-2' for='comp_%englishName%'>%chineseName%</label>" + 
	     	   "<div class='col-xs-10'><select class='form-control' id='comp_%englishName%' name='%englishName%' fetchUrl='accumulationfundaccount/listAccumulationFundAccount' valueProperty='accumulationFundAccountId' textProperty='accumulationFundAccountName'></select></div>" +
     	   "</div>";
     _ComponentMap.put("AccumulationFundAccountSelect", str);
     
     //Date
     str = "<div class='form-group'>" + 
	           "<label class='control-label col-xs-2' for='comp_%englishName%'>%chineseName%</label>" + 
	           "<div class='col-xs-10'>" + 
	               "<div class='input-group date common-date'>" + 
	                   "<input type='text' class='form-control' id='comp_%englishName%' name='%englishName%' readonly='readonly'>" + 
	                   "<span class='input-group-addon'><i class='glyphicon glyphicon-th'></i></span>" + 
	               "</div>" + 
	           "</div>" + 
           "</div>";
     _ComponentMap.put("Date", str);
     
     //Month
     str = "<div class='form-group'>" + 
	           "<label class='control-label col-xs-2' for='comp_%englishName%'>%chineseName%</label>" + 
	           "<div class='col-xs-10'>" + 
	               "<div class='input-group date only-month'>" + 
	                   "<input type='text' class='form-control' id='comp_%englishName%' name='%englishName%' readonly='readonly'>" + 
	                   "<span class='input-group-addon'><i class='glyphicon glyphicon-th'></i></span>" + 
	               "</div>" + 
	           "</div>" + 
           "</div>";
     _ComponentMap.put("Month", str);
     
     this.append = function (parent, key, englishName, chineseName) {
    	 var comp = this.get(key, englishName, chineseName);
    	 if(comp){
    		 parent.append(comp);
    		 
    		 //初始化DynamicSelect
    		 var ele = $('#comp_' + englishName);
    		 if(ele.attr('fetchUrl')){
    			 //Ajax获取数据并初始化select
    			 this.initDynamicSelect(ele);
    		 }
    	 }
     };
     
     this.initDynamicSelect = function (ele) {
    	 $.ajax({
            url : ele.attr('fetchUrl'),
            data : {},
            dataType:'json',
            type : 'post',
            success : function(message) {
                if(message){
                	$.each(message,function(){console.log(this[ele.attr("textProperty")]);
                		ele.append($("<option value='" + this[ele.attr("valueProperty")] + "'>" + this[ele.attr("textProperty")] + "</option>"));
                	});
                }
            }
        });
     };
     
     /** 取 **/
     this.get = function (key, englishName, chineseName) {
         var comp = _ComponentMap.get(key);
         if(comp && englishName && chineseName){
        	 comp = comp.replace(/%englishName%/g, englishName);
        	 comp = comp.replace(/%chineseName%/g, chineseName);
         }
    	 return comp;
     };
        
     /** 是否包含 Key **/
     this.containsKey = function ( key ) {
         return _ComponentMap.containsKey(key);
     };
 }
 
 function getInputTypeByCode(code){
	 switch(code){
		case '001':
			return 'Text';
		case '002':
			return 'TextArea';
		case '003':
			return 'Date';
		case '004':
			return 'Month';
		case '005':
			return 'FirstLevelOrgSelect';
		case '006':
			return 'SalaryUsageSelect';
		case '007':
			return 'SocialSecurityAccountSelect';
		case '008':
			return 'AccumulationFundAccountSelect';
	}
 }
 
 function getInputTypeNameByCode(code){
	 switch(code){
		case '001':
			return 'Text-文本框';
		case '002':
			return 'TextArea-文本域';
		case '003':
			return 'Date-日期';
		case '004':
			return 'Month-月份';
		case '005':
			return 'FirstLevelOrgSelect-一级部门选择框';
		case '006':
			return 'SalaryUsageSelect-工资用途选择框';
		case '007':
			return 'SocialSecurityAccountSelect-社保账户选择框';
		case '008':
			return 'AccumulationFundAccountSelect-公积金账户选择框';
	}
 }