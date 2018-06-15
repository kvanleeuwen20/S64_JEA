package service;

import domain.Message;
import dto.MessageDTO;

import javax.ejb.Stateless;
import javax.websocket.EncodeException;
import javax.websocket.Session;
import java.io.IOException;

@Stateless
public class MessagePushService {
    private SessionManager sessionManager;

    public MessagePushService() {
        this.sessionManager = SessionManager.getInstance();
    }

    public void addSession(Session session) {
        this.sessionManager.addSession(session);
    }

    public void removeSession(Session session) {
        this.sessionManager.removeSession(session);
    }

    public void sendUpdate(int userID, MessageDTO message) {
        Session session = this.sessionManager.findSession(userID);

        try {
            session.getBasicRemote().sendObject(message);
        } catch (IOException | EncodeException e) {
            e.printStackTrace();
        }
    }
}
