Cinema Server
=============================================

The cinema server implementation for Google App Engine.

You must use [maven 3.1.0](http://maven.apache.org).

You can both deploy it as a local web server or to GAE.

## [Local deployment](https://cloud.google.com/appengine/docs/java/tools/maven#testing_your_app_with_the_development_server)
``
mvn appengine:devserver
``

## Deploy to Google App Engine

* Login to your Google developers Console
* Create an appId (_cinemaendpoint_ for example)
* Modify the WEB-INF/appengine-web.xml to match the previously created appId

``
mvn appengine:update
``

the url for your app is: http://appid.appspot.com/_ah/api#p/filmendpoint/v1/
