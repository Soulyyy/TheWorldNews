package theworldnews.handlers.news.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import theworldnews.database.connection.DatabaseConnection;
import theworldnews.database.news.objects.Article;
import theworldnews.database.news.objects.ArticleResponse;
import theworldnews.database.news.objects.ArticleUserTuple;
import theworldnews.database.news.queries.DisplayQueries;
import theworldnews.database.users.objects.UserInfo;

@WebServlet(value = "/previewArticle")
public class PreviewController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String type = req.getParameter("type");
		String sizeStr = req.getParameter("size");

		if (type == null || sizeStr == null) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}

		try (Connection con = DatabaseConnection.getConnection()) {
			resp.setContentType("text/html;charset=UTF-8");
			PrintWriter out = resp.getWriter();
			Integer size = Integer.parseInt(sizeStr);

			// LinkedHashMap is bad as well
			ArrayList<ArticleUserTuple> articles = DisplayQueries
					.getDisplayarticlesByNumberAndType(con, size, type);

			StringBuilder sb = new StringBuilder();
			int i = 0;
			ArticleUserTuple key;
			UserInfo value;
			// sb.append(articles);
			while (!articles.isEmpty()) {
				if (articles.size() == 2 && ((i % 3 == 1) || (i % 3 == 0))) {
					key = articles.remove(0);
					sb.append(ArticleResponse.previewArticle(key.article,
							key.userinfo, 1));

					key = articles.remove(0);
					sb.append(ArticleResponse.previewArticle(key.article,
							key.userinfo, 2));

					sb.append(ArticleResponse.clearDiv());
				} else {
					if (i % 3 == 0) {
						key = articles.remove(0);
						sb.append(ArticleResponse.previewArticle(key.article,
								key.userinfo, 0));
						i++;
					} else if (i % 3 == 1) {
						key = articles.remove(0);
						sb.append(ArticleResponse.previewArticle(key.article,
								key.userinfo, 1));
						i++;
					} else if (i % 3 == 2) {
						key = articles.remove(0);
						sb.append(ArticleResponse.previewArticle(key.article,
								key.userinfo, 2));
						i++;
						sb.append(ArticleResponse.clearDiv());
					}
				}
			}

			String news = sb.toString();
			req.setAttribute("news", news);
			req.setAttribute("articles", articles);
			out.println(news);

		} catch (SQLException | URISyntaxException e) {
			resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
					e.getMessage());
		}
	}
}
