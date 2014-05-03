package theworldnews.database.news.objects;

/**
 * Object of a news article. id: A serial value, primary key in the database
 * image: image url header: header of the article content: article content, kept
 * as a string articlegroup: String of groups where the article is displayed,
 * int in the database author: user id of the author clickcount: int of the
 * number of clicks on the article
 */
public class Article {

	private Integer id;
	private String image;
	private String header;
	private String content;
	private String articlegroup; // String in object, int in database
	private int authorid;
	private int clickCount;

	/**
	 * 
	 * @param id
	 *            : int, serial primary key of the database entry
	 * @param image
	 *            : String, url of the image
	 * @param header
	 *            : String, header of the article
	 * @param content
	 *            : String, content of the article
	 * @param articlegroup
	 *            : String, list of groups the article belongs to, int in the
	 *            database
	 * @param authorid
	 *            : int, id value from the users table, corresponds to the
	 *            creator of the article
	 */
	public Article(int id, String image, String header, String content,
			String articlegroup, int authorid) {
		this.id = id;
		this.image = image;
		this.header = header;
		this.content = content;
		this.articlegroup = articlegroup;
		this.authorid = authorid;
	}

	/**
	 * 
	 * @param id
	 *            : int, serial primary key of the database entry
	 * @param image
	 *            : String, url of the image
	 * @param header
	 *            : String, header of the article
	 * @param articleGroup
	 *            : String, list of groups the article belongs to, int in the
	 *            database
	 * @param authorid
	 *            : int, id value from the users table, corresponds to the
	 *            creator of the article
	 */
	public Article(int id, String image, String header, String articleGroup, int authorid) {
		this(id, image, header, null, articleGroup, authorid);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getArticlegroup() {
		return articlegroup;
	}

	public void setArticlegroup(String articlegroup) {
		this.articlegroup = articlegroup;
	}

	public int getAuthorid() {
		return authorid;
	}

	public void setAuthorid(int authorid) {
		this.authorid = authorid;
	}

	public int getClickCount() {
		return clickCount;
	}

	public void setClickCount(int clickCount) {
		this.clickCount = clickCount;
	}

	public void incrementClickCount() {
		this.clickCount++;
	}
}
