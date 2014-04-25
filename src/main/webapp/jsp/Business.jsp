<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="TheWorldNews.newsdata.NewsArticle" %>
<%@ page import="TheWorldNews.database.querys.DisplayQueries" %>
<%@ taglib prefix="ex" uri="../WEB-INF/custom.tld"%>
<!DOCTYPE html> 
<html>
<head>
	<title>The World - Business</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link rel="stylesheet" type="text/css" href="../css/layout.css">
    <script src="../lib/jquery-1.11.0.js" type="text/javascript"></script>
	<script type="text/javascript" src="../js/mainPage.js"></script>
	<script type="text/javascript" src="../js/login.js"></script>
	<script type="text/javascript" src="https://apis.google.com/js/client.js?onload=handleClientLoad"></script>
</head>
<body>
<ex:Navigation/>
 <ex:AT type="Business" size="3"/>
</body>
</html>