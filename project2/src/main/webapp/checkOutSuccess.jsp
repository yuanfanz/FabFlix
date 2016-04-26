<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Check out success!</title>
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
</head>
<body>
<div class="container">
<div class="jumbotron">
        <h1>FabFlix</h1>
        <h4>We have all the movies that you want!</h4>
        <div class="menutext"><a href="main.jsp">Home</a> | <a href="search.jsp">Advanced Search</a> | <a href="cart.jsp">My Cart</a>| <a href="checkOutConfirm.jsp">Check out</a></div>
</div>
<div class="jumbotron" id="shopping" style="text-align:center">
	<h3>Successfully Bought: </h3>

   
  	<hr> 
				<% 
				   //首先判断session中是否有购物车对象
				   if(session.getAttribute("cart") != null)
				   {
					   
				%>
  	 		<div id="shopping" >
  	 		<table border="0" align="center" style="width:100%">
				<tr>
					<td width="30%">Title</td>
					<td width="30%">
						Quantity </td>
				</tr>
			</table>
			<br>
  	 		<c:forEach var="movie" items="${cart.getMovies().keySet()}">
				<table border="0" align="center" style="width:100%">
				<tr>
					<td width="30%">
						<c:out value="${movie.title}"></c:out>
					</td>
					<td width="30%">
						<c:out value="${cart.getMovies().get(movie)}" ></c:out>
                    </td>
				</tr>
				
			</table>
			</c:forEach>
			<% 
				}
			%>
			</div>
			</div>
			</div>
			<% 
			session.removeAttribute("cart");
			%>
</body>
</html>