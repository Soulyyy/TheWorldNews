package database.basefunctions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import database.userdata.User;

public class DatabaseCommands {
	
	
	private static String url = "jdbc:postgresql://localhost/testdb";
	private static String user =  "Souly";
	private static String password = "kalamees";
	private static Connection con = null;
	
	
	
	public static ArrayList<User> getUserQuery(String query) {
		 Statement stmt = null;
		  ArrayList<User> userList = new ArrayList<User>();
		 try {
			 con = DriverManager.getConnection(url, user, password);
			 stmt = con.createStatement();
		     ResultSet rs = stmt.executeQuery(query);
		     while(rs.next()) {
		    	 String username = rs.getString("username");
		    	 int accessrights = rs.getInt("accessrights");
		    	 int country = rs.getInt("country");
		    	 int id = rs.getInt("id");
		    	 User newUser = new User(id, username, accessrights, country);
		    	 userList.add(newUser);
		     }
		     return(userList);
		 }catch(SQLException se ) {
			 Logger lgr = Logger.getLogger(DatabaseCommands.class.getName());
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
	
	public static void addUser(User newUser, String pw) {
		 Statement stmt = null;
		 try {
			 con = DriverManager.getConnection(url, user, password);
			 stmt = con.createStatement();
			 //String query =  "INSERT INTO items (id, username, password, accessrights, country) VALUES (DEFAULT, "+
			 //newUser.userName+", "+newUser.accessRights+", "+newUser.country+ ");";
			 //PreparedStatement pst = con.prepareStatement(query);
			 String query = "INSERT INTO items (id, username, password, accessrights, country)"
			 		+ " VALUES (DEFAULT ,? ,? ,? ,?  )";
			 PreparedStatement pst = con.prepareStatement(query);
			 pst.setString(2, newUser.userName);
			 pst.setString(3, pw);
			 pst.setInt(4, newUser.accessRights);
			 pst.setInt(5, newUser.country);
			 pst.executeUpdate();
		 }catch(SQLException se) {
			 Logger lgr = Logger.getLogger(DatabaseCommands.class.getName());
	            lgr.log(Level.SEVERE, se.getMessage(), se);
		 }
	}


}
