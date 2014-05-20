package theworldnews.handlers.news.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedHashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import theworldnews.database.connection.DatabaseConnection;
import theworldnews.database.news.objects.Article;
import theworldnews.database.news.objects.ArticleResponse;
import theworldnews.database.news.queries.DisplayQueries;
import theworldnews.database.users.objects.UserInfo;
 
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import theworldnews.database.news.queries.EditQueries;

@WebServlet(value = "/editArticle")
public class EditController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Gson gson;

	@Override
	public void init() throws ServletException {
		super.init();
		gson = new Gson();
	}

	/**
	 * Get the object to be edited
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		if (id == null) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}

		try (Connection con = DatabaseConnection.getConnection()) {

			PrintWriter out = resp.getWriter();

			Integer articleid = Integer.parseInt(id);
			
			LinkedHashMap<Article, UserInfo> article = DisplayQueries.getViewarticleById(con, articleid);
			
			// out.print(article);
			// out.print(id);
			Article key = article.keySet().iterator().next();
			UserInfo value = article.get(key);
			out.print(ArticleResponse.editArticle(key));

		} catch (SQLException | URISyntaxException e) {
			resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,e.getMessage());
		}

	}

	/**
	 * Submit edit
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		// String id = req.getParameter("id");
		// if (id == null) {
			// resp.getWriter().write("asd");
			// return;
		// }
			resp.getWriter().write("g");
			// PrintWriter out = resp.getWriter();


		// try (Connection con = DatabaseConnection.getConnection()) {
			// Article article = gson.fromJson(req.getReader(), Article.class);
			// article.id = Integer.parseInt(id);
			// int x = EditQueries.editArticle(con, article);
			 // out.print(id);
			// resp.getWriter().write("g");

		// } catch (JsonParseException ex) {
			// resp.sendError(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());
		// } catch (SQLException | URISyntaxException e) {
			// resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
		// }
	}
}
