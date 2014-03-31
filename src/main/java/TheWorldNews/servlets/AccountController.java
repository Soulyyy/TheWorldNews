package TheWorldNews.servlets;
 
 
import TheWorldNews.database.DatabaseCommands;
import TheWorldNews.userdata.User;
import TheWorldNews.userdata.UserDataProvider;
import TheWorldNews.userdata.UserMemory;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
 
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
 
@WebServlet(value = "/accountSignup")
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
//      datastore = new UserMemory();
         
    }
     
     
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	//Get Json objects
    	resp.setHeader("Content-Type","application/json");
    	resp.setContentType("application/json;charset=UTF-8");

    	//Need to implement all methods in interface
    	String userString = req.getParameter("userName");
    	//      List<user> allUsers= datastore.findAllUsers();
    	resp.getWriter().write("all");
    	//      User user = datastore.findUserById(id);
    	resp.getWriter().write(userString);
    }
     
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
        	System.out.println("ENTERED POST FOR ACCOUNT ADD");
            User user = gson.fromJson(req.getReader(), User.class);
            DatabaseCommands.addUser(user);
            resp.setHeader("Content-Type", "application/json");
            resp.getWriter().write("{\"response\":\"account created \"}"); // peab midagi tagastama, muidu kohe fail. kui content-type on json, siis see siin peab ka korralik JSON olema
            System.out.println("SERLVET SUCCESS ON ACCOUNT ADD POST");
	} catch (JsonParseException ex) {
        	System.err.println("SERVLET FAIL ON ACCOUNT ADD POST");
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());
        }
    }
     
}