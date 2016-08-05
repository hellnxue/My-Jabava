/**
 * 封装常用工具功能
 *
 * @author zhiyang.wang@ezhiyang.com
 * @copyright ezhiyang.com 2016
 */
(function($){
	console.log("======= 这是得到的外部jquery功能 ======");
	console.log($);
	console.log($.now());

	/**
	 * 序列化表单数据
	 * TODO　扩展成新式写法
	 */
	jQuery.prototype.serializeObject=function(){  
		var obj=new Object();

		console.log("=========== array =========");
		console.log(this.serializeArray());

		$.each(this.serializeArray(),function(index,param){
			if(!(param.name in obj)){  
				obj[param.name]=param.value;  
			}
		});

		return obj;  
	};

})(jQuery);