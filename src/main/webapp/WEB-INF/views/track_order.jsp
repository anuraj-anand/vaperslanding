<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Orderbid.com | Dashborad</title>
<%@include file="include.jsp" %>

<link rel="stylesheet" href="css/other/bootstrap.css" type="text/css" />
<link rel="stylesheet" href="css/other/layout.css" type="text/css" />
<link rel="stylesheet" href="css/other/datepicker.css" type="text/css" />
<link rel="stylesheet" href="css/other/font-awesome.css" type="text/css" />
<script src="js/jquery-1.11.3.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/moment.js"></script>
<script src="app-js/LoginCtrl.js"></script>
<script src="app-js/UtilService.js"></script>

<script src="app-js/TrackOrderCtrl.js"></script>
<script src="js/bootstrap-datetimepicker.js"></script>
<!-- /////////////////// -->
<style>


#wrapper-track-order footer{
	background-color:#0b60a8;
	background-image:url(images/bg-footer.png);
	background-repeat:no-repeat;
	background-position:top 0;
	height:130px;
	padding:20px 0;
	
}

 a.logo {
    background-image: url(images/order-bid.png);
    background-repeat: no-repeat;
    height: 130px;
    display: block;
}
a {
    color: #337ab7;
    text-decoration: none;
}
a {
    background-color: transparent;
} 
</style>
<!-- ///////////////// -->
</head>
<body ng-app="orderbid">
<div id="wrapper-track-order" ng-controller="TrackOrderCtrl" data-ng-init="getLoggedIn();getOrderStatusList();">
	<header class="navbar navbar-inverse">
		<div class="container">
			<div class="repeater">
				<button aria-controls="navbar" aria-expanded="false" data-target="#navbar" data-toggle="collapse" class="navbar-toggle collapsed" type="button"> <span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </button>
				
				<a href="#" class="navbar-brand pull-left">Track your Order </a> <a href="#" class="pull-right refresh"><i class="fa fa-refresh"></i></a> </div>
				
			</div>
  
	
	</header>

	<nav>
		<div class="container">
			<div class="repeater">
				<div class="search-group">
					<input type="search" id="search" ng-model="orderNo" placeholder="Search your order id">
					<i ng-click="getOrderNoDetails();" class="fa fa-search"></i>
				</div>
			</div>
		</div>
	</nav>
	<!-- <section class="col-lg-12 col-md-12 col-sm-12">
					<div ng-view></div>
					h6>warehouse & logistics</h6
				</section> -->
	

<section>
	<div class="container">
		<div class="repeater">
			<div class="repeater">
				<ul class="form-box">
					<li><a href="#" class="logo"  alt="Order bid" title="Order bid"></a></li>
<li><select ng-model="selectedOrderStatus"><option ng-repeat="x in orderStatusList" value="{{x.id}}">{{x.orderStatus}}</option>
</select></li>
<li><button type="button" name=""  ng-click="updateOrderStatus();" class="btn btn-primary">SUBMIT</button></li>
				</ul>
			</div>
		</div>
	</div>
</section>

<footer style="margin-top:-8px;">
	

<!-- 	<p>Order ID - ATAP05895</p> -->
<!-- 	<p>06 FEB 2016 @01:08PM</p> -->
<!-- 	<p>Swarnadeep Apartment Flat no - 38 Keshav nagar, Pune - 411036</p> -->

<p>{{orderDetail.orderNo}}</p>
<p>{{getDateTimeFromTimeInmili(orderDetail.deliveryDate)}}</p>
<p>{{orderDetail.destAddress}}</p>
	
</footer>

</div>

<script>
 $(function () {
	var header = $('header').height();
	var footer = $('footer').height();
	var nav =   $('nav').height();
	var section =   $(document).height();
	var main =   $(document).height();
	var totalehHeight = main - header - footer - 92 ;
	$('section').css('height', totalehHeight + 'px');

});
</script>
 
</body>
</html>
