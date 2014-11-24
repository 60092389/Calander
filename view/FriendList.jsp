<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>

<!DOCTYPE html>
<html lang="ko">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


<link href="../css/bootstrap.css" rel="stylesheet">
<link href="../css/bootstrap.min.css" rel="stylesheet">

<!-- Custom css -->
<link href="../css/freelancer.css" rel="stylesheet">

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
		ArrayList<User> friendList = (ArrayList<User>)request.getAttribute("friendList") ;					
	%>


	<header>
		<div class="backgro">
			<section id="about">
				<div class="container">
					<div>
						Total <b><%=friendList.size()%></b> friends
					</div>
					<table class="table table-bordered table-stripped">
						<thead>
							<tr>								
								<th>USER ID</th>
								<th>NAME</th>
								<th>SHOWSCHEDULE</th>
							</tr>
						</thead>
						<tbody>
							<%
								//user List 가 존재 한다면 
								if(friendList != null) { 
								// 모든 user list를 반복문으로 출력한다.
								int i;
								for(i=0;i<friendList.size();i++) {
							%>

							<!--  User list 를 출력한다 -->
							<tr class="success">						
								<td><%=friendList.get(i).getU_id()%></td>							
								<td><%=friendList.get(i).getName()%></td>


								<td>
									<a href="" class= "btn btn-success">SHOWSCHEDULE</a>
								</td>
							</tr>
							<%
								}
							}
							%>

						</tbody>

					</table>					
				</div>
			</section>
		</div>
	</header>
	<%@ include file="./popup.jsp"%>
	<%@ include file="./footer.jsp"%>

</body>
</html>