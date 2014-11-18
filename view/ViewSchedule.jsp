<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="servlet.controler.UserControler"%>
<%@page import="DAO.UserDAO"%>
<%@page import="DTO.User"%>
<%@page import="Util.Util"%>
<%@page import="DTO.Schedule"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- css -->
<link href="./css/bootstrap.css" rel="stylesheet">
<link href="./css/bootstrap.min.css" rel="stylesheet">

<!-- Custom css -->
<link href="./css/freelancer.css" rel="stylesheet">

<!-- font  -->
<link href="./font-custom/css/font-awesome.min.css" rel="stylesheet"
	type="text/css">

<!-- jQuery Version 1.11.0 -->
<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
<!-- Bootstrap Core JavaScript -->
<script src="./js/bootstrap.min.js"></script>
<title>SCalendar</title>
</head>
<body>
	<%
		Schedule schedule = (Schedule) request
				.getAttribute("singleSchedule");
		String date = schedule.getYear() + "-" + schedule.getMonth() + "-"
				+ schedule.getDay();
		System.out.println("at view schedule : " + date);
	%>
	<%@ include file="./navigator.jsp"%>
	<header>
		<div class="backgro">
			<div class="container">
				<div class="showdetail">
					<br>
					<form class="form-horizontal" method="post">
						<fieldset>

							<legend>Schedule Detail</legend>
							<div class="form-group">
								<label for="inputDefault" class="col-lg-2 control-label">Title</label>
								<div class="col-lg-9">
									<input type="text" class="form-control" id="inputTitle"
										name="inputTitle" value="<%=schedule.getTitle()%>">
								</div>
							</div>

							<div class="form-group">
								<label for="inputDefault" class="col-lg-2 control-label">Scheduled
									Date </label>
								<div class="col-lg-9">
									<input type="date" class="form-control" id="inputDay"
										name="inputDay" value=<%=date%>>
								</div>
							</div>

							<div class="form-group">
								<label for="textArea" class="col-lg-2 control-label">Contents</label>
								<div class="col-lg-9">
									<textarea class="form-control" rows="3" id="inputContents"
										name="inputContents"><%=schedule.getContents()%></textarea>
								</div>
							</div>


							<div class="form-group">
								<div class="col-lg-10 col-lg-offset-1">
									<a href=<%=Util.MAINPATH%> class="btn btn-default">확인</a>
									<button type="submit" class="btn btn-primary">수정</button>
									<a href="CalendarController.go?type=<%=Util.SCHEDULEDELETE%>&scheduleid=<%=schedule.getS_id()%>"
										class="btn btn-warning" data-toggle="modal"> 일정삭제 </a>
								</div>
							</div>
						</fieldset>
						<input type="hidden" value=<%=schedule.getS_id()%> id="scheduleId" name="scheduleId"> 
						<input type="hidden" name="whatRequest" value="updateSchedule">

					</form>
				</div>
			</div>
		</div>
	</header>

	<%@ include file="./footer.jsp"%>

</body>
</html>