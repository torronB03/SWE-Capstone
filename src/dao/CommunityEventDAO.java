package dao;

import models.CommunityEvent;
import models.SessionConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
* @author Abernard13
* Contributers: yellol (Alex), robutlerr (Amari Butler), JFernandez03 (June), atclark123 (Amore)
* DOC last edited: 05/08 @ 3:30AM
* CommunityEventDAO is a data-access object that interfaces the java model class for 
* CommunityEvents with its database table.
* Allows for inserts and modifications
* @attributes  sessionConnection
* @methods  SESSION:  CommunityEventDAO
* @methods  OUTPUT:  getCommunityEventById, getAllCommunityEvents, extractCommunityEventFromResultSet
* @methods  MODIFICATIONS:  insertCommunityEvent, saveCommunityEventForUser
*/
public class CommunityEventDAO {

    private Connection sessionConnection;

    /**
     * Constructor for CommunityEventDAO
     * @param connObject connection object to the database
     */
    public CommunityEventDAO(SessionConnection connObject)
    {
        this.sessionConnection = connObject.getConnection();
    }

    // Insert a new Community Event
    /**
    * @param event  The CommunityEvent object to be inserted
    * @return 'insertCommunityEvent'
    */
    public boolean insertCommunityEvent(CommunityEvent event) {
        String sql =
            "INSERT INTO swegrg25.\"Community_Events\" " +
            "(name, description, event_date, user_data_index, address, contact_info, category, attendee_count, coordinate_location, location_notes, permanent) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) " +
            "RETURNING e_data_pk";

        try (PreparedStatement stmt = sessionConnection.prepareStatement(sql)) {
            stmt.setString(1, event.getName());
            stmt.setString(2, event.getDescription());
            stmt.setDate(3, Date.valueOf(event.getEventDate()));
            stmt.setLong(4, event.getUserDataIndex());
            stmt.setString(5, event.getAddress());
            stmt.setString(6, event.getContactInfo());
            stmt.setString(7, event.getCategory());
            stmt.setInt(8, event.getAttendeeCount());
            stmt.setObject(9, event.getCoordinateLocation());
            stmt.setString(10, event.getLocationNotes());
            stmt.setString(11, event.isPermanent());

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                event.seteDataPk(rs.getString("e_data_pk"));  // Store the generated primary key
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // Retrieve a community event by its ID (primary key)
    /**
    * @param eDataPk  The primary key of the community event
    * @return 'getCommunityEventById'
    */
    public CommunityEvent getCommunityEventById(String eDataPk) {
        String sql = "SELECT * FROM swegrg25.\"Community_Events\" WHERE e_data_pk = ?";

        try (PreparedStatement stmt = sessionConnection.prepareStatement(sql)) {
            stmt.setString(1, eDataPk);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return extractCommunityEventFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // Retrieve all community events
    /**
    * @return 'getAllCommunityEvents'
    */
    public List<CommunityEvent> getAllCommunityEvents() {
        List<CommunityEvent> events = new ArrayList<>();
        String sql = "SELECT * FROM swegrg25.\"Community_Events\"";


        try (Statement stmt = sessionConnection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                CommunityEvent event = extractCommunityEventFromResultSet(rs);
                System.out.println("Fetched event: " + event.getName());
                events.add(event);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return events;
    }

    // Helper method to extract a CommunityEvent from a ResultSet
    /**
    * @param rs  The ResultSet containing the data
    * @return 'extractCommunityEventFromResultSet'
    */
    private CommunityEvent extractCommunityEventFromResultSet(ResultSet rs) throws SQLException {
        CommunityEvent event = new CommunityEvent();
        event.seteDataPk(rs.getString("e_data_pk"));
        event.setName(rs.getString("name"));
        event.setDescription(rs.getString("description"));
        event.setEventDate(rs.getDate("event_date").toLocalDate());
        event.setUserDataIndex(rs.getLong("user_data_index"));
        event.setAddress(rs.getString("address"));
        event.setContactInfo(rs.getString("contact_info"));
        event.setCategory(rs.getString("category"));
        event.setAttendeeCount(rs.getInt("attendee_count"));
        event.setCoordinateLocation(rs.getObject("coordinate_location"));
        event.setLocationNotes(rs.getString("location_notes"));
        event.setPermanent(rs.getString("permanent"));
        return event;
    }

    /**
    * @param userId  The ID of the user saving the resource
    * @param resourceDataPk  The primary key of the resource data (e.g., "cc0001")
    * @param userCategory  The category of the resource (e.g., "community_center")
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
