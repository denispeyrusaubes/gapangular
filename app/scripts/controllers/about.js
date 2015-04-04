'use strict';

/**
 * @ngdoc function
 * @name endpointsApp.controller:AboutCtrl
 * @description
 * # AboutCtrl
 * Controller of the endpointsApp
 */
angular.module('endpointsApp')
  .controller('AboutCtrl', function ($scope) {
    $scope.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];
  });
