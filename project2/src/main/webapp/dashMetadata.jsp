<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.fablix.moviedb.DAO.TableDAO" %>
    <%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.fablix.moviedb.model.Table" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">   
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">

<title>Show Metadata</title>
</head>
<body>
<div class="container">
<div class="jumbotron">
        <h1>FabFlix</h1>
        <h4>For employees!</h4>
        <div class="menutext"><a href="employeedash.jsp">Employee</a></div>
</div>
<div class="jumbotron">
<h1>Tables detail:</h1>
<hr>
<%
TableDAO tableDao = new TableDAO();
List<Table> t = tableDao.getTables();
pageContext.setAttribute("t", t);
%>
<div class="jumbotron">
	<h2 align="center">Table List</h2>
	<hr>
	<c:forEach var="table" items="${t}">
	<table border="1" align="center" >
		<tr>
			<td style="text-align:left;"><c:out value="${table.name}"></c:out></td>
			<td>
			<table border="0.5" align="center">
			
				<c:forEach var="item" items="${table.attri_type }">
				<tr>
				<td style="text-align:left;"><c:out value="${item.key}"></c:out></td>
				<td style="text-align:left;"><c:out value="${item.value}"></c:out></td>
				</tr>
				</c:forEach>
			
			</table>
			</td>
		
			
			
		</tr>
		<tr></tr>
		
		</table>
		</c:forEach>
		</div>
		</div>
		</div>
	
	
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>

</body>
</html>