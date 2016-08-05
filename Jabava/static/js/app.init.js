// app.init.js
+function($){
	'use strict';
	console.log( $('[data-provide=datepicker]').length )
	// init datepicker
	$('[data-provide=datepicker]').datepicker({
		autoclose: true,
		language: 'zh-CN'
	})
}(jQuery);