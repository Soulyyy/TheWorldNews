package TheWorldNews.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import TheWorldNews.database.querys.NewsQuerys;
import TheWorldNews.database.querys.UserQuerys;
import TheWorldNews.newsdata.NewsArticle;
import TheWorldNews.userdata.User;
import TheWorldNews.userdata.UserMemory;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;


@WebServlet(value = "/submitNews")
public class SubmitNewsController extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6250734712181392637L;
	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;
	private Gson gson;
	private UserMemory userMemory;
	
	@Override
	public void init() throws ServletException {
		super.init();

	}


	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Entered get for newsArticles");
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
	
	//When article is sent to database.
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			System.out.println("Entered post for adding account");
            NewsArticle article = gson.fromJson(req.getReader(), NewsArticle.class);
            NewsQuerys.addArticle(article);
            resp.setHeader("Content-Type", "application/json");
            resp.getWriter().write("{\"response\":\"newsarticle created \"}"); // peab midagi tagastama, muidu kohe fail. kui content-type on json, siis see siin peab ka korralik JSON olema
            System.out.println("Servlet succeeded in adding newsarticle");
			
			
        } catch (JsonParseException ex) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());
        }
		
		
		
	}
}
