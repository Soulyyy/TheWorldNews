package theworldnews.database.users.objects;

public class UserInfo {

	public int userid;
	public String firstname;
	public String surname;
	public String email;
	public int country;

	/**
	 * @param userid The id of the corresponding user in the users table
	 * @param firstname the first name of the user
	 * @param surname the surname of the user
	 * @param email the e-mail address of the user
	 * @param country the country of residence of this user, an integer value
	 */
	public UserInfo(int userid, String firstname, String surname, String email,
					int country) {
		this.userid = userid;
		this.firstname = firstname;
		this.surname = surname;
		this.email = email;
		this.country = country;
	}

	public UserInfo(int userid, String firstname, String surname) {
		this(userid, firstname, surname, null, 0);
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 29 * hash + this.userid;
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
		final UserInfo other = (UserInfo) obj;
		return this.userid == other.userid;
	}

}
