package theworldnews.handlers.news.servlets;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import org.eclipse.jetty.websocket.servlet.ServletUpgradeRequest;
import org.eclipse.jetty.websocket.servlet.ServletUpgradeResponse;
import org.eclipse.jetty.websocket.servlet.WebSocketCreator;
import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;


//@WebServlet(value = "/newsFeed")
public class NewsSocketController extends WebSocketServlet implements WebSocketCreator {
	
	private ServletContext context;
    private List<NewsSocket> sockets;

	public void broadcast(String message) {
        for (NewsSocket socket : sockets) {
            try {
                socket.send(message);
            } catch (IOException e) {
                System.out.println("failed to broadcast to " + socket);
            }
        }
    }

    public List<NewsSocket> getSockets() {
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
    }

    @Override
    public Object createWebSocket(ServletUpgradeRequest req, ServletUpgradeResponse resp) {
        return new NewsSocket(this); // socket instance created per client
    }

    @Override
    public void configure(WebSocketServletFactory factory) {
        factory.setCreator(this);
    }

    private static void publish(NewsSocketController controller, ServletContext context) {
        // see @WebListener and @WebFilter for details about servlet init
        context.setAttribute(NewsSocketController.class.getName(), controller);
    }

    public static NewsSocketController find(ServletContext context) {
        return (NewsSocketController) context.getAttribute(NewsSocketController.class.getName());
    }
}
