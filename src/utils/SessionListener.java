package utils;

import java.util.logging.Logger;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import models.SessionConnection;

/**
 * class that listens to any sessions ending, then attempts to destroy any connections held in them
 */
@WebListener
public class SessionListener implements HttpSessionListener {

    @Override
    public void sessionDestroyed(HttpSessionEvent session)
    {
        SessionConnection sessionConnection = (SessionConnection)session.getSession().getAttribute("Connection");
        sessionConnection.endConnection();
        Logger.getLogger("SessionListener").warning("Connection is closing...");
    }
}   