package theworldnews.handlers.users.filters;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PostHashtagFilter implements Filter {

	private String contextPath;

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain fc) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		Logger.getLogger(this.getClass().getName()).log(Level.INFO,
				"Passing login check filter for page: " + req.getRequestURI());

		Integer loginRights = (Integer) req.getSession().getAttribute(
				"LOGIN_RIGHTS");

		if (loginRights == null || loginRights < 0) {
			// Try tricks, fly to index, makes sense.
			res.sendRedirect(contextPath + "/Index.jsp");
		} else {
			fc.doFilter(request, response);
		}
	}

	@Override
	public void init(FilterConfig fc) throws ServletException {
		contextPath = fc.getServletContext().getContextPath();
	}
}
