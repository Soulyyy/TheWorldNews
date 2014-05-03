package theworldnews.database.users.objects;

public class User {

	private int id;
	private int accessrights;
	private String username;
	private String password;

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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getAccessrights() {
		return accessrights;
	}

	public void setAccessrights(int accessrights) {
		this.accessrights = accessrights;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
