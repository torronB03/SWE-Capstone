package dao;

import models.MedicalCenter;
import models.SessionConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
* @author Abernard13
* Contributers: yellol (Alex), robutlerr (Amari Butler), JFernandez03 (June), atclark123 (Amore)
* DOC last edited: 05/08 @ 3:38AM
* MedicalCenterDAO is a data-access object that interfaces the java model class for 
* MedicalCenter with its database table.
* This is a location-based data structure; does not allow for internal modifications, but includes
* functionality for saving to a user's profile.
* @attributes  sessionConnection
* @methods  SESSION:  MedicalCenterDAO
* @methods  OUTPUT:  getAllMedicalCenters, getMedicalCenter
* @methods  MODIFICATIONS:  saveResourceForUser
*/
public class MedicalCenterDAO {

    private Connection sessionConnection;

    /**
     * Constructor for MedicalCenterDAO
     * @param connObject  SessionConnection object to establish a connection to the database
     */
    public MedicalCenterDAO(SessionConnection connObject) {
        this.sessionConnection = connObject.getConnection();
    }

    /**
     * Retrieves all medical centers from the database
     * @return List of MedicalCenter objects
     */
    public List<MedicalCenter> getAllMedicalCenters() {
        List<MedicalCenter> centers = new ArrayList<>();
        String query = "SELECT * FROM \"Med_Centers\"";

        try (PreparedStatement stmt = sessionConnection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                MedicalCenter center = new MedicalCenter();
                center.setMedDataPk(rs.getString("med_data_pk"));
                center.setMedDataIndex(rs.getLong("med_data_index"));
                center.setOrgName(rs.getString("org_name"));
                center.setOrgDataIndex(rs.getLong("org_data_index"));
                center.setUserDataIndex(rs.getLong("user_data_index"));
                center.setAverageWaitTime(rs.getInt("average_wait_time"));
                center.setAddress(rs.getString("address"));
                center.setContactInfo(rs.getString("contact_info"));
                center.setServicesProvided(rs.getString("services_provided"));
                center.setCoordinateLocation(rs.getString("coordinate_location"));
                center.setLocationNotes(rs.getString("location_notes"));
                center.setPermanent(rs.getString("permanent"));
                centers.add(center);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return centers;
    }

    /**
    * @param medDataIndex  The index of the medical center to retrieve
    * @return MedicalCenter object containing details of the medical center
    */
    public MedicalCenter getMedicalCenter(long medDataIndex) {
        String query = "SELECT * FROM \"Med_Centers\" WHERE med_data_index = ?";

        try (PreparedStatement stmt = sessionConnection.prepareStatement(query)) {
            stmt.setLong(1, medDataIndex);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    MedicalCenter center = new MedicalCenter();
                    center.setMedDataPk(rs.getString("med_data_pk"));
                    center.setMedDataIndex(rs.getLong("med_data_index"));
                    center.setOrgName(rs.getString("org_name"));
                    center.setOrgDataIndex(rs.getLong("org_data_index"));
                    center.setUserDataIndex(rs.getLong("user_data_index"));
                    center.setAverageWaitTime(rs.getInt("average_wait_time"));
                    center.setAddress(rs.getString("address"));
                    center.setContactInfo(rs.getString("contact_info"));
                    center.setServicesProvided(rs.getString("services_provided"));
                    center.setCoordinateLocation(rs.getString("coordinate_location"));
                    center.setLocationNotes(rs.getString("location_notes"));
                    center.setPermanent(rs.getString("permanent"));
                    return center;
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
    * @param userCategory  The category of the resource (e.g., "medical_center")
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
            System.err.println("Error saving medical center for user: " + e.getMessage());
            return false;
        }
    }
}
