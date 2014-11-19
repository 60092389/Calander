<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="servlet.controler.UserControler"%>
<%@page import="DAO.UserDAO"%>
<%@page import="DTO.User"%>
<%@page import="Util.Util"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!-- css -->
<link href="./css/bootstrap.css" rel="stylesheet">
<link href="./css/bootstrap.min.css" rel="stylesheet">

<!-- Custom css -->
<link href="./css/freelancer.css" rel="stylesheet">

<!-- Custom Fonts -->
 <link href="font-awesome-4.1.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
 <link href="http://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css">
 <link href="http://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic" rel="stylesheet" type="text/css">

<!-- jQuery Version 1.11.0 -->
<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="./js/bootstrap.min.js"></script>

<title>S-Calendar</title>
</head>
<body id="page-top" class="index">

<%

	String navbarTitle1 = Util.LOGIN;
	String navbarTitle2 = Util.JOIN;
	String navbarTitle3 = "";
	String navbarTitle4 = "";
	
	String formPath1 = "#"+Util.LOGIN;
	String formPath2 = "#"+Util.JOIN;
	String formPath3 = "#"+Util.FRIENDS;	
	String formPath4 = "#"+Util.ADDFRIENDS;
	
	UserControler controler = new UserControler();
	UserDAO userDao = new UserDAO(); 
	User user = controler.isSession(request,userDao);
	
	if (user != null) {
		
		navbarTitle1 = Util.LOGOUT;
		navbarTitle2 = Util.USERUPDATE;
		
		if(!user.getU_id().equals(Util.ADMIN))
		{
			navbarTitle3 = Util.FRIENDS;
			navbarTitle4 = Util.ADDFRIENDS;
		}
		formPath1 = "UserControler.do?type="+navbarTitle1;
		formPath2 = "#"+Util.USERUPDATE;
		
	}
%>
    <nav class="navbar navbar-default navbar-fixed-top">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header page-scroll">
                <a class="navbar-brand" href="./index.jsp">S-Calendar</a>
            </div>
						
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav navbar-right">        
                    <li class="page-scroll">
                        <a href= <%=formPath3%> class="portfolio-link" data-toggle="modal"><%=navbarTitle3%></a>
                    </li>
                    <li class="page-scroll">
                        <a href= <%=formPath4%> class="portfolio-link" data-toggle="modal"><%=navbarTitle4%></a>
                    </li>
                    <li class="page-scroll">
                        <a href= <%=formPath1%> class="portfolio-link" data-toggle="modal"><%=navbarTitle1%></a>
                    </li>
                    <li class="page-scroll">                    
                        <a href= <%=formPath2 %> class="portfolio-link" data-toggle="modal"><%=navbarTitle2 %></a>
                        
                    </li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container-fluid -->
    </nav>
</body>
</html>