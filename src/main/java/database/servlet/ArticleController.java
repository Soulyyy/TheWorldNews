package database.servlet;

import javax.servlet.annotation.WebServlet;

import com.google.gson.Gson;

import database.newsdata.NewsArticleMemory;
import database.newsdata.NewsDatabaseRequests;

@WebServlet(value = "/newsArticles")
public class ArticleController {
	
	private Gson gson;
	private NewsDatabaseRequests newsRequests;
	
	public void init() {
		gson = new Gson();
		newsRequests = new NewsArticleMemory();
	}

}
