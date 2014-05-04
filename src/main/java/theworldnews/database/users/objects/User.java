package theworldnews.database.users.objects;


public class User {

	public int id;
	public int accessrights;
	public String username;
	public String password;

	/**
	 * User constructor with all fields present
	 * 
	 * @param id Serial primary key of the database entry
	 * @param username User's account name
	 * @param accessrights Value of access rights basic=0, editor=1, admin=2
	 * @param password Sha256 hashed password
	 */
	public User(int id, String username, String password, int accessrights) {
		this.id = id;
		this.username = username;
		this.accessrights = accessrights;
		this.password = password;
	}

	/**
	 * Default User constructor, accessrights set to default value of basic=0
	 * 
	 * @param id Serial primary key of the database entry
	 * @param username User's account name
	 * @param password Sha256 hashed password
	 */
	public User(int id, String username, String password) {
		this(id, username, password, 0);
	}

}
