package theworldnews.database.news.objects;

public class Tag {

	public int id;
	public int userid;
	public int articleid;
	public String tagname;
	public int count;
	
	public Tag(int id, int userid, int articleid, String tagname, int count) {
		this.id=id;
		this.userid=userid;
		this.articleid=articleid;
		this.tagname=tagname;
		this.count=count;
	}
	public Tag(int articleid, String tagname, int count) {
		this(0,0,articleid, tagname, count);
	}
}
