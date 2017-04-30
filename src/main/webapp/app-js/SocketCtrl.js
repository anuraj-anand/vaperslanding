'use strict';

angular.module('orderbid').controller(
		'SocketCtrl',
		function($window, $scope, $rootScope, $http, $interval,$filter,
				ErrorHandlingService, ATP, growl) {
			 $scope.ATP = ATP;
             $scope.submit = function () {
                 ATP.send("ATP SERVER");
             };     
		});