package theworldnews.database.users.queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import theworldnews.database.users.objects.User;
import theworldnews.database.users.objects.UserInfo;
import theworldnews.database.users.interfaces.EditQueriesInterface;

public class EditQueries implements EditQueriesInterface {

	@Override
	public void addUser(Connection con, User user) {
		try {
			String query = "INSERT INTO users (id, username, password, accessrights)"
					+ " VALUES (DEFAULT ,? ,? ,?)";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, user.username);
			pst.setString(2, user.password);
			pst.setInt(3, user.accessrights);
			pst.executeUpdate();
		} catch (SQLException e) {
			Logger lgr = Logger.getLogger(EditQueries.class.getName());
			lgr.log(Level.SEVERE, e.getMessage(), e);
		}
	}

	@Override
	public void addUserInfo(Connection con, UserInfo userinfo, int userid) {
		try {
			String query = "INSERT INTO userinfo (userid, firstname, surname, country)"
					+ " VALUES (DEFAULT ,? ,? ,?)";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setInt(2, userid);
			pst.setString(2, userinfo.firstname);
			pst.setString(3, userinfo.surname);
			pst.setInt(4, userinfo.country);
			pst.executeUpdate();
		} catch (SQLException e) {
			Logger lgr = Logger.getLogger(EditQueries.class.getName());
			lgr.log(Level.SEVERE, e.getMessage(), e);
		}

	}

	@Override
	public void deleteUser(Connection con, int id) {
		try {
			String query = "DELETE FROM users WHERE id = ";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setInt(1, id);
			pst.executeUpdate();
		} catch (SQLException e) {
			Logger lgr = Logger.getLogger(EditQueries.class.getName());
			lgr.log(Level.SEVERE, e.getMessage(), e);
		}

	}

	@Override
	public void deleteUserInfo(Connection con, int userid) {
		try {
			String query = "DELETE FROM usersettings WHERE userid = ";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setInt(1, userid);
			pst.executeUpdate();
		} catch (SQLException e) {
			Logger lgr = Logger.getLogger(EditQueries.class.getName());
			lgr.log(Level.SEVERE, e.getMessage(), e);
		}
	}

	@Override
	public int returnUserid(Connection con, User user) {
		try {
			String query = "SELECT id FROM users WHERE username=?";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, user.username);
			ResultSet rs = pst.executeQuery();
			int userid = rs.getInt("id");
			return userid;
		} catch (SQLException e) {
			Logger lgr = Logger.getLogger(EditQueries.class.getName());
			lgr.log(Level.SEVERE, e.getMessage(), e);
		}
		return 0;
	}

}
