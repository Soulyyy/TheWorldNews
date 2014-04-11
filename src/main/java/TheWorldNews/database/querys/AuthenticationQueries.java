package TheWorldNews.database.querys;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import TheWorldNews.database.DatabaseConnection;

public class AuthenticationQueries {
	
	
	public int userAuthenticationStatus(String username, String authenticationKey) throws SQLException, URISyntaxException {
		System.out.println("Entered authentication verification");
		Connection con = DatabaseConnection.getConnection();
		String query = "SELECT accessrights from sessions where username = ? AND sessionid = ?";
		PreparedStatement pst = con.prepareStatement(query);
		pst.setString(1, username);
		pst.setString(2, authenticationKey);
		ResultSet rs = pst.executeQuery();
		if(!(rs.next())) return -1;
		else {
			System.out.println("KAS JÕUDIS SIIA?");
			int id = rs.getInt("accessrights");
			return id;
		}
	}
	
	public void removeAuthentication(String username, String authenticationKey) throws SQLException, URISyntaxException{
		System.out.println("Entered authentication delete");
		Connection con = DatabaseConnection.getConnection();
		String query = "DELETE * from sessions where username = ? AND sessionid = ?";
		PreparedStatement pst = con.prepareStatement(query);
		pst.setString(1, username);
		pst.setString(2, authenticationKey);
		pst.executeQuery();
		System.out.println("Removed authentication for an account");
	}
	
	public void addAuthentication(String username, String authenticationKey, String accessrights) throws SQLException, URISyntaxException {
		System.out.println("Entered authentication delete");
		Connection con = DatabaseConnection.getConnection();
		String query = "INSERT INTO sessions (sessionid, username, accessrights)"
		 		+ " VALUES (? ,? ,? ,? )";
		PreparedStatement pst = con.prepareStatement(query);
		pst.setString(2, username);
		pst.setString(1, authenticationKey);
		pst.setString(3, accessrights);
		pst.executeQuery();
		System.out.println("Added authentication for account");
	}

}
