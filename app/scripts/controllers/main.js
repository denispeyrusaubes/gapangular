'use strict';

/**
 * @ngdoc function
 * @name endpointsApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the endpointsApp
 */
angular.module('endpointsApp')
  .controller('MainCtrl', function ($scope) {
    $scope.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];
  });
