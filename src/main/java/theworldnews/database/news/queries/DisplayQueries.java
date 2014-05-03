package theworldnews.database.news.queries;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import theworldnews.database.connection.DatabaseConnection;
import theworldnews.database.news.objects.*;

public class DisplayQueries {
	
	public static ArrayList<Article> getArticlesByNumberAndType(int number, String type) throws SQLException, URISyntaxException {
		System.out.println("Entered article query get by number and type");
		Connection con = DatabaseConnection.getConnection();
		int articlegroup = ArticlegroupEncoding.stringToInt(type);
		 String query = "SELECT * FROM Articles WHERE articlegroup % ? =0 ORDER BY id DESC limit ?";
		 PreparedStatement pst = con.prepareStatement(query);
		 pst.setInt(1, articlegroup);
		 pst.setInt(2, number);
		 ResultSet rs = pst.executeQuery();
		 ArrayList<Article> listOfValues = new ArrayList<Article>();
		 //PARSE RESULTSET TO ARRAYLIST
		 
		 while(rs.next()){
			 String image = rs.getString("image");
			 String header = rs.getString("header"); //fails if in this order
			 String content = rs.getString("content");
			 
			 String articlegroupString = rs.getString("articlegroup");
			 listOfValues.add(new Article(1, image, header, content, articlegroupString));
		}
		 con.close();
		 return(listOfValues);
		 
	}
	
	public static Article getArticleById(int ID) throws SQLException, URISyntaxException{
		System.out.println("Entered article query get by id");
		Connection con = DatabaseConnection.getConnection();
		String query = "SELECT * FROM Articles WHERE id = ?";
		PreparedStatement pst = con.prepareStatement(query);
		pst.setInt(1, ID);
		ResultSet rs = pst.executeQuery();
		Integer id = rs.getInt("id");
		String image = rs.getString("image");
		String header = rs.getString("header");
		String content = rs.getString("content");
		String articlegroupString = rs.getString("articlegroup");
		
		Article article = new Article(id, image, header, content, articlegroupString);
		con.close();
		return article;
		
	}

}
