package dao;

import models.CommunityCenter;
import models.SessionConnection;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
* @author Abernard13
* Contributers: yellol (Alex), robutlerr (Amari Butler), JFernandez03 (June), atclark123 (Amore)
* DOC last edited: 05/08 @ 3:35AM
* CommunityCenterDAO is a data-access object that interfaces the java model class for 
* CommunityCenter with its database table.
* This is a location-based data structure; does not allow for internal modifications, but includes
* functionality for saving to a user's profile.
* @attributes  sessionConnection
* @methods  SESSION:  CommunityCenterDAO
* @methods  OUTPUT:  getAllCommunityCenters, getCommunityCenter, parseJsonArray
* @methods  MODIFICATIONS:  saveResourceForUser
*/
public class CommunityCenterDAO {

    private Connection sessionConnection;

    /**
    * Constructor for CommunityCenterDAO
    * @param connObject  SessionConnection object to establish a connection to the database
    */
    public CommunityCenterDAO(SessionConnection connObject) {
        this.sessionConnection = connObject.getConnection();
    }

    /**
    * @return 'getAllCommunityCenters'
    */
    public List<CommunityCenter> getAllCommunityCenters() {
        List<CommunityCenter> centers = new ArrayList<>();
        String query = "SELECT * FROM swegrg25.\"Community_Centers\"";
    
        try (PreparedStatement stmt = sessionConnection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
    
            while (rs.next()) {
                CommunityCenter center = new CommunityCenter();
                center.setCcDataPk(rs.getString("cc_data_pk")); // <- added
                center.setCcDataIndex(rs.getLong("cc_data_index"));
                center.setOrgName(rs.getString("org_name"));
                center.setOrgDataIndex(rs.getLong("org_data_index"));
                center.setUserDataIndex(rs.getLong("user_data_index"));
    
                center.setHoursOfOperation(parseJsonArray(rs.getString("hours_of_operation")));
                center.setAddress(rs.getString("address"));
                center.setContactInfo(rs.getString("contact_info"));
                center.setCoordinateLocation(rs.getString("coordinate_location"));
                center.setOfferedServices(rs.getString("offered_services"));
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
    * @param centerId  The ID of the community center to retrieve
    * @return 'getCommunityCenter'
    */
    public CommunityCenter getCommunityCenter(long centerId) {
        String query = "SELECT * FROM swegrg25.\"Community_Centers\" WHERE cc_data_index = ?";
    
        try (PreparedStatement stmt = sessionConnection.prepareStatement(query)) {
            stmt.setLong(1, centerId);
    
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    CommunityCenter center = new CommunityCenter();
                    center.setCcDataPk(rs.getString("cc_data_pk")); // <- added
                    center.setCcDataIndex(rs.getLong("cc_data_index"));
                    center.setOrgName(rs.getString("org_name"));
                    center.setOrgDataIndex(rs.getLong("org_data_index"));
                    center.setUserDataIndex(rs.getLong("user_data_index"));
    
                    center.setHoursOfOperation(parseJsonArray(rs.getString("hours_of_operation")));
                    center.setAddress(rs.getString("address"));
                    center.setContactInfo(rs.getString("contact_info"));
                    center.setCoordinateLocation(rs.getString("coordinate_location"));
                    center.setOfferedServices(rs.getString("offered_services"));
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
    * @param userId  The ID of the user saving the resource
    * @param resourceDataPk  The primary key of the resource being saved (e.g., "cc0001")
    * @param userCategory  The category of the user (e.g., "community_center")
    * @return 'saveResourceForUser'
    */
    public boolean saveResourceForUser(long userId, String resourceDataPk, String userCategory) {
        String query = "INSERT INTO swegrg25.\"Save_Resource\" (user_data_index, resource_data_pk, user_category) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = sessionConnection.prepareStatement(query)) {
            stmt.setLong(1, userId);
            stmt.setString(2, resourceDataPk);  // e.g., "cc0001"
            stmt.setString(3, userCategory);    // e.g., "community_center"

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            System.err.println("Error saving resource for user: " + e.getMessage());
            return false;
        }
    }

    // Utility: Parses a JSON array string into a Java List<String>
    /**
    * @param jsonArrayStr  The JSON array string to parse (e.g., "[\"item1\", \"item2\"]")
    * @return 'parseJsonArray'
    */
    private List<String> parseJsonArray(String jsonArrayStr) {
        List<String> list = new ArrayList<>();
        if (jsonArrayStr != null && !jsonArrayStr.isEmpty()) {
            jsonArrayStr = jsonArrayStr.replace("[", "").replace("]", "").replace("\"", "");
            for (String item : jsonArrayStr.split(",")) {
                if (!item.trim().isEmpty()) {
                    list.add(item.trim());
                }
            }
        }
        return list;
    }
}
