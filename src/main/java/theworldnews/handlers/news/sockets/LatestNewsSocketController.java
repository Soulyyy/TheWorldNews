package theworldnews.handlers.news.sockets;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;

import org.eclipse.jetty.websocket.api.UpgradeRequest;
import org.eclipse.jetty.websocket.api.UpgradeResponse;
import org.eclipse.jetty.websocket.servlet.*;

import theworldnews.database.connection.DatabaseConnection;
import theworldnews.database.news.objects.Article;
import theworldnews.database.news.queries.DisplayQueries;
import theworldnews.database.news.serializers.LatestArticleSerializer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.jndi.ldap.Connection;

@WebServlet(value = "/feed")
public class LatestNewsSocketController extends WebSocketServlet implements
		WebSocketCreator {

	private static final long serialVersionUID = 1L;
	private List<LatestNewsSocket> sockets;
	private ServletContext context;
	private Pinger pinger;
	private Gson gson;

	public void broadcast(String message) {
		for (LatestNewsSocket socket : sockets) {
			try {
				socket.send(message);
			} catch (IOException e) {
				System.out.println("failed to broadcast to " + socket);
			}
		}
	}

	public void loadMostRecent() throws SQLException, URISyntaxException {
		if (sockets.size() > 0) {
			List<Article> list = DisplayQueries
					.getLatestNews(DatabaseConnection.getConnection());
			for (LatestNewsSocket socket : sockets) {
				try {
					socket.send(gson.toJson(list));
				} catch (IOException e) {
					System.out.println("failed to broadcast to " + socket);
				}
			}
		}
	}

	public List<LatestNewsSocket> getSockets() {
		return sockets;
	}

	public ServletContext getContext() {
		return context;
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		sockets = new CopyOnWriteArrayList<>(); // thread-safe impl
		context = config.getServletContext(); // shared between ALL servlets
		publish(this, context); // so that other servlets could find us
		gson = new GsonBuilder().registerTypeAdapter(Article.class,
				new LatestArticleSerializer()).create();
		pinger = new Pinger();

	}

	@Override
	public Object createWebSocket(UpgradeRequest req, UpgradeResponse resp) {
		return new LatestNewsSocket(this); // socket instance created per client
	}

	@Override
	public void configure(WebSocketServletFactory factory) {
		factory.getPolicy().setIdleTimeout(1500);
		factory.setCreator(this);
	}

	private static void publish(LatestNewsSocketController controller,
			ServletContext context) {
		// see @WebListener and @WebFilter for details about servlet init
		context.setAttribute(LatestNewsSocketController.class.getName(),
				controller);
	}

	public static LatestNewsSocketController find(ServletContext context) {
		return (LatestNewsSocketController) context
				.getAttribute(LatestNewsSocketController.class.getName());
	}

	public void sendMessage(String text) {
		for (LatestNewsSocket socket : sockets) {
			try {
				socket.send(text);
			} catch (IOException e) {
				System.out.println("failed to broadcast to " + socket);
			}
		}
	}

	// What Tõnis took from Jaan, Hans took from Tõnis. Win! Keeping 'em
	// connections alive.
	private class Pinger extends Thread {

		public Pinger() {
			this.start();
		}

		@Override
		public synchronized void run() {
			while (true) {
				sendMessage("");
				try {
					Thread.sleep(30000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
