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

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>S CALENDAR</title>
</head>

<body>
	<%@include file="./navigator.jsp"%>

	<%
	ArrayList<User> allUsers = null ;  				// user list 를 담는 객체
	allUsers = userDao.getAllUsers(); 				// 모든 user list를 받는다 
	%>

	
	<header>
	<div class="backgro">
	
			<div class="row">
			<div class="col-md-12 page-info">
				<div class="pull-left">
					Total <b><%=allUsers.size() %></b> users
				</div>
				<div class="pull-right">
					<b>${users.page }</b> page / total <b>${users.numPages }</b> pages
				</div>
			</div>
		</div>
	
<section id="about">
<div class="container">
		<table class="table table-bordered table-stripped">
			<thead>
				<tr>
					<th>NUMBER ID</th>
					<th>USER ID</th>
					<th>PASSWORD</th>
					<th>NAME</th>
					<th>DELETE</th>
				</tr>
			</thead>
			<tbody>
						<% 
						//user List 가 존재 한다면 
						if(allUsers != null) 
						{ 
									
									// 모든 user list를 반복문으로 출력한다.
									int i;
									for(i=0;i<allUsers.size();i++)
									{  

							%>
						
						<!--  User list 를 출력한다 -->
						<tr class="success">
						
							<td><%=allUsers.get(i).getN_id()%></td>
							<td><%=allUsers.get(i).getU_id()%></td>
							<td><%=allUsers.get(i).getPassword()%></td>
							<td><%=allUsers.get(i).getName()%></td>
							
						
							<td>
							<% if(!allUsers.get(i).getU_id().equals(Util.ADMIN)){ %>
								 <a href="UserControler.do?type=<%=Util.USERDELETEBTN%>&<%=Util.USERID%>=<%=allUsers.get(i).getN_id()%>"
								  class="btn btn-warning" data-toggle="modal"> <%=Util.USERDELETEBTN%> </a>
								<%
								}
								%>
							
						 </td>
						</tr>
				<%  
				     }
						}
				%>
			
			
			
			</tbody>

		</table>
		<jsp:include page="page.jsp">
			<jsp:param name="currentPage" value="${users.page}" />
			<jsp:param name="url" value="user" />
			<jsp:param name="startPage" value="${users.startPageNo}" />
			<jsp:param name="endPage" value="${users.endPageNo}" />
			<jsp:param name="numPages" value="${users.numPages}" />
		</jsp:include>
</div>
</section>
</div>
</header>

	<%@ include file="./footer.jsp"%>
</body>
</html>