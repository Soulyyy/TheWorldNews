package TheWorldNews.tags;

import java.io.IOException;

import java.util.ArrayList;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.SkipPageException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import TheWorldNews.database.querys.DisplayQueries;
import TheWorldNews.newsdata.NewsArticle;



public class ArticleTag extends SimpleTagSupport{
	private String type;
	private String newsgroup;
	
	public ArticleTag(){
		
	}
	
	public void setType(String type) {
		this.type = type;
	}

	public void setNewsgroup(String newsgroup) {
		this.newsgroup = newsgroup;
	}

	public void displayMainArticles(String type, int number){
		try {
			
			ArrayList<NewsArticle> newsArticles = DisplayQueries.getArticlesByNumberAndType(number, type);
			
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
	public String mainArticle(NewsArticle article){
		
		StringBuffer sb = new StringBuffer();
		String img = article.image;
    	String header = article.header;
    	sb.append("<div class=\"mainArticle\">");
    	sb.append("<a href=\"#\"><img class=\"mainImage\" src=\"");
    	sb.append(img);
    	sb.append("\" alt=\"pilt\"></a>");
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
    	sb.append("\" alt=\"pilt\"></a>");
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
		sb.append("\" alt=\"pilt\"></a>");
		sb.append("<p class=\"mainText\"><a href=\"#\">");
		sb.append(header);
		sb.append("</a></p>");
		sb.append("</div>");
	
	return sb.toString();
}

	
@Override
public void doTag() throws JspException, IOException {
    System.out.println("Type is:" + type);
    System.out.println("NewsGroup is:" + newsgroup);
    try {
    	
    	int number = Integer.parseInt(newsgroup);
    	
    	displayMainArticles(type, number);
    	
    } catch (Exception e) {
        e.printStackTrace();
        // stop page from loading further by throwing SkipPageException
        throw new SkipPageException("Exception in formatting " + type
                + " with format " + newsgroup);
    }
}
}
