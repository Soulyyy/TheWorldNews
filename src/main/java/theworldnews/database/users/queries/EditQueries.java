package theworldnews.database.users.queries;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import theworldnews.database.connection.DatabaseConnection;
import theworldnews.database.users.objects.User;

public class EditQueries {
	
	
	
	public static ArrayList<User> getUserQuery(String query) {
		 Statement stmt = null;
		  ArrayList<User> userList = new ArrayList<User>();
		 try {
			 Connection con = DatabaseConnection.getConnection();
			 stmt = con.createStatement();
		     ResultSet rs = stmt.executeQuery(query);
		     while(rs.next()) {
		    	 String username = rs.getString("username");
		    	 int accessrights = rs.getInt("accessrights");
		    	 int country = rs.getInt("country");
		    	 int id = rs.getInt("id");
		    	 String firstname = rs.getString("firstname");
		    	 String surname = rs.getString("surname");
		    	 String email = rs.getString("email");
		    	 String pw = rs.getString("password");
		    	 User newUser = new User(id, username, pw, firstname, surname, email, accessrights, country);
		    	 userList.add(newUser);
		     }
		     con.close();
		     return(userList);
		 } catch(URISyntaxException x) {
			 Logger lgr = Logger.getLogger(UserQuerys.class.getName());
			 lgr.log(Level.WARNING, x.getMessage(), x);
		 }
		 catch(SQLException se ) {
			 Logger lgr = Logger.getLogger(UserQuerys.class.getName());
             lgr.log(Level.WARNING, se.getMessage(), se);
		 }finally {
			 if (stmt != null) { 
				 try {
					 stmt.close();
				 }catch (SQLException e) {
					 e.printStackTrace();
				 }
			 }
		 }
		return userList;
	}
	
	public static void addUser(User newUser) {
		Statement stmt = null;
		System.out.println("ENTER POST SERVER REQ");
		try {
			Connection con = DatabaseConnection.getConnection();
			stmt = con.createStatement();
			 String query = "INSERT INTO users (id, username, password, firstname, surname, email, accessrights, country)"
			 		+ " VALUES (DEFAULT ,? ,? ,? ,? ,? ,? ,?  )";
			 PreparedStatement pst = con.prepareStatement(query);
			 pst.setString(1, newUser.userName);
			 pst.setString(2, newUser.password);
			 pst.setString(3, newUser.firstname);
			 pst.setString(4, newUser.surname);
			 pst.setString(5, newUser.email);
			 pst.setInt(6, newUser.accessRights);
			 pst.setInt(7, newUser.country);
			 pst.executeUpdate();
			 con.close();
		 } catch(URISyntaxException x) {
			 Logger lgr = Logger.getLogger(UserQuerys.class.getName());
			 lgr.log(Level.WARNING, x.getMessage(), x);
		 }catch(SQLException se) {
			 Logger lgr = Logger.getLogger(UserQuerys.class.getName());
			 lgr.log(Level.SEVERE, se.getMessage(), se);
		 }
	}


}
