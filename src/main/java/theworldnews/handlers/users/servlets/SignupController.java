package theworldnews.handlers.users.servlets;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import theworldnews.database.connection.DatabaseConnection;
import theworldnews.database.users.objects.User;
import theworldnews.database.users.queries.EditQueries;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 
 * @author Souly
 * 
 */
@WebServlet(value = "/signupUser")
public class SignupController extends HttpServlet {

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

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// NOTHING TO SEE HERE
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Connection con = null;
		try {
			con = DatabaseConnection.getConnection();
			User user = gson.fromJson(req.getReader(), User.class);
			EditQueries.addUser(con, user);
			resp.setHeader("Content-Type", "application/json");
			resp.getWriter().write("{\"response\":\"account created \"}");
		} catch (JsonParseException e) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
		} catch (SQLException e) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
		} catch (URISyntaxException e) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				resp.sendError(HttpServletResponse.SC_BAD_REQUEST,
						e.getMessage());
			}
		}
	}

}