package theworldnews.database.news.queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import theworldnews.database.news.objects.Article;
import theworldnews.database.news.objects.ArticlegroupEncoding;

/**
 * 
 * @author Souly
 * 
 */
public class EditQueries {

	/**
	 * 
	 * @param con
	 *            Connection from @package java.sql
	 * @param article
	 *            Object of type Article
	 */
	public static void addArticle(Connection con, Article article) {
		try {
			String query = "INSERT INTO newsarticles (image, header, content, articlegroup, author, clickcount)"
					+ " VALUES (? ,? ,? ,?, ?, 0 )";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, article.image);
			pst.setString(2, article.header);
			pst.setString(3, article.content);
			int val = ArticlegroupEncoding.stringToInt(article.articlegroup);
			pst.setInt(4, val);
			pst.setInt(5, article.authorid);
			pst.executeUpdate();
		} catch (SQLException e) {
			Logger lgr = Logger.getLogger(EditQueries.class.getName());
			lgr.log(Level.SEVERE, e.getMessage(), e);
		}
	}

}
