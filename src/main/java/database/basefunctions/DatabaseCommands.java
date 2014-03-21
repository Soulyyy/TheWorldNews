package database.basefunctions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseCommands {
	
	
	private static String url = "jdbc:postgresql://localhost/testdb";
	private static String user =  "Souly";
	private static String password = "kalamees";
	
	public static String sqlCommand() {
		//Read data from somewhere
		String command;
		return(command);
	}
	
	public static String getAllUsers() {
		
	}

	
	public static void main(String[] args) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		
		
		//Change this, hard code
		
		try{
			
			//Open Connection
			System.out.println("Connecting to database...");
			con = DriverManager.getConnection(url, user, password);
			System.out.println("Connection established.");
			
			//Initialize tables
			String prepString = sqlCommand();
			
			
			PreparedStatement ps  = con.prepareStatement(prepString);
			ps.executeUpdate();
		}catch(SQLException se) {
			 se.printStackTrace();
		}catch(Exception e) {
			 e.printStackTrace();
		}finally {
			 try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }

            }catch (SQLException se) {
                Logger lgr = Logger.getLogger(Version.class.getName());
                lgr.log(Level.WARNING, se.getMessage(), se);
            }	 
		}
		
	}
}
