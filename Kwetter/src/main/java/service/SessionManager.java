package service;

import javax.websocket.Session;
import java.util.ArrayList;
import java.util.List;

public class SessionManager {
    private static SessionManager ourInstance = new SessionManager();

    public static SessionManager getInstance() {
        return ourInstance;
    }

    private List<Session> sessions;

    private SessionManager() {
        this.sessions = new ArrayList<>();
    }

    public void addSession(Session session) {
        this.sessions.add(session);
    }

    public void removeSession(Session session) {
        this.sessions.remove(session);
    }

    public Session findSession(int userID) {
        return this.sessions.stream().filter(session -> (Integer) session.getUserProperties().get("userID") == userID).findFirst().orElse(null);
    }
}
