'use strict';

angular.module('orderbid').controller('LocationsCtrl', function($window, $scope, $http, $rootScope, $interval, ErrorHandlingService, growl) {
	$scope.tariffs= {};
	
	$scope.getLocationDetails = function(cardType) {
		var response = $http.get('getLocationDetails/'+cardType);
		response.success(function(data, status, headers, config) {
			$scope.tariffs=data;
		});
		response.error(function(data, status, headers, config) {
			//$scope.failureMessage(data);
		});
	};
	
	$scope.tabSelected = "#local";
    $scope.tabChange = function(cardType){
        if(cardType==1){
        	 $scope.tabSelected = "#local";
        }else if(cardType == 2){
        	$scope.tabSelected = "#zonal";
        }else if(cardType == 3){
        	$scope.tabSelected = "#international";
        }
        $scope.getLocationDetails(cardType);
    	/*if (e.target.nodeName === 'A') {
            $scope.tabSelected = e.target.getAttribute("href");
            e.preventDefault();
        }*/
    };
    
	$scope.openRateCardPopup = function() {
		$("#tariffModel").modal('toggle');
	};
    
	$scope.tariff={};
	$scope.addRateCard = function(){
		var response = $http.post('addRateCard', $scope.tariff);
		response.success(function(data, status, headers, config) {
			growl.success('Rate card added successfully.',{title: 'Success!'});
			if($scope.tariff.cardType==1){
	        	 $scope.tabSelected = "#local";
	        }else if($scope.tariff.cardType == 2){
	        	$scope.tabSelected = "#zonal";
	        }else if($scope.tariff.cardType == 3){
	        	$scope.tabSelected = "#international";
	        }
			$scope.getLocationDetails($scope.tariff.cardType);
		});
		response.error(function(data, status, headers, config) {
			//$scope.failureMessage(data);
		});
	};
});