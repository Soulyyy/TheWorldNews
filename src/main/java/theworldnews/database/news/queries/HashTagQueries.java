package theworldnews.database.news.queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import theworldnews.database.news.objects.Tag;

public class HashTagQueries {

	public static int addHashTag(Connection con, int userid, int articleid,String tagname) {
		try {
			String query = "INSERT INTO tags (userid, articleid, tagname)"
					+ " VALUES (? ,? ,?) RETURNING ID";
			PreparedStatement pst = con.prepareStatement(query);

			pst.setInt(1, userid);
			pst.setInt(2, articleid);
			pst.setString(3, tagname);

			int result = pst.executeUpdate();
			return result;
		} catch (SQLException e) {
			Logger.getLogger(EditQueries.class.getName()).log(Level.SEVERE,e.getMessage(), e);
		}
		return -1;
	}

	public static int hashTagCountByUserOnArticle(Connection con, int userid,int articleid) {
		int rsd = 0;
		try {
			// Unverified
			String query = "SELECT COUNT(tags.id) FROM tags WHERE tags.userid=? AND tags.articleid=?";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setInt(1, userid);
			pst.setInt(2, articleid);
			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				rsd = rs.getInt(1);

			}
			return rsd;
		} catch (SQLException e) {
			Logger.getLogger(EditQueries.class.getName()).log(Level.SEVERE,e.getMessage(), e);
		}
		return rsd;
	}

	public static ArrayList<Tag> topHashTagsOnArticle(Connection con,int articleid, int count) {
		try {
			// Unverified
			String query = "SELECT tags.tagname, count(tags.articleid) AS tagcount FROM tags WHERE tags.articleid=? GROUP BY tags.tagname ORDER BY tagcount DESC limit ?";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setInt(1, articleid);
			pst.setInt(2, count);
			ResultSet rs = pst.executeQuery();
			ArrayList<Tag> tags = new ArrayList<Tag>();
			while (rs.next()) {
				Tag temp = new Tag(articleid,rs.getString("tagname"), rs.getInt("tagcount"));
				tags.add(temp);
			}
			return tags;
		} catch (SQLException e) {
			Logger.getLogger(EditQueries.class.getName()).log(Level.SEVERE,e.getMessage(), e);
		}
		return null;
	}

}
