package TheWorldNews.database.querys;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import TheWorldNews.database.DatabaseConnection;


public class LoginQueries {

	public static int getUserAccessrights(String username) throws SQLException, URISyntaxException {
		System.out.println("Getting access rights");
			Connection con = DatabaseConnection.getConnection();
			String query = "SELECT accessrights from users where username="+username;
			PreparedStatement pst = con.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			rs.next();		//We might not need this
			int id = rs.getInt("accessrights");
			return id;
	}
	
	public static boolean loginVerification(String username, String password) throws SQLException, URISyntaxException {
		System.out.println("Getting if user can log in");
		Connection con = DatabaseConnection.getConnection();
		String query = "SELECT accessrights from users where username = ? AND password = ?";
		PreparedStatement pst = con.prepareStatement(query);
		//If resultset is empty, not valid login details
		return(pst.executeQuery().next());
	}
	
	public static int loginWithAccessrights(String username, String password) throws SQLException, URISyntaxException {
		System.out.println("Access rights with verifictation, good login");
		Connection con = DatabaseConnection.getConnection();
		String query = "SELECT accessrights from users where username = ? AND password = ?";
		PreparedStatement pst = con.prepareStatement(query);
		pst.setString(1, username);
		pst.setString(2, password);
		if(!(pst.executeQuery().next())) return -1;
		else {
			ResultSet rs = pst.executeQuery();
			rs.next();		//We might not need this
			int id = rs.getInt("accessrights");
			return id;
		}
	}
	
}
