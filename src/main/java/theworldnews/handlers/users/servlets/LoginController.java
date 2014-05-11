package theworldnews.handlers.users.servlets;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import theworldnews.database.connection.DatabaseConnection;
import theworldnews.database.users.queries.AuthenticationQueries;

/**
 * Võimalikud funktsioonid: <ul>
 * <li>GET accountLogin - Tagastab session attribuudi LOGIN_RIGHTS väärtuse accessRights:LOGIN_RIGHTS</li>
 * <li>GET accountLogin?action=logout - logib aktiivse kasutaja välja ja saadab vastuse response:success</li>
 * <li>POST accountLogin userName, password - Tagastab accessRights:LOGIN_RIGHTS, kui kasutaja eksisteerib, muidu
 * "accessRights:-1"</li>
 * </ul>
 */
@WebServlet(value = "/accountLogin")
public class LoginController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		String username = req.getParameter("username");

		HttpSession sess = req.getSession();
		Integer accessRights = (Integer) sess.getAttribute("LOGIN_RIGHTS");

		resp.setContentType("application/json");

		if (action == null) {
			if (accessRights == null) {
				resp.getWriter().write("{\"accessRights\": \"null\"}");
			} else {
				resp.getWriter().write("{\"accessRights\": " + accessRights + "}");
			}
		} else if (action.equals("logout")) {
			if (username.equals("test")) {
				try (Connection con = DatabaseConnection.getConnection()) {
					String query = "DELETE FROM users WHERE users.username = ?";
					PreparedStatement pst = con.prepareStatement(query);
					pst.setString(1, "test");
					pst.executeUpdate();
				} catch (SQLException | URISyntaxException e) {
					Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Failed to remove test account");
				}
			}
			sess.removeAttribute("LOGIN_RIGHTS");
			resp.getWriter().write("{\"response\":\"success\"}");
		} else {
			resp.getWriter().write("{\"response\":\"nothing\"}");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");

		HttpSession sess = req.getSession();

		resp.setContentType("application/json");

		if (username == null || password == null) {
			resp.getWriter().write("{\"response\":\"nothing\"}");
			return;
		}

		try {
			int i = AuthenticationQueries.loginWithAccessrights(username, password);

			sess.setAttribute("LOGIN_RIGHTS", i);
			resp.getWriter().write("{\"accessRights\": " + i + "}");
		} catch (SQLException | URISyntaxException e) {
			resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}
}
