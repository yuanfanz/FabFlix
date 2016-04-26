<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" %>
<%@ page import="com.fablix.moviedb.model.Movies"%>
<%@ page import="com.fablix.moviedb.DAO.MoviesDAO"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Single Movie</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link href="css/main.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="js/lhgcore.js"></script>
    <script type="text/javascript" src="js/lhgdialog.js"></script>
    <script type="text/javascript">
      function selflog_show(id)
      { 
         var num =  document.getElementById("number").value; 
         J.dialog.get({id: 'haoyue_creat',title: 'Bought Successfully',width: 600,height:400, 
        	 link: '<%=path%>/servlet/CartServlet?id='+id+'&num='+num+'&action=add', cover:true});
      }
      function add()
      {
         var num = parseInt(document.getElementById("number").value);
         if(num < 100)
         {
            document.getElementById("number").value = ++num;
         }
      }
      function sub()
      {
         var num = parseInt(document.getElementById("number").value);
         if(num > 1)
         {
            document.getElementById("number").value = --num;
         }
      }
     
    </script>
  </head>
  
  <body>
    <h1>Movie details</h1>
    <a href="index.jsp">Home</a> >> <a href="index.jsp">Movies List</a>
    <hr>
    <center>
      <table width="750" height="60" cellpadding="0" cellspacing="0" border="0">
        <tr>
          <% 
             MoviesDAO itemDao = new MoviesDAO();
             Movies item = itemDao.getMovieById(Integer.parseInt(request.getParameter("id")));
             if(item != null)
             {
          %>
          <td width="70%" valign="top">
             <table>
               <%-- <tr>
                 <td rowspan="5"><img src="images/<%=item.getPicture()%>" width="200" height="160"/></td>
               </tr> --%>
               <tr>
                 <td><B><%=item.getTitle() %></B></td> 
               </tr>
               <tr>
                 <td>Price：<%-- <%=item.getPrice() %> --%> $</td>
               </tr>
               <tr>
                 <td>Quantity：<span id="sub" onclick="sub();">-</span><input type="text" id="number" 
                      name="number" value="1" size="2"/><span id="add" onclick="add();">+</span></td>
               </tr> 
             </table>
             <div id="cart">
               <img src="images/buy_now.png"><a href="javascript:selflog_show(<%=item.getId()%>)">
                     <img src="images/in_cart.png"></a><a href="servlet/CartServlet?action=show">
                     <img src="images/view_cart.jpg"/></a>
             </div>
          </td>
          <% 
            }
          %>
          <% 
              String list ="";
              //从客户端获得Cookies集合
              Cookie[] cookies = request.getCookies();
              //遍历这个Cookies集合
              if(cookies!=null&&cookies.length>0)
              {
	              for(Cookie c:cookies)
	              {
	                  if(c.getName().equals("ListViewCookie"))
	                  {
	                     list = c.getValue();
	                  }
	              }
	          }
              
              list+=request.getParameter("id")+",";
              //如果浏览记录超过1000条，清零.
              String[] arr = list.split(",");
              if(arr!=null&&arr.length>0)
              {
                  if(arr.length>=1000)
                  {
                      list="";
                  }
              }
              Cookie cookie = new Cookie("ListViewCookie",list);
              response.addCookie(cookie);
          
          %>
          <td width="30%" bgcolor="#EEE" align="center">
             <br>
             <b><font color="#FF7F00">Movies you have browsed</font></b><br>
             
             <%-- <% 
                ArrayList<Movies> itemlist = itemDao.getViewList(list);
                if(itemlist != null && itemlist.size() > 0 )
                {
                   System.out.println("itemlist.size=" + itemlist.size());
                   for(Movies i : itemlist)
                   {
                         
             %>
             <div>
             <dl>
               <dt>
                 <a href="details.jsp?id=<%=i.getId()%>"><img src="images/<%=i.getPicture() %>" 
                 width="120" height="90" border="1"/></a>
               </dt>
               <dd class="dd_name"><%=i.getTitle() %></dd> 
               <dd class="dd_year">Year:<%=i.getYear() %>&nbsp;&nbsp;Price: <%=i.getPrice() %> $ </dd> 
             </dl>
             </div>
             <% 
                   }
                }
             %> --%>
          </td>
        </tr>
      </table>
    </center>
  </body>
</html>
