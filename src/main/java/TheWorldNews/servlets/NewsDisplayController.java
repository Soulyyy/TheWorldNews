package TheWorldNews.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspWriter;

import TheWorldNews.database.querys.DisplayQueries;
import TheWorldNews.newsdata.NewsArticle;
import TheWorldNews.newsdata.NewsDisplay;

@WebServlet(value = "/displayNews")
public class NewsDisplayController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;


	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String newsType = req.getParameter("newsType");
			System.out.println(newsType);
			resp.setContentType("text/html");
		    PrintWriter out = resp.getWriter();
			ArrayList<NewsArticle> newsArticles = DisplayQueries.getArticlesByNumberAndType(6, newsType);
			
			StringBuffer sb = new StringBuffer();
			
			for(int i = 0;i<newsArticles.size();i++){
				sb.append(NewsDisplay.mainArticle(newsArticles.get(i)));
				i++;
				sb.append(NewsDisplay.leftArticle(newsArticles.get(i)));
				i++;
				sb.append(NewsDisplay.rightArticle(newsArticles.get(i)));
				i++;
			}
			out.print(sb.toString());
			
//			req.setAttribute("articles", newsArticles);
//			req.getRequestDispatcher("Index.jsp").forward(req, resp);;
		    

		    
		} catch (Exception e) {
			e.printStackTrace();
		}
		


    }
}
