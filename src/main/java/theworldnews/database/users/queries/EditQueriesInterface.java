package theworldnews.database.users.queries;

import java.util.List;

import theworldnews.database.users.objects.User;


public interface EditQueriesInterface {

	public User findUserById(int id);
	public List<User> findAllUsers();
	public List<User> findAllUsersByGroup(int groupId);
	public List<User> findAllUsersByCountry(int country);
	public void addUser(User user);
	//public int getAccessRights(String username);
	//public boolean confirmLogin(String username, String password);
}
