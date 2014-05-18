package theworldnews.database.news.queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import theworldnews.database.news.objects.Article;
import theworldnews.database.news.objects.ArticlegroupEncoding;

public class EditQueries {

	/**
	 * @param con
	 *            Connection from @package java.sql
	 * @param article
	 *            Object of type Article
	 * @return int with query id
	 */
	public static int addArticle(Connection con, Article article) {
		try {
			String query = "INSERT INTO newsarticles (image, header, content, articlegroup, author, clickcount)"
					+ " VALUES (? ,? ,? ,?, ?, 0 ) RETURNING ID";
			PreparedStatement pst = con.prepareStatement(query);

			pst.setString(1, article.image);
			pst.setString(2, article.header);
			pst.setString(3, article.content);
			int val = ArticlegroupEncoding.stringToInt(article.articlegroup);
			pst.setInt(4, val);
			pst.setInt(5, article.authorid);

			int result = pst.executeUpdate();
			return result;
		} catch (SQLException e) {
			Logger.getLogger(EditQueries.class.getName()).log(Level.SEVERE,
					e.getMessage(), e);
		}
		return -1;
	}

	public static int editArticle(Connection con, Article article) {
		try {
			String query = "UPDATE newsarticles SET image=?, header= ?, content=?, articlegroup=? WHERE id=? RETURNING ID";
			PreparedStatement pst = con.prepareStatement(query);

			pst.setString(1, article.image);
			pst.setString(2, article.header);
			pst.setString(3, article.content);
			int val = ArticlegroupEncoding.stringToInt(article.articlegroup);
			pst.setInt(4, val);
			pst.setString(5, article.id);
		
			int result = pst.executeUpdate();
			return result;
		} catch (SQLException e) {
			Logger.getLogger(EditQueries.class.getName()).log(Level.SEVERE,e.getMessage(), e);
			return -1;
		}
	}

}
