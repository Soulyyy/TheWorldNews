package TheWorldNews.newsdata;

import java.util.List;


public interface NewsDatabaseRequests {


    public  NewsArticle getArticleById (int id);
    public List<NewsArticle> getAllArticles();
    public List<NewsArticle> getArticlesByGroup(int groupId);


}


