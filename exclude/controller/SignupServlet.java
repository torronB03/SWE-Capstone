package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import utils.DatabaseConnection;
import models.User;
import org.mindrot.jbcrypt.BCrypt;

@WebServlet("/SignupServlet")
public class SignupServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        String passwordHash = BCrypt.hashpw(password, BCrypt.gensalt());

        try (Connection conn = DatabaseConnection.initializeDatabase()) {
            String query = "INSERT INTO users (email, password) VALUES (?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, email);
                stmt.setString(2, passwordHash);
                stmt.executeUpdate();
            }

            User newUser = new User();
            newUser.setEmail(email);
            newUser.setPasswordHash(passwordHash);

            JSONObject json = new JSONObject();
            json.put("success", true);
            json.put("message", "Signup successful for " + newUser.getEmail() + "!");
            response.getWriter().print(json.toString());

        } catch (SQLException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            JSONObject error = new JSONObject();
            error.put("success", false);
            error.put("error", "Signup failed: " + e.getMessage());
            response.getWriter().print(error.toString());
        }
    }
}

