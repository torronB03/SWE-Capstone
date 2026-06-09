package controller;

import dao.UserDAO;
import models.User;
import org.json.JSONObject;
import org.mindrot.jbcrypt.BCrypt;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    private final UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        response.getWriter().write("<h1>LoginServlet is running!</h1><p>Use POST to log in.</p>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        JSONObject jsonResponse = new JSONObject();

        String action = request.getParameter("action");

        if ("updateProfile".equals(action)) {
            handleProfileUpdate(request, response, jsonResponse);
        } else {
            handleLogin(request, response, jsonResponse);
        }
    }

    private void handleLogin(HttpServletRequest request, HttpServletResponse response, JSONObject jsonResponse)
            throws IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (email == null || password == null) {
            jsonResponse.put("success", false);
            jsonResponse.put("message", "Missing email or password.");
            response.getWriter().write(jsonResponse.toString());
            return;
        }

        User user = userDAO.findUser(email);

        if (user != null && BCrypt.checkpw(password, user.getUserPasswordHash())) {
            HttpSession session = request.getSession();
            session.setAttribute("userId", user.getUserId());
            session.setAttribute("email", email);

            jsonResponse.put("success", true);
            jsonResponse.put("message", "Login successful!");
        } else {
            jsonResponse.put("success", false);
            jsonResponse.put("message", "Invalid credentials.");
        }

        response.getWriter().write(jsonResponse.toString());
    }

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
}
