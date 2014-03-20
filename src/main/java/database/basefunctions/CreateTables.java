package database.basefunctions;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CreateTables {

	public static void main(String[] args) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		
		//Change this, hard code
		String url = "jdbc:postgresql://localhost/testdb";
		String user =  "Souly";
		String password = "kalamees";
		
		try{
			
			//Open Connection
			System.out.println("Connecting to database...");
			con = DriverManager.getConnection(url, user, password);
			System.out.println("Connection established.");
			
			//Initialize tables
			String userSql =" CREATE TABLE IF NOT EXISTS user(id SERIAL NOT NULL PRIMARY KEY,"
					+ " username VARCHAR(225) NOT NULL UNIQUE, password VARCHAR(225),"
					+ " accessrights integer NOT NULL, islogged varchar(10));";
			
			String articleSql = 
			
			PreparedStatement ps  = con.prepareStatement(userSql);
			PreparedStatement ns = con.prepareStatement("CREATE TABLE IF NOT EXISTS"
					+" newsarticle(id SERIAL NOT NULL PRIMARY KEY, image varchar(225),"
					+" header MEMO(65535) NOT NULL, articlegroup integer NOT NULL) ");
			ps.executeUpdate();
			ns.executeUpdate();
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
