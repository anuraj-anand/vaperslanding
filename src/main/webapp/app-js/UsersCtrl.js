'use strict';

angular.module('orderbid').controller('UsersCtrl', function($window, $scope, $rootScope, $http, $interval, ErrorHandlingService, growl) {

	$scope.allUsers= {};
	
	$scope.getAllUsers = function() {
		var response = $http.get('allUsers');
		response.success(function(data, status, headers, config) {
			$scope.allUsers=data;
		});
		response.error(function(data, status, headers, config) {
			//$scope.failureMessage(data);
		});
	};
	
	$scope.user = {};
	$scope.addUser= function(){
		$(".loading").show();
		var response = $http.post('addUser', $scope.user);
		
		response.success(function(data, status, headers, config) {
			$scope.getAllUsers();
			$(".loading").hide();
			$("#createNewUser").modal('toggle');
			growl.success('User added successfully.',{title: 'Success!'});
		});
		response.error(function(data, status, headers, config) {
			//$scope.failureMessage(data);
		});
	}
	
	$scope.openNewUserPopup = function(){
		$("#createNewUser").modal('toggle');
	}
	$scope.userId = '';
	$scope.resetPassword = function(user){
		$scope.user = user;
		$(".loading").show();
		var response = $http.post('resetPassword', $scope.user);
		response.success(function(data, status, headers, config) {
			growl.success('Password sent to your user email address.',{title: 'Success!'});
			$(".loading").hide();
		});
		response.error(function(data, status, headers, config) {
			//$scope.failureMessage(data);
		});
	}
});