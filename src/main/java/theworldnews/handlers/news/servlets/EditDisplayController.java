package theworldnews.handlers.news.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import theworldnews.database.connection.DatabaseConnection;
import theworldnews.database.news.objects.Article;
import theworldnews.database.news.objects.ArticleResponse;
import theworldnews.database.news.queries.DisplayQueries;

@WebServlet(value = "/editArticleDisplay")
public class EditDisplayController extends HttpServlet {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Gets all the articles the user has a right to edit If the user has accessrights 0, return 401- Unauthorized If
	 * the user has accessrights 1, return a jsp with articles he created If the user has accessrights 2, return all
	 * articles
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Integer userAuth = (Integer) req.getSession().getAttribute("LOGIN_RIGHTS");
		Integer userId = (Integer) req.getSession().getAttribute("LOGIN_ID");

		if (userAuth == null || userId == null) {
			resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		}

		if (userAuth > 0) {
			try (Connection con = DatabaseConnection.getConnection()) {
				PrintWriter out = resp.getWriter();
				out.println(userAuth);

				List<Article> articles;

				articles = DisplayQueries.getEditViewArticlesByAuthor(con, userId);

				out.print(ArticleResponse.editDisplayArticle(articles));

			} catch (SQLException | URISyntaxException e) {
				resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
			}
		}
	}
}
