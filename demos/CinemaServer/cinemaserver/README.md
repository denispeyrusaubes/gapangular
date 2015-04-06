Cinema Server
(c) Denis Peyrusaubes
=============================================

The cinema server implementation for Google App Engine.

You can both deploy it as a local web server using

[Deploy to local](https://cloud.google.com/appengine/docs/java/tools/maven#testing_your_app_with_the_development_server)




or you can deploy to the Google App Engine (you must have an account for that):
* Loggin to your Google developers Console
* Create an appId (_cinemaserver_ for example)
* Modify the WEB-INF/appengine-web.xml to match the appId

'''
mvn appengine:update
'''

