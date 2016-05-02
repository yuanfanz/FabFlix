package com.fablix.moviedb.dashboard;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.fablix.moviedb.DAO.EmployeesDAO;


@WebServlet("/dashLoginServlet")
public class dashLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private EmployeesDAO eDAO = new EmployeesDAO();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public dashLoginServlet() {
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
		//User user = new User();
		//PrintWriter out = response.getWriter();
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		//String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
		//System.out.println("gRecaptchaResponse=" + gRecaptchaResponse);
		// Verify CAPTCHA.
		/*boolean valid = verifyUtils.verify(gRecaptchaResponse);
		if (!valid) {
		    //errorString = "Captcha invalid!";
		    out.println("<HTML>" +
				"<HEAD><TITLE>" +
				"MovieDB: Error" +
				"</TITLE></HEAD>\n<BODY>" +
				"<P>Recaptcha WRONG!!!! </P></BODY></HTML>");
		    
		}else{*/
		try{
			if (eDAO.isAuthenticate(email, password)){
				
				//request.getSession().removeAttribute("fail");
				// set user's info o
				//user.setUsername(email);
		        //set attribute of the session to show in the main page.
				//HttpSession session = request.getSession();
		        //session.setAttribute("user", user);
		        response.sendRedirect(request.getContextPath()+ "/employeedash.jsp");
		        //request.getRequestDispatcher("/main.jsp").forward(request, response);
			}else{
				request.getSession().setAttribute("fail",true);
				response.sendRedirect(request.getContextPath()+ "/_dashboard.jsp");
				//request.getRequestDispatcher("/login.jsp").forward(request, response);
			}
		
		}catch(SQLException ex){
			System.err.println(ex.getMessage());
		}
		}
	}


