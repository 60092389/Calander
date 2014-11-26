<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="servlet.controler.UserControler"%>
<%@ page import="DAO.UserDAO"%>
<%@ page import="DTO.User"%>
<%@ page import="Util.Util"%>
<%@page import="java.util.*"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset = "UTF-8">


<title>S CALENDAR</title>
</head>
<body>

	<%
		UserDAO usersDao = new UserDAO(); 
		ArrayList<User> allUsers = null ;  				// user list 를 담는 객체
		allUsers = usersDao.getAllUsers();				// 모든 user list를 받는다
	%>
	
	
	
	<%	
	//AdminView에서 사용자들을 10명씩 보여줌
	int pageNo = 1;
	try{	
		pageNo = Integer.parseInt(request.getParameter("pageNo"));
		}catch(NumberFormatException e){}
	UserControler uControler = new UserControler();
	int numInPage = 10;
	int startPos = uControler.getStartPos(pageNo,numInPage);
	int numPages = uControler.getNumPages(pageNo,numInPage);
	
	List<User> users = (ArrayList<User>)uControler.getPageUsers(startPos,numInPage);
	
%> 


<% 
//페이지 넘기는 버튼 함수
	int startPage, endPage;
	int delta = 5;
	startPage = (pageNo <= delta)? 1: pageNo - delta;
	endPage = startPage + (delta*2) +1;
%>


</body>
</html>