package database.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.userdata.User;
import database.userdata.UserMemory;

public class NewsController extends HttpServlet {

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	       
        String image = req.getParameter("image");//not really image?
        String header = req.getParameter("header");
        String content = req.getParameter("content");
        //Assign prime number for each article group, multiply if in more than one, unique factorization defines
        int articleGroups = req.getParameter("articleGroup");
		
		
		//Need to implement all methods in interface
		String idString = req.getParameter("id");
		if (idString != null) {
			ArrayList<User> allUsers= UserMemory.findAllUsers();
			resp.getWriter().write(gson.toJson(allUsers));
		} else {
			int id = Integer.parseInt(idString);
	        User user = datastore.findUserById(id);
	        resp.getWriter().write(gson.toJson(user));
		}
	}
}
