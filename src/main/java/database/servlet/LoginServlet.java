package database.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {
	
	public void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		static final String JDBC_DRIVER="com.mysql.jdbc.Driver";  
	    static final String DB_URL="jdbc:mysql://localhost/TEST";
	    response.setHeader("Content-Type","application/json");
		response.setContentType("application/json;charset=UTF-8");

	}

}
