package theworldnews.database.news.queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import theworldnews.database.news.objects.*;

public class latest {

	public static String[] getlatest(Connection con) {
		try {
			String query = "SELECT header FROM newsarticles ORDER BY id DESC LIMIT 5";
			PreparedStatement pst = con.prepareStatement(query);
 
			ResultSet rs = pst.executeQuery();
			String[] la = new String[5];
			int i=0;
			while(rs.next()) {
				la[i] = rs.getString(1);
			}
			return la;

		} catch (SQLException e) {
			Logger.getLogger(latest.class.getName()).log(Level.SEVERE,
					e.getMessage(), e);
		}
		return null;
	}


}
