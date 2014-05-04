package theworldnews.handlers.users.servlets;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import theworldnews.database.users.queries.AuthenticationQueries;

/**
 * @author Murka
 * 
 *         Võimalikud funktsioonid: GET accountLogin - Tagastab session
 *         attribuudi LOGIN_RIGHTS väärtuse accessRights:LOGIN_RIGHTS GET
 *         accountLogin?action=logout - logib aktiivse kasutaja välja ja saadab
 *         vastuse response:success POST accountLogin userName, password -
 *         Tagastab accessRights:LOGIN_RIGHTS, kui kasutaja eksisteerib, muidu
 *         "accessRights:-1"
 */
@WebServlet(value = "/accountLogin")
public class LoginController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		super.init();
		gson = new Gson();
	}

	private Gson gson;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession sess = req.getSession();
		String action = req.getParameter("action");
		Integer accessRights = (Integer) sess.getAttribute("LOGIN_RIGHTS");

		resp.setContentType("application/json");

		if (action == null) {
			if (accessRights == null) {
				resp.getWriter().write("{\"accessRights\": \"null\"}");
			} else {
				resp.getWriter().write(
						"{\"accessRights\": " + accessRights + "}");
			}
		} else if (action.equals("logout")) {
			sess.removeAttribute("LOGIN_RIGHTS");
			resp.getWriter().write("{\"response\":\"success\"}");
		} else {
			resp.getWriter().write("{\"response\":\"nothing\"}");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			String username = req.getParameter("username");
			String password = req.getParameter("password");

			resp.setContentType("application/json");
			if (username != null && password != null) {
				int i = AuthenticationQueries.loginWithAccessrights(username,
						password);

				HttpSession sess = req.getSession();
				sess.setAttribute("LOGIN_RIGHTS", i);

				resp.getWriter().write("{\"accessRights\": " + i + "}");
			} else {
				resp.getWriter().write("{\"response\":\"nothing\"}");
			}
		} catch (JsonParseException ex) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());
		} catch (SQLException e) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
		} catch (URISyntaxException e) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
		}
	}
}
