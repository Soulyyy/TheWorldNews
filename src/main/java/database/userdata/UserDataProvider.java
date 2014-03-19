package database.userdata;

import java.util.List;


public interface UserDataProvider {

	public User findUserById(int id);
	public List<User> findAllUsers();
	public List<User> findAllUsersByGroup(int groupId);
	public List<User> findAllUsersByCountry(int country);
	public void addUser(User user);
}