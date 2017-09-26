package fr.acceis.forum.servlet;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ReplyFilter implements javax.servlet.Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		//String strA = httpServletRequest.getContextPath();
		//String strB = httpServletRequest.getRequestURL().toString();
		String strC = httpServletRequest.getRequestURI().toString();
		String strD = httpServletRequest.getQueryString();		
		//System.out.println("trace E: strA: "+ strA +"; strB: "+ strB+"; strC: "+ strC+"; strD: "+ strD);
		HttpSession session = httpServletRequest.getSession();
		Boolean authentified = (Boolean) session.getAttribute("authentified");
		if ( authentified == null || authentified == false ){
			HttpServletResponse httpResponse = (HttpServletResponse) response;
			httpResponse.sendRedirect("login?uriToRemind="+strC+"?"+strD);
		} else {
			chain.doFilter( request, response);
		}		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

}