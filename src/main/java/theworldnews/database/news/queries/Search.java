package theworldnews.database.news.queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
public class Search {

	public static ArrayList<String> getsearch(Connection con, String term) {
		String query = "WITH q AS ( SELECT to_tsquery(?) AS query), ranked AS  ( SELECT id, header, content,image , ts_rank_cd(tsv,query) AS rank FROM newsarticles,q WHERE q.query @@ tsv ORDER BY rank DESC LIMIT 10 ) SELECT id,ts_headline(header,q.query), ts_headline(content,q.query)  AS content, image FROM ranked,q ORDER BY ranked ASC";
		try (PreparedStatement pst = con.prepareStatement(query)) {
			pst.setString(1, term);
			ResultSet rs = pst.executeQuery();
			ArrayList<String> result = new ArrayList<String>();
			while (rs.next()) {
				int id = rs.getInt("id");
				String header = rs.getString("ts_headline");
				String content = rs.getString("content");
				String image = rs.getString("image");
				
				result.add(Integer.toString(id));
				result.add(header);
				result.add(content);		
				result.add(image);		
			}
			
		return result;
		}
		catch (SQLException e) {
			Logger.getLogger(latest.class.getName()).log(Level.SEVERE, e.getMessage(), e);
		}

		return null;
	}
}
