package theworldnews.handlers.news.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import theworldnews.database.connection.DatabaseConnection;
import theworldnews.database.news.objects.Article;
import theworldnews.database.news.objects.ArticleResponse;
import theworldnews.database.news.queries.*;
import java.util.ArrayList;

@WebServlet(value = "/searchText")
public class SearchController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try (Connection con = DatabaseConnection.getConnection()) {
			String asd =req.getParameter("searchBox");
			Gson gson = new Gson();
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
			ArrayList<String> result = Search.getsearch(con,rl);
 
			String test2 = gson.toJson(result);
			resp.getWriter().write(test2);
			 
		} catch (SQLException | URISyntaxException e) {
			resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,e.getMessage());
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		String searchString = req.getParameter("searchBox").trim();

		String s = "searchBox=" + searchString;

		resp.sendRedirect("/jsp/Search.jsp?" + s);

		// try (Connection con = DatabaseConnection.getConnection()) {
			// String asd = req.getParameter("jsondata");
			// ArrayList<String> result = Search.getsearch(con,asd);
			
			
			// resp.setHeader("Content-Type", "application/json");
			// resp.getWriter().write("{\"response\":"+asd+"}");
			
		// }
		// catch (SQLException | URISyntaxException e) {
			// resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
		// }
	}

}
