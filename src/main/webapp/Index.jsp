<%@ page import="theworldnews.handlers.news.servlets.PreviewController"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="theworldnews.database.news.objects.Article"%>
<%@ page import="theworldnews.database.news.queries.DisplayQueries"%>
<%@ taglib prefix="ex" uri="WEB-INF/custom.tld"%>

<!DOCTYPE html>
<html>
<head>
<title>The World</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="./css/layout.css">
<link rel="stylesheet" media="(min-width: 480px) and (max-width: 800px)" href="css/tablet.css" />
<link rel="stylesheet" media="(max-width: 479px)" href="css/small.css" />
<script src="./lib/jquery-1.11.0.js" type="text/javascript"></script>
<script type="text/javascript" src="js/mainPage.js"></script>
<script type="text/javascript" src="js/latestnews.js"></script>
<script type="text/javascript" src="js/login.js"></script>
<script type="text/javascript" src="https://apis.google.com/js/client.js?onload=handleClientLoad"></script>
</head>
<body>
	<div id="header">


		<!-- The World Log In  -->
		<div id="logoButtons">

			<header class="Head">
				<a class="Head" href="Index.jsp">THE WORLD</a>
			</header>

			<a id="add" href="jsp/settings.jsp"></a>


			<button id="toggleLogin">Log In</button>
			<button id="logoutButton">Log Out</button>

		</div>
		
		<!-- Search -->
		<div id="search">
			
			
			<form method="post" class="form-wrapper" action="/searchText">
			<p><input type="text" id="searchBox" name="q" placeholder="Search" /></p>
			</form>
			
		 </div> 
 
		<!-- Login -->
		<div id="loginContainer">
		
			<div id="login">
				<p>
					<input type="text" id="userName" name="login" value="" placeholder="Username">
				</p>
				<p>
					<input type="password" id="password" name="password" value="" placeholder="Password">
				</p>

				<a href="jsp/registerUser.jsp" id="regi">Not a User? Click here to register</a>
				<p class="submit">
					<button id="loginbutton">Log In</button>
				</p>
			</div>
			<div>
				<button id="authorize-button">Log In With Google</button>
			</div>

		</div>
		
		<noscript>
		
			<form id="noscriptlogin" action="/noscriptAccountLogin"  method="post">
				<input type="text" id="noscriptuserName" name="username" value=""
						placeholder="Username or Email">
				<input type="password" id="noscriptpassword" name="password" value=""
						placeholder="Password">
						
				<input id="noscriptloginbutton" type="submit" value="Log In"/>
				<a href="jsp/registerUser.jsp" id="noscriptregi">Not a User? Click here to register</a>
			
			
			</form>
		
		
		</noscript>		




		<!-- Navigation -->

		<div id="navigation">

			<ul>
				<li><a href="jsp/News.jsp">News</a></li>

				<li><a href="jsp/Business.jsp">Business</a></li>
				<li><a href="jsp/Sports.jsp">Sports</a></li>
				<li><a href="jsp/Science.jsp">Science</a></li>
				<li><a href="jsp/Arts.jsp">Arts</a></li>
				<li><a href="jsp/FashionStyle.jsp">Fashion &amp; Style</a></li>
			</ul>

			<!-- 
		<ul>
            <li><a data-menuItem="News">News</a> </li>
			<li><a data-menuItem="Business">Business</a></li>
			<li><a data-menuItem="Sports">Sports</a></li>
			<li><a data-menuItem="Science">Science</a></li>
			<li><a data-menuItem="Arts">Arts</a></li>
            <li><a data-menuItem="FashionStyle">Fashion &amp; Style</a></li>
		</ul>
        		-->
		</div>


	</div>


	<div id="articleGroup">
		<jsp:include page="/previewArticle">
			<jsp:param name="type" value="News" />
			<jsp:param name="size" value="18" />
		</jsp:include>
	</div>





	<!-- Latest News -->
	<div id="sideBar">

		<div id="latest">

			<header>
				<a id="latestNews"> Latest News </a>
			</header>

			<div class="latestNewsDisplay">

			</div>


		</div>

	</div>

	<div class="footer">
		<p>&nbsp;</p>
	</div>

	<!-- Google Logout -->
	<iframe name='myIFrame' id="myIFrame" style='display: none'></iframe>

</body>
</html>
