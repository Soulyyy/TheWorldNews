package TheWorldNews.newsdata;

import java.util.ArrayList;

/**
 * Created by kasutaja on 14.03.14.
 */

public class NewsArticle {
	public Integer id;
    public String image;
    public String header;
    public String content;
    //Assign prime number for each article group, multiply if in more than one, unique factorization defines
    public int articlegroups;

    public NewsArticle() {
    }

    public NewsArticle(int id,String image, String header, String content, int articleGroups) {
    	this.id = id;
        this.image = image;
        this.header = header;
        this.content = content;
        this.articlegroups = articleGroups;
    }

    public NewsArticle(String header, String content,  int articleGroups) {
        this.header = header;
        this.content = content;
        this.articlegroups = articleGroups;

    }
    
    public NewsArticle(int id,String image, String header, String content,  String articleGroupsCoded) {
    	this.id = id;
    	this.image = image;
        this.header = header;
        this.content = content;
        this.articlegroups = NewsEncoding.jointArticleConvertToInt(articleGroupsCoded);

    }
    
    public NewsArticle(String header, String content,  String articleGroupsCoded) {
        this.header = header;
        this.content = content;
        this.articlegroups = NewsEncoding.jointArticleConvertToInt(articleGroupsCoded);

    }
    
}
