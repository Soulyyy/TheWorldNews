package TheWorldNews.database.querys;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import TheWorldNews.database.DatabaseConnection;
import TheWorldNews.newsdata.NewsArticle;



public class NewsQuerys {
	
	//Other querys
	
	public static void getArticleByQuery() {
		
	}

	public static void addArticle(NewsArticle newArticle) {
		Statement stmt = null;
		System.out.println("Entered adding article to database");
		try {
			Connection con = DatabaseConnection.getConnection();
			stmt = con.createStatement();
			 String query = "INSERT INTO newsarticles (id, image, header, content, articlegroup)"
			 		+ " VALUES (DEFAULT ,? ,? ,? ,? )";
			 PreparedStatement pst = con.prepareStatement(query);
			 pst.setString(1, newArticle.image);
			 pst.setString(2, newArticle.header);
			 pst.setString(3, newArticle.content);
			 pst.setInt(4, newArticle.articleGroups);
			 pst.executeUpdate();
		 } catch(URISyntaxException x) {
			 Logger lgr = Logger.getLogger(NewsQuerys.class.getName());
			 lgr.log(Level.WARNING, x.getMessage(), x);
		 }catch(SQLException se) {
			 Logger lgr = Logger.getLogger(NewsQuerys.class.getName());
			 lgr.log(Level.SEVERE, se.getMessage(), se);
		 }
	}

	
}
