# GAPAngular

GAPAngular is javascript librairie.
It helps _AngularJS_ developpers to connect to [Google EndPoint](www.google.com)

## Without GAPAngular...

Of course, connecting yout _AngularJS_ controllers to _Google Endpoints_ without 
_GAPAngular_ was possible. But integration wasn't that easy and developpers
were used to encouter always same problems.

### Both frameworks have parallels bootstrap mecanisms

#### AngularJS

By default, _AngularJS_ is initialised after DOM construction, or if you prefer
when _ng-app_ is found. BTW, _AngularJS_ can be initialised by code, when you want:


```javascript
$document.onload(
	function initAngular() {
		angular.bootstrap($document,['myApp']);
	}
);

```

#### Javascript Client library _Google EndPoint_

_Client.js_ is a library that allows to use some proxies to connect to the
webservices you deployed using _Google Endpoins_. It mus downloaded using:

```html
<script src="https://apis.google.com/js/client.js?onload=init"></script>
``

Once this file downloaded, the _init()_ function is called, and you can start using 
this library... but, as a final step, you need to download your proxies, then you'll 
be able to call your webservice.

```javascript
gapi.client.load('endpointname', 'v1',null, "http://yourserver/_ah/api").then(
	function() {
		// gapi.client.endpoint name is now created
		// You can use your proxy
		// Let's suppose your webservice exposes a listData() function...
		gapi.client.endpointname.listData().execute(
			function(resp) {
				$scope.$apply($listData = resp.items);
			}
		}
		);
	}
);;
```

#### So, where is the problem ?

##### undefined

* You can't be sure that javascript client library (_client.js) will be downloaded
when AngularJS will bootstrap... And in your opinion, what happens if you try to use
a _google end Point proxy_ in one of your _AngularJS_ component ? 

```javascript
gapi.client.load(...)
```

_undefined_ ... and your controller will not be able to access your end point. This is
a problem...

##### Injection

_AngularJS_ is based upon dependency injection, it means that if a component needs to
work with another one, than you can inject on into another. thanks to _GAPAngular_ you
can now inject a _Google enpoint_ into your components if needed !

#### GAPangular helps you to:

### Initialize both of these two frameworks


_GAPAngular_ is based upon [Angular-deferred-bootstrap](https://github.com/philippd/angular-deferred-bootstrap). GAPAngular is able to wait for all the _Google endpoint_ to be completely initialized before bootstraing _AngularJS_.


Conf:
```javascript
		function init() {
			var conf = {
				'appName':'evalApp',
				'defaultRoot':'http://endpoint-address/_ah/api',
				'defaultVersion':'v1',
				'endpoints': [
				{'endpointname':'sessionendpoint','injectionname':'sessionproxy'}
				]};

				gapangular.bootstrap(conf);
			}
```

These function must be called that way:

```html
<script src="https://apis.google.com/js/client.js?onload=init"></script>
```

### And what about injection

You may notice that _GAPAngular_ config uses a parameter named _injectionName_.
I think you have understood this is the name you have to use to inject this proxy
into yout _AngularJS_ components:

```javascript
angular.module('evalControllers').
controller('SessionController', 
	[ // ...
	'sessionproxy',
	function ($scope,$routeParams,$location,
		sessionproxy) {
		// sessionproxy correspond exactement au nom dans le paramétrage donné
		// un peu plus haut
	}]);
```

Hope this help ;)

## Roadmap

* What about OAuth in _Google End Point_, need to test it...

* May be we can talk about your ideas ?










