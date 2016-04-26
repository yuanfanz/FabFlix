package com.fablix.moviedb.servlets.checkout;

import java.io.IOException;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fablix.moviedb.DAO.CreditcardsDAO;
import com.fablix.moviedb.DAO.CustomerDAO;
import com.fablix.moviedb.DAO.SaleDAO;
import com.fablix.moviedb.model.Cart;
import com.fablix.moviedb.model.Movies;
import com.fablix.moviedb.model.Sale;

/**
 * Servlet implementation class confirmServlet
 */
@WebServlet("/confirmServlet")
public class confirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//private MoviesDAO mDao = new MoviesDAO(); 
      private CreditcardsDAO creditcardsDAO = new CreditcardsDAO();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public confirmServlet() {
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
		
		Enumeration<String> parameterNames = request.getParameterNames();
		Map<String,Object> params = new HashMap<String,Object>();
		boolean result = false;
		//put the movie key words into the params hashMap.
		while (parameterNames.hasMoreElements()) {
            String name = (String) parameterNames.nextElement();
            
            String[] paramValues = request.getParameterValues(name);
            if(paramValues[0].length() != 0){
            	
            	params.put(name, paramValues[0]);  	
            	
            }
		}
		
		try {
			 result = creditcardsDAO.confirmCre(params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		if(result == true){
			try {
				writeIntoSale(request, response, params);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("ERROR: Wrong with the confirmServlet: Wrong with writeIntoSale method when customer info is right.");
			}
		}
		if(result == false){
			System.out.println("ERROR: Customer info is wrong");
		}
		
//		request.getSession().setAttribute("result", result);
		
		//request.getRequestDispatcher("/movieList.jsp").forward(request, response);
		if(result == true){
			response.sendRedirect(request.getContextPath()+ "/checkOutSuccess.jsp");
		} else if (result == false){
			response.sendRedirect(request.getContextPath()+ "/checkOutFailure.jsp");
		} else {
			response.sendRedirect(request.getContextPath()+ "/checkOutFailure.jsp");
		}
	}
	
	private void writeIntoSale(HttpServletRequest request, HttpServletResponse response, Map<String,Object> params) 
			throws SQLException, IOException{
		if(request.getSession().getAttribute("cart") == null){
			System.out.println("ERROR: There is no movie in the shopping cart.");
			response.sendRedirect(request.getContextPath()+ "/shoppingCartFailure.jsp");
		}
	
		Cart cart = (Cart)request.getSession().getAttribute("cart");
		Set<Movies> movies = cart.getMovies().keySet();
		ArrayList<Integer> movieList = new ArrayList<Integer>();

		for(Movies movie : movies){
			movieList.add(movie.getId());
		}
		
		int cus_id = -1;
		if(params.containsKey("ccnum")){
			String ccnum = (String)params.get("ccnum");
			CustomerDAO cdao = new CustomerDAO();
			cus_id = cdao.findCusidByCc(ccnum);
			
			System.out.println("ccnum: " + ccnum);
		} else {
			System.out.print("ccnum is not correct");
		}
		
		if(cus_id == -1){
			System.out.print("ccnum is not correct");
		}
		
		SaleDAO sdao = new SaleDAO();
		Sale sale = new Sale();
		sale.setCustomerId(cus_id);
		sale.setMovieId(movieList);
		
		sdao.addSale(sale);
	}
}
