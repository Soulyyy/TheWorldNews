package database.datastore;

import java.util.List;

import database.userdata.User;

public interface UserDataProvider {

	
	//Missing a lot of stuff
	public User findUserById(int id);
	public List<User> findAllItems();
	public void addUser(User user);
	
}
