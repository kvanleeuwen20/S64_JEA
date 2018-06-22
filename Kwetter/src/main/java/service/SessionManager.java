package service;

import javax.websocket.Session;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SessionManager {
    private static SessionManager ourInstance = new SessionManager();

    public static SessionManager getInstance() {
        return ourInstance;
    }

    private Map<Integer, Session> sessions;

    private SessionManager() {
        this.sessions = new HashMap<>();
    }

    public void addSession(Session session, int userID) {
        this.sessions.put(userID, session);
    }

    public void removeSession(Session session) {
        this.sessions.values().remove(session);
    }

    public Session findSession(int userID) {
        return this.sessions.get(userID);
    }
}
