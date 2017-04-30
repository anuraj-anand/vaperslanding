'use strict';


angular.module('orderbid').controller("Logisticfilter",['$scope','$http','$util',function($scope,$http,$util){
	
	 $(function () {
         $('#adminfilterstdate').datetimepicker();
         $('#adminfilteretdate').datetimepicker();

     });
	 
	 $scope.UnixDateTime = $util.UnixDateTime;
	
	$scope.getEsselLogisUsers = function(){
		
		var response = $http.get('getEselLogisUserList');
		response.success(function(data, status, headers, config) {
			$scope.eselLogisUsers = data;
		});
		response.error(function(data, status, headers, config) {
			//$scope.failureMessage(data);
		});	
	}
	
	$scope.filterRegisEselLogisUsers = function(){
		var data = {
			userType:-1,
			filterType:$scope.filterType,
			startTimestamp:$scope.startTimestamp,
			endTimestamp:$scope.endTimestamp
		};
		
		var response = $http({
		    url: "filterRegisEselLogisUsers", 
		    method: "POST",
		    params: data
		    });
		response.success(function(data, status, headers, config) {
			$scope.eselLogisUsers = data;
		});
		response.error(function(data, status, headers, config) {
			//$scope.failureMessage(data);
		});
	}
	
	$('#adminfilterstdate').on("dp.change",function(e){
			
		$scope.startTimestamp = e.date.unix();
	 });
	$('#adminfilteretdate').on("dp.change",function(e){
		$scope.endTimestamp = e.date.unix();
	 });
}]);