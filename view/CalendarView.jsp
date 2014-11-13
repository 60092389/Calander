<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<!DOCTYPE HTML >
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link href="../css/bootstrap.css" rel="stylesheet">
<link href="../css/bootstrap.min.css" rel="stylesheet">

<!-- Custom css -->
<link href="../css/calendar.css" rel="stylesheet">

<!-- font  -->
<link href="../font-custom/css/font-awesome.min.css" rel="stylesheet"
	type="text/css">

<!-- jQuery Version 1.11.0 -->
<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="./js/bootstrap.min.js"></script>
<script src="./js/calendar.js"></script>

<title>S CALENDAR</title>

</head>
<body>
	<%@include file="./navigator.jsp"%>

	<%
		Calendar cr = Calendar.getInstance();
		int year = cr.get(Calendar.YEAR);
		int month = cr.get(Calendar.MONTH);
		int date = cr.get(Calendar.DATE);

		String today = year + ":" + (month + 1) + ":" + date;

		String input_year = request.getParameter("year");
		String input_month = request.getParameter("month");

		if (input_year != null) {
			year = Integer.parseInt(input_year);
		}
		if (input_month != null) {
			month = Integer.parseInt(input_month);
		}

		cr.set(year, month, 1);

		int startDate = cr.getMinimum(Calendar.DATE);
		int endDate = cr.getActualMaximum(Calendar.DATE);
		int startDay = cr.get(Calendar.DAY_OF_WEEK);

		int count = 0;
	%>
	<!-- About Section -->
	<section id="about">
		<div class="container">
			<div class="row">
				<div class="col-lg-12 text-center">
					<br>
					<h2>Calendar</h2>
					<hr class="star-primary">
				</div>
			</div>
			<div class="row">

				<form method="post" action="" name="change">
					<table width="400" cellpadding="2" cellspacing="0" border="0"
						align="center">

						<tr>
							<td width="140" align="right"><input type="button" value="◁"
								onClick="monthDown(this.form)" class="btn btn-default"></td>
							<td width="120" align="center"><select name="year"
								onchange="selectCheck(this.form)">

									<%
										for (int i = year - 10; i < year + 10; i++) {
											String selected = (i == year) ? "selected" : "";
											String color = (i == year) ? "#CCCCCC" : "#FFFFFF";
											out.print("<option value = " + i + " " + selected
													+ " style=background:" + color + ">" + i + "</option>");
										}
									%>
							</select><select name="month" onchange="selectCheck(this.form)"
								class="col-lg-2 control-label">
									<%
										for (int i = 1; i <= 12; i++) {
											String selected = (i == month + 1) ? "selected" : "";
											String color = (i == month + 1) ? "#CCCCCC" : "#FFFFFF";
											out.print("<option value=" + i + " " + selected
													+ " style=background:" + color + ">" + i + "</option>");
										}
									%>
							</select></td>
							<td width="140"><input type="button" value="▷"
								onClick="monthUp(this.form)" class="btn btn-default"></td>
						</tr>
						<tr>
							<td align="right" colspan="3"><a href="./CalendarView.jsp"><font
									size="2">오늘 : <%=today%></font></a></td>
						</tr>
					</table>
					<input type="hidden" name="whatRequest" value="changeMonth">

				</form>
				<table cellpadding="2" cellspacing="0" border="1" align="center"
					class="table table-striped table-hover">
					<tr align="center">
						<td><font size="2">일</font></td>
						<td><font size="2">월</font></td>
						<td><font size="2">화</font></td>
						<td><font size="2">수</font></td>
						<td><font size="2">목</font></td>
						<td><font size="2">금</font></td>
						<td><font size="2">토</font></td>
					</tr>
					<tr>
						<%
							for (int i = 1; i < startDay; i++) {
								count++;
						%>
						<td>&nbsp;</td>
						<%
							}
							for (int i = startDate; i <= endDate; i++) {
								String bgcolor = (today.equals(year + ":" + (month + 1) + ":"
										+ i)) ? "#CCCCCC" : "#FFFFFF";
								String color = (count % 7 == 0 || count % 7 == 6) ? "red"
										: "black";
								count++;
						%>
						<td bgcolor="<%=bgcolor%>"><font size="2" color=<%=color%>><%=i%></font>

						</td>
						<%
							if (count % 7 == 0 && i < endDate) {
						%>
					</tr>
					<tr height="30">
						<%
							}
							}
							while (count % 7 != 0) {
						%>
						<td>&nbsp;</td>
						<%
							count++;
							}
						%>
					</tr>
				</table>
				<div id="add_sch">

					<a href=#addschedule class="portfolio-link" data-toggle="modal">
						<input type="button" value="일정추가" class="btn btn-default">
					</a>

				</div>
			</div>
		</div>
	</section>

	<%@ include file="./popup.jsp"%>
	<%@ include file="./footer.jsp"%>
</body>
</html>
