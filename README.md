# GAPAngular

[english](https://github.com/denispeyrusaubes/gapangular/blob/master/README-en.md)

GapAnpular est une librairie utilitaire en javascript.
Elle facilite la tâche aux développeurs _AngularJS_ souhaitant accéder 
aux webservice de Google appelés [Google EndPoint](www.google.com)

## Sans GAPAngular...

Evidemment, il était possible de connecter vos contrôleurs _AngularJS_
aux _Google Endpoints_ avant que _GAPAngular_ n'existe. Mais l'intégration
n'était pas simple, et les développeurs se heurtaient souvent aux mêmes
problèmes.

### Des processus de bootstrap parralèles...

#### AngularJS

Par défaut, _AngularJS_ est initialisé une fois le DOM de la page construit,
et plus précisémment au moment où la directive _ng-app_ est rencontrée.

AngularJS offre néanmoins une alternative à ce mécanisme qui consiste à 
initialiser vous même le  framework au moment où vous le considérez opportun:

```javascript
$document.onload(
	function initAngular() {
		angular.bootstrap($document,['myApp']);
	}
);

```

#### Librairie client de _Google EndPoint_

_Google_ met à disposition une librairie Javascript mettant à disposition des
développeurs un mécanisme de proxy permettant d'invoquer les webservices REST 
déployés dans _Google EndPoints_. Cette librairie est chargée de façon 
traditionnelle dans votre page html:

```html
<script src="https://apis.google.com/js/client.js?onload=init"></script>
``

Une fois que le navigateur a chargé cette dépendance, la fonction _init()_ est
invoquée. C'est uniquement à partir de là que vous avez la possibilité d'utiliser
la librairie utilitaire de Google. 

__Attention:__ Cela ne signifie pas pour autant que vous pouvez directement appeler 
vos webservices ! En effet une phase supplémentaire d'initialisation de la librairie 
est encore nécessaire...

L'ultime phase de paramétrage de l'api Client de Google consiste à charger les proxies
de vos webservices. C'est uniquement à l'issue de cette phase que vous pourrez
dialoguer avec ces derniers.

```javascript
gapi.client.load('endpointname', 'v1',null, "http://yourserver/_ah/api").then(
	function() {
		// A partir d'ici, le proxy pour accéder au webservice _endpointname_ est
		// chargé. Google vous le met à disposition en modifiant le protype de leur
		// objet gapi.client
		// En supposant que votre webservice mette à disposition une méthode listData()
		gapi.client.endpointname.listData().execute(
			function(resp) {
				$scope.$apply($listData = resp.items);
			}
		}
		);

	}
);;
```

#### Où est le problème ?

##### undefined

Les mécanismes précédemment décrits ne font pas paraître de façon évidente les problèmes
rencontrés par les développeurs utilisant ces deux outils simultanément.
En voici une description non exhaustive:

* Rien ne nous permet d'être certain que _client.js_(librairie _Google_) sera chargée
au moment ou AngularJS s'initialisera... Que se passe t-il alors si, dans le 
controlleur de l'écran initial, nous cherchons à exécuter le code suivant ? 

```javascript
gapi.client.load(...)
```

_undefined_  sera alors retourné par le runtime... et votre contrôleur ne pourra donc
pas accéder aux données qui lui sont à priori nécessaires... C'est un problème...

##### Qualité du code

AngularJS utilise le principe d'injection de dépendance. GAPAngular vous permet 
d'utiliser ce pattern avec les sources de données provenant de _Google Endpoint_

#### GAPangular vous permet de:

### initialisier correctement tout cela...

En s'appuyant sur [Angular-deferred-bootstrap](http://...), GAPAngular "attend" 
l'initialisation complète de vos proxis _Google endpoint_ ainsi que leur mise à 
disposition pour injection avant d'initialiser _AngularJS_

Pour cela, dans la page principale de votre application, il suffit de mettre
en oeuvre _GAPAngular_ de la façon suivante:

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

Cette fonction sera invoquée à la fin du chargement de l'api _Google Client_:

```html
<script src="https://apis.google.com/js/client.js?onload=init"></script>
```

### Bénéficier de l'injection de dépendance...

Vous noterez que l'un des paramètre d'initialisation de _GAPAngular_ s'appelle 
_injectionName_, vous l'aurez compris, il vous permet d'utiliser vos proxies
par le jeu de l'injection de dépendances dans vos controller _AngularJS_:

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

* faudrait que je jette un oeil à l'usage d'OAuth afin de gérer authentification 
et droits d'accès avec les _Google end points_

* Et puis si vous avez des idées intéressantes, parlons en...










