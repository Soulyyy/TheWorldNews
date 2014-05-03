package theworldnews.handlers.users.servlets;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import theworldnews.database.users.objects.User;
import theworldnews.database.users.queries.AuthenticationQueries;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;

@WebServlet(value = "/userLogin")
public class LoginController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		super.init();
		gson = new Gson();
	}

	private Gson gson;

	/**
	 * GET /accountLogin Võimalikud funktsioonid: "" - Tagastab session
	 * attribuudi LOGIN_RIGHTS väärtuse "accessRight:LOGIN_RIGHTS
	 * "?action=logout" - logib aktiivse kasutaja välja ja saadab vastuse
	 * "response:success" muu - Tagastab "response:nothing"
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		HttpSession sess = req.getSession();
		String action = req.getParameter("action");
		Integer accessRight = (Integer) sess.getAttribute("LOGIN_RIGHTS");

		resp.setContentType("application/json");

		if (action == null) {
			if (accessRight == null) {
				resp.getWriter().write("{\"accessRight\": \"null\"}");
			} else {
				resp.getWriter()
						.write("{\"accessRight\": " + accessRight + "}");
			}
		} else if (action.equals("logout")) {
			sess.removeAttribute("LOGIN_RIGHTS");
			resp.getWriter().write("{\"response\":\"success\"}");
		} else {
			resp.getWriter().write("{\"response\":\"nothing\"}");
		}
	}

	/**
	 * POST /accountLogin Võimalikud funktsioonid: ?userName=___&password=___ -
	 * Tagastab "accessRights:LOGIN_RIGHTS", kui kasutaja eksisteerib, muidu
	 * "accessRights:-1"
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			User currentUser = gson.fromJson(req.getReader(), User.class);
			int i = AuthenticationQueries.loginWithAccessrights(
					currentUser.getUsername(), currentUser.getPassword());

			HttpSession sess = req.getSession();
			sess.setAttribute("LOGIN_RIGHTS", i);

			resp.setContentType("application/json");
			resp.getWriter().write("{\"accessRights\": " + i + "}");
		} catch (JsonParseException ex) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());
		} catch (SQLException e) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
		} catch (URISyntaxException e) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
		}
	}
}
