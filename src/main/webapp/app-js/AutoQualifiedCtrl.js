'use strict';

angular.module('orderbid').controller('AutoQualifiedCtrl', function($window, $scope, $http, $rootScope, $interval, ErrorHandlingService, growl) {
	$scope.autoQualifiedOrders= {};
	
	$scope.getAutoqualified = function() {
		var response = $http.get('autoQulifyOrders');
		response.success(function(data, status, headers, config) {
			$scope.autoQualifiedOrders=data;
		});
		response.error(function(data, status, headers, config) {
			//$scope.failureMessage(data);
		});
	};
});