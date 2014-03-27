package TheWorldNews.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import TheWorldNews.userdata.User;
import TheWorldNews.userdata.UserMemory;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;

public class NewsController extends HttpServlet {
	
	private Gson gson;
	private UserMemory userMemory;
	
	@Override
	public void init() throws ServletException {
		super.init();

	}


	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	       
        String image = req.getParameter("image");//not really image?
        String header = req.getParameter("header");
        String content = req.getParameter("content");
        //Assign prime number for each article group, multiply if in more than one, unique factorization defines
        int articleGroups = Integer.parseInt(req.getParameter("articleGroup"));
        
		
		//Need to implement all methods in interface
		String idString = req.getParameter("id");
		if (idString != null) {
			ArrayList<User> allUsers= userMemory.findAllUsers();
			resp.getWriter().write(gson.toJson(allUsers));
		} else {
			int id = Integer.parseInt(idString);
	        User user = userMemory.findUserById(id);
	        resp.getWriter().write(gson.toJson(user));
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {

			
			
        } catch (JsonParseException ex) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());
        }
		
		
		
	}
}
