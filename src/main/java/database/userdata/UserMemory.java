package database.userdata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import database.datastore.UserDataProvider;

/*THIS IS PROBABLY A BULLSHIT CLASS.
 * USING THIS FOR TESTING PURPOSES, I AM 90% CONFIDENT WE NEED TO BLOW THIS UP
 */

public class UserMemory implements UserDataProvider{
	
	
	private final Map<Integer, User> users;
	private int userCount;

	public UserMemory(){
		//ArrayList for now, I like indexing fast(I think I do)
		/*
		ArrayList<User> firstUserGroup = new ArrayList<User>();
		firstUserGroup.add(new User(1, "GGMEANSGETGUD", 1,34));
		firstUserGroup.add(new User(2, "DIO BRANDO", 3,35));
		
		ArrayList<User> secondUserGrupp = new ArrayList<User>();
		secondUserGrupp.add(new User(3, "BeetaSilver", 2 , 36));
		secondUserGrupp.add(new User(4, "myysilver", 2, 34));
		*/
		
		users= new HashMap<Integer, User>();
		users.put(1,new User(1, "GGMEANSGETGUD", 1,34) );
		users.put(2,new User(2, "DIO BRANDO", 3,35) );
		userCount=2;
	}
	
	@Override
	public User findUserById(int id) {
		return users.get(id);
	}

	@Override
	public List<User> findAllItems() {
		return new ArrayList<>(users.values());
	}

	@Override
	public void addUser(User user) {
		users.put(userCount, user);
		user.id= ++userCount;
		
	}

}
