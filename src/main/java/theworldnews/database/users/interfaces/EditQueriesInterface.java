package theworldnews.database.users.interfaces;

import java.sql.Connection;

import theworldnews.database.users.objects.User;
import theworldnews.database.users.objects.UserInfo;

public interface EditQueriesInterface {

	/**
	 * 
	 * @param con
	 *            Connection from java.sql package
	 * @param user
	 *            Object of type User from theworldnews.database.users.objects
	 */
	public void addUser(Connection con, User user);

	/**
	 * 
	 * @param con
	 *            Connection from java.sql package
	 * 
	 * @param userinfo
	 *            Object of type UserInfo from
	 *            theworldnews.database.users.objects
	 * @param userid
	 *            "userid" value from table "userinfo". Equivalent to "id" from
	 *            table "users"
	 */
	public void addUserInfo(Connection con, UserInfo userinfo, int userid);

	/**
	 * 
	 * @param con
	 *            Connection from java.sql package
	 * 
	 * @param id
	 *            "id" value from table "users". Equivalent to "userid" from
	 *            table "userinfo"
	 */
	public void deleteUser(Connection con, int id);

	/**
	 * 
	 * @param con
	 *            Connection from java.sql package
	 * 
	 * @param userid
	 *            "userid" value from table "userinfo". Equivalent to "id" from
	 *            table "users"
	 */
	public void deleteUserInfo(Connection con, int userid);

	/**
	 * 
	 * @param con
	 *            Connection from java.sql package
	 * 
	 * @param user
	 *            Object of type User from theworldnews.database.users.objects
	 * @return The "id" column value from table "users"
	 */
	public int returnUserid(Connection con, User user);

}
