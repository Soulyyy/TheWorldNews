package database.servlet;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;

import database.userdata.UserDataProvider;
import database.userdata.User;
import database.userdata.UserMemory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(value = "/accounts")
public class AccountController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Gson gson;
	private UserDataProvider datastore;
	
	@Override
	public void init() throws ServletException {
		super.init();
		gson= new Gson();
		datastore = new UserMemory();
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Get Json objects
		resp.setHeader("Content-Type","application/json");
		resp.setContentType("application/json;charset=UTF-8");
		
		
		//Need to implement all methods in interface
		String idString = req.getParameter("id");
		if (idString != null) {
			List<User> allUsers= datastore.findAllUsers();
			resp.getWriter().write(gson.toJson(allUsers));
		} else {
			int id = Integer.parseInt(idString);
	        User user = datastore.findUserById(id);
	        resp.getWriter().write(gson.toJson(user));
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			User user = gson.fromJson(req.getReader(), User.class);
			datastore.addUser(user);
			
		    resp.setHeader("Content-Type", "application/json");
	        resp.getWriter().write(gson.toJson(user));
		} catch (JsonParseException ex) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());
        }
	}
	

}
