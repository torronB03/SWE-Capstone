package dao;

import models.FoodBank;
import models.SessionConnection;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
* @author Abernard13
* Contributers: yellol (Alex), robutlerr (Amari Butler), JFernandez03 (June), atclark123 (Amore)
* DOC last edited: 05/08 @ 5:14PM
* FoodBankDAO is a data-access object that interfaces the java model class for 
* FoodBank with its database table.
* This is a location-based data structure; does not allow for internal modifications, but includes
* functionality for saving to a user's profile.
* @attributes  sessionConnection
* @methods  SESSION:  FoodBankDAO
* @methods  OUTPUT:  getAllFoodBanks, getFoodBankById
* @methods  MODIFICATIONS:  saveResourceForUser
*/
public class FoodBankDAO {

    private Connection sessionConnection;

    /**
    *Constructor for FoodBankDAO
    * @param connObject  SessionConnection object to establish a connection to the database
    */
    public FoodBankDAO(SessionConnection connObject) {
        this.sessionConnection = connObject.getConnection();
    }

    /**
    * @return 'getAllFoodBanks'
    */
    public List<FoodBank> getAllFoodBanks() {
        List<FoodBank> foodBanks = new ArrayList<>();
        String query = "SELECT * FROM swegrg25.\"Food_Banks\"";

        try (PreparedStatement stmt = sessionConnection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                FoodBank fb = new FoodBank();
                fb.setFbDataIndex(rs.getLong("fb_data_index"));
                fb.setFbDataPk(rs.getString("fb_data_pk"));
                fb.setOrgName(rs.getString("org_name"));
                fb.setOrgDataIndex(rs.getLong("org_data_index"));
                fb.setUserDataIndex(rs.getLong("user_data_index"));
                fb.setAccommodationLimit(rs.getInt("accommodation_limit"));
                fb.setHoursOfOperation(rs.getString("hours_of_operation")); // JSON string
                fb.setCoordinateLocation(rs.getString("coordinate_location"));
                fb.setAddress(rs.getString("address"));
                fb.setContactInfo(rs.getString("contact_info"));
                fb.setLocationNotes(rs.getString("location_notes"));
                fb.setPermanent(rs.getString("permanent"));
                foodBanks.add(fb);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return foodBanks;
    }

    /**
    * @param foodBankId  The ID of the food bank to retrieve
    * @return 'getFoodBankById'
    */
    public FoodBank getFoodBankById(long foodBankId) {
        String query = "SELECT * FROM swegrg25.\"Food_Banks\" WHERE fb_data_index = ?";

        try (PreparedStatement stmt = sessionConnection.prepareStatement(query)) {
            stmt.setLong(1, foodBankId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    FoodBank fb = new FoodBank();
                    fb.setFbDataIndex(rs.getLong("fb_data_index"));
                    fb.setFbDataPk(rs.getString("fb_data_pk"));
                    fb.setOrgName(rs.getString("org_name"));
                    fb.setOrgDataIndex(rs.getLong("org_data_index"));
                    fb.setUserDataIndex(rs.getLong("user_data_index"));
                    fb.setAccommodationLimit(rs.getInt("accommodation_limit"));
                    fb.setHoursOfOperation(rs.getString("hours_of_operation"));
                    fb.setCoordinateLocation(rs.getString("coordinate_location"));
                    fb.setAddress(rs.getString("address"));
                    fb.setContactInfo(rs.getString("contact_info"));
                    fb.setLocationNotes(rs.getString("location_notes"));
                    fb.setPermanent(rs.getString("permanent"));
                    return fb;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace(); 
        }

        return null;
    }

    /**
    * @param userId  The ID of the user
    * @param resourceDataPk  The primary key of the resource data (e.g., "foba001") 
    * @param userCategory  The category of the resource (e.g., "food_bank")
    * @return 'saveResourceForUser'
    */
    public boolean saveResourceForUser(long userId, String resourceDataPk, String userCategory) {
        String query = "INSERT INTO swegrg25.\"Save_Resource\" (user_data_index, resource_data_pk, user_category) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = sessionConnection.prepareStatement(query)) {
            stmt.setLong(1, userId);
            stmt.setString(2, resourceDataPk);  // e.g., "foba001"
            stmt.setString(3, userCategory);    // e.g., "food_bank"
            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            System.err.println("Error saving resource for user: " + e.getMessage());
            return false;
        }
    }
}
