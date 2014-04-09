package TheWorldNews.database.querys;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import TheWorldNews.database.DatabaseConnection;
import TheWorldNews.newsdata.NewsArticle;



public class NewsQuerys {	
	
	//Other querys
	
	public static void getArticleByQuery() {
		
	}

	public static void addArticle(NewsArticle newArticle) throws SQLException, URISyntaxException {
		System.out.println("Entered adding article to database");
			Connection con = DatabaseConnection.getConnection();
			 String query = "INSERT INTO newsarticles (image, header, content, articlegroup)"
			 		+ " VALUES (? ,? ,? ,? )";
			 PreparedStatement pst = con.prepareStatement(query);
			 pst.setString(1, newArticle.image);
			 pst.setString(2, newArticle.header);
			 pst.setString(3, newArticle.content);
			 pst.setInt(4, newArticle.articlegroup);
			 pst.executeUpdate();
	}

	
}
