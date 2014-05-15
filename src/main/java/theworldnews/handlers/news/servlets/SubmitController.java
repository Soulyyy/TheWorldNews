package theworldnews.handlers.news.servlets;

import java.io.IOException;
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
import theworldnews.database.news.queries.EditQueries;
import theworldnews.handlers.news.sockets.*;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;

@WebServlet(value = "/submitArticle")
public class SubmitController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private Gson gson;

	@Override
	public void init() throws ServletException {
		super.init();
		gson = new Gson();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setHeader("Content-Type", "application/json");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try (Connection con = DatabaseConnection.getConnection()) {
			Article article = gson.fromJson(req.getReader(), Article.class);
			System.out.println(article.articlegroup);

			EditQueries.addArticle(con, article);
			resp.setHeader("Content-Type", "application/json");
			resp.getWriter().write("{\"response\":\"newsarticle created \"}");
 

		} catch (JsonParseException ex) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());
		} catch (SQLException | URISyntaxException e) {
			resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}
}
