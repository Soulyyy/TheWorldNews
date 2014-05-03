package theworldnews.database.users.objects;

public class UserSettings {

	private String firstname;
	private String surname;
	private String email;
	private int country;

	public UserSettings(String firstname, String surname, String email,
			int country) {

		this.setFirstname(firstname);
		this.setSurname(surname);
		this.setEmail(email);
		this.setCountry(country);
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getCountry() {
		return country;
	}

	public void setCountry(int country) {
		this.country = country;
	}

}
