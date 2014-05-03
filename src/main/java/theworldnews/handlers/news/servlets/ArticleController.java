package theworldnews.handlers.news.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import theworldnews.database.news.objects.Article;
import theworldnews.database.news.objects.ArticleDisplay;
import theworldnews.database.news.queries.DisplayQueries;

@WebServlet(value = "/newsArticle")
public class ArticleController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			PrintWriter out = resp.getWriter();
			Integer articleID = Integer.parseInt(req.getParameter("id"));
			Article article = DisplayQueries.getArticleById(articleID);

			out.print(ArticleDisplay.viewArticle(article));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
