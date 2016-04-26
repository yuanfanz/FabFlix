package com.fablix.moviedb.servlets.search;

import java.io.IOException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fablix.moviedb.DAO.MoiveInfoDAO;
import com.fablix.moviedb.DAO.MoviesDAO;
import com.fablix.moviedb.model.MovieInfo;
import com.fablix.moviedb.model.Movies;
import com.fablix.moviedb.model.Pager;

/**
 * Servlet implementation class searchServlet
 */
@WebServlet("/searchServlet")
public class searchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//private MoviesDAO mDao = new MoviesDAO(); 
      private MoiveInfoDAO movieInfoDAO = new MoiveInfoDAO();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public searchServlet() {
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
		
//		request.getParameter("title");
//		request.getParameter("year");
//		request.getParameter("director");
//		request.getParameter("first_name");
//		request.getParameter("last_name");
//		
		Enumeration<String> parameterNames = request.getParameterNames();
		Map<String,Object> params = new HashMap<String,Object>();
		Pager<MovieInfo> movieInfoList = new Pager<MovieInfo>();
		String query = "";
		
		
		
		//put the movie key words into the params hashMap.
		while (parameterNames.hasMoreElements()) {
            String name = (String) parameterNames.nextElement();
            
            String[] paramValues = request.getParameterValues(name);
            if(paramValues[0].length() != 0 
            		&& !name.equals("subMatch")
            		&& !name.equals("currentpageNum")
            		&& !name.equals("orderWord")
            		&& !name.equals("orderWay") 
            		&& !name.equals("pageSize")
            		&& !name.equals("prefix")
            		&& !name.equals("genre")
               ){
            	
            	params.put(name, paramValues[0]);
            	query += (name+"="+paramValues[0]+"&");  	
            	
            }
		}
		
			
			
		
		
		
		// check if there the user select the subMatch box;
		boolean subMatch = false;
		if (request.getParameterValues("subMatch")!=null){
			subMatch = true;
			query+= ("subMatch="+subMatch+"&");
		
		}else{
			subMatch = false;
		}
		
		//get current page number of the request.
		String currentPageNum = request.getParameter("currentpageNum")==null? "1":request.getParameter("currentpageNum");
		
		//get sort parameter of request.
		String orderWord = request.getParameter("orderWord")==null? "title":request.getParameter("orderWord");
		String orderWay  = request.getParameter("orderWay")==null? "ASC":request.getParameter("orderWay");
		
		//get page size of request.
		String pageSize = request.getParameter("pageSize")==null? "5":request.getParameter("pageSize");
		
		String prefix = request.getParameter("prefix");
		String genre = request.getParameter("genre");
		if(prefix != null && prefix.length()!=0)
		{
			//Pager<MovieInfo> movieInfoList = new Pager<MovieInfo>();
			MoiveInfoDAO mDao = new MoiveInfoDAO();
			//List<MovieInfo> movies = new ArrayList<MovieInfo>();
			try {
				movieInfoList = mDao.browseMovieInfo(prefix, orderWord, orderWay, Integer.valueOf(pageSize),Integer.valueOf(currentPageNum));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.err.println(e.getMessage());
			}

			request.getSession().setAttribute("movieInfoList", movieInfoList);
			request.getSession().setAttribute("prefix", prefix);
			request.getSession().setAttribute("orderWord",orderWord);
			request.getSession().setAttribute("orderWay",orderWay);
			request.getSession().setAttribute("pageSize", pageSize);
			request.getSession().removeAttribute("query");
			request.getSession().removeAttribute("genre");
		}
		else if(genre != null && genre.length()!=0)
		{
			
			List<MovieInfo> movies = new ArrayList<MovieInfo>();
			try {
				movieInfoList = movieInfoDAO.browseMovieByGenre(genre, orderWord, orderWay, Integer.valueOf(pageSize),Integer.valueOf(currentPageNum));
				
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.err.println(e.getMessage());
			}
			request.getSession().setAttribute("movieInfoList", movieInfoList);
			request.getSession().setAttribute("genre", genre);
			request.getSession().setAttribute("orderWord",orderWord);
			request.getSession().setAttribute("orderWay",orderWay);
			request.getSession().setAttribute("pageSize", pageSize);
			request.getSession().removeAttribute("query");
			request.getSession().removeAttribute("prefix");
		}else
		{
			try {
			 movieInfoList = movieInfoDAO.searchMovieInfo(params, orderWord, orderWay ,subMatch,Integer.valueOf(pageSize),Integer.valueOf(currentPageNum));
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println(e.getMessage());
			}
			request.getSession().setAttribute("movieInfoList", movieInfoList);
			request.getSession().setAttribute("query", query);
			request.getSession().setAttribute("orderWord",orderWord);
			request.getSession().setAttribute("orderWay",orderWay);
			request.getSession().setAttribute("pageSize", pageSize);
			request.getSession().removeAttribute("prefix");
			request.getSession().removeAttribute("genre");


			//request.getRequestDispatcher("/movieList.jsp").forward(request, response);
			
		}
		response.sendRedirect(request.getContextPath()+ "/movieList.jsp");
	}

}
