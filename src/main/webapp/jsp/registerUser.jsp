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
<title>The World - Registration</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="../css/layout.css">
<script src="../lib/jquery-1.11.0.js" type="text/javascript"></script>
<script type="text/javascript" src="../js/mainPage.js"></script>
<script type="text/javascript" src="../js/login.js"></script>
<script type="text/javascript" src="https://apis.google.com/js/client.js?onload=handleClientLoad"></script>
<script type="text/javascript" src="../js/registerUser.js"></script>
</head>
<body>
<ex:Navigation/>
        <div class="registerUser">
            <div class="registration">
                <p><label for="username">Choose your Username</label></p>
                <p><input name="username" id="username" type="text"></p>

                <p><label for="username">Choose your Password</label></p>
                <p><input name="password" id="pw" type="password"></p>

                <p><label for="username">Confirm your Password</label></p>
                <p><input name="password2" id="pw2" type="password"></p>

                <p><label>Name</label></p>
                <p>
                    <input name="first" id="first" type="text">
                    <input name="last" id="last" type="text">
                </p>

                <p><label>Email Address</label></p>
                <p><input name="other-email" id="email" type="text"></p>

                <button id="reg">Submit</button>
            </div>
        </div>
    </body>
</html>