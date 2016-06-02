<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>MovieList</title>
<style>
.qtip-wiki{
    width: 700px;
}

.qtip-wiki p{
    margin: 0 0 6px;
}

.qtip-wiki h1{
    font-size: 5px;
    line-height: 1.1;
    margin: 0 0 1px;
}

.qtip-wiki img{
    float: left;
    margin: 5px 5px 5px 0;
}

.qtip-wiki .info{
    overflow: hidden;
}

.qtip-wiki p.note{
    font-weight: 700;
}

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
    float: right;
    margin: 0 50px 10px 10px;
    filter: alpha(opacity=40); /* For IE8 and earlier */
    }
    td{
    padding:20px;
    margin:5px;
    }
    
    </style>

    <!-- Bootstrap -->
   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<link rel="stylesheet" 
  href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
  <link rel="stylesheet" type="text/css" href="http://cdnjs.cloudflare.com/ajax/libs/qtip2/2.2.1/jquery.qtip.min.css">
  <script type="text/javascript" src="http://cdn.jsdelivr.net/qtip2/2.2.1/jquery.qtip.min.js"></script>
  <script src="popup.js"></script>
<script type="text/javascript">
	//window.alert("wowowowowo");
	//var query = ${query}
	var currentPage = ${movieInfoList.currentPage};
	var totalPage = ${movieInfoList.totalPage};
	window.onload = function() {
		if (currentPage <= 1) {
			document.getElementById("prev").disabled = "disabled";
		}
		if (currentPage >= totalPage) {
			document.getElementById("next").disabled = "disabled";
		}
	}

	function submit(actionURL) {
		var btn = document.getElementById("form");
		btn.action = actionURL;
		btn.submit();
	}

	function nextPage() {
		if (currentPage >= totalPage) {
			return false;
		} else {
			//console.log('searchServlet?${query}'+'currentpageNum='
			//+(currentPage+1)+"&orderWord=${orderWord}&orderWay=${orderWay}&pageSize=${pageSize}&prefix=${prefix}&genre=${genre}");
			submit('searchServlet?${query}'
					+ 'currentpageNum='
					+ (currentPage + 1)
					+ '&orderWord=${orderWord}&orderWay=${orderWay}&pageSize=${pageSize}&prefix=${prefix}&genre=${genre}');
			return true;
		}
	}

	function prevPage() {
		//document.getElementById("next").value= "aaa";
		if (currentPage <= 1) {
			return false;
		} else {
			submit('searchServlet?${query}'
					+ 'currentpageNum='
					+ (currentPage - 1)
					+ '&orderWord=${orderWord}&orderWay=${orderWay}&pageSize=${pageSize}&prefix=${prefix}&genre=${genre}');
			return true;
		}
	}

	function titleasc() {
		submit('searchServlet?${query}'
				+ 'currentpageNum='
				+ currentPage
				+ '&orderWord=title&orderWay=ASC&pageSize=${pageSize}&prefix=${prefix}&genre=${genre}');
		return true;
	}

	function titledesc() {
		submit('searchServlet?${query}'
				+ 'currentpageNum='
				+ currentPage
				+ '&orderWord=title&orderWay=DESC&pageSize=${pageSize}&prefix=${prefix}&genre=${genre}');
		return true;
	}
	function yearasc() {
		submit('searchServlet?${query}'
				+ 'currentpageNum='
				+ currentPage
				+ '&orderWord=year&orderWay=ASC&pageSize=${pageSize}&prefix=${prefix}&genre=${genre}');
	}

	function yeardesc() {
		submit('searchServlet?${query}'
				+ 'currentpageNum='
				+ currentPage
				+ '&orderWord=year&orderWay=DESC&pageSize=${pageSize}&prefix=${prefix}&genre=${genre}');
	}
	function pageSize(pageSize) {
		submit('searchServlet?${query}'
				+ 'currentpageNum=1&orderWord=${orderWord}&orderWay=${orderWay}&prefix=${prefix}&genre=${genre}&pageSize='
				+ pageSize);
	}
</script>


</head>



<body>
<div class="container">
<div class="jumbotron">
        <h1>FabFlix</h1>
        <h4>We have all the movies that you want!</h4>
        <div class="menutext"><a href="main.jsp">Home</a> | <a href="search.jsp">Advanced Search</a> | <a href="cart.jsp">My Cart</a> | <a href="checkOutConfirm.jsp">Check out</a></div>
</div>

<div class="jumbotron">
	<h2 align="center">Movie List</h2>
	<hr>
	<h2 align="center"><c:if test="${empty movieInfoList.dataList}">No such movies found!</c:if></h2>
	
      
	<c:forEach var="movieInfo" items="${movieInfoList.dataList}">
		
		<table border="0" align="center" >
		<tr>
			<td>
				<img src="<c:url value="${movieInfo.movie.banner_url}"/>" width="200">
			</td>
		
		<td>
			<table border="0" align="center">
				<tr>
					<td style="text-align:left;">Id</td>
					<td><c:out value="${movieInfo.movie.id}"></c:out></td>
				</tr>
				<td>&nbsp;</td>
				<tr>
					<td>Title</td>
				
					<td><c:url value="/singleMovie.jsp" var="browse" > 
  						<c:param name="id" value="${movieInfo.movie.id}" />
						</c:url> 
						<div class="selector">
						<a href="${browse}" id="trigger" name="${movieInfo.movie.title}">
						<c:out value="${movieInfo.movie.title}"></c:out>
						</a>
						</div>
					</td>
				</tr>
				<td>&nbsp;</td>
				<tr>
					<td>Year</td>
					<td><c:out value="${movieInfo.movie.year}"></c:out></td>
				</tr>
				<td>&nbsp;</td>
			<tr>
				<td>Director</td>
				<td><c:out value="${movieInfo.movie.director}"></c:out></td>
			</tr>
			<td>&nbsp;</td>
			<tr>
				<td>Stars</td>
				<td><c:forEach var = "star" items="${movieInfo.stars}" >
						<c:url value="/singleStar.jsp" var="browse" > 
  						<c:param name="id" value="${star.id}" />
						</c:url>
						<a href="${browse}"> 
						<c:out value="${star.first_name}"></c:out>
						<c:out value="${star.last_name}"></c:out>
						<c:out value=" "></c:out>
						</a>
						<br>
					</c:forEach>
				</td>
			</tr>
			<td>&nbsp;</td>
			<tr>
				<td>Genres</td>
				<td><c:forEach var = "genre" items="${movieInfo.genres}" >
				        <c:url value="searchServlet" var="browse" > 
  						<c:param name="genre" value="${genre.name}" />
						</c:url>
						<a href="${browse}">
						<c:out value="${genre.name}"></c:out>
						<c:out value=" "></c:out>
						</a>
					</c:forEach>
				</td>
			</tr>
			<td>&nbsp;</td>
			<tr>
				<td>Trailer URL</td>
				<td><a href="<c:url value="${movieInfo.movie.trailer_url}"/>">&nbsp;Click Here</a></td>
			</tr>
			</table>
			</td>
		</tr>
		
		</table>
		
		<br>
		<hr>
		
	</c:forEach>
</div>


	     <div style="text-align: center">
				<form method='post' id="form" action='searchServlet'>
						<nav>
  							<ul class="pager">
								<li class="previous"><a id="prev"  onClick="prevPage()" class="btn btn-default"><span aria-hidden="true">&larr;</span>Prev</a></li>
					<%-- 				<c:out value="totalrecord=${movieInfoList.totalRecord}  "></c:out> --%>
									<c:out value="${movieInfoList.currentPage} out of ${movieInfoList.totalPage}"></c:out>
					<%-- 				<c:out value="totalpage=${movieInfoList.totalPage}  "></c:out> --%>
								<li class="next"><a id="next" onClick="nextPage()" class="btn btn-default">Next<span aria-hidden="true">&rarr;</span></a></li>
							 </ul>
						</nav>
<!-- 					<input id="prev" type="button" onClick="prevPage()" value="prev"> -->
<!-- 					<input id="next" type="button" onClick="nextPage()" value="next"> -->
				    <div class="btn-group">
					  <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					    Results per Page <span class="caret"></span>
					  </button>
					  <ul class="dropdown-menu">
					   	<li><a id="five"  onClick="pageSize(5)" >5</a></li>
					   	<li><a id="ten"  onClick="pageSize(10)">10</a></li>
					   	<li><a id="fifteen"  onClick="pageSize(15)">15</a></li>
					   	<li><a id="twenty"  onClick="pageSize(20)">20</a></li>
					   	<li><a id="twentyfive"  onClick="pageSize(25)">25</a></li>
						<li><a id="thirty"  onClick="pageSize(30)">30</a></li>
					  </ul>
					</div>
					<br>
					<br>
					<button id="titleASC" type="button" onClick="titleasc()" class="btn btn-success">title&uarr;</button>
					<button id="titleDESC" type="button" onClick="titledesc()" class="btn btn-danger">title&darr;</button>
<!-- 					<input id="titleASC" type="submit" onClick="titleasc()" value="title up"> -->
<!-- 					<input id="titleDESC" type="submit" onClick="titledesc()" value="title down">  -->
					<button id="yearASC" type="button" onClick="yearasc()" class="btn btn-success">year&uarr;</button>
					<button id="yearDESC" type="button" onClick="yeardesc()" class="btn btn-danger">year&darr;</button>
<!-- 					<input id="yearASC" type="submit" onClick="yearasc()" value="year up"> -->
<!-- 					<input id="yearDESC" type="submit" onClick="yeardesc()" value="year down">  -->
				</form>
			</div>
	
	<div style="text-align:center">
        <footer class="col-lg-4 col-lg-offset-4">
        <p>Posted by: cs122b-Group20</p>
       	 <p>Contact information: <a href="mailto:sc122b20@uci.edu">sc122b20@uci.edu</a>.</p>
     	</footer>
     	</div>
	</div>
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
</body>
</html>