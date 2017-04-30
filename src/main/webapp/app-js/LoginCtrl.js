'use strict';

angular
		.module('orderbid')
		.controller(
				'LoginCtrl',
				function($window, $scope, $http, $interval,
						ErrorHandlingService, growl) {

					// alert("Login ErrorHandlingService: " +
					// ErrorHandlingService);
					$scope.login = {};
					$scope.registration = {};
					$scope.registration.industryType = '';
					$scope.registration.company = '';
					$scope.registration.email = '';
					$scope.registration.username = '';
					$scope.registration.password = '';
					$scope.registration.mobile = '';
					$scope.login.userName = '';
					$scope.login.password = '';
					$scope.error = '';
					$scope.registrationAlert = '';
					
					
					/*$scope.delivery.username='';
				    $scope.delivery.password='';*/

					$scope.validate = function() {
						
						
						//alert("Validate Functiondddd"+JSON.stringify($scope.registration));
						var formData = {};
						var response = $http.post('sendmail',$scope.registration);
						response
								.success(function(data, status, headers, config) {
									
									growl.success('Sign Up successfully.',{title: 'Success!'});
									//alert("Sucess----"+JSON.stringify(data));
								//	$scope.formData = data;

								});
						response.error(function(data, status, headers, config) {
							alert("error::"+data);
						});

					}

					$scope.openForgetPasswordPopUp = function() {

						$("#emailModal").modal('show');

					};
					$scope.mailValidation = function() {
						$("#emailModal").modal('toggle');

						var classname = document.getElementById("checkemail").className;

						if (classname == 'input-group-addon danger') {
							alert("invalid entry");
							return;
						}

						else {

							var response = $http.post('forgotPassword',
									$scope.login);
							response.success(function(data, status, headers,
									config) {

								alert(data);

							});
							response.error(function(data, status, headers,
									config) {
								$scope.failureMessage(data);
								alert("Try Again lateer");

							});

						}

					};

					$scope.doRegister = function() {
						var validCheck = false;
						if ($scope.registration.company
								&& $scope.registration.email
								&& $scope.registration.username
								&& $scope.registration.password
								&& $scope.registration.mobile) {
							validCheck = true;
							var response = $http.post('createUser',
									$scope.registration);
							response
									.success(function(data, status, headers,
											config) {
										if (data == "Success") {
											// document.getElementById("regitrationAlert").style.dislay="block";
											// $(".dropdown-menu").hide();

											$scope.registrationAlert = 'Thank you for registraion. You will received email shortly. Please try to login.'
										} else if (data == "userExistsError") {
											// document.getElementById("regitrationAlert").style.dislay="block";
											// $(".dropdown-menu").hide();

											$scope.registrationAlert = 'Sorry for the incovience but this username/email/mobile is already used by someone. Please try again.'
										} else {
											// document.getElementById("regitrationAlert").style.dislay="block";
											// $(".dropdown-menu").hide();

											$scope.registrationAlert = 'Due to some technical difficulties we are not trying to register maybe please try after some time.'
										}
										$(".loading").hide();
									});
							response
									.error(function(data, status, headers,
											config) {
										document
												.getElementById("regitrationAlert").style.dislay = "block";
										$(".dropdown-menu").hide();
										$scope.registrationAlert = 'Due to some technical difficulties we are not trying to register maybe please try after some time.'
									});
						}
					};

					$scope.loadNewlyCreatedUsers = function() {
						var response = $http.post('api/loadNewlyCreatedUsers',
								{});
						response
								.success(function(data, status, headers, config) {
									var userObject = data;
									var htmlBody = '<div class="list-group">';
									var userData = '';
									var i;
									for (i = 0; i < userObject.length; i++) {
										userData = userData
												+ '<a class="list-group-item">'
												+ '<h5 class="list-group-item-heading">'
												+ userObject[i]["FIRSTNAME"]
												+ " "
												+ userObject[i]["LASTNAME"]
												+ '</h5>'
												+ '<p class="list-group-item-text">'
												+ userObject[i]["NAME"]
												+ '</p>'
												+ '<p class="list-group-item-text">'
												+ userObject[i]["DESCRIPTION"]
												+ '</p>' + '</a>';
									}
									$("#NewVendor").html(
											htmlBody + userData + '</div>');
								});
						response.error(function(data, status, headers, config) {
							alert("Exception while getting Online users");
						});
					};

					$scope.init = function() {
						var formData = {};
						var response = $http.post('getPublicKey', formData);
						response
								.success(function(data, status, headers, config) {
									// alert(data);
									$scope.rsaPublicKey = data;

								});
						response.error(function(data, status, headers, config) {
							// alert("error::"+data);
						});
						// $scope.getFooterDetails();
					};

					$scope.doLogin = function(doLogoutFromAllBrowsers) {
						if ($scope.login.userName && $scope.login.password
								&& $scope.login.userName != ""
								&& $scope.login.password != "") {

							$(".loading").show();

							if (doLogoutFromAllBrowsers != undefined
									&& doLogoutFromAllBrowsers != "") {

								$scope.login.doLogoutFromAllBrowsers = "doLogoutFromAllBrowsers";
							} else {

								var rsa = new RSAKey();
								rsa.setPublic($scope.rsaPublicKey, '10001');
								//alert("$scope.rsaPublicKey"+$scope.rsaPublicKey)
								var encPass = rsa
										.encrypt($scope.login.password);
								$scope.login.password = encPass;
								//alert("encPass"+encPass);
							}
							var response = $http.post('loginsubmit',
									$scope.login);
							response
									.success(function(data, status, headers,
											config) {
										if (data
												.indexOf("LOGOUT_FROM_ALL_BROWSERS") != -1) {
											// $scope.toggleLogin();
											$('#closeAllSessionAndLogin')
													.modal('show');

										} else if (data == "Error") {
											$scope.login.userName = '';
											$scope.login.password = '';
											$scope.error = "Invalid Username or password.";
										} else if (data == "notActivated") {
											$scope.login.userName = '';
											$scope.login.password = '';
											$scope.error = "Your account is not yet activated by system administrator Please contact system administrator.";
										}

									/*	
										
										
										else if (data == "NOTFILLED") {
											$scope.login.userName = '';
											$scope.login.password = '';
											$scope.error = "KYC DETAILS ARE NOT FILLED";
											location.href = "generic_account.jsp";

										}
*/
										else {
											/*
											 * //$scope.toggleLogin();
											 * $scope.login.userName = '';
											 * $scope.login.password = '';
											 */
											$window.location.href = "home";
										}
										$(".loading").hide();
									});
							response.error(function(data, status, headers,
									config) {
								alert("Exception details: " + JSON.stringify({
									data : data
								}));
							});
						}
					};

					//login for delivery boy 
					
					$scope.dboyLogin = function(doLogoutFromAllBrowsers) {
						
							if ($scope.delivery.userName && $scope.delivery.password
									&& $scope.delivery.userName != ""
									&& $scope.delivery.password != "") {

								$(".loading").show();

								if (doLogoutFromAllBrowsers != undefined
										&& doLogoutFromAllBrowsers != "") {

									$scope.login.doLogoutFromAllBrowsers = "doLogoutFromAllBrowsers";
								} else {

									var rsa = new RSAKey();
									rsa.setPublic($scope.rsaPublicKey, '10001');
									//alert("$scope.rsaPublicKey"+$scope.rsaPublicKey)
									var encPass = rsa
											.encrypt($scope.delivery.password);
									$scope.delivery.password = encPass;
									//alert("encPass"+encPass);
								}

							
							var response = $http.post('deliveryloginsubmit',$scope.delivery);
							
							response.success(function(data, status, headers,
											config) {
										if (data
												.indexOf("LOGOUT_FROM_ALL_BROWSERS") != -1) {
											// $scope.toggleLogin();
											$('#closeAllSessionAndLogin')
													.modal('show');

										} else if (data == "Error") {
											$scope.delivery.username = '';
											$scope.delivery.password = '';
											$scope.error = "Invalid Username or password.";
										} else if (data == "notActivated") {
											$scope.delivery.username = '';
											$scope.delivery.username = '';
											$scope.error = "Your account is not yet activated by system administrator Please contact system administrator.";
										}

									/*	
										
										
										else if (data == "NOTFILLED") {
											$scope.login.userName = '';
											$scope.login.password = '';
											$scope.error = "KYC DETAILS ARE NOT FILLED";
											location.href = "generic_account.jsp";

										}
*/
										else {
											/*
											 * //$scope.toggleLogin();
											 * $scope.login.userName = '';
											 * $scope.login.password = '';
											 */
											alert("inside the Success call");
											$window.location.href = "dboyhome";
										}
										$(".loading").hide();
									});
							response.error(function(data, status, headers,
									config) {
								alert("Exception details: " + JSON.stringify({
									data : data
								}));
							});
						}
					};
					
					
					
					
					//
					
					$scope.onlineUsers = 0;
					$scope.registeredUsers = 0;
					$scope.registeredRequests = 0;
					$scope.getFooterDetails = function() {
						var response = $http.get('api/getFooterDetails', {});
						response
								.success(function(data, status, headers, config) {
									// var reqList = eval('(' + data + ')');
									$scope.onlineUsers = data.online;
									$scope.registeredUsers = data.registered;
									$scope.registeredRequests = data.totalRequests;
								});
						response.error(function(data, status, headers, config) {
							alert("Exception");
						});
					}
				});