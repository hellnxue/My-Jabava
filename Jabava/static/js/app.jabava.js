// app.jabava.js
+function($){
	'use strict';

	var jabava = window.jabava = window.jabava || {};
	var _jb = {}

	_jb.settings = {
		path : 'static/js/',
		pluginPath : 'static/bootstrap/vendor/$pluginName$/',
		suffix : '.js',
		jsSuffix : '.min.js',
		cssSuffix : '.min.css'
	}

	_jb.packages = {
		'init' : 'app.init'
	}

	_jb.plugins = {
		'datepicker' : {
			'aka' : 'bootstrap-datepicker-master',
			'js' : 'dist/js/bootstrap-datepicker',
			'local' : 'dist/locales/bootstrap-datepicker.zh-CN',
			'css' : 'dist/css/bootstrap-datepicker3'
		}		
	}

	_jb.unique = function(timestamp){

		if(timestamp) return (new Date()).getTime()

		return (Math.random().toString().slice(2)) * 1

	}
	jabava.getPackageURI = function( packageName ){

		return _jb.settings.path + _jb.packages[packageName] + _jb.settings.suffix 

	}

	jabava.getPluginURI = function( pluginName ){

	}

	jabava.getPlugin = function( pluginName ){
		var getAka = _jb.plugins[pluginName]['aka'] || pluginName
		var getURI = _jb.settings.pluginPath.replace('$pluginName$', getAka)
		var realURI = ''

		if( _jb.plugins[pluginName]['js']  ){
			realURI = getURI + _jb.plugins[pluginName]['js'] + _jb.settings.jsSuffix + '?_t=' +_jb.unique()
			$('body').append( $('<script src="'+ realURI +'"></script>') )
		}
		if( _jb.plugins[pluginName]['local']  ){
			realURI = getURI + _jb.plugins[pluginName]['local'] + _jb.settings.jsSuffix + '?_t=' +_jb.unique()
			$('body').append( $('<script src="'+ realURI +'"></script>') )
		}
		if( _jb.plugins[pluginName]['css']  ){
			realURI = getURI + _jb.plugins[pluginName]['css'] + _jb.settings.cssSuffix + '?_t=' +_jb.unique()
			$('head').append( $('<link rel="stylesheet" href="'+ realURI +'>') )
		}
		console.log( getURI )

	}

	return window.jabava = jabava

}(jQuery);