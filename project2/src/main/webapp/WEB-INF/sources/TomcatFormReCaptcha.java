
/* A servlet to display the contents of the MySQL movieDB database */

import java.io.*;
import java.net.*;
import java.sql.*;
import java.text.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TomcatFormReCaptcha extends HttpServlet
{
    public String getServletInfo()
    {
       return "Servlet connects to MySQL database and displays result of a SELECT";
    }

    // Use http GET

    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException
    {
        // Output stream to STDOUT
        PrintWriter out = response.getWriter();

	String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
	System.out.println("gRecaptchaResponse=" + gRecaptchaResponse);
	// Verify CAPTCHA.
	boolean valid = VerifyUtils.verify(gRecaptchaResponse);
	if (!valid) {
	    //errorString = "Captcha invalid!";
	    out.println("<HTML>" +
			"<HEAD><TITLE>" +
			"MovieDB: Error" +
			"</TITLE></HEAD>\n<BODY>" +
			"<P>Recaptcha WRONG!!!! </P></BODY></HTML>");
	    return;
	}
	  
        String loginUser = "mytestuser";
        String loginPasswd = "mypassword";
        String loginUrl = "jdbc:mysql://localhost:3306/moviedb";

        response.setContentType("text/html");    // Response mime type


        out.println("<HTML><HEAD><TITLE>MovieDB</TITLE></HEAD>");
        out.println("<BODY><H1>MovieDB</H1>");

        try
           {
              //Class.forName("org.gjt.mm.mysql.Driver");
              Class.forName("com.mysql.jdbc.Driver").newInstance();

              Connection dbcon = DriverManager.getConnection(loginUrl, loginUser, loginPasswd);
              // Declare our statement
              Statement statement = dbcon.createStatement();

	      String lastname = request.getParameter("lastname");
              String query = "SELECT * from creditcards where last_name = '" + lastname + "'";

              // Perform the query
              ResultSet rs = statement.executeQuery(query);

              out.println("<TABLE border>");

              // Iterate through each row of rs
              while (rs.next())
              {
                  String m_ID = rs.getString("ID");
                  String m_FN = rs.getString("first_name");
                  String m_LN = rs.getString("last_name");
                  out.println("<tr>" +
                              "<td>" + m_ID + "</td>" +
                              "<td>" + m_FN + "</td>" +
                              "<td>" + m_LN + "</td>" +
                              "</tr>");
              }

              out.println("</TABLE>");

              rs.close();
              statement.close();
              dbcon.close();
            }
        catch (SQLException ex) {
              while (ex != null) {
                    System.out.println ("SQL Exception:  " + ex.getMessage ());
                    ex = ex.getNextException ();
                }  // end while
            }  // end catch SQLException

        catch(java.lang.Exception ex)
            {
                out.println("<HTML>" +
                            "<HEAD><TITLE>" +
                            "MovieDB: Error" +
                            "</TITLE></HEAD>\n<BODY>" +
                            "<P>SQL error in doGet: " +
                            ex.getMessage() + "</P></BODY></HTML>");
                return;
            }
         out.close();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException
    {
	doGet(request, response);
    }
}
