package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Set;

/**
 * PageRouterServlet.java
 * This servlet handles routing for the web application, forwarding requests to the appropriate HTML pages.
 * @methods  doGet
 */
@WebServlet("/router/*")
public class PageRouterServlet extends HttpServlet {

    // Valid page names
    private static final Set<String> allowedPages = Set.of(
        "dashboard", "home", "event_creation", "help", "learn_more",
        "login", "signup", "edit_profile", "user_account", "search", "explore"
    );

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String path = request.getPathInfo(); // e.g. "/home"
        if (path == null || path.equals("/")) {
            path = "/home"; // default to home
        }

        String page = path.substring(1); // "home"

        if (allowedPages.contains(page)) {
            // Forward to actual HTML file from src/main/webapp/pages/
            String filePath = "/pages/" + page + ".html";
            RequestDispatcher dispatcher = request.getRequestDispatcher(filePath);
            dispatcher.forward(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Page not found: " + page);
        }
    }
}
