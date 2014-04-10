package TheWorldNews.tags;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import TheWorldNews.database.querys.DisplayQueries;
import TheWorldNews.newsdata.NewsArticle;


public class ArticleTag extends SimpleTagSupport{
	
	public String mainArticle(NewsArticle article){
		
		StringBuffer sb = new StringBuffer();
		String img = article.image;
    	String header = article.header;
    	sb.append("<div class=\"mainArticle\">");
    	sb.append("<a href=\"#\"><img class=\"mainImage\" src=\"");
    	sb.append(img);
    	sb.append("\"alt=\"pilt\"></a>");
    	sb.append("<p class=\"mainText\"><a href=\"#\">");
    	sb.append(header);
    	sb.append("</a></p>");
    	sb.append("</div>");
		
		return sb.toString();
	}
	
public String leftArticle(NewsArticle article){
		
		StringBuffer sb = new StringBuffer();
		String img = article.image;
    	String header = article.header;
    	sb.append("<div class=\"leftArticle\">");
    	sb.append("<a href=\"#\"><img class=\"mainImage\" src=\"");
    	sb.append(img);
    	sb.append("\"alt=\"pilt\"></a>");
    	sb.append("<p class=\"mainText\"><a href=\"#\">");
    	sb.append(header);
    	sb.append("</a></p>");
    	sb.append("</div>");
		
		return sb.toString();
	}

public String rightArticle(NewsArticle article){
	
		StringBuffer sb = new StringBuffer();
		String img = article.image;
		String header = article.header;
		sb.append("<div class=\"rightArticle\">");
		sb.append("<a href=\"#\"><img class=\"mainImage\" src=\"");
		sb.append(img);
		sb.append("\"alt=\"pilt\"></a>");
		sb.append("<p class=\"mainText\"><a href=\"#\">");
		sb.append(header);
		sb.append("</a></p>");
		sb.append("</div>");
	
	return sb.toString();
}

	
	public void doTag() throws JspException, IOException {
		

		
//		ArrayList<NewsArticle> newsArticles = new ArrayList<NewsArticle>();
//		NewsArticle n1 = new NewsArticle(1,"http://i.imgur.com/iKv7rLN.jpg" , "Must be more to life", "none", 1);
//		NewsArticle n2 = new NewsArticle(2,"http://i.imgur.com/MDdYuXW.jpg","Avin' a giggle m8?","none",1);
//		NewsArticle n3 = new NewsArticle(3,"http://i.imgur.com/QyI6t1k.png","Part 4 fookin joocy", "none", 1);
//		
//		newsArticles.add(n1);
//		newsArticles.add(n2);
//		newsArticles.add(n3);
//		
		try {
			
			ArrayList<NewsArticle> newsArticles = DisplayQueries.getArticlesByNumberAndType(28, "News");
			
			StringBuffer sb = new StringBuffer();
			
			for(int i = 0;i<newsArticles.size();i++){
				sb.append(mainArticle(newsArticles.get(i)));
				i++;
				sb.append(leftArticle(newsArticles.get(i)));
				i++;
				sb.append(rightArticle(newsArticles.get(i)));
				i++;
			}
			
			JspWriter out = getJspContext().getOut();
		    
		    out.println(sb.toString());
		    
		} catch (Exception e) {
			e.printStackTrace();
		}
	    
	  }
}
