package database.servlet;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;

import database.basefunctions.DatabaseCommands;
import database.userdata.UserDataProvider;
import database.userdata.User;
import database.userdata.UserMemory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

//@WebServlet(value = "/accounts")
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
			DatabaseCommands.addUser(user);
			System.out.println("ENTERED POST FOR ACCOUNT ADD");

//			Connection con = (Connection) getServletContext().getAttribute("DBConnection");
//			PreparedStatement ps = null;
//			try {
//	
//	            ps = con.prepareStatement("insert into users(name, password, firstname, surname, email, accessRights, country)");
//	            ps.setString(1, user.userName);
//	            ps.setString(2, user.password);
//	            ps.setString(3, user.firstname);
//	            ps.setString(4, user.surname);
//	            ps.setString(5, user.email);
//	            ps.setInt(6, user.accessRights);
//	            ps.setInt(7,user.country);
//	             
//	            ps.execute();
//	            
//	            
//	            
//			} catch(SQLException e){
//				e.printStackTrace();
//			}
//			finally{
//				try {
//					ps.close();
//				} catch (SQLException e) {
//					System.out.println("SQLException in closing PreparedStatement");
//					e.printStackTrace();
//				}
//			}
			
		    resp.setHeader("Content-Type", "application/json");
		    System.out.println("SERLVET SUCCESS ON ACCOUNT ADD POST");
		} catch (JsonParseException ex) {
			System.err.println("SERVLET FAIL ON ACCOUNT ADD POST");
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());
        }
	}
	
}
