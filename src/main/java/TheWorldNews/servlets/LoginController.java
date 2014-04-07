package TheWorldNews.servlets;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import TheWorldNews.database.querys.LoginQueries;
import TheWorldNews.userdata.User;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;

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
	}
	
	
	
	//When article is sent to database.
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			System.out.println("Entered post for adding article");
			User currentUser = gson.fromJson(req.getReader(), User.class);
			int i=LoginQueries.loginWithAccessrights(currentUser.userName, currentUser.password);
			//You tell me what to do with this int
            resp.setHeader("Content-Type", "application/json");
            resp.getWriter().write("{\"response\":\"Login is verified \"}"); // peab midagi tagastama, muidu kohe fail. kui content-type on json, siis see siin peab ka korralik JSON olema
            System.out.println("Servlet succeeded in verifying log in status");
			
			
        } catch (JsonParseException ex) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());
        } catch (SQLException e) {
        	resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
		} catch (URISyntaxException e) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
		}
		
		
		
	}
	
	
}
