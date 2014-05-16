package theworldnews.database.news.queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class latest {

	public static String[] getlatest(Connection con) {
		String query = "SELECT id,header FROM newsarticles ORDER BY id DESC LIMIT 5";
		String[] la;
		try (PreparedStatement pst = con.prepareStatement(query)) {
			ResultSet rs = pst.executeQuery();
			la = new String[6];
			int i = 0;
			while (rs.next()) {
				if (i==0){
				la[0] = rs.getString(1);
				}
				else{
				la[i] = rs.getString(2);
			
				}
				i += 1;
			}
			return la;
		} catch (SQLException e) {
			Logger.getLogger(latest.class.getName()).log(Level.SEVERE, e.getMessage(), e);
		}

		return null;
	}
}
