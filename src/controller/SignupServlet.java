package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import java.util.logging.Logger;

import dao.UserDAO;
import models.SessionConnection;
import models.User;

import org.json.JSONObject;
import org.mindrot.jbcrypt.BCrypt;

import com.google.gson.Gson;

/**
 * SignupServlet.java
 * This servlet handles user signup requests and session management.
 */
@WebServlet("/user-api/signup")
public class SignupServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");

        Logger svltLog = Logger.getLogger (this.getClass().getName() + "Servlet");
        BufferedReader reader = request.getReader();
        JSONObject jsonResponse = new JSONObject();

        svltLog.warning ("Request received for signup.");

        // grab session
        User user;
        Object userSession = request.getSession().getAttribute("UserData");

        // if session exists and User object exists in their session
        if (userSession != null && userSession instanceof User)
        {
            // user is already logged in. lets make sure this cuts off here.
            user = (User)userSession;
            svltLog.warning ("Session detected, must have logged in.");
            jsonResponse.put("success", false);
            jsonResponse.put("message", "Signup failed because you are already logged in.");
            response.getWriter().write(jsonResponse.toString());
            return;
        }
        else
        {
            // create a new session
            HttpSession newSession = request.getSession(true);

            // let loginservlet handle logging the user's session
            user = new Gson().fromJson(reader, User.class);
            //newSession.setAttribute("UserData", user);

            // creates a SessionConnection object that holds the connection, along with some info
            // stores it in an attributed called "Connection"
            if (newSession.getAttribute("Connection") == null)
            {
                SessionConnection.createConnectionforSession(newSession, request.getRemoteAddr());
            }
            
            svltLog.warning ("New session created for " + user.getUserEmail());

            // missing data members from signup form
            user.setOver18(true);
            user.setTransport("Car");

            // DAO accepts the 'session connection' object, grabs the connection in it through .getConnection()
            UserDAO userDAO = new UserDAO((SessionConnection)newSession.getAttribute("Connection"));
            String password = user.getUserPasswordHash();
            String passwordHash = BCrypt.hashpw(password, BCrypt.gensalt());

            // TODO: validate stuff
            user.setUserPasswordHash(passwordHash);
            if (userDAO.insertUser(user)) 
            {
                jsonResponse.put("success", true);
            }
            else
            {
                jsonResponse.put("success", false);
                jsonResponse.put("message", "Signup failed because of a duplicate email, or a database error.");
            }
            response.getWriter().write(jsonResponse.toString());
        }

        

    }
}

