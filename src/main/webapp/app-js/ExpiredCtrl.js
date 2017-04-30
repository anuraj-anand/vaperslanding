'use strict';

angular.module('orderbid').controller('ExpiredCtrl', function($window, $scope, $rootScope, $http, $interval, ErrorHandlingService, growl) {
	
	$scope.expiredOrders= {};
	
	$scope.getExpiredOrders = function() {
		var response = $http.get('expiredOrders');
		response.success(function(data, status, headers, config) {
			$scope.expiredOrders=data;
		});
		response.error(function(data, status, headers, config) {
			//$scope.failureMessage(data);
		});
	};
});