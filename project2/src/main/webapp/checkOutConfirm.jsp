<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
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
    th, td {
    border-bottom: 1px solid #ddd;
    padding: 15px;
    text-align: left ;

    }
    tr:hover {background-color: #f5f5f5}
    
</style>

<title>checkout</title>
</head>
<body>
<div class="container">
<div class="jumbotron">
        <h1>FabFlix</h1>
        <h4>We have all the movies that you want!</h4>
        <div class="menutext"><a href="main.jsp">Home</a> | <a href="search.jsp">Advanced Search</a> | <a href="cart.jsp">My Cart</a></div>
</div>
<div id="shopping" style="text-align:center">
<h1>Shopping List</h1>
</div>
   
  	<hr> 
				<% 
				   //首先判断session中是否有购物车对象
				   if(session.getAttribute("cart") != null)
				   {
					   
				%>
  	 		<div id="shopping" >
  	 		<c:forEach var="movie" items="${cart.getMovies().keySet()}">
				<table border="1" align="center" style="width:100%">
				<tr>
					<td width="30%">
						<c:out value="${movie.title}"></c:out>
					<br>
					</td>
				
					<td width="70%">
						Quantity: 
						<c:out value="${cart.getMovies().get(movie)}" ></c:out>
                    </td>
				              
                    <td class="delete" width="10%">
					<a href="cartServlet?action=delete&id=${movie.getId()}" onclick="delcfm();">Delete</a>	                  
					</td>
				</tr>
				</table>
			</c:forEach>
			<% 
				}
			%>
			</div>
			<hr>

			<a href="cartServlet?action=show" >My Cart</a>	 
			<hr>
<div id="container">
		<h1 style="text-align: center;">Checkout Confirm Information:</h1>
		<div id="search-box" style="text-align:center">
			<form action='confirmServlet' method='post'>
				<p class="main">
					<label>First name : </label> <input name="firstname"><br> 
					<label>Last name : </label> <input name="lastname"><br>
					<label>Credit Card Number : </label><input name="ccnum"><br>
					<label>Expiration Time: </label><input name="extime">
					<label><font color="grey">Format: YYYY-MM-DD</font></label>				
					<br>
				</p>

				<p class="submit">
					<input type="submit" value="checkout" class="checkout" style="cursor:pointer;"/>
				</p>

			</form>
		</div>
      </div>
      <div style="text-align:center">
        <footer class="col-lg-4 col-lg-offset-4">
        <p>Posted by: cs122b-Group20</p>
       	 <p>Contact information: <a href="mailto:sc122b20@uci.edu">sc122b20@uci.edu</a>.</p>
     	</footer>
     	</div>
	</div>
	 <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
</body>
</html>