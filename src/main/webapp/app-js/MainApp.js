angular.module(
		'orderbid',
		[ 'ngRoute', 'angularFileUpload', 'frapontillo.bootstrap-switch',
				'angularUtils.directives.dirPagination', 'ui.bootstrap',
				'checklist-model', 'ngWebSocket', 'angular-growl' ]).filter(
		'fromNow',
		function() {
			return function(dateString) {
				// alert(moment(dateString).startOf('hour').fromNow());
				//
				return moment(dateString).format(
						"dddd, MMMM Do YYYY, h:mm:ss a");
				// return moment(dateString).fromNow()
			};
		}).filter('iif', function() {
	return function(input, trueValue, falseValue) {
		return input ? trueValue : falseValue;
	};
});
;

// configure our routes
angular.module('orderbid').config(function($routeProvider) {
	$routeProvider

	// route for the home page
	.when('/', {
		templateUrl : 'templates/dashboard.html',
		controller : 'DashboardCtrl'
	}).when('/home', {
		templateUrl : 'templates/dashboard.html',
		controller : 'DashboardCtrl'
	}).when('/dboyhome', {
		templateUrl : 'templates/track_order.html',
		controller : 'TrackOrderCtrl'
	}).when('/totalorders', {
		templateUrl : 'templates/totalorders.html',
		controller : 'TotalOrdersCtrl'
	}).when('/newlyadded', {
		templateUrl : 'templates/newlyadded.html',
		controller : 'NewlyAddedCtrl'
	}).when('/expired', {
		templateUrl : 'templates/expired.html',
		controller : 'ExpiredCtrl'
	}).when('/closed', {
		templateUrl : 'templates/closed.html',
		controller : 'ClosedCtrl'
	}).when('/autoQualified', {
		templateUrl : 'templates/autoqualified.html',
		controller : 'AutoQualifiedCtrl'
	})
	// route for the about page
	.when('/users', {
		templateUrl : 'templates/users.html',
		controller : 'UsersCtrl'
	}).when('/locations', {
		templateUrl : 'templates/locations.html',
		controller : 'LocationsCtrl'
	})
	// route for the about page
	.when('/account', {
		templateUrl : 'templates/account.html',
		controller : 'AccountCtrl'
	}).when('/changepassword', {
		templateUrl : 'templates/changepassword.html',
		controller : 'AccountCtrl'
	}).when('/auditTrail', {
		templateUrl : 'templates/auditTrail.html',
		controller : 'AccountCtrl'
	}).when('/cancellation', {
		templateUrl : 'templates/cancellation.html',
		controller : 'AccountCtrl'
	}).when('/activate', {
		templateUrl : 'templates/activate.html',
		controller : 'AccountCtrl'
	}).when('/sessions', {
		templateUrl : 'templates/biddingsessions.html',
		controller : 'AccountCtrl'
	}).when('/watches', {
		templateUrl : 'templates/watches.html',
		controller : 'WatchCtrl'
	}).when('/messagelog', {
		templateUrl : 'templates/messagelog.html',
		controller : 'MessageLogCtrl'
	}).when('/adminfilter', {
		templateUrl : 'templates/adminfilter.html',
		controller : 'AdminFilterCtrl'

	}).when('/lfilter', {
		templateUrl : 'templates/logisticfilter.html',
		controller : 'Logisticfilter'

	})
	
.when('/lfilterowned', {
	templateUrl : 'templates/logisticowned.html',
	controller : 'Logisticfilter'

})

.when('/lfilterdelivered', {
		templateUrl : 'templates/logisticDelivered.html',
		controller : 'Logisticfilter'

	})

	.when('/orders', {
		templateUrl : 'templates/orders.html',
		controller : 'OrdersCtrl'
	}).when('/notification', {
		templateUrl : 'templates/notification.html',
		controller : 'BidedOrdersCtrl'
	})

	.when('/Enotification', {
		templateUrl : 'templates/notification_user.html',
		controller : 'AccountCtrl'
	}).when('/bidedorders', {
		templateUrl : 'templates/bidedorders.html',
		controller : 'BidedOrdersCtrl'
	})
});

angular.module('orderbid').directive('ngEnter', function() {
	return function(scope, element, attrs) {
		element.bind("keydown keypress", function(event) {
			if (event.which === 13) {
				scope.$apply(function() {
					scope.$eval(attrs.ngEnter, {
						'event' : event
					});
				});

				event.preventDefault();
			}
		});
	};
});

angular
		.module('orderbid')
		.factory(
				'ATP',
				function($websocket) {
					// Open a WebSocket connection
					var ws = $websocket("ws://localhost:9090/orderbid/bidendpoint");
					var atp = [];
					ws
							.onMessage(function(event) {
								console.log('message: ', event.data);
								var response;
								try {
									response = angular.fromJson(event.data);
								} catch (e) {
									document.getElementById("helloId").innerHTML = "Sorry, connection failed ...";
									document.getElementById("btnAtpId").disabled = false;
									console.log('error: ', e);
									response = {
										'error' : e
									};
								}
								if (response.hello) {
									document.getElementById("helloId").innerHTML = response.hello;
									document.getElementById("btnAtpId").disabled = false;
								} else {
									for (var i = 0; i < response.length; i++) {
										atp.push({
											rank : response[i].rank,
											name : response[i].name,
											email : response[i].email
										});
									}
								}
							});
					ws.onError(function(event) {
						console.log('connection Error', event);
					});
					ws.onClose(function(event) {
						console.log('connection closed', event);
					});
					ws.onOpen(function() {
						console.log('connection open');
						ws.send('HELLO SERVER');
					});
					return {
						atp : atp,
						status : function() {
							return ws.readyState;
						},
						send : function(message) {
							if (angular.isString(message)) {
								ws.send(message);
							} else if (angular.isObject(message)) {
								ws.send(JSON.stringify(message));
							}
						}
					};
				});

/*
 * angular.module('orderbid').directive('bootstrapSwitch', [ function() { return {
 * restrict : 'A', require : '?ngModel', link : function(scope, element, attrs,
 * ngModel) { element.bootstrapSwitch();
 * 
 * element.on('switchChange.bootstrapSwitch', function(event, state) { if
 * (ngModel) { scope.$apply(function() { ngModel.$setViewValue(state); }); } });
 * 
 * scope.$watch(attrs.ngModel, function(newValue, oldValue) { if (newValue) {
 * element.bootstrapSwitch('state', true, true); } else {
 * element.bootstrapSwitch('state', false, true); } }); } }; } ]);
 */