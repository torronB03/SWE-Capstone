package controller;

import models.SessionConnection;
import models.User;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.mindrot.jbcrypt.BCrypt;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.logging.Logger;

/**
* EditProfileServlet is a servlet that handles editing the profile using data supplied from the edit_profile pages
* @attributes svltLog
* @methods  OUTPUT:  compareUserFields, doPost
* @methods  INPUT:  doGet
*/

@WebServlet("/user-api/edit-profile")
public class EditProfileServlet extends HttpServlet {
    /**
     * Logger for this servlet
     */
    Logger svltLog = Logger.getLogger(this.getClass().getName() + "Servlet");

    /**
     * @param input User with input values from edit profile page
     * @param current Currently logged-in user, for updating
     * @return returns User with updated data cumulative from supplied parameters
     */
    private User compareUserFields(User input, User current) {
        // null checks, if the fields are left blank
        User result = new User(current.getUserId(), current.getUserEmail(), current.getUserPasswordHash(),
                current.getFirstName(), current.getLastName(), current.getUsername(), current.isOver18(),
                current.getTransport());

        if (input.getFirstName() != null && !input.getFirstName().trim().isEmpty()) {
            result.setFirstName(input.getFirstName());
        }

        if (input.getLastName() != null && !input.getLastName().trim().isEmpty()) {
            result.setLastName(input.getLastName());
        }

        if (input.getUserPasswordHash() != null && !input.getUserPasswordHash().trim().isEmpty()) {
            String passwordHash = BCrypt.hashpw(input.getUserPasswordHash(), BCrypt.gensalt());
            result.setUserPasswordHash(passwordHash);
        }

        if (input.getUserEmail() != null && !input.getUserEmail().trim().isEmpty()) {
            result.setUserEmail(input.getUserEmail());
        }

        if (input.getUsername() != null && !input.getUsername().trim().isEmpty()) {
            result.setUsername(input.getUsername());
        }

        if (input.getTransport() != null && !input.getTransport().trim().isEmpty()) {
            result.setTransport(input.getTransport());
        }

        return result;
    }

    /**
     * @param request A request for the servlet
     * @param response the servlet's response
     * calls doPost() with the parameters provided
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * @param request A request for the servlet
     * @param response the servlet's response
     * gets parameters from doGet() and parses them for account information updating
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        JSONObject jsonResponse = new JSONObject();

        svltLog.warning("Request received.");

        // reader cannot be used twice (for gson and jsonparser), so we need the body
        // itself
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        String requestBody = sb.toString();

        svltLog.warning("Request body: " + requestBody);

        // we'll most likely receive user's input as json
        final Gson gson = new Gson();
        User inputData = gson.fromJson(requestBody, User.class);
        JsonObject parseExtraData = JsonParser.parseString(requestBody).getAsJsonObject();
        String oldPassword = parseExtraData.get("currentPassword").getAsString();
        String confirmNewPassword = parseExtraData.get("confirmPassword").getAsString();

        svltLog.warning("Attempting to edit profile..");

        // get session data, so we can get current user's id
        HttpSession userSession = request.getSession(true);
        Object loginData = userSession.getAttribute("UserData");
        if (loginData != null && loginData instanceof User) {

            svltLog.warning("User is logged in.");

            // connection check, not instantiated if not logged in
            Object connCheck = userSession.getAttribute("Connection");
            if (connCheck == null || connCheck instanceof SessionConnection) {
                SessionConnection.createConnectionforSession(userSession, request.getRemoteAddr());
                connCheck = userSession.getAttribute("Connection"); // refetch
            }

            SessionConnection userConnection = (SessionConnection) connCheck;
            UserDAO userDAO = new UserDAO(userConnection);
            User sessionData = (User) loginData; // get currently logged in
            User currentUser = userDAO.findUser(sessionData.getUserEmail());

            // password checkz
            // if confirmed password isnt the same as your new one
            if ((!confirmNewPassword.trim().equals(inputData.getUserPasswordHash().trim()))) {
                svltLog.warning("Confirmed password does not match..." + confirmNewPassword + " is not the same as "
                        + inputData.getUserPasswordHash() + ".");
                jsonResponse.put("message", "Confirmed password does not match.");
                response.getWriter().write(jsonResponse.toString());
                return;
            }

            // if the old password doesn't match the one in the database
            if (!BCrypt.checkpw(oldPassword, currentUser.getUserPasswordHash())) {
                svltLog.warning("Confirmed password does not match..");

                if (inputData.getUserPasswordHash().trim().isEmpty()) {
                    jsonResponse.put("message", "Please enter your current password to change your desired details.");
                } else {
                    jsonResponse.put("message", "Old password does not match.");
                }

                response.getWriter().write(jsonResponse.toString());
                return;
            }

            svltLog.warning("Password checks completed.");

            // do some validation of input data
            User validatedUser = compareUserFields(inputData, currentUser);

            userDAO.updateUser(validatedUser, currentUser.getUserId());

            svltLog.warning("Updated " + currentUser.getUserEmail() + "'s info.");
            jsonResponse.put("message", "Changed profile details as necessary.");

            // change session's userdata to updated data
            svltLog.warning ("adding userdata to session... ");
            validatedUser.setUserPasswordHash("");
            userSession.setAttribute("UserData", validatedUser);

            jsonResponse.put("success", true);
            response.getWriter().write(jsonResponse.toString());
            return;
        }

        jsonResponse.put("message", "You are not logged in.");
        response.getWriter().write(jsonResponse.toString());
    }

}
