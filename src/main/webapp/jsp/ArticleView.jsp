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
<title>The World</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="../css/layout.css">
<link rel="stylesheet" media="(min-width: 480px) and (max-width: 800px)" href="../css/tablet.css" />
<link rel="stylesheet" media="(max-width: 479px)" href="../css/small.css" />
<script src="../lib/jquery-1.11.0.js" type="text/javascript"></script>
<script type="text/javascript" src="../js/mainPage.js"></script>
<script type="text/javascript" src="../js/login.js"></script>
<script type="text/javascript" src="../js/latestnews.js"></script>
<script type="text/javascript" src="../js/hashtag.js"></script>
<script type="text/javascript"
	src="https://apis.google.com/js/client.js?onload=handleClientLoad"></script>
</head>
<body>
	<ex:Navigation />

	<div id="articleGroup">
	
		<jsp:include page="/displayArticle">
			<jsp:param value="<%=request.getParameter("id")%>" name="id" />
			<jsp:param value="<%=request.getParameter("image")%>" name="image" />
		</jsp:include>
		<div id="hashtags"></div>
		
		<div id="htcont">
			<form action="/tagController" method="post">
				Tag: <input type="text" name="tag"><br>
				<input type="hidden" name="id" value="<%=request.getParameter("id")%>">
				<input type="hidden" name="image" value="<%=request.getParameter("image")%>">
				<input type="submit" value="Submit">
			</form>
		</div>
		
	</div>

	


	<!-- Latest News -->
	<div id="sideBar">

		<div id="latest">

			<header>
				<a id="latestNews"> Latest News </a>
			</header>

			<div class="latestNewsDisplay"></div>


		</div>

	</div>










</body>
</html>