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

  <!--==========================
    Header
  ============================-->
  <header id="header">
    <div class="container-fluid">
      <button class="btn btn-info"><a href="/changeLang">UA</a></button>
      <button class="btn btn-info"><a href="/changeLang?lang=eng">ENG</a></button>
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
  </header><!-- #header -->


  <!-- Main -->


      <br>
      <br>
      <!-- Boxes -->
      <section id="team">
        <div class="container">
          <div class="section-header wow fadeInUp">
            <h3>Team</h3>
            <c:choose>
              <c:when test="${sessionScope.session_lang eq 'eng'}">
                <p><a href="/hello?sort_content=film">|Sort by film name|</a></p>
            <p><a href="/hello?sort_content=">|Sort by session date|</a></p>
                <c:if test="${sessionScope.sort_content eq 'film'}">
            <p><a href="/hello?sort_content=film&all_film=true">|All films|</a></p>
                </c:if>
              </c:when>
              <c:otherwise>
            <p> <a href="/hello?sort_content=film">|Сортування за назвою фільма|</a></p>
            <p> <a href="/hello?sort_content=">|Сортування за датою сеансу|</a></p>
                <c:if test="${sessionScope.sort_content eq 'film'}">
            <p> <a href="/hello?sort_content=film&all_film=true">|Усі фільми|</a></p>
                </c:if>
              </c:otherwise>
            </c:choose>
          </div>
        <c:choose>
          <c:when test="${sessionScope.sort_content eq 'film'}">
          <div class="row">
            <c:forEach var="film" items="${films}">
              <div class="col-sm-4 wow fadeInUp">
                <div class="member">
                  <img src="/images/${film.image}" class="img-fluid" alt="">
                <div class="member-info">
                  <div class="member-info-content">
                  <c:choose>
                    <c:when test="${sessionScope.session_lang eq 'eng'}">
                      <h3>${film.name}</h3>
                      <p>${film.description}</p>
                    </c:when>
                    <c:otherwise>
                      <h3>${film.nameUa}</h3>
                      <p>${film.descriptionUa}</p>
                    </c:otherwise>
                  </c:choose>
                  <a href="/session_for_film?id_film=${film.id}" class="button fit">Watch</a>
                  </div>
                </div>
              </div>
              </div>
            </c:forEach>
          </div>
          </c:when>


          <c:otherwise>
            <div class="row">
            <c:forEach var="date" items="${dates}">
            <div class="col wow fadeInUp">
                <div class="member-info">
                  <div class="member-info-content">
                  <h4>${date}</h4>
                  <a href="/sessions_for_day?day_of_week=${date}" class="button fit" data-poptrox="youtube,800x400">Watch</a>
                </div>
              </div>
            </div>
            </c:forEach>
            </div>
          </c:otherwise>
        </c:choose>
    </div>
    </section>
  <footer>
  </footer>

  <!-- JavaScript Libraries -->
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
