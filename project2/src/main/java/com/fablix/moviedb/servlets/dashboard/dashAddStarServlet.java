package com.fablix.moviedb.servlets.dashboard;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fablix.moviedb.DAO.StarsDAO;
import com.fablix.moviedb.model.Stars;

/**
 * Servlet implementation class dashAddStar
 */
@WebServlet("/dashAddStarServlet")
public class dashAddStarServlet extends HttpServlet {
	private StarsDAO sdao = new StarsDAO();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public dashAddStarServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
			try {
				Stars star = findStar(request, response);
				if(star.getId() == -1){
					try {
						addStar(request, response);
						response.sendRedirect(request.getContextPath()+ "/dashStarSuccess.jsp");
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						System.out.println("went wrong when add star, @dashAddStar servlet line 59.");
						response.sendRedirect(request.getContextPath()+ "/dashStarFailure.jsp");
					}
				} else {
					System.out.println("The star is existed so that you can not add him/her.");
					response.sendRedirect(request.getContextPath()+ "/dashStarFailure.jsp");
				}
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	private Stars findStar(HttpServletRequest request, HttpServletResponse response) 
			throws NumberFormatException, SQLException{
			
		String firstName = request.getParameter("firstname");
		String lastName = request.getParameter("lastname");
		Stars s = sdao.getStarsByName(firstName, lastName);
					
		return s;
	}
	
	private void addStar(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, ParseException {
		
		System.out.println("addStar procedure started.");
		String firstName = request.getParameter("firstname");
		String lastName = request.getParameter("lastname");
		String dob = request.getParameter("dob");
		String photo_url = request.getParameter("photo_url");
		Stars s = new Stars();
		s.setFirst_name(firstName);
		s.setLast_name(lastName);
		s.setPhoto_url(photo_url);
		
		DateFormat format = new SimpleDateFormat("yyyy.MM.dd", Locale.ENGLISH);
		Date date = format.parse(dob);
		s.setDob(date);
		
		sdao.addStar(s);
	}
}
