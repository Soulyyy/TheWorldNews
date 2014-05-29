package theworldnews.handlers.users.servlets;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import theworldnews.database.users.objects.User;
import theworldnews.database.users.queries.AuthenticationQueries;
import theworldnews.utilities.hashing.Sha256;

@WebServlet(value = "/noscriptAccountLogin")
public class NoscriptLoginController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		super.init();
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String pw = Sha256.hashSha256(password);
		HttpSession sess = req.getSession();

		if (username == null || password == null) {
			resp.getWriter().write("{\"response\":\"nothing\"}");
			return;
		}
		try {
			User u = AuthenticationQueries
					.loginVerification(username, pw);
			if (u == null) {
				resp.getWriter().write("{\"accessRights\": " + -1 + "}");
			} else {
				sess.setAttribute("LOGIN_ID", u.id);
				sess.setAttribute("LOGIN_USER", u.username);
				sess.setAttribute("LOGIN_RIGHTS", u.accessrights);
				String uri = req.getRequestURI();
				String pageName = uri.substring(uri.lastIndexOf("/")+1);
				System.out.println(uri +" "+ pageName);
				RequestDispatcher rd = req.getRequestDispatcher(pageName);
//				rd.forward(req, resp);
				resp.sendRedirect("/Index.jsp");
//				resp.sendRedirect(uri);
			}
		} catch (SQLException | URISyntaxException e) {
			resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
					e.getMessage());
		} catch (IOException e) {
			resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
					e.getMessage());
		}
	}

}
