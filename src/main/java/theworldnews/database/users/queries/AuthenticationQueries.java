package theworldnews.database.users.queries;

import java.net.URISyntaxException;
import java.sql.*;
import theworldnews.database.connection.DatabaseConnection;
import theworldnews.database.users.objects.User;

public class AuthenticationQueries {

	public static int getUserAccessrights(String username) throws SQLException, URISyntaxException {
		int id;
		try (Connection con = DatabaseConnection.getConnection()) {
			String query = "SELECT accessrights from users where username = ?";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, username);
			ResultSet rs = pst.executeQuery();
			rs.next(); // We might not need this
			id = rs.getInt("accessrights");
		}
		return id;
	}

	public static User loginVerification(String username, String password) throws SQLException, URISyntaxException {
		PreparedStatement pst;
		try (Connection con = DatabaseConnection.getConnection()) {
			String query = "SELECT id, accessrights from users where username = ? AND password = ?";
			pst = con.prepareStatement(query);
			pst.setString(1, username);
			pst.setString(2, password);

			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				return new User(rs.getInt("id"), username, password, rs.getInt("accessrights"));
			} else {
				return null;
			}
		}
	}
	/*
	 public static int loginWithAccessrights(String username, String password) throws SQLException, URISyntaxException {
	 try (Connection con = DatabaseConnection.getConnection()) {
	 String query = "SELECT accessrights from users where username = ? AND password = ?";
	 PreparedStatement pst = con.prepareStatement(query);
	 pst.setString(1, username);
	 pst.setString(2, password);
	 ResultSet rs = pst.executeQuery();
	 if (!(rs.next())) {
	 return -1;
	 } else {
	 return rs.getInt("accessrights");
	 }
	 }
	 }
	 */
}
