package theworldnews.handlers.news.sockets;



import RecentSocketController;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.ArrayList;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import org.eclipse.jetty.websocket.servlet.ServletUpgradeRequest;
import org.eclipse.jetty.websocket.servlet.ServletUpgradeResponse;
import org.eclipse.jetty.websocket.servlet.WebSocketCreator;
import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;





import com.google.gson.Gson;

/**
 * 
 * @author S
 * 
 */
@WebServlet(value = "/feed")
public class LatestNewsSocketController extends WebSocketServlet implements
		WebSocketCreator {

	private List<LatestNewsSocket> sockets;
	private ServletContext context;
	private Gson gson;

	// private Pinger pinger;
	
	
	public void loadLatestNews(){
		if (sockets.size() > 0) {
			//List<Article> list = whereEverTheFuck.getLatestNews();
			List<String> list = new ArrayList<String>();
			list.add("3");
			for(LatestNewsSocket socket : sockets){
				try {
				socket.send(gson.toJson(list));
				} catch (IOException e) {
					System.out.println("failed to send data to " + socket);
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
	public void init(ServletConfig config) throws ServletException{
		super.init(config);
		sockets = new CopyOnWriteArrayList<LatestNewsSocket>();
		publish(this, context);
		context = config.getServletContext(); // shared between ALL servlets
//		publish(this, context); // so that other servlets could find us
//		gson = new com.google.gson.GsonBuilder().registerTypeAdapter(Article.class,
//				new RecentItemSerializer()).create();
//		pinger = new Pinger();
	}

	public Object createWebSocket(ServletUpgradeRequest req,
			ServletUpgradeResponse resp) {
		// TODO Auto-generated method stub
		return new LatestNewsSocket(this);
	}

	@Override
	public void configure(WebSocketServletFactory factory) {
		// TODO Auto-generated method stub
		factory.setCreator(this);
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
	
    private static void publish(LatestNewsSocketController controller, ServletContext context) {
        // see @WebListener and @WebFilter for details about servlet init
        context.setAttribute(LatestNewsSocketController.class.getName(), controller);
    }
    
	public static LatestNewsSocketController find(ServletContext context) {
		return (LatestNewsSocketController) context
				.getAttribute(LatestNewsSocketController.class.getName());
	}

	/**
	 * Class to keep the connection open. Credit to Jaan Janno.
	 * https://github.com
	 * /JaanJanno/OnTime/blob/master/app/controllers/chat/ChatSocket.java
	 */
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
