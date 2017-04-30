'use strict';

angular.module('orderbid').controller('ClosedCtrl', 
		function($window, $scope, $rootScope, $http, $interval, ErrorHandlingService, growl) {
	
	$scope.closedOrders= {};
	$scope.pageno = 1; // from a drop down
	$scope.itemsPerPage = 10; // this could be a dynamic value
	$scope.getClosedOrders = function(pageno) {
		if (pageno) {
			$scope.pageno = pageno;
		} else {
			$scope.pageno = 1;
		}

		var response = $http.get('closedOrders/'+$scope.itemsPerPage+'/'+$scope.pageno);
		response.success(function(data, status, headers, config) {
			$scope.closedOrders=data;
		});
		response.error(function(data, status, headers, config) {
			//$scope.failureMessage(data);
		});
	};
});