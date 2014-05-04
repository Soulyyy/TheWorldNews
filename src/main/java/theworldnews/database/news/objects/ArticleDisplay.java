package theworldnews.database.news.objects;

public class ArticleDisplay {

	// positions: 0-top, 1-left, 2-right
	/**
	 * 
	 * @param article
	 *            : Object of type Article without content
	 * @param position
	 *            : int, position of the article in the previews page, top=0,
	 *            left=1, right=2
	 * @return : jsp representation of article without content
	 */
	public static String previewArticle(Article article, int position) {

		StringBuffer sb = new StringBuffer();
		String img = article.image;
		String header = article.header;
		Integer id = article.id;
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

	/**
	 * 
	 * @param article
	 *            : Object of type Article with content
	 * @return : jsp representation of article with conent
	 */
	public static String viewArticle(Article article) {
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

	public static String clearDiv() {
		StringBuffer sb = new StringBuffer();
		sb.append("<div class=\"clear\">");
		sb.append("</div>");

		return sb.toString();
	}

}
