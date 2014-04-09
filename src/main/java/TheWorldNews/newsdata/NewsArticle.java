package TheWorldNews.newsdata;


/**
 * Created by kasutaja on 14.03.14.
 */
public class NewsArticle {
	public Integer id;
    public String image;
    public String header;
    public String content;
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

    public NewsArticle(int id, String header, String content,  int articlegroup) {
    	this.id = id;
        this.header = header;
        this.content = content;
        this.articlegroup = articlegroup;

    }
    
    public NewsArticle(int id,String image, String header, String content,  String articleGroupsCoded) {
    	this.id = id;
    	this.image = image;
        this.header = header;
        this.content = content;
        this.articlegroup = NewsEncoding.jointArticleConvertToInt(articleGroupsCoded);

    }
    
    

    
}
