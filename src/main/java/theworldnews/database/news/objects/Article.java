package theworldnews.database.news.objects;

public class Article {

	private Integer id;
	private String image;
	private String header;
	private String content;
	private String articlegroup; // String in object, int in database
	private String author;
	private int clickCount;

	
	//Page object
	public Article(int id, String image, String header, String content,
			String articlegroup, String author) {
		this.setId(id);
		this.setImage(image);
		this.setHeader(header);
		this.setContent(content);
		this.setArticlegroup(articlegroup);
		this.setAuthor(author);

	}
	
	//Preview	
	public Article(int id, String image, String header, String articleGroup, String author) {
		this.setId(id);
		this.setImage(image);
		this.setHeader(header);
		this.setArticlegroup(articlegroup);
		this.setAuthor(author);
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

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
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
