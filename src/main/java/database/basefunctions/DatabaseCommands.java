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
	
	//Temporary
	//private static String url = "jdbc:postgresql://ec2-54-246-90-92.eu-west-1.compute.amazonaws.com:5432/d9peamsq6jc1hv?user=mwytimuobrzzuv&password=vVWQkDdzUuQUC8KMHn_OBjx_Bi&ssl=true";
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
		    	 String firstname = rs.getString("firstname");
		    	 String surname = rs.getString("surname");
		    	 String email = rs.getString("email");
		    	 String pw = rs.getString("password");
		    	 User newUser = new User(id, username, pw, firstname, surname, email, accessrights, country);
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
	
	public static void addUser(User newUser) {
		 Statement stmt = null;
		 try {
			 con = DriverManager.getConnection(url, user, password);
			 stmt = con.createStatement();
			 //String query =  "INSERT INTO items (id, username, password, accessrights, country) VALUES (DEFAULT, "+
			 //newUser.userName+", "+newUser.accessRights+", "+newUser.country+ ");";
			 //PreparedStatement pst = con.prepareStatement(query);
			 String query = "INSERT INTO items (id, username, password, fisrstname, surname, email, accessrights, country)"
			 		+ " VALUES (DEFAULT ,? ,? ,? ,? ,? ,? ,?  )";
			 PreparedStatement pst = con.prepareStatement(query);
			 pst.setString(2, newUser.userName);
			 pst.setString(3, newUser.password);
			 pst.setString(4, newUser.firstname);
			 pst.setString(5, newUser.surname);
			 pst.setString(6, newUser.email);
			 pst.setInt(7, newUser.accessRights);
			 pst.setInt(8, newUser.country);
			 pst.executeUpdate();
		 }catch(SQLException se) {
			 Logger lgr = Logger.getLogger(DatabaseCommands.class.getName());
	            lgr.log(Level.SEVERE, se.getMessage(), se);
		 }
	}


}
