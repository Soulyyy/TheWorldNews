package theworldnews.handlers.news.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import theworldnews.database.news.objects.Article;
import theworldnews.database.news.objects.ArticleDisplay;
import theworldnews.database.news.queries.DisplayQueries;

@WebServlet(value = "/newsDisplay")
public class DisplayController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			String news;
			List<Article> newsArticles = null;

			resp.setContentType("text/html;charset=UTF-8");
			PrintWriter out = resp.getWriter();

			String type = req.getParameter("type");
			Integer size = Integer.parseInt(req.getParameter("size"));

			newsArticles = DisplayQueries.getArticlesByNumberAndType(size, type);
			StringBuilder sb = new StringBuilder();
			int i = 0;
			while (!newsArticles.isEmpty()) {
				if (newsArticles.size() == 2 && ((i % 3 == 1) || (i % 3 == 0))) {
					sb.append(ArticleDisplay.leftArticle(newsArticles.remove(0)));
					sb.append(ArticleDisplay.rightArticle(newsArticles.remove(0)));
					sb.append(ArticleDisplay.clearDiv());
				} else {
					if (i % 3 == 0) {
						sb.append(ArticleDisplay.mainArticle(newsArticles
								.remove(0)));
						i++;
					} else if (i % 3 == 1) {
						sb.append(ArticleDisplay.leftArticle(newsArticles
								.remove(0)));
						i++;
					} else if (i % 3 == 2) {
						sb.append(ArticleDisplay.rightArticle(newsArticles
								.remove(0)));
						i++;
						sb.append(ArticleDisplay.clearDiv());
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
