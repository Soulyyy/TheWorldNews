package theworldnews.database.connection;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

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
}
