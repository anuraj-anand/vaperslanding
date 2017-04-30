'use strict';

angular.module('orderbid').controller('NewlyAddedCtrl', function($window, $scope, $http, $rootScope, $interval, ErrorHandlingService, growl) {
	$scope.newOrders= {};
	
	$scope.getNewOrders = function() {
		var response = $http.get('newOrders');
		response.success(function(data, status, headers, config) {
			$scope.newOrders=data;
		});
		response.error(function(data, status, headers, config) {
			//$scope.failureMessage(data);
		});
	};
});