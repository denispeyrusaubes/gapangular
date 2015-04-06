# endpoints

This library helps you to correctly bootstrap Google Client and AngularJS. Once your endpoints are correctly initialized, you can inject them in your differents AngularJS's components (i.e. controllers).

This library relies on Philipp Denzler's one: [angular-deferred-bootstrap](https://github.com/philippd/angular-deferred-bootstrap)

## Configuration

index.html must declare:

```html
	<script>    
	function init() {

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
* 

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

In order to initialise both AngularJS and your google endpoints services. You may pass a _conf_ object that contains differet fields:

| Field         | Detail                     | From           | DefaultValue  |
| ------------- |:--------------------------:|:--------------:| -------------:|
| appName       | AngularJS application name | none 	   	  |	-			  |
| defaultRoot   | RootURL to access to your endpoint      |   Google End Point		| - |
| defaultVersion| default version of your endpoint   |    Google End Point 		| v1 |
| endPoints		| detail for your endpoints  |    Google End Point 		| - |




## Build & development

Run `grunt` for building and `grunt serve` for preview.

## Links


[Google Endpoint](https://cloud.google.com/appengine/docs/java/endpoints')

[AngularJS](https://angularjs.org)

[angular-deferred-bootstrap](https://github.com/philippd/angular-deferred-bootstrap)
