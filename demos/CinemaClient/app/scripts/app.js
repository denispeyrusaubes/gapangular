'use strict';

/**
 * @ngdoc overview
 * @name endpointsApp
 * @description
 * # endpointsApp
 *
 * Main module of the application.
 */
 var theApp = angular
 .module('theApp', []);

 theApp.controller('MainController', function($scope,studioproxy,filmproxy) {
  $scope.greeting = 'Bonjour !';
  $scope.films = [];
  $scope.studios = [];
  
  filmproxy.listFilm().execute(function(resp){
    $scope.$apply(function () {$scope.films = resp.items; });
  } );      

  studioproxy.listStudio().execute(function(resp){
    $scope.$apply(function () {$scope.studios = resp.items; });
  } );}

  );
