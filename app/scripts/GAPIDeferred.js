'use strict';

function gapideferred() {
	var root = 'https://1-dot-cinemaendpoint.appspot.com/_ah/api';
	var endpoints = [
	{'endpointname':'filmendpoint','version':'v1','root':root,'injectionname':'filmproxy'},
	{'endpointname':'studioendpoint','version':'v1','root':root,'injectionname':'studioproxy'},
	{'endpointname':'actorendpoint','version':'v1','root':root,'injectionname':'actorproxy'},
	];



	var toResolve = {};

	for (var i=0;i<endpoints.length;i++){
		var endpointname  = endpoints[i].endpointname ;
		var injectionname = endpoints[i].injectionname || endpoints[i].endpointname ;
		var version		  = endpoints[i].version || 'v1' ;
		var root		  = endpoints[i].root || endpoints[0].root ;

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
		})(endpointname,version,root)


		];
	}

	window.deferredBootstrapper.bootstrap({
		element: window.document.body,
		module: 'theApp',
		resolve: toResolve
	});


}