<%@page import="TheWorldNews.servlets.NewsDisplayController"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="TheWorldNews.newsdata.NewsArticle" %>
<%@ page import="TheWorldNews.database.querys.DisplayQueries" %>

<!DOCTYPE html> 
<html manifest="worldnewsmanifest.appcache">
<head>
	<title>The World</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link rel="stylesheet" type="text/css" href="./css/layout.css">
    <script src="./lib/jquery-1.11.0.js" type="text/javascript"></script>
	<script type="text/javascript" src="js/mainPage.js"></script>
	<script type="text/javascript" src="js/login.js"></script>
	<script type="text/javascript" src="https://apis.google.com/js/client.js?onload=handleClientLoad"></script>
</head>
<body>
<div id="header">
 

	<!-- The World Log In & Settings -->
	<div id="logoButtons">
    
		<header class="Head"><a class="Head" href="Index.jsp" >THE WORLD</a></header>

        
        <button  id="settings"></button>

        
        <button id="toggleLogin">Log In</button>
        <button id="logoutButton">Log Out</button>

    </div> 
    
    <div id="search">
        
        
        <form action="search" class="form-wrapper">
    	<p><input type="text" id="searchBox" placeholder="Search"></p>
		</form>
        
	 </div>
     
     
     
	<div id="loginContainer">
		<div id="login">
			<p><input type="text" id="userName" name="login" value="" placeholder="Username or Email"></p>
			<p><input type="password" id="password" name="password" value="" placeholder="Password"></p>
		 
			<a href="html/registerUser.html" id="regi">Not a User? Click here to register</a> 
			<p class="submit"><button id="loginbutton">Log In</button></p>
		</div>
        <div>
        <button id="authorize-button">Log In With Google</button>
		</div>
	</div>
     
     
    
    	<!-- Navigation -->
    
    <div id="navigation">
     <!-- 
        <ul>
            <li><a href="jsp/News.jsp">News</a> </li>
            
			<li><a href="jsp/Business.jsp">Business</a></li>
			<li><a href="jsp/Sports.jsp">Sports</a></li>
			<li><a href="jsp/Science.jsp">Science</a></li>
			<li><a href="jsp/Arts.jsp">Arts</a></li>
            <li><a href="jsp/FashionStyle.jsp">Fashion &amp; Style</a></li>
		</ul>
		-->
		<ul>
            <li><a data-menuItem="News">News</a> </li>
			<li><a data-menuItem="Business">Business</a></li>
			<li><a data-menuItem="Sports">Sports</a></li>
			<li><a data-menuItem="Science">Science</a></li>
			<li><a data-menuItem="Arts">Arts</a></li>
            <li><a data-menuItem="FashionStyle">Fashion &amp; Style</a></li>
		</ul>
        
	</div>
    
 
</div>


<div class="clear">
</div>


<div id="articleGroup">


<jsp:include page="/displayNews">
	<jsp:param name="newsType" value="News"/>
	<jsp:param name="numberOfNews" value="6"/>
</jsp:include>

</div>


<div id="sideBar">

	<div id="latest">
    
        <header>
            <a id="latestNews" href="#">
    
                Latest News
    
            </a>
        </header>
    
        <div class="latestNewsDisplay">
        
                    <div class="itemodd">
                    
                        <div class="time">00:01</div>
    
                        <div class="latestHeading"><a href="#">#Rekt</a></div>

                    
                    </div>
                    
                    <div class="itemeven">
                    
                    	<div class="time">00:02</div>
    
                        <div class="latestHeading"><a href="#">Some day these links will be clickable</a></div>
                    
                    
                    </div>
                    
                    <div class="itemodd">
                    
                    	<div class="time">00:03</div>
    
                        <div class="latestHeading"><a href="#">But it is not this day!</a></div>
                    
                    </div>
                    
                    
                    <div class="itemeven">
                    	
                        <div class="time">00:04</div>
    
                        <div class="latestHeading"><a href="#">This day we write code!</a></div>
                    
                    </div>
                    
                    
                    <div class="itemodd">
                    
                    	<div class="time">00:05</div>
    
                        <div class="latestHeading"><a href="#">This is a test for how does this div react to a much much longer news report!</a></div>
                    
                    </div>
                    
                    <div class="itemeven">
                    
                    	<div class="time">00:06</div>
    
                        <div class="latestHeading"><a href="#">Siim Kallas is still behind bars....</a></div>
                    
                    </div>
                    
                    <div class="itemodd">
                    
                    	<div class="time">00:06</div>
    
                        <div class="latestHeading"><a href="#">Running out of ideas what to write</a></div>
                        
                    </div>
                    
                    <div class="itemeven">
                    
                    	<div class="time">14:04</div>
    
                        <div class="latestHeading"><a href="#">I don't care. I'm done.</a></div>
                        
                    </div>

        </div>


    </div>
    
     <jsp:include page="../java/TheWorldNews/servlets/NewsDisplayController.java">
	<jsp:param name="newsType" value="News"/>
	<jsp:param name="numberOfNews" value="6"/>
	
</jsp:include>

</div>
<div class="footer"> <p>&nbsp;</p> </div>
 
<iframe name='myIFrame' id="myIFrame" style='display:none'></iframe>
 
</body>
</html>