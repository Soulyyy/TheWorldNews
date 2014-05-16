package theworldnews.handlers.users.filters;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import theworldnews.database.connection.DatabaseConnection;
import theworldnews.database.news.objects.Article;
import theworldnews.database.news.queries.DisplayQueries;
import theworldnews.database.users.objects.UserInfo;

public class EditArticleFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain fc) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		Integer loginRights = (Integer) req.getSession().getAttribute("LOGIN_RIGHTS");
		Integer loginId = (Integer) req.getSession().getAttribute("LOGIN_ID");

		Integer articleId = Integer.valueOf(req.getParameter("id"));

		if (loginRights == null || loginRights < 1) {
			res.sendRedirect("/Index.jsp");
			return;
		}
		if (loginRights == 2) {
			fc.doFilter(request, response);
			return;
		}
		if (loginRights == 1) {
			if (articleId == null || loginId == null) {
				res.sendRedirect("/Index.jsp");
				return;
			}

			try (Connection con = DatabaseConnection.getConnection()) {
				Map<Article, UserInfo> articles = DisplayQueries.getDisplayarticleById(con, articleId);

				Article article = articles.keySet().iterator().next();

				if (article.authorid == loginId) {
					fc.doFilter(request, response);
				}
			} catch (SQLException | URISyntaxException | NoSuchElementException ex) {
				res.sendRedirect("/Index.jsp");
				Logger.getLogger(EditArticleFilter.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
			}
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void destroy() {
	}
}
