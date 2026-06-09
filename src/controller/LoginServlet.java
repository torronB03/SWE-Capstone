package controller;

import dao.UserDAO;
import models.SessionConnection;
import models.User;
import org.json.JSONObject;
import org.mindrot.jbcrypt.BCrypt;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * LoginServlet.java
 * This servlet handles user login requests and session management.
 * @attributes svltLog
 * @methods  handleLogin
 */
@WebServlet("/user-api/login")
public class LoginServlet extends HttpServlet {
    /**
     * Logger for this servlet
     */
    Logger svltLog = Logger.getLogger (this.getClass().getName() + "Servlet");
    
    /**
     * @param request A request for the servlet
     * @param response the servlet's response
     * gets a request and response, changes the response to json, 
     * makes an object of type JSONObject corresponding to the response, and sends this and the request and response to HandleLogin()
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        JSONObject jsonResponse = new JSONObject();

        //String action = request.getParameter("action");

        handleLogin(request, response, jsonResponse);
        /* 
        if ("updateProfile".equals(action)) {
            handleProfileUpdate(request, response, jsonResponse);
        } else {
            handleLogin(request, response, jsonResponse);
        }
        */
    }

    // class used to hold values for gson to fill
    private class LoginInfo {
        String email;
        String password;
    }

    /**
     * @param request A request for the servlet
     * @param response the servlet's response
     * @param jsonResponse the servlet's response in json form
     * gets a request, response, and json response, gets an email from the request and finds a corresponding user,
     * then matches the inputted password with that user's password and logs them in if there's a match
     */
    private void handleLogin(HttpServletRequest request, HttpServletResponse response, JSONObject jsonResponse)
            throws IOException {

        BufferedReader reader = request.getReader();

        // get input from request, fill LoginInfo object with that information
        Gson gson = new Gson();
        LoginInfo newInfo = gson.fromJson(reader, LoginInfo.class);

        HttpSession userSession = request.getSession(true);

        // empty credentails validation
        if (newInfo.email == null || newInfo.password == null) {
            jsonResponse.put("success", false);
            jsonResponse.put("message", "Missing email or password.");
            response.getWriter().write(jsonResponse.toString());
            return;
        }

        // create a database connection if it does not exist yet
        if (userSession.getAttribute("Connection") == null) 
        {
            svltLog.warning ("Creating connection for user...");
            SessionConnection.createConnectionforSession(userSession, request.getRemoteAddr());
        }

        // create userDAO that uses connection in session
        UserDAO userDAO = new UserDAO((SessionConnection)userSession.getAttribute("Connection"));

        svltLog.warning ("finding this user with the email " + newInfo.email);

        // get matching user's info
        User user = userDAO.findUser(newInfo.email);

        // if user exists and password matches with the session.
        if (user != null && BCrypt.checkpw(newInfo.password, user.getUserPasswordHash())) {
            jsonResponse.put("success", true);
            jsonResponse.put("message", "Login successful!");

            // put user's data into session info, though do not store the password lol
            if (userSession.getAttribute("UserData") == null) 
            {
                svltLog.warning ("adding userdata to session... ");
                user.setUserPasswordHash("");
                userSession.setAttribute("UserData", user);

                // logged in flag for easier access on js
                Cookie loggedInFlag = new Cookie("loggedIn", "true");
                loggedInFlag.setMaxAge(3600); 
                loggedInFlag.setPath("/"); 
                response.addCookie(loggedInFlag);
                
            }
        } else {
            jsonResponse.put("success", false);
            jsonResponse.put("message", "Invalid credentials.");
        }

        response.getWriter().write(jsonResponse.toString());
    }

    /* 
    private void handleProfileUpdate(HttpServletRequest request, HttpServletResponse response, JSONObject jsonResponse)
            throws IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            jsonResponse.put("success", false);
            jsonResponse.put("message", "Session expired. Please log in again.");
            response.getWriter().write(jsonResponse.toString());
            return;
        }

        int userId = (int) session.getAttribute("userId");

        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String username = request.getParameter("username");
        String currentPassword = request.getParameter("currentPassword");
        String newPassword = request.getParameter("newPassword");
        String confirmNewPassword = request.getParameter("confirmNewPassword");
        String over18 = request.getParameter("over18");
        String transport = request.getParameter("transport");

        email = (email != null) ? email : "";
        name = (name != null) ? name : "";
        username = (username != null) ? username : "";
        transport = (transport != null) ? transport : "";
        currentPassword = (currentPassword != null) ? currentPassword.trim() : "";
        newPassword = (newPassword != null) ? newPassword.trim() : "";
        confirmNewPassword = (confirmNewPassword != null) ? confirmNewPassword.trim() : "";

        User user = userDAO.findUser(userId);

        if (user == null) {
            jsonResponse.put("success", false);
            jsonResponse.put("message", "User not found.");
            response.getWriter().write(jsonResponse.toString());
            return;
        }

        if (!newPassword.isEmpty()) {
            if (!BCrypt.checkpw(currentPassword, user.getUserPasswordHash())) {
                jsonResponse.put("success", false);
                jsonResponse.put("message", "Current password is incorrect.");
                response.getWriter().write(jsonResponse.toString());
                return;
            }

            if (!newPassword.equals(confirmNewPassword)) {
                jsonResponse.put("success", false);
                jsonResponse.put("message", "New passwords do not match.");
                response.getWriter().write(jsonResponse.toString());
                return;
            }

            if (newPassword.length() < 6) {
                jsonResponse.put("success", false);
                jsonResponse.put("message", "Password must be at least 6 characters.");
                response.getWriter().write(jsonResponse.toString());
                return;
            }

            String newHashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());
            user.setUserPasswordHash(newHashedPassword);
        }

        user.setUserEmail(email);
        user.setName(name);
        user.setUsername(username);
        user.setOver18("true".equals(over18));
        user.setTransport(transport);

        boolean updateSuccess = userDAO.updateUser(user);

        if (updateSuccess) {
            jsonResponse.put("success", true);
            jsonResponse.put("message", "Profile updated successfully!");
        } else {
            jsonResponse.put("success", false);
            jsonResponse.put("message", "Failed to update profile.");
        }

        response.getWriter().write(jsonResponse.toString());
    }
        */
}
