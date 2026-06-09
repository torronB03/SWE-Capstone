package dao;

import models.SessionConnection;
import models.User;

import java.sql.*;
import java.util.logging.*;

/**
* @author Abernard13
* Contributers: yellol (Alex), JFernandez03 (June), Miklian, Jdemery110
* DOC last edited: 05/08 @ 1:00AM
* UserDAO is a data-access object that interfaces the java model class for 
* UserDAO with its database table.
* This is allows for the verification of the user and saving of the user.
* @attributes  sessionConnection
* @methods  SESSION:  UserDAO, ResultSetHandler
* @methods  FETCH:  findUser (x2)
* @methods  MODIFY:  insertUser, updateUser, convertToModel
*/
public class UserDAO {

    private Connection sessionConnection;

    // when creating a DAO, take the user's connection and use it!
    // this also means this DAO requires a user's connection to use.
    // perhaps, a temporary session can be used for getting events etc.
    /**
    * Constructor for UserDAO
    * @param connObject the session connection object containing the database connection
    */
    public UserDAO(SessionConnection connObject)
    {
        this.sessionConnection = connObject.getConnection();
    }

    
    // Find user by email
    /**
    * @param email the email of the user to find
    * @return 'findUser'
    */
    public User findUser(String email) {

        // validate session
        if (sessionConnection == null) { return null; }

        User user = null;
        String query = "select * from \"ENDUSER\" where email_linked = ?";

        try (PreparedStatement stmt = sessionConnection.prepareStatement(query)) {

            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                user = convertToModel(rs);
            }

        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            e.printStackTrace();
        }

        return user;
    }
    
    // Find user by id
    /**
    * @param id the id of the user to find
    * @return 'findUser'
    */
    public User findUser(int id) {
        User user = null;
        String query = "select * from \"ENDUSER\" where user_data_index = ?";

        try (PreparedStatement stmt = sessionConnection.prepareStatement(query)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                user = convertToModel(rs);
            }

        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            e.printStackTrace();
        }

        return user;
    }

    
    // Insert new user
    // NOTE: try-with-resources will automatically close a connection thats supplied to it
    /**
    * @param user the user to insert
    * @return 'insertUser'
    */
    public boolean insertUser(User user) {
        String query = "insert into \"ENDUSER\" (first_name, last_name, username, email_linked, password) values (?, ?, ?, ?, ?)";

        Logger daoLog = Logger.getLogger("UserDAO");
        daoLog.warning("tapping into the database...");
        // email check
        if (findUser(user.getUserEmail()) != null) { 
            daoLog.warning("duplicate email found.");
            return false; }
        try (PreparedStatement stmt = sessionConnection.prepareStatement(query)) {

            stmt.setString(1, user.getFirstName());
            stmt.setString(2, user.getLastName());
            stmt.setString(3, user.getUsername());
            stmt.setString(4, user.getUserEmail());
            stmt.setString(5, user.getUserPasswordHash());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            e.printStackTrace();
        }

        return false;
    }

    
    // Update existing user profile
    /**
    * @param newData the new data for the user
    * @param userId the id of the user to update
    * @return 'updateUser'
    */
    public boolean updateUser(User newData, int userId) {
        String sql = "UPDATE \"ENDUSER\" SET email_linked = ?, password = ?, username = ?, first_name = ?, last_name = ? WHERE user_data_index = ?";

        try (PreparedStatement stmt = sessionConnection.prepareStatement(sql)) {

            stmt.setString(1, newData.getUserEmail());
            stmt.setString(2, newData.getUserPasswordHash());
            stmt.setString(3, newData.getUsername());
            stmt.setString(4, newData.getFirstName());
            stmt.setString(5, newData.getLastName());
            stmt.setInt(6, userId);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Database error during update: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    
    // Convert result to User model
    /**
    * @param entry the ResultSet entry to convert
    * @return 'convertToModel'
    */
    private static User convertToModel(ResultSet entry) throws SQLException {
        User model = null;
        if (entry.next()) {
            model = new User("", "");
            model.setUserId(entry.getInt("user_data_index"));
            model.setUserEmail(entry.getString("email_linked"));
            model.setUsername(entry.getString("username"));
            model.setUserPasswordHash(entry.getString("password"));
            model.setFirstName(entry.getString("first_name"));
            model.setLastName(entry.getString("last_name"));
            model.setOver18(true);
            model.setTransport("Car");
        }
        return model;
    }
}
