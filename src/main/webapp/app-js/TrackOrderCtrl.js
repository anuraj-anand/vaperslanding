'use strict';


angular.module('orderbid').controller("TrackOrderCtrl",['$scope','$http','$rootScope','$util',function($scope,$http,$rootScope,$util){
	
	$scope.getLoggedIn = function() {
		var response = $http.get('getLoggedIn');
		response
				.success(function(data, status, headers, config) {
					$rootScope.loggedIn = data.user;
					//alert($rootScope.loggedIn.company);
					$("#welcomeText").html("Welcome "+ $rootScope.loggedIn.userName	+ " ("+ $rootScope.loggedIn.company.name + ")");
				});
		response.error(function(data, status, headers, config) {
			// $scope.failureMessage(data);
		});
	};
	
	$scope.getOrderStatusList = function() {
		var response = $http.get('getorderstatuslist');
		response
				.success(function(data, status, headers, config) {
					$scope.orderStatusList = data;
				});
		response.error(function(data, status, headers, config) {
			// $scope.failureMessage(data);
		});
	};
	
	$scope.getOrderNoDetails = function() {
		
		var data = {
				orderNo:$scope.orderNo
				
			};
			
			var response = $http({
			    url: "getOrderDetailsByNo", 
			    method: "POST",
			    params: data
			    });
		response
				.success(function(data, status, headers, config) {
//					alert(JSON.stringify(data));
					$scope.orderDetail = data;
					$scope.getDateTimeFromTimeInmili = $util.unixDateTime;
				});
		response.error(function(data, status, headers, config) {
			// $scope.failureMessage(data);
		});
	};
	
	
$scope.updateOrderStatus = function() {
		
		var data = {
				orderNo:$scope.orderNo,
				orderStatus:$scode.selectedOrderStatus
				
			};
			
			var response = $http({
			    url: "updateOrderStatus", 
			    method: "POST",
			    params: data
			    });
		response
				.success(function(data, status, headers, config) {
//					alert(JSON.stringify(data));
					$scope.orderDetail = data;
					$scope.getDateTimeFromTimeInmili = $util.unixDateTime;
				});
		response.error(function(data, status, headers, config) {
			// $scope.failureMessage(data);
		});
	};
	
	
}]);