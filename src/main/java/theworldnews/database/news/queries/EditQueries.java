package theworldnews.database.news.queries;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import theworldnews.database.connection.DatabaseConnection;
import theworldnews.database.news.objects.Article;
import theworldnews.database.news.objects.ArticlegroupEncoding;

public class EditQueries {

	// Other querys

	public static void getArticleByQuery() {

	}

	public static void addArticle(Article newArticle) throws SQLException,
			URISyntaxException {
		System.out.println("Entered adding article to database");
		Connection con = DatabaseConnection.getConnection();
		String query = "INSERT INTO newsarticles (image, header, content, articlegroup)"
				+ " VALUES (? ,? ,? ,? )";
		PreparedStatement pst = con.prepareStatement(query);
		pst.setString(1, newArticle.getImage());
		pst.setString(2, newArticle.getHeader());
		pst.setString(3, newArticle.getContent());
		int val = ArticlegroupEncoding
				.stringToInt(newArticle.getArticlegroup());
		pst.setInt(4, val);
		pst.executeUpdate();
		con.close();
	}

}
