package theworldnews.database.news.objects;

import java.util.LinkedHashMap;
import java.util.List;

import theworldnews.database.users.objects.UserInfo;

public class ArticleResponse {

	// TODO: load html from file and replace fields?
	/**
	 * @param article
	 *            : Object of type Article without content
	 * @param position
	 *            : int, position of the article in the previews page, top=0,
	 *            left=1, right=2
	 * @return jsp representation of article without content
	 */
	public static String previewArticle(Article article, UserInfo userinfo,
			int position) {
		StringBuilder sb = new StringBuilder();
		String img = article.image;
		String header = article.header;
		Integer id = article.id;
		String firstname = userinfo.firstname;
		String surname = userinfo.surname;

		if (position == 0) {
			sb.append("<div class=\"mainArticle\">");
		} else if (position == 1) {
			sb.append("<div class=\"leftArticle\">");
		} else if (position == 2) {
			sb.append("<div class=\"rightArticle\">");
		} else {
			throw new IllegalArgumentException(
					"Article position does not exist");
		}

		sb.append("<a href=\"jsp/ArticleView.jsp?id=")
				.append(id + "&image=" + img)
				.append("\"><div class=mainCropped><img class=\"mainImage\" src=\"")
				.append(img)
				.append("\" alt=\"pilt\"></div></a>")
				.append("<p class=\"authorText\">")
				.append("By " + firstname + " " + surname)
				.append("</p>")
				.append("<p class=\"mainText\"><a href=\"<%=request.getContextPath()%>/jsp/ArticleView.jsp?id=")
				.append(id).append("\">").append(header).append("</a></p>")
				.append("</div>");

		return sb.toString();
	}

	/**
	 * @param article
	 *            : Object of type Article with content
	 * @return : jsp representation of article with content
	 */
	public static String displayArticle(Article article, UserInfo userinfo) {
		StringBuilder sb = new StringBuilder();
		Integer id = article.id;
		String img = article.image;
		String firstname = userinfo.firstname;
		String surname = userinfo.surname;
		String header = article.header;
		String content = article.content;

		sb.append("<a href=\"ArticleView.jsp?id=")
				.append(id + "&image=" + img)
				.append("\"><img class=\"mainImage\" src=\"")
				.append(img)
				.append("\" alt=\"pilt\"></a>")
				.append("<p class=\"authorText\">")
				.append("By " + firstname + " " + surname)
				.append("</p>")
				.append("<p class=\"mainText\"><a href=\"ArticleView.jsp?id=")
				.append(id).append("\">").append(header).append("</a></p>")
				.append(clearDiv()).append(content);

		return sb.toString();
	}

	public static String sideArticle(Article article) {
		StringBuilder sb = new StringBuilder();
		String img = article.image;
		String header = article.header;
		sb.append("<div class=\"sideNews\">")
				.append("<a href=\"#\"><img class=\"img\" src=\"").append(img)
				.append("\" alt=\"pilt\"></a>")
				.append("<p class=\"sideText\"><a href=\"#\">").append(header)
				.append("</a></p>").append("</div>");

		return sb.toString();
	}

	public static String clearDiv() {
		return "<div class=\"clear\"></div>";
	}

	public static String editDisplayArticle(List<Article> articles) {

		StringBuilder sb = new StringBuilder();
		while (!articles.isEmpty()) {
			Article article = articles.remove(0);
			Integer id = article.id;
			String header = article.header;
			sb.append("<p class=\"mainText\"><a href=\"EditArticle.jsp?id=")
					.append(id).append("\">").append(header).append("</a></p>");
			sb.append(clearDiv());
		}

		return sb.toString();

	}

	public static String editArticle(Article article) {

		StringBuilder sb = new StringBuilder();
		sb.append("<fieldset id=\"editingFieldset\" name=\"edit\">")
				.append("<legend>Article</legend>")
				.append("<label class=\"titleText\">Article Title</label>")
				.append("<input id=\"titleInput\" class=\"input-block\" type=\"text\" value=\"")
				.append(article.header)
				.append("\">")
				.append("<label class=\"titleText\">Image URL</label>")
				.append("<input id=\"Image\" class=\"input-block\" type=\"text\" value=\"")
				.append(article.image)
				.append("\">")
				.append("<label class=\"titleText\">Article Text</label>")
				.append("<textarea id=\"textArea\">" + article.content
						+ "</textarea>").append("</fieldset>");

		return sb.toString();

	}
}
