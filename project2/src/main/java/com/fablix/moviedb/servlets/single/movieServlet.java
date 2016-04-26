package com.fablix.moviedb.servlets.single;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fablix.moviedb.DAO.MoviesDAO;
import com.fablix.moviedb.model.Movies;

/**
 * Servlet implementation class movieServlet
 */
@WebServlet("/movieServlet")
public class movieServlet extends HttpServlet {
	
	private String action;
	private MoviesDAO mdao = new MoviesDAO();  
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public movieServlet() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getParameter("action") != null){
			this.action = request.getParameter("action");
			try {
				findMovie(request, response);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.getRequestDispatcher("/singleMovie.jsp").forward(request, response);
		}
	}

	private Movies findMovie(HttpServletRequest request, HttpServletResponse response) 
			throws NumberFormatException, SQLException{
		
		String id = request.getParameter("id");
		Movies m = mdao.getMovieById(Integer.parseInt(id));
		request.getSession().setAttribute("id",  id);
				
		return m;
	}
}
