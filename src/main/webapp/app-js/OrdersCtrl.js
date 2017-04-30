'use strict';


angular.module('orderbid').controller("OrdersCtrl",['$scope','$http','$util',function($scope,$http,$util){
	
	 $(function () {
         $('#registeredordersstdate').datetimepicker({format:"YYYY-MM-DD HH:mm:ss"});
         $('#registeredordersetdate').datetimepicker({format:"YYYY-MM-DD HH:mm:ss"});

     });
	 
	 $scope.unixDateTime = $util.unixDateTime;
	
	$scope.getTotalRegisteredOrders = function(){
		
		
		//alert("====OrdersCtrl--===");
		var response = $http.get('getTotalRegisteredOrders');
		response.success(function(data, status, headers, config) {
			$scope.registeredOrders = data;
		});
		response.error(function(data, status, headers, config) {
			//$scope.failureMessage(data);
		});	
	}
	
	$scope.filterOrdersRegisteredByEsellerUser = function(){
		var data = {
			userId:$scope.eselUser.id,
			filterType:$scope.filterType,
			startTimestamp:$scope.startTimestamp,
			endTimestamp:$scope.endTimestamp
		};
		
		var response = $http({
		    url: "filterOrdersRegisteredByEsellertUser", 
		    method: "POST",
		    params: data
		    });
		response.success(function(data, status, headers, config) {
			$scope.registeredOrders = data;
		});
		response.error(function(data, status, headers, config) {
			//$scope.failureMessage(data);
		});
	}
	
	$scope.getEselLogisNameList = function() {
		 
		var response = $http.get('getEselLogisNameList');
		response.success(function(data, status, headers, config) {
			$scope.eselNames = data.eselNames;
		});
		response.error(function(data, status, headers, config) {
			// $scope.failureMessage(data);
		});
	}
	
	//
	
	$scope.getOrderNo = function() {
		alert("inside teh getOrderNoDetails function calll");
		
		
		var data = {
				orderNo:$scope.auditrail.ordernumber
				
			};
			
			var response = $http({
			    url: "getAuditOrderDetails", 
			    method: "POST",
			    params: data
			    });
		response
				.success(function(data, status, headers, config) {
//					alert(JSON.stringify(data));
					$scope.orderDetail = JSON.stringify(data);
					$scope.orderNo=data.orderNo;
					
					$scope.orderDate=data.orderDate;
					$scope.sourceAddress=data.sourceAddress;
					$scope.destAddress=data.destAddress;
					$scope.weight=data.weight;
					$scope.orderStatus=data.orderStatus;
					
					alert("$scope.orderNo"+$scope.orderNo);
					$scope.getDateTimeFromTimeInmili = $util.unixDateTime;
					
					alert("data"+JSON.stringify(data));
					
					 $('#show').show();
					 
					 $('#example').DataTable();
				});
		response.error(function(data, status, headers, config) {
			 //$scope.failureMessage(data);
			 alert("Errrrrrrr"+JSON.stringify(data));
		});
	};
	
	
	
	
	
	
	$('#registeredordersstdate').on("dp.change",function(e){
		$scope.startTimestamp = e.date.unix()*1000;
	 });
	$('#registeredordersetdate').on("dp.change",function(e){
		$scope.endTimestamp = e.date.unix()*1000;
	 });
}]);