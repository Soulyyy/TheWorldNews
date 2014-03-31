package TheWorldNews.database;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


import TheWorldNews.userdata.User;

public class DatabaseCommands {
	
	static Connection getConnection() throws SQLException, URISyntaxException {
        String databaseUrl = System.getenv("DATABASE_URL");
        if (databaseUrl != null) {
            return getHerokuConnection(new URI(databaseUrl));
        } else {
            return getLocalConnection();
        }
    }
	
	
	 static Connection getHerokuConnection(final URI dbUri)
	            throws SQLException, URISyntaxException {
	        String username = dbUri.getUserInfo().split(":")[0];
	        String password = dbUri.getUserInfo().split(":")[1];
	        String dbUrl = "jdbc:postgresql://" + dbUri.getHost()
	                + ':' + dbUri.getPort() + dbUri.getPath();
	 
	        return DriverManager.getConnection(
	                dbUrl, username, password);
	    }
	 
	 static Connection getLocalConnection() throws SQLException {
	        return DriverManager.getConnection(
	                "jdbc:postgresql://localhost/mydb", "user", "pass");
	    }
	
	private static String url = "jdbc:postgresql://hostname:port" +
            "/dbname?user=username&password=password&ssl=true";
	
	
	
	public static ArrayList<User> getUserQuery(String query) {
		 Statement stmt = null;
		  ArrayList<User> userList = new ArrayList<User>();
		 try {
			 Connection con = getConnection();
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
		 } catch(URISyntaxException x) {
			 Logger lgr = Logger.getLogger(DatabaseCommands.class.getName());
			 lgr.log(Level.WARNING, x.getMessage(), x);
		 }
		 catch(SQLException se ) {
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
			Connection con = getConnection();
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
		 } catch(URISyntaxException x) {
			 Logger lgr = Logger.getLogger(DatabaseCommands.class.getName());
			 lgr.log(Level.WARNING, x.getMessage(), x);
		 }catch(SQLException se) {
			 Logger lgr = Logger.getLogger(DatabaseCommands.class.getName());
			 lgr.log(Level.SEVERE, se.getMessage(), se);
		 }
	}


}
