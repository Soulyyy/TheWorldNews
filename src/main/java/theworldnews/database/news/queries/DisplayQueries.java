package theworldnews.database.news.queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import theworldnews.database.news.objects.*;
import theworldnews.database.users.objects.UserInfo;

public class DisplayQueries {

	/**
	 * @param con
	 *            Connection from java.sql package
	 * @param id
	 *            field value in table newsarticles
	 * @return Article without content
	 */
	public static TreeMap<Article, UserInfo> getDisplayarticleById(
			Connection con, int id) {
		try {
			String query = "SELECT userinfo.userid, userinfo.firstname, userinfo.surname,newsarticles.image,"
					+ "newsarticles.header, newsarticles.articlegroup FROM userinfo"
					+ "INNER JOIN newsarticles ON userinfo.userid=newsarticles.author WHERE userinfo.userid=?";
			// String query =
			// "SELECT newsarticles.image, newsarticles.header, newsarticles.content, newsarticles.articlegroup, newsarticles.author,"
			// +
			// "newsarticles.authorid, users.firstname , users.lastname FROM newsarticles WHERE id = ?";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			String image = rs.getString("image");
			String header = rs.getString("header");
			String articlegroup = rs.getString("articlegroup");
			int authorid = rs.getInt("author");
			Article article = new Article(id, image, header, articlegroup,
					authorid);
			String firstname = rs.getString("firstname");
			String surname = rs.getString("surname");
			UserInfo userinfo = new UserInfo(authorid, firstname, surname);
			TreeMap<Article, UserInfo> articleMap = new TreeMap<Article, UserInfo>();
			articleMap.put(article, userinfo);
			rs.close();
			pst.close();
			return articleMap;

		} catch (SQLException e) {
			Logger.getLogger(DisplayQueries.class.getName()).log(Level.SEVERE,
					e.getMessage(), e);
			return null;
		}
	}

	/**
	 * @param con
	 *            Connection from java.sql package
	 * @param id
	 *            field value in table newsarticles
	 * @return Article with content with the given id
	 */
	public static TreeMap<Article, UserInfo> getViewarticleById(Connection con,
			int id) {

		try {
			String query = "SELECT userinfo.userid, userinfo.firstname, userinfo.surname,newsarticles.image,"
					+ "newsarticles.header, newsarticles.content, newsarticles.articlegroup FROM userinfo"
					+ "INNER JOIN newsarticles ON userinfo.userid=newsarticles.author WHERE userinfo.userid=?";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				String image = rs.getString("image");
				String header = rs.getString("header");
				String content = rs.getString("content");
				String articlegroup = rs.getString("articlegroup");
				int authorid = rs.getInt("author");
				String firstname = rs.getString("firstname");
				String surname = rs.getString("surname");

				Article article = new Article(id, image, header, content,
						articlegroup, authorid);
				UserInfo userinfo = new UserInfo(id, firstname, surname);
				TreeMap<Article, UserInfo> articleMap = new TreeMap<Article, UserInfo>();
				articleMap.put(article, userinfo);
				rs.close();
				pst.close();
				return articleMap;
			} else {
				return null;
			}
		} catch (SQLException e) {

			Logger.getLogger(DisplayQueries.class.getName()).log(Level.SEVERE,
					e.getMessage(), e);
			return null;
		}
	}

	/**
	 * @param con
	 *            Connection from java.sql package
	 * @param number
	 *            number of Article objects requested in output
	 * @param type
	 *            articlegroup String value, defines the groups the objects
	 *            belong in
	 * @return List of n Article objects which belong to the groups defined by
	 *         type
	 */
	public static TreeMap<Article, UserInfo> getDisplayarticlesByNumberAndType(
			Connection con, int number, String type) {
		try {
			int articlegroup = ArticlegroupEncoding.stringToInt(type);
			String query = "SELECT userinfo.userid, userinfo.firstname, userinfo.surname,newsarticles.image,newsarticles.header,newsarticles.articlegroup,clickcount FROM userinfo"
					+ "INNER JOIN newsarticles ON userinfo.userid=newsarticles.author WHERE"
					+ " articlegroup % ? =0 ORDER BY id DESC limit ?";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setInt(1, articlegroup);
			pst.setInt(2, number);
			ResultSet rs = pst.executeQuery();
			TreeMap<Article, UserInfo> articleMap = new TreeMap<Article, UserInfo>();

			while (rs.next()) {
				Article article = new Article(rs.getInt("userid"),
						rs.getString("image"), rs.getString("header"),
						rs.getString("articlegroup"), rs.getInt("author"));
				UserInfo userinfo = new UserInfo(rs.getInt("userid"),
						rs.getString("firstname"), rs.getString("surname"));
				articleMap.put(article, userinfo);
			}
			rs.close();
			pst.close();
			return articleMap;
		} catch (SQLException e) {
			Logger lgr = Logger.getLogger(DisplayQueries.class.getName());
			lgr.log(Level.SEVERE, e.getMessage(), e);
		}
		return null;
	}

	/**
	 * @param con
	 *            Connection from java.sql package
	 * @param articlegroupid
	 *            value for an articlegroup String generated by
	 *            theworldnews.database.news.objects.ArticleGroupEncoding
	 * @return List of Article objects which belong to the given group
	 */
	public static List<Article> getDisplayarticlesByArticlegroup(
			Connection con, int articlegroupid) {
		try {
			String query = "SELECT id, image, header, articlegroup, author, clickcount"
					+ " FROM newsarticles WHERE articlegroup % ? =0 ORDER BY id DESC";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setInt(1, articlegroupid);
			ResultSet rs = pst.executeQuery();
			List<Article> articleArray = new ArrayList<>();

			while (rs.next()) {
				articleArray.add(new Article(rs.getInt("id"), rs
						.getString("image"), rs.getString("header"), rs
						.getString("articlegroup"), rs.getInt("author")));
			}
			return articleArray;
		} catch (SQLException e) {
			Logger lgr = Logger.getLogger(DisplayQueries.class.getName());
			lgr.log(Level.SEVERE, e.getMessage(), e);
		}
		return null;
	}

	public static List<Article> getEditViewArticlesByAuthor(Connection con,
			int userId) {
		String query = "SELECT * FROM newsarticles WHERE author=? order by id DESC";
		try {
			PreparedStatement pst = con.prepareStatement(query);
			pst.setInt(1, userId);
			ResultSet rs = pst.executeQuery();
			List<Article> articleArray = new ArrayList<>();

			while (rs.next()) {
				articleArray.add(new Article(rs.getInt("id"), rs
						.getString("image"), rs.getString("header"), rs
						.getString("content"), rs.getString("articlegroup"), rs
						.getInt("author")));
			}
			return articleArray;
		} catch (SQLException e) {
			Logger lgr = Logger.getLogger(DisplayQueries.class.getName());
			lgr.log(Level.SEVERE, e.getMessage(), e);
		}
		return null;
	}

	public static List<Article> getAllEditArticles(Connection con) {
		String query = "SELECT id, header, articlegroup, author FROM newsarticles order by id DESC";
		try {
			PreparedStatement pst = con.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			List<Article> articleArray = new ArrayList<>();

			while (rs.next()) {
				int id = rs.getInt("id");
				String header = rs.getString("header");
				String articlegroupString = rs.getString("articlegroup");
				int author = rs.getInt("author");
				articleArray.add(new Article(id, header, articlegroupString,
						author));
			}
			return articleArray;
		} catch (SQLException e) {
			return null;
		}
	}

	public static List<Article> getEditArticlesByAuthor(Connection con,
			int userId) {
		String query = "SELECT id, header, articlegroup, author FROM newsarticles WHERE author=? order by id DESC";
		try {
			PreparedStatement pst = con.prepareStatement(query);
			pst.setInt(1, userId);
			ResultSet rs = pst.executeQuery();
			List<Article> articleArray = new ArrayList<>();

			while (rs.next()) {
				int id = rs.getInt("id");
				String header = rs.getString("header");
				String articlegroupString = rs.getString("articlegroup");
				int author = rs.getInt("author");
				articleArray.add(new Article(id, header, articlegroupString,
						author));
			}
			return articleArray;
		} catch (SQLException e) {
			Logger lgr = Logger.getLogger(DisplayQueries.class.getName());
			lgr.log(Level.SEVERE, e.getMessage(), e);
		}
		return null;

	}

	public static List<Article> getLatestNews(Connection con) {
		String query = "SELECT id, header, articlegroup, author FROM newsarticles order by id DESC limit 5";
		try {
			PreparedStatement pst = con.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			List<Article> articleArray = new ArrayList<>();

			while (rs.next()) {
				int id = rs.getInt("id");
				String header = rs.getString("header");
				String articlegroupString = rs.getString("articlegroup");
				int author = rs.getInt("author");
				articleArray.add(new Article(id, header, articlegroupString,
						author));
			}
			return articleArray;
		} catch (SQLException e) {
			Logger lgr = Logger.getLogger(DisplayQueries.class.getName());
			lgr.log(Level.SEVERE, e.getMessage(), e);
			return null;
		}

	}

	// public static boolean editPageAuthorization(Connection con, int userid,
	// int articleid) {
	//
	//
	// }

}
