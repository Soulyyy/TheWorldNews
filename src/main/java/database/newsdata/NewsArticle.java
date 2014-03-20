package database.newsdata;

import java.util.ArrayList;

/**
 * Created by kasutaja on 14.03.14.
 */
public class NewsArticle {
    public String image;
    public String header;
    public String content;
    public int id;
    public ArrayList<Integer> articleGroups;

    public NewsArticle(int id) {
        this.id = id;
    }

    public NewsArticle(String image, String header, String content, int id, ArrayList<Integer> articleGroups) {
        this.image = image;
        this.header = header;
        this.content = content;
        this.id = id;
        this.articleGroups = articleGroups;
    }

    public NewsArticle(String header, String content, int id, ArrayList<Integer> articleGroups) {
        this.header = header;
        this.content = content;
        this.id = id;
        this.articleGroups = articleGroups;

    }

}
