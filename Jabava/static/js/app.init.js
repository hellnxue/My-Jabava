// app.init.js
+function($){
	'use strict';

	// init datepicker
	$('[data-provide=datepicker]').datepicker({
		autoclose: true,
		language: 'zh-CN'
	})
}(jQuery);