package com.fablix.moviedb.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class loginFilter
 */
@WebFilter(filterName="B1_loginFilter" , urlPatterns={"/*"})
public class loginFilter implements Filter {

    /**
     * Default constructor. 
     */
    public loginFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest req, ServletResponse rp, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest request  = (HttpServletRequest) req;
		HttpServletResponse response  = (HttpServletResponse) rp;
		
		String uri = request.getRequestURI();
		//System.out.println(uri);
		if (uri.contains("login") || uri.contains("dash")){
			if (request.getSession().getAttribute("user")!=null){
				response.sendRedirect(request.getContextPath()+"/main.jsp");
			}else{
				chain.doFilter(req, rp);
			}			
		
		}else{
			if(request.getSession().getAttribute("user")!=null){
				// pass the request along the filter chain
				chain.doFilter(req, rp);
			}else{
				 response.sendRedirect(request.getContextPath()+"/login.jsp");
				 
			}
		}		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
