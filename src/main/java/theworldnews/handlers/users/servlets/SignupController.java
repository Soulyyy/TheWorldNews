package theworldnews.handlers.users.servlets;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import theworldnews.database.connection.DatabaseConnection;
import theworldnews.database.users.objects.User;
import theworldnews.database.users.objects.UserInfo;
import theworldnews.database.users.queries.EditQueries;

@Path("/signupUser")
public class SignupController {

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response doPost(@FormParam("username") String username,
						   @FormParam("password") String password,
						   @FormParam("firstname") String firstname,
						   @FormParam("surname") String surname,
						   @FormParam("email") String email) {
		if (username == null || password == null || firstname == null || surname == null || email == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		try (Connection con = DatabaseConnection.getConnection()) {
			User user = new User(-1, username, password);
			UserInfo info = new UserInfo(-1, firstname, surname, email, 0);

			EditQueries.addUser(con, user);
			return Response.ok("{\"response\":\"account created \"}").build();
		} catch (SQLException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		} catch (URISyntaxException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}
}
