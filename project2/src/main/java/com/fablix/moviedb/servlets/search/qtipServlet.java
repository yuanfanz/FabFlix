package com.fablix.moviedb.servlets.search;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fablix.moviedb.DAO.MoviesDAO;
import com.fablix.moviedb.model.Movies;
import com.google.gson.JsonObject;

/**
 * Servlet implementation class ajaxServlet
 */
@WebServlet("/qtipServlet")
public class qtipServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private MoviesDAO movie = new MoviesDAO(); 
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public qtipServlet() {
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

                Movies result = movie.getMovieByTitle(term);
                System.out.println(result);
                JsonObject jobj = new JsonObject();
                jobj.addProperty("id", result.getId());
                jobj.addProperty("title",result.getTitle());
                jobj.addProperty("year",result.getYear());
                jobj.addProperty("director",result.getDirector());
                jobj.addProperty("trailer_url",result.getTrailer_url());
                
                System.out.println(jobj);
                PrintWriter out = response.getWriter();
                out.print(jobj);
                out.flush();

        } catch (Exception e) {
                System.err.println(e.getMessage());
        }

	}

}
