package theworldnews.handlers.servlets.news;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
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
public class NewsDisplayController extends HttpServlet {
	private ArrayList<NewsArticle> newsArticles = new ArrayList<NewsArticle>();
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			String news;

			resp.setContentType("text/html;charset=UTF-8");
			PrintWriter out = resp.getWriter();
			ArrayList<NewsArticle> newsArticles;
			String type = req.getParameter("type");
			Integer size = Integer.parseInt(req.getParameter("size"));

			newsArticles = new ArrayList<NewsArticle>();
			newsArticles = DisplayQueries
					.getArticlesByNumberAndType(size, type);

			StringBuffer sb = new StringBuffer();
			int i = 0;
			while (!newsArticles.isEmpty()) {
				if (newsArticles.size() == 2 && ((i % 3 == 1) || (i % 3 == 0))) {
					sb.append(NewsDisplay.leftArticle(newsArticles.remove(0)));
					sb.append(NewsDisplay.rightArticle(newsArticles.remove(0)));
					sb.append(NewsDisplay.clearDiv());
				} else {
					if (i % 3 == 0) {
						sb.append(NewsDisplay.mainArticle(newsArticles
								.remove(0)));
						i++;
					} else if (i % 3 == 1) {
						sb.append(NewsDisplay.leftArticle(newsArticles
								.remove(0)));
						i++;
					} else if (i % 3 == 2) {
						sb.append(NewsDisplay.rightArticle(newsArticles
								.remove(0)));
						i++;
						sb.append(NewsDisplay.clearDiv());
					}
				}
			}

			news = sb.toString();
			req.setAttribute("news", news);
			req.setAttribute("newsArticles", newsArticles);
			out.println(news);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
