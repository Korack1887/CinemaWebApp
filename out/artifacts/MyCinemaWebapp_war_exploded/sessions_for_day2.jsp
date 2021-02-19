<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>BizPage Bootstrap Template</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="" name="keywords">
    <meta content="" name="description">

    <!-- Favicons -->
    <link href="img/favicon.png" rel="icon">
    <link href="img/apple-touch-icon.png" rel="apple-touch-icon">

    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,700,700i|Montserrat:300,400,500,700" rel="stylesheet">

    <!-- Bootstrap CSS File -->
    <link href="lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Libraries CSS Files -->
    <link href="lib/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <link href="lib/animate/animate.min.css" rel="stylesheet">
    <link href="lib/ionicons/css/ionicons.min.css" rel="stylesheet">
    <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
    <link href="lib/lightbox/css/lightbox.min.css" rel="stylesheet">

    <!-- Main Stylesheet File -->
    <link href="css/style.css" rel="stylesheet">

    <!-- =======================================================
      Theme Name: BizPage
      Theme URL: https://bootstrapmade.com/bizpage-bootstrap-business-template/
      Author: BootstrapMade.com
      License: https://bootstrapmade.com/license/
    ======================================================= -->
</head>

<body>
<header id="header">
    <div class="container-fluid">
        <div id="logo" class="pull-left">
            <h1><a href="#intro" class="scrollto">BizPage</a></h1>
            <!-- Uncomment below if you prefer to use an image logo -->
            <!-- <a href="#intro"><img src="img/logo.png" alt="" title="" /></a>-->
        </div>

        <nav id="nav-menu-container">
            <ul class="nav-menu">
                <li class="menu-active"><a href="/hello">Home</a></li>
                <li><a href="/sign_in">Login</a></li>
                <li><a href="/login">Register</a></li>
                <li><a href="/makeOrder" class="icon ion-android-cart"></a></li>
                <li><c:if test="${not empty sessionScope.ses_role}">
                    <a href="/logout"><h2>Log out</h2></a>
                </c:if></li>
            </ul>
        </nav><!-- #nav-menu-container -->
    </div>
</header>
<br>



<main id="main">

    <c:set var="film" value="${sessions[0].film}"></c:set>
    <div class="container">
        <h3>Sort by: |<a href="/sessions_for_day?day_of_week=${day}&sort_content=">Date</a>|
            |<a href="/sessions_for_day?day_of_week=${day}&sort_content=seat">Seats</a>|
            |<a href="/sessions_for_day?day_of_week=${day}&sort_content=film">Films</a>|<br></h3>
        <div class="row">
            <c:choose>
                <c:when test="${sessionScope.sort_content eq 'film'}">
                    <c:forEach var="s" items="${sessions}">
                        <div class="col-sm-2">
                            <a href="seats?session_id=${s.id}" class="dates">
                                    ${s.dayAndMonthAndTime}
                            </a>
                        </div>
                        <div class="col-sm-4">
                            <h4><b>Film name: </b>${s.film.name}</h4>
                        </div>
                    </c:forEach>
                </c:when>
                <c:when test="${sessionScope.sort_content eq 'seat'}">
                    <c:forEach var="sessionSeat" items="${sessionsSeat}">
                        <div class="col-sm-2">
                            <a href="seats?session_id=${sessionSeat.key.id}" class="dates">
                                    ${sessionSeat.key.dayAndMonthAndTime}
                            </a>
                        </div>
                        <div class="col-sm-4">
                            <h4><b>Film name: </b>${sessionSeat.key.film.name}</h4>
                        </div>
                        <div class="col-sm-4">
                            <h4><b>Available seats: </b>${sessionSeat.value}</h4>
                        </div>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <c:forEach var="s" items="${sessions}">
                    <div class="col-sm-2">
                        <a href="seats?session_id=${s.id}" class="dates">
                                ${s.dayAndMonthAndTime}
                        </a>
                    </div>
                        <div class="col-sm-4">
                            <h4><b>Film name: </b>${s.film.name}</h4>
                        </div>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
    <br>
</main>
<!--container end.//-->

<script src="lib/jquery/jquery.min.js"></script>
<script src="lib/jquery/jquery-migrate.min.js"></script>
<script src="lib/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="lib/easing/easing.min.js"></script>
<script src="lib/superfish/hoverIntent.js"></script>
<script src="lib/superfish/superfish.min.js"></script>
<script src="lib/wow/wow.min.js"></script>
<script src="lib/waypoints/waypoints.min.js"></script>
<script src="lib/counterup/counterup.min.js"></script>
<script src="lib/owlcarousel/owl.carousel.min.js"></script>
<script src="lib/isotope/isotope.pkgd.min.js"></script>
<script src="lib/lightbox/js/lightbox.min.js"></script>
<script src="lib/touchSwipe/jquery.touchSwipe.min.js"></script>
<!-- Contact Form JavaScript File -->
<script src="contactform/contactform.js"></script>

<!-- Template Main Javascript File -->
<script src="js/main.js"></script>

</body>
</html>

