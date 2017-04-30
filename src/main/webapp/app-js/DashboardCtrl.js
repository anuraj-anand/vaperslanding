'use strict';

angular
		.module('orderbid')
		.controller(
				'DashboardCtrl',
				function($window, $scope, $rootScope, $http, $interval,
						ErrorHandlingService, FileUploader, $location, ATP, growl) {
					$scope.totalOrders = '';
					$scope.kycFilled = false;
					$scope.newOrders = '';
					$scope.expired = '';
					$scope.autoQualified = '';
					$scope.orders = {};
					$scope.closedOrders = '';
					$scope.openOrders = '';
					$scope.hotOrders = '';

					$scope.ATP = ATP;
					$scope.updateBid = function() {
						ATP.send("ATP SERVER");
					};

					// /filter

					$scope.openNewOrder = function() {
						$("#filtermodel").modal('toggle');
					};
					$scope.orderFilter = {};
					$scope.getOptionSelected = function() {
						$(".loading").show();
						var response = $http.post('filter', $scope.orderFilter);
						response.success(function(data, status, headers, config) {
							$(".loading").hide();
							$scope.orders = data.orders;
							$scope.total_count = data.totalCount;
							var now = new Date().getMilliseconds();
							angular.forEach($scope.todayTimes, function(value, key) {
								var time1 = value.startTime.split(":");
								var date1 = new Date();
								date1.setHours(time1[0]);
								date1.setMinutes(time1[1]);
								date1.setSeconds(time1[2]);
								
								var startTime = date1.getMilliseconds();
								var time2 = value.endTime.split(":");
								var date2 = new Date();
								date2.setHours(time2[0]);
								date2.setMinutes(time2[1]);
								date2.setSeconds(time2[2]);
								var endTime = date2.getMilliseconds();
								if (now >= startTime && now <= endTime) {
									$rootScope.bidEnabled = true;
								}
							});
						});
						response.error(function(data, status, headers, config) {
							$scope.failureMessage(data);
						});

					};

					$scope.getLoggedIn = function() {
						var response = $http.get('getLoggedIn');
						response
								.success(function(data, status, headers, config) {
									$rootScope.loggedIn = data.user;
									//alert($rootScope.loggedIn.company);
									if(data.kyc != true && $rootScope.loggedIn.company.type != 0){
										$location.path("/account");
										$scope.kycFilled = false;
										growl.error('Please fill out KYC information before starting any activity. OR System not verified your KYC details yet.'
												,{title: 'Error!'});
									}else{
										$scope.kycFilled = true;
										$scope.getAllDetails();
									}
									$("#welcomeText").html("Welcome "+ $rootScope.loggedIn.userName	+ " ("+ $rootScope.loggedIn.company.name + ")");
								});
						response.error(function(data, status, headers, config) {
							// $scope.failureMessage(data);
						});
					};

					$scope.getPortletData = function() {
						var response = $http.get('getPortletData');
						response
								.success(function(data, status, headers, config) {
									$scope.closedOrders = data.closedOrders;
									$scope.openOrders = data.openOrders;
									$scope.hotOrders = data.hotOrders;
									$scope.autoQualified = data.autoQualified;
								});
						response.error(function(data, status, headers, config) {
							// $scope.failureMessage(data);
						});
					};

					

					
					$scope.getAllDetails = function() {
						var response = $http.get('getAlldetails');
						response
								.success(function(data, status, headers, config) {
									
									$scope.totalOrders = data.totalOrders;
									$scope.newOrders = data.newOrders;
									$scope.expired = data.expired;
									if ($rootScope.loggedIn.company.type == 2) {
										$scope.autoQualified = data.autoQualified;
									}
								});
						response.error(function(data, status, headers, config) {
							// $scope.failureMessage(data);
						});
					};

					$scope.getAutoQualified = function() {
						var response = $http.get('autoQulifyOrders');
						response
								.success(function(data, status, headers, config) {
									// $scope.orders=data;
								});
						response.error(function(data, status, headers, config) {
							// $scope.failureMessage(data);
						});
					};
					
					
			       /* //10 seconds delay
					var timervalue=$("#timeer").val();
					
					
			        $timeout( function(){
			        	
			            $scope.test1 = $scope.getBiddingSessions();
			            alert("timervalue"+$scope.test1);
			        		
			        },1000);
			        
			        */
			        
			        
			        //time
			        /*$scope.time = 0;
			        
			        //timer callback
			        var timer = function() {
			            if( $scope.time < 20000 ) {
			                $scope.time += 1000;
			                $timeout(timer, 1000);
			            }
			        }
			        
			        //run!!
			        $timeout(timer, 1000);    */ 
			        
			       

					$scope.getRecentlyAdded = function() {
						var response = $http.get('recentlyAddedOrders');
						response
								.success(function(data, status, headers, config) {
									// $scope.orders=data;
								});
						response.error(function(data, status, headers, config) {
							// $scope.failureMessage(data);
						});
					};

					$scope.getDashboardOrders = function() {
						/*
						 * setTimeout(function(){
						 * if($rootScope.loggedIn.company.type == 2){
						 * //$scope.getAutoQualified(); }else{
						 * //$scope.getRecentlyAdded(); } },500);
						 */

					};

					$scope.bid = {};
					$scope.bid.orderNo = '';
					$scope.bid.maxBid = '';
					$scope.bid.maxUserBid = '';
					$scope.bid.askRate = '';
					$scope.openBidPopup = function(orderNo, maxBid, maxUserBid,
							askRate) {
						$("#bidOrder").modal('toggle');
						$scope.bid.orderNo = orderNo;
						$scope.bid.maxBid = maxBid;
						$scope.bid.maxUserBid = maxUserBid;
						$scope.bid.askRate = askRate;
					};

					$scope.bidError = '';
					$scope.addBid = function(orderId) {
						var bidAmt = $scope.bid.bidAmt;
						if(bidAmt == ''){
							bidAmt = $("#"+orderId+"_bidAmount").val();
							$scope.bid.bidAmt = bidAmt;
						}
						if (!$scope.bid.maxBid) {
							$scope.bid.maxBid = $scope.bid.askRate;
						}
						if (!$scope.bid.maxUserBid) {
							$scope.bid.maxUserBid = $scope.bid.askRate;
						}
						if (bidAmt < $scope.bid.maxBid
								&& bidAmt < $scope.bid.maxUserBid
								&& bidAmt < $scope.bid.askRate) {
							var response = $http.post('addBid', $scope.bid);
							response.success(function(data, status, headers,
									config) {
								$scope.getData($scope.pageno);
								$("#bidOrder").modal('hide');
								if(orderId != ''){
									$("#"+orderId+"_span_bidAmount").show();
									$("#"+orderId+"_span_bidAmount").html($("#"+orderId+"_bidAmount").val());
									$("#"+orderId+"_bidAmount").hide();	
								}
								growl.success('your bid added successfully.',{title: 'Success!'});
							});
							response.error(function(data, status, headers,
									config) {
								// $scope.failureMessage(data);
							});
						} else {
							$scope.bidError = 'You have to add lower price than current price.';
							growl.error($scope.bidError,{title: 'Error'});
							if(orderId != ''){
								$("#"+orderId+"_bidAmount").css("border", "1px solid red");
							}
						}
					};

					$scope.orderwrapper = {};
					$scope.orderwrapper.order = {};
					$scope.orderwrapper.shipmentDate = '';
					$scope.orderwrapper.deliveryDate = '';
					$scope.addOrder = function() {

						$scope.orderwrapper.shipmentDate = $("#shipmentDate")
								.find("input").val();
						$scope.orderwrapper.deliveryDate = $("#deliveryDate")
								.find("input").val();
						/*
						 * $scope.order.shipmentDate =
						 * $("#shipmentDate").data("datetimepicker").getDate();
						 * $scope. order.deliveryDate =
						 * $("#deliveryDate").data("datetimepicker").getDate();
						 * alert($scope.order.shipmentDate + "::"+$scope.
						 * order.deliveryDate)
						 */
						var response = $http.post('addOrder',
								$scope.orderwrapper);
						response
								.success(function(data, status, headers, config) {
									$scope.orders = data;
									$scope.getRecentlyAdded();
								});
						response.error(function(data, status, headers, config) {
							// $scope.failureMessage(data);
						});
					};

					$scope.openNewOrderPopup = function() {
						$("#createNewOrder").modal('toggle');
					};
					$scope.openImportPopup = function() {
						$("#ImportOrderPopup").modal('toggle');
					};

					var uploader = $scope.uploader = new FileUploader({
						url : 'uploadOrder'
					});

					// FILTERS

					uploader.filters.push({
						name : 'customFilter',
						fn : function(item /* {File|FileLikeObject} */,
								options) {
							return this.queue.length < 10;
						}
					});

					// CALLBACKS

					uploader.onWhenAddingFileFailed = function(
							item /* {File|FileLikeObject} */, filter, options) {
						console.info('onWhenAddingFileFailed', item, filter,
								options);
					};
					uploader.onAfterAddingFile = function(fileItem) {
						console.info('onAfterAddingFile', fileItem);
					};
					uploader.onAfterAddingAll = function(addedFileItems) {
						console.info('onAfterAddingAll', addedFileItems);
					};
					uploader.onBeforeUploadItem = function(item) {
						console.info('onBeforeUploadItem', item);
					};
					uploader.onProgressItem = function(fileItem, progress) {
						console.info('onProgressItem', fileItem, progress);
					};
					uploader.onProgressAll = function(progress) {
						console.info('onProgressAll', progress);
					};
					uploader.onSuccessItem = function(fileItem, response,
							status, headers) {
						console.info('onSuccessItem', fileItem, response,
								status, headers);
					};
					uploader.onErrorItem = function(fileItem, response, status,
							headers) {
						console.info('onErrorItem', fileItem, response, status,
								headers);
					};
					uploader.onCancelItem = function(fileItem, response,
							status, headers) {
						console.info('onCancelItem', fileItem, response,
								status, headers);
					};
					uploader.onCompleteItem = function(fileItem, response,
							status, headers) {
						console.info('onCompleteItem', fileItem, response,
								status, headers);
					};
					uploader.onCompleteAll = function() {
						console.info('onCompleteAll');
					};

					console.info('uploader', uploader);
					// $scope.pageno = 1; // initialize page no to 1
					$scope.total_count = 0;
					$scope.itemsPerPage = 10; // this could be a dynamic value
					$scope.pageno = 1; // from a drop down
					$rootScope.bidEnabled = false;
					$scope.getData = function(pageno) { // This would fetch the
						// data on page change.
						// In practice this should be in a factory.
						$(".loading").show();
						$scope.orders = [];
						if (pageno) {
							$scope.pageno = pageno;
						} else {
							$scope.pageno = 1;
						}

						// $http.get("autoQulifyOrders/"+$scope.itemsPerPage+"/"+pageno).success(function(response){
						var url = '';
						if ($rootScope.loggedIn.company.type == 1) {
							url = "recentlyAddedOrders/" + $scope.itemsPerPage
									+ "/" + pageno;
						} else if ($rootScope.loggedIn.company.type == 2) {
							url = "autoQulifyOrders/" + $scope.itemsPerPage
									+ "/" + pageno;
						} else if ($rootScope.loggedIn.company.type == 0) {
							url = "autoQulifyOrders/" + $scope.itemsPerPage
									+ "/" + pageno;
						}
						var response = $http.get(url);
						response.success(function(data, status, headers, config) {
							$(".loading").hide();
							$rootScope.watchesActive = false;
							$scope.orders = data.orders;
							$scope.total_count = data.totalCount;
							var now = new Date().getTime();
							angular.forEach($scope.todayTimes, function(value, key) {
								var time1 = value.startTime.split(":");
								var date1 = new Date();
								date1.setHours(time1[0]);
								date1.setMinutes(time1[1]);
								date1.setSeconds(time1[2]);

								var startTime = date1.getTime();
								var time2 = value.endTime.split(":");
								var date2 = new Date();
								date2.setHours(time2[0]);
								date2.setMinutes(time2[1]);
								date2.setSeconds(time2[2]);
								var endTime = date2.getTime();
								if (now >= startTime && now <= endTime) {
									$rootScope.bidEnabled = true;
								}
							});
						});
					};
					setTimeout(function() {
						$scope.getData($scope.pageno);
						$interval(function() {
							if ($rootScope.watchesActive) {
								$scope.getWatches();
							} else {
								$scope.getData($scope.pageno);
							}
						}, 1000000); // Call the function to
					}, 5000);

					$scope.selectedOrders = [];
					$scope.toggleSelection = function toggleSelection(orderId) {
						var idx = $scope.selectedOrders.indexOf(orderId);

						// is currently selected
						if (idx > -1) {
							$scope.selectedOrders.splice(idx, 1);
						}

						// is newly selected
						else {
							$scope.selectedOrders.push(orderId);
						}
					};
					
					$scope.selectAllOrder = function(){
						//$(".ordercheck").attr("checked", "true");
						if($("#selectAllCheck").is(":checked")){
							 $(".ordercheck").prop('checked', true);
			            }
			            else if($("#selectAllCheck").is(":not(:checked)")){
			                $(".ordercheck").removeAttr('checked');
			            }
						$(".ordercheck").each(function() {
							var id = $(this).attr("id").split("_")[0];
							$scope.toggleSelection(id);
							//alert($(this).attr("id"));
						});
					};
					
					$scope.addWatch = function() {
						var check = false;
						$(".ordercheck").each(function() {
							if($(this).is(":checked")){
								check= true;
							}
						});
						if(check){
							var response = $http.post('addWatch',
									$scope.selectedOrders);
							response
									.success(function(data, status, headers, config) {
										$location.path("/watches");
										growl.success('order added into your watch successfully.',{title: 'Success!'});
										// var path = $location.path() + "/watches"
										// ;
										// $location.path(path);
									});
							response.error(function(data, status, headers, config) {
								// $scope.failureMessage(data);
							});
						}else{
							growl.error('Please select atleast one order to add into your watch.',{title: 'Error!'});
						}
						
					};

					$scope.watchedOrders = {};
					$scope.tabCount = 1;
					$rootScope.watchesActive = false;
					$scope.getWatches = function() {
						var response = $http.get('watches/50/' + $scope.pageno);
						response
								.success(function(data, status, headers, config) {
									$scope.watchedOrders = data;
									var tabs = data.length / 10;
									var strNumber = tabs.toString();
									var reminder =strNumber.substr(0, strNumber.indexOf("."));
									reminder = Number(reminder);
									if(reminder < tabs){
										$scope.tabCount = reminder + 1;
									}else{
										$scope.tabCount = Math.round(tabs) ;
									}
									if($scope.tabCount <= 0){
										$scope.tabCount = 1;
									}
									//alert($scope.tabCount);
									$rootScope.watchesActive = true;
									$scope.total_count = data.totalCount;
									var now = new Date().getTime();
									angular.forEach($scope.todayTimes, function(value, key) {
										var time1 = value.startTime.split(":");
										var date1 = new Date();
										date1.setHours(time1[0]);
										date1.setMinutes(time1[1]);
										date1.setSeconds(time1[2]);

										var startTime = date1.getTime();
										var time2 = value.endTime.split(":");
										var date2 = new Date();
										date2.setHours(time2[0]);
										date2.setMinutes(time2[1]);
										date2.setSeconds(time2[2]);
										var endTime = date2.getTime();
										if (now >= startTime && now <= endTime) {
											$rootScope.bidEnabled = true;
										}
									});
								});
						response.error(function(data, status, headers, config) {
							// $scope.failureMessage(data);
							$rootScope.watchesActive = false;
						});
					};

					$scope.biddingSessions = [];
					$scope.todayTimes = [];
					$scope.sessionNumber = '';
					$scope.timeRemaining ='';
					$scope.getBiddingSessions = function() {
						var response = $http.get('getBiddingSessions');
						response
								.success(function(data, status, headers, config) {
									var sessionNo = 1;
									$scope.biddingSessions = data;
									angular
											.forEach(
													$scope.biddingSessions,
													function(value, key) {
														var d = new Date();
														var weekday = new Array(
																7);
														weekday[0] = "Sunday";
														weekday[1] = "Monday";
														weekday[2] = "Tuesday";
														weekday[3] = "Wednesday";
														weekday[4] = "Thursday";
														weekday[5] = "Friday";
														weekday[6] = "Saturday";
														var day = weekday[d.getDay()];
														
														if (day == value.day) {
															var now = new Date().getTime();
															var time1 = value.fromTime.split(":");
															var date1 = new Date();
															date1.setHours(time1[0]);
															date1.setMinutes(time1[1]);
															date1.setSeconds(time1[2]);
		
															var startTime = date1.getTime();
		
															var time2 = value.toTime.split(":");
															var date2 = new Date();
															date2.setHours(time2[0]);
															date2.setMinutes(time2[1]);
															date2.setSeconds(time2[2]);
															var endTime = date2.getTime();
															if (now >= startTime && now <= endTime) {
																var remain = parseInt(endTime - now);
																$interval(function() {
																	remain = remain - 1000;
																	$scope.timeRemaining = msToTime(remain);
																}, 1000);
																$scope.sessionNumber = 'Session '+ sessionNo;
																var times = {
																	startTime : value.fromTime,
																	endTime : value.toTime
																}
																$scope.todayTimes.push(times);
															}
															sessionNo = parseInt(sessionNo)+1;
														}
													});
								});
						response.error(function(data, status, headers, config) {
							// $scope.failureMessage(data);
						});
					};
					
					$scope.displaySubmenu = function(subMenu){
						$("#"+subMenu).toggle();

					};
					$scope.getNumber = function(num) {
						//var number = new Array(num);
						//alert("number::"+number+" num::"+num);
					    return num;   
					}
					$scope.range=new Array(10);
					$scope.inlineBid = function(orderId, orderNo, maxBid, maxUserBid, askRate){
						$("#"+orderId+"_span_bidAmount").hide();
						var bidAmt = $("#"+orderId+"_bidAmount").val();
						if(bidAmt > 0){
							$("#"+orderId+"_bidAmount").val(bidAmt);
						}else{
							$("#"+orderId+"_bidAmount").val($("#"+orderId+"_span_bidAmount").html());	
						}
						$("#"+orderId+"_bidAmount").show();
						$scope.bid.orderNo = orderNo;
						$scope.bid.maxBid = maxBid;
						$scope.bid.maxUserBid = maxUserBid;
						$scope.bid.askRate = askRate;
						$scope.bid.bidAmt = bidAmt;
					}
				});
