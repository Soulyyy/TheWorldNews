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
			String asd = req.getParameter("searchBox");
			// int articleid = 5;
			String rl = "";
			int c = 0;
 

			for(int i=0;i<asd.length();i++){
				System.out.println(asd.charAt(i));
				if (asd.charAt(i) =='"') {
					System.out.println("gh");
					c+=1;
					if (c == 3) {
						i+=1;
						while(asd.charAt(i) !='"') {
							rl+=asd.charAt(i);
							i+=1;
						}
					}
				}
			}
   
			int articleid=Integer.parseInt(rl.trim());
			// We display five tags
			System.out.println("BBBBBBBBBBBBBBBBBBBBBB");
						System.out.println(rl);
 
			
			resp.setHeader("Content-Type", "application/json");
			
			ArrayList<Tag> taglist = HashTagQueries.topHashTagsOnArticle(con,articleid, 5);
			
			Gson gson = new Gson();
			String test2 = gson.toJson(taglist);
			resp.getWriter().write(test2);
			
			
			// Gson gson = new GsonBuilder().create();
			// JsonArray response = gson.toJsonTree(taglist).getAsJsonArray();
		
			// resp.getWriter().write(response.getAsString());
			
			 // resp.getWriter().write("{\"response\":"+asd+"}");
			 
		} catch (SQLException | URISyntaxException | IOException e) {
			resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,e.getMessage());
		}

	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// Tag submission
		try (Connection con = DatabaseConnection.getConnection()) {
			String hashtag = req.getParameter("searchBox2");
			System.out.println("CCCCCCCCCCCCCCCCCCCCCCCCCC");
			System.out.println(hashtag);
			// if (hashtag == null)
				// hashtag = "nullhash";
			Integer userid = (Integer) req.getSession().getAttribute("LOGIN_ID");
			String asd = req.getParameter("term");
						System.out.println(asd);

			String rl = "";
			int c = 0;
 

			for(int i=0;i<asd.length();i++){
				System.out.println(asd.charAt(i));
				if (asd.charAt(i) =='"') {
					System.out.println("gh");
					c+=1;
					if (c == 3) {
						i+=1;
						while(asd.charAt(i) !='"') {
							rl+=asd.charAt(i);
							i+=1;
						}
					}
				}
			}
   			System.out.println(rl);

			int articleid=Integer.parseInt(rl.trim());
  
			int userTagCount = HashTagQueries.hashTagCountByUserOnArticle(con,userid, articleid);
			if (userTagCount < 5) {
				hashtag = hashtag.toLowerCase();
				if (!(hashtag.substring(0, 1).matches("#"))) {
					hashtag = "#" + hashtag;
				}
				@SuppressWarnings("unused")
				int response = HashTagQueries.addHashTag(con, userid,articleid, hashtag);
				resp.setHeader("Content-Type", "application/json");
				resp.getWriter().write("{\"response\":\"hashtag added\"}");
			} else {
				resp.sendError(HttpServletResponse.SC_FORBIDDEN,"You have reached the limit of tags");
			}
		} catch (SQLException | URISyntaxException e) {
			resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,e.getMessage());
		}

	}
}
