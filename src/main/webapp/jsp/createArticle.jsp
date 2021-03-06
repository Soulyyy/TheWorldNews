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
<title>The World - Creating Article</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="../css/editArticle.css">
<script type="text/javascript" src="../lib/tinymce/tinymce.min.js"></script>
<script src="../lib/jquery-1.11.0.js" type="text/javascript"></script>
<script type="text/javascript" src="../js/mainPage.js"></script>
<script type="text/javascript" src="../js/login.js"></script>
<script type="text/javascript" src="../js/createarticle.js"></script>
<script type="text/javascript"
	src="https://apis.google.com/js/client.js?onload=handleClientLoad"></script>
<script type="text/javascript" src="../js/tinymce.js"></script>
</head>
<body>
	<ex:Navigation />


	<div class="clear"></div>



	<div class="profile">

		<div class="profileSettings">

			<nav>

				<ul id="profileNav">

					<li class="profileList"><a href="createArticle.jsp">Create
							Article</a></li>
					<li class="profileList"><a href="EditArticleDisplay.jsp">Edit
							Articles</a></li>

				</ul>
			</nav>

		</div>

		<div class="profileOptions">

			<!--Siia div-i võiks muudetud teks ilmuda vms-->
			<div id="appearingText"></div>

			<div id="createarticle">


				<fieldset id="editingFieldset" name="edit">

					<legend>Article</legend>


					<label class="titleText">Article Title</label> <input
						id="titleInput" class="input-block" type="text" value="">


					<label class="titleText">Image URL</label> <input id="Image"
						class="input-block" type="text" value=""> <label
						class="titleText">Article Text</label>


					<textarea id="textArea"></textarea>


				</fieldset>
				<div class="clear"></div>
				<input checked type="checkbox" name="type" id="News" value="News">News
				<input type="checkbox" name="type" id="Business" value="Business">Business
				<input type="checkbox" name="type" id="Sports" value="Sports">Sports
				<input type="checkbox" name="type" id="Science" value="Science">Science
				<input type="checkbox" name="type" id="Arts" value="Arts">Arts
				<input type="checkbox" name="type" id="Fashion"
					value="Fashion & Style">Fashion & Style <br /> <br />
				<button id="articlesubmit">Submit</button>
				<button id="Cancel">Cancel</button>






			</div>

			<noscript>


				<form id="noscripteditingFieldset" action="/noscriptSubmitArticle"
					method="post">


					<label class="titleText">Article Title</label> <input
						id="noscripttitleInput" name="header" class="input-block" type="text"
						value=""> <label class="titleText">Image URL</label> <input
						id="noscriptImage" name="image" class="input-block" type="text" value="">


					<label class="titleText">Article Text</label>


					<textarea id="noscripttextArea" name="content"></textarea>

					<input checked type="checkbox" name="type" id="noscriptNews" value="News">News
					<input type="checkbox" name="type" id="noscriptBusiness" value="Business">Business
					<input type="checkbox" name="type" id="noscriptSports" value="Sports">Sports
					<input type="checkbox" name="type" id="noscriptScience" value="Science">Science
					<input type="checkbox" name="type" id="noscriptArts" value="Arts">Arts
					<input type="checkbox" name="type" id="noscriptFashion"
						value="Fashion & Style">Fashion & Style <br /> <br /> <input
						id="noscriptarticlesubmit" type="submit" value="Submit" /> 
				</form>



				<!-- Siia tuleb midagi kirjutada -->
			</noscript>

		</div>

	</div>

	<div class="clear"></div>


	<footer>
		<p>&nbsp;</p>
	</footer>

</body>
</html>
