<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Orderbid</title>

<%@include file="include.jsp" %>

<!-- <link rel="stylesheet" type="text/css" href="css/other/ForgetPasswordPopup.css"> -->
<script type="text/javascript" src="app-js/LoginCtrl.js"></script>




<!-- <script type="text/javascript" src="components/other/ForgotPasswordValidation.js"></script> -->






<!-- RSA Security -->
<script type="text/javascript" src="components/rsa/jsbn.js"></script>
<!--needed for rsa math operations-->
<script type="text/javascript" src="components/rsa/rng.js"></script>
<!--needed for rsa key generation-->
<script type="text/javascript" src="components/rsa/prng4.js"></script>
<!--needed for rsa key generation-->
<script type="text/javascript" src="components/rsa/rsa.js"></script>
<!-- <script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script> -->

	
	
	
	
</head>
<body>

	<h1 align="center">Delivery Boy login Page</h1>
	<div  ng-app="orderbid" ng-controller="LoginCtrl" data-ng-init="init()">
	<div class="container">
		<div id="loginbox" style="margin-top: 50px;"
			class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
			<div class="panel panel-info">


				<div style="padding-top: 30px" class="panel-body">

					<div style="display: none" id="login-alert"
						class="alert alert-danger col-sm-12"></div>

					

						<div style="margin-bottom: 25px" class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-user"></i></span> <input
								type="text" class="form-control" name="username" ng-model="delivery.userName"
								placeholder="username">
						</div>

						<div style="margin-bottom: 25px" class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-lock"></i></span> <input 
								type="password" class="form-control" name="password" ng-model="delivery.password"
								placeholder="password">
						</div>





						<div class="col-sm-12 controls">
							<button type="submit"  class="alert alert-success"  ng-click="dboyLogin()">Login </button> 
						</div>



				



				</div>
			</div>
		</div>

	</div>
	</div>
</body>
</html>
