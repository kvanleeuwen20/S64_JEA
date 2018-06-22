package service;

import domain.Message;
import domain.User;
import dto.MessageDTO;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.websocket.EncodeException;
import javax.websocket.Session;
import java.io.IOException;

@Stateless
public class MessagePushService {
    private SessionManager sessionManager;

    @Inject
    private MessageService messageService;

    @Inject
    private UserService userService;

    public MessagePushService() {
        this.sessionManager = SessionManager.getInstance();
    }

    public void addSession(Session session, int userID) {
        this.sessionManager.addSession(session, userID);
    }

    public void removeSession(Session session) {
        this.sessionManager.removeSession(session);
    }

    public void sendUpdate(int userID, MessageDTO message) {
        this.messageService.postMessage(message);

        Session session = this.sessionManager.findSession(userID);

        this.sendMessage(session, message);

        User user = userService.getUserByID(userID);

        for (User follower : user.getFollowers()) {
            Session followerSession = this.sessionManager.findSession(follower.getID());
            if (followerSession != null) {
                this.sendMessage(followerSession, message);
            }
        }
    }

    private void sendMessage(Session session, MessageDTO message) {
            try {
                session.getBasicRemote().sendObject(message);
            } catch (IOException | EncodeException e) {
                e.printStackTrace();
            }
    }
}
