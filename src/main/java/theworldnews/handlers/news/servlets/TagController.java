package theworldnews.handlers.news.servlets;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;

import theworldnews.database.connection.DatabaseConnection;
import theworldnews.database.news.objects.Tag;
import theworldnews.database.news.queries.HashTagQueries;

@WebServlet(value = "/tagController")
public class TagController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// Get tags for article
		try (Connection con = DatabaseConnection.getConnection()) {
			// Integer articleid = Integer.parseInt(req.getParameter("term"));
			String asd = req.getParameter("term");
			// We display five tags
			System.out.println("BBBBBBBBBBBBBBBBBBBBBB");
			// System.out.println(articleid);
			// Gson gson = new Gson();

			// ArrayList<Tag> taglist = HashTagQueries.topHashTagsOnArticle(con,articleid, 5);
			// String test2 = gson.toJson(taglist);
			// resp.getWriter().write(test2);
			// Gson gson = new GsonBuilder().create();
			// JsonArray response = gson.toJsonTree(taglist).getAsJsonArray();
			resp.setHeader("Content-Type", "application/json");
			// resp.getWriter().write(response.getAsString());
			 resp.getWriter().write("{\"response\":"+asd+"}");
		} catch (SQLException | URISyntaxException | IOException e) {
			resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,e.getMessage());
		}

	}

	// @Override
	// public void doPost(HttpServletRequest req, HttpServletResponse resp)
			// throws IOException {
		// try (Connection con = DatabaseConnection.getConnection()) {
			// int userid = (Integer) req.getSession().getAttribute("LOGIN_ID");
			// int articleid = Integer.parseInt(req.getParameter("id"));
			// int userTagCount = HashTagQueries.hashTagCountByUserOnArticle(con,
					// userid, articleid);
			// if (userTagCount < 5) {
				// String hashtag = req.getParameter("hashtag");
				// hashtag = hashtag.toLowerCase();
				// if (!(hashtag.substring(0, 1).matches("#"))) {
					// hashtag = "#" + hashtag;
				// }
				// int response = HashTagQueries.addHashTag(con, userid,
						// articleid, hashtag);
				// resp.setHeader("Content-Type", "application/json");
				// resp.getWriter().write("{\"response\":\"hashtag added\"}");
			// } else {
				// resp.sendError(HttpServletResponse.SC_FORBIDDEN,
						// "You have reached the limit of tags");
			// }
		// } catch (SQLException | URISyntaxException e) {
			// resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
					// e.getMessage());
		// }

	// }
}
