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
	<%@include file="./Page.jsp" %>



	<header>
		<div class="backgro">
			<section id="about">
				<div class="container">
					<div>
						Total <b><%=allUsers.size()%></b> users
					</div>
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
								if(users != null) { 
								// 모든 user list를 반복문으로 출력한다.
								int i;
								for(i=0;i<users.size();i++) {
							%>

							<!--  User list 를 출력한다 -->
							<tr class="success">

								<td><%=users.get(i).getN_id()%></td>
								<td><%=users.get(i).getU_id()%></td>
								<td><%=users.get(i).getPassword()%></td>
								<td><%=users.get(i).getName()%></td>


								<td>
									<%
										if(!users.get(i).getU_id().equals(Util.ADMIN)){
									%> <a
									href="UserControler.do?type=<%=Util.USERDELETEBTN%>&<%=Util.USERID%>=<%=users.get(i).getN_id()%>"
									class="btn btn-warning" data-toggle="modal"> <%=Util.USERDELETEBTN%>
								</a> <%	} %>

								</td>
							</tr>
							<%
								}
							}
							%>



						</tbody>

					</table>
					
					
					
				</div>
				
				<div class="text-center">
					<ul class="pagination">
					
					<%
						if(endPage > numPages){
							endPage = numPages;
						}
						if(pageNo <= 1){
					%>
					<li class="disabled"><a href="#">&laquo;</a>
				<%	} else { %>
					<li><a href="AdminView.jsp?pageNo=<%=pageNo-1 %>">&laquo;</a></li>
				<%	}
						String className = "";
						for(int i = startPage; i<= endPage; i++){
							className = (i == pageNo)? "active" : "";
					%>
					<li	class="<%=className%>">
						<a href=./AdminView.jsp?pageNo=<%=i%>><%=i%></a>
					</li>
				<%	}
						if(pageNo >= numPages){
					%>
					<li class="disabled"><a href="#">&raquo;</a></li>
				<%	} else { %>
					<li><a href="AdminView.jsp?pageNo=<%=pageNo+1 %>">&raquo;</a></li>
				<%	}%>
			
				</ul>
			</div>
				
			</section>
		</div>
		
	</header>

	<%@ include file="./footer.jsp"%>
</body>
</html>