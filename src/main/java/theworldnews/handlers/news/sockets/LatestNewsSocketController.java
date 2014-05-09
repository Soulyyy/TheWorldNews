package theworldnews.handlers.news.sockets;

 

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.websocket.servlet.ServletUpgradeRequest;
import org.eclipse.jetty.websocket.servlet.ServletUpgradeResponse;
import org.eclipse.jetty.websocket.servlet.WebSocketCreator;
import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

import theworldnews.database.news.objects.*;

import com.google.gson.Gson;

/**
 * 
 * @author S
 * 
 */
 
@WebServlet(value = "/feed")
public class LatestNewsSocketController extends WebSocketServlet implements WebSocketCreator {

    private List<LatestNewsSocket> sockets;
    private ServletContext context;

    public void broadcast(String message) {
        for (LatestNewsSocket socket : sockets) {
            try {
                socket.send(message);
            } catch (IOException e) {
                System.out.println("failed to broadcast to " + socket);
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
    }

    
    @Override
    public Object createWebSocket(ServletUpgradeRequest req, ServletUpgradeResponse resp) {
        return new LatestNewsSocket(this); // socket instance created per client
    }

    @Override
    public void configure(WebSocketServletFactory factory) {
    	factory.getPolicy().setIdleTimeout(1500);
        factory.setCreator(this);
    }

    private static void publish(LatestNewsSocketController controller, ServletContext context) {
        // see @WebListener and @WebFilter for details about servlet init
        context.setAttribute(LatestNewsSocketController.class.getName(), controller);
    }

    public static LatestNewsSocketController find(ServletContext context) {
        return (LatestNewsSocketController) context.getAttribute(LatestNewsSocketController.class.getName());
    }

}
