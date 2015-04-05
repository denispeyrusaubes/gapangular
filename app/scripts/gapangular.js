
/**
 * gapangluar - v0.0.1 - 04/05/2015
 * https://github.com/denispeyrusaubes/endpoints
 * Copyright (c) 2015 Denis Peyrusaubes
 * License: TBD
 */
 (function (window, document) {
 	'use strict';	


 	function gapangular(els) {

 	}


 	function bootstrap(conf) {
 		var toResolve = {};

 		for (var i=0;i<conf.endpoints.length;i++){
 			var endpointname  = conf.endpoints[i].endpointname ;
 			var injectionname = conf.endpoints[i].injectionname || endpoints[i].endpointname ;
 			var version		  = conf.endpoints[i].version || conf.defaultVersion ;
 			var root		  = conf.endpoints[i].root || conf.defaultRoot ;

 			toResolve[injectionname] = ['$q', 
 			(function(a,b,c) {
 				return function ($q) {
 					var deferred = $q.defer();
 					gapi.client.load(a, b ,undefined,c)
 					.then(function() {
 						deferred.resolve(gapi.client[a]);
 					});
 					return deferred.promise;
 				}
 			})(endpointname,version,root) ];
 		}

 		window.deferredBootstrapper.bootstrap({
 			element: window.document.body,
 			module: conf.appName,
 			resolve: toResolve
 		});
 	}

 	window.gapangular = {
 		bootstrap: bootstrap
 	};

 }(window,document));