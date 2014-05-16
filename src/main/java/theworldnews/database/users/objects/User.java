package theworldnews.database.users.objects;

import java.util.Objects;

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

	@Override
	public int hashCode() {
		int hash = 3;
		hash = 23 * hash + this.id;
		hash = 23 * hash + Objects.hashCode(this.username);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final User other = (User) obj;
		if (this.id != other.id) {
			return false;
		}
		if (!Objects.equals(this.username, other.username)) {
			return false;
		}
		return true;
	}

}
