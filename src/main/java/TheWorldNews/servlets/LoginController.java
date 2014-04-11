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
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
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
	}
	
	//What is this shit?
//	public String newsessionid() {
//		String idd = "";
//		Random r = new Random();
//		char[] massiiv = "1234567890qwertyuiopasdfghjklzxcvbnm".toCharArray();
//		for (int i = 0; i < 20; i++) {
//			idd += massiiv[r.nextInt(massiiv.length)];
//		}
//		return idd;
//	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			System.out.println("Entered post for adding article");
			User currentUser = gson.fromJson(req.getReader(), User.class);
			int i=LoginQueries.loginWithAccessrights(currentUser.userName, currentUser.password);

			//String newid = newsessionid();
			System.out.println(i+" This is the gay integer we are looking for. We like Gay ints!");
			if(i==-1) {
				
			} else {
				//MÜÜR; SIIN PUTSIS HALP
				addAuthentication(currentUser.userName, req.)
			}

			resp.addIntHeader("Authentication response", i);
			resp.setHeader("Content-Type", "application/json");
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
