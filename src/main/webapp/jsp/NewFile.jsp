<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="TheWorldNews.newsdata.NewsArticle" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
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
	ArrayList<NewsArticle> newsArticles = new ArrayList<NewsArticle>();
	Connection conn = DriverManager.getConnection("", "", "");
	Statement stmt = conn.createStatement();
	ResultSet articleGroup = stmt.executeQuery("SELECT a, b FROM TABLE2");
	String image;
    String header;
	String content;
	int articlegroup;
	
	while(articleGroup.next()){
		//CHANGED THESE NAMES, CHECK PLEASE
		image = articleGroup.getString("");
		header = articleGroup.getString(""); //fails if in this order
		content = articleGroup.getString("");
		articlegroup = articleGroup.getInt("");
		
		newsArticles.add(new NewsArticle(image,header,content,articlegroup));
	}

	
    
    for(int i = 0;i<newsArticles.size();i++){
    	
    	NewsArticle mainArticle = newsArticles.get(i);
    	String img = mainArticle.image;
    	String header = mainArticle.header;
    	%><div class="mainArticle">
    
    	<a href="#"><img class="mainImage" src=<%=img%> alt="pilt"></a>
        
    	<p class="mainText"><a href="#"><%=header%></a></p>
    
    </div>       <%i++;
    
    NewsArticle leftArticle = newsArticles.get(i);
	String lIMG = leftArticle.image;
	String lHeader = leftArticle.header;
    
    %>
    
    		<div class="leftArticle">
    
    	<a href="#"><img class="mainImage" src=<%=lIMG%> alt="pilt"></a>
        
        <p class="sideText"><a href="#"><%=lHeader%></a></p>
    
	</div>
	
	<%i++;
    
    NewsArticle rightArticle = newsArticles.get(i);
	String rIMG = leftArticle.image;
	String rHeader = leftArticle.header;
    
    %>
	
    
	<div class="rightArticle">
    
    	<a href="#"><img class="mainImage" src=<%=rIMG%> alt="pilt"></a>
        
        <p class="sideText"><a href="#"><%=rHeader%></a></p>
    
	</div>
    		
    	
    	
   <% 	
    }   
%>


</div>

<div id="sideBar">


</div>

</body>
</html>