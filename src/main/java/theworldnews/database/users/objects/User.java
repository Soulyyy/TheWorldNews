package theworldnews.database.users.objects;

public class User {

	private int id;
	private String username;
	private int accessrights;
	private String password;

	public User(int id, String username, int accessrights, String password) {
		this.setId(id);
		this.setUsername(username);
		this.setAccessrights(accessrights);
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
