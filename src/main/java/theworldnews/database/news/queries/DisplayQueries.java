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

public class DisplayQueries {

	/**
	 * @param con Connection from java.sql package
	 * @param id field value in table newsarticles
	 * @return Article without content
	 */
	public static Article getDisplayarticleById(Connection con, int id) {
		try {
			String query = "SELECT image, header, content, articlegroup, author FROM newsarticles WHERE id = ?";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			String image = rs.getString("image");
			String content = rs.getString("content");
			String header = rs.getString("header");
			String articlegroup = rs.getString("articlegroup");
			int authorid = rs.getInt("author");
			Article article = new Article(id, image, content, header, articlegroup, authorid);
			return article;

		} catch (SQLException e) {
			Logger.getLogger(DisplayQueries.class.getName()).log(Level.SEVERE, e.getMessage(), e);
		}
		return null;
	}

	/**
	 * @param con Connection from java.sql package
	 * @param id field value in table newsarticles
	 * @return Article with content with the given id
	 */
	public static Article getViewarticleById(Connection con, int id) {
		try {
			String query = "SELECT image, header, content, articlegroup, author FROM newsarticles WHERE id = ?";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			String image = rs.getString("image");
			String header = rs.getString("header");
			String content = rs.getString("content");
			String articlegroup = rs.getString("articlegroup");
			int authorid = rs.getInt("author");

			Article article = new Article(id, image, header, content, articlegroup, authorid);
			rs.close();
			pst.close();
			return article;

		} catch (SQLException e) {
			Logger.getLogger(DisplayQueries.class.getName()).log(Level.SEVERE, e.getMessage(), e);
		}
		// return null;
		return new Article(1, "a", "For some reason it comes here", "and returns this shit", "d", 5);
	}

	/**
	 * @param con Connection from java.sql package
	 * @param number number of Article objects requested in output
	 * @param type articlegroup String value, defines the groups the objects belong in
	 * @return List of n Article objects which belong to the groups defined by type
	 */
	public static List<Article> getDisplayarticlesByNumberAndType(Connection con, int number, String type) {
		try {
			int articlegroup = ArticlegroupEncoding.stringToInt(type);
			String query = "SELECT id,image,header,articlegroup,author,clickcount FROM newsarticles WHERE articlegroup % ? =0 ORDER BY id DESC limit ?";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setInt(1, articlegroup);
			pst.setInt(2, number);
			ResultSet rs = pst.executeQuery();
			List<Article> articleArray = new ArrayList<>();

			while (rs.next()) {
				int id = rs.getInt("id");
				String image = rs.getString("image");
				String header = rs.getString("header");
				String articlegroupString = rs.getString("articlegroup");
				int author = rs.getInt("author");
				articleArray.add(new Article(id, image, header,
											 articlegroupString, author));
			}
			return articleArray;
		} catch (SQLException e) {
			Logger lgr = Logger.getLogger(DisplayQueries.class.getName());
			lgr.log(Level.SEVERE, e.getMessage(), e);
		}
		return null;
	}

	/**
	 * @param con Connection from java.sql package
	 * @param articlegroupid value for an articlegroup String generated by
	 * theworldnews.database.news.objects.ArticleGroupEncoding
	 * @return List of Article objects which belong to the given group
	 */
	public static List<Article> getDisplayarticlesByArticlegroup(Connection con, int articlegroupid) {
		try {
			String query = "SELECT id, image, header, articlegroup, author, clickcount"
					+ " FROM newsarticles WHERE articlegroup % ? =0 ORDER BY id DESC";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setInt(1, articlegroupid);
			ResultSet rs = pst.executeQuery();
			List<Article> articleArray = new ArrayList<>();

			while (rs.next()) {
				int id = rs.getInt("id");
				String image = rs.getString("image");
				String header = rs.getString("header");
				String articlegroupString = rs.getString("articlegroup");
				int author = rs.getInt("author");
				articleArray.add(new Article(id, image, header,
											 articlegroupString, author));
			}
			return articleArray;
		} catch (SQLException e) {
			Logger lgr = Logger.getLogger(DisplayQueries.class.getName());
			lgr.log(Level.SEVERE, e.getMessage(), e);
		}
		return null;
	}

//	public static List<Article> getArticlesByCreator(){
//		
//	}
}
