package TheWorldNews.newsdata;

public class NewsDisplay {

	
public static String mainArticle(NewsArticle article){
		
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
	
public static String leftArticle(NewsArticle article){
		
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

public static String rightArticle(NewsArticle article){
	
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

public static String sideNews(NewsArticle article){
	StringBuffer sb = new StringBuffer();
	String img = article.image;
	String header = article.header;
	 sb.append("<div class=\"sideNews\">");
	    
 	sb.append("<a href=\"#\"><img class=\"img\" src=\"");
 	sb.append(img);
 	sb.append("\" alt=\"pilt\"></a>");
     
    sb.append("<p class=\"sideText\"><a href=\"#\">");
    sb.append(header);
    sb.append("</a></p>");
    sb.append("</div>");
    
    return sb.toString();
}

}
