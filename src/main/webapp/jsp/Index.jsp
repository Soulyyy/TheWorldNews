<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="TheWorldNews.newsdata.NewsArticle" %>
<%@ page import="TheWorldNews.database.querys.DisplayQueries" %>
<!DOCTYPE html> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="./css/layout.css">
</head>
<body>
<div id="header">
 

	<!-- The World Log In & Settings -->
	<div id="logoButtons">
    
		<header class="Head"><a class="Head" menuItem="index">THE WORLD</a></header>

        
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
            <li><a menuItem="News">News</a> </li>
			<li><a menuItem="Business">Business</a></li>
			<li><a menuItem="Sports">Sports</a></li>
			<li><a menuItem="Science">Science</a></li>
			<li><a menuItem="Arts">Arts</a></li>
            <li><a menuItem="FashionStyle">Fashion &amp; Style</a></li>
		</ul>
        
	</div>
    
 
</div>


<div class="clear">

</div>


<div id="articleGroup">

<%	
	//ArrayList<NewsArticle> newsArticles = DisplayQueries.getArticlesByNumberAndType(28, "News");
	ArrayList<NewsArticle> newsArticles = new ArrayList<NewsArticle>();
	NewsArticle n1 = new NewsArticle(1,"http://i.imgur.com/iKv7rLN.jpg" , "Must be more to life", "none", 1);
	NewsArticle n2 = new NewsArticle(2,"http://i.imgur.com/MDdYuXW.jpg","Avin' a giggle m8?","none",1);
	NewsArticle n3 = new NewsArticle(3,"http://i.imgur.com/QyI6t1k.png","Part 4 fookin joocy", "none", 1);
	newsArticles.add(n1);
	newsArticles.add(n2);
	newsArticles.add(n3);
    for(int i = 0;i<newsArticles.size();i++){
    	
    	NewsArticle mainArticle = newsArticles.get(i);
    	String img = mainArticle.image;
    	String header = mainArticle.header;
    	%><div class="mainArticle">
    
    	<a href="#"><img class="mainImage" src=<%=img%> alt="pilt"></a>
        
    	<p class="mainText"><a href="#"><%=header%></a></p>
    
    </div>
    <%
    i++;
    NewsArticle leftArticle = newsArticles.get(i);
	String leftIMG = leftArticle.image;
	String leftHeader = leftArticle.header;
    %>
    
    		<div class="leftArticle">
    
    	<a href="#"><img class="mainImage" src=<%=leftIMG%> alt="pilt"></a>
        
        <p class="sideText"><a href="#"><%=leftHeader%></a></p>
    
	</div>
	
	<%
	i++;
    NewsArticle rightArticle = newsArticles.get(i);
	String rightIMG = leftArticle.image;
	String rightHeader = leftArticle.header;
    
    %>
	
    
	<div class="rightArticle">
    
    	<a href="#"><img class="mainImage" src=<%=rightIMG%> alt="pilt"></a>
        
        <p class="sideText"><a href="#"><%=rightHeader%></a></p>
    
	</div>
    		
    	
    	
   <% 	
    }   
%>


</div>

<div id="sideBar">


</div>

</body>
</html>