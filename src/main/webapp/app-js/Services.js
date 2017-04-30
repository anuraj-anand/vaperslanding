
var BidErrorHandling = angular.module('orderbid').service('ErrorHandlingService', function($rootScope) {
	
	var scope = $rootScope.$new();
	
	scope.$on('event:httpResponseFailed_AccessDenied', function(e) {
		alert('Session Time out / HTTP ResponseFailed / Access Denied');
		window.location = 'index.html';
	});

	scope.$on('event:httpResponseFailed_Forbidden', function(e) {
		/*$scope.GlobalData.hideLoadingAnimation();*/
		window.location = 'index.html';
	});

	scope.$on('event:httpResponseFailed_NotAcceptable', function(e) {
		/*$scope.GlobalData.hideLoadingAnimation();*/
		window.location = 'index.html';
	});
	
});

BidErrorHandling.config(function($httpProvider) {

	var interceptor = [ '$rootScope', '$q', '$log', function(scope, $q, $log) {

		function success(response) {
			
			scope.$broadcast('event:httpResponseSuccessful');
			return response;
		}

		function error(response) {

			var status = response.status;

			if (status == 401) {
				scope.$broadcast('event:httpResponseFailed_AccessDenied');
			} else if (status == 403) {
				scope.$broadcast('event:httpResponseFailed_Forbidden');
			} else if (status == 406) {
				scope.$broadcast('event:httpResponseFailed_NotAcceptable');
			} else {
				scope.$broadcast('event:httpResponseFailed');
			}

			return $q.reject(response);

		}

		return function(promise) {
			return promise.then(success, error);
		}

	} ];

	//$httpProvider.responseInterceptors.push(interceptor);

});


