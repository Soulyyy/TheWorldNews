package theworldnews.handlers.news.sockets;

import java.io.IOException;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

/**
 * 
 * @author S
 * 
 */
@WebSocket
public class LatestNewsSocket {

	private Session session;
	private final LatestNewsSocketController controller;

	public LatestNewsSocket(LatestNewsSocketController controller) {
		this.controller = controller;
	}

	public void send(String message) throws IOException {
		if (session.isOpen()) {
			session.getRemote().sendString(message, null);
		}
	}

	@OnWebSocketConnect
	public void onOpen(Session session) {
		this.session = session;
		controller.getSockets().add(this);
	}

	@OnWebSocketClose
	public void onClose(int status, String message) {
		controller.getSockets().remove(this);
	}

	@OnWebSocketMessage
	public void onMessage(Session session, String message) throws IOException {
		System.out.println("received on websocket: " + message);
	}
}
