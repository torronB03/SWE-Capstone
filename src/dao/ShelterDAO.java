package dao;

import models.Shelter;
import models.SessionConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
* @author Abernard13
* Contributers: yellol (Alex), robutlerr (Amari Butler), JFernandez03 (June), atclark123 (Amore)
* DOC last edited: 05/08 @ 3:41AM
* ShelterDAO is a data-access object that interfaces the java model class for 
* Shelter with its database table.
* This is a location-based data structure; does not allow for internal modifications, but includes
* functionality for saving to a user's profile.
* @attributes  sessionConnection
* @methods  SESSION:  ShelterDAO
* @methods  OUTPUT:  getAllShelters, getShelterById
* @methods  MODIFICATIONS:  saveResourceForUser
*/
public class ShelterDAO {

    private Connection sessionConnection;

    /**
    * Constructor for ShelterDAO
    * @param connObject  SessionConnection object to establish a connection to the database
    */
    public ShelterDAO(SessionConnection connObject) {
        this.sessionConnection = connObject.getConnection();
    }

    /**
    * @return 'getAllShelters'
    */
    public List<Shelter> getAllShelters() {
        List<Shelter> shelters = new ArrayList<>();
        String query = "SELECT * FROM swegrg25.\"Shelters\"";

        try (PreparedStatement stmt = sessionConnection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Shelter sh = new Shelter();
                sh.setShDataPk(rs.getString("sh_data_pk"));
                sh.setShDataIndex(rs.getLong("sh_data_index"));
                sh.setOrgName(rs.getString("org_name"));
                sh.setOrgDataIndex(rs.getLong("org_data_index"));
                sh.setUserDataIndex(rs.getLong("user_data_index"));
                sh.setSpaceAvailable(rs.getInt("space_available"));
                sh.setMaximumCapacity(rs.getInt("maximum_capacity"));
                sh.setHoursOfOperation(rs.getString("hours_of_operation"));
                sh.setAddress(rs.getString("address"));
                sh.setContactInfo(rs.getString("contact_info"));
                sh.setCoordinateLocation(rs.getString("coordinate_location"));
                sh.setLocationNotes(rs.getString("location_notes"));
                sh.setPermanent(rs.getString("permanent"));
                shelters.add(sh);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return shelters;
    }

    /**
    * @param shelterId The ID of the shelter to retrieve
    * @return 'getShelterById'
    */
    public Shelter getShelterById(long shelterId) {
        String query = "SELECT * FROM swegrg25.\"Shelters\" WHERE sh_data_index = ?";

        try (PreparedStatement stmt = sessionConnection.prepareStatement(query)) {
            stmt.setLong(1, shelterId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Shelter sh = new Shelter();
                    sh.setShDataPk(rs.getString("sh_data_pk"));
                    sh.setShDataIndex(rs.getLong("sh_data_index"));
                    sh.setOrgName(rs.getString("org_name"));
                    sh.setOrgDataIndex(rs.getLong("org_data_index"));
                    sh.setUserDataIndex(rs.getLong("user_data_index"));
                    sh.setSpaceAvailable(rs.getInt("space_available"));
                    sh.setMaximumCapacity(rs.getInt("maximum_capacity"));
                    sh.setHoursOfOperation(rs.getString("hours_of_operation"));
                    sh.setAddress(rs.getString("address"));
                    sh.setContactInfo(rs.getString("contact_info"));
                    sh.setCoordinateLocation(rs.getString("coordinate_location"));
                    sh.setLocationNotes(rs.getString("location_notes"));
                    sh.setPermanent(rs.getString("permanent"));
                    return sh;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
    * @param userId  The ID of the user to save the resource for
    * @param resourceDataPk  The primary key of the resource data
    * @param userCategory  The category of the resource (e.g., "shelter")
    * @return 'saveResourceForUser'
    */
    public boolean saveResourceForUser(long userId, String resourceDataPk, String userCategory) {
        String query = "INSERT INTO swegrg25.\"Save_Resource\" (user_data_index, resource_data_pk, user_category) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = sessionConnection.prepareStatement(query)) {
            stmt.setLong(1, userId);
            stmt.setString(2, resourceDataPk);
            stmt.setString(3, userCategory);

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            System.err.println("Error saving shelter for user: " + e.getMessage());
            return false;
        }
    }
}
