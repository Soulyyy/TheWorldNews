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

@WebServlet(value = "/latestNews")
public class LatestNewsController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try (Connection con = DatabaseConnection.getConnection()) {
			Gson gson = new Gson();

			String[] test = latest.getlatest(con);
			String[] temp = new String[10];
			for(int i=0; i<10; i++){
				   temp[i]=test[i];
			}
	
			while ( true) {
				if (test[5].equals(temp[5])) {
					test = latest.getlatest(con);
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				else {
					String test2 = gson.toJson(test);
					String df = gson.toJson(temp);
					resp.getWriter().write(test2);
					break;
				}
			
				
			}
		

		} catch (SQLException | URISyntaxException e) {
			resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,e.getMessage());
		}
	}
}
