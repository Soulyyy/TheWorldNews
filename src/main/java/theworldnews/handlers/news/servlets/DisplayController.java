package theworldnews.handlers.news.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import theworldnews.database.connection.DatabaseConnection;
import theworldnews.database.news.objects.Article;
import theworldnews.database.news.objects.ArticleResponse;
import theworldnews.database.news.queries.DisplayQueries;

/**
 * 
 * @author Souly
 * 
 */
@WebServlet(value = "/displayNews")
public class DisplayController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Connection con = null;
		try {
			con = DatabaseConnection.getConnection();
			PrintWriter out = resp.getWriter();
			int articleid = Integer.parseInt(req.getParameter("id"));
			Article article = DisplayQueries.getDisplayarticleById(con,
					articleid);

			out.print(ArticleResponse.displayArticle(article));

		} catch (SQLException e) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
		} catch (URISyntaxException e) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
		} catch (Exception e) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				resp.sendError(HttpServletResponse.SC_BAD_REQUEST,
						e.getMessage());
			}
		}

	}

}
