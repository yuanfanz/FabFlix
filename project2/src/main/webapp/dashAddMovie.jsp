<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.fablix.moviedb.DAO.TableDAO" %>
    <%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.fablix.moviedb.model.Table" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">   
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">

<title>Add Movie</title>
</head>
<body>
<div class="container">
<div class="jumbotron">
        <h1>FabFlix</h1>
        <h4>For employees!</h4>
        <div class="menutext"><a href="employeedash.jsp">Employee</a></div>
</div>
<div class="jumbotron">
	<h1>Add a Movie</h1>
	<h2>Please fill in the movie's information</h2>
	<!-- title:varchar(100) year:integer director:varchar(100) banner_url:varchar(200) trailer_url:varchar(200) -->
	<form action='dashAddMovieServlet' method='post'>
		<p class="main">
					<label>Title: </label> <input name="title"><br> 
					<label>Year: </label> <input name="year"><br>
					<label>Director: </label><input name="director"><br>
					<label>Banner_url: </label><input name="banner_url"><br>
					<label>Trailer_url: </label><input name="trailer_url"><br>
					<br>
					Starred Star in this movie:   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<label><font color="grey">Please input only one star</font></label><br>
					<label>First Name: </label><input name="star_first"><br>
					<label>Last Name: </label><input name="star_last"><br><br>
					<label>The genre of this movie: </label><input name="genre">
					<label><font color="grey">Please input only one genre</font></label><br>
				</p>
				<p class="submit">
				<input type="submit" value="Add this movie" class="addStar" style="cursor:pointer;"/>
		</p>
	</form>
	</div>
	</div>
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
	
</body>
</html>