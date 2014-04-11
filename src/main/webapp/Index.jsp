<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="TheWorldNews.newsdata.NewsArticle" %>
<%@ page import="TheWorldNews.database.querys.DisplayQueries" %>
<%@ taglib prefix="ex" uri="WEB-INF/custom.tld"%>
<!DOCTYPE html> 
<html>
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
    
		<header class="Head"><a class="Head" menuItem="Index">THE WORLD</a></header>

        
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
     
        <ul>
            <li><a href="jsp/News.jsp">News</a> </li>
            
			<li><a href="jsp/Business.jsp">Business</a></li>
			<li><a href="jsp/Sports.jsp">Sports</a></li>
			<li><a href="jsp/Science.jsp">Science</a></li>
			<li><a href="jsp/Arts.jsp">Arts</a></li>
            <li><a href="jsp/FashionStyle.jsp">Fashion &amp; Style</a></li>
		</ul>
        
	</div>
    
 
</div>


<div class="clear">
</div>


<div id="articleGroup">


<ex:AT type="News" newsgroup="3"/>


</div>

<div id="sideBar">


</div>

</body>
</html>