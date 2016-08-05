$.fn.extend({
	/**
	 * 序列化表单，元素内容
	 * @return {[type]} [description]
	 */
	serializeObject:function(){
		if(this.length > 1){
				return false;
		}

		var arr=this.serializeArray();
		
		var obj= {};
		$.each(arr,function(k,v){
			obj[v.name]=v.value;
		});

		return obj;
	}
});

console.log("===== common is running =======");