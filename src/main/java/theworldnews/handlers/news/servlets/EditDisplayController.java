package theworldnews.handlers.news.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/editArticleDisplay")
public class EditDisplayController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Gets all the articles the user has a right to edit If the user has
	 * accessrights 0, return 401- Unauthorized If the user has accessrights 1,
	 * return a jsp with articles he created If the user has accessrights 2,
	 * return all articles
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		int userId = Integer.parseInt(req.getParameter("userid"));
		if(userId==0) {
			resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
		}
		else if(userId==1) {
			
		}
	}

}
