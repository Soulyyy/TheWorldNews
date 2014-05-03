package theworldnews.database.news.queries;

import java.util.List;

import theworldnews.database.news.objects.Article;


public interface NewsDatabaseRequests {


    public  Article getArticleById (int id);
    public List<Article> getAllArticles();
    public List<Article> getArticlesByGroup(int groupId);


}


