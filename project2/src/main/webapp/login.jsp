<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">   
<title>FabFlix login</title>
<script src='https://www.google.com/recaptcha/api.js'></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
<script>

var logfail = '${fail}';
window.onload = function(){
	
	if (logfail.length!=0){
		var logWarn = document.getElementById("wrong");
		logWarn.style.visibility = 'visible';
	}	

}
<% session.removeAttribute("fail"); %>

</script>
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
      	 <h4>We have all the movies that you want!</h4>
    	</div>
		<!-- <div class="logo">
			<img src="images/logo.jpg"/>
		</div> -->
		<div id="login-box">
			<div class="col-lg-4 col-lg-offset-4">
        	<ul class="nav nav-tabs">
         		 <li ><h2>Login</h2></li>
         	</ul>
         	<div class="tab-content">
          		<div id="login" class="tab-pane fade in active">
					<form action='loginServlet' method='post'>
					<div class="form-group">
						<p class="main">
						<br>
						<br>
						<label>Username : </label> 
						<input name="username"> 
						</p>
					</div>
					<div class="form-group">
						<p class="main">
						<label>Password : </label> 
						<input type="password" name="password">
						<br>
						<br>
						</p>
						<p style='visibility:hidden;' id='wrong'><font color='red'> Login failed!</font></p>
					</div>

						<p class="submit">
						<input type="submit" value="login" class="login" style="cursor:pointer;"/>
						</p>
						<div class="g-recaptcha" data-sitekey="6LfyaR4TAAAAAD1PCJsZ3WAkv3JVgoGp7YSov1qo"></div>
					</form>
					
					<h2><a href="/fabflix/_dashboard.jsp">For employee entry!</a></h2>
				</div>

			</div>
			</div>
		</div>
		 <div class="row">
      
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