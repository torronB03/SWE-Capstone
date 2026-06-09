package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.json.JSONObject;

import java.io.IOException;

@WebServlet("/SessionCheckServlet")
public class SessionCheckServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("application/json");
        JSONObject json = new JSONObject();
        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("userId") != null) {
            json.put("loggedIn", true);
            json.put("userId", session.getAttribute("userId")); // Optional
        } else {
            json.put("loggedIn", false);
        }
        
        response.getWriter().write(json.toString());
    }
}
