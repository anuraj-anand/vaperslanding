<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Orderbid : E-Seller</title>
<%@include file="include.jsp" %>
<script type="text/javascript" src="app-js/DashboardCtrl.js"></script>
<script type="text/javascript" src="app-js/UsersCtrl.js"></script>
<script type="text/javascript" src="app-js/AccountCtrl.js"></script>
<script type="text/javascript" src="app-js/NewlyAddedCtrl.js"></script>
<script type="text/javascript" src="app-js/TotalOrdersCtrl.js"></script>
<script type="text/javascript" src="app-js/ExpiredCtrl.js"></script>
<script type="text/javascript" src="app-js/ClosedCtrl.js"></script>
</head>
<body ng-app="orderbid">
<div growl></div> <!-- adding the grow directive to html -->
<div class="loading" style="display:none;">Loading&#8230;</div>
<%@include file="header.jsp" %>

<main class="dashboard-container">
		
		<div class="container-fluid">
		
			<div class="row" ng-controller="DashboardCtrl" data-ng-init="getLoggedIn()">
				<ul id="gn-menu" class="gn-menu-main side-menu">
				<li class="gn-trigger">
					<a class="gn-icon gn-icon-menu"><span>Menu</span></a>
					<nav class="gn-menu-wrapper">
						<div class="gn-scroller">
							<ul class="gn-menu">
								<!-- <div class="profile-container">
					
									<a href="#">Edit profile <i class="fa fa-pencil"></i></a>
									
									<div class="block"><span class="picture"><i class="fa fa-user"></i></span></div>
									<div  class="block"><span class="title">{{loggedIn.userName}}</span><p>{{loggedIn.company.name}}</p></div>
									
								</div> -->
								<li ng-if="kycFilled"><a class="gn-icon gn-icon-earth" href="#home">Dashboard</a></li>
								<li ng-if="kycFilled"><a class="gn-icon gn-icon-download" href="#totalorders">Total uploaded({{totalOrders}})</a></li>
								<li ng-if="kycFilled"><a class="gn-icon gn-icon-download" href="#newlyadded">Newly Uploaded({{newOrders}})</a></li>
								<li ng-if="kycFilled"><a class="gn-icon gn-icon-download" href="#expired">Expired ({{expired}})</a></li>
								
																
								
								<li>
									<a class="gn-icon gn-icon-download" href ng-click="displaySubmenu('accountsubMenu')" ng-if="loggedIn.type==1">Account</a>
									<ul class="gn-submenu" style="display:none;" id="accountsubMenu">
										<li><a class="gn-icon gn-icon-illustrator" href="#account" ng-if="loggedIn.type==1">Edit account</a></li>
										<li><a class="gn-icon gn-icon-illustrator" href="#changepassword" ng-if="loggedIn.type==1">Change password</a></li>
										<li><a class="gn-icon gn-icon-photoshop" href="#printerSetup" ng-if="loggedIn.type==1">Printer setup</a></li>
										<li><a class="gn-icon gn-icon-photoshop" href="#print" ng-if="loggedIn.type==1">Print</a></li>
									</ul>
								</li>
								
								
							
								<li ng-if="kycFilled"><a class="gn-icon gn-icon-download" href="#users">Users</a></li>
								<li ng-if="kycFilled"><a class="gn-icon gn-icon-download" href="#Enotification">Notification</a></li>
								<li ng-if="kycFilled"><a class="gn-icon gn-icon-download" href="#auditTrail">Audit Trails</a></li>
								<li ng-if="kycFilled"><a class="gn-icon gn-icon-download" href="#cancellation">Cancellation Request</a></li>
							<!-- 	<li>
									<a class="gn-icon gn-icon-download">Downloads</a>
									<ul class="gn-submenu">
										<li><a class="gn-icon gn-icon-illustrator">Vector Illustrations</a></li>
										<li><a class="gn-icon gn-icon-photoshop">Photoshop files</a></li>
									</ul>
								</li>
								<li><a class="gn-icon gn-icon-cog">Settings</a></li>
								<li><a class="gn-icon gn-icon-help">Help</a></li>
								<li>
									<a class="gn-icon gn-icon-archive">Archives</a>
									<ul class="gn-submenu">
										<li><a class="gn-icon gn-icon-article">Articles</a></li>
										<li><a class="gn-icon gn-icon-pictures">Images</a></li>
										<li><a class="gn-icon gn-icon-videos">Videos</a></li>
									</ul>
								</li> -->
							</ul>
						</div><!-- /gn-scroller -->
					</nav>
				</li>
			</ul>
				<!-- <aside class="col-lg-2 col-md-3 col-sm-3 side-menu">
					<div class="profile-container">
					
						<a href="#">Edit profile <i class="fa fa-pencil"></i></a>
						
						<div class="block"><span class="picture"><i class="fa fa-user"></i></span></div>
						<div  class="block"><span class="title">{{loggedIn.userName}}</span><p>{{loggedIn.company.name}}</p></div>
						
					</div>
					<nav>
						<ul class="nav navbar-nav">
							<li><a href="#home"><i class="fa fa-chevron-right"></i>Dashboard</a></li>
							<li><a href="#totalorders"><i class="fa fa-chevron-right"></i>Total uploaded({{totalOrders}})</a></li>
							<li><a href="#newlyadded"><i class="fa fa-chevron-right"></i>Newly Uploaded({{newOrders}})</a></li>
							<li><a href="#expired"><i class="fa fa-chevron-right"></i> Expired ({{expired}})</a></li>
							<li><a href="#account"><i class="fa fa-chevron-right"></i>Account</a></li>
							<li><a href="#users"><i class="fa fa-chevron-right"></i>Users</a></li>
						</ul>
					</nav>
				
				</aside> -->
				
				<section class="col-lg-12 col-md-12 col-sm-12">
					<div ng-view></div>
					<!--h6>warehouse & logistics</h6-->
				</section>
			</div>
		</div>

</main>

<footer>

	<div class="footer-top">
		<ul class="nav">
			<li><a href="#"><i class="fa fa-facebook"></i></a></li>
			<li><a href="#"><i class="fa fa-twitter"></i></a></li>
			<li><a href="#"><i class="fa fa-linkedin"></i></a></li>
		</ul>
	</div>

	<p>Copyrights 2015 - 2016. All Rights Reserved. Powered by orderbid.com</p>
	
</footer>
<script type="text/javascript"
	src="components/gnmenu/classie.js"></script>
<script type="text/javascript"
	src="components/gnmenu/gnmenu.js"></script>
<script>
	new gnMenu( document.getElementById( 'gn-menu' ) );
</script>
</body>
</html>
