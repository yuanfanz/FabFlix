<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Customer Information</title>
</head>
<body>
	<center>
		<img src="images/add_cart_success.jpg"/>
		<hr>
		<%
			String name = request.getParameter("name");
			String num = request.getParameter("num");
		%>
		Successfully bought <%=num %> movies, name：<%=name %>. &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<br>
		<hr>
		<div id="container">
		<div class="logo">
			<img src="images/logo.jpg"/>
		</div>
		<div id="login-box">
			<form action='loginServlet' method='post'>
				<p class="main">
					<label>username *: </label> <input name="username"> 
					<label>password *: </label> <input type="password" name="password">
					<br>
					<br>
				</p>
				<p class="submit">
					<a href="servlet/CartServlet?action=info">
                     <input type="submit" value="submit" class="login" style="cursor:pointer;"/></a>
				</p>
			</form>
		</div>
	</div>
	</center>
</body>
</html>