'use strict';

angular
		.module('orderbid')
		.controller(
				'AccountCtrl',
				function($window, $scope, $rootScope, $http, $interval,
						$filter, ErrorHandlingService, growl) {

					$scope.companies = {};
					$scope.selectedCompany = '';

					$scope.hstep = 1;
					$scope.mstep = 15;

					$scope.options = {
						hstep : [ 1, 2, 3 ],
						mstep : [ 1, 5, 10, 15, 25, 30 ]
					};

					$scope.ismeridian = true;
					$scope.toggleMode = function() {
						$scope.ismeridian = !$scope.ismeridian;
					};

					$scope.getCompanies = function() {
						var response = $http.get('getCompanies');
						response
								.success(function(data, status, headers, config) {
									$scope.companies = data;
								});
						response.error(function(data, status, headers, config) {
							// $scope.failureMessage(data);
						});
					};

					$scope.kyc = {};
					$scope.openKycDetails = function(companyId) {
						var response = $http.get('getKycDetails/' + companyId);
						response
								.success(function(data, status, headers, config) {
									$scope.kyc = data;
									$("#KYCModel").modal('toggle');
								});
						response.error(function(data, status, headers, config) {
							growl.error('KYC not filled yet.', {
								title : 'Error!'
							});
						});
					};

					/*
					 * $scope.getNotificationMessage = function() {
					 * 
					 * alert("==inside the getNotificationMessage=="); var
					 * response = $http.get('getNotificationMessage');
					 * response.success(function(data, status, headers, config) {
					 * 
					 * alert('inside the success call'); // $scope.usernames =
					 * data.usernames;
					 * 
					 * alert("data.usernames" + data); var value =
					 * JSON.stringify(data); alert("=====value" + value); });
					 * response.error(function(data, status, headers, config) { //
					 * $scope.failureMessage(data); }); }
					 */
					$scope.getResult = [];
					$scope.getNotificationMessage = function() {
						//alert("inside the Success data ");

						var response = $http.get('getNotificationMessage');
						response
								.success(function(data, status, headers, config) {
									$scope.getResult = data;

									//alert("inside the Success data " + data);
									// alert(data.last_Modified_Time);

									// var value = JSON.stringify(data);
									// alert("value" + value);
									// $scope.usernames=data;
								});
						response.error(function(data, status, headers, config) {
							// $scope.failureMessage(data);
						});
					};

					$scope.approveKYC = function(companyId) {
						var response = $http.post('approveKYC',
								$scope.kyc.company);
						response
								.success(function(data, status, headers, config) {
									$scope.kyc = data;
									$scope.getCompanies();
									growl.success('KYC approved successfully.',
											{
												title : 'Success!'
											});
								});
						response.error(function(data, status, headers, config) {
							// $scope.failureMessage(data);
						});
					};

					$scope.changeCompanyStatus = function(company) {
						// console.log('Selection changed11:: 11
						// status::'+$scope.selectedCompany.id);
						console.log('Selection changed11:' + company.id
								+ " ::status::" + company.active);
						if (company.activeBool) {
							company.active = 1;
						} else {
							company.active = 0;
						}
						$scope.changeCompany(company);
					};

					$scope.changeCompany = function(company) {
						$scope.selectedCompany = company;
						var response = $http.post('updateCompanyStatus',
								$scope.selectedCompany);
						response
								.success(function(data, status, headers, config) {
									growl
											.success(
													'Company status updated successfully.',
													{
														title : 'Success!'
													});
								});
						response.error(function(data, status, headers, config) {

						});
					};
					$scope.days = [ "All days", "Monday", "Tuesday",
							"Wednesday", "Thursday", "Friday", "Saturday",
							"Sunday" ];
					$scope.checkAll = function() {
						$scope.sessions.days = angular.copy($scope.days);
					};
					$scope.uncheckAll = function() {
						$scope.sessions.days = [];
					};
					$scope.checkDay = function(day, ele) {
						if (day == "All days" && ele.checked) {
							$scope.checkAll();
						} else if (day == "All days" && !ele.checked) {
							$scope.uncheckAll();
						}
					}
					$scope.sessions = {
						days : []
					};
					$scope.biddingSessions = [];
					$scope.getBiddingSessions = function() {
						var response = $http.get('getBiddingSessions');
						response
								.success(function(data, status, headers, config) {
									$scope.biddingSessions = data;
								});
						response.error(function(data, status, headers, config) {
							// $scope.failureMessage(data);
						});
					};
					$scope.fromTime = new Date();
					$scope.toTime = new Date();

					$scope.biddingSession = {};
					$scope.biddingSession.fromTime = '';
					$scope.biddingSession.toTime = '';
					$scope.biddingSession.day = '';
					$scope.addBiddingSession = function() {
						angular
								.forEach(
										$scope.sessions.days,
										function(day) {
											$scope.biddingSession = {};
											$scope.biddingSession.fromTime = $filter(
													'date')($scope.fromTime,
													'HH:mm:ss');
											$scope.biddingSession.toTime = $filter(
													'date')($scope.toTime,
													'HH:mm:ss');
											$scope.biddingSession.day = day;
											var response = $http.post(
													'addBiddingSession',
													$scope.biddingSession);
											response
													.success(function(data,
															status, headers,
															config) {
														$scope
																.getBiddingSessions();
														if (data == "duplicate") {
															growl
																	.error(
																			"This bidding session is already present.",
																			{
																				title : 'Error!'
																			});
														} else {
															growl
																	.success(
																			'Bidding session added successfully.',
																			{
																				title : 'Success!'
																			});
														}
													});
											response.error(function(data,
													status, headers, config) {
												// $scope.failureMessage(data);
											});
										})
					};

					$scope.deleteBiddingSession = function(biddingSession) {
						if ($window
								.confirm("Are you sure, Do you want to delete this bidding session?")) {
							var response = $http.post('deleteBiddingSession',
									biddingSession);
							response
									.success(function(data, status, headers,
											config) {
										$scope.getBiddingSessions();
										growl
												.success(
														'Bidding session deleted successfully.',
														{
															title : 'Success!'
														});
									});
							response.error(function(data, status, headers,
									config) {
								// $scope.failureMessage(data);
							});
						}
					};

					$scope.account = {};
					$scope.account.display = {};
					$scope.saveDisplayInfo = function() {
						var response = $http.post('saveDisplayInfo',
								$scope.account.display);
						response
								.success(function(data, status, headers, config) {
									growl
											.success(
													'Display information saved successfully.',
													{
														title : 'Success!'
													});
								});
						response.error(function(data, status, headers, config) {
							// $scope.failureMessage(data);
						});
					};

					$scope.account.pickup = {};
					$scope.savePickupInfo = function() {
						var response = $http.post('savePickupInfo',
								$scope.account.pickup);
						response
								.success(function(data, status, headers, config) {
									growl
											.success(
													'Pickup information saved successfully.',
													{
														title : 'Success!'
													});
								});
						response.error(function(data, status, headers, config) {
							// $scope.failureMessage(data);
						});
					};
					
					
					
					
					
					
					$scope.getAdminBidOrder = function() {
						
						$scope.getAdminBidOrder=[];
						
						
						$("#lOrder").show();
						$("#EOrder").hide();

						//alert("inside the getAdminBidOrder method call")
						var response = $http.post('getAdminBidOrder');
						response
								.success(function(data, status, headers, config) {

								//	alert("$scope.getAdminBidOrder=[];" + data)
									$scope.getAdminBidOrder = data;
									//alert("JSON" + JSON.stringify(data));
								});
						response.error(function(data, status, headers, config) {
							// $scope.failureMessage(data);
							//alert("failure " + data);

						});
					};
					
					
					$scope.getAdminOrder = function() {
						$scope.getAdminOrder={};

						//alert("inside the getAdminOrder method call")
						
						
						$("#EOrder").show();
						$("#lOrder").hide();
						
						
						var response = $http.post('getAdminOrder');
						response
								.success(function(data, status, headers, config) {

							//	alert("getAdminOrder" + data)
									$scope.getAdminOrder = data;
								//alert("getAdminOrder JSON " + JSON.stringify(data));
								});
						response.error(function(data, status, headers, config) {
							// $scope.failureMessage(data);
							//alert("failure " + data);

						});
						
						
						
					};
					
					$scope.getlogisticOrder = function() {

						//alert("inside the getlogisticOrder method call")
						var response = $http.post('getlogisticOrder');
						response
								.success(function(data, status, headers, config) {

								//	alert("dataagetlogisticOrder" + data)
									$scope.getlogisticOrder = data;
									//alert("JSON" + JSON.stringify(data));
								});
						response.error(function(data, status, headers, config) {
							// $scope.failureMessage(data);
							//alert("failure " + data);

						});
					};

					
					
			 
					 


					$scope.checkedItems = function() {
						
						//alert("inside the checkedItems function ");
				        var checkedItems = [];
				        angular.forEach($scope.getlogisticOrder, function(item, arrayIndex){
				        	
				        	//alert("===="+cb);
				        	//alert("key");
				            angular.forEach(item, function(cb, key) {
				                if(key.substring(0, 2) == "cb" && cb) {
				                    checkedItems.push(item)
				                  //  alert("item.id"+item.id+"item.value"+item.orderNo);
				                    //alert("JSON.stringify"+JSON.stringify(item));
				                }
				            })
				        })
				        return checkedItems
				    };
				    
				    $scope.checkedlist=[];
				    
					$scope.cancelBid = function() {

						//alert("inside the cancelBid method call")
						$scope.checkedlist=$scope.checkedItems();
						//alert("checkedlist"+JSON.stringify($scope.checkedlist));
						//alert(""+$scope.checkedlist.orderNo);
						
						
						
						var urlto = 'cancelBid?orderNo='
							+$scope.checkedlist.orderNo;
						//strat
						$
						.ajax({
							url : urlto,
							type : 'POST',
							cache : false,
							success : function(data) {
								

								 

								
							//	  alert("inside" +JSON .stringify(data));
								 
								
								
								

							}

						});
						
						
						//ends
						/*var response = $http.post('cancelBid',$scope.checkedlist);
						response
								.success(function(data, status, headers, config) {

									alert("dataaa" + data)
									$scope.esselerorder = data;
								//	alert("JSON" + JSON.stringify(data));
								});
						response.error(function(data, status, headers, config) {
							// $scope.failureMessage(data);
							alert("failure " + data);

						});*/
					};
					
					
					$scope.esselerorder = {};
					$scope.getEsselerorder = function() {

						//alert("inside the esseler method call")
						var response = $http.post('getEsselerorder');
						response
								.success(function(data, status, headers, config) {

							//		alert("dataaa" + data)
									$scope.esselerorder = data;
								//	alert("JSON" + JSON.stringify(data));
								});
						response.error(function(data, status, headers, config) {
							// $scope.failureMessage(data);
							//alert("failure " + data);

						});
					};

					$scope.orderdeatils = [];
					$scope.details={};

					$scope.getOrderDetailsByNo = function(item) {

						//alert("inside the viewSaveHistoryOrderDetails method call");
						//alert("===" + item);
						$scope.orderdeatils = item;
						var orderNo = item.orderNo;
						//alert("ordreno" + orderNo);

						// $("#myModal").show();
						var response = $http.post('getOrderDetailsByNo',
								$scope.orderdeatils);

						response
								.success(function(data, status, headers, config) {

									$scope.details=data;
									//alert("dataaakkk" + data)
									
									//$scope.esselerorder = data;
									//alert("JSON" + JSON.stringify(data));
								});
						response.error(function(data, status, headers, config) {
							// $scope.failureMessage(data);
							//alert("failure " + data);

						});
					};

					$scope.account.primary = {};
					$scope.savePrimaryInfo = function() {
						var response = $http.post('savePrimaryInfo',
								$scope.account.primary);
						response
								.success(function(data, status, headers, config) {
									growl
											.success(
													'Primary information saved successfully.',
													{
														title : 'Success!'
													});
								});
						response.error(function(data, status, headers, config) {
							// $scope.failureMessage(data);
						});
					};

					$scope.account.bank = {};
					$scope.saveBankInfo = function() {
						var response = $http.post('saveBankInfo',
								$scope.account.bank);
						response
								.success(function(data, status, headers, config) {
									growl
											.success(
													'Bank information saved successfully.',
													{
														title : 'Success!'
													});
								});
						response.error(function(data, status, headers, config) {
							// $scope.failureMessage(data);
						});
					};

					$scope.account.kyc = {};
					$scope.saveKYCInfo = function() {
						//alert("saveKYCINFO called")
						var response = $http.post('saveKYCInfo',
								$scope.account.kyc);
						response
								.success(function(data, status, headers, config) {

							//		alert("status" + status)
									growl
											.success(
													'KYC information saved successfully.Please wait for some time, System will verify your kyc details.',
													{
														title : 'Success!'
													});
								});
						response.error(function(data, status, headers, config) {
							// $scope.failureMessage(data);
						});
					};
					
					

					$scope.user=[];
					
					$scope.uploadProfilePics = function(obj) {
						//alert("inside the Profile pics call")
						var elemId = $(obj).attr("id");
						//alert("elemId" + elemId);
					//	alert("$scope.profilepics" + $scope.profilePicture);
						$scope.$apply(function(scope) {

							var file = document
									.querySelector('#' + elemId + '').files[0];
							var reader = new FileReader();
							reader.addEventListener("load", function() {
								if (elemId == "profilePicture") {
									//alert("kjkjkj=============");

									$scope.user.profilePicture = reader.result;
									//alert("$scope.profilepics"+ $scope.user.profilePicture);
									//alert(""+$scope.user);

								}

							}, false);

							if (file) {
								reader.readAsDataURL(file);
							}

						});
					};

					$scope.selectDocument = function(doc) {

						if (doc == "profilePicture") {
							$scope.selectedDocument = $scope.user.profilePicture;

						} 
					}
					
					
					//$scope.user = {};
					$scope.saveProfilePics = function() {
					//	alert("saveProfilePics called")
						//alert("$scope.saveProfilePics" + $scope.user.profilePicture);
					   //alert("$scope.user"+$rootScope.loggedIn.userName);
					   $scope.user.username=$rootScope.loggedIn.userName;
						//alert("$scope.user"+$scope.user.username);
						var response = $http.post('saveProfilePics',
								$scope.user);
						response
								.success(function(data, status, headers, config) {

							//		alert("status" + status);
								//	alert("data"+data);
									growl.success(
											'Profile Pics Saved Successfully.',
											{
												title : 'Success!'
											});
								});
						response.error(function(data, status, headers, config) {
							// $scope.failureMessage(data);
						});
					};

					// save Profile Pics

					// Upload Profile picture
					
					
					
				

					$scope.uploadKycDocuments = function(obj) {

						var elemId = $(obj).attr("id");

						$scope
								.$apply(function(scope) {

									var file = document.querySelector('#'
											+ elemId + '').files[0];
									var reader = new FileReader();
									reader
											.addEventListener(
													"load",
													function() {
														if (elemId == "addressproof") {
															//alert("kjkjkj");
															$scope.account.kyc.addressProof = reader.result;

														} else if (elemId == "idproof") {
															$scope.account.kyc.idProof = reader.result;

														} else if (elemId == "cancelledcheque") {
															$scope.account.kyc.cancelledCheque = reader.result;

														}

													}, false);

									if (file) {
										reader.readAsDataURL(file);
									}

								});
					};

					$scope.selectDocument = function(doc) {

						if (doc == "addressproof") {
							$scope.selectedDocument = $scope.account.kyc.addressProof;

						} else if (doc == "idproof") {
							$scope.selectedDocument = $scope.account.kyc.idProof;

						} else if (doc == "cancelledcheque") {
							$scope.selectedDocument = $scope.account.kyc.cancelledCheque;

						}
					}

					$scope.getAccountInfo = function() {
						$(".loading").show();
						var response = $http.get('getAccountInfo');
						response
								.success(function(data, status, headers, config) {
									$(".loading").hide();
									$scope.account.display = data.display;
									$scope.account.pickup = data.pickup;
									$scope.account.primary = data.primary;
									$scope.account.bank = data.bank;
									$scope.account.kyc = data.kyc;
									//alert("data.userdetails"+data.userdetails);
									$scope.user=data.userdetails;
									
									
								});
						response.error(function(data, status, headers, config) {
							// $scope.failureMessage(data);
						});
					};

					$scope.getActivateEselLogis = function() {
						var response = $http
								.get("getActiveEsselerLogisticUser");
						response
								.success(function(data, status, headers, config) {

									$scope.activeEsselCount = data.activeEsselCount;
									$scope.activeLogisCount = data.activeLogisCount;

								});
						response.error(function(data, status, headers, config) {
							alert("in error");
						});
					}

					$scope.getEsselLogiskycStatus = function() {
						var response = $http.get("getEsselLogiskycStatus");
						response
								.success(function(data, status, headers, config) {

									$scope.esselKycCompletedCount = data.esselKycCompletedCount;
									$scope.esselKycIncompletedCount = data.esselKycIncompletedCount;
									$scope.logisKycCompletedCount = data.logisKycCompletedCount;
									$scope.logisKycIncompletedCount = data.logisKycIncompletedCount;

								});
						response.error(function(data, status, headers, config) {
							alert("in error");
						});
					}

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

					// save Change Password
					$scope.change = {};
					$scope.saveChangedPassword = function() {
						// alert("inside the Change password Call");
						// alert("inside the Change password
						// Call"+$scope.change);

						var rsa = new RSAKey();
						rsa.setPublic($scope.rsaPublicKey, '10001');
						// alert("$scope.rsaPublicKey"+$scope.rsaPublicKey)
						var currentpassword = rsa
								.encrypt($scope.change.currentpassword);

						var encPassnew = rsa.encrypt($scope.change.newpassword);
						$scope.change.currentpassword = currentpassword;
						$scope.change.newpassword = encPassnew;

						// alert("encPassnew"+encPassnew);
						// alert("currentpassword"+currentpassword);

						// alert("encPass"+encPass);

						var response = $http.post('saveChangedPassword',
								$scope.change);

						response
								.success(function(data, status, headers, config) {
									//alert("Success:" + data);

								});
						response.error(function(data, status, headers, config) {
							alert("error::" + data);
						});

					}

				});
