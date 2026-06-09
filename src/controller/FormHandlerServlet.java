package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
* FormHandlerServlet is a servlet that generally handles forms from across the website
* @methods  INPUT: doPost, handleSignup, handleLog, handleEventCreation, handleVolunteerCreation
*/

@WebServlet("/form-handler")
public class FormHandlerServlet extends HttpServlet {

    /**
     * @param request A request for the servlet
     * @param response the servlet's response
     * When getting a form, discerns its type and sends its request and response to an appropriate function for the form type
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String formType = request.getParameter("formType");

        switch (formType) {
            case "signup":
                handleSignup(request, response);
                break;
            case "event":
                handleEventCreation(request, response);
                break;
            case "volunteer":
                handleVolunteerCreation(request, response);
                break;
            case "login":
                handleLogin(request, response);
                break;
            default:
                response.sendRedirect("../pages/error.html");
        }
    }

    /**
     * @param request A request for the servlet
     * @param response the servlet's response
     * Receives the request and response from doPost, then attains the email and password from the signup form,
     * prints the signup email, and redirects to the signup_success page
     */
    private void handleSignup(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        // TODO: Use UserDAO to store user
        System.out.println("Signup email: " + email);
        response.sendRedirect("../pages/signup_success.html");
    }

    /**
     * @param request A request for the servlet
     * @param response the servlet's response
     * Receives the request and response from doPost, then attains the email and password from the login form,
     * prints the login attempt with email, and redirects to the dashboard page
     */
    private void handleLogin(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        // TODO: Validate using UserDAO
        System.out.println("Login attempt: " + email);
        response.sendRedirect("../pages/dashboard.html");
    }

    /**
     * @param request A request for the servlet
     * @param response the servlet's response
     * Receives the request and response from doPost, then attains the event name and date from the event creation form,
     * prints the event name and date, and redirects to the event created page
     */
    private void handleEventCreation(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String eventName = request.getParameter("eventName");
        String date = request.getParameter("eventDate");
        System.out.println("New event: " + eventName + " on " + date);
        // TODO: Use EventDAO to store event
        response.sendRedirect("../pages/event_created.html");
    }

    /**
     * @param request A request for the servlet
     * @param response the servlet's response
     * Receives the request and response from doPost, then attains the volunteer event name, date, time, and description from the volunteer event creation form,
     * prints the opportunity name, time, and date, and redirects to the volunteer created page
     */
    private void handleVolunteerCreation(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String opportunityName = request.getParameter("opportunityName");
        String date = request.getParameter("date");
        String time = request.getParameter("time");
        String description = request.getParameter("description");

        System.out.println("Volunteer opportunity: " + opportunityName + " on " + date + " at " + time);
        // TODO: Use VolunteerDAO to store opportunity
        response.sendRedirect("../pages/volunteer_created.html");
    }
}
