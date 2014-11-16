<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
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

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>S CALENDAR</title>
</head>

<body>
	<%@include file="./navigator.jsp"%>
	
			<div class="row">
			<div class="col-md-12 page-info">
				<div class="pull-left">
					Total <b>${users.numItems }</b> users
				</div>
				<div class="pull-right">
					<b>${users.page }</b> page / total <b>${users.numPages }</b> pages
				</div>
			</div>
		</div>
	
<section id="about">
		<table class="table table-bordered table-stripped">
			<thead>
				<tr>
					<th>N_ID</th>
					<th>U_ID</th>
					<th>PASSWORD</th>
					<th>NAME</th>
					<th>DELETE</th>
				</tr>
			</thead>
			<tbody>
			<tr>
				<th> </th>
				<th></th>
				<th></th>
				<th></th>
				<th></th>
			
			</tbody>

		</table>
</section>
		<jsp:include page="page.jsp">
			<jsp:param name="currentPage" value="${users.page}" />
			<jsp:param name="url" value="user" />
			<jsp:param name="startPage" value="${users.startPageNo}" />
			<jsp:param name="endPage" value="${users.endPageNo}" />
			<jsp:param name="numPages" value="${users.numPages}" />
		</jsp:include>

	<%@ include file="./footer.jsp"%>
</body>
</html>