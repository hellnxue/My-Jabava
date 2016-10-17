// app.jabava.js
;(function($){
	'use strict';

	var jabava = window.jabava = window.jabava || {};
	var _jb = {};

	_jb.util.unique = function(timestamp){

		if(timestamp) return (new Date()).getTime();

		return (Math.random().toString().slice(2)) * 1;

	};



	window.jabava = _jb;

})(jQuery);