package theworldnews.database.users.queries;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import theworldnews.database.connection.DatabaseConnection;
import theworldnews.database.users.objects.User;

public class EditQueries {

	public static List<User> getUserQuery(String query) {
		Statement stmt = null;
		List<User> userList = new ArrayList<>();
		try {
			Connection con = DatabaseConnection.getConnection();
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				int id = rs.getInt("id");
				String username = rs.getString("username");
				String pw = rs.getString("password");
				int accessrights = rs.getInt("accessrights");

				User user = new User(id, username, pw, accessrights);
				userList.add(user);
			}
			con.close();
			return (userList);
		} catch (URISyntaxException x) {
			Logger lgr = Logger.getLogger(EditQueries.class.getName());
			lgr.log(Level.WARNING, x.getMessage(), x);
		} catch (SQLException se) {
			Logger lgr = Logger.getLogger(EditQueries.class.getName());
			lgr.log(Level.WARNING, se.getMessage(), se);
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return userList;
	}

	public static void addUser(User newUser) {
		try {
			Connection con = DatabaseConnection.getConnection();
			String query = "INSERT INTO users (id, username, password, accessrights)"
					+ " VALUES (DEFAULT ,? ,? ,?)";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, newUser.getUsername());
			pst.setString(2, newUser.getPassword());
			pst.setInt(3, newUser.getAccessrights());
			pst.executeUpdate();
			con.close();
		} catch (URISyntaxException x) {
			Logger lgr = Logger.getLogger(EditQueries.class.getName());
			lgr.log(Level.WARNING, x.getMessage(), x);
		} catch (SQLException se) {
			Logger lgr = Logger.getLogger(EditQueries.class.getName());
			lgr.log(Level.SEVERE, se.getMessage(), se);
		}
	}

}
