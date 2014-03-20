package database.newsdata;

import java.util.List;

/**
 * Created by kasutaja on 14.03.14.
 */
public interface NewsDatabaseRequests {


    public  NewsArticle getArticleById (int id);
    public List<NewsArticle> getAllArticles();
    public List<NewsArticle> getArticlesByGroup(int groupId);


}


