package TheWorldNews.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditrightCheckFilter implements Filter {

	private String contextPath;
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain fc) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
	    HttpServletResponse res = (HttpServletResponse) response;  

	    if (req.getSession().getAttribute("LOGIN_USER") == null) { //checks if there's a LOGIN_USER set in session...
	        res.sendRedirect(contextPath + "/Index.jsp"); //or page where you want to redirect
	    } else {
	      Integer userType = (Integer)req.getSession().getAttribute("LOGIN_USER");
	      if (userType < 2){ //check if user type is not admin
	        res.sendRedirect(contextPath + "/Index.jsp"); //or page where you want to  

	      }
	      fc.doFilter(request, response);
	    }
	}

	@Override
	public void init(FilterConfig fc) throws ServletException {
		contextPath = fc.getServletContext().getContextPath();
	}

}
