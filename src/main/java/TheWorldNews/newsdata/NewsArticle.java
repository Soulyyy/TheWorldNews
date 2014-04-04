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
    public String articlegroupstring;
    //Assign prime number for each article group, multiply if in more than one, unique factorization defines
    public int articlegroup;

    public NewsArticle() {
    }

    public NewsArticle(int id,String image, String header, String content, int articlegroup) {
    	this.id = id;
        this.image = image;
        this.header = header;
        this.content = content;
        this.articlegroup = articlegroup;
    }

    public NewsArticle(String header, String content,  int articlegroup) {
        this.header = header;
        this.content = content;
        this.articlegroup = articlegroup;

    }
    
    public NewsArticle(int id,String image, String header, String content,  String articleGroupsCoded) {
    	System.out.println("TRYING TO CREATE OBJECT");
    	this.id = id;
    	this.image = image;
        this.header = header;
        this.content = content;
        this.articlegroupstring =articleGroupsCoded;
        this.articlegroup = NewsEncoding.jointArticleConvertToInt(articleGroupsCoded);

    }
    
    public NewsArticle(String header, String content,  String articleGroupsCoded) {
        this.header = header;
        this.content = content;
        this.articlegroup = NewsEncoding.jointArticleConvertToInt(articleGroupsCoded);

    }
    
    public NewsArticle(String image, String header, String content, int articlegroup) {
        this.image = image;
        this.header = header;
        this.content = content;
        this.articlegroup = articlegroup;
    }
    
}
