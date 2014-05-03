package theworldnews.handlers.users.servlets;
 

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
 



import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 



import theworldnews.database.users.objects.User;
import theworldnews.database.users.queries.EditQueries;

import java.io.IOException;

 
@WebServlet(value = "/userSignup")
public class SignupController extends HttpServlet {
 
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Gson gson;
     
    @Override
    public void init() throws ServletException {
        super.init();
        gson= new Gson();
         
    }
     
     
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	//Get Json objects
    	resp.setHeader("Content-Type","application/json");
    	resp.setContentType("application/json;charset=UTF-8");

    	//Need to implement all methods in interface
    	String userString = req.getParameter("userName");
    	resp.getWriter().write("all");
    	resp.getWriter().write(userString);
    }
     
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
        	System.out.println("ENTERED POST FOR ACCOUNT ADD");
            User user = gson.fromJson(req.getReader(), User.class);
            EditQueries.addUser(user);
            resp.setHeader("Content-Type", "application/json");
            //Response, if none given, instant fail. Checks if JSON is correct? võrrelge neid commenteid, on sisu sama?
            resp.getWriter().write("{\"response\":\"account created \"}"); // peab midagi tagastama, muidu kohe fail. kui content-type on json, siis see siin peab ka korralik JSON olema
            System.out.println("SERLVET SUCCESS ON ACCOUNT ADD POST");
	} catch (JsonParseException ex) {
        	System.err.println("SERVLET FAIL ON ACCOUNT ADD POST");
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());
        }
    }
     
}