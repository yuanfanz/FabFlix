<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
<title>Add movie info</title>
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
    </style>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">

</head>
<body>
<body>
	<div id="container" class="container">
	<div class="jumbotron">
        <h1>FabFlix</h1>
        <h4>For employees!</h4>
        <div class="menutext"><a href="employeedash.jsp">Employee</a></div>
	</div>
		<h1 style="text-align: center;">Add Movie Information</h1>
		<div id="search-box" style="text-align:center">
			<form action='dashAddMovieServlet' method='post'>
				<p class="main">
					<label>Title : </label> 
					<input name="title"><br> 
					<label>Year : </label> 
					<input name="year"><br>
					<label>Director : </label>
					<input name="director"><br>
					<label>Star's Name: </label>
					<input name="star_name"><br>
					<label>Genre: </label>
					<input name="genre"><br>
	
					
					<input type="checkbox" name="subMatch">Submatch<br>
					<br>
				</p>

				<p class="submit">
					<input type="submit" value="search" class="search" style="cursor:pointer;"/>
				</p>

			</form>
		</div>

	</div>
</body>
</html>



</body>
</html>