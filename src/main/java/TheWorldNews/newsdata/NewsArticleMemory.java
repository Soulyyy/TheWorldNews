package TheWorldNews.newsdata;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import TheWorldNews.userdata.User;

public class NewsArticleMemory implements NewsDatabaseRequests {
	
	private final Map<Integer, NewsArticle> articles;
	private int articleCount;
	
	public NewsArticleMemory() {
		articles= new HashMap<Integer, NewsArticle>();
		
		articleCount=2;
	}

	@Override
	public NewsArticle getArticleById(int id) {
		return (articles.get(id));
	}

	@Override
	public List<NewsArticle> getAllArticles() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NewsArticle> getArticlesByGroup(int groupId) {
		// TODO Auto-generated method stub
		return null;
	}

}
