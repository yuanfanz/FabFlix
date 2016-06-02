<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="com.fablix.moviedb.model.Movies"%>
<%@ page import="com.fablix.moviedb.DAO.MoviesDAO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
  <script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<script src="autocomplete.js"></script>
<link rel="stylesheet" 
  href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">


<title>Home</title>
<style>
    body{

      font-family:'Trebuchet MS', Helvetica, sans-serif;
      padding-top: 1px;
      background-color: white;
      background-image:url("images/paris.jpg");
      background-repeat: no-repeat;
      background-position: top;
      background-attachment: fixed;
    }
    .carousel img{
	width: 100%;
	height: 200px;
	opacity:1.0;
	}

</style>

    <!-- Bootstrap -->
   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
</head>
<body>

<nav class="navbar navbar-default">
  <div class="container">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <a class="navbar-brand" href="#"><c:out value="Welcome, ${user.username}"></c:out></a>
    </div>
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li class="active"><a href="#">Home <span class="sr-only">(current)</span></a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
      <li><form action='searchServlet' method='post'><div class="search-container"><div class="ui-widget">search:<input id="movietitle" name="title"><ul id="results"></ul></div>
<br>
</div></form>
      </li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="cart.jsp">My cart</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="checkOutConfirm.jsp">Check out</a></li>
      </ul>
      
    </div>
   </div><!-- /.container-fluid -->
</nav>


<!-- <div class="ui-widget"><label for="tags">search</label> <input id="tags"></div> -->
<%! char c='0'; %> 



<div id="container">
<h1 style="text-align: center;">Search Movies</h1>
		<div id="search-box" style="text-align:center">
			<form action='searchServlet' method='post'>
				<p class="main">
					<label>title : </label> <input name="title" ><br> 
					<label>year : </label> <input name="year"><br>
					<label>director : </label><input name="director"><br>
					<label>star's first Name: </label><input name="first_name"><br>
					<label>last Name: </label><input name="last_name"><br>
	

					<input type="checkbox" name="subMatch">submatch<br>
					<br>
				</p>

				<p class="submit">
					<input type="submit" value="search" class="search" style="cursor:pointer;"/>
				</p>

			</form>
		</div>
	<div class="row">
	<div class="col-lg-5 col-lg-offset-1" >
		<h3 style="text-align: center;">Browse Movies by Title</h3>
		<div id="browse-box">
        	<% for(c = '0'; c <= '9'; c++) { %>

        		<c:url value="/searchServlet" var="browse" > 
  					<c:param name="prefix" value="<%= String.valueOf(c) %>" />
				</c:url> 
            	<a href="${browse}"> <%= c %></a>
            <% } %>

        	<% for(c = 'A'; c <= 'Z'; c++) { %>
        		<c:url value="/searchServlet" var="browse" > 
  					<c:param name="prefix" value="<%= String.valueOf(c) %>" />
				</c:url> 
            	<a href="${browse}"> <%= c %></a>
            <% } %>			
		</div>
	</div>
	<div class="col-lg-5">
		<h3 style="text-align: center;">Browse Movies by Genre</h3>
		<div id="browse-box">
        		<c:url value="/searchServlet" var="browse" > 
  					<c:param name="genre" value="Action" />
				</c:url> 
            	<a href="${browse}">Action</a>
            	<c:url value="/searchServlet" var="browse" > 
  					<c:param name="genre" value="Adventure" />
				</c:url> 
            	<a href="${browse}">Adventure</a>
            	<c:url value="/searchServlet" var="browse" > 
  					<c:param name="genre" value="Animation" />
				</c:url> 
            	<a href="${browse}">Animation</a>
            	<c:url value="/searchServlet" var="browse" > 
  					<c:param name="genre" value="Biography" />
				</c:url> 
            	<a href="${browse}">Biography</a>
            	<c:url value="/searchServlet" var="browse" > 
  					<c:param name="genre" value="Comedy" />
				</c:url> 
            	<a href="${browse}">Comedy</a>
            	<c:url value="/searchServlet" var="browse" > 
  					<c:param name="genre" value="Crime" />
				</c:url> 
            	<a href="${browse}">Crime</a>
            	<c:url value="/searchServlet" var="browse" > 
  					<c:param name="genre" value="Documentary" />
				</c:url> 
            	<a href="${browse}">Documentary</a>
            	<c:url value="/searchServlet" var="browse" > 
  					<c:param name="genre" value="Drama" />
				</c:url> 
            	<a href="${browse}">Drama</a>
            	<c:url value="/searchServlet" var="browse" > 
  					<c:param name="genre" value="Family" />
				</c:url> 
            	<a href="${browse}">Family</a>
            	<c:url value="/searchServlet" var="browse" > 
  					<c:param name="genre" value="Fantasy" />
				</c:url> 
            	<a href="${browse}">Fantasy</a>
            	<c:url value="/searchServlet" var="browse" > 
  					<c:param name="genre" value="Film-Noir" />
				</c:url> 
            	<a href="${browse}">Film-Noir</a>
            	<c:url value="/searchServlet" var="browse" > 
  					<c:param name="genre" value="History" />
				</c:url> 
            	<a href="${browse}">History</a>
            	<c:url value="/searchServlet" var="browse" > 
  					<c:param name="genre" value="Horror" />
				</c:url> 
            	<a href="${browse}">Horror</a>
            	<c:url value="/searchServlet" var="browse" > 
  					<c:param name="genre" value="Music" />
				</c:url> 
            	<a href="${browse}">Music</a>
            	<c:url value="/searchServlet" var="browse" > 
  					<c:param name="genre" value="Musical" />
				</c:url> 
            	<a href="${browse}">Musical</a>
            	<c:url value="/searchServlet" var="browse" > 
  					<c:param name="genre" value="Mystery" />
				</c:url> 
            	<a href="${browse}">Mystery</a>
            	<c:url value="/searchServlet" var="browse" > 
  					<c:param name="genre" value="Romance" />
				</c:url> 
            	<a href="${browse}">Romance</a>
            	<c:url value="/searchServlet" var="browse" > 
  					<c:param name="genre" value="Sci-Fi" />
				</c:url> 
            	<a href="${browse}">Sci-Fi</a>
            	<c:url value="/searchServlet" var="browse" >
  					<c:param name="genre" value="Sport" />
				</c:url> 
            	<a href="${browse}">Sport</a>
            	<c:url value="/searchServlet" var="browse" > 
  					<c:param name="genre" value="Thriller" />
				</c:url> 
            	<a href="${browse}">Thriller</a>
            	<c:url value="/searchServlet" var="browse" > 
  					<c:param name="genre" value="War" />
				</c:url> 
            	<a href="${browse}">War</a>
            	
            		
		</div>
		</div>
	</div>
	<br>
	
	<footer class="col-lg-4 col-lg-offset-4">
        <p>Posted by: cs122b-Group20</p>
       	 <p>Contact information: <a href="mailto:sc122b20@uci.edu">sc122b20@uci.edu</a>.</p>
     </footer>
</div><!--  end of container -->

   <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script> 
    <!-- Include all compiled plugins (below), or include individual files as needed -->
<!--    <script src="js/bootstrap.min.js"></script> -->

</body>
</html>