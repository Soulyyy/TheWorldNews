package TheWorldNews.database.querys;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import TheWorldNews.database.DatabaseConnection;
import TheWorldNews.newsdata.NewsArticle;
import TheWorldNews.newsdata.NewsEncoding;

public class DisplayQueries {
	
	public static ArrayList<NewsArticle> getArticlesByNumberAndType(int number, String type) throws SQLException, URISyntaxException {
		System.out.println("Entered article query get by number and type");
		Connection con = DatabaseConnection.getConnection();
		int articlegroup = NewsEncoding.jointArticleConvertToInt(type);
		 String query = "SELECT * FROM newsarticles WHERE mod(articlegroup,?)=0 ORDER BY id DESC limit ?";
		 PreparedStatement pst = con.prepareStatement(query);
		 pst.setInt(1, articlegroup);
		 pst.setInt(2, number);
		 ResultSet rs = pst.executeQuery();
		 ArrayList<NewsArticle> listOfValues = new ArrayList<NewsArticle>();
		 //PARSE RESULTSET TO ARRAYLIST
		 
		 while(rs.next()){
			 String image = rs.getString("image");
			 String header = rs.getString("header"); //fails if in this order
			 String content = rs.getString("content");
			 String sid = rs.getString("sid");
			 articlegroup = rs.getInt("articlegroup");
			 listOfValues.add(new NewsArticle(1, image, header, content, sid,articlegroup));
		}
		 return(listOfValues);
		 
	}

}
