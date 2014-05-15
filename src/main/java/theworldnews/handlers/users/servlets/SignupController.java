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

@WebServlet(value = "/signupUser")
public class SignupController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private Gson gson;

	@Override
	public void init() throws ServletException {
		super.init();
		gson = new Gson();

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.sendError(HttpServletResponse.SC_NOT_FOUND);
	}

	//TODO: Read also the userinfo
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try (Connection con = DatabaseConnection.getConnection()) {
			User user = gson.fromJson(req.getReader(), User.class);

			if (user.username == null || user.password == null) {
				resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Username or password empty");
				return;
			}

			int result = EditQueries.addUser(con, user);
			resp.setHeader("Content-Type", "application/json");

			if (result >= 0) {
				resp.getWriter().write("{\"response\":\"success\"}");
			} else {
				resp.getWriter().write("{\"response\":\"failure\"}");
			}
		} catch (JsonParseException e) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
		} catch (SQLException | URISyntaxException e) {
			resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}
}
