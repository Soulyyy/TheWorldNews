package TheWorldNews.newsdata;

import java.util.ArrayList;

/**
 * Created by kasutaja on 14.03.14.
 */
public class NewsArticle {
    public String image;
    public String header;
    public String content;
    //Assign prime number for each article group, multiply if in more than one, unique factorization defines
    int articleGroups;

    public NewsArticle() {
    }

    public NewsArticle(String image, String header, String content, int articleGroups) {
        this.image = image;
        this.header = header;
        this.content = content;
        this.articleGroups = articleGroups;
    }

    public NewsArticle(String header, String content,  int articleGroups) {
        this.header = header;
        this.content = content;
        this.articleGroups = articleGroups;

    }

}
