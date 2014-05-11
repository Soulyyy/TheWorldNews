package theworldnews.handlers.news.sockets;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

@WebSocket
public class LatestNewsSocket {

	private final LatestNewsSocketController controller;
	private Session session;

	public LatestNewsSocket(LatestNewsSocketController controller) {
		this.controller = controller;
	}

	public void send(String message) throws IOException {
		if (session == null) {
			Logger.getLogger(this.getClass().getName())
					.log(Level.SEVERE, "Tried to send on websocket when session is null");
			return;
		}

		if (session.isOpen()) {
			session.getRemote().sendString(message);
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
