'use strict';

angular.module('orderbid').controller('TotalOrdersCtrl', function($window, $scope, $rootScope, $http, $interval, ErrorHandlingService, growl) {
	
	$scope.totalOrders= {};
	
	$scope.getTotalOrders = function() {
		var response = $http.get('totalOrders');
		response.success(function(data, status, headers, config) {
			$scope.totalOrders=data;
		});
		response.error(function(data, status, headers, config) {
			//$scope.failureMessage(data);
		});
	};
});