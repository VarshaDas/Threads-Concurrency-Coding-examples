package Executors.ThreadLocal;

public class SessionData {
    private final String sessionId;

    public SessionData(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getSessionId() {
        return sessionId;
    }
}

