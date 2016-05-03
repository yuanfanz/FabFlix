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
<title>Add a Star</title>
<meta name="viewport" content="width=device-width, initial-scale=1">   
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">

</head>
<body>
<div class="container">
<div class="jumbotron">
        <h1>FabFlix</h1>
        <h4>For employees!</h4>
        <div class="menutext"><a href="employeedash.jsp">Employee</a></div>
</div>
<div class="jumbotron">
	<h1>Add a Star</h1>
	<h2>Please fill in the star's information</h2>
	<form action='dashAddStarServlet' method='post'>
		<p class="main">
					<label>First name: </label> <input name="firstname"><br> 
					<label>Last name: </label> <input name="lastname"><br>
					<label>Photo url if any: </label><input name="photo_url"><br>
					<label>Date of birth: </label><input name="dob"><label><font color="grey">Format: YYYY.MM.DD</font></label>				
					<br>
				</p>
				<p class="submit">
					<input type="submit" value="Add this star" class="addStar" style="cursor:pointer;"/>
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