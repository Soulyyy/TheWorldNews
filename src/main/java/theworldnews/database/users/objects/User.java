package theworldnews.database.users.objects;

public class User {

	private int id;
	private String username;
	private int accessrights;
	private String password;

	/**
	 * User constructor with all fields present
	 * 
	 * @param id
	 *            : int, serial primary key of the database entry
	 * @param username
	 *            : String, users account name
	 * @param accessrights
	 *            : int, Integer value of access rights basic=0, editor=1,
	 *            admin=2
	 * @param password
	 */
	public User(int id, String username, int accessrights, String password) {
		this.setId(id);
		this.setUsername(username);
		this.setAccessrights(accessrights);
		this.setPassword(password);
	}

	/**
	 * Default User constructor, accessrights set to default value of basic=0
	 * 
	 * @param id
	 *            : int, serial primary key of the database entry
	 * @param username
	 *            : String, users account name
	 * @param password
	 */
	public User(int id, String username, String password) {
		this.setId(id);
		this.setUsername(username);
		this.setAccessrights(0);
		this.setPassword(password);
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
