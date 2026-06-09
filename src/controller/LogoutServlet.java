package controller;

import models.User;
import org.json.JSONObject;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.logging.Logger;

/**
 * LogoutServlet.java
 * This servlet handles user logout requests and session management.
 * @attributes svltLog
 */
@WebServlet("/user-api/logout")
public class LogoutServlet extends HttpServlet {
    /**
     * Logger for this servlet
     */
    Logger svltLog = Logger.getLogger (this.getClass().getName() + "Servlet");
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                doPost(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        
        JSONObject jsonResponse = new JSONObject();

        // just deletes whatever user data is stored in the session
        // validation could be done but man
        HttpSession userSession = request.getSession(true);
        Object loginData = userSession.getAttribute("UserData");
        if (loginData != null && loginData instanceof User) 
        {

            svltLog.warning ("removing userdata from session... ");
            userSession.removeAttribute("UserData");

            // setting max age to 0 -> cookie gets deleted
            Cookie loggedInFlag = new Cookie("loggedIn", "");
            loggedInFlag.setMaxAge(0); 
            loggedInFlag.setPath("/"); 
            response.addCookie(loggedInFlag);

            jsonResponse.put("message", "Logged out of account successfully.");
            response.getWriter().write(jsonResponse.toString());
            return;
        }

        jsonResponse.put("message", "You are not logged in.");
        response.getWriter().write(jsonResponse.toString());
    }

}
