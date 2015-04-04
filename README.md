# endpoints
Angular module for Google endpoint initialization at bootstrap


# Objectifs:

Permet d'initialiser proprement AngularJS pour pouvoir fonctionner avec Google EndPoint.
Peut ausse se formuler en:
Permet d'initialiser Google EndPoint proprement pour pouvoir fonctionner avec AngularJS.

# Le problème

<script src="bower_components/angular/angular.js">

"This code downloads the angular.js script which registers a callback that will be executed by the browser when the containing HTML page is fully downloaded. When the callback is executed, Angular looks for the ngApp directive. If Angular finds the directive, it will bootstrap the application with the root of the application DOM being the element on which the ngApp directive was defined."


-> AngularJS ne s'initialise 


