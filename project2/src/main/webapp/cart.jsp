<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 

<%@ page import="com.fablix.moviedb.model.Cart" %>
<%@ page import="com.fablix.moviedb.model.Movies" %>
<%@ page import="com.fablix.moviedb.model.*" %>
<%@ page import="com.fablix.moviedb.DAO.*" %>

<%-- <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%> --%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
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
      background-position: right top;
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

    <script type="text/javascript">
    
    
      /* var num = document.getElementById("number").value; */
      /* var num = "<c:out value='${number}' />" - 0;
      console.log(num); */
     /*  window.onload = function(){
    	  if()
      } */
      function submit(actionURL){
			var btn = document.getElementById("form");
			btn.action = actionURL;
			btn.submit();
		}
      function add(id)
      {  
    	
          submit('cartServlet?id='+id+'&num=1'+'&action=change');
    	
      }
      function sub(id)
      {
    	 
          submit('cartServlet?id='+id+'&num=-1'+'&action=change');
    	 
      }
      
	  function delcfm() {
	        if (!confirm("Are you sure to delete？")) {
	            window.event.returnValue = false;
	        }
	  }
   </script>
   <title>My Cart</title>
  </head>
<body>
<div class="container">
<div class="jumbotron">
        <h1>FabFlix</h1>
        <h4>We have all the movies that you want!</h4>
        <div class="menutext"><a href="main.jsp">Home</a> | <a href="search.jsp">Advanced Search</a> | <a href="cart.jsp">My Cart</a></div>
</div>

   	<h1>My shopping cart</h1>
   	
  	<hr> 
				<% 
				   //首先判断session中是否有购物车对象
				   if(session.getAttribute("cart") != null)
				   {
					   
				%>

  	 		<div id="shopping">
  	 		<c:forEach var="movie" items="${cart.getMovies().keySet()}">
  	 		
				<table border="0" style="width:100%">
				<tr>
					<td width="20%">
						<c:out value="${movie.title}"></c:out>
					<br>
					</td>
					<td width="70%">
					<table border="0">
					<tr>
					<td>
						<form id="form" action="" method="post">
						Quantity: 
						<input type="button" onClick="sub(${movie.getId()});" value="-" />
						<input type="button" onClick="add(${movie.getId()})" value="+" />
						</form>
						</td><td>
						<form action="cartServlet" method="post">
							<input type="text" name="num" value="${cart.getMovies().get(movie)}" />
							
							<input type="hidden" name="action" value="changeQuantity" />
							<input type="hidden" name="id" value="${movie.getId()}" />
							<input type="submit" value="update" />
						</form>
						</td>
						</tr>
						</table>
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
			<hr>
			<c:url value="cartServlet?action=confirm" var="browse" >
			</c:url>
			<a href="${browse}">
			<c:out value="Confirm"></c:out>
        	</a>
	</div>
	<div style="text-align:center">
        <footer class="col-lg-4 col-lg-offset-4">
        <p>Posted by: cs122b-Group20</p>
       	 <p>Contact information: <a href="mailto:sc122b20@uci.edu">cs122b20@uci.edu</a>.</p>
     	</footer>
     </div>
	</div>
	 <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
  </body>
</html>