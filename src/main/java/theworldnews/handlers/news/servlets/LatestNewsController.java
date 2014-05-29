// package theworldnews.handlers.news.servlets;
//
// import java.io.IOException;
// import java.io.PrintWriter;
// import java.net.URISyntaxException;
// import java.sql.Connection;
// import java.sql.SQLException;
// import java.util.ArrayList;
// import java.util.List;
//
// import javax.servlet.ServletException;
// import javax.servlet.annotation.WebServlet;
// import javax.servlet.http.HttpServlet;
// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpServletResponse;
//
// import com.google.gson.Gson;
//
// import theworldnews.database.connection.DatabaseConnection;
// import theworldnews.database.news.objects.Article;
// import theworldnews.database.news.objects.ArticleResponse;
// import theworldnews.database.news.queries.*;
// import theworldnews.handlers.news.sockets.LatestNewsSocketController;
//
// @WebServlet(value = "/latestNews")
// public class LatestNewsController extends HttpServlet {
//
//	 private static final long serialVersionUID = 1L;
//
//	 @Override
//	 protected void doGet(HttpServletRequest req, HttpServletResponse resp)
//			 throws ServletException, IOException {
//
//		 try (Connection con = DatabaseConnection.getConnection()) {
//			 Gson gson = new Gson();
//
//			 List<Article> test = DisplayQueries.getLatestNews(con);
//			 String test2 = gson.toJson(test);
//			 resp.setHeader("Content-Type", "application/json");
//			 resp.getWriter().write(test2);
//
//			 try {
//				 LatestNewsSocketController.find(req.getServletContext())
//						 .sendMessage();
//			 } catch (NullPointerException e) {
//				 System.out
//						 .println("Tartu, we have a problem. Actually no twats are looking at our websockets.");
//			 }
//		 }
//
//	 }
// }
