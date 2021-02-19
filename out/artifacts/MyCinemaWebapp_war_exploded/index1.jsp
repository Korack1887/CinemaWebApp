<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!doctype html>
<html lang="en">
	<head>
		<title>Full Motion</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<link rel="stylesheet" href="assets/css/main.css"/>
	</head>
	<button><a href="/changeLang">UA</a></button>
	<button><a href="/changeLang?lang=eng">ENG</a></button>
	<div class="dropdown">
		<span><a class="icon fa-bars"></a></span>
		<div class="dropdown-content">
			<button><a href="/sign_in"><h2>Login</h2></a></button>
			<button><a href="/login"><h2>Register</h2></a></button>
			<c:if test="${not empty sessionScope.ses_role}">
				<button><a href="/logout"><h2>Log out</h2></a></button>
			</c:if>
		</div>
	</div>
	<div class="cart">
	<a href="/makeOrder" class="icon fa-shopping-cart"><span class="label">Make order</span></a>
	</div>
			<!-- Banner -->
			<!--
				To use a video as your background, set data-video to the name of your video without
				its extension (eg. images/banner). Your video must be available in both .mp4 and .webm
				formats to work correctly.
			-->
				<section id="banner" data-video="images/banner123">
					<div class="inner">
						<header>
							<h1>Full Motion</h1>
							<p>A responsive video gallery template with a functional lightbox<br />
							designed by <a href="https://templated.co/">Templated</a> and released under the Creative Commons License.</p>
						</header>
						<a href="#main" class="more">Learn More</a>
					</div>
				</section>

			<!-- Main -->
				<div id="main">
					<div class="inner">
					<c:choose>
						<c:when test="${sessionScope.session_lang eq 'eng'}">
						<a href="/hello?sort_content=film">|Sort by film name|</a>
							<a href="/hello?sort_content=">|Sort by session date|</a>
							<c:if test="${sessionScope.sort_content eq 'film'}">
								<a href="/hello?sort_content=film&all_film=true">|All films|</a>
							</c:if>
						</c:when>
						<c:otherwise>
							<a href="/hello?sort_content=film">|Сортування за назвою фільма|</a>
							<a href="/hello?sort_content=">|Сортування за датою сеансу|</a>
							<c:if test="${sessionScope.sort_content eq 'film'}">
								<a href="/hello?sort_content=film&all_film=true">|Усі фільми|</a>
							</c:if>
						</c:otherwise>
					</c:choose>

						<br>
					<!-- Boxes -->
						<div class="thumbnails">
							<c:choose>
								<c:when test="${sessionScope.sort_content eq 'film'}">
									<c:forEach var="film" items="${films}">
										<div class="box">
											<a href="#" class="image fit"><img src="/images/${film.image}" alt=""/></a>
											<div class="inner">
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
									</c:forEach>
								</c:when>
								<c:otherwise>
									<c:forEach var="date" items="${dates}">
										<div class="box">
											<div class="inner">
												<h3>${date}</h3>
												<a href="/sessions_for_day?day_of_week=${date}" class="button fit" data-poptrox="youtube,800x400">Watch</a>
											</div>
										</div>
									</c:forEach>
								</c:otherwise>
							</c:choose>
						</div>

					</div>
				</div>

			<!-- Footer -->
				<footer id="footer">
					<div class="inner">
						<h2>Etiam veroeros lorem</h2>
						<p>Pellentesque eleifend malesuada efficitur. Curabitur volutpat dui mi, ac imperdiet dolor tincidunt nec. Ut erat lectus, dictum sit amet lectus a, aliquet rutrum mauris. Etiam nec lectus hendrerit, consectetur risus viverra, iaculis orci. Phasellus eu nibh ut mi luctus auctor. Donec sit amet dolor in diam feugiat venenatis. </p>

						<ul class="icons">
							<li><a href="#" class="icon fa-twitter"><span class="label">Twitter</span></a></li>
							<li><a href="#" class="icon fa-facebook"><span class="label">Facebook</span></a></li>
							<li><a href="#" class="icon fa-instagram"><span class="label">Instagram</span></a></li>
							<li><a href="#" class="icon fa-envelope"><span class="label">Email</span></a></li>
						</ul>
						<p class="copyright">&copy; Untitled. Design: <a href="https://templated.co">TEMPLATED</a>. Images: <a href="https://unsplash.com/">Unsplash</a>. Videos: <a href="http://coverr.co/">Coverr</a>.</p>
					</div>
				</footer>

		<!-- Scripts -->
			<script src="assets/js/jquery.min.js"></script>
			<script src="assets/js/jquery.scrolly.min.js"></script>
			<script src="assets/js/jquery.poptrox.min.js"></script>
			<script src="assets/js/skel.min.js"></script>
			<script src="assets/js/util.js"></script>
			<script src="assets/js/main.js"></script>
	</body>
</html>