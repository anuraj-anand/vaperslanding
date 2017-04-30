

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Orderbid : Logistics</title>



<link rel="stylesheet" href="css/other/bootstrap.css" type="text/css" />
<link rel="stylesheet" href="css/other/layout.css" type="text/css" />
<link rel="stylesheet" href="css/other/bootstrap-datetimepicker.css" type="text/css" />
<link rel="stylesheet" href="css/other/font-awesome.css" type="text/css" />
<link rel="stylesheet" href="css/other/bootstrap-switch.min.css" type="text/css" />
<link rel="stylesheet" href="css/other/orderbid.css" type="text/css" />
<link rel="stylesheet" type="text/css" href="css/other/component.css" />
<script src="components/other/jquery-1.11.3.js"></script>
<script src="components/other/bootstrap.min.js"></script>
<script src="components/other/moment.js"></script>
<script src="components/other/bootstrap-datetimepicker.min.js"></script>
<script src="components/other/bootstrap-switch.min.js"></script>

<script src="components/angular/angular.js"></script>
<script type="text/javascript"
	src="components/angular/angular-sanitize.js"></script>
<script type="text/javascript"
	src="components/angular-route/angular-route.js"></script>
<script type="text/javascript"
	src="components/angular/angular-animate.js"></script>
<script type="text/javascript"
	src="components/angular/angular-route-animation-manager.js"></script>
<script type="text/javascript"
	src="components/angular-fileupload/angular-file-upload.min.js"></script>
	<script type="text/javascript"
	src="components/angular-bootstrap-switch/angular-bootstrap-switch.js"></script>
<script type="text/javascript"
	src="components/angular-webstorage/ngStorage.js"></script>
	<script type="text/javascript"
	src="components/angular-bootstrap/ui-bootstrap-tpls-1.2.0.min.js"></script>
	<script type="text/javascript"
	src="components/angular-pagination/dirPagination.js"></script>
	<script type="text/javascript"
	src="components/angular-checklist/checklist-model.js"></script>
	<script type="text/javascript"
	src="components/angular-websocket/angular-websocket.js"></script>
	
<script type="text/javascript" src="app-js/MainApp.js"></script>
<script type="text/javascript" src="app-js/Services.js"></script>
<script type="text/javascript" src="app-js/DashboardCtrl.js"></script>
<script type="text/javascript" src="app-js/UsersCtrl.js"></script>
<script type="text/javascript" src="app-js/AccountCtrl.js"></script>
<script type="text/javascript" src="app-js/NewlyAddedCtrl.js"></script>
<script type="text/javascript" src="app-js/TotalOrdersCtrl.js"></script>
<script type="text/javascript" src="app-js/AutoQualifiedCtrl.js"></script>
<script type="text/javascript" src="app-js/LocationsCtrl.js"></script>
<script type="text/javascript" src="app-js/WatchCtrl.js"></script>
</head>
<body ng-app="orderbid">


<main class="dashboard-container">

<h2>
	Account
</h2>
<div class="panel panel-success">
	<div class="panel-heading">Display information</div>
	<div class="panel-body">
		<form class="form-horizontal" role="form">
			<div class="form-group">
				<label class="control-label col-sm-2" for="email">Your
					display name:</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="email"
						placeholder="Enter email">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="pwd">Describe
					your business:</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="pwd"
						placeholder="Enter password">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="pwd">Returns and
					refund policy:</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="pwd"
						placeholder="Enter password">
				</div>
			</div>
		</form>
	</div>
</div>
<div class="panel panel-success">
	<div class="panel-heading">Pickup address</div>
	<div class="panel-body">
		<form class="form-horizontal" role="form">
			<div class="form-group">
				<label class="control-label col-sm-2" for="email">Address
					Line 1:</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="email"
						placeholder="Enter email">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="pwd">Address Line
					2:</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="pwd"
						placeholder="Enter password">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="pwd">Pin code:</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="pwd"
						placeholder="Enter password">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="pwd">Your pickup
					city:</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="pwd"
						placeholder="Enter password">
				</div>
			</div>
		</form>
	</div>
</div>
<div class="panel panel-success">
	<div class="panel-heading">Login details</div>
	<div class="panel-body">
		<form class="form-horizontal" role="form">
			<div class="form-group">
				<label class="control-label col-sm-2" for="pwd">Your name:</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="pwd"
						placeholder="Enter password">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="pwd">Your mobile
					number:</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="pwd"
						placeholder="Enter password">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="pwd">Your email
					address:</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="pwd"
						placeholder="Enter password">
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-default">Change
						password</button>
				</div>
			</div>
		</form>
	</div>
</div>
<div class="panel panel-success">
	<div class="panel-heading">Primary contact</div>
	<div class="panel-body">
		<form class="form-horizontal" role="form">
			<div class="form-group">
				<label class="control-label col-sm-2" for="pwd">Your name:</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="pwd"
						placeholder="Enter password">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="pwd">Your mobile
					number:</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="pwd"
						placeholder="Enter password">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="pwd">Your email
					address:</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="pwd"
						placeholder="Enter password">
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-default">Change
						password</button>
				</div>
			</div>
		</form>
	</div>
</div>
<div class="panel panel-success">
	<div class="panel-heading">Bank account</div>
	<div class="panel-body">
		<form class="form-horizontal" role="form">
			<div class="form-group">
				<label class="control-label col-sm-2" for="pwd">Your name:</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="pwd"
						placeholder="Enter password">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="pwd">Your mobile
					number:</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="pwd"
						placeholder="Enter password">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="pwd">Your email
					address:</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="pwd"
						placeholder="Enter password">
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-default">Change
						password</button>
				</div>
			</div>
		</form>
	</div>
</div>
<div class="panel panel-success">
	<div class="panel-heading">KYC documents</div>
	<div class="panel-body">
		<form class="form-horizontal" role="form">
			<div class="form-group">
				<label class="control-label col-sm-2" for="pwd">Your name:</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="pwd"
						placeholder="Enter password">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="pwd">Your mobile
					number:</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="pwd"
						placeholder="Enter password">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="pwd">Your email
					address:</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="pwd"
						placeholder="Enter password">
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-default">Change
						password</button>
				</div>
			</div>
		</form>
	</div>
</div>
</div>

</main>

<footer style="margin-top:-8px;">
	

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
