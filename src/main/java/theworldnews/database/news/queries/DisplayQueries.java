package theworldnews.database.news.queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
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
	public static LinkedHashMap<Article, UserInfo> getDisplayarticleById(
			Connection con, int id) {
		try {
			String query = "SELECT userinfo.userid, userinfo.firstname, userinfo.surname,newsarticles.image,"
					+ "newsarticles.header, newsarticles.articlegroup FROM userinfo"
					+ "INNER JOIN newsarticles ON userinfo.userid=newsarticles.author WHERE newsarticles.id=?";
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
			LinkedHashMap<Article, UserInfo> articleMap = new LinkedHashMap<Article, UserInfo>();
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
	public static LinkedHashMap<Article, UserInfo> getViewarticleById(
			Connection con, int id) {

		try {
			String query = "SELECT userinfo.userid, userinfo.firstname, userinfo.surname,newsarticles.image,"
					+ "newsarticles.header, newsarticles.content, newsarticles.articlegroup FROM userinfo "
					+ "INNER JOIN newsarticles ON userinfo.userid=newsarticles.author WHERE newsarticles.id=?";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			if(rs.next()){
			String image = rs.getString("image");
			String header = rs.getString("header");
			String content = rs.getString("content");
			String articlegroup = rs.getString("articlegroup");
			int authorid = rs.getInt("userid");
			String firstname = rs.getString("firstname");
			String surname = rs.getString("surname");

			Article article = new Article(id, image, header, content,
					articlegroup, authorid);
			UserInfo userinfo = new UserInfo(authorid, firstname, surname);
			LinkedHashMap<Article, UserInfo> articleMap = new LinkedHashMap<Article, UserInfo>();
			articleMap.put(article, userinfo);
		
			return articleMap;
			}
			
			rs.close();
			pst.close();
		} catch (SQLException e) {

			Logger.getLogger(DisplayQueries.class.getName()).log(Level.SEVERE,
					e.getMessage(), e);
			
		}
		return null;
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
	public static LinkedHashMap<Article, UserInfo> getDisplayarticlesByNumberAndType(
			Connection con, int number, String type) {
		try {
			int articlegroup = ArticlegroupEncoding.stringToInt(type);
			String query = "SELECT userinfo.userid, userinfo.firstname, userinfo.surname,newsarticles.image,newsarticles.header,newsarticles.articlegroup,clickcount FROM userinfo "
					+ "INNER JOIN newsarticles ON userinfo.userid=newsarticles.author WHERE"
					+ " articlegroup % ? =0 ORDER BY id DESC limit ?";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setInt(1, articlegroup);
			pst.setInt(2, number);
			ResultSet rs = pst.executeQuery();
			LinkedHashMap<Article, UserInfo> articleMap = new LinkedHashMap<Article, UserInfo>();
			System.out.println("Bangarang BAMBABMA");
			while (rs.next()) {
				System.out.println("Booyajka");
				Article article = new Article(rs.getInt("userid"),
						rs.getString("image"), rs.getString("header"),
						rs.getString("articlegroup"), rs.getInt("userid"));
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
			return new LinkedHashMap<Article, UserInfo>();
		}
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

	// I think this is plain stupid, because we don't NEED any aggregated data
	// gets. I had this written down in "etapp 2".
	public static int articlesSubmittedByUser(Connection con, int userid) {
		String query = "SELECT users.id, COUNT(newsarticles.id) AS NumberOfArticles FROM newsarticles LEFT JOIN users ON users.id=newsarticles.author"
				+ " WHERE users.id=? GROUP BY users.id";
		try {
			PreparedStatement pst = con.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			return rs.getInt("numberofarticles");
		} catch (SQLException e) {
			Logger lgr = Logger.getLogger(DisplayQueries.class.getName());
			lgr.log(Level.SEVERE, e.getMessage(), e);
			return 0;
		}
	}
}
