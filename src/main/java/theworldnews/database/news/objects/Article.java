package theworldnews.database.news.objects;

/**
 * Object of a news article. id: A serial value, primary key in the database
 * image: image url header: header of the article content: article content, kept
 * as a string articlegroup: String of groups where the article is displayed,
 * int in the database author: user id of the author clickcount: int of the
 * number of clicks on the article
 */
public class Article {

	public Integer id;
	public String image;
	public String header;
	public String content;
	public String articlegroup; // String in object, int in database
	public int authorid;
	public int clickCount;

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
	public Article(int id, String image, String header, String articleGroup,
			int authorid) {
		this(id, image, header, null, articleGroup, authorid);
	}

	// WHETHER WE WE NEED THIS. TBA!
	public void incrementClickCount() {
		this.clickCount++;
	}
}
