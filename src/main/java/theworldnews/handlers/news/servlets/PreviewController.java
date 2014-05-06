package theworldnews.handlers.news.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
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

/**
 * 
 * @author Souly
 * 
 */
@WebServlet(value = "/previewArticle")
public class PreviewController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Connection con = null;
		try {
			con = DatabaseConnection.getConnection();
			resp.setContentType("text/html;charset=UTF-8");
			PrintWriter out = resp.getWriter();
			List<Article> articles = new ArrayList<Article>();
			String type = req.getParameter("type");
			Integer size = Integer.parseInt(req.getParameter("size"));
			articles = DisplayQueries.getDisplayarticlesByNumberAndType(con,
					size, type);
			System.out.println("Siia");
			
			StringBuffer sb = new StringBuffer();
			int i = 0;
			while (!articles.isEmpty()) {
				if (articles.size() == 2 && ((i % 3 == 1) || (i % 3 == 0))) {
					sb.append(ArticleResponse.previewArticle(
							articles.remove(0), 1));
					sb.append(ArticleResponse.previewArticle(
							articles.remove(0), 2));
					sb.append(ArticleResponse.clearDiv());
				} else {
					if (i % 3 == 0) {
						sb.append(ArticleResponse.previewArticle(
								articles.remove(0), 0));
						i++;
					} else if (i % 3 == 1) {
						sb.append(ArticleResponse.previewArticle(
								articles.remove(0), 1));
						i++;
					} else if (i % 3 == 2) {
						sb.append(ArticleResponse.previewArticle(
								articles.remove(0), 2));
						i++;
						sb.append(ArticleResponse.clearDiv());
					}
				}
			}

			String news = sb.toString();
			req.setAttribute("news", news);
			req.setAttribute("articles", articles);
			out.println(news);

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
