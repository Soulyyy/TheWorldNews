package TheWorldNews.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
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
	private ArrayList<NewsArticle> newsArticles = new ArrayList<NewsArticle>();
	private static final long serialVersionUID = 1L;


	
	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String news;
//			String newsType = req.getParameter("newsType");
//			int size = Integer.parseInt(req.getParameter("numberOfNews"));
//			log(newsType);
//			System.out.println(newsType);
			resp.setContentType("text/html;charset=UTF-8");
		    PrintWriter out = resp.getWriter();
			ArrayList<NewsArticle> newsArticles;
//			NewsArticle n1 = new NewsArticle(1,"http://i.imgur.com/gPUK2bC.jpg","header1", "content1", "News;Science");
//			NewsArticle n2 = new NewsArticle(2,"http://i.imgur.com/wK6iK9d.jpg","header2", "content2", "News;Science");
//			NewsArticle n3 = new NewsArticle(3,"http://i.imgur.com/qOjr0Nn.jpg","header3", "content3", "News;Science");
			
//			newsArticles = new ArrayList<NewsArticle>();
			newsArticles = DisplayQueries.getArticlesByNumberAndType(6, "News");
//			newsArticles = DisplayQueries.getArticlesByNumberAndType(number, type);
//			newsArticles.add(n1);
//			newsArticles.add(n2);
//			newsArticles.add(n3);
			StringBuffer sb = new StringBuffer();
			
			for(int i = 0;i<newsArticles.size();i++){
				sb.append(NewsDisplay.mainArticle(newsArticles.get(i)));
				i++;
				sb.append(NewsDisplay.leftArticle(newsArticles.get(i)));
				i++;
				sb.append(NewsDisplay.rightArticle(newsArticles.get(i)));
				i++;
			}
			news = sb.toString();
			req.setAttribute("news", news);
			req.setAttribute("newsArticles", newsArticles);
			System.out.println("Voisiia");
			
			out.println("Siia");
			out.println(news);
			
//			RequestDispatcher reqDispatcher = getServletConfig().getServletContext().getRequestDispatcher("../../../webapp/Index.jsp");
//			req.getRequestDispatcher("/Index.jsp").forward(req, resp); 
//		    reqDispatcher.forward(req,resp);
			
//			req.setAttribute("articles", newsArticles);
//			req.getRequestDispatcher("Index.jsp").forward(req, resp);;
		    
//			req.getRequestDispatcher("../folder/index.jsp").forward(req, resp);
		    
		} catch (Exception e) {
			e.printStackTrace();
		}
		


    }
}
