package TheWorldNews.servlets;

import javax.servlet.annotation.WebServlet;

import TheWorldNews.newsdata.NewsArticleMemory;
import TheWorldNews.newsdata.NewsDatabaseRequests;

import com.google.gson.Gson;

@WebServlet(value = "/newsArticles")
public class ArticleController {
	
	private Gson gson;
	private NewsDatabaseRequests newsRequests;
	
	public void init() {
		gson = new Gson();
		newsRequests = new NewsArticleMemory();
	}

}
