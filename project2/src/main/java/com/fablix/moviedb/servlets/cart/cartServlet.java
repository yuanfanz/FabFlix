package com.fablix.moviedb.servlets.cart;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fablix.moviedb.DAO.MoviesDAO;
import com.fablix.moviedb.model.*;

/**
 * Servlet implementation class cartServlet
 */
@WebServlet("/cartServlet")
public class cartServlet extends HttpServlet {
	
	private String action; //the operation of the cart
    private MoviesDAO mdao = new MoviesDAO();   
	
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public cartServlet() {
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
		
		String path = request.getContextPath();
//		PrintWriter out = response.getWriter();
		if(request.getParameter("action") != null){
			this.action = request.getParameter("action");
			if(action.equals("add")){
				try {
					if(addToCart(request, response)){
						response.sendRedirect(path+ "/cart.jsp");
//						request.getRequestDispatcher("/customerInfo.jsp").forward(request, response);
					} else {
						response.sendRedirect(path+ "/cartFailure.jsp");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("ERROR: cartServlet/doPost addtoCart fail. SQLException");
				}
			} else if(action.equals("show")){
				response.sendRedirect(path+ "/cart.jsp");
			} else if(action.equals("delete")){
				try {
					if(deleteFromCart(request, response)){
						response.sendRedirect(path+ "/cart.jsp");
					} else {
						response.sendRedirect(path+ "/cartFailure.jsp");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("ERROR: cartServlet/doPost deleteFromCart fail. SQLException");
				}
			} else if(action.equals("info")){
				response.sendRedirect(path+ "/confirm.jsp");
			} else if(action.equals("change")){
				try {
					if(changeQuantity(request, response)){
						response.sendRedirect(path+ "/cart.jsp");
					} else{
						response.sendRedirect(path+ "/cart.jsp");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if(action.equals("confirm")){
				response.sendRedirect(path+ "/checkOutConfirm.jsp");
			} else if(action.equals("changeQuantity")){ 
				try {
					if(change(request, response)){
						System.out.println("jinlaile");
						response.sendRedirect(path+ "/cart.jsp");
						} else {
							System.out.println("jinle");
					response.sendRedirect(path+ "/cart.jsp");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    } 
			else {
				response.sendRedirect(path+ "/failure.jsp");
			}
		}
	}
	
	private boolean addToCart(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException{
		
		String id = request.getParameter("id");
		Movies item = mdao.getMovieById(Integer.parseInt(id));
		
		//if this is the first time to add movie into the cart
		//if is, create a new cart object
		if(request.getSession().getAttribute("cart") == null){
			Cart cart = new Cart();
			request.getSession().setAttribute("cart", cart);
		}
		Cart cart = (Cart)request.getSession().getAttribute("cart");
				if(cart.changeQuantity(item, 1)){
					request.getSession().setAttribute("cart", cart);
					return true;
				} else {
					return false;
				}
	}
	
	private boolean deleteFromCart(HttpServletRequest request, HttpServletResponse response)
			throws SQLException{
		
		String id = request.getParameter("id");
		Cart cart = (Cart)request.getSession().getAttribute("cart");
	    Movies item = mdao.getMovieById(Integer.parseInt(id));
		
	    if(cart.removeMoviesFromCart(item)){
	    	return true;
	    } else {
	    	return false;
	    }
	}
	
	private boolean changeQuantity(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException{
		
		String id = request.getParameter("id");
		String number = request.getParameter("num");
		Cart cart = (Cart)request.getSession().getAttribute("cart");
		Movies item = mdao.getMovieById(Integer.parseInt(id));
		
		if(cart.changeQuantity(item, Integer.parseInt(number))){
			request.getSession().setAttribute("cart", cart);
			return true;
		} else {
			return false;
		}
	}
	
	private boolean change(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException{
		
		String id = request.getParameter("id");
		String number = request.getParameter("num");
		System.out.println(number);
		Cart cart = (Cart)request.getSession().getAttribute("cart");
		Movies item = mdao.getMovieById(Integer.parseInt(id));
		
		if(cart.change(item, Integer.parseInt(number))){
			request.getSession().setAttribute("cart", cart);
			return true;
		} else {
			return false;
		}
	}
//	public void init() throws ServletException {
//		// Put your code here
//	}
}
