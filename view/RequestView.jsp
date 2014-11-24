<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>S Calendar</title>
</head>
<body>
<%@include file="./navigator.jsp"%>
<%
	List<User> requesterList = (ArrayList)request.getAttribute("reqlist");
%>
<header>
		<div class="backgro">
			<section id="about">					
				<div class="container">		
				<h1>친구요청</h1>			
					<table class="table table-bordered table-stripped">
						<thead>
							<tr>								
								<th>NAME</th>
								<th>ACCEPT</th>
								<th>REJECT</th>								
							</tr>
						</thead>
						<tbody>
							<%
								//찾는 친구 가 존재 한다면 
								for(int i=0;i<requesterList.size();i++) { 
								// 친구를 출력한다.		
								System.out.println(requesterList.get(i).getName());
							%>
							
							<tr class="success">							
								<td><%=requesterList.get(i).getName()%></td>	
																					
								<td>
									<form  name="sentMessage" id="contactForm"
										method="post" action="UserControler.do?type=<%=Util.ACCEPTREQ%>">
									<input type="hidden" name="friednid" value="<%=requesterList.get(i).getN_id() %>" />
									<input type="hidden" name="<%=Util.USERID%>" value="<%=user.getN_id()%>" />
									<button type="submit" class="btn btn-success">ACCEPT</button>
									</form>
								</td>								
								<td>
									<a
									href="UserControler.do?type=<%=Util.REJECTREQ%>&<%=Util.USERID%>=<%=user.getN_id()%>&
									friednid=<%=requesterList.get(i).getN_id() %>"
									class="btn btn-warning" data-toggle="modal"> REJECT	</a>
								</td>
							</tr>
							<%
 								}
							%>											
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