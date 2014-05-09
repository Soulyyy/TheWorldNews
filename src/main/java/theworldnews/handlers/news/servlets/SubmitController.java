package theworldnews.handlers.news.servlets;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import theworldnews.database.connection.DatabaseConnection;
import theworldnews.database.news.objects.Article;
import theworldnews.database.news.queries.EditQueries;
import theworldnews.handlers.news.sockets.*;

@Path("/submitArticle")
public class SubmitController {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response doPost(Article article,
						   @Context HttpServletRequest req) {
		try (Connection con = DatabaseConnection.getConnection()) {
			System.out.println(article.articlegroup);

			EditQueries.addArticle(con, article);

			//LatestNewsSocketController.find(req.getServletContext()).broadcast("a");
			try {
				LatestNewsSocketController.find(req.getServletContext())
						.broadcast("a");
			} catch (NullPointerException e) {
				System.out.println("WS nullpointer.");
			}

			return Response.ok("{\"response\":\"newsarticle created \"}").build();
		} catch (SQLException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		} catch (URISyntaxException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}
}
