package com.fablix.moviedb.servlets.search;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fablix.moviedb.DAO.MoviesDAO;
import com.fablix.moviedb.model.Movies;
import com.google.gson.Gson;

/**
 * Servlet implementation class ajaxServlet
 */
@WebServlet("/ajaxServlet")
public class ajaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private MoviesDAO movie = new MoviesDAO(); 
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ajaxServlet() {
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
		request.setCharacterEncoding("utf-8");
		
		response.setContentType("application/json");
        try {
                String term = request.getParameter("term");
                System.out.println("Data from ajax call " + term);

                ArrayList<String> list = movie.browseMovies(term);
                System.out.println(list);

                String searchList = new Gson().toJson(list);
                response.getWriter().write(searchList);

        } catch (Exception e) {
                System.err.println(e.getMessage());
        }

	}

}
