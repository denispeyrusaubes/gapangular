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
* _onload=init_ refers to the previously _init()_ declared method. This method 
will be run after the _client.js_ library download
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


## Build & development

Run `grunt` for building and `grunt serve` for preview.

## Links


[Google Endpoint](https://cloud.google.com/appengine/docs/java/endpoints')

[AngularJS](https://angularjs.org)

[angular-deferred-bootstrap](https://github.com/philippd/angular-deferred-bootstrap)
