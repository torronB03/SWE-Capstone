package controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/*")
public class RedirectServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String path = request.getPathInfo();

        if (path == null || path.equals("/")) {
            response.sendRedirect("pages/home.html");
        } else {
            String file = path.replaceAll("^/", "");  // remove leading slash
            response.sendRedirect("pages/" + file + ".html");
        }
    }
}
