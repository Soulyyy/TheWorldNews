package theworldnews.handlers.news.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
@WebServlet(value = "/displayarticle")
public class DisplayarticleController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			Connection con = DatabaseConnection.getConnection();
			PrintWriter out = resp.getWriter();
			int articleid = Integer.parseInt(req.getParameter("id"));
			Article article = DisplayQueries.getDisplayarticleById(con,
					articleid);

			out.print(ArticleResponse.viewArticle(article));

		} catch (SQLException e) {
			Logger lgr = Logger.getLogger(DisplayQueries.class.getName());
			lgr.log(Level.SEVERE, e.getMessage(), e);
		} catch (URISyntaxException e) {
			Logger lgr = Logger.getLogger(DisplayQueries.class.getName());
			lgr.log(Level.SEVERE, e.getMessage(), e);
		} catch (Exception e) {
			Logger lgr = Logger.getLogger(DisplayQueries.class.getName());
			lgr.log(Level.SEVERE, e.getMessage(), e);
		} finally {
			con.close();
		}

	}

}
