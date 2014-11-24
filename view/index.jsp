<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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

	<!-- navigator include -->
	<%@ include file="./navigator.jsp" %>
	
	<%
		if (user != null) {
	%>
	<jsp:forward page="UserControler.do">
		<jsp:param value="<%=Util.SESSION%>" name="type"/>
		<jsp:param value="<%=user.getU_id()%>" name="id"/>
		<jsp:param value="<%=user.getN_id()%>" name="nid"/>
	</jsp:forward>
	<%
		}
	%>
	
	 <!-- Content -->
    <header>
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <img class="img-responsive" src="./img/superman.gif" alt="logo">
                    <div class="intro-text">
                        <span class="name">S-Calendar</span>
                        <hr class="star-light">
                    </div>
                </div>
            </div>
        </div>
    </header>
    
    <!-- footer include -->
    <%@ include file="./footer.jsp" %>
    <%@ include file="./popup.jsp" %>
		
</body>
</html>