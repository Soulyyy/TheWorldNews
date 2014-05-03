package theworldnews.database.news.objects;

public class ArticleDisplay {

	// positions: 0-top, 1-left, 2-right
	public static String previewArticle(Article article, int position) {

		StringBuffer sb = new StringBuffer();
		String img = article.getImage();
		String header = article.getHeader();
		Integer id = article.getId();
		if (position == 0) {
			sb.append("<div class=\"mainArticle\">");
		} else if (position == 1) {
			sb.append("<div class=\"leftArticle\">");
		} else if (position == 2) {
			sb.append("<div class=\"rightArticle\">");
		} else
			throw new IllegalArgumentException(
					"Article position does not exist");
		sb.append("<div class=\"mainArticle\">");
		sb.append("<a href=\"jsp/ArticleView.jsp?id=" + id
				+ "\"><img class=\"mainImage\" src=\"");
		sb.append(img);
		sb.append("\" alt=\"pilt\"></a>");
		sb.append("<p class=\"mainText\"><a href=\"jsp/ArticleView.jsp?id="
				+ id + "\">");
		sb.append(header);
		sb.append("</a></p>");
		sb.append("</div>");

		return sb.toString();
	}

	public static String viewArticle(Article article) {
		StringBuffer sb = new StringBuffer();
		String img = article.getImage();
		String header = article.getHeader();
		Integer id = article.getId();
		String content = article.getContent();

		sb.append("<div class=\"mainArticle\">");
		sb.append("<a href=\"jsp/ArticleView.jsp?id=" + id
				+ "\"><img class=\"mainImage\" src=\"");
		sb.append(img);
		sb.append("\" alt=\"pilt\"></a>");
		sb.append("<p class=\"mainText\"><a href=\"jsp/ArticleView.jsp?id="
				+ id + "\">");
		sb.append(header);
		sb.append("</a></p>");
		sb.append(clearDiv());
		sb.append(content);
		sb.append("</div>");

		return sb.toString();
	}

	public static String sideArticle(Article article) {
		StringBuffer sb = new StringBuffer();
		String img = article.getImage();
		String header = article.getHeader();
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

	public static String clearDiv() {
		StringBuffer sb = new StringBuffer();
		sb.append("<div class=\"clear\">");
		sb.append("</div>");

		return sb.toString();
	}

}
