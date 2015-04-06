# endpoints

[Google Endpoint](https://cloud.google.com/appengine/docs/java/endpoints')

[AngularJS](https://angularjs.org)

[angular-deferred-bootstrap](https://github.com/philippd/angular-deferred-bootstrap)

This library helps you to correctly bootstrap Google Client and AngularJS. Once your endpoints are correctly initialized, you can inject them in your differents AngularJS's components (i.e. controllers)

## Configuration

index.html must declare:

```html
	<script>    
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

    </script>
    <script src="path_to/angular.js"></script>
    <script src="path_to/angular-deferred-bootstrap.js"></script>
    <script src="scripts/app.js"></script>
    <script src="https://apis.google.com/js/client.js?onload=init"></script> 
    <script src="path_to/gapangular.js"></script>

```

## Build & development

Run `grunt` for building and `grunt serve` for preview.

## Testing

Running `grunt test` will run the unit tests with karma.
