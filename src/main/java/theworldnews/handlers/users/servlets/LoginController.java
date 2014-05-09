package theworldnews.handlers.users.servlets;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import theworldnews.database.connection.DatabaseConnection;
import theworldnews.database.users.queries.AuthenticationQueries;

/**
 * Võimalikud funktsioonid:
 *		GET accountLogin - Tagastab session attribuudi LOGIN_RIGHTS väärtuse accessRights:LOGIN_RIGHTS
 *		GET accountLogin?action=logout - logib aktiivse kasutaja välja ja saadab vastuse response:success
 *		POST accountLogin userName, password - Tagastab accessRights:LOGIN_RIGHTS, kui kasutaja eksisteerib, muidu "accessRights:-1"
 */
@Path("/accountLogin")
public class LoginController {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response doGet(@QueryParam("action") String action,
						  @Context HttpServletRequest req) {
		HttpSession sess = req.getSession();

		Integer accessRights = (Integer) sess.getAttribute("LOGIN_RIGHTS");
		String username = (String) sess.getAttribute("LOGIN_USER");

		Connection con;

		if (action == null) {
			if (accessRights == null) {
				return Response.ok("{\"accessRights\": \"null\"}").build();
			} else {
				return Response.ok("{\"accessRights\": " + accessRights + "}").build();
			}
		} else if (action.equals("logout") && username != null) {
			if (username.equals("test")) {
				try {
					con = DatabaseConnection.getConnection();
					String query = "DELETE FROM users WHERE users.username = ?";
					PreparedStatement pst = con.prepareStatement(query);
					pst.setString(1, "test");
					pst.executeUpdate();
				} catch (SQLException e) {
					return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
				} catch (URISyntaxException e) {
					return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
				}
			}
			sess.removeAttribute("LOGIN_USER");
			sess.removeAttribute("LOGIN_RIGHTS");
			return Response.ok("{\"response\":\"success\"}").build();
		} else {
			return Response.ok("{\"response\":\"nothing\"}").build();
		}
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response doPost(@FormParam("username") String username,
						   @FormParam("password") String password,
						   @Context HttpServletRequest req) {
		try {
			if (username != null && password != null) {
				int i = AuthenticationQueries.loginWithAccessrights(username,
																	password);

				HttpSession sess = req.getSession();
				if (i >= 0) {
					sess.setAttribute("LOGIN_USER", username);
				}
				sess.setAttribute("LOGIN_RIGHTS", i);

				return Response.ok("{\"accessRights\": " + i + "}").build();
			} else {
				return Response.ok("{\"response\":\"nothing\"}").build();
			}
		} catch (SQLException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		} catch (URISyntaxException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}
}
