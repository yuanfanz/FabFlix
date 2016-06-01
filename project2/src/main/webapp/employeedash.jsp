<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">   
<script src='https://www.google.com/recaptcha/api.js'></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">

<title>For employees</title>

<style>
    body{
      font-family: 'Lato', sans-serif;
      padding-top: 40px;
      background-color: white;
      background-image:url("images/blueflower.jpg");
      background-repeat: no-repeat;
      background-position: left top;
      background-attachment: fixed;
    }
    footer{
      padding-top:50px;
    }
    .jumbotron{
      
      opacity: 0.6;
      filter: alpha(opacity=60); /* For IE8 and earlier */
    }
    .jumbotron h1, h4 {
    color: #000000;
    }
    
    .carousel-inner > .item > img,.carousel-inner > .item > a > img {
      width: 70%;
      padding: 30px;
      margin: auto;
    } 
    h2{
      color: black;
    }
</style>
</head>

<body>
<div class="container" id="container">
		<div class="jumbotron">
     	 <h1>FabFlix</h1>
      	 <h2>For employees!</h2>
    	</div>
    	
    	<a href="/fabflix/dashAddStar.jsp">Add a new star!</a>
		<br/>
		<a href="/fabflix/dashMetadata.jsp">Show metadata!</a>
		<br/>
		<a href="/fabflix/dashAddMovie.jsp">Add or update a movie!</a>
		<br/>
		
</div>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
</body>
</html>