package theworldnews.database.news.objects;

/**
 * Object of a news article
 */
public class Article {

	public Integer id; //Primary key
	public String image; //Image url
	public String header; //Header text
	public String content; //Content text
	public String articlegroup; //String of groups where the article is displayed
	public int authorid; //user id of the author
	public int clickCount;

	/**
	 * @param id int, serial primary key of the database entry
	 * @param image String, url of the image
	 * @param header String, header of the article
	 * @param content String, content of the article
	 * @param articlegroup String, list of groups the article belongs to, int in the database
	 * @param authorid int, id value from the users table, corresponds to the creator of the article
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
	 * @param id int, serial primary key of the database entry
	 * @param image String, url of the image
	 * @param header String, header of the article
	 * @param articleGroup String, list of groups the article belongs to, int in the database
	 * @param authorid int, id value from the users table, corresponds to the creator of the article
	 */
	public Article(int id, String image, String header, String articleGroup, int authorid) {
		this(id, image, header, null, articleGroup, authorid);
	}

	// WHETHER WE WE NEED THIS. TBA!
	public void incrementClickCount() {
		this.clickCount++;
	}
}
