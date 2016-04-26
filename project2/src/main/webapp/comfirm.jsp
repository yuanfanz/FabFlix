<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Confirmation</title>
</head>
<body>
	<h1>Confirmation</h1>
	<hr>
	<center>
		<img src="images/add_cart_success.jpg"/>
		<hr>
		<%
			String name = request.getParameter("name");
			String num = request.getParameter("num");
		%>
		Successfully bought <%=num %> movies, name：<%=name %>. &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<br>
	</center>
</body>
</html>