package TheWorldNews.userdata;

import java.util.ArrayList;
import java.util.List;


import TheWorldNews.database.querys.UserQuerys;

/*THIS IS PROBABLY A BULLSHIT CLASS.
 * USING THIS FOR TESTING PURPOSES, I AM 90% CONFIDENT WE NEED TO BLOW THIS UP
 */

public class UserMemory implements UserDataProvider{

	public UserMemory(){
		ArrayList<User> users = findAllUsers();
	}
	
	
	@Override
	public User findUserById(int id) {
		String query = "SELECT * FROM users WHERE id ="+id;
		ArrayList<User> allUsers= UserQuerys.getUserQuery(query);
		User onlyUser = allUsers.get(0);
		return (onlyUser);
	}


	@Override
	public void addUser(User user) {
		UserQuerys.addUser(user);
		
	}

	@Override
	public ArrayList<User> findAllUsers() {
		ArrayList<User> allUsers= UserQuerys.getUserQuery("SELECT * FROM users");
		return allUsers;
	}
	

	@Override
	public List<User> findAllUsersByGroup(int groupId) {
		String query = "SELECT * FROM users WHERE accessrights ="+groupId;
		ArrayList<User> allUsers= UserQuerys.getUserQuery(query);
		return (allUsers);
	}
	@Override
	public List<User> findAllUsersByCountry(int country) {
		String query = "SELECT * FROM users WHERE country ="+country;
		ArrayList<User> allUsers= UserQuerys.getUserQuery(query);
		return (allUsers);
	}


}
