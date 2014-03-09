package database.userdata;

public class User {
	
	
	//Maybe public is really bad, needs thinking
	public Integer id;		//Maybe private
	public String userName;
	public int accessRights;
	public int country;
	//Other stuff we find important, this is incomplete
	
	public User(){
		
	}
	
	public User(Integer id, String userName, int accessRights, int country){
		this.id=id;
		this.userName= userName;
		this.accessRights=accessRights;
		this.country=country;
	}
	
	//For editing rights
	public User(int accessRights){
		this.accessRights=accessRights;
	}

}
