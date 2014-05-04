package theworldnews.database.users.queries;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import theworldnews.database.connection.DatabaseConnection;

public class AuthenticationQueries {

	public static int getUserAccessrights(String username) throws SQLException,
			URISyntaxException {
		System.out.println("Getting access rights");
		Connection con = DatabaseConnection.getConnection();
		String query = "SELECT accessrights from users where username="
				+ username;
		PreparedStatement pst = con.prepareStatement(query);
		ResultSet rs = pst.executeQuery();
		rs.next(); // We might not need this
		int id = rs.getInt("accessrights");
		con.close();
		return id;
	}

	public static boolean loginVerification(String username, String password)
			throws SQLException, URISyntaxException {
		Connection con = DatabaseConnection.getConnection();
		String query = "SELECT accessrights from users where username = ? AND password = ?";
		PreparedStatement pst = con.prepareStatement(query);
		// If resultset is empty, not valid login details
		con.close();
		return (pst.executeQuery().next());
	}

	public static int loginWithAccessrights(String username, String password)
			throws SQLException, URISyntaxException {
		Connection con = DatabaseConnection.getConnection();
		String query = "SELECT accessrights from users where username = ? AND password = ?";
		PreparedStatement pst = con.prepareStatement(query);
		pst.setString(1, username);
		pst.setString(2, password);
		ResultSet rs = pst.executeQuery();
		if (!(rs.next())) {
			con.close();
			return -1;
		} else {
			int id = rs.getInt("accessrights");
			con.close();
			return id;
		}
	}

}
