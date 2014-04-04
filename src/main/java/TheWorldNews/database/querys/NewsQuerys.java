package TheWorldNews.database.querys;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import TheWorldNews.database.DatabaseConnection;
import TheWorldNews.newsdata.NewsArticle;



public class NewsQuerys {
	
	//Public connection for all database querys
		public static Connection getConnection() throws SQLException, URISyntaxException {
	        String databaseUrl = System.getenv("DATABASE_URL");
	        if (databaseUrl != null) {
	            return getHerokuConnection(new URI(databaseUrl));
	        } else {
	            return getLocalConnection();
	        }
	    }
		
		
		static Connection getHerokuConnection(final URI dbUri)throws SQLException, URISyntaxException {
			System.out.println("We are trying to connect with Heroku.");
			String username = dbUri.getUserInfo().split(":")[0];
			String password = dbUri.getUserInfo().split(":")[1];
			String dbUrl = "jdbc:postgresql://" + dbUri.getHost()
						+ ':' + dbUri.getPort() + dbUri.getPath();
		 
			return DriverManager.getConnection(dbUrl, username, password);
		    } 
		static Connection getLocalConnection() throws SQLException {
			return DriverManager.getConnection(
					"jdbc:postgresql://localhost/mydb", "user", "pass");
			}
	
	
	
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
