<%@ page import="theworldnews.handlers.news.servlets.PreviewController"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="theworldnews.database.news.objects.Article" %>
<%@ page import="theworldnews.database.news.queries.DisplayQueries" %>
<%@ taglib prefix="ex" uri="../WEB-INF/custom.tld"%>


<!DOCTYPE html>
<html>
<head>
<title>The World - Arts</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="../css/layout.css">
<script src="../lib/jquery-1.11.0.js" type="text/javascript"></script>
<script type="text/javascript" src="../js/mainPage.js"></script>
<script type="text/javascript" src="../js/login.js"></script>
<script type="text/javascript"
	src="https://apis.google.com/js/client.js?onload=handleClientLoad"></script>
</head>
<body>
<ex:Navigation/>
<jsp:include page="/previewArticle">
	<jsp:param name="type" value="Arts" />
	<jsp:param name="size" value="6" />
</jsp:include>
</body>
</html>