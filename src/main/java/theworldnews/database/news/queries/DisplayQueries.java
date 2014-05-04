package theworldnews.database.news.queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import theworldnews.database.news.interfaces.DisplayQueriesInterface;
import theworldnews.database.news.objects.*;

public class DisplayQueries implements DisplayQueriesInterface {

	@Override
	public Article getDisplayarticleById(Connection con, int id) {
		try {
			String query = "SELECT id, image, header, articlegroup, authorid FROM newsarticles WHERE id = ?";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			String image = rs.getString("image");
			String header = rs.getString("header");
			String articlegroup = rs.getString("articlegroup");
			int authorid = rs.getInt("authorid");
			Article article = new Article(id, image, header, articlegroup,
					authorid);
			return article;

		} catch (SQLException e) {
			Logger lgr = Logger.getLogger(DisplayQueries.class.getName());
			lgr.log(Level.SEVERE, e.getMessage(), e);
		}
		return null;
	}

	@Override
	public Article getViewarticleById(Connection con, int id) {
		try {
			String query = "SELECT * FROM newsarticles WHERE id = ?";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			String image = rs.getString("image");
			String header = rs.getString("header");
			String content = rs.getString("content");
			String articlegroup = rs.getString("articlegroup");
			int authorid = rs.getInt("authorid");
			Article article = new Article(id, image, header, content,
					articlegroup, authorid);
			return article;

		} catch (SQLException e) {
			Logger lgr = Logger.getLogger(DisplayQueries.class.getName());
			lgr.log(Level.SEVERE, e.getMessage(), e);
		}
		return null;
	}

	// WTH IS THIS SUPPOSED TO DO, REALLY NEED TO LOOK INTO THIS
	@Override
	public List<Article> getDisplayarticlesByNumberAndType(Connection con,
			int number, String type) {
		try {
			int articlegroup = ArticlegroupEncoding.stringToInt(type);
			String query = "SELECT id,image,header,articlegroup,author,clickcount FROM Articles WHERE articlegroup % ? =0 ORDER BY id DESC limit ?";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setInt(1, articlegroup);
			pst.setInt(2, number);
			ResultSet rs = pst.executeQuery();
			List<Article> articleArray = new ArrayList<Article>();

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

	@Override
	public List<Article> getDisplayarticlesByArticlegroup(Connection con,
			int articlegroupid) {

		try {
			String query = "SELECT id,image,header,articlegroup,author,clickcount FROM Articles WHERE articlegroup % ? =0 ORDER BY id DESC";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setInt(1, articlegroupid);
			ResultSet rs = pst.executeQuery();
			List<Article> articleArray = new ArrayList<Article>();

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

}
