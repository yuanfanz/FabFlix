package com.fablix.moviedb.servlets.log;
import java.util.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fablix.moviedb.DAO.CustomerDAO;
import com.fablix.moviedb.model.User;
import com.fablix.moviedb.verify.verifyUtils;

/**
 * Servlet implementation class loginServlet
 */
@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private CustomerDAO cDAO = new CustomerDAO();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		User user = new User();
		PrintWriter out = response.getWriter();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
		System.out.println("gRecaptchaResponse=" + gRecaptchaResponse);
		// Verify CAPTCHA.
		boolean valid = verifyUtils.verify(gRecaptchaResponse);
		if (!valid) {
		    //errorString = "Captcha invalid!";
		    out.println("<HTML>" +
				"<HEAD><TITLE>" +
				"MovieDB: Error" +
				"</TITLE></HEAD>\n<BODY>" +
				"<P>Recaptcha WRONG!!!! </P></BODY></HTML>");
		    
		}else{
		try{
			if (cDAO.isAuthenticate(username, password)){
				
				//request.getSession().removeAttribute("fail");
				// set user's info 
				user.setUsername(username);
		        //set attribute of the session to show in the main page.
				HttpSession session = request.getSession();
		        session.setAttribute("user", user);
		        response.sendRedirect(request.getContextPath()+ "/main.jsp");
		        //request.getRequestDispatcher("/main.jsp").forward(request, response);
			}else{
				request.getSession().setAttribute("fail",true);
				response.sendRedirect(request.getContextPath()+ "/login.jsp");
				//request.getRequestDispatcher("/login.jsp").forward(request, response);
			}
		
		}catch(SQLException ex){
			System.err.println(ex.getMessage());
		}
		}
	}

}
