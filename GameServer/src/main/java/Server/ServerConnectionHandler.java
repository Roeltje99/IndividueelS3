package Server;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ServerConnectionHandler extends TextWebSocketHandler
{
    private List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        for(WebSocketSession s : sessions)
        {
            if (s.getId() != session.getId())
            {
                if (s.isOpen())
                {
                    s.sendMessage(new TextMessage(session.getId() + ":" + message.getPayload()));
                }
                else
                {
                    sessions.remove(s);
                }
            }
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);
        System.out.println(session.getId());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus)
    {
        System.out.println("Session Closed: " + session.getId());
    }
}