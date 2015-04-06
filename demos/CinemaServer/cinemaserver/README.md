Cinema Server
=============================================

The cinema server implementation for Google App Engine.

You must use [maven 3.1.0](http://maven.apache.org).

You can both deploy it as a local web server or to GAE.

## [Local deployment](https://cloud.google.com/appengine/docs/java/tools/maven#testing_your_app_with_the_development_server)
``
mvn appengine:devserver
``
[Admin](http://localhost:8080/_ah/admin)
[API Explorer](http://localhost:8080/_ah/api/explorer)

Here is the configuration for gapangular (in _CinemaClient/app/index.html_)

```javascript
var conf = {
        'appName':'theApp',
        'defaultRoot':' http://localhost:8080/_ah/api',
        //'defaultRoot':'https://1-dot-cinemaendpoint.appspot.com/_ah/api',
        'defaultVersion':'v1',
        'endpoints': [
        {'endpointname':'filmendpoint','injectionname':'filmproxy'},
        {'endpointname':'studioendpoint','injectionname':'studioproxy'},
        {'endpointname':'actorendpoint','injectionname':'actorproxy'}
        ]};
``


## Deploy to Google App Engine

* Login to your Google developers Console
* Create an appId (_cinemaendpoint_ for example)
* Modify the WEB-INF/appengine-web.xml to match the previously created appId

``
mvn appengine:update
``

