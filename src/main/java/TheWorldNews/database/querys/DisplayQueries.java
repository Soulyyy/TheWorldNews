package TheWorldNews.database.querys;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import TheWorldNews.database.DatabaseConnection;
import TheWorldNews.newsdata.NewsArticle;
import TheWorldNews.newsdata.NewsEncoding;

public class DisplayQueries {
	
	public static ArrayList<NewsArticle> getArticlesByNumberAndType(int number, String type) throws SQLException, URISyntaxException {
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

			 		//CHANGED THESE NAMES, CHECK PLEASE
			 		String image = rs.getString("image");
			 		String header = rs.getString("header"); //fails if in this order
			 		String content = rs.getString("content");
			 		articlegroup = rs.getInt("articlegroup");
			 		listOfValues.add(new NewsArticle(1,image, header, content, articlegroup));

			 	}
		 return(listOfValues);
		 
	}

}