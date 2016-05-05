<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ page import="com.fablix.moviedb.DAO.StarsDAO" %>
<%@ page import="com.fablix.moviedb.DAO.MoviesDAO" %>
<%@ page import="com.fablix.moviedb.model.Stars" %>
<%@ page import="com.fablix.moviedb.model.Movies" %>
<%@ page import="com.fablix.moviedb.model.MovieInfo" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.SQLException" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
<style>
    body{
      
      font-family:'Trebuchet MS', Helvetica, sans-serif;
      padding-top: 1px;
      background-color: white;
      background-image:url("source/paris.jpg");
      background-repeat: no-repeat;
      background-position: top;
      background-attachment: fixed;
    }
    img {
    float: left ;
    margin: 20px 0 10px 10px;
    padding-left: 50px;
    opacity: 0.4;
    filter: alpha(opacity=40); /* For IE8 and earlier */
    }
    img:hover {
    opacity: 1.0;
    filter: alpha(opacity=100); /* For IE8 and earlier */
    }
</style>
<!-- If the user clicks on a movie, the corresponding "Single Star" page displays all the information about the movie, 
		including its id, title, year, director, a list of genres (hyperlinked), poster, a list of stars (hyperlinked), 
		and a link to its preview trailer. -->
<title>Single Star</title>
</head>
<body>
<div class="container">
<div class="jumbotron">
        <h1>FabFlix</h1>
        <h4>We have all the movies that you want!</h4>
        <div class="menutext"><a href="main.jsp">Home</a> | <a href="search.jsp">Advanced Search</a> | <a href="cart.jsp">My Cart</a>| <a href="checkOutConfirm.jsp">Check out</a></div>
</div>
	<div class="jumbotron">
        <h2>Star Details:</h2>
	<%
		if(request.getParameter("id") != null){
			int iden = Integer.parseInt(request.getParameter("id"));
			StarsDAO sdao = new StarsDAO();
			MoviesDAO mdao = new MoviesDAO();
			Stars s = sdao.getStarsById(iden);
			List<Movies> movies = mdao.getMoviesByStarId(iden);
			
			pageContext.setAttribute("movies", movies);
			pageContext.setAttribute("s", s);
		}
	%>
	<div class="row">
          
          <div class="col-lg-6 col-lg-offset-1">
            <div class="thumbnail">
              
              <div class="caption">
                <table border="0">
                <tr>
					<td style="text-align:left;">Star ID:</td>
					<td style="text-align:left;">
            			<c:out value="${s.getId()}"></c:out>
						<c:out value=" "></c:out>
						<br>
					</td>
				</tr>
                <tr>
					<td style="text-align:left;">Star Name:</td>
					<td style="text-align:left;">
            			<c:out value="${s.getFirst_name()}"></c:out>
						<c:out value="${s.getLast_name()}"></c:out>
						<c:out value=" "></c:out>
						<br>
					</td>
				</tr>
				<tr>
					<td style="text-align:left;">Date of Birth:</td>
					<td style="text-align:left;">
						<c:out value="${s.getDob()}"></c:out>
						<br>
					<%-- <c:out value="Picture"></c:out>
					<img src="<c:url value="${s.getPhoto_url}"/>"> --%>
					</td>
				</tr>
                
                <tr>
					<td style="text-align:left;">Starred in:</td>
					<td style="text-align:left;">
					<c:forEach var = "movie" items="${movies}" >
						<c:url value="/singleMovie.jsp" var="browse" > 
  						<c:param name="id" value="${movie.getId()}" />
						</c:url> 
            			<a href="${browse}">
            			<c:out value="${movie.getTitle()}"></c:out>
						<c:out value="("></c:out>
						<c:out value="${movie.getYear()}"></c:out>
						<c:out value=")"></c:out>
						<c:out value=" "></c:out>
            			</a>
						<br>
						</c:forEach>
					</td>
				</tr>
                </table>
                
              </div>
            </div>
          </div>
        </div>
	
		</div>
		<footer class="col-lg-4 col-lg-offset-4">
        <p>Posted by: cs122b-Group20</p>
       	 <p>Contact information: <a href="mailto:sc122b20@uci.edu">cs122b20@uci.edu</a>.</p>
     	</footer>
		</div>
		<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
</body>
</html>