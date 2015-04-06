# GAPAngular 

This library helps you to correctly bootstrap Google Client and AngularJS. Once your endpoints are correctly initialized, you can inject them in your differents AngularJS's components (i.e. controllers).

This library relies on Philipp Denzler's one: [angular-deferred-bootstrap](https://github.com/philippd/angular-deferred-bootstrap)

## Configuration

index.html must declare:

```html
	<script>    
	function init() {
		// Describe later
    }
    </script>
    <script src="path_to/angular.js"></script>
    <script src="path_to/angular-deferred-bootstrap.js"></script>
    <script src="scripts/app.js"></script>
    <script src="https://apis.google.com/js/client.js?onload=init"></script> 
    <script src="path_to/gapangular.js"></script>

```

where: 
* app.js is your angularJS's main conf file
* _onload=init_ refers to the previously _init()_ declared function. This function 
will be run after the _client.js_ once the _client.js_ library has finished loading.

_Bootstraping your angularJS application is manual. Do not use any ng-app directive_ !


```javascript
	function init() {
      var conf = {
        'appName':'theApp',
        'defaultRoot':'https://appid.appspot.com/_ah/api',
        'defaultVersion':'v1',
        'endpoints': [
        	{'endpointname':'endpoint1','injectionname':'endpoint1proxy'},
        	{'endpointname':'endpoint2','injectionname':'endpoint2proxy'}
        ]};

        gapangular.bootstrap(conf);
    }
```

Bootstraping your angularJS application is manual. __Do not use any ng-app directive__ !

In order to initialise both AngularJS and your Google endpoints services. You must pass a _conf_ object that contains differents fields:

| Field         | Detail                     | From           | DefaultValue  |
| ------------- |:--------------------------:|:--------------:| :-------------:|
| appName       | AngularJS application name | none 	   	  |	-			  |
| defaultRoot   | RootURL to access to your endpoint      |   Google End Point		| - |
| defaultVersion| default version of your endpoint   |    Google End Point 		| v1 |
| endPoints		| Array of endpoint description (_described later_)  |    Google End Point 		| - |


example of _endpoints_ value:

```javascript
[
    {'endpointname':'filmendpoint','injectionname':'filmproxy'},
    {'endpointname':'studioendpoint','injectionname':'studioproxy'},
    {'endpointname':'actorendpoint','injectionname':'actorproxy'}
]
```

These values are completed with the _defaultRoot_ and _defaultVersion_ previously described.
If default values are not correct for one endpoint entry, you can overide them using:

```javascript
[
	// ...
	{'endpointname':'studioendpoint','injectionname':'studioproxy',
		'version':'v2','root':'myroot'},
    // ...
]
```

Equivalent code (before gapangular exists):

```javascript
var ROOT = 'https://your_app_id.appspot.com/_ah/api';
gapi.client.load('your_api_name', 'v1', undefined, ROOT).then(
	function() {
		// you can use gapi.client.your_api_name
		injectionName = gapi.client.your_api_name
	}
);
```


__injectionname__ must be supplied. It corresponds to the name of the component you can
inject in your different angular's module.

```javascript
theApp.controller('MainController', function($scope,studioproxy,filmproxy) {
  $scope.films = [];
  $scope.studios = [];
  
  filmproxy.listFilm().execute(function(resp){
    $scope.$apply(function () {$scope.films = resp.items; });
  } );      

  studioproxy.listStudio().execute(function(resp){
    $scope.$apply(function () {$scope.studios = resp.items; });
  } );}

  );
```




## Build & development

Run `grunt` for building and `grunt serve` for preview.

## Links
[Angular-deferred-bootstrap](https://github.com/philippd/angular-deferred-bootstrap)

[Google Endpoint](https://cloud.google.com/appengine/docs/java/endpoints')

[AngularJS](https://angularjs.org)

