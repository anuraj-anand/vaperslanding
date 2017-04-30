<!DOCTYPE html>
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
<script>
$("#id_of_textbox").keyup(function(event){
    if(event.keyCode == 13){
        $("#id_of_button").click();
    }
});
</script>
</head>
<body>
<div id="wrapper1" ng-app="orderbid">
	<div class="loading" style="display:none;">Loading&#8230;</div>
	<header  class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<button aria-controls="navbar" aria-expanded="false" data-target="#navbar" data-toggle="collapse" class="navbar-toggle collapsed" type="button"> <span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </button>
				
				<a href="#" class="navbar-brand pull-left" style="padding-left:14px;">VU<i>X</i><span>VAPE COMMUNITY AND PRODUCTS</span></a> </div>
				 <div ng-controller="LoginCtrl" data-ng-init="init()" class="navbar-collapse collapse" id="navbar" aria-expanded="false" style="height: 1px;">
				 
					<ul class="nav navbar-nav navbar-right">
					<!--   <li><a aria-expanded="false" aria-haspopup="true" role="button" data-toggle="dropdown" href="#">Home</a></li> -->
					  <li><a href="#">About us</a></li>
					  <li><a href="#">Contact us</a></li>
					  <li class="dropdown"><a aria-expanded="false" aria-haspopup="true" role="button" data-toggle="dropdown" class="btn btn-default dropdown-toggle">sign up</a>
						<div class="panel panel-success"></div>
						<ul class="dropdown-menu form-box">
						<h3>Sign up</h3>
							<form>
							<span>{{registrationAlert}}</span>
								<li>
									<input id="username" type="text" name="username" size="30"  placeholder="Please Enter Your Name!" ng-model="registration.username" required/>
								</li>
								<div role="alert">
     							 <span class="error" ng-show="registration.username.$error.required">
       								Plaese Enter Your Name!</span>
    							</div>
								
								
								
								
								<li>
									<input id="email_address" type="email" name="email_address" size="30"  placeholder="Please Enter Your Email address" ng-model="registration.email" required/>
								</li>
								<div role="alert">
     							 <span class="error" ng-show="registration.email.$error.required">
       								Please Enter a Valid Email!</span>
    							</div>
								<li>
								<button type="submit" name="Register" ng-click="validate();">Sign up</button>
								</li>
							</form>
						</ul>
					  </li>
					   
					</ul>
				</div>
    </div>
	
	</header>

<div class="banner">

	<div class="container-fluid">
	
		<div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
  <!-- Indicators -->
  <ol class="carousel-indicators">
    <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
    <li data-target="#carousel-example-generic" data-slide-to="1"></li>
    <li data-target="#carousel-example-generic" data-slide-to="2"></li>
  </ol>

  <!-- Wrapper for slides -->
  <div class="carousel-inner" role="listbox">
    <div class="item active">
      <img src="images/3-1_720.jpg" width="1339px;" height="350px;" alt="World class logistic
vendors to choose from">
      <div class="carousel-caption">
       <!--  <h3>World class  vendors to choose from</h3> -->
      </div>
    </div>
    <div class="item">
      <img src="images/box-mod-atomizer-vapor_720.jpg" width="1339px;" height="350px;" alt="Register your E-Seller for product shipping">
      <div class="carousel-caption">
      <!--  <h3>Register your E-Seller for product shipping</h3> -->
      </div>
    </div>
	<div class="item">
      <img src="images/source-vapes-vaping-2-1024x641_720.jpg" width="1339px;" height="350px;" alt="Select the best carriers as per needs">
      <div class="carousel-caption">
       <!-- <h3>Select the best Logistic carriers as per </h3> -->
      </div>
    </div>
	
  </div>

  <!-- Controls -->
  <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
    <span class="sr-only">Previous</span>
  </a>
  <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
    <span class="sr-only">Next</span>
  </a>
</div>
	
	</div>

</div>

<main>

	<section class="main-container">
	
		<div class="container-fluid">
		
			<div class="row">
			
				<div class="col-lg-4 col-md-4 col-sm-4">
					<h2>Step 1</h2>
					<h4>Sign up now</h4>
				</div>
				
				<div class="col-lg-4 col-md-4 col-sm-4">
					<h2>Step 2</h2>
					<h4>Award the best bid</h4>
					
					<!--h6>warehouse & logistics</h6-->
				</div>
				
				<div class="col-lg-4 col-md-4 col-sm-4">
					<h2>Step 3</h2>
					<h4>Track order</h4>
				</div>
			
			</div>
	
		</div>
	
	</section>

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





<!--  ForGetPasswordModel -->








		<div class="modal fade modalMail" id="emailModal" role="dialog">
			<div ng-controller="LoginCtrl" class="modal-dialog">

		<!-- Modal content-->
				<div class="modal-dialog ">
					<div class="modal-content" id="modalcontent">
						<div class="modal-header" id="modalheader">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
							<h4 class="modal-title" id="modaltitle">Confirm Your E-Mail Address</h4>
						</div>
						<div class="modal-body">
							<!-- 	<div class="row">
					 
					</div> -->
							<div class="row">
								<div class="col-sm-12">
									<form method="post">
										<div class="form-group" id="formgroup">
											<label for="validate-email">Enter Email</label>
											<!-- <div class="input-group" data-validate="email"> -->
												<input type="text" class="form-control"  name="validate-email" id="validate-email" ng-model="login.email"
													placeholder="Enter your registered email address" required> 
											<!-- </div> -->
										</div>
									</form>
								</div>
							</div>
						</div>
						<div class="modal-footer" id="modalfooter">
							<button type="button" class="btn btn-primary"  style="    margin-right: 26px"
								ng-click="mailValidation()">SEND</button>
						</div>
					</div>
				</div>
			</div>
		</div>


















<!--  ForGetPasswordModel -->


















</div>









</body>
</html>