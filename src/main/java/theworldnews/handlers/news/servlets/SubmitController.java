package theworldnews.handlers.news.servlets;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import theworldnews.database.news.objects.Article;
import theworldnews.database.news.queries.EditQueries;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;


@WebServlet(value = "/newsSubmit")
public class SubmitController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private Gson gson;
	
	@Override
	public void init() throws ServletException {
		super.init();
		gson= new Gson();
	}

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
	}
	
	//When article is sent to database.
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
		

			System.out.println("Entered post for adding article");
            Article article = gson.fromJson(req.getReader(), Article.class);
            System.out.println(article.getArticlegroup());
            System.out.println("We manage to get JSON object to the required format");
			EditQueries.addArticle(article);
            resp.setHeader("Content-Type", "application/json");
            resp.getWriter().write("{\"response\":\"newsarticle created \"}"); // peab midagi tagastama, muidu kohe fail. kui content-type on json, siis see siin peab ka korralik JSON olema
            System.out.println("Servlet succeeded in adding newsarticle");
			
			
        } catch (JsonParseException ex) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());
        } 
		catch (SQLException e) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
		} catch (URISyntaxException e) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
		}
		
		
		
	}
}
