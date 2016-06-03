<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="com.fablix.moviedb.model.Movies"%>
<%@ page import="com.fablix.moviedb.DAO.MoviesDAO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Home</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
  <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
  <script src="//code.jquery.com/jquery-1.11.2.js"></script>
  <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<link rel="stylesheet" type="text/css" href="http://cdnjs.cloudflare.com/ajax/libs/qtip2/2.2.1/jquery.qtip.min.css">
  <script type="text/javascript" src="http://cdn.jsdelivr.net/qtip2/2.2.1/jquery.qtip.min.js"></script>
  <script src="popup.js"></script>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
body{
    padding: 50px;
}

.qtip-wiki{
    max-width: 385px;
}

.qtip-wiki p{
    margin: 0 0 6px;
}

.qtip-wiki h1{
    font-size: 20px;
    line-height: 1.1;
    margin: 0 0 5px;
}

.qtip-wiki img{
    float: left;
    margin: 10px 10px 10px 0;
}

.qtip-wiki .info{
    overflow: hidden;
}

.qtip-wiki p.note{
    font-weight: 700;
}
#qtip-overlay{
    position: fixed;
    left: -10000em;
    top: -10000em;
}
#content{
   background: white url('../images/content_bg.jpg') repeat-x 0 0;
   color: #323436;
   border-bottom: 2px solid #e1e1e1;
}

    /* Applied to modals with show.modal.blur set to true */
    #qtip-overlay.blurs{ cursor: pointer; }

    /* Change opacity of overlay here */
    #qtip-overlay div{
        position: absolute;
        left: 0; top: 0;
        width: 100%; height: 100%;

        background-color: black;

        opacity: 0.7;
        filter:alpha(opacity=70);
        -ms-filter:"progid:DXImageTransform.Microsoft.Alpha(Opacity=70)";
    }
    </style>

</head>
<body>
<%! char c='0'; %> 
<div id="link"><a href='http://qtip2.com/demos/data/owl'>Owl</a></div>

</body>
</html>