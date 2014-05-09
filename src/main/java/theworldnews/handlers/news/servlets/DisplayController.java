package theworldnews.handlers.news.servlets;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import theworldnews.database.connection.DatabaseConnection;
import theworldnews.database.news.objects.Article;
import theworldnews.database.news.objects.ArticleResponse;
import theworldnews.database.news.queries.DisplayQueries;

@Path("/displayArticle")
public class DisplayController {

	@GET
	@Produces(MediaType.TEXT_HTML)
	public Response doGet(@QueryParam("id") Integer id) {
		try (Connection con = DatabaseConnection.getConnection()) {
			Article article = DisplayQueries.getDisplayarticleById(con, id);

			return Response.ok(ArticleResponse.displayArticle(article)).build();
		} catch (SQLException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		} catch (URISyntaxException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}
}
