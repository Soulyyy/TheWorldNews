package TheWorldNews.userdata;

public class User {
	
	
	//Maybe public is really bad, needs thinking
	public Integer id;		//Maybe private
	public String userName;
	public int accessRights;
	public int country;
	public String firstname;
	public String surname;
	public String password;
	public String email;
	
	//Other stuff we find important, this is incomplete
	
	public User(){
		
	}
	
	public User(Integer id, String userName,String password, String firstname, String surname, String email, int accessRights, int country){
		//ID is unique
		this.id=id;
		this.userName= userName;
		this.accessRights=accessRights;
		this.country=country;
		this.password=password;
		this.email=email;
		this.firstname=firstname;
		this.surname=surname;
	}
	
	//For editing rights
	public User(int accessRights){
		this.accessRights=accessRights;
	}
	
	public User(String userName) {
		this.userName= userName;
		this.accessRights = 0;
		this.country = 0;
	}
	
	public User(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}

	
	

}
