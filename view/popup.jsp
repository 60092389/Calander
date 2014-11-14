<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="servlet.controler.UserControler"%>
<%@ page import="DAO.UserDAO"%>
<%@ page import="DTO.User"%>
<%@ page import="Util.Util"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset=UTF-8">

<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>

<title>S-Calendar</title>
</head>
<body>

	<%
		String title = Util.JOIN;
		String type = Util.JOIN;
		String SessionId = null;
		String valueId = "";
		String valueName = "";

		UserDAO popUserDao = new UserDAO();
		UserControler popcontroler = new UserControler();
		User popUser = popcontroler.isSession(request, popUserDao);

		if (popUser != null) {
			title = Util.USERUPDATE;
			type = Util.USERUPDATE;
			valueId = popUser.getU_id();
			valueName = popUser.getName();
		}
	%>
	<div class="portfolio-modal modal fade" id=<%=Util.LOGIN%>
		tabindex="-1" role="dialog" aria-hidden="true">
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

							<form class="form-horizontal" name="sentMessage" id="loginForm"
								method="post" action="UserControler.do?type=<%=Util.LOGIN%>">

								<div class="form-group">
									<label for="inputId" class="col-lg-2 control-label">Id</label>
									<div class="col-lg-10">
										<input type="text" class="form-control" id="inputLoginId"
											name="inputLoginId" placeholder="ID">
									</div>
								</div>
								<div class="form-group">
									<label for="inputPassword" class="col-lg-2 control-label">Password</label>
									<div class="col-lg-10">
										<input type="password" class="form-control"
											id="inputLoginPassword" name="inputLoginPassword"
											placeholder="Password">
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

	<div class="portfolio-modal modal fade" id="<%=Util.JOIN%>"
		tabindex="-1" role="dialog" aria-hidden="true">
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
							<h2>Join</h2>
							<hr class="star-primary">

							<form class="form-horizontal" name="sentMessage" id="contactForm"
								method="post" action="UserControler.do?type=<%=Util.JOIN%>">

								<div class="row form-group">
									<label for="inputEmail" class="col-lg-2 control-label">Id</label>
									<div class="col-lg-10">
										<input type="text" class="form-control" id="inputSignupId"
											name="inputSignupId" placeholder="ID">
									</div>
								</div>
								<div class="row form-group">
									<label for="inputPassword" class="col-lg-2 control-label">Password</label>
									<div class="col-lg-10">
										<input type="password" class="form-control"
											id="inputSignupPassword" name="inputSignupPassword"
											placeholder="Password">
									</div>
								</div>

								<div class="row form-group">
									<label for="inputPassword" class="col-lg-2 control-label">Confirm
									</label>
									<div class="col-lg-10">
										<input type="password" class="form-control"
											id="inputSignupPasswordConfirm"
											name="inputSignupPasswordConfirm"
											placeholder="Password Confirm">
									</div>
								</div>
								<div class="row form-group">
									<label for="inputName" class="col-lg-2 control-label">Name</label>
									<div class="col-lg-10">
										<input type="text" class="form-control" id="inputSignupName"
											name="inputSignupName" placeholder="Name">
									</div>
								</div>
								<div class="row">
									<div class="form-group col-xs-12">

										<button type="submit" class="btn btn-success btn-lg">Join</button>
										<button type="reset" class="btn btn-danger btn-lg">Cancel</button>

									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="portfolio-modal modal fade" id="addschedule" tabindex="-1"
		role="dialog" aria-hidden="true">
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
							<h2>Add Schedule</h2>
							<hr class="star-primary">

							<form class="form-horizontal" name="addSchedule" id="addSchedule"
								method="post" action="CalendarConroller.go">

								<div class="row form-group">
									<label for="inputTitle" class="col-lg-2 control-label">TITLE</label>
									<div class="col-lg-10">
										<input type="text" class="form-control" id="inputTitle"
											name="inputTitle" placeholder="TITLE">
									</div>
								</div>

								<div class="row form-group">
									<label for="inputDate" class="col-lg-2 control-label">PICK
										DATE</label>
									<div class="col-lg-10">
										<input type="date" class="form-control" id="inputDay"
											name="inputDay" placeholder="DAY">
									</div>
								</div>

								<div class="row form-group">
									<label for="inputText" class="col-lg-2 control-label">CONTENTS</label>
									<div class="col-lg-10">
										<textarea class="form-control" rows="4" id="inputContents"
											name="inputContents" form="addSchedule"></textarea>
									</div>
								</div>


								<div class="row">
									<div class="form-group col-xs-12" align="center">

										<button type="submit" class="btn btn-success btn-lg">등록</button>
										<button type="reset" class="btn btn-danger btn-lg">취소</button>

									</div>
								</div>

								<input type="hidden" name="whatRequest" value="addSchedule">
							</form>

						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="portfolio-modal modal fade" id="Friends" tabindex="-1"
		role="dialog" aria-hidden="true">
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
							<h2>Friends</h2>
							<hr class="star-primary">

							<form class="form-horizontal" name="receiveMessage"
								id="contactForm" method="post"
								action="UserControler.do?type=<%%>">

								<table id="friendstable" width="600" align="center" border="1">

									<tr>
										<td width="300">
											<div class="row form-group">
												<label for="friendsidsch" class="col-lg-2 control-label">ID</label>
											</div>
										</td>
										<td width="150">
											<div class="row form-group">
												<label for="friendsshowsch" class="col-lg-2 control-label">view</label>
											</div>
										</td>
										<td width="150">
											<div class="row form-group">
												<label for="friendsdeletesch" class="col-lg-2 control-label">delete</label>
											</div>
										</td>
									</tr>

								</table>
								
								<table id="friendstableview" width="600" align="center" border="1">

									<tr>
										<td width="300">
											<div class="row form-group">
												<label for="friendsid" class="col-lg-2 control-label"></label>
											</div>
										</td>
										<td width="150">
											<div class="row form-group">
												<label for="friendsshow" class="col-lg-2 control-label">
												<input type = "submit" class = "btn btn-success btn-lg" value = "view"></label>
											</div>
										</td>
										<td width="150">
											<div class="row form-group">
												<label for="friendsdelete" class="col-lg-2 control-label">
												<input type = "submit" class = "btn btn-danger btn-lg" value = "delete"></label>
											</div>
										</td>
									</tr>

								</table>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>