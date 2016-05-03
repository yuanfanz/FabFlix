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

import com.fablix.moviedb.DAO.MoviesDAO;
import com.fablix.moviedb.DAO.StarsDAO;
import com.fablix.moviedb.model.Movies;
import com.fablix.moviedb.model.Stars;

/**
 * Servlet implementation class dashAddMovieServlet
 */
@WebServlet("/dashAddMovieServlet")
public class dashAddMovieServlet extends HttpServlet {
	private MoviesDAO mdao = new MoviesDAO();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public dashAddMovieServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

				System.out.println("IN");
				boolean found = true;
				if(request.getParameter("year").length() == 0 || request.getParameter("title").length() == 0 
						|| request.getParameter("director").length() == 0 || request.getParameter("genre").length() == 0){
					
					response.sendRedirect(request.getContextPath()+ "/dashMovieFailure.jsp");
				} else if (request.getParameter("star_first").length() == 0 && request.getParameter("star_last").length() == 0){
					
					response.sendRedirect(request.getContextPath()+ "/dashMovieFailure.jsp");
				} else {
				try {
					
					found = findMovie(request, response);
					System.out.println(found);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				if(found == false){
				try {
					addMovie(request, response);
				} catch (SQLException | ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("@dashAddMovieServlet line 54.");
				}
				response.sendRedirect(request.getContextPath()+ "/dashMovieSuccess.jsp");

				}
				}
		}
	
	private void addMovie(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, ParseException {
	
		String trailer_url = null; String banner_url = null;
		String title = request.getParameter("title");
		String year = request.getParameter("year");
		String director = request.getParameter("director");
		trailer_url = request.getParameter("trailer_url");
		banner_url = request.getParameter("photo_url");
		String first_name = request.getParameter("star_first");
		String last_name = request.getParameter("star_last");
		String genre = request.getParameter("genre");
		
		Movies m = new Movies();
		
		m.setTitle(title);
		m.setYear(Integer.parseInt(year));
		m.setDirector(director);
		m.setTrailer_url(trailer_url);
		m.setBanner_url(banner_url);
	
		mdao.addMovie(m, first_name, last_name, genre);
	}
	
	private boolean findMovie(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException {
		
		Movies m = new Movies();
		String title = request.getParameter("title");
		String year = request.getParameter("year");
		String director = request.getParameter("director");
		
		m = mdao.getMovieByTitle(title);
		if(m.getId() == -1){
			return false;
		} else {
			if(m.getYear() == Integer.parseInt(year) && m.getDirector() == director){
				System.out.print("This movie existed.");
				return true;
			} else {
				return false;
			}
		}
	}
}
