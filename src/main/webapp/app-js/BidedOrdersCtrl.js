'use strict';

angular
		.module('orderbid')
		.controller(
				"BidedOrdersCtrl",
				[
						'$scope',
						'$http',
						'$util',
						function($scope, $http, $util) {

							$(function() {
								$('#bidedordersstdate').datetimepicker();
								$('#bidedordersetdate').datetimepicker();

							});

							$scope.unixDateTime = $util.unixDateTime;

							$scope.getTotalBidedOrders = function() {

								var response = $http.get('getTotalBidedOrders');
								response.success(function(data, status,
										headers, config) {
									$scope.bidedOrders = data;
								});
								response.error(function(data, status, headers,
										config) {
									// $scope.failureMessage(data);
								});
							}
							// esseler name list js function
							$scope.getEselLogisNameList = function() {

								var response = $http
										.get('getEselLogisNameList');
								response.success(function(data, status,
										headers, config) {
									$scope.logisNames = data.logisNames;
								});
								response.error(function(data, status, headers,
										config) {
									// $scope.failureMessage(data);
								});
							}

							// logistic and esseler list together except admin

							$scope.userdetails = {};
							$scope.getUsernamelist = function() {

								//alert("==inside the userlist==");
								var response = $http.get('getUsernamelist');
								response.success(function(data, status,
										headers, config) {

									//alert('inside the success call');
									$scope.usernames = data.usernames;
									$scope.userdetails = data.usernames;

									//alert("data.usernames" + data.usernames);
								});
								response.error(function(data, status, headers,
										config) {
									// $scope.failureMessage(data);
								});
							}

							// notification message

							// save Notifiaction message

							// notification message
							$scope.notification = {};

							$scope.SaveNotification = function() {
								// $scope.Username.name=[];

								var b_msg = $('#B_message').val();
								alert(b_msg);
								// var selectedvalue=$('#userselected
								// :selected').text();
								var selObj = document
										.getElementById('userselected');
								var selected = [];

								for (var i = 0, l = selObj.options.length; i < l; i++) {
									// Check if the option is selected
									if (selObj.options[i].selected) {
										selected
												.push(selObj.options[i].textContent);
									}
								}
								var valwith = selected.join(',');

								alert("valwith" + valwith);

								//alert("inside the savenotification call");
								// alert("$scope.Bmessage"+$scope.Bmessage);
								//alert("$scope.userdetails" + $scope.userdetails);
								//alert("$scope.userdetails.length"+ $scope.userdetails.length);

								/*
								 * uname= $scope.Username.name;
								 * id=$scope.Username.id; alert("selcted
								 * name"+uname);
								 * 
								 * substr = uname; alert("Substr"+substr);
								 * 
								 * var message = ""; for (var i = 0; i <
								 * $scope.ddlFruits.length; i++) { var fruitId =
								 * $scope.ddlFruits[i]; var fruitName =
								 * $.grep($scope.Fruits, function (fruit) {
								 * return fruit.Id == fruitId; })[0].Name;
								 * message += "Value: " + fruitId + " Text: " +
								 * fruitName + "\n"; } $window.alert(message);
								 */var message = "";
								var id = [];
								var Name = [];
								var idd = [];
								var nameee = [];
								//alert("$scope.usernamesjjjjjjjjjs"+ JSON.stringify($scope.usernames));
								//alert("$scope.userdetails"+ JSON.stringify($scope.userdetails))
								for (var i = 0; i < $scope.userdetails.length; i++) {
									// var name= $scope.Username.name[i];
									id = $scope.userdetails[i].id;
									//alert("idcccc" + id);
									Name = $scope.userdetails[i].name;
									idd += id + "-I";
									nameee += Name + "-A";
									// message += "Value: " + id + " Text: " +
									// Name + "\n";
								}
								//alert("$scope.Bmessage;" + $scope.Bmessage);
								//alert("iddddddddddd" + idd);
								//alert("Name" + nameee);
								message = b_msg;

								//alert("$scope.Bmessage" + message);
								/*
								 * var jsonString=JSON.stringify(uname);
								 * alert("jsjjd"+jsonString.name);
								 */

								var data = {
									uname : valwith,
									bmsg : message,
									ID : idd

								};

								// var jsonString = JSON.stringify(data);

								// alert("$scope.Username.bmeg" +
								// $scope.notification.Bmessage);

								/*
								 * alert("var jsonString =
								 * JSON.stringif"+jsonString);
								 */

								var response = $http({
									url : "SaveNotification",
									method : "POST",
									params : data
								});
								response.success(function(data, status,
										headers, config) {
									//alert("data========" + data);
								});
								response.error(function(data, status, headers,
										config) {
									// $scope.failureMessage(data);
									//alert("data== failure======" + data);
								});

								/*
								 * var response = $http.post('SaveNotification',
								 * $scope.notification);
								 * response.success(function(data, status,
								 * headers, config) {
								 * 
								 * alert("Details Inserted Successfully");
								 * 
								 * }); response.error(function(data, status,
								 * headers, config) {
								 * 
								 * alert("Try Again lateer here");
								 * 
								 * });
								 */
							}

							//

							$scope.filterOrdersByLogisticUser = function() {
								var data = {
									userId : $scope.logisUser.id,
									filterType : $scope.filterType,
									startTimestamp : $scope.startTimestamp,
									endTimestamp : $scope.endTimestamp
								};

								var response = $http({
									url : "filterOrdersByLogisticUser",
									method : "POST",
									params : data
								});
								response.success(function(data, status,
										headers, config) {
									$scope.bidedOrders = data;
								});
								response.error(function(data, status, headers,
										config) {
									// $scope.failureMessage(data);
								});
							}

							$('#bidedordersstdate')
									.on(
											"dp.change",
											function(e) {

												$scope.startTimestamp = e.date
														.unix() * 1000;
											});
							$('#bidedordersetdate')
									.on(
											"dp.change",
											function(e) {
												$scope.endTimestamp = e.date
														.unix() * 1000;
											});
						} ]);