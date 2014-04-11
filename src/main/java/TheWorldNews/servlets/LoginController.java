package TheWorldNews.servlets;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;

import javax.servlet.annotation.WebServlet;

import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;

import TheWorldNews.database.querys.AuthenticationQueries;
import TheWorldNews.database.querys.LoginQueries;
import TheWorldNews.userdata.User;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;

@WebServlet(value = "/accountLogin")
public class LoginController  extends HttpServlet {
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		super.init();
		gson= new Gson();
	}

	private Gson gson;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Entered get for logging in");
		try {
			String session = req.getSession().getId();
			int i= AuthenticationQueries.userAuthenticationStatus(session);
			resp.addIntHeader("Authentication response", i);
			resp.setHeader("Content-Type", "application/json");
			resp.getWriter().write("{\"response\":"+i+"}");
		} catch (SQLException e) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
			e.printStackTrace();
		} catch (URISyntaxException e) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
			e.printStackTrace();
		}
		
	}

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			System.out.println("Entered post for logging in");
			User currentUser = gson.fromJson(req.getReader(), User.class);
			int i=LoginQueries.loginWithAccessrights(currentUser.userName, currentUser.password);
			//String newid = newsessionid();
			System.out.println(i+" This is the gay integer we are looking for. We like Gay ints!");
			// if(i==-1) {
				// Some relevant response
				// resp.addIntHeader("Authentication response", i);
				// resp.setHeader("Content-Type", "application/json");
				// resp.getWriter().write("{\"response\":"+i+"}");
			// } else {
				// HttpSession session = req.getSession();
				// AuthenticationQueries.addAuthentication(session.getId(),currentUser.userName);
				// Here as well
				// resp.addIntHeader("Authentication response", i);
				// resp.setHeader("Content-Type", "application/json");
				// resp.getWriter().write("{\"response\":"+i+"}");
			// }
 
			resp.getWriter().write("{\"response\":"+i+"}");
            System.out.println("Servlet succeeded in verifying log in status");
		

			
						
			
        } catch (JsonParseException ex) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());
			}
         catch (SQLException e) {
			e.printStackTrace();
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());

		} catch (URISyntaxException e) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
	}
		
		
		
	}
	
	
}
