package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import models.User;

import com.google.gson.Gson;

/**
 * SessionStatusServlet.java
 * This servlet handles user session status requests, providing session data if available.
 */
@WebServlet("/user-api/session-data")
public class SessionStatusServlet extends HttpServlet {

    // FOR GETTING SESSION DATA

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        final Gson gson = new Gson();

        response.setContentType("application/json");
        HttpSession newSession = request.getSession(true);
        Object loginData = newSession.getAttribute("UserData");
        if (loginData != null && loginData instanceof User)
        {
            response.getWriter().write(gson.toJson((User)loginData));
        }
        else
        {
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        }
        
    }
}

