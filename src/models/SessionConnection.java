package models;

import java.sql.*;

import jakarta.servlet.http.HttpSession;
import utils.DBUtil;

/**
 * SessionConnection.java
 * class for session connections, to be stored in a user's Session
 * will only be created through createConnectionforSession, for simplicity when using it in servlets
 */
public class SessionConnection {
    private String address;
    private long creationTime;
    private Connection sessionConnection;
    private boolean authenticated = false;

    /**
     * create a 'session connection' object. this should not be created outside of createConnectionforSession
     * @param adr address of the user
     * @param time time of session creation
     */
    private SessionConnection(String adr, long time) {
        this.address = adr;
        this.creationTime = time;
    }

    // getters
    /**
     * @return address of the user
     */
    public String getAddress() {
        return this.address;
    }
    /**
     * @return creation time of the session
     */
    public long getTimecode() {
        return creationTime;
    }
    /**
     * @return true if the session is authenticated, false otherwise
     */
    public boolean getAuth() {
        return authenticated;
    }

    /**
     * create a connection for any given session.
     * logs their address and time created for some identifiable info
     * @param session the session to create a connection for
     * @param address the address of the user
     */
    public static void createConnectionforSession(HttpSession session, String address) {
        if (session.getAttribute("Connection") != null) {
            return;
        }
        SessionConnection sessionConnection = new SessionConnection(address, session.getCreationTime());
        sessionConnection.startConnection(session);
        session.setAttribute("Connection", sessionConnection);
    }

    // start Session
    /**
     * start a connection for the session
     * @param fallback session to remove the connection from if it fails
     * @return true if the connection was started successfully, false otherwise
     */
    public boolean startConnection(HttpSession fallback) {
        try {
            sessionConnection = DBUtil.startConnection();
            return true;
        } catch (SQLException e) {
            fallback.removeAttribute("Connection");
            System.err.println("Unable to start session: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    /**
     * get session's connection
     * @return the connection for the session
     */
    public Connection getConnection() {
        if (sessionConnection != null) {
            authenticated = true;
            return sessionConnection;
        }
        return null;
    }

    // close connection upon session end
    /**
     * end the connection for the session
     */
    public void endConnection() {
        try {
            if (sessionConnection != null) {
                sessionConnection.close();
            }
        } catch (SQLException e) {
            System.err.println("Unable to end session: " + e.getMessage());
            e.printStackTrace();
        }

    }
}
