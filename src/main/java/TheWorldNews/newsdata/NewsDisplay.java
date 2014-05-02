package TheWorldNews.newsdata;

public class NewsDisplay {

	public static String mainArticle(NewsArticle article) {

		StringBuffer sb = new StringBuffer();

		String img = article.image;
		String header = article.header;
		Integer id = article.id;
		sb.append("<div class=\"mainArticle\">");
		sb.append("<a href=\"jsp/ArticleView.jsp?id=" + id
				+ "\"><img class=\"mainImage\" src=\"");
		sb.append(img);
		sb.append("\" alt=\"pilt\"></a>");
		sb.append("<p class=\"mainText\"><a href=\"jsp/ArticleView.jsp?id=" + id + "\">");
		sb.append(header);
		sb.append("</a></p>");
		sb.append("</div>");

		return sb.toString();
	}

	public static String clearDiv() {
		StringBuffer sb = new StringBuffer();
		sb.append("<div class=\"clear\">");
		sb.append("</div>");

		return sb.toString();
	}

	public static String leftArticle(NewsArticle article) {

		StringBuffer sb = new StringBuffer();
		String img = article.image;
		String header = article.header;
		Integer id = article.id;
		sb.append("<div class=\"leftArticle\">");
		sb.append("<a href=\"jsp/ArticleView.jsp?id=" + id
				+ "\"><img class=\"mainImage\" src=\"");
		sb.append(img);
		sb.append("\" alt=\"pilt\"></a>");
		sb.append("<p class=\"mainText\"><a href=\"jsp/ArticleView.jsp?id=" + id + "\">");
		sb.append(header);
		sb.append("</a></p>");
		sb.append("</div>");

		return sb.toString();
	}

	public static String displayArticle(NewsArticle article) {
		StringBuffer sb = new StringBuffer();
		String img = article.image;
		String header = article.header;
		Integer id = article.id;
		String content = article.content;

		sb.append("<div class=\"mainArticle\">");
		sb.append("<a href=\"jsp/ArticleView.jsp?id=" + id
				+ "\"><img class=\"mainImage\" src=\"");
		sb.append(img);
		sb.append("\" alt=\"pilt\"></a>");
		sb.append("<p class=\"mainText\"><a href=\"jsp/ArticleView.jsp?id=" + id + "\">");
		sb.append(header);
		sb.append("</a></p>");
		sb.append(clearDiv());
		sb.append(content);
		sb.append("</div>");

		return sb.toString();

	}

	public static String rightArticle(NewsArticle article) {

		StringBuffer sb = new StringBuffer();
		String img = article.image;
		String header = article.header;
		Integer id = article.id;
		sb.append("<div class=\"rightArticle\">");
		sb.append("<a href=\"jsp/ArticleView.jsp?id=" + id
				+ "\"><img class=\"mainImage\" src=\"");
		sb.append(img);
		sb.append("\" alt=\"pilt\"></a>");
		sb.append("<p class=\"mainText\"><a href=\"jsp/ArticleView.jsp?id=" + id + "\">");
		sb.append(header);
		sb.append("</a></p>");
		sb.append("</div>");

		return sb.toString();
	}

	public static String sideNews(NewsArticle article) {
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
