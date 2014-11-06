<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset=UTF-8">

<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>

<title>S-Calendar</title>
</head>
<body>
		<div class="portfolio-modal modal fade" id="Login" tabindex="-1" role="dialog" aria-hidden="true">
		<div class="modal-content">
			<div class="close-modal" data-dismiss="modal">
				<div class="lr">
					<div class="rl"></div>
				</div>
			</div>
			<div class="container">
				<div class="row">
					<div class="col-lg-8 col-lg-offset-2">
						<div class="modal-body">
							<h2>Login</h2>
							<hr class="star-primary">

							<form class="form-horizontal" name="sentMessage" id="loginForm" method="post" action ="">
							
								<div class="form-group">
									<label for="inputId" class="col-lg-2 control-label">Id</label>
									<div class="col-lg-10">
										<input type="text" class="form-control" id="inputLoginId" name = "inputLoginId" placeholder="ID" >
									</div>
								</div>
								<div class="form-group">
									<label for="inputPassword" class="col-lg-2 control-label">Password</label>
									<div class="col-lg-10">
										<input type="password" class="form-control" id="inputLoginPassword" name = "inputLoginPassword" placeholder="Password" >
									</div>
								</div>
								
								<div class="row">
									<div class="form-group col-xs-12">
								
										<button type="submit" class="btn btn-success btn-lg">submit</button>
										<button type="reset" class="btn btn-danger btn-lg">cancel</button>

									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<div class="portfolio-modal modal fade" id="signup" tabindex="-1" role="dialog" aria-hidden="true">
		<div class="modal-content">
			<div class="close-modal" data-dismiss="modal">
				<div class="lr">
					<div class="rl"></div>
				</div>
			</div>
			<div class="container">
				<div class="row">
					<div class="col-lg-8 col-lg-offset-2">
						<div class="modal-body">
							<h2>Sign up</h2>
							<hr class="star-primary">

							<form class="form-horizontal" name="sentMessage" id="contactForm" method="post">
	
									<div class="row form-group">
									<label for="inputEmail" class="col-lg-2 control-label">Id</label>
									<div class="col-lg-10">
										<input type="text" class="form-control" id="inputEmail" name = "inputEmail" placeholder="ID" >
									</div>
								</div>
								<div class="row form-group">
									<label for="inputPassword" class="col-lg-2 control-label">Password</label>
									<div class="col-lg-10">
										<input type="password" class="form-control" id="inputPassword" name = "inputPassword"
											placeholder="Password" >
									</div>
								</div>
								
								<div class="row form-group">
									<label for="inputPassword" class="col-lg-2 control-label">Confirm </label>
									<div class="col-lg-10">
										<input type="password" class="form-control" id="inputPasswordConfirm" name = "inputPasswordConfirm"
											placeholder="Password Confirm"  >
									</div>
								</div>
								<div class="row form-group">
									<label for="inputName" class="col-lg-2 control-label">Name</label>
									<div class="col-lg-10">
										<input type="text" class="form-control" id="inputName" name = "inputName"  placeholder="Name" >
									</div>
								</div>
								<div class="row">
									<div class="form-group col-xs-12">
								
										<button type="submit" class="btn btn-success btn-lg">join</button>
										<button type="reset" class="btn btn-danger btn-lg">cancel</button>

									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	

</body>
</html>