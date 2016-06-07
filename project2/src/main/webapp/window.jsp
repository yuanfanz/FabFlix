<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ page import="com.fablix.moviedb.DAO.StarsDAO" %>
<%@ page import="com.fablix.moviedb.DAO.MoviesDAO" %>
<%@ page import="com.fablix.moviedb.DAO.GenresDAO" %>
<%@ page import="com.fablix.moviedb.model.Stars" %>
<%@ page import="com.fablix.moviedb.model.Movies" %>
<%@ page import="com.fablix.moviedb.model.Genres" %>
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
      background-image:url("images/paris.jpg");
      background-repeat: no-repeat;
      background-position: top;
      background-attachment: fixed;
    }
    img {
    float: left ;
    margin: 0 50px 10px 10px;
    filter: alpha(opacity=40); /* For IE8 and earlier */
    }
    
</style>

    <script type="text/javascript">
      
    function submit(actionURL){
		var btn = document.getElementById("form");
		btn.action = actionURL;
		btn.submit();
		}
    function selflog_show(id)
      { 
         var num = 1; 
         console.log('cartServlet?id='+id+'&num='+num+'&action=add');
         submit('cartServlet?id='+id+'&num='+num+'&action=add');
      }
      </script>
<title>Single Movie</title>
</head>
<body>

	<hr>
	<%
	if(request.getParameter("title") != null){
		String title = request.getParameter("title");
		MoviesDAO mdao = new MoviesDAO();
		StarsDAO sdao = new StarsDAO();
		GenresDAO gdao = new GenresDAO();
		Movies m = mdao.getMovieByTitle(title);
		List<Genres> genres = gdao.getGenreByMovie(m);
		List<Stars> stars = sdao.getStarsByMovie(m);
			
			pageContext.setAttribute("stars", stars);
			pageContext.setAttribute("m", m);
			pageContext.setAttribute("genres", genres);
		}
	%>
	
	
				<table border="0" align="center">
				<tr>
				<td style="text-align:left;">ID</td>
				<td style="text-align:left;">
            		<c:out value="${m.getId()}"></c:out>
					<c:out value=" "></c:out>
				</td>
				</tr>
				
			<tr>
				<td style="text-align:left;">Title</td>
				<td style="text-align:left;">
            		<c:out value="${m.getTitle()}"></c:out>
					<c:out value=" "></c:out>
					<br>
				</td>
			</tr>
			
			<tr>
				<td style="text-align:left;">Year</td>
				<td style="text-align:left;">
            		<c:out value="${m.getYear()}"></c:out>
					<c:out value=" "></c:out>
					<br>
				</td>
			</tr>
			
			<tr>
				<td style="text-align:left;">Director</td>
				<td style="text-align:left;">
					<c:out value="${m.getDirector()}"></c:out>
					<c:out value=" "></c:out>
					<br>
				</td>
			</tr>
			
			<tr>
				<td style="text-align:left;">Stars</td>
				<td style="text-align:left;">
				<c:forEach var = "star" items="${stars}" >
					<c:url value="/singleStar.jsp" var="browse" > 
  						<c:param name="id" value="${star.getId()}" />
						</c:url> 
            			<a href="${browse}">
            			<c:out value="${star.getFirst_name()}"></c:out>
						<c:out value=" "></c:out>
						<c:out value="${star.getLast_name()}"></c:out>
						<c:out value=" "></c:out>
            			</a>
						<br>
						</c:forEach>
				</td>
			</tr>
			
			<tr>
				<td style="text-align:left;">Genres</td>
				<td style="text-align:left;">
				<c:forEach var = "genre" items="${genres}" >
						<c:out value="${genre.name}"></c:out>
						<c:out value=" "></c:out>
						<br>
					</c:forEach>
				</td>
			</tr>
			
			<tr>
				<td style="text-align:left;">Trailer URL</td>
				<td style="text-align:left;"><a href="<c:url value="${m.getTrailer_url()}"/>">Trailer URL</a></td>
			</tr>
		</table>

		

		<hr>
		<!-- <a href="cartServlet?action=add">Add to cart</a> -->
		
		
   

        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
</body>
</html>