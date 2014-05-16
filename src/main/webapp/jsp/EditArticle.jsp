<%@ page import="theworldnews.handlers.news.servlets.PreviewController"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="theworldnews.database.news.objects.Article"%>
<%@ page import="theworldnews.database.news.queries.DisplayQueries"%>
<%@ taglib prefix="ex" uri="../WEB-INF/custom.tld"%>
<!DOCTYPE html>
<html>
<head>
<title>The World - News</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="../css/editArticle.css">
<script src="../lib/jquery-1.11.0.js" type="text/javascript"></script>
<script type="text/javascript" src="../js/mainPage.js"></script>
<script type="text/javascript" src="../js/login.js"></script>
<script type="text/javascript"
	src="https://apis.google.com/js/client.js?onload=handleClientLoad"></script>
<script type="text/javascript" src="../js/createarticle.js"></script>
</head>
<body>
	<ex:Navigation />
		<div class="profile">

			<div class="profileSettings">

				<nav>

					<ul id="profileNav">

						<li class="profileList"><a href="#">Profile Settings</a></li>
						<li class="profileList"><a href="#">Privacy Settings</a></li>
						<li class="profileList"><a href="#">Article Settings</a></li>
						<li class="profileList"><a href="#">Connected Accounts</a></li>
						<li class="profileList"><a href="#">Help</a></li>

					</ul>
				</nav>

			</div>

			<div class="profileOptions">
			
			
			<div id="createarticle">

                        
            	<fieldset id="editingFieldset" name="edit">
                	
                    <legend>Article</legend>
                

    				<label class="titleText">Article Title</label>
                    
    				<input id="titleInput" class="input-block" type="text" value="">
                    
                    
                    <label class="titleText">Image URL</label>
                    
    				<input id="Image" class="input-block" type="text" value="">


    				<label class="titleText">Article Text</label>
                     

                    <textarea id="textArea"></textarea>
                    
 
                </fieldset>
				<div class="clear"></div>
                <input checked type="checkbox" name="type" id="News" value="News">News
				<input type="checkbox" name="type" id="Business" value="Business">Business
				<input type="checkbox" name="type" id="Sports" value="Sports">Sports
				<input type="checkbox" name="type" id="Science" value="Science">Science
				<input type="checkbox" name="type" id="Arts" value="Arts">Arts
				<input type="checkbox" name="type" id="Fashion" value="Fashion & Style">Fashion & Style
 				<br />
 				<br />
                <button  id="articlesubmit">Submit</button>
                <button  id="Cancel">Cancel</button>
                                   

 		</div>
			





			</div>

		</div>
</body>
</html>