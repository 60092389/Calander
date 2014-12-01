<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="DTO.User" %>
 <%@ page import="DAO.FriendDAO" %>
 <%@ page import="java.util.*" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>S-Calendar</title>
</head>
<body>
	<%@include file="./navigator.jsp"%>
<%
	User friend = (User)request.getAttribute("friend");
	UserControler uCont = new UserControler(); 
	boolean isFriend = uCont.isFriend(Integer.parseInt(user.getN_id()),Integer.parseInt(friend.getN_id()));
%>
	
	<header>
		<div class="backgro">
			<section id="about">
				<div class="container">					
					<table class="table table-bordered table-stripped">
						<thead>
							<tr>								
								<th>USER ID</th>								
								<th>NAME</th>
								<th>REQUEST</th>
							</tr>
						</thead>
						<tbody>
							<%
								//찾는 친구 가 존재 한다면 
								if(friend != null && !friend.getU_id().equals(Util.ADMIN) && !friend.getU_id().equals(user.getU_id())) { 
								// 친구를 출력한다.						
							%>
							
							<tr class="success">							
								<td><%=friend.getU_id()%></td>								
								<td><%=friend.getName()%></td>
								<td>
								<%if(!isFriend){%>
									<a
									href="UserControler.do?type=<%=Util.REQUEST%>&<%=Util.FID%>=<%=friend.getN_id()%>&<%=Util.USERID%>=<%=user.getN_id() %>"
									class="btn btn-warning" data-toggle="modal"> 요청하기</a> 
									<%}else{ %>
									이미 친구 입니다.
									<%} %>
								</td>
							</tr>
							<%
 								}else{
							%>
							<tr class="success">							
								<td colspan="3"><h3>찾는 친구가 없습니다.</h3></td>								
							</tr>
							<%} %>						
						</tbody>
					</table>
					<a href="<%=Util.MAINPATH %>" class="btn btn-default btn">돌아가기</a>
				</div>
			</section>
		</div>
	</header>
	<%@ include file="./popup.jsp"%>
	<%@ include file="./footer.jsp"%>
</body>
</html>