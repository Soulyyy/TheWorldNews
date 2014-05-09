package theworldnews.handlers.news.servlets;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.*;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import theworldnews.database.connection.DatabaseConnection;
import theworldnews.database.news.objects.Article;
import theworldnews.database.news.objects.ArticleResponse;
import theworldnews.database.news.queries.DisplayQueries;

@Path("/previewArticle")
public class PreviewController {

	@GET
	@Produces(MediaType.TEXT_HTML)
	/**
	 * TODO: return Response type
	 */
	protected void doGet(@QueryParam("type") String type,
						 @QueryParam("size") Integer size,
						 @Context HttpServletRequest req,
						 @Context HttpServletResponse resp) throws IOException {
		try (Connection con = DatabaseConnection.getConnection()) {
			List<Article> articles = DisplayQueries.getDisplayarticlesByNumberAndType(con, size, type);

			StringBuilder sb = new StringBuilder();
			int i = 0;
			while (!articles.isEmpty()) {
				if (articles.size() == 2 && ((i % 3 == 1) || (i % 3 == 0))) {
					sb.append(ArticleResponse.previewArticle(articles.remove(0), 1));
					sb.append(ArticleResponse.previewArticle(articles.remove(0), 2));
					sb.append(ArticleResponse.clearDiv());
				} else {
					if (i % 3 == 0) {
						sb.append(ArticleResponse.previewArticle(articles.remove(0), 0));
						i++;
					} else if (i % 3 == 1) {
						sb.append(ArticleResponse.previewArticle(articles.remove(0), 1));
						i++;
					} else if (i % 3 == 2) {
						sb.append(ArticleResponse.previewArticle(articles.remove(0), 2));
						i++;
						sb.append(ArticleResponse.clearDiv());
					}
				}
			}

			String news = sb.toString();
			req.setAttribute("news", news);
			req.setAttribute("articles", articles);
			resp.getWriter().println(news);
		} catch (SQLException e) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
		} catch (URISyntaxException e) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
		} catch (Exception e) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
		}
	}
}
